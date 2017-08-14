/**
 * A class to create the I-shaped piece.
 **/
public class IPiece extends TetrisPiece {

	/**
	 * Constructor for the class sets up inherited field filledSquares.
	 **/
	public IPiece() {
		pieceRotation = 0;
		// 3D boolean array to hold the pieceValues for this shape.
		boolean[][][] pieceValues = {
				{ { true, false, false, false }, { true, false, false, false }, { true, false, false, false },
						{ true, false, false, false } },
				{ { true, true, true, true }, { false, false, false, false }, { false, false, false, false },
						{ false, false, false, false } },
				{ { true, false, false, false }, { true, false, false, false }, { true, false, false, false },
						{ true, false, false, false } },
				{ { true, true, true, true }, { false, false, false, false }, { false, false, false, false },
						{ false, false, false, false } } };
		filledSquares = pieceValues;
	}
}