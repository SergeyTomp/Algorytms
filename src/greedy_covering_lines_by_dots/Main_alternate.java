package greedy_covering_lines_by_dots;
import java.util.*;

class Main_alternate {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int segmentsCount = scanner.nextInt();
        int[][] segments = new int[segmentsCount][2];
        for (int i = 0; i < segmentsCount; i++) {
            segments[i][0] = scanner.nextInt();
            segments[i][1] = scanner.nextInt();
        }
        Arrays.sort(segments, Comparator.comparingInt(a -> a[1]));

        int points = 0;
        int lastPoint = -1;
        StringBuilder pointsResult = new StringBuilder();
        for (int i = 0; i < segmentsCount; i++) {
            if (lastPoint < segments[i][0]) {
                lastPoint = segments[i][1];
                points++;
                pointsResult.append(lastPoint).append(" ");
            }
        }
        System.out.println(points);
        System.out.println(pointsResult);
    }
}