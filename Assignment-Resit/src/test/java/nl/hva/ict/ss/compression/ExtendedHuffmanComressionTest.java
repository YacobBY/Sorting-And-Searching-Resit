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
}
