package nl.hva.ict.ss.compression;

import jdk.internal.util.xml.impl.Input;

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

       Node root;

       Object temp = input.readObject();
        System.out.println(temp);
        if (temp == null){
            root = new Node(read(input),read(input));
        }
        else {
        root = (Node)temp;
            System.out.println(root.getWeight()+ " "+ root.getCharacter());
        }

        return root;
    }



    public void write(ObjectOutputStream output) throws IOException {
        createOutput(output);
        output.flush();
        output.close();    // close the file.
    }
    public void createOutput(ObjectOutputStream output) throws IOException {
        try {
            if (character == null){
                output.writeObject(null);
                left.createOutput(output);
                right.createOutput(output);
            }
            else output.writeObject(new Node(weight, character));


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
