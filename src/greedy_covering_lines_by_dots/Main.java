package greedy_covering_lines_by_dots;

import java.util.*;

public class Main {

    private Scanner s = new Scanner(System.in);

    enum Side {
        LEFT, RIGHT
    }

    private class Point {

        Side side;
        long x;
        int num;

        Point(Side side, long x, int num) {
            this.side = side;
            this.x = x;
            this.num = num;
        }
    }

    public void run() {

        int n = s.nextInt();
        List<Long> resultList = new ArrayList<>(n);
        ArrayDeque<Integer> stack = new ArrayDeque<>(n);
        boolean[] fixedLines = new boolean[n];
        List<Point> pointList = new ArrayList<>();

        while (n != 0) {

            pointList.add(new Point(Side.LEFT, s.nextLong(), n ));
            pointList.add(new Point(Side.RIGHT, s.nextLong(), n ));
            n--;
        }

        pointList.sort((o1, o2) -> {
            if (o1.x < o2.x) {
                return -1;
            } else if (o1.x > o2.x) {
                return 1;
            } else
                return o1.side.ordinal() - o2.side.ordinal();
        });

        for (Point p : pointList) {

            if (p.side == Side.LEFT) {
                stack.push(p.num);
            } else {
                if (!fixedLines[p.num - 1]) {
                    resultList.add(p.x);
                    while (!stack.isEmpty()) {
                        fixedLines[stack.pop() - 1] = true;
                    }
                }
            }
        }

        System.out.println(resultList.size());
        resultList.forEach(x -> System.out.print(x + " "));
    }

    public static void main(String[] args) {

        new Main().run();
    }
}
