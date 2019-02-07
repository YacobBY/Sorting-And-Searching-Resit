package nl.hva.ict.ss.compression;

import java.io.InputStream;
import java.util.Scanner;


public class HuffmanCompression {
    private final String text;

    public HuffmanCompression(String text) {
        this.text = text;
    }

    public HuffmanCompression(InputStream input) {
        Scanner sc = new Scanner(input);
        sc.useDelimiter("\\Z"); // EOF marker
        text = sc.next();
    }

    /**
     * Returns the compression ratio assuming that every characters in the text uses 8 bits.
     *
     * @return the compression ratio.
     */
    public double getCompressionRatio() {
        return 0.0;
    }

    /**
     * Returns the root of the compression tree.
     *
     * @return the root of the compression tree.
     */
    Node getCompressionTree() {
        return null;
    }

    /**
     * Returns a list with the character and the code that is used to encode it.
     * The format per entry is: "'char' -> code"
     * For "aba" this would result in: ["'b' -> 0", "'a' -> 1"]
     * And for "cacbcac" this would result in: ["'b' -> 00", "'a' -> 01", "'c' -> 1"]
     *
     * @return the Huffman codes
     */
    String[] getCodes() {
        int maxAscii = 129;
        int charsInText = 0;
        int letterCount[] = new int[maxAscii];
        for (int i = 0; i < maxAscii; i++) {
            letterCount[i] = 0; // -1 for chars not in pattern
//            System.out.println(letterCount[i]);
        }
        for (int i = 0; i < text.length() - 1; i++) {
            int currentChar = text.charAt(i);
            letterCount[currentChar]++;
        }
        for (int i = 0; i < maxAscii; i++) {
            if (letterCount[i] > 0) {
                ++charsInText;
//                System.out.println("index " +i+ " "+letterCount[i]);
            }
        }

        String codeList[] = new String[charsInText];
        String countList[] = new String[charsInText];
        for (Integer c = 0; c <= charsInText; c++) {
            {
                int biggestNumber = 0;
                int biggestIndex = 0;
                for (int i = 0; i < maxAscii; i++) {
                    if (letterCount[i] > biggestNumber) {
                        biggestIndex = i;
                        biggestNumber = letterCount[i];
                    }
                }

                if (biggestNumber > 0) {
                    codeList[c] = "'" + (char) biggestIndex + "' ->" + Integer.toBinaryString(c);
                    countList[c] = "'" + (char) biggestIndex + "Count" + letterCount[biggestIndex];
                    letterCount[biggestIndex] = 0;
                }

            }

        }
        for (int i = 0; i < codeList.length; i++) {
            System.out.println(codeList[i]);
            System.out.println(countList[i]);
        }


        return codeList;
    }

}
