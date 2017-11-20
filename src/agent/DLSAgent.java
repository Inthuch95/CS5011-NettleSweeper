package agent;

import aima.core.logic.propositional.parsing.ast.Sentence;

import java.util.ArrayList;

import aima.core.logic.propositional.inference.DPLLSatisfiable;
import aima.core.logic.propositional.parsing.PLParser;
import game.Cell;
import game.NettleSweeper;

public class DLSAgent extends BasicAgent {
	private DPLLSatisfiable dpll = new DPLLSatisfiable();
	private final String NOT = "~";
	private final String AND = "&";
	private final String OR = "|";

	public DLSAgent(NettleSweeper ns) {
		super(ns);
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
			// use DLS if SPS did not make any changes
			if (!worldChanged) {
				System.out.println("solving with DLS");
				DLS();
			}
			// if other strategies cannot make further changes to the world, resort to RGS
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

	private void DLS() {
		ArrayList<Cell> frontiers = getFrontiers();
		// create options for all cells
		ArrayList<String> optionsList = getLogicOptionsList(frontiers);
		// create KBU string
		String KBU = getKBU(optionsList);
		// try to find nettles first
		proveNettle(KBU);
		proveNotNettle(KBU);
	}
	
	private void proveNettle(String KBU) {
		// prove if the cells contain nettle
		ArrayList<Cell> coveredCells = new ArrayList<Cell>();
		coveredCells.addAll(covered);
		String p;
		String prove;
		boolean ans;
		for (Cell cell : coveredCells) {
			p = "N" + cell.getRow() + cell.getCol();
			System.out.println("ProveNettle " + p);
			prove = KBU + " & ~" + p;
			ans = getDPLLSatisfiableStatus(prove);
			System.out.println("Does KBU entail " + p + "?, Test KBU & ~" + p);
			if (!ans) {// if false mark
				System.out.println("Yes, Nettle, Mark");
				markCell(cell.getRow(), cell.getCol());
			} else {
				System.out.println("No");
			}
			System.out.println("");
		}
	}
	
	private void proveNotNettle(String KBU) {
		// prove that the cells are clear
		ArrayList<Cell> coveredCells = new ArrayList<Cell>();
		coveredCells.addAll(covered);
		String p;
		String prove;
		boolean ans;
		for (Cell cell : coveredCells) {
			p = "N" + cell.getRow() + cell.getCol();
			System.out.println("ProveNotNettle " + p);
			prove = KBU + " & " + p;
			ans = getDPLLSatisfiableStatus(prove);
			System.out.println("Does KBU entail " + p + "?, Test KBU & " + p);
			if (!ans) {// if false mark
				System.out.println("Yes, No Nettle, Probe");
				openCell(cell.getRow(), cell.getCol());
			} else {
				System.out.println("No");
			}
			System.out.println("");
		}
	}
	
	private boolean getDPLLSatisfiableStatus(String query) {
		PLParser parser = new PLParser();
		Sentence sent = (Sentence) parser.parse(query);
		if (dpll.dpllSatisfiable(sent)) {
			System.out.println("Query is satisfiable");
			return true;
			} else {
			System.out.println("Query is NOT satisfiable");
			return false;
			}
	}
	
	private String getKBU(ArrayList<String> optionsList) {
		// create KBU String
		String KBU = "";
		for (int i = 0; i < optionsList.size(); i++) {
			if (i != 0) {
				KBU += " " + AND + " ";
			}
			KBU += optionsList.get(i);
		}
		return KBU;
	}
	
	private ArrayList<String> getLogicOptionsList(ArrayList<Cell> frontiers) {
		// get all logic options and store them in an arraylist
		ArrayList<String> optionsList = new ArrayList<String>();
		ArrayList<ArrayList<Cell>> possibleNettleSet;
		ArrayList<Cell> unmarked;
		String options;
		Cell cell;
		int nettleCount, markedCount;
		for (int i = 0; i < frontiers.size(); i++) {
			cell = frontiers.get(i);
			unmarked = getUnmarkedNeighbors(cell.getRow(), cell.getCol());
			markedCount = getMarkedNeighborsCount(cell.getRow(), cell.getCol());
			nettleCount = cell.getNumber() - markedCount;
			possibleNettleSet = getPossibleNettleSet(unmarked, nettleCount);
			options = getLogicOptions(unmarked, possibleNettleSet);
			optionsList.add(options);
		}
		return optionsList;
	}
	
	private String getLogicOptions(ArrayList<Cell> unmarked
			, ArrayList<ArrayList<Cell>> possibleNettleSet) {
		String options = "(";
		for (int i = 0; i < possibleNettleSet.size(); i++) {
			if (i != 0) {
				options += " " + OR + " ";
			}
			options += "(";
			for (int j = 0; j < unmarked.size(); j++) {
				Cell cell = unmarked.get(j);
				if (j != 0) {
					options += " " + AND + " ";
				}
				if (!possibleNettleSet.get(i).contains(cell)) {
					options += NOT;
				}
				options += "N" + cell.getRow() + cell.getCol();
			}
			options += ")";
		}
		options += ")";
		return options;
	}
	
	private ArrayList<ArrayList<Cell>> getPossibleNettleSet(ArrayList<Cell> unmarked
			, int nettleCount) {
		// get a set of all subsets
		ArrayList<ArrayList<Cell>> possibleNettleSet = powerSet(unmarked);
		// only keep the subsets that matches our criteria
		removeExcessSets(possibleNettleSet, nettleCount);
		return possibleNettleSet;
	}
	
	private ArrayList<ArrayList<Cell>> powerSet(ArrayList<Cell> originalSet) {
		// get a set of all subsets
		ArrayList<ArrayList<Cell>> sets = new ArrayList<ArrayList<Cell>>();
        if (originalSet.isEmpty()) {
            sets.add(new ArrayList<Cell>());
            return sets;
        }
        ArrayList<Cell> list = new ArrayList<Cell>(originalSet);
        Cell head = list.get(0);
        ArrayList<Cell> rest = new ArrayList<Cell>(list.subList(1, list.size()));
        for (ArrayList<Cell> set : powerSet(rest)) {
        	ArrayList<Cell> newSet = new ArrayList<Cell>();
            newSet.add(head);
            newSet.addAll(set);
            sets.add(newSet);
            sets.add(set);
        }
        return sets;
    }
	
	private void removeExcessSets(ArrayList<ArrayList<Cell>> powerSet, int desireLength) {
		// remove all sets that do not pass the criteria
		ArrayList<ArrayList<Cell>> removeSet = new ArrayList<ArrayList<Cell>>();
		for (ArrayList<Cell> set : powerSet) {
			if (set.size() != desireLength) {
				removeSet.add(set);
			}
		}
		powerSet.removeAll(removeSet);
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
