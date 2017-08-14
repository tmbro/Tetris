/**
 * This class to handles game logic and moves pieces according to user input.
 */
public class TetrisGame {

	/** INSTANCE VARIABLES **/
	public final int RIGHT = 3;
	public final int LEFT = 2;
	public final int DOWN = 1;
	public final int CW = 4;
	public final int CCW = 5;
	private int numLines = 0;
	private int numTetrises = 0;
	private TetrisBoard tetrisBoard;

	/**
	 * Constructor.
	 */
	public TetrisGame() {
	}

	/**
	 * Moves piece according to user input.
	 * 
	 * @param moveType
	 *            the type of move
	 */
	public void attemptMove(int moveType) {
		if (moveType == 1) {
			tetrisBoard.moveDown();
		} else if (moveType == 2) {
			tetrisBoard.moveLeft();
		} else if (moveType == 3) {
			tetrisBoard.moveRight();
		} else if (moveType == 4) {
			tetrisBoard.rotateClockwise();
		} else if (moveType == 5) {
			tetrisBoard.rotateCounterClockwise();
		}
		calculateScore();
	}

	/**
	 * Calculates the game score.
	 */
	public void calculateScore() {
		// temp var to store the number of lines formed
		int temporary = tetrisBoard.numberOfFormedLines();
		numLines += temporary;

		// checks if 4 lines were cleared at once
		if ((temporary % 4) == 0) {
			numTetrises += temporary / 4;
		}
	}

	/**
	 * Getter method for the number of cleared lines.
	 * 
	 * @return the number of cleared lines
	 */
	public int getNumLines() {
		return numLines;
	}

	/**
	 * Getter method for the number of cleared tetrises.
	 * 
	 * @return the number of cleared tetrises
	 */
	public int getNumTetrises() {
		return numTetrises;
	}

	/**
	 * Getter method for the Tetris board
	 * 
	 * @return the tetrisBoard
	 */
	public TetrisBoard getTetrisBoard() {
		tetrisBoard = new TetrisBoard();
		return tetrisBoard;
	}
}