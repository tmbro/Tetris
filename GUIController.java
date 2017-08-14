import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Controller for Tetris Game. Adds the score label to the North section of the
 * border layout and the GUIView to the Center section. Handles user input and
 * moves pieces accordingly.
 */
public class GUIController extends JPanel implements KeyListener {

	/**INSTANCE VARIABLES**/
	// default drop rate in milliseconds
	public final int DEFAULT_DROP_RATE = 1500;
	// creates an instance of TetrisGame called game
	private TetrisGame game;
	// creates an instance of GUIView called view
	private GUIView view;
	// creates a JLabel called linesLabel to display the num lines cleared
	private JLabel linesLabel;
	// creates a JLabel called tetrisesLabel to display num Tetrises cleared
	private JLabel tetrisesLabel;
	// creates a Timer variable called TetrisTimer
	private Timer TetrisTimer = new Timer();

	/**
	 * Constructor for GUIController
	 */
	public GUIController() {
		// use a BorderLayout
		super(new BorderLayout());
		setFocusable(true);
		// adds a key listener to the controller
		addKeyListener(this);
		// new instance of the TetrisGame
		game = new TetrisGame();
		// new instance of the GUIView
		view = new GUIView(game);
		// adds score panel to the frame
		createScore();
		// adds view panel to the frame
		createView();
		// sets up timer
		TetrisTimer.schedule(TetrisTask, DEFAULT_DROP_RATE, DEFAULT_DROP_RATE);
		// repaints the view
		view.repaint();
	}

	/**
	 * Timer for the game.
	 */
	private TimerTask TetrisTask = new TimerTask() {
		public void run() {
			game.attemptMove(game.DOWN);
			// repaints the view after every move
			view.repaint();
		}
	};

	/**
	 * Adds the view to the center of the border layout frame.
	 */
	private void createView() {
		// adds the GUIView to the GUIController frame
		add(view, BorderLayout.CENTER);
	}

	/**
	 * Calculates the score of numLines and numTetrises.
	 */
	private void createScore() {
		// to hold the score
		JPanel scorePanel = new JPanel();
		// to display the number of lines cleared
		linesLabel = new JLabel();
		// to display the number of tetrises cleared
		tetrisesLabel = new JLabel();
		// sets the text for the lines label
		linesLabel.setText("<html>Number of lines cleared: " + game.getNumLines() + "<br>");
		// sets the text for the tetrises label
		tetrisesLabel.setText("<html>Number of tetrises cleared: " + game.getNumTetrises() + "<br>");
		// adds the lines label to the north of the score panel
		scorePanel.add(linesLabel, BorderLayout.NORTH);
		// adds the tetrises label to the south of the score panel
		scorePanel.add(tetrisesLabel, BorderLayout.SOUTH);
		// adds the score panel to the north of the GUI
		add(scorePanel, BorderLayout.NORTH);
	}

	/**
	 * Moves the Tetris Piece when a key is released.
	 * 
	 * @param e
	 *            the key that is released
	 */
	public void keyReleased(KeyEvent e) {
		// "KeyCode" is the enum of the key that was pressed
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_Z) {
			// attempt to rotate clockwise
			game.attemptMove(game.CW);
		}

		else if (key == KeyEvent.VK_X) {
			// attempt to move counter-clockwise
			game.attemptMove(game.CCW);
		}

		else if (key == KeyEvent.VK_DOWN) {
			// attempt to move down
			game.attemptMove(game.DOWN);
		}

		else if (key == KeyEvent.VK_RIGHT) {
			// attempt to move right
			game.attemptMove(game.RIGHT);
		}

		else if (key == KeyEvent.VK_LEFT) {
			// attempt to move left
			game.attemptMove(game.LEFT);
		}
		// repaints the view after every move
		view.repaint();
		// updates the linesLabel with the score after every move
		linesLabel.setText("<html>Number of lines cleared: " + game.getNumLines() + "<br>");
		// updates the tetrisesLabel with the score after every move
		tetrisesLabel.setText("<html>Number of tetrises cleared: " + game.getNumTetrises() + "<br>");
	}

	/**
	 * Do something when a key is pressed
	 * 
	 * @param e
	 *            the key that is pressed
	 */
	public void keyPressed(KeyEvent e) {
		// not used
	}

	/**
	 * Do something when a key is pressed and released
	 * 
	 * @param e
	 *            the key that is pressed and released
	 */
	public void keyTyped(KeyEvent e) {
		// not used
	}
}