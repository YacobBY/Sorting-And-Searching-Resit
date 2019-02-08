package nl.hva.ict.ss.compression;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class ExtendedNodeTest extends NodeTest {
    // Put your tests here...
    @Test
    public void canReadWhatWasWritten() throws IOException, ClassNotFoundException {


        compressor = new HuffmanCompression("aabbc"); // 3*codeListBinaryValueTest, 4*b, 7*c
        Node root = compressor.getCompressionTree();


        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(("huffman-tree.bin")))) {


            Node tree = new Node(new Node(1, 'b'), new Node(2, 'a'));
            root.write(output);
        }

        Node tree = null;
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(("huffman-tree.bin")))) {

            root = Node.read(input);
            System.out.println("-----------");


        }

        assertEquals(Character.valueOf('b'), root.getLeft().getCharacter());
        assertEquals(Character.valueOf('a'), root.getRight().getCharacter());
    }

    @Test //Checks whether it makes sufficient nodes for each leaf and whether middle nodes are empty
    public void evenLeafsMeansDoubleNodes() {
        compressor = new HuffmanCompression("aabbccddeeffgghh"); // 3*codeListBinaryValueTest, 4*b, 7*c


        Node root = compressor.getCompressionTree();
        System.out.println(root.getLeft().getCharacter() + " " + root.getLeft().getWeight());
        System.out.println(root.getRight().getCharacter() + " " + root.getRight().getWeight());
        assertEquals(16, root.getWeight());
        assertEquals(2, root.getLeft().getLeft().getLeft().getWeight());
        assertEquals(2, root.getLeft().getRight().getRight().getWeight());
        assertEquals(2, root.getRight().getRight().getRight().getWeight());
        assertEquals("isNull", null, root.getRight().getRight().getCharacter());
    }

}
