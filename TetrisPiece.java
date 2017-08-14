/**
 * TetrisPiece is an abstract class that manipulates the orientation of the
 * falling piece according to the user’s keystrokes. This class represents a
 * piece made of 4 TetrisBlocks. It maintains 4 rotations (0 degrees, 90
 * degrees, 180 degrees and 270 degrees).
 **/
public abstract class TetrisPiece {

	// 3D array maintaining which squares are filled. First dimension
	// is rotation (index 0: 0 degrees, index 1: 90 degrees, index 2: 180
	// degrees, index 3: 270 degrees), second and third dimensions create 4x4
	// grid with true/false values indicating filled/empty squares
	protected boolean[][][] filledSquares;
	protected int pieceRotation;

	/**
	 * Constructor.
	 **/
	public TetrisPiece() {
	}

	/**
	 * Rotates piece clockwise.
	 **/
	public void rotateShapeClockwise() {
		pieceRotation = (pieceRotation + 1) % 4;
	}

	/**
	 * Rotates piece counter-clockwise.
	 **/
	public void rotateShapeCounterClockwise() {
		if (pieceRotation == 0) {
			pieceRotation = 3;
		} else {
			pieceRotation = (pieceRotation - 1) % 4;
		}
	}

	/**
	 * Gets the piece rotation.
	 * 
	 * @return the piece rotation
	 **/
	public int getPieceRotation() {
		return pieceRotation;
	}

	/**
	 * Checks if there is a TetrisBlock at the (row, col) position for the
	 * rotation rot, where rot is 0, 90, 180 or 270 degrees.
	 * 
	 * @param rot
	 *            the TetrisPiece rotation value (should be 0, 90, 180, or 270)
	 * @param row
	 *            the row within the TetrisPiece 4x4 grid
	 * @param col
	 *            the col within the TetrisPiece 4x4 grid
	 * @return true if there is a block in the position for that rotation, false
	 *         otherwise
	 **/
	public boolean isFilled(int rot, int row, int col) {
		// if there is a block
		if (filledSquares[rot][row][col] == true) {
			return true;
		} else {
			return false;
		}
	}
}