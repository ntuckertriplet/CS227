
package hw4;

import java.util.Arrays;

/**
 * Scoring category for a generalized full house. A hand with N dice satisfies
 * this category only in the following cases: If N is even, there are two
 * different values, each occurring exactly N/2 times. If N is odd, there are
 * two different values, one of them occurring N/2 times and the other occurring
 * N/2 + 1 times. For a hand that satisfies this category, the score is a fixed
 * value specified in the constructor; otherwise, the score is zero.
 * 
 * @author njtucker
 *
 */
public class FullHouse extends TwoArgumentsCategory{
	
	/*
	 * The potential points for a Full House to be set in constructor
	 */
	private int points = 0;

	/**
	 * Creates a new FullHouse object with the given display name and point value.
	 * 
	 * @param name   A string display name to use for the object
	 * @param points A point total to return as the score if a hand satisfies this
	 *               category
	 */
	public FullHouse(String name, int points) {
		super(name, points);
		this.points = points;
	}

	/**
	 * @return Returns true if the hand contains a full house, returns false
	 *         otherwise.
	 */
	@Override
	public boolean isSatisfiedBy(Hand hand) {
		int[] satisfiedArray = hand.getAllValues();
		Arrays.sort(satisfiedArray);
		boolean firstTwoSame = true, satisfied = true, isSatisfiedBy = true;
		if (satisfiedArray[0] != satisfiedArray[1]) {
			firstTwoSame = false;
		}
		if (firstTwoSame == true) {
			for (int i = 3; i < satisfiedArray.length - 1; i++) {
				if (satisfiedArray[i] != satisfiedArray[i + 1]) {
					satisfied = false;
				}
			}
			if (firstTwoSame == true && satisfied == true) {
				isSatisfiedBy = false;
			}
		}
		return isSatisfiedBy;
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