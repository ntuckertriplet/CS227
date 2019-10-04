package lab5;

/**
 * Model of a basketball for use in quality control simulations.
 */
public class Basketball
{
  
  private boolean isInflated;
  private double diameter;
  
  /**
   * Constructs an uninflated Basketball with the given diameter.
   */
  public Basketball(double givenDiameter)
  {
    isInflated = false;
    diameter = givenDiameter;
  }
  
  /**
   * Returns the diameter of this Basketball.
   */
  public double getDiameter()
  {
    return diameter;
  }
  
  /**
   * Determines whether this Basketball can be dribbled.
   * True if the basketball is inflated, false otherwise
   */
  public boolean isDribbleable()
  {
    // can be dribbled only if it is inflated
    return isInflated;
  }

   // Inflates this Basketball.
  public void inflate()
  {
    isInflated = true;
  }
  

}