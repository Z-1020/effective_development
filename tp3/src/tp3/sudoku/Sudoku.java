package tp3.sudoku;


/**
 * A class that allows to store a game of Sudoku.
 * It uses the class Square for each square in the Sudoku.  
 * @author dorbec
 * @version 1.2
 */
public class Sudoku implements Observer{

	public static final int SIZE_RT = 3;
	public static final int SIZE = SIZE_RT*SIZE_RT;
	private static final boolean COLOR = false; // This can put char in color if used in a console.
	private Square[][] grid;
	private Square firstEmpty;

	/**
	 * Constructor, builds an empty Sudoku.
	 */
	public Sudoku() {
		super();
		firstEmpty = null;
		this.grid = new Square[SIZE][SIZE];
		for (int line =0; line<SIZE; line++) {
			for(int column=0; column<SIZE; column++) {
				Square square = new Square(0,false);
				square.addObserver(this);
				grid[line][column] = square;
			}
		}
	}

	/**
	 * Empties a square where a choice was made.
	 * @param square the square that should be emptied.
	 */
	public void unset(Square square) {
		square.unset();
		firstEmpty = null;
	}

	/**
	 * Selects a value for a square.
	 * It is necessary to use this method when using the method 
	 * @param square the square to update
	 * @param value the value to put in.
	 */
	public void set(Square square, int value) {
		square.set(value);
		if(square == firstEmpty)
			firstEmpty = null;
	}

	/**
	 * Searches and returns the first square that has no value set.
	 * 
	 * The order for exploring the squares is line by line, from top to bottom.
	 * If all squares have a value set, returns null.
	 * 
	 * @return the first square that has no value set.
	 * @type Square
	 */
	public Square firstEmptySquare() {
		if(firstEmpty != null && firstEmpty.getValue() != 0)
			firstEmpty = null;
		if(firstEmpty != null) {		
			return firstEmpty;
		}
		else
			if(!isFilled())
				return firstEmpty;
			else
				return null;
	}

	/**
	 * Check whether there are no empty squares left.
	 * Does not check whether the filling follows the sudoku rules.
	 * Updates the reference to the first empty square
	 * @return true if all squares contain a value
	 * @type boolean
	 */
	public boolean isFilled() {
		int line = 0;		
		if(firstEmpty!= null && firstEmpty.getValue() != 0)
			firstEmpty = null;
		while(line < SIZE && firstEmpty == null) {
			int column = 0;
			while(column < SIZE && firstEmpty == null) {
				if(grid[line][column].getValue() > 0) 
					column++;
				else firstEmpty = grid[line][column];
			}
			line++;
		}
		return(firstEmpty == null);
	}

	/**
	 * This is a private method for placing values in the grid.
	 * It does not check anything.
	 * It places the value as forced, so that should not be changed later.
	 * This should be used only for initializing a grid.
	 * @param line
	 * @param column
	 * @param value
	 */
	private void forceValue(int line, int column, int value) {
		this.grid[line][column].putValue(value);
		firstEmpty = null;
	}

	@Override
	public void update() {
		firstEmpty = null;
	}

	/**
	 * returns an array of all the elements of the line required.
	 * @param line an integer from 0 to SIZE-1 (usually from 0 to 8)
	 * @return an array of squares corresponding to the line.
	 * @type Square[SIZE]
	 */
	public Square[] getLine(int line) {
		return grid[line];
	}

	/**
	 * returns an array of all the elements of the column required.
	 * @param column an integer from 0 to SIZE-1 (usually from 0 to 8)
	 * @return an array of squares corresponding to the column.
	 * @type Square[SIZE]
	 */
	public Square[] getColumn(int column) {
		Square[] col = new Square[SIZE];
		for(int line = 0; line < SIZE; line ++) {
			col[line] = grid[line][column];
		}
		return col;
	}

	/**
	 * Returns an array of all the elements of the required block.
	 * By a block, we mean a square of SIZE_RT*SIZE_RT (usually 3*3)
	 * @param number Position of the square required. Top left is 0, Top right is 2, Bottom right is 8
	 * @return
	 */
	public Square[] getBlock(int number) {
		Square[] block = new Square[SIZE];
		for(int line = 0; line < SIZE_RT; line ++)
			for(int column = 0; column < SIZE_RT; column ++) {
				block[SIZE_RT*line+column] = grid[line+SIZE_RT*((int) number/SIZE_RT)][column+SIZE_RT*(number%SIZE_RT)];
			}
		return block;
	}

