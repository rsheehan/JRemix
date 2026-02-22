package edu.fizz.remix.editor;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class REPLInputOutput extends JTextArea {

    private final AbstractDocument doc;
    public final static String INFOSTRING = """
            
            \t\
            To execute a line : return
            \t\
            To start a new line without execution : shift-return
            \t\
            To start a new indented line : shift-tab
            \t\
            To clear this area : command-shift-C
            
            """;

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

    public void clearTextArea() {
        try {
            doc.remove(0, doc.getLength());
        } catch (BadLocationException e) {
        }
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

        // Map the KeyStrokes to a custom Action name
        String simpleReturnActionName = "handleSimpleReturn";
        inputMap.put(simpleReturn, simpleReturnActionName);
        String shiftReturnActionName = "handleShiftReturn";
        inputMap.put(shiftReturn, shiftReturnActionName);
        String commandCActionName = "handleCommandC";
        inputMap.put(commandShiftC, commandCActionName);
        String indentedReturnActionName = "handleIndentedReturn";
        inputMap.put(indentedReturn, indentedReturnActionName);

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
                RemixEditor.remixOutput.clearTextArea();
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
            int lineNumber = getLineOfOffset(pos);
            int lastLineNumber = getLastLineNumber();
            // find the length of the last line
            int lastLineLength = getLastLineLength(lines);
            // If not the last line, copy the lines to the end of the document
            if (lineNumber < lastLineNumber) {
                System.out.print(lines); // System.out is redirected to the textArea - see TextAreaOutputStream
            } else {
                // after a newline
                System.out.println();
            }
            // print that many "-" values on the next line
            String separatorLine = String.valueOf('=').repeat(lastLineLength);
            System.out.println(separatorLine);
            // execute the lines; this may include many print statements
            String output = String.valueOf(RemixPrepareRun.runInteractiveText(lines));
            // print the result returned from the execution
            if (!output.isEmpty() && !output.endsWith("\n"))
                output = output + "\n";
            System.out.print(output);
            System.out.println(separatorLine);
            // move the caret to the end of the document
            int length = getDocument().getLength();
            setCaretPosition(length);
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }
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
        StringBuilder lines = new StringBuilder();
            int lineNumber = getLineOfOffset(offset);
            String lineText = getLineText(lineNumber);
            lines.append(lineText);
            if (lineText.startsWith("\t") || lineText.startsWith("…")) {
                while (lineNumber > 0) {
                    --lineNumber;
                    String prevLine = getLineText(lineNumber);
                    lines.insert(0, prevLine);
                    if (!prevLine.startsWith("\t") && !prevLine.startsWith("\n")) {
                        break;
                    }
                }
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

    private class FilterLineInput extends DocumentFilter {

        @Override
        public void replace(FilterBypass fb, int offset, int length, String str, AttributeSet a)
                throws BadLocationException {
            RemixEditor.systemOutput.setText(null);
            if (REPLInputOutput.this.isFocusOwner())
                if (str.equals("n")) {
                    if (possiblePrint(fb, offset, a))
                        return;
                } else if (str.equals(".")) {
                    if (possibleEllipsis(fb, offset, a))
                        return;
                }
            super.replace(fb, offset, length, str, a);
        }

        private boolean possiblePrint(FilterBypass fb, int offset, AttributeSet a) throws BadLocationException {
            if (offset == 0)
                return false;
            String prevChar = getDocument().getText(--offset, 1);
            if (prevChar.equals("\\")) {
                super.replace(fb, offset, 1, "↲", a);
                return true;
            }
            return false;
        }

        private boolean possibleEllipsis(FilterBypass fb, int offset, AttributeSet a) throws BadLocationException {
            if (offset < 2)
                return false;
            offset -= 2;
            String prevChar = getDocument().getText(offset, 2);
            if (prevChar.equals("..")) {
                super.replace(fb, offset, 2, "…", a);
                return true;
            }
            return false;
        }
    }
}
