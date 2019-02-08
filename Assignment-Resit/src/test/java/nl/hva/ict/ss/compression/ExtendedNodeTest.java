package nl.hva.ict.ss.compression;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ExtendedNodeTest extends NodeTest {
    // Put your tests here...
    @Test
    public void OutputStreamIsPreOrder() throws IOException, ClassNotFoundException {
        compressor = new HuffmanCompression("abbcc");
        Node root = compressor.getCompressionTree();
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(("huffman-tree.bin")))) {
            root.write(output);
        }


        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(("huffman-tree.bin")))) {


            assertNull( input.readObject()); //a
            assertEquals(1,  ((Node)input.readObject()).getWeight() ); //a
            assertEquals(2,  ((Node)input.readObject()).getWeight() ); //a
            assertNull( input.readObject()); //a
            assertEquals(2,  ((Node)input.readObject()).getWeight() );//c



//
//            assertEquals(1, root.getLeft().getLeft().getWeight());
//            assertEquals(2, root.getLeft().getRight().getWeight());
//            assertEquals(3, root.getRight().getWeight());
        }


    }

    @Test
    public void canReadWhatWasWrittenBigTree() throws IOException, ClassNotFoundException {
        compressor = new HuffmanCompression("aabbccddeeffgghh"); // 2-2 2-2 2-2 2-2
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

        System.out.println(root.getLeft().getCharacter() + " " + root.getLeft().getWeight());
        System.out.println(root.getRight().getCharacter() + " " + root.getRight().getWeight());
        assertEquals(16, root.getWeight());
        assertEquals(2, root.getLeft().getLeft().getLeft().getWeight());
        assertEquals(2, root.getLeft().getRight().getRight().getWeight());
        assertEquals(2, root.getRight().getRight().getRight().getWeight());
        assertEquals("isNull", null, root.getRight().getRight().getCharacter());
    }

    @Test //Checks whether it makes sufficient nodes for each leaf and whether middle nodes are empty
    public void evenWriteIsDoubleRead() {
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
