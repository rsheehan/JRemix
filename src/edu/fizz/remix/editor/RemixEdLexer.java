package edu.fizz.remix.editor;

import javax.swing.text.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

import static java.lang.Character.isDigit;

public class RemixEdLexer {

    private final RemixStyledDocument document;

    private final Style defaultStyle;
    private final Style variable;
    private final Style constant;
    private final Style singleQuote;
    private final Style parentheses;
    private final Style comment;
    private final Style multilineComment;
    private final Style operator;
    private final Style literal;
    private final Style string;
    private final Style keyword;
    private final Style separator;
    private final Style error;


    private static final List<String> keywords = Arrays.asList("return", "redo", "create", "extend", "ME", "MY",
            "setter", "setters", "getter", "getters", "getter/setter", "getters/setters", "library", "using", "uses");

    private static final List<String> literalWords = Arrays.asList("true", "false", "null");

    private static boolean firstWordChar(char c) {
        return !".()[\\]{,}:;—…'’⊕+-*×÷%=≠<≤>≥0123456789π~↲\" \t\n".contains(Character.toString(c));
    }
    // no longer allow "-" in
    private static boolean wordChar(char c) {
        return !".()[\\]{,}:;—…'’⊕+*×÷%=≠<≤>≥π~↲\" \t\n".contains(Character.toString(c));
    }
    private static boolean isSeparator(char c) {
        return ".:,…({[)}]".contains(Character.toString(c));
    }
    // currently missing '/' in operator list
    private static boolean isOperator(char c) {
        return "⊕+-*×÷%<>=≤≥≠".contains(Character.toString(c));
    }

    public RemixEdLexer(RemixStyledDocument document, boolean dark) {
        Color variableColour, constantColour, singleQuoteColour, stringColour, operatorColour, literalColour;
        this.document = document;

        // the style at the original position of the textPane
        defaultStyle = document.getStyle("default");
        SimpleAttributeSet attr = new SimpleAttributeSet();
        if (dark) {
            StyleConstants.setForeground(attr, Color.white);
        } else {
            StyleConstants.setForeground(attr, Color.black);
        }
        defaultStyle.addAttributes(attr);
        if (dark) {
            variableColour = new Color(255,255,200);
            constantColour = new Color(100, 200, 255);
            singleQuoteColour = new Color(70,70,70);
            stringColour = new Color(255, 200, 200);
            operatorColour = Color.green;
            literalColour = Color.cyan;
        } else {
            variableColour = new Color(0, 100, 150);
            constantColour = new Color(100, 0, 255);
            singleQuoteColour = new Color(185,185,185);
            stringColour = new Color(200, 10, 200);
            operatorColour = new Color(0, 150, 0);
            literalColour = new Color(205, 127, 50);
        }
        // variables
        variable = makeStyle("variable", variableColour, false, true, defaultStyle); // was italic
        // constants
        constant = makeStyle("constant", constantColour, false, false, defaultStyle);
        // singleQuote
        singleQuote = makeStyle("singleQuote", singleQuoteColour, false, false, defaultStyle);
        // parentheses
        parentheses = makeStyle("parentheses", new Color(150,150,250), false, false, defaultStyle);
        // comment from here to end of line
        comment = makeStyle("comment",new Color(170,121,66), true, false, defaultStyle);
        // multiline comment from starting "=" to ending "="
        multilineComment = makeStyle("multilineComment",new Color(170,121,66), false, false, defaultStyle);
        // operator text
        operator = makeStyle("operator", operatorColour, false, false, defaultStyle); // was italic
        // literals
        literal = makeStyle("literal", literalColour, true, false, defaultStyle);
        // string - just a version of literal for the RemixStyleDocument code
        string = makeStyle("string", stringColour, true, false, defaultStyle);
        // keywords
        keyword = makeStyle("keyword", Color.red, false, false, defaultStyle);
        // separator
        separator = makeStyle("separator", Color.magenta, false, false, defaultStyle);
        // error
        error = makeStyle("error", Color.red, false, false, defaultStyle );
        StyleConstants.setBackground(error, Color.red);
    }

    private Style makeStyle(String name, Color colour, boolean italic, boolean underline, Style base) {
        Style newStyle = document.addStyle(name, base);
        SimpleAttributeSet attr = new SimpleAttributeSet();
        if (colour != null)
            StyleConstants.setForeground(attr, colour);
        StyleConstants.setItalic(attr, italic);
        StyleConstants.setUnderline(attr, underline);
        newStyle.addAttributes(attr);
        return newStyle;
    }

