package cardgamelib.base.cards;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

public class Hand<C extends Card> {

	private List<C> cards;
	private Comparator<C> comp;

	public static <C extends Card> Hand<C> emptyHand(Comparator<C> comp) {
		return new Hand<C>(comp);
	}

	/**
	 * Initialize an empty unsorted hand.
	 */
	public Hand() {
		this(null);
	}

	/**
	 * Initialize an empty hand that maintains sort by comp.
	 * @param comp the sort order comparator
	 */
	public Hand(Comparator<C> comp) {
		this.cards = new LinkedList<C>();
		this.comp = comp;
	}

	/**
	 * Get the cards in this hand.
	 * @return list of cards in this hand
	 */
	public List<C> getCards() {
		return ImmutableList.copyOf(cards);
	}

	/**
	 * Add a card to this hand. Maintains sort if this hand was passed a
	 * comparator in the constructor.
	 * @param card the card to add
	 */
	public void addCard(C card) {
		cards.add(card);
		if (comp != null) // if there is a sort comparator
			Collections.sort(cards, comp);
	}

	/**
	 * Sort the hand by the given comparator. No effect if hand already has a
	 * sort order from the constructor.
	 * @param comp the comparator
	 */
	public void sort(Comparator<C> comp) {
		if (this.comp != null && comp != null) {
			Collections.sort(cards, comp);
		}
	}

	@Override
	public String toString() {
		List<C> sorted = Lists.newLinkedList(cards);
		Collections.sort(sorted, comp);
		return Joiner.on(" ").join(sorted);
	}
}
