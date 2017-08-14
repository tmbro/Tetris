/**
 * A class to create the L-shaped piece.
 **/
public class LPiece extends TetrisPiece {

	/**
	 * Constructor for the class sets up inherited field filledSquares.
	 **/
	public LPiece() {
		pieceRotation = 0;
		// 3D boolean array to hold the pieceValues for this shape.
		boolean[][][] pieceValues = {
				{ { true, false, false, false }, { true, false, false, false }, { true, true, false, false },
						{ false, false, false, false } },
				{ { true, true, true, false }, { true, false, false, false }, { false, false, false, false },
						{ false, false, false, false } },
				{ { true, true, false, false }, { false, true, false, false }, { false, true, false, false },
						{ false, false, false, false } },
				{ { false, false, true, false }, { true, true, true, false }, { false, false, false, false },
						{ false, false, false, false } } };
		filledSquares = pieceValues;
	}
}