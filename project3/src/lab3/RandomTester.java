package lab3;

import java.util.Random;
public class RandomTester 
{
	public static void main(String[] args)
	{
		Random rand = new Random();
		System.out.println(rand.nextInt(137));
		System.out.println(rand.nextInt(6));
		System.out.println(rand.nextInt(6));
		System.out.println(rand.nextInt(6));
	}
}
