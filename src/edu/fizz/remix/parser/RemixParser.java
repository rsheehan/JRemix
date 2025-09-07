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
		GETTER=35, SETTER=36, LIBRARY=37, USING=38, USES=39, SELFREF=40, CONSTANT=41, 
		IDENTIFIER=42, WORD=43, WORDPRODUCT=44, STRING=45, EMPTYIDENTIFIER=46;
	public static final int
		RULE_program = 0, RULE_library = 1, RULE_libraryName = 2, RULE_usingStatement = 3, 
		RULE_usingBlock = 4, RULE_usesStatement = 5, RULE_statementBlock = 6, 
		RULE_functionDefinition = 7, RULE_functionComment = 8, RULE_functionSignature = 9, 
		RULE_singleWord = 10, RULE_sigPart = 11, RULE_createObject = 12, RULE_extendObject = 13, 
		RULE_object = 14, RULE_field = 15, RULE_getterSetter = 16, RULE_getter = 17, 
		RULE_setter = 18, RULE_fieldId = 19, RULE_methodDefinition = 20, RULE_methodSignature = 21, 
		RULE_methodSigPart = 22, RULE_blockOfStatements = 23, RULE_statement = 24, 
		RULE_endOfStatement = 25, RULE_assignmentStatement = 26, RULE_printStatement = 27, 
		RULE_expression = 28, RULE_listElement = 29, RULE_listPart = 30, RULE_functionCall = 31, 
		RULE_callPart = 32, RULE_list = 33, RULE_separator = 34, RULE_listContents = 35, 
		RULE_map = 36, RULE_mapContents = 37, RULE_keyValue = 38, RULE_value = 39;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "library", "libraryName", "usingStatement", "usingBlock", 
			"usesStatement", "statementBlock", "functionDefinition", "functionComment", 
			"functionSignature", "singleWord", "sigPart", "createObject", "extendObject", 
			"object", "field", "getterSetter", "getter", "setter", "fieldId", "methodDefinition", 
			"methodSignature", "methodSigPart", "blockOfStatements", "statement", 
			"endOfStatement", "assignmentStatement", "printStatement", "expression", 
			"listElement", "listPart", "functionCall", "callPart", "list", "separator", 
			"listContents", "map", "mapContents", "keyValue", "value"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "':'", "'('", "')'", "'['", "']'", "'{'", "'}'", "','", "'~'", 
			null, null, null, "'\\n'", "'.'", null, null, null, null, null, "' < '", 
			"' > '", null, null, "' = '", null, null, "'-'", "'null'", null, "'return'", 
			"'redo'", "'create'", "'extend'", null, null, null, "'library'", "'using'", 
			"'uses'"
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
			"USING", "USES", "SELFREF", "CONSTANT", "IDENTIFIER", "WORD", "WORDPRODUCT", 
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
			setState(84);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 70248351065684L) != 0)) {
				{
				setState(82);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(80);
					functionDefinition();
					}
					break;
				case 2:
					{
					setState(81);
					statement();
					}
					break;
				}
				}
				setState(86);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(87);
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
		public LibraryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_library; }
	 
		public LibraryContext() { }
		public void copyFrom(LibraryContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LibNoUsesContext extends LibraryContext {
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
		public LibNoUsesContext(LibraryContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitLibNoUses(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LibUsesContext extends LibraryContext {
		public LibraryNameContext libraryName() {
			return getRuleContext(LibraryNameContext.class,0);
		}
		public UsingStatementContext usingStatement() {
			return getRuleContext(UsingStatementContext.class,0);
		}
		public LibUsesContext(LibraryContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitLibUses(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LibraryContext library() throws RecognitionException {
		LibraryContext _localctx = new LibraryContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_library);
		int _la;
		try {
			int _alt;
			setState(109);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				_localctx = new LibNoUsesContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(89);
				libraryName();
				setState(90);
				match(LBLOCK);
				setState(94);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(91);
						match(EOL);
						}
						} 
					}
					setState(96);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
				}
				setState(101);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 70248351065684L) != 0)) {
					{
					setState(99);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
					case 1:
						{
						setState(97);
						functionDefinition();
						}
						break;
					case 2:
						{
						setState(98);
						statement();
						}
						break;
					}
					}
					setState(103);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(104);
				match(RBLOCK);
				}
				break;
			case 2:
				_localctx = new LibUsesContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(106);
				libraryName();
				setState(107);
				usingStatement();
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
		enterRule(_localctx, 4, RULE_libraryName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(111);
			match(LIBRARY);
			setState(113);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==STRING) {
				{
				setState(112);
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
		enterRule(_localctx, 6, RULE_usingStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(115);
			match(USING);
			setState(116);
			expression(0);
			setState(121);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(117);
				match(COMMA);
				setState(118);
				expression(0);
				}
				}
				setState(123);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(124);
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
		enterRule(_localctx, 8, RULE_usingBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(126);
			match(LBLOCK);
			setState(131);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 70248351065684L) != 0)) {
				{
				setState(129);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
				case 1:
					{
					setState(127);
					functionDefinition();
					}
					break;
				case 2:
					{
					setState(128);
					statement();
					}
					break;
				}
				}
				setState(133);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(134);
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
	public static class UsesStatementContext extends ParserRuleContext {
		public TerminalNode USES() { return getToken(RemixParser.USES, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public StatementBlockContext statementBlock() {
			return getRuleContext(StatementBlockContext.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(RemixParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(RemixParser.COMMA, i);
		}
		public UsesStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_usesStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitUsesStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UsesStatementContext usesStatement() throws RecognitionException {
		UsesStatementContext _localctx = new UsesStatementContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_usesStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(136);
			match(USES);
			setState(137);
			expression(0);
			setState(142);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(138);
				match(COMMA);
				setState(139);
				expression(0);
				}
				}
				setState(144);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(145);
			statementBlock();
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
	public static class StatementBlockContext extends ParserRuleContext {
		public TerminalNode LBLOCK() { return getToken(RemixParser.LBLOCK, 0); }
		public TerminalNode RBLOCK() { return getToken(RemixParser.RBLOCK, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public StatementBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statementBlock; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitStatementBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementBlockContext statementBlock() throws RecognitionException {
		StatementBlockContext _localctx = new StatementBlockContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_statementBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147);
			match(LBLOCK);
			setState(149); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(148);
				statement();
				}
				}
				setState(151); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 70248351032916L) != 0) );
			setState(153);
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
		enterRule(_localctx, 14, RULE_functionDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(156);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DOC_COMMENT) {
				{
				setState(155);
				functionComment();
				}
			}

			setState(158);
			functionSignature();
			setState(159);
			match(COLON);
			setState(161);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(160);
				match(COLON);
				}
			}

			setState(164);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EOL) {
				{
				setState(163);
				match(EOL);
				}
			}

			setState(166);
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
		enterRule(_localctx, 16, RULE_functionComment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(168);
			match(DOC_COMMENT);
			setState(169);
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
		enterRule(_localctx, 18, RULE_functionSignature);
		int _la;
		try {
			setState(178);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(171);
				sigPart();
				setState(173); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(172);
					sigPart();
					}
					}
					setState(175); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 13194139533332L) != 0) );
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(177);
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
		enterRule(_localctx, 20, RULE_singleWord);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(180);
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
		enterRule(_localctx, 22, RULE_sigPart);
		try {
			setState(192);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case WORD:
				_localctx = new SigWordContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(182);
				match(WORD);
				}
				break;
			case LPAREN:
			case IDENTIFIER:
				_localctx = new SigParamContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(187);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case IDENTIFIER:
					{
					setState(183);
					match(IDENTIFIER);
					}
					break;
				case LPAREN:
					{
					setState(184);
					match(LPAREN);
					setState(185);
					match(IDENTIFIER);
					setState(186);
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
				setState(189);
				match(LBLOCK);
				setState(190);
				match(IDENTIFIER);
				setState(191);
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
		enterRule(_localctx, 24, RULE_createObject);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(194);
			match(CREATE);
			setState(195);
			match(LBLOCK);
			setState(199);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==EOL) {
				{
				{
				setState(196);
				match(EOL);
				}
				}
				setState(201);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(202);
			object();
			setState(203);
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
		enterRule(_localctx, 26, RULE_extendObject);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(205);
			match(EXTEND);
			setState(206);
			expression(0);
			setState(207);
			match(LBLOCK);
			setState(211);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==EOL) {
				{
				{
				setState(208);
				match(EOL);
				}
				}
				setState(213);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(214);
			object();
			setState(215);
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
		enterRule(_localctx, 28, RULE_object);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(220);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(217);
					field();
					}
					} 
				}
				setState(222);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			}
			{
			setState(224);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==GETTERSETTER) {
				{
				setState(223);
				getterSetter();
				}
			}

			setState(227);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==GETTER) {
				{
				setState(226);
				getter();
				}
			}

			setState(230);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SETTER) {
				{
				setState(229);
				setter();
				}
			}

			}
			setState(235);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 14293651193876L) != 0)) {
				{
				{
				setState(232);
				methodDefinition();
				}
				}
				setState(237);
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
		enterRule(_localctx, 30, RULE_field);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(238);
			match(IDENTIFIER);
			setState(239);
			match(COLON);
			setState(240);
			expression(0);
			setState(244);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==EOL) {
				{
				{
				setState(241);
				match(EOL);
				}
				}
				setState(246);
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
		enterRule(_localctx, 32, RULE_getterSetter);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(247);
			match(GETTERSETTER);
			setState(248);
			match(LBLOCK);
			setState(262); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(252);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==EOL) {
					{
					{
					setState(249);
					match(EOL);
					}
					}
					setState(254);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(255);
				fieldId();
				setState(259);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(256);
						separator();
						}
						} 
					}
					setState(261);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
				}
				}
				}
				setState(264); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==EOL || _la==IDENTIFIER );
			setState(266);
			match(RBLOCK);
			setState(270);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==EOL) {
				{
				{
				setState(267);
				match(EOL);
				}
				}
				setState(272);
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
		enterRule(_localctx, 34, RULE_getter);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(273);
			match(GETTER);
			setState(274);
			match(LBLOCK);
			setState(288); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(278);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==EOL) {
					{
					{
					setState(275);
					match(EOL);
					}
					}
					setState(280);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(281);
				fieldId();
				setState(285);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(282);
						separator();
						}
						} 
					}
					setState(287);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
				}
				}
				}
				setState(290); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==EOL || _la==IDENTIFIER );
			setState(292);
			match(RBLOCK);
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
		enterRule(_localctx, 36, RULE_setter);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(299);
			match(SETTER);
			setState(300);
			match(LBLOCK);
			setState(314); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(304);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==EOL) {
					{
					{
					setState(301);
					match(EOL);
					}
					}
					setState(306);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(307);
				fieldId();
				setState(311);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,36,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(308);
						separator();
						}
						} 
					}
					setState(313);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,36,_ctx);
				}
				}
				}
				setState(316); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==EOL || _la==IDENTIFIER );
			setState(318);
			match(RBLOCK);
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
		enterRule(_localctx, 38, RULE_fieldId);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(325);
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
		enterRule(_localctx, 40, RULE_methodDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(328);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DOC_COMMENT) {
				{
				setState(327);
				functionComment();
				}
			}

			setState(330);
			methodSignature();
			setState(331);
			match(COLON);
			setState(333);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EOL) {
				{
				setState(332);
				match(EOL);
				}
			}

			setState(335);
			blockOfStatements();
			setState(339);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==EOL) {
				{
				{
				setState(336);
				match(EOL);
				}
				}
				setState(341);
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
		enterRule(_localctx, 42, RULE_methodSignature);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(343); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(342);
				methodSigPart();
				}
				}
				setState(345); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 14293651161108L) != 0) );
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
		enterRule(_localctx, 44, RULE_methodSigPart);
		try {
			setState(359);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,43,_ctx) ) {
			case 1:
				_localctx = new MethSigWordContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(347);
				match(WORD);
				}
				break;
			case 2:
				_localctx = new MethSigParamContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(348);
				match(IDENTIFIER);
				}
				break;
			case 3:
				_localctx = new MethSigParamContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(349);
				match(LPAREN);
				setState(350);
				match(IDENTIFIER);
				setState(351);
				match(RPAREN);
				}
				break;
			case 4:
				_localctx = new MethSigSelfContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(352);
				match(SELFREF);
				}
				break;
			case 5:
				_localctx = new MethSigSelfContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(353);
				match(LPAREN);
				setState(354);
				match(SELFREF);
				setState(355);
				match(RPAREN);
				}
				break;
			case 6:
				_localctx = new MethSigBlockContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(356);
				match(LBLOCK);
				setState(357);
				match(IDENTIFIER);
				setState(358);
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
		enterRule(_localctx, 46, RULE_blockOfStatements);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(361);
			match(LBLOCK);
			setState(365);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 70248351032916L) != 0)) {
				{
				{
				setState(362);
				statement();
				}
				}
				setState(367);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(368);
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
	public static class UsingContext extends StatementContext {
		public UsingStatementContext usingStatement() {
			return getRuleContext(UsingStatementContext.class,0);
		}
		public UsingContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitUsing(this);
			else return visitor.visitChildren(this);
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
	public static class UsesContext extends StatementContext {
		public UsesStatementContext usesStatement() {
			return getRuleContext(UsesStatementContext.class,0);
		}
		public UsesContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RemixParserVisitor ) return ((RemixParserVisitor<? extends T>)visitor).visitUses(this);
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
		enterRule(_localctx, 48, RULE_statement);
		try {
			setState(381);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,46,_ctx) ) {
			case 1:
				_localctx = new AssStatementContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(370);
				assignmentStatement();
				}
				break;
			case 2:
				_localctx = new PrStatementContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(371);
				printStatement();
				}
				break;
			case 3:
				_localctx = new ExprContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(372);
				expression(0);
				}
				break;
			case 4:
				_localctx = new RedoContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(373);
				match(REDO);
				}
				break;
			case 5:
				_localctx = new ReturnContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(374);
				match(RETURN);
				setState(376);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,45,_ctx) ) {
				case 1:
					{
					setState(375);
					expression(0);
					}
					break;
				}
				}
				break;
			case 6:
				_localctx = new UsesContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(378);
				usesStatement();
				}
				break;
			case 7:
				_localctx = new UsingContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(379);
				usingStatement();
				}
				break;
			case 8:
				_localctx = new BlankContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(380);
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
		enterRule(_localctx, 50, RULE_endOfStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(383);
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
		enterRule(_localctx, 52, RULE_assignmentStatement);
		int _la;
		try {
			setState(400);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,48,_ctx) ) {
			case 1:
				_localctx = new SetVariableContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(385);
				match(IDENTIFIER);
				setState(386);
				match(COLON);
				setState(387);
				expression(0);
				}
				break;
			case 2:
				_localctx = new SetConstantContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(388);
				match(CONSTANT);
				setState(389);
				match(COLON);
				setState(390);
				expression(0);
				}
				break;
			case 3:
				_localctx = new SetListElementContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(391);
				match(IDENTIFIER);
				setState(393); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(392);
					listPart();
					}
					}
					setState(395); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==LBRACE );
				setState(397);
				match(COLON);
				setState(398);
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
		enterRule(_localctx, 54, RULE_printStatement);
		int _la;
		try {
			_localctx = new PrntStatementContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(410);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 69420496060500L) != 0)) {
				{
				setState(402);
				expression(0);
				setState(407);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(403);
					match(COMMA);
					setState(404);
					expression(0);
					}
					}
					setState(409);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(412);
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
		int _startState = 56;
		enterRecursionRule(_localctx, 56, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(449);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,53,_ctx) ) {
			case 1:
				{
				_localctx = new ExprMinusContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(415);
				match(MINUS);
				setState(416);
				expression(27);
				}
				break;
			case 2:
				{
				_localctx = new ExprListElementContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(417);
				listElement();
				}
				break;
			case 3:
				{
				_localctx = new ExprFncCallContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(418);
				functionCall();
				}
				break;
			case 4:
				{
				_localctx = new ExprConstantContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(419);
				match(CONSTANT);
				}
				break;
			case 5:
				{
				_localctx = new ExprVarContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(420);
				match(IDENTIFIER);
				}
				break;
			case 6:
				{
				_localctx = new ExprSelfContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(421);
				match(SELFREF);
				}
				break;
			case 7:
				{
				_localctx = new ExprNumberContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(422);
				match(NUMBER);
				}
				break;
			case 8:
				{
				_localctx = new ExprWordProductContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(423);
				match(WORDPRODUCT);
				}
				break;
			case 9:
				{
				_localctx = new ExprNullContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(424);
				match(NULL);
				}
				break;
			case 10:
				{
				_localctx = new ExprBooleanContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(425);
				match(BOOLEAN);
				}
				break;
			case 11:
				{
				_localctx = new ExprStringContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(426);
				match(STRING);
				}
				break;
			case 12:
				{
				_localctx = new ExprBlockContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(427);
				blockOfStatements();
				}
				break;
			case 13:
				{
				_localctx = new ExprListContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(428);
				list();
				}
				break;
			case 14:
				{
				_localctx = new ExprMapContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(429);
				map();
				}
				break;
			case 15:
				{
				_localctx = new ExprLibraryContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(430);
				library();
				}
				break;
			case 16:
				{
				_localctx = new ExprObjectContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(431);
				createObject();
				}
				break;
			case 17:
				{
				_localctx = new ExprExtendContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(432);
				extendObject();
				}
				break;
			case 18:
				{
				_localctx = new ExprParenContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(433);
				match(LPAREN);
				setState(437);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==EOL) {
					{
					{
					setState(434);
					match(EOL);
					}
					}
					setState(439);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(440);
				expression(0);
				setState(444);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==EOL) {
					{
					{
					setState(441);
					match(EOL);
					}
					}
					setState(446);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(447);
				match(RPAREN);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(480);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,55,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(478);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,54,_ctx) ) {
					case 1:
						{
						_localctx = new ExprMulContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(451);
						if (!(precpred(_ctx, 26))) throw new FailedPredicateException(this, "precpred(_ctx, 26)");
						setState(452);
						match(MUL);
						setState(453);
						expression(27);
						}
						break;
					case 2:
						{
						_localctx = new ExprAddContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(454);
						if (!(precpred(_ctx, 25))) throw new FailedPredicateException(this, "precpred(_ctx, 25)");
						setState(455);
						match(ADD);
						setState(456);
						expression(26);
						}
						break;
					case 3:
						{
						_localctx = new ExprLessContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(457);
						if (!(precpred(_ctx, 24))) throw new FailedPredicateException(this, "precpred(_ctx, 24)");
						setState(458);
						match(LESS);
						setState(459);
						expression(25);
						}
						break;
					case 4:
						{
						_localctx = new ExprGreaterContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(460);
						if (!(precpred(_ctx, 23))) throw new FailedPredicateException(this, "precpred(_ctx, 23)");
						setState(461);
						match(GREATER);
						setState(462);
						expression(24);
						}
						break;
					case 5:
						{
						_localctx = new ExprGreatEqlContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(463);
						if (!(precpred(_ctx, 22))) throw new FailedPredicateException(this, "precpred(_ctx, 22)");
						setState(464);
						match(GREATEREQUAL);
						setState(465);
						expression(23);
						}
						break;
					case 6:
						{
						_localctx = new ExprLessEqlContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(466);
						if (!(precpred(_ctx, 21))) throw new FailedPredicateException(this, "precpred(_ctx, 21)");
						setState(467);
						match(LESSEQUAL);
						setState(468);
						expression(22);
						}
						break;
					case 7:
						{
						_localctx = new ExprEqualContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(469);
						if (!(precpred(_ctx, 20))) throw new FailedPredicateException(this, "precpred(_ctx, 20)");
						setState(470);
						match(EQUAL);
						setState(471);
						expression(21);
						}
						break;
					case 8:
						{
						_localctx = new ExprNotEqlContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(472);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(473);
						match(NOTEQUAL);
						setState(474);
						expression(20);
						}
						break;
					case 9:
						{
						_localctx = new ExprConcatContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(475);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(476);
						match(CONCAT);
						setState(477);
						expression(19);
						}
						break;
					}
					} 
				}
				setState(482);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,55,_ctx);
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
		enterRule(_localctx, 58, RULE_listElement);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(483);
			match(IDENTIFIER);
			setState(485); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(484);
					listPart();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(487); 
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
		enterRule(_localctx, 60, RULE_listPart);
		try {
			_localctx = new ListPartExprContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(489);
			match(LBRACE);
			setState(490);
			expression(0);
			setState(491);
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
		enterRule(_localctx, 62, RULE_functionCall);
		try {
			int _alt;
			setState(500);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,58,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(493);
				callPart();
				setState(495); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(494);
						callPart();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(497); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,57,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(499);
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
		enterRule(_localctx, 64, RULE_callPart);
		int _la;
		try {
			setState(535);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,61,_ctx) ) {
			case 1:
				_localctx = new CallWordContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(502);
				match(WORD);
				}
				break;
			case 2:
				_localctx = new CallMinusExprContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(503);
				match(MINUS);
				setState(504);
				expression(0);
				}
				break;
			case 3:
				_localctx = new CallConstantContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(505);
				match(CONSTANT);
				}
				break;
			case 4:
				_localctx = new CallVarContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(506);
				match(IDENTIFIER);
				}
				break;
			case 5:
				_localctx = new CallParamContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(507);
				match(LPAREN);
				setState(511);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==EOL) {
					{
					{
					setState(508);
					match(EOL);
					}
					}
					setState(513);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(514);
				expression(0);
				setState(518);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==EOL) {
					{
					{
					setState(515);
					match(EOL);
					}
					}
					setState(520);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(521);
				match(RPAREN);
				}
				break;
			case 6:
				_localctx = new CallSelfContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(523);
				match(SELFREF);
				}
				break;
			case 7:
				_localctx = new CallSelfContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(524);
				match(LPAREN);
				setState(525);
				match(SELFREF);
				setState(526);
				match(RPAREN);
				}
				break;
			case 8:
				_localctx = new CallNumberContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(527);
				match(NUMBER);
				}
				break;
			case 9:
				_localctx = new CallWordProductContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(528);
				match(WORDPRODUCT);
				}
				break;
			case 10:
				_localctx = new CallNullContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(529);
				match(NULL);
				}
				break;
			case 11:
				_localctx = new CallBooleanContext(_localctx);
				enterOuterAlt(_localctx, 11);
				{
				setState(530);
				match(BOOLEAN);
				}
				break;
			case 12:
				_localctx = new CallStringContext(_localctx);
				enterOuterAlt(_localctx, 12);
				{
				setState(531);
				match(STRING);
				}
				break;
			case 13:
				_localctx = new CallBlockContext(_localctx);
				enterOuterAlt(_localctx, 13);
				{
				setState(532);
				blockOfStatements();
				}
				break;
			case 14:
				_localctx = new CallListContext(_localctx);
				enterOuterAlt(_localctx, 14);
				{
				setState(533);
				list();
				}
				break;
			case 15:
				_localctx = new CallMapContext(_localctx);
				enterOuterAlt(_localctx, 15);
				{
				setState(534);
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
		enterRule(_localctx, 66, RULE_list);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(537);
			match(LBRACE);
			setState(538);
			listContents();
			setState(539);
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
		enterRule(_localctx, 68, RULE_separator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(541);
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
		enterRule(_localctx, 70, RULE_listContents);
		int _la;
		try {
			setState(593);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,70,_ctx) ) {
			case 1:
				_localctx = new CommaListContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(551);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 69420496060500L) != 0)) {
					{
					setState(543);
					expression(0);
					setState(548);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(544);
						match(COMMA);
						setState(545);
						expression(0);
						}
						}
						setState(550);
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
				setState(553);
				match(LBLOCK);
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
				setState(577);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 69420496060500L) != 0)) {
					{
					{
					setState(560);
					expression(0);
					setState(572);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA || _la==EOL) {
						{
						{
						setState(561);
						separator();
						setState(565);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==EOL) {
							{
							{
							setState(562);
							match(EOL);
							}
							}
							setState(567);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(568);
						expression(0);
						}
						}
						setState(574);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
					}
					setState(579);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(580);
				match(RBLOCK);
				setState(584);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==EOL) {
					{
					{
					setState(581);
					match(EOL);
					}
					}
					setState(586);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 3:
				_localctx = new EmptyListContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(590);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==EOL) {
					{
					{
					setState(587);
					match(EOL);
					}
					}
					setState(592);
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
		enterRule(_localctx, 72, RULE_map);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(595);
			match(LBRACE);
			setState(596);
			mapContents();
			setState(597);
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
		enterRule(_localctx, 74, RULE_mapContents);
		int _la;
		try {
			setState(633);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING:
				_localctx = new CommaMapContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(599);
				keyValue();
				setState(604);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(600);
					match(COMMA);
					setState(601);
					keyValue();
					}
					}
					setState(606);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case LBLOCK:
				_localctx = new BlockMapContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(607);
				match(LBLOCK);
				setState(611);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==EOL) {
					{
					{
					setState(608);
					match(EOL);
					}
					}
					setState(613);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(614);
				keyValue();
				setState(626);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA || _la==EOL) {
					{
					{
					setState(615);
					separator();
					setState(619);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==EOL) {
						{
						{
						setState(616);
						match(EOL);
						}
						}
						setState(621);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(622);
					keyValue();
					}
					}
					setState(628);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(629);
				match(RBLOCK);
				setState(631);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==EOL) {
					{
					setState(630);
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
		enterRule(_localctx, 76, RULE_keyValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(635);
			match(STRING);
			setState(636);
			match(COLON);
			setState(637);
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
		enterRule(_localctx, 78, RULE_value);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(639);
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
		case 28:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 26);
		case 1:
			return precpred(_ctx, 25);
		case 2:
			return precpred(_ctx, 24);
		case 3:
			return precpred(_ctx, 23);
		case 4:
			return precpred(_ctx, 22);
		case 5:
			return precpred(_ctx, 21);
		case 6:
			return precpred(_ctx, 20);
		case 7:
			return precpred(_ctx, 19);
		case 8:
			return precpred(_ctx, 18);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001.\u0282\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
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
		"#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0002\'\u0007\'\u0001"+
		"\u0000\u0001\u0000\u0005\u0000S\b\u0000\n\u0000\f\u0000V\t\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0005\u0001]\b"+
		"\u0001\n\u0001\f\u0001`\t\u0001\u0001\u0001\u0001\u0001\u0005\u0001d\b"+
		"\u0001\n\u0001\f\u0001g\t\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0003\u0001n\b\u0001\u0001\u0002\u0001\u0002\u0003"+
		"\u0002r\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0005"+
		"\u0003x\b\u0003\n\u0003\f\u0003{\t\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0005\u0004\u0082\b\u0004\n\u0004\f\u0004"+
		"\u0085\t\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0005\u0005\u008d\b\u0005\n\u0005\f\u0005\u0090\t\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0004\u0006\u0096\b\u0006\u000b"+
		"\u0006\f\u0006\u0097\u0001\u0006\u0001\u0006\u0001\u0007\u0003\u0007\u009d"+
		"\b\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u00a2\b\u0007"+
		"\u0001\u0007\u0003\u0007\u00a5\b\u0007\u0001\u0007\u0001\u0007\u0001\b"+
		"\u0001\b\u0001\b\u0001\t\u0001\t\u0004\t\u00ae\b\t\u000b\t\f\t\u00af\u0001"+
		"\t\u0003\t\u00b3\b\t\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0003\u000b\u00bc\b\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0003\u000b\u00c1\b\u000b\u0001\f\u0001\f\u0001\f\u0005\f"+
		"\u00c6\b\f\n\f\f\f\u00c9\t\f\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0005\r\u00d2\b\r\n\r\f\r\u00d5\t\r\u0001\r\u0001\r\u0001\r"+
		"\u0001\u000e\u0005\u000e\u00db\b\u000e\n\u000e\f\u000e\u00de\t\u000e\u0001"+
		"\u000e\u0003\u000e\u00e1\b\u000e\u0001\u000e\u0003\u000e\u00e4\b\u000e"+
		"\u0001\u000e\u0003\u000e\u00e7\b\u000e\u0001\u000e\u0005\u000e\u00ea\b"+
		"\u000e\n\u000e\f\u000e\u00ed\t\u000e\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0005\u000f\u00f3\b\u000f\n\u000f\f\u000f\u00f6\t\u000f\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0005\u0010\u00fb\b\u0010\n\u0010\f\u0010"+
		"\u00fe\t\u0010\u0001\u0010\u0001\u0010\u0005\u0010\u0102\b\u0010\n\u0010"+
		"\f\u0010\u0105\t\u0010\u0004\u0010\u0107\b\u0010\u000b\u0010\f\u0010\u0108"+
		"\u0001\u0010\u0001\u0010\u0005\u0010\u010d\b\u0010\n\u0010\f\u0010\u0110"+
		"\t\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0005\u0011\u0115\b\u0011"+
		"\n\u0011\f\u0011\u0118\t\u0011\u0001\u0011\u0001\u0011\u0005\u0011\u011c"+
		"\b\u0011\n\u0011\f\u0011\u011f\t\u0011\u0004\u0011\u0121\b\u0011\u000b"+
		"\u0011\f\u0011\u0122\u0001\u0011\u0001\u0011\u0005\u0011\u0127\b\u0011"+
		"\n\u0011\f\u0011\u012a\t\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0005"+
		"\u0012\u012f\b\u0012\n\u0012\f\u0012\u0132\t\u0012\u0001\u0012\u0001\u0012"+
		"\u0005\u0012\u0136\b\u0012\n\u0012\f\u0012\u0139\t\u0012\u0004\u0012\u013b"+
		"\b\u0012\u000b\u0012\f\u0012\u013c\u0001\u0012\u0001\u0012\u0005\u0012"+
		"\u0141\b\u0012\n\u0012\f\u0012\u0144\t\u0012\u0001\u0013\u0001\u0013\u0001"+
		"\u0014\u0003\u0014\u0149\b\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0003"+
		"\u0014\u014e\b\u0014\u0001\u0014\u0001\u0014\u0005\u0014\u0152\b\u0014"+
		"\n\u0014\f\u0014\u0155\t\u0014\u0001\u0015\u0004\u0015\u0158\b\u0015\u000b"+
		"\u0015\f\u0015\u0159\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0003\u0016\u0168\b\u0016\u0001\u0017\u0001\u0017\u0005"+
		"\u0017\u016c\b\u0017\n\u0017\f\u0017\u016f\t\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018"+
		"\u0003\u0018\u0179\b\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0003\u0018"+
		"\u017e\b\u0018\u0001\u0019\u0001\u0019\u0001\u001a\u0001\u001a\u0001\u001a"+
		"\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0004\u001a"+
		"\u018a\b\u001a\u000b\u001a\f\u001a\u018b\u0001\u001a\u0001\u001a\u0001"+
		"\u001a\u0003\u001a\u0191\b\u001a\u0001\u001b\u0001\u001b\u0001\u001b\u0005"+
		"\u001b\u0196\b\u001b\n\u001b\f\u001b\u0199\t\u001b\u0003\u001b\u019b\b"+
		"\u001b\u0001\u001b\u0001\u001b\u0001\u001c\u0001\u001c\u0001\u001c\u0001"+
		"\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001"+
		"\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001"+
		"\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0005"+
		"\u001c\u01b4\b\u001c\n\u001c\f\u001c\u01b7\t\u001c\u0001\u001c\u0001\u001c"+
		"\u0005\u001c\u01bb\b\u001c\n\u001c\f\u001c\u01be\t\u001c\u0001\u001c\u0001"+
		"\u001c\u0003\u001c\u01c2\b\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001"+
		"\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001"+
		"\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001"+
		"\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001"+
		"\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0005"+
		"\u001c\u01df\b\u001c\n\u001c\f\u001c\u01e2\t\u001c\u0001\u001d\u0001\u001d"+
		"\u0004\u001d\u01e6\b\u001d\u000b\u001d\f\u001d\u01e7\u0001\u001e\u0001"+
		"\u001e\u0001\u001e\u0001\u001e\u0001\u001f\u0001\u001f\u0004\u001f\u01f0"+
		"\b\u001f\u000b\u001f\f\u001f\u01f1\u0001\u001f\u0003\u001f\u01f5\b\u001f"+
		"\u0001 \u0001 \u0001 \u0001 \u0001 \u0001 \u0001 \u0005 \u01fe\b \n \f"+
		" \u0201\t \u0001 \u0001 \u0005 \u0205\b \n \f \u0208\t \u0001 \u0001 "+
		"\u0001 \u0001 \u0001 \u0001 \u0001 \u0001 \u0001 \u0001 \u0001 \u0001"+
		" \u0001 \u0001 \u0003 \u0218\b \u0001!\u0001!\u0001!\u0001!\u0001\"\u0001"+
		"\"\u0001#\u0001#\u0001#\u0005#\u0223\b#\n#\f#\u0226\t#\u0003#\u0228\b"+
		"#\u0001#\u0001#\u0005#\u022c\b#\n#\f#\u022f\t#\u0001#\u0001#\u0001#\u0005"+
		"#\u0234\b#\n#\f#\u0237\t#\u0001#\u0001#\u0005#\u023b\b#\n#\f#\u023e\t"+
		"#\u0005#\u0240\b#\n#\f#\u0243\t#\u0001#\u0001#\u0005#\u0247\b#\n#\f#\u024a"+
		"\t#\u0001#\u0005#\u024d\b#\n#\f#\u0250\t#\u0003#\u0252\b#\u0001$\u0001"+
		"$\u0001$\u0001$\u0001%\u0001%\u0001%\u0005%\u025b\b%\n%\f%\u025e\t%\u0001"+
		"%\u0001%\u0005%\u0262\b%\n%\f%\u0265\t%\u0001%\u0001%\u0001%\u0005%\u026a"+
		"\b%\n%\f%\u026d\t%\u0001%\u0001%\u0005%\u0271\b%\n%\f%\u0274\t%\u0001"+
		"%\u0001%\u0003%\u0278\b%\u0003%\u027a\b%\u0001&\u0001&\u0001&\u0001&\u0001"+
		"\'\u0001\'\u0001\'\u0000\u00018(\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010"+
		"\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.02468:<>@BDFHJLN\u0000"+
		"\u0003\u0001\u0000\r\u000e\u0001\u0000\t\n\u0002\u0000\b\b\r\r\u02d7\u0000"+
		"T\u0001\u0000\u0000\u0000\u0002m\u0001\u0000\u0000\u0000\u0004o\u0001"+
		"\u0000\u0000\u0000\u0006s\u0001\u0000\u0000\u0000\b~\u0001\u0000\u0000"+
		"\u0000\n\u0088\u0001\u0000\u0000\u0000\f\u0093\u0001\u0000\u0000\u0000"+
		"\u000e\u009c\u0001\u0000\u0000\u0000\u0010\u00a8\u0001\u0000\u0000\u0000"+
		"\u0012\u00b2\u0001\u0000\u0000\u0000\u0014\u00b4\u0001\u0000\u0000\u0000"+
		"\u0016\u00c0\u0001\u0000\u0000\u0000\u0018\u00c2\u0001\u0000\u0000\u0000"+
		"\u001a\u00cd\u0001\u0000\u0000\u0000\u001c\u00dc\u0001\u0000\u0000\u0000"+
		"\u001e\u00ee\u0001\u0000\u0000\u0000 \u00f7\u0001\u0000\u0000\u0000\""+
		"\u0111\u0001\u0000\u0000\u0000$\u012b\u0001\u0000\u0000\u0000&\u0145\u0001"+
		"\u0000\u0000\u0000(\u0148\u0001\u0000\u0000\u0000*\u0157\u0001\u0000\u0000"+
		"\u0000,\u0167\u0001\u0000\u0000\u0000.\u0169\u0001\u0000\u0000\u00000"+
		"\u017d\u0001\u0000\u0000\u00002\u017f\u0001\u0000\u0000\u00004\u0190\u0001"+
		"\u0000\u0000\u00006\u019a\u0001\u0000\u0000\u00008\u01c1\u0001\u0000\u0000"+
		"\u0000:\u01e3\u0001\u0000\u0000\u0000<\u01e9\u0001\u0000\u0000\u0000>"+
		"\u01f4\u0001\u0000\u0000\u0000@\u0217\u0001\u0000\u0000\u0000B\u0219\u0001"+
		"\u0000\u0000\u0000D\u021d\u0001\u0000\u0000\u0000F\u0251\u0001\u0000\u0000"+
		"\u0000H\u0253\u0001\u0000\u0000\u0000J\u0279\u0001\u0000\u0000\u0000L"+
		"\u027b\u0001\u0000\u0000\u0000N\u027f\u0001\u0000\u0000\u0000PS\u0003"+
		"\u000e\u0007\u0000QS\u00030\u0018\u0000RP\u0001\u0000\u0000\u0000RQ\u0001"+
		"\u0000\u0000\u0000SV\u0001\u0000\u0000\u0000TR\u0001\u0000\u0000\u0000"+
		"TU\u0001\u0000\u0000\u0000UW\u0001\u0000\u0000\u0000VT\u0001\u0000\u0000"+
		"\u0000WX\u0005\u0000\u0000\u0001X\u0001\u0001\u0000\u0000\u0000YZ\u0003"+
		"\u0004\u0002\u0000Z^\u0005\u0004\u0000\u0000[]\u0005\r\u0000\u0000\\["+
		"\u0001\u0000\u0000\u0000]`\u0001\u0000\u0000\u0000^\\\u0001\u0000\u0000"+
		"\u0000^_\u0001\u0000\u0000\u0000_e\u0001\u0000\u0000\u0000`^\u0001\u0000"+
		"\u0000\u0000ad\u0003\u000e\u0007\u0000bd\u00030\u0018\u0000ca\u0001\u0000"+
		"\u0000\u0000cb\u0001\u0000\u0000\u0000dg\u0001\u0000\u0000\u0000ec\u0001"+
		"\u0000\u0000\u0000ef\u0001\u0000\u0000\u0000fh\u0001\u0000\u0000\u0000"+
		"ge\u0001\u0000\u0000\u0000hi\u0005\u0005\u0000\u0000in\u0001\u0000\u0000"+
		"\u0000jk\u0003\u0004\u0002\u0000kl\u0003\u0006\u0003\u0000ln\u0001\u0000"+
		"\u0000\u0000mY\u0001\u0000\u0000\u0000mj\u0001\u0000\u0000\u0000n\u0003"+
		"\u0001\u0000\u0000\u0000oq\u0005%\u0000\u0000pr\u0005-\u0000\u0000qp\u0001"+
		"\u0000\u0000\u0000qr\u0001\u0000\u0000\u0000r\u0005\u0001\u0000\u0000"+
		"\u0000st\u0005&\u0000\u0000ty\u00038\u001c\u0000uv\u0005\b\u0000\u0000"+
		"vx\u00038\u001c\u0000wu\u0001\u0000\u0000\u0000x{\u0001\u0000\u0000\u0000"+
		"yw\u0001\u0000\u0000\u0000yz\u0001\u0000\u0000\u0000z|\u0001\u0000\u0000"+
		"\u0000{y\u0001\u0000\u0000\u0000|}\u0003\b\u0004\u0000}\u0007\u0001\u0000"+
		"\u0000\u0000~\u0083\u0005\u0004\u0000\u0000\u007f\u0082\u0003\u000e\u0007"+
		"\u0000\u0080\u0082\u00030\u0018\u0000\u0081\u007f\u0001\u0000\u0000\u0000"+
		"\u0081\u0080\u0001\u0000\u0000\u0000\u0082\u0085\u0001\u0000\u0000\u0000"+
		"\u0083\u0081\u0001\u0000\u0000\u0000\u0083\u0084\u0001\u0000\u0000\u0000"+
		"\u0084\u0086\u0001\u0000\u0000\u0000\u0085\u0083\u0001\u0000\u0000\u0000"+
		"\u0086\u0087\u0005\u0005\u0000\u0000\u0087\t\u0001\u0000\u0000\u0000\u0088"+
		"\u0089\u0005\'\u0000\u0000\u0089\u008e\u00038\u001c\u0000\u008a\u008b"+
		"\u0005\b\u0000\u0000\u008b\u008d\u00038\u001c\u0000\u008c\u008a\u0001"+
		"\u0000\u0000\u0000\u008d\u0090\u0001\u0000\u0000\u0000\u008e\u008c\u0001"+
		"\u0000\u0000\u0000\u008e\u008f\u0001\u0000\u0000\u0000\u008f\u0091\u0001"+
		"\u0000\u0000\u0000\u0090\u008e\u0001\u0000\u0000\u0000\u0091\u0092\u0003"+
		"\f\u0006\u0000\u0092\u000b\u0001\u0000\u0000\u0000\u0093\u0095\u0005\u0004"+
		"\u0000\u0000\u0094\u0096\u00030\u0018\u0000\u0095\u0094\u0001\u0000\u0000"+
		"\u0000\u0096\u0097\u0001\u0000\u0000\u0000\u0097\u0095\u0001\u0000\u0000"+
		"\u0000\u0097\u0098\u0001\u0000\u0000\u0000\u0098\u0099\u0001\u0000\u0000"+
		"\u0000\u0099\u009a\u0005\u0005\u0000\u0000\u009a\r\u0001\u0000\u0000\u0000"+
		"\u009b\u009d\u0003\u0010\b\u0000\u009c\u009b\u0001\u0000\u0000\u0000\u009c"+
		"\u009d\u0001\u0000\u0000\u0000\u009d\u009e\u0001\u0000\u0000\u0000\u009e"+
		"\u009f\u0003\u0012\t\u0000\u009f\u00a1\u0005\u0001\u0000\u0000\u00a0\u00a2"+
		"\u0005\u0001\u0000\u0000\u00a1\u00a0\u0001\u0000\u0000\u0000\u00a1\u00a2"+
		"\u0001\u0000\u0000\u0000\u00a2\u00a4\u0001\u0000\u0000\u0000\u00a3\u00a5"+
		"\u0005\r\u0000\u0000\u00a4\u00a3\u0001\u0000\u0000\u0000\u00a4\u00a5\u0001"+
		"\u0000\u0000\u0000\u00a5\u00a6\u0001\u0000\u0000\u0000\u00a6\u00a7\u0003"+
		".\u0017\u0000\u00a7\u000f\u0001\u0000\u0000\u0000\u00a8\u00a9\u0005\u000f"+
		"\u0000\u0000\u00a9\u00aa\u0005\r\u0000\u0000\u00aa\u0011\u0001\u0000\u0000"+
		"\u0000\u00ab\u00ad\u0003\u0016\u000b\u0000\u00ac\u00ae\u0003\u0016\u000b"+
		"\u0000\u00ad\u00ac\u0001\u0000\u0000\u0000\u00ae\u00af\u0001\u0000\u0000"+
		"\u0000\u00af\u00ad\u0001\u0000\u0000\u0000\u00af\u00b0\u0001\u0000\u0000"+
		"\u0000\u00b0\u00b3\u0001\u0000\u0000\u0000\u00b1\u00b3\u0003\u0014\n\u0000"+
		"\u00b2\u00ab\u0001\u0000\u0000\u0000\u00b2\u00b1\u0001\u0000\u0000\u0000"+
		"\u00b3\u0013\u0001\u0000\u0000\u0000\u00b4\u00b5\u0005+\u0000\u0000\u00b5"+
		"\u0015\u0001\u0000\u0000\u0000\u00b6\u00c1\u0005+\u0000\u0000\u00b7\u00bc"+
		"\u0005*\u0000\u0000\u00b8\u00b9\u0005\u0002\u0000\u0000\u00b9\u00ba\u0005"+
		"*\u0000\u0000\u00ba\u00bc\u0005\u0003\u0000\u0000\u00bb\u00b7\u0001\u0000"+
		"\u0000\u0000\u00bb\u00b8\u0001\u0000\u0000\u0000\u00bc\u00c1\u0001\u0000"+
		"\u0000\u0000\u00bd\u00be\u0005\u0004\u0000\u0000\u00be\u00bf\u0005*\u0000"+
		"\u0000\u00bf\u00c1\u0005\u0005\u0000\u0000\u00c0\u00b6\u0001\u0000\u0000"+
		"\u0000\u00c0\u00bb\u0001\u0000\u0000\u0000\u00c0\u00bd\u0001\u0000\u0000"+
		"\u0000\u00c1\u0017\u0001\u0000\u0000\u0000\u00c2\u00c3\u0005 \u0000\u0000"+
		"\u00c3\u00c7\u0005\u0004\u0000\u0000\u00c4\u00c6\u0005\r\u0000\u0000\u00c5"+
		"\u00c4\u0001\u0000\u0000\u0000\u00c6\u00c9\u0001\u0000\u0000\u0000\u00c7"+
		"\u00c5\u0001\u0000\u0000\u0000\u00c7\u00c8\u0001\u0000\u0000\u0000\u00c8"+
		"\u00ca\u0001\u0000\u0000\u0000\u00c9\u00c7\u0001\u0000\u0000\u0000\u00ca"+
		"\u00cb\u0003\u001c\u000e\u0000\u00cb\u00cc\u0005\u0005\u0000\u0000\u00cc"+
		"\u0019\u0001\u0000\u0000\u0000\u00cd\u00ce\u0005!\u0000\u0000\u00ce\u00cf"+
		"\u00038\u001c\u0000\u00cf\u00d3\u0005\u0004\u0000\u0000\u00d0\u00d2\u0005"+
		"\r\u0000\u0000\u00d1\u00d0\u0001\u0000\u0000\u0000\u00d2\u00d5\u0001\u0000"+
		"\u0000\u0000\u00d3\u00d1\u0001\u0000\u0000\u0000\u00d3\u00d4\u0001\u0000"+
		"\u0000\u0000\u00d4\u00d6\u0001\u0000\u0000\u0000\u00d5\u00d3\u0001\u0000"+
		"\u0000\u0000\u00d6\u00d7\u0003\u001c\u000e\u0000\u00d7\u00d8\u0005\u0005"+
		"\u0000\u0000\u00d8\u001b\u0001\u0000\u0000\u0000\u00d9\u00db\u0003\u001e"+
		"\u000f\u0000\u00da\u00d9\u0001\u0000\u0000\u0000\u00db\u00de\u0001\u0000"+
		"\u0000\u0000\u00dc\u00da\u0001\u0000\u0000\u0000\u00dc\u00dd\u0001\u0000"+
		"\u0000\u0000\u00dd\u00e0\u0001\u0000\u0000\u0000\u00de\u00dc\u0001\u0000"+
		"\u0000\u0000\u00df\u00e1\u0003 \u0010\u0000\u00e0\u00df\u0001\u0000\u0000"+
		"\u0000\u00e0\u00e1\u0001\u0000\u0000\u0000\u00e1\u00e3\u0001\u0000\u0000"+
		"\u0000\u00e2\u00e4\u0003\"\u0011\u0000\u00e3\u00e2\u0001\u0000\u0000\u0000"+
		"\u00e3\u00e4\u0001\u0000\u0000\u0000\u00e4\u00e6\u0001\u0000\u0000\u0000"+
		"\u00e5\u00e7\u0003$\u0012\u0000\u00e6\u00e5\u0001\u0000\u0000\u0000\u00e6"+
		"\u00e7\u0001\u0000\u0000\u0000\u00e7\u00eb\u0001\u0000\u0000\u0000\u00e8"+
		"\u00ea\u0003(\u0014\u0000\u00e9\u00e8\u0001\u0000\u0000\u0000\u00ea\u00ed"+
		"\u0001\u0000\u0000\u0000\u00eb\u00e9\u0001\u0000\u0000\u0000\u00eb\u00ec"+
		"\u0001\u0000\u0000\u0000\u00ec\u001d\u0001\u0000\u0000\u0000\u00ed\u00eb"+
		"\u0001\u0000\u0000\u0000\u00ee\u00ef\u0005*\u0000\u0000\u00ef\u00f0\u0005"+
		"\u0001\u0000\u0000\u00f0\u00f4\u00038\u001c\u0000\u00f1\u00f3\u0005\r"+
		"\u0000\u0000\u00f2\u00f1\u0001\u0000\u0000\u0000\u00f3\u00f6\u0001\u0000"+
		"\u0000\u0000\u00f4\u00f2\u0001\u0000\u0000\u0000\u00f4\u00f5\u0001\u0000"+
		"\u0000\u0000\u00f5\u001f\u0001\u0000\u0000\u0000\u00f6\u00f4\u0001\u0000"+
		"\u0000\u0000\u00f7\u00f8\u0005\"\u0000\u0000\u00f8\u0106\u0005\u0004\u0000"+
		"\u0000\u00f9\u00fb\u0005\r\u0000\u0000\u00fa\u00f9\u0001\u0000\u0000\u0000"+
		"\u00fb\u00fe\u0001\u0000\u0000\u0000\u00fc\u00fa\u0001\u0000\u0000\u0000"+
		"\u00fc\u00fd\u0001\u0000\u0000\u0000\u00fd\u00ff\u0001\u0000\u0000\u0000"+
		"\u00fe\u00fc\u0001\u0000\u0000\u0000\u00ff\u0103\u0003&\u0013\u0000\u0100"+
		"\u0102\u0003D\"\u0000\u0101\u0100\u0001\u0000\u0000\u0000\u0102\u0105"+
		"\u0001\u0000\u0000\u0000\u0103\u0101\u0001\u0000\u0000\u0000\u0103\u0104"+
		"\u0001\u0000\u0000\u0000\u0104\u0107\u0001\u0000\u0000\u0000\u0105\u0103"+
		"\u0001\u0000\u0000\u0000\u0106\u00fc\u0001\u0000\u0000\u0000\u0107\u0108"+
		"\u0001\u0000\u0000\u0000\u0108\u0106\u0001\u0000\u0000\u0000\u0108\u0109"+
		"\u0001\u0000\u0000\u0000\u0109\u010a\u0001\u0000\u0000\u0000\u010a\u010e"+
		"\u0005\u0005\u0000\u0000\u010b\u010d\u0005\r\u0000\u0000\u010c\u010b\u0001"+
		"\u0000\u0000\u0000\u010d\u0110\u0001\u0000\u0000\u0000\u010e\u010c\u0001"+
		"\u0000\u0000\u0000\u010e\u010f\u0001\u0000\u0000\u0000\u010f!\u0001\u0000"+
		"\u0000\u0000\u0110\u010e\u0001\u0000\u0000\u0000\u0111\u0112\u0005#\u0000"+
		"\u0000\u0112\u0120\u0005\u0004\u0000\u0000\u0113\u0115\u0005\r\u0000\u0000"+
		"\u0114\u0113\u0001\u0000\u0000\u0000\u0115\u0118\u0001\u0000\u0000\u0000"+
		"\u0116\u0114\u0001\u0000\u0000\u0000\u0116\u0117\u0001\u0000\u0000\u0000"+
		"\u0117\u0119\u0001\u0000\u0000\u0000\u0118\u0116\u0001\u0000\u0000\u0000"+
		"\u0119\u011d\u0003&\u0013\u0000\u011a\u011c\u0003D\"\u0000\u011b\u011a"+
		"\u0001\u0000\u0000\u0000\u011c\u011f\u0001\u0000\u0000\u0000\u011d\u011b"+
		"\u0001\u0000\u0000\u0000\u011d\u011e\u0001\u0000\u0000\u0000\u011e\u0121"+
		"\u0001\u0000\u0000\u0000\u011f\u011d\u0001\u0000\u0000\u0000\u0120\u0116"+
		"\u0001\u0000\u0000\u0000\u0121\u0122\u0001\u0000\u0000\u0000\u0122\u0120"+
		"\u0001\u0000\u0000\u0000\u0122\u0123\u0001\u0000\u0000\u0000\u0123\u0124"+
		"\u0001\u0000\u0000\u0000\u0124\u0128\u0005\u0005\u0000\u0000\u0125\u0127"+
		"\u0005\r\u0000\u0000\u0126\u0125\u0001\u0000\u0000\u0000\u0127\u012a\u0001"+
		"\u0000\u0000\u0000\u0128\u0126\u0001\u0000\u0000\u0000\u0128\u0129\u0001"+
		"\u0000\u0000\u0000\u0129#\u0001\u0000\u0000\u0000\u012a\u0128\u0001\u0000"+
		"\u0000\u0000\u012b\u012c\u0005$\u0000\u0000\u012c\u013a\u0005\u0004\u0000"+
		"\u0000\u012d\u012f\u0005\r\u0000\u0000\u012e\u012d\u0001\u0000\u0000\u0000"+
		"\u012f\u0132\u0001\u0000\u0000\u0000\u0130\u012e\u0001\u0000\u0000\u0000"+
		"\u0130\u0131\u0001\u0000\u0000\u0000\u0131\u0133\u0001\u0000\u0000\u0000"+
		"\u0132\u0130\u0001\u0000\u0000\u0000\u0133\u0137\u0003&\u0013\u0000\u0134"+
		"\u0136\u0003D\"\u0000\u0135\u0134\u0001\u0000\u0000\u0000\u0136\u0139"+
		"\u0001\u0000\u0000\u0000\u0137\u0135\u0001\u0000\u0000\u0000\u0137\u0138"+
		"\u0001\u0000\u0000\u0000\u0138\u013b\u0001\u0000\u0000\u0000\u0139\u0137"+
		"\u0001\u0000\u0000\u0000\u013a\u0130\u0001\u0000\u0000\u0000\u013b\u013c"+
		"\u0001\u0000\u0000\u0000\u013c\u013a\u0001\u0000\u0000\u0000\u013c\u013d"+
		"\u0001\u0000\u0000\u0000\u013d\u013e\u0001\u0000\u0000\u0000\u013e\u0142"+
		"\u0005\u0005\u0000\u0000\u013f\u0141\u0005\r\u0000\u0000\u0140\u013f\u0001"+
		"\u0000\u0000\u0000\u0141\u0144\u0001\u0000\u0000\u0000\u0142\u0140\u0001"+
		"\u0000\u0000\u0000\u0142\u0143\u0001\u0000\u0000\u0000\u0143%\u0001\u0000"+
		"\u0000\u0000\u0144\u0142\u0001\u0000\u0000\u0000\u0145\u0146\u0005*\u0000"+
		"\u0000\u0146\'\u0001\u0000\u0000\u0000\u0147\u0149\u0003\u0010\b\u0000"+
		"\u0148\u0147\u0001\u0000\u0000\u0000\u0148\u0149\u0001\u0000\u0000\u0000"+
		"\u0149\u014a\u0001\u0000\u0000\u0000\u014a\u014b\u0003*\u0015\u0000\u014b"+
		"\u014d\u0005\u0001\u0000\u0000\u014c\u014e\u0005\r\u0000\u0000\u014d\u014c"+
		"\u0001\u0000\u0000\u0000\u014d\u014e\u0001\u0000\u0000\u0000\u014e\u014f"+
		"\u0001\u0000\u0000\u0000\u014f\u0153\u0003.\u0017\u0000\u0150\u0152\u0005"+
		"\r\u0000\u0000\u0151\u0150\u0001\u0000\u0000\u0000\u0152\u0155\u0001\u0000"+
		"\u0000\u0000\u0153\u0151\u0001\u0000\u0000\u0000\u0153\u0154\u0001\u0000"+
		"\u0000\u0000\u0154)\u0001\u0000\u0000\u0000\u0155\u0153\u0001\u0000\u0000"+
		"\u0000\u0156\u0158\u0003,\u0016\u0000\u0157\u0156\u0001\u0000\u0000\u0000"+
		"\u0158\u0159\u0001\u0000\u0000\u0000\u0159\u0157\u0001\u0000\u0000\u0000"+
		"\u0159\u015a\u0001\u0000\u0000\u0000\u015a+\u0001\u0000\u0000\u0000\u015b"+
		"\u0168\u0005+\u0000\u0000\u015c\u0168\u0005*\u0000\u0000\u015d\u015e\u0005"+
		"\u0002\u0000\u0000\u015e\u015f\u0005*\u0000\u0000\u015f\u0168\u0005\u0003"+
		"\u0000\u0000\u0160\u0168\u0005(\u0000\u0000\u0161\u0162\u0005\u0002\u0000"+
		"\u0000\u0162\u0163\u0005(\u0000\u0000\u0163\u0168\u0005\u0003\u0000\u0000"+
		"\u0164\u0165\u0005\u0004\u0000\u0000\u0165\u0166\u0005*\u0000\u0000\u0166"+
		"\u0168\u0005\u0005\u0000\u0000\u0167\u015b\u0001\u0000\u0000\u0000\u0167"+
		"\u015c\u0001\u0000\u0000\u0000\u0167\u015d\u0001\u0000\u0000\u0000\u0167"+
		"\u0160\u0001\u0000\u0000\u0000\u0167\u0161\u0001\u0000\u0000\u0000\u0167"+
		"\u0164\u0001\u0000\u0000\u0000\u0168-\u0001\u0000\u0000\u0000\u0169\u016d"+
		"\u0005\u0004\u0000\u0000\u016a\u016c\u00030\u0018\u0000\u016b\u016a\u0001"+
		"\u0000\u0000\u0000\u016c\u016f\u0001\u0000\u0000\u0000\u016d\u016b\u0001"+
		"\u0000\u0000\u0000\u016d\u016e\u0001\u0000\u0000\u0000\u016e\u0170\u0001"+
		"\u0000\u0000\u0000\u016f\u016d\u0001\u0000\u0000\u0000\u0170\u0171\u0005"+
		"\u0005\u0000\u0000\u0171/\u0001\u0000\u0000\u0000\u0172\u017e\u00034\u001a"+
		"\u0000\u0173\u017e\u00036\u001b\u0000\u0174\u017e\u00038\u001c\u0000\u0175"+
		"\u017e\u0005\u001f\u0000\u0000\u0176\u0178\u0005\u001e\u0000\u0000\u0177"+
		"\u0179\u00038\u001c\u0000\u0178\u0177\u0001\u0000\u0000\u0000\u0178\u0179"+
		"\u0001\u0000\u0000\u0000\u0179\u017e\u0001\u0000\u0000\u0000\u017a\u017e"+
		"\u0003\n\u0005\u0000\u017b\u017e\u0003\u0006\u0003\u0000\u017c\u017e\u0003"+
		"2\u0019\u0000\u017d\u0172\u0001\u0000\u0000\u0000\u017d\u0173\u0001\u0000"+
		"\u0000\u0000\u017d\u0174\u0001\u0000\u0000\u0000\u017d\u0175\u0001\u0000"+
		"\u0000\u0000\u017d\u0176\u0001\u0000\u0000\u0000\u017d\u017a\u0001\u0000"+
		"\u0000\u0000\u017d\u017b\u0001\u0000\u0000\u0000\u017d\u017c\u0001\u0000"+
		"\u0000\u0000\u017e1\u0001\u0000\u0000\u0000\u017f\u0180\u0007\u0000\u0000"+
		"\u0000\u01803\u0001\u0000\u0000\u0000\u0181\u0182\u0005*\u0000\u0000\u0182"+
		"\u0183\u0005\u0001\u0000\u0000\u0183\u0191\u00038\u001c\u0000\u0184\u0185"+
		"\u0005)\u0000\u0000\u0185\u0186\u0005\u0001\u0000\u0000\u0186\u0191\u0003"+
		"8\u001c\u0000\u0187\u0189\u0005*\u0000\u0000\u0188\u018a\u0003<\u001e"+
		"\u0000\u0189\u0188\u0001\u0000\u0000\u0000\u018a\u018b\u0001\u0000\u0000"+
		"\u0000\u018b\u0189\u0001\u0000\u0000\u0000\u018b\u018c\u0001\u0000\u0000"+
		"\u0000\u018c\u018d\u0001\u0000\u0000\u0000\u018d\u018e\u0005\u0001\u0000"+
		"\u0000\u018e\u018f\u00038\u001c\u0000\u018f\u0191\u0001\u0000\u0000\u0000"+
		"\u0190\u0181\u0001\u0000\u0000\u0000\u0190\u0184\u0001\u0000\u0000\u0000"+
		"\u0190\u0187\u0001\u0000\u0000\u0000\u01915\u0001\u0000\u0000\u0000\u0192"+
		"\u0197\u00038\u001c\u0000\u0193\u0194\u0005\b\u0000\u0000\u0194\u0196"+
		"\u00038\u001c\u0000\u0195\u0193\u0001\u0000\u0000\u0000\u0196\u0199\u0001"+
		"\u0000\u0000\u0000\u0197\u0195\u0001\u0000\u0000\u0000\u0197\u0198\u0001"+
		"\u0000\u0000\u0000\u0198\u019b\u0001\u0000\u0000\u0000\u0199\u0197\u0001"+
		"\u0000\u0000\u0000\u019a\u0192\u0001\u0000\u0000\u0000\u019a\u019b\u0001"+
		"\u0000\u0000\u0000\u019b\u019c\u0001\u0000\u0000\u0000\u019c\u019d\u0007"+
		"\u0001\u0000\u0000\u019d7\u0001\u0000\u0000\u0000\u019e\u019f\u0006\u001c"+
		"\uffff\uffff\u0000\u019f\u01a0\u0005\u001b\u0000\u0000\u01a0\u01c2\u0003"+
		"8\u001c\u001b\u01a1\u01c2\u0003:\u001d\u0000\u01a2\u01c2\u0003>\u001f"+
		"\u0000\u01a3\u01c2\u0005)\u0000\u0000\u01a4\u01c2\u0005*\u0000\u0000\u01a5"+
		"\u01c2\u0005(\u0000\u0000\u01a6\u01c2\u0005\u0011\u0000\u0000\u01a7\u01c2"+
		"\u0005,\u0000\u0000\u01a8\u01c2\u0005\u001c\u0000\u0000\u01a9\u01c2\u0005"+
		"\u001d\u0000\u0000\u01aa\u01c2\u0005-\u0000\u0000\u01ab\u01c2\u0003.\u0017"+
		"\u0000\u01ac\u01c2\u0003B!\u0000\u01ad\u01c2\u0003H$\u0000\u01ae\u01c2"+
		"\u0003\u0002\u0001\u0000\u01af\u01c2\u0003\u0018\f\u0000\u01b0\u01c2\u0003"+
		"\u001a\r\u0000\u01b1\u01b5\u0005\u0002\u0000\u0000\u01b2\u01b4\u0005\r"+
		"\u0000\u0000\u01b3\u01b2\u0001\u0000\u0000\u0000\u01b4\u01b7\u0001\u0000"+
		"\u0000\u0000\u01b5\u01b3\u0001\u0000\u0000\u0000\u01b5\u01b6\u0001\u0000"+
		"\u0000\u0000\u01b6\u01b8\u0001\u0000\u0000\u0000\u01b7\u01b5\u0001\u0000"+
		"\u0000\u0000\u01b8\u01bc\u00038\u001c\u0000\u01b9\u01bb\u0005\r\u0000"+
		"\u0000\u01ba\u01b9\u0001\u0000\u0000\u0000\u01bb\u01be\u0001\u0000\u0000"+
		"\u0000\u01bc\u01ba\u0001\u0000\u0000\u0000\u01bc\u01bd\u0001\u0000\u0000"+
		"\u0000\u01bd\u01bf\u0001\u0000\u0000\u0000\u01be\u01bc\u0001\u0000\u0000"+
		"\u0000\u01bf\u01c0\u0005\u0003\u0000\u0000\u01c0\u01c2\u0001\u0000\u0000"+
		"\u0000\u01c1\u019e\u0001\u0000\u0000\u0000\u01c1\u01a1\u0001\u0000\u0000"+
		"\u0000\u01c1\u01a2\u0001\u0000\u0000\u0000\u01c1\u01a3\u0001\u0000\u0000"+
		"\u0000\u01c1\u01a4\u0001\u0000\u0000\u0000\u01c1\u01a5\u0001\u0000\u0000"+
		"\u0000\u01c1\u01a6\u0001\u0000\u0000\u0000\u01c1\u01a7\u0001\u0000\u0000"+
		"\u0000\u01c1\u01a8\u0001\u0000\u0000\u0000\u01c1\u01a9\u0001\u0000\u0000"+
		"\u0000\u01c1\u01aa\u0001\u0000\u0000\u0000\u01c1\u01ab\u0001\u0000\u0000"+
		"\u0000\u01c1\u01ac\u0001\u0000\u0000\u0000\u01c1\u01ad\u0001\u0000\u0000"+
		"\u0000\u01c1\u01ae\u0001\u0000\u0000\u0000\u01c1\u01af\u0001\u0000\u0000"+
		"\u0000\u01c1\u01b0\u0001\u0000\u0000\u0000\u01c1\u01b1\u0001\u0000\u0000"+
		"\u0000\u01c2\u01e0\u0001\u0000\u0000\u0000\u01c3\u01c4\n\u001a\u0000\u0000"+
		"\u01c4\u01c5\u0005\u0013\u0000\u0000\u01c5\u01df\u00038\u001c\u001b\u01c6"+
		"\u01c7\n\u0019\u0000\u0000\u01c7\u01c8\u0005\u0012\u0000\u0000\u01c8\u01df"+
		"\u00038\u001c\u001a\u01c9\u01ca\n\u0018\u0000\u0000\u01ca\u01cb\u0005"+
		"\u0014\u0000\u0000\u01cb\u01df\u00038\u001c\u0019\u01cc\u01cd\n\u0017"+
		"\u0000\u0000\u01cd\u01ce\u0005\u0015\u0000\u0000\u01ce\u01df\u00038\u001c"+
		"\u0018\u01cf\u01d0\n\u0016\u0000\u0000\u01d0\u01d1\u0005\u0017\u0000\u0000"+
		"\u01d1\u01df\u00038\u001c\u0017\u01d2\u01d3\n\u0015\u0000\u0000\u01d3"+
		"\u01d4\u0005\u0016\u0000\u0000\u01d4\u01df\u00038\u001c\u0016\u01d5\u01d6"+
		"\n\u0014\u0000\u0000\u01d6\u01d7\u0005\u0018\u0000\u0000\u01d7\u01df\u0003"+
		"8\u001c\u0015\u01d8\u01d9\n\u0013\u0000\u0000\u01d9\u01da\u0005\u0019"+
		"\u0000\u0000\u01da\u01df\u00038\u001c\u0014\u01db\u01dc\n\u0012\u0000"+
		"\u0000\u01dc\u01dd\u0005\u001a\u0000\u0000\u01dd\u01df\u00038\u001c\u0013"+
		"\u01de\u01c3\u0001\u0000\u0000\u0000\u01de\u01c6\u0001\u0000\u0000\u0000"+
		"\u01de\u01c9\u0001\u0000\u0000\u0000\u01de\u01cc\u0001\u0000\u0000\u0000"+
		"\u01de\u01cf\u0001\u0000\u0000\u0000\u01de\u01d2\u0001\u0000\u0000\u0000"+
		"\u01de\u01d5\u0001\u0000\u0000\u0000\u01de\u01d8\u0001\u0000\u0000\u0000"+
		"\u01de\u01db\u0001\u0000\u0000\u0000\u01df\u01e2\u0001\u0000\u0000\u0000"+
		"\u01e0\u01de\u0001\u0000\u0000\u0000\u01e0\u01e1\u0001\u0000\u0000\u0000"+
		"\u01e19\u0001\u0000\u0000\u0000\u01e2\u01e0\u0001\u0000\u0000\u0000\u01e3"+
		"\u01e5\u0005*\u0000\u0000\u01e4\u01e6\u0003<\u001e\u0000\u01e5\u01e4\u0001"+
		"\u0000\u0000\u0000\u01e6\u01e7\u0001\u0000\u0000\u0000\u01e7\u01e5\u0001"+
		"\u0000\u0000\u0000\u01e7\u01e8\u0001\u0000\u0000\u0000\u01e8;\u0001\u0000"+
		"\u0000\u0000\u01e9\u01ea\u0005\u0006\u0000\u0000\u01ea\u01eb\u00038\u001c"+
		"\u0000\u01eb\u01ec\u0005\u0007\u0000\u0000\u01ec=\u0001\u0000\u0000\u0000"+
		"\u01ed\u01ef\u0003@ \u0000\u01ee\u01f0\u0003@ \u0000\u01ef\u01ee\u0001"+
		"\u0000\u0000\u0000\u01f0\u01f1\u0001\u0000\u0000\u0000\u01f1\u01ef\u0001"+
		"\u0000\u0000\u0000\u01f1\u01f2\u0001\u0000\u0000\u0000\u01f2\u01f5\u0001"+
		"\u0000\u0000\u0000\u01f3\u01f5\u0003\u0014\n\u0000\u01f4\u01ed\u0001\u0000"+
		"\u0000\u0000\u01f4\u01f3\u0001\u0000\u0000\u0000\u01f5?\u0001\u0000\u0000"+
		"\u0000\u01f6\u0218\u0005+\u0000\u0000\u01f7\u01f8\u0005\u001b\u0000\u0000"+
		"\u01f8\u0218\u00038\u001c\u0000\u01f9\u0218\u0005)\u0000\u0000\u01fa\u0218"+
		"\u0005*\u0000\u0000\u01fb\u01ff\u0005\u0002\u0000\u0000\u01fc\u01fe\u0005"+
		"\r\u0000\u0000\u01fd\u01fc\u0001\u0000\u0000\u0000\u01fe\u0201\u0001\u0000"+
		"\u0000\u0000\u01ff\u01fd\u0001\u0000\u0000\u0000\u01ff\u0200\u0001\u0000"+
		"\u0000\u0000\u0200\u0202\u0001\u0000\u0000\u0000\u0201\u01ff\u0001\u0000"+
		"\u0000\u0000\u0202\u0206\u00038\u001c\u0000\u0203\u0205\u0005\r\u0000"+
		"\u0000\u0204\u0203\u0001\u0000\u0000\u0000\u0205\u0208\u0001\u0000\u0000"+
		"\u0000\u0206\u0204\u0001\u0000\u0000\u0000\u0206\u0207\u0001\u0000\u0000"+
		"\u0000\u0207\u0209\u0001\u0000\u0000\u0000\u0208\u0206\u0001\u0000\u0000"+
		"\u0000\u0209\u020a\u0005\u0003\u0000\u0000\u020a\u0218\u0001\u0000\u0000"+
		"\u0000\u020b\u0218\u0005(\u0000\u0000\u020c\u020d\u0005\u0002\u0000\u0000"+
		"\u020d\u020e\u0005(\u0000\u0000\u020e\u0218\u0005\u0003\u0000\u0000\u020f"+
		"\u0218\u0005\u0011\u0000\u0000\u0210\u0218\u0005,\u0000\u0000\u0211\u0218"+
		"\u0005\u001c\u0000\u0000\u0212\u0218\u0005\u001d\u0000\u0000\u0213\u0218"+
		"\u0005-\u0000\u0000\u0214\u0218\u0003.\u0017\u0000\u0215\u0218\u0003B"+
		"!\u0000\u0216\u0218\u0003H$\u0000\u0217\u01f6\u0001\u0000\u0000\u0000"+
		"\u0217\u01f7\u0001\u0000\u0000\u0000\u0217\u01f9\u0001\u0000\u0000\u0000"+
		"\u0217\u01fa\u0001\u0000\u0000\u0000\u0217\u01fb\u0001\u0000\u0000\u0000"+
		"\u0217\u020b\u0001\u0000\u0000\u0000\u0217\u020c\u0001\u0000\u0000\u0000"+
		"\u0217\u020f\u0001\u0000\u0000\u0000\u0217\u0210\u0001\u0000\u0000\u0000"+
		"\u0217\u0211\u0001\u0000\u0000\u0000\u0217\u0212\u0001\u0000\u0000\u0000"+
		"\u0217\u0213\u0001\u0000\u0000\u0000\u0217\u0214\u0001\u0000\u0000\u0000"+
		"\u0217\u0215\u0001\u0000\u0000\u0000\u0217\u0216\u0001\u0000\u0000\u0000"+
		"\u0218A\u0001\u0000\u0000\u0000\u0219\u021a\u0005\u0006\u0000\u0000\u021a"+
		"\u021b\u0003F#\u0000\u021b\u021c\u0005\u0007\u0000\u0000\u021cC\u0001"+
		"\u0000\u0000\u0000\u021d\u021e\u0007\u0002\u0000\u0000\u021eE\u0001\u0000"+
		"\u0000\u0000\u021f\u0224\u00038\u001c\u0000\u0220\u0221\u0005\b\u0000"+
		"\u0000\u0221\u0223\u00038\u001c\u0000\u0222\u0220\u0001\u0000\u0000\u0000"+
		"\u0223\u0226\u0001\u0000\u0000\u0000\u0224\u0222\u0001\u0000\u0000\u0000"+
		"\u0224\u0225\u0001\u0000\u0000\u0000\u0225\u0228\u0001\u0000\u0000\u0000"+
		"\u0226\u0224\u0001\u0000\u0000\u0000\u0227\u021f\u0001\u0000\u0000\u0000"+
		"\u0227\u0228\u0001\u0000\u0000\u0000\u0228\u0252\u0001\u0000\u0000\u0000"+
		"\u0229\u022d\u0005\u0004\u0000\u0000\u022a\u022c\u0005\r\u0000\u0000\u022b"+
		"\u022a\u0001\u0000\u0000\u0000\u022c\u022f\u0001\u0000\u0000\u0000\u022d"+
		"\u022b\u0001\u0000\u0000\u0000\u022d\u022e\u0001\u0000\u0000\u0000\u022e"+
		"\u0241\u0001\u0000\u0000\u0000\u022f\u022d\u0001\u0000\u0000\u0000\u0230"+
		"\u023c\u00038\u001c\u0000\u0231\u0235\u0003D\"\u0000\u0232\u0234\u0005"+
		"\r\u0000\u0000\u0233\u0232\u0001\u0000\u0000\u0000\u0234\u0237\u0001\u0000"+
		"\u0000\u0000\u0235\u0233\u0001\u0000\u0000\u0000\u0235\u0236\u0001\u0000"+
		"\u0000\u0000\u0236\u0238\u0001\u0000\u0000\u0000\u0237\u0235\u0001\u0000"+
		"\u0000\u0000\u0238\u0239\u00038\u001c\u0000\u0239\u023b\u0001\u0000\u0000"+
		"\u0000\u023a\u0231\u0001\u0000\u0000\u0000\u023b\u023e\u0001\u0000\u0000"+
		"\u0000\u023c\u023a\u0001\u0000\u0000\u0000\u023c\u023d\u0001\u0000\u0000"+
		"\u0000\u023d\u0240\u0001\u0000\u0000\u0000\u023e\u023c\u0001\u0000\u0000"+
		"\u0000\u023f\u0230\u0001\u0000\u0000\u0000\u0240\u0243\u0001\u0000\u0000"+
		"\u0000\u0241\u023f\u0001\u0000\u0000\u0000\u0241\u0242\u0001\u0000\u0000"+
		"\u0000\u0242\u0244\u0001\u0000\u0000\u0000\u0243\u0241\u0001\u0000\u0000"+
		"\u0000\u0244\u0248\u0005\u0005\u0000\u0000\u0245\u0247\u0005\r\u0000\u0000"+
		"\u0246\u0245\u0001\u0000\u0000\u0000\u0247\u024a\u0001\u0000\u0000\u0000"+
		"\u0248\u0246\u0001\u0000\u0000\u0000\u0248\u0249\u0001\u0000\u0000\u0000"+
		"\u0249\u0252\u0001\u0000\u0000\u0000\u024a\u0248\u0001\u0000\u0000\u0000"+
		"\u024b\u024d\u0005\r\u0000\u0000\u024c\u024b\u0001\u0000\u0000\u0000\u024d"+
		"\u0250\u0001\u0000\u0000\u0000\u024e\u024c\u0001\u0000\u0000\u0000\u024e"+
		"\u024f\u0001\u0000\u0000\u0000\u024f\u0252\u0001\u0000\u0000\u0000\u0250"+
		"\u024e\u0001\u0000\u0000\u0000\u0251\u0227\u0001\u0000\u0000\u0000\u0251"+
		"\u0229\u0001\u0000\u0000\u0000\u0251\u024e\u0001\u0000\u0000\u0000\u0252"+
		"G\u0001\u0000\u0000\u0000\u0253\u0254\u0005\u0006\u0000\u0000\u0254\u0255"+
		"\u0003J%\u0000\u0255\u0256\u0005\u0007\u0000\u0000\u0256I\u0001\u0000"+
		"\u0000\u0000\u0257\u025c\u0003L&\u0000\u0258\u0259\u0005\b\u0000\u0000"+
		"\u0259\u025b\u0003L&\u0000\u025a\u0258\u0001\u0000\u0000\u0000\u025b\u025e"+
		"\u0001\u0000\u0000\u0000\u025c\u025a\u0001\u0000\u0000\u0000\u025c\u025d"+
		"\u0001\u0000\u0000\u0000\u025d\u027a\u0001\u0000\u0000\u0000\u025e\u025c"+
		"\u0001\u0000\u0000\u0000\u025f\u0263\u0005\u0004\u0000\u0000\u0260\u0262"+
		"\u0005\r\u0000\u0000\u0261\u0260\u0001\u0000\u0000\u0000\u0262\u0265\u0001"+
		"\u0000\u0000\u0000\u0263\u0261\u0001\u0000\u0000\u0000\u0263\u0264\u0001"+
		"\u0000\u0000\u0000\u0264\u0266\u0001\u0000\u0000\u0000\u0265\u0263\u0001"+
		"\u0000\u0000\u0000\u0266\u0272\u0003L&\u0000\u0267\u026b\u0003D\"\u0000"+
		"\u0268\u026a\u0005\r\u0000\u0000\u0269\u0268\u0001\u0000\u0000\u0000\u026a"+
		"\u026d\u0001\u0000\u0000\u0000\u026b\u0269\u0001\u0000\u0000\u0000\u026b"+
		"\u026c\u0001\u0000\u0000\u0000\u026c\u026e\u0001\u0000\u0000\u0000\u026d"+
		"\u026b\u0001\u0000\u0000\u0000\u026e\u026f\u0003L&\u0000\u026f\u0271\u0001"+
		"\u0000\u0000\u0000\u0270\u0267\u0001\u0000\u0000\u0000\u0271\u0274\u0001"+
		"\u0000\u0000\u0000\u0272\u0270\u0001\u0000\u0000\u0000\u0272\u0273\u0001"+
		"\u0000\u0000\u0000\u0273\u0275\u0001\u0000\u0000\u0000\u0274\u0272\u0001"+
		"\u0000\u0000\u0000\u0275\u0277\u0005\u0005\u0000\u0000\u0276\u0278\u0005"+
		"\r\u0000\u0000\u0277\u0276\u0001\u0000\u0000\u0000\u0277\u0278\u0001\u0000"+
		"\u0000\u0000\u0278\u027a\u0001\u0000\u0000\u0000\u0279\u0257\u0001\u0000"+
		"\u0000\u0000\u0279\u025f\u0001\u0000\u0000\u0000\u027aK\u0001\u0000\u0000"+
		"\u0000\u027b\u027c\u0005-\u0000\u0000\u027c\u027d\u0005\u0001\u0000\u0000"+
		"\u027d\u027e\u0003N\'\u0000\u027eM\u0001\u0000\u0000\u0000\u027f\u0280"+
		"\u00038\u001c\u0000\u0280O\u0001\u0000\u0000\u0000MRT^cemqy\u0081\u0083"+
		"\u008e\u0097\u009c\u00a1\u00a4\u00af\u00b2\u00bb\u00c0\u00c7\u00d3\u00dc"+
		"\u00e0\u00e3\u00e6\u00eb\u00f4\u00fc\u0103\u0108\u010e\u0116\u011d\u0122"+
		"\u0128\u0130\u0137\u013c\u0142\u0148\u014d\u0153\u0159\u0167\u016d\u0178"+
		"\u017d\u018b\u0190\u0197\u019a\u01b5\u01bc\u01c1\u01de\u01e0\u01e7\u01f1"+
		"\u01f4\u01ff\u0206\u0217\u0224\u0227\u022d\u0235\u023c\u0241\u0248\u024e"+
		"\u0251\u025c\u0263\u026b\u0272\u0277\u0279";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}