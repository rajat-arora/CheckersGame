/**
 * 
 * Interface defining all Checker methods and functions some of which are
 * unimplemented and will be implemented as future milestones are reached
 */

public interface CheckerInterface {

	/**
	 * Checks current checker with other checkers locations to see if current
	 * checker is overlapping
	 * 
	 * @param Checker
	 *            checker piece the user wants to place
	 * @param Checkers
	 *            array of current checkers on the board
	 * @param coordinates
	 *            a 2-dimensional array of checkers currently on the board used
	 *            to compare x and y coordinates
	 * @return returns true if a piece is already in the coordinates (illegal
	 *         move) or if the user attempts to place a piece on a white tile
	 *         returns false otherwise (the move is legal)
	 */
	public boolean illegalPlacement(Checker Checker, Checker[] Checkers,
			String[][] coordinates);

	/**
	 * This method allows for a piece to move two spaces instead of one (which
	 * is what needs to be done when an opponent piece must be hopped over and
	 * overtaken)
	 * 
	 * @param Checker
	 *            checker piece the user wants to place
	 * @param Checkers
	 *            array of current checkers on the board
	 * @param coordinates
	 *            a 2-dimensional array of checkers currently on the board used
	 *            to compare x and y coordinates
	 * @param prevCoord
	 *            the coordinate that the checker piece that the user wants to
	 *            move is currently on
	 * @return Returns true if the Checker is able to hop over a piece legally,
	 *         so if there is a piece in front of the selected piece and a legal
	 *         jump is possible. Otherwise, false is returned.
	 */
	public boolean hop(Checker Checker, Checker[] Checkers,
			String[][] coordinates, String prevCoord);

	/**
	 * Ensures that any pieces which are attempting to be placed do not overlap
	 * 
	 * @param Checker
	 *            current piece attempting to be placed
	 * @param Checkers
	 *            array of checker pieces currently on the board
	 * @param index
	 *            the possible index of the checker the user is attempting to
	 *            place
	 * @return returns true if the Checker's coordinates match the coordinates
	 *         of any piece currently on the board (in the Checkers array)
	 */
	public boolean Overlap(Checker Checker, Checker Checkers[], int index);

	/**
	 * This function checks if a move is legal. Checks the position of the
	 * current checker in relation to the other checkers on the board and if the
	 * piece is regular or a king and allows or disallows movement accordingly.
	 * 
	 * @param Checker
	 *            the current checker the user wishes to move
	 * @param Checkers
	 *            the array of all checker pieces on the board
	 * @param Coordinates
	 *            a 2-dimensional array of checkers currently on the board used
	 *            to compare x and y coordinates
	 * @param prevCoord
	 *            the coordinate that the checker piece that the user wants to
	 *            move is currently on
	 * @return return true if the move is legal, false if the move is illegal
	 */
	public boolean illegalMove(Checker Checker, Checker[] Checkers,
			String[][] Coordinates, String prevCoord);

	/**
	 * 
	 * This method checks if there is a checker piece in the path of the
	 * selected checker piece to be moved. If there is a checker in the path,
	 * return the checker that is in the path and do not allow movement
	 * 
	 * @param Checker
	 *            the current checker the user wishes to move
	 * @param Checkers
	 *            the array of all checker pieces on the board
	 * @param coordinates
	 *            a 2-dimensional array of checkers currently on the board used
	 *            to compare x and y coordinates
	 * @param prevCoord
	 *            the coordinate that the checker piece that the user wants to
	 *            move is currently on
	 * @return return the location of the checker piece that is in the path of
	 *         the selected checker
	 */
	public String[] CheckerInPath(Checker Checker, Checker[] Checkers,
			String[][] coordinates, String prevCoord);

	/**
	 * 
	 * This method is similar to CheckerInPath(), but the difference in this
	 * method is that this method discovers which two checkers were previously
	 * in the path of the checker before it jumped. This method computes which
	 * checker should be eliminated.
	 * 
	 * @param Checker
	 *            the current checker the user wishes to move
	 * @param Checkers
	 *            the array of all checker pieces on the board
	 * @param coordinates
	 *            a 2-dimensional array of checkers currently on the board used
	 *            to compare x and y coordinates
	 * @param prevCoord
	 *            the coordinate that the checker piece that the user wants to
	 *            move is currently on
	 * @param com
	 *            used to compare coordinates for selected king pieces
	 * @return return the array tl2
	 */
	public String[] CheckerInPath2(Checker Checker, Checker[] Checkers,
			String[][] coordinates, String prevCoord, String com);

	/**
	 * This method checks if the piece already has king status. If it does not,
	 * then the method checks the coordinate data of the piece Checker to see if
	 * it has made it to the opposite side of the board and then gives it king
	 * status.
	 * 
	 * @param checker
	 *            the current checker the user is attempting to move
	 * @param board
	 *            the array containing the coordinates of the board
	 * @return return true if the piece is on the other side of the board and is
	 *         not a king, otherwise return false
	 */
	public Boolean kingMe(Checker checker, String[][] board);

	/**
	 * gets coordinates of a checker piece
	 * 
	 * @return coordinates
	 */
	public String getCoord();

	/**
	 * setter for the coordinates of a piece
	 * 
	 * @param coord
	 *            the new coordinates of the piece
	 */
	public void setCoord(String coord);

	/**
	 * checks if the piece is a king or not
	 * 
	 * @return true if the piece is a king, false if not
	 */
	public boolean isKing();

	/**
	 * sets a piece to be a king
	 * 
	 * @param king
	 *            sets king if true, does not if false
	 */
	public void setKing(boolean king);

	/**
	 * gets the color value of a piece
	 * 
	 * @return color
	 */
	public String getColor();

	/**
	 * sets color of checker piece
	 * 
	 * @param color
	 *            either grey or black
	 */
	public void setColor(String color);

	/**
	 * Checks if there is a checker piece on the currently selected tile.
	 * 
	 * @param currentTileSelected   the selected tile
	 * @param Checkers		the array of checkers on the board
	 * @return 1 if selected or 0 if not
	 */

	public int isSelected(String currentTileSelected, Checker Checkers[]);
}
