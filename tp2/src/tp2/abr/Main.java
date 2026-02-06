package tp2.abr;

public class Main {

	public static void main(String[] args) {
		Node node = Node.exampleTree();
		System.out.println(node.toSortedString());
		System.out.println(node.contains(6.0));

	}

}
