package agent;

import java.util.ArrayList;

import aima.core.logic.propositional.parsing.ast.Sentence;
import aima.core.logic.propositional.inference.DPLLSatisfiable;
import aima.core.logic.propositional.parsing.PLParser;
import game.Cell;
import game.NettleSweeper;

public class DLSAgent extends BasicAgent {
	private Cell[][] currentWorld;
	private ArrayList<Cell> covered = new ArrayList<Cell>();
	private ArrayList<Cell> uncovered = new ArrayList<Cell>();
	private ArrayList<Cell> marked = new ArrayList<Cell>();
	private static DPLLSatisfiable dpll = new DPLLSatisfiable();


	public DLSAgent(NettleSweeper ns) {
		super(ns);
		covered = this.getCovered();
		uncovered = this.getUncovered();
		marked = this.getMarked();
		currentWorld = this.getCurrentWorld();
	}

	public void DLS() {
		String p = "N21";
		System.out.println("ProveNettle " + p);
		String KBU = "((N20 & ~N21 & ~N22) | (~N20 & N21 & ~N22) | (~N20 & ~N21 & N22)) "
				+ "& ((N20 & ~N21) | (~N20 & N21)) & ((N21 & ~N22) | (~N21 & N22))";
		String prove = KBU + " & ~" + p;
		boolean ans = displayDPLLSatisfiableStatus(prove);
		System.out.println("Does KBU entail " + p + "?, Test KBU & ~" + p);
		if (!ans) {// if false mark
			System.out.println("Yes, Nettle, Mark");
		} else {
			System.out.println("No");
		}
	}
	
	public static boolean displayDPLLSatisfiableStatus(String query) {
		PLParser parser = new PLParser();
		Sentence sent = (Sentence) parser.parse(query);
		if (dpll.dpllSatisfiable(sent)) {
//			System.out.println("Query is satisfiable");
			return true;
			} else {
//			System.out.println("Query is NOT satisfiable");
			return false;
			}

	}
}
