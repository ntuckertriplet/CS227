package lab6;



/**
 * An example with some buggy loops.
 */
public class SimpleLoops
{

  /**
   * @param args
   */
  public static void main(String[] args)
  {
    int count = countP("Mississippi");
    System.out.println(count);
	System.out.println("expected 2");
    
    int result2 = findLastP("Mississippi");
    System.out.println(result2);
	System.out.println("expected 9");
   
	int result3 = findFirstP("stop");
    System.out.println(result3);
	System.out.println("expected 3");
	  
    int result4 = findFirstP("xxxyyyzzz");
    System.out.println(result4);
    System.out.println("expected -1");
  }

  /**
   * Returns the number of P's in a string.
   * @param s
   *   the string to examine
   * @return
   *   number of P's in s
   */
  private static int countP(String s)
  {
    int count = 0;
    int i = 0;
    while (i < s.length())
    {

      if (isLetterP(s.charAt(i)))
      {
        count += 1;
      }
      i += 1;
    }
     return count;
  }
  
  /**
   * Returns the index of the last P in a string, or -1 if the
   * string contains no P's.
   * @param s
   *   the string to examine
   * @return
   *   index of the last P, or -1
   */
  private static int findLastP(String s)
  {
    int i = 0;
    while (i < s.length())
    {
      // start at the end of the string
      int index = s.length() - i - 1;
      if (isLetterP(s.charAt(index)))
      {
        return index;
      }
      i++;
    }
    // didn't find a P
    return -1;
  }
  
  /**
   * Returns the index of the first P in a string, or -1 if the
   * string contains no P's.
   * @param s
   *   the string to examine
   * @return
   *   index of the first vowel, or -1
   */
  private static int findFirstP(String s)
  {
    int i = 0;
    while (i < s.length())
    {
      if (isLetterP(s.charAt(i)))
      {
        return i;
      }
      i = i + 1;
    }
    
    // didn't find a P
    return -1;
  }
  
  /**
   * Returns true if the given character is the letter "P" (lowercase
   * or uppercase), false otherwise
   *   the character to check
   * @return
   *   true if the given character is a "P", false otherwise
   */
  private static boolean isLetterP(char ch)
  {
    return ch == 'p' || ch == 'P';
  }
}