	/**
	 * Returns the square at the given position.
	 */
	public Square getSquare(int line, int column) {
		if(line < 0 || line > SIZE || column < 0 || column > SIZE)
			return null;
		return grid[line][column];
	}


	/**
	 * Computes the number of the block for a given line and column
	 * @param line the line number
	 * @param column the column number
	 * @return the block number.
	 */
	public int whichBlock(int line, int column) {
		return SIZE_RT * (line / SIZE_RT) + column / SIZE_RT;
	}

	@Override
	public String toString() {
		String res= "";
		for(int line = 0; line < SIZE; line ++) {
			for(int column = 0; column < SIZE; column++) {
				res += " ";
				int v = grid[line][column].getValue();
				boolean forced = grid[line][column].isForced();
				if(v > 0) {
					if(forced && COLOR) {
						res=res+"\033[33m"; // Only for Unix terminals
					}
					res = res + v;
					if(forced && COLOR) {
						res=res+"\033[0m"; // Only for Unix terminals
					}
				}
				else 
					res += " ";
				if(forced && !COLOR) 
					res += ".";
				else 
					res += " ";
				if(column == 2 || column == 5)
					res += "|";
			}
			res += "\n";
			if(line == 2 || line == 5) {
				for (int i = 0; i < SIZE; i ++)
					res += "---";
				res += "--\n";
			}
		}
		return res;
	}

	/**
	 * Builds an example of a obvious sudoku board.
	 * @return the board built.
	 */
	public static Sudoku exampleForDummies() {
		Sudoku sudoku = new Sudoku();
		for(int i = 0; i < 9; i ++) {
			for(int j = 0; j < 9; j++) {
				if((9*i+j)%14!=0) {
					sudoku.forceValue(i,j,((j+3*i)+i/3)%9+1);
				}
			}
		}
		return sudoku;
	}	

	/**
	 * Builds an example of a sudoku board.
	 * @return the board built.
	 */
	public static Sudoku exampleVerySimple() {
		Sudoku sudoku = new Sudoku();
		for(int i = 0; i < 9; i ++) {
			for(int j = 0; j < 9; j++) {
				if((i+j)%5!=0) {
					sudoku.forceValue(i,j,((j+3*i)+i/3)%9+1);
				}
			}
		}
		return sudoku;
	}


	/**
	 * Builds an example of a sudoku board.
	 * @return the board built.
	 */
	public static Sudoku example() {
		Sudoku sudoku = new Sudoku();
		sudoku.forceValue(0,2,3);
		sudoku.forceValue(0,4,2);
		sudoku.forceValue(0,6,7);
		sudoku.forceValue(0,7,5);
		sudoku.forceValue(1,4,4);
		sudoku.forceValue(2,0,6);
		sudoku.forceValue(2,2,9);
		sudoku.forceValue(2,3,5);
		sudoku.forceValue(3,0,7);
		sudoku.forceValue(3,3,4);
		sudoku.forceValue(3,6,3);
		sudoku.forceValue(3,8,5);
		sudoku.forceValue(4,1,6);
		sudoku.forceValue(4,5,2);
		sudoku.forceValue(5,0,5);
		sudoku.forceValue(5,3,3);
		sudoku.forceValue(5,6,8);
		sudoku.forceValue(5,8,1);
		sudoku.forceValue(6,0,9);
		sudoku.forceValue(6,2,1);
		sudoku.forceValue(6,3,8);
		sudoku.forceValue(7,4,9);
		sudoku.forceValue(8,2,7);
		sudoku.forceValue(8,4,6);
		sudoku.forceValue(8,6,9);
		sudoku.forceValue(8,7,4);
		return sudoku;
	}

