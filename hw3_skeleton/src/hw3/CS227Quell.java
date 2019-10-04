package hw3;

import static api.CellState.MOVABLE_NEG;
import static api.CellState.MOVABLE_POS;
import static api.CellState.PEARL;
import static api.CellState.PORTAL;

import java.util.*;

import api.Cell;
import api.CellState;
import api.Descriptor;
import api.Direction;
import api.StringUtil;

/**
 * Basic game state and operations for a simplified version of the video game
 * "Quell".
 * @ntucker Nathan Tucker
 */
public class CS227Quell {
	/**
	 * Two-dimensional array of Cell objects representing the grid on which the game
	 * is played.
	 */
	private Cell[][] grid;

	/**
	 * Instance of GameSupport to be used in the move() algorithm.
	 */
	private GameSupport support;

	private int pearls;

	private int countOfMoves;

	private boolean playerDeath;

	private String playerRecordPosition;

	/**
	 * Constructs a game from the given string description. The conventions for
	 * representing cell states as characters can be found in
	 * <code>StringUtil</code>.
	 * 
	 * @param init    string array describing initial cell states
	 * @param support GameSupport instance to use in the <code>move</code> method
	 */
	public CS227Quell(String[] init, GameSupport support) {
		grid = StringUtil.createFromStringArray(init);
		this.support = support;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j].getState() == PEARL) {
					pearls++;
				}
			}
		}
	}

	/**
	 * Returns the number of columns in the grid.
	 * 
	 * @return width of the grid
	 */
	public int getColumns() {
		return grid[0].length;
	}

	/**
	 * Returns the number of rows in the grid.
	 * 
	 * @return height of the grid
	 */
	public int getRows() {
		return grid.length;
	}

	/**
	 * Returns the cell at the given row and column.
	 * 
	 * @param row row index for the cell
	 * @param col column index for the cell
	 * @return cell at given row and column
	 */
	public Cell getCell(int row, int col) {
		return grid[row][col];
	}

	/**
	 * Returns true if the game is over, false otherwise. The game ends when all
	 * pearls are removed from the grid or when the player lands on a cell with
	 * spikes.
	 * 
	 * @return true if the game is over, false otherwise
	 */
	public boolean isOver() {
		if (CellState.isSpikes(grid[getCurrentRow()][getCurrentColumn()].getState()))
			playerDeath = true;
		if (countPearls() == 0 || playerDeath == true) {
			return true;
		}
		return false;
	}

	/**
	 * Returns true if the game is over and the player did not die on spikes.
	 * 
	 * @return true if the player wins, false otherwise
	 */
	public boolean won() {
		if (isOver() == true && playerDeath == false) {
			return true;
		}
		return false;
	}

	/**
	 * Performs a move along a cell sequence in the given direction, updating the
	 * score, the move count, and all affected cells in the grid. The method returns
	 * an array of Descriptor objects representing the cells in original cell
	 * sequence before modification, with their <code>movedTo</code> and
	 * <code>disappeared</code> status set to indicate the cells' new locations
	 * after modification.
	 * 
	 * @param dir direction of the move
	 * @return array of Descriptor objects describing modified cells
	 */
	public Descriptor[] move(Direction dir) {
		Cell[] cells = getCellSequence(dir);
		Descriptor[] descriptors = new Descriptor[cells.length];
		for (int i = 0; i < descriptors.length; i++) {
			descriptors[i] = new Descriptor(cells[i], i);
		}
		support.shiftMovableBlocks(cells, descriptors);
		support.shiftPlayer(cells, descriptors, dir);
		setCellSequence(cells, dir);
		if (cells.length > 2) {
			countOfMoves++;
		}
		return null;
	}

	/**
	 * Returns the current number of moves made in this game.
	 * 
	 * @return number of moves made
	 */
	public int getMoves() {
		return countOfMoves;
	}

	/**
	 * Returns the row index for the player's current location.
	 * 
	 * @return current row index for the player
	 */
	public int getCurrentRow() {
		for (int i = 0; i < getRows(); i++) {
			for (int j = 0; j < getColumns(); j++) {
				if (grid[i][j].isPlayerPresent())
                    return i;
			}
		}
		return -1;
	}

	/**
	 * Returns the row index for the player's current location.
	 * 
	 * @return current column index for the player
	 */
	public int getCurrentColumn() {
		for (int i = 0; i < getRows(); i++) {
			for (int j = 0; j < getColumns(); j++) {
				if (grid[i][j].isPlayerPresent())
					return j;
			}
		}
		return -1;
	}

	/**
	 * Returns the current score (number of pearls disappeared) for this game.
	 * 
	 * @return current score
	 */
	public int getScore() {
		return countPearlsGone();
	}

	/**
	 * Returns the number of pearls left in the grid.
	 * 
	 * @return number of pearls in the grid
	 */
	public int countPearls() {
		return pearls - countPearlsGone();
	}

	/**
	 * Return the number of pearls that are no longer on the board
	 * 
	 * @return number of pearls that are no longer on the board
	 */
	private int countPearlsGone() {
		int countPearls = 0;
		
		//Iterate through the array for every i value and then every sub value of i, j
		for (int i = 0; i < getRows(); i++) {
			for (int j = 0; j < getColumns(); j++) {
				if (grid[i][j].getState() == CellState.PEARL) {
					countPearls++;
				}
			}
		}
		countPearls = pearls - countPearls;
		return countPearls;
	}

	/**
	 * Finds a valid cell sequence in the given direction starting with the player's
	 * current position and ending with a boundary cell as defined by the method
	 * CellState.isBoundary. The actual cell locations are obtained by following
	 * getNextRow and getNextColumn in the given direction, and the sequence ends
	 * when a boundary cell is found. A boundary cell is defined by the
	 * CellState.isBoundary and is different depending on whether a movable block
	 * has been encountered so far in the cell sequence (the player can move through
	 * open gates and portals, but the movable blocks cannot). It can be assumed
	 * that there will eventually be a boundary cell (i.e., the grid has no infinite
	 * loops). The first element of the returned array is the cell containing the
	 * player, and the last element of the array is the boundary cell. This method
	 * does not modify the grid or any aspect of the game state.
	 * 
	 * @param dir direction of the sequence
	 * @return array of cells in the cell sequence
	 */
	public Cell[] getCellSequence(Direction dir) {
		int row = getCurrentRow();
		int column = getCurrentColumn();
		
		// Creates a string that we modify
		playerRecordPosition = "";
		ArrayList<Cell> endPosition = new ArrayList<>();
		
		// It stops when it hits a boundary
		while (!CellState.isBoundary(getCell(row, column).getState(), false)) {
			
			// Direction is up or down, gets the current and updated row and the column
			if (dir == Direction.UP || dir == Direction.DOWN) {
				endPosition.add(getCell(row, column));
				playerRecordPosition += row + "" + column;
				
				// One of the many different portal checks
				if (endPosition.get(endPosition.size() - 1).getState() == CellState.PORTAL) {
					int secondRow = row;
					row = getNextRow(row, column, dir, true);
					column = getNextColumn(secondRow, column, dir, true);
				} else {
					row = getNextRow(row, column, dir, false);
				}
				
			} // Up or down direction, gets the current and updated row and column
			else if (dir == Direction.RIGHT || dir == Direction.LEFT) {
				endPosition.add(getCell(row, column));
				playerRecordPosition += row + "" + column;
				if (endPosition.get(endPosition.size() - 1).getState() == PORTAL) {
					int temp = row;
					row = getNextRow(row, column, dir, true);
					column = getNextColumn(temp, column, dir, true);
				} else {
					column = getNextColumn(row, column, dir, false);
				}

			}
		}
		endPosition.add(getCell(row, column));
		return endPosition.toArray(new Cell[endPosition.size()]);
	}

	/**
	 * Sets the given cell sequence and updates the player position. This method
	 * effectively retraces the steps for creating a cell sequence in the given
	 * direction, starting with the player's current position, and updates the grid
	 * with the new cells. Exactly one cell in the given sequence must have the
	 * condition isPlayerPresent true. The given cell sequence can be assumed to be
	 * structurally consistent with the existing grid, e.g., no portal or wall cells
	 * are moved.
	 * 
	 * This whole nubbin parses strings rather than acts upon it as a cell array, and I apologize, but
	 * I love my strings.
	 * 
	 * @param cells updated cells to replace existing ones in the sequence
	 * @param dir   direction of the cell sequence
	 */
	public void setCellSequence(Cell[] cells, Direction dir) {
		
		/*
		 * Initializes variables to be modified later, including the current position and move position,
		 * the move column and row to be zero to be modified, and the current row and column 
		 */
		int moveableBlocks = 0;
		int playerPosition = 0, movePosition = 0;
		int moveCurrentRow = 0, moveCurrentColumn = 0;
		int currentRow = getCurrentRow();
		int currentColumn = getCurrentColumn();
		for (int i = 0; i < cells.length; i++) {
			if (cells[i].isPlayerPresent()) {
				playerPosition = i;
			} else if (CellState.isMovable(cells[i].getState())) {
				moveableBlocks++;
				movePosition = i;
			}
		}
		
		//When the player position is [-1, -1] on the cell array
		if (currentRow == -1 && currentColumn == -1) {
			currentRow = Integer.parseInt(playerRecordPosition.substring(playerPosition + playerPosition,
					playerPosition + playerPosition + 1));
			currentColumn = Integer.parseInt(playerRecordPosition.substring(playerPosition + playerPosition + 1,
					playerPosition + playerPosition + 2));
		}
		for (int i = playerPosition + moveableBlocks; i > playerPosition; i--) {
			if (cells[i].getState() == MOVABLE_NEG || cells[i].getState() == MOVABLE_POS) {
				moveCurrentRow = Integer.parseInt(
						playerRecordPosition.substring(movePosition + movePosition, movePosition + movePosition + 1));
				moveCurrentColumn = Integer.parseInt(playerRecordPosition.substring(movePosition + movePosition + 1,
						movePosition + movePosition + 2));
				
				//Down move
				if (dir == Direction.DOWN) {
					grid[moveCurrentRow][moveCurrentColumn] = new Cell(cells[i]);
					currentRow = getNextRow(moveCurrentRow, moveCurrentColumn, Direction.UP,
							grid[currentRow][currentColumn].getState() == CellState.PORTAL);
					
				//Up move
				} else if (dir == Direction.UP) {
					grid[moveCurrentRow][moveCurrentColumn] = new Cell(cells[i]);
					moveCurrentRow = getNextRow(moveCurrentRow, moveCurrentColumn, Direction.DOWN,
							grid[currentRow][currentColumn].getState() == CellState.PORTAL);
					
				//Right move
				} else if (dir == Direction.RIGHT) {
					grid[moveCurrentRow][moveCurrentColumn] = new Cell(cells[i]);
					moveCurrentColumn = getNextColumn(moveCurrentRow, moveCurrentColumn, Direction.LEFT,
							grid[currentRow][currentColumn].getState() == CellState.PORTAL);
					
				//Finally, left move. It is assumed, as it is the last direction available
				} else {
					grid[moveCurrentRow][moveCurrentColumn] = new Cell(cells[i]);
					moveCurrentColumn = getNextColumn(moveCurrentRow, moveCurrentColumn, Direction.RIGHT,
							grid[currentRow][currentColumn].getState() == CellState.PORTAL);
				}
			}
		}
		
		//If attempt to move with a cells length greater than 2, starts at player position and decreases 
		if (cells.length > 2) {
			for (int i = playerPosition; i >= 0; i--) {
				
				//Down direction move
				if (dir == Direction.DOWN) {
					grid[currentRow][currentColumn] = new Cell(cells[i]);
					currentRow = getNextRow(currentRow, currentColumn, Direction.UP,
							grid[currentRow][currentColumn].getState() == CellState.PORTAL);
					
				//Up direction move
				} else if (dir == Direction.UP) {
					grid[currentRow][currentColumn] = new Cell(cells[i]);
					currentRow = getNextRow(currentRow, currentColumn, Direction.DOWN,
							grid[currentRow][currentColumn].getState() == CellState.PORTAL);
					
				//Right hand move
				} else if (dir == Direction.RIGHT) {
					grid[currentRow][currentColumn] = new Cell(cells[i]);
					currentColumn = getNextColumn(currentRow, currentColumn, Direction.LEFT,
							grid[currentRow][currentColumn].getState() == CellState.PORTAL);
				
				//Finally, left hand move
				} else {
					grid[currentRow][currentColumn] = new Cell(cells[i]);
					currentColumn = getNextColumn(currentRow, currentColumn, Direction.RIGHT,
							grid[currentRow][currentColumn].getState() == CellState.PORTAL);
				}
			}

		}
	}

	/**
	 * Helper method returns the next row for a cell sequence in the given
	 * direction, possibly wrapping around. If the flag doPortalJump is true, then
	 * the next row will be obtained by adding the cell's row offset. (Normally the
	 * caller will set this flag to true when first landing on a portal, but to
	 * false for the second portal of the pair.)
	 * 
	 * @param row          row for current cell
	 * @param col          column for current cell
	 * @param dir          direction
	 * @param doPortalJump true if the next cell should be based on a portal offset
	 *                     in case the current cell is a portal
	 * @return next row number in a cell sequence
	 */
	public int getNextRow(int row, int col, Direction dir, boolean doPortalJump) {
		
		// If it does a portal jump
		if (doPortalJump == true) {
			int rowAndOffsetGrid = grid[getNextRow(row, col, dir, false)][col].getRowOffset();
			return rowAndOffsetGrid + row;
		} 
		
		// Now check to see if the direction is up or down on the bottom or top
		else if (row == getRows() - 1 && dir == Direction.DOWN || row == 0 && dir == Direction.UP) {
			if (dir == Direction.DOWN) {
				return 0;
			} else {
				return getRows() - 1;
			}
		} 
		
		// Regular case if they are in the middle
		else {
			if (dir == Direction.DOWN) {
				return row + 1;
			} else if (dir == Direction.UP) {
				return row - 1;
			} else {
				return row;
			}
		}
	}

	/**
	 * Helper method returns the next column for a cell sequence in the given
	 * direction, possibly wrapping around. If the flag doPortalJump is true, then
	 * the next column will be obtained by adding the cell's column offset.
	 * (Normally the caller will set this flag to true when first landing on a
	 * portal, but to false for the second portal of the pair.)
	 * 
	 * @param row          row for current cell
	 * @param col          column for current cell
	 * @param dir          direction
	 * @param doPortalJump true if the next cell should be based on a portal offset
	 *                     in case the current cell is a portal
	 * @return next column number in a cell sequence
	 */
	public int getNextColumn(int row, int col, Direction dir, boolean doPortalJump) {
		
		// If portal jump is true
		if (doPortalJump == true) {
			int columnAndOffsetGrid = grid[row][getNextColumn(row, col, dir, false)].getColumnOffset();
			return columnAndOffsetGrid + col;
		} 
		
		// If there is no portal jump, now check if it is left or right to get column at
		// the far side
		else if (col == getColumns() - 1 && dir == Direction.RIGHT || col == 0 && dir == Direction.LEFT) {
			if (dir == Direction.LEFT) {
				return getColumns() - 1;
			} else {
				return 0;
			}
		} 
		
		// Normal case for if they are in anywhere in the middle of the grid
		else {
			if (dir == Direction.RIGHT) {
				return col + 1;
			} else if (dir == Direction.LEFT) {
				return col - 1;
			} else {
				return col;
			}
		}
	}

}
