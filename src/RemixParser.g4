// remix.g4
parser grammar RemixParser;

options { tokenVocab=RemixLexer; } // use tokens from RemixLexer.g4

/*
	Parser rules
*/

program				: ( functionDefinition | statement )* EOF ;

library				: LIBRARY STRING? LBLOCK EOL* ( functionDefinition | statement )* RBLOCK ;

usingLibrary		: USING expression (COMMA expression)* blockOfStatements ;

functionDefinition	: functionComment? functionSignature COLON COLON? EOL? blockOfStatements ;

functionComment		: DOC_COMMENT ;

functionSignature	: sigPart sigPart+
					| singleWord
					;

singleWord			: WORD ;

sigPart				: WORD						# sigWord
 					| (IDENTIFIER | LPAREN IDENTIFIER RPAREN )	# sigParam
					| LBLOCK IDENTIFIER RBLOCK	# sigBlock
					;

createObject		: CREATE LBLOCK EOL* object RBLOCK ;
extendObject		: EXTEND expression LBLOCK EOL* object RBLOCK ;

object				: field* (getterSetter? getter? setter?) methodDefinition* ;

field				: IDENTIFIER COLON expression EOL*;

getterSetter		: GETTERSETTER LBLOCK (EOL* fieldId separator*)+ RBLOCK EOL* ;

getter				: GETTER LBLOCK (EOL* fieldId separator*)+ RBLOCK EOL* ;

setter              : SETTER LBLOCK (EOL* fieldId separator*)+ RBLOCK EOL* ;

fieldId				: IDENTIFIER ;

methodDefinition	: (setterSignature | getterSignature | methodSignature) COLON EOL? blockOfStatements EOL* ;

// add getterSignature and setterSignature
// to deal with user defined getters and setters
methodSignature		: methodSigPart methodSigPart+ ;
getterSignature		: SELFREF IDENTIFIER ;
setterSignature		: SELFREF IDENTIFIER (IDENTIFIER | LPAREN IDENTIFIER RPAREN) ;

methodSigPart		: WORD						# methSigWord
					| IDENTIFIER				# methSigParam
					| LPAREN IDENTIFIER RPAREN	# methSigParam
					| SELFREF					# methSigSelf
					| LPAREN SELFREF RPAREN		# methSigSelf
					| LBLOCK IDENTIFIER RBLOCK	# methSigBlock
					;

blockOfStatements	: LBLOCK statement* RBLOCK ;

statement			: assignmentStatement	# assStatement	// label not used
					| printStatement		# prStatement	// label not used
					| expression 			# expr
					| endOfStatement		# blank // need to reconsider this
					| REDO					# redo
					| RETURN expression?	# return
					| usingLibrary			# usingStatement	// label not used
					;

endOfStatement		: EOL | EOS ;

assignmentStatement	: IDENTIFIER COLON expression 			# setVariable
					| IDENTIFIER listPart+ COLON expression	# setListElement
					;

printStatement		: (expression (COMMA expression)*)? (ENDPRINT | PRINTLN) # prntStatement
					;

expression			: MINUS expression						# exprMinus
					| expression MUL expression				# exprMul
					| expression ADD expression				# exprAdd
					| expression LESS expression			# exprLess
					| expression GREATER expression			# exprGreater
					| expression GREATEREQUAL expression	# exprGreatEql
					| expression LESSEQUAL expression		# exprLessEql
					| expression EQUAL expression			# exprEqual
					| expression NOTEQUAL expression		# exprNotEql
					| expression CONCAT expression			# exprConcat
					| listElement			# exprListElement // before functionCall
					| functionCall			# exprFncCall
					| IDENTIFIER			# exprVar
					| NUMBER				# exprNumber
					| WORDPRODUCT			# exprWordProduct
					| NULL					# exprNull
					| BOOLEAN				# exprBoolean
					| STRING				# exprString
					| blockOfStatements		# exprBlock
					| list					# exprList
					| map					# exprMap
					| library				# exprLibrary
					| createObject			# exprObject
					| extendObject			# exprExtend
					| getterMethodCall		# exprGetterMethod
					| setterMethodCall      # exprSetterMethod
					| LPAREN EOL* expression EOL* RPAREN	# exprParen // after functionCall
					;

getterMethodCall	: (IDENTIFIER | listElement) POSSESSIVE IDENTIFIER; // e.g. Robert's Name

setterMethodCall    : (IDENTIFIER | listElement) POSSESSIVE IDENTIFIER COLON expression ; // e.g. Robert's Name : "Rob"

listElement			: IDENTIFIER listPart+ ; // access a list element

listPart			: LBRACE expression RBRACE	# listPartExpr // { number or string } to access part of a list or map
//					| LBRACE key RBRACE			# listPartKey
					;

functionCall		: callPart callPart+
					| singleWord
					;

callPart			: WORD									# callWord
					| IDENTIFIER							# callVar
					| LPAREN EOL* expression EOL* RPAREN	# callParam
					| SELFREF								# callSelf
					| LPAREN SELFREF RPAREN					# callSelf
					| NUMBER								# callNumber // leave out ()
					| WORDPRODUCT							# callWordProduct
					| NULL									# callNull
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

 keyValue			: STRING COLON value ;

// key				: WORD | STRING ;
 value				: expression ;
