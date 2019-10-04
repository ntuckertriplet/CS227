
package hw4;

/**
 * Scoring category for a "large straight". A hand with N dice satisfies this
 * category only if there are N distinct consecutive values. For a hand that
 * satisfies this category, the score is a fixed value specified in the
 * constructor; otherwise, the score is zero.
 * 
 * @author njtucker
 *
 */
public class LargeStraight extends TwoArgumentsCategory {

	/*
	 * The point value for a Large Straight to be set in constructor
	 */
	private int points = 0;

	/**
	 * Creates a new LargeStraight object with the given display name and points
	 * 
	 * @param name   A string display name for the given object
	 * @param points A value to be returned if a hand is evaluated to satisfy the
	 *               given category
	 */
	public LargeStraight(String name, int points) {
		super(name, points);
		this.points = points;
	}

	/**
	 * @return Returns true if the hand contains only consecutive dice, returns
	 *         false otherwise.
	 */
	@Override
	public boolean isSatisfiedBy(Hand hand) {
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
		if (length == values.length) {
			return true;

		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hw4.api.TwoArgumentsCategory#getPotentialScore(hw4.Hand)
	 */
	@Override
	public int getPotentialScore(Hand hand) {
		return points;
	}

}
