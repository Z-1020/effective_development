package interrupteurs;

public class Interrupteurs{	

	/** Cette fonction sera exécutée avant les tests basiques, 
	 * vous pouvez vous en servir.
	 */
	public static void pourTest() {
		// 
	}
	
	
	/***************************** Question 1 ************************************/
	/**
	 * Teste toutes les configurations d'interrupteurs de la machine.
	 * @param machine la machine a tester.
	 * @return true si vous avez trouve une solution.
	 */
	public static boolean toutTester(Machine machine) {
		return testerUnSeul( machine, 0);
	}
	
	public static boolean testerUnSeul(Machine machine, int index) {
	    if(index == machine.nbInterrupteurs()){
	        return machine.start();
	    }
		machine.leverInterrupteur(index);
		if(testerUnSeul(machine, index + 1)) {
			return true;
		}
		machine.baisserInterrupteur(index);
		if(testerUnSeul(machine, index + 1)) {
			return true;
		}
		return false;
	}
	/***************************** Question 2 ************************************/

	/**
	 * Teste toutes les configurations d'interrupteurs de la machine avec exactement 
	 * nbHaut interrupteurs leves.
	 * @param machine la machine a tester.
	 * @param nbHaut nombre d'interrupteurs en position haute comme lu sur la note.
	 * @return true si vous avez trouve une solution.
	 */
	public static boolean testerAvecNote(Machine machine, int nbHaut) {
		return testerUneNote(machine, 0, nbHaut);	
	}
	
	public static boolean testerUneNote(Machine machine, int index, int restant) {
	    if(index == machine.nbInterrupteurs()){
	        if(restant == 0){
	            return machine.start();
	        }
	        return false;
	    }
	    int interrupteurRestant = machine.nbInterrupteurs() - index;
	    if(restant > interrupteurRestant || restant <0){
	        return false;
	    }
		
		if(testerUneNote(machine, index + 1 , restant)) {
			return true;
		}
		machine.leverInterrupteur(index);
		if(testerUneNote(machine, index + 1 , restant - 1)) {
			return true;
		}
		machine.baisserInterrupteur(index);
		return false;
	}
	
	/***************************** Question 3 ************************************/

	/**
	 * Teste toutes les configurations d'interrupteurs de la machine tels que 
	 * deux interrupteurs voisins ne sont jamais tous deux leves.
	 * @param machine
	 * @return true si vous avez trouve une solution.
	 */
	public static boolean testerNonConsecutifs(Machine machine) {
		return testerLevierNonConsecutif(machine, 0 , false);
	}
	
		public static boolean testerLevierNonConsecutif(Machine machine, int index, boolean levierprecedent) {
	    if(index == machine.nbInterrupteurs()){
	            return machine.start();
	    }
	    if(testerLevierNonConsecutif(machine, index +1, false)){
	            return true;
	        }
	    
	    if(!levierprecedent){
	        machine.leverInterrupteur(index);
	        if(testerLevierNonConsecutif(machine, index +1, true)){
	            return true;
	        }
	        machine.baisserInterrupteur(index);
	    }
		
		return false;
	}
	
	
	/***************************** Question 4 ************************************/

	/**
	 * Teste toutes les configurations d'interrupteurs de la machine avec exactement 
	 * nbHaut interrupteurs leves, et sans deux interrupteurs leves consecutifs.
	 * @param machine la machine a tester.
	 * @param nbHaut nombre d'interrupteurs en position haute comme lu sur la note.
	 * @return true si vous avez trouve une solution.
	 */
	public static boolean testerNonConsecutifsAvecNote(Machine machine, int nbHaut) {
		return testerNonConsecutifNote(machine, 0, nbHaut, false);
	}
	
	public static boolean testerNonConsecutifNote(Machine machine, int index, int restant, boolean levierPrecedent){
	    if(index == machine.nbInterrupteurs()){
	        if(restant == 0){
	            return machine.start();
	        }
	        return false;
	    }
	    int interrupteurRestant = machine.nbInterrupteurs() - index;
	    if(restant > interrupteurRestant || restant <0){
	        return false;
	    }
	    if(testerNonConsecutifNote(machine, index +1 , restant, false)){
	        return true;
	    }
	    
	    if(!levierPrecedent){
	        machine.leverInterrupteur(index);
	       if( testerNonConsecutifNote(machine, index +1 , restant -1, true) ){
	            return true;
	        }
	        machine.baisserInterrupteur(index);
	       
	    }
	    return false;
	    
	}
}
