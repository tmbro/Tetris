import javax.swing.JFrame;

/**
 * Creates a JFrame for the GUIController and GUIView to be added to. Starts the
 * game.
 */
public class GUIApplication {

	/**
	 * Sets the width of the frame to 800 pixels
	 */
	public static int FRAME_WIDTH = 370;

	/**
	 * Sets the height of the frame to 1200 pixels
	 */
	public static int FRAME_HEIGHT = 850;

	/**
	 * Start the game!
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// create a new JFrame to hold the Tetris game
		JFrame tetrisFrame = new JFrame("Tetris");

		// set size of JFrame
		tetrisFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);

		// create a game and add it to the panel
		tetrisFrame.add(new GUIController());

		// exit normally on closing the window
		tetrisFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// show frame
		tetrisFrame.setVisible(true);
	}
}