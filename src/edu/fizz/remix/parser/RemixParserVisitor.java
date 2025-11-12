// Generated from /Users/robert/IdeaProjects/remix/src/RemixParser.g4 by ANTLR 4.13.2
package edu.fizz.remix.parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link RemixParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface RemixParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link RemixParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(RemixParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by the {@code libNoUses}
	 * labeled alternative in {@link RemixParser#library}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLibNoUses(RemixParser.LibNoUsesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code libUses}
	 * labeled alternative in {@link RemixParser#library}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLibUses(RemixParser.LibUsesContext ctx);
	/**
	 * Visit a parse tree produced by {@link RemixParser#libraryName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLibraryName(RemixParser.LibraryNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link RemixParser#usingStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUsingStatement(RemixParser.UsingStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link RemixParser#usingBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUsingBlock(RemixParser.UsingBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link RemixParser#statementBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementBlock(RemixParser.StatementBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link RemixParser#functionDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDefinition(RemixParser.FunctionDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link RemixParser#functionComment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionComment(RemixParser.FunctionCommentContext ctx);
	/**
	 * Visit a parse tree produced by {@link RemixParser#functionSignature}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionSignature(RemixParser.FunctionSignatureContext ctx);
	/**
	 * Visit a parse tree produced by {@link RemixParser#singleWord}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingleWord(RemixParser.SingleWordContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sigWord}
	 * labeled alternative in {@link RemixParser#sigPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSigWord(RemixParser.SigWordContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sigParam}
	 * labeled alternative in {@link RemixParser#sigPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSigParam(RemixParser.SigParamContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sigBlock}
	 * labeled alternative in {@link RemixParser#sigPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSigBlock(RemixParser.SigBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link RemixParser#createObject}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateObject(RemixParser.CreateObjectContext ctx);
	/**
	 * Visit a parse tree produced by {@link RemixParser#extendObject}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExtendObject(RemixParser.ExtendObjectContext ctx);
	/**
	 * Visit a parse tree produced by {@link RemixParser#object}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObject(RemixParser.ObjectContext ctx);
	/**
	 * Visit a parse tree produced by {@link RemixParser#field}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitField(RemixParser.FieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link RemixParser#getterSetter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGetterSetter(RemixParser.GetterSetterContext ctx);
	/**
	 * Visit a parse tree produced by {@link RemixParser#getter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGetter(RemixParser.GetterContext ctx);
	/**
	 * Visit a parse tree produced by {@link RemixParser#setter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetter(RemixParser.SetterContext ctx);
	/**
	 * Visit a parse tree produced by {@link RemixParser#fieldId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldId(RemixParser.FieldIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link RemixParser#methodDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodDefinition(RemixParser.MethodDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link RemixParser#methodSignature}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodSignature(RemixParser.MethodSignatureContext ctx);
	/**
	 * Visit a parse tree produced by the {@code methSigWord}
	 * labeled alternative in {@link RemixParser#methodSigPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethSigWord(RemixParser.MethSigWordContext ctx);
	/**
	 * Visit a parse tree produced by the {@code methSigParam}
	 * labeled alternative in {@link RemixParser#methodSigPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethSigParam(RemixParser.MethSigParamContext ctx);
	/**
	 * Visit a parse tree produced by the {@code methSigSelf}
	 * labeled alternative in {@link RemixParser#methodSigPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethSigSelf(RemixParser.MethSigSelfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code methSigBlock}
	 * labeled alternative in {@link RemixParser#methodSigPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethSigBlock(RemixParser.MethSigBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link RemixParser#blockOfStatements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockOfStatements(RemixParser.BlockOfStatementsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assStatement}
	 * labeled alternative in {@link RemixParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssStatement(RemixParser.AssStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code prStatement}
	 * labeled alternative in {@link RemixParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrStatement(RemixParser.PrStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr}
	 * labeled alternative in {@link RemixParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(RemixParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code redo}
	 * labeled alternative in {@link RemixParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRedo(RemixParser.RedoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code return}
	 * labeled alternative in {@link RemixParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn(RemixParser.ReturnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code using}
	 * labeled alternative in {@link RemixParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUsing(RemixParser.UsingContext ctx);
	/**
	 * Visit a parse tree produced by the {@code blank}
	 * labeled alternative in {@link RemixParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlank(RemixParser.BlankContext ctx);
	/**
	 * Visit a parse tree produced by {@link RemixParser#endOfStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEndOfStatement(RemixParser.EndOfStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code setVariable}
	 * labeled alternative in {@link RemixParser#assignmentStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetVariable(RemixParser.SetVariableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code setConstant}
	 * labeled alternative in {@link RemixParser#assignmentStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetConstant(RemixParser.SetConstantContext ctx);
	/**
	 * Visit a parse tree produced by the {@code setListElement}
	 * labeled alternative in {@link RemixParser#assignmentStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetListElement(RemixParser.SetListElementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code prntStatement}
	 * labeled alternative in {@link RemixParser#printStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrntStatement(RemixParser.PrntStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprBoolean}
	 * labeled alternative in {@link RemixParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprBoolean(RemixParser.ExprBooleanContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprNotEql}
	 * labeled alternative in {@link RemixParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprNotEql(RemixParser.ExprNotEqlContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprMinus}
	 * labeled alternative in {@link RemixParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprMinus(RemixParser.ExprMinusContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprVar}
	 * labeled alternative in {@link RemixParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprVar(RemixParser.ExprVarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprExtend}
	 * labeled alternative in {@link RemixParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprExtend(RemixParser.ExprExtendContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprNumber}
	 * labeled alternative in {@link RemixParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprNumber(RemixParser.ExprNumberContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprGreatEql}
	 * labeled alternative in {@link RemixParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprGreatEql(RemixParser.ExprGreatEqlContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprNull}
	 * labeled alternative in {@link RemixParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprNull(RemixParser.ExprNullContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprListElement}
	 * labeled alternative in {@link RemixParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprListElement(RemixParser.ExprListElementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprConcat}
	 * labeled alternative in {@link RemixParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprConcat(RemixParser.ExprConcatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprAdd}
	 * labeled alternative in {@link RemixParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprAdd(RemixParser.ExprAddContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprMap}
	 * labeled alternative in {@link RemixParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprMap(RemixParser.ExprMapContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprConstant}
	 * labeled alternative in {@link RemixParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprConstant(RemixParser.ExprConstantContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprLess}
	 * labeled alternative in {@link RemixParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprLess(RemixParser.ExprLessContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprObject}
	 * labeled alternative in {@link RemixParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprObject(RemixParser.ExprObjectContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprLessEql}
	 * labeled alternative in {@link RemixParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprLessEql(RemixParser.ExprLessEqlContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprWordProduct}
	 * labeled alternative in {@link RemixParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprWordProduct(RemixParser.ExprWordProductContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprSelf}
	 * labeled alternative in {@link RemixParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprSelf(RemixParser.ExprSelfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprEqual}
	 * labeled alternative in {@link RemixParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprEqual(RemixParser.ExprEqualContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprParen}
	 * labeled alternative in {@link RemixParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprParen(RemixParser.ExprParenContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprMul}
	 * labeled alternative in {@link RemixParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprMul(RemixParser.ExprMulContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprString}
	 * labeled alternative in {@link RemixParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprString(RemixParser.ExprStringContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprList}
	 * labeled alternative in {@link RemixParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprList(RemixParser.ExprListContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprLibrary}
	 * labeled alternative in {@link RemixParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprLibrary(RemixParser.ExprLibraryContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprGreater}
	 * labeled alternative in {@link RemixParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprGreater(RemixParser.ExprGreaterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprBlock}
	 * labeled alternative in {@link RemixParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprBlock(RemixParser.ExprBlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprFncCall}
	 * labeled alternative in {@link RemixParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprFncCall(RemixParser.ExprFncCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link RemixParser#listElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListElement(RemixParser.ListElementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code listPartExpr}
	 * labeled alternative in {@link RemixParser#listPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListPartExpr(RemixParser.ListPartExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link RemixParser#functionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCall(RemixParser.FunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code callWord}
	 * labeled alternative in {@link RemixParser#callPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallWord(RemixParser.CallWordContext ctx);
	/**
	 * Visit a parse tree produced by the {@code callMinusExpr}
	 * labeled alternative in {@link RemixParser#callPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallMinusExpr(RemixParser.CallMinusExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code callConstant}
	 * labeled alternative in {@link RemixParser#callPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallConstant(RemixParser.CallConstantContext ctx);
	/**
	 * Visit a parse tree produced by the {@code callVar}
	 * labeled alternative in {@link RemixParser#callPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallVar(RemixParser.CallVarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code callParam}
	 * labeled alternative in {@link RemixParser#callPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallParam(RemixParser.CallParamContext ctx);
	/**
	 * Visit a parse tree produced by the {@code callSelf}
	 * labeled alternative in {@link RemixParser#callPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallSelf(RemixParser.CallSelfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code callNumber}
	 * labeled alternative in {@link RemixParser#callPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallNumber(RemixParser.CallNumberContext ctx);
	/**
	 * Visit a parse tree produced by the {@code callWordProduct}
	 * labeled alternative in {@link RemixParser#callPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallWordProduct(RemixParser.CallWordProductContext ctx);
	/**
	 * Visit a parse tree produced by the {@code callNull}
	 * labeled alternative in {@link RemixParser#callPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallNull(RemixParser.CallNullContext ctx);
	/**
	 * Visit a parse tree produced by the {@code callBoolean}
	 * labeled alternative in {@link RemixParser#callPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallBoolean(RemixParser.CallBooleanContext ctx);
	/**
	 * Visit a parse tree produced by the {@code callString}
	 * labeled alternative in {@link RemixParser#callPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallString(RemixParser.CallStringContext ctx);
	/**
	 * Visit a parse tree produced by the {@code callBlock}
	 * labeled alternative in {@link RemixParser#callPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallBlock(RemixParser.CallBlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code callList}
	 * labeled alternative in {@link RemixParser#callPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallList(RemixParser.CallListContext ctx);
	/**
	 * Visit a parse tree produced by the {@code callMap}
	 * labeled alternative in {@link RemixParser#callPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallMap(RemixParser.CallMapContext ctx);
	/**
	 * Visit a parse tree produced by {@link RemixParser#list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitList(RemixParser.ListContext ctx);
	/**
	 * Visit a parse tree produced by {@link RemixParser#separator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSeparator(RemixParser.SeparatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code commaList}
	 * labeled alternative in {@link RemixParser#listContents}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommaList(RemixParser.CommaListContext ctx);
	/**
	 * Visit a parse tree produced by the {@code blockList}
	 * labeled alternative in {@link RemixParser#listContents}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockList(RemixParser.BlockListContext ctx);
	/**
	 * Visit a parse tree produced by the {@code emptyList}
	 * labeled alternative in {@link RemixParser#listContents}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmptyList(RemixParser.EmptyListContext ctx);
	/**
	 * Visit a parse tree produced by {@link RemixParser#map}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMap(RemixParser.MapContext ctx);
	/**
	 * Visit a parse tree produced by the {@code commaMap}
	 * labeled alternative in {@link RemixParser#mapContents}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommaMap(RemixParser.CommaMapContext ctx);
	/**
	 * Visit a parse tree produced by the {@code blockMap}
	 * labeled alternative in {@link RemixParser#mapContents}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockMap(RemixParser.BlockMapContext ctx);
	/**
	 * Visit a parse tree produced by {@link RemixParser#keyValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyValue(RemixParser.KeyValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link RemixParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(RemixParser.ValueContext ctx);
}