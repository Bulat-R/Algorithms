package greedy_algorithms;

/*
НЕПРЕРЫВНЫЙ РЮКЗАК
Первая строка содержит количество предметов 1 ≤ n ≤ 10^3 и вместимость рюкзака 0 ≤ W ≤2⋅10^6.
Каждая из следующих n строк задаёт стоимость 0 ≤ ci ≤ 2⋅10^6 и объём 0 < wi ≤ 2⋅10^6 предмета (n, W, c, w  — целые числа).
Выведите максимальную стоимость частей предметов (от каждого предмета можно отделить любую часть, стоимость и объём
при этом пропорционально уменьшатся), помещающихся в данный рюкзак, с точностью не менее трёх знаков после запятой.
 */

import java.io.*;
import java.util.*;

public class ContinuousKnapsack {

    // Жадный алгоритм
    // Сложность O(N logN) - сортировка O(N) + заполнение O(logN) ???
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] first = bufferedReader.readLine().split(" +");
        int n = Integer.parseInt(first[0]);
        int capacity = Integer.parseInt(first[1]);
        ArrayList<double[]> things = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] tmp = bufferedReader.readLine().split(" +");
            double[] thing = new double[3];
            thing[0] = Double.parseDouble(tmp[0]);
            thing[1] = Double.parseDouble(tmp[1]);
            thing[2] = thing[0] / thing[1];
            things.add(thing);
        }
        bufferedReader.close();

        things.sort((o1, o2) -> Double.compare(o2[2], o1[2]));

        double money = 0;
        for (double[] thing : things) {
            if (capacity >= thing[1]) {
                capacity -= thing[1];
                money += thing[0];
            } else {
                money += capacity * thing[2];
                break;
            }
        }
        System.out.printf("%.3f", money);
    }
}
