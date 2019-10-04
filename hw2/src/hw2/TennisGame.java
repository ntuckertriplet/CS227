package hw2;

public class TennisGame {
	private int receiverPoints = 0;
	private int serverPoints = 0;
	private int numFaults = 0;
	private BallDirection direction = BallDirection.NOT_IN_PLAY;
	private boolean outOfBoundsTrajectory = false;

	/*
	 * Constructs a new TennisGame in which both players have zero points and the
	 * ball is initially not in play.
	 */
	public TennisGame() {
		serverPoints = 0;
		receiverPoints = 0;
		direction = BallDirection.NOT_IN_PLAY;
	}

	/*
	 * Directly sets the scores to the given amounts and sets the ball's status to
	 * NOT_IN_PLAY. Note that this operation may cause the scores to go down, or
	 * result in unrealistic values. This method is intended for testing only.
	 */
	public void setScore(int newServerScore, int newReceiverScore) {
		serverPoints = newServerScore;
		receiverPoints = newReceiverScore;
		direction = BallDirection.NOT_IN_PLAY;
	}

	// Returns the current number of points for the receiver.
	public int getReceiverPoints() {
		return receiverPoints;
	}

	// Returns the current number of points for the server.
	public int getServerPoints() {
		return serverPoints;
	}

	/*
	 * Returns the current status of the ball (traveling toward the receiver,
	 * traveling toward the server, or not in play).
	 */
	public BallDirection getBallStatus() {
		return direction;

	}

	/*
	 * Returns true if the game is over, which occurs when one player has more than
	 * three points AND has a margin of at least two points over the other player.
	 */
	public boolean isOver() {
		if (Math.abs(receiverPoints - serverPoints) >= 2 && (receiverPoints > 3 || serverPoints > 3)) {
			return true;
		} else {
			return false;
		}
	}

	// Returns true if the game is over and the server has won.
	public boolean serverWon() {
		if ((serverPoints > receiverPoints) && isOver() == true) {
			return true;
		} else {
			return false;
		}

	}

	// Returns true if the game is over and the receiver has won.
	public boolean receiverWon() {
		return receiverPoints > serverPoints && isOver();
	}

	/*
	 * Simulates the server serving the ball. If the serviceFault parameter is
	 * false, then the ball's status will be TOWARD_RECEIVER. If the serviceFault
	 * parameter is true, the number of faults is incremented, and if the number of
	 * faults reaches two, it is reset to zero and a point is awarded to the
	 * receiver. This method does nothing if the game is over or if the ball status
	 * isn't NOT_IN_PLAY.
	 */
	public void serve(boolean serviceFault) 
	{
		if ((this.isOver() == false && direction == BallDirection.NOT_IN_PLAY)) 
		{
			if (serviceFault == true) 
			{
				numFaults++;
				if (numFaults >= 2);
				{
					numFaults = 0;
					receiverPoints++;
				}
			}
			else
			{
				direction = BallDirection.TOWARD_RECEIVER;
				numFaults = 0;
			}
		}
	}

	/*
	 * Simulates a hit of the ball by the player toward whom the ball is currently
	 * moving. If the fault parameter is true, indicates that the hit results in a
	 * fault; then the rally ends with the other player getting a point, and the
	 * ball's status becomes NOT_IN_PLAY. If the fault parameter is false, then the
	 * ball's status just switches direction. The outOfBounds parameter indicates
	 * whether or not the trajectory of the ball would land it out of bounds, if the
	 * other player misses it. (The other player could elect to hit the ball before
	 * it bounces.) This method does nothing if the ball is not in play.
	 */
	public void hit(boolean fault, boolean headedOutOfBounds) 
	{
		if (fault == false)
		{
			if (direction == BallDirection.TOWARD_RECEIVER)
			{
				direction = BallDirection.TOWARD_SERVER;
			}
			else
			{
				direction = BallDirection.TOWARD_RECEIVER;
			}
			this.outOfBoundsTrajectory = headedOutOfBounds;
		}
		else
		{
			if (direction == BallDirection.TOWARD_RECEIVER)
			{
				serverPoints++;
			}
			else
			{
				receiverPoints++;
			}
			direction = BallDirection.NOT_IN_PLAY;
		}
	}

	/*
	 * Simulates a miss of the ball by the player toward whom the ball is currently
	 * traveling. If the ball is on an out-of-bounds trajectory, that player gets a
	 * point, otherwise the other player gets a point. This method always ends the
	 * rally, i.e., after this method executes, the ball is no longer in play. This
	 * method does nothing if the ball is not in play.
	 */
	public void miss() {
		if (direction != BallDirection.NOT_IN_PLAY) {
			if (outOfBoundsTrajectory) {
				if (direction == BallDirection.TOWARD_RECEIVER) {
					receiverPoints++;
				} else {
					serverPoints++;
					outOfBoundsTrajectory = false;
				}
			} else {
				if (direction == BallDirection.TOWARD_RECEIVER) {
					serverPoints++;
				} else {
					receiverPoints++;
				}
				direction = BallDirection.NOT_IN_PLAY;
			}
		}
	}

	/*
	 * Returns a string representation of the raw points for each player, in the
	 * form "x-y" where x is the number of points for the server and y is the number
	 * of points for the receiver.
	 */
	public java.lang.String getScore() {
		return serverPoints + "-" + receiverPoints;
	}

	/*
	 * Returns a string representation of the score using the bizarre conventions of
	 * tennis. If the game is over, the returned string is always of the form "x-y",
	 * where x is the server's score and y is the receiver's score. When the game is
	 * not over, the following rules apply: If the server's score is at least 4 and
	 * is exactly one more than the receiver's score, then the string is "advantage
	 * in”; If the receiver's score is at least 4 and is exactly one more than the
	 * receiver's score, then the string is "advantage out”; If the scores are equal
	 * and at least 3, the string is "deuce”; If the scores are equal and the value
	 * is 0, 1, or 2, the string is "love-all", "15-all", or "30-all", respectively;
	 * In all other cases, the string is of the form "a-b", where a is a string
	 * describing the server's score and b is a string describing the receiver's
	 * score, using "love" for 0 points, "15" for 1 point, "30" for 2 points, and
	 * "40" for three points.
	 */
	public String getCallString()
	{
		// If you keep giving me if-else statements that can be accomplished with single expressions, you'll keep getting single expressions
		return (isOver()) ? getScore() : 
					(serverPoints >= 4 && serverPoints - receiverPoints == 1) ? "advantage in" : 
					(receiverPoints >= 4 && receiverPoints - serverPoints == 1) ? "advantage out" :
					(serverPoints == receiverPoints && serverPoints >= 3) ? "deuce" :
					(serverPoints == receiverPoints && serverPoints < 3) ? 
							((serverPoints == 2) ? "30-all" : 
							((serverPoints == 1) ? "15-all" : "love-all")) : getSubCallString();
	}
	
	/**
	 * Determines the callString for a beginning-game (receiver or server scores < 3)
	 * @return representing the game's current score using tennis conventions
	 */
	protected String getSubCallString()
	{
		String section1 = (serverPoints == 0) ? "love" : (serverPoints == 1) ? "15" : (serverPoints == 2) ? "30" : "40";
		String section2 = (receiverPoints == 0 ? "love" : (receiverPoints == 1) ? "15" : (receiverPoints == 2) ? "30" : "40");
		return section1 + "-" + section2;
	}
}
