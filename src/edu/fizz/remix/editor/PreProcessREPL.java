package edu.fizz.remix.editor;

import java.io.*;

public class PreProcessREPL {


    public static String processContents(String contents) throws IOException {
        BufferedReader reader = new BufferedReader(new StringReader(contents));
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintWriter writer = new PrintWriter(output);
        writer.write('\n'); // to help the lexer find start of line

        while (dealWithLine(reader, writer)) ; // intentionally empty

        reader.close();
        writer.close();
        return output.toString();
    }

    /*
     * Deals with a line all the way to the logical end.
     * Takes comments and strings into account.
     * The end of line is appended to with brackets if required.
     */
    private static boolean dealWithLine(BufferedReader reader, PrintWriter writer) throws IOException {
        int lineDepth = 0;
        int ch;
        StringBuilder restOfLine = new StringBuilder();
        ch = reader.read();
        if (ch == -1)
            return finishFile(lineDepth, restOfLine.toString(), writer);
        // first handle tabs if any
        while (ch == '\t') {
            writer.write(ch);
            lineDepth++;
            ch = reader.read(); // first character following tabs
            if (ch == -1)
                return finishFile(lineDepth, restOfLine.toString(), writer);
        }
        // then deal with comment lines or blocks
        // don't add block closures if inside comments
        if (ch == '-') {
            writer.write(ch);
            gobbleToEndOfLine(reader, writer);
            return true; // back and on to next line
        } else if (ch == '=') {
            writer.write(ch);
            gobbleToEndOfCommentBlock(reader, writer);
            return true; // back and on to next line
        } else if (ch == '\n') { // it was a blank line
            writer.write(ch);
            return true;
        } else { // print all characters until either '\n' or ';'
            // doesn't deal with ';' comment in first position on line
            while (ch != '\n' && ch != ';') {
                writer.write(ch);
                if (ch == '"') {
                    gobbleString(reader, writer);
                }
                // reader.mark(1);
                ch = reader.read();
                if (ch == -1) // end of the file so need to
                    return finishFile(lineDepth, restOfLine.toString(), writer);
                if (ch == '\n') {
                    // this is the position we need to put block brackets if required
                    restOfLine = new StringBuilder("\n");
                } else if (ch == ';') {
                    // scan to end of the line keeping the string for writing later
                    restOfLine = new StringBuilder(";");
                    int commentCh;
                    do {
                        commentCh = reader.read();
                        if (commentCh == -1) {
                            return finishFile(lineDepth, restOfLine.toString(), writer); // another end of file
                        }
                        restOfLine.append((char) commentCh);
                    } while (commentCh != '\n');
                }
            }
            // still need to print the rest of the line
            // find the following indentation level
            int tabCount = findFollowingIndentation(reader);
            if (tabCount == lineDepth + 1) {
                writer.write('['); // precede newline with '['
            } else {
                closeBlocks(tabCount, lineDepth, writer);
            }
            // print the rest of the line e.g. in remaining comment
            writer.write(restOfLine.toString());
            return true;
        }
    }

    private static boolean finishFile(int lineDepth, String restOfLine, PrintWriter writer) {
        closeBlocks(0, lineDepth, writer);
        writer.write(restOfLine);
        return false;
    }

    /*
     * Read ahead until we can determine the indentation of the following
     * non-comment, non-empty line.
     * Return the depth of the following line.
     */
    private static int findFollowingIndentation(BufferedReader reader) throws IOException {
        // called just after a new line
        reader.mark(131072); // so can reset to here
        int tabCount = 0;
        int ch;
        while ((ch = reader.read()) != -1) {
            // need to ignore comment lines and sections
            // a comment line starts possible tabs then "-"
            // a comment block possible tabs then "="
            // and continues until another line with "="
            switch (ch) {
                case ' ' -> {
                    System.out.println("No spaces allowed at the start of a line.");
                    return -1; // indicates error
                }
                case '-' -> {
                    scanToEndOfLine(reader);
                    tabCount = 0;
                }
                case '=' -> {
                    scanToEndOfCommentBlock(reader);
                    tabCount = 0;
                }
                case '\n' ->
                    // this is only after no valid indented lines so far
                        tabCount = 0;
                case '\t' -> tabCount++;
                default -> {
                    reader.reset();
                    return tabCount;
                }
            }
        }
        reader.reset();
        return tabCount;
    }

    /*
     * Gobbles and outputs all characters inside the string
     * including the starting and closing ".
     * Takes escaped quotes into account.
     */
    static void gobbleString(BufferedReader reader, PrintWriter writer) throws IOException {
        int ch;
        do {
            ch = reader.read();
            if (ch == -1)
                return;
            // need to deal with escaped double quotes
            if (ch == '\\') {
                writer.write('\\');
                ch = reader.read();
                if (ch == -1)
                    return;
                if (ch == '"') {
                    writer.write('"');
                    ch = 0; // so while repeats
                    continue;
                }
            }
            writer.write((char)ch);
        } while (ch != '"');
    }

    /*
     * Gobbles and outputs all characters on the line
     * from this position. Doesn't add the '-' because
     * also called from gobbleToEndOfCommentBlock.
     */
    static void gobbleToEndOfLine(BufferedReader reader, PrintWriter writer) throws IOException {
        // comes after a '-'
        int i;
        do {
            i = reader.read();
            if (i == -1)
                return;
            writer.write((char)i);
        } while (i != '\n'); // stops after reading new line
    }

    private static void gobbleToEndOfCommentBlock(BufferedReader reader, PrintWriter writer) throws IOException {
        // comes after a '='
        int ch;
        gobbleToEndOfLine(reader, writer); // first line
        // if a "\t" then we will miss the "="
        do {
            do {
                ch = reader.read();
                if (ch == -1)
                    return;
                writer.write((char) ch);
            } while (ch == '\t');
            if (ch == '=') {
                break;
            }
            if (ch != '\n')
                gobbleToEndOfLine(reader, writer);
        } while (true);
        gobbleToEndOfLine(reader, writer); // last line
    }

    static void scanToEndOfLine(BufferedReader reader) throws IOException {
        // called while reading ahead to ignore this line
        // comes after a '-' or ';'
        int ch;
        do {
            ch = reader.read();
            if (ch == -1)
                return;
        } while (ch != '\n');
    }

    private static void scanToEndOfCommentBlock(BufferedReader reader) throws IOException {
        int ch = 0;
        do {
            if (ch != '\n')
                scanToEndOfLine(reader);
            do {
                ch = reader.read();
                if (ch == -1)
                    return;
            } while (ch == '\t'); // gobble any tabs before "="
        } while (ch != '=');
        scanToEndOfLine(reader);
    }

    static void closeBlocks(int tabCount, int depth, PrintWriter writer) {
        while (tabCount < depth) {
            writer.write(']');
            depth--;
        }
    }

}