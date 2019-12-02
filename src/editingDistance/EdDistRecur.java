package editingDistance;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class EdDistRecur {


    String string1;
    String string2;
    int[][] prev;

    private class FastScanner {

        private BufferedReader br;
        private StringTokenizer st;

        FastScanner() throws FileNotFoundException {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("src/input.txt"))));
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

    private int editingDistance(int n, int m) {

        if (n == 0 && m == 0) { return 0; }
        if (n == 0) { return m; }
        if (m == 0) { return n; }
        if (prev[n][m] != -2) {
            return prev[n][m];
        }

        int res1 = editingDistance(n - 1, m) + 1;
        int res2 = editingDistance(n, m - 1) + 1;
        int res3 = editingDistance(n - 1, m - 1) + (string1.charAt(n - 1) == string2.charAt(m - 1) ? 0 : 1);

        int result = Math.min(Math.min(res1, res2), res3);
        prev[n][m] = result;

        return result;
    }

    private void editingDistance(String str1, String str2) {

        string1 = str1;
        string2 = str2;
        prev = new int[str1.length() + 1][str2.length() + 1];
        for (int [] ints : prev) {
            Arrays.fill(ints, -2);
        }

        int result = editingDistance(str1.length(), str2.length());

        System.err.println(result);
        for (int [] ints : prev) {
            System.err.println(Arrays.toString(ints));
        }

//        int [][] array = new int[str1.length() + 1][str2.length() + 1];
//
//        for (int i = 0; i <= str1.length() + 1; i++) {
//            array[i][0] = Arrays.f
//        }

//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
//        pw.write(str1);
//        pw.write("\n");
//        pw.write(str2);
//        pw.close();


    }


    private void run() throws FileNotFoundException {

        FastScanner fs = new FastScanner();
        String str1 = fs.next();
        String str2 = fs.next();
        editingDistance(str1, str2);

    }

    public static void main(String[] args) throws FileNotFoundException {

        new EdDistRecur().run();
    }
}
