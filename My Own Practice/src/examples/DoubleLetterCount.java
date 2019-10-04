package examples;

public class DoubleLetterCount {
	public static boolean hasDoubleLetter(String s) {
		boolean answer = false;
		for (int i = 0; i < s.length() - 1; i += 1) {
			char c = s.charAt(i);
			char d = s.charAt(i + 1);
			if (c == d) {
				answer = true;
			}
		}
		return answer;
	}
}
