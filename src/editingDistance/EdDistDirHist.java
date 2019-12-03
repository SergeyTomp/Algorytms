package editingDistance;

import java.io.*;
import java.util.StringTokenizer;

public class EdDistDirHist {

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

        int[][] prev = new int[str1.length() + 1][str2.length() + 1];
        boolean[][] moveFirst = new boolean[str1.length() + 1][str2.length() + 1];
        boolean[][] moveSecond = new boolean[str1.length() + 1][str2.length() + 1];
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int result;

        // Редактирование - вычисление расстояние и запись хода редакции
        for (int i = 0; i <= str1.length(); i++) {
            for (int j = 0; j <= str2.length(); j++) {

                if (i == 0) {                               // вставка в целевую строку при пустой исходной, двигаемся по целевой строке к её концу
                    result = j;
                    moveFirst [i][j] = true;
                }
                else if (j == 0) {                          // удаление из исходной строки в случае пустой целевой, двигаемся по исходной строке к её концу
                    result = i;
                    moveSecond [i][j] = true;
                }
                else {
                    int res1 = prev[i - 1][j] + 1;
                    int res2 = prev[i][j - 1] + 1;
                    int res3 = prev[i - 1][j - 1] + (str1.charAt(i - 1) == str2.charAt(j - 1) ? 0 : 1);
                    if (res1 <= res2 && res1 <= res3) {     // удаление из исходной строки
                        moveSecond [i][j] = true;
                        result = res1;
                    } else if (res2 <= res3) {              // вставка в целевую строку
                        moveFirst [i][j] = true;
                        result = res2;
                    } else {                                // замена буквы из исходной строки на букву целевой
                        moveFirst [i][j] = true;
                        moveSecond [i][j] = true;
                        result = res3;
                    }
                }
                prev[i][j] = result;
            }
        }

        // Восстановление хода редактирования
        int i = str1.length();
        int j = str2.length();
        StringBuilder line1 = new StringBuilder();
        StringBuilder line2 = new StringBuilder();
        boolean mf;
        boolean ms;
        while (i > 0 || j > 0) {
            mf = moveFirst [i][j];
            ms = moveSecond [i][j];
        if (mf) {                               // была вставка или замена, перед целевой строкой добавляется предыдущая буква, при вставке (см.ниже) перед исходной строкой добавится "-"
                line2.append(str2.charAt(j - 1));
                j--;
            } else {
                line2.append("-");              // было удаление, перед целевой строкой добавляется "-"
            }
        if (ms) {                               // было удаление или замена, перед исходной строкой добавляется предыдущая буква, при удалении (см.выше) перед целевой строкой добавляется "-"
                line1.append(str1.charAt(i - 1));
                i--;
            }else{
                line1.append("-");              // была вставка, перед исходной строкой добавляется "-"
            }
        }

        pw.write(String.valueOf(prev[str1.length()][str2.length()]));
        pw.write("\n");
        pw.write(line1.reverse().toString());
        pw.write("\n");
        pw.write(line2.reverse().toString());
        pw.close();
    }


    private void run() throws FileNotFoundException {

        FastScanner fs = new FastScanner();
        String str1 = fs.next();
        String str2 = fs.next();
        editingDistance(str1, str2);
    }

    public static void main(String[] args) throws FileNotFoundException {

        new EdDistDirHist().run();
    }
}
