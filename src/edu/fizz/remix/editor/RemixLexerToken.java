package edu.fizz.remix.editor;

public class RemixLexerToken {
    public static final int COLON = 1;
    public static final int LPAREN = 2;
    public static final int RPAREN = 3;
    public static final int LBLOCK = 4;
    public static final int RBLOCK = 5;
    public static final int LBRACE = 6;
    public static final int RBRACE = 7;
    public static final int COMMA = 8;
    public static final int ENDPRINT = 9;
    public static final int PRINTLN = 10;
    public static final int SPACE = 11;
    public static final int CONT = 12;
    public static final int EOL = 13;
    public static final int EOS = 14;
    public static final int DOC_COMMENT = 15;
    public static final int COMMENT = 16;
    public static final int COMMENT_SECTION = 17;
    public static final int NUMBER = 18;
    public static final int ADD = 19;
    public static final int MUL = 20;
    public static final int LESS = 21;
    public static final int GREATER = 22;
    public static final int LESSEQUAL = 23;
    public static final int GREATEREQUAL = 24;
    public static final int EQUAL = 25;
    public static final int NOTEQUAL = 26;
    public static final int CONCAT = 27;
    public static final int MINUS = 28;
    public static final int NULL = 29;
    public static final int BOOLEAN = 30;
    public static final int RETURN = 31;
    public static final int REDO = 32;
    public static final int CREATE = 33;
    public static final int EXTEND = 34;
    public static final int GETTERSETTER = 35;
    public static final int GETTER = 36;
    public static final int SETTER = 37;
    public static final int LIBRARY = 38;
    public static final int USING = 39;
    public static final int SELFREF = 40;
    public static final int CONSTANT = 41;
    public static final int IDENTIFIER = 42;
    public static final int BAD_IDENTIFIER = 43;
    public static final int WORD = 44;
    public static final int WORDPRODUCT = 45;
    public static final int STRING_START = 46;
    public static final int COMMENT_END = 47;
    public static final int COMMENT_INCOMPLETE = 48;
    public static final int COMMENT_TEXT = 49;
    public static final int STRING_TEXT = 50;
    public static final int STRING_END = 51;
    public static final int STRING_INCOMPLETE = 52;

//    private final int tokenNumber;

//    RemixLexerToken(int tokenNumber) {
//        this.tokenNumber = tokenNumber;
//    }
//
//    // 1. Create a static map to store the relationships
//    private static final Map<Integer, RemixLexerToken> BY_CODE = new HashMap<>();
//
//    // 2. Populate the map in a static block when the class loads
//    static {
//        for (RemixLexerToken status : values()) {
//            BY_CODE.put(status.tokenNumber, status);
//        }
//    }
//
//    public int getTokenNumber() {
//        return tokenNumber;
//    }
//
//    // 3. Expose a public static method for reverse lookup
//    public static RemixLexerToken fromToken(int code) {
//        return BY_CODE.get(code); // Returns null if code doesn't exist
//    }
}
