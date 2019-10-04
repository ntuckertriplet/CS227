package lab7;

import java.util.*;

public class testerClass {

	private static int[] convertListToArray(ArrayList<Integer> l) {
		int[] result = new int[l.size()];
		for (int i = 0; i < l.size(); i++) {
			result[i] = l.get(i);
		}
		return result;
	}

	public static int[] getPositiveNumbers(int[] arr) {
		ArrayList<Integer> result = new ArrayList<>();
		for (int i : arr) {
			if (i > 0) {
				result.add(i);
			}
		}
		return convertListToArray(result);
	}

	public static int[] randomExperiment(int max, int iters) {
		Random randomGenerator = new Random();
		int[] experiment = new int[max];
		for (int i = 0; i < iters; i++) {
			experiment[randomGenerator.nextInt(max)]++;
		}
		return experiment;
	}

	public static int[] getRandomArray(int max, int iters) {
		Random randomGenerator = new Random();
		ArrayList<Integer> result = new ArrayList<>();
		for (int i = 0; i < iters; i++) {
			int j = randomGenerator.nextInt(max);
			while (result.contains(j)) {
				j = randomGenerator.nextInt(max);
			}
			result.add(j);
		}
		return convertListToArray(result);
	}
}