package agent;

import java.util.ArrayList;
import java.util.Random;

import game.Cell;
import game.NettleSweeper;

public class BasicAgent {
	private NettleSweeper ns;
	private int randomGuess = 0;
	protected Cell[][] currentWorld;
	protected final int UNMARKED = -2;
	protected final int MARKED = -3; 
	protected boolean gameOver = false;
	protected ArrayList<Cell> covered;
	protected ArrayList<Cell> uncovered;
	protected ArrayList<Cell> marked;
	protected boolean worldChanged;
	
	public BasicAgent(NettleSweeper ns) {
		this.ns = ns;
		currentWorld = ns.getEnvironment();
		createKnowledgeBase();
	}
	
	public void solveNettleWorld() {
		printWorld();
		// uncover cell(0, 0) first
		openCell(0, 0);
		printWorld();
		while (!covered.isEmpty()) {
			worldChanged = false;
			// attempt to use SPS
			System.out.println("solving with SPS");
			singlePointStrategy();
			// if SPS cannot make further changes to the world, resort to RGS
			if (!worldChanged) {
				System.out.println("resort to RGS");
				randomGuessStrategy();
			}
			// print current status of the nettle world
			printWorld();
			// if the agent uncover a nettle then the game is over
			if (gameOver) {
				break;
			}
		}
		printSummary();
	} 
	
	protected void singlePointStrategy() {
		// uncover/mark cells with SPS
		for (int row = 0; row < currentWorld.length; row++) {
			for (int col = 0; col < currentWorld.length; col++) {
				if (covered.contains(currentWorld[row][col])) {
					takeAppropriateAction(row, col);
				}
			}
		}
	}
	
	private void takeAppropriateAction(int row, int col) {
		// uncover the cell if AFN
		// mark the cell if AMN
		ArrayList<Cell> neighbors = getAllNeighbors(row, col);
		for (Cell neighbor : neighbors) {
			if (uncovered.contains(neighbor) || marked.contains(neighbor)) {
				if (allFreeNeighbors(neighbor)) {
					openCell(row, col);
					break;
				} else if (allMarkedNeighbors(neighbor)) {
					markCell(row, col);
					break;
				}
			}
		}
	}
	
