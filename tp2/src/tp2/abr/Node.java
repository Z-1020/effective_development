package tp2.abr;

public class Node {
	private double value;
	
	private Node left = null;
	private Node right = null;
	
	public Node(double value) {
		this.value = value;
	}
	
	public double getValue() {
		return value;
	}
	
	public Node getLeftChild() {
		return left;
	}
	
	public boolean hasLeftChild() {
		return left != null;
	}
	
	public Node getRightChild() {
		return right;
	}
	
	public boolean hasRightChild() {
		return right != null;
	}
	
	public void setLeftChild(Node left) {
		this.left = left;
	}
	
	public void setRightChild(Node right) {
		this.right = right;
	}
	
	@Override
	public String toString() {
		return toString("");
	}
	
	public String toString(String prefixe) {
		String res = "";
		if(hasRightChild()) {
			res += getRightChild().toString(prefixe+"  / ");
		}
		res += prefixe + value + "\n";
		if(hasLeftChild()) {
			res += getLeftChild().toString(prefixe+"  \\ ");
		}
		return res;
	}
	
	public static Node exampleTree() {
		Node t = new Node(3.4);
		Node tl = new Node(1.0);
		t.setLeftChild(tl);
		tl.setLeftChild(new Node(0.2));
		tl.setRightChild(new Node(2.8));
		Node tr = new Node(6.0);
		t.setRightChild(tr);
		Node trr = new Node(8.1);
		tr.setRightChild(trr);
		Node trrl = new Node(6.4);
		trr.setLeftChild(trrl);
		trrl.setLeftChild(new Node(6.1));
		Node trl = new Node(4.9);
		tr.setLeftChild(trl);
		trl.setRightChild(new Node(5.9));
		return t;
	}
	
	
	
	/** 
	 * affiche les valeurs contenues dans l'arbre dans l'ordre croissant.
	 */
	public String toSortedString() {
		String res = "";

		// 1. Sous-arbre gauche
		if (hasLeftChild()) {
			res += left.toSortedString();
		}

		// 2. Nœud courant
		res += value + " ";

		// 3. Sous-arbre droit
		if (hasRightChild()) {
			res += right.toSortedString();
		}

		return res;
	}
	
	/**
	 * Vérifie si l'arbre contient la valeur en paramètre
	 * On utilise une méthode statique pour éviter 
	 * d'avoir des problèmes avec un arbre vide (null) 
	 * @param tree l'arbre
	 * @param value la valeur
	 */
	public static boolean contains(Node tree, double value) {
		//TODO
		if(tree == null) {
			return false;
		}
		if(tree.getValue() == value) {
			return true;
		}
		boolean res = false; 
		if(tree.hasLeftChild()) {
			return contains(tree.getLeftChild(), value);
		}
		if(tree.hasRightChild()) {
			return contains(tree.getRightChild(), value);
		}
		return false;
	}
	
	public boolean contains(double value) {
		return contains(this, value);
	}
	
	/**
	 * @brief Insère une nouvelle valeur dans l'arbre
	 * 
	 * Ici, ce n'est plus pertinent d'utiliser une méthode statique,
	 * car on a besoin d'accéder au noeud null depuis le père 
	 * @param value la valeur à insérer
	 */
	public void insert(double value) {
		//TODO
		System.err.println("Fonction à écrire");
	}

	/**
	 * @brief retire une valeur de l'arbre
	 * @param value la valeur à retirer
	 */
	public void remove(double value) {
		//TODO
		System.err.println("Fonction à écrire");		
	}
	
}
