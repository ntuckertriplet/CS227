package hw4;

import java.util.Arrays;
import java.util.Random;

import hw4.api.*;

/**
 * This class represents values of a group of dice for a dice game such as
 * Yahtzee in which multiple rolls per turn are allowed. The number of faces on
 * the dice, the number of dice in the Hand, and the maximum number of rolls are
 * configurable via the constructor. At any time some of the dice may be
 * <em>available</em> to be rolled, and the other dice are <em>fixed</em>. Calls
 * to the <code>roll()</code> method roll the available dice only. After the
 * maximum number of rolls, all dice are automatically fixed; before that, the
 * client can select which dice to "keep" (change from available to fixed) and
 * which dice to "free" (change from fixed to available).
 * <p>
 * Note that valid die values range from 1 through the given
 * <code>maxValue</code>.
 * 
 * @author njtucker
 */
public class Hand {
	/**
	 * The maximum number of rolls for the hand
	 */
	private int maxRolls = 0;

	/*
	 * the initial number of rolls to be modified
	 */
	private int rolls = 0;

	/**
	 * The Current number of rolls for the hand
	 */
	private int currentRollCount = 0;

	/*
	 * contains all the values of the current hand
	 */
	private Die[] hand;

	/**
	 * Constructs a new Hand in which each die initially has the value 1.
	 * 
	 * @param numDice  number of dice in this group
	 * @param maxValue largest possible die value, where values range from 1 through
	 *                 <code>maxValue</code>
	 * @param maxRolls maximum number of total rolls
	 */
	public Hand(int numDice, int maxValue, int maxRolls) {
		hand = new Die[numDice];
		this.maxRolls = maxRolls;
		for (int i = 0; i < hand.length; i++) {
			hand[i] = new Die(1, maxValue);
			hand[i].setAvailable(true);
		}
	}

	/**
	 * Constructs a new Hand in which each die initially has the value given by the
	 * <code>initialValues</code> array. If the length of the array is greater than
	 * the number of dice, the extra values are ignored. If the length of the array
	 * is smaller than the number of dice, remaining dice will be initialized to the
	 * value 1.
	 * <p>
	 * This version of the constructor is primarily intended for testing.
	 * 
	 * @param numDice       number of dice in this hand
	 * @param maxValue      largest possible die value, where values range from 1
	 *                      through <code>maxValue</code>
	 * @param maxRolls      maximum number of total rolls
	 * @param initialValues initial values for the dice
	 */
	public Hand(int numDice, int maxValue, int maxRolls, int[] initialValues) {
		hand = new Die[numDice];
		this.maxRolls = maxRolls;
		for (int i = 0; i < hand.length; i++) {
			hand[i] = new Die(initialValues[i], maxValue);
			hand[i].setAvailable(true);
		}
	}

	/**
	 * Returns the number of dice in this hand.
	 * 
	 * @return number of dice in this hand
	 */
	public int getNumDice() {
		return hand.length;
	}

	/**
	 * Returns the maximum die value in this hand. Valid values start at 1.
	 * 
	 * @return maximum die value
	 */
	public int getMaxValue() {
		return hand[0].maxValue();
	}

	/**
	 * Rolls all available dice using the given random number generator. If the
	 * number of rolls has reached the maximum, all dice are marked as fixed.
	 * 
	 * @param rand random number generator to be used for rolling dice
	 */
	public void roll(Random rand) {
		for (int i = 0; i < hand.length; i++) {
			if (hand[i].isAvailable()) {
				hand[i].roll(rand);
			}
		}
		rolls++;
		if (rolls == 2) {
			for (int i = 0; i < hand.length; i++) {
				hand[i].setAvailable(false);
			}
		}
	}

