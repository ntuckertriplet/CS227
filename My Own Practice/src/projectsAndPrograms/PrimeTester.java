package projectsAndPrograms;

import java.util.concurrent.TimeUnit;

public class PrimeTester {

	public static void main(String[] args) throws InterruptedException {
		int i = 5;
		while (i < 10000) {
			int k = i;
			k = (k * k) - 1;
			if (k % 24 == 0) {
				TimeUnit.MILLISECONDS.sleep(100);
				System.out.println("My Prime " + i);
				if (isPrime(i)) {
					System.out.println("Actual Prime " + i);
				} else {
					System.out.println("Actual Not Prime" + i);
				}
			} else {
				TimeUnit.MILLISECONDS.sleep(100);
				System.out.println("My Not Prime " + i);
				if (!isPrime(i)) {
					System.out.println("Actual Not Prime " + i);
				}
			}
			i++;
		}
	}

	public static boolean isPrime(int num) {
		if (num == 2)
			return true;
		if (num < 2 || num % 2 == 0)
			return false;
		for (int i = 3; i * i <= num; i += 2)
			if (num % i == 0)
				return false;
		return true;
	}
}
