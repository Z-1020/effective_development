package colortree;

import java.util.ArrayList;
import java.util.Random;

public class FullNode implements Node {
	private Color color;
	private ArrayList<FullNode> children;
	private boolean marked = false;
	private static Random random = new Random();
	
	
	/**
	 * Constructor for a node of a tree.
	 * @param color the character used for label
	 */
	public FullNode(Color color) {
		super();
		this.color = color;
		this.children = new ArrayList<FullNode>();
	}
	
	/**
	 * Constructor that directly include the childrens.
	 * @param color label of the node
	 * @param children children nodes.
	 */
	public FullNode(Color color, FullNode... children) {
		this(color);
		for(FullNode child : children) {
			this.children.add(child);
		}
	}

	/* (non-Javadoc)
	 * @see tpnote2019.tests.Node#getColor()
	 */
	@Override
	public Color getColor() {
		return this.color;
	}

	/* (non-Javadoc)
	 * @see tpnote2019.tests.Node#setColor(tpnote2019.Color)
	 */
	@Override
	public void setColor(Color color) {
		this.color = color;
	}
	
	/* (non-Javadoc)
	 * @see tpnote2019.tests.Node#getChildren()
	 */
	@Override
	public FullNode[] getChildren() {
		FullNode[] mychildren = new FullNode[children.size()];
		int i = 0;
		for(FullNode child : children) {
			mychildren[i] = child;
			i++;
		}
		return mychildren;
	}
	


	/* (non-Javadoc)
	 * @see tpnote2019.tests.Node#nbChildren()
	 */
	@Override
	public int nbChildren() {
		return children.size();
	}
	
	/**
	 * adds a child to the node, on rightmost position.
	 * @param child the AnagramNode to add
	 */
	public void addChild(FullNode child) {
		children.add(child);
	}
	/**
	 * adds a child to the node, on rightmost position.
	 * @param children the AnagramNode to add
	 */
	public void addChild(FullNode... children) {
		for (FullNode child : children)
		  this.children.add(child);
	}

	public void mark() {
		this.marked=true;
	}
	
	public void unmark() {
		this.marked=false;
	}
	
	public boolean isMarked() {
		return marked;
	}
	
	/**
	 * Switch the position of the two nodes as children
	 * @param child1 the first node
	 * @param child2 the second node
	 * @throws IllegalStateException when trying to switch position of non existing nodes
	 */
	protected void switchChildren(FullNode child1, FullNode child2) throws IllegalStateException{
		int pos1 = children.indexOf(child1);
		int pos2 = children.indexOf(child2);
		if(pos1 == -1 || pos2 == -1) {
			throw(new IllegalStateException("Trying to switch position of non existing children"));
		}
		children.set(pos1, child2);
		children.set(pos2, child1);
	}

	/**
	 * Switch the position of the two nodes as children
	 * @param pos1 the position of the first node
	 * @param pos2 the position of the second node
	 * @throws IndexOutOfBoundsException when position are inconsistent with the number of children
	 */
	protected void switchChildren(int pos1, int pos2) throws IndexOutOfBoundsException{
		FullNode child1 = children.get(pos1);
		FullNode child2 = children.get(pos2);
		children.set(pos1, child2);
		children.set(pos2, child1);
	}
	

	
	/** 
	 * Static functions for generating.
	 */
	
	/**
	 * Makes a random Color as a character in range [a-zA-Z]
	 */
	private static Color randomColor(Random random, int nbColors) {
		Color color = Color.get(random.nextInt(nbColors));
		return color;
	}
	
	/**
	 * Builds a random tree with random colors.
	 */
	public static FullNode randomTree(Random rand, int nbColors) {
		FullNode root = new FullNode(randomColor(rand, nbColors));
		
		while(rand.nextBoolean()) {
			root.addChild(randomTree(rand,nbColors));
		}
		return root;		
	}
	
	public static FullNode randomTree(int seed) {
		Random rand = new Random(seed);
		int nbCol = 2;
		if(seed > 100) {
			nbCol = rand.nextInt(Color.nbColors()-2)+2;
		}
		return randomTree(rand, nbCol);		
	}
	
	public static FullNode randomTree() {
		return randomTree(random, 2);
	}
	
}
