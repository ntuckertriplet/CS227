/**
 * The package containing the second homework assignment
 */
package hw2;

/**
 * Represents a set of tennis matches, a series of games.
 * @author Aaron
 */
public class Set {
	
	// Properties
	
	/**
	 * Specifies the minimum number of matches that a player needs to win in order to win the set
	 */
	protected int minimumGamesToWin;
	
	/**
	 * Boolean flag specifying which player serves first
	 */
	protected boolean player1ServesFirst = false;
	
	/**
	 * Score indicators for the two different players
	 */
	protected int player1Score = 0, player0Score = 0;
	
	/**
	 * The current tennis game object
	 */
	protected TennisGame currentGame;
	
	// Constructors
	
	/**
	 * Creates a new Set object.
	 * @param minimumGamesToWin The minimum number of games needed in order to win the set.
	 * @param player1ServesFirst Specifies whether player1 serves first.
	 */
	public Set(int minimumGamesToWin, boolean player1ServesFirst)
	{
		this.minimumGamesToWin = minimumGamesToWin;
		this.player1ServesFirst = player1ServesFirst;
		currentGame = new TennisGame();
	}

	// Methods
	
	// Protected
	
	/**
	 * Updates the player's score for the game, if it's over... Otherwise, it does nothing
	 */
	protected void metaUpdateScores()
	{
		if(currentGame.isOver())
		{
			if(currentGame.receiverWon())
			{
				if(player1ServesFirst)
					player0Score++;
				else
					player1Score++;
			}
			else
				if(player1ServesFirst)
					player1Score++;
				else
					player0Score++;
		}
	}
	
	// Public
	
	/**
	 * Invokes the setScore method on the current game and updates the winner's score if the action ends the current game.
	 * This function is for testing and does not test to make sure that the scores are realistic
	 * Does nothing if the set is over, or if the current game is over
	 * @param serverScore Score for the server
	 * @param receiverScore Score for the receiver
	 */
	public void fastForward(int serverScore, int receiverScore)
	{
		if(!isSetOver() && !currentGame.isOver())
		{
			currentGame.setScore(serverScore, receiverScore);
			metaUpdateScores();
		}
	}
	
	/**
	 * Returns the current status of the game using the form "Set: x-y Game: ss"
	 * Here x is the current server's set score, and y is the current receiver's set score
	 * ss is the callString from the current game, if useCallString is false, then it returns the default call string a-b
	 * where a is the server's game score, and b is the receiver's game score.
	 * @param useCallString Determines if the return value should have the callstring format
	 * @return Returns the status of the game using the form "Set: x-y Game: ss"
	 */
	public String getCurrentStatus(boolean useCallString)
	{
		String section1 = "Set: " + ((player1ServesFirst) ? player1Score + "-" + player0Score : player0Score + "-" + player1Score);
		String section2 = " Game: " + ((useCallString) ? currentGame.getCallString() : currentGame.getScore());
		return section1 + section2;
	}
	
	/**
	 * Invokes the hit() function on the current game and updates the winner's score if the game ends
	 * Does nothing if the set is over, or the current game is over.
	 * @param fault boolean indicator determining if the current hit resulted in a fault
	 * @param outOfBounds determines if the ball is now headed out of bounds
	 */
	public void hit(boolean fault, boolean outOfBounds)
	{
		if(!isSetOver())
		{
			// This function already won't run if the current game has ended
			// By default if the game is ended, then the ball is NOT_IN_PLAY
			currentGame.hit(fault, outOfBounds);
			metaUpdateScores();
		}
	}

	/**
	 * Determines if the current game has ended
	 * @return Returns true if the game is over, false otherwise
	 */
	public boolean isCurrentGameOver()
	{
		return currentGame.isOver();
	}
	
	/**
	 * Determines if the current set has ended
	 * The current set has ended when a player has won the minimum number of games required to win, and has won at least 2 more games than
	 * the other player
	 * @return Returns true if the set is over, returns false otherwise.
	 */
	public boolean isSetOver()
	{
		return (Math.abs(player1Score - player0Score) >= 2 && (player1Score >= minimumGamesToWin || player0Score >= minimumGamesToWin));
	}
	
	/**
	 * Invokes the miss() function on the current game and updates the player's score if it ends in the game ending.
	 * Does nothing if the set is over, or the current game is over.
	 */
	public void miss()
	{
		if(!isSetOver())
		{
			// Already does not run if the current game is over
			currentGame.miss();
			metaUpdateScores();
		}
	}
	
	/**
	 * Creates a new TennisGame in this set, switching the service to the opposite player
	 * Does nothing if the current set is over, or if the current game is ongoing
	 */
	public void newGame()
	{
		if(currentGame.isOver() && !isSetOver())
		{
			player1ServesFirst = !player1ServesFirst;
			currentGame = new TennisGame();
		}
	}
	
	/**
	 * Returns the number of games won by player 0.
	 * @return Returns the number of games won by player 0.
	 */
	public int player0GamesWon()
	{
		return player0Score;
	}
	
	/**
	 * Returns the number of games won by player 1.
	 * @return Returns the number of games won by player 1.
	 */
	public int player1GamesWon()
	{
		return player1Score;
	}
	
	/**
	 * Invokes the serve() on the current game and updates the winner's count of games won if the action ends the current game.
	 * Does nothing if the current set is over, or the current game is over.
	 * @param serviceFault Determines if there was a fault on the serve
	 */
	public void serve(boolean serviceFault)
	{
		if(!isSetOver() && !currentGame.isOver())
		{
			currentGame.serve(serviceFault);
			if(serviceFault)
				metaUpdateScores();
		}
	}
	
	/**
	 * Determines who is currently serving either in the current game
	 * @return Returns a 1 if player 1 is serving and a zero if player 0 is serving.
	 */
	public int whoIsServing()
	{
		return (player1ServesFirst) ? 1 : 0;
	}
}