    /**
     * Goes to start of a selected line and relexes the code from
     * here until no more changes are necessary.
     * Needs to take into account multiline comments and strings.
     * @param pos the location inside the line to start lexing
     * @return the location after all necessary changes have been made
     */
    public int lexFromHere(int pos) throws BadLocationException {
        String styleName;
        pos = startOfLine(pos);

        if (pos == 0) {
            styleName = "default";
        } else // the style at the end of the previous line
            styleName = getStyleName(pos - 1);
        if (styleName.equals("multilineComment")) {
            pos = dealWithMultiLineComment(pos);
        } else if (styleName.equals("string")) {
            pos = dealWithString(pos);
        }
        // deal with rest of line
        pos = lexUntilEndOfLine(pos);
        pos = lexOverFollowingMultilineCommentLines(pos);
        pos = lexOverFollowingMultilineStrings(pos);
        return pos;
    }

    /**
     * Find the position at the start of this line.
     * @param pos the position
     * @return the position at the start of the line containing pos
     */
    private int startOfLine(int pos) throws BadLocationException {
        while (pos > 0 && getChar(pos - 1) != '\n')
            pos--;
        return pos;
    }

    private boolean isStartOfLine(int pos) throws BadLocationException {
        return pos == startOfLine(pos);
    }

    /**
     * Keeps relexing over lines if they were originally inside a
     * multiline comment block.
     * @param pos must be start of a line
     * @return first position of line not a comment line
     */
    private int lexOverFollowingMultilineCommentLines(int pos) throws BadLocationException {
        final int docLength = document.getLength();
        if (pos >= docLength) {
            return pos;
        }
        String lineStartStyle = getStyleName(pos);
        while (pos < docLength && (lineStartStyle.equals("multilineComment")  || lineStartStyle.equals("comment") || defaultTabsEqual(pos))) {
            pos = lexUntilEndOfLine(pos);
            lineStartStyle = getStyleName(pos);
        }
        return pos;
    }

    /**
     * Keeps relexing over lines if they were originally inside a
     * multiline string.
     * @param pos must be start of a line
     * @return first position of line not starting with a string
     */
    private int lexOverFollowingMultilineStrings(int pos) throws BadLocationException {
        final int docLength = document.getLength();
        if (pos >= docLength) {
            return docLength;
        }
        while (pos < docLength && (getStyleName(pos).equals("string"))) {
            pos = lexUntilEndOfLine(pos);
        }
        return pos;
    }

    /**
     * Check to see if the starting pos is just tabs before '='
     * @param pos the position
     * @return true if this is the start of a multiline comment
     */
    private boolean defaultTabsEqual(int pos) throws BadLocationException {
        String style = getStyleName(pos);
        if (!style.equals("default"))
            return false;
        char ch = getChar(pos);
        final int docLength = document.getLength();
        while (ch == '\t') {
            if (!(pos < docLength)) break;
            ch = getChar(++pos);
        }
        return ch == '=';
    }

    /**
     * Keep lexing from pos until a newline or end of doc is reached.
     * Can go over more than one line if we encounter multiline comments or strings.
     * @param pos the position to start lexing from
     * @return the position after the end of the line
     */
    public int lexUntilEndOfLine(int pos) throws BadLocationException {
        final int length = document.getLength();
        // can get stuck in an infinite loop if pos comes back unchanged or less.
        // This can happen if a character is inserted before a tab.
        while (pos < length && getChar(pos) != '\n') {
            pos = dealWithRun(pos);
        }
        document.setCharacterAttributes(pos, 1, defaultStyle, true);
        return pos >= length ? length : pos + 1;
    }

