package main;

import agent.EESAgent;
import game.NettleSweeper;

public class Logic2 {
	public static void main(String[] args) {
		String difficulty = args[0];
		int worldNumber = Integer.parseInt(args[1]);
		// create game with selected nettle world
		NettleSweeper ns = new NettleSweeper(difficulty, worldNumber);
		EESAgent agent = new EESAgent(ns);
		// use the logical agent to solve the nettle world
		System.out.println("Nettle World\n");
		agent.solveNettleWorld();
	}
}
