/**
 * A class to create the Z-shaped piece.
 **/
public class ZPiece extends TetrisPiece {

	/**
	 * Constructor for the class sets up inherited field filledSquares.
	 **/
	public ZPiece() {
		pieceRotation = 0;
		// 3D boolean array to hold the pieceValues for this shape.
		boolean[][][] pieceValues = {
				{ { true, true, false, false }, { false, true, true, false }, { false, false, false, false },
						{ false, false, false, false } },
				{ { false, true, false, false }, { true, true, false, false }, { true, false, false, false },
						{ false, false, false, false } },
				{ { true, true, false, false }, { false, true, true, false }, { false, false, false, false },
						{ false, false, false, false } },
				{ { false, true, false, false }, { true, true, false, false }, { true, false, false, false },
						{ false, false, false, false } } };
		filledSquares = pieceValues;
	}
}