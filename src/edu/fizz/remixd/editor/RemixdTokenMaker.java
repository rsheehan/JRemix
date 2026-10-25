package edu.fizz.remixd.editor;

import edu.fizz.remixd.parser.RemixdLexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rsyntaxtextarea.TokenMakerBase;

import javax.swing.text.Segment;

public class RemixdTokenMaker extends TokenMakerBase {

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
        RemixdLexer lexer = new RemixdLexer(input);

        // --- Restore state from the previous line ---
        switch (initialTokenType) {
            case RemixdTokenTypes.COMMENT_MULTILINE -> lexer.pushMode(RemixdLexer.IN_COMMENT);
            case RemixdTokenTypes.LITERAL_STRING_DOUBLE_QUOTE -> lexer.pushMode(RemixdLexer.IN_STRING);
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
            // Map your ANTLR token type to RSyntaxTextArea RemixdTokenTypes
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
        if (currentLexerMode == RemixdLexer.IN_COMMENT) {
            return RemixdTokenTypes.COMMENT_MULTILINE;
        } if (currentLexerMode == RemixdLexer.IN_STRING) {
            return RemixdTokenTypes.LITERAL_STRING_DOUBLE_QUOTE;
        } else {
            return switch (antlrTokenType) {
//                case RemixLexerToken.WORD -> RemixdTokenTypes.FUNCTION;
                case RemixLexerToken.RETURN,
                     RemixLexerToken.REDO,
                     RemixLexerToken.CREATE,
                     RemixLexerToken.EXTEND,
                     RemixLexerToken.GETTERSETTER,
                     RemixLexerToken.GETTER,
                     RemixLexerToken.SETTER,
                     RemixLexerToken.LIBRARY,
                     RemixLexerToken.USING,
                     RemixLexerToken.SELFREF -> RemixdTokenTypes.RESERVED_WORD;
                case RemixLexerToken.LBLOCK,
                     RemixLexerToken.RBLOCK,
                     RemixLexerToken.LBRACE,
                     RemixLexerToken.RBRACE,
                     RemixLexerToken.LPAREN,
                     RemixLexerToken.RPAREN,
                     RemixLexerToken.COLON,
                     RemixLexerToken.COMMA -> RemixdTokenTypes.SEPARATOR;
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
                     RemixLexerToken.PRINTLN -> RemixdTokenTypes.OPERATOR;
                case RemixLexerToken.STRING_START,
                     RemixLexerToken.STRING_TEXT,
                     RemixLexerToken.STRING_END,
                     RemixLexerToken.STRING_INCOMPLETE -> RemixdTokenTypes.LITERAL_STRING_DOUBLE_QUOTE;
                case RemixLexerToken.NUMBER -> RemixdTokenTypes.LITERAL_NUMBER_DECIMAL_INT;
                case RemixLexerToken.BOOLEAN,
                     RemixLexerToken.NULL -> RemixdTokenTypes.LITERAL_BOOLEAN;
                case RemixLexerToken.COMMENT -> RemixdTokenTypes.COMMENT_EOL;
                case RemixLexerToken.COMMENT_END,
                     RemixLexerToken.COMMENT_INCOMPLETE -> RemixdTokenTypes.COMMENT_MULTILINE;
                case RemixLexerToken.CONSTANT -> RemixdTokenTypes.CONSTANT;
                case RemixLexerToken.IDENTIFIER -> RemixdTokenTypes.VARIABLE;
                case RemixLexerToken.SPACE -> RemixdTokenTypes.WHITESPACE;
                default -> RemixdTokenTypes.IDENTIFIER;
            };
        }
    }

}
