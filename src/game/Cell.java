package game;

/**
 * The class that represents each cell in a nettle world
 *
 */
public class Cell {
	private final int UNMARKED = -2;
	private final int MARKED = -3;
	private final int NETTLE = -1; 
	private int row, col, number;
	
	public Cell(int row, int col, int number) {
		/**
		 * number
		 * -2 indicates covered cell
		 * -3 flagged cell
		 */
		this.row = row;
		this.col = col;
		this.number = number;
	}
	
	/**
	 * Get the row number of the cell
	 * @return the row of the cell
	 */
	public int getRow() {
		return this.row;
	}
	
	/**
	 * Get the column number of the cell
	 * @return the column of the cell
	 */
	public int getCol() {
		return this.col;
	}
	
	/**
	 * Get cell information for agent
	 * @return number inside the cell
	 */
	public int getNumber() {
		return this.number;
	}
	
	/**
	 * Assign new number to cell (use when opening or marking cell)
	 * @param number - number assign to the cell
	 */
	public void setNumber(int number) {
		this.number = number;
	}
	
	@Override
	public String toString() {
		// print the state of the cell
		String output;
		switch (this.number) {
			case UNMARKED:
				output = "#";
				break;
			case MARKED:
				output = "F";
				break;
			case NETTLE:
				output = "N";
				break;
			default:
				output = Integer.toString(this.number);
				break;
		}
		return output;
	}
	
}
