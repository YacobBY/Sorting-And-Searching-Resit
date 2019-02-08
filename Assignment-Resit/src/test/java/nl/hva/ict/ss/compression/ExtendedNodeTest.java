package nl.hva.ict.ss.compression;

import org.junit.Test;
import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ExtendedNodeTest extends NodeTest {
    // Put your tests here...

    @Test
    public void OutputStreamIsPreOrder() throws IOException, ClassNotFoundException {
        compressor = new HuffmanCompression("abbcccccc");
        //should output: null null 1 a 2 b 6 c
        Node root = compressor.getCompressionTree();
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(("huffman-tree.bin")))) {
            root.write(output);
        }
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(("huffman-tree.bin")))) {
            assertNull(input.readObject()); //a
            assertNull(input.readObject()); //a
            assertEquals(1, (input.readObject())); //1
            assertEquals("a", (input.readObject()).toString()); //a
            assertEquals(2, (input.readObject())); //b
            assertEquals("b", (input.readObject()).toString()); //2
            assertEquals(6, (input.readObject())); //6
            assertEquals("c", (input.readObject()).toString()); //2
        }
    }

    @Test //Writes a big tree to a binary file as weights and chars, then constructs the tree back from them
    public void canReadWhatWasWrittenBigTree() throws IOException, ClassNotFoundException {
        compressor = new HuffmanCompression("aabbccddeeffgghhh"); // 2-2 2-2 2-2 2-2
        Node root = compressor.getCompressionTree();
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(("huffman-tree.bin")))) {
            root.write(output);
        }
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(("huffman-tree.bin")))) {
            root = Node.read(input);
        }
        assertEquals(17, root.getWeight());
        assertEquals(2, root.getLeft().getLeft().getLeft().getWeight());
        assertEquals(2, root.getLeft().getRight().getRight().getWeight());
        assertEquals(3, root.getRight().getRight().getRight().getWeight());
        assertEquals("isNull", null, root.getRight().getRight().getCharacter());
    }


}
