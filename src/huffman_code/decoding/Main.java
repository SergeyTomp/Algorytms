package huffman_code.decoding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    private PriorityQueue<Line> pq;
    private String encodedLine;

    private class Line implements Comparable<Line>{

        String ch;
        String code;

        Line(String ch, String code) {
            this.ch = ch;
            this.code = code;
        }

        @Override
        public String toString() {
            return "Line{" +
                    "ch='" + ch + '\'' +
                    ", code='" + code + '\'' +
                    '}';
        }

        @Override
        public int compareTo(Line line) {
            return line.code.compareTo(this.code);
        }
    }

    private void fillQueue(String[] line) {

        pq.offer(new Line(line[0], line[1]));
    }

    private void run() {

        int qty;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String s1 = br.readLine();
            String[] header = s1.split(" ");
            qty = Integer.parseInt(header[0]);
            pq = new PriorityQueue<>(qty);

            for (int i = 0; i < qty; i++) {
                fillQueue(br.readLine().split(": "));
            }
            encodedLine = br.readLine();
            while (pq.size() > 0) {

                Line poll = pq.poll();
                encodedLine = encodedLine.replaceAll(poll.code, poll.ch);
            }
            System.err.println(encodedLine);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
            new Main().run();
    }
}
