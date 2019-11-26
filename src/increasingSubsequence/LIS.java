package increasingSubsequence;

import java.util.Arrays;

public class LIS {

    private int CeilIndex(int A[], int l, int r, int key) {
        while (r - l > 1) {
            int m = l + (r - l) / 2;
            if (A[m] >= key)
                r = m;
            else
                l = m;
        }
        return r;
    }

    private int LongestIncreasingSubsequenceLength(int A[], int size) {

        // Add boundary case, when array size is one

        int[] tailTable = new int[size];
        int[] sequence = new int[size];
        int len; // always points empty slot

        tailTable[0] = A[0];
        sequence[0] = 0;
        len = 1;
        for (int i = 1; i < size; i++) {
            if (A[i] < tailTable[0]) {
                // new smallest value
                tailTable[0] = A[i];
                sequence[i] = 0;
            } else if (A[i] > tailTable[len - 1]) {
                // A[i] wants to extend largest subsequence
                tailTable[len++] = A[i];
                sequence[i] = len;
            }
            else{// A[i] wants to be current end candidate of an existing
                // subsequence. It will replace ceil value in tailTable
                int index = CeilIndex(tailTable, -1, len - 1, A[i]);
                tailTable[index] = A[i];
                sequence[i] = index;
            }

        }
        System.err.println(Arrays.toString(A));
        System.err.println(Arrays.toString(sequence));
        System.err.println(Arrays.toString(tailTable));
        return len;
    }

    private void run(int A[], int size) {
        System.out.println("Length of Longest Increasing Subsequence is " + LongestIncreasingSubsequenceLength(A, size));
    }

    public static void main(String[] args) {
        int A[] = { 2, 5, 3, 7, 11, 8, 10, 13, 6 };
        int n = A.length;
        new LIS().run(A, n);
    }
}

