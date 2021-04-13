package greedy_algorithms;

/*
РАЗЛИЧНЫЕ СЛАГАЕМЫЕ
По данному числу 1 ≤ n ≤ 10^9 найдите максимальное число k, для которого n можно представить как сумму k различных натуральных слагаемых.
Выведите в первой строке число k, во второй — k слагаемых.
 */

import java.io.*;
import java.util.*;

public class Task3 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        bufferedReader.close();
        greedyAlgorithm(n);
        bufferedReader.close();
    }

    // Решение задачи через арифметическую прогрессию (не является жадным алгоритмом)
    // Сложность O(N^1/2)
    private static void arithmeticProgression(int n) throws IOException {
        int size = (int) ((Math.sqrt(1 + 8 * (long) n) - 1) / 2) - 1;
        int sum = (size * size  + size) / 2;
        int last = n - sum;
        System.out.println(size + 1);
        for (int i = 1; i <= size; i++) {
            System.out.print(i + " ");;
        }
        System.out.println(last);
    }

    // Жадный алгоритм
    // Сложность O(logN) ???
    private static void greedyAlgorithm(int n) {
        ArrayList<Integer> list = new ArrayList<>();
        int sum = n;
        for (int i = 1; i < n; i++) {
            list.add(i);
            sum -= i;
            if (sum < i + 1) {
                list.set(list.size() - 1, i + sum);
                break;
            }
        }
        System.out.println(list.size());
        for (Integer in : list) {
            System.out.print(in + " ");
        }
    }
}
