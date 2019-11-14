package quickSort;

import java.util.Arrays;

public class QuickSort {

    private enum Side {
        LEFT, RIGHT
    }

    private class Point implements Comparable<Point> {

        private Side side;
        private int x;

        Point(int x) {
            this.x = x;
        }

        @Override
        public int compareTo(Point o) {
            return this.x - o.x;
        }

        @Override
        public String toString() {
            return String.valueOf(x);
        }
    }

    private void swap(Point[] array, int j, int i) {

        Point tmp = array[j];
        array[j] = array[i];
        array[i] = tmp;
    }

    private int[] partition_3(Point[] array, int left, int right) {
        Point base = array[left];
        int j = left;
        int k = left;
        for (int i = left + 1; i <= right; i ++) {
            if (array[i].compareTo(base) < 0) {
                j += 1;
                swap(array, j, i);
                k += 1;
                swap(array, k, i);
            } else if (array[i].compareTo(base) == 0) {
                k += 1;
                swap(array, k, i);
            }
            swap(array, left, j);
        }
        return new int[]{j, k};
    }

    private int partition_2(Point[] array, int left, int right) {

        Point base = array[left];
        int j = left;
        for (int i = left + 1; i <= right; i++) {
            if (array[i].compareTo(base) <= 0) {
                j += 1;
                swap(array, j, i);
            }
            swap(array, left, j);
        }
        return j;
    }

    private void quickSort(Point [] array, int left, int right) {

        if (left >= right) {
            return;
        }
        int [] m = partition_3(array, left, right);
        quickSort(array, left, m[0]);
        quickSort(array, m[1] + 1, right);
    }

    private int search(Point [] array, int x) {
        int count = 0;

        return count;
    }

    private void run() {
        Point [] array = new Point[]{new Point(-1), new Point(4), new Point(2), new Point(3), new Point(4), new Point(5), new
                Point(-1)};
        System.err.print(Arrays.toString(array));
        System.err.println();
        quickSort(array, 0, array.length - 1);
        System.err.print(Arrays.toString(array));
    }

    public static void main(String[] args) {
        new QuickSort().run();
    }
}
