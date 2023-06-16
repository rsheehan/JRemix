package edu.fizz.remix;

import edu.fizz.remix.parser.RemixParser;
import edu.fizz.remix.parser.RemixParserBaseVisitor;
import edu.fizz.remix.runtime.Runtime;
import edu.fizz.remix.runtime.*;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EvalVisitor extends RemixParserBaseVisitor<Object> {

    /*
     * This really needs to visit the entire program before executing anything.
     * It should collect all function definitions and all top-level code.
     * The functions get added to the function table.
     * The function table should also include all built-in functions.
     * The top-level code is a block sequence.
     * Only then should the top-level code block be executed.
     */

    // private Map<String, Object> currentVariableContext;

    /** EOL* LBLOCK ( functionDefinition | statement )* RBLOCK EOF */
    /* This is where the Remix functions are added to the function table. */
    @Override
    public Block visitProgram(RemixParser.ProgramContext ctx) {
        Block theProgram = new Block();
        int n = ctx.getChildCount();
        for (int i = 0; i < n; i++) {
            ParseTree node = ctx.getChild(i);
            if (node instanceof RemixParser.StatementContext) {
                Expression statement = (Expression)visit(node);
                if (statement != null) // can be blank statements
                    theProgram.addStatement(statement);
            } else if (node instanceof RemixParser.FunctionDefinitionContext) {
                RemixFunction function = (RemixFunction)visit(node);
                Runtime.addFunction(function);
            }
        }
        return theProgram;
    }

    /** RETURN expression? */
    @Override
    public ReturnStatement visitReturn(RemixParser.ReturnContext ctx) {
        Expression returnValue = null;
        if (ctx.getChildCount() == 2)
            returnValue = (Expression)visit(ctx.expression());
        return new ReturnStatement(returnValue);
    }

    /** REDO */
    @Override
    public RedoStatement visitRedo(RemixParser.RedoContext ctx) {
        return new RedoStatement();
    }

    /** functionSignature COLON COLON? blockOfStatements */
    @Override
    public RemixFunction visitFunctionDefinition(RemixParser.FunctionDefinitionContext ctx) {
        int colonPosition = 2;
        String funcComment = null;
        if (ctx.getChild(0) instanceof RemixParser.FunctionCommentContext) {
            funcComment = (String)visit(ctx.functionComment());
            colonPosition++; // because the comment existed
//            System.out.println(funcComment);
        }
        @SuppressWarnings ("unchecked")
        FunctionName<String> funcSig = (FunctionName<String>)visit(ctx.functionSignature());
        boolean transparent = ctx.getChild(colonPosition).getText().equals(":");
        Block block = (Block)visit(ctx.blockOfStatements());
        return new RemixFunction(
                funcSig.getAllNames(),
                block,
                funcSig.getParameters(),
                funcSig.getBlockParams(),
                transparent,
                funcComment
        );
    }

    /** DOC_COMMENT */
    @Override
    public String visitFunctionComment(RemixParser.FunctionCommentContext ctx) {
        String comment = ctx.DOC_COMMENT().getText();
        return comment.substring(4,comment.length()-4);
    }

    /** sigPart sigPart+ */
    @Override
    public FunctionName<String> visitFunctionSignature(RemixParser.FunctionSignatureContext ctx) {
        FunctionName<String> funcSig = new FunctionName<>();
        int n = ctx.getChildCount();
        for (int i = 0; i < n; i++) {
            ParseTree node = ctx.getChild(i);
            if (node instanceof RemixParser.SigWordContext) {
                funcSig.addToName(node.getText());
            } else if (node instanceof RemixParser.SigParamContext) {
                funcSig.addParam((String)visit(node));
            } else if (node instanceof RemixParser.SigBlockContext) {
                funcSig.addBlockParam((String)visit(node));
            }
        }
        return funcSig;
    }

    /** CREATE LBLOCK EOL* object RBLOCK */
    @Override
    public RemixObjectExpression visitCreateObject(RemixParser.CreateObjectContext ctx) {
        return (RemixObjectExpression)visit(ctx.object());
    }

    /** field* getter? methodDefinition* */
    @Override
    public RemixObjectExpression visitObject(RemixParser.ObjectContext ctx) {
        RemixObjectExpression objectExpr = new RemixObjectExpression();
        MethodTable methodTable = new MethodTable();
        int n = ctx.getChildCount();
        for (int i = 0; i < n; i++) {
            ParseTree node = ctx.getChild(i);
            if (node instanceof RemixParser.FieldContext) {
                AssignmentStatement initStmnt = (AssignmentStatement)visit(node);
                objectExpr.addVarInitialization(initStmnt);
            } else if (node instanceof RemixParser.GetterSetterContext) {
                @SuppressWarnings("unchecked")
                List<String> getSetNames = (List<String>)visit(node);
                for (String name : getSetNames) {
                    String getMethodName = methodTable.createGetter(name);
                    Runtime.addMethodName(getMethodName, 1);
                    String setMethodName = methodTable.createSetter(name);
                    Runtime.addMethodName(setMethodName, 1);
                }
            } else if (node instanceof RemixParser.GetterContext) {
                @SuppressWarnings("unchecked")
                List<String> getterNames = (List<String>)visit(node);
                for (String name : getterNames) {
                    String methodName = methodTable.createGetter(name);
                    Runtime.addMethodName(methodName, 1);
                }
            }else if (node instanceof RemixParser.SetterContext) {
                @SuppressWarnings("unchecked")
                List<String> setterNames = (List<String>)visit(node);
                for (String name : setterNames) {
                    String methodName = methodTable.createSetter(name);
                    Runtime.addMethodName(methodName, 1);
                }
            } else if (node instanceof RemixParser.MethodDefinitionContext) {
                Method method = (Method)visit(node);
                // add it to the methodTable for the object
                methodTable.addMethod(method);
                // add it to the global table
                Runtime.addMethodName(method.getName(), method.getSelfRef());
            }
        }
        objectExpr.addMethodTable(methodTable);
        return objectExpr;
    }

    /** WORD COLON expression EOL+ */
    @Override
    public Expression visitField(RemixParser.FieldContext ctx) {
        String varName = ctx.WORD().getText();
        Expression expression = (Expression) visit(ctx.expression());
        return new AssignmentStatement(varName, expression);
    }

    /** GETTERSETTER LBLOCK (EOL* fieldId separator*)+ RBLOCK EOL* */
    @Override
    public List<String> visitGetterSetter(RemixParser.GetterSetterContext ctx) {
        return getFieldNames(ctx);
    }

    /** GETTER LBLOCK (EOL* fieldId EOL*)+ RBLOCK EOL* */
    @Override
    public List<String> visitGetter(RemixParser.GetterContext ctx) {
        return getFieldNames(ctx);
    }

    /** SETTER LBLOCK (EOL* fieldId EOL*)+ RBLOCK EOL* */
    @Override
    public List<String> visitSetter(RemixParser.SetterContext ctx) {
        return getFieldNames(ctx);
    }

    private List<String> getFieldNames(ParserRuleContext ctx) {
        List<String> fieldList = new ArrayList<>();
        String fieldName;
        int n = ctx.getChildCount();
        for (int i = 0; i < n; i++) {
            ParseTree node = ctx.getChild(i);
            if (node instanceof RemixParser.FieldIdContext) {
                fieldName = (String) visit(node);
                fieldList.add(fieldName);
            }
        }
        return fieldList;
    }

    /** WORD */
    @Override
    public String visitFieldId(RemixParser.FieldIdContext ctx) {
        return ctx.WORD().getText();
    }

    /** methodSignature COLON EOL? blockOfStatements */
    @Override
    public Method visitMethodDefinition(RemixParser.MethodDefinitionContext ctx) {
        // doesn't deal with pass through "::" methods yet
        // I don't see a need for transparent methods
        MethodName methSig = (MethodName)visit(ctx.methodSignature());
        Block block = (Block)visit(ctx.blockOfStatements());
        return new Method(methSig.getAllNames(), block, methSig.getParameters(), methSig.getBlockParams(), methSig.getSelfRef());
    }

    /** methodSigPart methodSigPart+ */
    @Override
    public MethodName visitMethodSignature(RemixParser.MethodSignatureContext ctx) {
        MethodName methodSig = new MethodName();
        int n = ctx.getChildCount();
        for (int i = 0; i < n; i++) {
            ParseTree node = ctx.getChild(i);
            if (node instanceof RemixParser.MethSigWordContext) {
                methodSig.addToName(node.getText());
            } else if (node instanceof RemixParser.MethSigParamContext) {
                methodSig.addParam((String)visit(node));
            } else if (node instanceof RemixParser.MethSigSelfContext) {
                methodSig.setSelfRefNow();
            } else if (node instanceof RemixParser.MethSigBlockContext) {
                methodSig.addBlockParam((String)visit(node));
            }
        }
        return methodSig;
    }

    /** LPAREN WORD RPAREN */
    @Override
    public String visitMethSigParam(RemixParser.MethSigParamContext ctx) {
        return ctx.WORD().getText();
    }

    /** LBLOCK WORD RBLOCK */
    @Override
    public String visitMethSigBlock(RemixParser.MethSigBlockContext ctx) {
        return ctx.WORD().getText();
    }

    /** LPAREN WORD RPAREN */
    @Override
    public String visitSigParam(RemixParser.SigParamContext ctx) {
        return ctx.WORD().getText();
    }

    /** LBLOCK WORD RBLOCK */
    @Override
    public String visitSigBlock(RemixParser.SigBlockContext ctx) {
        return ctx.WORD().getText();
    }

    /** LBLOCK statement* RBLOCK */
    @Override
    public Block visitBlockOfStatements(RemixParser.BlockOfStatementsContext ctx) {
        return produceBlockExpression(ctx);
    }

    /** WORD COLON expression EOL */
    @Override
    public AssignmentStatement visitSetVariable(RemixParser.SetVariableContext ctx) {
        String varName = ctx.WORD().getText();
        Expression expression = (Expression) visit(ctx.expression());
        return new AssignmentStatement(varName, expression);
    }

    /** (expression (COMMA expression)*)? PRINTLN */
    public PrintStatement visitPrntStatement(RemixParser.PrntStatementContext ctx) {
        List<Expression> expressionList = new ArrayList<>();
        int n = ctx.getChildCount();
        for (int i = 0; i < n-1; i++) {
            ParseTree node = ctx.getChild(i);
            if (node instanceof RemixParser.ExpressionContext) {
                expressionList.add((Expression) visit(node));
            }
        }
        String end = ctx.getChild(n-1).getText();
        boolean newline = "\\n↲".contains(end);
        return new PrintStatement(expressionList, newline);
    }

    /** expression ADD expression */
    @Override
    public Expression visitExprAdd(RemixParser.ExprAddContext ctx) {
        Expression first = (Expression) visit(ctx.expression(0));
        Expression second = (Expression) visit(ctx.expression(1));
        return new BinaryExpression(first, "+", second);
    }

    /** expression SUB expression */
    @Override
    public Expression visitExprSub(RemixParser.ExprSubContext ctx) {
        Expression first = (Expression) visit(ctx.expression(0));
        Expression second = (Expression) visit(ctx.expression(1));
        return new BinaryExpression(first, "-", second);
    }

    /** expression MUL expression */
    @Override
    public Expression visitExprMul(RemixParser.ExprMulContext ctx) {
        Expression first = (Expression) visit(ctx.expression(0));
        Expression second = (Expression) visit(ctx.expression(1));
        return new BinaryExpression(first, "*", second);
    }

    /** expression DIV expression */
    @Override
    public Expression visitExprDiv(RemixParser.ExprDivContext ctx) {
        Expression first = (Expression) visit(ctx.expression(0));
        Expression second = (Expression) visit(ctx.expression(1));
        return new BinaryExpression(first, "/", second);
    }

    /** expression MOD expression */
    @Override
    public Expression visitExprMod(RemixParser.ExprModContext ctx) {
        Expression first = (Expression) visit(ctx.expression(0));
        Expression second = (Expression) visit(ctx.expression(1));
        return new BinaryExpression(first, "%", second);
    }

    /** expression LESS expression */
    @Override
    public Expression visitExprLess(RemixParser.ExprLessContext ctx) {
        Expression first = (Expression) visit(ctx.expression(0));
        Expression second = (Expression) visit(ctx.expression(1));
        return new BinaryExpression(first, "<", second);
    }

    /** expression GREATER expression */
    @Override
    public Expression visitExprGreater(RemixParser.ExprGreaterContext ctx) {
        Expression first = (Expression) visit(ctx.expression(0));
        Expression second = (Expression) visit(ctx.expression(1));
        return new BinaryExpression(first, ">", second);
    }

    /** expression GREATEREQUAL expression */
    @Override
    public Expression visitExprGreatEql(RemixParser.ExprGreatEqlContext ctx) {
        Expression first = (Expression) visit(ctx.expression(0));
        Expression second = (Expression) visit(ctx.expression(1));
        return new BinaryExpression(first, ">=", second);
    }

    /** expression LESSEQUAL expression */
    @Override
    public Expression visitExprLessEql(RemixParser.ExprLessEqlContext ctx) {
        Expression first = (Expression) visit(ctx.expression(0));
        Expression second = (Expression) visit(ctx.expression(1));
        return new BinaryExpression(first, "<=", second);
    }

    /** expression EQUAL expression */
    @Override
    public Expression visitExprEqual(RemixParser.ExprEqualContext ctx) {
        Expression first = (Expression) visit(ctx.expression(0));
        Expression second = (Expression) visit(ctx.expression(1));
        return new BinaryExpression(first, "=", second);
    }

    /** expression NOTEQUAL expression */
    @Override
    public Expression visitExprNotEql(RemixParser.ExprNotEqlContext ctx) {
        Expression first = (Expression) visit(ctx.expression(0));
        Expression second = (Expression) visit(ctx.expression(1));
        return new BinaryExpression(first, "!=", second);
    }

    /** expression CONCAT expression */
    @Override
    public Expression visitExprConcat(RemixParser.ExprConcatContext ctx) {
        Expression first = (Expression) visit(ctx.expression(0));
        Expression second = (Expression) visit(ctx.expression(1));
        FunctionCallExpression concatCall = new FunctionCallExpression();
        concatCall.addParam(first);
        concatCall.addToName(ctx.CONCAT().getText().trim()); // need to trim because of spaces
        concatCall.addParam(second);
        return concatCall;
    }

    /** LPAREN EOL* expression EOL* RPAREN */
    @Override
    public Expression visitExprParen(RemixParser.ExprParenContext ctx) {
        return (Expression) visit(ctx.expression());
    }

    /** WORD (from expression) */ // this should be a variable reference
    @Override
    public Expression visitExprVar(RemixParser.ExprVarContext ctx) {
        String varName = ctx.WORD().getText();
        return new VarValueExpression(varName);
    }

    /** NUMBER (from expression) */
    @Override
    public Expression visitExprNumber(RemixParser.ExprNumberContext ctx) {
        Expression number;
        try {
            number = produceLongExpression(ctx.NUMBER().getText());
        } catch (NumberFormatException exception) {
            number = produceDoubleExpression(ctx.NUMBER().getText());
        }
        return number;
    }

    /** BOOLEAN (from expression) */
    @Override
    public Expression visitExprBoolean(RemixParser.ExprBooleanContext ctx) {
        return produceBooleanExpression(ctx.BOOLEAN().getText());
    }

    /** STRING (from expression) */
    @Override
    public Expression visitExprString(RemixParser.ExprStringContext ctx) {
        return produceStringExpression(ctx.STRING().getText());
    }

    /** blockOfStatements (from expression) */
    @Override
    public Block visitExprBlock(RemixParser.ExprBlockContext ctx) {
        // this is just the super version, need to go down a level to remove []
        return (Block)visitChildren(ctx);
    }

    /** WORD POSSESSIVE WORD */
    @Override
    public Expression visitGetterMethodCall(RemixParser.GetterMethodCallContext ctx) {
        FunctionCallExpression getterCall = new FunctionCallExpression();
        // first WORD is the object name
        getterCall.addParam(new VarValueExpression(ctx.WORD(0).getText()));
        // second WORD is the field name in the object
        getterCall.addToName(ctx.WORD(1).getText());
        return getterCall;
    }

    /** WORD POSSESSIVE WORD COLON expression */
    @Override
    public Expression visitSetterMethodCall(RemixParser.SetterMethodCallContext ctx) {
        FunctionCallExpression setterCall = new FunctionCallExpression();
        // first WORD is the object name
        setterCall.addParam(new VarValueExpression(ctx.WORD(0).getText()));
        // second WORD is the field name in the object
        setterCall.addToName(ctx.WORD(1).getText());
        // now add the expression
        setterCall.addParam((Expression) visit(ctx.expression()));
        return setterCall;
    }

    /** callPart callPart+ */
    @Override
    public Expression visitFunctionCall(RemixParser.FunctionCallContext ctx) {
        FunctionCallExpression funcCall = new FunctionCallExpression();
        int n = ctx.getChildCount();
        for (int i=0; i<n; i++) {
            ParseTree node = ctx.getChild(i);
            if (node instanceof RemixParser.CallWordContext) {
                funcCall.addToName(node.getText());
            } else if (node instanceof RemixParser.CallSelfContext) {
                funcCall.addParam(new SelfReference());
            } else if (node instanceof RemixParser.CallBlockContext) {
                Expression expression = (Expression)visit(node);
                funcCall.addBlockParam(expression);
            } else if (node instanceof RemixParser.CallParamContext ||
                    node instanceof RemixParser.CallNumberContext ||
                    node instanceof RemixParser.CallBooleanContext ||
                    node instanceof RemixParser.CallStringContext ||
                    node instanceof RemixParser.CallListContext ||
                    node instanceof RemixParser.CallMapContext) {
                Expression expression = (Expression)visit(node);
                funcCall.addParam(expression);
            }
        }
        return funcCall;
    }

    /** LPAREN EOL* expression EOL* RPAREN */
    @Override
    public Expression visitCallParam(RemixParser.CallParamContext ctx) {
        return (Expression) visit(ctx.expression());
    }

    /** NUMBER (from callPart) */
    @Override
    public Expression visitCallNumber(RemixParser.CallNumberContext ctx) {
        Expression number;
        try {
            number = produceLongExpression(ctx.NUMBER().getText());
        } catch (NumberFormatException exception) {
            number = produceDoubleExpression(ctx.NUMBER().getText());
        }
        return number;
    }

    /** BOOLEAN (from callPart) */
    @Override
    public Expression visitCallBoolean(RemixParser.CallBooleanContext ctx) {
        return produceBooleanExpression(ctx.BOOLEAN().getText());
    }

    /** STRING (from callPart) */
    @Override
    public Expression visitCallString(RemixParser.CallStringContext ctx) {
        return produceStringExpression(ctx.STRING().getText());
    }

    /** blockOfStatements (from callPart) */
    @Override
    public Block visitCallBlock(RemixParser.CallBlockContext ctx) {
        // this is just the super version, need to go down a level to remove []
        return (Block)visitChildren(ctx);
    }

    /** list (from callPart) */
    @Override
    public Object visitCallList(RemixParser.CallListContext ctx) {
        return visit(ctx.list());
    }

    /** map (from callPart) */
    @Override
    public Object visitCallMap(RemixParser.CallMapContext ctx) {
        return visit(ctx.map());
    }

    /** LBRACE listContents RBRACE */
    @Override
    public Object visitList(RemixParser.ListContext ctx) {
        return visit(ctx.listContents());
    }

    /** (expression (COMMA expression)*)? */
    @Override
    public RemixListExpression visitCommaList(RemixParser.CommaListContext ctx) {
        return produceListExpression(ctx);
    }

    /** LBLOCK EOL* (expression (separator EOL* expression)*)* RBLOCK EOL? */
    @Override
    public RemixListExpression visitBlockList(RemixParser.BlockListContext ctx) {
        return produceListExpression(ctx);
    }

    /** EOL* */
    @Override
    public RemixListExpression visitEmptyList(RemixParser.EmptyListContext ctx) {
        return new RemixListExpression(null);
    }

    /** LBRACE mapContents RBRACE */
    @Override
    public Object visitMap(RemixParser.MapContext ctx) {
        return visit(ctx.mapContents());
    }

    /** keyValue (COMMA keyValue)* */
    @Override
    public RemixMapExpression visitCommaMap(RemixParser.CommaMapContext ctx) {
        return produceMapExpression(ctx);
    }

    /** LBLOCK EOL* keyValue (separator EOL* keyValue)* RBLOCK EOL? */
    @Override
    public RemixMapExpression visitBlockMap(RemixParser.BlockMapContext ctx) {
        return produceMapExpression(ctx);
    }

    /** WORD listPart+
     * Access to both list elements and map elements.
     * If there is only one listPart it could be a function call instead.
     * This is taken care of at runtime; see GetElementExpression.evaluate.
     */
    @Override
    public GetElementExpression visitListElement(RemixParser.ListElementContext ctx) {
        String listName = ctx.WORD().getText();
        // need to loop through possibly multiple listParts
        int n = ctx.getChildCount();
        List<Expression> listParts = new ArrayList<>();
        for (int i=0; i<n; i++) {
            ParseTree node = ctx.getChild(i);
            if (node instanceof RemixParser.ListPartContext) {
                Expression expression = (Expression)visit(node);
                listParts.add(expression);
            }
        }
        return new GetElementExpression(listName, listParts);
    }

    /** WORD listPart+ COLON expression
     * Set both list elements and map elements.
     */
    @Override
    public SetElementExpression visitSetListElement(RemixParser.SetListElementContext ctx) {
        String listName = ctx.WORD().getText();
        // need to loop through possibly multiple listParts
        List<Expression> listParts = new ArrayList<>();
        int i = 1;
        ParseTree node = ctx.getChild(i);
        while (node instanceof RemixParser.ListPartContext) {
            Expression expression = (Expression)visit(node);
            listParts.add(expression);
            node = ctx.getChild(++i);
        }
        Expression expression = (Expression) visit(ctx.expression());
        return new SetElementExpression(listName, listParts, expression);
    }

    /** LBRACE expression RBRACE */
    @Override
    public Expression visitListPart(RemixParser.ListPartContext ctx) {
        return (Expression)visit(ctx.expression());
    }

    @Override
    public String visitKey(RemixParser.KeyContext ctx) {
        String keyName = ctx.getText();
        if (keyName.startsWith("\"") && keyName.endsWith("\"")) {
            keyName = keyName.substring(1, keyName.length() - 1);
        }
        return keyName;
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
        if (doubleString.equals("π"))
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
            if (node instanceof RemixParser.StatementContext) {
                Expression statement = (Expression)visit(node);
                if (statement != null) // can be blank statements
                    block.addStatement(statement);
            }
        }
        return block;
    }

    public RemixListExpression produceListExpression(ParseTree ctx) {
        List<Expression> list = new ArrayList<>();
        int n = ctx.getChildCount();
        for (int i = 0; i < n; i++) {
            ParseTree node = ctx.getChild(i);
            if (node instanceof RemixParser.ExpressionContext) {
                list.add((Expression)visit(node));
            }
        }
        return new RemixListExpression(list);
    }

    public RemixMapExpression produceMapExpression(ParseTree ctx) {
        Map<String, Expression> map = new HashMap<>();
        int n = ctx.getChildCount();
        for (int i = 0; i < n; i++) {
            ParseTree node = ctx.getChild(i);
            if (node instanceof RemixParser.KeyValueContext keyValue) {
                String key = (String)visit(keyValue.key());
                Expression value = (Expression)visit(keyValue.value());
                map.put(key, value);
            }
        }
        return new RemixMapExpression(map);
    }

}