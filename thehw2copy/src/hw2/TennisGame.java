/**
 * 
 */
package hw2;

/**
 * A class to represent a single tennis game
 * @author Aaron
 */
public class TennisGame {
	
	/**
	 * Server's score
	 */
	protected int serverScore = 0;
	
	/**
	 * Receiver's score
	 */
	protected int receiverScore = 0;
	
	/**
	 * The current direction of the ball
	 */
	protected BallDirection ballDirection = BallDirection.NOT_IN_PLAY;
	
	/**
	 * The current number of faults
	 */
	protected int numFaults = 0;
	
	/**
	 * Flags if the ball is currently headed out of bounds or not.
	 */
	protected boolean headedOutOfBounds = false;
	
	// Because the default contructor takes no arguments, and all of the instance variables have their default values defined above
	// We don't need to supply a constructor for the class, java automatically supplies an empty constructor if one is not supplied
	
	/**
	 * Gets the current Ball's direction
	 * @return Returns the current ball's direction, either towards the server, receiver, or not in play.
	 */
	public BallDirection getBallStatus()
	{
		return ballDirection;
	}

	/**
	 * Gets the current score status using the scoring convention of tennis
	 * @return Returns a string containing the score status using tennis conventions
	 */
	public String getCallString()
	{
		// If you keep giving me if-else statements that can be accomplished with single expressions, you'll keep getting single expressions
		return (isOver()) ? getScore() : 
					(serverScore >= 4 && serverScore - receiverScore == 1) ? "advantage in" : 
					(receiverScore >= 4 && receiverScore - serverScore == 1) ? "advantage out" :
					(serverScore == receiverScore && serverScore >= 3) ? "deuce" :
					(serverScore == receiverScore && serverScore < 3) ? 
							((serverScore == 2) ? "30-all" : 
							((serverScore == 1) ? "15-all" : "love-all")) : getSubCallString();
	}
	
	/**
	 * Determines the callString for a beginning-game (receiver or server scores < 3)
	 * @return representing the game's current score using tennis conventions
	 */
	protected String getSubCallString()
	{
		String section1 = (serverScore == 0) ? "love" : (serverScore == 1) ? "15" : (serverScore == 2) ? "30" : "40";
		String section2 = (receiverScore == 0 ? "love" : (receiverScore == 1) ? "15" : (receiverScore == 2) ? "30" : "40");
		return section1 + "-" + section2;
	}
	
	/**
	 * Gets the current number of points for the receiver
	 * @return Returns the point total
	 */
	public int getReceiverPoints()
	{
		return receiverScore;
	}
	
	/**
	 * Returns a string representation of the raw points for each player, in the form "x-y" where x is the number of points for the server and y is the number of points for the receiver.
	 */
	public String getScore()
	{
		return serverScore + "-" + receiverScore;
	}

	/**
	 * Gets the current number of points for the server
	 * @return Returns the point total
	 */
	public int getServerPoints()
	{
		return serverScore;
	}
	
	/**
	 * Simulates a hit of the ball by the player toward whom the ball is currently moving.
	 * @param fault determines if the hit produces a fault
	 * @param headedOutOfBounds determines if the hit sends the ball out of bounds
	 */
	public void hit(boolean fault, boolean headedOutOfBounds)
	{
		if(ballDirection != BallDirection.NOT_IN_PLAY)
		{
			if(fault)
			{
				if(ballDirection == BallDirection.TOWARD_RECEIVER)
					serverScore++;
				else
					receiverScore++;
				ballDirection = BallDirection.NOT_IN_PLAY;
			}
			else
			{
				if(ballDirection == BallDirection.TOWARD_RECEIVER)
					ballDirection = BallDirection.TOWARD_SERVER;
				else
					ballDirection = BallDirection.TOWARD_RECEIVER;
				
				this.headedOutOfBounds = headedOutOfBounds;
			}
		}
	}
	
	/**
	 * Determines if the game is over, which occurs when a player has more than 3 points and has a gap of at least 2 points ahead of the other player
	 * @return Returns a boolean indicating if the game is over
	 */
	public boolean isOver()
	{
		return (Math.abs(receiverScore - serverScore) >= 2 && (receiverScore > 3 || serverScore > 3));
	}
	
	/**
	 * Simulates a miss by the player whom the ball is traveling towards, if the ball is headed out of bounds, then that player gets the point
	 * otherwise, the other player gets the point.  This ends the rally (ie. the ball is no longer in play after this)
	 */
	public void miss()
	{
		if(ballDirection != BallDirection.NOT_IN_PLAY)
		{
			if(headedOutOfBounds)
			{
				if(ballDirection == BallDirection.TOWARD_RECEIVER)
					receiverScore++;
				else
					serverScore++;
				headedOutOfBounds = false;
			}
			else
			{
				if(ballDirection == BallDirection.TOWARD_RECEIVER)
					serverScore++;
				else
					receiverScore++;
			}
			ballDirection = BallDirection.NOT_IN_PLAY;
		}
	}
	
	/**
	 * Determines if the game is over and the receiver has won the game
	 * @return Returns a boolean indicating if the receiver has won the game.
	 */
	public boolean receiverWon()
	{
		return receiverScore > serverScore && isOver();
	}
	
	/**
	 * Simulates the server serving the ball, does nothing if the ball is NOT_IN_PLAY or if the game is over
	 * @param serviceFault determines if the serve is a fault
	 */
	public void serve(boolean serviceFault)
	{
		if(ballDirection == BallDirection.NOT_IN_PLAY && !isOver())
		{
			if(serviceFault)
			{
				if((++numFaults) >= 2)
				{
					numFaults = 0;
					receiverScore++;
				}
			}
			else
			{
				ballDirection = BallDirection.TOWARD_RECEIVER;
				numFaults = 0;
			}
		}
	}
	
	/**
	 * Determines if the game is over and the server has won the game
	 * @return Returns a boolean indicating if the server has won the game.
	 */
	public boolean serverWon()
	{
		return receiverScore < serverScore && isOver();
	}
	
	/**
	 * Directly sets the score of the server and receiver and sets the ball to NOT_IN_PLAY
	 * @param newServerScore The new score of the server
	 * @param newReceiverScore The new score of the receiver
	 */
	public void setScore(int newServerScore, int newReceiverScore)
	{
		serverScore = newServerScore;
		receiverScore = newReceiverScore;
		ballDirection = BallDirection.NOT_IN_PLAY;
	}

}