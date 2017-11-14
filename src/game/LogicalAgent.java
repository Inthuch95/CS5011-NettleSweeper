package game;

import java.util.ArrayList;

public class LogicalAgent {
	private final int UNMARKED = -2;
	private final int FLAGGED = -3; 
	private NettleSweeper ns;
	private Cell[][] currentWorld;
	private boolean gameLost = false;
	private ArrayList<Cell> unopened = new ArrayList<Cell>();
	private ArrayList<Cell> opened = new ArrayList<Cell>();
	
	public LogicalAgent(NettleSweeper ns) {
		this.ns = ns;
		createGameWorld();
	}
	
	public void openCell(int row, int col) {
		System.out.println("reveal " + row + " " + col);
		// ask the game to reveal the number behind the cell 
		int number = ns.getCellNumber(row, col);
		currentWorld[row][col].setNumber(number);
		// update list of opened and unopened cells
		unopened.remove(currentWorld[row][col]);
		opened.add(currentWorld[row][col]);
		// open all neighbors if cell's value is 0
		if (currentWorld[row][col].getNumber() == 0) {
			openAllNeighborCells(row, col);
		}
		// check if the cell contain nettle
		isNettle(currentWorld[row][col]);
	}
	
	public void openAllNeighborCells(int row, int col) {
		// open all valid neighbors of current cell
		// cell to the north
		if (isValidNeighbor(row - 1, col) && !opened.contains(currentWorld[row-1][col])) {
			openCell(row - 1, col);
		}
		// cell to the south
 		if(isValidNeighbor(row + 1, col) && !opened.contains(currentWorld[row+1][col])) {
 			openCell(row + 1, col);
 	    }
 		// cell to the east
 		if(isValidNeighbor(row, col + 1) && !opened.contains(currentWorld[row][col+1])) {
 			openCell(row, col + 1);
 	    }
 		// cell to the west
 		if(isValidNeighbor(row, col - 1) && !opened.contains(currentWorld[row][col-1])) {
 			openCell(row, col - 1);
 	    }
 		// cell to the northeast
		if (isValidNeighbor(row - 1, col + 1) && !opened.contains(currentWorld[row-1][col+1])) {
			openCell(row - 1, col + 1);
		}
		// cell to the northwest
		if (isValidNeighbor(row - 1, col - 1) && !opened.contains(currentWorld[row-1][col-1])) {
			openCell(row - 1, col - 1);
		}
		// cell to the southeast
 		if(isValidNeighbor(row + 1, col + 1) && !opened.contains(currentWorld[row+1][col+1])) {
 			openCell(row + 1, col + 1);
 	    }
 		// cell to the southwest
 		if(isValidNeighbor(row + 1, col - 1) && !opened.contains(currentWorld[row+1][col-1])) {
 			openCell(row + 1, col - 1);
 	    }
	}
	
	private boolean isValidNeighbor(int row, int col) {
		return !(row < 0 || row >= currentWorld.length || col < 0 || col >= currentWorld.length);
	}
	
	public void markCell(int row, int col) {
		System.out.println("mark " + row + " " + col);
		// mark the cell indicating that it contains nettle
		currentWorld[row][col].setNumber(FLAGGED);
		// update list of opened and unopened cells
		unopened.remove(currentWorld[row][col]);
		opened.add(currentWorld[row][col]);
	}
	
	public void printWorld() {
		// print the status of current world
		System.out.println("--------------------");
		for (int i = 0; i < currentWorld.length; i++) {
			for (int j = 0;j < currentWorld[i].length;j++) {
				System.out.print(currentWorld[i][j] + " ");
			}
			System.out.print("\n");
		}
		System.out.println("--------------------");
	}
	
	public void isNettle(Cell currentCell) {
		if (currentCell.getNumber() == -1) {
			gameLost = true;
		}
	}
	
	public boolean getGameLost() {
		return this.gameLost;
	}
	
	private void createGameWorld() {
		// create game world for the agent
		// all cells are unopened at the beginning
		int dimension = ns.getDimension();
		currentWorld = new Cell[dimension][dimension];
		for (int i = 0; i < dimension; i++) {
			for (int j = 0;j < dimension;j++) {
				currentWorld[i][j] = new Cell(i, j, UNMARKED);
				unopened.add(currentWorld[i][j]);
			}
		}
	}
}
