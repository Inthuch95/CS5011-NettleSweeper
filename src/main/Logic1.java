package main;

import agent.BasicAgent;
import game.NettleSweeper;

public class Logic1 {

	public static void main(String[] args) {
		String difficulty = args[0];
		int worldNumber = Integer.parseInt(args[1]);
		// create game with selected nettle world
		NettleSweeper ns = new NettleSweeper(difficulty, worldNumber);
		BasicAgent agent = new BasicAgent(ns);
		// use the logical agent to solve the nettle world
		System.out.println("Nettle World: " + difficulty + " " + worldNumber + "\n");
		agent.solveNettleWorld();
	}
}
