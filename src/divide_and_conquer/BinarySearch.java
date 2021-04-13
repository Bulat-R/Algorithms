package divide_and_conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BinarySearch {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] first = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(first[0]);
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(first[i + 1]);
        }

        String[] second = bufferedReader.readLine().split(" ");
        for (int i = 0; i < Integer.parseInt(second[0]); i++) {
            System.out.print(findIndex(arr, Integer.parseInt(second[i + 1])) + " ");
        }


    }

    private static int findIndex(int[] arr, int number) {
        return -1;
    }
}
