
package hw4;

/**
 * Scoring category for a "yahtzee". A hand with N dice satisfies this category
 * only if all N values are the same. For a hand that satisfies this category,
 * the score is a fixed value specified in the constructor; otherwise, the score
 * is zero.
 * 
 * @author njtucker
 *
 */
public class AllOfAKind extends TwoArgumentsCategory {
	
	/*
	 * Points to be overwritten in constructor
	 */
	private int points = 0;
	
	/*
	 * Used in potential score to check if it is, in fact, a large straight
	 */
	private boolean satisfied = false;

	/**
	 * Creates a new object with the given display name and score
	 * 
	 * @param name   A string display name to give to the object
	 * @param points the number of points to get upon satisfaction of this category
	 */
	public AllOfAKind(String name, int points) {
		super(name, points);
		this.points = points;
	}

	/**
	 * @return Returns true if every die in the hand contains the same value
	 */
	@Override
	public boolean isSatisfiedBy(Hand hand) {
		int[] satisfiedArray = hand.getAllValues();
		if (hand.getNumDice() == 0) {
			return false;
		} else {
			for (int i : satisfiedArray) {
				if (satisfiedArray[i] == satisfiedArray[i + 1]) {
					satisfied = true;
				}
			}
		}
		return satisfied;
	}

	/*
	 * (non-Javadoc)
	 * @see hw4.api.TwoArgumentsCategory#getPotentialScore(hw4.Hand)
	 */
	@Override
	public int getPotentialScore(Hand hand) {
		if(satisfied) {
			return points;
		}
		return 0;
	}
}