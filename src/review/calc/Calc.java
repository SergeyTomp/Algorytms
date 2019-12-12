package review.calc;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Calc {
    private class FastScanner {

        private BufferedReader br;
        private StringTokenizer st;

        FastScanner() throws FileNotFoundException {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("src/review/calc/input.txt"))));
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
    private void calculate(int n) {

        int[]oper = new int[n + 1];
        oper[0] = -1;
        List<List<Integer>> path = new ArrayList<>();
        int div_2, div_3;
        for (int i = 1; i < n + 1; i++) {

            div_2 = i % 2 == 0 ? oper[i / 2] + 1: Integer.MAX_VALUE;
            div_3 = i % 3 == 0 ? oper[i / 3] + 1: Integer.MAX_VALUE;
            oper[i] = Math.min(Math.min(div_3, div_2), oper[i - 1] + 1);
        }

        System.err.println(oper[n]);
    }

    private void run() throws FileNotFoundException {
        FastScanner fs = new FastScanner();
        calculate(fs.nextInt());
    }

    public static void main(String[] args) throws FileNotFoundException {
        new Calc().run();
    }
}
