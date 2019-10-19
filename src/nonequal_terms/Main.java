package nonequal_terms;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        List<Integer> termsList = new ArrayList<>();

//        int stepBack;
        int sum = 0;
        int i = 0;
        while (sum < n) {

            i++;
            sum += i;
            termsList.add(i);
        }
        if (sum > n) {

//            stepBack = n - (sum - i);
            i = sum - n;
//            i = i - stepBack;
            termsList.remove(i - 1);
        }
        System.out.println(termsList.size());
        termsList.forEach(t -> System.out.print(t + " "));
    }
}
