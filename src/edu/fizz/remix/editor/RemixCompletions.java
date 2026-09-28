package edu.fizz.remix.editor;

import edu.fizz.remix.runtime.LibrariesAndCompletions;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class RemixCompletions {

    private final RemixTextArea textArea;
    private CompletionInfo completionsHere;

    public RemixCompletions(RemixTextArea textArea) {
        this.textArea = textArea;

        String actionKey = "ShiftTabAction";
        textArea.getInputMap().put(KeyStroke.getKeyStroke("shift TAB"), actionKey);
        textArea.getActionMap().put(actionKey, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RemixEditorWindow window = textArea.getWindow();
                window.showDocText();
            }
        });

        actionKey = "EscAction";
        textArea.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), actionKey);
        textArea.getActionMap().put(actionKey, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("cancelCompletionHanding");
                cancelCompletionHandling();
            }
        });
    }

    public void clearCompletions() {
        completionsHere = null;
        RemixEditorWindow window = textArea.getWindow();
        if (window.docPopup != null)
            window.docPopup.hide();
    }

    // Reverts to original value
    public void cancelCompletionHandling() {
        String completion;
        if (completionsHere != null) {
            int completionLength = completionsHere.currentLength();
            completion = completionsHere.originalCompletion();
            int endOffset = completionsHere.offset + completionLength;
            textArea.replaceRange(completion.substring(0, completion.length() - 1), completionsHere.offset, endOffset);
            clearCompletions();
        }
    }

    public boolean noCompletions() {
        return completionsHere == null;
    }

    // Called from keystroke event handler set up in RemixTextArea.
    public String completionHandling(int offset, int lineNumber) throws BadLocationException {
        ArrayList<String> completions;
        String completionAndDoc;
        String completionText;
        String completionComment = "";
        int splitPos;
        if (completionsHere == null) {
            textArea.reparseProgramText(); // editor can be null if printing this document
            String seedWord = wordSoFar(offset);
            if (seedWord.startsWith("'")) { // variable
                completions = LibrariesAndCompletions.variableCompletionsFrom(seedWord);
                seedWord += "'";
                offset += 1;
            } else if (seedWord.startsWith("#")) { // refvar
                completions = LibrariesAndCompletions.variableCompletionsFrom(seedWord);
            } else if (seedWord.equals(seedWord.toUpperCase())) { // constant
                completions = LibrariesAndCompletions.constantCompletionsFrom(seedWord, lineNumber);
            } else { // function call
                seedWord = seedWord.stripLeading();
                completions = LibrariesAndCompletions.createCompletionsFrom(seedWord, lineNumber);
            }
            if (!seedWord.isEmpty() && !completions.isEmpty()) {
                int seedLength = seedWord.length();
                if (!seedWord.startsWith("'") && !seedWord.startsWith("#") && !seedWord.equals(seedWord.toUpperCase())) // only function calls
                    completions.add(seedWord + "\n");
                completionsHere = new CompletionInfo(completions, offset - seedLength);
                completionAndDoc = completionsHere.nextCompletion();
                splitPos = completionAndDoc.indexOf('\n');
                completionText = completionAndDoc.substring(0, splitPos);
                completionComment = completionAndDoc.substring(splitPos + 1);
                int endOffset = completionsHere.offset + seedLength;

                textArea.replaceRangeNoCaretListener(completionText, completionsHere.offset, endOffset);
                textArea.setCaretPosition(completionsHere.offset);
                int lineStartOffset = textArea.getLineStartOffset(lineNumber);
                AutoTab.selectedNextParam(textArea,
                                          AutoUtil.extractLine(textArea, lineNumber),
                                          lineNumber, completionsHere.offset - lineStartOffset,
                                          lineStartOffset);
            }
        } else {
            int completionLength = completionsHere.currentLength();
            completionAndDoc = completionsHere.nextCompletion();
            splitPos = completionAndDoc.indexOf('\n');
            completionText = completionAndDoc.substring(0, splitPos);
            completionComment = completionAndDoc.substring(splitPos + 1);
            int endOffset = completionsHere.offset + completionLength;

            textArea.replaceRangeNoCaretListener(completionText, completionsHere.offset, endOffset);
            textArea.setCaretPosition(completionsHere.offset);
//            AutoTab.fromCaretParamMove(textArea);
            int lineStartOffset = textArea.getLineStartOffset(lineNumber);
            AutoTab.selectedNextParam(textArea,
                                      AutoUtil.extractLine(textArea, lineNumber),
                                      lineNumber, completionsHere.offset - lineStartOffset,
                                      lineStartOffset);
        }
        return completionComment.isEmpty() ? null : completionComment;
    }

    /* From the current position move back to gather a word. */
    private String wordSoFar(int pos) throws BadLocationException {
        StringBuilder word = new StringBuilder();
        boolean nextCharQuote = false;
        String ch;
        boolean couldBeConstant = false;

        if (textArea.getDocument().getLength() > pos) {
            ch = textArea.getText(pos, 1);
            nextCharQuote = ch.equals("'");
        }
        if (pos > 0) {
            ch = textArea.getText(pos - 1, 1);
            if (Character.isUpperCase(ch.charAt(0)) || ch.equals("-")) {
                couldBeConstant = true;
            }
        }
        while (--pos >= 0) {
            ch = textArea.getText(pos, 1);
            if (couldBeConstant) {
                if (" .()[\\]{,}:—§@…’0123456789×÷≤≥≠=√²↲⊕\"\t\n".contains(ch))
                    break;
                if (!Character.isUpperCase(ch.charAt(0)) && !ch.equals("-")) {
                    couldBeConstant = false;
                }
            }
            if (".()[\\]{,}:—§@…’0123456789×÷≤≥≠=√²↲⊕\"\t\n".contains(ch)) // ⊕+*×÷%=≠<≤>≥
                break;
            if (ch.equals("'")) {
                if (nextCharQuote)
                    word.append(ch); // puts quote at beginning as flag
                break;
            }
            word.append(ch);
        }
        word.reverse();
        return word.toString();
    }

    public int completionNumber() {
        return completionsHere == null ? -1 : completionsHere.currentIndex;
    }

    private static class CompletionInfo {

        private final ArrayList<String> completionList;
        private final int offset;
        private int currentIndex = -1;

        private CompletionInfo(ArrayList<String> completionList, int offset) {
            this.completionList = completionList;
            this.offset = offset;
        }

        private int currentLength() {
            String completion = completionList.get(currentIndex);
            return completion.substring(0, completion.indexOf('\n')).length();
        }

        private String nextCompletion() {
            currentIndex++;
            if (currentIndex >= completionList.size())
                currentIndex = 0;
            return completionList.get(currentIndex);
        }

        private String originalCompletion() {
            return completionList.getLast();
        }
    }
}
