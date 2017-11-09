package game;

public class NettleSweeper {
	private int[][] world;
	
	public NettleSweeper(int[][] world) {
		this.world = world;
	}
	
	public int getCellInfo(int row, int col) {
		return this.world[row][col];
	}
	
	public int getDimension() {
		return this.world.length;
	}
}
