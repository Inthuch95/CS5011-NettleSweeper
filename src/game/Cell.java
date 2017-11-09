package game;

/**
 * Represents each cell in a nettle world
 *
 */
public class Cell {
	private int row, col, number;
	
	public Cell(int row, int col, int number) {
		/**
		 * number
		 * -2 indicates unprobed cell
		 * -3 flagged cell
		 */
		this.row = row;
		this.col = col;
		this.number = number;
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
		String output;
		if (this.number == -2) {
			output = "?";
		} else if (this.number == -3) {
			output = "F";
		} else {
			output = Integer.toString(this.number);
		}
		
		return output;
	}
}
