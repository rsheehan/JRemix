package edu.fizz.remix.editor;

import edu.fizz.remix.runtime.LibrariesAndCompletions;

import java.util.ArrayList;
import java.util.Collections;

public class CompletionTester {

    public static void main(String[] args){
//        try {
//            LibrariesAndCompletions.prepareEnvironment();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        LibrariesAndCompletions.resetToEditorStandard();
//        for (String name : LibrariesAndCompletions.originalFunctionList) {
//            System.out.printf("%s: %s%n", name, LibrariesAndCompletions.completionTable.get(name));
//        }
//        String searchWord = "app";
//        int i = Collections.binarySearch(LibrariesAndCompletions.originalFunctionList, searchWord);
//        if (i < 0) {
//            i = Math.abs(i) - 1;
//        }
        ArrayList<String> completions = new ArrayList<>();
        System.out.println(completions);
    }
}
