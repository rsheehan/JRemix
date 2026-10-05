package edu.fizz.remix.editor;

import javax.swing.text.AbstractDocument;

public class REPLTextArea extends RemixTextArea {

    public REPLTextArea(boolean inREPL, boolean dark) {
        super(defaultFontForScreen, dark);
        setBracketMatchingEnabled(true); // requires the token maker to provide getBracketPairs

        AutoPairer.enableAutoPair(this);
        AutoModify.enableAutoModify(this);
        AutoIndent.enableAutoIndent(this);
        AutoTab.enableAutoTab(this);

        AbstractDocument doc = (AbstractDocument) getDocument();
        doc.setDocumentFilter(new RemixDocumentFilter(this));
    }

}
