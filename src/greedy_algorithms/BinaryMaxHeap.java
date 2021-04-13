package greedy_algorithms;

import java.io.*;
import java.util.*;

public class BinaryMaxHeap<T extends Comparable<T>> {
    private final ArrayList<T> ARRAY = new ArrayList<>(10_000);

    public static void main(String[] args) throws IOException {
        BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<>();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            String[] str = bufferedReader.readLine().split(" ");
            if (str[0].equals("Insert")) {
                heap.insert(Integer.parseInt(str[1]));
            }
            if (str[0].equals("ExtractMax")) {
                Integer extr = heap.extractMax();
                if (extr != null) {
                    System.out.println();
                    System.out.println(extr);
                }
            }
        }
        bufferedReader.close();
    }

    public void insert(T element) {
        ARRAY.add(element);
        swiftUp(element, ARRAY.size() - 1);
    }

    public T getMax() {
        return ARRAY.get(0);
    }

    public T extractMax() {
        if (ARRAY.isEmpty()) {
            return null;
        }
        if (ARRAY.size() == 1) {
            return ARRAY.remove(0);
        } else {
            T element = ARRAY.get(0);
            T last = ARRAY.remove(ARRAY.size() - 1);
            ARRAY.set(0, last);
            swiftDown(ARRAY.get(0), 0);
            return element;
        }
    }

    private void swiftDown(T element, int elementIndex) {
        T left = null;
        T right = null;

        if (elementIndex * 2 + 1 < ARRAY.size()) {
            left = ARRAY.get(elementIndex * 2 + 1);
        }
        if (elementIndex * 2 + 2 < ARRAY.size()) {
            right = ARRAY.get(elementIndex * 2 + 2);
        }

        if (left != null && element.compareTo(left) < 0) {
            if (right == null || left.compareTo(right) > 0) {
                ARRAY.set(elementIndex * 2 + 1, element);
                ARRAY.set(elementIndex, left);
                swiftDown(element, elementIndex * 2 + 1);
            } else if (element.compareTo(right) < 0){
                ARRAY.set(elementIndex * 2 + 2, element);
                ARRAY.set(elementIndex, right);
                swiftDown(element, elementIndex * 2 + 2);
            }
        } else if (right != null && element.compareTo(right) < 0) {
            ARRAY.set(elementIndex * 2 + 2, element);
            ARRAY.set(elementIndex, right);
            swiftDown(element, elementIndex * 2 + 2);
        }
    }

    private void swiftUp(T element, int elementIndex) {
        if (elementIndex > 0) {
            T parent = ARRAY.get((elementIndex - 1) / 2);
            if (element.compareTo(parent) > 0) {
                ARRAY.set((elementIndex - 1) / 2, element);
                ARRAY.set(elementIndex, parent);
                swiftUp(element, (elementIndex - 1) / 2);
            }
        }
    }

    @Override
    public String toString() {
        return ARRAY.toString();
    }
}
