package huffman_code.coding;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class HuffmanTree {

    class Node implements Comparable<Node>{

        final int sum;
        String code;

        public Node(int sum) {
            this.sum = sum;
        }

        void buildCode(String code) {
            this.code = code;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(sum, o.sum);
        }
    }

    class Parent extends Node {

        Node left;
        Node right;

        public Parent(Node left, Node right) {

            super(left.sum + right.sum);
            this.left = left;
            this.right = right;
        }

        @Override
        void buildCode(String code) {
            super.buildCode(code);
            left.buildCode(this.code + "0");
            right.buildCode(this.code + "1");
        }
    }

    private class Leaf extends Node {

        char symbol;

        public Leaf(char symbol, int count) {
            super(count);
            this.symbol = symbol;
        }
    }

    private void run() {
        Scanner s = new Scanner(System.in);
        String text = s.next();
        s.close();

        PriorityQueue<Node> tree = new PriorityQueue<>();
        Map<Character, Integer> frequencies = new HashMap<>();
        Map<Character, Node> charCodes = new HashMap<>();

        text.chars().mapToObj(c -> (char) c).forEach(c -> frequencies.merge(c, 1, (v1, v2) -> v1 + 1));
        frequencies.forEach((k, v) -> {
            Leaf leaf = new Leaf(k, v);
            tree.offer(leaf);
            charCodes.put(leaf.symbol, leaf);
        });

        while (tree.size() > 1) {
            Node n1 = tree.poll();
            Node n2 = tree.poll();
            Parent parent = new Parent(n1, n2);
            tree.offer(parent);
        }

        Node root = tree.poll();

        if (frequencies.size() == 1) {root.buildCode("0"); }
        else { root.buildCode(""); }

        StringBuilder sb = new StringBuilder();
        text.chars().mapToObj(c -> (char) c).forEach(c -> sb.append(charCodes.get(c).code));
        String encodedText = sb.toString();

        System.out.println(frequencies.size() + " " + encodedText.length());
        charCodes.forEach((k, v) -> System.out.println(k + ": " + v.code));
        System.out.println(encodedText);
    }

    public static void main(String[] args) {
        new HuffmanTree().run();
    }
}
