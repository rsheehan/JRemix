package edu.fizz.remix.editor;

import javax.swing.text.*;
import java.awt.*;
import java.util.List;
import java.util.*;

import static java.lang.Character.isDigit;
import static java.lang.System.err;

public class RemixEdLexer {

    private enum LexMode {
        startOfLine,
        insideLine,
    }
//    private static JTextPane textPane;
    private final RemixStyledDocument document;

    private final Style defaultStyle;
    private final Style variable;
    private final Style constant;
    private final Style singleQuote;
    private final Style parentheses;
    private final Style comment;
    private final Style operator;
    private final Style literal;
    private final Style string;
    private final Style keyword;
    private final Style separator;

    // horrible global state variables
    private LexMode mode;

    private static final List<String> keywords = Arrays.asList("return", "redo", "create", "extend", "ME", "MY",
            "setter", "setters", "getter", "getters", "getter/setter", "getters/setters", "library", "using");
    // "create" is only a keyword if it is on a line by itself
    // "using" should only be a keyword if is by comma separated words

    private static final List<String> literalWords = Arrays.asList("true", "false", "null");

    private static boolean firstWordChar(char c) {
        return !".()[\\]{,}:;—…'’⊕+-*×÷%=≠<≤>≥0123456789π\" \t\n".contains(Character.toString(c));
    }
    // no longer allow "-" in
    private static boolean wordChar(char c) {
        return !".()[\\]{,}:;—…'’⊕+*×÷%=≠<≤>≥π\" \t\n".contains(Character.toString(c));
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
        // comments, all sorts
        comment = makeStyle("comment",new Color(170,121,66), true, false, defaultStyle);
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
    }

    private Style makeStyle(String name, Color colour, boolean italic, boolean underline, Style base) {
        Style newStyle = document.addStyle(name, base);
        SimpleAttributeSet attr = new SimpleAttributeSet();
        if (colour != null)
            StyleConstants.setForeground(attr, colour);
//        StyleConstants.setFontSize(attr, RemixEditor.SIZE);
        StyleConstants.setItalic(attr, italic);
        StyleConstants.setUnderline(attr, underline);
        newStyle.addAttributes(attr);
        return newStyle;
    }

    public void fullLex() throws BadLocationException {
        RemixEditor.systemOutput.setText("");
        mode = LexMode.startOfLine;
        int pos = 0;
        while (pos < document.getLength()) {
            pos = processChar(pos);
        }
    }

    private char getChar(int pos) throws BadLocationException {
        return document.getText(pos, 1).toCharArray()[0];
    }

    public String getStyleName(int pos) {
        return (String) document
                .getCharacterElement(pos)
                .getAttributes()
                .getAttribute(StyleConstants.NameAttribute);
    }

    private int processChar(int pos) throws BadLocationException {
        char ch = getChar(pos);

        switch (mode) {
            case startOfLine -> {
                return dealWithStartOfLine(ch, pos);
            }
            case insideLine -> {
                return dealWithInsideLine(ch, pos);
            }
        }
        return pos + 1;
    }

    private int dealWithStartOfLine(char ch, int pos) throws BadLocationException {
        switch (ch) {
            case ' ' -> {
                err.println("Lines cannot start with a space.");
                mode = LexMode.insideLine;
            }
            case '\t' -> {
                mode = LexMode.insideLine;
                return dealWithTabs(pos);
            }
            case '\n' -> { // mode stays startOfLine
            }
            case '\"' -> {
                mode = LexMode.insideLine;
                return dealWithString(pos);
            }
            case '\'' -> {
                mode = LexMode.insideLine;
                return dealWithVariable(pos);
            }
            case '-', ';' -> {
                return dealWithSingleLineComment(pos);
            }
            case '=' -> {
                mode = LexMode.startOfLine;
                return dealWithMultiLineComment(pos);
            }
            default -> {
                mode = LexMode.insideLine;
                if (firstWordChar(ch)) {
                    return dealWithWord(pos);
                } else if (isSeparator(ch)) {
                    return dealWithSeparator(ch, pos);
                } else if (isOperator(ch)) {
                    return dealWithOperator(pos);
                } else if (isDigit(ch)) {
                    return dealWithNumber(pos);
                } else if (ch == 'π')
                    return dealWithPi(pos);
            }
        }
        return pos + 1;
    }

