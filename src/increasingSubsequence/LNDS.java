package increasingSubsequence;

import java.util.Arrays;

public class LNDS {

    private void searchLNDS(int[] array, int size) {


        int tailTable[] = new int[size];
        int prevIndex[] = new int[size];
        int max = 0;
        for(int i = 0; i < size; i++) {
            int el = array[i];
            int idx = Arrays.binarySearch(tailTable, 0, max, el);
            if(idx < 0) {
                idx = -(idx + 1);
            }
            if(tailTable[idx] == el) { // duplicate found, let's find the last one
                idx = Arrays.binarySearch(tailTable, 0, max, el + 1);
                if(idx < 0) {
                    idx = -(idx + 1);
                }
            }
            tailTable[idx] = el;
            prevIndex[i] = idx;
            if(idx == max) {
                max++;
            }
        }

        System.err.println(Arrays.toString(array));
        System.err.println(Arrays.toString(prevIndex));
        System.err.println(Arrays.toString(tailTable));
        System.err.println(max);
        StringBuilder sb = new StringBuilder(max);

        int prev_i = max - 1;
        for (int i = size - 1; i >= 0; i--) {

            if (prevIndex[i] == prev_i) {
                sb.append(Math.abs(i - size + 1)).append(" ");
                prev_i --;
            }
        }
        System.err.println(sb.toString());
    }

    private void run(int[] array, int size) {

        searchLNDS(array, size);

    }

    public static void main(String[] args) {

//        int array[] = { 2, 5, 3, 7, 11, 8, 10, 13, 6 };
//        int array[] = { 2, 4, 4, 5, 5, 5, 3, 1, 10 };
//        int array[] = { 7,7,7,7,7,7,2,3,3,3,3,10};
//        int array[] = { 9,9,9,1,1,2,3,3,3,3,10};
        int array[] = { 2,4,4,3,5};
        int n = array.length;
        new LNDS().run(array, n);
    }
}
