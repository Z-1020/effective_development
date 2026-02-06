package tp3.sudoku;


/**
 * Class Square is here to handle squares of a Sudoku game.
 * @author dorbec
 * 
 * @version 1.2
 */
public class Square extends Observable{
	private int value;
	private boolean forced;	
	
	/**
	 * Constructor, with a value
	 * @param value the value to put in the square
	 * @param forced whether this value is the initial value for the board.
	 */
	public Square(int value, boolean forced) {
		super();
		this.value = value;
		this.forced = forced;
	}

	/**
	 * Sets a new value to the square, provided the square is not forced.
	 * @param value the value
	 * @return false when the change is not allowed.
	 */
	public boolean set(int value) {
		if(forced) {
			return false;
		}
		else {
			this.value = value;
			//notifyObservers();
			return true;
		}
	}
	
	/**
	 * Access to the value of the square
	 * @return the value, 0 if no value is set.
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * Check whether the square is forced.
	 * @return true if the square is forced.
	 */
	public boolean isForced() {
		return forced;
	}

	/** 
	 * Empty the square if it is not forced.
	 */
	public void unset() {
		set(0);
		notifyObservers();
	}

	/** 
	 * Places a value in the square, which is force. 
	 * This is only for initialization purpose, 
	 * set is the correct method for ingame changes.
	 * @param value the value to force.
	 */
	public void putValue(int value) {
		this.value = value;
		notifyObservers();
		this.forced = true;
	}
}
