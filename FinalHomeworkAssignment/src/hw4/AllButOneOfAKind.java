
package hw4;

import java.util.Arrays;


/**
 * Scoring category for (N-1) of a kind. A hand with N dice satisfies this
 * category only if at least N - 1 of the values are the same. For a hand that
 * satisfies this category, the score the sum of all die values; otherwise the
 * score is zero.
 * 
 * @author njtucker
 */
public class AllButOneOfAKind extends SumOfPartsScoring {

	/**
	 * Creates a new AllButOneOfAKind class with the given name
	 * 
	 * @param name a string name for the class
	 */
	public AllButOneOfAKind(String name) {
		super(name);
	}

	/**
	 * @return Returns true if the hand contains all but one of a kind, returns
	 *         false otherwise.
	 */
	@Override
	public boolean isSatisfiedBy(Hand hand) {
		int[] satisfiedArray = hand.getAllValues();
		Arrays.sort(satisfiedArray);
		boolean satisfied = true;
		//Is everything after the first element the same?
		for (int i = 1; i < satisfiedArray.length - 1; i++) {
			if (satisfiedArray[i] != satisfiedArray[i + 1]) {
				satisfied = false;
			}
		}
		return satisfied;
	}
}