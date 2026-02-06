package mastermind;

import java.util.Random;

public class MasterMind {

	private int nbPegs;
	private int nbColors;
	private Color[] config;
	private Color[] solution;
	private boolean cows; // whether it is possible to know the colors from the config.
	
	/** generates a basic MasterMind.
	 * Test will be made with more variety.
	 */
	public MasterMind() {
		this(0, false, 4, 6);
	}
	
	/**
	 * generates a MasterMind
	 * @param seed for reproducibility
	 * @param cows whether the colors in the combination are known
	 * @param nbPegs number of pegs.
	 * @param nbColors number of colors.
	 */
	public MasterMind(int seed, boolean cows, int nbPegs, int nbColors) {
		Random rand = new Random(seed);
		this.cows = cows;
		this.nbPegs = nbPegs;
		if(nbColors > Color.nbColors()) 
			nbColors = Color.nbColors();
		this.nbColors = nbColors;
		config = new Color[nbPegs];
		solution = new Color[nbPegs];
		for(int i = 0; i < nbPegs; i ++) {
			solution[i] = Color.get(rand.nextInt(nbColors));
			config[i] = null;
		}
	}
	
	/**
	 * States the number of pegs in the combination. 
	 * This number varies on different games.
	 * @return the number of pegs in the code.
	 */
	public int nbPegs() {
		return nbPegs;
	}

	/**
	 * States the number of colors in the combinations.
	 * The colors are listed in the class Color.
	 * @return the number of colors
	 */
	public int nbColors() {
		return nbColors;
	}

	/**
	 * Places a peg of a given color on the given place.
	 * @param peg the place where the peg is placed. Start from 0 (but handled safely).
	 * @param color the color to place. No verification.
	 */
	public void putPeg(int peg, Color color) {
		peg = peg % nbPegs;
		config[peg] = color;
	}

	/**
	 * Return the color of the peg in the tried configuration.
	 * @param peg the position of the peg
	 * @return the color tried, null if none.
	 */
	public Color getPeg(int peg) {
		return config[peg%nbPegs];
	}
	
	/**
	 * Clears a place from its peg.
	 * @param peg the position to clear. 
	 */
	public void removePeg(int peg) {
		peg = peg % nbPegs;
		config[peg] = null;
	}

	/**
	 * States whether the position has been found.
	 * @return true if the position matches the solution looked for.
	 */
	public boolean isFound() {
		boolean res = true;
		for(int i =0; i < nbPegs; i ++)
		{
			res = res && config[i] == solution[i];
		}
		return res;
	}
	
	/**
	 * If allowed, states whether more pegs of the color should be added to the config.
	 * Else, always says true.
	 * @param color the color to check
	 * @return false if there are enough pegs of that color in the config.
	 */
	public boolean morePegs(Color color) {
		if(!cows)
			return true;
		int nb_config = 0;
		int nb_sol = 0; 
		for(int i = 0; i < nbPegs; i++) {
			if(config[i] == color) {
				nb_config++;
				}
			if(solution[i] == color) {
				nb_sol++;
			}
		}
		return (nb_config < nb_sol);
	}

	/** 
	 * getter on cows (for grading purpose);
	 * @return
	 */
	public boolean useCows() {
		return cows;
	}	
}
