package agent;

import java.util.ArrayList;

import game.Cell;
import game.NettleSweeper;

public class DLSAgent extends BasicAgent {
	private Cell[][] currentWorld;
	private ArrayList<Cell> covered = new ArrayList<Cell>();
	private ArrayList<Cell> uncovered = new ArrayList<Cell>();
	private ArrayList<Cell> marked = new ArrayList<Cell>();
	
	public DLSAgent (NettleSweeper ns) {
		super(ns);
		covered = this.getCovered();
		uncovered = this.getUncovered();
		marked = this.getMarked();
		currentWorld = this.getCurrentWorld();
	}
	
	public void DLStrategy () {
		
	}
}
