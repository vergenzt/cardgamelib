package cardgamelib.base.game;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

import com.google.common.collect.Lists;

public abstract class Game<G extends Game<G>> {
	private static final int PORT = 8972;

	/**
	 * Run a server to host the game.
	 * @param args
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) throws Exception {

		if (args.length != 1) {
			System.err.println("Invalid arguments. 1st argument must be Game class name.");
			System.exit(1);
		}

		// initialize variables
		final ServerSocket listener;
		final Game game;
		final List players = Lists.newArrayList();

		// instantiate the game
		String gameClassName = args[0];
		System.out.println("Constructing game: " + gameClassName);
		try {
			game = (Game)Class.forName(gameClassName).newInstance();
		} catch (ReflectiveOperationException e) {
			System.err.println("Could not instantiate game class.");
			e.printStackTrace();
			System.exit(1);
			return;
		}

		// get the agents
		System.out.println("Opening server port " + PORT);
		listener = new ServerSocket(PORT);

		System.out.println("Waiting for agents to join...");
		final AtomicBoolean done = new AtomicBoolean(false);
		Thread thread = new Thread("get-agents") {
			@Override public void run() {
				while (!done.get()) {
					try {
						listener.setSoTimeout(1);
						players.add(game.newPlayer(listener.accept(), players.size()));
						System.out.println("Added player.");
					} catch (SocketTimeoutException e) {
					} catch (IOException e) {
						e.printStackTrace();
						System.exit(1);
					}
				}
			}
		};
		thread.start();

		System.out.println("(Press Enter to begin game.)");
		Scanner sc = new Scanner(System.in);
		sc.nextLine();
		sc.close();
		System.out.println("Beginning game.");
		done.set(true);
		thread.join();

		// begin doing stuff
		game.setUp(players);
		for (Player player : (List<Player>)players)
			player.start();
		game.mainLoop();

		listener.close();
	}

	/**
	 * The public bus of events that everyone sees.
	 */
	//protected final Bus mainBus;
	//protected final Map<Player<G>,Bus> playerBuses;




	/**
	 * Initialize the state of the game.
	 */
	public abstract void setUp(List<? extends Player<G>> players);

	/**
	 * Get a new Player instance for this game.
	 * @param socket
	 * @param playerId
	 * @return an initialized player
	 * @throws IOException
	 */
	public abstract Player<G> newPlayer(Socket socket, int playerId)
			throws IOException;

	/**
	 * The main execution loop of the game.
	 * @throws IOException
	 */
	public abstract void mainLoop() throws IOException;

}
