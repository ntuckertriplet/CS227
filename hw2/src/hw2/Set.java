package hw2;

public class Set {
	private int player0Score = 0;
	private int player1Score = 0;
	private int minGames = 0;
	private boolean player0ServeFirst = false;
	private TennisGame game;

	/*
	 * Invokes serve on the current game and updates the winner's count of games won
	 * if the action ends the current game. Does nothing if the set is over or if
	 * the current game is over.
	 */
	public Set(int minimumGamesToWin, boolean player1ServesFirst) {
		this.minGames = minimumGamesToWin;
		this.player0ServeFirst = player1ServesFirst;
		game = new TennisGame();
	}

	/*
	 * Invokes serve on the current game and updates the winner's count of games won
	 * if the action ends the current game. Does nothing if the set is over or if
	 * the current game is over.
	 */
	public void serve(boolean serviceFault) {
		if (isSetOver() == false && game.isOver() == false) {
			game.serve(serviceFault);
			if (serviceFault) {
				updateScores();
			}
		}
	}

	/*
	 * Invokes hit on the current game and updates the winner's count of games won
	 * if the action ends the current game. Does nothing if the set is over or if
	 * the current game is over.
	 */
	public void hit(boolean fault, boolean outOfBounds) {
		if (this.isSetOver() == false && game.isOver() == false) {
			game.hit(fault, outOfBounds);
			updateScores();
		}
	}

	/*
	 * Invokes miss on the current game and updates the winner's count of games won
	 * if the action ends the current game. Does nothing if the set is over or if
	 * the current game is over.
	 */
	public void miss() {
		if (isSetOver() == false && game.isOver() == false) {
			game.miss();
			updateScores();
		}
	}

	/*
	 * Invokes setScore on the current game and updates the winner's count of games
	 * won if the action ends the current game. Does nothing if the set is over or
	 * if the current game is over. This method is intended for testing and does not
	 * check that the given scores are realistic.
	 */

	public void fastForward(int serverScore, int receiverScore) {
		if ((isSetOver() == false) && (game.isOver() == false)) {
			game.setScore(serverScore, receiverScore);
			updateScores();
		}
	}

	/*
	 * Starts a new game in this set, switching the service to the opposite player.
	 * This method does nothing if the current game is still in progress, or if the
	 * set is over.
	 */
	public void newGame() {
		if (game.isOver() == true && isSetOver() == false) {
			player0ServeFirst = !player0ServeFirst;
			game = new TennisGame();
		}
	}

	// Returns true if the current game is over.
	public boolean isCurrentGameOver() {
		return game.isOver();
	}

	/*
	 * Returns true if the set is over. The set ends when a player has won the
	 * minimum number of games AND has won at least two games more than the other
	 * player.
	 */
	public boolean isSetOver() {
		if (Math.abs(player1Score - player0Score) >= 2 && (player1Score >= minGames || player0Score >= minGames)) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Returns a string representation of the current status of the set in the form
	 * "Set: x-y Game: ss". Here x is the number of games won by the currently
	 * serving player, y is the number of games won by the other player, and ss is
	 * the score string for the current game. If the parameter useCallString is
	 * false, then the string ss is formatted as in TennisGame.getScore();
	 * otherwise, the string ss is formed according to the conventions of
	 * TennisGame.getCallString().
	 */
	public String getCurrentStatus(boolean useCallString) {
		String setScore = "Set: "
				+ ((player0ServeFirst) ? player1Score + "-" + player0Score : player0Score + "-" + player1Score);
		String gameScore = " Game: " + ((useCallString) ? game.getCallString() : game.getScore());
		return setScore + gameScore;
	}

	// Returns the player (0 or 1) who is the server in the current game.
	public int whoIsServing() {
		return (player0ServeFirst) ? 1 : 0;
	}

	// Returns the number of games won by player 0.
	public int player0GamesWon() {
		return player0Score;
	}

	// Returns the number of games won by player 1.
	public int player1GamesWon() {
		return player1Score;
	}

	/**
	 * Updates the player's score for the game. Does nothing if the game is over
	 */
	protected void updateScores() {
		if (game.isOver()) {
			if (game.receiverWon()) {
				if (player0ServeFirst) {
					player0Score++;
				} else {
					player1Score++;
				}
			} else {
				if (player0ServeFirst) {
					player1Score++;
				} else {
					player0Score++;
				}
			}
		}
	}
}