	/**
	 * Builds an example of a sudoku board (supposedly harder to solve).
	 * @return the board built.
	 */
	public static Sudoku exampleHard() {
		Sudoku sudoku = new Sudoku();
		sudoku.forceValue(0,0,1);
		sudoku.forceValue(0,3,2);
		sudoku.forceValue(0,5,8);
		sudoku.forceValue(0,8,9);
		sudoku.forceValue(1,1,2);
		sudoku.forceValue(1,7,3);
		sudoku.forceValue(2,2,5);
		sudoku.forceValue(2,4,9);
		sudoku.forceValue(2,6,6);
		sudoku.forceValue(3,1,3);
		sudoku.forceValue(3,7,1);
		sudoku.forceValue(4,2,9);
		sudoku.forceValue(4,6,8);
		sudoku.forceValue(5,0,2);
		sudoku.forceValue(5,3,4);
		sudoku.forceValue(5,4,6);
		sudoku.forceValue(5,5,1);
		sudoku.forceValue(5,8,5);
		sudoku.forceValue(6,3,5);
		sudoku.forceValue(6,5,9);
		sudoku.forceValue(7,0,7);
		sudoku.forceValue(7,1,9);
		sudoku.forceValue(7,7,2);
		sudoku.forceValue(7,8,4);
		sudoku.forceValue(8,1,5);
		sudoku.forceValue(8,7,7);
		return sudoku;
	}

	/**
	 * Builds an example of a sudoku board with multiple solutions (4).
	 * @return the board built.
	 */
	public static Sudoku exampleMultiple() {
		Sudoku sudoku = new Sudoku();
		sudoku.forceValue(0,0,1);
		sudoku.forceValue(0,3,2);
		sudoku.forceValue(0,5,8);
		sudoku.forceValue(0,8,9);
		sudoku.forceValue(1,1,2);
		sudoku.forceValue(1,7,3);
		sudoku.forceValue(2,4,9);
		sudoku.forceValue(2,6,6);
		sudoku.forceValue(3,1,3);
		sudoku.forceValue(3,7,1);
		sudoku.forceValue(4,2,9);
		sudoku.forceValue(4,6,8);
		sudoku.forceValue(5,3,4);
		sudoku.forceValue(5,4,6);
		sudoku.forceValue(5,5,1);
		sudoku.forceValue(5,8,5);
		sudoku.forceValue(6,3,5);
		sudoku.forceValue(6,5,9);
		sudoku.forceValue(7,0,7);
		sudoku.forceValue(7,1,9);
		sudoku.forceValue(7,7,2);
		sudoku.forceValue(7,8,4);
		sudoku.forceValue(8,1,5);
		sudoku.forceValue(8,7,7);
		return sudoku;
	}

	/**
	 * Builds an example of a sudoku board with a single solution.
	 * @return the board built.
	 */
	public static Sudoku exampleVeryHard() {
		Sudoku sudoku = new Sudoku();
		sudoku.forceValue(0,0,5);
		sudoku.forceValue(0,2,3);
		sudoku.forceValue(0,6,4);
		sudoku.forceValue(0,8,6);
		sudoku.forceValue(1,0,9);
		sudoku.forceValue(1,8,3);
		sudoku.forceValue(2,1,6);
		sudoku.forceValue(2,4,5);
		sudoku.forceValue(2,7,8);
		sudoku.forceValue(3,1,4);
		sudoku.forceValue(3,7,9);
		sudoku.forceValue(4,0,6);
		sudoku.forceValue(4,4,9);
		sudoku.forceValue(4,8,2);
		sudoku.forceValue(5,3,4);
		sudoku.forceValue(5,5,7);
		sudoku.forceValue(6,0,1);
		sudoku.forceValue(6,8,4);
		sudoku.forceValue(7,0,2);
		sudoku.forceValue(7,2,9);
		sudoku.forceValue(7,4,1);
		sudoku.forceValue(7,6,5);
		sudoku.forceValue(7,8,7);
		sudoku.forceValue(8,0,4);
		sudoku.forceValue(8,3,2);
		sudoku.forceValue(8,4,3);
		sudoku.forceValue(8,5,5);
		sudoku.forceValue(8,8,9);		
		return sudoku;
	}
	
	
	public static void main(String[] args) {
		Sudoku s = exampleVerySimple();
		System.out.println(s);
		s = exampleVeryHard();
		System.out.println(s);
	}

}
