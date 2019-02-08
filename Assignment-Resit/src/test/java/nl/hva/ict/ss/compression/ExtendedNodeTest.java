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
        Node root = compressor.getCompressionTree();
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(("huffman-tree.bin")))) {
            root.write(output);
        }


        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(("huffman-tree.bin")))) {


//            System.out.println((input.readObject()) ); //a
//            System.out.println((input.readObject()) ); //a
//            System.out.println((input.readObject()) ); //a
//            System.out.println((input.readObject()) ); //a
//            System.out.println((input.readObject()) ); //a
//            System.out.println((input.readObject()) ); //a

            assertNull( input.readObject()); //a
            assertNull( input.readObject()); //a
            assertEquals(1,  ( input.readObject())); //1
            assertEquals(1,  ( input.readObject())); //a
            assertEquals(1,  ( input.readObject())); //2
            assertEquals(1,  ( input.readObject())); //b
            assertEquals(1,  ( input.readObject())); //6
            assertEquals(1,  ( input.readObject())); //c
//            assertEquals(1,  ((Node)input.readObject()).getWeight() ); //a
//            assertEquals(2,  ((Node)input.readObject()).getWeight() ); //a
//            assertNull( input.readObject()); //a
//            assertEquals(2,  ((Node)input.readObject()).getWeight() );//c



//
//            assertEquals(1, root.getLeft().getLeft().getWeight());
//            assertEquals(2, root.getLeft().getRight().getWeight());
//            assertEquals(3, root.getRight().getWeight());
        }


    }

    @Test //Writes a big tree to a binary file as weights and chars, then constructs the tree back from them
    public void canReadWhatWasWrittenBigTree() throws IOException, ClassNotFoundException {
        compressor = new HuffmanCompression("aabbccddeeffgghh"); // 2-2 2-2 2-2 2-2
        Node root = compressor.getCompressionTree();

        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(("huffman-tree.bin")))) {
            root.write(output);
        }

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
