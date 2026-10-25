// Generated from /Users/robert/IdeaProjects/remix'd/src/RemixdParser.g4 by ANTLR 4.13.2
package edu.fizz.remixd.parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link RemixdParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface RemixdParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link RemixdParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(RemixdParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link RemixdParser#library}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLibrary(RemixdParser.LibraryContext ctx);
	/**
	 * Visit a parse tree produced by {@link RemixdParser#string}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString(RemixdParser.StringContext ctx);
	/**
	 * Visit a parse tree produced by {@link RemixdParser#libraryName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLibraryName(RemixdParser.LibraryNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link RemixdParser#libAssignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLibAssignment(RemixdParser.LibAssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link RemixdParser#usingStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUsingStatement(RemixdParser.UsingStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link RemixdParser#usingBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUsingBlock(RemixdParser.UsingBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link RemixdParser#setConstant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetConstant(RemixdParser.SetConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link RemixdParser#functionDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDefinition(RemixdParser.FunctionDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link RemixdParser#functionComment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionComment(RemixdParser.FunctionCommentContext ctx);
	/**
	 * Visit a parse tree produced by {@link RemixdParser#functionSignature}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionSignature(RemixdParser.FunctionSignatureContext ctx);
	/**
	 * Visit a parse tree produced by {@link RemixdParser#singleWord}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingleWord(RemixdParser.SingleWordContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sigWord}
	 * labeled alternative in {@link RemixdParser#sigPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSigWord(RemixdParser.SigWordContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sigParam}
	 * labeled alternative in {@link RemixdParser#sigPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSigParam(RemixdParser.SigParamContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sigBlock}
	 * labeled alternative in {@link RemixdParser#sigPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSigBlock(RemixdParser.SigBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link RemixdParser#createObject}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateObject(RemixdParser.CreateObjectContext ctx);
	/**
	 * Visit a parse tree produced by {@link RemixdParser#extendObject}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExtendObject(RemixdParser.ExtendObjectContext ctx);
	/**
	 * Visit a parse tree produced by {@link RemixdParser#createParams}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateParams(RemixdParser.CreateParamsContext ctx);
	/**
	 * Visit a parse tree produced by {@link RemixdParser#object}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObject(RemixdParser.ObjectContext ctx);
	/**
	 * Visit a parse tree produced by {@link RemixdParser#field}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitField(RemixdParser.FieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link RemixdParser#getterSetter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGetterSetter(RemixdParser.GetterSetterContext ctx);
	/**
	 * Visit a parse tree produced by {@link RemixdParser#getter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGetter(RemixdParser.GetterContext ctx);
	/**
	 * Visit a parse tree produced by {@link RemixdParser#setter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetter(RemixdParser.SetterContext ctx);
	/**
	 * Visit a parse tree produced by {@link RemixdParser#fieldId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldId(RemixdParser.FieldIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link RemixdParser#methodDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodDefinition(RemixdParser.MethodDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link RemixdParser#methodSignature}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodSignature(RemixdParser.MethodSignatureContext ctx);
	/**
	 * Visit a parse tree produced by the {@code methSigWord}
	 * labeled alternative in {@link RemixdParser#methodSigPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethSigWord(RemixdParser.MethSigWordContext ctx);
	/**
	 * Visit a parse tree produced by the {@code methSigParam}
	 * labeled alternative in {@link RemixdParser#methodSigPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethSigParam(RemixdParser.MethSigParamContext ctx);
	/**
	 * Visit a parse tree produced by the {@code methSigSelf}
	 * labeled alternative in {@link RemixdParser#methodSigPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethSigSelf(RemixdParser.MethSigSelfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code methSigBlock}
	 * labeled alternative in {@link RemixdParser#methodSigPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethSigBlock(RemixdParser.MethSigBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link RemixdParser#blockOfStatements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockOfStatements(RemixdParser.BlockOfStatementsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assStatement}
	 * labeled alternative in {@link RemixdParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssStatement(RemixdParser.AssStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code prStatement}
	 * labeled alternative in {@link RemixdParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrStatement(RemixdParser.PrStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr}
	 * labeled alternative in {@link RemixdParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(RemixdParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code redo}
	 * labeled alternative in {@link RemixdParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRedo(RemixdParser.RedoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code return}
	 * labeled alternative in {@link RemixdParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn(RemixdParser.ReturnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code blank}
	 * labeled alternative in {@link RemixdParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlank(RemixdParser.BlankContext ctx);
	/**
	 * Visit a parse tree produced by {@link RemixdParser#endOfStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEndOfStatement(RemixdParser.EndOfStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code setVariable}
	 * labeled alternative in {@link RemixdParser#assignmentStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetVariable(RemixdParser.SetVariableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code setListElement}
	 * labeled alternative in {@link RemixdParser#assignmentStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetListElement(RemixdParser.SetListElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link RemixdParser#printStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintStatement(RemixdParser.PrintStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprBoolean}
	 * labeled alternative in {@link RemixdParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprBoolean(RemixdParser.ExprBooleanContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprNotEql}
	 * labeled alternative in {@link RemixdParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprNotEql(RemixdParser.ExprNotEqlContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprMinus}
	 * labeled alternative in {@link RemixdParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprMinus(RemixdParser.ExprMinusContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprVar}
	 * labeled alternative in {@link RemixdParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprVar(RemixdParser.ExprVarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprExtend}
	 * labeled alternative in {@link RemixdParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprExtend(RemixdParser.ExprExtendContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprNumber}
	 * labeled alternative in {@link RemixdParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprNumber(RemixdParser.ExprNumberContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprGreatEql}
	 * labeled alternative in {@link RemixdParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprGreatEql(RemixdParser.ExprGreatEqlContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprNull}
	 * labeled alternative in {@link RemixdParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprNull(RemixdParser.ExprNullContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprListElement}
	 * labeled alternative in {@link RemixdParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprListElement(RemixdParser.ExprListElementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprConcat}
	 * labeled alternative in {@link RemixdParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprConcat(RemixdParser.ExprConcatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprAdd}
	 * labeled alternative in {@link RemixdParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprAdd(RemixdParser.ExprAddContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprMap}
	 * labeled alternative in {@link RemixdParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprMap(RemixdParser.ExprMapContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprConstant}
	 * labeled alternative in {@link RemixdParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprConstant(RemixdParser.ExprConstantContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprLess}
	 * labeled alternative in {@link RemixdParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprLess(RemixdParser.ExprLessContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprObject}
	 * labeled alternative in {@link RemixdParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprObject(RemixdParser.ExprObjectContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprLessEql}
	 * labeled alternative in {@link RemixdParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprLessEql(RemixdParser.ExprLessEqlContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprWordProduct}
	 * labeled alternative in {@link RemixdParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprWordProduct(RemixdParser.ExprWordProductContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprSelf}
	 * labeled alternative in {@link RemixdParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprSelf(RemixdParser.ExprSelfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprEqual}
	 * labeled alternative in {@link RemixdParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprEqual(RemixdParser.ExprEqualContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprParen}
	 * labeled alternative in {@link RemixdParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprParen(RemixdParser.ExprParenContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprMul}
	 * labeled alternative in {@link RemixdParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprMul(RemixdParser.ExprMulContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprString}
	 * labeled alternative in {@link RemixdParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprString(RemixdParser.ExprStringContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprList}
	 * labeled alternative in {@link RemixdParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprList(RemixdParser.ExprListContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprGreater}
	 * labeled alternative in {@link RemixdParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprGreater(RemixdParser.ExprGreaterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprBlock}
	 * labeled alternative in {@link RemixdParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprBlock(RemixdParser.ExprBlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprFncCall}
	 * labeled alternative in {@link RemixdParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprFncCall(RemixdParser.ExprFncCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link RemixdParser#listElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListElement(RemixdParser.ListElementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code listPartExpr}
	 * labeled alternative in {@link RemixdParser#listPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListPartExpr(RemixdParser.ListPartExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link RemixdParser#functionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCall(RemixdParser.FunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code callWord}
	 * labeled alternative in {@link RemixdParser#callPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallWord(RemixdParser.CallWordContext ctx);
	/**
	 * Visit a parse tree produced by the {@code callMinusExpr}
	 * labeled alternative in {@link RemixdParser#callPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallMinusExpr(RemixdParser.CallMinusExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code callConstant}
	 * labeled alternative in {@link RemixdParser#callPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallConstant(RemixdParser.CallConstantContext ctx);
	/**
	 * Visit a parse tree produced by the {@code callVar}
	 * labeled alternative in {@link RemixdParser#callPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallVar(RemixdParser.CallVarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code callParam}
	 * labeled alternative in {@link RemixdParser#callPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallParam(RemixdParser.CallParamContext ctx);
	/**
	 * Visit a parse tree produced by the {@code callSelf}
	 * labeled alternative in {@link RemixdParser#callPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallSelf(RemixdParser.CallSelfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code callNumber}
	 * labeled alternative in {@link RemixdParser#callPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallNumber(RemixdParser.CallNumberContext ctx);
	/**
	 * Visit a parse tree produced by the {@code callWordProduct}
	 * labeled alternative in {@link RemixdParser#callPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallWordProduct(RemixdParser.CallWordProductContext ctx);
	/**
	 * Visit a parse tree produced by the {@code callNull}
	 * labeled alternative in {@link RemixdParser#callPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallNull(RemixdParser.CallNullContext ctx);
	/**
	 * Visit a parse tree produced by the {@code callBoolean}
	 * labeled alternative in {@link RemixdParser#callPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallBoolean(RemixdParser.CallBooleanContext ctx);
	/**
	 * Visit a parse tree produced by the {@code callString}
	 * labeled alternative in {@link RemixdParser#callPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallString(RemixdParser.CallStringContext ctx);
	/**
	 * Visit a parse tree produced by the {@code callBlock}
	 * labeled alternative in {@link RemixdParser#callPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallBlock(RemixdParser.CallBlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code callList}
	 * labeled alternative in {@link RemixdParser#callPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallList(RemixdParser.CallListContext ctx);
	/**
	 * Visit a parse tree produced by the {@code callMap}
	 * labeled alternative in {@link RemixdParser#callPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallMap(RemixdParser.CallMapContext ctx);
	/**
	 * Visit a parse tree produced by {@link RemixdParser#list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitList(RemixdParser.ListContext ctx);
	/**
	 * Visit a parse tree produced by {@link RemixdParser#separator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSeparator(RemixdParser.SeparatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code commaList}
	 * labeled alternative in {@link RemixdParser#listContents}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommaList(RemixdParser.CommaListContext ctx);
	/**
	 * Visit a parse tree produced by the {@code blockList}
	 * labeled alternative in {@link RemixdParser#listContents}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockList(RemixdParser.BlockListContext ctx);
	/**
	 * Visit a parse tree produced by the {@code emptyList}
	 * labeled alternative in {@link RemixdParser#listContents}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmptyList(RemixdParser.EmptyListContext ctx);
	/**
	 * Visit a parse tree produced by {@link RemixdParser#map}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMap(RemixdParser.MapContext ctx);
	/**
	 * Visit a parse tree produced by the {@code commaMap}
	 * labeled alternative in {@link RemixdParser#mapContents}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommaMap(RemixdParser.CommaMapContext ctx);
	/**
	 * Visit a parse tree produced by the {@code blockMap}
	 * labeled alternative in {@link RemixdParser#mapContents}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockMap(RemixdParser.BlockMapContext ctx);
	/**
	 * Visit a parse tree produced by {@link RemixdParser#keyValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyValue(RemixdParser.KeyValueContext ctx);
}