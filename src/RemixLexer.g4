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
fragment COMMENT_SECTION	: EOL [ \t]* '=' .*? EOL [ \t]* '=' ~'\n'* ;
fragment REMAINING_COMMENT	: ';' ~'\n'* ;

NUMBER				: '-'? ( 'π' | DIGIT+ ('.' DIGIT+)?) ;
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
USING				: 'using' ; // using a-library
SELFREF				: 'ME' | 'MY' ;

POSSESSIVE			: '\'s' ; // used for field access e.g. Robert's age

IDENTIFIER			: (FIRSTCHAR CHARACTER* CAPITAL | CAPITAL) CHARACTER*; // [#A-Zδ] CHARACTER* hack
WORD				: FIRSTCHAR CHARACTER* ;
WORDPRODUCT			: '-'? DIGIT+ ('.' DIGIT+)? (IDENTIFIER | 'π');

STRING				: '"' ('\\"' | .)*? '"' ;

// everything apart from white space, newline or special is a character
fragment FIRSTCHAR	: ~[.()[\]{,}:—|…'’\-0-9" ~\t\n↲] ; // ⊕+\-*×÷%=≠<≤>≥
fragment CHARACTER	: ~[.()[\]{,}:—|…'’" ~\t\n↲] ; // ⊕+*×÷%=≠<≤>≥

fragment CAPITAL 	: [A-Z\u0391-\u03A9] ; // Roman and Greek capital letters
