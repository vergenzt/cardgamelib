

import java.util.Collections;
import java.util.Comparator;
import java.util.Set;

import cardgamelib.standard.StandardCard;

public class CardSet implements Comparable<CardSet> {

	private Set<StandardCard> cards;

	public CardSet(Set<StandardCard> cards) {
		this.cards = cards;
	}

	public Set<StandardCard> getCards() {
		return cards;
	}
	
	public boolean isComparable(CardSet other) {
		return this.cards.size() == other.cards.size();
	}

	@Override
	public int compareTo(CardSet other) {
		Comparator<StandardCard> comp = CardComparator.instance;
		return comp.compare(
			Collections.max(cards, comp),
			Collections.max(other.cards, comp)
		);
	}

}
