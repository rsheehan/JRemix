package edu.fizz.remix.editor;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class RemixDocumentFilter extends DocumentFilter {
    public RemixDocumentFilter() {
        super();
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {

        super.insertString(fb, offset, string, attr);
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        super.remove(fb, offset, length);
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        super.replace(fb, offset, length, text, attrs);
    }

    private static boolean inStringOrComment(int pos) {
        String styleName = RemixEdLexer.getStyleName(pos);
        return styleName.equals("string") || styleName.equals("comment");
    }

    /*
    Autoindent to the same depth as on the previous line.
    Takes strings into account.
    If the new line is after ":" we indent one extra tab.
    If the newline is after "[" we indent one extra tab and add
    a newline before the closing "]" which is indented to the original depth.
*/
//    private void autoIndent(int offset) throws BadLocationException {
//        // find previous indentation
//        // skipping over comments and strings
//        super.insertString(offset, "\n", null);
//        StringBuilder tabbedReturn = new StringBuilder();
//        // could be defining a function (or method)
//        String before = "";
//        if (offset > 0) {
//            before = getText(offset - 1, 1);
//        }
//        boolean extraNewline = false;
//        if (before.equals(":"))
//            tabbedReturn.append("\t");
//        else if (before.equals("[")) {
//            extraNewline = true;
//        }
//        for (int pos = offset - 1; pos >= 0; pos--) {
//            if (inStringOrComment(pos))
//                continue;
//            String ch = getText(pos, 1);
//            if (ch.equals("\t")) {
//                tabbedReturn.append("\t");
//            }
//            if (ch.equals("\n") || pos == 0) {
//                String extra = "";
//                if (extraNewline) {
//                    extra = tabbedReturn.toString();
//                    tabbedReturn.append("\t\n");
//                }
//                super.insertString(offset + 1, tabbedReturn.toString() + extra, null);
//                break;
//            }
//        }
//        if (extraNewline)
//            textPane.setCaretPosition(offset + tabbedReturn.length());
//    }
}
