import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * View class that paints the graphics objects to the screen.
 */
public class GUIView extends JPanel {

	private TetrisGame game;
	private TetrisBoard board;

	/**
	 * Constructor initializes the game.
	 * 
	 * @param game
	 *            TetrisGame variable
	 */
	public GUIView(TetrisGame game) {
		this.game = game;
		board = game.getTetrisBoard();
	}

	/**
	 * Paint method. Draws the pieces on the board.
	 * 
	 * @param g
	 *            graphics object to draw on
	 */
	public void paint(Graphics g) {
		// paints the board outline
		paintBoardOutline(g, computeBlockSize());
		// sets piecePosition to equal the currentPieceGridPosition
		int[] piecePosition = board.getCurrentPieceGridPosition();
		// sets currentPiece to equal the current piece on the board
		TetrisPiece currentPiece = board.getCurrentPiece();

		// loop through the board and paint block where appropriate
		for (int i = 0; i < board.getBlockMatrix().length; i++) {
			// for each board column
			for (int j = 0; j < board.getBlockMatrix()[0].length; j++) {
				// if occupied
				if (board.getBlockMatrix()[i][j] == true) {
					// paint a block
					paintBlock(g, i, j, computeBlockSize());
				} else {
					// checks positions in the 4x4 matrix
					if (i >= piecePosition[0] && i <= piecePosition[0] + 3) {
						if (j >= piecePosition[1] && j <= piecePosition[1] + 3) {
							if (currentPiece.isFilled(currentPiece.getPieceRotation(), i - piecePosition[0],
									j - piecePosition[1])) {
								// paint a block
								paintColoredBlock(g, i, j, computeBlockSize());
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Paints board outline. Outline is a black rectangle that dynamically
	 * changes according to the block size and number of rows & columns.
	 * 
	 * @param g
	 *            graphics object to draw on
	 * @param blockSize
	 *            size of the block
	 */
	private void paintBoardOutline(Graphics g, int blockSize) {
		// sets board outline color
		g.setColor(Color.BLACK);
		// draws the outline
		g.drawRect(0, 0, board.NUM_COLS * computeBlockSize(), board.NUM_ROWS * computeBlockSize());
	}

	/**
	 * Paints the blocks. Sets the blocks to grey once they become a part of the
	 * board (added to the block matrix).
	 * 
	 * @param g
	 *            graphics object to draw on
	 * @param row
	 *            the current piece's row (row where block is painted)
	 * @param col
	 *            the current piece's column (column where block is painted)
	 * @param blockSize
	 *            the size of the block
	 */
	private void paintBlock(Graphics g, int row, int col, int blockSize) {
		// grey
		g.setColor(new Color(114, 109, 109));
		// paints a rectangle to represent the block
		g.fillRect(col * blockSize, row * blockSize, blockSize, blockSize);
		// sets the color of the block outline to black
		g.setColor(Color.black);
		// paints a rectangle around the perimeter of the block (block outline)
		g.drawRect(col * blockSize, row * blockSize, blockSize, blockSize);
	}

	/**
	 * Paints the blocks. Sets the blocks to different colors according to the
	 * different shapes. Once blocks are added to the board, paintBlock is
	 * called, which changes block color to grey.
	 * 
	 * @param g
	 *            graphics object to draw on
	 * @param row
	 *            the current piece's row (row where block is painted)
	 * @param col
	 *            the current piece's column (column where block is painted)
	 * @param blockSize
	 *            the size of the block
	 */
	private void paintColoredBlock(Graphics g, int row, int col, int blockSize) {
		// if IPiece
		if (board.currentPieceCode == 1) {
			// set color to PINK
			g.setColor(new Color(255, 82, 152));
			// paint square
			g.fillRect(col * blockSize, row * blockSize, blockSize, blockSize);
			// if JPiece
		} else if (board.currentPieceCode == 2) {
			// set color to BLUE
			g.setColor(new Color(16, 147, 246));
			// paint square
			g.fillRect(col * blockSize, row * blockSize, blockSize, blockSize);
			// if LPiece
		} else if (board.currentPieceCode == 3) {
			// set color to GREEN
			g.setColor(new Color(35, 215, 53));
			// paint square
			g.fillRect(col * blockSize, row * blockSize, blockSize, blockSize);
			// if OPiece
		} else if (board.currentPieceCode == 4) {
			// set color to ORANGE
			g.setColor(new Color(247, 180, 22));
			// paint square
			g.fillRect(col * blockSize, row * blockSize, blockSize, blockSize);
			// if SPiece
		} else if (board.currentPieceCode == 5) {
			// set color to RUST
			g.setColor(new Color(247, 70, 22));
			// paint square
			g.fillRect(col * blockSize, row * blockSize, blockSize, blockSize);
			// if TPiece
		} else if (board.currentPieceCode == 6) {
			// set color to YELLOW
			g.setColor(new Color(255, 228, 46));
			// paint square
			g.fillRect(col * blockSize, row * blockSize, blockSize, blockSize);
			// if ZPiece
		} else {
			// set color to PURPLE
			g.setColor(new Color(197, 116, 252));
			// paint square
			g.fillRect(col * blockSize, row * blockSize, blockSize, blockSize);
		}
		// set outline color to black
		g.setColor(Color.black);
		// paint outline around square
		g.drawRect(col * blockSize, row * blockSize, blockSize, blockSize);
	}

	/**
	 * Returns the block size.
	 * 
	 * @return int size
	 */
	private int computeBlockSize() {
		return 50;
	}
}