package knapsackWithoutReps;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {

    private class FastScanner {

        private BufferedReader br;
        private StringTokenizer st;

        FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        private String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    private void findMaxWeight(int W, int [] bars) {

        int qty = bars.length;
        int [][] array = new int[W + 1][qty + 1];
        for (int[] ar : array){
            Arrays.fill(ar, 0);
        }
        for (int b = 1; b <= qty; b++) {
            for (int w = 1; w <= W; w++) {
                array[w][b] = array[w][b - 1];
                if (bars[b - 1] <= w) {
                    array[w][b] = Math.max(array[w][b], array[w - bars[b - 1]][b - 1] + bars[b - 1]);
                }
            }
        }
        System.out.println(array[W][qty]);
    }

    private void run() {

        FastScanner fs = new FastScanner();
        int W = fs.nextInt();
        int n = fs.nextInt();
        int [] bars = new int[n];
        IntStream.rangeClosed(0, n - 1).forEach(i -> bars[i] = fs.nextInt());
        findMaxWeight(W, bars);
    }

    public static void main(String[] args) {
        new  Main().run();
    }
}
