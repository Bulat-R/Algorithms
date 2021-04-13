package greedy_algorithms;

/*
ПОКРЫТЬ ОТРЕЗКИ ТОЧКАМИ
По данным n отрезкам необходимо найти множество точек минимального размера, для которого каждый из отрезков содержит хотя бы одну из точек.
В первой строке дано число 1 ≤ n ≤ 100 отрезков. Каждая из последующих n строк содержит по два числа 0≤ l ≤ r ≤ 10^9, задающих начало и конец отрезка.
Выведите оптимальное число m точек и сами m точек. Если таких множеств точек несколько, выведите любое из них.
 */

import java.io.*;
import java.util.*;

public class Task1 {

    // Жадный алгоритм
    // Сложность O(N logN) - сортировка O(N) + заполнение O(logN) ???
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList <int[]> segments = new ArrayList<>();
        ArrayList<Integer> points = new ArrayList<>();

        int n = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < n; i++) {
            String[] tmp = bufferedReader.readLine().split(" +");
            int[] segment = new int[tmp.length];
            segment[0] = Integer.parseInt(tmp[0]);
            segment[1] = Integer.parseInt(tmp[1]);
            segments.add(segment);
        }
        bufferedReader.close();

        // отсортируем отрезки по конечным точкам
        segments.sort(Comparator.comparingInt(o -> o[1]));

        // берем конечную точку первого отрезка и добавляем в список
        int currentPoint = segments.get(0)[1];
        points.add(currentPoint);

        // если отрезок не содержит текущую точку, добавляем его конечную точку в список
        for (int i = 1; i < segments.size(); i++) {
            if (segments.get(i)[0] > currentPoint || segments.get(i)[1] < currentPoint) {
                currentPoint = segments.get(i)[1];
                points.add(currentPoint);
            }
        }

        // вывод результатов
        System.out.println(points.size());
        StringJoiner stringJoiner = new StringJoiner(" ");
        for (Integer p : points) {
            stringJoiner.add(p.toString());
        }
        System.out.println(stringJoiner);
    }
}
