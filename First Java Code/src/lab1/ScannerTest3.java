package lab1;

import java.util.Scanner;

public class ScannerTest3

{
  private static Scanner scanner;

public static void main(String[] args)
  {
    // a simple stream of input to try
    String test = "Hi there Steve";
    
    scanner = new Scanner(test);
    
    String first = scanner.next();
    System.out.println(first);
    
    String second = scanner.next();
    System.out.println(second);

    String third = scanner.next();
    System.out.println(third);
    
    // this will be a runtime error
   // scanner.next();
  }

}