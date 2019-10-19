package fibonaci_divide_reamainder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    // Даны целые числа 1≤n≤10^18 и 2≤m≤10^5, необходимо найти остаток от деления n-го числа Фибоначчи на m.
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        long n = s.nextLong();
        long m = s.nextLong();
        List<Long> modList = new ArrayList<>(Arrays.asList(0L, 1L));

        // Для начала, нам нужно найти период Пизано.
        // Для его нахождения вам понадобится знать 2 вещи:
        // первое - период Пизано всегда начинается с 0, 1 ,
        // второе - период Пизано не может быть больше чем 6*m.
        // делаем список остатков:
        for(int i = 2; i < m * 6; i++){
            modList.add((modList.get(i - 1) + modList.get(i - 2)) % m);
            if(modList.get(i) == 1 && modList.get(i-1) == 0){
                break;
            }
        }

        // С помощью этого списка можно найти необходимый остаток.
        // Этим остатком будет такой элемент данного списка, индекс которого есть остаток от деления числа n на период Пизано.
        long period = modList.size() - 2;
        int modIndex = (int)(n % period);
        System.out.print(modList.get(modIndex));
    }
}
