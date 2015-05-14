/**
 * Main GUI Class
 * displays the checker board and all buttons pertaining to operation of the game 
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class MainGUI extends Init implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * These arrays are the grid that compose the board. The XCoord Array holds
	 * the letters A-H that span the possible movement spaces from left to right
	 * of the game window.
	 */
	private String[] XCoord = { "A", "B", "C", "D", "E", "F", "G", "H" };

	private Boolean inPath = false;
	/**
	 * The YCoord Array holds the numbers 1-8 that span the possible movement
	 * spaces from top to bottom of the game window
	 */
	private Integer[] YCoord = { 1, 2, 3, 4, 5, 6, 7, 8 };

	/**
	 * Contains Coordinates for every tile and is used to set it up in a grid
	 * layout to resemble a checkers game board
	 */
	private String[][] coordinates = new String[8][8];
	// unmodified
	private String[][] coordinates2 = new String[8][8];

	private String Coord = "";
	private String futureCoord = "";

	/**
	 * Places a click-able JButton on every one of the movement spaces on the
	 * game board
	 */
	public JButton[][] tiles = new JButton[8][8];

	public String prevCommand;

	/**
	 * Creates a JPanel which is the interface that will be used to interact
	 * with the user. JPanel allows the user to give a specific set of commands
	 * using the ActionListener class on each button that is placed on the
	 * JPanel.
	 */
	public JPanel jp1 = new JPanel();
	/**
	 * Another JPanel, refer to jp1 explanation.
	 */
	public JPanel jp2 = new JPanel();
	/**
	 * Another JPanel, refer to jp1 explanation.
	 */
	public JPanel jp3 = new JPanel();

	/**
	 * Used as a counter to check how many times a user has clicked on the
	 * board.
	 */
	public int counter2 = 0;

	/**
	 * Checks if the game has started. Initial value is false.
	 */
	private boolean gameStart = false;
	/**
	 * private array that will contain all the checker pieces that are currently
	 * on the board
	 */
	private Checker Checkers[];
	/**
	 * private constant for maximum amount of black checker pieces allowed on
	 * the board
	 */
	private int maxBlack;
	/**
	 * private constant for maximum amount of grey checker pieces allowed on the
	 * board
	 */
	private int maxGrey;

	/**
	 * Used as a counter to check how many times a user has clicked on the
	 * board.
	 */
	private int counter3 = 0;

	/**
	 * Holds Maximum number of King Black Pieces
	 */
	private int maxKingBlack;

	/**
	 * Checks if Yellow piece has been selected
	 */
	private boolean isYellow = false;

	/**
	 * Holds Maximum number of King White Pieces
	 */
	private int maxKingGrey;
	/**
	 * private constant for the total amount of pieces that are allowed on the
	 * board
	 */
	private int total;
	/**
	 * private counter value for game pieces on the board starting at 0
	 */
	private int counter = 0;
	/**
	 * Checks if the game is in setup mode
	 */
	private boolean setup = false;

	/**
	 * Check if user Selected piece on board
	 */
	private boolean selected = false;
	/**
	 * Check if user Selected piece on board
	 */

	private boolean selected2 = false;
	/**
	 * Check for the location piece is pressed
	 */

	private int locationPressed;
	/**
	 * Holds instance of Checker
	 */

	private Checker[] Checkers2;

	/**
	 * Button initialization for game menu
	 */
	JButton newGameButton = new JButton("New Game");
	JButton loadGameButton = new JButton("Load Game");
	JButton quitGameButton = new JButton("Quit");

	JTextField scoreWindowBlack;
	JTextField scoreWindowGrey;
	JLabel Jlabel;
	JLabel Jlabel1;
	JLabel Jlabel2;

	/**
	 * Creates the main window and the checker board (composed of buttons) for
	 * the game
	 */
	public MainGUI() {
		/**
		 * Intilizes the coordinates on the board
		 */
		for (int i = 0; i < XCoord.length; i++) {
			for (int j = 0; j < YCoord.length; j++) {
				coordinates[i][j] = XCoord[i] + "-"
						+ Integer.toString(YCoord[j]);
				coordinates2[i][j] = XCoord[i] + "-"
						+ Integer.toString(YCoord[j]);
			}
		}

		for (int i = XCoord.length - 1; i > -1; i--) {
			for (int j = 0; j < YCoord.length; j++) {
				tiles[i][j] = new JButton(coordinates[j][i]);

			}
		}

		/**
		 * This will set the Layout of the Main JPanel to BoxLayout
		 */
		this.setLayout(new BorderLayout());
		/**
		 * These will set the Color and Border of the Main JPanel
		 */
		this.setBorder(BorderFactory
				.createLineBorder(new Color(130, 50, 40), 3));
		this.setBackground(Color.CYAN);

		/**
		 * This will set the Layout of the JPanel1 to BoxLayout
		 */
		jp1.setLayout(new GridLayout(0, 8));

		/**
		 * sets the color of the pieces in a staggered fashion to ensure
		 * checkering of the colors
		 */
		Color bColor = new Color(0xED1C24); // red
		Color b2Color = new Color(0xEFE4B0);
		for (int i = 0; i < XCoord.length; i++) {
			for (int j = 0; j < YCoord.length; j++) {

				if (i % 2 == 0) {
					if (j % 2 == 0) {

						tiles[i][j].setBackground(b2Color);
					} else {
						tiles[i][j].setBackground(bColor);
					}
				} else {
					if (j % 2 == 0) {
						tiles[i][j].setBackground(bColor);
					} else {
						tiles[i][j].setBackground(b2Color);
					}

				}
				// /***
				tiles[i][j].addActionListener(this);
				tiles[i][j].setEnabled(false);
				tiles[i][j].setBorder(BorderFactory
						.createLineBorder(Color.white));
				jp1.add(tiles[i][j]);

			}
		}

		/**
		 * Creates a JPanel of the Preferred Size and the GridLayout setup
		 */
		jp1.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), " Checkers Board"));
		this.add(jp1);
		jp2.setPreferredSize(new Dimension(200, 100));
		jp2.setBorder(BorderFactory.createLineBorder(Color.blue, 1));
		jp2.setLayout(new GridLayout(0, 3));

		/**
		 * Adds the buttons to setup a new game, to load a game, and to Quit a
		 * game
		 */
		newGameButton.addActionListener(this);
		Color aColor = new Color(0xc3c3c3);
		newGameButton.setBackground(aColor);
		loadGameButton.addActionListener(this);
		loadGameButton.setBackground(aColor);
		quitGameButton.addActionListener(this);
		quitGameButton.setBackground(aColor);

		/**
		 * Adds the game setup buttons to the JPanel being used
		 */
		// jp2.setPreferredSize(new Dimension(100, 200));
		// jp2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(Color.BLACK,
		// Color.black), " Checkers Game"));
		jp2.add(newGameButton);
		jp2.add(loadGameButton);
		jp2.add(quitGameButton);
		this.add(jp2, BorderLayout.SOUTH);

		scoreWindowBlack = new JTextField();
		scoreWindowGrey = new JTextField();
		scoreWindowBlack.setEditable(false);
		scoreWindowGrey.setEditable(false);
		scoreWindowBlack.setForeground(Color.red);
		scoreWindowGrey.setForeground(Color.red);

		Font labelFont = scoreWindowBlack.getFont();

		// Set the label's font size to the newly determined size.
		scoreWindowBlack
				.setFont(new Font(labelFont.getName(), Font.PLAIN, 100));

		labelFont = scoreWindowGrey.getFont();

		scoreWindowGrey.setFont(new Font(labelFont.getName(), Font.PLAIN, 100));

		// scoreWindowBlack.setEnabled(false);
		// scoreWindowGrey.setEnabled(false);

		scoreWindowBlack.setText("0");
		scoreWindowGrey.setText("0");

		Jlabel = new JLabel();
		Jlabel1 = new JLabel();
		Jlabel2 = new JLabel();
		Jlabel.setText("Your Score:");
		Jlabel1.setText("Black:");
		Jlabel2.setText("White:");

		jp3.setLayout(new BoxLayout(jp3, BoxLayout.PAGE_AXIS));

		jp3.setPreferredSize(new Dimension(120, 100));
		jp3.setBorder(BorderFactory.createLineBorder(Color.red, 1));

		Jlabel1.setPreferredSize(new Dimension(20, 20));
		Jlabel2.setPreferredSize(new Dimension(20, 20));

		jp3.add(Jlabel);
		jp3.add(Jlabel1);

		jp3.add(scoreWindowBlack);
		jp3.add(Jlabel2);
		jp3.add(scoreWindowGrey);
		this.add(jp3, BorderLayout.EAST);
	}

	// places 12 by 12 pieces on board

	/**
	 * when the standard game option is selected (when prompted by New Game)
	 * sets up a standard 12 piece versus 12 piece checkers game
	 */
	public void standardGame() {

		int counter = 0; // 0 -> 23 , 29 - 63
		Checkers = new Checker[24];
		int counter2 = 0;

		/**
		 * This for loop sets grey pieces on tiles from 0 to 23 and then black
		 * on tiles 29 to 63 Does this by using an algorithm that checks what (x
		 * - node), if even, then it searches the y's for even predecessor using
		 * counter and determines if it's black or white depending on if counter
		 * < 24 or counter > 40 If the (x-node) is odd the same thing above
		 * applies however it searches the y's for odd predecessor then does
		 * comparison When it determines black or white, it then sets the icon
		 */
		for (int i = 0; i < XCoord.length; i++) {
			for (int j = 0; j < YCoord.length; j++) {

				if (j % 2 != 0) {
					if (i % 2 == 0) {
						if (counter < 24) {
							tiles[i][j].setIcon(new ImageIcon(getClass()
									.getResource("grey_piece.gif")));
							Checkers[counter2] = new Checker("Grey",
									tiles[i][j].getText(), false);
							counter2 = counter2 + 1;

						} else if (counter > 40) {
							tiles[i][j].setIcon(new ImageIcon(getClass()
									.getResource("black_piece.gif")));
							Checkers[counter2] = new Checker("Black",
									tiles[i][j].getText(), false);
							counter2 = counter2 + 1;

						}
					}
				} else {
					if (i % 2 != 0) {
						if (counter < 24) {
							tiles[i][j].setIcon(new ImageIcon(getClass()
									.getResource("grey_piece.gif")));
							Checkers[counter2] = new Checker("Grey",
									tiles[i][j].getText(), false);
							counter2 = counter2 + 1;

						} else if (counter > 39) {
							tiles[i][j].setIcon(new ImageIcon(getClass()
									.getResource("black_piece.gif")));
							Checkers[counter2] = new Checker("Black",
									tiles[i][j].getText(), false);
							counter2 = counter2 + 1;
						}
					}
				}
				counter = counter + 1;

				tiles[i][j].setEnabled(true);
				jp1.add(tiles[i][j]);
			}
		}
		setup = false;
	}

	/**
	 * When the custom game option is selected, (when prompted by the Custom
	 * Game button) this function takes the custom setup that was previously
	 * received from user input and stored as an array (tiles), and sets the
	 * respective buttons to be enabled.
	 */
	public void customGame() {
		for (int i = 0; i < XCoord.length; i++) {
			for (int j = 0; j < YCoord.length; j++) {
				tiles[i][j].setEnabled(true);
			}
		}
		setup = true;
	}

	/**
	 * This function writes the enabled pieces that are currently on the board
	 * into a text file which is then saved and can be loaded at a later time.
	 * 
	 * @throws FileNotFoundException
	 */
	public void saveGame() throws FileNotFoundException {
		PrintWriter writer = new PrintWriter("lol.txt");

		// iterate through array, write each element of array to file
		for (int i = 0; i < Checkers.length; i++) {
			// write array element to file
			if (Checkers != null) {
				writer.print(Checkers[i].getColor() + ","
						+ Checkers[i].getCoord() + "," + Checkers[i].isKing());
				// write separator between array elements to file
				writer.print("\n");
			}
		}

		// done writing, close writer
		writer.close();
	}

	/**
	 * Checks if a saved file exists, and if there is no directory associated
	 * with it.
	 * 
	 * @return boolean True if file exists else false
	 * @throws FileNotFoundException
	 */
	public boolean checkSave() throws FileNotFoundException {
		File f = new File("lol.txt");
		if (f.exists() && !f.isDirectory()) {
			return true;
		}
		return false;
	}

	/**
	 * Looks at all the checker pieces and sets each button to have no icon
	 */
	public void clearBoard() {
		for (int i = 0; i < XCoord.length; i++) {
			for (int j = 0; j < YCoord.length; j++) {
				tiles[i][j].setIcon(null);
				tiles[i][j].repaint();
			}
		}
		this.repaint();
	}

	/**
	 * When user chooses custom setup and selects the number of black and grey
	 * pieces to put on the board, this function allows it to place each checker
	 * on the board. First the user pushes the black pieces onto the board by
	 * clicking the appropriate button. Each valid click reduces the maxBlack
	 * counter by 1. Once all the black pieces are placed (maxBlack counter is
	 * 0), the grey pieces follow the same process until the remaining pieces
	 * from the counter (total) is 0.
	 * 
	 * @param location
	 */
	public void placeChecker(String location) {
		for (int i = 0; i < XCoord.length; i++) {
			for (int j = 0; j < YCoord.length; j++) {
				// if user clicked on correct location then execute
				if (location == tiles[i][j].getText()) {
					// if users have placed all pieces ask user if they want to
					// keep configuration
					// if no, clear board
					// other wise set pieces on board counter to 0 and do
					// nothing
					if (counter == total) {
						int n = JOptionPane.showConfirmDialog(this,
								"Would you like keep the configuration?",
								"Keep Configuration?",
								JOptionPane.YES_NO_OPTION);
						if (n == 0) {
							setup = false;
							counter = 0;

						} else {
							setup = false;
							clearBoard();
						}
					}

					// if the user hasn't places the max amount of black pieces
					// on the board
					// we check if it's a legal placement the place it on the
					// board
					if (maxBlack - 1 >= counter && setup == true) {
						Checkers[counter] = new Checker("Black",
								tiles[i][j].getText(), false);
						if (Checkers[counter].illegalPlacement(
								Checkers[counter], Checkers, coordinates) != true
								&& Checkers[counter].Overlap(Checkers[counter],
										Checkers, counter) != true) {
							counter = counter + 1;
							tiles[i][j].setIcon(new ImageIcon(getClass()
									.getResource("black_piece.gif")));
						} else {
							Checkers[counter] = null;
						}
						// if the user hasn't places the max amount of grey
						// pieces on the board but has placed some black pieces
						// we check if it's a legal placement the place it on
						// the board
					} else if (maxBlack <= counter && setup == true
							&& maxGrey + maxBlack > counter) {

						Checkers[counter] = new Checker("Grey",
								tiles[i][j].getText(), false);
						if (Checkers[counter].illegalPlacement(
								Checkers[counter], Checkers, coordinates) != true
								&& Checkers[counter].Overlap(Checkers[counter],
										Checkers, counter) != true) {
							tiles[i][j].setIcon(new ImageIcon(getClass()
									.getResource("grey_piece.gif")));
							counter = counter + 1;

						}
					}

					// if the user hasn't placed any King Black pieces but has
					// placed some amount of grey pieces and some black pieces
					// we check if it's a legal placement the place it on the
					// board

					if (maxGrey + maxBlack <= counter && setup == true
							&& maxGrey + maxBlack + maxKingBlack > counter
							&& maxKingBlack != 0) {

						Checkers[counter] = new Checker("Black",
								tiles[i][j].getText(), true);
						if (Checkers[counter].illegalPlacement(
								Checkers[counter], Checkers, coordinates) != true
								&& Checkers[counter].Overlap(Checkers[counter],
										Checkers, counter) != true) {
							tiles[i][j].setIcon(new ImageIcon(getClass()
									.getResource("black_pieceking.gif")));
							counter = counter + 1;

						}
					}
					// if the user hasn't placed any King Grey Black pieces but
					// has placed some amount of grey pieces and some black
					// pieces and some King Black pieces
					// we check if it's a legal placement the place it on the
					// board

					if (maxGrey + maxBlack + maxKingBlack <= counter
							&& setup == true
							&& maxGrey + maxBlack + maxKingBlack + maxKingBlack > counter
							&& maxKingGrey != 0) {

						Checkers[counter] = new Checker("Grey",
								tiles[i][j].getText(), true);
						if (Checkers[counter].illegalPlacement(
								Checkers[counter], Checkers, coordinates) != true
								&& Checkers[counter].Overlap(Checkers[counter],
										Checkers, counter) != true) {
							tiles[i][j].setIcon(new ImageIcon(getClass()
									.getResource("white_piece1king1.gif")));
							counter = counter + 1;

							// if illegal
							// we do nothing and erase the current illegal
							// location clicked on from memory

						}

						else {
							Checkers[counter] = null;
						}
					}
				}
			}
		}
	}

	/**
	 * Opens a saved file and reads each line, parses it into a checker array,
	 * where each line is a new checker. Then places these checkers onto the
	 * board.
	 * 
	 * @throws FileNotFoundException
	 */
	@SuppressWarnings("resource")
	public void loadGame() throws FileNotFoundException {
		File file = new File("lol.txt");
		newGameButton.setEnabled(false);

		int temp = 0;
		Scanner scanner = new Scanner(file);

		while (scanner.hasNextLine()) {
			scanner.nextLine();
			temp++;
		}
		// System.out.println(temp);
		Checkers = new Checker[temp];
		scanner = new Scanner(file);
		temp = 0;

		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if (temp < 24) {

				String a[] = line.split(",");
				a[0] = a[0].trim();
				a[1] = a[1].trim();

				Checkers[temp] = new Checker(a[0], a[1],
						Boolean.parseBoolean(a[2]));
			}

			temp++;
		}
		scanner.close();
		clearBoard();
		setGame();
	}

	/**
	 * When the Standard Game option is selected, this function sets the pieces
	 * on the board as per the requirements of a standard checkers game.
	 */
	public void setGame() {

		for (int i = 0; i < XCoord.length; i++) {
			for (int j = 0; j < YCoord.length; j++) {
				for (int r = 0; r < Checkers.length; r++) {

					if (Checkers[r].getCoord().equals(tiles[i][j].getText())) {

						if (Checkers[r].getColor().equals("Black")) {
							if (Checkers[r].isKing() != true) {
								tiles[i][j].setIcon(new ImageIcon(getClass()
										.getResource("black_piece.gif")));
							} else {
								tiles[i][j].setIcon(new ImageIcon(getClass()
										.getResource("black_pieceking.gif")));
							}
						}

						if (Checkers[r].getColor().equals("Grey")) {
							if (Checkers[r].isKing() != true) {
								tiles[i][j].setIcon(new ImageIcon(getClass()
										.getResource("grey_piece.gif")));
							} else {
								tiles[i][j].setIcon(new ImageIcon(getClass()
										.getResource("white_piece1king1.gif")));
							}
						}

					}
				}
				tiles[i][j].repaint();
				tiles[i][j].setEnabled(true);
			}
		}
		this.repaint();
	}

	/**
	 * 
	 * This method removes a checker piece from the board. The method searches
	 * for the coordinate of the selected checker piece that the user want to
	 * move using a for loop and sets the icon of that piece to null.
	 * 
	 * @param location
	 *            the location where the checker piece is being moved to
	 * 
	 */
	// this method removes piece from board
	// sets the icon of current checker to null
	public void removepiece(String location) {
		for (int i = 0; i < XCoord.length; i++) {
			for (int j = 0; j < YCoord.length; j++) {

				if (tiles[i][j].getText().matches(location)) {
					tiles[i][j].setIcon(null);
				}

			}
		}
	}

	/**
	 * This method adds a piece to the board during the game to the location of
	 * the tile. It uses for loops to see if the piece is a black or white
	 * piece. Then for each of the black and white checks, another for loop
	 * checks if the piece is a king or not. Then a Checker piece is added
	 * depending on which for loop was executed.
	 * 
	 * @param location
	 *            the location where the checker piece is being moved to
	 * 
	 */
	// this method adds a piece to the board during custom game to location of
	// tile.
	public void addpiece(String location) {
		for (int i = 0; i < XCoord.length; i++) {
			for (int j = 0; j < YCoord.length; j++) {

				if (tiles[i][j].getText().matches(location)) {
					for (int k = 0; k < Checkers.length; k++) {
						if (Checkers[k].getCoord().equals(location)) {
							Checkers[k].setCoord(location);
							if (Checkers[k].getColor().trim().equals("Black")) {
								if (Checkers[k].isKing()) {
									tiles[i][j].setIcon(new ImageIcon(
											getClass().getResource(
													"black_pieceking.gif")));
								} else {
									tiles[i][j].setIcon(new ImageIcon(
											getClass().getResource(
													"black_piece.gif")));
								}
							} else {
								if (Checkers[k].isKing()) {
									tiles[i][j].setIcon(new ImageIcon(
											getClass().getResource(
													"white_piece1king1.gif")));
								} else {
									tiles[i][j].setIcon(new ImageIcon(
											getClass().getResource(
													"grey_piece.gif")));
								}

							}
						}
					}
				}
			}
		}
	}

	/**
	 * Stores the location of the checker pressed in the Checkers array
	 */
	// stores location of checker pressed in Checkers array
	public int h = 0;

	/**
	 * This method highlights the selected piece. It uses a for loop to find the
	 * coordinates of the piece, then sets the color of the coodinate's border
	 * to yellow.
	 * 
	 * @param Location
	 *            the location where the checker piece is being moved to
	 */
	// this method highlights selected piece
	public void highlightTile(String Location) {
		for (int i = 0; i < XCoord.length; i++) {
			for (int j = 0; j < YCoord.length; j++) {
				if (Location.matches(coordinates2[j][i])) {
					tiles[i][j].setBorder(BorderFactory.createLineBorder(
							Color.YELLOW, 3));
				}
			}
		}
	}

	/**
	 * This method unhighlights the selected piece. It uses a for loop to find
	 * the coordinates of the piece, then sets the color of the coodinate's
	 * border to white.
	 * 
	 * 
	 * @param Location
	 *            the location where the checker piece is being moved to
	 */
	// / this method un-highlights selected piece
	public void unhighlightTile(String Location) {
		for (int i = 0; i < XCoord.length; i++) {
			for (int j = 0; j < YCoord.length; j++) {
				if (Location.matches(coordinates2[j][i])) {
					tiles[i][j].setBorder(BorderFactory
							.createLineBorder(Color.WHITE));
				}
			}
		}
	}

	boolean var1 = false;

	/**
	 * This implements minMax Algorithm This algorithm is explained in
	 * Documentation
	 * 
	 * @param Checkers
	 * @return True when complete
	 */

	public boolean minMax(Checker Checkers[]) {

		// temp counters
		int counter = 0;
		int counter2 = 0;

		// Stores Black Checkers
		Checker[] black;

		// Store Grey Checkers
		Checker[] grey;

		// split checkers into Black and white array
		// By first getting size of Black and White Array
		// Then creating the arrays in memory
		for (int i = 0; i < Checkers.length; i++) {
			if (Checkers[i].getColor().matches("Black")) {
				counter = counter + 1;
			} else if (Checkers[i].getColor().matches("Grey")) {
				counter2 = counter2 + 1;
			}

		}
		black = new Checker[counter];
		grey = new Checker[counter2];
		counter = 0;
		counter2 = 0;
		for (int i = 0; i < Checkers.length; i++) {
			if (Checkers[i].getColor().matches("Black")) {
				black[counter] = Checkers[i];
				counter++;
			} else if (Checkers[i].getColor().matches("Grey")) {
				grey[counter2] = Checkers[i];
				counter2++;
			}
		}

		// holds temporary moves for each node
		String[] temp = null;
		String[] temp2 = null;

		// Will hold the root of each tree
		ArrayList<Node> branch1 = new ArrayList<Node>();
		// Used for data manipulation on tree's
		ArrayList<Node> branchTemp = new ArrayList<Node>();

		// create new nodes by checking for all possible moves and storing into
		// list.
		for (int i = 0; i < grey.length; i++) {
			temp = null;
			temp = grey[i].possibleMoves(grey[i], Checkers, coordinates2);
			temp2 = grey[i].possibleMovesHop(grey[i], Checkers, coordinates2);

			for (int j = 0; j < temp.length; j++) {
				if (temp[j] != null && temp[j] != "") {
					branch1.add(new Node(grey[i].getCoord(),
							grey[i].getColor(), temp[j], grey[i].isKing(),
							Checkers));
				}
			}

			for (int j = 0; j < temp2.length; j++) {
				if (temp2[j] != null && temp2[j] != "") {
					branch1.add(new Node(grey[i].getCoord(),
							grey[i].getColor(), temp2[j], grey[i].isKing(),
							Checkers));
				}
			}
		}
		

		// iterate through each tree
		Iterator<Node> it = branch1.iterator();
		counter = 0;

		// iterate through every tree check if legal, if so, then say it's a
		// legal move and store into temp list
		while (it.hasNext()) {
			Node tempNode = it.next();
			tempNode.legalMove(tempNode.getCoord(), coordinates, coordinates2);
			if (tempNode.legalMove(tempNode.getFutureCoord(), coordinates,
					coordinates2)) {
				branchTemp.add(tempNode);
				   //System.out.println(branchTemp.size());
					//System.out.println(branchTemp.get(counter) + "ss");
					counter++;
			}

		}
		// clear branch 1
		branch1.clear();
		System.out.println(branchTemp.size());

		// make branch1 equal branch Temp
		branch1 = branchTemp;

		// Stores Next Brach of tree
		ArrayList<Node> branch2 = new ArrayList<Node>();

		// Iterate through each tree branch

		it = branch1.iterator();
		
		while (it.hasNext()) {

			Node branchone = it.next();
			counter = 0;
			counter2 = 0;

			// For every Branch get the Checker configuration
			// then count how many black and Grey Pieces on the Board
			// And put into Black and Grey Array
			Checker[] Check = branchone.getCoordinates();
			for (int i = 0; i < Check.length; i++) {
				if (Check[i].getColor().matches("Black")) {
					counter = counter + 1;
				} else if (Check[i].getColor().matches("Grey")) {
					counter2 = counter2 + 1;
				}

			}

			black = new Checker[counter];
			grey = new Checker[counter2];
			counter = 0;
			counter2 = 0;
			for (int i = 0; i < Check.length; i++) {
				if (Check[i].getColor().matches("Black")) {
					black[counter] = Check[i];
					counter++;
				} else if (Check[i].getColor().matches("Grey")) {
					grey[counter2] = Check[i];
					counter2++;
				}
			}

			temp = null;
			temp2 = null;

			// used for data manipulation
			ArrayList<Node> branchTemp2 = new ArrayList<Node>();
			// used to store possible moves
			ArrayList<Node> branchTemp21 = new ArrayList<Node>();

			// store possible moves for each node into list
			for (int i = 0; i < black.length; i++) {

				temp = black[i].possibleMoves(black[i],
						branchone.getCoordinates(), coordinates2);
				temp2 = grey[i].possibleMoves(grey[i], Checkers, coordinates2);

				for (int j = 0; j < temp.length; j++) {
					if (temp[j] != null && temp[j] != "") {
						branchTemp21.add(new Node(black[i].getCoord(), black[i]
								.getColor(), temp[j], black[i].isKing(),
								branchone.getCoordinates()));

					}
				}

				for (int j = 0; j < temp2.length; j++) {
					if (temp2[j] != null && temp2[j] != "") {
						branchTemp21.add(new Node(black[i].getCoord(), black[i]
								.getColor(), temp2[j], black[i].isKing(),
								branchone.getCoordinates()));

					}
				}
			}

			// for each list if the move is legal, then store it into branch
			// Temp2.

			it = branchTemp21.iterator();
			System.out.println(branchTemp21.size());

			while (it.hasNext()) {
				Node tempNode = it.next();
				tempNode.legalMove(tempNode.getCoord(), coordinates,
						coordinates2);
				if (tempNode.legalMove(tempNode.getFutureCoord(), coordinates,
						coordinates2)) {
					branchTemp2.add(tempNode);
				}

			}
			

			// Add branchTemp2 to branch2
			it = branchTemp2.iterator();
			while (it.hasNext()) {
				branch2.add(it.next());
			}

		}
		

		// Calc the MaxWeightt
		it = branch2.iterator();
		ArrayList<Integer> weight = new ArrayList<Integer>();
		counter = 0;
		counter2 = 0;

		while (it.hasNext()) {
			// System.out.print(it.next().getCoord());
			Node lol = it.next();
			Checker[] l = lol.getCoordinates();
			for (int i = 0; i < l.length; i++) {
				if (l[i].getColor().matches("Black")) {
					counter = counter + 1;
				} else if (l[i].getColor().matches("Grey")) {
					counter2 = counter2 + 1;
				}

			}
			weight.add(counter2 - counter);

		}

		// Store MaxWeight
		int MaxWeight = maxIndex(weight);
    
		// Transfers up Tree
		it = branch1.iterator();
		counter = 0;
		counter2 = 0;
		while (it.hasNext()) {
			if (it.next().getFutureCoord()
					.matches(branch2.get(MaxWeight).getCoord())) {
				counter++;
			}

		}

		// Get MaxWeight
		Coord = branch1.get(counter).getCoord();
		futureCoord = branch1.get(counter).getFutureCoord();

		return var1 = true;

	}

	public static int maxIndex(ArrayList<Integer> list) {
		return list.indexOf(Collections.max(list));
	}

	public void gameHandler(String command) {
		// Get the location of the checker clicked on
		locationPressed = (Checkers[0].isSelected(command, Checkers));

		// Check if a checker has been selected
		// if so we make selected true, inc counter by 1,
		// store the location of the checker pressed on tile in
		// prevCommand
		// and use h to store where in the checker array we pressed the
		// checker
		if (locationPressed != -1 && selected == false) {
			selected = true;
			counter2 = counter2 + 1;
			prevCommand = command;
			h = locationPressed;

		}

		// If we have selected a tile we highlight that tile and
		// increase counter by 1
		if (selected == true) {
			highlightTile(prevCommand);
			counter2 = counter2 + 1;
		}

		// execute if user has selected the location where checker
		// should move on board
		if (counter2 == 3) {
			// set the current checker clicked on to it's new location
			// where it should move
			Checkers[h].setCoord(command);
			// This variable checks if there is a checker in the path of
			// movement
			// if so store into this variable
			String[] aqa = Checkers[h].CheckerInPath(Checkers[h], Checkers,
					coordinates2, prevCommand);

			// if the previous variable initialized is not null then we
			// have something in the path
			if (aqa[0] != null || aqa[1] != null) {
				inPath = true;
			} else {
				inPath = false;
			}

			// Checks if the tile that user has clicked on is a hop

			boolean hop = Checkers[h].hop(Checkers[h], Checkers, coordinates2,
					prevCommand);

			// this stores location of checker in path
			int l = -1;
			// this stores value of location of checker in path
			String[] aqa2 = null;
			// if something is in it's path and the user wants to jump a
			// piece execute
			if (hop == true && inPath == true) {
				// Find the location of the checkers in the path (both
				// right and left)
				aqa2 = Checkers[h].CheckerInPath2(Checkers[h], Checkers,
						coordinates2, command, prevCommand);
				// Figure out which checker is the correct checker out
				// of both right and left
				// By figuring out what direction the checker is heading
				for (int i = 0; i < aqa2.length; i++) {
					for (int j = 0; j < aqa.length; j++)
						if (aqa2[i] != null) {
							if (aqa[j] != null) {
								if (aqa[j].matches(aqa2[i])) {
									l = i;
								}
							}
						}
				}
			}

			// stores if piece we are hopping over is not the same color
			// as the current checker
			Boolean match = false;

			// checks if piece we are hopping over is not the same color
			// as the current checker
			// if true set match to true
			for (int i = 0; i < Checkers.length; i++) {
				if (l != -1 && aqa != null) {
					if (aqa2[l].matches(Checkers[i].getCoord())) {
						if (Checkers[i].getColor().matches(
								Checkers[h].getColor())) {
							match = true;
						}
					}
				}
			}
			// if no overlap, conflict of colors while hopping , and the
			// user is selecting a red tile, execute
			if (!Checkers[h].Overlap(Checkers[h], Checkers, h)
					&& !Checkers[h].illegalPlacement(Checkers[h], Checkers,
							coordinates) && !match) {

				// if the user is not hopping over anything and clicks
				// the correct square execute
				if (Checkers[h].illegalMove(Checkers[h], Checkers,
						coordinates2, prevCommand) && l == -1) {
					// Check if the current piece has gotten to the
					// other side, if so, set piece to king.
					if (Checkers[h].kingMe(Checkers[h], coordinates2)) {
						Checkers[h].setKing(true);
					}

					// move piece, un-select the tile and change
					// selected to false, set counter 0
					selected = false;
					removepiece(prevCommand);
					unhighlightTile(prevCommand);
					addpiece(command);
					this.repaint();
					counter2 = 0;
					// if the user is hopping over anything and clicks
					// the correct square execute
				} else if (Checkers[h].hop(Checkers[h], Checkers, coordinates2,
						prevCommand) && inPath == true && l != -1) {
					// Check if the current piece has gotten to the
					// other side, if so, set piece to king.
					if (Checkers[h].kingMe(Checkers[h], coordinates2)) {
						Checkers[h].setKing(true);
					}
					// remove the previous piece from board, set
					// selected false
					selected = false;
					removepiece(prevCommand);

					// execute if there is checker in the pieces path
					if (l != -1) {
						// remove the checker in path
						removepiece(aqa2[l]);
						// remove the checker in path from the array by
						// making it n-1 smaller
						for (int b = 0; b < Checkers.length; b++) {
							if (Checkers[b].getCoord().equals(aqa2[l])) {
								Checker[] lol = Checkers;
								int count = 0;
								Checkers = new Checker[lol.length - 1];
								for (int i = 0; i < lol.length; i++) {
									if (i != b) {
										Checkers[count] = lol[i];
										count = count + 1;
									}
								}
							}
						}
					}
					// un-highlight the previous tile and move piece,
					// set counter 0
					unhighlightTile(prevCommand);
					addpiece(command);
					this.repaint();
					counter2 = 0;
					// if the user makes illegal movement then set
					// counter 2
					// change the current piece coord to current tile
					// piece on
					// make user try again
				} else {
					counter2 = 2;
					Checkers[h].setCoord(prevCommand);
				}
				// if user makes illegal movement execute
			} else {
				// if user clicks on piece, then, de-select
				if (Checkers[h].getCoord() == command) {
					selected = false;
					counter2 = 0;
					Checkers[h].setCoord(prevCommand);
					unhighlightTile(prevCommand);

					// make user try again
				} else {
					counter2 = 2;
					Checkers[h].setCoord(prevCommand);
				}
			}
		}

	}

	boolean Comp = false;
	// size of Checkers array
	int size = 0;
	int isAI = 0;
	int black = 0;
	int white = 0;

	/**
	 * Receives the actions associated with each button and saves it as a
	 * string. When the user clicks the button, the program performs the action
	 * associated with it.
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		String command = ae.getActionCommand();

		switch (command) {
		case "New Game":

			// checks with user if new game or custom
			Object[] options = { "Standard Game", "Custom Game" };
			int n = JOptionPane.showOptionDialog(this, "Please Select:",
					" New Game ", JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

			// true if custom game selected
			if (n == 0) {

				// Notify user about selection
				JOptionPane.showMessageDialog(this,
						"Standard 12 by 12 Game Slected");
				standardGame();
				// starts game
				gameStart = true;
				// new game button disables
				newGameButton.setEnabled(false);

				Object[] options2 = { "1vs.1", "1vs.Computer" };
				int r = JOptionPane.showOptionDialog(this, "Please Select:",
						" New Game ", JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, options2,
						options2[1]);

				if (r == 0) {
					Comp = false;
					isAI = 0;
				} else if (r == 1) {
					// System.out.println("hl");
					Comp = true;
					isAI = 1;
				}

				size = Checkers.length;

				for (int i = 0; i < Checkers.length; i++) {
					if (Checkers[i].getColor().matches("Black")) {
						black++;
					} else {
						white++;
					}
				}
				// if user selects

				// handles custom game
			} else if (n == 1) {
				// Asks user for how many black pieces they want
				Object[] possibilities = { "1", "2", "3", "4", "5", "6", "7",
						"8", "9", "10", "11", "12" };

				String s = (String) JOptionPane.showInputDialog(
						this,
						"How Many Black pieces would you like?",
						"Customized Dialog",
						JOptionPane.PLAIN_MESSAGE,
						(new ImageIcon(getClass().getResource(
								"/black_piece.gif"))),

						possibilities, "1");

				String r = null;
				String v = null;
				String j = null;

				if (s != null) {

					// Asks user for how many grey pieces they want

					r = (String) JOptionPane.showInputDialog(
							this,
							"How Many Grey pieces would you like?",
							"Customized Dialog",
							JOptionPane.PLAIN_MESSAGE,
							(new ImageIcon(getClass().getResource(
									"grey_piece.gif"))),

							possibilities, "1");

					maxBlack = Integer.parseInt(s);

					if (r != null) {
						maxGrey = Integer.parseInt(r);
						total = maxBlack + maxGrey;
					}
					// Asks user for how many black King pieces they want
					// Makes sure, if the user hasn't previously canceled the
					// selection of grey pieces and makes sure there isn't 24
					// pieces on the board
					if (r != null && total < 24) {
						v = (String) JOptionPane.showInputDialog(
								this,
								"How Many Black King pieces would you like?",
								"Customized Dialog",
								JOptionPane.PLAIN_MESSAGE,
								(new ImageIcon(getClass().getResource(
										"black_pieceking.gif"))),

								possibilities, "1");

						if (v != null) {
							maxKingBlack = Integer.parseInt(v);
							total = total + maxKingBlack;
						}
					}

					// Asks user for how many Grey King pieces they want
					// Makes sure, if the user hasn't previously canceled the
					// selection of King Black Pieces and makes sure there isn't
					// 24 pieces on the board

					if (v != null && total < 24) {
						j = (String) JOptionPane.showInputDialog(
								this,
								"How Many Grey King pieces would you like?",
								"Customized Dialog",
								JOptionPane.PLAIN_MESSAGE,
								(new ImageIcon(getClass().getResource(
										"white_piece1king1.gif"))),

								possibilities, "1");
						if (j != null) {
							maxKingGrey = Integer.parseInt(j);
							total = maxBlack + maxGrey + maxKingBlack
									+ maxKingGrey;
							black = maxBlack + maxKingBlack;
							white = maxGrey + maxKingGrey;
						}
					}
				}
				// if more then 24 pieces plays a message
				if (total > 24) {
					JOptionPane.showMessageDialog(this,
							"Too many pieces selected try again!!");
				}

				// Checks users selections for custom game then, checks if two
				// different types of pieces are placed on the board.
				// Then this starts custom game by setting user defined
				// selection of number of pieces selected by user
				// to it's respective variables then starts custom game method
				if ((s != null && r != null) || v != null || j != null) {
					if (!(total > 24)) {
						gameStart = true;
						newGameButton.setEnabled(false);
						Checkers = new Checker[total];
						customGame();

						Object[] options2 = { "1v1", "1vComputa" };
						int k = JOptionPane.showOptionDialog(this,
								"Please Select:", " New Game ",
								JOptionPane.YES_NO_CANCEL_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, options2,
								options2[1]);

						if (k == 0) {
							Comp = false;
							isAI = 0;
						} else if (k == 1) {
							Comp = true;
							isAI = 1;
						}

						size = Checkers.length;

					}
				}
			}
			break;

		// if custom game button pressed, we check if the game already started,
		// if true, ask user if they want to end game and override
		// otherwise if the game hasen't started,load the game

		case "Load Game":

			if (gameStart == true) {
				int k = JOptionPane
						.showConfirmDialog(
								this,
								"Load previous game midway? Doing so will result in current game to be lost and not saved.",
								"Sure", JOptionPane.YES_NO_OPTION);
				if (k == 0) {
					try {
						if (checkSave()) {
							gameStart = true;
							loadGame();
							Object[] options2 = { "1v1", "1vComputa" };
							int r = JOptionPane.showOptionDialog(this,
									"Please Select:", " New Game ",
									JOptionPane.YES_NO_CANCEL_OPTION,
									JOptionPane.QUESTION_MESSAGE, null,
									options2, options2[1]);

							if (r == 0) {
								Comp = false;
								isAI = 0;
							} else if (r == 1) {
								Comp = true;
								isAI = 1;
							}

							size = Checkers.length;
							for (int i = 0; i < Checkers.length; i++) {
								if (Checkers[i].getColor().matches("Black")) {
									black++;
								} else {
									white++;
								}
							}

						} else {
							JOptionPane
									.showMessageDialog(this, "No file found");
						}

					} catch (FileNotFoundException e) {

						e.printStackTrace();
					}
				}
			}

			if (gameStart == false) {
				try {
					if (checkSave()) {
						gameStart = true;
						loadGame();
						Object[] options2 = { "1v1", "1vComputa" };
						int r = JOptionPane.showOptionDialog(this,
								"Please Select:", " New Game ",
								JOptionPane.YES_NO_CANCEL_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, options2,
								options2[1]);

						if (r == 0) {
							Comp = false;
							isAI = 0;
						} else if (r == 1) {
							Comp = true;
							isAI = 1;
						}

						size = Checkers.length;

						for (int i = 0; i < Checkers.length; i++) {
							if (Checkers[i].getColor().matches("Black")) {
								black++;
							} else {
								white++;
							}
						}

					} else {
						JOptionPane.showMessageDialog(this, "No file found");
					}

				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}

			break;

		// this handles the quit button
		// if the game hasn't started, quits
		// otherwise it asks user if they want to save, if so, checks if file
		// exists, if so, asks to override, otherwise file saves

		case "Quit":
			if (gameStart == false) {
				System.exit(0);
			} else if (gameStart == true) {
				int r = JOptionPane.showConfirmDialog(this,
						"Do you want quit?", "Quit", JOptionPane.YES_NO_OPTION);
				if (r == 0) {

					r = JOptionPane.showConfirmDialog(this, "Save the game?",
							"Save", JOptionPane.YES_NO_OPTION);

					if (r == 1) {
						System.exit(0);
					} else {

						try {
							if (checkSave() == true) {
								r = JOptionPane.showConfirmDialog(this,
										"Overide", "Overide ",
										JOptionPane.YES_NO_OPTION);

								if (r == 0) {
									try {
										saveGame();
										System.exit(0);
									} catch (FileNotFoundException e) {
										System.out.println("no file found");
									}
								} else {
									System.exit(0);
								}

							} else {

								try {
									saveGame();
									System.exit(0);
								} catch (FileNotFoundException e) {
									System.out.println("no file found");
								}
							}
						} catch (HeadlessException e) {
							System.out.println("no file found");
						} catch (FileNotFoundException e) {
							System.out.println("no file found");
						}
					}
				}
			}

			break;
		// this handles the checker buttons
		default:

			// If the game has started execute

			if (gameStart == true && setup == false) {

				// Checks how many black and white checkers on board
				int blackC = 0;
				int whiteC = 0;

				// checks if it's users turn, did they select their respectful
				// piece
				int s = -1;

				// Checks if user selected wrong or right tile
				int legalTileSelected = 0;

				// Checks if user selected on yellow tile.
				for (int i = 0; i < XCoord.length; i++) {
					for (int j = 0; j < YCoord.length; j++) {

						if (i % 2 == 0) {
							if (j % 2 == 0) {
								isYellow = true;
							} else {
								if (tiles[i][j].getText().matches(command)) {
									legalTileSelected = 1;
								}
							}
						} else {
							if (j % 2 == 0) {
								if (tiles[i][j].getText().matches(command)) {
									legalTileSelected = 1;
								}
							} else {
								isYellow = true;

							}
						}
					}
				}

				// Check if user is clicking on there respectful peice and not
				// the AI's
				for (int i = 0; i < Checkers.length; i++) {
					if (Checkers[i].getCoord().trim().matches(command.trim())) {
						if (Checkers[i].getColor().matches("Grey")) {
							s = 1;

						}
					}
				}

				// Checks if user A tile has been Selected the user has clicked
				// on a legal tile
				if (selected == true || selected2 == true && isYellow == false) {
					legalTileSelected = 1;
					selected2 = true;
				}

				// Figure out how many black and white peices
				for (int i = 0; i < Checkers.length; i++) {
					if (Checkers[i].getColor().matches("Black")) {
						blackC++;
					} else {
						whiteC++;
					}
				}

				// Check if user win or hasn't win

				if (whiteC == 0 || blackC == 0) {
					if (isAI == 1) {
						JOptionPane.showMessageDialog(null, "U Have Lost.!");
						System.exit(0);
					} else {
						JOptionPane.showMessageDialog(null, "U. Won!");
						System.exit(0);
					}
				} else {
					scoreWindowGrey.setText(Integer.toString(black - blackC));
					scoreWindowBlack.setText(Integer.toString(white - whiteC));
				}

				// if AI is selected and legal tile is selected
				if (isAI == 1 && legalTileSelected == 1) {
					// if user selected right tile
					// start game handler by passing tile clicked on
					// inc counter 3 by 1 if not 3
					if (s == -1) {
						if (counter3 <= 3) {
							gameHandler(command);
							if (counter3 == 3) {
								counter3++;
							}
							jp1.repaint();
						}

						// if counter2 is 0, so user has moved it's peice
						// then start MinMax Alogrithm for AI
						if (counter2 == 0) {
							try {
								Thread.sleep(222);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							minMax(Checkers);
							gameHandler(Coord);
							gameHandler(futureCoord);
							jp1.repaint();

							counter3 = 0;

						}
						// inc counter

						counter3++;

						// Check number of black and white pieces and check if
						// user has started game
						blackC = 0;
						whiteC = 0;
						for (int i = 0; i < Checkers.length; i++) {
							if (Checkers[i].getColor().matches("Black")) {
								blackC++;
							} else {
								whiteC++;
							}
						}
						if (whiteC == 0 || blackC == 0) {
							if (isAI == 1) {
								JOptionPane.showMessageDialog(null,
										"U Have Lost.!");
								System.exit(0);
							} else {
								JOptionPane.showMessageDialog(null, "U. Won!");
								System.exit(0);
							}
						} else {
							scoreWindowGrey.setText(Integer.toString(black
									- blackC));
							scoreWindowBlack.setText(Integer.toString(white
									- whiteC));
						}
					}

					// if we don't have AI and legal tile
					// then just run game handler
					// and see if user has won or not
				} else if (isAI == 0 && legalTileSelected == 1) {

					gameHandler(command);

					blackC = 0;
					whiteC = 0;
					for (int i = 0; i < Checkers.length; i++) {
						if (Checkers[i].getColor().matches("Black")) {
							blackC++;
						} else {
							whiteC++;
						}
					}
					if (whiteC == 0 || blackC == 0) {
						if (isAI == 1 && legalTileSelected == 1) {
							JOptionPane
									.showMessageDialog(null, "U Have Lost.!");
							System.exit(0);
						} else {
							JOptionPane.showMessageDialog(null, "U. Won!");
							System.exit(0);
						}
					} else {
						scoreWindowGrey.setText(Integer
								.toString(black - blackC));
						scoreWindowBlack.setText(Integer.toString(white
								- whiteC));
					}
				}

			}

			if (counter2 == 4) {
				selected = false;
			}
		}

		// setup pieces
		if (setup == true) {
			placeChecker(command);
		}

		// if user doesn't like configuration
		if (setup == false && counter == total && counter != 0) {
			setup = true;
			counter2 = 0;

		}

	}

}
