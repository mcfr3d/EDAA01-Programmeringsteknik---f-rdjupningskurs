
public class SudukoSolver {
	private int[][] game;

	/**
	 * Constructor, created an emtpy matrix with 9x9 elements
	 * 
	 */
	public SudukoSolver() {
		game = new int[9][9];

	}

	/**
	 * Solves the game. Also checks if the numbers apply to the rules.
	 * 
	 * @return returns false if it does not apply to the rules. Else returns
	 *         true and solves the game.
	 */
	public boolean solve() {
		boolean completeCheck = true;
		for (int bRow = 0; bRow < 9; bRow++) {
			for (int bCol = 0; bCol < 9; bCol++) {
				int value = game[bRow][bCol];
				if (value != 0) {
					completeCheck = completeCheck(bRow, bCol, value);
				}
			}
		}
		if (!completeCheck) {
			return false;
		}
		return solve(0, 0);
	}

	/**
	 * Solves the sudoku. The empty tiles will receive a number between 1 and 9.
	 * Also checks the tiles with preset number, so that these apply to the
	 * rules
	 * 
	 * 
	 * @param row
	 *            is the row of the matrix
	 * @param col
	 *            the column of the matrix
	 * @return false if no solution is available, else true.
	 */
	private boolean solve(int row, int col) {

		if (row > 8) {

			return true;
		}

		if (getValue(row, col) == 0) {
			boolean filled = false;
			for (int i = 1; i <= 9; i++) {
				boolean possible = completeCheck(row, col, i);
				if (possible) {
					setValue(row, col, i);
					if (col == 8) {
						filled = solve(row + 1, 0);
					} else {
						filled = solve(row, col + 1);
					}
					if (filled) {
						return true;
					}
				}
			}
		} else {

			boolean valid = completeCheck(row, col, getValue(row, col));
			if (valid) {
				if (col == 8) {
					return solve(row + 1, 0);
				} else {
					return solve(row, col + 1);
				}

			}
		}
		setValue(row, col, 0);
		return false;

	}

	/**
	 * Sets the sudoku board to the board given
	 * 
	 * @param board
	 *            the sudoku board to be solved
	 */
	public void setBoard(int[][] board) {
		game = board;
	}

	/**
	 * Returns the value of the postion row, col in the matrix
	 * 
	 * @param row
	 *            is the row of the matrix
	 * @param col
	 *            is the column of the matix
	 * @return the value of the specific position
	 */
	private int getValue(int row, int col) {

		return game[row][col];
	}

	/**
	 * Puts a value in the specific postion into the matrix.
	 * 
	 * @param row
	 *            is the row of the matrix
	 * @param col
	 *            is the column of the matix
	 * @param value
	 *            the value to be inserted
	 */
	private void setValue(int row, int col, int value) {
		game[row][col] = value;
	}

	/**
	 * Checks horizontally if the position in matrix apply to the rules of the
	 * game
	 * 
	 * @param row
	 *            which row to be checked
	 * @param col
	 *            which column to be checked
	 * @param value
	 *            is the value at game[row][col]
	 * @return false if the value does not apply to rules, otherwise true
	 */
	private boolean horisontalCheck(int row, int col, int value) {

		for (int i = 0; i < game[row].length; i++) {
			if (i != col) {
				if (value == getValue(row, i)) {
					return false;
				}

			}

		}
		return true;
	}

	/**
	 * Checks vertically if the position in matrix apply to the rules of the
	 * game
	 * 
	 * @param row
	 *            which row to be checked
	 * @param col
	 *            which column to be checked
	 * @param value
	 *            is the value at game[row][col]
	 * @return false if the value does not apply to rules, otherwise true
	 */
	private boolean verticalCheck(int row, int col, int value) {

		for (int i = 0; i < game.length; i++) {
			if (i != row) {
				if (value == getValue(i, col)) {
					return false;
				}

			}

		}
		return true;
	}

	/**
	 * Checks the 3x3 part of the sudoku game board, if is applies to the rules
	 * of the game
	 * 
	 * @param row
	 *            which row to be checked
	 * @param col
	 *            which column to be checked
	 * @param value
	 *            is the value at game[row][col]
	 * @return false if the value does not apply to rules, otherwise true
	 */
	private boolean boxCheck(int row, int col, int value) {
		int rowStepsfrom0 = row % 3;
		int colStepsfrom0 = col % 3;
		int boxStartRow = row - rowStepsfrom0;
		int boxStartCol = col - colStepsfrom0;
		for (int i = 0; i < 3; i++) {
			int rownr = boxStartRow + i;
			for (int j = 0; j < 3; j++) {
				int colnr = boxStartCol + j;
				if (row != rownr && col != colnr) {
					if (value == getValue(rownr, colnr)) {
						return false;
					}
				}
			}
		}

		return true;
	}

	/**
	 * Combined check of the rules.
	 * 
	 * @param row
	 *            which row to be checked
	 * @param col
	 *            which column to be checked
	 * @param value
	 *            is the value at game[row][col]
	 * @return false if the value does not apply to rules, otherwise true
	 */
	private boolean completeCheck(int row, int col, int value) {
		return horisontalCheck(row, col, value) && verticalCheck(row, col, value) && boxCheck(row, col, value);
	}

	/**
	 * Construct a string of the game board
	 * 
	 * @return returns a string representation of the matrix
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {

				sb.append(getValue(i, j));
				if (j % 3 == 2) {
					sb.append("|");
				}
				if (i % 3 != 2 || j == 8) {
					sb.append(" ");
				} else {
					sb.append("_");
				}
			}
			sb.append("\n");

		}
		return sb.toString();
	}

	public int[][] returnBoard() {
		return game;
	}
}
