public class Node {

	/*
	 * Stores the current Nodes Coordinates
	 */
	private String coord;
	/*
	 * Stores the current Nodes color
	 */
	private String color;
	/*
	 * Store if current Node King
	 */
	private boolean king;
	/*
	 * Stores it's Parent
	 */
	private String futureCoord;
	/*
	 * Stores the current Nodes Checker Configuration
	 */
	private Checker[] Coordinates;

	/**
	 * Constructor, Creates a new node
	 * 
	 * @param coord
	 * @param color
	 * @param futureCoord
	 * @param king
	 * @param Coordinates
	 */
	public Node(String coord, String color, String futureCoord, boolean king,
			Checker[] Coordinates) {
		this.setCoord(coord);
		this.setColor(color);
		this.setFutureCoord(futureCoord);
		this.setKing(king);
		this.setCoordinates(Coordinates);
	}

	/*
	 * Stores if configuration is on correct tile.
	 */
	protected int locationPressed = -1;
	/*
	 * Stores the location in the Checkers array of current checker
	 */
	protected int h = 0;
	/*
	 * Checks if node future move is correct
	 */
	protected boolean selected = false;
	/*
	 * A counter
	 */
	protected int counter = 0;
	/*
	 * A second counter
	 */
	protected int counter2 = 0;
	/*
	 * Stores the previous command
	 */
	protected String prevCommand = "";
	/*
	 * Checks if a checker is in path
	 */
	protected boolean inPath = false;

	/**
	 * Checks for an Legal Move for AI. Slightly, identical to the one in
	 * MainGui
	 * 
	 * @param command
	 * @param coordinates
	 * @param coordinates2
	 * @return if legal or not
	 */
	public boolean legalMove(String command, String[][] coordinates,
			String[][] coordinates2) {
		// System.out.println(command + "jjj");
		// Get the location of the checker clicked on
		locationPressed = (Coordinates[0].isSelected(command, Coordinates));

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
			// highlightTile(prevCommand);
			counter2 = counter2 + 1;
		}

