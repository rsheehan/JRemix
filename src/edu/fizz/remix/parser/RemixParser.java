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
		GETTER=35, SETTER=36, LIBRARY=37, USING=38, SELFREF=39, POSSESSIVE=40, 
		CONSTANT=41, IDENTIFIER=42, WORD=43, WORDPRODUCT=44, STRING=45;
	public static final int
		RULE_program = 0, RULE_library = 1, RULE_usingLibrary = 2, RULE_usingBlock = 3, 
		RULE_functionDefinition = 4, RULE_functionComment = 5, RULE_functionSignature = 6, 
		RULE_singleWord = 7, RULE_sigPart = 8, RULE_createObject = 9, RULE_extendObject = 10, 
		RULE_object = 11, RULE_field = 12, RULE_getterSetter = 13, RULE_getter = 14, 
		RULE_setter = 15, RULE_fieldId = 16, RULE_methodDefinition = 17, RULE_methodSignature = 18, 
		RULE_getterSignature = 19, RULE_setterSignature = 20, RULE_methodSigPart = 21, 
		RULE_blockOfStatements = 22, RULE_statement = 23, RULE_endOfStatement = 24, 
		RULE_assignmentStatement = 25, RULE_printStatement = 26, RULE_expression = 27, 
		RULE_getterMethodCall = 28, RULE_setterMethodCall = 29, RULE_getterSetterObject = 30, 
		RULE_listElement = 31, RULE_listPart = 32, RULE_functionCall = 33, RULE_callPart = 34, 
		RULE_list = 35, RULE_separator = 36, RULE_listContents = 37, RULE_map = 38, 
		RULE_mapContents = 39, RULE_keyValue = 40, RULE_value = 41;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "library", "usingLibrary", "usingBlock", "functionDefinition", 
			"functionComment", "functionSignature", "singleWord", "sigPart", "createObject", 
			"extendObject", "object", "field", "getterSetter", "getter", "setter", 
			"fieldId", "methodDefinition", "methodSignature", "getterSignature", 
			"setterSignature", "methodSigPart", "blockOfStatements", "statement", 
			"endOfStatement", "assignmentStatement", "printStatement", "expression", 
			"getterMethodCall", "setterMethodCall", "getterSetterObject", "listElement", 
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
			"USING", "SELFREF", "POSSESSIVE", "CONSTANT", "IDENTIFIER", "WORD", "WORDPRODUCT", 
			"STRING"
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
			setState(88);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 69148839437908L) != 0)) {
				{
				setState(86);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(84);
					functionDefinition();
					}
					break;
				case 2:
					{
					setState(85);
					statement();
					}
					break;
				}
				}
				setState(90);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(91);
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
	public static class LibraryContext extends ParserRuleContext {
		public TerminalNode LIBRARY() { return getToken(RemixParser.LIBRARY, 0); }
		public TerminalNode LBLOCK() { return getToken(RemixParser.LBLOCK, 0); }
		public TerminalNode RBLOCK() { return getToken(RemixParser.RBLOCK, 0); }
		public TerminalNode STRING() { return getToken(RemixParser.STRING, 0); }
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
		enterRule(_localctx, 2, RULE_library);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
			match(LIBRARY);
			setState(95);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==STRING) {
				{
				setState(94);
				match(STRING);
				}
			}

			setState(97);
			match(LBLOCK);
			setState(101);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(98);
					match(EOL);
					}
					} 
				}
				setState(103);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			}
			setState(108);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 69148839437908L) != 0)) {
				{
				setState(106);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
				case 1:
					{
					setState(104);
					functionDefinition();
					}
					break;
				case 2:
					{
					setState(105);
					statement();
					}
					break;
				}
				}
				setState(110);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(111);
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
	public static class UsingLibraryContext extends ParserRuleContext {
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
		public UsingLibraryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_usingLibrary; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitUsingLibrary(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UsingLibraryContext usingLibrary() throws RecognitionException {
		UsingLibraryContext _localctx = new UsingLibraryContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_usingLibrary);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			match(USING);
			setState(114);
			expression(0);
			setState(119);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(115);
				match(COMMA);
				setState(116);
				expression(0);
				}
				}
				setState(121);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(122);
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
		enterRule(_localctx, 6, RULE_usingBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
			match(LBLOCK);
			setState(129);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 69148839437908L) != 0)) {
				{
				setState(127);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
				case 1:
					{
					setState(125);
					functionDefinition();
					}
					break;
				case 2:
					{
					setState(126);
					statement();
					}
					break;
				}
				}
				setState(131);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(132);
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
		enterRule(_localctx, 8, RULE_functionDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(135);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DOC_COMMENT) {
				{
				setState(134);
				functionComment();
				}
			}

			setState(137);
			functionSignature();
			setState(138);
			match(COLON);
			setState(140);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(139);
				match(COLON);
				}
			}

			setState(143);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EOL) {
				{
				setState(142);
				match(EOL);
				}
			}

			setState(145);
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
		enterRule(_localctx, 10, RULE_functionComment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147);
			match(DOC_COMMENT);
			setState(148);
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
		enterRule(_localctx, 12, RULE_functionSignature);
		int _la;
		try {
			setState(157);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(150);
				sigPart();
				setState(152); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(151);
					sigPart();
					}
					}
					setState(154); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 13194139533332L) != 0) );
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(156);
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
		enterRule(_localctx, 14, RULE_singleWord);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(159);
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
		enterRule(_localctx, 16, RULE_sigPart);
		try {
			setState(171);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case WORD:
				_localctx = new SigWordContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(161);
				match(WORD);
				}
				break;
			case LPAREN:
			case IDENTIFIER:
				_localctx = new SigParamContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(166);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case IDENTIFIER:
					{
					setState(162);
					match(IDENTIFIER);
					}
					break;
				case LPAREN:
					{
					setState(163);
					match(LPAREN);
					setState(164);
					match(IDENTIFIER);
					setState(165);
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
				setState(168);
				match(LBLOCK);
				setState(169);
				match(IDENTIFIER);
				setState(170);
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
		enterRule(_localctx, 18, RULE_createObject);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(173);
			match(CREATE);
			setState(174);
			match(LBLOCK);
			setState(178);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==EOL) {
				{
				{
				setState(175);
				match(EOL);
				}
				}
				setState(180);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(181);
			object();
			setState(182);
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
		enterRule(_localctx, 20, RULE_extendObject);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(184);
			match(EXTEND);
			setState(185);
			expression(0);
			setState(186);
			match(LBLOCK);
			setState(190);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==EOL) {
				{
				{
				setState(187);
				match(EOL);
				}
				}
				setState(192);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(193);
			object();
			setState(194);
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
		enterRule(_localctx, 22, RULE_object);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(199);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(196);
					field();
					}
					} 
				}
				setState(201);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			}
			{
			setState(203);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==GETTERSETTER) {
				{
				setState(202);
				getterSetter();
				}
			}

			setState(206);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==GETTER) {
				{
				setState(205);
				getter();
				}
			}

			setState(209);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SETTER) {
				{
				setState(208);
				setter();
				}
			}

			}
			setState(214);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 13743895379988L) != 0)) {
				{
				{
				setState(211);
				methodDefinition();
				}
				}
				setState(216);
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
		enterRule(_localctx, 24, RULE_field);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(217);
			match(IDENTIFIER);
			setState(218);
			match(COLON);
			setState(219);
			expression(0);
			setState(223);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==EOL) {
				{
				{
				setState(220);
				match(EOL);
				}
				}
				setState(225);
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
		enterRule(_localctx, 26, RULE_getterSetter);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(226);
			match(GETTERSETTER);
			setState(227);
			match(LBLOCK);
			setState(241); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(231);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==EOL) {
					{
					{
					setState(228);
					match(EOL);
					}
					}
					setState(233);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(234);
				fieldId();
				setState(238);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(235);
						separator();
						}
						} 
					}
					setState(240);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
				}
				}
				}
				setState(243); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==EOL || _la==IDENTIFIER );
			setState(245);
			match(RBLOCK);
			setState(249);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==EOL) {
				{
				{
				setState(246);
				match(EOL);
				}
				}
				setState(251);
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
		enterRule(_localctx, 28, RULE_getter);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(252);
			match(GETTER);
			setState(253);
			match(LBLOCK);
			setState(267); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(257);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==EOL) {
					{
					{
					setState(254);
					match(EOL);
					}
					}
					setState(259);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(260);
				fieldId();
				setState(264);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(261);
						separator();
						}
						} 
					}
					setState(266);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
				}
				}
				}
				setState(269); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==EOL || _la==IDENTIFIER );
			setState(271);
			match(RBLOCK);
			setState(275);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==EOL) {
				{
				{
				setState(272);
				match(EOL);
				}
				}
				setState(277);
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
		enterRule(_localctx, 30, RULE_setter);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(278);
			match(SETTER);
			setState(279);
			match(LBLOCK);
			setState(293); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(283);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==EOL) {
					{
					{
					setState(280);
					match(EOL);
					}
					}
					setState(285);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(286);
				fieldId();
				setState(290);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(287);
						separator();
						}
						} 
					}
					setState(292);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
				}
				}
				}
				setState(295); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==EOL || _la==IDENTIFIER );
			setState(297);
			match(RBLOCK);
			setState(301);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==EOL) {
				{
				{
				setState(298);
				match(EOL);
				}
				}
				setState(303);
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
		enterRule(_localctx, 32, RULE_fieldId);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(304);
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
		public TerminalNode COLON() { return getToken(RemixParser.COLON, 0); }
		public BlockOfStatementsContext blockOfStatements() {
			return getRuleContext(BlockOfStatementsContext.class,0);
		}
		public SetterSignatureContext setterSignature() {
			return getRuleContext(SetterSignatureContext.class,0);
		}
		public GetterSignatureContext getterSignature() {
			return getRuleContext(GetterSignatureContext.class,0);
		}
		public MethodSignatureContext methodSignature() {
			return getRuleContext(MethodSignatureContext.class,0);
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
		enterRule(_localctx, 34, RULE_methodDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(307);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DOC_COMMENT) {
				{
				setState(306);
				functionComment();
				}
			}

			setState(312);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
			case 1:
				{
				setState(309);
				setterSignature();
				}
				break;
			case 2:
				{
				setState(310);
				getterSignature();
				}
				break;
			case 3:
				{
				setState(311);
				methodSignature();
				}
				break;
			}
			setState(314);
			match(COLON);
			setState(316);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EOL) {
				{
				setState(315);
				match(EOL);
				}
			}

			setState(318);
			blockOfStatements();
			setState(322);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==EOL) {
				{
				{
				setState(319);
				match(EOL);
				}
				}
				setState(324);
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
		enterRule(_localctx, 36, RULE_methodSignature);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(325);
			methodSigPart();
			setState(327); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(326);
				methodSigPart();
				}
				}
				setState(329); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 13743895347220L) != 0) );
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
	public static class GetterSignatureContext extends ParserRuleContext {
		public TerminalNode SELFREF() { return getToken(RemixParser.SELFREF, 0); }
		public TerminalNode IDENTIFIER() { return getToken(RemixParser.IDENTIFIER, 0); }
		public GetterSignatureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_getterSignature; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitGetterSignature(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GetterSignatureContext getterSignature() throws RecognitionException {
		GetterSignatureContext _localctx = new GetterSignatureContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_getterSignature);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(331);
			match(SELFREF);
			setState(332);
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
	public static class SetterSignatureContext extends ParserRuleContext {
		public TerminalNode SELFREF() { return getToken(RemixParser.SELFREF, 0); }
		public List<TerminalNode> IDENTIFIER() { return getTokens(RemixParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(RemixParser.IDENTIFIER, i);
		}
		public TerminalNode LPAREN() { return getToken(RemixParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(RemixParser.RPAREN, 0); }
		public SetterSignatureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_setterSignature; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitSetterSignature(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SetterSignatureContext setterSignature() throws RecognitionException {
		SetterSignatureContext _localctx = new SetterSignatureContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_setterSignature);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(334);
			match(SELFREF);
			setState(335);
			match(IDENTIFIER);
			setState(340);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				{
				setState(336);
				match(IDENTIFIER);
				}
				break;
			case LPAREN:
				{
				setState(337);
				match(LPAREN);
				setState(338);
				match(IDENTIFIER);
				setState(339);
				match(RPAREN);
				}
				break;
			default:
				throw new NoViableAltException(this);
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
			setState(354);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
			case 1:
				_localctx = new MethSigWordContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(342);
				match(WORD);
				}
				break;
			case 2:
				_localctx = new MethSigParamContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(343);
				match(IDENTIFIER);
				}
				break;
			case 3:
				_localctx = new MethSigParamContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(344);
				match(LPAREN);
				setState(345);
				match(IDENTIFIER);
				setState(346);
				match(RPAREN);
				}
				break;
			case 4:
				_localctx = new MethSigSelfContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(347);
				match(SELFREF);
				}
				break;
			case 5:
				_localctx = new MethSigSelfContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(348);
				match(LPAREN);
				setState(349);
				match(SELFREF);
				setState(350);
				match(RPAREN);
				}
				break;
			case 6:
				_localctx = new MethSigBlockContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(351);
				match(LBLOCK);
				setState(352);
				match(IDENTIFIER);
				setState(353);
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
			setState(356);
			match(LBLOCK);
			setState(360);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 69148839405140L) != 0)) {
				{
				{
				setState(357);
				statement();
				}
				}
				setState(362);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(363);
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
	public static class UsingStatementContext extends StatementContext {
		public UsingLibraryContext usingLibrary() {
			return getRuleContext(UsingLibraryContext.class,0);
		}
		public UsingStatementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitUsingStatement(this);
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
			setState(375);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,45,_ctx) ) {
			case 1:
				_localctx = new AssStatementContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(365);
				assignmentStatement();
				}
				break;
			case 2:
				_localctx = new PrStatementContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(366);
				printStatement();
				}
				break;
			case 3:
				_localctx = new ExprContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(367);
				expression(0);
				}
				break;
			case 4:
				_localctx = new RedoContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(368);
				match(REDO);
				}
				break;
			case 5:
				_localctx = new ReturnContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(369);
				match(RETURN);
				setState(371);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,44,_ctx) ) {
				case 1:
					{
					setState(370);
					expression(0);
					}
					break;
				}
				}
				break;
			case 6:
				_localctx = new UsingStatementContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(373);
				usingLibrary();
				}
				break;
			case 7:
				_localctx = new BlankContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(374);
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
			setState(377);
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
			setState(394);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,47,_ctx) ) {
			case 1:
				_localctx = new SetVariableContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(379);
				match(IDENTIFIER);
				setState(380);
				match(COLON);
				setState(381);
				expression(0);
				}
				break;
			case 2:
				_localctx = new SetConstantContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(382);
				match(CONSTANT);
				setState(383);
				match(COLON);
				setState(384);
				expression(0);
				}
				break;
			case 3:
				_localctx = new SetListElementContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(385);
				match(IDENTIFIER);
				setState(387); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(386);
					listPart();
					}
					}
					setState(389); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==LBRACE );
				setState(391);
				match(COLON);
				setState(392);
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
			setState(404);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 68870740246612L) != 0)) {
				{
				setState(396);
				expression(0);
				setState(401);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(397);
					match(COMMA);
					setState(398);
					expression(0);
					}
					}
					setState(403);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(406);
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
	public static class ExprLibraryContext extends ExpressionContext {
		public LibraryContext library() {
			return getRuleContext(LibraryContext.class,0);
		}
		public ExprLibraryContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitExprLibrary(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprGetterMethodContext extends ExpressionContext {
		public GetterMethodCallContext getterMethodCall() {
			return getRuleContext(GetterMethodCallContext.class,0);
		}
		public ExprGetterMethodContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitExprGetterMethod(this);
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
	@SuppressWarnings("CheckReturnValue")
	public static class ExprSetterMethodContext extends ExpressionContext {
		public SetterMethodCallContext setterMethodCall() {
			return getRuleContext(SetterMethodCallContext.class,0);
		}
		public ExprSetterMethodContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitExprSetterMethod(this);
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
			setState(445);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,52,_ctx) ) {
			case 1:
				{
				_localctx = new ExprMinusContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(409);
				match(MINUS);
				setState(410);
				expression(29);
				}
				break;
			case 2:
				{
				_localctx = new ExprListElementContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(411);
				listElement();
				}
				break;
			case 3:
				{
				_localctx = new ExprFncCallContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(412);
				functionCall();
				}
				break;
			case 4:
				{
				_localctx = new ExprConstantContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(413);
				match(CONSTANT);
				}
				break;
			case 5:
				{
				_localctx = new ExprVarContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(414);
				match(IDENTIFIER);
				}
				break;
			case 6:
				{
				_localctx = new ExprSelfContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(415);
				match(SELFREF);
				}
				break;
			case 7:
				{
				_localctx = new ExprNumberContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(416);
				match(NUMBER);
				}
				break;
			case 8:
				{
				_localctx = new ExprWordProductContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(417);
				match(WORDPRODUCT);
				}
				break;
			case 9:
				{
				_localctx = new ExprNullContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(418);
				match(NULL);
				}
				break;
			case 10:
				{
				_localctx = new ExprBooleanContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(419);
				match(BOOLEAN);
				}
				break;
			case 11:
				{
				_localctx = new ExprStringContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(420);
				match(STRING);
				}
				break;
			case 12:
				{
				_localctx = new ExprBlockContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(421);
				blockOfStatements();
				}
				break;
			case 13:
				{
				_localctx = new ExprListContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(422);
				list();
				}
				break;
			case 14:
				{
				_localctx = new ExprMapContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(423);
				map();
				}
				break;
			case 15:
				{
				_localctx = new ExprLibraryContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(424);
				library();
				}
				break;
			case 16:
				{
				_localctx = new ExprObjectContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(425);
				createObject();
				}
				break;
			case 17:
				{
				_localctx = new ExprExtendContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(426);
				extendObject();
				}
				break;
			case 18:
				{
				_localctx = new ExprGetterMethodContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(427);
				getterMethodCall();
				}
				break;
			case 19:
				{
				_localctx = new ExprSetterMethodContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(428);
				setterMethodCall();
				}
				break;
			case 20:
				{
				_localctx = new ExprParenContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(429);
				match(LPAREN);
				setState(433);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==EOL) {
					{
					{
					setState(430);
					match(EOL);
					}
					}
					setState(435);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(436);
				expression(0);
				setState(440);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==EOL) {
					{
					{
					setState(437);
					match(EOL);
					}
					}
					setState(442);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(443);
				match(RPAREN);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(476);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,54,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(474);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,53,_ctx) ) {
					case 1:
						{
						_localctx = new ExprMulContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(447);
						if (!(precpred(_ctx, 28))) throw new FailedPredicateException(this, "precpred(_ctx, 28)");
						setState(448);
						match(MUL);
						setState(449);
						expression(29);
						}
						break;
					case 2:
						{
						_localctx = new ExprAddContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(450);
						if (!(precpred(_ctx, 27))) throw new FailedPredicateException(this, "precpred(_ctx, 27)");
						setState(451);
						match(ADD);
						setState(452);
						expression(28);
						}
						break;
					case 3:
						{
						_localctx = new ExprLessContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(453);
						if (!(precpred(_ctx, 26))) throw new FailedPredicateException(this, "precpred(_ctx, 26)");
						setState(454);
						match(LESS);
						setState(455);
						expression(27);
						}
						break;
					case 4:
						{
						_localctx = new ExprGreaterContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(456);
						if (!(precpred(_ctx, 25))) throw new FailedPredicateException(this, "precpred(_ctx, 25)");
						setState(457);
						match(GREATER);
						setState(458);
						expression(26);
						}
						break;
					case 5:
						{
						_localctx = new ExprGreatEqlContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(459);
						if (!(precpred(_ctx, 24))) throw new FailedPredicateException(this, "precpred(_ctx, 24)");
						setState(460);
						match(GREATEREQUAL);
						setState(461);
						expression(25);
						}
						break;
					case 6:
						{
						_localctx = new ExprLessEqlContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(462);
						if (!(precpred(_ctx, 23))) throw new FailedPredicateException(this, "precpred(_ctx, 23)");
						setState(463);
						match(LESSEQUAL);
						setState(464);
						expression(24);
						}
						break;
					case 7:
						{
						_localctx = new ExprEqualContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(465);
						if (!(precpred(_ctx, 22))) throw new FailedPredicateException(this, "precpred(_ctx, 22)");
						setState(466);
						match(EQUAL);
						setState(467);
						expression(23);
						}
						break;
					case 8:
						{
						_localctx = new ExprNotEqlContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(468);
						if (!(precpred(_ctx, 21))) throw new FailedPredicateException(this, "precpred(_ctx, 21)");
						setState(469);
						match(NOTEQUAL);
						setState(470);
						expression(22);
						}
						break;
					case 9:
						{
						_localctx = new ExprConcatContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(471);
						if (!(precpred(_ctx, 20))) throw new FailedPredicateException(this, "precpred(_ctx, 20)");
						setState(472);
						match(CONCAT);
						setState(473);
						expression(21);
						}
						break;
					}
					} 
				}
				setState(478);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,54,_ctx);
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
	public static class GetterMethodCallContext extends ParserRuleContext {
		public GetterSetterObjectContext getterSetterObject() {
			return getRuleContext(GetterSetterObjectContext.class,0);
		}
		public TerminalNode POSSESSIVE() { return getToken(RemixParser.POSSESSIVE, 0); }
		public TerminalNode IDENTIFIER() { return getToken(RemixParser.IDENTIFIER, 0); }
		public GetterMethodCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_getterMethodCall; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitGetterMethodCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GetterMethodCallContext getterMethodCall() throws RecognitionException {
		GetterMethodCallContext _localctx = new GetterMethodCallContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_getterMethodCall);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(479);
			getterSetterObject();
			setState(480);
			match(POSSESSIVE);
			setState(481);
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
	public static class SetterMethodCallContext extends ParserRuleContext {
		public GetterSetterObjectContext getterSetterObject() {
			return getRuleContext(GetterSetterObjectContext.class,0);
		}
		public TerminalNode POSSESSIVE() { return getToken(RemixParser.POSSESSIVE, 0); }
		public TerminalNode IDENTIFIER() { return getToken(RemixParser.IDENTIFIER, 0); }
		public TerminalNode COLON() { return getToken(RemixParser.COLON, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public SetterMethodCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_setterMethodCall; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitSetterMethodCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SetterMethodCallContext setterMethodCall() throws RecognitionException {
		SetterMethodCallContext _localctx = new SetterMethodCallContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_setterMethodCall);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(483);
			getterSetterObject();
			setState(484);
			match(POSSESSIVE);
			setState(485);
			match(IDENTIFIER);
			setState(486);
			match(COLON);
			setState(487);
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

	@SuppressWarnings("CheckReturnValue")
	public static class GetterSetterObjectContext extends ParserRuleContext {
		public GetterSetterObjectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_getterSetterObject; }
	 
		public GetterSetterObjectContext() { }
		public void copyFrom(GetterSetterObjectContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IdentifierGetterSetterContext extends GetterSetterObjectContext {
		public TerminalNode IDENTIFIER() { return getToken(RemixParser.IDENTIFIER, 0); }
		public IdentifierGetterSetterContext(GetterSetterObjectContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitIdentifierGetterSetter(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ConstantGetterSetterContext extends GetterSetterObjectContext {
		public TerminalNode CONSTANT() { return getToken(RemixParser.CONSTANT, 0); }
		public ConstantGetterSetterContext(GetterSetterObjectContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitConstantGetterSetter(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ListElementGetterSetterContext extends GetterSetterObjectContext {
		public ListElementContext listElement() {
			return getRuleContext(ListElementContext.class,0);
		}
		public ListElementGetterSetterContext(GetterSetterObjectContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitListElementGetterSetter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GetterSetterObjectContext getterSetterObject() throws RecognitionException {
		GetterSetterObjectContext _localctx = new GetterSetterObjectContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_getterSetterObject);
		try {
			setState(492);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,55,_ctx) ) {
			case 1:
				_localctx = new IdentifierGetterSetterContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(489);
				match(IDENTIFIER);
				}
				break;
			case 2:
				_localctx = new ConstantGetterSetterContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(490);
				match(CONSTANT);
				}
				break;
			case 3:
				_localctx = new ListElementGetterSetterContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(491);
				listElement();
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
		enterRule(_localctx, 62, RULE_listElement);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(494);
			match(IDENTIFIER);
			setState(496); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(495);
					listPart();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(498); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,56,_ctx);
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
		enterRule(_localctx, 64, RULE_listPart);
		try {
			_localctx = new ListPartExprContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(500);
			match(LBRACE);
			setState(501);
			expression(0);
			setState(502);
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
		enterRule(_localctx, 66, RULE_functionCall);
		try {
			int _alt;
			setState(511);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,58,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(504);
				callPart();
				setState(506); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(505);
						callPart();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(508); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,57,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(510);
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
		enterRule(_localctx, 68, RULE_callPart);
		int _la;
		try {
			setState(546);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,61,_ctx) ) {
			case 1:
				_localctx = new CallWordContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(513);
				match(WORD);
				}
				break;
			case 2:
				_localctx = new CallMinusExprContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(514);
				match(MINUS);
				setState(515);
				expression(0);
				}
				break;
			case 3:
				_localctx = new CallConstantContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(516);
				match(CONSTANT);
				}
				break;
			case 4:
				_localctx = new CallVarContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(517);
				match(IDENTIFIER);
				}
				break;
			case 5:
				_localctx = new CallParamContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(518);
				match(LPAREN);
				setState(522);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==EOL) {
					{
					{
					setState(519);
					match(EOL);
					}
					}
					setState(524);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(525);
				expression(0);
				setState(529);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==EOL) {
					{
					{
					setState(526);
					match(EOL);
					}
					}
					setState(531);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(532);
				match(RPAREN);
				}
				break;
			case 6:
				_localctx = new CallSelfContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(534);
				match(SELFREF);
				}
				break;
			case 7:
				_localctx = new CallSelfContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(535);
				match(LPAREN);
				setState(536);
				match(SELFREF);
				setState(537);
				match(RPAREN);
				}
				break;
			case 8:
				_localctx = new CallNumberContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(538);
				match(NUMBER);
				}
				break;
			case 9:
				_localctx = new CallWordProductContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(539);
				match(WORDPRODUCT);
				}
				break;
			case 10:
				_localctx = new CallNullContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(540);
				match(NULL);
				}
				break;
			case 11:
				_localctx = new CallBooleanContext(_localctx);
				enterOuterAlt(_localctx, 11);
				{
				setState(541);
				match(BOOLEAN);
				}
				break;
			case 12:
				_localctx = new CallStringContext(_localctx);
				enterOuterAlt(_localctx, 12);
				{
				setState(542);
				match(STRING);
				}
				break;
			case 13:
				_localctx = new CallBlockContext(_localctx);
				enterOuterAlt(_localctx, 13);
				{
				setState(543);
				blockOfStatements();
				}
				break;
			case 14:
				_localctx = new CallListContext(_localctx);
				enterOuterAlt(_localctx, 14);
				{
				setState(544);
				list();
				}
				break;
			case 15:
				_localctx = new CallMapContext(_localctx);
				enterOuterAlt(_localctx, 15);
				{
				setState(545);
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
		enterRule(_localctx, 70, RULE_list);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(548);
			match(LBRACE);
			setState(549);
			listContents();
			setState(550);
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
		enterRule(_localctx, 72, RULE_separator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(552);
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
		enterRule(_localctx, 74, RULE_listContents);
		int _la;
		try {
			setState(604);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,70,_ctx) ) {
			case 1:
				_localctx = new CommaListContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(562);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 68870740246612L) != 0)) {
					{
					setState(554);
					expression(0);
					setState(559);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(555);
						match(COMMA);
						setState(556);
						expression(0);
						}
						}
						setState(561);
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
				setState(564);
				match(LBLOCK);
				setState(568);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==EOL) {
					{
					{
					setState(565);
					match(EOL);
					}
					}
					setState(570);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(588);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 68870740246612L) != 0)) {
					{
					{
					setState(571);
					expression(0);
					setState(583);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA || _la==EOL) {
						{
						{
						setState(572);
						separator();
						setState(576);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==EOL) {
							{
							{
							setState(573);
							match(EOL);
							}
							}
							setState(578);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(579);
						expression(0);
						}
						}
						setState(585);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
					}
					setState(590);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(591);
				match(RBLOCK);
				setState(595);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==EOL) {
					{
					{
					setState(592);
					match(EOL);
					}
					}
					setState(597);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 3:
				_localctx = new EmptyListContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(601);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==EOL) {
					{
					{
					setState(598);
					match(EOL);
					}
					}
					setState(603);
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
		enterRule(_localctx, 76, RULE_map);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(606);
			match(LBRACE);
			setState(607);
			mapContents();
			setState(608);
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
		enterRule(_localctx, 78, RULE_mapContents);
		int _la;
		try {
			setState(644);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING:
				_localctx = new CommaMapContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(610);
				keyValue();
				setState(615);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(611);
					match(COMMA);
					setState(612);
					keyValue();
					}
					}
					setState(617);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case LBLOCK:
				_localctx = new BlockMapContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(618);
				match(LBLOCK);
				setState(622);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==EOL) {
					{
					{
					setState(619);
					match(EOL);
					}
					}
					setState(624);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(625);
				keyValue();
				setState(637);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA || _la==EOL) {
					{
					{
					setState(626);
					separator();
					setState(630);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==EOL) {
						{
						{
						setState(627);
						match(EOL);
						}
						}
						setState(632);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(633);
					keyValue();
					}
					}
					setState(639);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(640);
				match(RBLOCK);
				setState(642);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==EOL) {
					{
					setState(641);
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
		enterRule(_localctx, 80, RULE_keyValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(646);
			match(STRING);
			setState(647);
			match(COLON);
			setState(648);
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
		enterRule(_localctx, 82, RULE_value);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(650);
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
			return precpred(_ctx, 28);
		case 1:
			return precpred(_ctx, 27);
		case 2:
			return precpred(_ctx, 26);
		case 3:
			return precpred(_ctx, 25);
		case 4:
			return precpred(_ctx, 24);
		case 5:
			return precpred(_ctx, 23);
		case 6:
			return precpred(_ctx, 22);
		case 7:
			return precpred(_ctx, 21);
		case 8:
			return precpred(_ctx, 20);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001-\u028d\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
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
		"#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0002\'\u0007\'\u0002"+
		"(\u0007(\u0002)\u0007)\u0001\u0000\u0001\u0000\u0005\u0000W\b\u0000\n"+
		"\u0000\f\u0000Z\t\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001"+
		"\u0003\u0001`\b\u0001\u0001\u0001\u0001\u0001\u0005\u0001d\b\u0001\n\u0001"+
		"\f\u0001g\t\u0001\u0001\u0001\u0001\u0001\u0005\u0001k\b\u0001\n\u0001"+
		"\f\u0001n\t\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0005\u0002v\b\u0002\n\u0002\f\u0002y\t\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0005\u0003\u0080"+
		"\b\u0003\n\u0003\f\u0003\u0083\t\u0003\u0001\u0003\u0001\u0003\u0001\u0004"+
		"\u0003\u0004\u0088\b\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004"+
		"\u008d\b\u0004\u0001\u0004\u0003\u0004\u0090\b\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0004"+
		"\u0006\u0099\b\u0006\u000b\u0006\f\u0006\u009a\u0001\u0006\u0003\u0006"+
		"\u009e\b\u0006\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b"+
		"\u0001\b\u0003\b\u00a7\b\b\u0001\b\u0001\b\u0001\b\u0003\b\u00ac\b\b\u0001"+
		"\t\u0001\t\u0001\t\u0005\t\u00b1\b\t\n\t\f\t\u00b4\t\t\u0001\t\u0001\t"+
		"\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0005\n\u00bd\b\n\n\n\f\n\u00c0"+
		"\t\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0005\u000b\u00c6\b\u000b\n\u000b"+
		"\f\u000b\u00c9\t\u000b\u0001\u000b\u0003\u000b\u00cc\b\u000b\u0001\u000b"+
		"\u0003\u000b\u00cf\b\u000b\u0001\u000b\u0003\u000b\u00d2\b\u000b\u0001"+
		"\u000b\u0005\u000b\u00d5\b\u000b\n\u000b\f\u000b\u00d8\t\u000b\u0001\f"+
		"\u0001\f\u0001\f\u0001\f\u0005\f\u00de\b\f\n\f\f\f\u00e1\t\f\u0001\r\u0001"+
		"\r\u0001\r\u0005\r\u00e6\b\r\n\r\f\r\u00e9\t\r\u0001\r\u0001\r\u0005\r"+
		"\u00ed\b\r\n\r\f\r\u00f0\t\r\u0004\r\u00f2\b\r\u000b\r\f\r\u00f3\u0001"+
		"\r\u0001\r\u0005\r\u00f8\b\r\n\r\f\r\u00fb\t\r\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0005\u000e\u0100\b\u000e\n\u000e\f\u000e\u0103\t\u000e\u0001"+
		"\u000e\u0001\u000e\u0005\u000e\u0107\b\u000e\n\u000e\f\u000e\u010a\t\u000e"+
		"\u0004\u000e\u010c\b\u000e\u000b\u000e\f\u000e\u010d\u0001\u000e\u0001"+
		"\u000e\u0005\u000e\u0112\b\u000e\n\u000e\f\u000e\u0115\t\u000e\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0005\u000f\u011a\b\u000f\n\u000f\f\u000f\u011d"+
		"\t\u000f\u0001\u000f\u0001\u000f\u0005\u000f\u0121\b\u000f\n\u000f\f\u000f"+
		"\u0124\t\u000f\u0004\u000f\u0126\b\u000f\u000b\u000f\f\u000f\u0127\u0001"+
		"\u000f\u0001\u000f\u0005\u000f\u012c\b\u000f\n\u000f\f\u000f\u012f\t\u000f"+
		"\u0001\u0010\u0001\u0010\u0001\u0011\u0003\u0011\u0134\b\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0003\u0011\u0139\b\u0011\u0001\u0011\u0001\u0011"+
		"\u0003\u0011\u013d\b\u0011\u0001\u0011\u0001\u0011\u0005\u0011\u0141\b"+
		"\u0011\n\u0011\f\u0011\u0144\t\u0011\u0001\u0012\u0001\u0012\u0004\u0012"+
		"\u0148\b\u0012\u000b\u0012\f\u0012\u0149\u0001\u0013\u0001\u0013\u0001"+
		"\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0003\u0014\u0155\b\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0003\u0015\u0163\b\u0015\u0001\u0016\u0001"+
		"\u0016\u0005\u0016\u0167\b\u0016\n\u0016\f\u0016\u016a\t\u0016\u0001\u0016"+
		"\u0001\u0016\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0003\u0017\u0174\b\u0017\u0001\u0017\u0001\u0017\u0003\u0017"+
		"\u0178\b\u0017\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019\u0001\u0019"+
		"\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0004\u0019"+
		"\u0184\b\u0019\u000b\u0019\f\u0019\u0185\u0001\u0019\u0001\u0019\u0001"+
		"\u0019\u0003\u0019\u018b\b\u0019\u0001\u001a\u0001\u001a\u0001\u001a\u0005"+
		"\u001a\u0190\b\u001a\n\u001a\f\u001a\u0193\t\u001a\u0003\u001a\u0195\b"+
		"\u001a\u0001\u001a\u0001\u001a\u0001\u001b\u0001\u001b\u0001\u001b\u0001"+
		"\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001"+
		"\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001"+
		"\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001"+
		"\u001b\u0001\u001b\u0005\u001b\u01b0\b\u001b\n\u001b\f\u001b\u01b3\t\u001b"+
		"\u0001\u001b\u0001\u001b\u0005\u001b\u01b7\b\u001b\n\u001b\f\u001b\u01ba"+
		"\t\u001b\u0001\u001b\u0001\u001b\u0003\u001b\u01be\b\u001b\u0001\u001b"+
		"\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b"+
		"\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b"+
		"\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b"+
		"\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b"+
		"\u0001\u001b\u0001\u001b\u0005\u001b\u01db\b\u001b\n\u001b\f\u001b\u01de"+
		"\t\u001b\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001d\u0001"+
		"\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001e\u0001"+
		"\u001e\u0001\u001e\u0003\u001e\u01ed\b\u001e\u0001\u001f\u0001\u001f\u0004"+
		"\u001f\u01f1\b\u001f\u000b\u001f\f\u001f\u01f2\u0001 \u0001 \u0001 \u0001"+
		" \u0001!\u0001!\u0004!\u01fb\b!\u000b!\f!\u01fc\u0001!\u0003!\u0200\b"+
		"!\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0005\"\u0209"+
		"\b\"\n\"\f\"\u020c\t\"\u0001\"\u0001\"\u0005\"\u0210\b\"\n\"\f\"\u0213"+
		"\t\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001"+
		"\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0003\"\u0223\b\"\u0001#\u0001"+
		"#\u0001#\u0001#\u0001$\u0001$\u0001%\u0001%\u0001%\u0005%\u022e\b%\n%"+
		"\f%\u0231\t%\u0003%\u0233\b%\u0001%\u0001%\u0005%\u0237\b%\n%\f%\u023a"+
		"\t%\u0001%\u0001%\u0001%\u0005%\u023f\b%\n%\f%\u0242\t%\u0001%\u0001%"+
		"\u0005%\u0246\b%\n%\f%\u0249\t%\u0005%\u024b\b%\n%\f%\u024e\t%\u0001%"+
		"\u0001%\u0005%\u0252\b%\n%\f%\u0255\t%\u0001%\u0005%\u0258\b%\n%\f%\u025b"+
		"\t%\u0003%\u025d\b%\u0001&\u0001&\u0001&\u0001&\u0001\'\u0001\'\u0001"+
		"\'\u0005\'\u0266\b\'\n\'\f\'\u0269\t\'\u0001\'\u0001\'\u0005\'\u026d\b"+
		"\'\n\'\f\'\u0270\t\'\u0001\'\u0001\'\u0001\'\u0005\'\u0275\b\'\n\'\f\'"+
		"\u0278\t\'\u0001\'\u0001\'\u0005\'\u027c\b\'\n\'\f\'\u027f\t\'\u0001\'"+
		"\u0001\'\u0003\'\u0283\b\'\u0003\'\u0285\b\'\u0001(\u0001(\u0001(\u0001"+
		"(\u0001)\u0001)\u0001)\u0000\u00016*\u0000\u0002\u0004\u0006\b\n\f\u000e"+
		"\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.02468:<>@BDF"+
		"HJLNPR\u0000\u0003\u0001\u0000\r\u000e\u0001\u0000\t\n\u0002\u0000\b\b"+
		"\r\r\u02e3\u0000X\u0001\u0000\u0000\u0000\u0002]\u0001\u0000\u0000\u0000"+
		"\u0004q\u0001\u0000\u0000\u0000\u0006|\u0001\u0000\u0000\u0000\b\u0087"+
		"\u0001\u0000\u0000\u0000\n\u0093\u0001\u0000\u0000\u0000\f\u009d\u0001"+
		"\u0000\u0000\u0000\u000e\u009f\u0001\u0000\u0000\u0000\u0010\u00ab\u0001"+
		"\u0000\u0000\u0000\u0012\u00ad\u0001\u0000\u0000\u0000\u0014\u00b8\u0001"+
		"\u0000\u0000\u0000\u0016\u00c7\u0001\u0000\u0000\u0000\u0018\u00d9\u0001"+
		"\u0000\u0000\u0000\u001a\u00e2\u0001\u0000\u0000\u0000\u001c\u00fc\u0001"+
		"\u0000\u0000\u0000\u001e\u0116\u0001\u0000\u0000\u0000 \u0130\u0001\u0000"+
		"\u0000\u0000\"\u0133\u0001\u0000\u0000\u0000$\u0145\u0001\u0000\u0000"+
		"\u0000&\u014b\u0001\u0000\u0000\u0000(\u014e\u0001\u0000\u0000\u0000*"+
		"\u0162\u0001\u0000\u0000\u0000,\u0164\u0001\u0000\u0000\u0000.\u0177\u0001"+
		"\u0000\u0000\u00000\u0179\u0001\u0000\u0000\u00002\u018a\u0001\u0000\u0000"+
		"\u00004\u0194\u0001\u0000\u0000\u00006\u01bd\u0001\u0000\u0000\u00008"+
		"\u01df\u0001\u0000\u0000\u0000:\u01e3\u0001\u0000\u0000\u0000<\u01ec\u0001"+
		"\u0000\u0000\u0000>\u01ee\u0001\u0000\u0000\u0000@\u01f4\u0001\u0000\u0000"+
		"\u0000B\u01ff\u0001\u0000\u0000\u0000D\u0222\u0001\u0000\u0000\u0000F"+
		"\u0224\u0001\u0000\u0000\u0000H\u0228\u0001\u0000\u0000\u0000J\u025c\u0001"+
		"\u0000\u0000\u0000L\u025e\u0001\u0000\u0000\u0000N\u0284\u0001\u0000\u0000"+
		"\u0000P\u0286\u0001\u0000\u0000\u0000R\u028a\u0001\u0000\u0000\u0000T"+
		"W\u0003\b\u0004\u0000UW\u0003.\u0017\u0000VT\u0001\u0000\u0000\u0000V"+
		"U\u0001\u0000\u0000\u0000WZ\u0001\u0000\u0000\u0000XV\u0001\u0000\u0000"+
		"\u0000XY\u0001\u0000\u0000\u0000Y[\u0001\u0000\u0000\u0000ZX\u0001\u0000"+
		"\u0000\u0000[\\\u0005\u0000\u0000\u0001\\\u0001\u0001\u0000\u0000\u0000"+
		"]_\u0005%\u0000\u0000^`\u0005-\u0000\u0000_^\u0001\u0000\u0000\u0000_"+
		"`\u0001\u0000\u0000\u0000`a\u0001\u0000\u0000\u0000ae\u0005\u0004\u0000"+
		"\u0000bd\u0005\r\u0000\u0000cb\u0001\u0000\u0000\u0000dg\u0001\u0000\u0000"+
		"\u0000ec\u0001\u0000\u0000\u0000ef\u0001\u0000\u0000\u0000fl\u0001\u0000"+
		"\u0000\u0000ge\u0001\u0000\u0000\u0000hk\u0003\b\u0004\u0000ik\u0003."+
		"\u0017\u0000jh\u0001\u0000\u0000\u0000ji\u0001\u0000\u0000\u0000kn\u0001"+
		"\u0000\u0000\u0000lj\u0001\u0000\u0000\u0000lm\u0001\u0000\u0000\u0000"+
		"mo\u0001\u0000\u0000\u0000nl\u0001\u0000\u0000\u0000op\u0005\u0005\u0000"+
		"\u0000p\u0003\u0001\u0000\u0000\u0000qr\u0005&\u0000\u0000rw\u00036\u001b"+
		"\u0000st\u0005\b\u0000\u0000tv\u00036\u001b\u0000us\u0001\u0000\u0000"+
		"\u0000vy\u0001\u0000\u0000\u0000wu\u0001\u0000\u0000\u0000wx\u0001\u0000"+
		"\u0000\u0000xz\u0001\u0000\u0000\u0000yw\u0001\u0000\u0000\u0000z{\u0003"+
		"\u0006\u0003\u0000{\u0005\u0001\u0000\u0000\u0000|\u0081\u0005\u0004\u0000"+
		"\u0000}\u0080\u0003\b\u0004\u0000~\u0080\u0003.\u0017\u0000\u007f}\u0001"+
		"\u0000\u0000\u0000\u007f~\u0001\u0000\u0000\u0000\u0080\u0083\u0001\u0000"+
		"\u0000\u0000\u0081\u007f\u0001\u0000\u0000\u0000\u0081\u0082\u0001\u0000"+
		"\u0000\u0000\u0082\u0084\u0001\u0000\u0000\u0000\u0083\u0081\u0001\u0000"+
		"\u0000\u0000\u0084\u0085\u0005\u0005\u0000\u0000\u0085\u0007\u0001\u0000"+
		"\u0000\u0000\u0086\u0088\u0003\n\u0005\u0000\u0087\u0086\u0001\u0000\u0000"+
		"\u0000\u0087\u0088\u0001\u0000\u0000\u0000\u0088\u0089\u0001\u0000\u0000"+
		"\u0000\u0089\u008a\u0003\f\u0006\u0000\u008a\u008c\u0005\u0001\u0000\u0000"+
		"\u008b\u008d\u0005\u0001\u0000\u0000\u008c\u008b\u0001\u0000\u0000\u0000"+
		"\u008c\u008d\u0001\u0000\u0000\u0000\u008d\u008f\u0001\u0000\u0000\u0000"+
		"\u008e\u0090\u0005\r\u0000\u0000\u008f\u008e\u0001\u0000\u0000\u0000\u008f"+
		"\u0090\u0001\u0000\u0000\u0000\u0090\u0091\u0001\u0000\u0000\u0000\u0091"+
		"\u0092\u0003,\u0016\u0000\u0092\t\u0001\u0000\u0000\u0000\u0093\u0094"+
		"\u0005\u000f\u0000\u0000\u0094\u0095\u0005\r\u0000\u0000\u0095\u000b\u0001"+
		"\u0000\u0000\u0000\u0096\u0098\u0003\u0010\b\u0000\u0097\u0099\u0003\u0010"+
		"\b\u0000\u0098\u0097\u0001\u0000\u0000\u0000\u0099\u009a\u0001\u0000\u0000"+
		"\u0000\u009a\u0098\u0001\u0000\u0000\u0000\u009a\u009b\u0001\u0000\u0000"+
		"\u0000\u009b\u009e\u0001\u0000\u0000\u0000\u009c\u009e\u0003\u000e\u0007"+
		"\u0000\u009d\u0096\u0001\u0000\u0000\u0000\u009d\u009c\u0001\u0000\u0000"+
		"\u0000\u009e\r\u0001\u0000\u0000\u0000\u009f\u00a0\u0005+\u0000\u0000"+
		"\u00a0\u000f\u0001\u0000\u0000\u0000\u00a1\u00ac\u0005+\u0000\u0000\u00a2"+
		"\u00a7\u0005*\u0000\u0000\u00a3\u00a4\u0005\u0002\u0000\u0000\u00a4\u00a5"+
		"\u0005*\u0000\u0000\u00a5\u00a7\u0005\u0003\u0000\u0000\u00a6\u00a2\u0001"+
		"\u0000\u0000\u0000\u00a6\u00a3\u0001\u0000\u0000\u0000\u00a7\u00ac\u0001"+
		"\u0000\u0000\u0000\u00a8\u00a9\u0005\u0004\u0000\u0000\u00a9\u00aa\u0005"+
		"*\u0000\u0000\u00aa\u00ac\u0005\u0005\u0000\u0000\u00ab\u00a1\u0001\u0000"+
		"\u0000\u0000\u00ab\u00a6\u0001\u0000\u0000\u0000\u00ab\u00a8\u0001\u0000"+
		"\u0000\u0000\u00ac\u0011\u0001\u0000\u0000\u0000\u00ad\u00ae\u0005 \u0000"+
		"\u0000\u00ae\u00b2\u0005\u0004\u0000\u0000\u00af\u00b1\u0005\r\u0000\u0000"+
		"\u00b0\u00af\u0001\u0000\u0000\u0000\u00b1\u00b4\u0001\u0000\u0000\u0000"+
		"\u00b2\u00b0\u0001\u0000\u0000\u0000\u00b2\u00b3\u0001\u0000\u0000\u0000"+
		"\u00b3\u00b5\u0001\u0000\u0000\u0000\u00b4\u00b2\u0001\u0000\u0000\u0000"+
		"\u00b5\u00b6\u0003\u0016\u000b\u0000\u00b6\u00b7\u0005\u0005\u0000\u0000"+
		"\u00b7\u0013\u0001\u0000\u0000\u0000\u00b8\u00b9\u0005!\u0000\u0000\u00b9"+
		"\u00ba\u00036\u001b\u0000\u00ba\u00be\u0005\u0004\u0000\u0000\u00bb\u00bd"+
		"\u0005\r\u0000\u0000\u00bc\u00bb\u0001\u0000\u0000\u0000\u00bd\u00c0\u0001"+
		"\u0000\u0000\u0000\u00be\u00bc\u0001\u0000\u0000\u0000\u00be\u00bf\u0001"+
		"\u0000\u0000\u0000\u00bf\u00c1\u0001\u0000\u0000\u0000\u00c0\u00be\u0001"+
		"\u0000\u0000\u0000\u00c1\u00c2\u0003\u0016\u000b\u0000\u00c2\u00c3\u0005"+
		"\u0005\u0000\u0000\u00c3\u0015\u0001\u0000\u0000\u0000\u00c4\u00c6\u0003"+
		"\u0018\f\u0000\u00c5\u00c4\u0001\u0000\u0000\u0000\u00c6\u00c9\u0001\u0000"+
		"\u0000\u0000\u00c7\u00c5\u0001\u0000\u0000\u0000\u00c7\u00c8\u0001\u0000"+
		"\u0000\u0000\u00c8\u00cb\u0001\u0000\u0000\u0000\u00c9\u00c7\u0001\u0000"+
		"\u0000\u0000\u00ca\u00cc\u0003\u001a\r\u0000\u00cb\u00ca\u0001\u0000\u0000"+
		"\u0000\u00cb\u00cc\u0001\u0000\u0000\u0000\u00cc\u00ce\u0001\u0000\u0000"+
		"\u0000\u00cd\u00cf\u0003\u001c\u000e\u0000\u00ce\u00cd\u0001\u0000\u0000"+
		"\u0000\u00ce\u00cf\u0001\u0000\u0000\u0000\u00cf\u00d1\u0001\u0000\u0000"+
		"\u0000\u00d0\u00d2\u0003\u001e\u000f\u0000\u00d1\u00d0\u0001\u0000\u0000"+
		"\u0000\u00d1\u00d2\u0001\u0000\u0000\u0000\u00d2\u00d6\u0001\u0000\u0000"+
		"\u0000\u00d3\u00d5\u0003\"\u0011\u0000\u00d4\u00d3\u0001\u0000\u0000\u0000"+
		"\u00d5\u00d8\u0001\u0000\u0000\u0000\u00d6\u00d4\u0001\u0000\u0000\u0000"+
		"\u00d6\u00d7\u0001\u0000\u0000\u0000\u00d7\u0017\u0001\u0000\u0000\u0000"+
		"\u00d8\u00d6\u0001\u0000\u0000\u0000\u00d9\u00da\u0005*\u0000\u0000\u00da"+
		"\u00db\u0005\u0001\u0000\u0000\u00db\u00df\u00036\u001b\u0000\u00dc\u00de"+
		"\u0005\r\u0000\u0000\u00dd\u00dc\u0001\u0000\u0000\u0000\u00de\u00e1\u0001"+
		"\u0000\u0000\u0000\u00df\u00dd\u0001\u0000\u0000\u0000\u00df\u00e0\u0001"+
		"\u0000\u0000\u0000\u00e0\u0019\u0001\u0000\u0000\u0000\u00e1\u00df\u0001"+
		"\u0000\u0000\u0000\u00e2\u00e3\u0005\"\u0000\u0000\u00e3\u00f1\u0005\u0004"+
		"\u0000\u0000\u00e4\u00e6\u0005\r\u0000\u0000\u00e5\u00e4\u0001\u0000\u0000"+
		"\u0000\u00e6\u00e9\u0001\u0000\u0000\u0000\u00e7\u00e5\u0001\u0000\u0000"+
		"\u0000\u00e7\u00e8\u0001\u0000\u0000\u0000\u00e8\u00ea\u0001\u0000\u0000"+
		"\u0000\u00e9\u00e7\u0001\u0000\u0000\u0000\u00ea\u00ee\u0003 \u0010\u0000"+
		"\u00eb\u00ed\u0003H$\u0000\u00ec\u00eb\u0001\u0000\u0000\u0000\u00ed\u00f0"+
		"\u0001\u0000\u0000\u0000\u00ee\u00ec\u0001\u0000\u0000\u0000\u00ee\u00ef"+
		"\u0001\u0000\u0000\u0000\u00ef\u00f2\u0001\u0000\u0000\u0000\u00f0\u00ee"+
		"\u0001\u0000\u0000\u0000\u00f1\u00e7\u0001\u0000\u0000\u0000\u00f2\u00f3"+
		"\u0001\u0000\u0000\u0000\u00f3\u00f1\u0001\u0000\u0000\u0000\u00f3\u00f4"+
		"\u0001\u0000\u0000\u0000\u00f4\u00f5\u0001\u0000\u0000\u0000\u00f5\u00f9"+
		"\u0005\u0005\u0000\u0000\u00f6\u00f8\u0005\r\u0000\u0000\u00f7\u00f6\u0001"+
		"\u0000\u0000\u0000\u00f8\u00fb\u0001\u0000\u0000\u0000\u00f9\u00f7\u0001"+
		"\u0000\u0000\u0000\u00f9\u00fa\u0001\u0000\u0000\u0000\u00fa\u001b\u0001"+
		"\u0000\u0000\u0000\u00fb\u00f9\u0001\u0000\u0000\u0000\u00fc\u00fd\u0005"+
		"#\u0000\u0000\u00fd\u010b\u0005\u0004\u0000\u0000\u00fe\u0100\u0005\r"+
		"\u0000\u0000\u00ff\u00fe\u0001\u0000\u0000\u0000\u0100\u0103\u0001\u0000"+
		"\u0000\u0000\u0101\u00ff\u0001\u0000\u0000\u0000\u0101\u0102\u0001\u0000"+
		"\u0000\u0000\u0102\u0104\u0001\u0000\u0000\u0000\u0103\u0101\u0001\u0000"+
		"\u0000\u0000\u0104\u0108\u0003 \u0010\u0000\u0105\u0107\u0003H$\u0000"+
		"\u0106\u0105\u0001\u0000\u0000\u0000\u0107\u010a\u0001\u0000\u0000\u0000"+
		"\u0108\u0106\u0001\u0000\u0000\u0000\u0108\u0109\u0001\u0000\u0000\u0000"+
		"\u0109\u010c\u0001\u0000\u0000\u0000\u010a\u0108\u0001\u0000\u0000\u0000"+
		"\u010b\u0101\u0001\u0000\u0000\u0000\u010c\u010d\u0001\u0000\u0000\u0000"+
		"\u010d\u010b\u0001\u0000\u0000\u0000\u010d\u010e\u0001\u0000\u0000\u0000"+
		"\u010e\u010f\u0001\u0000\u0000\u0000\u010f\u0113\u0005\u0005\u0000\u0000"+
		"\u0110\u0112\u0005\r\u0000\u0000\u0111\u0110\u0001\u0000\u0000\u0000\u0112"+
		"\u0115\u0001\u0000\u0000\u0000\u0113\u0111\u0001\u0000\u0000\u0000\u0113"+
		"\u0114\u0001\u0000\u0000\u0000\u0114\u001d\u0001\u0000\u0000\u0000\u0115"+
		"\u0113\u0001\u0000\u0000\u0000\u0116\u0117\u0005$\u0000\u0000\u0117\u0125"+
		"\u0005\u0004\u0000\u0000\u0118\u011a\u0005\r\u0000\u0000\u0119\u0118\u0001"+
		"\u0000\u0000\u0000\u011a\u011d\u0001\u0000\u0000\u0000\u011b\u0119\u0001"+
		"\u0000\u0000\u0000\u011b\u011c\u0001\u0000\u0000\u0000\u011c\u011e\u0001"+
		"\u0000\u0000\u0000\u011d\u011b\u0001\u0000\u0000\u0000\u011e\u0122\u0003"+
		" \u0010\u0000\u011f\u0121\u0003H$\u0000\u0120\u011f\u0001\u0000\u0000"+
		"\u0000\u0121\u0124\u0001\u0000\u0000\u0000\u0122\u0120\u0001\u0000\u0000"+
		"\u0000\u0122\u0123\u0001\u0000\u0000\u0000\u0123\u0126\u0001\u0000\u0000"+
		"\u0000\u0124\u0122\u0001\u0000\u0000\u0000\u0125\u011b\u0001\u0000\u0000"+
		"\u0000\u0126\u0127\u0001\u0000\u0000\u0000\u0127\u0125\u0001\u0000\u0000"+
		"\u0000\u0127\u0128\u0001\u0000\u0000\u0000\u0128\u0129\u0001\u0000\u0000"+
		"\u0000\u0129\u012d\u0005\u0005\u0000\u0000\u012a\u012c\u0005\r\u0000\u0000"+
		"\u012b\u012a\u0001\u0000\u0000\u0000\u012c\u012f\u0001\u0000\u0000\u0000"+
		"\u012d\u012b\u0001\u0000\u0000\u0000\u012d\u012e\u0001\u0000\u0000\u0000"+
		"\u012e\u001f\u0001\u0000\u0000\u0000\u012f\u012d\u0001\u0000\u0000\u0000"+
		"\u0130\u0131\u0005*\u0000\u0000\u0131!\u0001\u0000\u0000\u0000\u0132\u0134"+
		"\u0003\n\u0005\u0000\u0133\u0132\u0001\u0000\u0000\u0000\u0133\u0134\u0001"+
		"\u0000\u0000\u0000\u0134\u0138\u0001\u0000\u0000\u0000\u0135\u0139\u0003"+
		"(\u0014\u0000\u0136\u0139\u0003&\u0013\u0000\u0137\u0139\u0003$\u0012"+
		"\u0000\u0138\u0135\u0001\u0000\u0000\u0000\u0138\u0136\u0001\u0000\u0000"+
		"\u0000\u0138\u0137\u0001\u0000\u0000\u0000\u0139\u013a\u0001\u0000\u0000"+
		"\u0000\u013a\u013c\u0005\u0001\u0000\u0000\u013b\u013d\u0005\r\u0000\u0000"+
		"\u013c\u013b\u0001\u0000\u0000\u0000\u013c\u013d\u0001\u0000\u0000\u0000"+
		"\u013d\u013e\u0001\u0000\u0000\u0000\u013e\u0142\u0003,\u0016\u0000\u013f"+
		"\u0141\u0005\r\u0000\u0000\u0140\u013f\u0001\u0000\u0000\u0000\u0141\u0144"+
		"\u0001\u0000\u0000\u0000\u0142\u0140\u0001\u0000\u0000\u0000\u0142\u0143"+
		"\u0001\u0000\u0000\u0000\u0143#\u0001\u0000\u0000\u0000\u0144\u0142\u0001"+
		"\u0000\u0000\u0000\u0145\u0147\u0003*\u0015\u0000\u0146\u0148\u0003*\u0015"+
		"\u0000\u0147\u0146\u0001\u0000\u0000\u0000\u0148\u0149\u0001\u0000\u0000"+
		"\u0000\u0149\u0147\u0001\u0000\u0000\u0000\u0149\u014a\u0001\u0000\u0000"+
		"\u0000\u014a%\u0001\u0000\u0000\u0000\u014b\u014c\u0005\'\u0000\u0000"+
		"\u014c\u014d\u0005*\u0000\u0000\u014d\'\u0001\u0000\u0000\u0000\u014e"+
		"\u014f\u0005\'\u0000\u0000\u014f\u0154\u0005*\u0000\u0000\u0150\u0155"+
		"\u0005*\u0000\u0000\u0151\u0152\u0005\u0002\u0000\u0000\u0152\u0153\u0005"+
		"*\u0000\u0000\u0153\u0155\u0005\u0003\u0000\u0000\u0154\u0150\u0001\u0000"+
		"\u0000\u0000\u0154\u0151\u0001\u0000\u0000\u0000\u0155)\u0001\u0000\u0000"+
		"\u0000\u0156\u0163\u0005+\u0000\u0000\u0157\u0163\u0005*\u0000\u0000\u0158"+
		"\u0159\u0005\u0002\u0000\u0000\u0159\u015a\u0005*\u0000\u0000\u015a\u0163"+
		"\u0005\u0003\u0000\u0000\u015b\u0163\u0005\'\u0000\u0000\u015c\u015d\u0005"+
		"\u0002\u0000\u0000\u015d\u015e\u0005\'\u0000\u0000\u015e\u0163\u0005\u0003"+
		"\u0000\u0000\u015f\u0160\u0005\u0004\u0000\u0000\u0160\u0161\u0005*\u0000"+
		"\u0000\u0161\u0163\u0005\u0005\u0000\u0000\u0162\u0156\u0001\u0000\u0000"+
		"\u0000\u0162\u0157\u0001\u0000\u0000\u0000\u0162\u0158\u0001\u0000\u0000"+
		"\u0000\u0162\u015b\u0001\u0000\u0000\u0000\u0162\u015c\u0001\u0000\u0000"+
		"\u0000\u0162\u015f\u0001\u0000\u0000\u0000\u0163+\u0001\u0000\u0000\u0000"+
		"\u0164\u0168\u0005\u0004\u0000\u0000\u0165\u0167\u0003.\u0017\u0000\u0166"+
		"\u0165\u0001\u0000\u0000\u0000\u0167\u016a\u0001\u0000\u0000\u0000\u0168"+
		"\u0166\u0001\u0000\u0000\u0000\u0168\u0169\u0001\u0000\u0000\u0000\u0169"+
		"\u016b\u0001\u0000\u0000\u0000\u016a\u0168\u0001\u0000\u0000\u0000\u016b"+
		"\u016c\u0005\u0005\u0000\u0000\u016c-\u0001\u0000\u0000\u0000\u016d\u0178"+
		"\u00032\u0019\u0000\u016e\u0178\u00034\u001a\u0000\u016f\u0178\u00036"+
		"\u001b\u0000\u0170\u0178\u0005\u001f\u0000\u0000\u0171\u0173\u0005\u001e"+
		"\u0000\u0000\u0172\u0174\u00036\u001b\u0000\u0173\u0172\u0001\u0000\u0000"+
		"\u0000\u0173\u0174\u0001\u0000\u0000\u0000\u0174\u0178\u0001\u0000\u0000"+
		"\u0000\u0175\u0178\u0003\u0004\u0002\u0000\u0176\u0178\u00030\u0018\u0000"+
		"\u0177\u016d\u0001\u0000\u0000\u0000\u0177\u016e\u0001\u0000\u0000\u0000"+
		"\u0177\u016f\u0001\u0000\u0000\u0000\u0177\u0170\u0001\u0000\u0000\u0000"+
		"\u0177\u0171\u0001\u0000\u0000\u0000\u0177\u0175\u0001\u0000\u0000\u0000"+
		"\u0177\u0176\u0001\u0000\u0000\u0000\u0178/\u0001\u0000\u0000\u0000\u0179"+
		"\u017a\u0007\u0000\u0000\u0000\u017a1\u0001\u0000\u0000\u0000\u017b\u017c"+
		"\u0005*\u0000\u0000\u017c\u017d\u0005\u0001\u0000\u0000\u017d\u018b\u0003"+
		"6\u001b\u0000\u017e\u017f\u0005)\u0000\u0000\u017f\u0180\u0005\u0001\u0000"+
		"\u0000\u0180\u018b\u00036\u001b\u0000\u0181\u0183\u0005*\u0000\u0000\u0182"+
		"\u0184\u0003@ \u0000\u0183\u0182\u0001\u0000\u0000\u0000\u0184\u0185\u0001"+
		"\u0000\u0000\u0000\u0185\u0183\u0001\u0000\u0000\u0000\u0185\u0186\u0001"+
		"\u0000\u0000\u0000\u0186\u0187\u0001\u0000\u0000\u0000\u0187\u0188\u0005"+
		"\u0001\u0000\u0000\u0188\u0189\u00036\u001b\u0000\u0189\u018b\u0001\u0000"+
		"\u0000\u0000\u018a\u017b\u0001\u0000\u0000\u0000\u018a\u017e\u0001\u0000"+
		"\u0000\u0000\u018a\u0181\u0001\u0000\u0000\u0000\u018b3\u0001\u0000\u0000"+
		"\u0000\u018c\u0191\u00036\u001b\u0000\u018d\u018e\u0005\b\u0000\u0000"+
		"\u018e\u0190\u00036\u001b\u0000\u018f\u018d\u0001\u0000\u0000\u0000\u0190"+
		"\u0193\u0001\u0000\u0000\u0000\u0191\u018f\u0001\u0000\u0000\u0000\u0191"+
		"\u0192\u0001\u0000\u0000\u0000\u0192\u0195\u0001\u0000\u0000\u0000\u0193"+
		"\u0191\u0001\u0000\u0000\u0000\u0194\u018c\u0001\u0000\u0000\u0000\u0194"+
		"\u0195\u0001\u0000\u0000\u0000\u0195\u0196\u0001\u0000\u0000\u0000\u0196"+
		"\u0197\u0007\u0001\u0000\u0000\u01975\u0001\u0000\u0000\u0000\u0198\u0199"+
		"\u0006\u001b\uffff\uffff\u0000\u0199\u019a\u0005\u001b\u0000\u0000\u019a"+
		"\u01be\u00036\u001b\u001d\u019b\u01be\u0003>\u001f\u0000\u019c\u01be\u0003"+
		"B!\u0000\u019d\u01be\u0005)\u0000\u0000\u019e\u01be\u0005*\u0000\u0000"+
		"\u019f\u01be\u0005\'\u0000\u0000\u01a0\u01be\u0005\u0011\u0000\u0000\u01a1"+
		"\u01be\u0005,\u0000\u0000\u01a2\u01be\u0005\u001c\u0000\u0000\u01a3\u01be"+
		"\u0005\u001d\u0000\u0000\u01a4\u01be\u0005-\u0000\u0000\u01a5\u01be\u0003"+
		",\u0016\u0000\u01a6\u01be\u0003F#\u0000\u01a7\u01be\u0003L&\u0000\u01a8"+
		"\u01be\u0003\u0002\u0001\u0000\u01a9\u01be\u0003\u0012\t\u0000\u01aa\u01be"+
		"\u0003\u0014\n\u0000\u01ab\u01be\u00038\u001c\u0000\u01ac\u01be\u0003"+
		":\u001d\u0000\u01ad\u01b1\u0005\u0002\u0000\u0000\u01ae\u01b0\u0005\r"+
		"\u0000\u0000\u01af\u01ae\u0001\u0000\u0000\u0000\u01b0\u01b3\u0001\u0000"+
		"\u0000\u0000\u01b1\u01af\u0001\u0000\u0000\u0000\u01b1\u01b2\u0001\u0000"+
		"\u0000\u0000\u01b2\u01b4\u0001\u0000\u0000\u0000\u01b3\u01b1\u0001\u0000"+
		"\u0000\u0000\u01b4\u01b8\u00036\u001b\u0000\u01b5\u01b7\u0005\r\u0000"+
		"\u0000\u01b6\u01b5\u0001\u0000\u0000\u0000\u01b7\u01ba\u0001\u0000\u0000"+
		"\u0000\u01b8\u01b6\u0001\u0000\u0000\u0000\u01b8\u01b9\u0001\u0000\u0000"+
		"\u0000\u01b9\u01bb\u0001\u0000\u0000\u0000\u01ba\u01b8\u0001\u0000\u0000"+
		"\u0000\u01bb\u01bc\u0005\u0003\u0000\u0000\u01bc\u01be\u0001\u0000\u0000"+
		"\u0000\u01bd\u0198\u0001\u0000\u0000\u0000\u01bd\u019b\u0001\u0000\u0000"+
		"\u0000\u01bd\u019c\u0001\u0000\u0000\u0000\u01bd\u019d\u0001\u0000\u0000"+
		"\u0000\u01bd\u019e\u0001\u0000\u0000\u0000\u01bd\u019f\u0001\u0000\u0000"+
		"\u0000\u01bd\u01a0\u0001\u0000\u0000\u0000\u01bd\u01a1\u0001\u0000\u0000"+
		"\u0000\u01bd\u01a2\u0001\u0000\u0000\u0000\u01bd\u01a3\u0001\u0000\u0000"+
		"\u0000\u01bd\u01a4\u0001\u0000\u0000\u0000\u01bd\u01a5\u0001\u0000\u0000"+
		"\u0000\u01bd\u01a6\u0001\u0000\u0000\u0000\u01bd\u01a7\u0001\u0000\u0000"+
		"\u0000\u01bd\u01a8\u0001\u0000\u0000\u0000\u01bd\u01a9\u0001\u0000\u0000"+
		"\u0000\u01bd\u01aa\u0001\u0000\u0000\u0000\u01bd\u01ab\u0001\u0000\u0000"+
		"\u0000\u01bd\u01ac\u0001\u0000\u0000\u0000\u01bd\u01ad\u0001\u0000\u0000"+
		"\u0000\u01be\u01dc\u0001\u0000\u0000\u0000\u01bf\u01c0\n\u001c\u0000\u0000"+
		"\u01c0\u01c1\u0005\u0013\u0000\u0000\u01c1\u01db\u00036\u001b\u001d\u01c2"+
		"\u01c3\n\u001b\u0000\u0000\u01c3\u01c4\u0005\u0012\u0000\u0000\u01c4\u01db"+
		"\u00036\u001b\u001c\u01c5\u01c6\n\u001a\u0000\u0000\u01c6\u01c7\u0005"+
		"\u0014\u0000\u0000\u01c7\u01db\u00036\u001b\u001b\u01c8\u01c9\n\u0019"+
		"\u0000\u0000\u01c9\u01ca\u0005\u0015\u0000\u0000\u01ca\u01db\u00036\u001b"+
		"\u001a\u01cb\u01cc\n\u0018\u0000\u0000\u01cc\u01cd\u0005\u0017\u0000\u0000"+
		"\u01cd\u01db\u00036\u001b\u0019\u01ce\u01cf\n\u0017\u0000\u0000\u01cf"+
		"\u01d0\u0005\u0016\u0000\u0000\u01d0\u01db\u00036\u001b\u0018\u01d1\u01d2"+
		"\n\u0016\u0000\u0000\u01d2\u01d3\u0005\u0018\u0000\u0000\u01d3\u01db\u0003"+
		"6\u001b\u0017\u01d4\u01d5\n\u0015\u0000\u0000\u01d5\u01d6\u0005\u0019"+
		"\u0000\u0000\u01d6\u01db\u00036\u001b\u0016\u01d7\u01d8\n\u0014\u0000"+
		"\u0000\u01d8\u01d9\u0005\u001a\u0000\u0000\u01d9\u01db\u00036\u001b\u0015"+
		"\u01da\u01bf\u0001\u0000\u0000\u0000\u01da\u01c2\u0001\u0000\u0000\u0000"+
		"\u01da\u01c5\u0001\u0000\u0000\u0000\u01da\u01c8\u0001\u0000\u0000\u0000"+
		"\u01da\u01cb\u0001\u0000\u0000\u0000\u01da\u01ce\u0001\u0000\u0000\u0000"+
		"\u01da\u01d1\u0001\u0000\u0000\u0000\u01da\u01d4\u0001\u0000\u0000\u0000"+
		"\u01da\u01d7\u0001\u0000\u0000\u0000\u01db\u01de\u0001\u0000\u0000\u0000"+
		"\u01dc\u01da\u0001\u0000\u0000\u0000\u01dc\u01dd\u0001\u0000\u0000\u0000"+
		"\u01dd7\u0001\u0000\u0000\u0000\u01de\u01dc\u0001\u0000\u0000\u0000\u01df"+
		"\u01e0\u0003<\u001e\u0000\u01e0\u01e1\u0005(\u0000\u0000\u01e1\u01e2\u0005"+
		"*\u0000\u0000\u01e29\u0001\u0000\u0000\u0000\u01e3\u01e4\u0003<\u001e"+
		"\u0000\u01e4\u01e5\u0005(\u0000\u0000\u01e5\u01e6\u0005*\u0000\u0000\u01e6"+
		"\u01e7\u0005\u0001\u0000\u0000\u01e7\u01e8\u00036\u001b\u0000\u01e8;\u0001"+
		"\u0000\u0000\u0000\u01e9\u01ed\u0005*\u0000\u0000\u01ea\u01ed\u0005)\u0000"+
		"\u0000\u01eb\u01ed\u0003>\u001f\u0000\u01ec\u01e9\u0001\u0000\u0000\u0000"+
		"\u01ec\u01ea\u0001\u0000\u0000\u0000\u01ec\u01eb\u0001\u0000\u0000\u0000"+
		"\u01ed=\u0001\u0000\u0000\u0000\u01ee\u01f0\u0005*\u0000\u0000\u01ef\u01f1"+
		"\u0003@ \u0000\u01f0\u01ef\u0001\u0000\u0000\u0000\u01f1\u01f2\u0001\u0000"+
		"\u0000\u0000\u01f2\u01f0\u0001\u0000\u0000\u0000\u01f2\u01f3\u0001\u0000"+
		"\u0000\u0000\u01f3?\u0001\u0000\u0000\u0000\u01f4\u01f5\u0005\u0006\u0000"+
		"\u0000\u01f5\u01f6\u00036\u001b\u0000\u01f6\u01f7\u0005\u0007\u0000\u0000"+
		"\u01f7A\u0001\u0000\u0000\u0000\u01f8\u01fa\u0003D\"\u0000\u01f9\u01fb"+
		"\u0003D\"\u0000\u01fa\u01f9\u0001\u0000\u0000\u0000\u01fb\u01fc\u0001"+
		"\u0000\u0000\u0000\u01fc\u01fa\u0001\u0000\u0000\u0000\u01fc\u01fd\u0001"+
		"\u0000\u0000\u0000\u01fd\u0200\u0001\u0000\u0000\u0000\u01fe\u0200\u0003"+
		"\u000e\u0007\u0000\u01ff\u01f8\u0001\u0000\u0000\u0000\u01ff\u01fe\u0001"+
		"\u0000\u0000\u0000\u0200C\u0001\u0000\u0000\u0000\u0201\u0223\u0005+\u0000"+
		"\u0000\u0202\u0203\u0005\u001b\u0000\u0000\u0203\u0223\u00036\u001b\u0000"+
		"\u0204\u0223\u0005)\u0000\u0000\u0205\u0223\u0005*\u0000\u0000\u0206\u020a"+
		"\u0005\u0002\u0000\u0000\u0207\u0209\u0005\r\u0000\u0000\u0208\u0207\u0001"+
		"\u0000\u0000\u0000\u0209\u020c\u0001\u0000\u0000\u0000\u020a\u0208\u0001"+
		"\u0000\u0000\u0000\u020a\u020b\u0001\u0000\u0000\u0000\u020b\u020d\u0001"+
		"\u0000\u0000\u0000\u020c\u020a\u0001\u0000\u0000\u0000\u020d\u0211\u0003"+
		"6\u001b\u0000\u020e\u0210\u0005\r\u0000\u0000\u020f\u020e\u0001\u0000"+
		"\u0000\u0000\u0210\u0213\u0001\u0000\u0000\u0000\u0211\u020f\u0001\u0000"+
		"\u0000\u0000\u0211\u0212\u0001\u0000\u0000\u0000\u0212\u0214\u0001\u0000"+
		"\u0000\u0000\u0213\u0211\u0001\u0000\u0000\u0000\u0214\u0215\u0005\u0003"+
		"\u0000\u0000\u0215\u0223\u0001\u0000\u0000\u0000\u0216\u0223\u0005\'\u0000"+
		"\u0000\u0217\u0218\u0005\u0002\u0000\u0000\u0218\u0219\u0005\'\u0000\u0000"+
		"\u0219\u0223\u0005\u0003\u0000\u0000\u021a\u0223\u0005\u0011\u0000\u0000"+
		"\u021b\u0223\u0005,\u0000\u0000\u021c\u0223\u0005\u001c\u0000\u0000\u021d"+
		"\u0223\u0005\u001d\u0000\u0000\u021e\u0223\u0005-\u0000\u0000\u021f\u0223"+
		"\u0003,\u0016\u0000\u0220\u0223\u0003F#\u0000\u0221\u0223\u0003L&\u0000"+
		"\u0222\u0201\u0001\u0000\u0000\u0000\u0222\u0202\u0001\u0000\u0000\u0000"+
		"\u0222\u0204\u0001\u0000\u0000\u0000\u0222\u0205\u0001\u0000\u0000\u0000"+
		"\u0222\u0206\u0001\u0000\u0000\u0000\u0222\u0216\u0001\u0000\u0000\u0000"+
		"\u0222\u0217\u0001\u0000\u0000\u0000\u0222\u021a\u0001\u0000\u0000\u0000"+
		"\u0222\u021b\u0001\u0000\u0000\u0000\u0222\u021c\u0001\u0000\u0000\u0000"+
		"\u0222\u021d\u0001\u0000\u0000\u0000\u0222\u021e\u0001\u0000\u0000\u0000"+
		"\u0222\u021f\u0001\u0000\u0000\u0000\u0222\u0220\u0001\u0000\u0000\u0000"+
		"\u0222\u0221\u0001\u0000\u0000\u0000\u0223E\u0001\u0000\u0000\u0000\u0224"+
		"\u0225\u0005\u0006\u0000\u0000\u0225\u0226\u0003J%\u0000\u0226\u0227\u0005"+
		"\u0007\u0000\u0000\u0227G\u0001\u0000\u0000\u0000\u0228\u0229\u0007\u0002"+
		"\u0000\u0000\u0229I\u0001\u0000\u0000\u0000\u022a\u022f\u00036\u001b\u0000"+
		"\u022b\u022c\u0005\b\u0000\u0000\u022c\u022e\u00036\u001b\u0000\u022d"+
		"\u022b\u0001\u0000\u0000\u0000\u022e\u0231\u0001\u0000\u0000\u0000\u022f"+
		"\u022d\u0001\u0000\u0000\u0000\u022f\u0230\u0001\u0000\u0000\u0000\u0230"+
		"\u0233\u0001\u0000\u0000\u0000\u0231\u022f\u0001\u0000\u0000\u0000\u0232"+
		"\u022a\u0001\u0000\u0000\u0000\u0232\u0233\u0001\u0000\u0000\u0000\u0233"+
		"\u025d\u0001\u0000\u0000\u0000\u0234\u0238\u0005\u0004\u0000\u0000\u0235"+
		"\u0237\u0005\r\u0000\u0000\u0236\u0235\u0001\u0000\u0000\u0000\u0237\u023a"+
		"\u0001\u0000\u0000\u0000\u0238\u0236\u0001\u0000\u0000\u0000\u0238\u0239"+
		"\u0001\u0000\u0000\u0000\u0239\u024c\u0001\u0000\u0000\u0000\u023a\u0238"+
		"\u0001\u0000\u0000\u0000\u023b\u0247\u00036\u001b\u0000\u023c\u0240\u0003"+
		"H$\u0000\u023d\u023f\u0005\r\u0000\u0000\u023e\u023d\u0001\u0000\u0000"+
		"\u0000\u023f\u0242\u0001\u0000\u0000\u0000\u0240\u023e\u0001\u0000\u0000"+
		"\u0000\u0240\u0241\u0001\u0000\u0000\u0000\u0241\u0243\u0001\u0000\u0000"+
		"\u0000\u0242\u0240\u0001\u0000\u0000\u0000\u0243\u0244\u00036\u001b\u0000"+
		"\u0244\u0246\u0001\u0000\u0000\u0000\u0245\u023c\u0001\u0000\u0000\u0000"+
		"\u0246\u0249\u0001\u0000\u0000\u0000\u0247\u0245\u0001\u0000\u0000\u0000"+
		"\u0247\u0248\u0001\u0000\u0000\u0000\u0248\u024b\u0001\u0000\u0000\u0000"+
		"\u0249\u0247\u0001\u0000\u0000\u0000\u024a\u023b\u0001\u0000\u0000\u0000"+
		"\u024b\u024e\u0001\u0000\u0000\u0000\u024c\u024a\u0001\u0000\u0000\u0000"+
		"\u024c\u024d\u0001\u0000\u0000\u0000\u024d\u024f\u0001\u0000\u0000\u0000"+
		"\u024e\u024c\u0001\u0000\u0000\u0000\u024f\u0253\u0005\u0005\u0000\u0000"+
		"\u0250\u0252\u0005\r\u0000\u0000\u0251\u0250\u0001\u0000\u0000\u0000\u0252"+
		"\u0255\u0001\u0000\u0000\u0000\u0253\u0251\u0001\u0000\u0000\u0000\u0253"+
		"\u0254\u0001\u0000\u0000\u0000\u0254\u025d\u0001\u0000\u0000\u0000\u0255"+
		"\u0253\u0001\u0000\u0000\u0000\u0256\u0258\u0005\r\u0000\u0000\u0257\u0256"+
		"\u0001\u0000\u0000\u0000\u0258\u025b\u0001\u0000\u0000\u0000\u0259\u0257"+
		"\u0001\u0000\u0000\u0000\u0259\u025a\u0001\u0000\u0000\u0000\u025a\u025d"+
		"\u0001\u0000\u0000\u0000\u025b\u0259\u0001\u0000\u0000\u0000\u025c\u0232"+
		"\u0001\u0000\u0000\u0000\u025c\u0234\u0001\u0000\u0000\u0000\u025c\u0259"+
		"\u0001\u0000\u0000\u0000\u025dK\u0001\u0000\u0000\u0000\u025e\u025f\u0005"+
		"\u0006\u0000\u0000\u025f\u0260\u0003N\'\u0000\u0260\u0261\u0005\u0007"+
		"\u0000\u0000\u0261M\u0001\u0000\u0000\u0000\u0262\u0267\u0003P(\u0000"+
		"\u0263\u0264\u0005\b\u0000\u0000\u0264\u0266\u0003P(\u0000\u0265\u0263"+
		"\u0001\u0000\u0000\u0000\u0266\u0269\u0001\u0000\u0000\u0000\u0267\u0265"+
		"\u0001\u0000\u0000\u0000\u0267\u0268\u0001\u0000\u0000\u0000\u0268\u0285"+
		"\u0001\u0000\u0000\u0000\u0269\u0267\u0001\u0000\u0000\u0000\u026a\u026e"+
		"\u0005\u0004\u0000\u0000\u026b\u026d\u0005\r\u0000\u0000\u026c\u026b\u0001"+
		"\u0000\u0000\u0000\u026d\u0270\u0001\u0000\u0000\u0000\u026e\u026c\u0001"+
		"\u0000\u0000\u0000\u026e\u026f\u0001\u0000\u0000\u0000\u026f\u0271\u0001"+
		"\u0000\u0000\u0000\u0270\u026e\u0001\u0000\u0000\u0000\u0271\u027d\u0003"+
		"P(\u0000\u0272\u0276\u0003H$\u0000\u0273\u0275\u0005\r\u0000\u0000\u0274"+
		"\u0273\u0001\u0000\u0000\u0000\u0275\u0278\u0001\u0000\u0000\u0000\u0276"+
		"\u0274\u0001\u0000\u0000\u0000\u0276\u0277\u0001\u0000\u0000\u0000\u0277"+
		"\u0279\u0001\u0000\u0000\u0000\u0278\u0276\u0001\u0000\u0000\u0000\u0279"+
		"\u027a\u0003P(\u0000\u027a\u027c\u0001\u0000\u0000\u0000\u027b\u0272\u0001"+
		"\u0000\u0000\u0000\u027c\u027f\u0001\u0000\u0000\u0000\u027d\u027b\u0001"+
		"\u0000\u0000\u0000\u027d\u027e\u0001\u0000\u0000\u0000\u027e\u0280\u0001"+
		"\u0000\u0000\u0000\u027f\u027d\u0001\u0000\u0000\u0000\u0280\u0282\u0005"+
		"\u0005\u0000\u0000\u0281\u0283\u0005\r\u0000\u0000\u0282\u0281\u0001\u0000"+
		"\u0000\u0000\u0282\u0283\u0001\u0000\u0000\u0000\u0283\u0285\u0001\u0000"+
		"\u0000\u0000\u0284\u0262\u0001\u0000\u0000\u0000\u0284\u026a\u0001\u0000"+
		"\u0000\u0000\u0285O\u0001\u0000\u0000\u0000\u0286\u0287\u0005-\u0000\u0000"+
		"\u0287\u0288\u0005\u0001\u0000\u0000\u0288\u0289\u0003R)\u0000\u0289Q"+
		"\u0001\u0000\u0000\u0000\u028a\u028b\u00036\u001b\u0000\u028bS\u0001\u0000"+
		"\u0000\u0000MVX_ejlw\u007f\u0081\u0087\u008c\u008f\u009a\u009d\u00a6\u00ab"+
		"\u00b2\u00be\u00c7\u00cb\u00ce\u00d1\u00d6\u00df\u00e7\u00ee\u00f3\u00f9"+
		"\u0101\u0108\u010d\u0113\u011b\u0122\u0127\u012d\u0133\u0138\u013c\u0142"+
		"\u0149\u0154\u0162\u0168\u0173\u0177\u0185\u018a\u0191\u0194\u01b1\u01b8"+
		"\u01bd\u01da\u01dc\u01ec\u01f2\u01fc\u01ff\u020a\u0211\u0222\u022f\u0232"+
		"\u0238\u0240\u0247\u024c\u0253\u0259\u025c\u0267\u026e\u0276\u027d\u0282"+
		"\u0284";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}