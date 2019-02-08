package nl.hva.ict.ss.compression;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Node implements Comparable<Node>, Serializable {
    private Node left;
    private Node right;
    private int weight;
    private Character character;

    public Node(int weight, Character c) {
        this.weight = weight;
        this.character = c;
    }

    public Node(Node left, Node right) {
        this.weight = left.weight + right.weight;
        this.left = left;
        this.right = right;
    }

    public static Node read(ObjectInputStream input) throws IOException, ClassNotFoundException {
        /* Open the file and set to read objects from it. */

//        ArrayList<Node> addingList = new ArrayList<>();
//
//        if (node.getCharacter()!=null){
//            addingList.add(node);
//            System.out.println("node added");
//        }
//        else {
//            for (Node n : getNodeCodes(node.getLeft())){
//                addingList.add(n);
//            }
//            for (Node n : getNodeCodes(node.getRight())){
//                addingList.add(n);
//            }
//        }
        /* Read a tree object, and all the subtrees */
        Node newBase = (Node) input.readObject();

        System.out.println(newBase.getLeft());
        return newBase;

    }

    public void write(ObjectOutputStream output) throws IOException {

        try {

            Node base = new Node(left, right);
            output.writeObject(null); // Write the tree to the stream.
            output.writeObject('a'); // Write the tree to the stream.

            output.flush();
            output.close();    // close the file.


        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public int compareTo(Node o) {

        return weight - o.getWeight();
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public int getWeight() {
        return weight;
    }

    public Character getCharacter() {
        return character;
    }

}
