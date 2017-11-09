package game;

/**
 * The class contains the nettle worlds of all difficulty
 *
 */
public class Worlds {
	// collection of worlds in 2d array format
	private int[][] easy1 = new int [][] {
		{0, 0, 0, 2, -1},
		{0, 0, 0, 2,-1},
		{1, 2, 1, 2, 1},
		{-1, 3, -1, 2, 0},
		{1, 3, -1, 2, 0},
		};

	private int[][] easy2 = new int [][] {
		{0, 0, 1, -1, -1},
		{0, 0, 1, 2, 2},
		{1, 2, 1, 1, 0},
		{-1, 2, -1, 2, 1},
		{1, 2, 2, -1, 1},
		};
			
	private int[][] easy3 = new int [][] {
		{0, 0, 1, 2, 2},
		{0, 1, 3,-1,-1},
		{0, 2, -1, -1, 3},
		{0, 2, -1, 3, 1},
		{0, 1, 1, 1, 0},
		}; 
	
	private int[][] easy4= new int [][] {
		{0, 0, 0, 0, 0},
		{1, 2, 1, 1, 0},
		{-1, 2, -1, 1, 0},
		{3, 5, 3, 2, 0},
		{-1, -1, -1, 1, 0},
		};
			
	private int[][] easy5= new int [][] {
		{0, 0, 0, 1, -1},
		{1, 1, 1, 1, 1},
		{2, -1, 2, 0, 0},
		{3, -1, 3, 1, 0},
	    {-1, 3, -1, 1, 0},
		};
		
	private int[][] medium1 = new int [][] {
		{0, 0, 0, 1, 1, 1, 0, 1, -1},
		{0, 0, 1, 2, -1, 1, 0, 1, 1},
		{0, 0, 2, -1, 3, 1, 0, 1, 1},
		{0, 0, 2, -1, 2, 0, 0, 1, -1},
		{1, 1, 1, 1, 1, 0, 0, 2, 2},
		{-1, 2, 1, 1, 0, 0, 0, 1, -1},
		{1, 2, -1, 1, 0, 0, 0, 1, 1},
		{1, 2, 1, 1, 0, 0, 1, 1, 1},
		{-1, 1, 0, 0, 0, 0, 1, -1, 1},
		};

	private int[][] medium2 = new int [][] {
		{0, 0, 0, 0, 0, 0, 1, 1, 1},
		{0, 0, 0, 0, 1, 1, 3, -1, 2},
		{0, 0, 0, 0, 2, -1, 6, -1, 4},
		{0, 0, 0,0, 2, -1, -1, -1, -1},
		{0, 0, 0, 0, 1, 3, -1, 5, 3},
		{0, 0, 0, 0, 0, 1, 2, -1, 1},
		{0, 0, 0, 0, 0, 0, 1, 1, 1},
		{0, 0, 0, 0, 1, 1, 1, 0, 0},
		{0, 0, 0, 0, 1, -1, 1, 0, 0},
		};

	private int[][] medium3= new int [][] {
		{0, 0, 1, -1, 1, 1, -1, 2, 1},
		{1, 1, 1, 1, 1, 1, 2, -1, 1},
		{-1, 1, 0, 0, 0, 1, 2, 2, 1},
		{2, 2, 1,1, 1, 2, -1, 1, 0},
		{1, -1, 1, 1, -1,2, 1, 1, 0},
		{1, 1, 1, 1, 1, 1, 0, 1, 1},
		{0, 0, 0, 0, 0, 1, 1, 2, -1},
		{0, 0, 0, 0, 0, 1, -1, 3, 2},
		{0, 0, 0, 0, 0, 1, 1, 2, -1},
		};

	private int[][] medium4= new int [][] {
		{0, 0, 0, 0, 0, 0, 1, -1, 1},
		{0, 1, 2, 2, 1, 0, 1, 1, 1},
		{0, 1, -1, -1, 1, 0, 0, 0, 0},
		{0, 1, 2,2, 1, 0, 0, 0, 0},
		{0, 0, 1, 1, 1, 0, 1, 1, 1},
		{0, 0, 1, -1, 2,1, 2, -1, 2},
		{1, 1, 2, 1, 2, -1, 4, 4, -1},
		{1,-1, 1, 0, 1, 2, -1, -1, 2},
		{1, 1, 1, 0, 0, 1, 2, 2, 1},
		}; 

	private	int[][] medium5= new int [][] {
		{0, 0, 0, 0, 0, 0, 0, 0, 0},
		{1, 1, 1, 0, 0, 0, 0, 0, 0},
		{1, -1, 1, 0, 0, 1, 1, 1, 0},
		{1, 1, 1,0, 1, 2, -1, 1, 0},
		{0, 0, 0, 0, 1, -1, 3, 3, 1},
		{1, 2, 2, 1, 1,2, -1, 3, -1},
		{1, -1, -1, 2, 1, 1,2, -1, 2},
		{2,3, 4, -1, 1, 0, 1, 1, 1},
		{1,-1, 2, 1, 1, 0, 0, 0, 0},
		};
	
