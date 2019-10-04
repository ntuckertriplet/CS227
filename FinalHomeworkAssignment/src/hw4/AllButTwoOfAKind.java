
package hw4;

import java.util.Arrays;




/**
 * Scoring category for (N-2) of a kind. A hand with N dice satisfies this
 * category only if at least N - 2 of the values are the same. For a hand that
 * satisfies this category, the score the sum of all die values; otherwise the
 * score is zero.
 * 
 * @author njtucker
 *
 */
public class AllButTwoOfAKind extends SumOfPartsScoring {

	/**
	 * Creates a new AllButTwoOfAKind class and gives it the assigned name
	 * 
	 * @param name A string name to give to the object
	 */
	public AllButTwoOfAKind(String name) {
		super(name);
	}

	/**
	 * @return Returns true if the hand contains all but two of a kind, returns
	 *         false otherwise.
	 */
	@Override
	public boolean isSatisfiedBy(Hand hand) {
		int[] satisfiedArray = hand.getAllValues();
		Arrays.sort(satisfiedArray);
		boolean satisfied = true;
		//Check to see if everything after the first two elements are the same
		for (int i = 2; i < satisfiedArray.length - 1; i++) {
			if (satisfiedArray[i] != satisfiedArray[i + 1]) {
				satisfied = false;
			}
		}
		
		//if so, are the first two the same?
		if (satisfied) {
			if (satisfiedArray[0] == satisfiedArray[1]) {
				satisfied = true;
			} else {
				satisfied = false;
			}
		}
		return satisfied;
	}
}