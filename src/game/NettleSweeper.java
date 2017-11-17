package game;

public class NettleSweeper {
	private int[][] world;
	private Cell[][] initialWorld;
	
	public NettleSweeper(int[][] world) {
		this.world = world;
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
				if (world[row][col] == -1) {
					nettle++;
				}
			}
		}
		return nettle;
	}
	
	public void createGameEnvironment() {
		// create game world for the agent
		// all cells are covered at the beginning
		initialWorld = new Cell[world.length][world.length];
		final int UNMARKED = -2;
		for (int i = 0; i < world.length; i++) {
			for (int j = 0;j < world.length;j++) {
				initialWorld[i][j] = new Cell(i, j, UNMARKED);
			}
		}
	}
	
	public boolean isGameOver(int cellNumber) {
		if (cellNumber == -1) {
			return true;
		}
		return false;
	}
}
