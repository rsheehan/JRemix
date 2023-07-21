// remix.g4
parser grammar RemixParser;

options { tokenVocab=RemixLexer; } // use tokens from RemixLexer.g4

/*
	Parser rules
*/

program				: ( functionDefinition | statement )* EOF ;

library				: LIBRARY STRING? LBLOCK EOL* (functionDefinition EOL*)* RBLOCK ;

usingLibrary		: USING WORD (COMMA WORD)* blockOfStatements ;

functionDefinition	: functionComment? functionSignature COLON COLON? EOL? blockOfStatements ;

functionComment		: DOC_COMMENT ;

// Functions always have at least two parts, otherwise it will be an assignment statement.
functionSignature	: sigPart sigPart+ ; // part of a signature

sigPart				: WORD					# sigWord
					| LPAREN WORD RPAREN	# sigParam
					| LBLOCK WORD RBLOCK	# sigBlock
					;

createObject		: CREATE LBLOCK EOL* object RBLOCK ;

object				: field* (getterSetter? getter? setter?) methodDefinition* ;

field				: WORD COLON expression EOL*;

getterSetter		: GETTERSETTER LBLOCK (EOL* fieldId separator*)+ RBLOCK EOL* ;

getter				: GETTER LBLOCK (EOL* fieldId separator*)+ RBLOCK EOL* ;

setter              : SETTER LBLOCK (EOL* fieldId separator*)+ RBLOCK EOL* ;

fieldId				: WORD ;

methodDefinition	: methodSignature COLON EOL? blockOfStatements EOL* ;

methodSignature		: methodSigPart methodSigPart+ ;

methodSigPart		: WORD					# methSigWord
					| LPAREN WORD RPAREN	# methSigParam
					| LPAREN SELFREF RPAREN	# methSigSelf
					| LBLOCK WORD RBLOCK	# methSigBlock
					;

blockOfStatements	: LBLOCK statement* RBLOCK ;

statement			: assignmentStatement	# assStatement	// label not used
					| printStatement		# prStatement	// label not used
					| expression 			# expr
					| endOfStatement		# blank // need to reconsider this
					| REDO					# redo
					| RETURN expression?	# return
					| usingLibrary			# usingStatement
					;

endOfStatement		: EOL | EOS ;

assignmentStatement	: WORD COLON expression 			# setVariable
					| WORD listPart+ COLON expression	# setListElement
					;

printStatement		: (expression (COMMA expression)*)? (ENDPRINT | PRINTLN) # prntStatement
					;

expression			: MINUS expression						# exprMinus
					| expression MUL expression				# exprMul
//					| expression DIV expression				# exprDiv
//					| expression MOD expression				# exprMod
					| expression ADD expression				# exprAdd
//					| expression SUB expression				# exprSub
					| expression LESS expression			# exprLess
					| expression GREATER expression			# exprGreater
					| expression GREATEREQUAL expression	# exprGreatEql
					| expression LESSEQUAL expression		# exprLessEql
					| expression EQUAL expression			# exprEqual
					| expression NOTEQUAL expression		# exprNotEql
					| expression CONCAT expression			# exprConcat
					| listElement			# exprListElement // before functionCall
					| functionCall			# exprFncCall
					| WORD					# exprVar
					| NUMBER				# exprNumber
					| WORDPRODUCT			# exprWordProduct
					| BOOLEAN				# exprBoolean
					| STRING				# exprString
					| blockOfStatements		# exprBlock
					| list					# exprList
					| map					# exprMap
					| library				# exprLibrary
					| createObject			# exprObject
					| getterMethodCall		# exprGetterMethod
					| setterMethodCall      # exprSetterMethod
					| LPAREN EOL* expression EOL* RPAREN	# exprParen // after functionCall
					;

getterMethodCall	: (WORD | listElement) POSSESSIVE WORD; // e.g. Robert's name

setterMethodCall    : (WORD | listElement) POSSESSIVE WORD COLON expression ; // e.g. Robert's name : "Rob"

listElement			: WORD listPart+ ; // access a list element

listPart			: LBRACE expression RBRACE ; // { number or key } to access part of a list

functionCall		: callPart callPart+ ; // at least two call parts

callPart			: WORD									# callWord
					| LPAREN EOL* expression EOL* RPAREN	# callParam
					| LPAREN SELFREF RPAREN					# callSelf
					| NUMBER								# callNumber // leave out ()
					| BOOLEAN								# callBoolean
					| STRING								# callString
					| blockOfStatements						# callBlock
					| list									# callList
					| map									# callMap
					;

list				: LBRACE listContents RBRACE ;
separator			: EOL | COMMA ;

listContents		: (expression (COMMA expression)*)?	# commaList
					| LBLOCK EOL* (expression (separator EOL* expression)*)* RBLOCK EOL* # blockList
					| EOL*								# emptyList
					;

 map				: LBRACE mapContents RBRACE ;

 mapContents		: keyValue (COMMA keyValue)* 		# commaMap
 					| LBLOCK EOL* keyValue (separator EOL* keyValue)* RBLOCK EOL? # blockMap
					;

 keyValue			: key COLON value ;

 key				: WORD | STRING ;
 value				: expression ;
