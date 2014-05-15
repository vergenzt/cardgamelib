package cardgamelib.base.game;

public abstract class Information extends Communication {

	public Information(String name) {
		super("INFORM", name);
	}

	@Override
	protected boolean acceptResponse(String response) {
		return response.equalsIgnoreCase("okay");
	}
}
