/**
 * A class to create the S-shaped piece.
 **/
public class SPiece extends TetrisPiece {

	/**
	 * Constructor for the class sets up inherited field filledSquares.
	 **/
	public SPiece() {
		pieceRotation = 0;
		// 3D boolean array to hold the pieceValues for this shape.
		boolean[][][] pieceValues = {
				{ { false, true, true, false }, { true, true, false, false }, { false, false, false, false },
						{ false, false, false, false } },
				{ { true, false, false, false }, { true, true, false, false }, { false, true, false, false },
						{ false, false, false, false } },
				{ { false, true, true, false }, { true, true, false, false }, { false, false, false, false },
						{ false, false, false, false } },
				{ { true, false, false, false }, { true, true, false, false }, { false, true, false, false },
						{ false, false, false, false } } };
		filledSquares = pieceValues;
	}
}