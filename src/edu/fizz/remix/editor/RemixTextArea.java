package edu.fizz.remix.editor;

import edu.fizz.remix.EvalVisitorForEditor;
import edu.fizz.remix.runtime.LibrariesAndCompletions;
import edu.fizz.remix.runtime.LibraryExpression;
import org.antlr.v4.runtime.tree.ParseTree;
import org.fife.ui.rsyntaxtextarea.AbstractTokenMakerFactory;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.TokenMakerFactory;
import org.fife.ui.rtextarea.SearchContext;
import org.fife.ui.rtextarea.SearchEngine;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;
import java.awt.*;
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

    public RemixTextArea() { // called for printing
        super(20, 60);
        setFont(defaultFontForPrinting);
        setSyntaxEditingStyle("text/Remix");
    }

    public RemixTextArea(int rows, int cols) {
        super(rows, cols);
        setFont(defaultFontForScreen);
        setSyntaxEditingStyle("text/Remix");
        setBracketMatchingEnabled(true); // requires the token maker to provide getBracketPairs

        AutoPairer.enableAutoPair(this);
        AutoModify.enableAutoModify(this);
        AutoIndent.enableAutoIndent(this);
        AutoTab.enableAutoTab(this);
        completionControl = new RemixCompletions(this);
        caretListener = new CaretMove();
        addCaretListener(caretListener);

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
            public void changedUpdate(DocumentEvent e) {

            }
        });

        // also key listener for when code is inserted
        // so that editor contents is set to changed.
    }

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

    public RemixEditorWindow getWindow() {
        return (RemixEditorWindow) SwingUtilities.getWindowAncestor(this);
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

    public void reparseProgramText() {
        LibrariesAndCompletions.resetToEditorStandard();
        ParseTree tree = RemixPrepareRun.processParse(getText());
        EvalVisitorForEditor eval = new EvalVisitorForEditor(getWindow().getCurrentFileName());
        LibraryExpression programLib = (LibraryExpression) eval.visit(tree);
        programLib.setActiveLines(LibraryExpression.ALLLINES);
        /*
        Revert added libraries to baseLibrary
        LibrariesAndCompletions.resetAddedLibraries();

        Add programLib
        LibrariesAndCompletions.addLibrary(programLib);
         */
        LibrariesAndCompletions.addAfterBaseLibrary(programLib);
        /* the visit above fills in addedLibraries in LibrariesAndCompletions
        this the result program is added as well. */
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
        }
    }

    private int getLineNumber(int mark) {
        Element root = getDocument().getDefaultRootElement();
        return root.getElementIndex(mark);
    }
}
