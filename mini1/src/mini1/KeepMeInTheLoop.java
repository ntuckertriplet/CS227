package mini1;

import java.util.Arrays;

public class KeepMeInTheLoop {

	private KeepMeInTheLoop() {
	}

	public static int findNth(java.lang.String s, char ch, int n) {
		int count = 1;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == ch) {
				if (count == n) {
					return i;
				}
				count++;
			}
		}
		return -1;
	}

	/*
	 * Returns a new string in which all consonants in the given string are doubled.
	 * Characters that are already doubled are not doubled again. Any characters
	 * other than vowels (those in "aeiouAEIOU") are considered to be consonants.
	 * For example, doubleConsonants("rabbit42") returns "rrabbitt4422".
	 */
	public static java.lang.String doubleConsonants(java.lang.String s) {
		return s.replaceAll("(?i)(([^aeiou])\\2+)|([^aeiou])", "$1$3$3");
	}

	/*
	 * Returns the number of iterations required until n is equal to 1, where each
	 * iteration updates n in the following way: if n is even divide it in half else
	 * multiply n by three and add 1
	 * 
	 * For example, given n == 6, the successive values of n would be 3, 10, 5, 16,
	 * 8, 4, 2, 1, so the method returns 8. If n is less than 1, the method returns
	 * -1.
	 */
	public static int findStoppingTime(int n) {
		int time = 0;
		if (n > 0) {
			while (n != 1) {
				if (n % 2 == 0) {
					n = n / 2;
					time++;
				} else {
					n = (n * 3) + 1;
					time++;
				}
			}
		} else {
			time = -1;
		}
		return time;
	}

	/*
	 * Suppose you have some amount of savings in the bank, and it increases each
	 * month by earning interest at a fixed rate. You have a budget for monthly
	 * expenses, but the monthly cost goes up by a fixed amount each month due to
	 * inflation and possibly your increasingly high standards for French cuisine.
	 * Count the number of months you can go bumming around the world before running
	 * out of money.
	 */
	public static int howLong(double balance, double monthlyCost, double interestRate, double increase) {
		int i = 1;
		interestRate = interestRate / 12;
		while ((balance - monthlyCost) >= monthlyCost) {
			balance = balance - monthlyCost;
			monthlyCost = monthlyCost + increase;
			balance += balance * interestRate;
			i++;

		}
		return i;
	}

	/*
	 * Determines whether the given string follows the rule,
	 * "i before e, except after c". For example, IBeforeE("banana") returns true,
	 * IBeforeE("conceive") returns true, IBeforeE("ieicei") returns false,
	 * IBeforeE("caei") returns false, IBeforeE("weigh") returns false
	 */
	public static boolean isIBeforeE(String s) {
		String temp = s.toLowerCase();
		if (temp.contains("e") == false || temp.contains("i") == false) {
			return true;
		}
		for (int i = temp.indexOf("e"); i < temp
				.length(); i += (temp.contains("e") ? temp.indexOf("e") : temp.length())) {
			if ((i > 0 && temp.charAt(i - 1) == 'i') && (i <= 1 || temp.charAt(i - 2) == 'c')) {
				return false;
			} else if ((i < temp.length() - 1) && (i == 0 || temp.charAt(i - 1) != 'c') && temp.charAt(i + 1) == 'i') {
				return false;
			}
			temp = temp.substring(temp.indexOf("e") + 1);
			i = 0;
		}
		return true;
	}

	/*
	 * Returns the second largest number in a string of integers. For example, given
	 * the string "42 137 -7 42 66 55" the method returns 66. Note that the second
	 * largest value may be the same as the largest, e.g., for the string "5 5 5 3"
	 * the method returns 5.
	 */
	public static int findSecondLargest(String nums) {
		String[] firstNumbers = nums.split(" ");
		int[] secondNumbers = new int[firstNumbers.length];
		for (int i = 0; i < firstNumbers.length; i++) {
			secondNumbers[i] = Integer.parseInt(firstNumbers[i]);
		}
		Arrays.sort(secondNumbers);
		return secondNumbers[secondNumbers.length - 2];
	}

	/*
	 * Determines whether the two given strings are permutations (rearrangements) of
	 * each other. Strings may contain arbitrary char values, not just ASCII
	 * characters. The method is case sensitive. For example,
	 * isPermutation("abcabc", "baaccb") returns true, isPermutation("abc", "cbba")
	 * returns false, isPermutation("Abc", "abc") returns false
	 */
	public static boolean isPermutation(java.lang.String s, java.lang.String t) {
		char[] first = s.toCharArray();
		char[] second = t.toCharArray();
		Arrays.sort(first);
		Arrays.sort(second);
		return new String(first).equals(new String(second));
	}

	/*
	 * Determines whether the string target occurs as a substring of string source
	 * where "gaps" are allowed between characters of target. That is, the
	 * characters in target occur in source in their given order but do not have to
	 * be adjacent. (Pictured another way, this method returns true if target could
	 * be obtained from source by removing some of the letters of source.) This
	 * method is case sensitive. For example, containsWithGaps("temperamental",
	 * "meme") returns true, containsWithGaps("temperamental", "alarm") returns
	 * false, containsWithGaps("temperamental", "total") returns false,
	 * containsWithGaps("temperamental", "temperamental") returns true,
	 * containsWithGaps("temperamental", "temperamentally") returns false,
	 * containsWithGaps("temperamental", "") returns true
	 */
	public static boolean containsWithGaps(String source, String target) {
		if (target == "") {
			return true;
		}
		int j = 0;
		for (int i = source.indexOf(target.charAt(0)); i < source.length(); i++) {
			char test = source.charAt(i);
			if (test == target.charAt(j)) {
				j++;
				if (j >= target.length()) {
					return true;
				}
				if (source.indexOf(target.charAt(j), i + 1) < 0) {
					return false;
				} else {
					i = source.indexOf(target.charAt(j), i + 1) - 1;
				}
			} else if (j >= target.length()) {
				return true;
			}
		}
		return false;
	}
}
