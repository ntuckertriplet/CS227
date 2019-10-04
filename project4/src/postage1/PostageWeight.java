package postage1;

import java.util.Scanner;

public class PostageWeight 
{

private static Scanner cost;

public static void main(String[] args) 
	{
	cost = new Scanner(System.in);
	
	System.out.print("Enter the weight: ");
	
	double nextDouble = cost.nextDouble();
	
	System.out.print(PostageUtil.computePostage(nextDouble));
	
	}

}
 