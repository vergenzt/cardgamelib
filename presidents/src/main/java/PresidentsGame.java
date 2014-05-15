

import java.io.IOException;
import java.net.Socket;
import java.util.List;

import cardgamelib.base.game.Game;
import cardgamelib.base.game.Information;
import cardgamelib.base.game.Player;
import cardgamelib.standard.StandardCardDeck;

public class PresidentsGame extends Game<PresidentsGame> {

	private List<PresidentsPlayer> players;
	private boolean arePositionsSet = false;

	/**
	 * The positions in the game of President.
	 */
	public enum Position {
		PRES, VPRES, VBUTT, BUTT
	}

	/**
	 * Get the Agent in the given position. Returns null if that position is
	 * not filled or is ambiguous.
	 * @param pos
	 * @return the agent, or null if the position is ambiguous or filled
	 */
	public PresidentsPlayer getPosition(Position pos) {
		if (!arePositionsSet) return null;
		switch (pos) {
		case PRES: return players.get(0);
		case VPRES: return players.get(1);
		case BUTT: return players.get(players.size()-1);
		case VBUTT: return players.get(players.size()-2);
		}
		return null;
	}

	@Override
	public Player<PresidentsGame> newPlayer(Socket socket, int playerId) throws IOException {
		return new PresidentsPlayer(this, socket, playerId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setUp(List<? extends Player<PresidentsGame>> players) {
		assert players.size() >= 4;
		assert players.size() <= 8;
		this.players = (List<PresidentsPlayer>)players;
	}

	/**
	 * Initialize a round. Shuffle and deal the cards, and distribute them
	 * to the players. Request card exchanges if necessary.
	 * @throws IOException
	 */
	void setUpRound() throws IOException {
		StandardCardDeck deck = StandardCardDeck.newDeck();

		// get the cards to be even per player
		deck.sort(CardComparator.instance);
		for (int i=0; i<(deck.size() % players.size()); i++)
			deck.remove(0);
		deck.shuffle();

		// distribute them
		while (!deck.isEmpty()) {
			for (PresidentsPlayer player : players)
				player.getHand().addCard(deck.drawCard());
		}

		for (final PresidentsPlayer player : players) {
			player.inform(new Information("hand-updated") {
				@Override
				public String getContents() {
					return player.getHand().toString();
				}
			});
		}

	}

	@Override
	public void mainLoop() throws IOException {
		setUpRound();

		while(true);
	}

}
