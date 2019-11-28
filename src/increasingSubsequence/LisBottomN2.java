package increasingSubsequence;

import java.util.Arrays;

public class LisBottomN2 {

    private void searchLIS(int array[], int size) {

        int[] sequence = new int[size];

        for (int i = 0; i < size; i++) {
            sequence[i] = 1;
            for (int j = 0; j < i; j++) {
                if (array[j] < array[i] && sequence[j] + 1 > sequence[i]) {
                    sequence[i] = sequence[j] + 1;
                }
            }
        }

        int len = Arrays.stream(sequence).max().orElse(1);
        int[] out = new int[len];

        int prev_a = -1;
        int prev_s = len;
        int out_i = 0;
        for (int i = array.length - 1; i >= 0; i--) {

            if (prev_a == -1 && sequence[i] == prev_s) {
                prev_a = array[i];
                out[out_i] = prev_a;
                out_i++;
            } else if (sequence[i] == prev_s - 1 && array[i] < prev_a) {
                prev_a = array[i];
                prev_s = sequence[i];
                out[out_i] = prev_a;
                out_i++;
            }
        }

        System.err.println(len);
        System.err.println(Arrays.toString(sequence));

        StringBuilder sb = new StringBuilder(len * 2);
        Arrays.stream(out).filter(e -> e != 0).sorted().forEach(e -> sb.append(e).append(" "));
        System.err.println(sb.toString());
    }

    private void run(int array[], int size) {
        searchLIS(array, size);
    }

    public static void main(String[] args) {
        int array[] = { 2, 5, 3, 7, 11, 8, 10, 13, 6 };
//        int array[] = { 5, 10, 6, 12, 3, 24, 7, 8};
//        int array[] = { 4, 4, 4, 4, 4, 4, 4, 4};
        int n = array.length;
        new LisBottomN2().run(array, n);
    }

    /*  [2, 5, 3, 7, 11, 8, 10, 13, 6]
        [0, 1, 1, 2, 3, 3, 4, 5, 2]
        [2, 3, 6, 8, 10, 13, 0, 0, 0]
        6
        2 3 7 8 10 13*/
}
