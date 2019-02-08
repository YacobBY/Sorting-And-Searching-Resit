package nl.hva.ict.ss.compression;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class NodeTest {
    HuffmanCompression compressor;

    @Test
    public void canReadWhatWasWritten() throws IOException, ClassNotFoundException {


        compressor = new HuffmanCompression("aaabbbbccccccc"); // 3*codeListBinaryValueTest, 4*b, 7*c
        Node root = compressor.getCompressionTree();


        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(("huffman-tree.bin")))) {


            Node tree = new Node(new Node(1, 'b'), new Node(2, 'a'));


            root.write(output);
        }

        Node tree = null;

        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(("huffman-tree.bin")))) {

            tree = Node.read(input);
            System.out.println("-----------");
            System.out.println(root.getLeft().getLeft().getCharacter());
            System.out.println(tree.getLeft().getLeft().getWeight());
        }



        assertEquals(Character.valueOf('b'), tree.getLeft().getCharacter());
        assertEquals(Character.valueOf('a'), tree.getRight().getCharacter());
    }
}