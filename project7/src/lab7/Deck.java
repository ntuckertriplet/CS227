package lab7;

import java.util.ArrayList;
import java.util.Random;

import lab7.Card.Suit;

/**
 * Class representing a standard 52-card deck of playing cards from which cards
 * can be selected at random.
 */
public class Deck {
	/**
	 * The cards comprising this deck.
	 */
	private Card[] cards;

	/**
	 * Constructs a new deck with a default random number generator.
	 */
	public Deck() {
		new Random();
		init();
	}

	/**
	 * Constructs a new deck with the given random number generator.
	 */
	public Deck(Random givenGenerator) {
		init();
	}

	/**
	 * Returns a new array containing k elements selected at random from this deck.
	 */
	public Card[] select(int k) {
		if (cards.length == 0) {
			return null;
		}
		int[] selectedCards = testerClass.getRandomArray(cards.length, k);
		// System.out.println(selected_cards.length);
		ArrayList<Card> result = new ArrayList<>();
		for (int i : selectedCards) {
			result.add(cards[i]);
		}
		// System.out.println(result.size());
		return result.toArray(new Card[result.size()]);
	}

	/**
	 * Initializes a new deck of 52 cards.
	 */
	private void init() {
		cards = new Card[52];
		int index = 0;
		for (int rank = 1; rank <= 13; ++rank) {
			cards[index] = new Card(rank, Suit.CLUBS);
			index += 1;
			cards[index] = new Card(rank, Suit.DIAMONDS);
			index += 1;
			cards[index] = new Card(rank, Suit.HEARTS);
			index += 1;
			cards[index] = new Card(rank, Suit.SPADES);
			index += 1;
		}

	}
}