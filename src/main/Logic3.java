package main;

import agent.LogicalAgent;
import game.NettleSweeper;
import game.Worlds;

public class Logic3 {

	public static void main(String[] args) {
		String difficulty = args[0];
		int worldNumber = Integer.parseInt(args[1]);
		int[][] world = getWorld(difficulty, worldNumber);
		// create game with selected nettle world
		NettleSweeper ns = new NettleSweeper(world);
		LogicalAgent agent = new LogicalAgent(ns);
		// use the logical agent to solve the nettle world
		solve(agent);
	}
	
	private static void solve(LogicalAgent agent) {
		// probe (0, 0) first
		agent.openCell(0, 0);
		agent.printWorld();
	}

	private static int[][] getWorld(String difficulty, int worldNumber) {
		// get the nettle world based on difficulty and world number
		Worlds w = new Worlds();
		int[][] world = null;
		if (difficulty.equals("easy")) {
			world = w.getEasyWorld(worldNumber);
		} else if (difficulty.equals("medium")) {
			world = w.getMediumWorld(worldNumber);
		} else {
			world = w.getHardWorld(worldNumber);
		}
		
		return world;
	}

}
