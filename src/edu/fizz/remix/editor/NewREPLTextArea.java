package edu.fizz.remix.editor;

import edu.fizz.remix.runtime.Runtime;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class NewREPLTextArea extends RemixTextArea {

    public final static String INFOSTRING = """
            
            \tThe screen is shared between code segments which may be executed
            \tand output segments from execution of code segments or programs.
            \tCode segments and output segments are separated by
            \t-------------------- lines.
            
            \tYou cannot type into output segments.
            
            \tTo execute a code segment : command-return
            \tTo clear this area : command-shift-C
            \tTo stop a runaway computation : control-C
            """;

    /*
    Trying to keep the helpful editing from RemixTextArea and integrate it with
    a live environment.

    Text will be divided in sections of two types:
        the code being entered
        the output of a code section running
    So we need markers to delineate the start of code section
    This code section can be edited in any fashion.
    When a command-Enter is typed anywhere in a code section the section
    is then executed.
        This places an output section marker in the text
        followed by the output
        followed by code section marker
     If a code section is not at the bottom and the user edits it
     the section should be copied to the bottom with change and caret
     at the corresponding place.

     Output sections can be modified but this does nothing.
     */

    private static final int MAXLINES = 100000; // the maximum number of lines in the area
    private static final String MARKER = "--------------------";
    public volatile boolean programFinished = true;

    private edu.fizz.remix.runtime.Runtime.REPLSwingWorker REPLworker;

    private final AbstractDocument document;
    private ArrayList<int[]> codeLines; // start and finish line numbers for code lines

    private ArrayList<int[]> createCodeLines() {
        codeLines = new ArrayList<>(100);
        codeLines.add(new int[]{0, MAXLINES - 1});
        return codeLines;
    }

    public NewREPLTextArea(Font font, boolean dark) {
        super(font, dark);
        NewREPLTokenMaker.REPLTextArea = this; // have to pass it somehow
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                setSyntaxEditingStyle("text/RemixREPL");
            }
        });
        document = (AbstractDocument)getDocument();
        AutoPairer.enableAutoPair(this);
        AutoModify.enableAutoModify(this);
        AutoIndent.enableAutoIndent(this);
        AutoTab.enableAutoTab(this);
//        setAutoIndentEnabled(false);
        setLineWrap( true );
        setWrapStyleWord( true );
        setTabSize(4);
        document.setDocumentFilter(new FilterLineInput());
        configureKeyBindings();
        codeLines = createCodeLines();
    }

    /*
    Returns true iff the offset is in a code section.
     */
    public boolean insideCodeSection(int offset) {
        boolean result = false;
        int lineNumber = 0;
        try {
            lineNumber = getLineOfOffset(offset);
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }
        for (int[] linePair : codeLines) {
            if (lineNumber >= linePair[0] && lineNumber <= linePair[1]) {
                result = true;
                break;
            }
        }
        return result;
    }

    public boolean bothEndsInsideCodeSection(int start, int end) {
        int[] section = getSectionContaining(start);
        if (section[0] == MAXLINES - 1)
            return false;
        int endLine = MAXLINES - 1;
        try {
            endLine = getLineOfOffset(end);
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }
        return section[0] <= endLine && endLine <= section[1];
    }

    /*
    Returns true iff the offset is inside the last code section.
    i.e. At the bottom of the text.
     */
    private boolean inLastCodeSection(int offset) {
        int lineNumber = 0;
        try {
            lineNumber = getLineOfOffset(offset);
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }
        int[] lastCodeSection = codeLines.getLast();
        return lastCodeSection[0] <= lineNumber;
    }

    /*
    Find the section the offset is in.
     */
    private int[] getSectionContaining(int offset) {
        int lineNumber = 0;
        try {
            lineNumber = getLineOfOffset(offset);
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }
        int[] codeSection = {MAXLINES - 1, MAXLINES - 1};
        for (int[] linePair : codeLines) {
            if (lineNumber >= linePair[0] && lineNumber <= linePair[1]) {
                codeSection = linePair;
                break;
            }
        }
        return codeSection;
    }

    /*
    Called by a program running from a RemixEditorWindow
    when the program starts.
     */
    public void clearForOutput() {
        int[] firstLineGroup = clearToBeginning();
        firstLineGroup[0] = MAXLINES - 1; // i.e. [9999, 9999] program output not in highlights
    }

    private int[] clearToBeginning() {
        codeLines = createCodeLines();
        setText("");
        setCaretPosition(0);
        int[] firstLineGroup = codeLines.getFirst();
        return firstLineGroup;
    }

    /*
    Called by a running program or code fragment
    when the output is finished.
     */
    public void finishOutput() {
        String allText = getText();
        char lastChar = allText.charAt(allText.length() - 1);
        if (lastChar != '\n')
            System.out.println();
        System.out.println(MARKER);
        setCaretPosition(getText().length());
        int finishLine = getLineCount() - 1;
        // gross
        if (codeLines.getFirst()[0] == MAXLINES - 1) // must have been a text editor file run
            codeLines.getFirst()[0] = finishLine;
        else
            codeLines.add(new int[]{finishLine, MAXLINES - 1});
    }

    private void configureKeyBindings() {
        InputMap inputMap = this.getInputMap(NewREPLTextArea.WHEN_FOCUSED);
        ActionMap actionMap = this.getActionMap();

        // Define the KeyStroke for Command + Return (Meta + Enter)
        // KeyEvent.VK_ENTER is the key code for the Return/Enter key
        // InputEvent.META_DOWN_MASK represents the Command key on Mac
        KeyStroke commandReturn = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, InputEvent.META_DOWN_MASK);
