package mergeSort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**Вариант из практики по курсу, тоже сортировка на месте, но исходный временный массив пустой  в начале
 * С индексами массивов та же фишка - правый крайний индекс всегда на 1 больше реального правого индекса, поэтому при нечётном количестве
 * средний индекс всегда бурет реально средним, а при чётном - правая половина всегда на 1 меньше левой.
 * При этом несуществующие правые индексы игнорируются проверками инкркменированного индекса на == или на строго < */
public class MergeSort {

    private int[] src;
    private int[] temp;
    private long count;

    private void merge(int left, int mid, int right) {

        int i = left;
        int j = mid;
        for (int k = left; k < right; k++){
            if (j == right || (i < mid && src[i] <= src[j])) {
                temp[k] = src[i];
                i++;
            }else{
                temp[k] = src[j];
                count += mid - i;
                j++;
            }
        }
        System.arraycopy(temp, left, src, left, right - left);
    }

    private void mergeSort(int left, int right) {
        if (right <= left + 1) return;
        int mid = (left + right) >> 1;
        mergeSort(left, mid);
        mergeSort(mid, right);
        merge(left, mid, right);
    }

    private void run() {

        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int size = Integer.parseInt(br.readLine());
            src = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            temp = new int[size];
            mergeSort(0, size);
            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new MergeSort().run();
    }
}
