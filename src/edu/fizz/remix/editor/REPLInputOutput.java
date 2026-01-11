package edu.fizz.remix.editor;

import javax.swing.*;
import javax.swing.text.*;

public class REPLInputOutput extends JTextArea {

    public REPLInputOutput() {
        AbstractDocument doc = (AbstractDocument) getDocument();
        doc.setDocumentFilter(new FilterLineInput());
    }

    /**
     * Get the last line number of the text.
     * @return the zero based line number of the last line
     */
    private int getLastLineNumber() {
        AbstractDocument doc = (AbstractDocument) getDocument();
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
//            int lastLine = getLastLineNumber();
//            if (lastLine > line)
//                append("\n" + lineText);
//            RemixPrepareRun.runInteractiveText(lineText);
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
        public void insertString(FilterBypass fb, int offset, String str, AttributeSet a)
                throws BadLocationException {
//            RemixEditor.systemOutput.setText("");
            if (REPLInputOutput.this.isFocusOwner())
                if (str.equals("\n")) {
                    dealWithNewLine(fb, offset, a);
                    return;
                } else if (str.equals("\t")) {
                    dealWithTab(fb, offset, a);
                }
            super.insertString(fb, offset, str, a);
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String str, AttributeSet a)
                throws BadLocationException {
            RemixEditor.systemOutput.setText("");
            if (REPLInputOutput.this.isFocusOwner())
                if (str.equals("\n")) {
                    dealWithNewLine(fb, offset, a);
                    return;
                } else if (str.equals("\t")) {
                    dealWithTab(fb, offset, a);
                    return;
                }
            super.replace(fb, offset, length, str, a);
        }

        private void dealWithTab(FilterBypass fb, int offset, AttributeSet a) throws BadLocationException {
            String lineText = getLineAtOffset(offset);
            int lastPos = REPLInputOutput.this.getDocument().getLength();
            boolean endOfLine = offsetEndOfLine(offset);
            // add a newline into document
            if (offsetEndOfLine(offset)) {
//                linesSinceMargin.append('\n');
                // find number of tabs on current line
                int numTabs = countLeadingTabs(lineText);
                // place newline and tabs into document
                StringBuilder sb = new StringBuilder("\n\t");
                for (int i = 0; i < numTabs; i++) {
                    sb.append('\t');
                }
                super.insertString(fb, lastPos, sb.toString(), a);
            } else {
                // place single tab in document
                super.insertString(fb, lastPos, "\t", a);
            }
        }

        private int countLeadingTabs(String str) {
            int count = 0;
            for (int i = 0; i < str.length(); i++)
                if (str.charAt(i) == '\t')
                    count++;
                else
                    break;
            return count;
        }

        private String linesToExecute(int offset) {
            int lineNumber;
            StringBuilder lines = new StringBuilder();
            String lineText = getLineAtOffset(offset);
            lines.append(lineText);
            if (lineText.startsWith("\t")) {
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

        private void dealWithNewLine(FilterBypass fb, int offset, AttributeSet a) throws BadLocationException {
            String lineText = getLineAtOffset(offset);
            int lastPos = REPLInputOutput.this.getDocument().getLength();
            if (lineText.isEmpty()) {
                super.insertString(fb, lastPos, "\n", a);
                return;
            }
            int lineNumber = getLineOfOffset(offset);
            int lastLineNumber = getLastLineNumber();
            super.insertString(fb, lastPos++, "\n", a);
            if (lastLineNumber > lineNumber) {
                super.insertString(fb, lastPos, lineText, a);
                lastPos += lineText.length();
            }
//            ArrayList<String> linesSinceMargin = new ArrayList<>();
//            while (lineNumber > 0) {
//                String prevLine = getLineText(--lineNumber);
//                lines
//            }
//            StringBuilder linesToExecute = new StringBuilder();
//            linesToExecute.insert()
            Object output = RemixPrepareRun.runInteractiveText(linesToExecute(offset));
            if (output != null) {
                String stringOutput = output.toString();
                super.insertString(fb, lastPos, stringOutput, a);
                lastPos += stringOutput.length();
                super.insertString(fb, lastPos, "\n", a);
            }
            REPLInputOutput.this.setCaretPosition(REPLInputOutput.this.getDocument().getLength());
            return;
        }
    }
}