    /**
     * The main lexing function.
     * Lexes from pos until a lexical type has been dealt with.
     * Must move pos on before returning.
     * @param pos the start position
     * @return the position after this run
     */
    private int dealWithRun(int pos) throws BadLocationException {
        // being at the start of a line is different
        // for single line and multiline comments.
        // The same chars "-" and "=" are interpreted differently
        // if not at the start of a line.
        char ch;
        if (isStartOfLine(pos)) {
            int tabPos = pos;
            pos = gobbleTabs(pos);
            document.setCharacterAttributes(tabPos,pos - tabPos, defaultStyle, true);
            ch = getChar(pos);
            switch (ch) {
                case ' ' -> { // spaces not allowed at the start of lines (even after tabs)
                    document.setCharacterAttributes(pos, 1, error, true);
                    return pos + 1;
                }
                case '-' -> {
                    pos = dealWithSingleLineComment(pos);
                    return pos;
                }
                case '=' -> {
                    document.setCharacterAttributes(pos, 1, multilineComment, true);
                    pos = dealWithMultiLineComment(pos + 1);
                    pos = lexOverFollowingMultilineCommentLines(pos);
                    return pos;
                }
            }
        }
        ch = getChar(pos);
        // now back to normal processing
        switch (ch) {
            case ' ' -> {
                pos = dealWithSpaces(pos);
                return pos;
            }
            case '\n' -> {
                return pos + 1;
            }
            case '\"' -> {
                pos = dealWithString(pos);
                // possible the next line was a continuation of the string
                // if so and the string is now terminated need to relex the next line
                // and so on
                pos = lexOverFollowingMultilineStrings(pos);
                return pos;
            }
            case '\'' -> {
                return dealWithVariable(pos);
            }
            case ';' -> {
                return dealWithSingleLineComment(pos);
            }
            default -> {
                if (firstWordChar(ch)) {
                    return dealWithWord(pos);
                } else if (isSeparator(ch)) {
                    return dealWithSeparator(ch, pos);
                } else if (isOperator(ch)) {
                    return dealWithOperator(pos);
                } else if (isDigit(ch)) {
                    return dealWithNumber(pos);
                } else if (ch == 'π') {
                    return dealWithPi(pos);
                } else if (ch == '\t') { // tabs must only be at the start of lines, not following non-tabs
                    document.setCharacterAttributes(pos, 1, error, true);
                    return pos + 1;
                } else { // make the current character default?
                    document.setCharacterAttributes(pos, 1, defaultStyle, true);
                    return pos + 1;
                }
            }
        }
    }

    public void lexAfterUndoRedo(AbstractDocument.DefaultDocumentEvent event, boolean undoing) throws BadLocationException {
        String type = event.getType().toString();
        int offset = event.getOffset();
        int length = event.getLength();
        if (undoing && type.equals("REMOVE") || !undoing && type.equals("INSERT")) {
            // we are inserting
            int pos = lexFromHere(offset);
            if (pos - offset < length) {
                System.out.println("should do more lexing?");
            }
        } else if (undoing && type.equals("INSERT") || !undoing && type.equals("REMOVE")) {
            lexFromHere(offset);
        }
    }

    public void fullLex() throws BadLocationException {
        RemixEditor.systemOutput.setText("");
        int pos = 0;
        while (pos < document.getLength()) {
            pos = lexUntilEndOfLine(pos);
        }
    }

    private char getChar(int pos) throws BadLocationException {
        return document.getText(pos, 1).toCharArray()[0];
    }

    public String getStyleName(int pos) {
        if (pos < 0 || pos >= document.getLength())
            return "default";
        else
            return (String) document
                    .getCharacterElement(pos)
                    .getAttributes()
                    .getAttribute(StyleConstants.NameAttribute);
    }

    /**
     * Move past any tabs.
     * @param pos   the position to start inspecting for tabs
     * @return the first position not a tab or else the document length
     */
    private int gobbleTabs(int pos) throws BadLocationException {
        int tabPos = pos;
        while (tabPos < document.getLength() && getChar(tabPos) == '\t') {
            tabPos++;
        }
        return tabPos;
    }

    private int dealWithSpaces(int pos) throws BadLocationException {
        int spacePos = pos;
        for (pos++; pos < document.getLength(); pos++) {
            char ch = getChar(pos);
            if (ch != ' ')
                break;
        }
        document.setCharacterAttributes(spacePos, pos - spacePos, defaultStyle,true);
        return pos;
    }

    /**
     * Starting at pos keep going until the comment
     * finishes or at end of document.
     * pos is the position of the first character after "-"
     * or after "=" when a multline comment has finished
     * The style changes after the closing "=" to be "comment"
     * and at the following '\n' to be "default"
     *
     * @param pos the starting position
     * @return the position after newline at the end of the comment
     */
    private int dealWithSingleLineComment(int pos) throws BadLocationException {
        int commentPos;
        for (commentPos = pos; commentPos < document.getLength(); commentPos++) {
            char ch = getChar(commentPos);
            if (ch == '\n')
                break;
        }
        document.setCharacterAttributes(pos, commentPos - pos, comment, true);
        document.setCharacterAttributes(commentPos, 1, defaultStyle, true);

        return commentPos + 1;
    }

    /**
     * We are already inside a multiline comment.
     * Starting at pos keep going until the comment
     * finishes or at end of document.
     * The pos could be at the start of a line
     * The style changes after the closing "=" to be "comment"
     * and at the following '\n' to be "default"
     *
     * @param pos a position inside a multiline comment
     * @return the position after newline at the end of the comment
     */
    private int dealWithMultiLineComment(int pos) throws BadLocationException {
        int commentPos = pos;
        int docLength = document.getLength();
        while (commentPos < docLength) {
            if (isStartOfLine(commentPos)) { // could be terminating "=" if at start of line
                commentPos = gobbleTabs(commentPos);
                char ch = getChar(commentPos);
                if (ch == '=') { // finishing
                    document.setCharacterAttributes(pos, commentPos - pos, multilineComment, true);
                    return dealWithSingleLineComment(commentPos);
                }
            }
            // keep moving on until end of line or document
            while (commentPos < docLength) {
                char ch = getChar(commentPos++);
                if (ch == '\n')
                    break;
            }
            document.setCharacterAttributes(pos, commentPos - pos, multilineComment, true);
        }
        return commentPos;
    }

