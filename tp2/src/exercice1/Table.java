package exercice1;

import java.util.LinkedList;
import java.util.Random;

public class Table {
	
	LinkedList<String>[] tableauListe;
	
	public Table() {
		this.tableauListe = new LinkedList[100];
		for(int i=0; i<100; i++)
		this.tableauListe[i] = new LinkedList<>();
	}
	
	public int hashcode(int value) {
		value = Math.abs(value);
	    int sum = 0;

	    while (value > 0) {
	        sum += value % 100;
	        value /= 100;
	    }

	    return sum % 100;
	}
	
	public void add(int value) {
		int cle = hashcode(value);
		this.tableauListe[cle].add(""+value );
		System.out.println(this.tableauListe[cle]);
			
	}
	
	public boolean contains(int value) {
		int cle = hashcode(value);
		String chaine = "" + value;
		for(int i=0; i<this.tableauListe[cle].size(); i++) {
			if(this.tableauListe[cle].get(i).equals(chaine)) {
				return true;
			}
		}
		return false;
	}
	
	public void remove(int value) {
		int cle = hashcode(value);
		String chaine = "" + value;
		for(int i=0; i< this.tableauListe[cle].size(); i++) {
			if(this.tableauListe[cle].get(i).equals(chaine)) {
				this.tableauListe[cle].remove(i);
			}
		}
			
	}
	
	public void remplirAleatoire() {
		Random random = new Random();
		for(int i=0; i<5000; i++) {
			int nb = random.nextInt(0, 100000);
			add(nb);
		}
	}
	

}
