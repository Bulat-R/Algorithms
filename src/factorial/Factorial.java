package factorial;

import java.math.BigInteger;

public class Factorial {

    public static void main(String[] args) {
        System.out.println(factorial(20));
        System.out.println(bigFactorial(2500));
    }

    // факториал через рекурсию
    private static long factorial(int n) {
        if (n == 1) {
            return 1;
        }
        return n * (factorial(n - 1));
    }

    // факториал больших чисел
    private static String bigFactorial(int n) {
        BigInteger big = new BigInteger("1");
        while (n > 0) {
            big = big.multiply(new BigInteger(String.valueOf(n)));
            n = n -1;
        }
        return big.toString();
    }
}
