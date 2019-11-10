package mergeSort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/** Вариант с сотрировкой на месте, т.е. без создания временных массивов на каждом обороте рекурсии.
 * Создаётся только дубль исходного массива на старте, результат в конце оказывается в исходном массиве.
 * Расход памяти  O(n).
 * На каждом обороте  рекурсии, начиная с первого вызова mergeSort, src и dst меняются местами.
 * Фишка в том, что при возврате из рекурсии по наступлению базового случая
 * сливаем в интервал массива две ранее слитых половинки, после чего этот интервал сам становится одной из половинок.
 * Чтобы получить просто код сортировки слиянием, нужно раскомментировать строку вывода результатата сортировки.
 * Для ускоренния работы можно раскомментировать блок обработки базового случая при размере элементарного массива равного 2,
 * но расчёт инверий при этом будет врать*/
public class Main {

    private long count = 0;

    private void mergeSort(int[] src, int[] dst, int start, int end) {

        if (end - start < 2) {
            return;
        }
//        if (end - start == 2) {
//            if (dst[start] > dst[start + 1]) {
//                int tmp = dst[start];
//                dst[start] = dst[start + 1];
//                dst[start + 1] = tmp;
//            }
//            return;
//        }
        int mid = (start + end) / 2;
        mergeSort(dst, src, start, mid);
        mergeSort(dst, src, mid, end);

        int i = start;
        int j = mid;
        int k = start;
        while (k < end) {
            if (j >= end || (i < mid && src[i] <= src[j])) {
                dst[k] = src[i];
                i++;
            } else {
                dst[k] = src[j];
                if (i < mid ) {
                    count += (mid - i) ;
                }
                j++;
            }
            k++;
        }
    }

    private void run() {

        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int size = Integer.parseInt(br.readLine());
            int[] src = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] dst = Arrays.copyOf(src, size);
            mergeSort(dst, src,0, size);
//            Arrays.stream(src).forEach(i -> System.out.print(i + " "));
            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {

        new Main().run();
    }
}
