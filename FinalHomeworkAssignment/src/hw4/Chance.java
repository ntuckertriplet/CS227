
package hw4;



/**
 * Scoring category that is satisfied by any hand. The score is the sum of all die values.
 * @author njtucker
 *
 */
public class Chance extends SumOfPartsScoring {

	/**
	 * Creates a new chance object with the given display name
	 * @param name A string display name for the object
	 */
	public Chance(String name) {
		super(name);
	}

	/**
	 * @return Always returns true.
	 */
	@Override
	public boolean isSatisfiedBy(Hand hand) {
		return true;
	}

}