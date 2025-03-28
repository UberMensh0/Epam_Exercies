package com.epam.rd.autotasks;

import java.math.BigInteger;

public class Factorial {
    public String factorial(String n) {
        if (n == null || !n.matches("\\d+")) {
            throw new IllegalArgumentException("Invalid input");
        }

        int num = Integer.parseInt(n);
        if (num < 0) {
            throw new IllegalArgumentException("Negative numbers are not allowed");
        }

        // ✅ FIX: Add debug prints to track values
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= num; i++) {
            result = result.multiply(BigInteger.valueOf(i));
            System.out.println("DEBUG: i = " + i + ", result = " + result); // 🔥 Debug Print
        }

        System.out.println("FINAL RESULT for " + n + " = " + result); // 🔥 Debug Print
        return result.toString();
    }

    public static void main(String[] args) {
        Factorial f = new Factorial();
        System.out.println("factorial(3) = " + f.factorial("3")); // Expected: "6"
        System.out.println("factorial(10) = " + f.factorial("10")); // Expected: "3628800"
    }
}