	private int[][] hard1 = new int [][] {
		{0, 0, 0, 0, 2, -1, 4, -1, 2, -1},
		{2, 2, 1, 0, 2, -1, -1, 4, 3, 2},
		{-1, -1, 1, 0, 1, 3, -1, 3, -1, 1},
		{-1, 3, 1, 0, 0, 1, 2, 3, 2, 1},
		{2, 2, 2, 1, 1, 0, 1, -1, 1, 0},
		{1, -1, 3, -1, 4, 3, 3, 2, 1, 0},
		{1, 1, 3, -1, -1, -1, -1, 1, 0, 0},
		{0, 1, 2, 4, -1, 4, 2, 1, 0, 0},
		{0, 1, -1, 3, 2, 1, 0, 0, 0, 0},
		{0, 1, 2, -1, 1, 0, 0, 0, 0, 0},
	};	 

	private int[][] hard2 = new int [][] {
		{0, 0, 2, -1, 2, 0, 0, 0, 1, 1},
		{0, 1, 3, -1, 2, 0, 0, 0, 2, -1},
		{0, 2, -1, 3, 1, 0, 0, 0, 2, -1},
		{2, 4, -1, 2, 0, 0, 0, 0, 1, 1},
		{-1, -1, 3, 2, 1, 0, 0, 0, 0, 0},
		{-1, 4, 3, -1, 3, 2, 2, 2, 1, 1},
		{-1, 3, 2, -1, 3, -1, -1, 3, -1,1},
		{-1, 2, 1, 2, 3, 4, -1, 3, 1, 1},
		{1, 1, 1, 3, -1, 3, 1, 1, 0, 0},
		{0, 0, 1, -1, -1, 2, 0, 0, 0, 0},
	}; 
						
						
						
	private int[][] hard3= new int [][] {
		{0, 0, 0, 0, 1, 3, -1, 2, 0, 0},
		{1, 1, 1, 0, 1, -1, -1, 3, 2, 2},
		{1, -1, 1, 0, 1, 2, 2, 2, -1, -1},
		{1, 1, 1, 0, 0, 1, 1, 2, 3, -1},
		{1, 1, 0, 0, 0, 1, -1, 1, 2, 2},
		{-1, 2, 0, 0, 0, 1, 1, 1, 1, -1},
		{-1, 4, 1, 0, 0, 0, 0, 1, 2,2},
		{-1, -1, 3, 2, 2, 2, 1, 2, -1, 1},
		{-1, -1, 3, -1, -1, 2, -1, 2, 1, 1},
		{-1, 3, 2, 2, 2, 2, 1, 1, 0, 0},
	}; 

				
	private int[][] hard4= new int [][] {
		{0, 0, 0, 2, -1, -1, -1, 1, 0, 0},
		{0, 0, 0, 3, -1, 6, 3, 1, 1, 1},
		{0, 0, 0, 2, -1, -1, 1, 0, 1, -1},
		{1, 1, 0, 1, 3, 3, 3, 2, 4, 3},
		{-1, 1, 0, 0, 1, -1, 2, -1, -1, -1},
		{1, 1, 0, 0, 1, 1, 2, 2, 4, -1},
		{0, 0, 0, 1, 1, 1, 1, 1,2, 1},
		{1, 2, 2, 2, -1, 1, 2, -1, 3, 1},
		{1, -1, -1, 2, 1, 1, 2, -1, -1, 2},
		{1, 2, 2, 1, 0, 0, 1, 3, -1, 2},
		};  
		
		
	private int[][] hard5= new int [][] {
		{0, 1, 1, 1, 1, -1, -1, 2, 1, 0},
		{0, 1, -1, 2, 2, 2, 3, -1, 1, 0},
		{0, 2, 4, -1, 2, 0, 1, 1, 1, 0},
		{0, 1, -1, -1, 3, 1, 1, 2, 1, 1},
		{0, 1, 4, -1, 3, 2, -1, 3, -1, 1},
		{0, 0, 2, -1, 3, 3, -1, 3, 1, 1},
		{0, 1, 3, 3, 3, -1, 2, 1, 0, 0},
		{1, 2, -1, -1, 3, 2, 1, 0, 0, 0},
		{-1, 2, 2, 3, -1, 3, 2, 1, 1, 1},
		{1, 1, 0, 1, 2, -1, -1, 1, 1, -1},
	};
	
	/**
	 * Retrieve easy nettle world
	 * @param worldNumber - indicates the chosen world
	 * @return nettle world corresponding with worldNumber
	 */
	public int[][] getEasyWorld(int worldNumber) {
		switch (worldNumber) {
			case 1:
				return this.easy1;
			case 2:
				return this.easy2;
			case 3:
				return this.easy3;
			case 4:
				return this.easy4;
			case 5:
				return this.easy5;
			default:
				return this.easy1;	
		}
	}
	
	/**
	 * Retrieve medium nettle world
	 * @param worldNumber - indicates the chosen world
	 * @return nettle world corresponding with worldNumber
	 */
	public int[][] getMediumWorld(int worldNumber) {
		switch (worldNumber) {
			case 1:
				return this.medium1;
			case 2:
				return this.medium2;
			case 3:
				return this.medium3;
			case 4:
				return this.medium4;
			case 5:
				return this.medium5;
			default:
				return this.medium1;	
		}
	}
	
	/**
	 * Retrieve hard nettle world
	 * @param worldNumber - indicates the chosen world
	 * @return nettle world corresponding with worldNumber
	 */
	public int[][] getHardWorld(int worldNumber) {
		switch (worldNumber) {
			case 1:
				return this.hard1;
			case 2:
				return this.hard2;
			case 3:
				return this.hard3;
			case 4:
				return this.hard4;
			case 5:
				return this.hard5;
			default:
				return this.hard1;	
		}
	}
}
