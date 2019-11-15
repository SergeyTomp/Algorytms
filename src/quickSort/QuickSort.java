package quickSort;

import java.util.Arrays;
import java.util.Objects;

import static quickSort.QuickSort.Side.LEFT;
import static quickSort.QuickSort.Side.RIGHT;

public class QuickSort {

    enum Side {
        LEFT, RIGHT
    }

    private class Point implements Comparable<Point> {

        private Side side;
        private int x;

        Point(int x, Side side) {
            this.x = x;
            this.side = side;
        }

        @Override
        public int compareTo(Point o) {
//            return this.x == o.x ? - o.side.compareTo(this.side) : this.x - o.x;
            return this.x - o.x;
        }

//        @Override
//        public String toString() {
//            return side.name() + " " + x;
//        }

        @Override
        public String toString() {
            return String.valueOf(x);
        }

//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            Point point = (Point) o;
//            return x == point.x &&
//                    side == point.side;
//        }
    }

    private void swap(Point[] array, int j, int i) {
        System.err.println();
        Arrays.stream(array).forEach(p -> System.err.print(p.x + " "));
        Point tmp = array[j];
        array[j] = array[i];
        array[i] = tmp;
        System.err.println();
        Arrays.stream(array).forEach(p -> System.err.print(p.x + " "));
    }

    private int[] partition_3(Point[] array, int left, int right) {
        Point base = array[left];
        int j = left;
        int k = left;
        for (int i = left + 1; i <= right; i++) {
            if (array[i].compareTo(base) < 0) {
                j += 1;
                swap(array, j, i);
                k += 1;
                swap(array, k, i);
            } else if (array[i].compareTo(base) == 0) {
                k += 1;
                swap(array, k, i);
            }
        }
        swap(array, left, j);
        j--;
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
        Point [] array = new Point[]{
                new Point(-1, LEFT),
                new Point(4, LEFT),
                new Point(2, LEFT),
                new Point(3, LEFT),
                new Point(4, RIGHT),
                new Point(5, LEFT),
                new Point(-1, LEFT),
                new Point(-1, LEFT)};
//        System.err.print(Arrays.toString(array));
        Arrays.stream(array).forEach(p -> System.err.print(p.x + " "));
        System.err.println();
        quickSort(array, 0, array.length - 1);
//        System.err.print(Arrays.toString(array));
        Arrays.stream(array).forEach(p -> System.err.print(p.x + " "));

    }

    public static void main(String[] args) {
        new QuickSort().run();
    }
}
