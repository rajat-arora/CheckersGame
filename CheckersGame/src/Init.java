/**
 * runs main JPanel
 * @author	Rajat
 * @date	Tuesday February 25th 2014
 * @version	milestone 3
 */

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Init extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {

		/**
		 * Create main panel window
		 */

		JFrame window = new JFrame("Checkers Game");
		/**
		 * Sets Class file MainGui to Main Panel window
		 */
		MainGUI content = new MainGUI();
		window.setContentPane(content);
		/**
		 * Set's The Main Gui size
		 */
		window.setSize(1000, 700);

		/**
		 * Refrains user from resizing window
		 */
		window.setResizable(false);
		/**
		 * Sets location of Main Panel on user screen
		 */
		window.setLocation(100, 100);
		/**
		 * Refrains user from exiting program unless they press the exit button
		 */
		window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		/**
		 * Sets the window to visible
		 */
		window.setVisible(true);

	}

}
