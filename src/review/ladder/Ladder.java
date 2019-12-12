package review.ladder;

import java.io.*;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Ladder {

    private class FastScanner {

        private BufferedReader br;
        private StringTokenizer st;

        FastScanner() throws FileNotFoundException {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("src/review/ladder/input.txt"))));
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

    private void findMaxSum(int qty, int[]steps) {

        int result;

        if (qty == 1) { result = steps[0]; }
        else {

            int[]cost = new int[qty + 1];
            cost[1] = steps[0];
            for (int i = 2; i < qty + 1; i++) {
                cost[i] = Math.max(cost[i - 2], cost[i - 1]) + steps[i - 1];
            }
            result = cost[qty];
        }
        System.out.println(result);

        //альтернатива без массива, но и без возможности восстановить путь
//        int[] prev = new int[3];
//        Arrays.stream(steps).forEach(value -> {
//            prev[2] = value + Math.max(prev[0], prev[1]);
//            prev[0] = prev[1];
//            prev[1] = prev[2];});
//        System.out.println(prev[2]);

    }

    private void run() throws FileNotFoundException {

        FastScanner fs = new FastScanner();
        int qty = fs.nextInt();
        int[]steps = new int[qty];
        IntStream.rangeClosed(0, qty - 1).forEach(i -> steps[i] = fs.nextInt());
        findMaxSum(qty, steps);
    }

    public static void main(String[] args) throws FileNotFoundException {
        new Ladder().run();
    }
}
