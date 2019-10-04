
package hw4;

/**
 * Scoring category for all primes. A hand satisfies this category only if all
 * die values are prime numbers. If so, the score is the sum of all parts. The display
 * name for this category is always "All Primes".
 * 
 * @author njtucker
 *
 */
public class AllPrimes extends SumOfPartsScoring {

	/**
	 * Creates a new AllPrimes object with the display name "All Primes"
	 */
	public AllPrimes() {
		super("All Primes");
	}

	/**
	 * @return Returns true if the hand contains only prime numbers
	 */
	@Override
	public boolean isSatisfiedBy(Hand hand) {
		for (int die : hand.getAllValues()) {
			if (isPrime(die)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @param k The number to check
	 * @return Returns true if the number is prime, returns false otherwise.
	 */
	private boolean isPrime(int k) {
		// check if n is a multiple of 2
		if (k % 2 == 0) {
			return false;
		}
		// if not, then just check the odds
		for (int i = 3; i * i <= k; i += 2) {
			if (k % i == 0) {
				return false;
			}
		}
		return true;
	}
	/*
	 * This is a prime number algorithm that I wanted to try out.
	 * It uses a prime number property that, given a prime number n, n^2-1 is 
	 * a multiple of 24. I couldn't figure out how to properly get this working, as there
	 * are numbers that will break this algorithm. The first number that throws a false positive is
	 * 25. I have no idea what the common pattern is amongst false positives, but I 
	 * really want to get this working in the future, as it is very efficient.
	 * 
	 * // return (k == 2 || k == 3 || ((k * k) - 1) % 24 == 0);
	 */
	

}