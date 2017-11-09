package game;

public class LogicalAgent {
	private NettleSweeper ns;
	private Cell[][] currentWorld;
	private final int UNMARKED = -2;
	private final int FLAGGED = -3; 
	
	public LogicalAgent(NettleSweeper ns) {
		this.ns = ns;
		createGameWorld();
	}
	
	public void solve() {
		// probe (0, 0) first
		openCell(0, 0);
		printWorld();
	}
	
	private void openCell(int row, int col) {
		// open an unmarked cell
		if (currentWorld[row][col].getNumber() == UNMARKED) {
			int number = ns.getCellInfo(row, col);
			currentWorld[row][col].setNumber(number); 
		}
	}
	
	private void markCell(int row, int col) {
		// mark the cell indicating that it contains nettle
		currentWorld[row][col].setNumber(FLAGGED);
	}
	
	private void printWorld() {
		// print the status of current world
		for (int i = 0; i < currentWorld.length; i++) {
			for (int j = 0;j < currentWorld[i].length;j++) {
				System.out.print(currentWorld[i][j] + " ");
			}
			System.out.print("\n");
		}
	}
	
	private void createGameWorld() {
		// create game world for the agent
		// all cells are unprobed at the beginning
		int dimension = ns.getDimension();
		currentWorld = new Cell[dimension][dimension];
		for (int i = 0; i < dimension; i++) {
			for (int j = 0;j < dimension;j++) {
				currentWorld[i][j] = new Cell(i, j, UNMARKED);
			}
		}
	}
}
