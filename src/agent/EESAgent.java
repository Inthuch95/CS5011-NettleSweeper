package agent;

import java.util.ArrayList;

import game.Cell;
import game.NettleSweeper;

public class EESAgent extends BasicAgent {
	private Cell[][] currentWorld;
	private ArrayList<Cell> covered = new ArrayList<Cell>();
	private ArrayList<Cell> uncovered = new ArrayList<Cell>();
	
	public EESAgent (NettleSweeper ns) {
		super(ns);
		covered = this.getCovered();
		uncovered = this.getUncovered();
		currentWorld = this.getCurrentWorld();
	}
	
	public void easyEquationStrategy() {
		ArrayList<Cell> frontiers = getFrontiers();
		ArrayList<ArrayList<Cell>> borderingPairs = getBorderingPairs(frontiers);
		Cell cellA, cellB;
		int diff;
		ArrayList<Cell> unmarkedSetA, unmarkedSetB, setDiff;
		for (int i = 0; i < borderingPairs.size(); i++) {
			// refer to the pair as cell A and cell B
			cellA = borderingPairs.get(i).get(0);
			cellB = borderingPairs.get(i).get(1);
			// calculate diff
			diff = getDiff(cellA, cellB);
			// get unmarked neighbors of both cells
			unmarkedSetA = getUnmarkedNeighbors(cellA.getRow(), cellA.getCol());
			unmarkedSetB = getUnmarkedNeighbors(cellB.getRow(), cellB.getCol());
			// check for overlaps
			// don't take any action if there is no overlap
			if (unmarkedSetA.containsAll(unmarkedSetB) || unmarkedSetB.containsAll(unmarkedSetA)) {
				setDiff = getSetDiff(unmarkedSetA,unmarkedSetB);
				// open cells if 0
				// mark cells if diff is equal to the size of setDiff
				// otherwise abandon
				if (diff == 0) {
					for (Cell cell : setDiff) {
						openCell(cell.getRow(), cell.getCol());
					}
				} else if (diff == setDiff.size()) {
					// need to fix marking logic
					for (Cell cell : setDiff) {
						markCell(cell.getRow(), cell.getCol());
					}
				}
				attemptToFinish();
			}
		}
	}
	
	private ArrayList<Cell> getSetDiff(ArrayList<Cell>setA, ArrayList<Cell> setB) {
		// get the difference between the two sets
		ArrayList<Cell> setDiff = new ArrayList<Cell>();
		if (setA.size() > setB.size()) {
			setA.removeAll(setB);
			setDiff = setA;
		} else {
			setB.removeAll(setA);
			setDiff = setB;
		}
		return setDiff;
	}
	
	private int getDiff(Cell cellA, Cell cellB) {
		// calculate diff between the cell pair
		// diff = |(cellA_number - cellA_marked) - (cellB_number - cellB_marked)| 
		int cellAMarked = getMarkedNeighborsCount(cellA.getRow(), cellA.getCol());
		int cellBMarked = getMarkedNeighborsCount(cellB.getRow(), cellB.getCol());
		int diff = Math.abs((cellA.getNumber() - cellAMarked) 
				- (cellB.getNumber() - cellBMarked));
		return diff;
	}
	
	private ArrayList<Cell> getUnmarkedNeighbors(int row, int col) {
		// get all unmarked neighbors of a cell
		ArrayList<Cell> neighbors = getAllNeighbors(row, col);
		ArrayList<Cell> unmarked = new ArrayList<Cell>();
		for (Cell neighbor : neighbors) {
			if (neighbor.getNumber() == UNMARKED) {
				unmarked.add(neighbor);
			}
		}
		return unmarked;
	}
	
	private int getMarkedNeighborsCount(int row, int col) {
		// get the number of marked neighbors of a cell 
		ArrayList<Cell> neighbors = getAllNeighbors(row, col);
		int nettleCount = 0;
		for (Cell neighbor : neighbors) {
			if (neighbor.getNumber() == MARKED) {
				nettleCount++;
			}
		}
		return nettleCount;
	}
	
	private ArrayList<Cell> getFrontiers() {
		ArrayList<Cell> frontiers = new ArrayList<Cell>();
		// get uncovered cells which have at least one covered neighbor
		for (int row = 0; row < currentWorld.length; row++) {
			for (int col = 0; col < currentWorld.length; col++) {
				if (coveredNeighborExist(row, col) 
						&& uncovered.contains(currentWorld[row][col])) {
					frontiers.add(currentWorld[row][col]);
				}
			}
		}
		return frontiers;
	}
	
	private ArrayList<ArrayList<Cell>> getBorderingPairs(ArrayList<Cell> frontiers) {
		// create a list of bordering pairs
		ArrayList<ArrayList<Cell>> borderingPairs = new ArrayList<ArrayList<Cell>>();
		ArrayList<Cell> borderingCells = new ArrayList<Cell>();
		boolean duplicate;
		for (int i = 0; i < frontiers.size(); i++) {
			borderingCells = getBorderingCells(frontiers.get(i).getRow(), frontiers.get(i).getCol());
			for (Cell cell : borderingCells) {
				if (frontiers.contains(cell)) {
					ArrayList<Cell> pair = new ArrayList<Cell>();
					pair.add(frontiers.get(i));
					pair.add(cell);
					duplicate = checkDuplicatePairs(borderingPairs, pair);
					if (!duplicate) {
						borderingPairs.add(pair);
					}
				}
			}
		}
		return borderingPairs;
	}
	
	private boolean checkDuplicatePairs(ArrayList<ArrayList<Cell>> borderingPairs
			, ArrayList<Cell> pair) {
		// make sure that there is no duplicate bordering pairs
		boolean duplicate = false;
		for (int i = 0; i < borderingPairs.size(); i++) {
			if (borderingPairs.get(i).containsAll(pair)) {
				duplicate = true;
				break;
			}
		}
		
		return duplicate;
	}
	
	private ArrayList<Cell> getBorderingCells(int row, int col) {
		// get cells in north, south, east, and west direction
		ArrayList<Cell> borderingCells = new ArrayList<Cell>();
		// cell to the north
		if (isValidNeighbor(row - 1, col)) {
			borderingCells.add(currentWorld[row-1][col]);
		}
		// cell to the south
 		if(isValidNeighbor(row + 1, col)) {
 			borderingCells.add(currentWorld[row+1][col]);
 	    }
 		// cell to the east
 		if(isValidNeighbor(row, col + 1)) {
 			borderingCells.add(currentWorld[row][col+1]);
 	    }
 		// cell to the west
 		if(isValidNeighbor(row, col - 1)) {
 			borderingCells.add(currentWorld[row][col-1]);
 	    }
 		return borderingCells;
	}
	
	private boolean coveredNeighborExist(int row, int col) {
		// check if covered neighbor exists
		ArrayList<Cell> neighbors = getAllNeighbors(row, col);
		for (Cell neighbor : neighbors) {
			if (covered.contains(neighbor)) {
				return true;
			}
		}
		return false;
	}
}
