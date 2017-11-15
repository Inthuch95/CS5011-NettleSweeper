package game;

public class NettleSweeper {
	private int[][] world;
	
	public NettleSweeper(int[][] world) {
		this.world = world;
	}
	
	public int getCellNumber(int row, int col) {
		return this.world[row][col];
	}
	
	public int getDimension() {
		return this.world.length;
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
	
	public boolean isGameOver(int cellNumber) {
		if (cellNumber == -1) {
			return true;
		}
		return false;
	}
}
