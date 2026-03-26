package edu.fizz.remix.editor;

import edu.fizz.remix.runtime.Runtime;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class REPLInputOutput extends JTextArea {

    private final AbstractDocument doc;
    private int topOfCodePos = 0; // offset to the top of the statement
    private int bottomOfCodeLine = 0; // line at the bottom of the statement to be executed
    public final static String INFOSTRING = """
            
            \t\
            To execute a line : return
            \t\
            To start a new line without execution : shift-return
            \t\
            To start a new indented line : shift-tab
            \t\
            To clear this area : command-shift-C
            \t\
            To stop a runaway computation : control-C
            \t\
            Lines starting with "-" are comments. e.g. "-2 + 4"
            
            """;

    private Runtime.REPLSwingWorker REPLworker;
    private String separatorLine;

    public REPLInputOutput() {
        doc = (AbstractDocument) getDocument();
        doc.setDocumentFilter(new FilterLineInput());
        setBackground(Color.darkGray);
        setFont(new Font("Courier New", Font.PLAIN, 14));
        setForeground(Color.white);
        setCaretColor(Color.white);
        setLineWrap( true );
        setWrapStyleWord( true );
        setTabSize(4);
        configureKeyBindings();
    }

    private void configureKeyBindings() {
        InputMap inputMap = this.getInputMap(JTextArea.WHEN_FOCUSED);
        ActionMap actionMap = this.getActionMap();

        // Define the KeyStroke for Command + Return (Meta + Enter)
        // KeyEvent.VK_ENTER is the key code for the Return/Enter key
        // InputEvent.META_DOWN_MASK represents the Command key on Mac
        KeyStroke shiftReturn = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, InputEvent.SHIFT_DOWN_MASK);
        KeyStroke commandShiftC = KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.META_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK);
        KeyStroke simpleReturn = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0,false);
        KeyStroke indentedReturn = KeyStroke.getKeyStroke(KeyEvent.VK_TAB, InputEvent.SHIFT_DOWN_MASK);
        KeyStroke interruptExecution = KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK);

        // Map the KeyStrokes to a custom Action name
        String simpleReturnActionName = "handleSimpleReturn";
        inputMap.put(simpleReturn, simpleReturnActionName);
        String shiftReturnActionName = "handleShiftReturn";
        inputMap.put(shiftReturn, shiftReturnActionName);
        String commandCActionName = "handleCommandC";
        inputMap.put(commandShiftC, commandCActionName);
        String indentedReturnActionName = "handleIndentedReturn";
        inputMap.put(indentedReturn, indentedReturnActionName);
        String interruptExecutionActionName = "handleInterruptExecution";
        inputMap.put(interruptExecution, interruptExecutionActionName);

        // Put the custom Action into the ActionMap
        actionMap.put(simpleReturnActionName, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // This code executes when Return is pressed
                int pos = getCaretPosition();
                if (using(pos)) // line starts with "using"
                    return;
                if (definition(pos)) // line ends with ":"
                    return;
                executeLines(pos);
            }
        });
        // Put the custom Action into the ActionMap
        actionMap.put(commandCActionName, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // This code executes when Command + C is pressed
                RemixEditor.remixOutput.setText(null); // clearTextArea();
            }
        });
        actionMap.put(shiftReturnActionName, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // deal with return to indent to level
                int tabCount = 0;
                int pos = getCaretPosition();
                try {
                    int lineNumber = getLineOfOffset(pos);
                    String currentLine = getLineText(lineNumber);
                    for (int i = 0; i < currentLine.length(); i++)
                        if (currentLine.charAt(i) == '\t')
                            tabCount++;
                        else
                            break;
                } catch (BadLocationException ex) {
                    throw new RuntimeException(ex);
                }
                insert("\n" + "\t".repeat(tabCount), pos);
            }
        });
        actionMap.put(indentedReturnActionName, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // deal with return to indent to level
                int tabCount = 0;
                int pos = getCaretPosition();
                try {
                    int lineNumber = getLineOfOffset(pos);
                    String currentLine = getLineText(lineNumber);
                    for (int i = 0; i < currentLine.length(); i++)
                        if (currentLine.charAt(i) == '\t')
                            tabCount++;
                        else
                            break;
                } catch (BadLocationException ex) {
                    throw new RuntimeException(ex);
                }
                insert("\n" + "\t".repeat(tabCount + 1), pos);
            }
        });
        actionMap.put(interruptExecutionActionName, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (REPLworker != null)
                    REPLworker.cancel(true);
            }
        });
    }

    /*
    Indent following line if this line ends with ":".
    Return true iff this line starts with ":".
     */
    private boolean definition(int pos) {
        boolean defn = false;
        try {
            int lineNumber = getLineOfOffset(pos);
            String lineText = getLineText(lineNumber);
            if (lineText.endsWith(":")) {
                defn = true;
                insert("\n\t", pos);
            }
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }
        return defn;
    }

    /*
    Indent following line if this line starts with "using".
    Return true iff this line starts with "using".
     */
    private boolean using(int pos) {
        boolean using = false;
        try {
            int lineNumber = getLineOfOffset(pos);
            String lineText = getLineText(lineNumber);
            if (lineText.startsWith("using")) {
                using = true;
                insert("\n\t", pos);
            }
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }
        return using;
    }

    private void executeLines(int pos) {
        try {
            // collect the lines to execute
            String lines = linesToExecute(pos);
            if (lines.isEmpty())
                return;
            // check the line against the length of the document
            // find the length of the last line
            int lastLineLength = getLastLineLength(lines);
            // If not the last line, copy the lines to the end of the document
            if (bottomOfCodeLine != getLastLineNumber()) {
                System.out.print(lines); // System.out is redirected to the textArea - see TextAreaOutputStream
            } else {
                // after a newline
                System.out.println();
            }
            // print that many "-" values on the next line
            separatorLine = String.valueOf('=').repeat(lastLineLength);
            System.out.println(separatorLine);
            // execute the lines; this may include many print statements
            Object result = RemixPrepareRun.runInteractiveText(lines, this);
            if (result instanceof Runtime.REPLSwingWorker) {
                REPLworker = ((Runtime.REPLSwingWorker) result);
                return;
            }
            // result is function information, I think
            String output = String.valueOf(result);
            // print the result returned from the execution
            displayOutput(output);
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }
    }

    public void displayOutput(String output) {
        if (!output.isEmpty() && !output.endsWith("\n"))
            output = output + "\n";
        System.out.print(output);
        System.out.println(separatorLine);
        // move the caret to the end of the document
        int length = getDocument().getLength();
        setCaretPosition(length);
    }

    private int getLastLineLength(String executableLines) {
        if (executableLines.endsWith("\n"))
            executableLines = executableLines.substring(0, executableLines.length() - 1);
        int position = executableLines.lastIndexOf('\n');
        String lastLine;
        if (position == -1)
            lastLine = executableLines;
        else
            lastLine = executableLines.substring(position + 1);
        int lineLength = 0;
        for (char character : lastLine.toCharArray()) {
            if (character == '\t')
                lineLength += 4;
            else
                ++lineLength;
        }
        return lineLength;
    }

    private String linesToExecute(int offset) throws BadLocationException {
        int lastLineNumber = getLastLineNumber();
        StringBuilder lines = new StringBuilder();
        int lineNumber = getLineOfOffset(offset);
        String lineText = getLineText(lineNumber);
        if (lineText.startsWith("}") || lineText.startsWith("\t") || lineText.startsWith("…")) {
            while (lineNumber > 0) {
                --lineNumber;
                lineText = getLineText(lineNumber);
                if (!lineText.startsWith("\t") && !lineText.startsWith("\n") && !lineText.startsWith("…")) {
                    break;
                }
            }
        }
        topOfCodePos = getLineStartOffset(lineNumber);
        lines.append(lineText);
        bottomOfCodeLine = lineNumber;
        while (lineNumber < lastLineNumber) {
            ++lineNumber;
            lineText = getLineText(lineNumber);
            // finish if line has non-tab at the beginning
            // but include the line if it starts with "}" or "..." and carry on, it could have an indented block
            if (!lineText.startsWith("\t") && !lineText.startsWith("\n") && !lineText.startsWith("…") && !lineText.startsWith("}")) {
                break;
            }
            lines.append(lineText);
            bottomOfCodeLine = lineNumber;
        }
        return lines.toString();
    }

    /**
     * Get the last line number of the text.
     * @return the zero based line number of the last line
     */
    private int getLastLineNumber() {
        // The default root element contains a child element for each line
        Element rootElem = doc.getDefaultRootElement();
        // getElementCount() returns the number of lines (1-based)
        return rootElem.getElementCount() - 1;
    }

    private String getLineText(int line) throws BadLocationException {
        int lineStartOffset = getLineStartOffset(line);
        int lineEndOffset = getLineEndOffset(line);
        return getText(lineStartOffset, lineEndOffset - lineStartOffset);
    }

    private boolean atEndOfText(int offset) throws BadLocationException {
        int lineNumber = getLineOfOffset(offset);
        int lastLineNumber = getLastLineNumber();
        while (lineNumber < lastLineNumber) {
            ++lineNumber;
            String lineText = getLineText(lineNumber);
            // finish if line has non-tab at the beginning
            // but include the line if it starts with "}" or "..." and carry on, it could have an indented block
            if (!lineText.startsWith("\t") && !lineText.startsWith("\n") && !lineText.startsWith("…") && !lineText.startsWith("}")) {
                break;
            }
        }
        return lineNumber == lastLineNumber;
    }

    private class FilterLineInput extends DocumentFilter {

        public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
            int newPos = afterPossibleCopy(fb, offset);
            fb.remove(newPos, length);
            setCaretPosition(newPos);
        }

        /*
        Returns the position after possibly copying code to the end of the document.
         */
        private int afterPossibleCopy(FilterBypass fb, int offset) throws BadLocationException {
            int displacement = -1;
            int endOfText = doc.getLength();
            if (!atEndOfText(offset)) {
                // copy the linesToExecute to the end of the text
                String linesToCopy = linesToExecute(offset);
                linesToCopy = linesToCopy.stripTrailing();
                displacement =  offset - topOfCodePos;
                fb.replace(endOfText, 0, linesToCopy, null);
            }
            if (displacement == -1) {
                return offset;
            } else
                return endOfText + displacement;
        }

        // based on similar method in RemixStyledDocument
        private boolean replaceOperator(FilterBypass fb, String target, String input, int offset) throws BadLocationException {
            int targetLen = target.length() - 1; // not counting last character
            if (offset >= targetLen) {
                String match = doc.getText(offset - targetLen, targetLen) + input; // existing plus new char
                if (match.equals(target)) {
                    String replacement = RemixStyledDocument.operators.get(target);
                    if ("π√²".contains(replacement)) {
                        // if the previous character is a word character don't do the replacement
                        int pos = offset - targetLen - 1;
                        if (pos >= 0) {
                            String ch = getText(pos, 1);
                            if (!" .()[\\]{,}:—|§@…'’⊕+-*×÷%=≠<≤>≥0123456789\"\t\n".contains(ch))
                                return false; // don't replace as pi is part of word
                        }
                    }
                    if (replacement.equals(" ⊕ ") && getText(offset, 1).equals(")")) {
                        fb.replace(offset - targetLen, targetLen + 1, replacement, null);
                    } else {
                        fb.replace(offset - targetLen, targetLen, replacement, null);
                    }
                    return true;
                }
            }
            return false;
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String str, AttributeSet a)
                throws BadLocationException {
            if (offset == 0 && length == doc.getLength() && str == null) {
                fb.replace(offset, length, null, a);
                return;
            }
            RemixEditor.systemOutput.setText(null);
            // check to see if the offset is at the end of the REPL text
            int newPos = afterPossibleCopy(fb, offset);
            setCaretPosition(newPos);

            for (String target : RemixStyledDocument.operators.keySet()) {
                if (replaceOperator(fb, target, str, offset)) {
                    return;
                }
            }
            fb.replace(newPos, length, str, a);
        }

    }
}
