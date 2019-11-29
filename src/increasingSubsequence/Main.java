package increasingSubsequence;

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

    private void searchLNDS(int[] array, int size) {

        int tailTable[] = new int[size];
        int prevIndex[] = new int[size];
        int max = 0;
        for(int i = 0; i < size; i++) {
            int el = array[i];
            int idx = Arrays.binarySearch(tailTable, 0, max, el);
            if(idx < 0) {
                idx = -(idx + 1);
            }
            if(tailTable[idx] == el) { // duplicate found, let's find the last one
                idx = Arrays.binarySearch(tailTable, 0, max, el + 1);
                if(idx < 0) {
                    idx = -(idx + 1);
                }
            }
            tailTable[idx] = el;
            prevIndex[i] = idx;
            if(idx == max) {
                max++;
            }
        }

        StringBuilder sb = new StringBuilder(max);
        System.out.println(max);

        int prev_i = max - 1;
        for (int i = size - 1; i >= 0; i--) {

            if (prevIndex[i] == prev_i) {
                sb.append(Math.abs(i - size)).append(" ");
                prev_i --;
            }
        }
        System.out.println(sb.toString());
    }

    private void run() {

        FastScanner fs = new FastScanner();

        int size = fs.nextInt();
        int array[] = new int[size];
        int n = size - 1;
        IntStream.rangeClosed(0, size - 1).map(i -> n - i).forEach(i -> array[i] = fs.nextInt());

        searchLNDS(array, size);
    }

    public static void main(String[] args) {

        new Main().run();
    }
}
