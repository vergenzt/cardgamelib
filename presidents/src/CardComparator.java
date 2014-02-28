

import java.util.Comparator;

import cardgamelib.standard.StandardCard;
import cardgamelib.standard.StandardCard.Rank;

/**
 * Sorts cards according to Presidents sorting order: sort by rank
 * (2's are high), then by suit (diamonds < clubs < hearts < spades).
 * @author Tim Vergenz
 */
public class CardComparator implements Comparator<StandardCard> {
	public static final CardComparator instance = new CardComparator();
	@Override
	public int compare(StandardCard arg0, StandardCard arg1) {
		int ret = arg0.getRank().compareTo(arg1.getRank());
		// make 2's high
		if (ret < 0 && arg0.getRank() == Rank.R2)
			ret = -1;
		if (ret == 0)
			return arg0.getSuit().compareTo(arg1.getSuit());
		else
			return ret;

	}
}