	/**
	 * Selects a single die value to be changed from the available dice to the fixed
	 * dice. If there are multiple available dice with the given value, only one is
	 * changed to be fixed. Has no effect if the given value is not among the values
	 * in the available dice. Has no effect if the number of rolls has reached the
	 * maximum.
	 * 
	 * @param value die value to be changed from available to fixed
	 */
	public void keep(int value) {
		if (rolls != maxRolls) {
			for (int i = 0; i < hand.length; i++) {
				if (hand[i].value() == value) {
					hand[i].setAvailable(false);
					break;
				}
			}
		}

	}

	/**
	 * Selects a die value to be moved from the fixed dice to the available dice
	 * (i.e. so it will be re-rolled in the next call to <code>roll()</code>). If
	 * there are multiple fixed dice with the given value, only one is changed be
	 * available. Has no effect if the given value is not among the values in the
	 * fixed dice. Has no effect if the number of rolls has reached the maximum.
	 * 
	 * @param value die value to be moved
	 */
	public void free(int value) {
		if (rolls != maxRolls) {
			for (int i = 0; i < hand.length; i++) {
				if (hand[i].value() == value) {
					hand[i].setAvailable(true);
				}
			}
		}
	}

	/**
	 * Causes all die values to be changed from available to fixed. Has no effect if
	 * the number of rolls has reached the maximum.
	 */
	public void keepAll() {
		if (currentRollCount < maxRolls) {
			for (Die die : hand) {
				die.setAvailable(false);
			}
		}
	}

	/**
	 * Causes all die values to be changed from fixed to available. Has no effect if
	 * the number of rolls has reached the maximum.
	 */
	public void freeAll() {
		if (currentRollCount < maxRolls) {
			for (Die die : hand) {
				die.setAvailable(true);
			}
		}
	}

	/**
	 * Determines whether there are any dice available to be rolled in this hand.
	 * 
	 * @return true if there are no available dice, false otherwise
	 */
	public boolean isComplete() {
		for (Die d : hand) {
			if (d.isAvailable()) {
				return false;
			}
		}
		return true;
	}

	/*
	 * returns the fixed dice in the game. Fixed meaning dice that are not available
	 * 
	 * @return all the fixed values in this hand
	 */
	public Die[] getFixedDice() {
		int count = 0;
		for (int i = 0; i < hand.length; i++) {
			if (!hand[i].isAvailable()) {
				count++;
			}
		}
		int temp = 0;
		Die[] fix = new Die[count];
		for (int j = 0; j < hand.length; j++) {
			if (!hand[j].isAvailable()) {
				fix[temp] = hand[j];
				temp++;
			}
		}
		DieComparator d = new DieComparator();
		Arrays.sort(fix, d);
		return fix;
	}

	/*
	 * returns the available dice in the game. Available meaning dice that are not
	 * fixed
	 * 
	 * @return all the available values in this hand
	 */
	public Die[] getAvailableDice() {
		int count = 0;
		for (int i = 0; i < hand.length; i++) {
			if (hand[i].isAvailable()) {
				count++;
			}
		}
		int temp = 0;
		Die[] fix = new Die[count];
		for (int j = 0; j < hand.length; j++) {
			if (hand[j].isAvailable()) {
				fix[temp] = hand[j];
				temp++;
			}
		}
		DieComparator d = new DieComparator();
		Arrays.sort(fix, d);
		return fix;

	}

	/**
	 * Returns all die values in this hand, in ascending order.
	 * 
	 * @return all die values in this hand
	 */
	public int[] getAllValues() {
		int[] result = new int[hand.length];
		for (int i = 0; i < hand.length; i++) {
			result[i] = hand[i].value();
		}
		return result;
	}

	/**
	 * Returns an array of all the dice in this hand.
	 * 
	 * @return array of all dice
	 */
	public Die[] getAllDice() {
		Die[] output = new Die[hand.length];
		for (int i = 0; i < hand.length; i++) {
			output[i] = hand[i];
		}
		DieComparator d = new DieComparator();
		Arrays.sort(output, d);
		return output;
	}

}
