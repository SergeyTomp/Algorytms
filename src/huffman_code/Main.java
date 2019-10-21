package huffman_code;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    private int[] frequencies;
    private String [] codeTable;
    private int count;

    private class Node{

        int frequency;
        Character character;
        Node leftChild;
        Node rightChild;
        Node parent;

        Node(Node leftChild, Node rightChild) {
            this.frequency = leftChild.frequency + rightChild.frequency;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
//            if (leftChild.root.frequency <= rightChild.root.frequency) {
//                this.leftChild = leftChild.root;
//                this.rightChild = rightChild.root;
//            } else {
//                this.leftChild = rightChild.root;
//                this.rightChild = leftChild.root;
//            }
        }

        Node(int frequency, Character character) {
            this.frequency = frequency;
            this.character = character;
        }
    }
    @SuppressWarnings(value = "nls")
    private Node buildHuffmanTree(int[] frequencies) {

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.frequency));

        for (int i = 0; i < frequencies.length; i++) {
            if (frequencies[i] != 0) {
                priorityQueue.offer(new Node(frequencies[i], (char)i));
                count += 1;
            }
        }

        while (priorityQueue.size() > 1) {

            Node t1 = priorityQueue.poll();
            Node t2 = priorityQueue.poll();
            Node temp = new Node(t1, t2);
            temp.leftChild.parent = temp;
            temp.rightChild.parent = temp;
            priorityQueue.offer(temp);
        }

        return priorityQueue.poll();
    }

    private String encode(String text) {

        frequencies = new int[256];
        text.chars().mapToObj(c -> (char)c).forEach(c -> frequencies[c] = frequencies[c] + 1);
        Node huffmanTree = buildHuffmanTree(frequencies);
        String[] codeTable = makeCodeTable(huffmanTree);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            result.append(codeTable[text.charAt(i)]);
        }
        return result.toString();
    }

    private String [] makeCodeTable(Node huffmanTree) {

        codeTable = new String[256];
        makeCodeTable(huffmanTree, new StringBuilder(), codeTable);
        return codeTable;
    }

    private void makeCodeTable(Node node, StringBuilder code, String [] codeTable ) {

        if (node.character != null) {
            if (node.parent == null) {
                code.append('0');
            }
            codeTable[node.character] = code.toString();
            return;
        }
        makeCodeTable(node.leftChild, code.append('0'), codeTable);
        code.deleteCharAt(code.length() - 1);
        makeCodeTable(node.rightChild, code.append('1'), codeTable);
        code.deleteCharAt(code.length() - 1);
    }

    private void run(String text) {
        String encodedText = encode(text);
        System.out.println(count + " " + encodedText.length());
        for (int i = 0; i < codeTable.length; i++) {
            if (codeTable[i] != null) {
                System.out.println((char)i + ": " + codeTable[i]);
            }
        }
        System.out.println(encodedText);
    }

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        new Main().run(s.next());
        s.close();
    }
}
