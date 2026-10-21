package edu.fizz.remix.editor;

import edu.fizz.remix.EvalVisitorForEditor;
import edu.fizz.remix.runtime.LibrariesAndCompletions;
import edu.fizz.remix.runtime.LibraryExpression;
import org.antlr.v4.runtime.tree.ParseTree;
import org.fife.ui.rsyntaxtextarea.*;
import org.fife.ui.rtextarea.SearchContext;
import org.fife.ui.rtextarea.SearchEngine;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.Rectangle2D;

public class RemixTextArea extends RSyntaxTextArea {

    static {
        AbstractTokenMakerFactory atmf = (AbstractTokenMakerFactory) TokenMakerFactory.getDefaultInstance();
        atmf.putMapping("text/Remix", "edu.fizz.remix.editor.RemixTokenMaker");
    }

    public static final int SIZE = 13; // 12 is small but gives 80 col printout on A4
    public static final int PRINTSIZE = 9;
    public static final Font defaultFontForScreen = new Font("monospaced", Font.PLAIN, SIZE);
    public static final Font defaultFontForPrinting = new Font("monospaced", Font.PLAIN, PRINTSIZE);

    RemixCompletions completionControl;
    CaretListener caretListener;

    public boolean isEditorContentSaved() {
        return editorContentSaved;
    }

    public void setEditorContentSaved(boolean editorContentSaved) {
        this.editorContentSaved = editorContentSaved;
    }

    boolean editorContentSaved = true;
    boolean isREPLArea = false;

    public RemixTextArea(Font font, boolean dark) { // called for printing
        setFont(font);
        setDark(dark);
        setSyntaxEditingStyle("text/Remix");
        setSyntaxScheme(createRemixScheme());
        modifyScheme(dark);
    }

    public RemixTextArea(boolean replArea, boolean dark) {
        this(defaultFontForScreen, dark);
        isREPLArea = replArea;
        setBracketMatchingEnabled(true); // requires the token maker to provide getBracketPairs

        AutoPairer.enableAutoPair(this);
        AutoModify.enableAutoModify(this);
        AutoIndent.enableAutoIndent(this);
        AutoTab.enableAutoTab(this);
        completionControl = new RemixCompletions(this);
        caretListener = new CaretMove();
        addCaretListener(caretListener);

        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                completionControl.clearCompletions();
            }

