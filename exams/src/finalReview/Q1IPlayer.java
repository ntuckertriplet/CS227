package finalReview;

import java.util.*;

public class Q1IPlayer {
	public static void main(String[] args) {
		
		
		
	}
	
	
	interface IPlayer {
		int play(); // Returns the player's move, which is always 0, 1, or 2

		int getPreviousMove(int movesAgo); // Returns a previous move
	}

	
	class RandomPlayer implements IPlayer {
		private Random rand = new Random();
		private ArrayList<Integer> history = new ArrayList<Integer>();

		public int play() {
			int move = rand.nextInt(3); // randomly chooses 0, 1, or 2
			history.add(move);
			return move;
		}

		public int getPreviousMove(int movesAgo) {
			return history.get(history.size() - movesAgo);
		}
	}

	
	class AlternatingPlayer implements IPlayer {
		private int state = 0;
		private Random rand = new Random();
		private ArrayList<Integer> history = new ArrayList<Integer>();

		public int play() {
			// usually returns 0, but every third move randomly chooses 1 or 2
			int move = 0;
			if (state % 3 == 2) {
				move = rand.nextInt(2) + 1;
			}
			++state;
			history.add(move);
			return move;
		}

		
		public int getPreviousMove(int movesAgo) {
			return history.get(history.size() - movesAgo);
		}
	}

}
