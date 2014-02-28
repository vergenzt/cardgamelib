package cardgamelib.standard;

import cardgamelib.base.cards.Card;

/**
 * A card from a deck of standard playing cards.
 * @author Tim Vergenz
 */
public class StandardCard extends Card {

	public enum Rank {
		R2("2"), R3("3"), R4("4"), R5("5"), R6("6"), R7("7"), R8("8"), R9("9"), R10("10"),
		JACK("J"), QUEEN("Q"), KING("K"), ACE("A"), JOKER("Jok");

		private String name;
		private Rank(String name) { this.name = name; }
		@Override
		public String toString() { return name; }

		private static Rank[] valuesWithoutJoker;

		/**
		 * Returns Rank.values() minus Rank.JOKER.
		 * @return everything but the Joker
		 */
		public static Rank[] valuesWithoutJoker() {
			if (valuesWithoutJoker != null)
				return valuesWithoutJoker;
			else {
				Rank[] values = Rank.values();
				Rank[] valuesWithoutJoker = new Rank[values.length-1];
				for (int i=0,j=0; i<values.length; i++) {
					if (values[i] != Rank.JOKER)
						valuesWithoutJoker[j++] = values[i];
				}
				return valuesWithoutJoker;
			}
		}
	}
	public enum Suit {
		SPADES("♠"), HEARTS("♥"), CLUBS("♣"), DIAMONDS("♦");

		private String name;
		private Suit(String name) { this.name = name; }
		@Override
		public String toString() { return name; }
	}

	private final Rank rank;
	private final Suit suit;

	/*
	 * Private to enforce singleton.
	 */
	StandardCard(Rank rank, Suit suit) {
		if (rank == Rank.JOKER)
			assert suit == null;
		this.rank = rank;
		this.suit = suit;
	}

	/**
	 * Get the rank of this card.
	 * @return the rank of this card
	 */
	public Rank getRank() {
		return rank;
	}

	/**
	 * Get the suit of this card.
	 * @return the suit of this card
	 */
	public Suit getSuit() {
		return suit;
	}

	@Override
	public String toString() {
		return rank.toString() + (suit != null ? suit.toString() : "");
	}
}
