package cardgamelib.base.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class Communication {

	private String type;
	private String name;

	protected Communication(String type, String name) {
		this.type = type;
		this.name = name;
	}

	public String send(PrintWriter output, BufferedReader input) throws IOException {
		output.printf("%s %s\n", getType(), getName());
		output.println(getContents());
		String response = input.readLine();
		if (!acceptResponse(response)) {
			throw new RuntimeException();
		}
		output.printf("END %s\n", getName());
		return response;
	}

	/**
	 * Check whether the response is okay.
	 * @param response the response from the client
	 * @return true if response is valid
	 */
	protected abstract boolean acceptResponse(String response);

	public abstract String getContents();

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}
}