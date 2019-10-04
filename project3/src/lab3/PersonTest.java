package lab3;

public class PersonTest 
{
	public static void main(String[] args)
	{
		Person p = new Person("Steve", 20);
		System.out.println(p.getName());
		System.out.println(p.getAge());
		System.out.println(p.getNameLength());
	}
}
