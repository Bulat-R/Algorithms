package gcd;

import java.math.BigInteger;

public class GreatestCommonDivisor {

    public static void main(String[] args) {

        System.out.println(bigGCD(
                new BigInteger("321865315799999862243594222222222222237424568245162535"),
                new BigInteger("457921234090543097327314432888888888888888807444444446749")));

    }

    // НОД простой алгоритм
    // Сложность O(N)
    public static int gsd (int a, int b) {
        for (int i = Math.min(a, b); i > 1; i--) {
            if (a % i == 0 && b % i == 0) {
                return i;
            }
        }
        return 1;
    }

    // НОД через Алгоритм Евклида и рекурсию
    // Сложность O(logN)
    public static int gsdEuclid (int a, int b) {
        if (a == 0) {
            return b;
        }
        if (b == 0) {
            return a;
        }
        if (a >= b) {
            return gsdEuclid(a % b, b);
        } else {
            return gsdEuclid(a, b % a);
        }
    }

    // НОД через Алгоритм Евклида для очень больших чисел без рекурсии
    // Сложность O(logN)
    public static BigInteger bigGCD(BigInteger a, BigInteger b) {
        while (true) {
            if (a.equals(BigInteger.ZERO)) return b;
            if (b.equals(BigInteger.ZERO)) return a;
            System.out.println(a + " " + b);
            if (a.compareTo(b) >= 0) {
                a = a.mod(b);
            } else {
                b = b.mod(a);
            }
        }
    }
}
