package editingDistance;

import java.io.*;
import java.util.StringTokenizer;

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

    private void editingDistance(String str1, String str2) {

        int[][] prev = new int[str1.length() + 1][str2.length() + 1];

        for (int i = 0; i <= str1.length(); i++) {
            for (int j = 0; j <= str2.length(); j++) {

                if (i == 0) {
                    prev[i][j] = j;
                } else if (j == 0) {
                    prev[i][j] = i;
                } else {
                    int res1 = prev[i - 1][j] + 1;
                    int res2 = prev[i][j - 1] + 1;
                    int res3 = prev[i - 1][j - 1] + (str1.charAt(i - 1) == str2.charAt(j - 1) ? 0 : 1);
                    prev[i][j] = Math.min(Math.min(res1, res2), res3);
                }
            }
        }

        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        pw.write(String.valueOf(prev[str1.length()][str2.length()]));
        pw.close();
    }
    private void run(){

        FastScanner fs = new FastScanner();
        String str1 = fs.next();
        String str2 = fs.next();
        editingDistance(str1, str2);
    }

    public static void main(String[] args) {
        new Main().run();
    }
}
