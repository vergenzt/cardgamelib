package cardgamelib.base.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * The helper class for player threads in a game.
 */
public abstract class Player<G extends Game<G>> extends Thread {

	private final Socket socket;
	private final BufferedReader input;
	private final PrintWriter output;

	protected G game;
	protected int playerId;
	protected String name;

	private Queue<Communication> commQueue = new ConcurrentLinkedQueue<Communication>();

	protected Player(G game, Socket socket, int playerId) throws IOException {
		super("player-" + playerId);
		this.game = game;
		this.playerId = playerId;
		this.socket = socket;
		this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		this.output = new PrintWriter(socket.getOutputStream(), true);
	}

	public void inform(Information info) throws IOException {
		commQueue.add(info);
	}

	public void informAndBlock(Information info) throws IOException {
		inform(info);
		while (commQueue.contains(info));
	}

	@Override
	public void run() {
		try {
			output.println("Name?");
			name = input.readLine();
			output.println("Hello, player " + playerId + ", " + name);

			while (true) {
				if (!commQueue.isEmpty()) {
					Communication comm = commQueue.poll();
					comm.send(output, input);
				}
			}
        } catch (IOException e) {
			e.printStackTrace();
		} finally {
            try {socket.close();} catch (IOException e) {}
        }
	}

}