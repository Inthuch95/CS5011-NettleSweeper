package game;

/**
 * The Nettlesweeper game class. It contains a nettle sweeper world selected by the user
 * , total number of nettles and the number behind each cell. It is also responsible for 
 * creating the initial game environment for the agent
 *
 */
public class NettleSweeper {
	private final int UNMARKED = -2;
	private final int NETTLE = -1; 
	private int[][] world;
	private Cell[][] initialWorld;
	private String difficulty;
	private int worldNumber;
	
	public NettleSweeper(String difficulty, int worldNumber) {
		// initialize the game 
		this.difficulty = difficulty;
		this.worldNumber = worldNumber;
		getChosenWorld(difficulty, worldNumber);
		createGameEnvironment();
	}
	
	/**
	 * Reveal the number behind the cell to the agent.
	 * @param row - row of the cell
	 * @param col - column of the cell
	 * @return number behind the cell
	 */
	public int getCellNumber(int row, int col) {
		return this.world[row][col];
	}
	
	/**
	 * Allow the agent to access initial nettle world
	 * @return initial nettle world
	 */
	public Cell[][] getEnvironment() {
		return this.initialWorld;
	}
	
	/**
	 * Allow the agent to access the total number of nettle
	 * @return total number of nettle
	 */
	public int getNumberOfNettle() {
		int nettle = 0;
		for (int row = 0; row < world.length; row++) {
			for (int col = 0; col < world.length; col++) {
				if (world[row][col] == NETTLE) {
					nettle++;
				}
			}
		}
		return nettle;
	}
	
	/**
	 * Allow the agent to know about the difficulty of the game
	 * @return chosen difficulty level
	 */
	public String getDifficulty() {
		return this.difficulty;
	}
	
	/**
	 * Allow the agent to read selected world number value
	 * @return chosen world number
	 */
	public int getWorldNumber() {
		return this.worldNumber;
	}
	
	private void getChosenWorld(String difficulty, int worldNumber) {
		// get the nettle world based on difficulty and world number
		Worlds w = new Worlds();
		if (difficulty.equals("easy")) {
			world = w.getEasyWorld(worldNumber);
		} else if (difficulty.equals("medium")) {
			world = w.getMediumWorld(worldNumber);
		} else {
			world = w.getHardWorld(worldNumber);
		}
	}
	
	private void createGameEnvironment() {
		// create game world for the agent
		// all cells are covered at the beginning
		initialWorld = new Cell[world.length][world.length];
		for (int i = 0; i < world.length; i++) {
			for (int j = 0;j < world.length;j++) {
				initialWorld[i][j] = new Cell(i, j, UNMARKED);
			}
		}
	}
	
	public boolean isGameOver(int cellNumber) {
		// check if the agent uncovers a nettle
		if (cellNumber == NETTLE) {
			return true;
		}
		return false;
	}
}
