
package hw4;

/**
 * Scoring category that is based on counting occurrences of a particular target
 * value (specified in the constructor). This category is satisfied by any hand.
 * The score is the sum of just the die values that match the target value.
 * 
 * @author njtucker
 */
public class CountOccurrences extends AbstractScoring {

	/**
	 * The value used for scoring
	 */
	private int target = 0;

	/**
	 * Creates a new CountOccurences object with the given display name, and the
	 * targeted value.
	 * 
	 * @param name   A string display name for the object
	 * @param number A target value to count the occurrences of
	 */
	public CountOccurrences(String name, int number) {
		super(name);
		target = number;
	}

	/**
	 * @return Always returns true.
	 */
	@Override
	public boolean isSatisfiedBy(Hand hand) {
		int[] values = hand.getAllValues();
		for (int i : values) {
			if (values[i] == target) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @return Returns the number of occurrences of the specified value in the hand
	 *         multiplied by the target value.
	 */
	@Override
	public int getPotentialScore(Hand hand) {
		int output = 0;
		int[] values = hand.getAllValues();
		for (int i = 0; i < values.length; i++) {
			if (values[i] == target) {
				output++;
			}
		}
		return output * target;
	}

}