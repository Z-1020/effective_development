package marques;

public interface Node {

	/**
	 * Returns the label of the node.
	 * @return the label of the node
	 * @type char
	 */
	char getLabel();

	/**
	 * Inform whether the node is marked
	 * @return a boolean whether the node is marked 
	 */
	boolean isMarked();

	/**
	 * Returns the child at position position
	 * @param position the position of the required child.
	 * @return the child expected or null if it does not exist.
	 */
	Node getChild(int position);

	/**
	 * Returns the maximum number of children in the node.
	 * @return
	 */
	int nbChildren();
	
}