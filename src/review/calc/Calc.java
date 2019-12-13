package review.calc;

import java.io.*;
import java.util.*;

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

        /*
        * массив оптимального количества действий для каждого последующего числа, начиная с 2 до заданного
        * размер массива на единицу больше, чтобы удобнее использовать индекс, как само число
        * индекс наибольшего числа в массиве даст самое дорогое по количеству операций число в пределах заданного
        */
        int[]oper = new int[n + 1];
        oper[0] = -1;

        /*
        * массив предыдущих чисел, предыдущее число само является индексом предыдущего для себя числа.
        * напр.:
        * исходное число используем как индекс в этом массиве,
        * по этому индексу находим предыдущее число, из которого оптимально получено текущее,
        * оно тоже будет индексом, снова по нему находим предшествующее число т.д. до числа 1
        */
        int[]prev = new int[n + 1];
        prev[1] = 1;

        int div_2, div_3;
        for (int i = 1; i < n + 1; i++) {

            div_2 = i % 2 == 0 ? oper[i / 2] + 1: Integer.MAX_VALUE;
            div_3 = i % 3 == 0 ? oper[i / 3] + 1: Integer.MAX_VALUE;
            oper[i] = Math.min(Math.min(div_3, div_2), oper[i - 1] + 1);

            if (oper[i] == div_2) {
                prev[i] = i / 2;
            } else if (oper[i] == div_3) {
                prev[i] = i / 3;
            } else {
                prev[i] = i - 1;
            }
        }

        // выводим количество операций
        System.out.println(oper[n]);

        // вспомогательный вывод массива prev для понимания восстановления решения
//        System.err.println(Arrays.toString(prev));

        //восстанавливаем ход операций
        StringBuilder out = new StringBuilder();

        //по массиву истории
        List<Integer> result = new ArrayList<>();
        result.add(n);

        while (n != 1) {
            result.add(prev[n]);
            n = prev[n];
        }
        result.stream().sorted().forEach(i -> out.append(i).append(" "));

        // без массива истории, но с повторной арифметикой
//        int k = oper[n];
//        int[]hist = new int[k + 1];
//        while (n > 0) {
//            hist[k] = n;
//            k--;
//            if (n % 3 == 0 && oper[n / 3] == k) {
//                n /= 3;
//                continue;
//            } else if (n % 2 == 0 && oper[n / 2] == k) {
//                n /= 2;
//                continue;
//            }
//            n--;
//        }
//        Arrays.stream(hist).forEach(a -> out.append(a).append(" "));

        // вывод
        System.out.println(out.toString());
    }

    private void run() throws FileNotFoundException {
        FastScanner fs = new FastScanner();
        calculate(fs.nextInt());
    }

    public static void main(String[] args) throws FileNotFoundException {
        new Calc().run();
    }
}
