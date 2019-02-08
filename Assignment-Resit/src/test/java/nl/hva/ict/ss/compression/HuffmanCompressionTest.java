package nl.hva.ict.ss.compression;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HuffmanCompressionTest {
    HuffmanCompression compressor;

    @Before
    public void setup() {
        compressor = new HuffmanCompression(getClass().getResourceAsStream("/edu/princeton/cs/algs4/Huffman.java"));
    }

    @Test
    public void checkWeightSimple() {
        compressor = new HuffmanCompression("aba");

        Node root = compressor.getCompressionTree();
        System.out.println(root.getLeft().getCharacter()+ " weight =  "+ root.getLeft().getWeight());
        System.out.println(root.getRight().getCharacter()+ " weight =  "+ root.getRight().getWeight());
        assertEquals(3, root.getWeight());
        Node left = root.getLeft();
        Node right = root.getRight();
        assertEquals(1, left.getWeight());
        assertEquals(2, right.getWeight());
    }

    @Test
    public void nodelistSize() {
        // Handle Linux/Mac and Windows end-of-line characters, 87 and 88 are both ok.
        int  nodeListSize = compressor.createNodeList().size();
        System.out.println(compressor.getCodes().length);
        assertTrue("You appear to have some very strange end-of-line configuration on your machine!", nodeListSize == 86|| nodeListSize == 87 || nodeListSize == 88);
    }

    @Test
    public void checkNodes() {
        // Handle Linux/Mac and Windows end-of-line characters, 87 and 88 are both ok.
        Node root = compressor.getCompressionTree();
        System.out.println(root.getLeft().getWeight());
        System.out.println(root.getLeft().getLeft().getWeight());
        System.out.println(root.getLeft().getRight().getWeight());
        System.out.println(root.getRight().getWeight());

    }

    @Test
    public void checkUniqueCharacter() {
        // Handle Linux/Mac and Windows end-of-line characters, 87 and 88 are both ok.
        int numberOfChars = compressor.getCodes().length;
        System.out.println(compressor.getCodes().length);
        assertTrue("You appear to have some very strange end-of-line configuration on your machine!", numberOfChars == 86|| numberOfChars == 87 || numberOfChars == 88);
    }


    @Test
    public void checkSimpleCompressionRatio() {
        compressor = new HuffmanCompression("aba");
        assertEquals(0.125,compressor.getCompressionRatio(), 0.0001);
    }

}