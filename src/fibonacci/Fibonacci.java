package fibonacci;

import java.math.BigInteger;

public class Fibonacci {

    public static void main(String[] args) {
        long timeStart = System.currentTimeMillis();
        System.out.println(modFibonacci(1_000_000_000_000_000_000L, 99_999));
        long timeEnd = System.currentTimeMillis();
        System.out.println(timeEnd - timeStart);
    }

    // N-e число Фибоначчи через рекурсию
    // Сложность O(2^n)
    public static long fibonacciRecursive(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    // N-e число Фибоначчи через массив
    // Сложность O(n)
    public static long fibonacciArray (int n) {
        if (n <= 1) {
            return n;
        }
        long[] fibArr = new long[n + 1];
        fibArr[0] = 0;
        fibArr[1] = 1;
        for (int i = 2; i < fibArr.length; i++) {
            fibArr[i] = fibArr[i - 1] + fibArr[i - 2];
        }
        return fibArr[n];
    }

    // N-e число Фибоначчи
    // Сложность O(n)
    public static long fibonacci (int n) {
        if (n <= 1) {
            return n;
        }
        long fib0 = 0, fib1 = 1, fib = 0;
        for (int i = 1; i < n; i++) {
            fib = fib0 + fib1;
            fib0 = fib1;
            fib1 = fib;
        }
        return fib;
    }

    // Очень большие числа Фибоначчи
    // Сложность O(n)
    public static BigInteger bigFibonacci(int n) {
        if (n < 2) {
            return BigInteger.valueOf(n);
        }
        BigInteger fib0 = BigInteger.ZERO;
        BigInteger fib1 = BigInteger.ONE;
        BigInteger fib = BigInteger.ZERO;
        for (int i = 1; i < n; i++) {
            fib = fib0.add(fib1);
            fib0 = fib1;
            fib1 = fib;
        }
        return fib;
    }

    //Последняя цифра N-го числа Фибоначчи
    // N <= 1E+7
    public static int lastDigitOfFibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        int fib0 = 0, fib1 = 1, fib = 0;
        for (int i = 1; i < n; i++) {
            fib = (fib1 + fib0) % 10;
            fib0 = fib1;
            fib1 = fib;
        }
        return fib;
    }

    //Найти остаток от деления N-го числа Фибоначчи на M
    // N <= 1E+18; 1 < M <= 1E+5
    // fib % m --> Период Пизано
    public static int modFibonacci (long n, int m) {
        if (n <= 1) {
            return (int) n;
        }
        int fibMod0 = 0, fibMod1 = 1, fibMod = 0;
        for (long i = 1; i < n; i++) {
            fibMod = (fibMod0 + fibMod1) % m;
            if (fibMod1 == 0 && fibMod == 1) {
                return modFibonacci(n % i, m);
            } else {
                fibMod0 = fibMod1;
                fibMod1 = fibMod;
            }
        }
        return fibMod;
    }
}