            @Override
            public void focusLost(FocusEvent e) {
                completionControl.clearCompletions();
            }
        });

        AbstractDocument doc = (AbstractDocument) getDocument();
        doc.setDocumentFilter(new RemixDocumentFilter(this));
        doc.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                editorContentSaved = false;
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                editorContentSaved = false;
            }

            @Override
            public void changedUpdate(DocumentEvent e) {}

        });
    }

    /*
    Sets the basic background, foreground, highlight and caret colours.
     */
    public void setDark(boolean dark) {
        if (dark) {
            setBackground(Color.black);
            setForeground(Color.white);
            setCurrentLineHighlightColor(Color.darkGray);
            setCaretColor(Color.yellow);
        } else {
            setBackground(Color.white);
            setForeground(Color.black);
            setCurrentLineHighlightColor(new Color(240, 240, 240));
            setCaretColor(Color.blue);
        }
    }

    public void searchForward(SearchContext searchContext) {
        boolean found = SearchEngine.find(this, searchContext).wasFound();
        if (!found) {
            int caretPos = getCaretPosition();
            setCaretPosition(0); // go back to the beginning and try again
            found = SearchEngine.find(this, searchContext).wasFound();
            if (!found) {
                setCaretPosition(caretPos); // restore caret
            }
        }
    }

    public void searchBack(SearchContext searchContext) {
        boolean found = SearchEngine.find(this, searchContext).wasFound();
        if (!found) {
            int caretPos = getCaretPosition();
            setCaretPosition(getDocument().getLength()); // go back to the beginning and try again
            found = SearchEngine.find(this, searchContext).wasFound();
            if (!found) {
                setCaretPosition(caretPos); // restore caret
            }
        }
    }

    /**
     * Removes the caret listener.
     * Calls replaceRange on the text area.
     * If text is empty does a delete.
     * Adds the caret listener back.
     * @param text the text to insert
     * @param start the start position
     * @param end the end position
     */
    public void replaceRangeNoCaretListener(String text, int start, int end) {
        removeCaretListener(caretListener);
        replaceRange(text, start, end);
        addCaretListener(caretListener);
    }

    public Window getWindow() {
        return SwingUtilities.getWindowAncestor(this);
    }

    public Point getCaretScreenLocation() {
        if (getDocument().getLength() == 0)
            return new Point(10, 5);
        try {
            Rectangle2D rect = modelToView2D(getCaretPosition());
            return new Point((int)rect.getX(), (int)rect.getY());
        } catch (BadLocationException e) {
            System.out.println("The nasty modelToView2D error in RemixTextArea getCaretScreenLocation");
            return new Point(10, 5);
        }
    }

    /*
    Only called from RemixCompletions.completionHandling
     */
    public void reparseProgramText() {
        String text;
        if (isREPLArea) {
            text = REPLPanel.allText + getText();
            LibrariesAndCompletions.removeUnnamedLibs();
        } else {
            LibrariesAndCompletions.resetToEditorStandard();
            text = getText();
        }

        ParseTree tree = RemixPrepareRun.processParse(text);

        EvalVisitorForEditor eval = new EvalVisitorForEditor(((RemixBaseWindow)getWindow()).getCurrentFileName());
        LibraryExpression programLib = (LibraryExpression) eval.visit(tree);
        programLib.setActiveLines(LibraryExpression.ALLLINES);

        LibrariesAndCompletions.addAfterBaseLibrary(programLib);
        /* the visit above fills in addedLibraries in LibrariesAndCompletions
        this the result program is added as well. */
    }

    SyntaxScheme createRemixScheme() {
        SyntaxScheme scheme = new SyntaxScheme(true);
        Font defaultFont = getFont();

        Font smallerFont = defaultFont.deriveFont(defaultFont.getSize() - 1f);
        Font italicFont = smallerFont.deriveFont(Font.ITALIC);

        org.fife.ui.rsyntaxtextarea.Style commentStyle = new org.fife.ui.rsyntaxtextarea.Style(new Color(170, 121, 66), null, italicFont);
        org.fife.ui.rsyntaxtextarea.Style keywordStyle = new Style(Color.red);

        scheme.setStyle(RemixTokenTypes.RESERVED_WORD, keywordStyle);
        scheme.setStyle(RemixTokenTypes.COMMENT_EOL, commentStyle);
        scheme.setStyle(RemixTokenTypes.COMMENT_MULTILINE, commentStyle);
        return scheme;
    }

    /*
    Modifies the scheme to either be light or dark.
     */
    void modifyScheme(boolean dark) {
        Font defaultFont = getFont();
        Font boldFont = defaultFont.deriveFont(Font.BOLD);
        Style darkLiteralStyle = new Style(Color.cyan);
        Style lightLiteralStyle = new Style(new Color(205, 127, 50));

        SyntaxScheme scheme = getSyntaxScheme();
        scheme.setStyle(RemixTokenTypes.CONSTANT,
                        new Style(new Color(100, 200, 255), null, boldFont));
        if (dark) {
            scheme.setStyle(RemixTokenTypes.FUNCTION,
                            new Style(Color.white));
            scheme.setStyle(RemixTokenTypes.VARIABLE,
                            new Style(new Color(255, 255, 200)));
            scheme.setStyle(RemixTokenTypes.LITERAL_STRING_DOUBLE_QUOTE,
                            new Style(new Color(255, 200, 200)));
            scheme.setStyle(RemixTokenTypes.LITERAL_NUMBER_DECIMAL_INT,
                            darkLiteralStyle);
            scheme.setStyle(RemixTokenTypes.LITERAL_BOOLEAN,
                            darkLiteralStyle);
            scheme.setStyle(RemixTokenTypes.OPERATOR,
                            new Style(Color.green));

        } else {
            scheme.setStyle(RemixTokenTypes.FUNCTION,
                            new Style(Color.black));
            scheme.setStyle(RemixTokenTypes.VARIABLE,
                            new Style(new Color(0, 100, 150)));
            scheme.setStyle(RemixTokenTypes.LITERAL_STRING_DOUBLE_QUOTE,
                            new Style(new Color(200, 10, 200)));
            scheme.setStyle(RemixTokenTypes.LITERAL_NUMBER_DECIMAL_INT,
                            lightLiteralStyle);
            scheme.setStyle(RemixTokenTypes.LITERAL_BOOLEAN,
                            lightLiteralStyle);
            scheme.setStyle(RemixTokenTypes.OPERATOR,
                            new Style(new Color(205, 127, 50)));
        }
        repaint();
    }

    private class CaretMove implements CaretListener {

        private int lastLineNumber = 0;
        private String lastLine = "";
        private int lastCompletionNumber = -1;

        @Override
        public void caretUpdate(CaretEvent event) {
            int mark = event.getMark();
            int lineNumber = getLineNumber(mark);
            String line = AutoUtil.extractLine(RemixTextArea.this, lineNumber);
            int completionNumber = completionControl.completionNumber();

            boolean differentLine = lineNumber != lastLineNumber;
            if (differentLine) {
                completionControl.clearCompletions();
            } else { // same line number
                boolean sameCompletion = completionNumber == lastCompletionNumber;
                assert line != null;
                boolean sameLineContents = line.equals(lastLine);
                if (sameCompletion && !sameLineContents) { // changed
                    completionControl.clearCompletions();
                }
            }
            lastLineNumber = lineNumber;
            lastLine = line;
            lastCompletionNumber = completionNumber;

            boolean hasSelection = (getSelectionStart() != getSelectionEnd());
            // Toggle bracket matching based on whether text is currently selected
            setBracketMatchingEnabled(!hasSelection);
        }
    }

    private int getLineNumber(int mark) {
        Element root = getDocument().getDefaultRootElement();
        return root.getElementIndex(mark);
    }
}
