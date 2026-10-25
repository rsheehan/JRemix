package edu.fizz.remixd;

import edu.fizz.remixd.editor.RemixdPrepareRun;
import edu.fizz.remixd.parser.RemixdParser;
import edu.fizz.remixd.parser.RemixdParserBaseVisitor;
import edu.fizz.remixd.runtime.*;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class EvalVisitor extends RemixdParserBaseVisitor<Object> {

    /*
     * This really needs to visit the entire program before executing anything.
     * It should collect all function definitions and all top-level code.
     * The functions get added to the function table.
     * The function table should also include all built-in functions.
     * The top-level code is a block sequence.
     * Only then should the top-level code block be executed.
     */

    // the file name from runEditorText in RemixdPrepareRun
    // this is passed to lots of the nodes created in this class
    private final String fileName;

    /*
    This is the libraryExpression of the program to run, or the real library
    currently being loaded.
     */
    LibraryExpression programLibrary = new LibraryExpression();

    public EvalVisitor(String fileName) {
        super();
        this.fileName = fileName; // was "EVALVISITOR: " + fileName;
    }

    /** ( functionDefinition | statement | setConstant | usingStatement | library | libAssignment )* EOF */
    /* This is where the Remixd functions are added to the function table. */
    @Override
    public LibraryExpression visitProgram(RemixdParser.ProgramContext ctx) {
        /*
        The current library could be the baseLibrary, or the programLibrary
         */
        int n = ctx.getChildCount();
        for (int i = 0; i < n; i++) {
            ParseTree node = ctx.getChild(i);
            if (node instanceof RemixdParser.StatementContext) {
                Expression statement = (Expression)visit(node);
                if (statement != null) {// can be blank statements
                    programLibrary.block.addStatement(statement);
                    checkForUnusedValue(statement, i < n - 2);
                }
            } else if (node instanceof RemixdParser.SetConstantContext) {
                ConstantAssignmentStatement constantAssignment = (ConstantAssignmentStatement) visit(node);
                constantAssignment.setLibraryStoredIn(programLibrary);
                programLibrary.block.addStatement(constantAssignment);
            } else if (node instanceof RemixdParser.FunctionDefinitionContext) {
                RemixdFunction function = (RemixdFunction) visit(node);
                programLibrary.addFunction(function);
            } else if (node instanceof RemixdParser.UsingStatementContext) {
                UsingLibBlock usingLibBlock = (UsingLibBlock)visit(node);
                programLibrary.block.addStatement(usingLibBlock);
            } else if (node instanceof RemixdParser.LibraryContext) {
                LibraryExpression libraryExpression = (LibraryExpression) visit(node);
                programLibrary.block.addStatement(libraryExpression);
            } else if (node instanceof RemixdParser.LibAssignmentContext) {
                AssignmentStatement assignmentStatement = (AssignmentStatement) visit(node);
                if (assignmentStatement instanceof ConstantAssignmentStatement constantAssignmentStatement)
                    constantAssignmentStatement.setLibraryStoredIn(programLibrary);
                programLibrary.block.addStatement(assignmentStatement);
            }
        }
        return programLibrary;
    }

    /** LIBRARY STRING? */
    @Override
    public LibraryExpression visitLibraryName(RemixdParser.LibraryNameContext ctx) {
        LibraryExpression library;
        if (ctx.string() != null) {
            String libName = ctx.string().getText();
            libName = libName.substring(1, libName.length() - 1); // strip off quotes
            try {
                Class libClass = Class.forName(libName);
                library = (LibraryExpression) libClass.getDeclaredConstructor().newInstance();
                library.setJavaFileName(libName);
                return library;
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                     InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                System.err.printf("Class not found: %s%n", libName);
                throw new RuntimeException(e);
            }
        } else {
            library = new LibraryExpression();
            return library;
        }
    }

    /** libraryName LBLOCK EOL* (functionDefinition | statement | setConstant | usingStatement)* RBLOCK */
    public LibraryExpression visitLibrary(RemixdParser.LibraryContext ctx) {
        LibraryExpression library = (LibraryExpression) visit(ctx.libraryName());
        library.setTrueLibrary();
        int n = ctx.getChildCount();
        for (int i = 0; i < n; i++) {
            ParseTree node = ctx.getChild(i);
            if (node instanceof RemixdParser.StatementContext) {
                Expression statement = (Expression)visit(node);
                if (statement != null) {// can be blank statements
                    library.block.addStatement(statement);
                }
            }  else if (node instanceof RemixdParser.SetConstantContext) {
                ConstantAssignmentStatement constantAssignment = (ConstantAssignmentStatement) visit(node);
                constantAssignment.setLibraryStoredIn(library);
                library.block.addStatement(constantAssignment);
            } else if (node instanceof RemixdParser.FunctionDefinitionContext) {
                RemixdFunction function = (RemixdFunction)visit(node);
                library.addFunction(function);
            } else if (node instanceof RemixdParser.UsingStatementContext) {
                LibraryExpression program = programLibrary; // only one level
                programLibrary = library;
                UsingLibBlock usingLibBlock = (UsingLibBlock) visit(node);
                library.block.addStatement(usingLibBlock);
                programLibrary = program;
            }
        }
        return library;
    }

    /** CONSTANT COLON library */
    @Override
    public ConstantAssignmentStatement visitLibAssignment(RemixdParser.LibAssignmentContext ctx) {
        String constName = ctx.CONSTANT().getText();
        LibraryExpression library = (LibraryExpression) visit(ctx.library());
        return new ConstantAssignmentStatement(constName, library);
    }

    /** USING expression (COMMA expression)* usingBlock */
    @Override
    public UsingLibBlock visitUsingStatement(RemixdParser.UsingStatementContext ctx) throws NullPointerException {
        ArrayList<Expression> libraryExpressions = new ArrayList<>();
        LibraryExpression usingBlock;

        int n = ctx.getChildCount();
        for (int i = 1; i < n - 1; i++) { // first node = "using", last = "usingBlock"
            ParseTree node = ctx.getChild(i);
            if (node instanceof RemixdParser.ExpressionContext) {
                libraryExpressions.add((Expression) visit(node));
            }
        }
        try {
            usingBlock = (LibraryExpression) visit(ctx.usingBlock());
        } catch (NullPointerException _) {
            return null;
        }

        UsingLibBlock usingLibBlock = new UsingLibBlock(libraryExpressions.toArray(new Expression[1]), usingBlock);

        for (Function function : usingBlock.functionTable.values()) {
            FunctionInUsing usingFunction = new FunctionInUsing((RemixdFunction) function, usingLibBlock.libraryMap());
            programLibrary.addFunction(usingFunction);
        }
        usingBlock.block.setConstantAssignmentLibrary(programLibrary);
        return usingLibBlock;
    }

    /** LBLOCK (functionDefinition | statement | setConstant)* RBLOCK */
    @Override
    public LibraryExpression visitUsingBlock(RemixdParser.UsingBlockContext ctx) {
        LibraryExpression usingBlock = new LibraryExpression();
        for (int i = 0; i < ctx.getChildCount(); i++) {
            ParseTree node = ctx.getChild(i);
            if (node instanceof RemixdParser.StatementContext) {
                Expression statement = (Expression)visit(node);
                if (statement != null) // can be blank statements
                    usingBlock.block.addStatement(statement);
            }  else if (node instanceof RemixdParser.SetConstantContext) {
                ConstantAssignmentStatement constantAssignment = (ConstantAssignmentStatement) visit(node);
                usingBlock.block.addStatement(constantAssignment);
            } else if (node instanceof RemixdParser.FunctionDefinitionContext) {
                RemixdFunction function = (RemixdFunction)visit(node);
                usingBlock.addFunction(function);
            }
        }
        return usingBlock;
    }

    /** RETURN expression? */
    @Override
    public ReturnStatement visitReturn(RemixdParser.ReturnContext ctx) {
        Expression returnValue = null;
        if (ctx.getChildCount() == 2)
            returnValue = (Expression)visit(ctx.expression());
        return new ReturnStatement(returnValue);
    }

    /** REDO */
    @Override
    public RedoStatement visitRedo(RemixdParser.RedoContext ctx) {
        return new RedoStatement();
    }

    /** functionComment? functionSignature COLON COLON? EOL? blockOfStatements */
    @Override
    public RemixdFunction visitFunctionDefinition(RemixdParser.FunctionDefinitionContext ctx) {
        int colonPosition = 2;
        String funcComment = null;
        if (ctx.getChild(0) instanceof RemixdParser.FunctionCommentContext) {
            funcComment = (String)visit(ctx.functionComment());
            colonPosition++; // because the comment existed
        }
        FunctionName<String> funcSig = (FunctionName<String>)visit(ctx.functionSignature());
        boolean transparent;
        try {
            transparent = ctx.getChild(colonPosition).getText().equals(":");
        } catch (NullPointerException e) {
            return null;
        }
        Block block = (Block)visit(ctx.blockOfStatements());
        return new RemixdFunction(
                funcSig.getAllNames(),
                block,
                funcSig.getParameters(),
                funcSig.getBlockParams(),
                transparent,
                funcComment
        );
    }

    /** DOC_COMMENT EOL */
    @Override
    public String visitFunctionComment(RemixdParser.FunctionCommentContext ctx) {
        String comment = ctx.DOC_COMMENT().getText();
        comment = comment.replace("\t", "");
        return comment.substring(4,comment.length()-3);
    }

    /** sigPart sigPart+ | singleWord */
    @Override
    public FunctionName<String> visitFunctionSignature(RemixdParser.FunctionSignatureContext ctx) {
        FunctionName<String> funcSig = new FunctionName<>();
        int n = ctx.getChildCount();
        for (int i = 0; i < n; i++) {
            ParseTree node = ctx.getChild(i);
            if (node instanceof RemixdParser.SingleWordContext ||  // added single word functions
                    node instanceof RemixdParser.SigWordContext) {
                funcSig.addToName(node.getText());
            } else if (node instanceof RemixdParser.SigParamContext) {
                funcSig.addParam((String)visit(node));
            } else if (node instanceof RemixdParser.SigBlockContext) {
                funcSig.addBlockParam((String)visit(node));
            }
        }
        return funcSig;
    }

    /** CREATE createParams? LBLOCK EOL* object RBLOCK */
    @Override
    public RemixdObjectExpression visitCreateObject(RemixdParser.CreateObjectContext ctx) {
        RemixdObjectExpression objectExpression = (RemixdObjectExpression)visit(ctx.object());
        // gather any create parameters
        if (ctx.createParams() != null) {
            List<String> fieldsToCopy = (List<String>)visit(ctx.createParams());
            int lineNumber = ctx.getStart().getLine() - 1;
            int lineOffset = ctx.getStart().getCharPositionInLine();
            for (String fieldName : fieldsToCopy) {
                VarValueExpression expression = new VarValueExpression(fieldName, fileName, lineNumber, lineOffset);
                FieldAssignmentStatement initStmnt = new FieldAssignmentStatement(fieldName, expression);
                objectExpression.addVarInitialization(initStmnt);
            }
        }
        return objectExpression;
    }

    /** fieldId (COMMA fieldId)* */
    @Override
    public List<String> visitCreateParams(RemixdParser.CreateParamsContext ctx) {
        return getFieldNames(ctx);
    }

    /** EXTEND expression (COMMA createParams)? LBLOCK EOL* object RBLOCK */
    @Override
    public RemixdObjectExpression visitExtendObject(RemixdParser.ExtendObjectContext ctx) {
        Expression expression = (Expression) visit(ctx.expression());
        RemixdObjectExpression extendedObject = (RemixdObjectExpression)visit(ctx.object());
        if (ctx.createParams() != null) {
            List<String> fieldsToCopy = (List<String>)visit(ctx.createParams());
            int lineNumber = ctx.getStart().getLine() - 1;
            int lineOffset = ctx.getStart().getCharPositionInLine();
            for (String fieldName : fieldsToCopy) {
                VarValueExpression varValueExpression = new VarValueExpression(fieldName, fileName, lineNumber, lineOffset);
                FieldAssignmentStatement initStmnt = new FieldAssignmentStatement(fieldName, varValueExpression);
                extendedObject.addVarInitialization(initStmnt);
            }
        }
        return new RemixdExtendedObjectExpression(expression, extendedObject);
    }

    /** field* (getterSetter? getter? setter?) methodDefinition* */
    @Override
    public RemixdObjectExpression visitObject(RemixdParser.ObjectContext ctx) {
        RemixdObjectExpression objectExpr = new RemixdObjectExpression();
        MethodTable methodTable = new MethodTable();

        int n = ctx.getChildCount();
        for (int i = 0; i < n; i++) {
            ParseTree node = ctx.getChild(i);
            if (node instanceof RemixdParser.FieldContext) {
                FieldAssignmentStatement initStmnt = (FieldAssignmentStatement)visit(node);
                objectExpr.addVarInitialization(initStmnt);
            } else if (node instanceof RemixdParser.GetterSetterContext) {
                List<String> getSetNames = (List<String>)visit(node);
                for (String name : getSetNames) {
                    String getMethodName = methodTable.createGetter(name, fileName);
                    LibraryExpression.addMethodName(getMethodName, 1, null);
                    String setMethodName = methodTable.createSetter(name);
                    LibraryExpression.addMethodName(setMethodName, 1, null);
                }
            } else if (node instanceof RemixdParser.GetterContext) {
                List<String> getterNames = (List<String>)visit(node);
                for (String name : getterNames) {
                    String methodName = methodTable.createGetter(name, fileName);
                    LibraryExpression.addMethodName(methodName, 1, null);
                }
            }else if (node instanceof RemixdParser.SetterContext) {
                List<String> setterNames = (List<String>)visit(node);
                for (String name : setterNames) {
                    String methodName = methodTable.createSetter(name);
                    LibraryExpression.addMethodName(methodName, 1, null);
                }
            } else if (node instanceof RemixdParser.MethodDefinitionContext) {
                Method method = (Method)visit(node);
                // add it to the methodTable for the object
                methodTable.addMethod(method);
                // add it to the global table
                LibraryExpression.addMethodName(method.getFirstName(), method.getSelfRef(), method);
            }
        }
        objectExpr.addMethodTable(methodTable);
        return objectExpr;
    }

    /** IDENTIFIER COLON expression EOL+ */
    @Override
    public Expression visitField(RemixdParser.FieldContext ctx) {
        String varName = identifier(ctx.IDENTIFIER().getText());
        Expression expression = (Expression) visit(ctx.expression());
        return new FieldAssignmentStatement(varName, expression);
    }

    /** GETTERSETTER LBLOCK (EOL* fieldId separator*)+ RBLOCK EOL* */
    @Override
    public List<String> visitGetterSetter(RemixdParser.GetterSetterContext ctx) {
        return getFieldNames(ctx);
    }

    /** GETTER LBLOCK (EOL* fieldId separator*)+ RBLOCK EOL* */
    @Override
    public List<String> visitGetter(RemixdParser.GetterContext ctx) {
        return getFieldNames(ctx);
    }

    /** SETTER LBLOCK (EOL* fieldId separator*)+ RBLOCK EOL* */
    @Override
    public List<String> visitSetter(RemixdParser.SetterContext ctx) {
        return getFieldNames(ctx);
    }

    private List<String> getFieldNames(ParserRuleContext ctx) {
        List<String> fieldList = new ArrayList<>();
        String fieldName;
        int n = ctx.getChildCount();
        for (int i = 0; i < n; i++) {
            ParseTree node = ctx.getChild(i);
            if (node instanceof RemixdParser.FieldIdContext) {
                fieldName = (String) visit(node);
                fieldList.add(fieldName);
            }
        }
        return fieldList;
    }

    /** IDENTIFIER */
    @Override
    public String visitFieldId(RemixdParser.FieldIdContext ctx) {
        return identifier(ctx.IDENTIFIER().getText());
    }

    /** functionComment? methodSignature COLON EOL? blockOfStatements EOL* */
    @Override
    public Method visitMethodDefinition(RemixdParser.MethodDefinitionContext ctx) {
        // doesn't deal with pass through "::" methods yet
        // I don't see a need for transparent methods
        int i = 0;
        String methodComment = null;
        if (ctx.getChild(i) instanceof RemixdParser.FunctionCommentContext) {
            methodComment = (String)visit(ctx.functionComment());
            i++;
        }
        MethodName methSig;
        ParseTree node = ctx.getChild(i);
        methSig = (MethodName)visit(node);
        Block block = (Block)visit(ctx.blockOfStatements());
        return new Method(methSig.getAllNames(), block, methSig.getParameters(), methSig.getBlockParams(), methSig.getSelfRef(), methodComment);
    }

    /** methodSigPart+ */
    @Override
    public MethodName visitMethodSignature(RemixdParser.MethodSignatureContext ctx) {
        MethodName methodSig = new MethodName();
        int n = ctx.getChildCount();
        for (int i = 0; i < n; i++) {
            ParseTree node = ctx.getChild(i);
            if (node instanceof RemixdParser.MethSigWordContext) {
                methodSig.addToName(node.getText());
            } else if (node instanceof RemixdParser.MethSigParamContext) {
                methodSig.addParam((String)visit(node));
            } else if (node instanceof RemixdParser.MethSigSelfContext) {
                methodSig.setSelfRefNow();
            } else if (node instanceof RemixdParser.MethSigBlockContext) {
                methodSig.addBlockParam((String)visit(node));
            }
        }
        return methodSig;
    }

    private String identifier(String varName) {
        if (varName.startsWith("'")) {
            varName = varName.substring(1, varName.length() - 1);
        }
        return varName;
    }

    /** LPAREN IDENTIFIER RPAREN */
    @Override
    public String visitMethSigParam(RemixdParser.MethSigParamContext ctx) {
        return identifier(ctx.IDENTIFIER().getText());
    }

    /** LBLOCK IDENTIFIER RBLOCK */
    @Override
    public String visitMethSigBlock(RemixdParser.MethSigBlockContext ctx) {
        return identifier(ctx.IDENTIFIER().getText());
    }

    /** IDENTIFIER | LPAREN IDENTIFIER RPAREN */
    @Override
    public String visitSigParam(RemixdParser.SigParamContext ctx) {
        return identifier(ctx.IDENTIFIER().getText());
    }

    /** LBLOCK IDENTIFIER RBLOCK */
    @Override
    public String visitSigBlock(RemixdParser.SigBlockContext ctx) {
        return identifier(ctx.IDENTIFIER().getText());
    }

    /** LBLOCK statement* RBLOCK */
    @Override
    public Block visitBlockOfStatements(RemixdParser.BlockOfStatementsContext ctx) {
        return produceBlockExpression(ctx);
    }

    /** IDENTIFIER COLON expression */
    @Override
    public AssignmentStatement visitSetVariable(RemixdParser.SetVariableContext ctx) {
        String varName = identifier(ctx.IDENTIFIER().getText());
        Expression expression = (Expression) visit(ctx.expression());
        return new AssignmentStatement(varName, expression);
    }

    /** CONSTANT COLON expression */
    @Override
    public ConstantAssignmentStatement visitSetConstant(RemixdParser.SetConstantContext ctx) {
        String constName = ctx.CONSTANT().getText();
        Expression expression = (Expression) visit(ctx.expression());
        return new ConstantAssignmentStatement(constName, expression);
    }

    /** (expression (COMMA expression)*)? (ENDPRINT | PRINTLN) */
    public PrintStatement visitPrintStatement(RemixdParser.PrintStatementContext ctx) {
        List<Expression> expressionList = new ArrayList<>();
        int n = ctx.getChildCount();
        for (int i = 0; i < n-1; i++) {
            ParseTree node = ctx.getChild(i);
            if (node instanceof RemixdParser.ExpressionContext) {
                expressionList.add((Expression) visit(node));
            }
        }
        String end = ctx.getChild(n-1).getText();
        boolean newline = "\\n↲".contains(end);
        return new PrintStatement(expressionList, newline);
    }

    /** expression ADD expression */
    @Override
    public Expression visitExprAdd(RemixdParser.ExprAddContext ctx) {
        Expression first = (Expression) visit(ctx.expression(0));
        Expression second = (Expression) visit(ctx.expression(1));
        String operator = ctx.ADD().getText().trim();
        int lineNumber = ctx.getStart().getLine() - 1;
        return new BinaryExpression(first, operator, second, fileName, lineNumber);
    }

    /** MINUS expression */
    @Override
    public Expression visitExprMinus(RemixdParser.ExprMinusContext ctx) {
        Expression value = (Expression) visit(ctx.expression());
        SimpleExpression<Long> minusOne = new SimpleExpression<>(-1L);
        int lineNumber = ctx.getStart().getLine() - 1;
        return new BinaryExpression(minusOne, "*", value, fileName, lineNumber);
    }

    /** expression MUL expression */
    @Override
    public Expression visitExprMul(RemixdParser.ExprMulContext ctx) {
        Expression first = (Expression) visit(ctx.expression(0));
        Expression second = (Expression) visit(ctx.expression(1));
        String operator = ctx.MUL().getText().trim();
        int lineNumber = ctx.getStart().getLine() - 1;
        return new BinaryExpression(first, operator, second, fileName, lineNumber);
    }

    /** expression LESS expression */
    @Override
    public Expression visitExprLess(RemixdParser.ExprLessContext ctx) {
        Expression first = (Expression) visit(ctx.expression(0));
        Expression second = (Expression) visit(ctx.expression(1));
        int lineNumber = ctx.getStart().getLine() - 1;
        return new BinaryExpression(first, "<", second, fileName, lineNumber);
    }

    /** expression GREATER expression */
    @Override
    public Expression visitExprGreater(RemixdParser.ExprGreaterContext ctx) {
        Expression first = (Expression) visit(ctx.expression(0));
        Expression second = (Expression) visit(ctx.expression(1));
        int lineNumber = ctx.getStart().getLine() - 1;
        return new BinaryExpression(first, ">", second, fileName, lineNumber);
    }

    /** expression GREATEREQUAL expression */
    @Override
    public Expression visitExprGreatEql(RemixdParser.ExprGreatEqlContext ctx) {
        Expression first = (Expression) visit(ctx.expression(0));
        Expression second = (Expression) visit(ctx.expression(1));
        int lineNumber = ctx.getStart().getLine() - 1;
        return new BinaryExpression(first, ">=", second, fileName, lineNumber);
    }

    /** expression LESSEQUAL expression */
    @Override
    public Expression visitExprLessEql(RemixdParser.ExprLessEqlContext ctx) {
        Expression first = (Expression) visit(ctx.expression(0));
        Expression second = (Expression) visit(ctx.expression(1));
        int lineNumber = ctx.getStart().getLine() - 1;
        return new BinaryExpression(first, "<=", second, fileName, lineNumber);
    }

    /** expression EQUAL expression */
    @Override
    public Expression visitExprEqual(RemixdParser.ExprEqualContext ctx) {
        Expression first = (Expression) visit(ctx.expression(0));
        Expression second = (Expression) visit(ctx.expression(1));
        int lineNumber = ctx.getStart().getLine() - 1;
        return new BinaryExpression(first, "=", second, fileName, lineNumber);
    }

    /** expression NOTEQUAL expression */
    @Override
    public Expression visitExprNotEql(RemixdParser.ExprNotEqlContext ctx) {
        Expression first = (Expression) visit(ctx.expression(0));
        Expression second = (Expression) visit(ctx.expression(1));
        int lineNumber = ctx.getStart().getLine() - 1;
        return new BinaryExpression(first, "!=", second, fileName, lineNumber);
    }

    /** expression CONCAT expression */
    @Override
    public Expression visitExprConcat(RemixdParser.ExprConcatContext ctx) {
        Expression first = (Expression) visit(ctx.expression(0));
        Expression second = (Expression) visit(ctx.expression(1));
//        String fileName = RemixdPrepareRun.getFileName();
        int lineNumber = ctx.getStart().getLine() - 1;
        int lineOffset = ctx.getStart().getCharPositionInLine();
        FunctionCallExpression concatCall = new FunctionCallExpression(fileName, lineNumber, lineOffset);
//        FunctionCallExpression concatCall = new FunctionCallExpression();
        concatCall.addParam(first);
        concatCall.addToName(ctx.CONCAT().getText().trim()); // need to trim because of spaces
        concatCall.addParam(second);
        return concatCall;
    }

    /** LPAREN EOL* expression EOL* RPAREN */
    @Override
    public Expression visitExprParen(RemixdParser.ExprParenContext ctx) {
        return (Expression) visit(ctx.expression());
    }

    /** IDENTIFIER (from expression) */ // this should be a variable reference
    @Override
    public Expression  visitExprVar(RemixdParser.ExprVarContext ctx) {
        String varName = identifier(ctx.IDENTIFIER().getText());
        int lineNumber = ctx.getStart().getLine() - 1;
        int lineOffset = ctx.getStart().getCharPositionInLine();
        return new VarValueExpression(varName, fileName, lineNumber, lineOffset);
    }

    /** CONSTANT (from expression) */
    @Override
    public Expression visitExprConstant(RemixdParser.ExprConstantContext ctx) {
        String constantName = ctx.CONSTANT().getText();
        //        constantValue.setLineNumber(ctx.getStart().getLine() - 1);
        int lineNumber = ctx.getStart().getLine() - 1;
        return new ConstantValueExpression(constantName, lineNumber);
    }

    /** SELFREF (from expression) */
    @Override
    public Expression visitExprSelf(RemixdParser.ExprSelfContext ctx) {
        return new SelfReference();
    }

    /** NUMBER (from expression) */
    @Override
    public Expression visitExprNumber(RemixdParser.ExprNumberContext ctx) {
        Expression number;
        try {
            number = produceLongExpression(ctx.NUMBER().getText());
        } catch (NumberFormatException exception) {
            number = produceDoubleExpression(ctx.NUMBER().getText());
        }
        return number;
    }

    /** WORDPRODUCT (from expression) */
    @Override
    public Expression visitExprWordProduct(RemixdParser.ExprWordProductContext ctx) {
        String[] numberAndWord = splitNumberAndWord(ctx.getText());
        Expression number;
        Expression word;
        try {
            number = produceLongExpression(numberAndWord[0]);
        } catch (NumberFormatException exception) {
            number = produceDoubleExpression(numberAndWord[0]);
        }
        if (numberAndWord[1].equals("π") || numberAndWord[1].equals("pi")) {
            word = produceDoubleExpression("π");
        } else {
            int lineNumber = ctx.getStart().getLine() - 1;
            int lineOffset = ctx.getStart().getCharPositionInLine();
            word = new VarValueExpression(numberAndWord[1], fileName, lineNumber, lineOffset);
        }
        int lineNumber = ctx.getStart().getLine() - 1;
        return new BinaryExpression(number, "*", word, fileName, lineNumber);
    }

    private String[] splitNumberAndWord(String productWord) {
        StringBuilder product;
        String word;
        product = new StringBuilder();
        int i = 0;
        char c = productWord.charAt(i);
        if (c == '-') {
            product.append(c);
            c = productWord.charAt(++i);
        }
        while (Character.isDigit(c)) {
            product.append(c);
            c = productWord.charAt(++i);
        }
        if (c == '.') {
            product.append(c);
            c = productWord.charAt(++i);
        }
        while (Character.isDigit(c)) {
            product.append(c);
            c = productWord.charAt(++i);
        }
        if (productWord.substring(i).equals("π"))
            word = "π";
        else // take off the single quotes
            word = productWord.substring(i + 1, productWord.length() - 1);
        return new String[] {product.toString(), word};
    }

    /** NULL (from expression) */
    @Override
    public Expression visitExprNull(RemixdParser.ExprNullContext ctx) {
        return RemixdNull.value();
    }

    /** BOOLEAN (from expression) */
    @Override
    public Expression visitExprBoolean(RemixdParser.ExprBooleanContext ctx) {
        return produceBooleanExpression(ctx.BOOLEAN().getText());
    }

    /** string (from expression) */
    @Override
    public Expression visitExprString(RemixdParser.ExprStringContext ctx) {
        return produceStringExpression(ctx.string().getText());
    }

    /** blockOfStatements (from expression) */
    @Override
    public Block visitExprBlock(RemixdParser.ExprBlockContext ctx) {
        // this is just the super version, need to go down a level to remove []
        return (Block)visitChildren(ctx);
    }

    /** IDENTIFIER (from callPart) */
    @Override
    public Expression visitCallVar(RemixdParser.CallVarContext ctx) {
        String varName = identifier(ctx.IDENTIFIER().getText());
        int lineNumber = ctx.getStart().getLine() - 1;
        int lineOffset = ctx.getStart().getCharPositionInLine();
        return new VarValueExpression(varName, fileName, lineNumber, lineOffset);
    }

    /** MINUS expression (from callPart) */
    @Override
    public Expression visitCallMinusExpr(RemixdParser.CallMinusExprContext ctx) {
        Expression value = (Expression) visit(ctx.expression());
        SimpleExpression<Long> minusOne = new SimpleExpression<>(-1L);
        int lineNumber = ctx.getStart().getLine() - 1;
        return new BinaryExpression(minusOne, "*", value, fileName, lineNumber);
    }

    /** CONSTANT (from callPart) */
    @Override
    public Expression visitCallConstant(RemixdParser.CallConstantContext ctx) {
        String constantName = ctx.CONSTANT().getText();
        int lineNumber = ctx.getStart().getLine() - 1;
        return new ConstantValueExpression(constantName, lineNumber);
    }

    /** callPart callPart+ | singleWord */
    @Override
    public Expression visitFunctionCall(RemixdParser.FunctionCallContext ctx) {
//        String fileName = RemixdPrepareRun.getFileName();
        int lineNumber = ctx.getStart().getLine() - 1;
        int lineOffset = ctx.getStart().getCharPositionInLine();
        FunctionCallExpression funcCall = new FunctionCallExpression(fileName, lineNumber, lineOffset);
        int n = ctx.getChildCount();
        for (int i=0; i<n; i++) {
            ParseTree node = ctx.getChild(i);
            if (node instanceof RemixdParser.SingleWordContext || // added single word functions
                    node instanceof RemixdParser.CallWordContext) {
                funcCall.addToName(node.getText());
            } else if (node instanceof RemixdParser.CallSelfContext) {
                funcCall.addParam(new SelfReference());
            } else if (node instanceof RemixdParser.CallBlockContext) {
                Expression expression = (Expression)visit(node);
                funcCall.addBlockParam(expression);
            } else if (node instanceof RemixdParser.CallConstantContext ||
                    node instanceof RemixdParser.CallVarContext ||
                    node instanceof RemixdParser.CallMinusExprContext ||
                    node instanceof RemixdParser.CallParamContext ||
                    node instanceof RemixdParser.CallNumberContext ||
                    node instanceof RemixdParser.CallWordProductContext ||
                    node instanceof RemixdParser.CallNullContext ||
                    node instanceof RemixdParser.CallBooleanContext ||
                    node instanceof RemixdParser.CallStringContext ||
                    node instanceof RemixdParser.CallListContext ||
                    node instanceof RemixdParser.CallMapContext) {
                Expression expression = (Expression)visit(node);
                funcCall.addParam(expression);
            }
        }
//        System.err.printf("Function - %s - call on line: %d%n", funcCall.toString(), lineNumber);
        return funcCall;
    }

    /** LPAREN EOL* expression EOL* RPAREN */
    @Override
    public Expression visitCallParam(RemixdParser.CallParamContext ctx) {
        return (Expression) visit(ctx.expression());
    }

    /** NUMBER (from callPart) */
    @Override
    public Expression visitCallNumber(RemixdParser.CallNumberContext ctx) {
        Expression number;
        try {
            number = produceLongExpression(ctx.NUMBER().getText());
        } catch (NumberFormatException exception) {
            number = produceDoubleExpression(ctx.NUMBER().getText());
        }
        return number;
    }

    /** WORDPRODUCT (from callPart) */
    @Override
    public Expression visitCallWordProduct(RemixdParser.CallWordProductContext ctx) {
        String[] numberAndWord = splitNumberAndWord(ctx.getText());
        Expression number;
        Expression word;
        try {
            number = produceLongExpression(numberAndWord[0]);
        } catch (NumberFormatException exception) {
            number = produceDoubleExpression(numberAndWord[0]);
        }
        if (numberAndWord[1].equals("π") || numberAndWord[1].equals("pi")) {
            word = produceDoubleExpression("π");
        } else {
            int lineNumber = ctx.getStart().getLine() - 1;
            int lineOffset = ctx.getStart().getCharPositionInLine();
            word = new VarValueExpression(numberAndWord[1], fileName, lineNumber, lineOffset);
        }
        int lineNumber = ctx.getStart().getLine() - 1;
        return new BinaryExpression(number, "*", word, fileName, lineNumber);
    }

    /** NULL (from callPart) */
    @Override
    public Expression visitCallNull(RemixdParser.CallNullContext ctx) {
        return RemixdNull.value();
    }

    /** BOOLEAN (from callPart) */
    @Override
    public Expression visitCallBoolean(RemixdParser.CallBooleanContext ctx) {
        return produceBooleanExpression(ctx.BOOLEAN().getText());
    }

    /** string (from callPart) */
    @Override
    public Expression visitCallString(RemixdParser.CallStringContext ctx) {
        return produceStringExpression(ctx.string().getText());
    }

    /** blockOfStatements (from callPart) */
    @Override
    public Block visitCallBlock(RemixdParser.CallBlockContext ctx) {
        // this is just the super version, need to go down a level to remove []
        return (Block)visitChildren(ctx);
    }

    /** list (from callPart) */
    @Override
    public Object visitCallList(RemixdParser.CallListContext ctx) {
        return visit(ctx.list());
    }

    /** map (from callPart) */
    @Override
    public Object visitCallMap(RemixdParser.CallMapContext ctx) {
        return visit(ctx.map());
    }

    /** LBRACE listContents RBRACE */
    @Override
    public Object visitList(RemixdParser.ListContext ctx) {
        return visit(ctx.listContents());
    }

    /** (expression (COMMA expression)*)? */
    @Override
    public RemixdListExpression visitCommaList(RemixdParser.CommaListContext ctx) {
        return produceListExpression(ctx);
    }

    /** LBLOCK EOL* (expression (separator EOL* expression)*)* RBLOCK EOL? */
    @Override
    public RemixdListExpression visitBlockList(RemixdParser.BlockListContext ctx) {
        return produceListExpression(ctx);
    }

    /** EOL* */
    @Override
    public RemixdListExpression visitEmptyList(RemixdParser.EmptyListContext ctx) {
        return new RemixdListExpression(null);
    }

    /** LBRACE mapContents RBRACE */
    @Override
    public Object visitMap(RemixdParser.MapContext ctx) {
        return visit(ctx.mapContents());
    }

    /** keyValue (COMMA keyValue)* */
    @Override
    public RemixdMapExpression visitCommaMap(RemixdParser.CommaMapContext ctx) {
        return produceMapExpression(ctx);
    }

    /** LBLOCK EOL* keyValue (separator EOL* keyValue)* RBLOCK EOL? */
    @Override
    public RemixdMapExpression visitBlockMap(RemixdParser.BlockMapContext ctx) {
        return produceMapExpression(ctx);
    }

    /** IDENTIFIER listPart+
     * Access to both list elements and map elements.
     * If there is only one listPart it could be a function call instead.
     * This is taken care of at runtime; see GetElementExpression.evaluate.
     * TODO: Remove runtime check as should not be necessary anymore.
     */
    @Override
    public GetElementExpression visitListElement(RemixdParser.ListElementContext ctx) {
        String listName = identifier(ctx.IDENTIFIER().getText());
        // need to loop through possibly multiple listParts
        int n = ctx.getChildCount();
        List listParts = new ArrayList<>();
        for (int i=0; i<n; i++) {
            ParseTree node = ctx.getChild(i);
            if (node instanceof RemixdParser.ListPartContext) {
                Object listPartID = visit(node);
                listParts.add(listPartID);
            }
        }
        return new GetElementExpression(listName, listParts);
    }

    /** IDENTIFIER listPart+ COLON expression
     * Set both list elements and map elements.
     */
    @Override
    public SetElementExpression visitSetListElement(RemixdParser.SetListElementContext ctx) {
        String listName = identifier(ctx.IDENTIFIER().getText());
        // need to loop through possibly multiple listParts
        List listParts = new ArrayList<>();
        int i = 1;
        ParseTree node = ctx.getChild(i);
        while (node instanceof RemixdParser.ListPartContext) {
            Object listPartID = visit(node);
            listParts.add(listPartID);
            node = ctx.getChild(++i);
        }
        Expression expression = (Expression) visit(ctx.expression());
        return new SetElementExpression(listName, listParts, expression);
    }

    /** LBRACE expression RBRACE */
    @Override
    public Object visitListPartExpr(RemixdParser.ListPartExprContext ctx) {
        return visit(ctx.expression());
    }

    /* Helper methods */

    private SimpleExpression<String> produceStringExpression(String string) {
        string = string.substring(1, string.length() - 1); // remove surrounding ""
        string = string.replace("\\\"", "\""); // deal with escape chars
        string = string.replace("\\n", "\n");
        return new SimpleExpression<>(string);
    }

    private SimpleExpression<Long> produceLongExpression(String longString) {
        return new SimpleExpression<>(Long.parseLong(longString));
    }

    private SimpleExpression<Double> produceDoubleExpression(String doubleString) {
        if (doubleString.equals("π") || doubleString.equals("pi"))
            return new SimpleExpression<>(Math.PI);
        return new SimpleExpression<>(Double.parseDouble(doubleString));
    }

    private SimpleExpression<Boolean> produceBooleanExpression(String boolString) {
        return new SimpleExpression<>(Boolean.parseBoolean(boolString));
    }

    private Block produceBlockExpression(ParseTree ctx) {
        Block block = new Block();
        int n = ctx.getChildCount();
        for (int i = 0; i < n; i++) {
            ParseTree node = ctx.getChild(i);
            if (node instanceof RemixdParser.StatementContext) {
                Expression statement = (Expression)visit(node);
                if (statement != null) { // can be blank statements
                    block.addStatement(statement);
                    checkForUnusedValue(statement, i < n - 2);
                }
            }
        }
        return block;
    }

    // The last statement is not a problem.
    private void checkForUnusedValue(Expression statement, boolean notLast) {
        if (statement instanceof VarValueExpression varValueExpression && notLast) {
            String variableName = varValueExpression.toString();
            int lineNumber = varValueExpression.getLineNumber();
            int lineOffset = varValueExpression.getOffSet();
            if (!fileName.equals(RemixdPrepareRun.INTERACTIVETEXT)) {
                System.err.format("%s - line: %d, offset: %d%n", fileName, lineNumber, lineOffset);
                System.err.printf("\tWarning: Variable %s value not used.%n", variableName);
            }
        }
    }

    public RemixdListExpression produceListExpression(ParseTree ctx) {
        RemixdList<Expression> list = new RemixdList<>();
        int n = ctx.getChildCount();
        for (int i = 0; i < n; i++) {
            ParseTree node = ctx.getChild(i);
            if (node instanceof RemixdParser.ExpressionContext) {
                list.add(visit(node));
            }
        }
        return new RemixdListExpression(list);
    }

    public RemixdMapExpression produceMapExpression(ParseTree ctx) {
        RemixdMap<Expression, Expression> map = new RemixdMap<>();
        int n = ctx.getChildCount();
        for (int i = 0; i < n; i++) {
            ParseTree node = ctx.getChild(i);
            if (node instanceof RemixdParser.KeyValueContext keyValue) {
//                String key = keyValue.string().getText(); // key());
                Expression key = (Expression)visit(keyValue.expression(0)); // key());
//                key = key.substring(1,key.length() - 1); // chop off "s at both ends
                Expression value = (Expression)visit(keyValue.expression(1));
                map.put(key, value);
            }
        }
        return new RemixdMapExpression(map);
    }

}