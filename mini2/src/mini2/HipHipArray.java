package mini2;

import java.util.*;

public class HipHipArray {

	private HipHipArray() {
	}

	/*
	 * Create a histogram for a data set given as an array of double values. The
	 * method returns an array arr of length numBins such that arr[i] contains the
	 * count of values from the data set that are in the range for the ith "bin."
	 */
	public static int[] makeHistogram(double[] data, int numBins, double min, double max) {
		double binSize = (max - min) / numBins;
		int histogram[] = new int[numBins];
		for (int i = 0; i < histogram.length; i++)
			histogram[i] = 0;
		for (int i = 0; i < data.length; i++) {
			if (data[i] >= min && data[i] < max) {
				int bin_index = (int) ((data[i] - min) / binSize);
				histogram[bin_index]++;
			}
		}
		return histogram;
	}

	/*
	 * Determines whether the given array is a permutation. In this context, a
	 * permutation is an array that contains each of the values 0 through n - 1
	 * exactly once, where n is the length of the array. For an empty array, the
	 * method returns true.
	 */
	public static boolean isPermutation(int[] arr) {
		int[] newArray = new int[arr.length];
		System.arraycopy(arr, 0, newArray, 0, arr.length);
		Arrays.sort(newArray);
		for (int i = 0; i < newArray.length - 1; i++) {
			if (newArray[i] + 1 != newArray[i + 1]) {
				return false;
			}
		}
		return true;
	}

	/*
	 * Creates a boolean array arr of the given size such that arr[i] is true if and
	 * only if the number i is not divisible by any number in the given array
	 * divisors that is strictly smaller than i. It can be assumed that the given
	 * array is sorted in ascending (nondecreasing) order and all elements are
	 * greater than zero. Remember that 0 is divisible by any nonzero number (leaves
	 * a remainder of zero after division).
	 */
	public static boolean[] makeSieve(int size, int[] divisors) {
		boolean arr[] = new boolean[size];
		for (int i = 1; i < size; i++)
			arr[i] = true;
		for (int k = 0; k < divisors.length; k++) {
			for (int p = 2; p * divisors[k] < size; p++) {
				arr[divisors[k] * p] = false;
			}
		}
		return arr;
	}

	/*
	 * Shifts the elements of the given array by the given amount, shifting right if
	 * amount is positive and left if amount is negative. Vacated cells are filled
	 * with zeros. The amount may be larger than the array size (in which case the
	 * result is all zeros). The given array is modified by this operation.
	 */
	public static void shift(int[] arr, int amount) {
		if (amount > 0) {
			for (int j = 0; j < amount; j++) {
				int shifted = 0;
				for (int i = arr.length - 1; i != 0; i--) {
					arr[i] = arr[i - 1];
				}
				arr[0] = shifted;
			}
			if (amount < arr.length) {
				for (int k = 0; k < amount; k++) {
					arr[k] = 0;
				}
			} else {
				for (int k = 0; k < arr.length - 1; k++) {
					arr[k] = 0;
				}
			}
		} else {
			for (int j = 0; j > amount; j--) {
				int shifted = arr[0];
				for (int i = 1; i < arr.length; i++) {
					arr[i - 1] = arr[i];
				}
				arr[arr.length - 1] = shifted;
			}
			if (Math.abs(amount) < arr.length) {
				for (int k = arr.length - 1; k > Math.abs(amount) + 1; k--) {
					arr[k] = 0;
				}
			} else {
				for (int k = 0; k < arr.length - 1; k++) {
					arr[k] = 0;
				}
			}
		}
	}

	/*
	 * Rotates the elements of the given array by the given amount, towards the
	 * right if amount is positive and left if amount is negative. Values moved off
	 * either end of the array are wrapped around. The amount may be arbitrarily
	 * large. The given array is modified by this operation.
	 */
	public static void rotate(int[] arr, int amount) {
		if (amount > 0) {
			for (int j = 0; j < amount; j++) {
				int rotated = arr[arr.length - 1];
				for (int i = arr.length - 1; i != 0; i--) {
					arr[i] = arr[i - 1];
				}
				arr[0] = rotated;
			}
		} else {
			for (int j = 0; j > amount; j--) {
				int rotated = arr[0];
				for (int i = 1; i < arr.length; i++) {
					arr[i - 1] = arr[i];
				}
				arr[arr.length - 1] = rotated;
			}
		}
	}

	/*
	 * Returns a new array with duplicate elements removed. The relative ordering of
	 * the first occurrence of each element is unchanged. The given array is not
	 * modified.
	 */
	public static java.lang.String[] removeDups(java.lang.String[] arr) {
		String[] singles = new String[arr.length];
		for (int i = 0; i < arr.length; i++) {
			boolean duplicate = false;
			int j = 0;
			while (j < i) {
				if (arr[i].equals(arr[j])) {
					duplicate = true;
				}
				j++;
			}
			if (duplicate == false) {
				singles[i] = arr[i];
			}
		}
		List<String> values = new ArrayList<String>();
		for (String data : singles) {
			if (data != null) {
				values.add(data);
			}
		}
		String[] target = values.toArray(new String[values.size()]);
		return target;
	}

	/*
	 * Given an array, returns a new array consisting of the longest run of
	 * consecutive nondecreasing values in the given array. If there are multiple
	 * runs of the same maximal length, the first such run is returned.
	 */
	public static int[] longestRun(int[] arr) {
		int index = 0, maxIndex = 0, length = 0, maxLength = 0;
		for (int i = 0; i < arr.length; ++i) {
			if (i == 0) {
				index = 0;
				length = 1;
			} else {
				if (arr[i] >= arr[i - 1]) {
					length++;
				} else {
					if (length > maxLength) {
						maxIndex = index;
						maxLength = length;
					}
					length = 1;
					index = i;
				}
			}
		}
		if (length > maxLength) {
			maxIndex = index;
			maxLength = length;
		}
		int[] result = new int[maxLength];
		for (int i = 0; i < maxLength; ++i) {
			result[i] = arr[maxIndex + i];
		}
		return result;
	}

	/*
	 * Given an array of length n, return a new array of length 2n - 1 that is a
	 * "palindrome" created from the given array by appending all elements except
	 * the last one in reverse.
	 */
	public static int[] createPalindrome(int[] arr) {
		if (arr.length != 0) {
			int[] newArray = new int[(arr.length * 2) - 1];
			int bounds = 0;
			for (int i = 0; i < arr.length; i++) {
				newArray[i] = arr[i];
			}
			for (int j = newArray.length - 1; j >= arr.length; j--) {
				newArray[j] = arr[bounds];
				bounds++;
			}
			return newArray;
		} else {
			return arr;
		}

	}
}
