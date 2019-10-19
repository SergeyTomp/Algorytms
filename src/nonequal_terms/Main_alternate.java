package nonequal_terms;

import java.util.Scanner;

public class Main_alternate {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int i = 1;

        StringBuilder sb = new StringBuilder();

        while ( n > 2 * i ) {
            n -= i;
            sb.append(i++ + " ");
        }

        sb.append(n);
        System.out.println(i);
        System.out.println(sb.toString());
    }
}

