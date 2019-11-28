package increasingSubsequence;

import java.util.Arrays;

public class LMS {

    private void searchLMS(int array[], int size) {

        int[] sequence = new int[size];

        for (int i = 0; i < size; i++) {
            sequence[i] = 1;
            for (int j = 0; j < i; j++) {
                if (array[i] % array[j] == 0 && sequence[j] + 1 > sequence[i]) {
                    sequence[i] = sequence[j] + 1;
                }
            }
        }

        int len = Arrays.stream(sequence).max().orElse(-1);

        System.out.println(Arrays.toString(sequence));

        int[] out = new int[len];
        StringBuilder sb = new StringBuilder(out.length * 2);

        if (len > 1) {

            int prev_a = -1;
            int prev_s = len;
            int out_i = 0;
            for (int i = array.length - 1; i >= 0; i--) {

                if (prev_a == -1 && sequence[i] == prev_s) {
                    prev_a = array[i];
                    out[out_i] = prev_a;
                    out_i++;
                } else if (sequence[i] == prev_s - 1 && prev_a % array[i] == 0) {
                    prev_s = sequence[i];
                    out[out_i] = array[i];
                    out_i++;
                }
            }
            System.out.println("length = " + (len));
            Arrays.stream(out).sorted().forEach(e -> sb.append(e).append(" "));
            System.out.println("sequence: " + sb.toString());
        } else {
            System.out.println("length = " + 1);
            System.out.println("sequence: empty");
        }

    }

    private void run(int array[], int size) {
        searchLMS(array, size);
    }

    public static void main(String[] args) {
        int array[] = { 2, 5, 3, 7, 11, 8, 10, 13, 6 };
//        int array[] = { 5, 10, 6, 12, 3, 24, 7, 8};
//        int array[] = { 4, 4, 4, 4, 4, 4, 4, 4};
//        int array[] = { 10, 9, 8, 7, 6, 5, 4, 20};
//        int array[] = { 3, 6, 7, 12};
        int n = array.length;
        new LMS().run(array, n);
    }
}
