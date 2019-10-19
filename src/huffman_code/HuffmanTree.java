package huffman_code;

import java.util.*;

public class HuffmanTree {

    private Node root;

    public HuffmanTree() {
    }

    public HuffmanTree(Node root) {
        this.root = root;
    }

    private class Node{

        int frequency;
        Character character;
        Node leftChild;
        Node rightChild;
        Node parent;

        public Node(HuffmanTree leftChild, HuffmanTree rightChild) {
            this.frequency = leftChild.root.frequency + rightChild.root.frequency;
            this.leftChild = leftChild.root;
            this.rightChild = rightChild.root;
//            if (leftChild.root.frequency <= rightChild.root.frequency) {
//                this.leftChild = leftChild.root;
//                this.rightChild = rightChild.root;
//            } else {
//                this.leftChild = rightChild.root;
//                this.rightChild = leftChild.root;
//            }
        }

        public Node(int frequency, Character character) {
            this.frequency = frequency;
            this.character = character;
        }
    }

    private HuffmanTree buildHuffmanTree(int[] frequencies) {

        PriorityQueue<HuffmanTree> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.root.frequency));

        for (int i = 0; i < frequencies.length; i++) {
            if (frequencies[i] != 0) {
                priorityQueue.offer(new HuffmanTree(new Node(frequencies[i], (char)i)));
            }
        }

        while (priorityQueue.size() > 1) {

            HuffmanTree t1 = priorityQueue.poll();
            HuffmanTree t2 = priorityQueue.poll();
            HuffmanTree temp = new HuffmanTree(new Node(t1, t2));
            temp.root.leftChild.parent = temp.root;
            temp.root.rightChild.parent = temp.root;
            priorityQueue.offer(temp);
        }

        return priorityQueue.poll();
    }

    private String encode(String text) {

        int[] frequencies = new int[256];
        text.chars().mapToObj(c -> (char)c).forEach(c -> frequencies[c] = frequencies[c] + 1);
        HuffmanTree huffmanTree = buildHuffmanTree(frequencies);
        String[] codeTable = makeCodeTable(huffmanTree);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            result.append(codeTable[text.charAt(i)]);
        }
        return result.toString();
    }

    private String [] makeCodeTable(HuffmanTree huffmanTree) {

        String [] codeTable = new String[256];

        makeCodeTable(huffmanTree.root, new StringBuilder(), codeTable);

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
        System.err.println(encodedText);
    }

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        new HuffmanTree().run(s.next());
        s.close();
    }
}
