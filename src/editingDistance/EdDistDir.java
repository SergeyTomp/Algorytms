package editingDistance;

import java.io.*;
import java.util.StringTokenizer;

public class EdDistDir {

    private class FastScanner {

        private BufferedReader br;
        private StringTokenizer st;

        FastScanner() throws FileNotFoundException {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("src/editingDistance/input.txt"))));
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

                if (i == 0) { prev[i][j] = j; }
                else if (j == 0) { prev[i][j] = i; }
                else {
                    int res1 = prev[i - 1][j] + 1;
                    int res2 = prev[i][j - 1] + 1;
                    int res3 = prev[i - 1][j - 1] + (str1.charAt(i - 1) == str2.charAt(j - 1) ? 0 : 1);
                    prev[i][j] = Math.min(Math.min(res1, res2), res3);
                }
            }
        }

        /*Восстановление хода редактирования: (идея восстановления строк с конца "на пальцах"):
        * 1. если шаг назад идёт по горизонту, значит здесь была вставка в целевую строку:
        *   -> к хвосту целевой строки спереди добавляем предыдущую букву этой строки, именно она была вставлена, что показывает "-" в исходной строке (см.ниже)
        *   -> к хвосту исходной строки спереди добавляем "-", т.е. это был сдвиг её символов для выравнивания, в т.ч. пустого символа, если строка кончилась
        * 2. если шаг назад идёт по вертикали, значит здесь было удаление символа из исходной строки:
        *   -> к хвосту целевой строки спереди добавляем "-", т.е. это был сдвиг остатка строки для выравнивания, в т.ч. пустого символа, если строка кончилась
        *   -> к хвосту исходной строки спереди добавляем предыдущую букву этой строки, именно она была удалена, что показывает "-" в целевой строке
        * 3. если шаг назад идёт по диагонали наверх, значит была замена, в т.ч. замена совпадающих символов с нулевой ценой:
        *   -> к хвосту целевой строки спереди добавляем предыдущую букву этой строки, именно она была поставлена здесь вместо буквы исходной строке в этой позиции
        *   -> к хвосту исходной строки спереди добавляем предыдущую букву этой строки, именно её меняли при несовпадении с буквой исходной строки в этой позиции
        *
        * В принципе можно сохранить все движения при прямом проходе в двух boolean массивах соответственно для исходной и целевой строк,
        * после чего восстановить из них, тогда можно не писать два раза одни и теже проверки направления движения.
        *    -> если было движение по горизонту пишем true в массив движений исходной строки, т.е. в неё вствлялся "-", а все оставшиеся символы сдвигались вправо
        *    -> если было движение по вертикали пишем true в массив движений целевой строки, т.е. в неё вствлялся "-", а все оствшиеся символы сдвигались вправо*/

        int i = str1.length();
        int j = str2.length();
        StringBuilder line1 = new StringBuilder();
        StringBuilder line2 = new StringBuilder();
        while (i > 0 || j > 0) {

            if (i == 0) {                           // строка i закончилась, дополняем символами строки j
                line1.append("-");
                line2.append(str2.charAt(j - 1));
                j--;
            } else if (j == 0) {                    // строка j закончилась, дополняем символами строки i
                line2.append("-");
                line1.append(str1.charAt(i - 1));
                i--;
            } else {

                int res1 = prev[i - 1][j] + 1;
                int res2 = prev[i][j - 1] + 1;
                int res3 = prev[i - 1][j - 1] + (str1.charAt(i - 1) == str2.charAt(j - 1) ? 0 : 1);

                if (res1 <= res2 && res1 <= res3) {   // было удаление
                    line2.append("-");
                    line1.append(str1.charAt(i - 1));
                    i--;
                } else if (res2 <= res3) {            // была вставка
                    line1.append("-");
                    line2.append(str2.charAt(j -1));
                    j--;
                } else {                              // была замена
                    line1.append(str1.charAt(i - 1));
                    line2.append(str2.charAt(j -1));
                    i--;
                    j--;
                }
            }
        }

        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        pw.write(line1.reverse().toString());
        pw.write("\n");
        pw.write(line2.reverse().toString());
        pw.write("\n");
        pw.write(String.valueOf(prev[str1.length()][str2.length()]));
        pw.close();
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
