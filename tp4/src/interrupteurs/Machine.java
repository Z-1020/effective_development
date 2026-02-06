package interrupteurs;

public interface Machine {

	/**
	 * indique le nombre total d'interrupteurs de la machine.
	 * @return
	 */
	int nbInterrupteurs();

	/**
	 * Leve l'interrupteur numero i
	 * @param numero de l'interrupteur a lever.
	 */
	void leverInterrupteur(int numero);

	/**
	 * Baisse l'interrupteur numero i
	 * @param numero de l'interrupteur a baisser.
	 */
	void baisserInterrupteur(int numero);

	/**
	 * Essaye d'allumer la machine selon la configuration actuelle
	 * @return true si la machine demarre!
	 */
	boolean start();
	
	/**
	 * Indique si l'interrupteur en position numero est leve par un booleen
	 * @param numero de l'interrupteur interroge
	 * @return true si l'interrupteur est leve
	 */
	boolean estLeve(int numero);

}