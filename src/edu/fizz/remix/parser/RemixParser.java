// Generated from /Users/robert/IdeaProjects/remix/src/RemixParser.g4 by ANTLR 4.13.1
package edu.fizz.remix.parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class RemixParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		COLON=1, LPAREN=2, RPAREN=3, LBLOCK=4, RBLOCK=5, LBRACE=6, RBRACE=7, COMMA=8, 
		ENDPRINT=9, PRINTLN=10, SPACE=11, CONT=12, EOL=13, EOS=14, DOC_COMMENT=15, 
		COMMENT=16, NUMBER=17, ADD=18, MUL=19, LESS=20, GREATER=21, LESSEQUAL=22, 
		GREATEREQUAL=23, EQUAL=24, NOTEQUAL=25, CONCAT=26, MINUS=27, BOOLEAN=28, 
		RETURN=29, REDO=30, CREATE=31, EXTEND=32, GETTERSETTER=33, GETTER=34, 
		SETTER=35, LIBRARY=36, USING=37, SELFREF=38, POSSESSIVE=39, IDENTIFIER=40, 
		WORD=41, WORDPRODUCT=42, STRING=43;
	public static final int
		RULE_program = 0, RULE_library = 1, RULE_usingLibrary = 2, RULE_functionDefinition = 3, 
		RULE_functionComment = 4, RULE_functionSignature = 5, RULE_sigPart = 6, 
		RULE_createObject = 7, RULE_extendObject = 8, RULE_object = 9, RULE_field = 10, 
		RULE_getterSetter = 11, RULE_getter = 12, RULE_setter = 13, RULE_fieldId = 14, 
		RULE_methodDefinition = 15, RULE_methodSignature = 16, RULE_methodSigPart = 17, 
		RULE_blockOfStatements = 18, RULE_statement = 19, RULE_endOfStatement = 20, 
		RULE_assignmentStatement = 21, RULE_printStatement = 22, RULE_expression = 23, 
		RULE_getterMethodCall = 24, RULE_setterMethodCall = 25, RULE_listElement = 26, 
		RULE_listPart = 27, RULE_functionCall = 28, RULE_callPart = 29, RULE_list = 30, 
		RULE_separator = 31, RULE_listContents = 32, RULE_map = 33, RULE_mapContents = 34, 
		RULE_keyValue = 35, RULE_key = 36, RULE_value = 37;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "library", "usingLibrary", "functionDefinition", "functionComment", 
			"functionSignature", "sigPart", "createObject", "extendObject", "object", 
			"field", "getterSetter", "getter", "setter", "fieldId", "methodDefinition", 
			"methodSignature", "methodSigPart", "blockOfStatements", "statement", 
			"endOfStatement", "assignmentStatement", "printStatement", "expression", 
			"getterMethodCall", "setterMethodCall", "listElement", "listPart", "functionCall", 
			"callPart", "list", "separator", "listContents", "map", "mapContents", 
			"keyValue", "key", "value"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "':'", "'('", "')'", "'['", "']'", "'{'", "'}'", "','", "'~'", 
			null, null, null, "'\\n'", "'.'", null, null, null, null, null, "' < '", 
			"' > '", null, null, "' = '", null, null, "'-'", null, "'return'", "'redo'", 
			"'create'", "'extend'", null, null, null, "'library'", "'using'", null, 
			"''s'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "COLON", "LPAREN", "RPAREN", "LBLOCK", "RBLOCK", "LBRACE", "RBRACE", 
			"COMMA", "ENDPRINT", "PRINTLN", "SPACE", "CONT", "EOL", "EOS", "DOC_COMMENT", 
			"COMMENT", "NUMBER", "ADD", "MUL", "LESS", "GREATER", "LESSEQUAL", "GREATEREQUAL", 
			"EQUAL", "NOTEQUAL", "CONCAT", "MINUS", "BOOLEAN", "RETURN", "REDO", 
			"CREATE", "EXTEND", "GETTERSETTER", "GETTER", "SETTER", "LIBRARY", "USING", 
			"SELFREF", "POSSESSIVE", "IDENTIFIER", "WORD", "WORDPRODUCT", "STRING"
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
			setState(80);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 16982166660692L) != 0)) {
				{
				setState(78);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(76);
					functionDefinition();
					}
					break;
				case 2:
					{
					setState(77);
					statement();
					}
					break;
				}
				}
				setState(82);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(83);
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
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			match(LIBRARY);
			setState(87);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==STRING) {
				{
				setState(86);
				match(STRING);
				}
			}

			setState(89);
			match(LBLOCK);
			setState(93);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==EOL) {
				{
				{
				setState(90);
				match(EOL);
				}
				}
				setState(95);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(105);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 3298534916116L) != 0)) {
				{
				{
				setState(96);
				functionDefinition();
				setState(100);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==EOL) {
					{
					{
					setState(97);
					match(EOL);
					}
					}
					setState(102);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				setState(107);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(108);
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
		public BlockOfStatementsContext blockOfStatements() {
			return getRuleContext(BlockOfStatementsContext.class,0);
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
			setState(110);
			match(USING);
			setState(111);
			expression(0);
			setState(116);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(112);
				match(COMMA);
				setState(113);
				expression(0);
				}
				}
				setState(118);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(119);
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
		enterRule(_localctx, 6, RULE_functionDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DOC_COMMENT) {
				{
				setState(121);
				functionComment();
				}
			}

			setState(124);
			functionSignature();
			setState(125);
			match(COLON);
			setState(127);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(126);
				match(COLON);
				}
			}

			setState(130);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EOL) {
				{
				setState(129);
				match(EOL);
				}
			}

			setState(132);
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
		enterRule(_localctx, 8, RULE_functionComment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(134);
			match(DOC_COMMENT);
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
		enterRule(_localctx, 10, RULE_functionSignature);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(136);
			sigPart();
			setState(138); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(137);
				sigPart();
				}
				}
				setState(140); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 3298534883348L) != 0) );
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
		enterRule(_localctx, 12, RULE_sigPart);
		try {
			setState(152);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case WORD:
				_localctx = new SigWordContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(142);
				match(WORD);
				}
				break;
			case LPAREN:
			case IDENTIFIER:
				_localctx = new SigParamContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(147);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case IDENTIFIER:
					{
					setState(143);
					match(IDENTIFIER);
					}
					break;
				case LPAREN:
					{
					setState(144);
					match(LPAREN);
					setState(145);
					match(IDENTIFIER);
					setState(146);
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
				setState(149);
				match(LBLOCK);
				setState(150);
				match(IDENTIFIER);
				setState(151);
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
		enterRule(_localctx, 14, RULE_createObject);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(154);
			match(CREATE);
			setState(155);
			match(LBLOCK);
			setState(159);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==EOL) {
				{
				{
				setState(156);
				match(EOL);
				}
				}
				setState(161);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(162);
			object();
			setState(163);
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
		enterRule(_localctx, 16, RULE_extendObject);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(165);
			match(EXTEND);
			setState(166);
			expression(0);
			setState(167);
			match(LBLOCK);
			setState(171);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==EOL) {
				{
				{
				setState(168);
				match(EOL);
				}
				}
				setState(173);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(174);
			object();
			setState(175);
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
		enterRule(_localctx, 18, RULE_object);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(180);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(177);
					field();
					}
					} 
				}
				setState(182);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			}
			{
			setState(184);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==GETTERSETTER) {
				{
				setState(183);
				getterSetter();
				}
			}

			setState(187);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==GETTER) {
				{
				setState(186);
				getter();
				}
			}

			setState(190);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SETTER) {
				{
				setState(189);
				setter();
				}
			}

			}
			setState(195);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 3573412790292L) != 0)) {
				{
				{
				setState(192);
				methodDefinition();
				}
				}
				setState(197);
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
		enterRule(_localctx, 20, RULE_field);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(198);
			match(IDENTIFIER);
			setState(199);
			match(COLON);
			setState(200);
			expression(0);
			setState(204);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==EOL) {
				{
				{
				setState(201);
				match(EOL);
				}
				}
				setState(206);
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
		enterRule(_localctx, 22, RULE_getterSetter);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(207);
			match(GETTERSETTER);
			setState(208);
			match(LBLOCK);
			setState(222); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(212);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==EOL) {
					{
					{
					setState(209);
					match(EOL);
					}
					}
					setState(214);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(215);
				fieldId();
				setState(219);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(216);
						separator();
						}
						} 
					}
					setState(221);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
				}
				}
				}
				setState(224); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==EOL || _la==IDENTIFIER );
			setState(226);
			match(RBLOCK);
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
		enterRule(_localctx, 24, RULE_getter);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(233);
			match(GETTER);
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
		enterRule(_localctx, 26, RULE_setter);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(259);
			match(SETTER);
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
		enterRule(_localctx, 28, RULE_fieldId);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(285);
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
		enterRule(_localctx, 30, RULE_methodDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(287);
			methodSignature();
			setState(288);
			match(COLON);
			setState(290);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EOL) {
				{
				setState(289);
				match(EOL);
				}
			}

			setState(292);
			blockOfStatements();
			setState(296);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==EOL) {
				{
				{
				setState(293);
				match(EOL);
				}
				}
				setState(298);
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
		enterRule(_localctx, 32, RULE_methodSignature);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(299);
			methodSigPart();
			setState(301); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(300);
				methodSigPart();
				}
				}
				setState(303); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 3573412790292L) != 0) );
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
		enterRule(_localctx, 34, RULE_methodSigPart);
		try {
			setState(317);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
			case 1:
				_localctx = new MethSigWordContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(305);
				match(WORD);
				}
				break;
			case 2:
				_localctx = new MethSigParamContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(306);
				match(IDENTIFIER);
				}
				break;
			case 3:
				_localctx = new MethSigParamContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(307);
				match(LPAREN);
				setState(308);
				match(IDENTIFIER);
				setState(309);
				match(RPAREN);
				}
				break;
			case 4:
				_localctx = new MethSigSelfContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(310);
				match(SELFREF);
				}
				break;
			case 5:
				_localctx = new MethSigSelfContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(311);
				match(LPAREN);
				setState(312);
				match(SELFREF);
				setState(313);
				match(RPAREN);
				}
				break;
			case 6:
				_localctx = new MethSigBlockContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(314);
				match(LBLOCK);
				setState(315);
				match(IDENTIFIER);
				setState(316);
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
		enterRule(_localctx, 36, RULE_blockOfStatements);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(319);
			match(LBLOCK);
			setState(323);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 16982166627924L) != 0)) {
				{
				{
				setState(320);
				statement();
				}
				}
				setState(325);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(326);
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
		enterRule(_localctx, 38, RULE_statement);
		try {
			setState(338);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
			case 1:
				_localctx = new AssStatementContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(328);
				assignmentStatement();
				}
				break;
			case 2:
				_localctx = new PrStatementContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(329);
				printStatement();
				}
				break;
			case 3:
				_localctx = new ExprContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(330);
				expression(0);
				}
				break;
			case 4:
				_localctx = new BlankContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(331);
				endOfStatement();
				}
				break;
			case 5:
				_localctx = new RedoContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(332);
				match(REDO);
				}
				break;
			case 6:
				_localctx = new ReturnContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(333);
				match(RETURN);
				setState(335);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
				case 1:
					{
					setState(334);
					expression(0);
					}
					break;
				}
				}
				break;
			case 7:
				_localctx = new UsingStatementContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(337);
				usingLibrary();
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
		enterRule(_localctx, 40, RULE_endOfStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(340);
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

	public final AssignmentStatementContext assignmentStatement() throws RecognitionException {
		AssignmentStatementContext _localctx = new AssignmentStatementContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_assignmentStatement);
		int _la;
		try {
			setState(354);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
			case 1:
				_localctx = new SetVariableContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(342);
				match(IDENTIFIER);
				setState(343);
				match(COLON);
				setState(344);
				expression(0);
				}
				break;
			case 2:
				_localctx = new SetListElementContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(345);
				match(IDENTIFIER);
				setState(347); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(346);
					listPart();
					}
					}
					setState(349); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==LBRACE );
				setState(351);
				match(COLON);
				setState(352);
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
		enterRule(_localctx, 44, RULE_printStatement);
		int _la;
		try {
			_localctx = new PrntStatementContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(364);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 16843117035604L) != 0)) {
				{
				setState(356);
				expression(0);
				setState(361);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(357);
					match(COMMA);
					setState(358);
					expression(0);
					}
					}
					setState(363);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(366);
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
		int _startState = 46;
		enterRecursionRule(_localctx, 46, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(402);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,46,_ctx) ) {
			case 1:
				{
				_localctx = new ExprMinusContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(369);
				match(MINUS);
				setState(370);
				expression(26);
				}
				break;
			case 2:
				{
				_localctx = new ExprListElementContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(371);
				listElement();
				}
				break;
			case 3:
				{
				_localctx = new ExprFncCallContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(372);
				functionCall();
				}
				break;
			case 4:
				{
				_localctx = new ExprVarContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(373);
				match(IDENTIFIER);
				}
				break;
			case 5:
				{
				_localctx = new ExprNumberContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(374);
				match(NUMBER);
				}
				break;
			case 6:
				{
				_localctx = new ExprWordProductContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(375);
				match(WORDPRODUCT);
				}
				break;
			case 7:
				{
				_localctx = new ExprBooleanContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(376);
				match(BOOLEAN);
				}
				break;
			case 8:
				{
				_localctx = new ExprStringContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(377);
				match(STRING);
				}
				break;
			case 9:
				{
				_localctx = new ExprBlockContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(378);
				blockOfStatements();
				}
				break;
			case 10:
				{
				_localctx = new ExprListContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(379);
				list();
				}
				break;
			case 11:
				{
				_localctx = new ExprMapContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(380);
				map();
				}
				break;
			case 12:
				{
				_localctx = new ExprLibraryContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(381);
				library();
				}
				break;
			case 13:
				{
				_localctx = new ExprObjectContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(382);
				createObject();
				}
				break;
			case 14:
				{
				_localctx = new ExprExtendContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(383);
				extendObject();
				}
				break;
			case 15:
				{
				_localctx = new ExprGetterMethodContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(384);
				getterMethodCall();
				}
				break;
			case 16:
				{
				_localctx = new ExprSetterMethodContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(385);
				setterMethodCall();
				}
				break;
			case 17:
				{
				_localctx = new ExprParenContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(386);
				match(LPAREN);
				setState(390);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==EOL) {
					{
					{
					setState(387);
					match(EOL);
					}
					}
					setState(392);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(393);
				expression(0);
				setState(397);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==EOL) {
					{
					{
					setState(394);
					match(EOL);
					}
					}
					setState(399);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(400);
				match(RPAREN);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(433);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,48,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(431);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,47,_ctx) ) {
					case 1:
						{
						_localctx = new ExprMulContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(404);
						if (!(precpred(_ctx, 25))) throw new FailedPredicateException(this, "precpred(_ctx, 25)");
						setState(405);
						match(MUL);
						setState(406);
						expression(26);
						}
						break;
					case 2:
						{
						_localctx = new ExprAddContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(407);
						if (!(precpred(_ctx, 24))) throw new FailedPredicateException(this, "precpred(_ctx, 24)");
						setState(408);
						match(ADD);
						setState(409);
						expression(25);
						}
						break;
					case 3:
						{
						_localctx = new ExprLessContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(410);
						if (!(precpred(_ctx, 23))) throw new FailedPredicateException(this, "precpred(_ctx, 23)");
						setState(411);
						match(LESS);
						setState(412);
						expression(24);
						}
						break;
					case 4:
						{
						_localctx = new ExprGreaterContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(413);
						if (!(precpred(_ctx, 22))) throw new FailedPredicateException(this, "precpred(_ctx, 22)");
						setState(414);
						match(GREATER);
						setState(415);
						expression(23);
						}
						break;
					case 5:
						{
						_localctx = new ExprGreatEqlContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(416);
						if (!(precpred(_ctx, 21))) throw new FailedPredicateException(this, "precpred(_ctx, 21)");
						setState(417);
						match(GREATEREQUAL);
						setState(418);
						expression(22);
						}
						break;
					case 6:
						{
						_localctx = new ExprLessEqlContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(419);
						if (!(precpred(_ctx, 20))) throw new FailedPredicateException(this, "precpred(_ctx, 20)");
						setState(420);
						match(LESSEQUAL);
						setState(421);
						expression(21);
						}
						break;
					case 7:
						{
						_localctx = new ExprEqualContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(422);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(423);
						match(EQUAL);
						setState(424);
						expression(20);
						}
						break;
					case 8:
						{
						_localctx = new ExprNotEqlContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(425);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(426);
						match(NOTEQUAL);
						setState(427);
						expression(19);
						}
						break;
					case 9:
						{
						_localctx = new ExprConcatContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(428);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(429);
						match(CONCAT);
						setState(430);
						expression(18);
						}
						break;
					}
					} 
				}
				setState(435);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,48,_ctx);
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
		public TerminalNode POSSESSIVE() { return getToken(RemixParser.POSSESSIVE, 0); }
		public List<TerminalNode> IDENTIFIER() { return getTokens(RemixParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(RemixParser.IDENTIFIER, i);
		}
		public ListElementContext listElement() {
			return getRuleContext(ListElementContext.class,0);
		}
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
		enterRule(_localctx, 48, RULE_getterMethodCall);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(438);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,49,_ctx) ) {
			case 1:
				{
				setState(436);
				match(IDENTIFIER);
				}
				break;
			case 2:
				{
				setState(437);
				listElement();
				}
				break;
			}
			setState(440);
			match(POSSESSIVE);
			setState(441);
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
		public TerminalNode POSSESSIVE() { return getToken(RemixParser.POSSESSIVE, 0); }
		public List<TerminalNode> IDENTIFIER() { return getTokens(RemixParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(RemixParser.IDENTIFIER, i);
		}
		public TerminalNode COLON() { return getToken(RemixParser.COLON, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ListElementContext listElement() {
			return getRuleContext(ListElementContext.class,0);
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
		enterRule(_localctx, 50, RULE_setterMethodCall);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(445);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,50,_ctx) ) {
			case 1:
				{
				setState(443);
				match(IDENTIFIER);
				}
				break;
			case 2:
				{
				setState(444);
				listElement();
				}
				break;
			}
			setState(447);
			match(POSSESSIVE);
			setState(448);
			match(IDENTIFIER);
			setState(449);
			match(COLON);
			setState(450);
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
		enterRule(_localctx, 52, RULE_listElement);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(452);
			match(IDENTIFIER);
			setState(454); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(453);
					listPart();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(456); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,51,_ctx);
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
	@SuppressWarnings("CheckReturnValue")
	public static class ListPartKeyContext extends ListPartContext {
		public TerminalNode LBRACE() { return getToken(RemixParser.LBRACE, 0); }
		public KeyContext key() {
			return getRuleContext(KeyContext.class,0);
		}
		public TerminalNode RBRACE() { return getToken(RemixParser.RBRACE, 0); }
		public ListPartKeyContext(ListPartContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitListPartKey(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListPartContext listPart() throws RecognitionException {
		ListPartContext _localctx = new ListPartContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_listPart);
		try {
			setState(466);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,52,_ctx) ) {
			case 1:
				_localctx = new ListPartExprContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(458);
				match(LBRACE);
				setState(459);
				expression(0);
				setState(460);
				match(RBRACE);
				}
				break;
			case 2:
				_localctx = new ListPartKeyContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(462);
				match(LBRACE);
				setState(463);
				key();
				setState(464);
				match(RBRACE);
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
	public static class FunctionCallContext extends ParserRuleContext {
		public List<CallPartContext> callPart() {
			return getRuleContexts(CallPartContext.class);
		}
		public CallPartContext callPart(int i) {
			return getRuleContext(CallPartContext.class,i);
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
		enterRule(_localctx, 56, RULE_functionCall);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(468);
			callPart();
			setState(470); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(469);
					callPart();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(472); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,53,_ctx);
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
		enterRule(_localctx, 58, RULE_callPart);
		int _la;
		try {
			setState(502);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,56,_ctx) ) {
			case 1:
				_localctx = new CallWordContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(474);
				match(WORD);
				}
				break;
			case 2:
				_localctx = new CallVarContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(475);
				match(IDENTIFIER);
				}
				break;
			case 3:
				_localctx = new CallParamContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(476);
				match(LPAREN);
				setState(480);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==EOL) {
					{
					{
					setState(477);
					match(EOL);
					}
					}
					setState(482);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(483);
				expression(0);
				setState(487);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==EOL) {
					{
					{
					setState(484);
					match(EOL);
					}
					}
					setState(489);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(490);
				match(RPAREN);
				}
				break;
			case 4:
				_localctx = new CallSelfContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(492);
				match(SELFREF);
				}
				break;
			case 5:
				_localctx = new CallSelfContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(493);
				match(LPAREN);
				setState(494);
				match(SELFREF);
				setState(495);
				match(RPAREN);
				}
				break;
			case 6:
				_localctx = new CallNumberContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(496);
				match(NUMBER);
				}
				break;
			case 7:
				_localctx = new CallBooleanContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(497);
				match(BOOLEAN);
				}
				break;
			case 8:
				_localctx = new CallStringContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(498);
				match(STRING);
				}
				break;
			case 9:
				_localctx = new CallBlockContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(499);
				blockOfStatements();
				}
				break;
			case 10:
				_localctx = new CallListContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(500);
				list();
				}
				break;
			case 11:
				_localctx = new CallMapContext(_localctx);
				enterOuterAlt(_localctx, 11);
				{
				setState(501);
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
		enterRule(_localctx, 60, RULE_list);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(504);
			match(LBRACE);
			setState(505);
			listContents();
			setState(506);
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
		enterRule(_localctx, 62, RULE_separator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(508);
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
		enterRule(_localctx, 64, RULE_listContents);
		int _la;
		try {
			setState(560);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,65,_ctx) ) {
			case 1:
				_localctx = new CommaListContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(518);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 16843117035604L) != 0)) {
					{
					setState(510);
					expression(0);
					setState(515);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(511);
						match(COMMA);
						setState(512);
						expression(0);
						}
						}
						setState(517);
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
				setState(520);
				match(LBLOCK);
				setState(524);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==EOL) {
					{
					{
					setState(521);
					match(EOL);
					}
					}
					setState(526);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(544);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 16843117035604L) != 0)) {
					{
					{
					setState(527);
					expression(0);
					setState(539);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA || _la==EOL) {
						{
						{
						setState(528);
						separator();
						setState(532);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==EOL) {
							{
							{
							setState(529);
							match(EOL);
							}
							}
							setState(534);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(535);
						expression(0);
						}
						}
						setState(541);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
					}
					setState(546);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(547);
				match(RBLOCK);
				setState(551);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==EOL) {
					{
					{
					setState(548);
					match(EOL);
					}
					}
					setState(553);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 3:
				_localctx = new EmptyListContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(557);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==EOL) {
					{
					{
					setState(554);
					match(EOL);
					}
					}
					setState(559);
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
		enterRule(_localctx, 66, RULE_map);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(562);
			match(LBRACE);
			setState(563);
			mapContents();
			setState(564);
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
		enterRule(_localctx, 68, RULE_mapContents);
		int _la;
		try {
			setState(600);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case WORD:
			case STRING:
				_localctx = new CommaMapContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(566);
				keyValue();
				setState(571);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(567);
					match(COMMA);
					setState(568);
					keyValue();
					}
					}
					setState(573);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case LBLOCK:
				_localctx = new BlockMapContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(574);
				match(LBLOCK);
				setState(578);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==EOL) {
					{
					{
					setState(575);
					match(EOL);
					}
					}
					setState(580);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(581);
				keyValue();
				setState(593);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA || _la==EOL) {
					{
					{
					setState(582);
					separator();
					setState(586);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==EOL) {
						{
						{
						setState(583);
						match(EOL);
						}
						}
						setState(588);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(589);
					keyValue();
					}
					}
					setState(595);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(596);
				match(RBLOCK);
				setState(598);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==EOL) {
					{
					setState(597);
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
		public KeyContext key() {
			return getRuleContext(KeyContext.class,0);
		}
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
		enterRule(_localctx, 70, RULE_keyValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(602);
			key();
			setState(603);
			match(COLON);
			setState(604);
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
	public static class KeyContext extends ParserRuleContext {
		public TerminalNode WORD() { return getToken(RemixParser.WORD, 0); }
		public TerminalNode STRING() { return getToken(RemixParser.STRING, 0); }
		public KeyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_key; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitKey(this);
			else return visitor.visitChildren(this);
		}
	}

	public final KeyContext key() throws RecognitionException {
		KeyContext _localctx = new KeyContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_key);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(606);
			_la = _input.LA(1);
			if ( !(_la==WORD || _la==STRING) ) {
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
		enterRule(_localctx, 74, RULE_value);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(608);
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
		case 23:
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
		"\u0004\u0001+\u0263\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
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
		"#\u0007#\u0002$\u0007$\u0002%\u0007%\u0001\u0000\u0001\u0000\u0005\u0000"+
		"O\b\u0000\n\u0000\f\u0000R\t\u0000\u0001\u0000\u0001\u0000\u0001\u0001"+
		"\u0001\u0001\u0003\u0001X\b\u0001\u0001\u0001\u0001\u0001\u0005\u0001"+
		"\\\b\u0001\n\u0001\f\u0001_\t\u0001\u0001\u0001\u0001\u0001\u0005\u0001"+
		"c\b\u0001\n\u0001\f\u0001f\t\u0001\u0005\u0001h\b\u0001\n\u0001\f\u0001"+
		"k\t\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0005\u0002s\b\u0002\n\u0002\f\u0002v\t\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0003\u0003\u0003{\b\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0003\u0003\u0080\b\u0003\u0001\u0003\u0003\u0003\u0083\b"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0005\u0001"+
		"\u0005\u0004\u0005\u008b\b\u0005\u000b\u0005\f\u0005\u008c\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006\u0094\b\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006\u0099\b\u0006\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0005\u0007\u009e\b\u0007\n\u0007\f\u0007\u00a1"+
		"\t\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0005\b\u00aa\b\b\n\b\f\b\u00ad\t\b\u0001\b\u0001\b\u0001\b\u0001\t"+
		"\u0005\t\u00b3\b\t\n\t\f\t\u00b6\t\t\u0001\t\u0003\t\u00b9\b\t\u0001\t"+
		"\u0003\t\u00bc\b\t\u0001\t\u0003\t\u00bf\b\t\u0001\t\u0005\t\u00c2\b\t"+
		"\n\t\f\t\u00c5\t\t\u0001\n\u0001\n\u0001\n\u0001\n\u0005\n\u00cb\b\n\n"+
		"\n\f\n\u00ce\t\n\u0001\u000b\u0001\u000b\u0001\u000b\u0005\u000b\u00d3"+
		"\b\u000b\n\u000b\f\u000b\u00d6\t\u000b\u0001\u000b\u0001\u000b\u0005\u000b"+
		"\u00da\b\u000b\n\u000b\f\u000b\u00dd\t\u000b\u0004\u000b\u00df\b\u000b"+
		"\u000b\u000b\f\u000b\u00e0\u0001\u000b\u0001\u000b\u0005\u000b\u00e5\b"+
		"\u000b\n\u000b\f\u000b\u00e8\t\u000b\u0001\f\u0001\f\u0001\f\u0005\f\u00ed"+
		"\b\f\n\f\f\f\u00f0\t\f\u0001\f\u0001\f\u0005\f\u00f4\b\f\n\f\f\f\u00f7"+
		"\t\f\u0004\f\u00f9\b\f\u000b\f\f\f\u00fa\u0001\f\u0001\f\u0005\f\u00ff"+
		"\b\f\n\f\f\f\u0102\t\f\u0001\r\u0001\r\u0001\r\u0005\r\u0107\b\r\n\r\f"+
		"\r\u010a\t\r\u0001\r\u0001\r\u0005\r\u010e\b\r\n\r\f\r\u0111\t\r\u0004"+
		"\r\u0113\b\r\u000b\r\f\r\u0114\u0001\r\u0001\r\u0005\r\u0119\b\r\n\r\f"+
		"\r\u011c\t\r\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0003\u000f\u0123\b\u000f\u0001\u000f\u0001\u000f\u0005\u000f\u0127\b"+
		"\u000f\n\u000f\f\u000f\u012a\t\u000f\u0001\u0010\u0001\u0010\u0004\u0010"+
		"\u012e\b\u0010\u000b\u0010\f\u0010\u012f\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0003\u0011\u013e\b\u0011\u0001"+
		"\u0012\u0001\u0012\u0005\u0012\u0142\b\u0012\n\u0012\f\u0012\u0145\t\u0012"+
		"\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0003\u0013\u0150\b\u0013\u0001\u0013"+
		"\u0003\u0013\u0153\b\u0013\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0004\u0015\u015c\b\u0015\u000b\u0015"+
		"\f\u0015\u015d\u0001\u0015\u0001\u0015\u0001\u0015\u0003\u0015\u0163\b"+
		"\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0005\u0016\u0168\b\u0016\n"+
		"\u0016\f\u0016\u016b\t\u0016\u0003\u0016\u016d\b\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0005\u0017\u0185\b\u0017\n\u0017\f\u0017"+
		"\u0188\t\u0017\u0001\u0017\u0001\u0017\u0005\u0017\u018c\b\u0017\n\u0017"+
		"\f\u0017\u018f\t\u0017\u0001\u0017\u0001\u0017\u0003\u0017\u0193\b\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0005\u0017\u01b0\b\u0017\n\u0017"+
		"\f\u0017\u01b3\t\u0017\u0001\u0018\u0001\u0018\u0003\u0018\u01b7\b\u0018"+
		"\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019\u0003\u0019"+
		"\u01be\b\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019"+
		"\u0001\u001a\u0001\u001a\u0004\u001a\u01c7\b\u001a\u000b\u001a\f\u001a"+
		"\u01c8\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001"+
		"\u001b\u0001\u001b\u0001\u001b\u0003\u001b\u01d3\b\u001b\u0001\u001c\u0001"+
		"\u001c\u0004\u001c\u01d7\b\u001c\u000b\u001c\f\u001c\u01d8\u0001\u001d"+
		"\u0001\u001d\u0001\u001d\u0001\u001d\u0005\u001d\u01df\b\u001d\n\u001d"+
		"\f\u001d\u01e2\t\u001d\u0001\u001d\u0001\u001d\u0005\u001d\u01e6\b\u001d"+
		"\n\u001d\f\u001d\u01e9\t\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001"+
		"\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001"+
		"\u001d\u0001\u001d\u0001\u001d\u0003\u001d\u01f7\b\u001d\u0001\u001e\u0001"+
		"\u001e\u0001\u001e\u0001\u001e\u0001\u001f\u0001\u001f\u0001 \u0001 \u0001"+
		" \u0005 \u0202\b \n \f \u0205\t \u0003 \u0207\b \u0001 \u0001 \u0005 "+
		"\u020b\b \n \f \u020e\t \u0001 \u0001 \u0001 \u0005 \u0213\b \n \f \u0216"+
		"\t \u0001 \u0001 \u0005 \u021a\b \n \f \u021d\t \u0005 \u021f\b \n \f"+
		" \u0222\t \u0001 \u0001 \u0005 \u0226\b \n \f \u0229\t \u0001 \u0005 "+
		"\u022c\b \n \f \u022f\t \u0003 \u0231\b \u0001!\u0001!\u0001!\u0001!\u0001"+
		"\"\u0001\"\u0001\"\u0005\"\u023a\b\"\n\"\f\"\u023d\t\"\u0001\"\u0001\""+
		"\u0005\"\u0241\b\"\n\"\f\"\u0244\t\"\u0001\"\u0001\"\u0001\"\u0005\"\u0249"+
		"\b\"\n\"\f\"\u024c\t\"\u0001\"\u0001\"\u0005\"\u0250\b\"\n\"\f\"\u0253"+
		"\t\"\u0001\"\u0001\"\u0003\"\u0257\b\"\u0003\"\u0259\b\"\u0001#\u0001"+
		"#\u0001#\u0001#\u0001$\u0001$\u0001%\u0001%\u0001%\u0000\u0001.&\u0000"+
		"\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c"+
		"\u001e \"$&(*,.02468:<>@BDFHJ\u0000\u0004\u0001\u0000\r\u000e\u0001\u0000"+
		"\t\n\u0002\u0000\b\b\r\r\u0002\u0000))++\u02ae\u0000P\u0001\u0000\u0000"+
		"\u0000\u0002U\u0001\u0000\u0000\u0000\u0004n\u0001\u0000\u0000\u0000\u0006"+
		"z\u0001\u0000\u0000\u0000\b\u0086\u0001\u0000\u0000\u0000\n\u0088\u0001"+
		"\u0000\u0000\u0000\f\u0098\u0001\u0000\u0000\u0000\u000e\u009a\u0001\u0000"+
		"\u0000\u0000\u0010\u00a5\u0001\u0000\u0000\u0000\u0012\u00b4\u0001\u0000"+
		"\u0000\u0000\u0014\u00c6\u0001\u0000\u0000\u0000\u0016\u00cf\u0001\u0000"+
		"\u0000\u0000\u0018\u00e9\u0001\u0000\u0000\u0000\u001a\u0103\u0001\u0000"+
		"\u0000\u0000\u001c\u011d\u0001\u0000\u0000\u0000\u001e\u011f\u0001\u0000"+
		"\u0000\u0000 \u012b\u0001\u0000\u0000\u0000\"\u013d\u0001\u0000\u0000"+
		"\u0000$\u013f\u0001\u0000\u0000\u0000&\u0152\u0001\u0000\u0000\u0000("+
		"\u0154\u0001\u0000\u0000\u0000*\u0162\u0001\u0000\u0000\u0000,\u016c\u0001"+
		"\u0000\u0000\u0000.\u0192\u0001\u0000\u0000\u00000\u01b6\u0001\u0000\u0000"+
		"\u00002\u01bd\u0001\u0000\u0000\u00004\u01c4\u0001\u0000\u0000\u00006"+
		"\u01d2\u0001\u0000\u0000\u00008\u01d4\u0001\u0000\u0000\u0000:\u01f6\u0001"+
		"\u0000\u0000\u0000<\u01f8\u0001\u0000\u0000\u0000>\u01fc\u0001\u0000\u0000"+
		"\u0000@\u0230\u0001\u0000\u0000\u0000B\u0232\u0001\u0000\u0000\u0000D"+
		"\u0258\u0001\u0000\u0000\u0000F\u025a\u0001\u0000\u0000\u0000H\u025e\u0001"+
		"\u0000\u0000\u0000J\u0260\u0001\u0000\u0000\u0000LO\u0003\u0006\u0003"+
		"\u0000MO\u0003&\u0013\u0000NL\u0001\u0000\u0000\u0000NM\u0001\u0000\u0000"+
		"\u0000OR\u0001\u0000\u0000\u0000PN\u0001\u0000\u0000\u0000PQ\u0001\u0000"+
		"\u0000\u0000QS\u0001\u0000\u0000\u0000RP\u0001\u0000\u0000\u0000ST\u0005"+
		"\u0000\u0000\u0001T\u0001\u0001\u0000\u0000\u0000UW\u0005$\u0000\u0000"+
		"VX\u0005+\u0000\u0000WV\u0001\u0000\u0000\u0000WX\u0001\u0000\u0000\u0000"+
		"XY\u0001\u0000\u0000\u0000Y]\u0005\u0004\u0000\u0000Z\\\u0005\r\u0000"+
		"\u0000[Z\u0001\u0000\u0000\u0000\\_\u0001\u0000\u0000\u0000][\u0001\u0000"+
		"\u0000\u0000]^\u0001\u0000\u0000\u0000^i\u0001\u0000\u0000\u0000_]\u0001"+
		"\u0000\u0000\u0000`d\u0003\u0006\u0003\u0000ac\u0005\r\u0000\u0000ba\u0001"+
		"\u0000\u0000\u0000cf\u0001\u0000\u0000\u0000db\u0001\u0000\u0000\u0000"+
		"de\u0001\u0000\u0000\u0000eh\u0001\u0000\u0000\u0000fd\u0001\u0000\u0000"+
		"\u0000g`\u0001\u0000\u0000\u0000hk\u0001\u0000\u0000\u0000ig\u0001\u0000"+
		"\u0000\u0000ij\u0001\u0000\u0000\u0000jl\u0001\u0000\u0000\u0000ki\u0001"+
		"\u0000\u0000\u0000lm\u0005\u0005\u0000\u0000m\u0003\u0001\u0000\u0000"+
		"\u0000no\u0005%\u0000\u0000ot\u0003.\u0017\u0000pq\u0005\b\u0000\u0000"+
		"qs\u0003.\u0017\u0000rp\u0001\u0000\u0000\u0000sv\u0001\u0000\u0000\u0000"+
		"tr\u0001\u0000\u0000\u0000tu\u0001\u0000\u0000\u0000uw\u0001\u0000\u0000"+
		"\u0000vt\u0001\u0000\u0000\u0000wx\u0003$\u0012\u0000x\u0005\u0001\u0000"+
		"\u0000\u0000y{\u0003\b\u0004\u0000zy\u0001\u0000\u0000\u0000z{\u0001\u0000"+
		"\u0000\u0000{|\u0001\u0000\u0000\u0000|}\u0003\n\u0005\u0000}\u007f\u0005"+
		"\u0001\u0000\u0000~\u0080\u0005\u0001\u0000\u0000\u007f~\u0001\u0000\u0000"+
		"\u0000\u007f\u0080\u0001\u0000\u0000\u0000\u0080\u0082\u0001\u0000\u0000"+
		"\u0000\u0081\u0083\u0005\r\u0000\u0000\u0082\u0081\u0001\u0000\u0000\u0000"+
		"\u0082\u0083\u0001\u0000\u0000\u0000\u0083\u0084\u0001\u0000\u0000\u0000"+
		"\u0084\u0085\u0003$\u0012\u0000\u0085\u0007\u0001\u0000\u0000\u0000\u0086"+
		"\u0087\u0005\u000f\u0000\u0000\u0087\t\u0001\u0000\u0000\u0000\u0088\u008a"+
		"\u0003\f\u0006\u0000\u0089\u008b\u0003\f\u0006\u0000\u008a\u0089\u0001"+
		"\u0000\u0000\u0000\u008b\u008c\u0001\u0000\u0000\u0000\u008c\u008a\u0001"+
		"\u0000\u0000\u0000\u008c\u008d\u0001\u0000\u0000\u0000\u008d\u000b\u0001"+
		"\u0000\u0000\u0000\u008e\u0099\u0005)\u0000\u0000\u008f\u0094\u0005(\u0000"+
		"\u0000\u0090\u0091\u0005\u0002\u0000\u0000\u0091\u0092\u0005(\u0000\u0000"+
		"\u0092\u0094\u0005\u0003\u0000\u0000\u0093\u008f\u0001\u0000\u0000\u0000"+
		"\u0093\u0090\u0001\u0000\u0000\u0000\u0094\u0099\u0001\u0000\u0000\u0000"+
		"\u0095\u0096\u0005\u0004\u0000\u0000\u0096\u0097\u0005(\u0000\u0000\u0097"+
		"\u0099\u0005\u0005\u0000\u0000\u0098\u008e\u0001\u0000\u0000\u0000\u0098"+
		"\u0093\u0001\u0000\u0000\u0000\u0098\u0095\u0001\u0000\u0000\u0000\u0099"+
		"\r\u0001\u0000\u0000\u0000\u009a\u009b\u0005\u001f\u0000\u0000\u009b\u009f"+
		"\u0005\u0004\u0000\u0000\u009c\u009e\u0005\r\u0000\u0000\u009d\u009c\u0001"+
		"\u0000\u0000\u0000\u009e\u00a1\u0001\u0000\u0000\u0000\u009f\u009d\u0001"+
		"\u0000\u0000\u0000\u009f\u00a0\u0001\u0000\u0000\u0000\u00a0\u00a2\u0001"+
		"\u0000\u0000\u0000\u00a1\u009f\u0001\u0000\u0000\u0000\u00a2\u00a3\u0003"+
		"\u0012\t\u0000\u00a3\u00a4\u0005\u0005\u0000\u0000\u00a4\u000f\u0001\u0000"+
		"\u0000\u0000\u00a5\u00a6\u0005 \u0000\u0000\u00a6\u00a7\u0003.\u0017\u0000"+
		"\u00a7\u00ab\u0005\u0004\u0000\u0000\u00a8\u00aa\u0005\r\u0000\u0000\u00a9"+
		"\u00a8\u0001\u0000\u0000\u0000\u00aa\u00ad\u0001\u0000\u0000\u0000\u00ab"+
		"\u00a9\u0001\u0000\u0000\u0000\u00ab\u00ac\u0001\u0000\u0000\u0000\u00ac"+
		"\u00ae\u0001\u0000\u0000\u0000\u00ad\u00ab\u0001\u0000\u0000\u0000\u00ae"+
		"\u00af\u0003\u0012\t\u0000\u00af\u00b0\u0005\u0005\u0000\u0000\u00b0\u0011"+
		"\u0001\u0000\u0000\u0000\u00b1\u00b3\u0003\u0014\n\u0000\u00b2\u00b1\u0001"+
		"\u0000\u0000\u0000\u00b3\u00b6\u0001\u0000\u0000\u0000\u00b4\u00b2\u0001"+
		"\u0000\u0000\u0000\u00b4\u00b5\u0001\u0000\u0000\u0000\u00b5\u00b8\u0001"+
		"\u0000\u0000\u0000\u00b6\u00b4\u0001\u0000\u0000\u0000\u00b7\u00b9\u0003"+
		"\u0016\u000b\u0000\u00b8\u00b7\u0001\u0000\u0000\u0000\u00b8\u00b9\u0001"+
		"\u0000\u0000\u0000\u00b9\u00bb\u0001\u0000\u0000\u0000\u00ba\u00bc\u0003"+
		"\u0018\f\u0000\u00bb\u00ba\u0001\u0000\u0000\u0000\u00bb\u00bc\u0001\u0000"+
		"\u0000\u0000\u00bc\u00be\u0001\u0000\u0000\u0000\u00bd\u00bf\u0003\u001a"+
		"\r\u0000\u00be\u00bd\u0001\u0000\u0000\u0000\u00be\u00bf\u0001\u0000\u0000"+
		"\u0000\u00bf\u00c3\u0001\u0000\u0000\u0000\u00c0\u00c2\u0003\u001e\u000f"+
		"\u0000\u00c1\u00c0\u0001\u0000\u0000\u0000\u00c2\u00c5\u0001\u0000\u0000"+
		"\u0000\u00c3\u00c1\u0001\u0000\u0000\u0000\u00c3\u00c4\u0001\u0000\u0000"+
		"\u0000\u00c4\u0013\u0001\u0000\u0000\u0000\u00c5\u00c3\u0001\u0000\u0000"+
		"\u0000\u00c6\u00c7\u0005(\u0000\u0000\u00c7\u00c8\u0005\u0001\u0000\u0000"+
		"\u00c8\u00cc\u0003.\u0017\u0000\u00c9\u00cb\u0005\r\u0000\u0000\u00ca"+
		"\u00c9\u0001\u0000\u0000\u0000\u00cb\u00ce\u0001\u0000\u0000\u0000\u00cc"+
		"\u00ca\u0001\u0000\u0000\u0000\u00cc\u00cd\u0001\u0000\u0000\u0000\u00cd"+
		"\u0015\u0001\u0000\u0000\u0000\u00ce\u00cc\u0001\u0000\u0000\u0000\u00cf"+
		"\u00d0\u0005!\u0000\u0000\u00d0\u00de\u0005\u0004\u0000\u0000\u00d1\u00d3"+
		"\u0005\r\u0000\u0000\u00d2\u00d1\u0001\u0000\u0000\u0000\u00d3\u00d6\u0001"+
		"\u0000\u0000\u0000\u00d4\u00d2\u0001\u0000\u0000\u0000\u00d4\u00d5\u0001"+
		"\u0000\u0000\u0000\u00d5\u00d7\u0001\u0000\u0000\u0000\u00d6\u00d4\u0001"+
		"\u0000\u0000\u0000\u00d7\u00db\u0003\u001c\u000e\u0000\u00d8\u00da\u0003"+
		">\u001f\u0000\u00d9\u00d8\u0001\u0000\u0000\u0000\u00da\u00dd\u0001\u0000"+
		"\u0000\u0000\u00db\u00d9\u0001\u0000\u0000\u0000\u00db\u00dc\u0001\u0000"+
		"\u0000\u0000\u00dc\u00df\u0001\u0000\u0000\u0000\u00dd\u00db\u0001\u0000"+
		"\u0000\u0000\u00de\u00d4\u0001\u0000\u0000\u0000\u00df\u00e0\u0001\u0000"+
		"\u0000\u0000\u00e0\u00de\u0001\u0000\u0000\u0000\u00e0\u00e1\u0001\u0000"+
		"\u0000\u0000\u00e1\u00e2\u0001\u0000\u0000\u0000\u00e2\u00e6\u0005\u0005"+
		"\u0000\u0000\u00e3\u00e5\u0005\r\u0000\u0000\u00e4\u00e3\u0001\u0000\u0000"+
		"\u0000\u00e5\u00e8\u0001\u0000\u0000\u0000\u00e6\u00e4\u0001\u0000\u0000"+
		"\u0000\u00e6\u00e7\u0001\u0000\u0000\u0000\u00e7\u0017\u0001\u0000\u0000"+
		"\u0000\u00e8\u00e6\u0001\u0000\u0000\u0000\u00e9\u00ea\u0005\"\u0000\u0000"+
		"\u00ea\u00f8\u0005\u0004\u0000\u0000\u00eb\u00ed\u0005\r\u0000\u0000\u00ec"+
		"\u00eb\u0001\u0000\u0000\u0000\u00ed\u00f0\u0001\u0000\u0000\u0000\u00ee"+
		"\u00ec\u0001\u0000\u0000\u0000\u00ee\u00ef\u0001\u0000\u0000\u0000\u00ef"+
		"\u00f1\u0001\u0000\u0000\u0000\u00f0\u00ee\u0001\u0000\u0000\u0000\u00f1"+
		"\u00f5\u0003\u001c\u000e\u0000\u00f2\u00f4\u0003>\u001f\u0000\u00f3\u00f2"+
		"\u0001\u0000\u0000\u0000\u00f4\u00f7\u0001\u0000\u0000\u0000\u00f5\u00f3"+
		"\u0001\u0000\u0000\u0000\u00f5\u00f6\u0001\u0000\u0000\u0000\u00f6\u00f9"+
		"\u0001\u0000\u0000\u0000\u00f7\u00f5\u0001\u0000\u0000\u0000\u00f8\u00ee"+
		"\u0001\u0000\u0000\u0000\u00f9\u00fa\u0001\u0000\u0000\u0000\u00fa\u00f8"+
		"\u0001\u0000\u0000\u0000\u00fa\u00fb\u0001\u0000\u0000\u0000\u00fb\u00fc"+
		"\u0001\u0000\u0000\u0000\u00fc\u0100\u0005\u0005\u0000\u0000\u00fd\u00ff"+
		"\u0005\r\u0000\u0000\u00fe\u00fd\u0001\u0000\u0000\u0000\u00ff\u0102\u0001"+
		"\u0000\u0000\u0000\u0100\u00fe\u0001\u0000\u0000\u0000\u0100\u0101\u0001"+
		"\u0000\u0000\u0000\u0101\u0019\u0001\u0000\u0000\u0000\u0102\u0100\u0001"+
		"\u0000\u0000\u0000\u0103\u0104\u0005#\u0000\u0000\u0104\u0112\u0005\u0004"+
		"\u0000\u0000\u0105\u0107\u0005\r\u0000\u0000\u0106\u0105\u0001\u0000\u0000"+
		"\u0000\u0107\u010a\u0001\u0000\u0000\u0000\u0108\u0106\u0001\u0000\u0000"+
		"\u0000\u0108\u0109\u0001\u0000\u0000\u0000\u0109\u010b\u0001\u0000\u0000"+
		"\u0000\u010a\u0108\u0001\u0000\u0000\u0000\u010b\u010f\u0003\u001c\u000e"+
		"\u0000\u010c\u010e\u0003>\u001f\u0000\u010d\u010c\u0001\u0000\u0000\u0000"+
		"\u010e\u0111\u0001\u0000\u0000\u0000\u010f\u010d\u0001\u0000\u0000\u0000"+
		"\u010f\u0110\u0001\u0000\u0000\u0000\u0110\u0113\u0001\u0000\u0000\u0000"+
		"\u0111\u010f\u0001\u0000\u0000\u0000\u0112\u0108\u0001\u0000\u0000\u0000"+
		"\u0113\u0114\u0001\u0000\u0000\u0000\u0114\u0112\u0001\u0000\u0000\u0000"+
		"\u0114\u0115\u0001\u0000\u0000\u0000\u0115\u0116\u0001\u0000\u0000\u0000"+
		"\u0116\u011a\u0005\u0005\u0000\u0000\u0117\u0119\u0005\r\u0000\u0000\u0118"+
		"\u0117\u0001\u0000\u0000\u0000\u0119\u011c\u0001\u0000\u0000\u0000\u011a"+
		"\u0118\u0001\u0000\u0000\u0000\u011a\u011b\u0001\u0000\u0000\u0000\u011b"+
		"\u001b\u0001\u0000\u0000\u0000\u011c\u011a\u0001\u0000\u0000\u0000\u011d"+
		"\u011e\u0005(\u0000\u0000\u011e\u001d\u0001\u0000\u0000\u0000\u011f\u0120"+
		"\u0003 \u0010\u0000\u0120\u0122\u0005\u0001\u0000\u0000\u0121\u0123\u0005"+
		"\r\u0000\u0000\u0122\u0121\u0001\u0000\u0000\u0000\u0122\u0123\u0001\u0000"+
		"\u0000\u0000\u0123\u0124\u0001\u0000\u0000\u0000\u0124\u0128\u0003$\u0012"+
		"\u0000\u0125\u0127\u0005\r\u0000\u0000\u0126\u0125\u0001\u0000\u0000\u0000"+
		"\u0127\u012a\u0001\u0000\u0000\u0000\u0128\u0126\u0001\u0000\u0000\u0000"+
		"\u0128\u0129\u0001\u0000\u0000\u0000\u0129\u001f\u0001\u0000\u0000\u0000"+
		"\u012a\u0128\u0001\u0000\u0000\u0000\u012b\u012d\u0003\"\u0011\u0000\u012c"+
		"\u012e\u0003\"\u0011\u0000\u012d\u012c\u0001\u0000\u0000\u0000\u012e\u012f"+
		"\u0001\u0000\u0000\u0000\u012f\u012d\u0001\u0000\u0000\u0000\u012f\u0130"+
		"\u0001\u0000\u0000\u0000\u0130!\u0001\u0000\u0000\u0000\u0131\u013e\u0005"+
		")\u0000\u0000\u0132\u013e\u0005(\u0000\u0000\u0133\u0134\u0005\u0002\u0000"+
		"\u0000\u0134\u0135\u0005(\u0000\u0000\u0135\u013e\u0005\u0003\u0000\u0000"+
		"\u0136\u013e\u0005&\u0000\u0000\u0137\u0138\u0005\u0002\u0000\u0000\u0138"+
		"\u0139\u0005&\u0000\u0000\u0139\u013e\u0005\u0003\u0000\u0000\u013a\u013b"+
		"\u0005\u0004\u0000\u0000\u013b\u013c\u0005(\u0000\u0000\u013c\u013e\u0005"+
		"\u0005\u0000\u0000\u013d\u0131\u0001\u0000\u0000\u0000\u013d\u0132\u0001"+
		"\u0000\u0000\u0000\u013d\u0133\u0001\u0000\u0000\u0000\u013d\u0136\u0001"+
		"\u0000\u0000\u0000\u013d\u0137\u0001\u0000\u0000\u0000\u013d\u013a\u0001"+
		"\u0000\u0000\u0000\u013e#\u0001\u0000\u0000\u0000\u013f\u0143\u0005\u0004"+
		"\u0000\u0000\u0140\u0142\u0003&\u0013\u0000\u0141\u0140\u0001\u0000\u0000"+
		"\u0000\u0142\u0145\u0001\u0000\u0000\u0000\u0143\u0141\u0001\u0000\u0000"+
		"\u0000\u0143\u0144\u0001\u0000\u0000\u0000\u0144\u0146\u0001\u0000\u0000"+
		"\u0000\u0145\u0143\u0001\u0000\u0000\u0000\u0146\u0147\u0005\u0005\u0000"+
		"\u0000\u0147%\u0001\u0000\u0000\u0000\u0148\u0153\u0003*\u0015\u0000\u0149"+
		"\u0153\u0003,\u0016\u0000\u014a\u0153\u0003.\u0017\u0000\u014b\u0153\u0003"+
		"(\u0014\u0000\u014c\u0153\u0005\u001e\u0000\u0000\u014d\u014f\u0005\u001d"+
		"\u0000\u0000\u014e\u0150\u0003.\u0017\u0000\u014f\u014e\u0001\u0000\u0000"+
		"\u0000\u014f\u0150\u0001\u0000\u0000\u0000\u0150\u0153\u0001\u0000\u0000"+
		"\u0000\u0151\u0153\u0003\u0004\u0002\u0000\u0152\u0148\u0001\u0000\u0000"+
		"\u0000\u0152\u0149\u0001\u0000\u0000\u0000\u0152\u014a\u0001\u0000\u0000"+
		"\u0000\u0152\u014b\u0001\u0000\u0000\u0000\u0152\u014c\u0001\u0000\u0000"+
		"\u0000\u0152\u014d\u0001\u0000\u0000\u0000\u0152\u0151\u0001\u0000\u0000"+
		"\u0000\u0153\'\u0001\u0000\u0000\u0000\u0154\u0155\u0007\u0000\u0000\u0000"+
		"\u0155)\u0001\u0000\u0000\u0000\u0156\u0157\u0005(\u0000\u0000\u0157\u0158"+
		"\u0005\u0001\u0000\u0000\u0158\u0163\u0003.\u0017\u0000\u0159\u015b\u0005"+
		"(\u0000\u0000\u015a\u015c\u00036\u001b\u0000\u015b\u015a\u0001\u0000\u0000"+
		"\u0000\u015c\u015d\u0001\u0000\u0000\u0000\u015d\u015b\u0001\u0000\u0000"+
		"\u0000\u015d\u015e\u0001\u0000\u0000\u0000\u015e\u015f\u0001\u0000\u0000"+
		"\u0000\u015f\u0160\u0005\u0001\u0000\u0000\u0160\u0161\u0003.\u0017\u0000"+
		"\u0161\u0163\u0001\u0000\u0000\u0000\u0162\u0156\u0001\u0000\u0000\u0000"+
		"\u0162\u0159\u0001\u0000\u0000\u0000\u0163+\u0001\u0000\u0000\u0000\u0164"+
		"\u0169\u0003.\u0017\u0000\u0165\u0166\u0005\b\u0000\u0000\u0166\u0168"+
		"\u0003.\u0017\u0000\u0167\u0165\u0001\u0000\u0000\u0000\u0168\u016b\u0001"+
		"\u0000\u0000\u0000\u0169\u0167\u0001\u0000\u0000\u0000\u0169\u016a\u0001"+
		"\u0000\u0000\u0000\u016a\u016d\u0001\u0000\u0000\u0000\u016b\u0169\u0001"+
		"\u0000\u0000\u0000\u016c\u0164\u0001\u0000\u0000\u0000\u016c\u016d\u0001"+
		"\u0000\u0000\u0000\u016d\u016e\u0001\u0000\u0000\u0000\u016e\u016f\u0007"+
		"\u0001\u0000\u0000\u016f-\u0001\u0000\u0000\u0000\u0170\u0171\u0006\u0017"+
		"\uffff\uffff\u0000\u0171\u0172\u0005\u001b\u0000\u0000\u0172\u0193\u0003"+
		".\u0017\u001a\u0173\u0193\u00034\u001a\u0000\u0174\u0193\u00038\u001c"+
		"\u0000\u0175\u0193\u0005(\u0000\u0000\u0176\u0193\u0005\u0011\u0000\u0000"+
		"\u0177\u0193\u0005*\u0000\u0000\u0178\u0193\u0005\u001c\u0000\u0000\u0179"+
		"\u0193\u0005+\u0000\u0000\u017a\u0193\u0003$\u0012\u0000\u017b\u0193\u0003"+
		"<\u001e\u0000\u017c\u0193\u0003B!\u0000\u017d\u0193\u0003\u0002\u0001"+
		"\u0000\u017e\u0193\u0003\u000e\u0007\u0000\u017f\u0193\u0003\u0010\b\u0000"+
		"\u0180\u0193\u00030\u0018\u0000\u0181\u0193\u00032\u0019\u0000\u0182\u0186"+
		"\u0005\u0002\u0000\u0000\u0183\u0185\u0005\r\u0000\u0000\u0184\u0183\u0001"+
		"\u0000\u0000\u0000\u0185\u0188\u0001\u0000\u0000\u0000\u0186\u0184\u0001"+
		"\u0000\u0000\u0000\u0186\u0187\u0001\u0000\u0000\u0000\u0187\u0189\u0001"+
		"\u0000\u0000\u0000\u0188\u0186\u0001\u0000\u0000\u0000\u0189\u018d\u0003"+
		".\u0017\u0000\u018a\u018c\u0005\r\u0000\u0000\u018b\u018a\u0001\u0000"+
		"\u0000\u0000\u018c\u018f\u0001\u0000\u0000\u0000\u018d\u018b\u0001\u0000"+
		"\u0000\u0000\u018d\u018e\u0001\u0000\u0000\u0000\u018e\u0190\u0001\u0000"+
		"\u0000\u0000\u018f\u018d\u0001\u0000\u0000\u0000\u0190\u0191\u0005\u0003"+
		"\u0000\u0000\u0191\u0193\u0001\u0000\u0000\u0000\u0192\u0170\u0001\u0000"+
		"\u0000\u0000\u0192\u0173\u0001\u0000\u0000\u0000\u0192\u0174\u0001\u0000"+
		"\u0000\u0000\u0192\u0175\u0001\u0000\u0000\u0000\u0192\u0176\u0001\u0000"+
		"\u0000\u0000\u0192\u0177\u0001\u0000\u0000\u0000\u0192\u0178\u0001\u0000"+
		"\u0000\u0000\u0192\u0179\u0001\u0000\u0000\u0000\u0192\u017a\u0001\u0000"+
		"\u0000\u0000\u0192\u017b\u0001\u0000\u0000\u0000\u0192\u017c\u0001\u0000"+
		"\u0000\u0000\u0192\u017d\u0001\u0000\u0000\u0000\u0192\u017e\u0001\u0000"+
		"\u0000\u0000\u0192\u017f\u0001\u0000\u0000\u0000\u0192\u0180\u0001\u0000"+
		"\u0000\u0000\u0192\u0181\u0001\u0000\u0000\u0000\u0192\u0182\u0001\u0000"+
		"\u0000\u0000\u0193\u01b1\u0001\u0000\u0000\u0000\u0194\u0195\n\u0019\u0000"+
		"\u0000\u0195\u0196\u0005\u0013\u0000\u0000\u0196\u01b0\u0003.\u0017\u001a"+
		"\u0197\u0198\n\u0018\u0000\u0000\u0198\u0199\u0005\u0012\u0000\u0000\u0199"+
		"\u01b0\u0003.\u0017\u0019\u019a\u019b\n\u0017\u0000\u0000\u019b\u019c"+
		"\u0005\u0014\u0000\u0000\u019c\u01b0\u0003.\u0017\u0018\u019d\u019e\n"+
		"\u0016\u0000\u0000\u019e\u019f\u0005\u0015\u0000\u0000\u019f\u01b0\u0003"+
		".\u0017\u0017\u01a0\u01a1\n\u0015\u0000\u0000\u01a1\u01a2\u0005\u0017"+
		"\u0000\u0000\u01a2\u01b0\u0003.\u0017\u0016\u01a3\u01a4\n\u0014\u0000"+
		"\u0000\u01a4\u01a5\u0005\u0016\u0000\u0000\u01a5\u01b0\u0003.\u0017\u0015"+
		"\u01a6\u01a7\n\u0013\u0000\u0000\u01a7\u01a8\u0005\u0018\u0000\u0000\u01a8"+
		"\u01b0\u0003.\u0017\u0014\u01a9\u01aa\n\u0012\u0000\u0000\u01aa\u01ab"+
		"\u0005\u0019\u0000\u0000\u01ab\u01b0\u0003.\u0017\u0013\u01ac\u01ad\n"+
		"\u0011\u0000\u0000\u01ad\u01ae\u0005\u001a\u0000\u0000\u01ae\u01b0\u0003"+
		".\u0017\u0012\u01af\u0194\u0001\u0000\u0000\u0000\u01af\u0197\u0001\u0000"+
		"\u0000\u0000\u01af\u019a\u0001\u0000\u0000\u0000\u01af\u019d\u0001\u0000"+
		"\u0000\u0000\u01af\u01a0\u0001\u0000\u0000\u0000\u01af\u01a3\u0001\u0000"+
		"\u0000\u0000\u01af\u01a6\u0001\u0000\u0000\u0000\u01af\u01a9\u0001\u0000"+
		"\u0000\u0000\u01af\u01ac\u0001\u0000\u0000\u0000\u01b0\u01b3\u0001\u0000"+
		"\u0000\u0000\u01b1\u01af\u0001\u0000\u0000\u0000\u01b1\u01b2\u0001\u0000"+
		"\u0000\u0000\u01b2/\u0001\u0000\u0000\u0000\u01b3\u01b1\u0001\u0000\u0000"+
		"\u0000\u01b4\u01b7\u0005(\u0000\u0000\u01b5\u01b7\u00034\u001a\u0000\u01b6"+
		"\u01b4\u0001\u0000\u0000\u0000\u01b6\u01b5\u0001\u0000\u0000\u0000\u01b7"+
		"\u01b8\u0001\u0000\u0000\u0000\u01b8\u01b9\u0005\'\u0000\u0000\u01b9\u01ba"+
		"\u0005(\u0000\u0000\u01ba1\u0001\u0000\u0000\u0000\u01bb\u01be\u0005("+
		"\u0000\u0000\u01bc\u01be\u00034\u001a\u0000\u01bd\u01bb\u0001\u0000\u0000"+
		"\u0000\u01bd\u01bc\u0001\u0000\u0000\u0000\u01be\u01bf\u0001\u0000\u0000"+
		"\u0000\u01bf\u01c0\u0005\'\u0000\u0000\u01c0\u01c1\u0005(\u0000\u0000"+
		"\u01c1\u01c2\u0005\u0001\u0000\u0000\u01c2\u01c3\u0003.\u0017\u0000\u01c3"+
		"3\u0001\u0000\u0000\u0000\u01c4\u01c6\u0005(\u0000\u0000\u01c5\u01c7\u0003"+
		"6\u001b\u0000\u01c6\u01c5\u0001\u0000\u0000\u0000\u01c7\u01c8\u0001\u0000"+
		"\u0000\u0000\u01c8\u01c6\u0001\u0000\u0000\u0000\u01c8\u01c9\u0001\u0000"+
		"\u0000\u0000\u01c95\u0001\u0000\u0000\u0000\u01ca\u01cb\u0005\u0006\u0000"+
		"\u0000\u01cb\u01cc\u0003.\u0017\u0000\u01cc\u01cd\u0005\u0007\u0000\u0000"+
		"\u01cd\u01d3\u0001\u0000\u0000\u0000\u01ce\u01cf\u0005\u0006\u0000\u0000"+
		"\u01cf\u01d0\u0003H$\u0000\u01d0\u01d1\u0005\u0007\u0000\u0000\u01d1\u01d3"+
		"\u0001\u0000\u0000\u0000\u01d2\u01ca\u0001\u0000\u0000\u0000\u01d2\u01ce"+
		"\u0001\u0000\u0000\u0000\u01d37\u0001\u0000\u0000\u0000\u01d4\u01d6\u0003"+
		":\u001d\u0000\u01d5\u01d7\u0003:\u001d\u0000\u01d6\u01d5\u0001\u0000\u0000"+
		"\u0000\u01d7\u01d8\u0001\u0000\u0000\u0000\u01d8\u01d6\u0001\u0000\u0000"+
		"\u0000\u01d8\u01d9\u0001\u0000\u0000\u0000\u01d99\u0001\u0000\u0000\u0000"+
		"\u01da\u01f7\u0005)\u0000\u0000\u01db\u01f7\u0005(\u0000\u0000\u01dc\u01e0"+
		"\u0005\u0002\u0000\u0000\u01dd\u01df\u0005\r\u0000\u0000\u01de\u01dd\u0001"+
		"\u0000\u0000\u0000\u01df\u01e2\u0001\u0000\u0000\u0000\u01e0\u01de\u0001"+
		"\u0000\u0000\u0000\u01e0\u01e1\u0001\u0000\u0000\u0000\u01e1\u01e3\u0001"+
		"\u0000\u0000\u0000\u01e2\u01e0\u0001\u0000\u0000\u0000\u01e3\u01e7\u0003"+
		".\u0017\u0000\u01e4\u01e6\u0005\r\u0000\u0000\u01e5\u01e4\u0001\u0000"+
		"\u0000\u0000\u01e6\u01e9\u0001\u0000\u0000\u0000\u01e7\u01e5\u0001\u0000"+
		"\u0000\u0000\u01e7\u01e8\u0001\u0000\u0000\u0000\u01e8\u01ea\u0001\u0000"+
		"\u0000\u0000\u01e9\u01e7\u0001\u0000\u0000\u0000\u01ea\u01eb\u0005\u0003"+
		"\u0000\u0000\u01eb\u01f7\u0001\u0000\u0000\u0000\u01ec\u01f7\u0005&\u0000"+
		"\u0000\u01ed\u01ee\u0005\u0002\u0000\u0000\u01ee\u01ef\u0005&\u0000\u0000"+
		"\u01ef\u01f7\u0005\u0003\u0000\u0000\u01f0\u01f7\u0005\u0011\u0000\u0000"+
		"\u01f1\u01f7\u0005\u001c\u0000\u0000\u01f2\u01f7\u0005+\u0000\u0000\u01f3"+
		"\u01f7\u0003$\u0012\u0000\u01f4\u01f7\u0003<\u001e\u0000\u01f5\u01f7\u0003"+
		"B!\u0000\u01f6\u01da\u0001\u0000\u0000\u0000\u01f6\u01db\u0001\u0000\u0000"+
		"\u0000\u01f6\u01dc\u0001\u0000\u0000\u0000\u01f6\u01ec\u0001\u0000\u0000"+
		"\u0000\u01f6\u01ed\u0001\u0000\u0000\u0000\u01f6\u01f0\u0001\u0000\u0000"+
		"\u0000\u01f6\u01f1\u0001\u0000\u0000\u0000\u01f6\u01f2\u0001\u0000\u0000"+
		"\u0000\u01f6\u01f3\u0001\u0000\u0000\u0000\u01f6\u01f4\u0001\u0000\u0000"+
		"\u0000\u01f6\u01f5\u0001\u0000\u0000\u0000\u01f7;\u0001\u0000\u0000\u0000"+
		"\u01f8\u01f9\u0005\u0006\u0000\u0000\u01f9\u01fa\u0003@ \u0000\u01fa\u01fb"+
		"\u0005\u0007\u0000\u0000\u01fb=\u0001\u0000\u0000\u0000\u01fc\u01fd\u0007"+
		"\u0002\u0000\u0000\u01fd?\u0001\u0000\u0000\u0000\u01fe\u0203\u0003.\u0017"+
		"\u0000\u01ff\u0200\u0005\b\u0000\u0000\u0200\u0202\u0003.\u0017\u0000"+
		"\u0201\u01ff\u0001\u0000\u0000\u0000\u0202\u0205\u0001\u0000\u0000\u0000"+
		"\u0203\u0201\u0001\u0000\u0000\u0000\u0203\u0204\u0001\u0000\u0000\u0000"+
		"\u0204\u0207\u0001\u0000\u0000\u0000\u0205\u0203\u0001\u0000\u0000\u0000"+
		"\u0206\u01fe\u0001\u0000\u0000\u0000\u0206\u0207\u0001\u0000\u0000\u0000"+
		"\u0207\u0231\u0001\u0000\u0000\u0000\u0208\u020c\u0005\u0004\u0000\u0000"+
		"\u0209\u020b\u0005\r\u0000\u0000\u020a\u0209\u0001\u0000\u0000\u0000\u020b"+
		"\u020e\u0001\u0000\u0000\u0000\u020c\u020a\u0001\u0000\u0000\u0000\u020c"+
		"\u020d\u0001\u0000\u0000\u0000\u020d\u0220\u0001\u0000\u0000\u0000\u020e"+
		"\u020c\u0001\u0000\u0000\u0000\u020f\u021b\u0003.\u0017\u0000\u0210\u0214"+
		"\u0003>\u001f\u0000\u0211\u0213\u0005\r\u0000\u0000\u0212\u0211\u0001"+
		"\u0000\u0000\u0000\u0213\u0216\u0001\u0000\u0000\u0000\u0214\u0212\u0001"+
		"\u0000\u0000\u0000\u0214\u0215\u0001\u0000\u0000\u0000\u0215\u0217\u0001"+
		"\u0000\u0000\u0000\u0216\u0214\u0001\u0000\u0000\u0000\u0217\u0218\u0003"+
		".\u0017\u0000\u0218\u021a\u0001\u0000\u0000\u0000\u0219\u0210\u0001\u0000"+
		"\u0000\u0000\u021a\u021d\u0001\u0000\u0000\u0000\u021b\u0219\u0001\u0000"+
		"\u0000\u0000\u021b\u021c\u0001\u0000\u0000\u0000\u021c\u021f\u0001\u0000"+
		"\u0000\u0000\u021d\u021b\u0001\u0000\u0000\u0000\u021e\u020f\u0001\u0000"+
		"\u0000\u0000\u021f\u0222\u0001\u0000\u0000\u0000\u0220\u021e\u0001\u0000"+
		"\u0000\u0000\u0220\u0221\u0001\u0000\u0000\u0000\u0221\u0223\u0001\u0000"+
		"\u0000\u0000\u0222\u0220\u0001\u0000\u0000\u0000\u0223\u0227\u0005\u0005"+
		"\u0000\u0000\u0224\u0226\u0005\r\u0000\u0000\u0225\u0224\u0001\u0000\u0000"+
		"\u0000\u0226\u0229\u0001\u0000\u0000\u0000\u0227\u0225\u0001\u0000\u0000"+
		"\u0000\u0227\u0228\u0001\u0000\u0000\u0000\u0228\u0231\u0001\u0000\u0000"+
		"\u0000\u0229\u0227\u0001\u0000\u0000\u0000\u022a\u022c\u0005\r\u0000\u0000"+
		"\u022b\u022a\u0001\u0000\u0000\u0000\u022c\u022f\u0001\u0000\u0000\u0000"+
		"\u022d\u022b\u0001\u0000\u0000\u0000\u022d\u022e\u0001\u0000\u0000\u0000"+
		"\u022e\u0231\u0001\u0000\u0000\u0000\u022f\u022d\u0001\u0000\u0000\u0000"+
		"\u0230\u0206\u0001\u0000\u0000\u0000\u0230\u0208\u0001\u0000\u0000\u0000"+
		"\u0230\u022d\u0001\u0000\u0000\u0000\u0231A\u0001\u0000\u0000\u0000\u0232"+
		"\u0233\u0005\u0006\u0000\u0000\u0233\u0234\u0003D\"\u0000\u0234\u0235"+
		"\u0005\u0007\u0000\u0000\u0235C\u0001\u0000\u0000\u0000\u0236\u023b\u0003"+
		"F#\u0000\u0237\u0238\u0005\b\u0000\u0000\u0238\u023a\u0003F#\u0000\u0239"+
		"\u0237\u0001\u0000\u0000\u0000\u023a\u023d\u0001\u0000\u0000\u0000\u023b"+
		"\u0239\u0001\u0000\u0000\u0000\u023b\u023c\u0001\u0000\u0000\u0000\u023c"+
		"\u0259\u0001\u0000\u0000\u0000\u023d\u023b\u0001\u0000\u0000\u0000\u023e"+
		"\u0242\u0005\u0004\u0000\u0000\u023f\u0241\u0005\r\u0000\u0000\u0240\u023f"+
		"\u0001\u0000\u0000\u0000\u0241\u0244\u0001\u0000\u0000\u0000\u0242\u0240"+
		"\u0001\u0000\u0000\u0000\u0242\u0243\u0001\u0000\u0000\u0000\u0243\u0245"+
		"\u0001\u0000\u0000\u0000\u0244\u0242\u0001\u0000\u0000\u0000\u0245\u0251"+
		"\u0003F#\u0000\u0246\u024a\u0003>\u001f\u0000\u0247\u0249\u0005\r\u0000"+
		"\u0000\u0248\u0247\u0001\u0000\u0000\u0000\u0249\u024c\u0001\u0000\u0000"+
		"\u0000\u024a\u0248\u0001\u0000\u0000\u0000\u024a\u024b\u0001\u0000\u0000"+
		"\u0000\u024b\u024d\u0001\u0000\u0000\u0000\u024c\u024a\u0001\u0000\u0000"+
		"\u0000\u024d\u024e\u0003F#\u0000\u024e\u0250\u0001\u0000\u0000\u0000\u024f"+
		"\u0246\u0001\u0000\u0000\u0000\u0250\u0253\u0001\u0000\u0000\u0000\u0251"+
		"\u024f\u0001\u0000\u0000\u0000\u0251\u0252\u0001\u0000\u0000\u0000\u0252"+
		"\u0254\u0001\u0000\u0000\u0000\u0253\u0251\u0001\u0000\u0000\u0000\u0254"+
		"\u0256\u0005\u0005\u0000\u0000\u0255\u0257\u0005\r\u0000\u0000\u0256\u0255"+
		"\u0001\u0000\u0000\u0000\u0256\u0257\u0001\u0000\u0000\u0000\u0257\u0259"+
		"\u0001\u0000\u0000\u0000\u0258\u0236\u0001\u0000\u0000\u0000\u0258\u023e"+
		"\u0001\u0000\u0000\u0000\u0259E\u0001\u0000\u0000\u0000\u025a\u025b\u0003"+
		"H$\u0000\u025b\u025c\u0005\u0001\u0000\u0000\u025c\u025d\u0003J%\u0000"+
		"\u025dG\u0001\u0000\u0000\u0000\u025e\u025f\u0007\u0003\u0000\u0000\u025f"+
		"I\u0001\u0000\u0000\u0000\u0260\u0261\u0003.\u0017\u0000\u0261K\u0001"+
		"\u0000\u0000\u0000HNPW]ditz\u007f\u0082\u008c\u0093\u0098\u009f\u00ab"+
		"\u00b4\u00b8\u00bb\u00be\u00c3\u00cc\u00d4\u00db\u00e0\u00e6\u00ee\u00f5"+
		"\u00fa\u0100\u0108\u010f\u0114\u011a\u0122\u0128\u012f\u013d\u0143\u014f"+
		"\u0152\u015d\u0162\u0169\u016c\u0186\u018d\u0192\u01af\u01b1\u01b6\u01bd"+
		"\u01c8\u01d2\u01d8\u01e0\u01e7\u01f6\u0203\u0206\u020c\u0214\u021b\u0220"+
		"\u0227\u022d\u0230\u023b\u0242\u024a\u0251\u0256\u0258";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}