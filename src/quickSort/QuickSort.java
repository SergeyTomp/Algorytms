package quickSort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

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
            return this.x == o.x ? - o.side.compareTo(this.side) : this.x - o.x;
        }

        @Override
        public String toString() {
            return side.name() + " " + x;
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
        for (int i = left + 1; i <= right; i++) {
            if (array[i].compareTo(base) < 0) {
                j += 1;
                k += 1;
                swap(array, j, i);
                if (k > j) {
                    swap(array, k, i);
                }
            } else if (array[i].compareTo(base) == 0) {
                k += 1;
                swap(array, k, i);
            }
        }
        swap(array, left, j);
        j--;
        return new int[]{j, k};
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

        for (Point p : array) {

            if (p.x > x) {
                return count;
            }

            if (p.side == Side.LEFT) {
                count++;
            } else if (p.x < x && p.side == Side.RIGHT) {
                count--;
            }
        }
        return count;
    }

    private void run() {

        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            String[] qty = br.readLine().split(" ");

            final int linesQty = Integer.parseInt(qty[0]);
            final Point[] linePoints = new Point[linesQty * 2];

            final int testPointsQty= Integer.parseInt(qty[1]);
            final int[] testPoints = new int[testPointsQty];

            int linePointIndex = 0;
            for (int i = 0; i < linesQty; i++) {

                String[] lp = br.readLine().split(" ");
                linePoints[linePointIndex] = new Point(Integer.parseInt(lp[0]), Side.LEFT);
                linePointIndex++;
                linePoints[linePointIndex] = new Point(Integer.parseInt(lp[1]), Side.RIGHT);
                linePointIndex++;
            }

            String[] tp = br.readLine().split(" ");

            for (int i = 0; i < testPoints.length; i++) {
                testPoints[i] = Integer.parseInt(tp[i]);
            }

            quickSort(linePoints, 0, linePoints.length - 1);



            StringBuilder out = new StringBuilder();
            Arrays.stream(testPoints).forEach(p -> out.append(search(linePoints, p)).append(" "));
            System.out.println(out.toString());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new QuickSort().run();
    }
}
