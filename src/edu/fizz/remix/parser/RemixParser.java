// Generated from /Users/rshe005/Library/CloudStorage/OneDrive-TheUniversityofAuckland/MyDrive/IdeaProjects/remix/src/RemixParser.g4 by ANTLR 4.12.0
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
	static { RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		COLON=1, LPAREN=2, RPAREN=3, LBLOCK=4, RBLOCK=5, LBRACE=6, RBRACE=7, COMMA=8, 
		SPACE=9, CONT=10, EOL=11, EOS=12, DOC_COMMENT=13, COMMENT=14, NUMBER=15, 
		ADD=16, SUB=17, MUL=18, DIV=19, MOD=20, LESS=21, GREATER=22, LESSEQUAL=23, 
		GREATEREQUAL=24, EQUAL=25, NOTEQUAL=26, CONCAT=27, BOOLEAN=28, RETURN=29, 
		REDO=30, CREATE=31, GETTERSETTER=32, GETTER=33, SETTER=34, SELFREF=35, 
		POSSESSIVE=36, WORD=37, STRING=38;
	public static final int
		RULE_program = 0, RULE_functionDefinition = 1, RULE_functionComment = 2, 
		RULE_functionSignature = 3, RULE_sigPart = 4, RULE_createObject = 5, RULE_object = 6, 
		RULE_field = 7, RULE_getterSetter = 8, RULE_getter = 9, RULE_setter = 10, 
		RULE_fieldId = 11, RULE_methodDefinition = 12, RULE_methodSignature = 13, 
		RULE_methodSigPart = 14, RULE_blockOfStatements = 15, RULE_statement = 16, 
		RULE_endOfStatement = 17, RULE_assignmentStatement = 18, RULE_expression = 19, 
		RULE_getterMethodCall = 20, RULE_setterMethodCall = 21, RULE_listElement = 22, 
		RULE_listPart = 23, RULE_functionCall = 24, RULE_callPart = 25, RULE_list = 26, 
		RULE_separator = 27, RULE_listContents = 28, RULE_map = 29, RULE_mapContents = 30, 
		RULE_keyValue = 31, RULE_key = 32, RULE_value = 33;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "functionDefinition", "functionComment", "functionSignature", 
			"sigPart", "createObject", "object", "field", "getterSetter", "getter", 
			"setter", "fieldId", "methodDefinition", "methodSignature", "methodSigPart", 
			"blockOfStatements", "statement", "endOfStatement", "assignmentStatement", 
			"expression", "getterMethodCall", "setterMethodCall", "listElement", 
			"listPart", "functionCall", "callPart", "list", "separator", "listContents", 
			"map", "mapContents", "keyValue", "key", "value"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "':'", "'('", "')'", "'['", "']'", "'{'", "'}'", "','", null, null, 
			"'\\n'", "'.'", null, null, null, "' + '", "' - '", null, null, "' % '", 
			"' < '", "' > '", null, null, "' = '", null, null, null, "'return'", 
			"'redo'", "'create'", null, null, null, null, "''s'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "COLON", "LPAREN", "RPAREN", "LBLOCK", "RBLOCK", "LBRACE", "RBRACE", 
			"COMMA", "SPACE", "CONT", "EOL", "EOS", "DOC_COMMENT", "COMMENT", "NUMBER", 
			"ADD", "SUB", "MUL", "DIV", "MOD", "LESS", "GREATER", "LESSEQUAL", "GREATEREQUAL", 
			"EQUAL", "NOTEQUAL", "CONCAT", "BOOLEAN", "RETURN", "REDO", "CREATE", 
			"GETTERSETTER", "GETTER", "SETTER", "SELFREF", "POSSESSIVE", "WORD", 
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
			setState(72);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 416343439444L) != 0)) {
				{
				setState(70);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(68);
					functionDefinition();
					}
					break;
				case 2:
					{
					setState(69);
					statement();
					}
					break;
				}
				}
				setState(74);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(75);
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
		enterRule(_localctx, 2, RULE_functionDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(78);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DOC_COMMENT) {
				{
				setState(77);
				functionComment();
				}
			}

			setState(80);
			functionSignature();
			setState(81);
			match(COLON);
			setState(83);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(82);
				match(COLON);
				}
			}

			setState(86);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EOL) {
				{
				setState(85);
				match(EOL);
				}
			}

			setState(88);
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
		enterRule(_localctx, 4, RULE_functionComment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90);
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
		enterRule(_localctx, 6, RULE_functionSignature);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			sigPart();
			setState(94); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(93);
				sigPart();
				}
				}
				setState(96); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 137438953492L) != 0) );
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
		public TerminalNode LPAREN() { return getToken(RemixParser.LPAREN, 0); }
		public TerminalNode WORD() { return getToken(RemixParser.WORD, 0); }
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
		public TerminalNode WORD() { return getToken(RemixParser.WORD, 0); }
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
		enterRule(_localctx, 8, RULE_sigPart);
		try {
			setState(105);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case WORD:
				_localctx = new SigWordContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(98);
				match(WORD);
				}
				break;
			case LPAREN:
				_localctx = new SigParamContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(99);
				match(LPAREN);
				setState(100);
				match(WORD);
				setState(101);
				match(RPAREN);
				}
				break;
			case LBLOCK:
				_localctx = new SigBlockContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(102);
				match(LBLOCK);
				setState(103);
				match(WORD);
				setState(104);
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
		enterRule(_localctx, 10, RULE_createObject);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107);
			match(CREATE);
			setState(108);
			match(LBLOCK);
			setState(112);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==EOL) {
				{
				{
				setState(109);
				match(EOL);
				}
				}
				setState(114);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(115);
			object();
			setState(116);
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
		enterRule(_localctx, 12, RULE_object);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(121);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(118);
					field();
					}
					} 
				}
				setState(123);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			}
			{
			setState(125);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==GETTERSETTER) {
				{
				setState(124);
				getterSetter();
				}
			}

			setState(128);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==GETTER) {
				{
				setState(127);
				getter();
				}
			}

			setState(131);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SETTER) {
				{
				setState(130);
				setter();
				}
			}

			}
			setState(136);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 137438953492L) != 0)) {
				{
				{
				setState(133);
				methodDefinition();
				}
				}
				setState(138);
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
		public TerminalNode WORD() { return getToken(RemixParser.WORD, 0); }
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
		enterRule(_localctx, 14, RULE_field);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(139);
			match(WORD);
			setState(140);
			match(COLON);
			setState(141);
			expression(0);
			setState(145);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==EOL) {
				{
				{
				setState(142);
				match(EOL);
				}
				}
				setState(147);
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
		enterRule(_localctx, 16, RULE_getterSetter);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(148);
			match(GETTERSETTER);
			setState(149);
			match(LBLOCK);
			setState(163); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(153);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==EOL) {
					{
					{
					setState(150);
					match(EOL);
					}
					}
					setState(155);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(156);
				fieldId();
				setState(160);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(157);
						separator();
						}
						} 
					}
					setState(162);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
				}
				}
				}
				setState(165); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==EOL || _la==WORD );
			setState(167);
			match(RBLOCK);
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
		enterRule(_localctx, 18, RULE_getter);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(174);
			match(GETTER);
			setState(175);
			match(LBLOCK);
			setState(189); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(179);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==EOL) {
					{
					{
					setState(176);
					match(EOL);
					}
					}
					setState(181);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(182);
				fieldId();
				setState(186);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(183);
						separator();
						}
						} 
					}
					setState(188);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
				}
				}
				}
				setState(191); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==EOL || _la==WORD );
			setState(193);
			match(RBLOCK);
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
		enterRule(_localctx, 20, RULE_setter);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(200);
			match(SETTER);
			setState(201);
			match(LBLOCK);
			setState(215); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(205);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==EOL) {
					{
					{
					setState(202);
					match(EOL);
					}
					}
					setState(207);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(208);
				fieldId();
				setState(212);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(209);
						separator();
						}
						} 
					}
					setState(214);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
				}
				}
				}
				setState(217); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==EOL || _la==WORD );
			setState(219);
			match(RBLOCK);
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
	public static class FieldIdContext extends ParserRuleContext {
		public TerminalNode WORD() { return getToken(RemixParser.WORD, 0); }
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
		enterRule(_localctx, 22, RULE_fieldId);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(226);
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
		enterRule(_localctx, 24, RULE_methodDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(228);
			methodSignature();
			setState(229);
			match(COLON);
			setState(231);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EOL) {
				{
				setState(230);
				match(EOL);
				}
			}

			setState(233);
			blockOfStatements();
			setState(237);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==EOL) {
				{
				{
				setState(234);
				match(EOL);
				}
				}
				setState(239);
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
		enterRule(_localctx, 26, RULE_methodSignature);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(240);
			methodSigPart();
			setState(242); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(241);
				methodSigPart();
				}
				}
				setState(244); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 137438953492L) != 0) );
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
		public TerminalNode LPAREN() { return getToken(RemixParser.LPAREN, 0); }
		public TerminalNode WORD() { return getToken(RemixParser.WORD, 0); }
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
		public TerminalNode WORD() { return getToken(RemixParser.WORD, 0); }
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
		public TerminalNode LPAREN() { return getToken(RemixParser.LPAREN, 0); }
		public TerminalNode SELFREF() { return getToken(RemixParser.SELFREF, 0); }
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
		enterRule(_localctx, 28, RULE_methodSigPart);
		try {
			setState(256);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				_localctx = new MethSigWordContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(246);
				match(WORD);
				}
				break;
			case 2:
				_localctx = new MethSigParamContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(247);
				match(LPAREN);
				setState(248);
				match(WORD);
				setState(249);
				match(RPAREN);
				}
				break;
			case 3:
				_localctx = new MethSigSelfContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(250);
				match(LPAREN);
				setState(251);
				match(SELFREF);
				setState(252);
				match(RPAREN);
				}
				break;
			case 4:
				_localctx = new MethSigBlockContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(253);
				match(LBLOCK);
				setState(254);
				match(WORD);
				setState(255);
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
		enterRule(_localctx, 30, RULE_blockOfStatements);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(258);
			match(LBLOCK);
			setState(262);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 416343431252L) != 0)) {
				{
				{
				setState(259);
				statement();
				}
				}
				setState(264);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(265);
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
		enterRule(_localctx, 32, RULE_statement);
		try {
			setState(275);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
			case 1:
				_localctx = new AssStatementContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(267);
				assignmentStatement();
				}
				break;
			case 2:
				_localctx = new ExprContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(268);
				expression(0);
				}
				break;
			case 3:
				_localctx = new BlankContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(269);
				endOfStatement();
				}
				break;
			case 4:
				_localctx = new RedoContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(270);
				match(REDO);
				}
				break;
			case 5:
				_localctx = new ReturnContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(271);
				match(RETURN);
				setState(273);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
				case 1:
					{
					setState(272);
					expression(0);
					}
					break;
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
		enterRule(_localctx, 34, RULE_endOfStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(277);
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
		public TerminalNode WORD() { return getToken(RemixParser.WORD, 0); }
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
		public TerminalNode WORD() { return getToken(RemixParser.WORD, 0); }
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
		enterRule(_localctx, 36, RULE_assignmentStatement);
		int _la;
		try {
			setState(291);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
			case 1:
				_localctx = new SetVariableContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(279);
				match(WORD);
				setState(280);
				match(COLON);
				setState(281);
				expression(0);
				}
				break;
			case 2:
				_localctx = new SetListElementContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(282);
				match(WORD);
				setState(284); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(283);
					listPart();
					}
					}
					setState(286); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==LBRACE );
				setState(288);
				match(COLON);
				setState(289);
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
	public static class ExprVarContext extends ExpressionContext {
		public TerminalNode WORD() { return getToken(RemixParser.WORD, 0); }
		public ExprVarContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitExprVar(this);
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
	public static class ExprDivContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode DIV() { return getToken(RemixParser.DIV, 0); }
		public ExprDivContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitExprDiv(this);
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
	public static class ExprSubContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode SUB() { return getToken(RemixParser.SUB, 0); }
		public ExprSubContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitExprSub(this);
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
	public static class ExprModContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode MOD() { return getToken(RemixParser.MOD, 0); }
		public ExprModContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitExprMod(this);
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
		int _startState = 38;
		enterRecursionRule(_localctx, 38, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(322);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
			case 1:
				{
				_localctx = new ExprListElementContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(294);
				listElement();
				}
				break;
			case 2:
				{
				_localctx = new ExprFncCallContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(295);
				functionCall();
				}
				break;
			case 3:
				{
				_localctx = new ExprVarContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(296);
				match(WORD);
				}
				break;
			case 4:
				{
				_localctx = new ExprNumberContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(297);
				match(NUMBER);
				}
				break;
			case 5:
				{
				_localctx = new ExprBooleanContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(298);
				match(BOOLEAN);
				}
				break;
			case 6:
				{
				_localctx = new ExprStringContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(299);
				match(STRING);
				}
				break;
			case 7:
				{
				_localctx = new ExprBlockContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(300);
				blockOfStatements();
				}
				break;
			case 8:
				{
				_localctx = new ExprListContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(301);
				list();
				}
				break;
			case 9:
				{
				_localctx = new ExprMapContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(302);
				map();
				}
				break;
			case 10:
				{
				_localctx = new ExprObjectContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(303);
				createObject();
				}
				break;
			case 11:
				{
				_localctx = new ExprGetterMethodContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(304);
				getterMethodCall();
				}
				break;
			case 12:
				{
				_localctx = new ExprSetterMethodContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(305);
				setterMethodCall();
				}
				break;
			case 13:
				{
				_localctx = new ExprParenContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(306);
				match(LPAREN);
				setState(310);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==EOL) {
					{
					{
					setState(307);
					match(EOL);
					}
					}
					setState(312);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(313);
				expression(0);
				setState(317);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==EOL) {
					{
					{
					setState(314);
					match(EOL);
					}
					}
					setState(319);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(320);
				match(RPAREN);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(362);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,39,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(360);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
					case 1:
						{
						_localctx = new ExprAddContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(324);
						if (!(precpred(_ctx, 25))) throw new FailedPredicateException(this, "precpred(_ctx, 25)");
						setState(325);
						match(ADD);
						setState(326);
						expression(26);
						}
						break;
					case 2:
						{
						_localctx = new ExprSubContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(327);
						if (!(precpred(_ctx, 24))) throw new FailedPredicateException(this, "precpred(_ctx, 24)");
						setState(328);
						match(SUB);
						setState(329);
						expression(25);
						}
						break;
					case 3:
						{
						_localctx = new ExprMulContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(330);
						if (!(precpred(_ctx, 23))) throw new FailedPredicateException(this, "precpred(_ctx, 23)");
						setState(331);
						match(MUL);
						setState(332);
						expression(24);
						}
						break;
					case 4:
						{
						_localctx = new ExprDivContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(333);
						if (!(precpred(_ctx, 22))) throw new FailedPredicateException(this, "precpred(_ctx, 22)");
						setState(334);
						match(DIV);
						setState(335);
						expression(23);
						}
						break;
					case 5:
						{
						_localctx = new ExprModContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(336);
						if (!(precpred(_ctx, 21))) throw new FailedPredicateException(this, "precpred(_ctx, 21)");
						setState(337);
						match(MOD);
						setState(338);
						expression(22);
						}
						break;
					case 6:
						{
						_localctx = new ExprLessContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(339);
						if (!(precpred(_ctx, 20))) throw new FailedPredicateException(this, "precpred(_ctx, 20)");
						setState(340);
						match(LESS);
						setState(341);
						expression(21);
						}
						break;
					case 7:
						{
						_localctx = new ExprGreaterContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(342);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(343);
						match(GREATER);
						setState(344);
						expression(20);
						}
						break;
					case 8:
						{
						_localctx = new ExprGreatEqlContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(345);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(346);
						match(GREATEREQUAL);
						setState(347);
						expression(19);
						}
						break;
					case 9:
						{
						_localctx = new ExprLessEqlContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(348);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(349);
						match(LESSEQUAL);
						setState(350);
						expression(18);
						}
						break;
					case 10:
						{
						_localctx = new ExprEqualContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(351);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(352);
						match(EQUAL);
						setState(353);
						expression(17);
						}
						break;
					case 11:
						{
						_localctx = new ExprNotEqlContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(354);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(355);
						match(NOTEQUAL);
						setState(356);
						expression(16);
						}
						break;
					case 12:
						{
						_localctx = new ExprConcatContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(357);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(358);
						match(CONCAT);
						setState(359);
						expression(15);
						}
						break;
					}
					} 
				}
				setState(364);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,39,_ctx);
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
		public List<TerminalNode> WORD() { return getTokens(RemixParser.WORD); }
		public TerminalNode WORD(int i) {
			return getToken(RemixParser.WORD, i);
		}
		public TerminalNode POSSESSIVE() { return getToken(RemixParser.POSSESSIVE, 0); }
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
		enterRule(_localctx, 40, RULE_getterMethodCall);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(365);
			match(WORD);
			setState(366);
			match(POSSESSIVE);
			setState(367);
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
	public static class SetterMethodCallContext extends ParserRuleContext {
		public List<TerminalNode> WORD() { return getTokens(RemixParser.WORD); }
		public TerminalNode WORD(int i) {
			return getToken(RemixParser.WORD, i);
		}
		public TerminalNode POSSESSIVE() { return getToken(RemixParser.POSSESSIVE, 0); }
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
		enterRule(_localctx, 42, RULE_setterMethodCall);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(369);
			match(WORD);
			setState(370);
			match(POSSESSIVE);
			setState(371);
			match(WORD);
			setState(372);
			match(COLON);
			setState(373);
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
		public TerminalNode WORD() { return getToken(RemixParser.WORD, 0); }
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
		enterRule(_localctx, 44, RULE_listElement);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(375);
			match(WORD);
			setState(377); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(376);
					listPart();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(379); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,40,_ctx);
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
		public TerminalNode LBRACE() { return getToken(RemixParser.LBRACE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RBRACE() { return getToken(RemixParser.RBRACE, 0); }
		public ListPartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listPart; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitListPart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListPartContext listPart() throws RecognitionException {
		ListPartContext _localctx = new ListPartContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_listPart);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(381);
			match(LBRACE);
			setState(382);
			expression(0);
			setState(383);
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
		enterRule(_localctx, 48, RULE_functionCall);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(385);
			callPart();
			setState(387); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(386);
					callPart();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(389); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,41,_ctx);
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
		public TerminalNode LPAREN() { return getToken(RemixParser.LPAREN, 0); }
		public TerminalNode SELFREF() { return getToken(RemixParser.SELFREF, 0); }
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
		enterRule(_localctx, 50, RULE_callPart);
		int _la;
		try {
			setState(417);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,44,_ctx) ) {
			case 1:
				_localctx = new CallWordContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(391);
				match(WORD);
				}
				break;
			case 2:
				_localctx = new CallParamContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(392);
				match(LPAREN);
				setState(396);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==EOL) {
					{
					{
					setState(393);
					match(EOL);
					}
					}
					setState(398);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(399);
				expression(0);
				setState(403);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==EOL) {
					{
					{
					setState(400);
					match(EOL);
					}
					}
					setState(405);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(406);
				match(RPAREN);
				}
				break;
			case 3:
				_localctx = new CallSelfContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(408);
				match(LPAREN);
				setState(409);
				match(SELFREF);
				setState(410);
				match(RPAREN);
				}
				break;
			case 4:
				_localctx = new CallNumberContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(411);
				match(NUMBER);
				}
				break;
			case 5:
				_localctx = new CallBooleanContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(412);
				match(BOOLEAN);
				}
				break;
			case 6:
				_localctx = new CallStringContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(413);
				match(STRING);
				}
				break;
			case 7:
				_localctx = new CallBlockContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(414);
				blockOfStatements();
				}
				break;
			case 8:
				_localctx = new CallListContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(415);
				list();
				}
				break;
			case 9:
				_localctx = new CallMapContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(416);
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
		enterRule(_localctx, 52, RULE_list);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(419);
			match(LBRACE);
			setState(420);
			listContents();
			setState(421);
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
		enterRule(_localctx, 54, RULE_separator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(423);
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
		enterRule(_localctx, 56, RULE_listContents);
		int _la;
		try {
			setState(472);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,53,_ctx) ) {
			case 1:
				_localctx = new CommaListContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(433);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 414732812372L) != 0)) {
					{
					setState(425);
					expression(0);
					setState(430);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(426);
						match(COMMA);
						setState(427);
						expression(0);
						}
						}
						setState(432);
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
				setState(435);
				match(LBLOCK);
				setState(439);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==EOL) {
					{
					{
					setState(436);
					match(EOL);
					}
					}
					setState(441);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(459);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 414732812372L) != 0)) {
					{
					{
					setState(442);
					expression(0);
					setState(454);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA || _la==EOL) {
						{
						{
						setState(443);
						separator();
						setState(447);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==EOL) {
							{
							{
							setState(444);
							match(EOL);
							}
							}
							setState(449);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(450);
						expression(0);
						}
						}
						setState(456);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
					}
					setState(461);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(462);
				match(RBLOCK);
				setState(464);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==EOL) {
					{
					setState(463);
					match(EOL);
					}
				}

				}
				break;
			case 3:
				_localctx = new EmptyListContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(469);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==EOL) {
					{
					{
					setState(466);
					match(EOL);
					}
					}
					setState(471);
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
		enterRule(_localctx, 58, RULE_map);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(474);
			match(LBRACE);
			setState(475);
			mapContents();
			setState(476);
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
		enterRule(_localctx, 60, RULE_mapContents);
		int _la;
		try {
			setState(512);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case WORD:
			case STRING:
				_localctx = new CommaMapContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(478);
				keyValue();
				setState(483);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(479);
					match(COMMA);
					setState(480);
					keyValue();
					}
					}
					setState(485);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case LBLOCK:
				_localctx = new BlockMapContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(486);
				match(LBLOCK);
				setState(490);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==EOL) {
					{
					{
					setState(487);
					match(EOL);
					}
					}
					setState(492);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(493);
				keyValue();
				setState(505);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA || _la==EOL) {
					{
					{
					setState(494);
					separator();
					setState(498);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==EOL) {
						{
						{
						setState(495);
						match(EOL);
						}
						}
						setState(500);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(501);
					keyValue();
					}
					}
					setState(507);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(508);
				match(RBLOCK);
				setState(510);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==EOL) {
					{
					setState(509);
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
		enterRule(_localctx, 62, RULE_keyValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(514);
			key();
			setState(515);
			match(COLON);
			setState(516);
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
		enterRule(_localctx, 64, RULE_key);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(518);
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
		enterRule(_localctx, 66, RULE_value);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(520);
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
		case 19:
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
		case 9:
			return precpred(_ctx, 16);
		case 10:
			return precpred(_ctx, 15);
		case 11:
			return precpred(_ctx, 14);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001&\u020b\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0001\u0000\u0001"+
		"\u0000\u0005\u0000G\b\u0000\n\u0000\f\u0000J\t\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0001\u0003\u0001O\b\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0003\u0001T\b\u0001\u0001\u0001\u0003\u0001W\b\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0004\u0003"+
		"_\b\u0003\u000b\u0003\f\u0003`\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004j\b\u0004\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0005\u0005o\b\u0005\n\u0005\f\u0005r\t"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0005\u0006x\b"+
		"\u0006\n\u0006\f\u0006{\t\u0006\u0001\u0006\u0003\u0006~\b\u0006\u0001"+
		"\u0006\u0003\u0006\u0081\b\u0006\u0001\u0006\u0003\u0006\u0084\b\u0006"+
		"\u0001\u0006\u0005\u0006\u0087\b\u0006\n\u0006\f\u0006\u008a\t\u0006\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0005\u0007\u0090\b\u0007\n"+
		"\u0007\f\u0007\u0093\t\u0007\u0001\b\u0001\b\u0001\b\u0005\b\u0098\b\b"+
		"\n\b\f\b\u009b\t\b\u0001\b\u0001\b\u0005\b\u009f\b\b\n\b\f\b\u00a2\t\b"+
		"\u0004\b\u00a4\b\b\u000b\b\f\b\u00a5\u0001\b\u0001\b\u0005\b\u00aa\b\b"+
		"\n\b\f\b\u00ad\t\b\u0001\t\u0001\t\u0001\t\u0005\t\u00b2\b\t\n\t\f\t\u00b5"+
		"\t\t\u0001\t\u0001\t\u0005\t\u00b9\b\t\n\t\f\t\u00bc\t\t\u0004\t\u00be"+
		"\b\t\u000b\t\f\t\u00bf\u0001\t\u0001\t\u0005\t\u00c4\b\t\n\t\f\t\u00c7"+
		"\t\t\u0001\n\u0001\n\u0001\n\u0005\n\u00cc\b\n\n\n\f\n\u00cf\t\n\u0001"+
		"\n\u0001\n\u0005\n\u00d3\b\n\n\n\f\n\u00d6\t\n\u0004\n\u00d8\b\n\u000b"+
		"\n\f\n\u00d9\u0001\n\u0001\n\u0005\n\u00de\b\n\n\n\f\n\u00e1\t\n\u0001"+
		"\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0003\f\u00e8\b\f\u0001\f\u0001"+
		"\f\u0005\f\u00ec\b\f\n\f\f\f\u00ef\t\f\u0001\r\u0001\r\u0004\r\u00f3\b"+
		"\r\u000b\r\f\r\u00f4\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0003"+
		"\u000e\u0101\b\u000e\u0001\u000f\u0001\u000f\u0005\u000f\u0105\b\u000f"+
		"\n\u000f\f\u000f\u0108\t\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0003\u0010\u0112"+
		"\b\u0010\u0003\u0010\u0114\b\u0010\u0001\u0011\u0001\u0011\u0001\u0012"+
		"\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0004\u0012\u011d\b\u0012"+
		"\u000b\u0012\f\u0012\u011e\u0001\u0012\u0001\u0012\u0001\u0012\u0003\u0012"+
		"\u0124\b\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0005\u0013\u0135\b\u0013"+
		"\n\u0013\f\u0013\u0138\t\u0013\u0001\u0013\u0001\u0013\u0005\u0013\u013c"+
		"\b\u0013\n\u0013\f\u0013\u013f\t\u0013\u0001\u0013\u0001\u0013\u0003\u0013"+
		"\u0143\b\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0005\u0013\u0169\b\u0013\n\u0013\f\u0013\u016c\t\u0013\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0004"+
		"\u0016\u017a\b\u0016\u000b\u0016\f\u0016\u017b\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0018\u0001\u0018\u0004\u0018\u0184\b\u0018"+
		"\u000b\u0018\f\u0018\u0185\u0001\u0019\u0001\u0019\u0001\u0019\u0005\u0019"+
		"\u018b\b\u0019\n\u0019\f\u0019\u018e\t\u0019\u0001\u0019\u0001\u0019\u0005"+
		"\u0019\u0192\b\u0019\n\u0019\f\u0019\u0195\t\u0019\u0001\u0019\u0001\u0019"+
		"\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019"+
		"\u0001\u0019\u0001\u0019\u0001\u0019\u0003\u0019\u01a2\b\u0019\u0001\u001a"+
		"\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001b\u0001\u001b\u0001\u001c"+
		"\u0001\u001c\u0001\u001c\u0005\u001c\u01ad\b\u001c\n\u001c\f\u001c\u01b0"+
		"\t\u001c\u0003\u001c\u01b2\b\u001c\u0001\u001c\u0001\u001c\u0005\u001c"+
		"\u01b6\b\u001c\n\u001c\f\u001c\u01b9\t\u001c\u0001\u001c\u0001\u001c\u0001"+
		"\u001c\u0005\u001c\u01be\b\u001c\n\u001c\f\u001c\u01c1\t\u001c\u0001\u001c"+
		"\u0001\u001c\u0005\u001c\u01c5\b\u001c\n\u001c\f\u001c\u01c8\t\u001c\u0005"+
		"\u001c\u01ca\b\u001c\n\u001c\f\u001c\u01cd\t\u001c\u0001\u001c\u0001\u001c"+
		"\u0003\u001c\u01d1\b\u001c\u0001\u001c\u0005\u001c\u01d4\b\u001c\n\u001c"+
		"\f\u001c\u01d7\t\u001c\u0003\u001c\u01d9\b\u001c\u0001\u001d\u0001\u001d"+
		"\u0001\u001d\u0001\u001d\u0001\u001e\u0001\u001e\u0001\u001e\u0005\u001e"+
		"\u01e2\b\u001e\n\u001e\f\u001e\u01e5\t\u001e\u0001\u001e\u0001\u001e\u0005"+
		"\u001e\u01e9\b\u001e\n\u001e\f\u001e\u01ec\t\u001e\u0001\u001e\u0001\u001e"+
		"\u0001\u001e\u0005\u001e\u01f1\b\u001e\n\u001e\f\u001e\u01f4\t\u001e\u0001"+
		"\u001e\u0001\u001e\u0005\u001e\u01f8\b\u001e\n\u001e\f\u001e\u01fb\t\u001e"+
		"\u0001\u001e\u0001\u001e\u0003\u001e\u01ff\b\u001e\u0003\u001e\u0201\b"+
		"\u001e\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001 \u0001 \u0001"+
		"!\u0001!\u0001!\u0000\u0001&\"\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010"+
		"\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.02468:<>@B\u0000\u0003"+
		"\u0001\u0000\u000b\f\u0002\u0000\b\b\u000b\u000b\u0001\u0000%&\u0247\u0000"+
		"H\u0001\u0000\u0000\u0000\u0002N\u0001\u0000\u0000\u0000\u0004Z\u0001"+
		"\u0000\u0000\u0000\u0006\\\u0001\u0000\u0000\u0000\bi\u0001\u0000\u0000"+
		"\u0000\nk\u0001\u0000\u0000\u0000\fy\u0001\u0000\u0000\u0000\u000e\u008b"+
		"\u0001\u0000\u0000\u0000\u0010\u0094\u0001\u0000\u0000\u0000\u0012\u00ae"+
		"\u0001\u0000\u0000\u0000\u0014\u00c8\u0001\u0000\u0000\u0000\u0016\u00e2"+
		"\u0001\u0000\u0000\u0000\u0018\u00e4\u0001\u0000\u0000\u0000\u001a\u00f0"+
		"\u0001\u0000\u0000\u0000\u001c\u0100\u0001\u0000\u0000\u0000\u001e\u0102"+
		"\u0001\u0000\u0000\u0000 \u0113\u0001\u0000\u0000\u0000\"\u0115\u0001"+
		"\u0000\u0000\u0000$\u0123\u0001\u0000\u0000\u0000&\u0142\u0001\u0000\u0000"+
		"\u0000(\u016d\u0001\u0000\u0000\u0000*\u0171\u0001\u0000\u0000\u0000,"+
		"\u0177\u0001\u0000\u0000\u0000.\u017d\u0001\u0000\u0000\u00000\u0181\u0001"+
		"\u0000\u0000\u00002\u01a1\u0001\u0000\u0000\u00004\u01a3\u0001\u0000\u0000"+
		"\u00006\u01a7\u0001\u0000\u0000\u00008\u01d8\u0001\u0000\u0000\u0000:"+
		"\u01da\u0001\u0000\u0000\u0000<\u0200\u0001\u0000\u0000\u0000>\u0202\u0001"+
		"\u0000\u0000\u0000@\u0206\u0001\u0000\u0000\u0000B\u0208\u0001\u0000\u0000"+
		"\u0000DG\u0003\u0002\u0001\u0000EG\u0003 \u0010\u0000FD\u0001\u0000\u0000"+
		"\u0000FE\u0001\u0000\u0000\u0000GJ\u0001\u0000\u0000\u0000HF\u0001\u0000"+
		"\u0000\u0000HI\u0001\u0000\u0000\u0000IK\u0001\u0000\u0000\u0000JH\u0001"+
		"\u0000\u0000\u0000KL\u0005\u0000\u0000\u0001L\u0001\u0001\u0000\u0000"+
		"\u0000MO\u0003\u0004\u0002\u0000NM\u0001\u0000\u0000\u0000NO\u0001\u0000"+
		"\u0000\u0000OP\u0001\u0000\u0000\u0000PQ\u0003\u0006\u0003\u0000QS\u0005"+
		"\u0001\u0000\u0000RT\u0005\u0001\u0000\u0000SR\u0001\u0000\u0000\u0000"+
		"ST\u0001\u0000\u0000\u0000TV\u0001\u0000\u0000\u0000UW\u0005\u000b\u0000"+
		"\u0000VU\u0001\u0000\u0000\u0000VW\u0001\u0000\u0000\u0000WX\u0001\u0000"+
		"\u0000\u0000XY\u0003\u001e\u000f\u0000Y\u0003\u0001\u0000\u0000\u0000"+
		"Z[\u0005\r\u0000\u0000[\u0005\u0001\u0000\u0000\u0000\\^\u0003\b\u0004"+
		"\u0000]_\u0003\b\u0004\u0000^]\u0001\u0000\u0000\u0000_`\u0001\u0000\u0000"+
		"\u0000`^\u0001\u0000\u0000\u0000`a\u0001\u0000\u0000\u0000a\u0007\u0001"+
		"\u0000\u0000\u0000bj\u0005%\u0000\u0000cd\u0005\u0002\u0000\u0000de\u0005"+
		"%\u0000\u0000ej\u0005\u0003\u0000\u0000fg\u0005\u0004\u0000\u0000gh\u0005"+
		"%\u0000\u0000hj\u0005\u0005\u0000\u0000ib\u0001\u0000\u0000\u0000ic\u0001"+
		"\u0000\u0000\u0000if\u0001\u0000\u0000\u0000j\t\u0001\u0000\u0000\u0000"+
		"kl\u0005\u001f\u0000\u0000lp\u0005\u0004\u0000\u0000mo\u0005\u000b\u0000"+
		"\u0000nm\u0001\u0000\u0000\u0000or\u0001\u0000\u0000\u0000pn\u0001\u0000"+
		"\u0000\u0000pq\u0001\u0000\u0000\u0000qs\u0001\u0000\u0000\u0000rp\u0001"+
		"\u0000\u0000\u0000st\u0003\f\u0006\u0000tu\u0005\u0005\u0000\u0000u\u000b"+
		"\u0001\u0000\u0000\u0000vx\u0003\u000e\u0007\u0000wv\u0001\u0000\u0000"+
		"\u0000x{\u0001\u0000\u0000\u0000yw\u0001\u0000\u0000\u0000yz\u0001\u0000"+
		"\u0000\u0000z}\u0001\u0000\u0000\u0000{y\u0001\u0000\u0000\u0000|~\u0003"+
		"\u0010\b\u0000}|\u0001\u0000\u0000\u0000}~\u0001\u0000\u0000\u0000~\u0080"+
		"\u0001\u0000\u0000\u0000\u007f\u0081\u0003\u0012\t\u0000\u0080\u007f\u0001"+
		"\u0000\u0000\u0000\u0080\u0081\u0001\u0000\u0000\u0000\u0081\u0083\u0001"+
		"\u0000\u0000\u0000\u0082\u0084\u0003\u0014\n\u0000\u0083\u0082\u0001\u0000"+
		"\u0000\u0000\u0083\u0084\u0001\u0000\u0000\u0000\u0084\u0088\u0001\u0000"+
		"\u0000\u0000\u0085\u0087\u0003\u0018\f\u0000\u0086\u0085\u0001\u0000\u0000"+
		"\u0000\u0087\u008a\u0001\u0000\u0000\u0000\u0088\u0086\u0001\u0000\u0000"+
		"\u0000\u0088\u0089\u0001\u0000\u0000\u0000\u0089\r\u0001\u0000\u0000\u0000"+
		"\u008a\u0088\u0001\u0000\u0000\u0000\u008b\u008c\u0005%\u0000\u0000\u008c"+
		"\u008d\u0005\u0001\u0000\u0000\u008d\u0091\u0003&\u0013\u0000\u008e\u0090"+
		"\u0005\u000b\u0000\u0000\u008f\u008e\u0001\u0000\u0000\u0000\u0090\u0093"+
		"\u0001\u0000\u0000\u0000\u0091\u008f\u0001\u0000\u0000\u0000\u0091\u0092"+
		"\u0001\u0000\u0000\u0000\u0092\u000f\u0001\u0000\u0000\u0000\u0093\u0091"+
		"\u0001\u0000\u0000\u0000\u0094\u0095\u0005 \u0000\u0000\u0095\u00a3\u0005"+
		"\u0004\u0000\u0000\u0096\u0098\u0005\u000b\u0000\u0000\u0097\u0096\u0001"+
		"\u0000\u0000\u0000\u0098\u009b\u0001\u0000\u0000\u0000\u0099\u0097\u0001"+
		"\u0000\u0000\u0000\u0099\u009a\u0001\u0000\u0000\u0000\u009a\u009c\u0001"+
		"\u0000\u0000\u0000\u009b\u0099\u0001\u0000\u0000\u0000\u009c\u00a0\u0003"+
		"\u0016\u000b\u0000\u009d\u009f\u00036\u001b\u0000\u009e\u009d\u0001\u0000"+
		"\u0000\u0000\u009f\u00a2\u0001\u0000\u0000\u0000\u00a0\u009e\u0001\u0000"+
		"\u0000\u0000\u00a0\u00a1\u0001\u0000\u0000\u0000\u00a1\u00a4\u0001\u0000"+
		"\u0000\u0000\u00a2\u00a0\u0001\u0000\u0000\u0000\u00a3\u0099\u0001\u0000"+
		"\u0000\u0000\u00a4\u00a5\u0001\u0000\u0000\u0000\u00a5\u00a3\u0001\u0000"+
		"\u0000\u0000\u00a5\u00a6\u0001\u0000\u0000\u0000\u00a6\u00a7\u0001\u0000"+
		"\u0000\u0000\u00a7\u00ab\u0005\u0005\u0000\u0000\u00a8\u00aa\u0005\u000b"+
		"\u0000\u0000\u00a9\u00a8\u0001\u0000\u0000\u0000\u00aa\u00ad\u0001\u0000"+
		"\u0000\u0000\u00ab\u00a9\u0001\u0000\u0000\u0000\u00ab\u00ac\u0001\u0000"+
		"\u0000\u0000\u00ac\u0011\u0001\u0000\u0000\u0000\u00ad\u00ab\u0001\u0000"+
		"\u0000\u0000\u00ae\u00af\u0005!\u0000\u0000\u00af\u00bd\u0005\u0004\u0000"+
		"\u0000\u00b0\u00b2\u0005\u000b\u0000\u0000\u00b1\u00b0\u0001\u0000\u0000"+
		"\u0000\u00b2\u00b5\u0001\u0000\u0000\u0000\u00b3\u00b1\u0001\u0000\u0000"+
		"\u0000\u00b3\u00b4\u0001\u0000\u0000\u0000\u00b4\u00b6\u0001\u0000\u0000"+
		"\u0000\u00b5\u00b3\u0001\u0000\u0000\u0000\u00b6\u00ba\u0003\u0016\u000b"+
		"\u0000\u00b7\u00b9\u00036\u001b\u0000\u00b8\u00b7\u0001\u0000\u0000\u0000"+
		"\u00b9\u00bc\u0001\u0000\u0000\u0000\u00ba\u00b8\u0001\u0000\u0000\u0000"+
		"\u00ba\u00bb\u0001\u0000\u0000\u0000\u00bb\u00be\u0001\u0000\u0000\u0000"+
		"\u00bc\u00ba\u0001\u0000\u0000\u0000\u00bd\u00b3\u0001\u0000\u0000\u0000"+
		"\u00be\u00bf\u0001\u0000\u0000\u0000\u00bf\u00bd\u0001\u0000\u0000\u0000"+
		"\u00bf\u00c0\u0001\u0000\u0000\u0000\u00c0\u00c1\u0001\u0000\u0000\u0000"+
		"\u00c1\u00c5\u0005\u0005\u0000\u0000\u00c2\u00c4\u0005\u000b\u0000\u0000"+
		"\u00c3\u00c2\u0001\u0000\u0000\u0000\u00c4\u00c7\u0001\u0000\u0000\u0000"+
		"\u00c5\u00c3\u0001\u0000\u0000\u0000\u00c5\u00c6\u0001\u0000\u0000\u0000"+
		"\u00c6\u0013\u0001\u0000\u0000\u0000\u00c7\u00c5\u0001\u0000\u0000\u0000"+
		"\u00c8\u00c9\u0005\"\u0000\u0000\u00c9\u00d7\u0005\u0004\u0000\u0000\u00ca"+
		"\u00cc\u0005\u000b\u0000\u0000\u00cb\u00ca\u0001\u0000\u0000\u0000\u00cc"+
		"\u00cf\u0001\u0000\u0000\u0000\u00cd\u00cb\u0001\u0000\u0000\u0000\u00cd"+
		"\u00ce\u0001\u0000\u0000\u0000\u00ce\u00d0\u0001\u0000\u0000\u0000\u00cf"+
		"\u00cd\u0001\u0000\u0000\u0000\u00d0\u00d4\u0003\u0016\u000b\u0000\u00d1"+
		"\u00d3\u00036\u001b\u0000\u00d2\u00d1\u0001\u0000\u0000\u0000\u00d3\u00d6"+
		"\u0001\u0000\u0000\u0000\u00d4\u00d2\u0001\u0000\u0000\u0000\u00d4\u00d5"+
		"\u0001\u0000\u0000\u0000\u00d5\u00d8\u0001\u0000\u0000\u0000\u00d6\u00d4"+
		"\u0001\u0000\u0000\u0000\u00d7\u00cd\u0001\u0000\u0000\u0000\u00d8\u00d9"+
		"\u0001\u0000\u0000\u0000\u00d9\u00d7\u0001\u0000\u0000\u0000\u00d9\u00da"+
		"\u0001\u0000\u0000\u0000\u00da\u00db\u0001\u0000\u0000\u0000\u00db\u00df"+
		"\u0005\u0005\u0000\u0000\u00dc\u00de\u0005\u000b\u0000\u0000\u00dd\u00dc"+
		"\u0001\u0000\u0000\u0000\u00de\u00e1\u0001\u0000\u0000\u0000\u00df\u00dd"+
		"\u0001\u0000\u0000\u0000\u00df\u00e0\u0001\u0000\u0000\u0000\u00e0\u0015"+
		"\u0001\u0000\u0000\u0000\u00e1\u00df\u0001\u0000\u0000\u0000\u00e2\u00e3"+
		"\u0005%\u0000\u0000\u00e3\u0017\u0001\u0000\u0000\u0000\u00e4\u00e5\u0003"+
		"\u001a\r\u0000\u00e5\u00e7\u0005\u0001\u0000\u0000\u00e6\u00e8\u0005\u000b"+
		"\u0000\u0000\u00e7\u00e6\u0001\u0000\u0000\u0000\u00e7\u00e8\u0001\u0000"+
		"\u0000\u0000\u00e8\u00e9\u0001\u0000\u0000\u0000\u00e9\u00ed\u0003\u001e"+
		"\u000f\u0000\u00ea\u00ec\u0005\u000b\u0000\u0000\u00eb\u00ea\u0001\u0000"+
		"\u0000\u0000\u00ec\u00ef\u0001\u0000\u0000\u0000\u00ed\u00eb\u0001\u0000"+
		"\u0000\u0000\u00ed\u00ee\u0001\u0000\u0000\u0000\u00ee\u0019\u0001\u0000"+
		"\u0000\u0000\u00ef\u00ed\u0001\u0000\u0000\u0000\u00f0\u00f2\u0003\u001c"+
		"\u000e\u0000\u00f1\u00f3\u0003\u001c\u000e\u0000\u00f2\u00f1\u0001\u0000"+
		"\u0000\u0000\u00f3\u00f4\u0001\u0000\u0000\u0000\u00f4\u00f2\u0001\u0000"+
		"\u0000\u0000\u00f4\u00f5\u0001\u0000\u0000\u0000\u00f5\u001b\u0001\u0000"+
		"\u0000\u0000\u00f6\u0101\u0005%\u0000\u0000\u00f7\u00f8\u0005\u0002\u0000"+
		"\u0000\u00f8\u00f9\u0005%\u0000\u0000\u00f9\u0101\u0005\u0003\u0000\u0000"+
		"\u00fa\u00fb\u0005\u0002\u0000\u0000\u00fb\u00fc\u0005#\u0000\u0000\u00fc"+
		"\u0101\u0005\u0003\u0000\u0000\u00fd\u00fe\u0005\u0004\u0000\u0000\u00fe"+
		"\u00ff\u0005%\u0000\u0000\u00ff\u0101\u0005\u0005\u0000\u0000\u0100\u00f6"+
		"\u0001\u0000\u0000\u0000\u0100\u00f7\u0001\u0000\u0000\u0000\u0100\u00fa"+
		"\u0001\u0000\u0000\u0000\u0100\u00fd\u0001\u0000\u0000\u0000\u0101\u001d"+
		"\u0001\u0000\u0000\u0000\u0102\u0106\u0005\u0004\u0000\u0000\u0103\u0105"+
		"\u0003 \u0010\u0000\u0104\u0103\u0001\u0000\u0000\u0000\u0105\u0108\u0001"+
		"\u0000\u0000\u0000\u0106\u0104\u0001\u0000\u0000\u0000\u0106\u0107\u0001"+
		"\u0000\u0000\u0000\u0107\u0109\u0001\u0000\u0000\u0000\u0108\u0106\u0001"+
		"\u0000\u0000\u0000\u0109\u010a\u0005\u0005\u0000\u0000\u010a\u001f\u0001"+
		"\u0000\u0000\u0000\u010b\u0114\u0003$\u0012\u0000\u010c\u0114\u0003&\u0013"+
		"\u0000\u010d\u0114\u0003\"\u0011\u0000\u010e\u0114\u0005\u001e\u0000\u0000"+
		"\u010f\u0111\u0005\u001d\u0000\u0000\u0110\u0112\u0003&\u0013\u0000\u0111"+
		"\u0110\u0001\u0000\u0000\u0000\u0111\u0112\u0001\u0000\u0000\u0000\u0112"+
		"\u0114\u0001\u0000\u0000\u0000\u0113\u010b\u0001\u0000\u0000\u0000\u0113"+
		"\u010c\u0001\u0000\u0000\u0000\u0113\u010d\u0001\u0000\u0000\u0000\u0113"+
		"\u010e\u0001\u0000\u0000\u0000\u0113\u010f\u0001\u0000\u0000\u0000\u0114"+
		"!\u0001\u0000\u0000\u0000\u0115\u0116\u0007\u0000\u0000\u0000\u0116#\u0001"+
		"\u0000\u0000\u0000\u0117\u0118\u0005%\u0000\u0000\u0118\u0119\u0005\u0001"+
		"\u0000\u0000\u0119\u0124\u0003&\u0013\u0000\u011a\u011c\u0005%\u0000\u0000"+
		"\u011b\u011d\u0003.\u0017\u0000\u011c\u011b\u0001\u0000\u0000\u0000\u011d"+
		"\u011e\u0001\u0000\u0000\u0000\u011e\u011c\u0001\u0000\u0000\u0000\u011e"+
		"\u011f\u0001\u0000\u0000\u0000\u011f\u0120\u0001\u0000\u0000\u0000\u0120"+
		"\u0121\u0005\u0001\u0000\u0000\u0121\u0122\u0003&\u0013\u0000\u0122\u0124"+
		"\u0001\u0000\u0000\u0000\u0123\u0117\u0001\u0000\u0000\u0000\u0123\u011a"+
		"\u0001\u0000\u0000\u0000\u0124%\u0001\u0000\u0000\u0000\u0125\u0126\u0006"+
		"\u0013\uffff\uffff\u0000\u0126\u0143\u0003,\u0016\u0000\u0127\u0143\u0003"+
		"0\u0018\u0000\u0128\u0143\u0005%\u0000\u0000\u0129\u0143\u0005\u000f\u0000"+
		"\u0000\u012a\u0143\u0005\u001c\u0000\u0000\u012b\u0143\u0005&\u0000\u0000"+
		"\u012c\u0143\u0003\u001e\u000f\u0000\u012d\u0143\u00034\u001a\u0000\u012e"+
		"\u0143\u0003:\u001d\u0000\u012f\u0143\u0003\n\u0005\u0000\u0130\u0143"+
		"\u0003(\u0014\u0000\u0131\u0143\u0003*\u0015\u0000\u0132\u0136\u0005\u0002"+
		"\u0000\u0000\u0133\u0135\u0005\u000b\u0000\u0000\u0134\u0133\u0001\u0000"+
		"\u0000\u0000\u0135\u0138\u0001\u0000\u0000\u0000\u0136\u0134\u0001\u0000"+
		"\u0000\u0000\u0136\u0137\u0001\u0000\u0000\u0000\u0137\u0139\u0001\u0000"+
		"\u0000\u0000\u0138\u0136\u0001\u0000\u0000\u0000\u0139\u013d\u0003&\u0013"+
		"\u0000\u013a\u013c\u0005\u000b\u0000\u0000\u013b\u013a\u0001\u0000\u0000"+
		"\u0000\u013c\u013f\u0001\u0000\u0000\u0000\u013d\u013b\u0001\u0000\u0000"+
		"\u0000\u013d\u013e\u0001\u0000\u0000\u0000\u013e\u0140\u0001\u0000\u0000"+
		"\u0000\u013f\u013d\u0001\u0000\u0000\u0000\u0140\u0141\u0005\u0003\u0000"+
		"\u0000\u0141\u0143\u0001\u0000\u0000\u0000\u0142\u0125\u0001\u0000\u0000"+
		"\u0000\u0142\u0127\u0001\u0000\u0000\u0000\u0142\u0128\u0001\u0000\u0000"+
		"\u0000\u0142\u0129\u0001\u0000\u0000\u0000\u0142\u012a\u0001\u0000\u0000"+
		"\u0000\u0142\u012b\u0001\u0000\u0000\u0000\u0142\u012c\u0001\u0000\u0000"+
		"\u0000\u0142\u012d\u0001\u0000\u0000\u0000\u0142\u012e\u0001\u0000\u0000"+
		"\u0000\u0142\u012f\u0001\u0000\u0000\u0000\u0142\u0130\u0001\u0000\u0000"+
		"\u0000\u0142\u0131\u0001\u0000\u0000\u0000\u0142\u0132\u0001\u0000\u0000"+
		"\u0000\u0143\u016a\u0001\u0000\u0000\u0000\u0144\u0145\n\u0019\u0000\u0000"+
		"\u0145\u0146\u0005\u0010\u0000\u0000\u0146\u0169\u0003&\u0013\u001a\u0147"+
		"\u0148\n\u0018\u0000\u0000\u0148\u0149\u0005\u0011\u0000\u0000\u0149\u0169"+
		"\u0003&\u0013\u0019\u014a\u014b\n\u0017\u0000\u0000\u014b\u014c\u0005"+
		"\u0012\u0000\u0000\u014c\u0169\u0003&\u0013\u0018\u014d\u014e\n\u0016"+
		"\u0000\u0000\u014e\u014f\u0005\u0013\u0000\u0000\u014f\u0169\u0003&\u0013"+
		"\u0017\u0150\u0151\n\u0015\u0000\u0000\u0151\u0152\u0005\u0014\u0000\u0000"+
		"\u0152\u0169\u0003&\u0013\u0016\u0153\u0154\n\u0014\u0000\u0000\u0154"+
		"\u0155\u0005\u0015\u0000\u0000\u0155\u0169\u0003&\u0013\u0015\u0156\u0157"+
		"\n\u0013\u0000\u0000\u0157\u0158\u0005\u0016\u0000\u0000\u0158\u0169\u0003"+
		"&\u0013\u0014\u0159\u015a\n\u0012\u0000\u0000\u015a\u015b\u0005\u0018"+
		"\u0000\u0000\u015b\u0169\u0003&\u0013\u0013\u015c\u015d\n\u0011\u0000"+
		"\u0000\u015d\u015e\u0005\u0017\u0000\u0000\u015e\u0169\u0003&\u0013\u0012"+
		"\u015f\u0160\n\u0010\u0000\u0000\u0160\u0161\u0005\u0019\u0000\u0000\u0161"+
		"\u0169\u0003&\u0013\u0011\u0162\u0163\n\u000f\u0000\u0000\u0163\u0164"+
		"\u0005\u001a\u0000\u0000\u0164\u0169\u0003&\u0013\u0010\u0165\u0166\n"+
		"\u000e\u0000\u0000\u0166\u0167\u0005\u001b\u0000\u0000\u0167\u0169\u0003"+
		"&\u0013\u000f\u0168\u0144\u0001\u0000\u0000\u0000\u0168\u0147\u0001\u0000"+
		"\u0000\u0000\u0168\u014a\u0001\u0000\u0000\u0000\u0168\u014d\u0001\u0000"+
		"\u0000\u0000\u0168\u0150\u0001\u0000\u0000\u0000\u0168\u0153\u0001\u0000"+
		"\u0000\u0000\u0168\u0156\u0001\u0000\u0000\u0000\u0168\u0159\u0001\u0000"+
		"\u0000\u0000\u0168\u015c\u0001\u0000\u0000\u0000\u0168\u015f\u0001\u0000"+
		"\u0000\u0000\u0168\u0162\u0001\u0000\u0000\u0000\u0168\u0165\u0001\u0000"+
		"\u0000\u0000\u0169\u016c\u0001\u0000\u0000\u0000\u016a\u0168\u0001\u0000"+
		"\u0000\u0000\u016a\u016b\u0001\u0000\u0000\u0000\u016b\'\u0001\u0000\u0000"+
		"\u0000\u016c\u016a\u0001\u0000\u0000\u0000\u016d\u016e\u0005%\u0000\u0000"+
		"\u016e\u016f\u0005$\u0000\u0000\u016f\u0170\u0005%\u0000\u0000\u0170)"+
		"\u0001\u0000\u0000\u0000\u0171\u0172\u0005%\u0000\u0000\u0172\u0173\u0005"+
		"$\u0000\u0000\u0173\u0174\u0005%\u0000\u0000\u0174\u0175\u0005\u0001\u0000"+
		"\u0000\u0175\u0176\u0003&\u0013\u0000\u0176+\u0001\u0000\u0000\u0000\u0177"+
		"\u0179\u0005%\u0000\u0000\u0178\u017a\u0003.\u0017\u0000\u0179\u0178\u0001"+
		"\u0000\u0000\u0000\u017a\u017b\u0001\u0000\u0000\u0000\u017b\u0179\u0001"+
		"\u0000\u0000\u0000\u017b\u017c\u0001\u0000\u0000\u0000\u017c-\u0001\u0000"+
		"\u0000\u0000\u017d\u017e\u0005\u0006\u0000\u0000\u017e\u017f\u0003&\u0013"+
		"\u0000\u017f\u0180\u0005\u0007\u0000\u0000\u0180/\u0001\u0000\u0000\u0000"+
		"\u0181\u0183\u00032\u0019\u0000\u0182\u0184\u00032\u0019\u0000\u0183\u0182"+
		"\u0001\u0000\u0000\u0000\u0184\u0185\u0001\u0000\u0000\u0000\u0185\u0183"+
		"\u0001\u0000\u0000\u0000\u0185\u0186\u0001\u0000\u0000\u0000\u01861\u0001"+
		"\u0000\u0000\u0000\u0187\u01a2\u0005%\u0000\u0000\u0188\u018c\u0005\u0002"+
		"\u0000\u0000\u0189\u018b\u0005\u000b\u0000\u0000\u018a\u0189\u0001\u0000"+
		"\u0000\u0000\u018b\u018e\u0001\u0000\u0000\u0000\u018c\u018a\u0001\u0000"+
		"\u0000\u0000\u018c\u018d\u0001\u0000\u0000\u0000\u018d\u018f\u0001\u0000"+
		"\u0000\u0000\u018e\u018c\u0001\u0000\u0000\u0000\u018f\u0193\u0003&\u0013"+
		"\u0000\u0190\u0192\u0005\u000b\u0000\u0000\u0191\u0190\u0001\u0000\u0000"+
		"\u0000\u0192\u0195\u0001\u0000\u0000\u0000\u0193\u0191\u0001\u0000\u0000"+
		"\u0000\u0193\u0194\u0001\u0000\u0000\u0000\u0194\u0196\u0001\u0000\u0000"+
		"\u0000\u0195\u0193\u0001\u0000\u0000\u0000\u0196\u0197\u0005\u0003\u0000"+
		"\u0000\u0197\u01a2\u0001\u0000\u0000\u0000\u0198\u0199\u0005\u0002\u0000"+
		"\u0000\u0199\u019a\u0005#\u0000\u0000\u019a\u01a2\u0005\u0003\u0000\u0000"+
		"\u019b\u01a2\u0005\u000f\u0000\u0000\u019c\u01a2\u0005\u001c\u0000\u0000"+
		"\u019d\u01a2\u0005&\u0000\u0000\u019e\u01a2\u0003\u001e\u000f\u0000\u019f"+
		"\u01a2\u00034\u001a\u0000\u01a0\u01a2\u0003:\u001d\u0000\u01a1\u0187\u0001"+
		"\u0000\u0000\u0000\u01a1\u0188\u0001\u0000\u0000\u0000\u01a1\u0198\u0001"+
		"\u0000\u0000\u0000\u01a1\u019b\u0001\u0000\u0000\u0000\u01a1\u019c\u0001"+
		"\u0000\u0000\u0000\u01a1\u019d\u0001\u0000\u0000\u0000\u01a1\u019e\u0001"+
		"\u0000\u0000\u0000\u01a1\u019f\u0001\u0000\u0000\u0000\u01a1\u01a0\u0001"+
		"\u0000\u0000\u0000\u01a23\u0001\u0000\u0000\u0000\u01a3\u01a4\u0005\u0006"+
		"\u0000\u0000\u01a4\u01a5\u00038\u001c\u0000\u01a5\u01a6\u0005\u0007\u0000"+
		"\u0000\u01a65\u0001\u0000\u0000\u0000\u01a7\u01a8\u0007\u0001\u0000\u0000"+
		"\u01a87\u0001\u0000\u0000\u0000\u01a9\u01ae\u0003&\u0013\u0000\u01aa\u01ab"+
		"\u0005\b\u0000\u0000\u01ab\u01ad\u0003&\u0013\u0000\u01ac\u01aa\u0001"+
		"\u0000\u0000\u0000\u01ad\u01b0\u0001\u0000\u0000\u0000\u01ae\u01ac\u0001"+
		"\u0000\u0000\u0000\u01ae\u01af\u0001\u0000\u0000\u0000\u01af\u01b2\u0001"+
		"\u0000\u0000\u0000\u01b0\u01ae\u0001\u0000\u0000\u0000\u01b1\u01a9\u0001"+
		"\u0000\u0000\u0000\u01b1\u01b2\u0001\u0000\u0000\u0000\u01b2\u01d9\u0001"+
		"\u0000\u0000\u0000\u01b3\u01b7\u0005\u0004\u0000\u0000\u01b4\u01b6\u0005"+
		"\u000b\u0000\u0000\u01b5\u01b4\u0001\u0000\u0000\u0000\u01b6\u01b9\u0001"+
		"\u0000\u0000\u0000\u01b7\u01b5\u0001\u0000\u0000\u0000\u01b7\u01b8\u0001"+
		"\u0000\u0000\u0000\u01b8\u01cb\u0001\u0000\u0000\u0000\u01b9\u01b7\u0001"+
		"\u0000\u0000\u0000\u01ba\u01c6\u0003&\u0013\u0000\u01bb\u01bf\u00036\u001b"+
		"\u0000\u01bc\u01be\u0005\u000b\u0000\u0000\u01bd\u01bc\u0001\u0000\u0000"+
		"\u0000\u01be\u01c1\u0001\u0000\u0000\u0000\u01bf\u01bd\u0001\u0000\u0000"+
		"\u0000\u01bf\u01c0\u0001\u0000\u0000\u0000\u01c0\u01c2\u0001\u0000\u0000"+
		"\u0000\u01c1\u01bf\u0001\u0000\u0000\u0000\u01c2\u01c3\u0003&\u0013\u0000"+
		"\u01c3\u01c5\u0001\u0000\u0000\u0000\u01c4\u01bb\u0001\u0000\u0000\u0000"+
		"\u01c5\u01c8\u0001\u0000\u0000\u0000\u01c6\u01c4\u0001\u0000\u0000\u0000"+
		"\u01c6\u01c7\u0001\u0000\u0000\u0000\u01c7\u01ca\u0001\u0000\u0000\u0000"+
		"\u01c8\u01c6\u0001\u0000\u0000\u0000\u01c9\u01ba\u0001\u0000\u0000\u0000"+
		"\u01ca\u01cd\u0001\u0000\u0000\u0000\u01cb\u01c9\u0001\u0000\u0000\u0000"+
		"\u01cb\u01cc\u0001\u0000\u0000\u0000\u01cc\u01ce\u0001\u0000\u0000\u0000"+
		"\u01cd\u01cb\u0001\u0000\u0000\u0000\u01ce\u01d0\u0005\u0005\u0000\u0000"+
		"\u01cf\u01d1\u0005\u000b\u0000\u0000\u01d0\u01cf\u0001\u0000\u0000\u0000"+
		"\u01d0\u01d1\u0001\u0000\u0000\u0000\u01d1\u01d9\u0001\u0000\u0000\u0000"+
		"\u01d2\u01d4\u0005\u000b\u0000\u0000\u01d3\u01d2\u0001\u0000\u0000\u0000"+
		"\u01d4\u01d7\u0001\u0000\u0000\u0000\u01d5\u01d3\u0001\u0000\u0000\u0000"+
		"\u01d5\u01d6\u0001\u0000\u0000\u0000\u01d6\u01d9\u0001\u0000\u0000\u0000"+
		"\u01d7\u01d5\u0001\u0000\u0000\u0000\u01d8\u01b1\u0001\u0000\u0000\u0000"+
		"\u01d8\u01b3\u0001\u0000\u0000\u0000\u01d8\u01d5\u0001\u0000\u0000\u0000"+
		"\u01d99\u0001\u0000\u0000\u0000\u01da\u01db\u0005\u0006\u0000\u0000\u01db"+
		"\u01dc\u0003<\u001e\u0000\u01dc\u01dd\u0005\u0007\u0000\u0000\u01dd;\u0001"+
		"\u0000\u0000\u0000\u01de\u01e3\u0003>\u001f\u0000\u01df\u01e0\u0005\b"+
		"\u0000\u0000\u01e0\u01e2\u0003>\u001f\u0000\u01e1\u01df\u0001\u0000\u0000"+
		"\u0000\u01e2\u01e5\u0001\u0000\u0000\u0000\u01e3\u01e1\u0001\u0000\u0000"+
		"\u0000\u01e3\u01e4\u0001\u0000\u0000\u0000\u01e4\u0201\u0001\u0000\u0000"+
		"\u0000\u01e5\u01e3\u0001\u0000\u0000\u0000\u01e6\u01ea\u0005\u0004\u0000"+
		"\u0000\u01e7\u01e9\u0005\u000b\u0000\u0000\u01e8\u01e7\u0001\u0000\u0000"+
		"\u0000\u01e9\u01ec\u0001\u0000\u0000\u0000\u01ea\u01e8\u0001\u0000\u0000"+
		"\u0000\u01ea\u01eb\u0001\u0000\u0000\u0000\u01eb\u01ed\u0001\u0000\u0000"+
		"\u0000\u01ec\u01ea\u0001\u0000\u0000\u0000\u01ed\u01f9\u0003>\u001f\u0000"+
		"\u01ee\u01f2\u00036\u001b\u0000\u01ef\u01f1\u0005\u000b\u0000\u0000\u01f0"+
		"\u01ef\u0001\u0000\u0000\u0000\u01f1\u01f4\u0001\u0000\u0000\u0000\u01f2"+
		"\u01f0\u0001\u0000\u0000\u0000\u01f2\u01f3\u0001\u0000\u0000\u0000\u01f3"+
		"\u01f5\u0001\u0000\u0000\u0000\u01f4\u01f2\u0001\u0000\u0000\u0000\u01f5"+
		"\u01f6\u0003>\u001f\u0000\u01f6\u01f8\u0001\u0000\u0000\u0000\u01f7\u01ee"+
		"\u0001\u0000\u0000\u0000\u01f8\u01fb\u0001\u0000\u0000\u0000\u01f9\u01f7"+
		"\u0001\u0000\u0000\u0000\u01f9\u01fa\u0001\u0000\u0000\u0000\u01fa\u01fc"+
		"\u0001\u0000\u0000\u0000\u01fb\u01f9\u0001\u0000\u0000\u0000\u01fc\u01fe"+
		"\u0005\u0005\u0000\u0000\u01fd\u01ff\u0005\u000b\u0000\u0000\u01fe\u01fd"+
		"\u0001\u0000\u0000\u0000\u01fe\u01ff\u0001\u0000\u0000\u0000\u01ff\u0201"+
		"\u0001\u0000\u0000\u0000\u0200\u01de\u0001\u0000\u0000\u0000\u0200\u01e6"+
		"\u0001\u0000\u0000\u0000\u0201=\u0001\u0000\u0000\u0000\u0202\u0203\u0003"+
		"@ \u0000\u0203\u0204\u0005\u0001\u0000\u0000\u0204\u0205\u0003B!\u0000"+
		"\u0205?\u0001\u0000\u0000\u0000\u0206\u0207\u0007\u0002\u0000\u0000\u0207"+
		"A\u0001\u0000\u0000\u0000\u0208\u0209\u0003&\u0013\u0000\u0209C\u0001"+
		"\u0000\u0000\u0000<FHNSV`ipy}\u0080\u0083\u0088\u0091\u0099\u00a0\u00a5"+
		"\u00ab\u00b3\u00ba\u00bf\u00c5\u00cd\u00d4\u00d9\u00df\u00e7\u00ed\u00f4"+
		"\u0100\u0106\u0111\u0113\u011e\u0123\u0136\u013d\u0142\u0168\u016a\u017b"+
		"\u0185\u018c\u0193\u01a1\u01ae\u01b1\u01b7\u01bf\u01c6\u01cb\u01d0\u01d5"+
		"\u01d8\u01e3\u01ea\u01f2\u01f9\u01fe\u0200";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}