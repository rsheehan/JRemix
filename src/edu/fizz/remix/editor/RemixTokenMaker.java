package edu.fizz.remix.editor;

import edu.fizz.remix.parser.RemixLexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.fife.ui.rsyntaxtextarea.*;
import org.fife.ui.rsyntaxtextarea.Token;

import javax.swing.text.Segment;

public class RemixTokenMaker extends TokenMakerBase {

    // Overriding TokenMaker - needed for bracket matching
    public String getBracketPairs() {
        return "{}()[]";
    }

    // Overriding TokenMaker
    public String[] getLineCommentStartAndEnd(int languageIndex) {
        return new String[]{"-", null};
    }

    @Override
    public Token getTokenList(Segment text, int initialTokenType, int startOffset) {
        resetTokenList();
        String line = text.toString();
        CharStream input = CharStreams.fromString(line);
        RemixLexer lexer = new RemixLexer(input);

//        System.out.println("initialTokenType: " + initialTokenType);
//        System.out.println(line);

        // --- Restore state from the previous line ---
        switch (initialTokenType) {
            case RemixTokenTypes.COMMENT_MULTILINE -> lexer.pushMode(RemixLexer.IN_COMMENT);
            case RemixTokenTypes.LITERAL_STRING_DOUBLE_QUOTE -> lexer.pushMode(RemixLexer.IN_STRING);
        }

        int currentLineOffset = text.offset;
        org.antlr.v4.runtime.Token antlrToken;
        int rSyntaxType;
        int lastAntlrTokenType = -1;
        while (true) {
            antlrToken = lexer.nextToken();
            if (antlrToken.getType() == org.antlr.v4.runtime.Token.EOF)
                break;
            lastAntlrTokenType = antlrToken.getType();
            // Convert ANTLR token indices to document-relative offsets
            int start = startOffset + antlrToken.getStartIndex();
            // Map your ANTLR token type to RSyntaxTextArea RemixTokenTypes
//            System.out.print("antlr token type:" + antlrToken.getType() + " - ");
//            System.out.println("lexer mode: " + lexer._mode);
             rSyntaxType = mapToRSyntaxType(antlrToken.getType(), lexer._mode);
            // Add the token to RSyntaxTextArea's linked list
            addToken(text, currentLineOffset + antlrToken.getStartIndex(),
                     currentLineOffset + antlrToken.getStopIndex(), rSyntaxType, start);
        }
        boolean addNull = switch (lastAntlrTokenType) {
            case RemixLexerToken.COMMENT_SECTION,
                 RemixLexerToken.COMMENT_TEXT,
                 RemixLexerToken.COMMENT_INCOMPLETE,
                 RemixLexerToken.STRING_START,
                 RemixLexerToken.STRING_TEXT,
                 RemixLexerToken.STRING_INCOMPLETE -> false;
            default -> true;
        };
        if (addNull) {
            addNullToken(); // only add if not multiline
        }
        return firstToken;
    }

    private int mapToRSyntaxType(int antlrTokenType, int currentLexerMode) {

        // If we are currently inside the comment mode, everything is a comment
        if (currentLexerMode == RemixLexer.IN_COMMENT) {
            return RemixTokenTypes.COMMENT_MULTILINE;
        } if (currentLexerMode == RemixLexer.IN_STRING) {
            return RemixTokenTypes.LITERAL_STRING_DOUBLE_QUOTE;
        } else {
            return switch (antlrTokenType) {
//                case RemixLexerToken.WORD -> RemixTokenTypes.FUNCTION;
                case RemixLexerToken.RETURN,
                     RemixLexerToken.REDO,
                     RemixLexerToken.CREATE,
                     RemixLexerToken.EXTEND,
                     RemixLexerToken.GETTERSETTER,
                     RemixLexerToken.GETTER,
                     RemixLexerToken.SETTER,
                     RemixLexerToken.LIBRARY,
                     RemixLexerToken.USING,
                     RemixLexerToken.SELFREF -> RemixTokenTypes.RESERVED_WORD;
                case RemixLexerToken.LBLOCK,
                     RemixLexerToken.RBLOCK,
                     RemixLexerToken.LBRACE,
                     RemixLexerToken.RBRACE,
                     RemixLexerToken.LPAREN,
                     RemixLexerToken.RPAREN,
                     RemixLexerToken.COLON,
                     RemixLexerToken.COMMA -> RemixTokenTypes.SEPARATOR;
                case RemixLexerToken.ADD,
                     RemixLexerToken.MINUS,
                     RemixLexerToken.MUL,
                     RemixLexerToken.LESS,
                     RemixLexerToken.LESSEQUAL,
                     RemixLexerToken.GREATER,
                     RemixLexerToken.GREATEREQUAL,
                     RemixLexerToken.EQUAL,
                     RemixLexerToken.NOTEQUAL,
                     RemixLexerToken.CONCAT,
                     RemixLexerToken.ENDPRINT,
                     RemixLexerToken.PRINTLN -> RemixTokenTypes.OPERATOR;
                case RemixLexerToken.STRING_START,
                     RemixLexerToken.STRING_TEXT,
                     RemixLexerToken.STRING_END,
                     RemixLexerToken.STRING_INCOMPLETE -> RemixTokenTypes.LITERAL_STRING_DOUBLE_QUOTE;
                case RemixLexerToken.NUMBER -> RemixTokenTypes.LITERAL_NUMBER_DECIMAL_INT;
                case RemixLexerToken.BOOLEAN -> RemixTokenTypes.LITERAL_BOOLEAN;
                case RemixLexerToken.COMMENT -> RemixTokenTypes.COMMENT_EOL;
                case RemixLexerToken.COMMENT_END,
                     RemixLexerToken.COMMENT_INCOMPLETE -> RemixTokenTypes.COMMENT_MULTILINE;
                case RemixLexerToken.CONSTANT -> RemixTokenTypes.CONSTANT;
                case RemixLexerToken.IDENTIFIER -> RemixTokenTypes.VARIABLE;
                case RemixLexerToken.SPACE -> RemixTokenTypes.WHITESPACE;
                default -> RemixTokenTypes.IDENTIFIER;
            };
        }
    }

}
