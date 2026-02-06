package marques;

public class TestsBasiques {

	final static int nbQuestions = 7;
	final static String SEP = "*******************************************************************";
	private String affichage;
	private int note[];

	public TestsBasiques() {
		note = new int[nbQuestions];
		affichage = "";
	}

	public void affiche(String S) {
		affichage += S + " ";
	}

	public Node getTree() {
		return ReadOnlyNode.example1();
	}



	public void question1() {
		Node tree = getTree();
		affichage = "";
		String expected = "a b e g h l ";
		System.out.println("Lancement des tests sur la question 1.\nCe qui s'affiche ici n'est pas pris en compte..");
		try {
			Reponse.afficheMarques(tree);
		}
		catch(Exception e){
			System.err.println("Le programme a leve une exception "+ e);
			return;
		}
		System.out.println("\n");

		System.out.println(SEP);
		System.out.println("Question 1 :");
		System.out.println("------------");
		System.out.print("Resultat obtenu :\n     ");
		System.out.println(affichage);
		System.out.println("a comparer avec \n     "+ expected );
		System.out.println(SEP + "\n");
		if(affichage.equals(expected)) 
			note[0] = 10;
		else 
			note[0] = 0;
	}


	public void question2() {
		Node tree = getTree();
		affichage = "";
		int expected = 4;
		int result;
		try {
			result = Reponse.retourneProfondeurMaxMarque(tree);
		}
		catch(Exception e){
			System.err.println("Le programme a leve une exception "+ e);
			System.out.println(SEP+"\n");
			return;
		}
		if(result == expected) 
			note[1] = 10;
		else 
			note[1] = 0;
		System.out.println(SEP);
		System.out.println("Question 2 :");
		System.out.println("------------");
		System.out.print("Resultat obtenu :\n     ");
		try {
			System.out.println("Profondeur " + result  );
			System.out.println("a comparer avec \n     "+ "Profondeur " + expected );
		}
		catch(Exception e) {
			System.out.println("Echec, lors de l'execution.");
		}
		System.out.println(SEP + "\n");
	}
	
	public void question3() {
		Node tree = getTree();
		affichage = "";
		String expected = "a ab ace adg adgh adgil ";
		System.out.println("Lancement des tests sur la question 3.");

		try {
			Reponse.afficheCheminMarques(tree);
		}
		catch(Exception e){
			System.err.println("Le programme a leve une exception "+ e);
			return;
		}
		System.out.println("\n");

		System.out.println(SEP);
		System.out.println("Question 3 :");
		System.out.println("------------");
		System.out.print("Resultat obtenu :\n     ");
		System.out.println(affichage);
		System.out.println("a comparer avec \n     "+ expected );
		System.out.println(SEP + "\n");
		if(affichage.equals(expected)) 
			note[2] = 10;
		else 
			note[2] = 0;
	}


	public void bilan() {
		System.out.println(SEP);
		System.out.println("Bilan :");
		System.out.println("-------");
		for(int i = 0; i< nbQuestions; i++) {
			System.out.print("Question " + Integer.toString(i+1) + " : ");
			if(note[i] == 10) 
				System.out.println("Ok");
			else if(note[i] > 0 )
				System.out.println("Resultat partiel");
			else 
				System.out.println("Echec");
		}
		System.out.println(SEP);
	}


}

