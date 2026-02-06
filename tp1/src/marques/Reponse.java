package marques;

public class Reponse {
	private static Tests tests = new Tests();
	
	/** 
	 * Lance quelques tests basiques sur vos fonctions.
	 */
	public static void main(String[] args) {
		// section 1
		// test basique question 1
		tests.question1();
		// test basique question 2
		tests.question2();
		// test basique question 3
		tests.question3();
		
		// bilan
	//	tests.bilan();
		System.exit(0);
	}

	/**
	 * Fonction affiche, a utiliser pour tous les affichages demandes.
	 * @param S Chaine de caracteres a afficher.
	 */
	private static void affiche(String S) {
		tests.affiche(S);
	}

	/*** fin du preambule, la suite est a modifier ***/
	
	/*****************************************************************************/
	/***************************** Section 1 ************************************/
	/*****************************************************************************/
	
	/** les questions suivantes sont testees initialement sur cet arbre,
	 * bien plus pour l'evaluation!
	 */
	 @SuppressWarnings("unused")
	private Node exampleDArbre = tests.getTree();
	
	/***************************** Question 1 ************************************/
	
	/**
	 * Question 1 : afficher les labels des sommets marques.
	 * ATTENTION, rappel : Les affichages se font avec la fonction affiche(String S)
	 */
	public static void afficheMarques(Node tree) {
		// REMPLACER la ligne suivante par votre code:
		if(tree.isMarked() == true) {
			affiche(tree.getLabel()+"");
		}
		for(int i=0; i< tree.nbChildren(); i++) {
			Node child = tree.getChild(i);
			afficheMarques(child);
		}
	}
	
	
	
	/***************************** Question 2 ************************************/
	
	/**
	 * Question 2 : Retourne la profondeur du noeud marque le plus profond. 
	 * Pas d'affichage ici.
	 */
	
	
	public static int retourneProfondeurMaxMarque(Node tree){
		int profondeurMax =-1;
		if(tree == null){
		    return -1;
		}
		if((tree.isMarked())) {
			profondeurMax = 0;
		}
		for(int i=0; i<tree.nbChildren(); i++){
			Node child = tree.getChild(i);
			int profondeurEnfant = retourneProfondeurMaxMarque(child);
			if(profondeurEnfant != -1){
			    if(profondeurEnfant >= profondeurMax){
			    profondeurMax = profondeurEnfant +1;
			}
		}
		}
		return profondeurMax;
	}
	
	
	/***************************** Question 3 ************************************/
	
	/**
	 * Question 3 : afficher les labels de tous les noeuds sur le chemin de la racine a chacun des des sommets marques.
	 * ATTENTION, rappel : Les affichages se font avec la fonction affiche(String S)
	 */
	public static void afficheCheminMarques(Node tree) {
	    if(tree != null) {
	    	afficheChemin(tree, "");
	    }
	}
	
	public static void afficheChemin(Node tree, String chemin) {
		String cheminMarque ="";
		if(chemin.equals("")) {
			cheminMarque = ""+ tree.getLabel();
		}
		else {
			cheminMarque = chemin + "" + tree.getLabel();
		}
		if(tree.isMarked()) {
			affiche(cheminMarque);
		}
		for(int i=0; i<tree.nbChildren(); i++){
			Node child = tree.getChild(i);
			afficheChemin(child, cheminMarque);
		}

	}
	
	
}