		// execute if user has selected the location where checker
		// should move on board
		if (counter2 == 3) {
			// set the current checker clicked on to it's new location
			// where it should move
			Coordinates[h].setCoord(command);
			// This variable checks if there is a checker in the path of
			// movement
			// if so store into this variable
			String[] aqa = Coordinates[h].CheckerInPath(Coordinates[h],
					Coordinates, coordinates2, prevCommand);

			// if the previous variable initialized is not null then we
			// have something in the path
			if (aqa[0] != null || aqa[1] != null) {
				inPath = true;
			} else {
				inPath = false;
			}

			// Checks if the tile that user has clicked on is a hop

			boolean hop = Coordinates[h].hop(Coordinates[h], Coordinates,
					coordinates2, prevCommand);

			// this stores location of checker in path
			int l = -1;
			// this stores value of location of checker in path
			String[] aqa2 = null;
			// if something is in it's path and the user wants to jump a
			// piece execute
			if (hop == true && inPath == true) {
				// Find the location of the checkers in the path (both
				// right and left)
				aqa2 = Coordinates[h].CheckerInPath2(Coordinates[h],
						Coordinates, coordinates2, command, prevCommand);
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
			for (int i = 0; i < Coordinates.length; i++) {
				if (l != -1 && aqa != null) {
					if (aqa2[l].matches(Coordinates[i].getCoord())) {
						if (Coordinates[i].getColor().matches(
								Coordinates[h].getColor())) {
							match = true;
						}
					}
				}
			}
			// if no overlap, conflict of colors while hopping , and the
			// user is selecting a red tile, execute
			if (!Coordinates[h].Overlap(Coordinates[h], Coordinates, h)
					&& !Coordinates[h].illegalPlacement(Coordinates[h],
							Coordinates, coordinates) && !match) {

				// if the user is not hopping over anything and clicks
				// the correct square execute
				if (Coordinates[h].illegalMove(Coordinates[h], Coordinates,
						coordinates2, prevCommand) && l == -1) {
					// Check if the current piece has gotten to the
					// other side, if so, set piece to king.
					if (Coordinates[h].kingMe(Coordinates[h], coordinates2)) {
						Coordinates[h].setKing(true);
					}

					// move piece, un-select the tile and change
					// selected to false, set counter 0
					selected = false;
					// removepiece(prevCommand);
					// unhighlightTile(prevCommand);
					// addpiece(command);
					// this.repaint();
					counter2 = 0;
					Coordinates[h].setCoord(prevCommand);
					return true;
					// if the user is hopping over anything and clicks
					// the correct square execute
				} else if (Coordinates[h].hop(Coordinates[h], Coordinates,
						coordinates2, prevCommand) && inPath == true && l != -1) {
					// Check if the current piece has gotten to the
					// other side, if so, set piece to king.
					if (Coordinates[h].kingMe(Coordinates[h], coordinates2)) {
						Coordinates[h].setKing(true);
					}
					// remove the previous piece from board, set
					// selected false
					selected = false;
					// removepiece(prevCommand);

					// execute if there is checker in the pieces path
					if (l != -1) {
						// remove the checker in path
						// removepiece(aqa2[l]);
						// remove the checker in path from the array by
						// making it n-1 smaller
						for (int b = 0; b < Coordinates.length; b++) {
							if (Coordinates[b].getCoord().equals(aqa2[l])) {
								Checker[] lol = Coordinates;
								int count = 0;
								Coordinates = new Checker[lol.length - 1];
								for (int i = 0; i < lol.length; i++) {
									if (i != b) {
										Coordinates[count] = lol[i];
										count = count + 1;
									}
								}
							}
						}
					}
					// un-highlight the previous tile and move piece,
					// set counter 0
					// unhighlightTile(prevCommand);
					// addpiece(command);
					// this.repaint();
					counter2 = 0;
					Coordinates[h].setCoord(prevCommand);
					return true;
					// if the user makes illegal movement then set
					// counter 2
					// change the current piece coord to current tile
					// piece on
					// make user try again
				} else {
					counter2 = 2;
					Coordinates[h].setCoord(prevCommand);
				}
				// if user makes illegal movement execute
			} else {
				// if user clicks on piece, then, de-select
				if (Coordinates[h].getCoord() == command) {
					selected = false;
					counter2 = 0;
					Coordinates[h].setCoord(prevCommand);
					// unhighlightTile(prevCommand);

					// make user try again
				} else {
					counter2 = 2;
					Coordinates[h].setCoord(prevCommand);
				}
			}
		}
		return false;

	}

	/**
	 * Gets the coordinates
	 * 
	 * @return coord
	 */
	public String getCoord() {
		return coord;
	}

	/**
	 * Sets the coordinates
	 * 
	 * @param coord
	 */
	public void setCoord(String coord) {
		this.coord = coord;
	}

	/**
	 * Gets the color
	 * 
	 * @return color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * Sets the color
	 * 
	 * @param color
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * Checks if king
	 * 
	 * @return if king
	 */
	public boolean isKing() {
		return king;
	}

	/**
	 * Sets if king
	 * 
	 * @param king
	 */
	public void setKing(boolean king) {
		this.king = king;
	}

	/**
	 * Gets the future coord
	 * 
	 * @return future coord
	 */

	public String getFutureCoord() {
		return futureCoord;
	}

	/**
	 * Sets the future Coord
	 * 
	 * @param futureCoord
	 */
	public void setFutureCoord(String futureCoord) {
		this.futureCoord = futureCoord;
	}

	/**
	 * Gets the current Checkers
	 * 
	 * @return Coordinates
	 */
	public Checker[] getCoordinates() {
		return Coordinates;
	}

	/**
	 * Sets the current Checkers
	 * 
	 * @param Checker []
	 */
	public void setCoordinates(Checker[] coordinates) {
		Coordinates = coordinates;
	}

}
