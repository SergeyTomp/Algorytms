package review.calc;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private class FastScanner {

        private BufferedReader br;
        private StringTokenizer st;

        FastScanner(){
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
    private void calculate(int n) {
        /*
         * массив оптимального количества действий для каждого последующего числа, начиная с 2 и до заданного
         * размер массива на единицу больше, чтобы удобнее использовать индекс, как само число
         * индекс наибольшего числа в массиве даст самое дорогое по количеству операций число в пределах заданного, если оно интересует
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

        //восстанавливаем ход операций
        List<Integer> result = new ArrayList<>();
        result.add(n);

        while (n != 1) {
            result.add(prev[n]);
            n = prev[n];
        }

        // вывод
        StringBuilder out = new StringBuilder();
        result.stream().sorted().forEach(i -> out.append(i).append(" "));
        System.out.println(out.toString());
    }

    private void run(){
        FastScanner fs = new FastScanner();
        calculate(fs.nextInt());
    }

    public static void main(String[] args){
        new Main().run();
    }
}
