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
            case RemixdLexerToken.COMMENT_SECTION,
                 RemixdLexerToken.COMMENT_TEXT,
                 RemixdLexerToken.COMMENT_INCOMPLETE,
                 RemixdLexerToken.STRING_START,
                 RemixdLexerToken.STRING_TEXT,
                 RemixdLexerToken.STRING_INCOMPLETE -> false;
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
//                case RemixdLexerToken.WORD -> RemixdTokenTypes.FUNCTION;
                case RemixdLexerToken.RETURN,
                     RemixdLexerToken.REDO,
                     RemixdLexerToken.CREATE,
                     RemixdLexerToken.EXTEND,
                     RemixdLexerToken.GETTERSETTER,
                     RemixdLexerToken.GETTER,
                     RemixdLexerToken.SETTER,
                     RemixdLexerToken.LIBRARY,
                     RemixdLexerToken.USING,
                     RemixdLexerToken.SELFREF -> RemixdTokenTypes.RESERVED_WORD;
                case RemixdLexerToken.LBLOCK,
                     RemixdLexerToken.RBLOCK,
                     RemixdLexerToken.LBRACE,
                     RemixdLexerToken.RBRACE,
                     RemixdLexerToken.LPAREN,
                     RemixdLexerToken.RPAREN,
                     RemixdLexerToken.COLON,
                     RemixdLexerToken.COMMA -> RemixdTokenTypes.SEPARATOR;
                case RemixdLexerToken.ADD,
                     RemixdLexerToken.MINUS,
                     RemixdLexerToken.MUL,
                     RemixdLexerToken.LESS,
                     RemixdLexerToken.LESSEQUAL,
                     RemixdLexerToken.GREATER,
                     RemixdLexerToken.GREATEREQUAL,
                     RemixdLexerToken.EQUAL,
                     RemixdLexerToken.NOTEQUAL,
                     RemixdLexerToken.CONCAT,
                     RemixdLexerToken.ENDPRINT,
                     RemixdLexerToken.PRINTLN -> RemixdTokenTypes.OPERATOR;
                case RemixdLexerToken.STRING_START,
                     RemixdLexerToken.STRING_TEXT,
                     RemixdLexerToken.STRING_END,
                     RemixdLexerToken.STRING_INCOMPLETE -> RemixdTokenTypes.LITERAL_STRING_DOUBLE_QUOTE;
                case RemixdLexerToken.NUMBER -> RemixdTokenTypes.LITERAL_NUMBER_DECIMAL_INT;
                case RemixdLexerToken.BOOLEAN,
                     RemixdLexerToken.NULL -> RemixdTokenTypes.LITERAL_BOOLEAN;
                case RemixdLexerToken.COMMENT -> RemixdTokenTypes.COMMENT_EOL;
                case RemixdLexerToken.COMMENT_END,
                     RemixdLexerToken.COMMENT_INCOMPLETE -> RemixdTokenTypes.COMMENT_MULTILINE;
                case RemixdLexerToken.CONSTANT -> RemixdTokenTypes.CONSTANT;
                case RemixdLexerToken.IDENTIFIER -> RemixdTokenTypes.VARIABLE;
                case RemixdLexerToken.SPACE -> RemixdTokenTypes.WHITESPACE;
                default -> RemixdTokenTypes.IDENTIFIER;
            };
        }
    }

}
