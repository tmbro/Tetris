/**
 * TetrisBoard represents the game model: a board on which Tetris is played.
 * This class maintains the grid (block matrix) and the current piece.
 **/
public class TetrisBoard {

	/** INSTANCE VARIABLES **/
	public final int NUM_COLS = 7;
	public final int NUM_ROWS = 15;
	private boolean[][] blockMatrix;
	private TetrisPiece currentPiece;
	private int[] currentPieceGridPosition;
	public int currentPieceCode;

	/**
	 * Constructor sets up the board.
	 **/
	public TetrisBoard() {
		// creates a new, empty board
		blockMatrix = new boolean[NUM_ROWS][NUM_COLS];
		// places piece in desired location
		initCurrentGridPosition();
		// add first piece to board
		addNewPiece();
	}

	/**
	 * Creates an int array of length two to keep track of the current piece's
	 * grid position (row, col).
	 **/
	private void initCurrentGridPosition() {
		currentPieceGridPosition = new int[2];
		currentPieceGridPosition[0] = 0;
		currentPieceGridPosition[1] = 3;
	}

	/**
	 * Lands piece when user can no longer move.
	 **/
	public void landPiece() {
		// loops through rows of the 4x4 block
		for (int i = 0; i < 4; i++) {
			// cycles through columns of the 4x4 block
			for (int j = 0; j < 4; j++) {
				// if the space holding the current piece is filled
				if (currentPiece.isFilled(currentPiece.getPieceRotation(), i, j) == true) {
					// add the current piece to the blockMatrix
					blockMatrix[currentPieceGridPosition[0] + i][currentPieceGridPosition[1] + j] = true;
				}
			}
		}
		// checks if line is full
		fullLine(currentPieceGridPosition[0]);
		addNewPiece();
	}

	/**
	 * Checks if moving left is valid. If valid, moves the current piece left.
	 * 
	 * @return true if valid move was performed, false if it was not
	 **/
	public boolean moveLeft() {
		// if space one block left to the current piece is empty
		if (validMove(currentPiece, currentPiece.getPieceRotation(), currentPieceGridPosition[0],
				currentPieceGridPosition[1] - 1) == true) {
			// move the piece one position to the left (subtracts one from col)
			currentPieceGridPosition[1] = currentPieceGridPosition[1] - 1;
			// piece was able to move
			return true;
		} else {
			// piece was not able to move
			return false;
		}
	}

	/**
	 * Checks if moving right is valid. If valid, moves the current piece right.
	 * 
	 * @return true if valid move was performed, false if it was not
	 **/
	public boolean moveRight() {
		// if space one block right to the current piece is empty
		if (validMove(currentPiece, currentPiece.getPieceRotation(), currentPieceGridPosition[0],
				currentPieceGridPosition[1] + 1) == true) {
			// move the piece to one position to the right (adds one to col)
			currentPieceGridPosition[1] = currentPieceGridPosition[1] + 1;
			// piece was able to move
			return true;
		} else {
			// piece was not able to move
			return false;
		}
	}

	/**
	 * Checks if moving down is valid. If valid, moves the current piece down.
	 * 
	 * @return true if valid move was performed, false if it was not
	 **/
	public boolean moveDown() {
		// if space one block beneath the current piece is empty
		if (validMove(currentPiece, currentPiece.getPieceRotation(), currentPieceGridPosition[0] + 1,
				currentPieceGridPosition[1]) == true) {
			// move the piece one position down (adds one to row)
			currentPieceGridPosition[0] = currentPieceGridPosition[0] + 1;
			// piece was able to move
			return true;
		} else {
			// call landPiece method to add the piece to the blockMatrix
			landPiece();
			// piece was not able to move
			return false;
		}
	}

