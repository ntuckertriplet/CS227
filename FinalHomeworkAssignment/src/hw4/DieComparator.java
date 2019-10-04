package hw4;

import java.util.*;

import hw4.api.Die;

/**
 * Comparator for ordering Die objects. Dice are ordered first by value; dice
 * with the same value are ordered by their max value, and dice with the same
 * value and the same max value are ordered by whether they are available, with
 * available dice preceding non-available dice.
 * 
 * @author njtucker
 */
public class DieComparator implements Comparator<Die> {
	@Override
	public int compare(Die left, Die right) {
		int difference = left.value() - right.value();
		int maxDifference = left.maxValue() - right.maxValue();
		if (left.equals(right)) {
			return 0;
		} else if (difference != 0) {
			return difference;
		} else if (maxDifference != 0) {
			return maxDifference;
		} else if (left.isAvailable()) {
			return -1;
		} else {
			return 1;
		}
	}

}