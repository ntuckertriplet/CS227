
package hw4;

import hw4.api.ScoringCategory;

/**
 * An abstract scoring category that require two arguments used to 
 * implement the scoring category interface
 * and fill in the commonly used methods with bodies
 * 
 * @author njtucker
 */
public abstract class TwoArgumentsCategory implements ScoringCategory {

	/**
	 * The hand that has been assigned to this category
	 */
	private Hand assignedHand = null;

	/**
	 * The named assigned to this object upon construction
	 */
	private String name;

	public TwoArgumentsCategory(String name, int points) {
		this.name = name;
	}

	/**
	 * Determines whether this category is filled.
	 * 
	 * @return true if this category has been permanently filled, false otherwise
	 */
	@Override
	public boolean isFilled() {
		if (assignedHand != null) 
			return true;
		
		return false;
	}

	/**
	 * If the category has been filled, returns the score for the permanently saved
	 * hand that was used to fill it; otherwise returns 0.
	 * 
	 * @return score for the category or zero if not filled
	 */
	@Override
	public int getScore() {
		if (isFilled()) 
			return getPotentialScore(assignedHand);
		
		return 0;
	}

	/**
	 * Returns the Hand that was used to fill this category, or null if not
	 * <em>filled</em>.
	 * 
	 * @return hand filling this category, or null if not filled
	 */
	@Override
	public Hand getHand() {
		return assignedHand;
	}

	/**
	 * Returns the name for this category.
	 * 
	 * @return name for this category
	 */
	@Override
	public String getDisplayName() {
		return name;
	}

	/**
	 * Permanently sets the hand being used to fill this category. The score is set
	 * to the value of <code>getPotentialScore</code> for the given hand. Throws
	 * <code>IllegalStateException</code> if the category has already been filled or
	 * if the given hand is not <em>complete</em> (as defined by the
	 * <code>Hand.isComplete</code> method).
	 * 
	 * @param hand hand to be used to satisfy this category
	 * @throws IllegalStateException if the given hand is not <em>complete</em>
	 *                               (according to the <code>isComplete()</code>
	 *                               method of Hand) or if this category is already
	 *                               filled
	 */
	@Override
	public void fill(Hand hand) {

		if (isFilled() || !hand.isComplete()) {
			throw new IllegalStateException();
		}
		assignedHand = hand;
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
	public abstract int getPotentialScore(Hand hand);

}