	/**
	 * Checks if rotating clockwise is valid. If valid, rotate the current piece
	 * clockwise by 90 degrees.
	 * 
	 * @return true if valid move was performed, false if it was not
	 **/
	public boolean rotateClockwise() {
		// if piece can rotate one position clockwise
		if (validMove(currentPiece, (currentPiece.getPieceRotation() + 1) % 4, currentPieceGridPosition[0],
				currentPieceGridPosition[1])) {
			// rotate the shape
			currentPiece.rotateShapeClockwise();
			// piece was able to rotate
			return true;
		} else {
			// piece was not able to rotate
			return false;
		}
	}

	/**
	 * Checks if rotating clockwise is valid. If valid, rotate the current piece
	 * counter-clockwise by 90 degrees.
	 * 
	 * @return true if valid move was performed, false if it was not
	 **/
	public boolean rotateCounterClockwise() {

		int rotation;

		if (currentPiece.getPieceRotation() == 0) {
			rotation = currentPiece.getPieceRotation() + 3;
		} else {
			rotation = currentPiece.getPieceRotation() - 1;
		}

		// if piece can rotate one position counter clockwise
		if (validMove(currentPiece, rotation, currentPieceGridPosition[0], currentPieceGridPosition[1])) {
			// rotate the shape
			currentPiece.rotateShapeCounterClockwise();
			// piece was able to rotate
			return true;
		} else {
			// piece was not able to rotate
			return false;
		}
	}

	/**
	 * Checks if placing the piece at grid position (row, col) with the rotation
	 * rot (values can be 0, 90, 180, 270) would cause a collision (i.e., if
	 * there would be a block on an already-filled grid square).
	 * 
	 * @param piece
	 *            the current TetrisPiece
	 * @param rot
	 *            the rotation number
	 * @param gridRow
	 *            the row
	 * @param gridCol
	 *            the column
	 * @return true if there would be a collision
	 **/
	private boolean detectCollision(TetrisPiece piece, int rot, int gridRow, int gridCol) {
		// loop through rows of 4x4 block
		for (int i = 0; i < 4; i++) {
			// loop through columns of 4x4 block
			for (int j = 0; j < 4; j++) {
				// if location on piece is filled (true)
				if (piece.isFilled(rot, i, j) == true) {
					// if location in blockMatrix is true (isFilled)
					if (blockMatrix[gridRow + i][gridCol + j] == true) {
						// collision detected
						return true;
					}
				}
			}
		}
		// no collision detected
		return false;
	}

	/**
	 * Checks if placing the piece at grid position (row, col) with the rotation
	 * rot (values can be 0, 90, 180, 270) would cause an out of bounds
	 * condition (i.e., if there would be a block falling off the board).
	 * 
	 * @param piece
	 *            the current TetrisPiece
	 * @param rot
	 *            the rotation number
	 * @param gridRow
	 *            the row
	 * @param gridCol
	 *            the column
	 * @return true if there would be a bounding error
	 **/
	private boolean detectOutOfBounds(TetrisPiece piece, int rot, int gridRow, int gridCol) {
		// loop through rows of 4x4 block
		for (int i = 0; i < 4; i++) {
			// loop through columns of 4x4 block
			for (int j = 0; j < 4; j++) {
				// if location on piece is filled (true)
				if (piece.isFilled(rot, i, j) == true) {
					// if location is out of bounds on board
					if (gridRow + i >= NUM_ROWS || gridCol + j >= NUM_COLS || gridCol < 0) {
						// piece out of bounds detected
						return true;
					}
				}
			}
		}
		// piece out of bounds not detected
		return false;
	}

	/**
	 * Checks if placing the piece at grid position (row, col) with the rotation
	 * rot (values can be 0, 90, 180, 270) is a valid move.
	 * 
	 * @param piece
	 *            the current TetrisPiece
	 * @param rot
	 *            the rotation number
	 * @param gridRow
	 *            the row
	 * @param gridCol
	 *            the column
	 * @return true if no collision or bounding error
	 **/
	private boolean validMove(TetrisPiece piece, int rot, int gridRow, int gridCol) {
		// if there is a collision or a piece is out of bounds
		if (detectOutOfBounds(piece, rot, gridRow, gridCol) == true
				|| detectCollision(piece, rot, gridRow, gridCol) == true) {
			// move is not valid
			return false;
		} else {
			// move is valid
			return true;
		}
	}

