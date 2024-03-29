package quickSort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class Main {

    enum Side {

        LEFT(1), TEST(0), RIGHT(-1);
        int alter;

        Side(int alter) {
            this.alter = alter;
        }
    }

    private class Point implements Comparable<Point> {

        private Side side;
        private int x;
        private int number;

        Point(int x, Side side, int number) {
            this.x = x;
            this.side = side;
            this.number = number;
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
        int rand = (new Random().nextInt(right - left) + left);
        swap(array, rand, left);
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

    private void search(Point [] array, Map<Point, Integer> testPointCounts) {

        int count = 0;
        int last = testPointCounts.size();
        for (Point p : array) {

            if (p.side == Side.TEST) {

                testPointCounts.put(p, count);
            }
            if (p.number == last) {
                return;
            }
            count += p.side.alter;
        }
    }

    private void run() {

        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            String[] qty = br.readLine().split(" ");

            final int linesQty = Integer.parseInt(qty[0]);
            final int testPointsQty= Integer.parseInt(qty[1]);
            final StringBuilder out = new StringBuilder();
            final Map<Point, Integer> testPointCounts = new LinkedHashMap<>(testPointsQty);
            final Point[] linePoints = new Point[linesQty * 2 + testPointsQty];

            int linePointIndex = 0;
            for (int i = 0; i < linesQty; i++) {

                String[] lp = br.readLine().split(" ");
                linePoints[linePointIndex] = new Point(Integer.parseInt(lp[0]), Side.LEFT, 0);
                linePointIndex++;
                linePoints[linePointIndex] = new Point(Integer.parseInt(lp[1]), Side.RIGHT, 0);
                linePointIndex++;
            }

            String[] tp = br.readLine().split(" ");

            for (int i = 0; i < testPointsQty; i++) {
                linePoints[linePointIndex] = new Point(Integer.parseInt(tp[i]), Side.TEST, i);
                testPointCounts.put(linePoints[linePointIndex], 0);
                linePointIndex++;
            }

            quickSort(linePoints, 0, linePoints.length - 1);
            search(linePoints, testPointCounts);

            testPointCounts.forEach((k,v) ->out.append(v).append(" "));
            System.out.println(out.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Main().run();
    }
}
