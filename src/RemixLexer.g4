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

SPACE				: (' ' | '\t') -> skip ;
CONT				: ( // also used to help deal with PreProcess output
					'\n' '\t'* ELLIPSIS
					|
					ELLIPSIS '\n'
					|
					ELLIPSIS
					) -> skip ;

fragment ELLIPSIS	: '...' | '…' ;

EOL					: '\n' ;	// End Of Line
EOS					: '.' ;		// End Of Statement

fragment DIGIT		: [0-9] ;

DOC_COMMENT			: EOL '=-' EOL .*? EOL '=-' EOL ;

COMMENT				: (
					COMMENT_LINE // first visible character '-'
					|
					COMMENT_SECTION // lines surrounded with "="
					|
					REMAINING_COMMENT // everything on a line following ';'
					) -> skip ;

fragment COMMENT_LINE		: EOL [ \t]* '-' ~'\n'* ;
fragment COMMENT_SECTION	: EOL '=' .*? EOL '=' ~'\n'* ;
fragment REMAINING_COMMENT	: ';' ~'\n'* ;

NUMBER				: 'π' | '-'? DIGIT+ ('.' DIGIT+)? ;
ADD					: ' + ' ;
SUB					: ' - ' ;
MUL					: ' * ' | ' × ' ;
DIV					: ' / ' | ' ÷ ' ;
MOD					: ' % ' ;
LESS				: ' < ' ;
GREATER				: ' > ' ;
LESSEQUAL			: ' <= ' | ' ≤ ' ;
GREATEREQUAL		: ' >= ' | ' ≥ ' ;
EQUAL				: ' = ' ;
NOTEQUAL			: ' != ' | ' ≠ ' ;
CONCAT				: ' (+) ' | ' ⊕ ' ;

// Keywords - consider not using these to provide greater flexibility.
BOOLEAN				: 'true' | 'false' ;
RETURN				: 'return' ;
REDO				: 'redo' ;
CREATE				: 'create' ;
GETTERSETTER		: 'getter' 's'? '/setter' 's'? ;
GETTER				: 'getter' 's'? ;
SETTER				: 'setter' 's'? ;
LIBRARY				: 'library' ;

SELFREF				: 'me' | 'my' ;
POSSESSIVE			: '\'s' ; // used for field access e.g. Robert's age
WORD				: FIRSTCHAR CHARACTER* ;

STRING				: '"' ('\\"' | .)*? '"' ;

// everything apart from white space, newline or special is a character
fragment FIRSTCHAR	: ~[.()[\]{,}:—|…'’0-9" ~\t\n↲] ; // ⊕+\-*×÷%=≠<≤>≥
fragment CHARACTER	: ~[.()[\]{,}:—|…'’" ~\t\n↲] ; // ⊕+*×÷%=≠<≤>≥
