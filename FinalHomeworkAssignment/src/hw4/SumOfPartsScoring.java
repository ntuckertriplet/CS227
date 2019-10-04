
package hw4;

import hw4.AbstractScoring;
import hw4.Hand;

/**
 * An abstract scoring category class that extends the abstract
 * scoring category class, but scoring method is filled in with a default
 * summing of all of the die values.
 * 
 * @author njtucker
 *
 */
public abstract class SumOfPartsScoring extends AbstractScoring {

	/**
	 * Creates a new AScoring category with the given display name
	 * 
	 * @param name A string display name for the object
	 */
	public SumOfPartsScoring(String name) {
		super(name);
	}

	/**
	 * Determines whether the given hand satisfies the defined criteria for this
	 * scoring category. The criteria are determined by the concrete type
	 * implementing the interface. This method does not modify the state of this
	 * category and does not modify the hand.
	 * 
	 * @param hand hand to check
	 * @return true if the given hand satisfies the defined criteria for the
	 *         category, false otherwise
	 */
	@Override
	public abstract boolean isSatisfiedBy(Hand hand);

	/**
	 * Returns the potential score that would result from using the given hand to
	 * fill this category. Always returns zero if the <code>isSatisfiedBy()</code>
	 * method returns false for the given hand. This method does not modify the
	 * state of this category and does not modify the hand.
	 * 
	 * @param hand hand to check
	 * @return potential score for the given hand
	 */
	@Override
	public int getPotentialScore(Hand hand) {
		int sum = 0;
		if (isSatisfiedBy(hand)) {
			for (int d : hand.getAllValues()) {
				sum += d;
			}
		}
		return sum;
	}

}