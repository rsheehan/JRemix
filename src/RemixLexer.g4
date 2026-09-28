// remix lexer grammar
lexer grammar RemixLexer;

// Written to follow after the PreProcess class has dealt with implicit blocks.

/*
	Lexer rules
*/

COLON				: ':' ;
LPAREN				: '(' ;
RPAREN				: ')' ;
LBLOCK				: '[' ;
RBLOCK				: ']' ;
LBRACE				: '{' ;
RBRACE				: '}' ;
COMMA				: ',' ;
ENDPRINT			: '~' ;
PRINTLN				: ('\\n' | '↲') ;

SPACE				: (' ' | '\t') -> channel(HIDDEN) ;
CONT				: ( // also used to help deal with PreProcess output
					'\n' '\t'* ELLIPSIS
					|
					ELLIPSIS
					) -> channel(HIDDEN) ;

fragment ELLIPSIS	: '...' | '…' ;

EOL					: '\n' ;	// End Of Line
EOS					: '.' ;		// End Of Statement

fragment DIGIT		: [0-9] ;

DOC_COMMENT			: EOL '\t'* '=-' EOL .*? EOL '\t'* '=-' ;	// because multiline does not work
																// syntax highlighting

COMMENT				: (
					COMMENT_LINE // first visible character '-'
					|
					REMAINING_COMMENT // everything on a line following ';'
					) -> channel(HIDDEN) ;

fragment COMMENT_LINE		: {getCharPositionInLine() == 0}? '\t'* '-' ~'\n'* ;
fragment REMAINING_COMMENT	: ';' ~'\n'* ;
COMMENT_SECTION	: {getCharPositionInLine() == 0}? '\t'* '=' ~'\n'* -> pushMode(IN_COMMENT), channel(HIDDEN) ;

NUMBER				: '-'? ( 'pi' | 'π' | DIGIT+ ('.' DIGIT+)?) ;
ADD					: ' + ' | ' - ' ;
MUL					: ' * ' | ' × ' | ' / ' | ' ÷ ' | ' % ' ;
LESS				: ' < ' ;
GREATER				: ' > ' ;
LESSEQUAL			: ' <= ' | ' ≤ ' ;
GREATEREQUAL		: ' >= ' | ' ≥ ' ;
EQUAL				: ' = ' ;
NOTEQUAL			: ' != ' | ' ≠ ' ;
CONCAT				: ' (+) ' | ' ⊕ ' ;
MINUS				: '-' ;

// Keywords - consider not using these to provide greater flexibility.
NULL				: 'null' ;
BOOLEAN				: 'true' | 'false' ;
RETURN				: 'return' ;
REDO				: 'redo' ;
CREATE				: 'create' ;
EXTEND				: 'extend' ;
GETTERSETTER		: 'getter' 's'? '/setter' 's'? ;
GETTER				: 'getter' 's'? ;
SETTER				: 'setter' 's'? ;
LIBRARY				: 'library' ;
USING				: 'using' ;

SELFREF				: 'ME' | 'MY' ;

CONSTANT			: CAPITAL (CAPITAL | '-' | DIGIT)* ;
IDENTIFIER			: '\'' IDCHAR* '\''
					| '#' FIRSTCHAR CHARACTER* ;
BAD_IDENTIFIER		: '\'' .*? ;
WORD				: FIRSTCHAR CHARACTER* ;
WORDPRODUCT			: '-'? DIGIT+ ('.' DIGIT+)? (IDENTIFIER | 'π') ;

STRING_START		: '"' -> pushMode(IN_STRING) ;

// everything apart from white space, newline or special is a character
fragment IDCHAR		: ~['\t\n↲] ;
fragment FIRSTCHAR	: ~[.()[\]{,};:—⫾…'\-0-9" ~\t\n↲] ; // ⊕+\-*×÷%=≠<≤>≥
fragment CHARACTER	: ~[.()[\]{,};:—⫾…'" ~\t\n↲] ; // ⊕+*×÷%=≠<≤>≥

fragment CAPITAL 	: [A-Z\u0391-\u03A9] ; // Roman and Greek capital letters

//EMPTYIDENTIFIER		: ('\'\'') -> channel(HIDDEN) ; // only used to prevent lex error in the editor
//ERROR_TOKEN			: . ;

mode IN_COMMENT;
	COMMENT_END		: ({getCharPositionInLine() == 0}? '\t'* '=' ~'\n'*) -> popMode, channel(HIDDEN) ;
	COMMENT_INCOMPLETE : EOF -> popMode, channel(HIDDEN) ;
	COMMENT_TEXT	: . -> channel(HIDDEN) ;

mode IN_STRING;
	STRING_TEXT			: (~'"' | '\\"') ;
	STRING_END			: '"' -> popMode ;
	STRING_INCOMPLETE 	: EOF -> popMode ;