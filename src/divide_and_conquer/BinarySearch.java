package divide_and_conquer;

/*
В первой строке даны через пробел число N <= 1e5 и массив [1...N] натуральных чисел в порядке возрастания
Во второй строке даны через пробел число M <= 1e5 и M натуральных чисел
Вывести через пробел для каждого числа M номер в массиве или -1
 */

import java.io.*;
import java.util.StringJoiner;

public class BinarySearch {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] first = bufferedReader.readLine().split(" ");
        int[] arr = new int[first.length];
        for (int i = 0; i < first.length; i++) {
            arr[i] = Integer.parseInt(first[i]);
        }

        String[] second = bufferedReader.readLine().split(" ");
        StringJoiner stringJoiner = new StringJoiner(" ");
        for (int i = 1; i < second.length; i++) {
            int ind = findIndex(arr, Integer.parseInt(second[i]));
            stringJoiner.add(String.valueOf(ind));
        }
        System.out.println(stringJoiner);
    }

    private static int findIndex(int[] arr, int number) {
        if (arr.length == 0) {
            return -1;
        }
        int left = 1;
        int right = arr.length - 1;
        int currentInd;
        while (left <= right) {
            currentInd = (right + left) / 2;
            if (arr[currentInd] == number) {
                return currentInd;
            } else if (arr[currentInd] > number) {
                right = currentInd - 1;
            } else {
                left = currentInd + 1;
            }
        }
        return -1;
    }

/*
Дан упордоченный массив целых чисел.
Напишите алгоритм, который за O(logN) проверит, есть ли в массиве число равное своему индексу
 */
    private static boolean isConditionTrue(int[] arr) {
//        if (arr.length == 0) {
//            return false;
//        }
//        int left = 0;
//        int right = arr.length - 1;
//        int index;
//        while (left <= right) {
//            index = (right - left) / 2;
//            if (index == arr[index]) {
//                return true;
//            } else if (index > arr[index]) {
//                right = index - 1;
//            } else {
//                left = index + 1;
//            }
//        }
        return false;
    }
}
