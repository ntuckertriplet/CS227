package lab6;

public class Checkpoint1 
{
	public static void main(String[] args)
	{
		SimpleTests x = new SimpleTests();
		String name1 = "Nathan ed James von der Tucker";
		
		x.checkpoint1(name1);
		
		System.out.println(x.getInitials());
		System.out.println(x.firstVowel());
		
		SimpleTests y = new SimpleTests();
		String name2 = "Phillip Edwards Nathaniel Isaac Smith";
		
		y.checkpoint1(name2);
		
		System.out.println(y.getInitials());
		System.out.println(y.firstVowel());
		
		
	}
}