    private int dealWithInsideLine(char ch, int pos) throws BadLocationException {
        switch (ch) {
            case ' ' -> {
                return dealWithSpaces(pos);
            }
//            case '\t' -> err.println("Only use tabs at the start of a line.");
            case '\n' -> mode = LexMode.startOfLine;
            case '\"' -> {
                return dealWithString(pos);
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
                } else { // make the current character default?
                    document.setCharacterAttributes(pos, 1, defaultStyle, true);
                }
            }
        }
        return pos + 1;
    }

    /*
    The next two functions dealWithTabs and dealWithSpaces currently
    use "pos" as the moving pointer into the text.
    The rest of the methods use the local variable and keep pos as the
    starting location. Just style consistency things which needs correcting.
     */
    private int dealWithTabs(int pos) throws BadLocationException {
        char ch = 0;
        int tabStart = pos;
        for (pos++; pos < document.getLength(); pos++) {
            ch = getChar(pos);
            if (ch == ' ')
                err.println("Lines cannot have a space following a tab.");
            if (ch != '\t')
                break;
        }
        document.setCharacterAttributes(tabStart, pos - tabStart, defaultStyle, true);
        if (ch == '-') // can be single line comment after tabs
            return dealWithSingleLineComment(pos);
        else if (ch == '=') // can be a multiline comment after tabs
            return dealWithMultiLineComment(pos);
        return pos;
    }

    private int dealWithSpaces(int pos) throws BadLocationException {
//        String prevStyle = pos > 0 ? getStyleName(pos - 1) : "default"; // should just get Style
        int spacePos = pos;
        for (pos++; pos < document.getLength(); pos++) {
            char ch = getChar(pos);
            if (ch != ' ')
                break;
        }
        document.setCharacterAttributes(spacePos, pos - spacePos, defaultStyle,true);
        return pos;
    }

    private int dealWithSingleLineComment(int pos) throws BadLocationException {
        int commentPos;
        mode = LexMode.startOfLine; // after throwing the rest away
        for (commentPos = pos + 1; commentPos < document.getLength(); commentPos++) {
            char ch = getChar(commentPos);
            if (ch == '\n')
                break;
        }
        document.setCharacterAttributes(pos, commentPos - pos, comment, true);
        return commentPos + 1;
    }

    private int dealWithMultiLineComment(int pos) throws BadLocationException {
        int commentPos;
        boolean tabbedStartOfLine = false;
        for (commentPos = pos + 1; commentPos < document.getLength(); commentPos++) {
            char ch = getChar(commentPos);
            if (tabbedStartOfLine && ch == '=') {// finishing
                commentPos++;
                break;
            }
            if (ch == '\n') {
                tabbedStartOfLine = true;
            } else {
                if (tabbedStartOfLine) {
                    tabbedStartOfLine = ch == '\t';
                }
            }
        }
        for (; commentPos < document.getLength(); commentPos++) { // mop up any remaining characters on the line
            char ch = getChar(commentPos);
            if (ch == '\n')
                break;
        }
        mode = LexMode.startOfLine;
        document.setCharacterAttributes(pos, commentPos - pos, comment, true);
        return commentPos + 1;
    }

    private int dealWithString(int pos) throws BadLocationException {
        // check the '\' escape character.
        boolean escape = false;
        int stringPos;
        char ch = 0;
        for (stringPos = pos + 1; stringPos < document.getLength(); stringPos++) {
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
        // N.B + 1 in line below because of ending and starting "
        int length = stringPos - pos;
        if (ch == '\"')
            length++; // only extend style if necessary
        document.setCharacterAttributes(pos, length, string, true);
        return stringPos + 1;
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
        for (Character ch : word.toCharArray()) {
            if (!(Character.isUpperCase(ch) || ch == '-')) {
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
