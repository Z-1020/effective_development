package marques;

import java.util.ArrayList;

public class ReadOnlyNode implements Node{
	protected char label;
	protected boolean marked;
	protected ArrayList<Node> children;

	/**
	 * Constructor for a node of a tree.
	 * @param label the character of the tree
	 */
	public ReadOnlyNode(char label) {
		super();
		this.label = label;
		this.children = new ArrayList<Node>();
		this.marked = false;
	}
	
	public ReadOnlyNode(char label, boolean mark) {
		super();
		this.label = label;
		this.children = new ArrayList<Node>();
		this.marked = mark;
	}
	
	private ReadOnlyNode(char label, boolean mark, Node... children) {
		super();
		this.label = label;
		this.children = new ArrayList<Node>();
		for(Node child : children) {
			this.children.add(child);
		}
		this.marked = mark;
	}

	/* (non-Javadoc)
	 * @see examen.Node#getLabel()
	 */
	@Override
	public char getLabel() {
		return label;
	}
	
	/* (non-Javadoc)
	 * @see examen.Node#isMarked()
	 */
	@Override
	public boolean isMarked() {
		return marked;
	}
	
	/**
	 * Returns the child at position position
	 * @param position the position of the required child.
	 * @return the child expected or null if it does not exist.
	 */
	@Override
	public Node getChild(int position) {
		if(position < children.size())
				return children.get(position);
		else return null;
	}

	/* (non-Javadoc)
	 * @see examen.Node#nbChildren()
	 */
	@Override
	public int nbChildren() {
		return children.size();
	}
		
	/**
	 * A static function that returns an example tree
	 */
	public static Node example1() {
		Node i = new ReadOnlyNode('i', false, new ReadOnlyNode('j'), new ReadOnlyNode('k'), new ReadOnlyNode('l',true), new ReadOnlyNode('m'));
		Node g = new ReadOnlyNode('g', true, new ReadOnlyNode('h',true), i);
		Node d = new ReadOnlyNode('d',false, g);
		Node c = new ReadOnlyNode('c', false, new ReadOnlyNode('e',true), new ReadOnlyNode('f'));
		Node a = new ReadOnlyNode('a', true, new ReadOnlyNode('b',true), c, d);
		return a;
	}
}