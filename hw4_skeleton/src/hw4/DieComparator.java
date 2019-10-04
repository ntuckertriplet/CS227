package hw4;

import java.util.Comparator;

import hw4.api.Die;

/**
 * Comparator for ordering Die objects.  Dice are ordered first
 * by value; dice with the same value are ordered by their max value, and dice
 * with the same value and the same max value are ordered by whether they are
 * available, with available dice preceding non-available dice.
 */
public class DieComparator implements Comparator<Die>
{
  @Override
  public int compare(Die left, Die right)
  {
    // TODO
    return 0;
  }

}
