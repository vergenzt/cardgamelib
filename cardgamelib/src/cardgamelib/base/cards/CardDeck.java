package cardgamelib.base.cards;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;


public class CardDeck<C extends Card> {

	// instance variables
	protected final List<C> cards;

	protected CardDeck() {
		cards = new LinkedList<C>();
	}

	/**
	 * Get the number of cards in the deck.
	 * @return the number of cards
	 */
	public int size() {
		return cards.size();
	}

	/**
	 * Get whether this deck is empty.
	 * @return true if size == 0
	 */
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Add a card to the top of this deck.
	 * @param card the card to add
	 * @return the card deck
	 */
	public CardDeck<C> add(C card) {
		cards.add(card);
		return this;
	}

	/**
	 * Remove a card from this deck.
	 * @param index the index of the card to remove
	 * @return the card deck
	 */
	public CardDeck<C> remove(int index) {
		cards.remove(index);
		return this;
	}

	/**
	 * Draw the top card from the deck (removing it) and return it.
	 * @return the drawn card
	 */
	public C drawCard() {
		return cards.remove(cards.size()-1);
	}

	/**
	 * Shuffle this deck.
	 * @return the card deck
	 */
	public CardDeck<C> shuffle() {
		Collections.shuffle(cards);
		return this;
	}

	/**
	 * Sort this deck according to the given order.
	 * @return the card deck
	 */
	public CardDeck<C> sort(Comparator<C> comp) {
		Collections.sort(cards, comp);
		return this;
	}

}
