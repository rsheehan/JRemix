package edu.fizz.remix.editor;

import edu.fizz.remix.parser.RemixLexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class RemixEdAntlrLexer {

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

    public RemixEdAntlrLexer(RemixStyledDocument document, boolean dark) {
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
            variableColour = new Color(255, 255, 200);
            constantColour = new Color(100, 200, 255);
            singleQuoteColour = new Color(70, 70, 70);
            stringColour = new Color(255, 200, 200);
            operatorColour = Color.green;
            literalColour = Color.cyan;
        } else {
            variableColour = new Color(0, 100, 150);
            constantColour = new Color(100, 0, 255);
            singleQuoteColour = new Color(185, 185, 185);
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
        parentheses = makeStyle("parentheses", new Color(150, 150, 250), false, false, defaultStyle);
        // comment from here to end of line
        comment = makeStyle("comment", new Color(170, 121, 66), true, false, defaultStyle);
        // multiline comment from starting "=" to ending "="
        multilineComment = makeStyle("multilineComment", new Color(170, 121, 66), false, false, defaultStyle);
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
        error = makeStyle("error", Color.red, false, false, defaultStyle);
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

    public void highlighter() {
        String chunkOfText = null;
        try {
            chunkOfText = document.getText(0, document.getLength());
        } catch (BadLocationException e) {
            System.err.println("Bad location in highligher.");
            return;
        }
        CharStream input = CharStreams.fromString(chunkOfText);
        RemixLexer lexer = new RemixLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        tokens.fill();
        SwingUtilities.invokeLater(() -> {
            for (Token token : tokens.getTokens()) {
                String text = token.getText();
                String tokenName = lexer.getVocabulary().getSymbolicName(token.getType());
                int start = token.getStartIndex();
                int finish = token.getStopIndex();
                int tokenNumber = token.getType();
                AttributeSet attrs;
                if (tokenName.equals("IDENTIFIER")) {
                    attrs = variable;
                    System.out.format("%s: %s type: %s, from %d to %d%n", tokenName, text, tokenNumber, start, finish);
                } else if (tokenName.equals("EOF")) {
                    return;
                } else {
                    attrs = defaultStyle;
                }
                document.setCharacterAttributes(start,finish - start + 1, attrs, true);
            }
        });
    }
}
