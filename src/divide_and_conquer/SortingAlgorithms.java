package divide_and_conquer;

import java.util.*;

public class SortingAlgorithms {

    public static void main(String[] args) {
//        int[] array1 = {5, 2, 10, 26, 15, 0, -9, 1, 60, 17, 6, 21, 24, 53, 50, -50, 64, 4, 27, 5, 73, 10, 15, -80, -2, 63, 90, 76, 64, 24, 17, 28, -18, 45, -87, 29, 76, 24, 54};
//        System.out.println(Arrays.toString(bubbleSort(array1)));
//        int[] array2 = {5, 2, 10, 26, 15, 0, -9, 1, 60, 17, 6, 21, 24, 53, 50, -50, 64, 4, 27, 5, 73, 10, 15, -80, -2, 63, 90, 76, 64, 24, 17, 28, -18, 45, -87, 29, 76, 24, 54};
//        System.out.println(Arrays.toString(mergeSort1(array2)));
//        int[] array3 = {5, 2, 10, 26, 15, 0, -9, 1, 60, 17, 6, 21, 24, 53, 50, -50, 64, 4, 27, 5, 73, 10, 15, -80, -2, 63, 90, 76, 64, 24, 17, 28, -18, 45, -87, 29, 76, 24, 54};
//        System.out.println(Arrays.toString(mergeSort2(array3)));
//
//
//        Random random = new Random();
//        int length = 50_000;
//        int[] arr1 = new int[length];
//        int[] arr2 = new int[length];
//        int[] arr3 = new int[length];
//        for (int i = 0; i < length; i++) {
//            arr1[i] = random.nextInt(length);
//            arr2[i] = random.nextInt(length);
//            arr3[i] = random.nextInt(length);
//        }
//
//        long startBubble = System.currentTimeMillis();
//        bubbleSort(arr1);
//        long stopBubble = System.currentTimeMillis();
//        System.out.println("bubbleSort time: " + (stopBubble - startBubble));
//
//        long startMerge1 = System.currentTimeMillis();
//        mergeSort1(arr2);
//        long stopMerge1 = System.currentTimeMillis();
//        System.out.println("mergeSort1 time: " + (stopMerge1 - startMerge1));
//
//        long startMerge2 = System.currentTimeMillis();
//        mergeSort2(arr3);
//        long stopMerge2 = System.currentTimeMillis();
//        System.out.println("mergeSort2 time: " + (stopMerge2 - startMerge2));

        int[] arr = {2, 3, 9, 2, 9};
        System.out.println(countOfInv(arr));
    }

    // O(N^2)
    private static int[] bubbleSort(int[] array) {
        for (int j = 0; j < array.length; j++) {
            for (int i = 1; i < array.length; i++) {
                if (array[i] < (array[i - 1])) {
                    int tmp = array[i - 1];
                    array[i - 1] = array[i];
                    array[i] = tmp;
                }
            }
        }
        return array;
    }

    // O(N*logN)
    private static int[] mergeSort1(int[] array) {
        if (array.length < 2) {
            return array;
        }
        int[] arr1 = new int[array.length / 2];
        int[] arr2 = new int[array.length - array.length / 2];
        System.arraycopy(array, 0, arr1, 0, arr1.length);
        System.arraycopy(array, arr1.length, arr2, 0, arr2.length);
        return merge(mergeSort1(arr1), mergeSort1(arr2));
    }

    private static int[] mergeSort2(int[] array) {
        Queue<int[]> queue = new LinkedList<>();
        for (int j : array) {
            int[] tmp = {j};
            queue.add(tmp);
        }
        while (queue.size() > 1) {
            int[] tmp = merge(queue.poll(), queue.poll());
            queue.add(tmp);
        }
        return queue.peek();
    }

    private static int[] merge(int[] arr1, int[] arr2) {
        int[] merged = new int[arr1.length + arr2.length];
        int ind1 = 0, ind2 = 0;
        for (int i = 0; i < merged.length; i++) {
            if (ind1 < arr1.length && ind2 < arr2.length) {
                if (arr1[ind1] < arr2[ind2]) {
                    merged[i] = arr1[ind1];
                    ind1++;
                } else {
                    merged[i] = arr2[ind2];
                    ind2++;
                }
            } else {
                if (ind1 < arr1.length) {
                    merged[i] = arr1[ind1];
                    ind1++;
                } else {
                    merged[i] = arr2[ind2];
                    ind2++;
                }
            }
        }
        return merged;
    }

    // На вход подается неупорядоченный массив размером 1е5 натуральных чисел до 1е9
    // Посчитать количество инверсий массива (инверсия - пара элементов Ai > Aj при j > i)
    private static long countOfInv(int[] arr) {
        if (arr.length < 2) {
            return 0;
        }
        int powOfTwo = 1;
        while (arr.length > powOfTwo) {
            powOfTwo = powOfTwo << 1;
        }
        int[] newArr = new int[powOfTwo];
        System.arraycopy(arr, 0, newArr, newArr.length - arr.length, arr.length);

        long count = 0;
        Queue<int[]> queue = new LinkedList<>();
        for (int j : newArr) {
            int[] tmp = {j};
            queue.add(tmp);
        }

        while (queue.size() > 1) {
            int[] arr1 = queue.poll();
            int[] arr2 = queue.poll();
            int[] tmp = new int[arr1.length + arr2.length];
            int ind1 = 0, ind2 = 0;

            for (int i = 0; i < tmp.length; i++) {
                if (ind1 < arr1.length && ind2 < arr2.length) {
                    if (arr1[ind1] <= arr2[ind2]) {
                        tmp[i] = arr1[ind1];
                        ind1++;
                    } else {
                        tmp[i] = arr2[ind2];
                        ind2++;
                        count = count + arr1.length - ind1;
                    }
                } else {
                    if (ind1 < arr1.length) {
                        tmp[i] = arr1[ind1];
                        ind1++;
                    } else {
                        tmp[i] = arr2[ind2];
                        ind2++;
                    }
                }
            }
            System.out.println(Arrays.toString(tmp));
            queue.add(tmp);
        }
        return count;
    }
}
