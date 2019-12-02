package editingDistance;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class EdDistDir {

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


    private void editingDistance(String str1, String str2) {

        string1 = str1;
        string2 = str2;
        prev = new int[str1.length() + 1][str2.length() + 1];



        for (int i = 0; i <= string1.length(); i++) {
            for (int j = 0; j <= string2.length(); j++) {

                if (i == 0 && j == 0) {prev[i][j] = 0;}
                else if (i == 0) {prev[i][j] = j; }
                else if (j == 0) { prev[i][j] = i; }
                else {
                    int res1 = prev[i - 1][j] + 1;
                    int res2 = prev[i][j - 1] + 1;
                    int res3 = prev[i - 1][j - 1] + (string1.charAt(i - 1) == string2.charAt(j - 1) ? 0 : 1);

                    prev[i][j] = Math.min(Math.min(res1, res2), res3);
                }
            }
        }
        for (int [] ints : prev) {
            System.err.println(Arrays.toString(ints));
        }
        System.err.println(prev[str1.length()][str2.length()]);


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

        new EdDistDir().run();
    }
}
