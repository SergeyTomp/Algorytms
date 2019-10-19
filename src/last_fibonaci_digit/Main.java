package last_fibonaci_digit;

import java.util.Scanner;

public class Main {

    //найти последнюю цифру большого числа фибоначи

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        int a = 0;
        int b = 1;
        int fib;
        while (true) {
            int n = s.nextInt();
            for(int i = 1; i < n; i++){
                a = a % 10;
                b = b % 10;
                fib = (a + b) % 10;
                a = b;
                b = fib;

            }
            System.out.println(b);
            a=0;
            b=1;
        }
    }
}
