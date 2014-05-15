package cardgamelib.base.cards;

import java.util.LinkedList;
import java.util.List;

/**
 * A stack of cards.
 * @author Tim Vergenz
 */
public class CardStack<C extends Card> {

	// instance variables
	protected final List<C> cards;

	protected CardStack() {
		cards = new LinkedList<C>();
	}

	/**
	 * Add a card to the top of this stack.
	 * @param card
	 * @return the stack
	 */
	public CardStack<C> add(C card) {
		cards.add(card);
		return this;
	}

}
