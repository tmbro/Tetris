/**
 * A class to create the O-shaped piece.
 **/
public class OPiece extends TetrisPiece {

	/**
	 * Constructor for the class sets up inherited field filledSquares.
	 **/
	public OPiece() {
		pieceRotation = 0;
		// 3D boolean array to hold the pieceValues for this shape.
		boolean[][][] pieceValues = {
				{ { true, true, false, false }, { true, true, false, false }, { false, false, false, false },
						{ false, false, false, false } },
				{ { true, true, false, false }, { true, true, false, false }, { false, false, false, false },
						{ false, false, false, false } },
				{ { true, true, false, false }, { true, true, false, false }, { false, false, false, false },
						{ false, false, false, false } },
				{ { true, true, false, false }, { true, true, false, false }, { false, false, false, false },
						{ false, false, false, false } } };
		filledSquares = pieceValues;
	}
}