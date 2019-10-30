package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;

public class BinarySearch {

    private int search(int [] array, int target) {
        int low = 1;
        int high = array.length - 1;

        while (low <= high) {

            int mid = (low + high) / 2;
            int rc = target - array[mid];
            if (rc < 0) {
                high = mid - 1;
            } else if (rc > 0) {
                low = mid + 1;
            }else return mid;
        }
        return - 1;
    }

    private void run() {

        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            int[] arr1 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] arr2 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            IntStream.range(1, arr2.length).forEach(i -> System.out.print(search(arr1, arr2[i]) + " "));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {

        new BinarySearch().run();
    }
}
