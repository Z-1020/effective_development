package colortree;

public interface Node {

	/**
	 * @return the color of the node.
	 */
	Color getColor();

	/**
	 * Changes the color of the node
	 * @param color the new color to set
	 */
	void setColor(Color color);

	/**
	 * @return an array with the children.
	 */
	Node[] getChildren();

	/**
	 * @return the number of children of the node.
	 */
	int nbChildren();

}
