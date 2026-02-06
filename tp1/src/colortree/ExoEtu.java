package colortree;

public class ExoEtu {
		
		/**
		 * You can run some tests here, the output should be seen with 
		 * the little rocket
		 * @param args
		 */
		public static void main(String[] args) {
			// run some tests here if you want.
			ExoEtu monTravail = new ExoEtu();
			Node exampleTree = new ExampleTree();
			System.out.println(monTravail.encode(exampleTree));
			System.out.println("nombre de couleur rouge :" +monTravail.countColor(exampleTree,Color.RED));
			System.out.println(monTravail.countAtDepth(exampleTree,Color.RED, 2));
			System.out.println("Score : "+ monTravail.score(exampleTree));
		}

		/**
		 * Count the number of nodes of a given color in the tree.
		 * @param root the root node of the tree
		 * @param color the color whose vertices to count.
		 * @return the number of nodes in the tree with the given color.
		 */
		public int countColor(Node root, Color color) {
		int compteur = 0;
		if (root.getColor() == color) {
			compteur += 1;
		}
		for (Node child : root.getChildren()) {
			compteur = compteur + countColor(child, color);
		}

		return compteur;
	}

	/**
	 * Encode a tree into a string, where the nodes are represented by their color,
	 * and the children of a node are surrounded by '<' and '>'
	 * 
	 * @param root the root of the tree to encode
	 * @return the code as a String.
	 */
	public String encode(Node root) {
		String code = root.getColor().getCode() + "";
		if (root.nbChildren() > 0) {
			code = code + "<";
			for (Node child : root.getChildren()) {
				code = code + encode(child);
			}
			code = code + ">";
		}

		return code;
	}

	/**
	 * Counts the number of nodes at a given depth of a given color
	 * 
	 * @param root  the root of the tree to search
	 * @param col   the color to search
	 * @param depth the depth looked for. Root is at depth 0.
	 * @return the number of nodes of color col at depth depth.
	 */
	public int countAtDepth(Node root, Color col, int depth) {
		int compteur = 0;
		if (0 == depth) {
			if(root.getColor() == col) {
				return 1;
			}
		}
		
		for (Node child : root.getChildren()) {
			compteur = compteur + countAtDepth(child, col, depth-1);
		}

		return compteur;
	}

	/**
	 * Return the score of the given node.
	 * 
	 * The score of a node is 1 + the score of every child of different color.
	 * 
	 * @param root the node whose score is to compute
	 * @return the score.
	 */
	public int score(Node root) {
			int compteur = 1;
			
			if(root == null ) {
				return 0;
			}
			
			for(Node child : root.getChildren()) {
				
					
					if(root.getColor() != child.getColor()){
					    compteur = compteur + score(child);
					}
			}
			return compteur;
		}
		
		
}
