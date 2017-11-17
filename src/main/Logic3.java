package main;

import java.util.ArrayList;

import agent.DLSAgent;
import game.Cell;
import game.NettleSweeper;
import game.Worlds;

public class Logic3 {

	public static void main(String[] args) {
		String difficulty = args[0];
		int worldNumber = Integer.parseInt(args[1]);
		int[][] world = getWorld(difficulty, worldNumber);
		// create game with selected nettle world
		NettleSweeper ns = new NettleSweeper(world);
		DLSAgent agent = new DLSAgent(ns);
		// use the logical agent to solve the nettle world
		solve(agent);
	}
	
	private static void solve(DLSAgent agent) {
		ArrayList<Cell> covered = agent.getCovered();
		System.out.println("Nettle World\n");
		// uncover cell(0, 0) first
		agent.openCell(0, 0);
		agent.printWorld();
		while (!covered.isEmpty()) {
			agent.setWorldChanged(false);
			// attempt to use SPS
			System.out.println("solving with SPS");
			agent.singlePointStrategy();
			if (!agent.getWorldChanged()) {
				System.out.println("solving with DLS");
				agent.DLStrategy();
			}
			// if other strategies cannot make further changes to the world, resort to RGS
			if (!agent.getWorldChanged()) {
				System.out.println("resort to RGS");
				agent.randomGuessStrategy();
			}
			// print current status of the nettle world
			agent.printWorld();
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
