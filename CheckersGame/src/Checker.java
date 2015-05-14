/**
 * implements all methods and functions defined in CheckerInterface
 * 
 * 
 */
public class Checker implements CheckerInterface {

	/**
	 * Stores the color of the checker object on the board. Can be either Black
	 * or Grey.
	 */
	private String color;
	/**
	 * Stores true if piece is king. Otherwise stores false.
	 */
	private boolean king;
	/**
	 * Stores the current location of the checker on the board. Location of form
	 * A-5, A-6....
	 */
	private String Coord;

	/**
	 * Constructor for Checker object sets the color of the checker sets the
	 * coordinates of the checker makes the checker a king if it is a king
	 * 
	 * @param color
	 *            the color of the piece; unchanged once set
	 * @param Coord
	 *            the currently stored coordinates of the checker piece
	 * @param king
	 *            boolean value for is the piece is a king or not; unchanged
	 *            once set
	 */
	public Checker(String color, String Coord, boolean king) {
		this.setColor(color);
		setCoord(Coord);
		this.setKing(king);
	}

	/**
	 * Checks the PosssibleMoves For AI
	 * 
	 * @param Checker
	 * @param Checkers
	 * @param coordinates
	 * @return All possible moves
	 */
	public String[] possibleMoves(Checker Checker, Checker[] Checkers,
			String[][] coordinates) {
		// Stores location of where piece is being moved
		int l = 0;
		int m = 0;

		// store location of two red tiles behind the location pressed to move
		// to
		String[] tl = new String[4];
		// store location of any pieces behind the location pressed to move to
		String[] tl2 = new String[4];

		// looks for location of where user wants to move piece
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (Checker.getCoord().toString().trim()
						.matches(coordinates[i][j].trim())) {
					l = i;
					m = j;
				}

			}

		}

		if (Checker.king) {

			// if color is grey
			// we then compare the location that we want to move the checker to
			// predefined locations using
			// the checkers location (j,i) we have obtained previously by
			// increasing
			// them by 1 or -1 and making sure
			// they stay within the boundaries of array of tiles
			// we store the locations into tl, which contains the pre defined
			// locations of where the the checkers can move
			// the color comparisons swapped in this case since we are looking
			// backwards

			if (m - 1 >= 0 && m - 1 < 8) {
				if ((l - 1 >= 0 && l - 1 < 8)) {
					tl[0] = coordinates[l + 1][m - 1];
				}
				if ((l + 1 >= 0 && l + 1 < 8)) {
					tl[0] = coordinates[l - 1][m - 1];
				}

				if ((l - 1 >= 0 && l - 1 < 8) && (l + 1 >= 0 && l + 1 < 8)) {
					tl[0] = coordinates[l + 1][m - 1];
					tl[1] = coordinates[l - 1][m - 1];

				}

			}
			// if color is black
			// we then compare the location that we want to move the checker to
			// predefined locations using
			// the checkers location (j,i) we have obtained previously by
			// increasing
			// them by 1 or -1 and making sure
			// they stay within the boundaries of array of tiles
			// we store the locations into tl, which contains the pre defined
			// locations of where the the checkers can move

			if (m + 1 >= 0 && m + 1 < 8) {

				if (!(l - 1 >= 0 && l - 1 < 8)) {
					tl[2] = coordinates[l + 1][m + 1];

				}

				if (!(l + 1 >= 0 && l + 1 < 8)) {
					tl[2] = coordinates[l - 1][m + 1];
				}

				if ((l - 1 >= 0 && l - 1 < 8) && (l + 1 >= 0 && l + 1 < 8)) {
					tl[2] = coordinates[l + 1][m + 1];
					tl[3] = coordinates[l - 1][m + 1];

				}

			}
		}

		if (!Checker.king) {

			// if color is grey
			// we then compare the location that we want to move the checker to
			// predefined locations using
			// the checkers location (j,i) we have obtained previously by
			// increasing
			// them by 1 or -1 and making sure
			// they stay within the boundaries of array of tiles
			// we store the locations into tl, which contains the pre defined
			// locations of where the the checkers can move
			// the color comparisons swapped in this case since we are looking
			// backwards
			if (Checker.getColor().matches("Black")) {
				if (m - 1 >= 0 && m - 1 < 8) {
					if (!(l - 1 >= 0 && l - 1 < 8)) {
						tl[0] = coordinates[l + 1][m - 1];
					}
					if (!(l + 1 >= 0 && l + 1 < 8)) {
						tl[0] = coordinates[l - 1][m - 1];
					}

					if ((l - 1 >= 0 && l - 1 < 8) && (l + 1 >= 0 && l + 1 < 8)) {
						tl[0] = coordinates[l + 1][m - 1];
						tl[1] = coordinates[l - 1][m - 1];

					}

				}

			}
			// if color is black
			// we then compare the location that we want to move the checker to
			// predefined locations using
			// the checkers location (j,i) we have obtained previously by
			// increasing
			// them by 1 or -1 and making sure
			// they stay within the boundaries of array of tiles
			// we store the locations into tl, which contains the pre defined
			// locations of where the the checkers can move

			if (Checker.getColor().matches("Grey")) {
				if (m + 1 >= 0 && m + 1 < 8) {

					if (!(l - 1 >= 0 && l - 1 < 8)) {
						tl[0] = coordinates[l + 1][m + 1];

					}

					if (!(l + 1 >= 0 && l + 1 < 8)) {
						tl[0] = coordinates[l - 1][m + 1];
					}

					if ((l - 1 >= 0 && l - 1 < 8) && (l + 1 >= 0 && l + 1 < 8)) {
						tl[0] = coordinates[l + 1][m + 1];
						tl[1] = coordinates[l - 1][m + 1];

					}

				}
			}
		}

		// return the if anything in path, if nothing we return null
		return tl;

	}

	/**
	 * Checks the PosssibleMoves to hop pieces For AI
	 * 
	 * @param Checker
	 * @param Checkers
	 * @param coordinates
	 * @return All possible hopping pieces moves
	 */
	public String[] possibleMovesHop(Checker Checker, Checker[] Checkers,
			String[][] coordinates) {
		// Stores location of where piece is being moved
		int l = 0;
		int m = 0;

		// store location of two red tiles behind the location pressed to move
		// to
		String[] tl = new String[4];
		// store location of any pieces behind the location pressed to move to
		String[] tl2 = new String[4];

		// looks for location of where user wants to move piece
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (Checker.getCoord().toString().trim()
						.matches(coordinates[i][j].trim())) {
					l = i;
					m = j;
				}

			}

		}

		if (Checker.king) {

			// if color is grey
			// we then compare the location that we want to move the checker to
			// predefined locations using
			// the checkers location (j,i) we have obtained previously by
			// increasing
			// them by 1 or -1 and making sure
			// they stay within the boundaries of array of tiles
			// we store the locations into tl, which contains the pre defined
			// locations of where the the checkers can move
			// the color comparisons swapped in this case since we are looking
			// backwards

			if (m - 2 >= 0 && m - 2 < 8) {
				if (!(l - 2 >= 0 && l - 2 < 8)) {
					tl[0] = coordinates[l + 2][m - 2];
				}
				if (!(l + 2 >= 0 && l + 2 < 8)) {
					tl[0] = coordinates[l - 2][m - 2];
				}

				if ((l - 2 >= 0 && l - 2 < 8) && (l + 2 >= 0 && l + 2 < 8)) {
					tl[0] = coordinates[l + 2][m - 2];
					tl[1] = coordinates[l - 2][m - 2];

				}

			}
			// if color is black
			// we then compare the location that we want to move the checker to
			// predefined locations using
			// the checkers location (j,i) we have obtained previously by
			// increasing
			// them by 1 or -1 and making sure
			// they stay within the boundaries of array of tiles
			// we store the locations into tl, which contains the pre defined
			// locations of where the the checkers can move

			if (m + 2 >= 0 && m + 2 < 8) {

				if (!(l - 2 >= 0 && l - 2 < 8)) {
					tl[2] = coordinates[l + 2][m + 2];

				}

				if (!(l + 2 >= 0 && l + 2 < 8)) {
					tl[2] = coordinates[l - 2][m + 2];
				}

				if ((l - 2 >= 0 && l - 2 < 8) && (l + 2 >= 0 && l + 2 < 8)) {
					tl[2] = coordinates[l + 2][m + 2];
					tl[3] = coordinates[l - 2][m + 2];

				}

			}
		}

		if (!Checker.king) {

			// if color is grey
			// we then compare the location that we want to move the checker to
			// predefined locations using
			// the checkers location (j,i) we have obtained previously by
			// increasing
			// them by 1 or -1 and making sure
			// they stay within the boundaries of array of tiles
			// we store the locations into tl, which contains the pre defined
			// locations of where the the checkers can move
			// the color comparisons swapped in this case since we are looking
			// backwards
			if (Checker.getColor().matches("Black")) {
				if (m - 2 >= 0 && m - 2 < 8) {
					if (!(l - 2 >= 0 && l - 2 < 8)) {
						tl[0] = coordinates[l + 2][m - 2];
					}
					if (!(l + 2 >= 0 && l + 2 < 8)) {
						tl[0] = coordinates[l - 2][m - 2];
					}

					if ((l - 2 >= 0 && l - 2 < 8) && (l + 2 >= 0 && l + 2 < 8)) {
						tl[0] = coordinates[l + 2][m - 2];
						tl[1] = coordinates[l - 2][m - 2];

					}

				}

			}
			// if color is black
			// we then compare the location that we want to move the checker to
			// predefined locations using
			// the checkers location (j,i) we have obtained previously by
			// increasing
			// them by 1 or -1 and making sure
			// they stay within the boundaries of array of tiles
			// we store the locations into tl, which contains the pre defined
			// locations of where the the checkers can move

			if (Checker.getColor().matches("Grey")) {
				if (m + 2 >= 0 && m + 2 < 8) {

					if (!(l - 2 >= 0 && l - 2 < 8)) {
						tl[0] = coordinates[l + 2][m + 2];

					}

					if (!(l + 2 >= 0 && l + 2 < 8)) {
						tl[0] = coordinates[l - 2][m + 2];
					}

					if ((l - 2 >= 0 && l - 2 < 8) && (l + 2 >= 0 && l + 2 < 8)) {
						tl[0] = coordinates[l + 2][m + 2];
						tl[1] = coordinates[l - 2][m + 2];

					}

				}
			}
		}

		// return the if anything in path, if nothing we return null
		return tl;
	}

	/**
	 * sets the white tiles' coordinates to 'null' so that no pieces can be
	 * placed on them if the user attempts to place a piece on a null tile or a
	 * tile with a piece already in that tile, the method returns true so that
	 * the piece cannot be placed
	 */
	@Override
	public boolean illegalPlacement(Checker Checker, Checker[] Checkers,
			String[][] coordinates) {

		// iterate through tiles
		for (int j = 0; j < 8; j++) {
			for (int i = 0; i < 8; i++) {
				// check if we are on a white tile. Set it to null.
				if (j % 2 != 0) {
					if (i % 2 == 0) {
						coordinates[i][j] = "null";
					}
				} else {
					if (i % 2 != 0) {
						coordinates[i][j] = "null";
					}
				}
			}
		}

		// iterate through tiles and check if any pieces on a white tile. If
		// true then return false otherwise return true.
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {

				if (Checker.getCoord().matches(coordinates[i][j])) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * disallows placement of pieces on tiles which already have pieces placed
	 * in them this is done by returning true if the coordinates of Checker
	 * match the coordinates of the current Checker i in Checkers[]
	 */
	public boolean Overlap(Checker Checker, Checker Checkers[], int index) {
		// Iterate through all current checkers on the board
		for (int i = 0; i < Checkers.length; i++) {
			// Checks if the current checker is on top of any pieces on the
			// board.
			// If true return true, else, return false.
			if (index != i && Checkers[i] != null) {
				if (Checkers[i].Coord.matches(Checker.getCoord())) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Checks if current move is legal and returns false if it is illegal if the
	 * piece is a king, allow backwards and forwards movement if a chain of
	 * moves is possible, take all moves in the chain if the square is empty,
	 * and not white, allow movement by returning true
	 */
	@Override
	public boolean illegalMove(Checker Checker, Checker[] Checkers,
			String[][] coordinates, String prevCoord) {
		// stores location of piece pressed
		int l = 0;
		int m = 0;
		// stores location of where piece should move
		int r = 0;
		// stores color of piece
		String prevColor = "";

		// looks for location of piece in the tiles
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {

				if (prevCoord.trim().matches(coordinates[i][j])) {
					l = i;
					m = j;
				}

			}

		}

		// store color of checker
		prevColor = Checker.getColor();
		// if checker is king then figure out what direction it's heading
		// if forward then set color black, otherwise grey
		if (Checker.isKing()) {

			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {

					if (Checker.getCoord().trim().matches(coordinates[i][j])) {
						r = j;
					}

				}

			}

			if (r < m) {
				Checker.setColor("Black");
			} else {
				Checker.setColor("Grey");
			}
		}

		// if color is black
		// we then compare the location that we want to move the checker to
		// predefined locations using
		// the checkers location (j,i) we have obtained previously by increasing
		// them by 1 or -1 and making sure
		// they stay within the boundaries of array of tiles
		// if the user has selected the right tile, return true, else false
		if (Checker.getColor().trim().equals("Black")) {
			if (m - 1 >= 0 && m - 1 < 8) {
				if (!(l - 1 >= 0 && l - 1 < 8)) {
					if (Checker.getCoord().trim()
							.matches(coordinates[l + 1][m - 1])) {
						Checker.setColor(prevColor);
						return true;

					}
				}

				if (!(l + 1 >= 0 && l + 1 < 8)) {
					if (Checker.getCoord().trim()
							.matches(coordinates[l - 1][m - 1])) {
						Checker.setColor(prevColor);
						return true;

					}
				}

				if ((l - 1 >= 0 && l - 1 < 8) && (l + 1 >= 0 && l + 1 < 8)) {
					if (Checker.getCoord().trim()
							.matches(coordinates[l + 1][m - 1])) {
						Checker.setColor(prevColor);
						return true;

					}
					if (Checker.getCoord().trim()
							.matches(coordinates[l - 1][m - 1])) {
						Checker.setColor(prevColor);
						return true;

					}
				}

			}

		}

		// if color is grey
		// we then compare the location that we want to move the checker to
		// predefined locations using
		// the checkers location (j,i) we have obtained previously by increasing
		// them by 1 or -1 and making sure
		// they stay within the boundaries of array of tiles
		// if the user has selected the right tile, return true, else false

		if (Checker.getColor().trim().equals("Grey")) {
			if (m + 1 >= 0 && m + 1 < 8) {

				if (!(l - 1 >= 0 && l - 1 < 8)) {
					if (Checker.getCoord().trim()
							.matches(coordinates[l + 1][m + 1])) {
						Checker.setColor(prevColor);
						return true;

					}

				}

				if (!(l + 1 >= 0 && l + 1 < 8)) {
					if (Checker.getCoord().trim()
							.matches(coordinates[l - 1][m + 1])) {
						Checker.setColor(prevColor);
						return true;

					}
				}

				if ((l - 1 >= 0 && l - 1 < 8) && (l + 1 >= 0 && l + 1 < 8)) {
					if (Checker.getCoord().trim()
							.matches(coordinates[l + 1][m + 1])) {
						Checker.setColor(prevColor);
						return true;

					}
					if (Checker.getCoord().trim()
							.matches(coordinates[l - 1][m + 1])) {
						Checker.setColor(prevColor);
						return true;

					}

				}

			}
		}
		// set Checker piece to it's original color
		Checker.setColor(prevColor);
		return false;
	}

	/**
	 * If the user has a piece in it's path, this method then checks if the user
	 * is able to jump over that piece.
	 */
	public boolean hop(Checker Checker, Checker[] Checkers,
			String[][] coordinates, String prevCoord) {
		// stores location of piece pressed
		int l = 0;
		int m = 0;
		// stores location of where piece should move
		int r = 0;
		// stores color of piece
		String prevColor = "";

		// looks for location of piece in the tiles

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {

				if (prevCoord.trim().matches(coordinates[i][j])) {
					l = i;
					m = j;
				}

			}

		}
		// store color of checker
		prevColor = Checker.getColor();
		// if checker is king then figure out what direction it's heading
		// if forward then set color black, otherwise grey

		if (Checker.isKing()) {

			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {

					if (Checker.getCoord().trim().matches(coordinates[i][j])) {
						r = j;
					}

				}

			}

			if (r < m) {
				Checker.setColor("Black");
			} else {
				Checker.setColor("Grey");
			}
		}

		// if color is black
		// we then compare the location that we want to move the checker to
		// predefined locations using
		// the checkers location (j,i) we have obtained previously by increasing
		// them by 1 or -1 and making sure
		// they stay within the boundaries of array of tiles
		// if the user has selected the right tile, return true, else false
		if (Checker.getColor().trim().equals("Black")) {
			if (m - 2 >= 0 && m - 2 < 8) {

				if (!(l - 2 >= 0 && l - 2 < 8)) {
					if (Checker.getCoord().trim()
							.matches(coordinates[l + 2][m - 2])) {
						Checker.setColor(prevColor);
						return true;

					}

				}

				if (!(l + 2 >= 0 && l + 2 < 8)) {
					if (Checker.getCoord().trim()
							.matches(coordinates[l - 2][m - 2])) {
						Checker.setColor(prevColor);
						return true;

					}
				}

				if ((l - 2 >= 0 && l - 2 < 8) && (l + 2 >= 0 && l + 2 < 8)) {
					if (Checker.getCoord().trim()
							.matches(coordinates[l + 2][m - 2])) {
						Checker.setColor(prevColor);
						return true;

					}
					if (Checker.getCoord().trim()
							.matches(coordinates[l - 2][m - 2])) {
						Checker.setColor(prevColor);
						return true;

					}

				}

			}

		}

		// if color is grey
		// we then compare the location that we want to move the checker to
		// predefined locations using
		// the checkers location (j,i) we have obtained previously by increasing
		// them by 1 or -1 and making sure
		// they stay within the boundaries of array of tiles
		// if the user has selected the right tile, return true, else false

		if (Checker.getColor().trim().equals("Grey")) {
			if (m + 2 >= 0 && m + 2 < 8) {

				if (!(l - 2 >= 0 && l - 2 < 8)) {
					if (Checker.getCoord().trim()
							.matches(coordinates[l + 2][m + 2])) {
						Checker.setColor(prevColor);
						return true;

					}

				}

				if (!(l + 2 >= 0 && l + 2 < 8)) {
					if (Checker.getCoord().trim()
							.matches(coordinates[l - 2][m + 2])) {
						Checker.setColor(prevColor);
						return true;

					}
				}

				if ((l - 2 >= 0 && l - 2 < 8) && (l + 2 >= 0 && l + 2 < 8)) {
					if (Checker.getCoord().trim()
							.matches(coordinates[l + 2][m + 2])) {
						Checker.setColor(prevColor);
						return true;

					}
					if (Checker.getCoord().trim()
							.matches(coordinates[l - 2][m + 2])) {
						Checker.setColor(prevColor);
						return true;

					}

				}

			}
		}
		// set checker color to original
		Checker.setColor(prevColor);
		return false;
	}

	/**
	 * Checks if there is a Checker in the path of movement of the current
	 * checker if so return location of that checker
	 */

	public String[] CheckerInPath(Checker Checker, Checker[] Checkers,
			String[][] coordinates, String prevCoord) {
		// stores location of piece pressed
		int l = 0;
		int m = 0;
		// stores location of where piece should move
		int r = 0;
		// stores color of piece

		String prevColor = "";

		// store location of where piece should move
		String[] tl = new String[2];
		// store location of any pieces in path
		String[] tl2 = new String[2];

		// looks for location of piece in the tiles

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {

				if (prevCoord.trim().matches(coordinates[i][j])) {
					l = i;
					m = j;
				}

			}

		}

		// get color of checker
		prevColor = Checker.getColor();
		// if checker is king then figure out what direction it's heading
		// if forward then set color black, otherwise grey
		if (Checker.isKing()) {

			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {

					if (Checker.getCoord().trim().matches(coordinates[i][j])) {
						r = j;
					}

				}

			}

			if (r < m) {
				Checker.setColor("Black");
			} else {
				Checker.setColor("Grey");
			}
		}

		// if color is black
		// we then compare the location that we want to move the checker to
		// predefined locations using
		// the checkers location (j,i) we have obtained previously by increasing
		// them by 1 or -1 and making sure
		// they stay within the boundaries of array of tiles
		// we store the locations into tl, which contains the pre defined
		// locations of where the the checkers can move
		if (Checker.getColor().trim().equals("Black")) {
			if (m - 1 >= 0 && m - 1 < 8) {
				if (!(l - 1 >= 0 && l - 1 < 8)) {
					tl[0] = coordinates[l + 1][m - 1];

				}

				if (!(l + 1 >= 0 && l + 1 < 8)) {
					tl[0] = coordinates[l - 1][m - 1];

				}

				if ((l - 1 >= 0 && l - 1 < 8) && (l + 1 >= 0 && l + 1 < 8)) {
					tl[0] = coordinates[l + 1][m - 1];
					tl[1] = coordinates[l - 1][m - 1];

				}

			}

		}
		// if color is grey
		// we then compare the location that we want to move the checker to
		// predefined locations using
		// the checkers location (j,i) we have obtained previously by increasing
		// them by 1 or -1 and making sure
		// they stay within the boundaries of array of tiles
		// we store the locations into tl, which contains the pre defined
		// locations of where the the checkers can move

		if (Checker.getColor().trim().equals("Grey")) {
			if (m + 1 >= 0 && m + 1 < 8) {
				if (!(l - 1 >= 0 && l - 1 < 8)) {
					tl[0] = coordinates[l + 1][m + 1];

				}
				if (!(l + 1 >= 0 && l + 1 < 8)) {
					tl[0] = coordinates[l - 1][m + 1];
				}

				if ((l - 1 >= 0 && l - 1 < 8) && (l + 1 >= 0 && l + 1 < 8)) {
					tl[0] = coordinates[l + 1][m + 1];
					tl[1] = coordinates[l - 1][m + 1];

				}

			}
		}

		// this gets the current location of checker on the board
		int currentChecekerLocation = 0;
		for (int i = 0; i < Checkers.length; i++) {
			if (Checker.getCoord().matches(Checkers[i].getCoord())) {
				currentChecekerLocation = i;

			}
		}

		// Here we see if any of the locations in tl are a checker piece, we
		// then store into tl2
		for (int i = 0; i < Checkers.length; i++) {
			for (int j = 0; j < tl.length; j++) {
				if (i != currentChecekerLocation) {
					if (tl[j] != null) {
						if (Checkers[i].getCoord().matches(tl[j])) {
							tl2[j] = tl[j];

						}
					}
				}
			}
		}
		// restore previous color
		Checker.setColor(prevColor);
		// return the if anything in path, if nothing we return null
		return tl2;
	}

	/**
	 * This method figures out what two checkers were behind the checker when it
	 * jumped.
	 */
	public String[] CheckerInPath2(Checker Checker, Checker[] Checkers,
			String[][] coordinates, String prevCoord, String com) {
		// Stores location of where piece is being moved
		int l = 0;
		int m = 0;
		// stores location of piece pressed
		int r = 0;
		// stores location of where piece should move
		int p = 0;
		// stores checker color
		String prevColor = "";
		// store location of two red tiles behind the location pressed to move
		// to
		String[] tl = new String[2];
		// store location of any pieces behind the location pressed to move to
		String[] tl2 = new String[2];

		// looks for location of where user wants to move piece
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (prevCoord.trim().matches(coordinates[i][j])) {
					l = i;
					m = j;
				}

			}

		}

		// set to current checker color
		prevColor = Checker.getColor();

		// if checker is king then figure out what direction it's heading by
		// first finding the original location
		// of the piece
		// if forward then set color black, otherwise grey

		if (Checker.isKing()) {
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {

					if (com.trim().matches(coordinates[i][j])) {
						p = j;
					}

				}

			}

			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {

					if (Checker.getCoord().trim().matches(coordinates[i][j])) {
						r = j;
					}
				}
			}

			if (r < p) {
				Checker.setColor("Black");
			} else {
				Checker.setColor("Grey");
			}
		}

		// if color is grey
		// we then compare the location that we want to move the checker to
		// predefined locations using
		// the checkers location (j,i) we have obtained previously by increasing
		// them by 1 or -1 and making sure
		// they stay within the boundaries of array of tiles
		// we store the locations into tl, which contains the pre defined
		// locations of where the the checkers can move
		// the color comparisons swapped in this case since we are looking
		// backwards
		if (Checker.getColor().trim().equals("Grey")) {
			if (m - 1 >= 0 && m - 1 < 8) {
				if (!(l - 1 >= 0 && l - 1 < 8)) {
					tl[0] = coordinates[l + 1][m - 1];
				}
				if (!(l + 1 >= 0 && l + 1 < 8)) {
					tl[0] = coordinates[l - 1][m - 1];
				}

				if ((l - 1 >= 0 && l - 1 < 8) && (l + 1 >= 0 && l + 1 < 8)) {
					tl[0] = coordinates[l + 1][m - 1];
					tl[1] = coordinates[l - 1][m - 1];

				}

			}

		}
		// if color is black
		// we then compare the location that we want to move the checker to
		// predefined locations using
		// the checkers location (j,i) we have obtained previously by increasing
		// them by 1 or -1 and making sure
		// they stay within the boundaries of array of tiles
		// we store the locations into tl, which contains the pre defined
		// locations of where the the checkers can move

		if (Checker.getColor().trim().equals("Black")) {
			if (m + 1 >= 0 && m + 1 < 8) {

				if (!(l - 1 >= 0 && l - 1 < 8)) {
					tl[0] = coordinates[l + 1][m + 1];

				}

				if (!(l + 1 >= 0 && l + 1 < 8)) {
					tl[0] = coordinates[l - 1][m + 1];
				}

				if ((l - 1 >= 0 && l - 1 < 8) && (l + 1 >= 0 && l + 1 < 8)) {
					tl[0] = coordinates[l + 1][m + 1];
					tl[1] = coordinates[l - 1][m + 1];

				}

			}
		}

		// this gets the current location of checker on the board
		int currentChecekerLocation = 0;
		for (int i = 0; i < Checkers.length; i++) {
			if (Checker.getCoord().matches(Checkers[i].getCoord())) {
				currentChecekerLocation = i;

			}
		}

		// Here we see if any of the locations in tl are a checker piece, we
		// then store into tl2
		for (int i = 0; i < Checkers.length; i++) {
			for (int j = 0; j < tl.length; j++) {
				if (i != currentChecekerLocation) {
					if (tl[j] != null) {
						if (Checkers[i].getCoord().matches(tl[j])) {
							tl2[j] = tl[j];

						}
					}
				}
			}
		}

		// restore previous color
		Checker.setColor(prevColor);
		// return the if anything in path, if nothing we return null
		return tl2;
	}

	/**
	 * Checks if user has made it to other side of board. Then depending on
	 * color of piece and if the piece is already a king or not, we set the
	 * piece to a king.
	 */
	// Checks if user has made it to other side of board. Then depending on
	// color and if king or not
	// we set it to king
	public Boolean kingMe(Checker checker, String[][] board) {
		// check if king
		if (!checker.isKing()) {
			for (int i = 0; i < 8; i++) {
				// if black and on tile[0..7][0] then return true, and set to
				// king
				if (checker.getColor().matches("Black")) {
					if (board[i][0].matches(checker.getCoord())) {
						return true;
					}
				}
				// if grey and on tile[0..7][7] then return true, and set
				if (checker.getColor().matches("Grey")) {
					if (board[i][7].matches(checker.getCoord())) {
						return true;
					}
				}
			}
		}
		// otherwise return false if not on other side or is king
		return false;
	}

	/**
	 * returns the color value of a piece
	 */
	@Override
	public String getColor() {
		return color;
	}

	/**
	 * sets the color value of a piece
	 */
	@Override
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * returns whether or not the piece is a king
	 */
	@Override
	public boolean isKing() {
		return king;
	}

	/**
	 * sets the current non-king piece to a king
	 */
	public void setKing(boolean king) {
		this.king = king;
	}

	/**
	 * returns the coordinates of the piece
	 */
	@Override
	public String getCoord() {
		return Coord;
	}

	/**
	 * sets the coordinates of the piece
	 */
	@Override
	public void setCoord(String coord) {
		Coord = coord;
	}

	/**
	 * checks if the tile selected has the selected checker on it
	 */
	@Override
	public int isSelected(String currentTileSelected, Checker[] Checkers) {
		for (int i = 0; i < Checkers.length; i++) {
			if (currentTileSelected.trim().matches(
					Checkers[i].getCoord().trim())) {
				return i;
			}
		}
		return -1;
	}

}
