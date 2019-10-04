package lab3;

import java.util.Random;
/**
 * A RabbitModel is used to simulate the growth
 * of a population of rabbits. 
 */
public class RabbitModel
{
  public int currentPopulation;
  
  public Random rand = new Random();
  /**
   * Constructs a new RabbitModel.
   */
  public RabbitModel()
  {
    currentPopulation = 0;
  }  
 
  /**
   * Returns the current number of rabbits.
   * @return
   *   current rabbit population
   */
  public int getPopulation()
  {
	  return currentPopulation;
  }
  
  /**
   * Updates the population to simulate the
   * passing of one year.
   */
  public void simulateYear() 
  { 
	  currentPopulation += rand.nextInt(11);
  }
  
  /**
   * Sets or resets the state of the model to the 
   * initial conditions.
   */
  public void reset()
  {
    currentPopulation = 0;
  }
}