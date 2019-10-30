package heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

public class Heap {
/**
 Первая строка входа содержит число операций 1≤n≤105. Каждая из последующих n строк задают операцию одного из следующих двух типов:
 Insert x, где 0≤x≤109 — целое число;
 ExtractMax.
 Первая операция добавляет число x в очередь с приоритетами, вторая — извлекает максимальное число и выводит его.

 Sample Input:
 6
 Insert 200
 Insert 10
 ExtractMax
 Insert 5
 Insert 500
 ExtractMax

 Sample Output:
 200
 500*/
private PriorityQueue<Integer> pq = new PriorityQueue<>((i0, i1) -> i1 - i0);
    private String[] pair;
    private void parse(String s) {

        pair = s.split(" ");

        if (pair.length == 2) {
            pq.offer(Integer.parseInt(pair[1]));
        }else{
            System.out.println(pq.poll());
        }
    }

    private void run() throws IOException {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            final int q = Integer.parseInt(br.readLine());
            IntStream.range(0, q).forEach(l -> {
                try {
                    parse(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public static void main(String[] args) throws IOException {
        new Heap().run();
    }
}
