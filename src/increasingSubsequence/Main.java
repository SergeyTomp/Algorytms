package increasingSubsequence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    private void searchLMS(int array[], int size) {

        int[] sequence = new int[size];

        for (int i = 0; i < size; i++) {
            sequence[i] = 1;
            for (int j = 0; j < i; j++) {
                if (array[i] % array[j] == 0 && sequence[j] + 1 > sequence[i]) {
                    sequence[i] = sequence[j] + 1;
                }
            }
        }

        int len = Arrays.stream(sequence).max().orElse(-1);
        System.out.println(len);
    }

    private void run() {

        FastScanner fs = new FastScanner();

        int size = fs.nextInt();
        int array[] = new int[size];
        IntStream.rangeClosed(0, size - 1).forEach(i -> array[i] = fs.nextInt());
        searchLMS(array, size);
    }

    public static void main(String[] args) {
        new Main().run();
    }
}