    /**
     * Starting at the next character after pos keep going until the string
     * finishes or end of document.
     * @param pos the position
     * @return the position after the concluding "
     */
    private int dealWithString(int pos) throws BadLocationException {
        // check the '\' escape character.
        boolean escape = false;
        int stringPos;
        char ch = 0;
        final int docLength = document.getLength();
        for (stringPos = pos + 1; stringPos < docLength; stringPos++) {
            ch = getChar(stringPos);
            if (escape) { // skip over this character
                escape = false;
                continue;
            }
            escape = ch == '\\';
            if (ch == '\"') { // end of string
                break;
            }
        }
        if (ch == '\"') {
            stringPos++; // only extend style if necessary
        }
        document.setCharacterAttributes(pos, stringPos - pos, string, true);
        document.setCharacterAttributes(stringPos, 1, defaultStyle, true);
        return stringPos;
    }

    private int dealWithNumber(int pos) throws BadLocationException {
        int numPos;
        for (numPos = pos + 1; numPos < document.getLength(); numPos++) {
            char ch = getChar(numPos);
            if (!isDigit(ch))
                break;
        }
        document.setCharacterAttributes(pos, numPos - pos, literal, true);
        return numPos;
    }

    private int dealWithPi(int pos) {
        document.setCharacterAttributes(pos, 1, literal, true);
        return pos + 1;
    }

    private int dealWithVariable(int pos) throws BadLocationException {
        int varPos;
        char ch = ' ';
        for (varPos = pos + 1; varPos < document.getLength(); varPos++) {
            ch = getChar(varPos);
            if (ch == '\'' || ch == '\n')
                break;
        }
        document.setCharacterAttributes(pos, 1, singleQuote, true);
        document.setCharacterAttributes(pos + 1, varPos - pos - 1, variable, true);
        if (ch == '\'')
            document.setCharacterAttributes(varPos, 1, singleQuote, true);
        else // could be a bad location if at the end of the document
            document.setCharacterAttributes(varPos, 1, defaultStyle, true);
        return varPos + 1;
    }

    private int dealWithWord(int pos) throws BadLocationException {
        // gobble word chars until space, separator, operator, newline
        int wordPos;
        for (wordPos = pos + 1; wordPos < document.getLength(); wordPos++) {
            char ch = getChar(wordPos);
            if (!wordChar(ch)) {
                break;
            }
        }
        // should work out if the word is a keyword, variable or not
        AttributeSet wordStyle;
        String word = document.getText(pos, wordPos - pos);
        if (isKeyword(word))
            wordStyle = keyword;
        else if (isConstantWord(word))
            wordStyle = constant;
        else if (isLiteralWord(word))
            wordStyle = literal;
        else if (isRefVariable(pos))
            wordStyle = variable;
        else
            wordStyle = document.getStyle("default");
        document.setCharacterAttributes(pos, wordPos - pos, wordStyle, true);
        return wordPos; // goes back to last character
    }

    public boolean isKeyword(String word) {
        return keywords.contains(word);
    }

    private boolean isConstantWord(String word) {
        boolean constant = true;
        boolean first = true;
        for (Character ch : word.toCharArray()) {
            if (first) {
                if (!(Character.isUpperCase(ch))) {
                    constant = false;
                    break;
                }
                first = false;
            } else if (!(Character.isUpperCase(ch) || Character.isDigit(ch) || ch == '-')) {
                constant = false;
                break;
            }
        }
        return constant;
    }

    private boolean isLiteralWord(String word) {
        return literalWords.contains(word);
    }

    private boolean isRefVariable(int startWord) throws BadLocationException {
        return document.getText(startWord, 1).equals("#"); //identifierChar(document.getText(startWord, 1));
    }

    private int dealWithSeparator(char ch, int pos) {
        if (ch == '(' || ch == ')') {
            document.setCharacterAttributes(pos, 1, parentheses, true);
        } else
            document.setCharacterAttributes(pos, 1, separator, true);
        return pos + 1;
    }

    private int dealWithOperator(int pos) {
        document.setCharacterAttributes(pos, 1, operator, true);
        return pos + 1;
    }

}
