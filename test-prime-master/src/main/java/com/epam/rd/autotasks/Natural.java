package com.epam.rd.autotasks;

public class Natural {
	public boolean isPrime(int n) throws IllegalArgumentException {
		if (n < 0) throw new IllegalArgumentException("Negative number not allowed.");

		if (n < 2) return false; // 0 and 1 are not prime

		for (int i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0) {
				return false;
			}
		}

		return true;
	}
}