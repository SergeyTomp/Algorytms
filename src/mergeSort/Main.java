package mergeSort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Formatter;

public class Main {

    private Integer count = 0;

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
            Arrays.stream(src).forEach(i -> System.out.print(i + " "));
            System.out.println();
            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {

        new Main().run();
    }
}
