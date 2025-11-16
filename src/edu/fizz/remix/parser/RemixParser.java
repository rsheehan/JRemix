// Generated from /Users/robert/IdeaProjects/remix/src/RemixParser.g4 by ANTLR 4.13.2
package edu.fizz.remix.parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class RemixParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		COLON=1, LPAREN=2, RPAREN=3, LBLOCK=4, RBLOCK=5, LBRACE=6, RBRACE=7, COMMA=8, 
		ENDPRINT=9, PRINTLN=10, SPACE=11, CONT=12, EOL=13, EOS=14, DOC_COMMENT=15, 
		COMMENT=16, NUMBER=17, ADD=18, MUL=19, LESS=20, GREATER=21, LESSEQUAL=22, 
		GREATEREQUAL=23, EQUAL=24, NOTEQUAL=25, CONCAT=26, MINUS=27, NULL=28, 
		BOOLEAN=29, RETURN=30, REDO=31, CREATE=32, EXTEND=33, GETTERSETTER=34, 
		GETTER=35, SETTER=36, LIBRARY=37, USING=38, SELFREF=39, CONSTANT=40, IDENTIFIER=41, 
		WORD=42, WORDPRODUCT=43, STRING=44, EMPTYIDENTIFIER=45;
	public static final int
		RULE_program = 0, RULE_libraryName = 1, RULE_library = 2, RULE_libAssignment = 3, 
		RULE_usingStatement = 4, RULE_usingBlock = 5, RULE_functionDefinition = 6, 
		RULE_functionComment = 7, RULE_functionSignature = 8, RULE_singleWord = 9, 
		RULE_sigPart = 10, RULE_createObject = 11, RULE_extendObject = 12, RULE_object = 13, 
		RULE_field = 14, RULE_getterSetter = 15, RULE_getter = 16, RULE_setter = 17, 
		RULE_fieldId = 18, RULE_methodDefinition = 19, RULE_methodSignature = 20, 
		RULE_methodSigPart = 21, RULE_blockOfStatements = 22, RULE_statement = 23, 
		RULE_endOfStatement = 24, RULE_assignmentStatement = 25, RULE_printStatement = 26, 
		RULE_expression = 27, RULE_listElement = 28, RULE_listPart = 29, RULE_functionCall = 30, 
		RULE_callPart = 31, RULE_list = 32, RULE_separator = 33, RULE_listContents = 34, 
		RULE_map = 35, RULE_mapContents = 36, RULE_keyValue = 37, RULE_value = 38;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "libraryName", "library", "libAssignment", "usingStatement", 
			"usingBlock", "functionDefinition", "functionComment", "functionSignature", 
			"singleWord", "sigPart", "createObject", "extendObject", "object", "field", 
			"getterSetter", "getter", "setter", "fieldId", "methodDefinition", "methodSignature", 
			"methodSigPart", "blockOfStatements", "statement", "endOfStatement", 
			"assignmentStatement", "printStatement", "expression", "listElement", 
			"listPart", "functionCall", "callPart", "list", "separator", "listContents", 
			"map", "mapContents", "keyValue", "value"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "':'", "'('", "')'", "'['", "']'", "'{'", "'}'", "','", "'~'", 
			null, null, null, "'\\n'", "'.'", null, null, null, null, null, "' < '", 
			"' > '", null, null, "' = '", null, null, "'-'", "'null'", null, "'return'", 
			"'redo'", "'create'", "'extend'", null, null, null, "'library'", "'using'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "COLON", "LPAREN", "RPAREN", "LBLOCK", "RBLOCK", "LBRACE", "RBRACE", 
			"COMMA", "ENDPRINT", "PRINTLN", "SPACE", "CONT", "EOL", "EOS", "DOC_COMMENT", 
			"COMMENT", "NUMBER", "ADD", "MUL", "LESS", "GREATER", "LESSEQUAL", "GREATEREQUAL", 
			"EQUAL", "NOTEQUAL", "CONCAT", "MINUS", "NULL", "BOOLEAN", "RETURN", 
			"REDO", "CREATE", "EXTEND", "GETTERSETTER", "GETTER", "SETTER", "LIBRARY", 
			"USING", "SELFREF", "CONSTANT", "IDENTIFIER", "WORD", "WORDPRODUCT", 
			"STRING", "EMPTYIDENTIFIER"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "RemixParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public RemixParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(RemixParser.EOF, 0); }
		public List<FunctionDefinitionContext> functionDefinition() {
			return getRuleContexts(FunctionDefinitionContext.class);
		}
		public FunctionDefinitionContext functionDefinition(int i) {
			return getRuleContext(FunctionDefinitionContext.class,i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public List<UsingStatementContext> usingStatement() {
			return getRuleContexts(UsingStatementContext.class);
		}
		public UsingStatementContext usingStatement(int i) {
			return getRuleContext(UsingStatementContext.class,i);
		}
		public List<LibraryContext> library() {
			return getRuleContexts(LibraryContext.class);
		}
		public LibraryContext library(int i) {
			return getRuleContext(LibraryContext.class,i);
		}
		public List<LibAssignmentContext> libAssignment() {
			return getRuleContexts(LibAssignmentContext.class);
		}
		public LibAssignmentContext libAssignment(int i) {
			return getRuleContext(LibAssignmentContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 35063978976852L) != 0)) {
				{
				setState(83);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(78);
					functionDefinition();
					}
					break;
				case 2:
					{
					setState(79);
					statement();
					}
					break;
				case 3:
					{
					setState(80);
					usingStatement();
					}
					break;
				case 4:
					{
					setState(81);
					library();
					}
					break;
				case 5:
					{
					setState(82);
					libAssignment();
					}
					break;
				}
				}
				setState(87);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(88);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LibraryNameContext extends ParserRuleContext {
		public TerminalNode LIBRARY() { return getToken(RemixParser.LIBRARY, 0); }
		public TerminalNode STRING() { return getToken(RemixParser.STRING, 0); }
		public LibraryNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_libraryName; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitLibraryName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LibraryNameContext libraryName() throws RecognitionException {
		LibraryNameContext _localctx = new LibraryNameContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_libraryName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90);
			match(LIBRARY);
			setState(92);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==STRING) {
				{
				setState(91);
				match(STRING);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LibraryContext extends ParserRuleContext {
		public LibraryNameContext libraryName() {
			return getRuleContext(LibraryNameContext.class,0);
		}
		public TerminalNode LBLOCK() { return getToken(RemixParser.LBLOCK, 0); }
		public TerminalNode RBLOCK() { return getToken(RemixParser.RBLOCK, 0); }
		public List<TerminalNode> EOL() { return getTokens(RemixParser.EOL); }
		public TerminalNode EOL(int i) {
			return getToken(RemixParser.EOL, i);
		}
		public List<FunctionDefinitionContext> functionDefinition() {
			return getRuleContexts(FunctionDefinitionContext.class);
		}
		public FunctionDefinitionContext functionDefinition(int i) {
			return getRuleContext(FunctionDefinitionContext.class,i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public List<UsingStatementContext> usingStatement() {
			return getRuleContexts(UsingStatementContext.class);
		}
		public UsingStatementContext usingStatement(int i) {
			return getRuleContext(UsingStatementContext.class,i);
		}
		public LibraryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_library; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitLibrary(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LibraryContext library() throws RecognitionException {
		LibraryContext _localctx = new LibraryContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_library);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(94);
			libraryName();
			setState(95);
			match(LBLOCK);
			setState(99);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(96);
					match(EOL);
					}
					} 
				}
				setState(101);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			}
			setState(107);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 34926540023380L) != 0)) {
				{
				setState(105);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
				case 1:
					{
					setState(102);
					functionDefinition();
					}
					break;
				case 2:
					{
					setState(103);
					statement();
					}
					break;
				case 3:
					{
					setState(104);
					usingStatement();
					}
					break;
				}
				}
				setState(109);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(110);
			match(RBLOCK);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LibAssignmentContext extends ParserRuleContext {
		public LibAssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_libAssignment; }
	 
		public LibAssignmentContext() { }
		public void copyFrom(LibAssignmentContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SetConLibraryContext extends LibAssignmentContext {
		public TerminalNode CONSTANT() { return getToken(RemixParser.CONSTANT, 0); }
		public TerminalNode COLON() { return getToken(RemixParser.COLON, 0); }
		public LibraryContext library() {
			return getRuleContext(LibraryContext.class,0);
		}
		public SetConLibraryContext(LibAssignmentContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitSetConLibrary(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SetVarLibraryContext extends LibAssignmentContext {
		public TerminalNode IDENTIFIER() { return getToken(RemixParser.IDENTIFIER, 0); }
		public TerminalNode COLON() { return getToken(RemixParser.COLON, 0); }
		public LibraryContext library() {
			return getRuleContext(LibraryContext.class,0);
		}
		public SetVarLibraryContext(LibAssignmentContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitSetVarLibrary(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LibAssignmentContext libAssignment() throws RecognitionException {
		LibAssignmentContext _localctx = new LibAssignmentContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_libAssignment);
		try {
			setState(118);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				_localctx = new SetVarLibraryContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(112);
				match(IDENTIFIER);
				setState(113);
				match(COLON);
				setState(114);
				library();
				}
				break;
			case CONSTANT:
				_localctx = new SetConLibraryContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(115);
				match(CONSTANT);
				setState(116);
				match(COLON);
				setState(117);
				library();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UsingStatementContext extends ParserRuleContext {
		public TerminalNode USING() { return getToken(RemixParser.USING, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public UsingBlockContext usingBlock() {
			return getRuleContext(UsingBlockContext.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(RemixParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(RemixParser.COMMA, i);
		}
		public UsingStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_usingStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitUsingStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UsingStatementContext usingStatement() throws RecognitionException {
		UsingStatementContext _localctx = new UsingStatementContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_usingStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(120);
			match(USING);
			setState(121);
			expression(0);
			setState(126);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(122);
				match(COMMA);
				setState(123);
				expression(0);
				}
				}
				setState(128);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(129);
			usingBlock();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UsingBlockContext extends ParserRuleContext {
		public TerminalNode LBLOCK() { return getToken(RemixParser.LBLOCK, 0); }
		public TerminalNode RBLOCK() { return getToken(RemixParser.RBLOCK, 0); }
		public List<FunctionDefinitionContext> functionDefinition() {
			return getRuleContexts(FunctionDefinitionContext.class);
		}
		public FunctionDefinitionContext functionDefinition(int i) {
			return getRuleContext(FunctionDefinitionContext.class,i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public UsingBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_usingBlock; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitUsingBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UsingBlockContext usingBlock() throws RecognitionException {
		UsingBlockContext _localctx = new UsingBlockContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_usingBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131);
			match(LBLOCK);
			setState(136);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 34651662116436L) != 0)) {
				{
				setState(134);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
				case 1:
					{
					setState(132);
					functionDefinition();
					}
					break;
				case 2:
					{
					setState(133);
					statement();
					}
					break;
				}
				}
				setState(138);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(139);
			match(RBLOCK);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FunctionDefinitionContext extends ParserRuleContext {
		public FunctionSignatureContext functionSignature() {
			return getRuleContext(FunctionSignatureContext.class,0);
		}
		public List<TerminalNode> COLON() { return getTokens(RemixParser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(RemixParser.COLON, i);
		}
		public BlockOfStatementsContext blockOfStatements() {
			return getRuleContext(BlockOfStatementsContext.class,0);
		}
		public FunctionCommentContext functionComment() {
			return getRuleContext(FunctionCommentContext.class,0);
		}
		public TerminalNode EOL() { return getToken(RemixParser.EOL, 0); }
		public FunctionDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionDefinition; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitFunctionDefinition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionDefinitionContext functionDefinition() throws RecognitionException {
		FunctionDefinitionContext _localctx = new FunctionDefinitionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_functionDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(142);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DOC_COMMENT) {
				{
				setState(141);
				functionComment();
				}
			}

			setState(144);
			functionSignature();
			setState(145);
			match(COLON);
			setState(147);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(146);
				match(COLON);
				}
			}

			setState(150);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EOL) {
				{
				setState(149);
				match(EOL);
				}
			}

			setState(152);
			blockOfStatements();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FunctionCommentContext extends ParserRuleContext {
		public TerminalNode DOC_COMMENT() { return getToken(RemixParser.DOC_COMMENT, 0); }
		public TerminalNode EOL() { return getToken(RemixParser.EOL, 0); }
		public FunctionCommentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionComment; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitFunctionComment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionCommentContext functionComment() throws RecognitionException {
		FunctionCommentContext _localctx = new FunctionCommentContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_functionComment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(154);
			match(DOC_COMMENT);
			setState(155);
			match(EOL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FunctionSignatureContext extends ParserRuleContext {
		public List<SigPartContext> sigPart() {
			return getRuleContexts(SigPartContext.class);
		}
		public SigPartContext sigPart(int i) {
			return getRuleContext(SigPartContext.class,i);
		}
		public SingleWordContext singleWord() {
			return getRuleContext(SingleWordContext.class,0);
		}
		public FunctionSignatureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionSignature; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitFunctionSignature(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionSignatureContext functionSignature() throws RecognitionException {
		FunctionSignatureContext _localctx = new FunctionSignatureContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_functionSignature);
		int _la;
		try {
			setState(164);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(157);
				sigPart();
				setState(159); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(158);
					sigPart();
					}
					}
					setState(161); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 6597069766676L) != 0) );
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(163);
				singleWord();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SingleWordContext extends ParserRuleContext {
		public TerminalNode WORD() { return getToken(RemixParser.WORD, 0); }
		public SingleWordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_singleWord; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitSingleWord(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SingleWordContext singleWord() throws RecognitionException {
		SingleWordContext _localctx = new SingleWordContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_singleWord);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166);
			match(WORD);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SigPartContext extends ParserRuleContext {
		public SigPartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sigPart; }
	 
		public SigPartContext() { }
		public void copyFrom(SigPartContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SigParamContext extends SigPartContext {
		public TerminalNode IDENTIFIER() { return getToken(RemixParser.IDENTIFIER, 0); }
		public TerminalNode LPAREN() { return getToken(RemixParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(RemixParser.RPAREN, 0); }
		public SigParamContext(SigPartContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitSigParam(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SigBlockContext extends SigPartContext {
		public TerminalNode LBLOCK() { return getToken(RemixParser.LBLOCK, 0); }
		public TerminalNode IDENTIFIER() { return getToken(RemixParser.IDENTIFIER, 0); }
		public TerminalNode RBLOCK() { return getToken(RemixParser.RBLOCK, 0); }
		public SigBlockContext(SigPartContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitSigBlock(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SigWordContext extends SigPartContext {
		public TerminalNode WORD() { return getToken(RemixParser.WORD, 0); }
		public SigWordContext(SigPartContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitSigWord(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SigPartContext sigPart() throws RecognitionException {
		SigPartContext _localctx = new SigPartContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_sigPart);
		try {
			setState(178);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case WORD:
				_localctx = new SigWordContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(168);
				match(WORD);
				}
				break;
			case LPAREN:
			case IDENTIFIER:
				_localctx = new SigParamContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(173);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case IDENTIFIER:
					{
					setState(169);
					match(IDENTIFIER);
					}
					break;
				case LPAREN:
					{
					setState(170);
					match(LPAREN);
					setState(171);
					match(IDENTIFIER);
					setState(172);
					match(RPAREN);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case LBLOCK:
				_localctx = new SigBlockContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(175);
				match(LBLOCK);
				setState(176);
				match(IDENTIFIER);
				setState(177);
				match(RBLOCK);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CreateObjectContext extends ParserRuleContext {
		public TerminalNode CREATE() { return getToken(RemixParser.CREATE, 0); }
		public TerminalNode LBLOCK() { return getToken(RemixParser.LBLOCK, 0); }
		public ObjectContext object() {
			return getRuleContext(ObjectContext.class,0);
		}
		public TerminalNode RBLOCK() { return getToken(RemixParser.RBLOCK, 0); }
		public List<TerminalNode> EOL() { return getTokens(RemixParser.EOL); }
		public TerminalNode EOL(int i) {
			return getToken(RemixParser.EOL, i);
		}
		public CreateObjectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_createObject; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitCreateObject(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CreateObjectContext createObject() throws RecognitionException {
		CreateObjectContext _localctx = new CreateObjectContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_createObject);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(180);
			match(CREATE);
			setState(181);
			match(LBLOCK);
			setState(185);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==EOL) {
				{
				{
				setState(182);
				match(EOL);
				}
				}
				setState(187);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(188);
			object();
			setState(189);
			match(RBLOCK);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExtendObjectContext extends ParserRuleContext {
		public TerminalNode EXTEND() { return getToken(RemixParser.EXTEND, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode LBLOCK() { return getToken(RemixParser.LBLOCK, 0); }
		public ObjectContext object() {
			return getRuleContext(ObjectContext.class,0);
		}
		public TerminalNode RBLOCK() { return getToken(RemixParser.RBLOCK, 0); }
		public List<TerminalNode> EOL() { return getTokens(RemixParser.EOL); }
		public TerminalNode EOL(int i) {
			return getToken(RemixParser.EOL, i);
		}
		public ExtendObjectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_extendObject; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitExtendObject(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExtendObjectContext extendObject() throws RecognitionException {
		ExtendObjectContext _localctx = new ExtendObjectContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_extendObject);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(191);
			match(EXTEND);
			setState(192);
			expression(0);
			setState(193);
			match(LBLOCK);
			setState(197);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==EOL) {
				{
				{
				setState(194);
				match(EOL);
				}
				}
				setState(199);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(200);
			object();
			setState(201);
			match(RBLOCK);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ObjectContext extends ParserRuleContext {
		public List<FieldContext> field() {
			return getRuleContexts(FieldContext.class);
		}
		public FieldContext field(int i) {
			return getRuleContext(FieldContext.class,i);
		}
		public List<MethodDefinitionContext> methodDefinition() {
			return getRuleContexts(MethodDefinitionContext.class);
		}
		public MethodDefinitionContext methodDefinition(int i) {
			return getRuleContext(MethodDefinitionContext.class,i);
		}
		public GetterSetterContext getterSetter() {
			return getRuleContext(GetterSetterContext.class,0);
		}
		public GetterContext getter() {
			return getRuleContext(GetterContext.class,0);
		}
		public SetterContext setter() {
			return getRuleContext(SetterContext.class,0);
		}
		public ObjectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_object; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitObject(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ObjectContext object() throws RecognitionException {
		ObjectContext _localctx = new ObjectContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_object);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(206);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(203);
					field();
					}
					} 
				}
				setState(208);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			}
			{
			setState(210);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==GETTERSETTER) {
				{
				setState(209);
				getterSetter();
				}
			}

			setState(213);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==GETTER) {
				{
				setState(212);
				getter();
				}
			}

			setState(216);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SETTER) {
				{
				setState(215);
				setter();
				}
			}

			}
			setState(221);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 7146825613332L) != 0)) {
				{
				{
				setState(218);
				methodDefinition();
				}
				}
				setState(223);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FieldContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(RemixParser.IDENTIFIER, 0); }
		public TerminalNode COLON() { return getToken(RemixParser.COLON, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<TerminalNode> EOL() { return getTokens(RemixParser.EOL); }
		public TerminalNode EOL(int i) {
			return getToken(RemixParser.EOL, i);
		}
		public FieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_field; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitField(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FieldContext field() throws RecognitionException {
		FieldContext _localctx = new FieldContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_field);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(224);
			match(IDENTIFIER);
			setState(225);
			match(COLON);
			setState(226);
			expression(0);
			setState(230);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==EOL) {
				{
				{
				setState(227);
				match(EOL);
				}
				}
				setState(232);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class GetterSetterContext extends ParserRuleContext {
		public TerminalNode GETTERSETTER() { return getToken(RemixParser.GETTERSETTER, 0); }
		public TerminalNode LBLOCK() { return getToken(RemixParser.LBLOCK, 0); }
		public TerminalNode RBLOCK() { return getToken(RemixParser.RBLOCK, 0); }
		public List<FieldIdContext> fieldId() {
			return getRuleContexts(FieldIdContext.class);
		}
		public FieldIdContext fieldId(int i) {
			return getRuleContext(FieldIdContext.class,i);
		}
		public List<TerminalNode> EOL() { return getTokens(RemixParser.EOL); }
		public TerminalNode EOL(int i) {
			return getToken(RemixParser.EOL, i);
		}
		public List<SeparatorContext> separator() {
			return getRuleContexts(SeparatorContext.class);
		}
		public SeparatorContext separator(int i) {
			return getRuleContext(SeparatorContext.class,i);
		}
		public GetterSetterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_getterSetter; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitGetterSetter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GetterSetterContext getterSetter() throws RecognitionException {
		GetterSetterContext _localctx = new GetterSetterContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_getterSetter);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(233);
			match(GETTERSETTER);
			setState(234);
			match(LBLOCK);
			setState(248); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(238);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==EOL) {
					{
					{
					setState(235);
					match(EOL);
					}
					}
					setState(240);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(241);
				fieldId();
				setState(245);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(242);
						separator();
						}
						} 
					}
					setState(247);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
				}
				}
				}
				setState(250); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==EOL || _la==IDENTIFIER );
			setState(252);
			match(RBLOCK);
			setState(256);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==EOL) {
				{
				{
				setState(253);
				match(EOL);
				}
				}
				setState(258);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class GetterContext extends ParserRuleContext {
		public TerminalNode GETTER() { return getToken(RemixParser.GETTER, 0); }
		public TerminalNode LBLOCK() { return getToken(RemixParser.LBLOCK, 0); }
		public TerminalNode RBLOCK() { return getToken(RemixParser.RBLOCK, 0); }
		public List<FieldIdContext> fieldId() {
			return getRuleContexts(FieldIdContext.class);
		}
		public FieldIdContext fieldId(int i) {
			return getRuleContext(FieldIdContext.class,i);
		}
		public List<TerminalNode> EOL() { return getTokens(RemixParser.EOL); }
		public TerminalNode EOL(int i) {
			return getToken(RemixParser.EOL, i);
		}
		public List<SeparatorContext> separator() {
			return getRuleContexts(SeparatorContext.class);
		}
		public SeparatorContext separator(int i) {
			return getRuleContext(SeparatorContext.class,i);
		}
		public GetterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_getter; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitGetter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GetterContext getter() throws RecognitionException {
		GetterContext _localctx = new GetterContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_getter);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(259);
			match(GETTER);
			setState(260);
			match(LBLOCK);
			setState(274); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(264);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==EOL) {
					{
					{
					setState(261);
					match(EOL);
					}
					}
					setState(266);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(267);
				fieldId();
				setState(271);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(268);
						separator();
						}
						} 
					}
					setState(273);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
				}
				}
				}
				setState(276); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==EOL || _la==IDENTIFIER );
			setState(278);
			match(RBLOCK);
			setState(282);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==EOL) {
				{
				{
				setState(279);
				match(EOL);
				}
				}
				setState(284);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SetterContext extends ParserRuleContext {
		public TerminalNode SETTER() { return getToken(RemixParser.SETTER, 0); }
		public TerminalNode LBLOCK() { return getToken(RemixParser.LBLOCK, 0); }
		public TerminalNode RBLOCK() { return getToken(RemixParser.RBLOCK, 0); }
		public List<FieldIdContext> fieldId() {
			return getRuleContexts(FieldIdContext.class);
		}
		public FieldIdContext fieldId(int i) {
			return getRuleContext(FieldIdContext.class,i);
		}
		public List<TerminalNode> EOL() { return getTokens(RemixParser.EOL); }
		public TerminalNode EOL(int i) {
			return getToken(RemixParser.EOL, i);
		}
		public List<SeparatorContext> separator() {
			return getRuleContexts(SeparatorContext.class);
		}
		public SeparatorContext separator(int i) {
			return getRuleContext(SeparatorContext.class,i);
		}
		public SetterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_setter; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitSetter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SetterContext setter() throws RecognitionException {
		SetterContext _localctx = new SetterContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_setter);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(285);
			match(SETTER);
			setState(286);
			match(LBLOCK);
			setState(300); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(290);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==EOL) {
					{
					{
					setState(287);
					match(EOL);
					}
					}
					setState(292);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(293);
				fieldId();
				setState(297);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(294);
						separator();
						}
						} 
					}
					setState(299);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
				}
				}
				}
				setState(302); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==EOL || _la==IDENTIFIER );
			setState(304);
			match(RBLOCK);
			setState(308);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==EOL) {
				{
				{
				setState(305);
				match(EOL);
				}
				}
				setState(310);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FieldIdContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(RemixParser.IDENTIFIER, 0); }
		public FieldIdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldId; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitFieldId(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FieldIdContext fieldId() throws RecognitionException {
		FieldIdContext _localctx = new FieldIdContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_fieldId);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(311);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MethodDefinitionContext extends ParserRuleContext {
		public MethodSignatureContext methodSignature() {
			return getRuleContext(MethodSignatureContext.class,0);
		}
		public TerminalNode COLON() { return getToken(RemixParser.COLON, 0); }
		public BlockOfStatementsContext blockOfStatements() {
			return getRuleContext(BlockOfStatementsContext.class,0);
		}
		public FunctionCommentContext functionComment() {
			return getRuleContext(FunctionCommentContext.class,0);
		}
		public List<TerminalNode> EOL() { return getTokens(RemixParser.EOL); }
		public TerminalNode EOL(int i) {
			return getToken(RemixParser.EOL, i);
		}
		public MethodDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodDefinition; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitMethodDefinition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MethodDefinitionContext methodDefinition() throws RecognitionException {
		MethodDefinitionContext _localctx = new MethodDefinitionContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_methodDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(314);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DOC_COMMENT) {
				{
				setState(313);
				functionComment();
				}
			}

			setState(316);
			methodSignature();
			setState(317);
			match(COLON);
			setState(319);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EOL) {
				{
				setState(318);
				match(EOL);
				}
			}

			setState(321);
			blockOfStatements();
			setState(325);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==EOL) {
				{
				{
				setState(322);
				match(EOL);
				}
				}
				setState(327);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MethodSignatureContext extends ParserRuleContext {
		public List<MethodSigPartContext> methodSigPart() {
			return getRuleContexts(MethodSigPartContext.class);
		}
		public MethodSigPartContext methodSigPart(int i) {
			return getRuleContext(MethodSigPartContext.class,i);
		}
		public MethodSignatureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodSignature; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitMethodSignature(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MethodSignatureContext methodSignature() throws RecognitionException {
		MethodSignatureContext _localctx = new MethodSignatureContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_methodSignature);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(329); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(328);
				methodSigPart();
				}
				}
				setState(331); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 7146825580564L) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MethodSigPartContext extends ParserRuleContext {
		public MethodSigPartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodSigPart; }
	 
		public MethodSigPartContext() { }
		public void copyFrom(MethodSigPartContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MethSigWordContext extends MethodSigPartContext {
		public TerminalNode WORD() { return getToken(RemixParser.WORD, 0); }
		public MethSigWordContext(MethodSigPartContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitMethSigWord(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MethSigParamContext extends MethodSigPartContext {
		public TerminalNode IDENTIFIER() { return getToken(RemixParser.IDENTIFIER, 0); }
		public TerminalNode LPAREN() { return getToken(RemixParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(RemixParser.RPAREN, 0); }
		public MethSigParamContext(MethodSigPartContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitMethSigParam(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MethSigBlockContext extends MethodSigPartContext {
		public TerminalNode LBLOCK() { return getToken(RemixParser.LBLOCK, 0); }
		public TerminalNode IDENTIFIER() { return getToken(RemixParser.IDENTIFIER, 0); }
		public TerminalNode RBLOCK() { return getToken(RemixParser.RBLOCK, 0); }
		public MethSigBlockContext(MethodSigPartContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitMethSigBlock(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MethSigSelfContext extends MethodSigPartContext {
		public TerminalNode SELFREF() { return getToken(RemixParser.SELFREF, 0); }
		public TerminalNode LPAREN() { return getToken(RemixParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(RemixParser.RPAREN, 0); }
		public MethSigSelfContext(MethodSigPartContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitMethSigSelf(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MethodSigPartContext methodSigPart() throws RecognitionException {
		MethodSigPartContext _localctx = new MethodSigPartContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_methodSigPart);
		try {
			setState(345);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
			case 1:
				_localctx = new MethSigWordContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(333);
				match(WORD);
				}
				break;
			case 2:
				_localctx = new MethSigParamContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(334);
				match(IDENTIFIER);
				}
				break;
			case 3:
				_localctx = new MethSigParamContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(335);
				match(LPAREN);
				setState(336);
				match(IDENTIFIER);
				setState(337);
				match(RPAREN);
				}
				break;
			case 4:
				_localctx = new MethSigSelfContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(338);
				match(SELFREF);
				}
				break;
			case 5:
				_localctx = new MethSigSelfContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(339);
				match(LPAREN);
				setState(340);
				match(SELFREF);
				setState(341);
				match(RPAREN);
				}
				break;
			case 6:
				_localctx = new MethSigBlockContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(342);
				match(LBLOCK);
				setState(343);
				match(IDENTIFIER);
				setState(344);
				match(RBLOCK);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BlockOfStatementsContext extends ParserRuleContext {
		public TerminalNode LBLOCK() { return getToken(RemixParser.LBLOCK, 0); }
		public TerminalNode RBLOCK() { return getToken(RemixParser.RBLOCK, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public BlockOfStatementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blockOfStatements; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitBlockOfStatements(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockOfStatementsContext blockOfStatements() throws RecognitionException {
		BlockOfStatementsContext _localctx = new BlockOfStatementsContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_blockOfStatements);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(347);
			match(LBLOCK);
			setState(351);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 34651662083668L) != 0)) {
				{
				{
				setState(348);
				statement();
				}
				}
				setState(353);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(354);
			match(RBLOCK);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	 
		public StatementContext() { }
		public void copyFrom(StatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BlankContext extends StatementContext {
		public EndOfStatementContext endOfStatement() {
			return getRuleContext(EndOfStatementContext.class,0);
		}
		public BlankContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitBlank(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PrStatementContext extends StatementContext {
		public PrintStatementContext printStatement() {
			return getRuleContext(PrintStatementContext.class,0);
		}
		public PrStatementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitPrStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends StatementContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExprContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class RedoContext extends StatementContext {
		public TerminalNode REDO() { return getToken(RemixParser.REDO, 0); }
		public RedoContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitRedo(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AssStatementContext extends StatementContext {
		public AssignmentStatementContext assignmentStatement() {
			return getRuleContext(AssignmentStatementContext.class,0);
		}
		public AssStatementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitAssStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ReturnContext extends StatementContext {
		public TerminalNode RETURN() { return getToken(RemixParser.RETURN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ReturnContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitReturn(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_statement);
		try {
			setState(365);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,44,_ctx) ) {
			case 1:
				_localctx = new AssStatementContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(356);
				assignmentStatement();
				}
				break;
			case 2:
				_localctx = new PrStatementContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(357);
				printStatement();
				}
				break;
			case 3:
				_localctx = new ExprContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(358);
				expression(0);
				}
				break;
			case 4:
				_localctx = new RedoContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(359);
				match(REDO);
				}
				break;
			case 5:
				_localctx = new ReturnContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(360);
				match(RETURN);
				setState(362);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,43,_ctx) ) {
				case 1:
					{
					setState(361);
					expression(0);
					}
					break;
				}
				}
				break;
			case 6:
				_localctx = new BlankContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(364);
				endOfStatement();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class EndOfStatementContext extends ParserRuleContext {
		public TerminalNode EOL() { return getToken(RemixParser.EOL, 0); }
		public TerminalNode EOS() { return getToken(RemixParser.EOS, 0); }
		public EndOfStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_endOfStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitEndOfStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EndOfStatementContext endOfStatement() throws RecognitionException {
		EndOfStatementContext _localctx = new EndOfStatementContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_endOfStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(367);
			_la = _input.LA(1);
			if ( !(_la==EOL || _la==EOS) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AssignmentStatementContext extends ParserRuleContext {
		public AssignmentStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignmentStatement; }
	 
		public AssignmentStatementContext() { }
		public void copyFrom(AssignmentStatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SetListElementContext extends AssignmentStatementContext {
		public TerminalNode IDENTIFIER() { return getToken(RemixParser.IDENTIFIER, 0); }
		public TerminalNode COLON() { return getToken(RemixParser.COLON, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<ListPartContext> listPart() {
			return getRuleContexts(ListPartContext.class);
		}
		public ListPartContext listPart(int i) {
			return getRuleContext(ListPartContext.class,i);
		}
		public SetListElementContext(AssignmentStatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitSetListElement(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SetVariableContext extends AssignmentStatementContext {
		public TerminalNode IDENTIFIER() { return getToken(RemixParser.IDENTIFIER, 0); }
		public TerminalNode COLON() { return getToken(RemixParser.COLON, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public SetVariableContext(AssignmentStatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitSetVariable(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SetConstantContext extends AssignmentStatementContext {
		public TerminalNode CONSTANT() { return getToken(RemixParser.CONSTANT, 0); }
		public TerminalNode COLON() { return getToken(RemixParser.COLON, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public SetConstantContext(AssignmentStatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitSetConstant(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentStatementContext assignmentStatement() throws RecognitionException {
		AssignmentStatementContext _localctx = new AssignmentStatementContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_assignmentStatement);
		int _la;
		try {
			setState(384);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,46,_ctx) ) {
			case 1:
				_localctx = new SetVariableContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(369);
				match(IDENTIFIER);
				setState(370);
				match(COLON);
				setState(371);
				expression(0);
				}
				break;
			case 2:
				_localctx = new SetConstantContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(372);
				match(CONSTANT);
				setState(373);
				match(COLON);
				setState(374);
				expression(0);
				}
				break;
			case 3:
				_localctx = new SetListElementContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(375);
				match(IDENTIFIER);
				setState(377); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(376);
					listPart();
					}
					}
					setState(379); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==LBRACE );
				setState(381);
				match(COLON);
				setState(382);
				expression(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PrintStatementContext extends ParserRuleContext {
		public PrintStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_printStatement; }
	 
		public PrintStatementContext() { }
		public void copyFrom(PrintStatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PrntStatementContext extends PrintStatementContext {
		public TerminalNode ENDPRINT() { return getToken(RemixParser.ENDPRINT, 0); }
		public TerminalNode PRINTLN() { return getToken(RemixParser.PRINTLN, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(RemixParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(RemixParser.COMMA, i);
		}
		public PrntStatementContext(PrintStatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitPrntStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrintStatementContext printStatement() throws RecognitionException {
		PrintStatementContext _localctx = new PrintStatementContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_printStatement);
		int _la;
		try {
			_localctx = new PrntStatementContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(394);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 34648440832084L) != 0)) {
				{
				setState(386);
				expression(0);
				setState(391);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(387);
					match(COMMA);
					setState(388);
					expression(0);
					}
					}
					setState(393);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(396);
			_la = _input.LA(1);
			if ( !(_la==ENDPRINT || _la==PRINTLN) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprBooleanContext extends ExpressionContext {
		public TerminalNode BOOLEAN() { return getToken(RemixParser.BOOLEAN, 0); }
		public ExprBooleanContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitExprBoolean(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprNotEqlContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode NOTEQUAL() { return getToken(RemixParser.NOTEQUAL, 0); }
		public ExprNotEqlContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitExprNotEql(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprMinusContext extends ExpressionContext {
		public TerminalNode MINUS() { return getToken(RemixParser.MINUS, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExprMinusContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitExprMinus(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprVarContext extends ExpressionContext {
		public TerminalNode IDENTIFIER() { return getToken(RemixParser.IDENTIFIER, 0); }
		public ExprVarContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitExprVar(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprExtendContext extends ExpressionContext {
		public ExtendObjectContext extendObject() {
			return getRuleContext(ExtendObjectContext.class,0);
		}
		public ExprExtendContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitExprExtend(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprNumberContext extends ExpressionContext {
		public TerminalNode NUMBER() { return getToken(RemixParser.NUMBER, 0); }
		public ExprNumberContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitExprNumber(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprGreatEqlContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode GREATEREQUAL() { return getToken(RemixParser.GREATEREQUAL, 0); }
		public ExprGreatEqlContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitExprGreatEql(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprNullContext extends ExpressionContext {
		public TerminalNode NULL() { return getToken(RemixParser.NULL, 0); }
		public ExprNullContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitExprNull(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprListElementContext extends ExpressionContext {
		public ListElementContext listElement() {
			return getRuleContext(ListElementContext.class,0);
		}
		public ExprListElementContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitExprListElement(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprConcatContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode CONCAT() { return getToken(RemixParser.CONCAT, 0); }
		public ExprConcatContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitExprConcat(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprAddContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode ADD() { return getToken(RemixParser.ADD, 0); }
		public ExprAddContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitExprAdd(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprMapContext extends ExpressionContext {
		public MapContext map() {
			return getRuleContext(MapContext.class,0);
		}
		public ExprMapContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitExprMap(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprConstantContext extends ExpressionContext {
		public TerminalNode CONSTANT() { return getToken(RemixParser.CONSTANT, 0); }
		public ExprConstantContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitExprConstant(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprLessContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode LESS() { return getToken(RemixParser.LESS, 0); }
		public ExprLessContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitExprLess(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprObjectContext extends ExpressionContext {
		public CreateObjectContext createObject() {
			return getRuleContext(CreateObjectContext.class,0);
		}
		public ExprObjectContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitExprObject(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprLessEqlContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode LESSEQUAL() { return getToken(RemixParser.LESSEQUAL, 0); }
		public ExprLessEqlContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitExprLessEql(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprWordProductContext extends ExpressionContext {
		public TerminalNode WORDPRODUCT() { return getToken(RemixParser.WORDPRODUCT, 0); }
		public ExprWordProductContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitExprWordProduct(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprSelfContext extends ExpressionContext {
		public TerminalNode SELFREF() { return getToken(RemixParser.SELFREF, 0); }
		public ExprSelfContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitExprSelf(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprEqualContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode EQUAL() { return getToken(RemixParser.EQUAL, 0); }
		public ExprEqualContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitExprEqual(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprParenContext extends ExpressionContext {
		public TerminalNode LPAREN() { return getToken(RemixParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(RemixParser.RPAREN, 0); }
		public List<TerminalNode> EOL() { return getTokens(RemixParser.EOL); }
		public TerminalNode EOL(int i) {
			return getToken(RemixParser.EOL, i);
		}
		public ExprParenContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitExprParen(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprMulContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode MUL() { return getToken(RemixParser.MUL, 0); }
		public ExprMulContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitExprMul(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprStringContext extends ExpressionContext {
		public TerminalNode STRING() { return getToken(RemixParser.STRING, 0); }
		public ExprStringContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitExprString(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprListContext extends ExpressionContext {
		public ListContext list() {
			return getRuleContext(ListContext.class,0);
		}
		public ExprListContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitExprList(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprGreaterContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode GREATER() { return getToken(RemixParser.GREATER, 0); }
		public ExprGreaterContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitExprGreater(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprBlockContext extends ExpressionContext {
		public BlockOfStatementsContext blockOfStatements() {
			return getRuleContext(BlockOfStatementsContext.class,0);
		}
		public ExprBlockContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitExprBlock(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprFncCallContext extends ExpressionContext {
		public FunctionCallContext functionCall() {
			return getRuleContext(FunctionCallContext.class,0);
		}
		public ExprFncCallContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitExprFncCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 54;
		enterRecursionRule(_localctx, 54, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(432);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,51,_ctx) ) {
			case 1:
				{
				_localctx = new ExprMinusContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(399);
				match(MINUS);
				setState(400);
				expression(26);
				}
				break;
			case 2:
				{
				_localctx = new ExprListElementContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(401);
				listElement();
				}
				break;
			case 3:
				{
				_localctx = new ExprFncCallContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(402);
				functionCall();
				}
				break;
			case 4:
				{
				_localctx = new ExprConstantContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(403);
				match(CONSTANT);
				}
				break;
			case 5:
				{
				_localctx = new ExprVarContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(404);
				match(IDENTIFIER);
				}
				break;
			case 6:
				{
				_localctx = new ExprSelfContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(405);
				match(SELFREF);
				}
				break;
			case 7:
				{
				_localctx = new ExprNumberContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(406);
				match(NUMBER);
				}
				break;
			case 8:
				{
				_localctx = new ExprWordProductContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(407);
				match(WORDPRODUCT);
				}
				break;
			case 9:
				{
				_localctx = new ExprNullContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(408);
				match(NULL);
				}
				break;
			case 10:
				{
				_localctx = new ExprBooleanContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(409);
				match(BOOLEAN);
				}
				break;
			case 11:
				{
				_localctx = new ExprStringContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(410);
				match(STRING);
				}
				break;
			case 12:
				{
				_localctx = new ExprBlockContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(411);
				blockOfStatements();
				}
				break;
			case 13:
				{
				_localctx = new ExprListContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(412);
				list();
				}
				break;
			case 14:
				{
				_localctx = new ExprMapContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(413);
				map();
				}
				break;
			case 15:
				{
				_localctx = new ExprObjectContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(414);
				createObject();
				}
				break;
			case 16:
				{
				_localctx = new ExprExtendContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(415);
				extendObject();
				}
				break;
			case 17:
				{
				_localctx = new ExprParenContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(416);
				match(LPAREN);
				setState(420);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==EOL) {
					{
					{
					setState(417);
					match(EOL);
					}
					}
					setState(422);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(423);
				expression(0);
				setState(427);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==EOL) {
					{
					{
					setState(424);
					match(EOL);
					}
					}
					setState(429);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(430);
				match(RPAREN);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(463);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,53,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(461);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,52,_ctx) ) {
					case 1:
						{
						_localctx = new ExprMulContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(434);
						if (!(precpred(_ctx, 25))) throw new FailedPredicateException(this, "precpred(_ctx, 25)");
						setState(435);
						match(MUL);
						setState(436);
						expression(26);
						}
						break;
					case 2:
						{
						_localctx = new ExprAddContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(437);
						if (!(precpred(_ctx, 24))) throw new FailedPredicateException(this, "precpred(_ctx, 24)");
						setState(438);
						match(ADD);
						setState(439);
						expression(25);
						}
						break;
					case 3:
						{
						_localctx = new ExprLessContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(440);
						if (!(precpred(_ctx, 23))) throw new FailedPredicateException(this, "precpred(_ctx, 23)");
						setState(441);
						match(LESS);
						setState(442);
						expression(24);
						}
						break;
					case 4:
						{
						_localctx = new ExprGreaterContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(443);
						if (!(precpred(_ctx, 22))) throw new FailedPredicateException(this, "precpred(_ctx, 22)");
						setState(444);
						match(GREATER);
						setState(445);
						expression(23);
						}
						break;
					case 5:
						{
						_localctx = new ExprGreatEqlContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(446);
						if (!(precpred(_ctx, 21))) throw new FailedPredicateException(this, "precpred(_ctx, 21)");
						setState(447);
						match(GREATEREQUAL);
						setState(448);
						expression(22);
						}
						break;
					case 6:
						{
						_localctx = new ExprLessEqlContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(449);
						if (!(precpred(_ctx, 20))) throw new FailedPredicateException(this, "precpred(_ctx, 20)");
						setState(450);
						match(LESSEQUAL);
						setState(451);
						expression(21);
						}
						break;
					case 7:
						{
						_localctx = new ExprEqualContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(452);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(453);
						match(EQUAL);
						setState(454);
						expression(20);
						}
						break;
					case 8:
						{
						_localctx = new ExprNotEqlContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(455);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(456);
						match(NOTEQUAL);
						setState(457);
						expression(19);
						}
						break;
					case 9:
						{
						_localctx = new ExprConcatContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(458);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(459);
						match(CONCAT);
						setState(460);
						expression(18);
						}
						break;
					}
					} 
				}
				setState(465);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,53,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ListElementContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(RemixParser.IDENTIFIER, 0); }
		public List<ListPartContext> listPart() {
			return getRuleContexts(ListPartContext.class);
		}
		public ListPartContext listPart(int i) {
			return getRuleContext(ListPartContext.class,i);
		}
		public ListElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listElement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitListElement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListElementContext listElement() throws RecognitionException {
		ListElementContext _localctx = new ListElementContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_listElement);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(466);
			match(IDENTIFIER);
			setState(468); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(467);
					listPart();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(470); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,54,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ListPartContext extends ParserRuleContext {
		public ListPartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listPart; }
	 
		public ListPartContext() { }
		public void copyFrom(ListPartContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ListPartExprContext extends ListPartContext {
		public TerminalNode LBRACE() { return getToken(RemixParser.LBRACE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RBRACE() { return getToken(RemixParser.RBRACE, 0); }
		public ListPartExprContext(ListPartContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitListPartExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListPartContext listPart() throws RecognitionException {
		ListPartContext _localctx = new ListPartContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_listPart);
		try {
			_localctx = new ListPartExprContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(472);
			match(LBRACE);
			setState(473);
			expression(0);
			setState(474);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FunctionCallContext extends ParserRuleContext {
		public List<CallPartContext> callPart() {
			return getRuleContexts(CallPartContext.class);
		}
		public CallPartContext callPart(int i) {
			return getRuleContext(CallPartContext.class,i);
		}
		public SingleWordContext singleWord() {
			return getRuleContext(SingleWordContext.class,0);
		}
		public FunctionCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionCall; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitFunctionCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionCallContext functionCall() throws RecognitionException {
		FunctionCallContext _localctx = new FunctionCallContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_functionCall);
		try {
			int _alt;
			setState(483);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,56,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(476);
				callPart();
				setState(478); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(477);
						callPart();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(480); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,55,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(482);
				singleWord();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CallPartContext extends ParserRuleContext {
		public CallPartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_callPart; }
	 
		public CallPartContext() { }
		public void copyFrom(CallPartContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CallStringContext extends CallPartContext {
		public TerminalNode STRING() { return getToken(RemixParser.STRING, 0); }
		public CallStringContext(CallPartContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitCallString(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CallParamContext extends CallPartContext {
		public TerminalNode LPAREN() { return getToken(RemixParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(RemixParser.RPAREN, 0); }
		public List<TerminalNode> EOL() { return getTokens(RemixParser.EOL); }
		public TerminalNode EOL(int i) {
			return getToken(RemixParser.EOL, i);
		}
		public CallParamContext(CallPartContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitCallParam(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CallWordProductContext extends CallPartContext {
		public TerminalNode WORDPRODUCT() { return getToken(RemixParser.WORDPRODUCT, 0); }
		public CallWordProductContext(CallPartContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitCallWordProduct(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CallBlockContext extends CallPartContext {
		public BlockOfStatementsContext blockOfStatements() {
			return getRuleContext(BlockOfStatementsContext.class,0);
		}
		public CallBlockContext(CallPartContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitCallBlock(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CallMinusExprContext extends CallPartContext {
		public TerminalNode MINUS() { return getToken(RemixParser.MINUS, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public CallMinusExprContext(CallPartContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitCallMinusExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CallMapContext extends CallPartContext {
		public MapContext map() {
			return getRuleContext(MapContext.class,0);
		}
		public CallMapContext(CallPartContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitCallMap(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CallListContext extends CallPartContext {
		public ListContext list() {
			return getRuleContext(ListContext.class,0);
		}
		public CallListContext(CallPartContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitCallList(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CallConstantContext extends CallPartContext {
		public TerminalNode CONSTANT() { return getToken(RemixParser.CONSTANT, 0); }
		public CallConstantContext(CallPartContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitCallConstant(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CallNumberContext extends CallPartContext {
		public TerminalNode NUMBER() { return getToken(RemixParser.NUMBER, 0); }
		public CallNumberContext(CallPartContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitCallNumber(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CallNullContext extends CallPartContext {
		public TerminalNode NULL() { return getToken(RemixParser.NULL, 0); }
		public CallNullContext(CallPartContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitCallNull(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CallVarContext extends CallPartContext {
		public TerminalNode IDENTIFIER() { return getToken(RemixParser.IDENTIFIER, 0); }
		public CallVarContext(CallPartContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitCallVar(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CallBooleanContext extends CallPartContext {
		public TerminalNode BOOLEAN() { return getToken(RemixParser.BOOLEAN, 0); }
		public CallBooleanContext(CallPartContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitCallBoolean(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CallWordContext extends CallPartContext {
		public TerminalNode WORD() { return getToken(RemixParser.WORD, 0); }
		public CallWordContext(CallPartContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitCallWord(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CallSelfContext extends CallPartContext {
		public TerminalNode SELFREF() { return getToken(RemixParser.SELFREF, 0); }
		public TerminalNode LPAREN() { return getToken(RemixParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(RemixParser.RPAREN, 0); }
		public CallSelfContext(CallPartContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitCallSelf(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CallPartContext callPart() throws RecognitionException {
		CallPartContext _localctx = new CallPartContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_callPart);
		int _la;
		try {
			setState(518);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,59,_ctx) ) {
			case 1:
				_localctx = new CallWordContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(485);
				match(WORD);
				}
				break;
			case 2:
				_localctx = new CallMinusExprContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(486);
				match(MINUS);
				setState(487);
				expression(0);
				}
				break;
			case 3:
				_localctx = new CallConstantContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(488);
				match(CONSTANT);
				}
				break;
			case 4:
				_localctx = new CallVarContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(489);
				match(IDENTIFIER);
				}
				break;
			case 5:
				_localctx = new CallParamContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(490);
				match(LPAREN);
				setState(494);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==EOL) {
					{
					{
					setState(491);
					match(EOL);
					}
					}
					setState(496);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(497);
				expression(0);
				setState(501);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==EOL) {
					{
					{
					setState(498);
					match(EOL);
					}
					}
					setState(503);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(504);
				match(RPAREN);
				}
				break;
			case 6:
				_localctx = new CallSelfContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(506);
				match(SELFREF);
				}
				break;
			case 7:
				_localctx = new CallSelfContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(507);
				match(LPAREN);
				setState(508);
				match(SELFREF);
				setState(509);
				match(RPAREN);
				}
				break;
			case 8:
				_localctx = new CallNumberContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(510);
				match(NUMBER);
				}
				break;
			case 9:
				_localctx = new CallWordProductContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(511);
				match(WORDPRODUCT);
				}
				break;
			case 10:
				_localctx = new CallNullContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(512);
				match(NULL);
				}
				break;
			case 11:
				_localctx = new CallBooleanContext(_localctx);
				enterOuterAlt(_localctx, 11);
				{
				setState(513);
				match(BOOLEAN);
				}
				break;
			case 12:
				_localctx = new CallStringContext(_localctx);
				enterOuterAlt(_localctx, 12);
				{
				setState(514);
				match(STRING);
				}
				break;
			case 13:
				_localctx = new CallBlockContext(_localctx);
				enterOuterAlt(_localctx, 13);
				{
				setState(515);
				blockOfStatements();
				}
				break;
			case 14:
				_localctx = new CallListContext(_localctx);
				enterOuterAlt(_localctx, 14);
				{
				setState(516);
				list();
				}
				break;
			case 15:
				_localctx = new CallMapContext(_localctx);
				enterOuterAlt(_localctx, 15);
				{
				setState(517);
				map();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ListContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(RemixParser.LBRACE, 0); }
		public ListContentsContext listContents() {
			return getRuleContext(ListContentsContext.class,0);
		}
		public TerminalNode RBRACE() { return getToken(RemixParser.RBRACE, 0); }
		public ListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_list; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListContext list() throws RecognitionException {
		ListContext _localctx = new ListContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_list);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(520);
			match(LBRACE);
			setState(521);
			listContents();
			setState(522);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SeparatorContext extends ParserRuleContext {
		public TerminalNode EOL() { return getToken(RemixParser.EOL, 0); }
		public TerminalNode COMMA() { return getToken(RemixParser.COMMA, 0); }
		public SeparatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_separator; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitSeparator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SeparatorContext separator() throws RecognitionException {
		SeparatorContext _localctx = new SeparatorContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_separator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(524);
			_la = _input.LA(1);
			if ( !(_la==COMMA || _la==EOL) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ListContentsContext extends ParserRuleContext {
		public ListContentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listContents; }
	 
		public ListContentsContext() { }
		public void copyFrom(ListContentsContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class EmptyListContext extends ListContentsContext {
		public List<TerminalNode> EOL() { return getTokens(RemixParser.EOL); }
		public TerminalNode EOL(int i) {
			return getToken(RemixParser.EOL, i);
		}
		public EmptyListContext(ListContentsContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitEmptyList(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BlockListContext extends ListContentsContext {
		public TerminalNode LBLOCK() { return getToken(RemixParser.LBLOCK, 0); }
		public TerminalNode RBLOCK() { return getToken(RemixParser.RBLOCK, 0); }
		public List<TerminalNode> EOL() { return getTokens(RemixParser.EOL); }
		public TerminalNode EOL(int i) {
			return getToken(RemixParser.EOL, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<SeparatorContext> separator() {
			return getRuleContexts(SeparatorContext.class);
		}
		public SeparatorContext separator(int i) {
			return getRuleContext(SeparatorContext.class,i);
		}
		public BlockListContext(ListContentsContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitBlockList(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CommaListContext extends ListContentsContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(RemixParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(RemixParser.COMMA, i);
		}
		public CommaListContext(ListContentsContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitCommaList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListContentsContext listContents() throws RecognitionException {
		ListContentsContext _localctx = new ListContentsContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_listContents);
		int _la;
		try {
			setState(576);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,68,_ctx) ) {
			case 1:
				_localctx = new CommaListContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(534);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 34648440832084L) != 0)) {
					{
					setState(526);
					expression(0);
					setState(531);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(527);
						match(COMMA);
						setState(528);
						expression(0);
						}
						}
						setState(533);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				}
				break;
			case 2:
				_localctx = new BlockListContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(536);
				match(LBLOCK);
				setState(540);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==EOL) {
					{
					{
					setState(537);
					match(EOL);
					}
					}
					setState(542);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(560);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 34648440832084L) != 0)) {
					{
					{
					setState(543);
					expression(0);
					setState(555);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA || _la==EOL) {
						{
						{
						setState(544);
						separator();
						setState(548);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==EOL) {
							{
							{
							setState(545);
							match(EOL);
							}
							}
							setState(550);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(551);
						expression(0);
						}
						}
						setState(557);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
					}
					setState(562);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(563);
				match(RBLOCK);
				setState(567);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==EOL) {
					{
					{
					setState(564);
					match(EOL);
					}
					}
					setState(569);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 3:
				_localctx = new EmptyListContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(573);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==EOL) {
					{
					{
					setState(570);
					match(EOL);
					}
					}
					setState(575);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MapContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(RemixParser.LBRACE, 0); }
		public MapContentsContext mapContents() {
			return getRuleContext(MapContentsContext.class,0);
		}
		public TerminalNode RBRACE() { return getToken(RemixParser.RBRACE, 0); }
		public MapContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_map; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitMap(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MapContext map() throws RecognitionException {
		MapContext _localctx = new MapContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_map);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(578);
			match(LBRACE);
			setState(579);
			mapContents();
			setState(580);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MapContentsContext extends ParserRuleContext {
		public MapContentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mapContents; }
	 
		public MapContentsContext() { }
		public void copyFrom(MapContentsContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CommaMapContext extends MapContentsContext {
		public List<KeyValueContext> keyValue() {
			return getRuleContexts(KeyValueContext.class);
		}
		public KeyValueContext keyValue(int i) {
			return getRuleContext(KeyValueContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(RemixParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(RemixParser.COMMA, i);
		}
		public CommaMapContext(MapContentsContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitCommaMap(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BlockMapContext extends MapContentsContext {
		public TerminalNode LBLOCK() { return getToken(RemixParser.LBLOCK, 0); }
		public List<KeyValueContext> keyValue() {
			return getRuleContexts(KeyValueContext.class);
		}
		public KeyValueContext keyValue(int i) {
			return getRuleContext(KeyValueContext.class,i);
		}
		public TerminalNode RBLOCK() { return getToken(RemixParser.RBLOCK, 0); }
		public List<TerminalNode> EOL() { return getTokens(RemixParser.EOL); }
		public TerminalNode EOL(int i) {
			return getToken(RemixParser.EOL, i);
		}
		public List<SeparatorContext> separator() {
			return getRuleContexts(SeparatorContext.class);
		}
		public SeparatorContext separator(int i) {
			return getRuleContext(SeparatorContext.class,i);
		}
		public BlockMapContext(MapContentsContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitBlockMap(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MapContentsContext mapContents() throws RecognitionException {
		MapContentsContext _localctx = new MapContentsContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_mapContents);
		int _la;
		try {
			setState(616);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING:
				_localctx = new CommaMapContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(582);
				keyValue();
				setState(587);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(583);
					match(COMMA);
					setState(584);
					keyValue();
					}
					}
					setState(589);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case LBLOCK:
				_localctx = new BlockMapContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(590);
				match(LBLOCK);
				setState(594);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==EOL) {
					{
					{
					setState(591);
					match(EOL);
					}
					}
					setState(596);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(597);
				keyValue();
				setState(609);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA || _la==EOL) {
					{
					{
					setState(598);
					separator();
					setState(602);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==EOL) {
						{
						{
						setState(599);
						match(EOL);
						}
						}
						setState(604);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(605);
					keyValue();
					}
					}
					setState(611);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(612);
				match(RBLOCK);
				setState(614);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==EOL) {
					{
					setState(613);
					match(EOL);
					}
				}

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class KeyValueContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(RemixParser.STRING, 0); }
		public TerminalNode COLON() { return getToken(RemixParser.COLON, 0); }
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public KeyValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyValue; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitKeyValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final KeyValueContext keyValue() throws RecognitionException {
		KeyValueContext _localctx = new KeyValueContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_keyValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(618);
			match(STRING);
			setState(619);
			match(COLON);
			setState(620);
			value();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ValueContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_value);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(622);
			expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 27:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 25);
		case 1:
			return precpred(_ctx, 24);
		case 2:
			return precpred(_ctx, 23);
		case 3:
			return precpred(_ctx, 22);
		case 4:
			return precpred(_ctx, 21);
		case 5:
			return precpred(_ctx, 20);
		case 6:
			return precpred(_ctx, 19);
		case 7:
			return precpred(_ctx, 18);
		case 8:
			return precpred(_ctx, 17);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001-\u0271\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007\"\u0002"+
		"#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0005\u0000T\b\u0000\n\u0000\f\u0000"+
		"W\t\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0003\u0001"+
		"]\b\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0005\u0002b\b\u0002\n\u0002"+
		"\f\u0002e\t\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0005\u0002j\b\u0002"+
		"\n\u0002\f\u0002m\t\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003w\b\u0003"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0005\u0004}\b\u0004"+
		"\n\u0004\f\u0004\u0080\t\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0005\u0005\u0087\b\u0005\n\u0005\f\u0005\u008a\t\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0006\u0003\u0006\u008f\b\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0003\u0006\u0094\b\u0006\u0001\u0006\u0003\u0006"+
		"\u0097\b\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\b\u0001\b\u0004\b\u00a0\b\b\u000b\b\f\b\u00a1\u0001\b\u0003\b\u00a5"+
		"\b\b\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003\n\u00ae"+
		"\b\n\u0001\n\u0001\n\u0001\n\u0003\n\u00b3\b\n\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0005\u000b\u00b8\b\u000b\n\u000b\f\u000b\u00bb\t\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0005\f"+
		"\u00c4\b\f\n\f\f\f\u00c7\t\f\u0001\f\u0001\f\u0001\f\u0001\r\u0005\r\u00cd"+
		"\b\r\n\r\f\r\u00d0\t\r\u0001\r\u0003\r\u00d3\b\r\u0001\r\u0003\r\u00d6"+
		"\b\r\u0001\r\u0003\r\u00d9\b\r\u0001\r\u0005\r\u00dc\b\r\n\r\f\r\u00df"+
		"\t\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0005\u000e\u00e5"+
		"\b\u000e\n\u000e\f\u000e\u00e8\t\u000e\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0005\u000f\u00ed\b\u000f\n\u000f\f\u000f\u00f0\t\u000f\u0001\u000f\u0001"+
		"\u000f\u0005\u000f\u00f4\b\u000f\n\u000f\f\u000f\u00f7\t\u000f\u0004\u000f"+
		"\u00f9\b\u000f\u000b\u000f\f\u000f\u00fa\u0001\u000f\u0001\u000f\u0005"+
		"\u000f\u00ff\b\u000f\n\u000f\f\u000f\u0102\t\u000f\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0005\u0010\u0107\b\u0010\n\u0010\f\u0010\u010a\t\u0010\u0001"+
		"\u0010\u0001\u0010\u0005\u0010\u010e\b\u0010\n\u0010\f\u0010\u0111\t\u0010"+
		"\u0004\u0010\u0113\b\u0010\u000b\u0010\f\u0010\u0114\u0001\u0010\u0001"+
		"\u0010\u0005\u0010\u0119\b\u0010\n\u0010\f\u0010\u011c\t\u0010\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0005\u0011\u0121\b\u0011\n\u0011\f\u0011\u0124"+
		"\t\u0011\u0001\u0011\u0001\u0011\u0005\u0011\u0128\b\u0011\n\u0011\f\u0011"+
		"\u012b\t\u0011\u0004\u0011\u012d\b\u0011\u000b\u0011\f\u0011\u012e\u0001"+
		"\u0011\u0001\u0011\u0005\u0011\u0133\b\u0011\n\u0011\f\u0011\u0136\t\u0011"+
		"\u0001\u0012\u0001\u0012\u0001\u0013\u0003\u0013\u013b\b\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0003\u0013\u0140\b\u0013\u0001\u0013\u0001\u0013"+
		"\u0005\u0013\u0144\b\u0013\n\u0013\f\u0013\u0147\t\u0013\u0001\u0014\u0004"+
		"\u0014\u014a\b\u0014\u000b\u0014\f\u0014\u014b\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0003\u0015\u015a\b\u0015"+
		"\u0001\u0016\u0001\u0016\u0005\u0016\u015e\b\u0016\n\u0016\f\u0016\u0161"+
		"\t\u0016\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0003\u0017\u016b\b\u0017\u0001\u0017\u0003"+
		"\u0017\u016e\b\u0017\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019\u0001"+
		"\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0004"+
		"\u0019\u017a\b\u0019\u000b\u0019\f\u0019\u017b\u0001\u0019\u0001\u0019"+
		"\u0001\u0019\u0003\u0019\u0181\b\u0019\u0001\u001a\u0001\u001a\u0001\u001a"+
		"\u0005\u001a\u0186\b\u001a\n\u001a\f\u001a\u0189\t\u001a\u0003\u001a\u018b"+
		"\b\u001a\u0001\u001a\u0001\u001a\u0001\u001b\u0001\u001b\u0001\u001b\u0001"+
		"\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001"+
		"\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001"+
		"\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0005\u001b\u01a3"+
		"\b\u001b\n\u001b\f\u001b\u01a6\t\u001b\u0001\u001b\u0001\u001b\u0005\u001b"+
		"\u01aa\b\u001b\n\u001b\f\u001b\u01ad\t\u001b\u0001\u001b\u0001\u001b\u0003"+
		"\u001b\u01b1\b\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001"+
		"\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001"+
		"\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001"+
		"\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001"+
		"\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0005\u001b\u01ce"+
		"\b\u001b\n\u001b\f\u001b\u01d1\t\u001b\u0001\u001c\u0001\u001c\u0004\u001c"+
		"\u01d5\b\u001c\u000b\u001c\f\u001c\u01d6\u0001\u001d\u0001\u001d\u0001"+
		"\u001d\u0001\u001d\u0001\u001e\u0001\u001e\u0004\u001e\u01df\b\u001e\u000b"+
		"\u001e\f\u001e\u01e0\u0001\u001e\u0003\u001e\u01e4\b\u001e\u0001\u001f"+
		"\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f"+
		"\u0005\u001f\u01ed\b\u001f\n\u001f\f\u001f\u01f0\t\u001f\u0001\u001f\u0001"+
		"\u001f\u0005\u001f\u01f4\b\u001f\n\u001f\f\u001f\u01f7\t\u001f\u0001\u001f"+
		"\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f"+
		"\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f"+
		"\u0001\u001f\u0003\u001f\u0207\b\u001f\u0001 \u0001 \u0001 \u0001 \u0001"+
		"!\u0001!\u0001\"\u0001\"\u0001\"\u0005\"\u0212\b\"\n\"\f\"\u0215\t\"\u0003"+
		"\"\u0217\b\"\u0001\"\u0001\"\u0005\"\u021b\b\"\n\"\f\"\u021e\t\"\u0001"+
		"\"\u0001\"\u0001\"\u0005\"\u0223\b\"\n\"\f\"\u0226\t\"\u0001\"\u0001\""+
		"\u0005\"\u022a\b\"\n\"\f\"\u022d\t\"\u0005\"\u022f\b\"\n\"\f\"\u0232\t"+
		"\"\u0001\"\u0001\"\u0005\"\u0236\b\"\n\"\f\"\u0239\t\"\u0001\"\u0005\""+
		"\u023c\b\"\n\"\f\"\u023f\t\"\u0003\"\u0241\b\"\u0001#\u0001#\u0001#\u0001"+
		"#\u0001$\u0001$\u0001$\u0005$\u024a\b$\n$\f$\u024d\t$\u0001$\u0001$\u0005"+
		"$\u0251\b$\n$\f$\u0254\t$\u0001$\u0001$\u0001$\u0005$\u0259\b$\n$\f$\u025c"+
		"\t$\u0001$\u0001$\u0005$\u0260\b$\n$\f$\u0263\t$\u0001$\u0001$\u0003$"+
		"\u0267\b$\u0003$\u0269\b$\u0001%\u0001%\u0001%\u0001%\u0001&\u0001&\u0001"+
		"&\u0000\u00016\'\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014"+
		"\u0016\u0018\u001a\u001c\u001e \"$&(*,.02468:<>@BDFHJL\u0000\u0003\u0001"+
		"\u0000\r\u000e\u0001\u0000\t\n\u0002\u0000\b\b\r\r\u02c6\u0000U\u0001"+
		"\u0000\u0000\u0000\u0002Z\u0001\u0000\u0000\u0000\u0004^\u0001\u0000\u0000"+
		"\u0000\u0006v\u0001\u0000\u0000\u0000\bx\u0001\u0000\u0000\u0000\n\u0083"+
		"\u0001\u0000\u0000\u0000\f\u008e\u0001\u0000\u0000\u0000\u000e\u009a\u0001"+
		"\u0000\u0000\u0000\u0010\u00a4\u0001\u0000\u0000\u0000\u0012\u00a6\u0001"+
		"\u0000\u0000\u0000\u0014\u00b2\u0001\u0000\u0000\u0000\u0016\u00b4\u0001"+
		"\u0000\u0000\u0000\u0018\u00bf\u0001\u0000\u0000\u0000\u001a\u00ce\u0001"+
		"\u0000\u0000\u0000\u001c\u00e0\u0001\u0000\u0000\u0000\u001e\u00e9\u0001"+
		"\u0000\u0000\u0000 \u0103\u0001\u0000\u0000\u0000\"\u011d\u0001\u0000"+
		"\u0000\u0000$\u0137\u0001\u0000\u0000\u0000&\u013a\u0001\u0000\u0000\u0000"+
		"(\u0149\u0001\u0000\u0000\u0000*\u0159\u0001\u0000\u0000\u0000,\u015b"+
		"\u0001\u0000\u0000\u0000.\u016d\u0001\u0000\u0000\u00000\u016f\u0001\u0000"+
		"\u0000\u00002\u0180\u0001\u0000\u0000\u00004\u018a\u0001\u0000\u0000\u0000"+
		"6\u01b0\u0001\u0000\u0000\u00008\u01d2\u0001\u0000\u0000\u0000:\u01d8"+
		"\u0001\u0000\u0000\u0000<\u01e3\u0001\u0000\u0000\u0000>\u0206\u0001\u0000"+
		"\u0000\u0000@\u0208\u0001\u0000\u0000\u0000B\u020c\u0001\u0000\u0000\u0000"+
		"D\u0240\u0001\u0000\u0000\u0000F\u0242\u0001\u0000\u0000\u0000H\u0268"+
		"\u0001\u0000\u0000\u0000J\u026a\u0001\u0000\u0000\u0000L\u026e\u0001\u0000"+
		"\u0000\u0000NT\u0003\f\u0006\u0000OT\u0003.\u0017\u0000PT\u0003\b\u0004"+
		"\u0000QT\u0003\u0004\u0002\u0000RT\u0003\u0006\u0003\u0000SN\u0001\u0000"+
		"\u0000\u0000SO\u0001\u0000\u0000\u0000SP\u0001\u0000\u0000\u0000SQ\u0001"+
		"\u0000\u0000\u0000SR\u0001\u0000\u0000\u0000TW\u0001\u0000\u0000\u0000"+
		"US\u0001\u0000\u0000\u0000UV\u0001\u0000\u0000\u0000VX\u0001\u0000\u0000"+
		"\u0000WU\u0001\u0000\u0000\u0000XY\u0005\u0000\u0000\u0001Y\u0001\u0001"+
		"\u0000\u0000\u0000Z\\\u0005%\u0000\u0000[]\u0005,\u0000\u0000\\[\u0001"+
		"\u0000\u0000\u0000\\]\u0001\u0000\u0000\u0000]\u0003\u0001\u0000\u0000"+
		"\u0000^_\u0003\u0002\u0001\u0000_c\u0005\u0004\u0000\u0000`b\u0005\r\u0000"+
		"\u0000a`\u0001\u0000\u0000\u0000be\u0001\u0000\u0000\u0000ca\u0001\u0000"+
		"\u0000\u0000cd\u0001\u0000\u0000\u0000dk\u0001\u0000\u0000\u0000ec\u0001"+
		"\u0000\u0000\u0000fj\u0003\f\u0006\u0000gj\u0003.\u0017\u0000hj\u0003"+
		"\b\u0004\u0000if\u0001\u0000\u0000\u0000ig\u0001\u0000\u0000\u0000ih\u0001"+
		"\u0000\u0000\u0000jm\u0001\u0000\u0000\u0000ki\u0001\u0000\u0000\u0000"+
		"kl\u0001\u0000\u0000\u0000ln\u0001\u0000\u0000\u0000mk\u0001\u0000\u0000"+
		"\u0000no\u0005\u0005\u0000\u0000o\u0005\u0001\u0000\u0000\u0000pq\u0005"+
		")\u0000\u0000qr\u0005\u0001\u0000\u0000rw\u0003\u0004\u0002\u0000st\u0005"+
		"(\u0000\u0000tu\u0005\u0001\u0000\u0000uw\u0003\u0004\u0002\u0000vp\u0001"+
		"\u0000\u0000\u0000vs\u0001\u0000\u0000\u0000w\u0007\u0001\u0000\u0000"+
		"\u0000xy\u0005&\u0000\u0000y~\u00036\u001b\u0000z{\u0005\b\u0000\u0000"+
		"{}\u00036\u001b\u0000|z\u0001\u0000\u0000\u0000}\u0080\u0001\u0000\u0000"+
		"\u0000~|\u0001\u0000\u0000\u0000~\u007f\u0001\u0000\u0000\u0000\u007f"+
		"\u0081\u0001\u0000\u0000\u0000\u0080~\u0001\u0000\u0000\u0000\u0081\u0082"+
		"\u0003\n\u0005\u0000\u0082\t\u0001\u0000\u0000\u0000\u0083\u0088\u0005"+
		"\u0004\u0000\u0000\u0084\u0087\u0003\f\u0006\u0000\u0085\u0087\u0003."+
		"\u0017\u0000\u0086\u0084\u0001\u0000\u0000\u0000\u0086\u0085\u0001\u0000"+
		"\u0000\u0000\u0087\u008a\u0001\u0000\u0000\u0000\u0088\u0086\u0001\u0000"+
		"\u0000\u0000\u0088\u0089\u0001\u0000\u0000\u0000\u0089\u008b\u0001\u0000"+
		"\u0000\u0000\u008a\u0088\u0001\u0000\u0000\u0000\u008b\u008c\u0005\u0005"+
		"\u0000\u0000\u008c\u000b\u0001\u0000\u0000\u0000\u008d\u008f\u0003\u000e"+
		"\u0007\u0000\u008e\u008d\u0001\u0000\u0000\u0000\u008e\u008f\u0001\u0000"+
		"\u0000\u0000\u008f\u0090\u0001\u0000\u0000\u0000\u0090\u0091\u0003\u0010"+
		"\b\u0000\u0091\u0093\u0005\u0001\u0000\u0000\u0092\u0094\u0005\u0001\u0000"+
		"\u0000\u0093\u0092\u0001\u0000\u0000\u0000\u0093\u0094\u0001\u0000\u0000"+
		"\u0000\u0094\u0096\u0001\u0000\u0000\u0000\u0095\u0097\u0005\r\u0000\u0000"+
		"\u0096\u0095\u0001\u0000\u0000\u0000\u0096\u0097\u0001\u0000\u0000\u0000"+
		"\u0097\u0098\u0001\u0000\u0000\u0000\u0098\u0099\u0003,\u0016\u0000\u0099"+
		"\r\u0001\u0000\u0000\u0000\u009a\u009b\u0005\u000f\u0000\u0000\u009b\u009c"+
		"\u0005\r\u0000\u0000\u009c\u000f\u0001\u0000\u0000\u0000\u009d\u009f\u0003"+
		"\u0014\n\u0000\u009e\u00a0\u0003\u0014\n\u0000\u009f\u009e\u0001\u0000"+
		"\u0000\u0000\u00a0\u00a1\u0001\u0000\u0000\u0000\u00a1\u009f\u0001\u0000"+
		"\u0000\u0000\u00a1\u00a2\u0001\u0000\u0000\u0000\u00a2\u00a5\u0001\u0000"+
		"\u0000\u0000\u00a3\u00a5\u0003\u0012\t\u0000\u00a4\u009d\u0001\u0000\u0000"+
		"\u0000\u00a4\u00a3\u0001\u0000\u0000\u0000\u00a5\u0011\u0001\u0000\u0000"+
		"\u0000\u00a6\u00a7\u0005*\u0000\u0000\u00a7\u0013\u0001\u0000\u0000\u0000"+
		"\u00a8\u00b3\u0005*\u0000\u0000\u00a9\u00ae\u0005)\u0000\u0000\u00aa\u00ab"+
		"\u0005\u0002\u0000\u0000\u00ab\u00ac\u0005)\u0000\u0000\u00ac\u00ae\u0005"+
		"\u0003\u0000\u0000\u00ad\u00a9\u0001\u0000\u0000\u0000\u00ad\u00aa\u0001"+
		"\u0000\u0000\u0000\u00ae\u00b3\u0001\u0000\u0000\u0000\u00af\u00b0\u0005"+
		"\u0004\u0000\u0000\u00b0\u00b1\u0005)\u0000\u0000\u00b1\u00b3\u0005\u0005"+
		"\u0000\u0000\u00b2\u00a8\u0001\u0000\u0000\u0000\u00b2\u00ad\u0001\u0000"+
		"\u0000\u0000\u00b2\u00af\u0001\u0000\u0000\u0000\u00b3\u0015\u0001\u0000"+
		"\u0000\u0000\u00b4\u00b5\u0005 \u0000\u0000\u00b5\u00b9\u0005\u0004\u0000"+
		"\u0000\u00b6\u00b8\u0005\r\u0000\u0000\u00b7\u00b6\u0001\u0000\u0000\u0000"+
		"\u00b8\u00bb\u0001\u0000\u0000\u0000\u00b9\u00b7\u0001\u0000\u0000\u0000"+
		"\u00b9\u00ba\u0001\u0000\u0000\u0000\u00ba\u00bc\u0001\u0000\u0000\u0000"+
		"\u00bb\u00b9\u0001\u0000\u0000\u0000\u00bc\u00bd\u0003\u001a\r\u0000\u00bd"+
		"\u00be\u0005\u0005\u0000\u0000\u00be\u0017\u0001\u0000\u0000\u0000\u00bf"+
		"\u00c0\u0005!\u0000\u0000\u00c0\u00c1\u00036\u001b\u0000\u00c1\u00c5\u0005"+
		"\u0004\u0000\u0000\u00c2\u00c4\u0005\r\u0000\u0000\u00c3\u00c2\u0001\u0000"+
		"\u0000\u0000\u00c4\u00c7\u0001\u0000\u0000\u0000\u00c5\u00c3\u0001\u0000"+
		"\u0000\u0000\u00c5\u00c6\u0001\u0000\u0000\u0000\u00c6\u00c8\u0001\u0000"+
		"\u0000\u0000\u00c7\u00c5\u0001\u0000\u0000\u0000\u00c8\u00c9\u0003\u001a"+
		"\r\u0000\u00c9\u00ca\u0005\u0005\u0000\u0000\u00ca\u0019\u0001\u0000\u0000"+
		"\u0000\u00cb\u00cd\u0003\u001c\u000e\u0000\u00cc\u00cb\u0001\u0000\u0000"+
		"\u0000\u00cd\u00d0\u0001\u0000\u0000\u0000\u00ce\u00cc\u0001\u0000\u0000"+
		"\u0000\u00ce\u00cf\u0001\u0000\u0000\u0000\u00cf\u00d2\u0001\u0000\u0000"+
		"\u0000\u00d0\u00ce\u0001\u0000\u0000\u0000\u00d1\u00d3\u0003\u001e\u000f"+
		"\u0000\u00d2\u00d1\u0001\u0000\u0000\u0000\u00d2\u00d3\u0001\u0000\u0000"+
		"\u0000\u00d3\u00d5\u0001\u0000\u0000\u0000\u00d4\u00d6\u0003 \u0010\u0000"+
		"\u00d5\u00d4\u0001\u0000\u0000\u0000\u00d5\u00d6\u0001\u0000\u0000\u0000"+
		"\u00d6\u00d8\u0001\u0000\u0000\u0000\u00d7\u00d9\u0003\"\u0011\u0000\u00d8"+
		"\u00d7\u0001\u0000\u0000\u0000\u00d8\u00d9\u0001\u0000\u0000\u0000\u00d9"+
		"\u00dd\u0001\u0000\u0000\u0000\u00da\u00dc\u0003&\u0013\u0000\u00db\u00da"+
		"\u0001\u0000\u0000\u0000\u00dc\u00df\u0001\u0000\u0000\u0000\u00dd\u00db"+
		"\u0001\u0000\u0000\u0000\u00dd\u00de\u0001\u0000\u0000\u0000\u00de\u001b"+
		"\u0001\u0000\u0000\u0000\u00df\u00dd\u0001\u0000\u0000\u0000\u00e0\u00e1"+
		"\u0005)\u0000\u0000\u00e1\u00e2\u0005\u0001\u0000\u0000\u00e2\u00e6\u0003"+
		"6\u001b\u0000\u00e3\u00e5\u0005\r\u0000\u0000\u00e4\u00e3\u0001\u0000"+
		"\u0000\u0000\u00e5\u00e8\u0001\u0000\u0000\u0000\u00e6\u00e4\u0001\u0000"+
		"\u0000\u0000\u00e6\u00e7\u0001\u0000\u0000\u0000\u00e7\u001d\u0001\u0000"+
		"\u0000\u0000\u00e8\u00e6\u0001\u0000\u0000\u0000\u00e9\u00ea\u0005\"\u0000"+
		"\u0000\u00ea\u00f8\u0005\u0004\u0000\u0000\u00eb\u00ed\u0005\r\u0000\u0000"+
		"\u00ec\u00eb\u0001\u0000\u0000\u0000\u00ed\u00f0\u0001\u0000\u0000\u0000"+
		"\u00ee\u00ec\u0001\u0000\u0000\u0000\u00ee\u00ef\u0001\u0000\u0000\u0000"+
		"\u00ef\u00f1\u0001\u0000\u0000\u0000\u00f0\u00ee\u0001\u0000\u0000\u0000"+
		"\u00f1\u00f5\u0003$\u0012\u0000\u00f2\u00f4\u0003B!\u0000\u00f3\u00f2"+
		"\u0001\u0000\u0000\u0000\u00f4\u00f7\u0001\u0000\u0000\u0000\u00f5\u00f3"+
		"\u0001\u0000\u0000\u0000\u00f5\u00f6\u0001\u0000\u0000\u0000\u00f6\u00f9"+
		"\u0001\u0000\u0000\u0000\u00f7\u00f5\u0001\u0000\u0000\u0000\u00f8\u00ee"+
		"\u0001\u0000\u0000\u0000\u00f9\u00fa\u0001\u0000\u0000\u0000\u00fa\u00f8"+
		"\u0001\u0000\u0000\u0000\u00fa\u00fb\u0001\u0000\u0000\u0000\u00fb\u00fc"+
		"\u0001\u0000\u0000\u0000\u00fc\u0100\u0005\u0005\u0000\u0000\u00fd\u00ff"+
		"\u0005\r\u0000\u0000\u00fe\u00fd\u0001\u0000\u0000\u0000\u00ff\u0102\u0001"+
		"\u0000\u0000\u0000\u0100\u00fe\u0001\u0000\u0000\u0000\u0100\u0101\u0001"+
		"\u0000\u0000\u0000\u0101\u001f\u0001\u0000\u0000\u0000\u0102\u0100\u0001"+
		"\u0000\u0000\u0000\u0103\u0104\u0005#\u0000\u0000\u0104\u0112\u0005\u0004"+
		"\u0000\u0000\u0105\u0107\u0005\r\u0000\u0000\u0106\u0105\u0001\u0000\u0000"+
		"\u0000\u0107\u010a\u0001\u0000\u0000\u0000\u0108\u0106\u0001\u0000\u0000"+
		"\u0000\u0108\u0109\u0001\u0000\u0000\u0000\u0109\u010b\u0001\u0000\u0000"+
		"\u0000\u010a\u0108\u0001\u0000\u0000\u0000\u010b\u010f\u0003$\u0012\u0000"+
		"\u010c\u010e\u0003B!\u0000\u010d\u010c\u0001\u0000\u0000\u0000\u010e\u0111"+
		"\u0001\u0000\u0000\u0000\u010f\u010d\u0001\u0000\u0000\u0000\u010f\u0110"+
		"\u0001\u0000\u0000\u0000\u0110\u0113\u0001\u0000\u0000\u0000\u0111\u010f"+
		"\u0001\u0000\u0000\u0000\u0112\u0108\u0001\u0000\u0000\u0000\u0113\u0114"+
		"\u0001\u0000\u0000\u0000\u0114\u0112\u0001\u0000\u0000\u0000\u0114\u0115"+
		"\u0001\u0000\u0000\u0000\u0115\u0116\u0001\u0000\u0000\u0000\u0116\u011a"+
		"\u0005\u0005\u0000\u0000\u0117\u0119\u0005\r\u0000\u0000\u0118\u0117\u0001"+
		"\u0000\u0000\u0000\u0119\u011c\u0001\u0000\u0000\u0000\u011a\u0118\u0001"+
		"\u0000\u0000\u0000\u011a\u011b\u0001\u0000\u0000\u0000\u011b!\u0001\u0000"+
		"\u0000\u0000\u011c\u011a\u0001\u0000\u0000\u0000\u011d\u011e\u0005$\u0000"+
		"\u0000\u011e\u012c\u0005\u0004\u0000\u0000\u011f\u0121\u0005\r\u0000\u0000"+
		"\u0120\u011f\u0001\u0000\u0000\u0000\u0121\u0124\u0001\u0000\u0000\u0000"+
		"\u0122\u0120\u0001\u0000\u0000\u0000\u0122\u0123\u0001\u0000\u0000\u0000"+
		"\u0123\u0125\u0001\u0000\u0000\u0000\u0124\u0122\u0001\u0000\u0000\u0000"+
		"\u0125\u0129\u0003$\u0012\u0000\u0126\u0128\u0003B!\u0000\u0127\u0126"+
		"\u0001\u0000\u0000\u0000\u0128\u012b\u0001\u0000\u0000\u0000\u0129\u0127"+
		"\u0001\u0000\u0000\u0000\u0129\u012a\u0001\u0000\u0000\u0000\u012a\u012d"+
		"\u0001\u0000\u0000\u0000\u012b\u0129\u0001\u0000\u0000\u0000\u012c\u0122"+
		"\u0001\u0000\u0000\u0000\u012d\u012e\u0001\u0000\u0000\u0000\u012e\u012c"+
		"\u0001\u0000\u0000\u0000\u012e\u012f\u0001\u0000\u0000\u0000\u012f\u0130"+
		"\u0001\u0000\u0000\u0000\u0130\u0134\u0005\u0005\u0000\u0000\u0131\u0133"+
		"\u0005\r\u0000\u0000\u0132\u0131\u0001\u0000\u0000\u0000\u0133\u0136\u0001"+
		"\u0000\u0000\u0000\u0134\u0132\u0001\u0000\u0000\u0000\u0134\u0135\u0001"+
		"\u0000\u0000\u0000\u0135#\u0001\u0000\u0000\u0000\u0136\u0134\u0001\u0000"+
		"\u0000\u0000\u0137\u0138\u0005)\u0000\u0000\u0138%\u0001\u0000\u0000\u0000"+
		"\u0139\u013b\u0003\u000e\u0007\u0000\u013a\u0139\u0001\u0000\u0000\u0000"+
		"\u013a\u013b\u0001\u0000\u0000\u0000\u013b\u013c\u0001\u0000\u0000\u0000"+
		"\u013c\u013d\u0003(\u0014\u0000\u013d\u013f\u0005\u0001\u0000\u0000\u013e"+
		"\u0140\u0005\r\u0000\u0000\u013f\u013e\u0001\u0000\u0000\u0000\u013f\u0140"+
		"\u0001\u0000\u0000\u0000\u0140\u0141\u0001\u0000\u0000\u0000\u0141\u0145"+
		"\u0003,\u0016\u0000\u0142\u0144\u0005\r\u0000\u0000\u0143\u0142\u0001"+
		"\u0000\u0000\u0000\u0144\u0147\u0001\u0000\u0000\u0000\u0145\u0143\u0001"+
		"\u0000\u0000\u0000\u0145\u0146\u0001\u0000\u0000\u0000\u0146\'\u0001\u0000"+
		"\u0000\u0000\u0147\u0145\u0001\u0000\u0000\u0000\u0148\u014a\u0003*\u0015"+
		"\u0000\u0149\u0148\u0001\u0000\u0000\u0000\u014a\u014b\u0001\u0000\u0000"+
		"\u0000\u014b\u0149\u0001\u0000\u0000\u0000\u014b\u014c\u0001\u0000\u0000"+
		"\u0000\u014c)\u0001\u0000\u0000\u0000\u014d\u015a\u0005*\u0000\u0000\u014e"+
		"\u015a\u0005)\u0000\u0000\u014f\u0150\u0005\u0002\u0000\u0000\u0150\u0151"+
		"\u0005)\u0000\u0000\u0151\u015a\u0005\u0003\u0000\u0000\u0152\u015a\u0005"+
		"\'\u0000\u0000\u0153\u0154\u0005\u0002\u0000\u0000\u0154\u0155\u0005\'"+
		"\u0000\u0000\u0155\u015a\u0005\u0003\u0000\u0000\u0156\u0157\u0005\u0004"+
		"\u0000\u0000\u0157\u0158\u0005)\u0000\u0000\u0158\u015a\u0005\u0005\u0000"+
		"\u0000\u0159\u014d\u0001\u0000\u0000\u0000\u0159\u014e\u0001\u0000\u0000"+
		"\u0000\u0159\u014f\u0001\u0000\u0000\u0000\u0159\u0152\u0001\u0000\u0000"+
		"\u0000\u0159\u0153\u0001\u0000\u0000\u0000\u0159\u0156\u0001\u0000\u0000"+
		"\u0000\u015a+\u0001\u0000\u0000\u0000\u015b\u015f\u0005\u0004\u0000\u0000"+
		"\u015c\u015e\u0003.\u0017\u0000\u015d\u015c\u0001\u0000\u0000\u0000\u015e"+
		"\u0161\u0001\u0000\u0000\u0000\u015f\u015d\u0001\u0000\u0000\u0000\u015f"+
		"\u0160\u0001\u0000\u0000\u0000\u0160\u0162\u0001\u0000\u0000\u0000\u0161"+
		"\u015f\u0001\u0000\u0000\u0000\u0162\u0163\u0005\u0005\u0000\u0000\u0163"+
		"-\u0001\u0000\u0000\u0000\u0164\u016e\u00032\u0019\u0000\u0165\u016e\u0003"+
		"4\u001a\u0000\u0166\u016e\u00036\u001b\u0000\u0167\u016e\u0005\u001f\u0000"+
		"\u0000\u0168\u016a\u0005\u001e\u0000\u0000\u0169\u016b\u00036\u001b\u0000"+
		"\u016a\u0169\u0001\u0000\u0000\u0000\u016a\u016b\u0001\u0000\u0000\u0000"+
		"\u016b\u016e\u0001\u0000\u0000\u0000\u016c\u016e\u00030\u0018\u0000\u016d"+
		"\u0164\u0001\u0000\u0000\u0000\u016d\u0165\u0001\u0000\u0000\u0000\u016d"+
		"\u0166\u0001\u0000\u0000\u0000\u016d\u0167\u0001\u0000\u0000\u0000\u016d"+
		"\u0168\u0001\u0000\u0000\u0000\u016d\u016c\u0001\u0000\u0000\u0000\u016e"+
		"/\u0001\u0000\u0000\u0000\u016f\u0170\u0007\u0000\u0000\u0000\u01701\u0001"+
		"\u0000\u0000\u0000\u0171\u0172\u0005)\u0000\u0000\u0172\u0173\u0005\u0001"+
		"\u0000\u0000\u0173\u0181\u00036\u001b\u0000\u0174\u0175\u0005(\u0000\u0000"+
		"\u0175\u0176\u0005\u0001\u0000\u0000\u0176\u0181\u00036\u001b\u0000\u0177"+
		"\u0179\u0005)\u0000\u0000\u0178\u017a\u0003:\u001d\u0000\u0179\u0178\u0001"+
		"\u0000\u0000\u0000\u017a\u017b\u0001\u0000\u0000\u0000\u017b\u0179\u0001"+
		"\u0000\u0000\u0000\u017b\u017c\u0001\u0000\u0000\u0000\u017c\u017d\u0001"+
		"\u0000\u0000\u0000\u017d\u017e\u0005\u0001\u0000\u0000\u017e\u017f\u0003"+
		"6\u001b\u0000\u017f\u0181\u0001\u0000\u0000\u0000\u0180\u0171\u0001\u0000"+
		"\u0000\u0000\u0180\u0174\u0001\u0000\u0000\u0000\u0180\u0177\u0001\u0000"+
		"\u0000\u0000\u01813\u0001\u0000\u0000\u0000\u0182\u0187\u00036\u001b\u0000"+
		"\u0183\u0184\u0005\b\u0000\u0000\u0184\u0186\u00036\u001b\u0000\u0185"+
		"\u0183\u0001\u0000\u0000\u0000\u0186\u0189\u0001\u0000\u0000\u0000\u0187"+
		"\u0185\u0001\u0000\u0000\u0000\u0187\u0188\u0001\u0000\u0000\u0000\u0188"+
		"\u018b\u0001\u0000\u0000\u0000\u0189\u0187\u0001\u0000\u0000\u0000\u018a"+
		"\u0182\u0001\u0000\u0000\u0000\u018a\u018b\u0001\u0000\u0000\u0000\u018b"+
		"\u018c\u0001\u0000\u0000\u0000\u018c\u018d\u0007\u0001\u0000\u0000\u018d"+
		"5\u0001\u0000\u0000\u0000\u018e\u018f\u0006\u001b\uffff\uffff\u0000\u018f"+
		"\u0190\u0005\u001b\u0000\u0000\u0190\u01b1\u00036\u001b\u001a\u0191\u01b1"+
		"\u00038\u001c\u0000\u0192\u01b1\u0003<\u001e\u0000\u0193\u01b1\u0005("+
		"\u0000\u0000\u0194\u01b1\u0005)\u0000\u0000\u0195\u01b1\u0005\'\u0000"+
		"\u0000\u0196\u01b1\u0005\u0011\u0000\u0000\u0197\u01b1\u0005+\u0000\u0000"+
		"\u0198\u01b1\u0005\u001c\u0000\u0000\u0199\u01b1\u0005\u001d\u0000\u0000"+
		"\u019a\u01b1\u0005,\u0000\u0000\u019b\u01b1\u0003,\u0016\u0000\u019c\u01b1"+
		"\u0003@ \u0000\u019d\u01b1\u0003F#\u0000\u019e\u01b1\u0003\u0016\u000b"+
		"\u0000\u019f\u01b1\u0003\u0018\f\u0000\u01a0\u01a4\u0005\u0002\u0000\u0000"+
		"\u01a1\u01a3\u0005\r\u0000\u0000\u01a2\u01a1\u0001\u0000\u0000\u0000\u01a3"+
		"\u01a6\u0001\u0000\u0000\u0000\u01a4\u01a2\u0001\u0000\u0000\u0000\u01a4"+
		"\u01a5\u0001\u0000\u0000\u0000\u01a5\u01a7\u0001\u0000\u0000\u0000\u01a6"+
		"\u01a4\u0001\u0000\u0000\u0000\u01a7\u01ab\u00036\u001b\u0000\u01a8\u01aa"+
		"\u0005\r\u0000\u0000\u01a9\u01a8\u0001\u0000\u0000\u0000\u01aa\u01ad\u0001"+
		"\u0000\u0000\u0000\u01ab\u01a9\u0001\u0000\u0000\u0000\u01ab\u01ac\u0001"+
		"\u0000\u0000\u0000\u01ac\u01ae\u0001\u0000\u0000\u0000\u01ad\u01ab\u0001"+
		"\u0000\u0000\u0000\u01ae\u01af\u0005\u0003\u0000\u0000\u01af\u01b1\u0001"+
		"\u0000\u0000\u0000\u01b0\u018e\u0001\u0000\u0000\u0000\u01b0\u0191\u0001"+
		"\u0000\u0000\u0000\u01b0\u0192\u0001\u0000\u0000\u0000\u01b0\u0193\u0001"+
		"\u0000\u0000\u0000\u01b0\u0194\u0001\u0000\u0000\u0000\u01b0\u0195\u0001"+
		"\u0000\u0000\u0000\u01b0\u0196\u0001\u0000\u0000\u0000\u01b0\u0197\u0001"+
		"\u0000\u0000\u0000\u01b0\u0198\u0001\u0000\u0000\u0000\u01b0\u0199\u0001"+
		"\u0000\u0000\u0000\u01b0\u019a\u0001\u0000\u0000\u0000\u01b0\u019b\u0001"+
		"\u0000\u0000\u0000\u01b0\u019c\u0001\u0000\u0000\u0000\u01b0\u019d\u0001"+
		"\u0000\u0000\u0000\u01b0\u019e\u0001\u0000\u0000\u0000\u01b0\u019f\u0001"+
		"\u0000\u0000\u0000\u01b0\u01a0\u0001\u0000\u0000\u0000\u01b1\u01cf\u0001"+
		"\u0000\u0000\u0000\u01b2\u01b3\n\u0019\u0000\u0000\u01b3\u01b4\u0005\u0013"+
		"\u0000\u0000\u01b4\u01ce\u00036\u001b\u001a\u01b5\u01b6\n\u0018\u0000"+
		"\u0000\u01b6\u01b7\u0005\u0012\u0000\u0000\u01b7\u01ce\u00036\u001b\u0019"+
		"\u01b8\u01b9\n\u0017\u0000\u0000\u01b9\u01ba\u0005\u0014\u0000\u0000\u01ba"+
		"\u01ce\u00036\u001b\u0018\u01bb\u01bc\n\u0016\u0000\u0000\u01bc\u01bd"+
		"\u0005\u0015\u0000\u0000\u01bd\u01ce\u00036\u001b\u0017\u01be\u01bf\n"+
		"\u0015\u0000\u0000\u01bf\u01c0\u0005\u0017\u0000\u0000\u01c0\u01ce\u0003"+
		"6\u001b\u0016\u01c1\u01c2\n\u0014\u0000\u0000\u01c2\u01c3\u0005\u0016"+
		"\u0000\u0000\u01c3\u01ce\u00036\u001b\u0015\u01c4\u01c5\n\u0013\u0000"+
		"\u0000\u01c5\u01c6\u0005\u0018\u0000\u0000\u01c6\u01ce\u00036\u001b\u0014"+
		"\u01c7\u01c8\n\u0012\u0000\u0000\u01c8\u01c9\u0005\u0019\u0000\u0000\u01c9"+
		"\u01ce\u00036\u001b\u0013\u01ca\u01cb\n\u0011\u0000\u0000\u01cb\u01cc"+
		"\u0005\u001a\u0000\u0000\u01cc\u01ce\u00036\u001b\u0012\u01cd\u01b2\u0001"+
		"\u0000\u0000\u0000\u01cd\u01b5\u0001\u0000\u0000\u0000\u01cd\u01b8\u0001"+
		"\u0000\u0000\u0000\u01cd\u01bb\u0001\u0000\u0000\u0000\u01cd\u01be\u0001"+
		"\u0000\u0000\u0000\u01cd\u01c1\u0001\u0000\u0000\u0000\u01cd\u01c4\u0001"+
		"\u0000\u0000\u0000\u01cd\u01c7\u0001\u0000\u0000\u0000\u01cd\u01ca\u0001"+
		"\u0000\u0000\u0000\u01ce\u01d1\u0001\u0000\u0000\u0000\u01cf\u01cd\u0001"+
		"\u0000\u0000\u0000\u01cf\u01d0\u0001\u0000\u0000\u0000\u01d07\u0001\u0000"+
		"\u0000\u0000\u01d1\u01cf\u0001\u0000\u0000\u0000\u01d2\u01d4\u0005)\u0000"+
		"\u0000\u01d3\u01d5\u0003:\u001d\u0000\u01d4\u01d3\u0001\u0000\u0000\u0000"+
		"\u01d5\u01d6\u0001\u0000\u0000\u0000\u01d6\u01d4\u0001\u0000\u0000\u0000"+
		"\u01d6\u01d7\u0001\u0000\u0000\u0000\u01d79\u0001\u0000\u0000\u0000\u01d8"+
		"\u01d9\u0005\u0006\u0000\u0000\u01d9\u01da\u00036\u001b\u0000\u01da\u01db"+
		"\u0005\u0007\u0000\u0000\u01db;\u0001\u0000\u0000\u0000\u01dc\u01de\u0003"+
		">\u001f\u0000\u01dd\u01df\u0003>\u001f\u0000\u01de\u01dd\u0001\u0000\u0000"+
		"\u0000\u01df\u01e0\u0001\u0000\u0000\u0000\u01e0\u01de\u0001\u0000\u0000"+
		"\u0000\u01e0\u01e1\u0001\u0000\u0000\u0000\u01e1\u01e4\u0001\u0000\u0000"+
		"\u0000\u01e2\u01e4\u0003\u0012\t\u0000\u01e3\u01dc\u0001\u0000\u0000\u0000"+
		"\u01e3\u01e2\u0001\u0000\u0000\u0000\u01e4=\u0001\u0000\u0000\u0000\u01e5"+
		"\u0207\u0005*\u0000\u0000\u01e6\u01e7\u0005\u001b\u0000\u0000\u01e7\u0207"+
		"\u00036\u001b\u0000\u01e8\u0207\u0005(\u0000\u0000\u01e9\u0207\u0005)"+
		"\u0000\u0000\u01ea\u01ee\u0005\u0002\u0000\u0000\u01eb\u01ed\u0005\r\u0000"+
		"\u0000\u01ec\u01eb\u0001\u0000\u0000\u0000\u01ed\u01f0\u0001\u0000\u0000"+
		"\u0000\u01ee\u01ec\u0001\u0000\u0000\u0000\u01ee\u01ef\u0001\u0000\u0000"+
		"\u0000\u01ef\u01f1\u0001\u0000\u0000\u0000\u01f0\u01ee\u0001\u0000\u0000"+
		"\u0000\u01f1\u01f5\u00036\u001b\u0000\u01f2\u01f4\u0005\r\u0000\u0000"+
		"\u01f3\u01f2\u0001\u0000\u0000\u0000\u01f4\u01f7\u0001\u0000\u0000\u0000"+
		"\u01f5\u01f3\u0001\u0000\u0000\u0000\u01f5\u01f6\u0001\u0000\u0000\u0000"+
		"\u01f6\u01f8\u0001\u0000\u0000\u0000\u01f7\u01f5\u0001\u0000\u0000\u0000"+
		"\u01f8\u01f9\u0005\u0003\u0000\u0000\u01f9\u0207\u0001\u0000\u0000\u0000"+
		"\u01fa\u0207\u0005\'\u0000\u0000\u01fb\u01fc\u0005\u0002\u0000\u0000\u01fc"+
		"\u01fd\u0005\'\u0000\u0000\u01fd\u0207\u0005\u0003\u0000\u0000\u01fe\u0207"+
		"\u0005\u0011\u0000\u0000\u01ff\u0207\u0005+\u0000\u0000\u0200\u0207\u0005"+
		"\u001c\u0000\u0000\u0201\u0207\u0005\u001d\u0000\u0000\u0202\u0207\u0005"+
		",\u0000\u0000\u0203\u0207\u0003,\u0016\u0000\u0204\u0207\u0003@ \u0000"+
		"\u0205\u0207\u0003F#\u0000\u0206\u01e5\u0001\u0000\u0000\u0000\u0206\u01e6"+
		"\u0001\u0000\u0000\u0000\u0206\u01e8\u0001\u0000\u0000\u0000\u0206\u01e9"+
		"\u0001\u0000\u0000\u0000\u0206\u01ea\u0001\u0000\u0000\u0000\u0206\u01fa"+
		"\u0001\u0000\u0000\u0000\u0206\u01fb\u0001\u0000\u0000\u0000\u0206\u01fe"+
		"\u0001\u0000\u0000\u0000\u0206\u01ff\u0001\u0000\u0000\u0000\u0206\u0200"+
		"\u0001\u0000\u0000\u0000\u0206\u0201\u0001\u0000\u0000\u0000\u0206\u0202"+
		"\u0001\u0000\u0000\u0000\u0206\u0203\u0001\u0000\u0000\u0000\u0206\u0204"+
		"\u0001\u0000\u0000\u0000\u0206\u0205\u0001\u0000\u0000\u0000\u0207?\u0001"+
		"\u0000\u0000\u0000\u0208\u0209\u0005\u0006\u0000\u0000\u0209\u020a\u0003"+
		"D\"\u0000\u020a\u020b\u0005\u0007\u0000\u0000\u020bA\u0001\u0000\u0000"+
		"\u0000\u020c\u020d\u0007\u0002\u0000\u0000\u020dC\u0001\u0000\u0000\u0000"+
		"\u020e\u0213\u00036\u001b\u0000\u020f\u0210\u0005\b\u0000\u0000\u0210"+
		"\u0212\u00036\u001b\u0000\u0211\u020f\u0001\u0000\u0000\u0000\u0212\u0215"+
		"\u0001\u0000\u0000\u0000\u0213\u0211\u0001\u0000\u0000\u0000\u0213\u0214"+
		"\u0001\u0000\u0000\u0000\u0214\u0217\u0001\u0000\u0000\u0000\u0215\u0213"+
		"\u0001\u0000\u0000\u0000\u0216\u020e\u0001\u0000\u0000\u0000\u0216\u0217"+
		"\u0001\u0000\u0000\u0000\u0217\u0241\u0001\u0000\u0000\u0000\u0218\u021c"+
		"\u0005\u0004\u0000\u0000\u0219\u021b\u0005\r\u0000\u0000\u021a\u0219\u0001"+
		"\u0000\u0000\u0000\u021b\u021e\u0001\u0000\u0000\u0000\u021c\u021a\u0001"+
		"\u0000\u0000\u0000\u021c\u021d\u0001\u0000\u0000\u0000\u021d\u0230\u0001"+
		"\u0000\u0000\u0000\u021e\u021c\u0001\u0000\u0000\u0000\u021f\u022b\u0003"+
		"6\u001b\u0000\u0220\u0224\u0003B!\u0000\u0221\u0223\u0005\r\u0000\u0000"+
		"\u0222\u0221\u0001\u0000\u0000\u0000\u0223\u0226\u0001\u0000\u0000\u0000"+
		"\u0224\u0222\u0001\u0000\u0000\u0000\u0224\u0225\u0001\u0000\u0000\u0000"+
		"\u0225\u0227\u0001\u0000\u0000\u0000\u0226\u0224\u0001\u0000\u0000\u0000"+
		"\u0227\u0228\u00036\u001b\u0000\u0228\u022a\u0001\u0000\u0000\u0000\u0229"+
		"\u0220\u0001\u0000\u0000\u0000\u022a\u022d\u0001\u0000\u0000\u0000\u022b"+
		"\u0229\u0001\u0000\u0000\u0000\u022b\u022c\u0001\u0000\u0000\u0000\u022c"+
		"\u022f\u0001\u0000\u0000\u0000\u022d\u022b\u0001\u0000\u0000\u0000\u022e"+
		"\u021f\u0001\u0000\u0000\u0000\u022f\u0232\u0001\u0000\u0000\u0000\u0230"+
		"\u022e\u0001\u0000\u0000\u0000\u0230\u0231\u0001\u0000\u0000\u0000\u0231"+
		"\u0233\u0001\u0000\u0000\u0000\u0232\u0230\u0001\u0000\u0000\u0000\u0233"+
		"\u0237\u0005\u0005\u0000\u0000\u0234\u0236\u0005\r\u0000\u0000\u0235\u0234"+
		"\u0001\u0000\u0000\u0000\u0236\u0239\u0001\u0000\u0000\u0000\u0237\u0235"+
		"\u0001\u0000\u0000\u0000\u0237\u0238\u0001\u0000\u0000\u0000\u0238\u0241"+
		"\u0001\u0000\u0000\u0000\u0239\u0237\u0001\u0000\u0000\u0000\u023a\u023c"+
		"\u0005\r\u0000\u0000\u023b\u023a\u0001\u0000\u0000\u0000\u023c\u023f\u0001"+
		"\u0000\u0000\u0000\u023d\u023b\u0001\u0000\u0000\u0000\u023d\u023e\u0001"+
		"\u0000\u0000\u0000\u023e\u0241\u0001\u0000\u0000\u0000\u023f\u023d\u0001"+
		"\u0000\u0000\u0000\u0240\u0216\u0001\u0000\u0000\u0000\u0240\u0218\u0001"+
		"\u0000\u0000\u0000\u0240\u023d\u0001\u0000\u0000\u0000\u0241E\u0001\u0000"+
		"\u0000\u0000\u0242\u0243\u0005\u0006\u0000\u0000\u0243\u0244\u0003H$\u0000"+
		"\u0244\u0245\u0005\u0007\u0000\u0000\u0245G\u0001\u0000\u0000\u0000\u0246"+
		"\u024b\u0003J%\u0000\u0247\u0248\u0005\b\u0000\u0000\u0248\u024a\u0003"+
		"J%\u0000\u0249\u0247\u0001\u0000\u0000\u0000\u024a\u024d\u0001\u0000\u0000"+
		"\u0000\u024b\u0249\u0001\u0000\u0000\u0000\u024b\u024c\u0001\u0000\u0000"+
		"\u0000\u024c\u0269\u0001\u0000\u0000\u0000\u024d\u024b\u0001\u0000\u0000"+
		"\u0000\u024e\u0252\u0005\u0004\u0000\u0000\u024f\u0251\u0005\r\u0000\u0000"+
		"\u0250\u024f\u0001\u0000\u0000\u0000\u0251\u0254\u0001\u0000\u0000\u0000"+
		"\u0252\u0250\u0001\u0000\u0000\u0000\u0252\u0253\u0001\u0000\u0000\u0000"+
		"\u0253\u0255\u0001\u0000\u0000\u0000\u0254\u0252\u0001\u0000\u0000\u0000"+
		"\u0255\u0261\u0003J%\u0000\u0256\u025a\u0003B!\u0000\u0257\u0259\u0005"+
		"\r\u0000\u0000\u0258\u0257\u0001\u0000\u0000\u0000\u0259\u025c\u0001\u0000"+
		"\u0000\u0000\u025a\u0258\u0001\u0000\u0000\u0000\u025a\u025b\u0001\u0000"+
		"\u0000\u0000\u025b\u025d\u0001\u0000\u0000\u0000\u025c\u025a\u0001\u0000"+
		"\u0000\u0000\u025d\u025e\u0003J%\u0000\u025e\u0260\u0001\u0000\u0000\u0000"+
		"\u025f\u0256\u0001\u0000\u0000\u0000\u0260\u0263\u0001\u0000\u0000\u0000"+
		"\u0261\u025f\u0001\u0000\u0000\u0000\u0261\u0262\u0001\u0000\u0000\u0000"+
		"\u0262\u0264\u0001\u0000\u0000\u0000\u0263\u0261\u0001\u0000\u0000\u0000"+
		"\u0264\u0266\u0005\u0005\u0000\u0000\u0265\u0267\u0005\r\u0000\u0000\u0266"+
		"\u0265\u0001\u0000\u0000\u0000\u0266\u0267\u0001\u0000\u0000\u0000\u0267"+
		"\u0269\u0001\u0000\u0000\u0000\u0268\u0246\u0001\u0000\u0000\u0000\u0268"+
		"\u024e\u0001\u0000\u0000\u0000\u0269I\u0001\u0000\u0000\u0000\u026a\u026b"+
		"\u0005,\u0000\u0000\u026b\u026c\u0005\u0001\u0000\u0000\u026c\u026d\u0003"+
		"L&\u0000\u026dK\u0001\u0000\u0000\u0000\u026e\u026f\u00036\u001b\u0000"+
		"\u026fM\u0001\u0000\u0000\u0000KSU\\cikv~\u0086\u0088\u008e\u0093\u0096"+
		"\u00a1\u00a4\u00ad\u00b2\u00b9\u00c5\u00ce\u00d2\u00d5\u00d8\u00dd\u00e6"+
		"\u00ee\u00f5\u00fa\u0100\u0108\u010f\u0114\u011a\u0122\u0129\u012e\u0134"+
		"\u013a\u013f\u0145\u014b\u0159\u015f\u016a\u016d\u017b\u0180\u0187\u018a"+
		"\u01a4\u01ab\u01b0\u01cd\u01cf\u01d6\u01e0\u01e3\u01ee\u01f5\u0206\u0213"+
		"\u0216\u021c\u0224\u022b\u0230\u0237\u023d\u0240\u024b\u0252\u025a\u0261"+
		"\u0266\u0268";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}