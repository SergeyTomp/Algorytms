package greatest_common_divider;

import java.util.Scanner;

public class Main {

    // вычисление НОД - вычитаем из большего меньшее, пока не останется 0 и !=0, это и будет НОД
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        long n = s.nextLong();
        long m = s.nextLong();
        while (m != 0) {
            long tmp = n % m;
            n = m;
            m = tmp;
        }
        System.out.println(n);
    }
}
