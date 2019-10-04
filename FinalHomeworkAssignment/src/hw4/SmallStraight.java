
package hw4;

/**
 * Scoring category for a "small straight". A hand with N dice satisfies this
 * category only if it includes N - 1 distinct consecutive values. For a hand
 * that satisfies this category, the score is a fixed value specified in the
 * constructor; otherwise, the score is zero.
 * 
 * @author njtucker
 *
 */
public class SmallStraight extends TwoArgumentsCategory {
	
	/*
	 * Variable to be changed in the constructor
	 */
	private int points = 0;


	/**
	 * Creates a new SmallStraight object with the given display name and point
	 * value
	 * 
	 * @param name   A string display name for the object
	 * @param points A value to return if a given hand satisfies this category
	 */
	public SmallStraight(String name, int points) {
		super(name, points);
		this.points = points;
	}

	/**
	 * @return Returns true if the hand contains a small straight, returns false
	 *         otherwise.
	 */
	@Override
	public boolean isSatisfiedBy(Hand hand) {
		//Checks to find the longest run in the hand
		int[] values = hand.getAllValues();
		int length = 0, maxLength = 0;
		for (int i = 0; i < values.length; ++i) {
			if (i == 0) {
				length = 1;
			} else {
				if (values[i] >= values[i - 1]) {
					length++;
				} else {
					if (length > maxLength) {
						maxLength = length;
					}
					length = 1;
				}
			}
		}
		
		//If the hand is N-1, where N is the size of the hand, it's a small straight
		if (length == values.length - 1) {
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see hw4.api.TwoArgumentsCategory#getPotentialScore(hw4.Hand)
	 */
	@Override
	public int getPotentialScore(Hand hand) {
			return points;
	}
}