//        KeyStroke shiftReturn = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, InputEvent.SHIFT_DOWN_MASK);
        KeyStroke commandShiftC = KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.META_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK);
//        KeyStroke simpleReturn = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0,false);
//        KeyStroke indentedReturn = KeyStroke.getKeyStroke(KeyEvent.VK_TAB, InputEvent.SHIFT_DOWN_MASK);
        KeyStroke interruptExecution = KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK);
//
        // Map the KeyStrokes to a custom Action name
        String executeReturnActionName = "executeReturn";
        inputMap.put(commandReturn, executeReturnActionName);
//        String simpleReturnActionName = "handleSimpleReturn";
//        inputMap.put(simpleReturn, simpleReturnActionName);
//        String shiftReturnActionName = "handleShiftReturn";
//        inputMap.put(shiftReturn, shiftReturnActionName);
        String commandCActionName = "handleCommandC";
        inputMap.put(commandShiftC, commandCActionName);
//        String indentedReturnActionName = "handleIndentedReturn";
//        inputMap.put(indentedReturn, indentedReturnActionName);
        String interruptExecutionActionName = "handleInterruptExecution";
        inputMap.put(interruptExecution, interruptExecutionActionName);
//
        // Put the custom Action into the ActionMap
        actionMap.put(executeReturnActionName, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // collect the text from the last codeLine lines
                int[] codeSection = codeLines.getLast();
                String textToExecute = getTextToEnd(codeSection[0]);
                if (!textToExecute.isEmpty()) {
                    executeLines(textToExecute);
                }
            }
        });
//        actionMap.put(simpleReturnActionName, new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // This code executes when Return is pressed
//                int pos = getCaretPosition();
//                if (using(pos)) // line starts with "using"
//                    return;
//                if (definition(pos)) // line ends with ":"
//                    return;
//                executeLines(pos);
//            }
//        });
//
        actionMap.put(commandCActionName, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // This code executes when Command shift + C is pressed
                clearToBeginning();
            }
        });
