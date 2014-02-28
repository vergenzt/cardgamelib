

import java.io.IOException;
import java.net.Socket;

import javax.swing.text.Position;

import cardgamelib.base.cards.Hand;
import cardgamelib.base.game.Player;
import cardgamelib.standard.StandardCard;

public class PresidentsPlayer extends Player<PresidentsGame> {

	private Hand<StandardCard> hand;
	private Position position;

	protected PresidentsPlayer(PresidentsGame game, Socket socket, int playerId)
			throws IOException {
		super(game, socket, playerId);

		this.hand = new Hand<StandardCard>(CardComparator.instance);
		this.position = null;
	}

	public Hand<StandardCard> getHand() {
		return hand;
	}

	public Position getPosition() {
		return position;
	}

}
