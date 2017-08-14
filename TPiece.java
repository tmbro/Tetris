/**
 * A class to create the T-shaped piece.
 **/
public class TPiece extends TetrisPiece {

	/**
	 * Constructor for the class sets up inherited field filledSquares.
	 **/
	public TPiece() {
		pieceRotation = 0;
		// 3D boolean array to hold the pieceValues for this shape.
		boolean[][][] pieceValues = {
				{ { false, true, false, false }, { true, true, true, false }, { false, false, false, false },
						{ false, false, false, false } },
				{ { true, false, false, false }, { true, true, false, false }, { true, false, false, false },
						{ false, false, false, false } },
				{ { true, true, true, false }, { false, true, false, false }, { false, false, false, false },
						{ false, false, false, false } },
				{ { false, true, false, false }, { true, true, false, false }, { false, true, false, false },
						{ false, false, false, false } } };
		filledSquares = pieceValues;
	}
}