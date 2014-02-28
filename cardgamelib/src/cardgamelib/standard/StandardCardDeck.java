package cardgamelib.standard;

import cardgamelib.base.cards.CardDeck;
import cardgamelib.standard.StandardCard.Rank;
import cardgamelib.standard.StandardCard.Suit;

public class StandardCardDeck extends CardDeck<StandardCard> {

	private StandardCardDeck() { }

	/**
	 * Get a standard card deck, without Jokers.
	 * @return a standard playing card deck
	 */
	public static StandardCardDeck newDeck() {
		StandardCardDeck deck = new StandardCardDeck();
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.valuesWithoutJoker()) {
				deck.add(new StandardCard(rank, suit));
			}
		}
		return deck;
	}

	/**
	 * Get a standard card deck, with Jokers.
	 * @return a standard playing card deck
	 */
	public static StandardCardDeck newDeckWithJokers() {
		return (StandardCardDeck)newDeck()
			.add(new StandardCard(Rank.JOKER, null))
			.add(new StandardCard(Rank.JOKER, null));
	}

	/**
	 * Get a standard card deck, without Jokers, shuffled.
	 * @return a standard playing card deck
	 */
	public static StandardCardDeck newShuffledDeck() {
		return (StandardCardDeck)newDeck()
			.shuffle();
	}

	/**
	 * Get a standard card deck, with Jokers, shuffled.
	 * @return a standard playing card deck
	 */
	public static StandardCardDeck newShuffledDeckWithJokers() {
		return (StandardCardDeck)newDeckWithJokers().shuffle();
	}
}
