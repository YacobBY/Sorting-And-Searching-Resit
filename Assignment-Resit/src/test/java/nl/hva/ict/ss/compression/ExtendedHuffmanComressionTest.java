package nl.hva.ict.ss.compression;

import org.junit.Before;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ExtendedHuffmanComressionTest extends HuffmanCompressionTest {
    HuffmanCompression compressor;

    @Before
    public void setup() {
        compressor = new HuffmanCompression(getClass().getResourceAsStream("/edu/princeton/cs/algs4/Huffman.java"));
    }
    @Test
    public void checkNodeValueDistribution() {
        compressor = new HuffmanCompression("aaabbbbccccccc"); // 3*a, 4*b, 7*c

        Node root = compressor.getCompressionTree();
        System.out.println(root.getLeft().getCharacter()+ " "+ root.getLeft().getWeight());
        System.out.println(root.getRight().getCharacter()+ " "+ root.getRight().getWeight());
        assertEquals(14, root.getWeight());

        Node left = root.getLeft();
        Node right = root.getRight();
        assertEquals(3, root.getLeft().getLeft().getWeight());
        assertEquals(4, root.getLeft().getRight().getWeight());
        assertEquals(7, right.getWeight());
    }




}
