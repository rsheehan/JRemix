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
    private static RemixStyledDocument document;

    private static Style
        defaultStyle,
        variable,
        parentheses,
        comment,
        operator,
        literal,
        string,
        keyword,
        separator;

    // horrible global state variables
    private static LexMode mode;

    private static final List<String> keywords = Arrays.asList("return", "redo", "create", "extend", "ME", "MY",
            "setter", "setters", "getter", "getters", "getter/setter", "getters/setters", "library", "using");
    // "create" is only a keyword if it is on a line by itself
    // "using" should only be a keyword if is by comma separated words

    private static final List<String> literalWords = Arrays.asList("true", "false");

    private static boolean identifierChar(String c) {
        return "ABCDEFGHIJKLMNOPQRSTUVWXYZ#ΑΒΓΔΕΖΗΘΙΚΛΜΝΞΟΠΡΣΤΥΦΧΨΩ".contains(c);
    }
    private static boolean firstWordChar(char c) {
        return !".()[\\]{,}:;—|…'’⊕+-*×÷%=≠<≤>≥0123456789π\" \t\n".contains(Character.toString(c));
    }
    // no longer allow "-" in
    private static boolean wordChar(char c) {
        return !".()[\\]{,}:;—|…'’⊕+*×÷%=≠<≤>≥π\" \t\n".contains(Character.toString(c));
    }
    private static boolean isSeparator(char c) {
        return ".:,({[)}]".contains(Character.toString(c));
    }
    // currently missing '/' in operator list
    private static boolean isOperator(char c) {
        return "⊕+-*×÷%<>=≤≥≠".contains(Character.toString(c));
    }

    public static void initStyles(RemixStyledDocument document) {
        RemixEdLexer.document = document;

        // the style at the original position of the textPane
        defaultStyle = document.getStyle("default");
        SimpleAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setForeground(attr, Color.white);
        defaultStyle.addAttributes(attr);
        // variables
        variable = makeStyle("variable", new Color(200,200,200), true, defaultStyle);
        // parentheses
        parentheses = makeStyle("parentheses", new Color(150,150,250), false, defaultStyle );
        // comments, all sorts
        comment = makeStyle("comment",new Color(170,121,66), true, defaultStyle );
        // operator text
        operator = makeStyle("operator", Color.green, true, defaultStyle);
        // literals
        literal = makeStyle("literal", Color.cyan, true, defaultStyle);
        // string - just a version of literal for the RemixStyleDocument code
        string = makeStyle("string", new Color(255,200,200), true, defaultStyle);
        // keywords
        keyword = makeStyle("keyword", Color.red, false, defaultStyle);
        // separator
        separator = makeStyle("separator", Color.magenta, false, defaultStyle);
    }

    private static Style makeStyle(String name, Color colour, boolean italic, Style base) {
        Style newStyle = document.addStyle(name, base);
        SimpleAttributeSet attr = new SimpleAttributeSet();
        if (colour != null)
            StyleConstants.setForeground(attr, colour);
        StyleConstants.setItalic(attr, italic);
        newStyle.addAttributes(attr);
        return newStyle;
    }

    public static void fullLex() throws BadLocationException {
        mode = LexMode.startOfLine;
        int pos = 0;
        while (pos < document.getLength()) {
            pos = processChar(pos);
        }
    }

    private static char getChar(int pos) throws BadLocationException {
        return document.getText(pos, 1).toCharArray()[0];
    }

    public static String getStyleName(int pos) {
        return (String) document
                .getCharacterElement(pos)
                .getAttributes()
                .getAttribute(StyleConstants.NameAttribute);
    }

    private static int processChar(int pos) throws BadLocationException {
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

    private static int dealWithStartOfLine(char ch, int pos) throws BadLocationException {
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

    private static int dealWithInsideLine(char ch, int pos) throws BadLocationException {
        switch (ch) {
            case ' ' -> {
                return dealWithSpaces(pos);
            }
//            case '\t' -> err.println("Only use tabs at the start of a line.");
            case '\n' -> mode = LexMode.startOfLine;
            case '\"' -> {
                return dealWithString(pos);
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
    starting location. Just a style consistency things which needs correcting.
     */
    private static int dealWithTabs(int pos) throws BadLocationException {
//        String prevStyle = pos > 0 ? getStyleName(pos - 1) : "default"; // should just get Style
        char ch = 0;
        int tabStart = pos;
        for (pos++; pos < document.getLength(); pos++) {
            ch = getChar(pos);
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

    private static int dealWithSpaces(int pos) throws BadLocationException {
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

    private static int dealWithSingleLineComment(int pos) throws BadLocationException {
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

    private static int dealWithMultiLineComment(int pos) throws BadLocationException {
        int commentPos;
        boolean tabbedStartOfLine = false;
//        boolean newline = false;
        for (commentPos = pos + 1; commentPos < document.getLength(); commentPos++) {
            char ch = getChar(commentPos);
            if (tabbedStartOfLine && ch == '=') {// finishing
                commentPos++;
                break;
            }
//            newline = false;
            if (ch == '\n') {
//                newline = true;
                tabbedStartOfLine = true;
            } else {
                if (tabbedStartOfLine) {
                    tabbedStartOfLine = ch == '\t';
                }
            }
//            newline = ch == '\n';
        }
        for (; commentPos < document.getLength(); commentPos++) { // mop up any remaining characters on the line
            char ch = getChar(commentPos);
            if (ch == '\n')
                break;
        }
        document.setCharacterAttributes(pos, commentPos - pos, comment, true);
        return commentPos + 1;
    }

    private static int dealWithString(int pos) throws BadLocationException {
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

    private static int dealWithNumber(int pos) throws BadLocationException {
        int numPos;
        for (numPos = pos + 1; numPos < document.getLength(); numPos++) {
            char ch = getChar(numPos);
            if (!isDigit(ch))
                break;
        }
        document.setCharacterAttributes(pos, numPos - pos, literal, true);
        return numPos;
    }

    private static int dealWithPi(int pos) {
        document.setCharacterAttributes(pos, 1, literal, true);
        return pos + 1;
    }

    private static int dealWithWord(int pos) throws BadLocationException {
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
        if (isKeyword(word, pos))
            wordStyle = keyword;
        else if (isLiteralWord(word))
            wordStyle = literal;
        else if (isVariable(pos, wordPos - 1))
            wordStyle = variable;
        else
            wordStyle = document.getStyle("default");
        document.setCharacterAttributes(pos, wordPos - pos, wordStyle, true);
        return wordPos; // goes back to last character
    }

    private static boolean isKeyword(String word, int start) throws BadLocationException {
        switch (word) {
            case "create" -> {
                String before = getCharBefore(start);
                String after = getCharAfter(start + 5);
                if ((":\n\t".contains(before)) && // all strings contain the empty string
                        (after.isEmpty() || after.equals("\n")))
                    return true;
            }
            case "extend" -> {
                String before = getCharBefore(start);
                String after = getCharAfter(start + 5);
                if ((":\n\t".contains(before)) && // all strings contain the empty string
                        after.equals("("))
                    return true;
            }
            case "me", "my" -> {
                if ((start > 1 && getChar(start - 1) == '(') &&
                        (document.getLength() > start + 2 && getChar(start + 2) == ')'))
                    return true;
            }
        }
        return keywords.contains(word);
    }

    private static boolean isLiteralWord(String word) {
        return literalWords.contains(word);
    }

    private static boolean isVariable(int startWord, int endWord) throws BadLocationException {
        boolean capital = false;
        for (int i = startWord; i <= endWord; i++) {
            if (identifierChar(document.getText(i, 1)))
                capital = true;
        }
        return capital; //identifierChar(document.getText(startWord, 1));
    }

    private static boolean follows(String wordBefore, int pos) throws BadLocationException {
        // we know it must have the last letter of wordBefore as the first non-space character
        StringBuilder word = new StringBuilder();
        boolean started = false;
        while (pos > 0) {
            pos--;
            char ch = document.getText(pos, 1).charAt(0);
            if (wordChar(ch)) {
                started = true;
                word.insert(0, ch);
            } else if (started) {
                break;
            }
        }
        return word.toString().equals(wordBefore);
    }

    // pos is the start of a single word
    // if preceded by ":" then find the indentation before the variable being assigned to
    // e.g. var : apply     - with the remainder of the apply function indented on the next line
    public static int indentationBefore(int pos) throws BadLocationException {
        String before = "";
        int count = 0;
        // work out current indentation
        while (pos > 0) {
            pos--;
            before = document.getText(pos, 1);
            if (!before.equals(" ")) {
                break;
            }
        }

        if (before.equals(":"))
            pos = startOfVar(pos);
        else
            pos++;

        while (pos > 0) {
            pos--;
            before = document.getText(pos, 1);
            if (before.equals("\n"))
                break;
            if (before.equals("\t")) {
                count++;
            } else {
                return -1;
            }
        }
        return count;
    }

    // pos is ":" need to move back over variable being assigned to so that we
    // can find the indentation at the start
    public static int startOfVar(int pos) throws BadLocationException {
        while (pos > 0) {
            pos--;
            if (!document.getText(pos, 1).equals(" "))
                break;
        }
        while (pos > 0) {
            if (wordChar(getChar(pos)))
                pos--;
            else {
                pos++;
                break;
            }
        }
        return pos;
    }

    // pos is the end of a single word
    private static int indentationAfter(int pos) throws BadLocationException {
        String after;
        int count = 0;
        while (pos < document.getLength()) {
            pos++;
            after = document.getText(pos, 1);
            if (after.equals(" "))
                continue;
            if (after.equals("\n"))
                break;
            else
                return -1;
        }
        // work out following indentation
        while (pos < document.getLength()) {
            pos++;
            after = document.getText(pos, 1);
            if (after.equals("\t"))
                count++;
            else
                break;
        }
        return count;
    }

    private static String getCharAfter(int pos) throws BadLocationException {
        String result = "";
        while (pos < document.getLength()) {
            pos++;
            result = document.getText(pos, 1);
            if (!result.equals(" "))
                return result;
        }
        return result;
    }

    private static String getCharBefore(int pos) throws BadLocationException {
        String result = "";
        while (pos > 0) {
            pos--;
            result = document.getText(pos, 1);
            if (!result.equals(" "))
                return result;
        }
        return result;
    }

    private static int dealWithSeparator(char ch, int pos) {
        if (ch == '(' || ch == ')') {
            document.setCharacterAttributes(pos, 1, parentheses, true);
        } else
            document.setCharacterAttributes(pos, 1, separator, true);
        return pos + 1;
    }

    private static int dealWithOperator(int pos) {
        document.setCharacterAttributes(pos, 1, operator, true);
        return pos + 1;
    }

}
