package finalReview;

import java.util.Scanner;

public class Q8Trace {

	private static Scanner scanner;

	public static void main(String[] args) {
		String text = "10 20 23skidoo 30 foo bar";
		int total = 0;
		int i = 0;
		scanner = new Scanner(text);
		while(scanner.hasNext()) {
			try {
				String s = scanner.next();
				i = Integer.parseInt(s);
				total += i;
			} catch (NumberFormatException nfe) {
				total -= i;
			}
		}
		System.out.println(total);
	}

}