	/**
	 * Adds a new random Tetris piece to the board at the specified start grid
	 * position.
	 **/
	public void addNewPiece() {

		int randomPieceValue = 1 + (int) (Math.random() * ((7 - 1) + 1));

		if (randomPieceValue == 1) {
			currentPieceCode = 1;
			currentPiece = new IPiece();
		} else if (randomPieceValue == 2) {
			currentPieceCode = 2;
			currentPiece = new JPiece();
		} else if (randomPieceValue == 3) {
			currentPieceCode = 3;
			currentPiece = new LPiece();
		} else if (randomPieceValue == 4) {
			currentPieceCode = 4;
			currentPiece = new OPiece();
		} else if (randomPieceValue == 5) {
			currentPieceCode = 5;
			currentPiece = new SPiece();
		} else if (randomPieceValue == 6) {
			currentPieceCode = 6;
			currentPiece = new TPiece();
		} else {
			currentPieceCode = 7;
			currentPiece = new ZPiece();
		}

		initCurrentGridPosition();
	}

	/**
	 * Detects and removes any lines formed.
	 * 
	 * @return the total number of formed lines found.
	 **/
	public int numberOfFormedLines() {

		int numLinesFormed = 0;

		// loop through rows on board
		for (int i = 0; i < NUM_ROWS; i++) {
			if (fullLine(i) == true) {
				removeLine(i);
				numLinesFormed++;
			}
		}

		return numLinesFormed;
	}

	/**
	 * Checks if a row has a full line.
	 * 
	 * @param row
	 *            the row to be checked
	 * @return true if full
	 **/
	private boolean fullLine(int row) {
		// loop through the number of columns
		for (int i = 0; i < NUM_COLS; i++) {
			// if the row has an empty space
			if (blockMatrix[row][i] == false) {
				// line is not full
				return false;
			}
		}
		// line is full
		return true;
	}

	/**
	 * Removes the selected line according to row passed in through parameters.
	 * Shifts all values for rows at a lower index to be one row higher. Makes
	 * every value in Row 0 equal to false.
	 * 
	 * @param row
	 *            the row to be removed
	 **/
	private void removeLine(int row) {
		// cycles through rows between the deleted row and the top of the board
		for (int r = row; r > 0; r--) {
			// cycles through the columns
			for (int c = 0; c < NUM_COLS; c++) {
				// move row down to empty row
				blockMatrix[r][c] = blockMatrix[r - 1][c];
			}

		}
		// cycles through the columns
		for (int i = 0; i < NUM_COLS; i++) {
			// sets the position to false (empty)
			blockMatrix[0][i] = false;
		}
	}

	/**
	 * Getter method for the blockMatrix.
	 * 
	 * @return the block matrix
	 **/
	public boolean[][] getBlockMatrix() {
		return blockMatrix;
	}

	/**
	 * Getter method for the current Tetris piece.
	 * 
	 * @return the current Tetris piece
	 **/
	public TetrisPiece getCurrentPiece() {
		return currentPiece;
	}

	/**
	 * Getter method for the current piece's grid position.
	 * 
	 * @return the piece's current grid position
	 **/
	public int[] getCurrentPieceGridPosition() {
		return currentPieceGridPosition;
	}

	/**
	 * Getter method for the number of columns.
	 * 
	 * @return the number of columns in the block matrix
	 **/
	public int getNumCols() {
		return NUM_COLS;
	}

	/**
	 * Getter method for the number of rows.
	 * 
	 * @return the number of rows in the block matrix
	 **/
	public int getNumRows() {
		return NUM_COLS;
	}
}