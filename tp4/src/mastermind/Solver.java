package mastermind;

public class Solver {

	/**
	 * You can run some tests here, the output should be seen with the little rocket
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// run some tests here if you want.
		MasterMind mastermind = new MasterMind();
		System.out.println(checkThemAll(mastermind));
		System.out.println(checkWithCows(mastermind));
	}

	public static boolean hasEmpty(MasterMind mastermind) {
		for (int i = 0; i < mastermind.nbPegs(); i++) {
			if (mastermind.getPeg(i) == null) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Looks for a solution of the mastermind.
	 * 
	 * @param mastermind
	 * @return true when a solution is found, leaves the mastermind on state.
	 */

	public static boolean checkThemAll(MasterMind mastermind) {
		if (mastermind.isFound() && hasEmpty(mastermind) == false) {
			return true;
		}
		for (int i = 0; i < mastermind.nbPegs(); i++) {
			if (mastermind.getPeg(i) == null) {
				for (int j = 0; j < mastermind.nbColors(); j++) {
					mastermind.putPeg(i, Color.get(j));
					if (checkThemAll(mastermind)) {
						return true;
					}
				}
				mastermind.removePeg(i);
				return false;
			}

		}
		return false;
	}

	/**
	 * Look for a solution of the mastermind with cows.
	 * 
	 * @param mastermind
	 * @return true when a solution is found, leaves the mastermind on state.
	 */
	public static boolean checkWithCows(MasterMind mastermind) {
		if ( hasEmpty(mastermind) == false && mastermind.isFound()) {
			return true;
		}
		for (int i = 0; i < mastermind.nbPegs(); i++) {
			if (mastermind.getPeg(i) == null) {
				for (int j = 0; j < mastermind.nbColors(); j++) {
					if (mastermind.morePegs(Color.get(j))) {
						mastermind.putPeg(i, Color.get(j));
						if (checkWithCows(mastermind)) {
							return true;
						}
					}
				}
				mastermind.removePeg(i);
				return false;
			}
		}
		return false;
	}

}