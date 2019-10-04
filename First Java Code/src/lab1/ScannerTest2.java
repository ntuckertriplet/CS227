package lab1;

import java.util.Scanner;

public class ScannerTest2 
{
	private static Scanner scanner;

	public static void main(String[] args)
	{
		scanner = new Scanner("10 42");
		
		int first = scanner.nextInt();
		int second = scanner.nextInt();
		System.out.println(first + second);
	}
}
