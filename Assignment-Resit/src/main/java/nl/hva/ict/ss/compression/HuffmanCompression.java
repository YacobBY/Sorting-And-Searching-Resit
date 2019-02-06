package nl.hva.ict.ss.compression;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
     * @return the compression ratio.
     */
    public double getCompressionRatio() {
        return 0.0;
    }

    /**
     * Returns the root of the compression tree.
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
     * @return the Huffman codes
     */
    String[] getCodes() {
        int maxAscii = 128;
        int letterCount[] = new int[maxAscii];
        for (int i = 0; i < maxAscii; i++) {
            letterCount[i] = 0; // -1 for chars not in pattern
//            System.out.println(letterCount[i]);
        }
        for (int i = 0; i < text.length()-1; i++) {
            int currentChar = text.charAt(i);
            letterCount[currentChar]++;

//            System.out.println((char)i+":"+letterCount[i]);
        }

        for (int i = 0; i < maxAscii; i++) {

            System.out.println(letterCount[i]);
        }

        return null;
    }

}
