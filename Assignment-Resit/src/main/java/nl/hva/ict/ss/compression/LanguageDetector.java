package nl.hva.ict.ss.compression;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LanguageDetector {
    private String content; // Once an instance is created this will hold the complete content of the file.
    private String code;
    private String docs;
    private int letterCount[];

    public LanguageDetector(InputStream input) {
        Scanner sc = new Scanner(input);
        sc.useDelimiter("\\Z"); // EOF marker
        content = (sc.next());
        separateDocsFromCode();
        removeNonMethodCalls();
        findMethodCalls();
        alphabetCounter();

    }

    public void separateDocsFromCode() {
        code = content.toLowerCase();
        StringBuilder docsBuilder = new StringBuilder();
        Pattern commentFinder = Pattern.compile("(?s)/\\*(.)*?\\*/", Pattern.MULTILINE | Pattern.COMMENTS);
        Matcher matcher = commentFinder.matcher(code);
        Matcher matcher2 = commentFinder.matcher(code);
        while (matcher.find()) {
            docsBuilder.append(matcher.group());
        }
        while (matcher2.find()) {
            code = matcher.replaceAll("");
        }
        docs = docsBuilder.toString();
        System.out.println("DOCS:");
        System.out.println(docs);
        System.out.println("-----------------------------");
        System.out.println("CODE:");
        System.out.println(code);
        System.out.println("-----------------------------");

    }

    public void removeNonMethodCalls() {



        Pattern removeNonFunctionCalls = Pattern.compile("(while|switch|for|if|public|static|private|protected|void)(.*?)((\\()|(\\{))", Pattern.MULTILINE | Pattern.COMMENTS);
        Matcher letterMatcher = removeNonFunctionCalls.matcher(code);
//        System.out.println(code);
        while (letterMatcher.find()) {
            code = letterMatcher.replaceAll("");
        }
//        System.out.println(code);


    }
    public void findMethodCalls() {
        ArrayList <String> methodCalls = new ArrayList<>();
        Pattern getFunctionCalls = Pattern.compile("(([a-z0-9<>\\.\\[\\]])+?)\\s*(\\()(([a-z0-9<>\\-\\.\\,\\+\\/\\[\\\\\\]()\\s\"\'])*)(\\))", Pattern.MULTILINE | Pattern.COMMENTS);
        Matcher functionCallMatcher = getFunctionCalls.matcher(code);
        while (functionCallMatcher.find()) {
            methodCalls.add(functionCallMatcher.group());
        }
        StringBuilder methodsFound = new StringBuilder();
        for (String method : methodCalls){
//            System.out.println(method);
            methodsFound.append(method+"\n");
        }
        String allMethods = methodsFound.toString();
//        System.out.println("ALLMETHODS");
//        System.out.println(allMethods); (([a-z0-9<>\.\[\]])*?)\s*(\()

//        System.out.println("aaa");
        System.out.println("First method call list" + allMethods);
        Pattern getInnerFunctions = Pattern.compile("(\\(([a-z0-9<>\\-\\.\\,\\+\\/\\s\\[\\\\\\]()\"'])*?)\\s*(\\()(([a-z0-9<>\\-\\.\\,\\+\\/\\s*\\[\\\\\\]()\"'])*?)(\\))", Pattern.MULTILINE | Pattern.COMMENTS);
        Matcher innerFunctionMatcher = getInnerFunctions.matcher(code);
        ArrayList <String> secondCalls = new ArrayList<>();
        System.out.println("------------------------------------");

        while (innerFunctionMatcher.find()) {
            String innerCall = innerFunctionMatcher.group();
            Matcher getFunc = getFunctionCalls.matcher(innerCall);
            while (getFunc.find()) {

                secondCalls.add(getFunc.group());
            }

        }
        System.out.println("Inner method calls list");
        for (String secondCall : secondCalls){
            System.out.println(secondCall);

        }
        System.out.println("Total amount of methods found: "+(methodCalls.size()+secondCalls.size()));
        System.out.println("------------------------------------");


    }


    public void alphabetCounter() {
        ArrayList<String> characters = new ArrayList<>();
        ArrayList<Integer> count = new ArrayList<>();

        int totalLetters = 0;
        String lettersInCountingOrder = "";
        //Makes array with 123 slots, only 97 to 122 will be used
        letterCount = new int[123];
        for (int i = 0; i < 123; i++) {
            letterCount[i] = 0; // -1 for chars not in pattern
//            System.out.println(letterCount[i]);
        }
        for (int i = 'a'; i <= 'z'; i++) {
            Pattern letterPattern = Pattern.compile("" + (char) i, Pattern.MULTILINE | Pattern.COMMENTS);
            Matcher letterMatcher = letterPattern.matcher(docs);
            while (letterMatcher.find()) {
                letterCount[i]++;
            }
            System.out.println((char)i+":"+letterCount[i]);
        }
        System.out.println("------------------------------------");
        System.out.println("Letters in count order:");
        while (true) {
            int biggestNumber = 0;
            int biggestIndex = 0;
            for (int i = 0; i < 123; i++) {
                if (letterCount[i] > biggestNumber) {
                    biggestIndex = i;
                    biggestNumber = letterCount[i];
                }
            }

            if (biggestNumber > 0) {
                totalLetters += biggestNumber;
                characters.add("" + (char) biggestIndex);
                count.add(biggestNumber);
                lettersInCountingOrder += "" + (char) biggestIndex;

                letterCount[biggestIndex] = 0;
                System.out.println(lettersInCountingOrder);
            } else {
//                System.out.println(lettersInCountingOrder);
                break;
            }
        }

        System.out.println("------------------------------------");

        System.out.println("Now printing all characters with their percentage of occurance");
        for (int i = 0; i < characters.size(); i++) {
            Double c = Double.valueOf(count.get(i));
            Double total = Double.valueOf(totalLetters);
            System.out.println(characters.get(i) + " occurred " + (c / total) * 100 + "%");
        }
        System.out.println("------------------------------------");

    }


}
//a = 97
//z = 122
