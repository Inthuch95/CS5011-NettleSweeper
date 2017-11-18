package agent;

import aima.core.logic.propositional.parsing.ast.Sentence;
import aima.core.logic.propositional.inference.DPLLSatisfiable;
import aima.core.logic.propositional.parsing.PLParser;
import game.NettleSweeper;

public class DLSAgent extends BasicAgent {
	private static DPLLSatisfiable dpll = new DPLLSatisfiable();

	public DLSAgent(NettleSweeper ns) {
		super(ns);
	}
	
	public void solveNettleWorld() {
		// uncover cell(0, 0) first
		openCell(0, 0);
		printWorld();
		DLS();
//		while (!covered.isEmpty()) {
//			worldChanged = false;
//			// attempt to use SPS
//			System.out.println("solving with SPS");
//			singlePointStrategy();
//			if (!worldChanged) {
//				System.out.println("solving with DLS");
//				DLS();
//			}
//			// if other strategies cannot make further changes to the world, resort to RGS
//			if (!worldChanged) {
//				System.out.println("resort to RGS");
//				randomGuessStrategy();
//			}
//			// print current status of the nettle world
//			printWorld();
//			// if the agent uncover a nettle then the game is over
//			if (gameOver) {
//				break;
//			}
//		}
//		printSummary();
	}

	private void DLS() {
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
	
	private static boolean displayDPLLSatisfiableStatus(String query) {
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
