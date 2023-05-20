package edu.fizz.remix.editor;

import edu.fizz.remix.runtime.Runtime;

import java.util.ArrayList;
import java.util.Collections;

public class CompletionTester {

    public static void main(String[] args){
        try {
            Runtime.prepareEnvironment();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Runtime.buildOriginalCompletions();
        for (String name : Runtime.originalFunctionList) {
            System.out.printf("%s: %s%n", name, Runtime.completionTable.get(name));
        }
        String searchWord = "app";
        int i = Collections.binarySearch(Runtime.originalFunctionList, searchWord);
        if (i < 0) {
            i = Math.abs(i) - 1;
        }
        ArrayList<String> completions = new ArrayList<>();
//        while (i >= 0 && i < Runtime.originalFunctionList.size()) {
//            String completion = Runtime.originalFunctionList.get(i);
//            if (completion.startsWith(searchWord)) {
//                completions.add(Runtime.completionTable.get(completion));
//            } else
//                break;
//            i++;
//        }
        System.out.println(completions);
    }
}
