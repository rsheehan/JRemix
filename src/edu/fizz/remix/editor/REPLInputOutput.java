package edu.fizz.remix.editor;

import edu.fizz.remix.runtime.BuiltInFunctionsLibrary;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class REPLInputOutput extends JTextArea {

    private AbstractDocument doc;
    private final String executeFlag = "*go*";

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
        KeyStroke commandReturn = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, InputEvent.META_DOWN_MASK);

        // Map the KeyStroke to a custom Action name
        String commandReturnActionName = "handleCommandReturn";
        inputMap.put(commandReturn, commandReturnActionName);

        // Put the custom Action into the ActionMap
        actionMap.put(commandReturnActionName, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // This code executes when Command + Return is pressed
                try {
                    doc.replace(getCaretPosition(), 0, executeFlag, null);
                } catch (BadLocationException ex) {
                    throw new RuntimeException(ex);
                }
                REPLInputOutput.this.append("\n");
                // You can add your specific logic here, e.g., submit a form, run a command
                // You might use the getText() method to retrieve the input
            }
        });
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

    /**
     * Is the offset at the end of its line?
     * @param offset
     * @return true iff offset at the end of its line
     */
    private boolean offsetEndOfLine(int offset) {
        int endLineOffset = 0;
        try {
            int lineNum = getLineOfOffset(offset);
            endLineOffset = getLineEndOffset(lineNum);
        } catch (BadLocationException ex) {
            ex.printStackTrace();
            System.out.println("Invalid offset provided.");
        }
        return endLineOffset == offset;
    }

    private String getLineAtOffset(int offset) {
        try {
            // Get the line number from the offset
            int line = getLineOfOffset(offset);

            final String lineText = getLineText(line);
            // if the last line of text we don't append it again
            return lineText;
        } catch (BadLocationException ex) {
            ex.printStackTrace();
            System.out.println("Invalid offset provided.");
        }
        return "";
    }

    private String getLineText(int line) throws BadLocationException {
        // Get the start offset of that specific line
        int lineStartOffset = getLineStartOffset(line);
        // Get the end offset of that specific line (or the start of the next line)
        int lineEndOffset = getLineEndOffset(line);
        // Extract the line text using the document's getText method
        String lineText = getDocument().getText(lineStartOffset, lineEndOffset - lineStartOffset);
        return lineText;
    }

    private class FilterLineInput extends DocumentFilter {

        @Override
        public void replace(FilterBypass fb, int offset, int length, String str, AttributeSet a)
                throws BadLocationException {
            RemixEditor.systemOutput.setText(null);
            if (REPLInputOutput.this.isFocusOwner())
                if (str.equals(executeFlag))  {
                    execute(fb, offset, a);
                    return;
//                } else if (str.equals("\t")) {
//                    dealWithTab(fb, offset, a);
//                    return;
                } else if (str.equals("n")) {
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

//        private void dealWithTab(FilterBypass fb, int offset, AttributeSet a) throws BadLocationException {
//            String lineText = getLineAtOffset(offset);
//            int lastPos = REPLInputOutput.this.getDocument().getLength();
//            boolean endOfLine = offsetEndOfLine(offset);
//            // add a newline into document
//            if (offsetEndOfLine(offset)) {
////                linesSinceMargin.append('\n');
//                // find number of tabs on current line
//                int numTabs = countLeadingTabs(lineText);
//                // place newline and tabs into document
//                StringBuilder sb = new StringBuilder("\n\t");
//                for (int i = 0; i < numTabs; i++) {
//                    sb.append('\t');
//                }
//                super.insertString(fb, lastPos, sb.toString(), a);
//            } else {
//                // place single tab in document
//                super.insertString(fb, lastPos, "\t", a);
//            }
//        }

//        private int countLeadingTabs(String str) {
//            int count = 0;
//            for (int i = 0; i < str.length(); i++)
//                if (str.charAt(i) == '\t')
//                    count++;
//                else
//                    break;
//            return count;
//        }

        private String linesToExecute(int offset) {
            int lineNumber;
            StringBuilder lines = new StringBuilder();
            String lineText = getLineAtOffset(offset);
            lines.append(lineText);
            if (lineText.startsWith("\t") || lineText.startsWith("…")) {
                try {
                    lineNumber = getLineOfOffset(offset);
                    while (lineNumber > 0) {
                        --lineNumber;
                        String prevLine = getLineText(lineNumber);
                        lines.insert(0, prevLine);
                        if (!prevLine.startsWith("\t") && !prevLine.startsWith("\n")) {
                            break;
                        }
                    }
                } catch (BadLocationException e) {
                    throw new RuntimeException(e);
                }
            }
            return lines.toString();
        }

        // Executes the current line or lines.
        private void execute(FilterBypass fb, int offset, AttributeSet a) throws BadLocationException {
            String lineText = getLineAtOffset(offset);
            int lastPos = REPLInputOutput.this.getDocument().getLength();
            if (lineText.isEmpty()) {
                return;
            }
            int lineNumber = getLineOfOffset(offset);
            int lastLineNumber = getLastLineNumber();
//            super.insertString(fb, lastPos++, "\n", a);
            String executableLines = linesToExecute(offset);
            if (lastLineNumber > lineNumber) {
                // change to move all required lines
                super.insertString(fb, lastPos, executableLines, a);
                lastPos += executableLines.length();
            }
            int lengthOfLastLine = lastLineLength(executableLines.substring(0, executableLines.length()));
            StringBuilder outputSB = new StringBuilder();
            for (int i = 0; i < lengthOfLastLine; i++) {
                outputSB.append("-");
            }
            outputSB.append("\n");
            BuiltInFunctionsLibrary.PrintFunction.publish(outputSB); // don't put quotes around it
            Object output = RemixPrepareRun.runInteractiveText(executableLines);
            //            super.insertString(fb, lastPos, stringOutput, a);
            BuiltInFunctionsLibrary.PrintFunction.publish(output.toString() + "\n");
            REPLInputOutput.this.setCaretPosition(REPLInputOutput.this.getDocument().getLength());
        }

        private int lastLineLength(String executableLines) {
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
    }
}
