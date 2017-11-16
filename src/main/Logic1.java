package main;

import java.util.ArrayList;

import agent.BasicAgent;
import game.Cell;
import game.NettleSweeper;
import game.Worlds;

public class Logic1 {

	public static void main(String[] args) {
		String difficulty = args[0];
		int worldNumber = Integer.parseInt(args[1]);
		int[][] world = getWorld(difficulty, worldNumber);
		// create game with selected nettle world
		NettleSweeper ns = new NettleSweeper(world);
		BasicAgent agent = new BasicAgent(ns);
		// use the logical agent to solve the nettle world
		solve(agent);
	}
	
	private static void solve(BasicAgent agent) {
		ArrayList<Cell> covered = agent.getCovered();
		ArrayList<Cell> marked = agent.getMarked();
		int totalNettle = agent.getTotalNettle();
		System.out.println("Nettle World\n");
		// uncover cell(0, 0) first
		agent.openCell(0, 0);
		agent.printWorld();
		while (!covered.isEmpty() && marked.size() < totalNettle) {
			agent.setWorldChanged(false);
			// attempt to use SPS
			System.out.println("Solving with SPS");
			agent.singlePointStrategy();
			// if SPS cannot make further changes to the world, resort to RGS
			if (!agent.getWorldChanged()) {
				System.out.println("resort to RGS");
				agent.randomGuessStrategy();
			}
			// print current status of the nettle world
			agent.printWorld();
			// check if all nettles are marked
			if (marked.size() == totalNettle) {
				System.out.println("Found all nettles");
				// uncover the rest of the cells and end the game
				agent.openAllCells();
				break;
			}
			// if the agent uncover a nettle then the game is over
			if (agent.getGameOver()) {
				break;
			}
		}
		agent.printSummary();
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
