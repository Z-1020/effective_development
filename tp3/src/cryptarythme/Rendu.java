package cryptarythme;

import java.util.ArrayList;
import java.util.LinkedList;

public class Rendu {

	public boolean hasEmpty(Puzzle problem) {
		for (int i = 0; i < problem.getNbCharacters(); i++) {
			if (problem.getCharacterValue(i) == -1) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Computes a solution to the problem given as a parameter. The computed
	 * solution may use more than once the same digit. If a solution is found, the
	 * Puzzle will remain in the state of the solution.
	 * 
	 * @param problem the Puzzle to solve
	 * @return true if a solution is found.
	 */
	public Boolean solve(Puzzle problem) throws Exception {
		if (!hasEmpty(problem) && problem.verify()) {
			return true;
		}
		for (int index = 0; index < problem.getNbCharacters(); index++) {
			if (problem.getCharacterValue(index) == -1) {
				for (int val = 0; val < 10; val++) {
					problem.setCharacter(index, val);
					if (solve(problem)) {
						return true;
					}
				}
				problem.unsetCharacter(index);
				return false;
			}
		}
		return false;
	}

	/**
	 * Computes the number of solutions to the problem given as a parameter. The
	 * computed solution may use more than once the same digit. Leaves the Puzzle in
	 * a state where all digit are uninitialized.
	 * 
	 * @param problem the Puzzle to solve
	 * @return the number of solutions found
	 */
	
	
	public int nbSolutions(Puzzle problem) throws Exception {
		int compteur = 0;
		if (!hasEmpty(problem) && problem.verify()) {
			return 1;
		}
		for (int index = 0; index < problem.getNbCharacters(); index++) {
			if (problem.getCharacterValue(index) == -1) {
				for (int val = 0; val < 10; val++) {
					problem.setCharacter(index, val);
						compteur = compteur + nbSolutions(problem);
						
				}
				problem.unsetCharacter(index);
				return compteur;
			}
		}
		return compteur;
	}

	public static void main(String[] args) throws Exception {
		Rendu solution = new Rendu();
		Puzzle problem = new Puzzle(5);
		
		if (solution.solve(problem))
			System.out.println("OK, problem solved");
		else
			System.out.println("KO, problem not solved");

		problem = new Puzzle(5);
		int nbSol = solution.nbSolutions(problem);
		if (nbSol == 1)
			System.out.println("OK, problem has 1 solution");
		else
			System.out.println("KO, number of solutions found: " + nbSol);

	}
}