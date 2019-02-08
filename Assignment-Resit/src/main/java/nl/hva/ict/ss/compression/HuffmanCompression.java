package nl.hva.ict.ss.compression;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
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
     * Returns a list with the character and the code that is used to encode it.
     * The format per entry is: "'char' -> code"
     * For "aba" this would result in: ["'b' -> 0", "'a' -> 1"]
     * And for "cacbcac" this would result in: ["'b' -> 00", "'a' -> 01", "'c' -> 1"]
     *
     * @return the Huffman codes
     */

    String[] getCodes() {
        //Use Node to save all node values from left to right
        ArrayList<Node> nodeCodes = new ArrayList<>();
        Node root = getCompressionTree();
//        while (root.getRight()=null)


        String codes[] = new String[4];
        return codes;
    }

    public ArrayList<Node> getNodeCodes(Node node) {
        ArrayList<Node> addingList = new ArrayList<>();

        if (node.getCharacter()!=null){
            addingList.add(node);
            System.out.println("node added");
        }
        else {
            for (Node n : getNodeCodes(node.getLeft())){
                addingList.add(n);
            }
            for (Node n : getNodeCodes(node.getRight())){
                addingList.add(n);
            }
        }
        return addingList;
    }

    /**
     * Returns the root of the compression tree.
     *
     * @return the root of the compression tree.
     */

    Node getCompressionTree() {
        ArrayList<Node> nodeList = createNodeList();

        Collections.sort(nodeList, Collections.reverseOrder());
        for (Node node : nodeList
        ) {
            System.out.println(node.getCharacter() + " " + node.getWeight());
        }
        System.out.println(nodeList.size());

        while (nodeList.size() > 2) {
            System.out.println(nodeList.get(nodeList.size() - 1).getWeight());
            Node node1 = nodeList.get(nodeList.size() - 1);
            nodeList.remove(nodeList.get(nodeList.size() - 1));
            Node node2 = nodeList.get(nodeList.size() - 1);
            nodeList.remove(nodeList.get(nodeList.size() - 1));
            nodeList.add(new Node(node1, node2));
            Collections.sort(nodeList, Collections.reverseOrder());
        }

        Node root = new Node(nodeList.get(nodeList.size() - 1), nodeList.get(nodeList.size() - 2));
        System.out.println(root.getWeight());
        System.out.println(root.getLeft().getWeight());
        System.out.println(root.getRight().getWeight());
        return root;
    }

    ArrayList<Node> createNodeList() {
        ArrayList<Node> nodelist = new ArrayList<>();
        int maxAscii = 128;
        int charsInText = 0;
        int letterCount[] = new int[maxAscii];
        for (int i = 0; i < maxAscii; i++) {
            letterCount[i] = 0;
        }
        for (int i = 0; i < text.length(); i++) {
            int currentChar = text.charAt(i);
            letterCount[currentChar]++;
        }
        for (int i = 0; i < maxAscii; i++) {
            if (letterCount[i] > 0) {
                ++charsInText;
            }
        }
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
                    nodelist.add(new Node(letterCount[biggestIndex], (char) biggestIndex));
                    letterCount[biggestIndex] = 0;
                }
            }
        }

//        for (Node node : nodelist
//        ) {
//            System.out.println(node.getCharacter() + " " + node.getWeight());
//        }
//        System.out.println(nodelist.size());
        return nodelist;
    }


}
