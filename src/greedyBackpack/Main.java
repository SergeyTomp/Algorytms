package greedyBackpack;

import java.util.Arrays;
import java.util.Scanner;
//Первая строка содержит количество предметов 1≤n≤103 и вместимость рюкзака 0≤W≤2⋅106.
// Каждая из следующих n строк задаёт стоимость 0≤ci≤2⋅106 и объём 0<wi≤2⋅106 предмета (n, W, ci, wi — целые числа).
// Выведите максимальную стоимость частей предметов (от каждого предмета можно отделить любую часть, стоимость и объём при этом пропорционально уменьшатся),
// помещающихся в данный рюкзак, с точностью не менее трёх знаков после запятой.
public class Main {

    int qty;
    int volume;
    Item[] array;
    double result;

    Scanner s = new Scanner(System.in);

    class Item implements Comparable<Item> {

        int weight;
        int cost;

        public Item(int cost, int weight) {
            this.weight = weight;
            this.cost = cost;
        }

        @Override
        public int compareTo(Item o) {
            long o1 = (long)cost * o.weight;
            long o2 = (long) o.cost * weight;
            return -Long.compare(o1,o2);
        }
    }


    void run() {

        qty = s.nextInt();
        volume = s.nextInt();
        array = new Item[qty];

        while (qty > 0) {

            array[qty - 1] = new Item(s.nextInt(),s.nextInt());
            qty--;
        }

        Arrays.sort(array);

        for (Item item : array) {

            if (volume >= item.weight) {
                volume -= item.weight;
                result += item.cost;
            } else {
                result += item.cost * (double) volume / item.weight;
                break;
            }
        }
//        System.out.println(String.format("%.3f",result));
        System.out.println(result);
    }
    public static void main(String[] args) {
        new Main().run();
    }
}
