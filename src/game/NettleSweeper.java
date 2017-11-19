package game;

public class NettleSweeper {
	private final int UNMARKED = -2;
	private final int NETTLE = -1; 
	private int[][] world;
	private Cell[][] initialWorld;
	private String difficulty;
	private int worldNumber;
	
	public NettleSweeper(String difficulty, int worldNumber) {
		this.difficulty = difficulty;
		this.worldNumber = worldNumber;
		getChosenWorld(difficulty, worldNumber);
		createGameEnvironment();
	}
	
	public int getCellNumber(int row, int col) {
		return this.world[row][col];
	}
	
	public Cell[][] getEnvironment() {
		return this.initialWorld;
	}
	
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
	
	public String getDifficulty() {
		return this.difficulty;
	}
	
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
		if (cellNumber == NETTLE) {
			return true;
		}
		return false;
	}
}