//
//        actionMap.put(shiftReturnActionName, new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // deal with return to indent to level
//                int tabCount = 0;
//                int pos = getCaretPosition();
//                pos = adjustToEndIfNecessary(pos);
//                try {
//                    int lineNumber = getLineOfOffset(pos);
//                    String currentLine = getLineText(lineNumber);
//                    for (int i = 0; i < currentLine.length(); i++)
//                        if (currentLine.charAt(i) == '\t')
//                            tabCount++;
//                        else
//                            break;
//                } catch (BadLocationException ex) {
//                    throw new RuntimeException(ex);
//                }
//                insert("\n" + "\t".repeat(tabCount), pos);
//            }
//        });
//
//        actionMap.put(indentedReturnActionName, new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // deal with return to indent to level
//                int tabCount = 0;
//                int pos = getCaretPosition();
//                pos = adjustToEndIfNecessary(pos);
//                try {
//                    int lineNumber = getLineOfOffset(pos);
//                    String currentLine = getLineText(lineNumber);
//                    for (int i = 0; i < currentLine.length(); i++)
//                        if (currentLine.charAt(i) == '\t')
//                            tabCount++;
//                        else
//                            break;
//                } catch (BadLocationException ex) {
//                    throw new RuntimeException(ex);
//                }
//                insert("\n" + "\t".repeat(tabCount + 1), pos);
//            }
//        });
//
        actionMap.put(interruptExecutionActionName, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (REPLworker != null)
                    REPLworker.cancel(true);
            }
        });
    }

    private String getTextToEnd(int startLine) {
        try {
            // Get the starting character offset of the first line
            int startOffset = getLineStartOffset(startLine);

            // Get the ending character offset of the last line
            int endOffset = getLineEndOffset(getLineCount() - 1);

            // Extract and return the text within those bounds
            return getText(startOffset, endOffset - startOffset);

        } catch (BadLocationException e) {
            e.printStackTrace();
            return "";
        }
    }

    private void executeLines(String lines) {
        int[] lastLineGroup = codeLines.getLast();
        // modify the codeLines last to the position of the MARKER line
        lastLineGroup[1] = getLineCount() - 1;

        // check to see if the end of text is at the first position, 0, of the last line
        int length = getDocument().getLength();
        try {
            int row = getLineOfOffset(length);
            int column = length - getLineStartOffset(row);
            if (column == 0) {
                // fudge the last line group to include the empty line
//                lastLineGroup[1]++;
                System.out.println();
            }
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }

//        programFinished = false;
        if (!lines.endsWith("\n"))
            System.out.println();
        System.out.println(MARKER);
        // call runTextNewREPLTextArea in RemixPrepareRun
        Object result = RemixPrepareRun.runTextNewREPLTextArea(lines, this);
        if (result instanceof edu.fizz.remix.runtime.Runtime.REPLSwingWorker) {
            REPLworker = ((Runtime.REPLSwingWorker) result);
            return;
        }
        displayOutput(String.valueOf(result));
        // a codeLines group starting at final MARKER line
        // is added by the call
    }

    public void displayOutput(String output) {
        System.out.println(output);
        System.out.println(MARKER);
        // move the caret to the end of the document
        int length = getDocument().getLength();
        setCaretPosition(length);
        // add new code section starting from MARKER line
        codeLines.add(new int[]{getLineCount() - 1, MAXLINES - 1});
    }

    private class FilterLineInput extends DocumentFilter {

        public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
            if (bothEndsInsideCodeSection(offset, offset + length)) {
                if (inLastCodeSection(offset)) {
                    fb.remove(offset, length);
                }
                // should really copy the modified code to the end
                // find code section
                int[] requiredCodeSection = getSectionContaining(offset);
                // find offset of start of the section
                int lineCodeStartOffset = getLineStartOffset(requiredCodeSection[0]);
                // find offset of end of the sectoin
                int lineCodeEndOffset = getLineEndOffset(requiredCodeSection[1]);
                int offsetInsideSection = offset - lineCodeStartOffset;
                int endOfText = getDocument().getLength();
                // the -1 following is to remove any \n
                String codeSectionText = getText(lineCodeStartOffset, lineCodeEndOffset - lineCodeStartOffset - 1);
                StringBuilder removedTextBuilder = new StringBuilder(codeSectionText);
                int start = offset - lineCodeStartOffset;removedTextBuilder.delete(start, start + length);
                fb.insertString(endOfText, removedTextBuilder.toString(), null);
                // move the caret
                setCaretPosition(endOfText + offsetInsideSection);
            }
        }

        /*
        The insert could be coming from the user typing
        or output from running code.
         */
        public void insertString(FilterBypass fb, int offset, String text, AttributeSet attrs) throws BadLocationException {
            if (insideCodeSection(offset)) {
                if (inLastCodeSection(offset))
                    fb.insertString(offset, text, attrs);
                else {
                    // find code section
                    int[] requiredCodeSection = getSectionContaining(offset);
                    // find offset of start of the section
                    int lineCodeStartOffset = getLineStartOffset(requiredCodeSection[0]);
                    // find offset of end of the sectoin
                    int lineCodeEndOffset = getLineEndOffset(requiredCodeSection[1]);
                    int offsetInsideSection = offset - lineCodeStartOffset;
                    int endOfText = getDocument().getLength();
                    String codeSectionText = getText(lineCodeStartOffset, lineCodeEndOffset - lineCodeStartOffset - 1);
                    fb.insertString(endOfText, codeSectionText, attrs);
                    fb.insertString(endOfText + offsetInsideSection,
                                    text, attrs);
                    // does this put the caret where I want it - no
                    setCaretPosition(endOfText + offsetInsideSection + text.length());
                }
            } else {
        /*
        The insert could be coming from the user typing
        or output from running code.
         */
//                if (!programFinished) {
                    fb.insertString(offset, text, attrs);
                    setCaretPosition(offset + text.length());
//                }
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String str, AttributeSet attrs)
                throws BadLocationException {
            // check the position of offset
            // if in a code section we need to determine if it is the last section
            // if not we copy the code section to the end of the text
            // keeping the offset in the code section to the same as in the earlier one
            // and make the changes in that new location
            if (insideCodeSection(offset)) {
                if (inLastCodeSection(offset))
                    fb.replace(offset, length, str, attrs);
                else {
                    // move the code section to the bottom of the document
                    // keeping track of the offset into the code section
                    // because we want to move the caret there.
                    // find code section
                    int[] requiredCodeSection = getSectionContaining(offset);
                    // find offset of start of the section
                    int lineCodeStartOffset = getLineStartOffset(requiredCodeSection[0]);
                    int lineCodeEndOffset = getLineEndOffset(requiredCodeSection[1]);
                    // the line must have \n on the end as it is not the last code section - chop it off
                    String codeSectionText = getText(lineCodeStartOffset, lineCodeEndOffset - lineCodeStartOffset - 1);
                    int endOfText = getDocument().getLength();
                    // copy the code to the end of the document
                    fb.insertString(endOfText, codeSectionText, attrs);
                    int offsetInsideSection = offset - lineCodeStartOffset;
                    fb.insertString(endOfText + offsetInsideSection, str, attrs);
                    setCaretPosition(endOfText + offsetInsideSection + str.length());
                }
            } else {
                // if not in a code section we do nothing
                // I might jump to the end of the text, we'll see
            }
        }
    }

}
