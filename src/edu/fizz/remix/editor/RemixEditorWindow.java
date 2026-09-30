package edu.fizz.remix.editor;

import edu.fizz.remix.libraries.Graphics;
import org.fife.ui.rsyntaxtextarea.HtmlUtil;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextAreaEditorKit;
import org.fife.ui.rsyntaxtextarea.Style;
import org.fife.ui.rsyntaxtextarea.SyntaxScheme;
import org.fife.ui.rtextarea.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RemixEditorWindow extends JFrame {

    private final REPLInputOutput remixOutput;
    private final RemixTextArea editorTextArea;
    private final RTextScrollPane lineNumberPane;
    private static String untitledName = "untitled";

    final PopupFactory popupFactory = new PopupFactory();
    Popup docPopup;
    final JPanel docPanel = new JPanel();
    JTextArea docArea;
    protected Point popupScreenLocation = null;

    public String getCurrentFileName() {
        return currentFileName;
    }

    private String currentFileName;
    private String currentAbsoluteFileName = null;

    private RemixSwingWorker remixRunner;

    private static final ArrayList<Graphics.AnimateFunction.AnimationBlock> animations = new ArrayList<>();
    private static final Lock animationLock = new ReentrantLock();
    private static final Condition animationFinished = animationLock.newCondition();

    public static void addAnimation(Graphics.AnimateFunction.AnimationBlock animation) {
        animations.add(animation);
    }

    public static boolean animationsStopped() {
        for (Graphics.AnimateFunction.AnimationBlock animationBlock : animations) {
            if (!animationBlock.isStopped())
                return false;
        }
        return true;
    }

    private static void stopAllAnimations() {
        for (Graphics.AnimateFunction.AnimationBlock animationBlock : animations) {
            animationBlock.stopAnimation();
        }
    }

    public static void indicateAnAnimationFinished() { // called from any animation in Graphics AnimationBlock
        animationLock.lock();
        try {
            animationFinished.signalAll();
        } finally {
            animationLock.unlock();
        }
    }

    public static void waitForProgramFinish() {
        animationLock.lock();
        try {
            while (!animationsStopped()) {
                animationFinished.await();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            animationLock.unlock();
        }
    }

    public RemixEditorWindow(RemixApp remixApp, boolean darkTheme) {
        super("untitled");
        this.remixOutput = RemixApp.remixOutput;
        editorTextArea = new RemixTextArea(60, 90);
        lineNumberPane = new RTextScrollPane(editorTextArea);

        JPanel remixPanel = new JPanel(new BorderLayout());
        remixPanel.add(lineNumberPane, BorderLayout.CENTER);

        editorTextArea.setSyntaxScheme(createRemixScheme(editorTextArea));
        setDark(darkTheme);

        //Set up the menu bar.
        JMenuBar mb = new JMenuBar();
        JMenu fileMenu = createFileMenu();
        mb.add(fileMenu);
        JMenu editMenu = createEditMenu();
        mb.add(editMenu);
        JMenu controlMenu = createControlMenu();
        mb.add(controlMenu);
        setJMenuBar(mb);

        setContentPane(remixPanel);
        pack();

        docArea = new JTextArea("Document goes here.");
        docArea.setForeground(Color.red);
        docPanel.add(docArea);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (editorTextArea.isEditorContentSaved()) {
                    dispose();
                } else {
                    int response = JOptionPane.showConfirmDialog(
                            RemixEditorWindow.this,
                            "Content changed.\nAre you sure you want to close this window?",
                            "Select an Option",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE
                    );

                    if (response == JOptionPane.YES_OPTION) {
                        dispose(); // Or System.exit(0) to exit the whole app
                    }
                }
            }

            @Override
            public void windowClosed(WindowEvent e) {
                // Called after dispose() finishes
                remixApp.editorWindowClosed(RemixEditorWindow.this);
            }
        });
    }

    public void setDark(boolean dark) {
        editorTextArea.setDark(dark);
        Gutter gutter = lineNumberPane.getGutter();
        if (dark) {
            editorTextArea.setSelectionColor(new Color(100, 100, 100));
            gutter.setBackground(Color.black);
            gutter.setLineNumberColor(Color.yellow);
        } else {
            editorTextArea.setSelectionColor(new Color(220, 220, 220));
            gutter.setBackground(Color.white);
            gutter.setLineNumberColor(Color.blue);
        }
        modifyScheme(dark, editorTextArea);
    }

    private SyntaxScheme createRemixScheme(RemixTextArea textArea) {
        SyntaxScheme scheme = new SyntaxScheme(true);
        Font defaultFont = textArea.getFont();

        Font smallerFont = defaultFont.deriveFont(defaultFont.getSize() - 1f);
        Font italicFont = smallerFont.deriveFont(Font.ITALIC);

        Style commentStyle = new Style(new Color(170, 121, 66), null, italicFont);
        Style keywordStyle = new Style(Color.red);

        scheme.setStyle(RemixTokenTypes.RESERVED_WORD, keywordStyle);
        scheme.setStyle(RemixTokenTypes.COMMENT_EOL, commentStyle);
        scheme.setStyle(RemixTokenTypes.COMMENT_MULTILINE, commentStyle);
        return scheme;
    }

    private void modifyScheme(boolean dark, RemixTextArea textArea) {
        Font defaultFont = textArea.getFont();
        Font boldFont = defaultFont.deriveFont(Font.BOLD);
        Style darkLiteralStyle = new Style(Color.cyan);
        Style lightLiteralStyle = new Style(new Color(205, 127, 50));

        SyntaxScheme scheme = textArea.getSyntaxScheme();
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
        textArea.repaint();
    }

    private Point getPopupScreenLocation() {
        Point editorLocation = editorTextArea.getLocationOnScreen();
        Point caretPoint = editorTextArea.getCaretScreenLocation();
        return new Point(caretPoint.x + editorLocation.x, caretPoint.y + editorLocation.y + 16);
    }

    public boolean highlightSearch(SearchContext searchContext) {
        SearchResult matches = SearchEngine.markAll(editorTextArea, searchContext);
        return matches.getMarkedCount() > 0;
    }

    /***** File menu and items *****/

    //Create the file menu.
    protected JMenu createFileMenu() {
        JMenu menu = new JMenu("File");
        SaveFileAction saveAction = new SaveFileAction();
        menu.add(saveAction);
        SaveAsFileAction saveAsAction = new SaveAsFileAction();
        menu.add(saveAsAction);
        PrintFileAction printFileAction = new PrintFileAction();
        menu.add(printFileAction);
        return menu;
    }

    public void showDocText() {
        RemixCompletions completionControl = editorTextArea.completionControl;
        try {
            if (completionControl.noCompletions())
                popupScreenLocation = getPopupScreenLocation();
            int caretPos = editorTextArea.getCaretPosition();
            int lineNumber = editorTextArea.getCaretLineNumber();

            String docText = completionControl.completionHandling(caretPos, lineNumber);
            // the completionHandling will move the caret hence clearing popupScreenLocation
            if (docPopup != null)
                docPopup.hide();
            if (docText != null && !docText.isEmpty()) {
                docArea.setText(docText);
                docPopup = popupFactory.getPopup(editorTextArea,
                                                 docPanel,
                                                 popupScreenLocation.x,
                                                 popupScreenLocation.y);
                docPopup.show();
            }
        } catch (BadLocationException ex) {
            System.out.println("Should not happen Window showDocText");
        }
    }

    class SaveFileAction extends AbstractAction {
        public SaveFileAction() {
            super("Save");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            saveFile();
        }
    }

    class SaveAsFileAction extends AbstractAction {
        public SaveAsFileAction() {
            super("Save as…");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            saveAsFile();
        }
    }

    private void saveFile() {
        if (currentAbsoluteFileName == null)
            saveAsFile();
        else {
            try {
                Files.write(Path.of(currentAbsoluteFileName), editorTextArea.getText().getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            editorTextArea.setEditorContentSaved(true);
        }
    }

    private void saveAsFile() {
        JFileChooser chooser = new JFileChooser(RemixApp.currentDirectory);
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Remix programs", "rem");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showSaveDialog(editorTextArea);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            RemixApp.currentDirectory = chooser.getCurrentDirectory().getAbsolutePath();
            File file = chooser.getSelectedFile();
            currentFileName = file.getName();
            setTitle(currentFileName);
            currentAbsoluteFileName = file.getAbsolutePath();
            saveFile();
        }
    }

    class PrintFileAction extends AbstractAction {
        public PrintFileAction() {
            super("Print…");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            printWithCustomStyle();
        }
    }

    public void printWithCustomStyle() {
        RemixTextArea printTextArea = new RemixTextArea();
        int targetFontSize = printTextArea.getFont().getSize();
        Color targetBgColor = Color.white;
        printTextArea.setSyntaxScheme(createRemixScheme(printTextArea));
        printTextArea.setDark(false);
        printTextArea.setText(editorTextArea.getText());
        modifyScheme(false, printTextArea);

        try {
             // Extract the styled raw HTML
            int start = 0;
            int end = printTextArea.getDocument().getLength();
            String generatedHtml = HtmlUtil.getTextAsHtml(printTextArea, start, end);

            // Inject an explicit page-level CSS style body override block to force body color formatting
            JEditorPane printPane = getPrintPane(targetBgColor, generatedHtml, targetFontSize);

            // REDUCE WHITESPACE: Strip internal text component padding
//            printPane.setMargin(new Insets(0, 0, 0, 0));

            // Setup Printer Job and REDUCE WHITESPACE : Strip hardware page margins
            PrinterJob job = PrinterJob.getPrinterJob();
            PageFormat pf = job.defaultPage();
            Paper paper = getPaper(pf);
            pf.setPaper(paper);

            // Pass the adjusted PageFormat configuration to the print system
            // Wrap the JEditorPane's native printable inside our PageNumberDecorator
            Printable basePrintable = printPane.getPrintable(null, null);
            PageNumberDecorator decoratedPrintable = new PageNumberDecorator(basePrintable);

            job.setPrintable(decoratedPrintable, pf);
            if (job.printDialog()) {
                job.print();
            }
        } catch (PrinterException ex) {
            ex.printStackTrace();
        }
    }

    private static Paper getPaper(PageFormat pf) {
        Paper paper = pf.getPaper();

        // Define narrow margins (e.g., 18 points = 0.25 inches) to maximize paper usage
        // Set this to 0 if you want completely zeroed hardware margins (subject to printer limits)
        double marginInPoints = 24.0;

        paper.setImageableArea(
                marginInPoints,
                marginInPoints,
                paper.getWidth() - (marginInPoints * 2),
                paper.getHeight() - (marginInPoints * 2)
        );
        return paper;
    }

    private static JEditorPane getPrintPane(Color targetBgColor, String generatedHtml, int targetFontSize) {
        String hexColor = String.format("#%02x%02x%02x", targetBgColor.getRed(), targetBgColor.getGreen(), targetBgColor.getBlue());
        String styledHtml = generatedHtml.replace(
                "<pre style=\"",
                "<pre style=\"background-color: " + hexColor + "; font-size: " + targetFontSize + "pt; "
        );

        // Pipe the HTML into a background JEditorPane and print
        JEditorPane printPane = new JEditorPane();
        printPane.setContentType("text/html");
        printPane.setText(styledHtml);
        return printPane;
    }

    /**
     * Inner class that handles rendering page numbers over the default text printing layer.
     */
    private class PageNumberDecorator implements Printable {
        private final Printable delegate;
        private final int headerOffset = 15;

        public PageNumberDecorator(Printable delegate) {
            this.delegate = delegate;
        }

        @Override
        public int print(java.awt.Graphics g, PageFormat pageFormat, int pageIndex) throws PrinterException {
            Graphics2D g2d = (Graphics2D) g;
            // 1. Create a proxy PageFormat that subtracts the header height from the layout budget
            PageFormat adjustedFormat = new PageFormat() {
                @Override
                public double getImageableHeight() {
                    // Shrink the text block height so it finishes printing higher up the page
                    return pageFormat.getImageableHeight() - headerOffset;
                }
                @Override
                public double getImageableWidth() { return pageFormat.getImageableWidth(); }
                @Override
                public double getImageableX() { return pageFormat.getImageableX(); }
                @Override
                public double getImageableY() { return pageFormat.getImageableY(); }
                @Override
                public Paper getPaper() { return pageFormat.getPaper(); }
                @Override
                public int getOrientation() { return pageFormat.getOrientation(); }
            };

            // Intercept and shift the graphics engine downward BEFORE the delegate text prints
            g2d.translate(0, headerOffset);

            // let the HTML view layout layer render the code lines normally
            int result = delegate.print(g2d, adjustedFormat, pageIndex);

            // Shift the graphics framework back up to calculate absolute header positioning
            g2d.translate(0, -headerOffset);

            // If the document has no more code lines to print, stop drawing
            if (result == NO_SUCH_PAGE) {
                return NO_SUCH_PAGE;
            }

            // Draw the header and page numbers over the printed page canvas context

            g2d.setColor(Color.DARK_GRAY);
            g2d.setFont(new Font("SansSerif", Font.BOLD, 10));
            FontMetrics headerMetrics = g2d.getFontMetrics();

            // ==========================================
            // RENDER RUNNING TITLE (Top-Left)
            // ==========================================
            float titleX = (float) pageFormat.getImageableX();

            // FIX: Drop the baseline downward by the font's Ascent height so tops aren't clipped
            float titleY = (float) (pageFormat.getImageableY() + headerMetrics.getAscent());

            g2d.drawString(currentFileName, titleX, titleY);

            // ==========================================
            // RENDER PAGINATION FOOTER (Bottom-Right)
            // ==========================================
            g2d.setFont(new Font("SansSerif", Font.PLAIN, 9));
            FontMetrics footerMetrics = g2d.getFontMetrics();

            // Format pagination string (pageIndex is 0-indexed, so add 1)
            String pageNumText = "Page " + (pageIndex + 1);

            // Calculate bottom-right positioning alignment matching the narrow margins setup
            int textWidth = footerMetrics.stringWidth(pageNumText);

            // Position horizontally right-aligned within the allowed printable width bounds
            float footerX = (float) (pageFormat.getImageableX() + pageFormat.getImageableWidth() - textWidth);

            // Position vertically at the absolute bottom of the allowed printable area height profile
            float footerY = (float) (pageFormat.getImageableY() + pageFormat.getImageableHeight() - footerMetrics.getDescent());

            g2d.drawString(pageNumText, footerX, footerY);

            return PAGE_EXISTS;
        }

    }



    /***** Edit menu and items *****/

    //Create the edit menu.
    protected JMenu createEditMenu() {
        JMenu editMenu = new JMenu("Edit");
        editMenu.add(createMenuItem(RemixTextArea.getAction(RemixTextArea.UNDO_ACTION)));
        editMenu.add(createMenuItem(RemixTextArea.getAction(RemixTextArea.REDO_ACTION)));
        editMenu.addSeparator();

        editMenu.add(createMenuItem(RemixTextArea.getAction(RemixTextArea.CUT_ACTION)));
        editMenu.add(createMenuItem(RemixTextArea.getAction(RemixTextArea.COPY_ACTION)));
        editMenu.add(createMenuItem(RemixTextArea.getAction(RemixTextArea.PASTE_ACTION)));
        editMenu.add(createMenuItem(RemixTextArea.getAction(RemixTextArea.DELETE_ACTION)));
        editMenu.addSeparator();

        Action find = new FindAction("Find");
        editMenu.add(find);
        Action findForwards = new FindForwardAction("Find forwards");
        editMenu.add(findForwards);
        Action backFind = new FindBackwardAction("Find backwards");
        editMenu.add(backFind);
        editMenu.addSeparator();

        Action increaseIndent = editorTextArea.getActionMap().get(RSyntaxTextAreaEditorKit.insertTabAction);
        increaseIndent.putValue(Action.NAME, "Indent selection");
        increaseIndent.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.META_DOWN_MASK));

        editMenu.add(increaseIndent);
        Action decreaseIndent = editorTextArea.getActionMap().get(RSyntaxTextAreaEditorKit.rstaDecreaseIndentAction);
        decreaseIndent.putValue(Action.NAME, "Dedent selection");
        decreaseIndent.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.META_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));
        editMenu.add(decreaseIndent);
        editMenu.addSeparator();

        editMenu.add(createMenuItem(RemixTextArea.getAction(RemixTextArea.SELECT_ALL_ACTION)));
        return editMenu;
    }

    private static JMenuItem createMenuItem(Action action) {
        JMenuItem item = new JMenuItem(action);
        item.setToolTipText(null); // Swing annoyingly adds tool tip text to the menu item
        return item;
    }

    protected RunAction runAction;
    protected StopAction stopAction;
    
    /***** Control menu and items *****/
    protected JMenu createControlMenu() {
        JMenu menu = new JMenu("Control");
        runAction = new RunAction();
        menu.add(runAction);
        stopAction = new StopAction();
        menu.add(stopAction);
        return menu;
    }

    protected class RunAction extends AbstractAction {

        protected RunAction() {
            super("Run");
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.META_DOWN_MASK));
            setEnabled(true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            remixOutput.setFocusable(true); // Make it focusable again for future use
            remixRunner = new RemixSwingWorker(
                    RemixEditorWindow.this
            );
            stopAction.setEnabled(true);
            setEnabled(false); // changed back when running finishes or is terminated
            animations.clear();
            remixRunner.execute(); // this causes the program to run in a background thread
        }
    }

    protected class StopAction extends AbstractAction {

        protected StopAction() {
            super("Stop");
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.META_DOWN_MASK));
            setEnabled(false);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            remixRunner.cancel(true);
            stopAllAnimations();
            System.out.println("Program cancelled.");
            setEnabled(false);
            runAction.setEnabled(true);
        }
    }

    public void newFileInWindow() {
        untitledName = "*" + untitledName + "*";
        currentFileName = untitledName;
        setTitle(currentFileName);
        editorTextArea.setCaretPosition(0);
        editorTextArea.setEditorContentSaved(false);
    }

    public boolean openFileInWindow(File remFile) {
        currentFileName = remFile.getName();
        currentAbsoluteFileName = remFile.getAbsolutePath();
        setTitle(currentFileName);
        try {
            Scanner myReader = new Scanner(remFile);
            int position = 0;
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                editorTextArea. insert(line + "\n", position);
                position += line.length() + 1;
            }
            myReader.close();
            editorTextArea.setCaretPosition(0);
            editorTextArea.setEditorContentSaved(true);
        } catch (FileNotFoundException e) {
            return false;
        }
        return true;
    }

    private SearchContext searchContext = null;

    public class FindAction extends AbstractAction {

        public FindAction(String name) {
            super(name);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.META_DOWN_MASK));
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String searchTerm = JOptionPane.showInputDialog(
                    editorTextArea,         // Parent component
                    "Enter search term:",   // Message
                    "Find",                 // Title
                    JOptionPane.QUESTION_MESSAGE // Message type (icon)
            );

            // Process the input
            if (searchTerm != null && !searchTerm.isEmpty()) {
                searchContext = new SearchContext();
                searchContext.setMarkAll(false);
                searchContext.setSearchFor(searchTerm);
                searchContext.setSearchForward(true);
                editorTextArea.searchForward(searchContext);
            }
        }
    }

    public class FindForwardAction extends AbstractAction {

        public FindForwardAction(String name) {
            super(name);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.META_DOWN_MASK));
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (searchContext != null)
                editorTextArea.searchForward(searchContext);
        }
    }

    public class FindBackwardAction extends AbstractAction {

        public FindBackwardAction(String name) {
            super(name);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.META_DOWN_MASK));
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (searchContext != null) {
                searchContext.setSearchForward(false);
                editorTextArea.searchBack(searchContext);
            }
        }
    }

    public String getProgramText() {
        return editorTextArea.getText();
    }

    static void main() {
        // Just to test. This is not really main.
        // Start all Swing applications on the EDT.
        SwingUtilities.invokeLater(() -> new RemixEditorWindow(null, true).setVisible(true));
    }
}
