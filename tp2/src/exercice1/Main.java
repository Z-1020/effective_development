package exercice1;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Table table = new Table();
		System.out.println(table.hashcode(32));
		System.out.println(table.hashcode(21));
		System.out.println(table.hashcode(456));
		table.add(456);
		table.add(21);
		table.add(32);
		System.out.println(table.contains(456));
		table.remove(32);
		table.remplirAleatoire();
		System.out.println(System.currentTimeMillis());
		
		table.contains(75539);

	}

}
