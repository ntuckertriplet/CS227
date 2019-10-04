package exams;

import java.util.*;
import java.io.*;

public class Exam2Review {

	private static Scanner s1;



	//Question 5
	public static String getPassword() {
		int i = 0;
		String returnpass = null;
		while (i == 0) {
			System.out.println("Please enter your password twice");
			s1 = new Scanner(System.in);
			String pass1 = s1.nextLine();
			String pass2 = s1.nextLine();
			if (pass1.equals(pass2)) {
				System.out.println("Accepted");
				System.out.println("Your password is:" + pass1);
				returnpass = pass1;
				i++;
			} else {
				System.out.println("Please retry");
			}
		}
		return returnpass;
	}
	
	
	//Question 2
	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(System.in);
		System.out.print("Enter a file name: ");
		String file = s.next();
		s.close();
		PrintWriter p = new PrintWriter(new File(file.substring(0, file.length() - 5) + ".out"));
		s = new Scanner(new File(file));
		while(s.hasNextLine()) p.println(s.nextLine().split("//")[0]);
		p.close();
		s.close();
	}
	
	
	
	//Question 8a
	public static int ways(int n) {
		if(n == 1 || n == 2) return n;
		if(n == 3) return 4;
		return ways(n - 1) + ways(n - 2) + ways(n - 3);
	}
	
	
	
	//Question 8b
	public static int routes(int r, int c) {
		if(r == 0 || c == 0) return 1;
		return routes(r - 1, c) + routes(r, c - 1);
	}
	
	
	
	//Question 8c
	public static ArrayList<String> findJavaFiles(File f) {
		ArrayList<String> files = new ArrayList<String>();
		if(!f.isDirectory()) {
			if(f.getName().substring(f.getName().length() - 5).equals(".java")) files.add(f.getName());
		} else {
			File[] dir = f.listFiles();
			for(File i : dir) files.addAll(findJavaFiles(i));
		}
		return files;
	}
}
