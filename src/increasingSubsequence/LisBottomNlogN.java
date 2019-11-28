package increasingSubsequence;

import java.util.Arrays;

public class LisBottomNlogN {

    private int ceilIndex(int A[], int left, int right, int key) {
        while (right - left > 1) {
            int mid = left + (right - left) / 2;
            if (A[mid] >= key)
                right = mid;
            else
                left = mid;
        }
        return right;
    }

    private void searchLIS(int array[], int size) {

        // Add boundary case, when array size is one

        int[] tailTable = new int[size];
        int[] sequence = new int[size];
        int len; // всегда указывает на первую свободную ячейку в tailTable

        tailTable[0] = array[0];
        sequence[0] = 0;
        len = 1;
        for (int i = 1; i < size; i++) {
            if (array[i] < tailTable[0]) {  // замещает последний член в самом коротком кандидате на LisBottomNlogN.
                tailTable[0] = array[i];
                sequence[i] = 0;
            } else if (array[i] > tailTable[len - 1]) { // array[i] увеличивает текущую LisBottomNlogN
                sequence[i] = len;
                tailTable[len] = array[i];
                len++;
            }
            else{// array[i] замещает последний член в одном из промежуточных кандидаттов на LisBottomNlogN.
                int index = ceilIndex(tailTable, -1, len - 1, array[i]);
                tailTable[index] = array[i];
                sequence[i] = index;
            }

        }
        System.err.println(Arrays.toString(array));
        System.err.println(Arrays.toString(sequence));
        System.err.println(Arrays.toString(tailTable));
        StringBuilder sb = new StringBuilder(size);

        int prev_a = -1;
        int prev_s = len - 1;
        int tail_i = 0;
        for (int i = array.length - 1; i >= 0; i--) {

            if (prev_a == -1 && sequence[i] == prev_s) {
                prev_a = array[i];
                tailTable[tail_i] = prev_a;
                tail_i++;
            } else if (sequence[i] == prev_s - 1 && array[i] < prev_a) {
                prev_a = array[i];
                prev_s = sequence[i];
                tailTable[tail_i] = prev_a;
                tail_i++;
            }
        }
        Arrays.stream(tailTable).filter(e -> e != 0).sorted().forEach(e -> sb.append(e).append(" "));
        System.err.println(len);
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
        new LisBottomNlogN().run(array, n);
    }
}