	private boolean allFreeNeighbors(Cell cell) {
		// true if: cellNumber == nettleCount
		ArrayList<Cell> neighbors = getAllNeighbors(cell.getRow(), cell.getCol());
		int nettleCount = 0;
		for (Cell neighbor : neighbors) {
			if (neighbor.getNumber() == MARKED) {
				nettleCount++;
			}
		}
		if (cell.getNumber() == nettleCount) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean allMarkedNeighbors(Cell cell) {
		// true if: cellNumber - nettleCount ==  unmarkedCount
		ArrayList<Cell> neighbors = getAllNeighbors(cell.getRow(), cell.getCol());
		int nettleCount = 0;
		int unmarkedCount = 0;
		for (Cell neighbor : neighbors) {
			if (neighbor.getNumber() == UNMARKED) {
				unmarkedCount++;
			}
			if (neighbor.getNumber() == MARKED) {
				nettleCount++;
			}
		}
		if (cell.getNumber() - nettleCount == unmarkedCount) {
			return true;
		} else {
			return false;
		}
	}
	
	protected void randomGuessStrategy() {
		// pick a random cell from covered list and uncover that cell
		Random randomGenerator = new Random();
		int index = randomGenerator.nextInt(covered.size());
		Cell cell = covered.get(index);
		openCell(cell.getRow(), cell.getCol());
		randomGuess++;
	}
	
	protected void openCell(int row, int col) {
		System.out.println("reveal " + row + " " + col);
		// ask the game to reveal the number behind the cell 
		int number = ns.getCellNumber(row, col);
		currentWorld[row][col].setNumber(number);
		// update knowledge base
		covered.remove(currentWorld[row][col]);
		uncovered.add(currentWorld[row][col]);
		if (number == 0) {
			openAllNeighborCells(row, col);
		}
		worldChanged = true;
		// check if the cell contain nettle
		gameOver = ns.isGameOver(currentWorld[row][col].getNumber());
	}
	
	private void openAllNeighborCells(int row, int col) {
		// open all valid neighbors of current cell
		ArrayList<Cell> neighbors = getAllNeighbors(row, col);
		
		for (Cell neighbor : neighbors) {
			if (!uncovered.contains(neighbor) && !marked.contains(neighbor)) {
				openCell(neighbor.getRow(), neighbor.getCol());
			}
		}
	}
	
	protected void markCell(int row, int col) {
		System.out.println("mark " + row + " " + col);
		// mark the cell indicating that it contains nettle
		currentWorld[row][col].setNumber(MARKED);
		// update knowledge base
		covered.remove(currentWorld[row][col]);
		marked.add(currentWorld[row][col]);
		worldChanged = true;
	}
	
	protected ArrayList<Cell> getAllNeighbors(int row, int col) {
		ArrayList<Cell> neighbors = new ArrayList<Cell>();
		// cell to the north
		if (isValidNeighbor(row - 1, col)) {
			neighbors.add(currentWorld[row-1][col]);
		}
		// cell to the south
 		if(isValidNeighbor(row + 1, col)) {
 			neighbors.add(currentWorld[row+1][col]);
 	    }
 		// cell to the east
 		if(isValidNeighbor(row, col + 1)) {
 			neighbors.add(currentWorld[row][col+1]);
 	    }
 		// cell to the west
 		if(isValidNeighbor(row, col - 1)) {
 			neighbors.add(currentWorld[row][col-1]);
 	    }
 		// cell to the northeast
		if (isValidNeighbor(row - 1, col + 1)) {
			neighbors.add(currentWorld[row - 1][col+1]);
		}
		// cell to the northwest
		if (isValidNeighbor(row - 1, col - 1)) {
			neighbors.add(currentWorld[row - 1][col-1]);
		}
		// cell to the southeast
 		if(isValidNeighbor(row + 1, col + 1)) {
 			neighbors.add(currentWorld[row+1][col+1]);
 	    }
 		// cell to the southwest
 		if(isValidNeighbor(row + 1, col - 1)) {
 			neighbors.add(currentWorld[row+1][col-1]);
 	    }
 		return neighbors;
	}
	
	protected boolean isValidNeighbor(int row, int col) {
		// returns true if cell is not out of bound
		return !(row < 0 || row >= currentWorld.length || col < 0 || col >= currentWorld.length);
	}
	
	protected void printWorld() {
		// print the status of current world
		System.out.println("--------------------");
		for (int i = 0; i < currentWorld.length; i++) {
			for (int j = 0;j < currentWorld[i].length;j++) {
				System.out.printf("%-4s", currentWorld[i][j]);
			}
			System.out.print("\n");
		}
		System.out.println("--------------------");
	}
	
	protected void printSummary() {
		// print the summary at the end of the game
		if (!gameOver) {
			System.out.println("\nSummary");
			printWorld();
			System.out.println("nettle world: " + ns.getDifficulty() + " " + ns.getWorldNumber());
			System.out.println("random guess: " + randomGuess);
			System.out.println("game won");
		} else {
			System.out.println("\nSummary");
			printWorld();
			System.out.println("nettle world: " + ns.getDifficulty() + " " + ns.getWorldNumber());
			System.out.println("random guess: " + randomGuess);
			System.out.println("game lost");
		}
	}
	
	private void createKnowledgeBase() {
		// populate the agent's knowledge base at the beginning of the game
		covered = new ArrayList<Cell>();
		uncovered = new ArrayList<Cell>();
		marked = new ArrayList<Cell>();
		for (int row = 0; row < currentWorld.length; row++) {
			for (int col = 0; col < currentWorld.length; col++) {
				covered.add(currentWorld[row][col]);
			}
		}
	}
}
