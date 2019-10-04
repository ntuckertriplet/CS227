package lab1;

import java.util.Scanner;

public class ScannerTest 
{
	private static Scanner scanner;

	public static void main(String[] args)
	{
		scanner = new Scanner(System.in);
		
		String first = scanner.next();
		System.out.println("You typed: " + first);
		
		String second = scanner.next();
		System.out.println("You typed: " + second);
	} 
}
