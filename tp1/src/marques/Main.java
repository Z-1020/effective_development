package marques;

public class Main {

	public static void main(String[] args) {
		TestsBasiques test = new TestsBasiques();
		test.question1();

	}
	public static void afficheMarques(Node tree) {
		if(tree.isMarked() == true) {
			System.out.println(tree.getLabel());
		}
		for(int i=0; i< tree.nbChildren(); i++) {
			Node child = tree.getChild(i);
			afficheMarques(child);
		}
	}

}
