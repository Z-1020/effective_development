package cryptarythme;

import java.util.ArrayList;
import java.util.Random;

public class Puzzle {
	public static final int BASE = 10;
	// for having fewer digits to use, take only digits 0 mod COEFF
	// No carry should be used to allow it
	public static final int COEFF = 2;  

	class Operation{
		int operator1;
		int operator2;
		int carry;
		int result;
		int carried;

		public Operation(int operator1, int operator2, int carry, int result) {
			super();
			this.operator1 = operator1;
			this.operator2 = operator2;
			this.carry = carry;
			this.result = result;
			this.carried = 0;
		}
		public Operation(int operator1, int operator2, int carry, int result, int carried) {
			super();
			this.operator1 = operator1;
			this.operator2 = operator2;
			this.carry = carry;
			this.result = result;
			this.carried = carried;
		}

		Boolean check(Puzzle puzzle) {
			return ((puzzle.getCharacterValue(operator1) 
						+ puzzle.getCharacterValue(operator2) 
						+ carry)
					== (puzzle.getCharacterValue(result) + BASE * carried));
		}
	}

	private ArrayList<Integer> table;
	private int nbLetters;
	private ArrayList<Operation> operations;
	private boolean onlyFullSet;

	public Puzzle(int nbLetters){
		onlyFullSet = true;
		table = new ArrayList<Integer>();
		operations = new ArrayList<Operation>();
		this.nbLetters = nbLetters;
		for(int i = 0; i < nbLetters; i++) {
			table.add(-1);
		}
		setExample();
	}

	/**
	 * Defines an example puzzle 
	 */
	public void setExample() {
		// (U) TO + (U) GO == OUT
		operations.clear();
		operations.add(new Operation(0,0,0,1)); // O + O = T
		operations.add(new Operation(1,2,0,3,1)); // T + G = U + 10
		operations.add(new Operation(3,3,1,0,0)); // U + U + 1 = O
		operations.add(new Operation(4,4,0,3,1)); // S + S = O + 10
		// Solution: O(0) = 1, T(1) = 2, G(2) = 8, U(3) = 0, S(4) = 5 
	}
	
	/**
	 * Defines a purely random problem, with operations to satisfy.
	 */
	public void setRandom() {
		Random rand = new Random();
		setRandom(rand);
	}

	/**
	 * Same with a given seed
	 * @param seed
	 */
	public void setRandom(int seed) {
		Random rand = new Random(seed);
		setRandom(rand);
	}

	/**
	 * Same with a given random generator
	 * @param rand
	 */
	public void setRandom(Random rand) {
		operations.clear();
		for(int i = 0; i < nbLetters/2; i++){
			operations.add(new Operation(
					rand.nextInt(nbLetters),
					rand.nextInt(nbLetters),
					rand.nextInt(1),
					rand.nextInt(nbLetters)));
		}
	}

	/**
	 * Generates a solution, 
	 * i.e. a permutation of values from 0 to BASE/COEFF - 1
	 * @param rand the Random to use for generation
	 * @return the solution as an array of integer.
	 */
	private int[] generateSolution(Random rand) {
		int[] sol = new int[BASE/COEFF];
		for(int i = 0; i < BASE/COEFF; i ++) {
			sol[i] = i;
		}
		for(int i = 0; i < BASE/COEFF - 1; i ++) {
			int temp = sol[i];
			int echange = rand.nextInt(BASE/COEFF-i)+i;
			sol[i] = sol[echange];
			sol[echange] = temp;
		}
		return sol;
	}

	public void setWithSolution() {
		setWithSolution(new Random());
	}

	public void setWithSolution(int i) {
		setWithSolution(new Random(i));
	}

	public void setWithSolution(Random rand) {
		operations.clear();
		int[] sol = generateSolution(rand);
		int carry = 0;
		for(int i = 0; i < nbLetters; i++){
			int operateur1 = rand.nextInt(BASE/COEFF) * COEFF;
			int operateur2 = rand.nextInt(BASE/COEFF) * COEFF;
			int result = (operateur1 + operateur2 + carry) % BASE;
			int carried = (operateur1 + operateur2 + carry) / BASE;
			operations.add(
					new Operation(
							sol[operateur1/COEFF],
							sol[operateur2/COEFF], 
							carry, 
							sol[result/COEFF],
							carried));
			//carry = carried;
		}
	}

	public void setCharacter(int index, int value) {
		if(index >= 0 && index < nbLetters && value >= 0 && value < BASE)
			table.set(index, value);
		else throw new IllegalArgumentException("unexpected arguments to setCharacter : " + index + ", " + value);
	}

	public void unsetCharacter(int index) {
		if(index >= 0 && index < nbLetters)
			table.set(index, -1);
		else throw new IllegalArgumentException("unexpected arguments to unsetCharacter : " + index);		
	}

	/**
	 * Check whether the affected values are a solution to the problem
	 * @return true when it is a solution.
	 */
	public Boolean verify() {
		int[] unicity = new int[10];
		for(Integer i : table) {
			if(i == -1) {
				System.out.println("The problem is not fully set");
				this.onlyFullSet  = false; 
				return false;
			}
			if(unicity[i]++ > 0) {
				return false;
			}
		}
		for(Operation operation : operations) {
			if(!operation.check(this)) {
				return false;
			}
		}
		return true;
	}

	/** 
	 * @param index the index of the character to check.
	 * @return the number assigned to the character, -1 if no value has been assigned.
	 */
	int getCharacterValue(int index) {
		if(index >= 0 && index < nbLetters) {
			int res = table.get(index);
			return res;
		}
		else throw new IllegalArgumentException("unexpected arguments to getCharacterValue : " + index);
	}

	/**
	 * @return the number of letters to assign a digit to.
	 */
	public int getNbCharacters() {
		return nbLetters;
	}

	public String printProposal() {
		StringBuilder res  = new StringBuilder();
		for(int i = 0; i < nbLetters; i++) {
			res.append("\n");
			res.append((char)('A' + i));
			res.append(" -> ");
			res.append(table.get(i));

		}
		return res.toString();
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		for(Operation op: operations) {
			res.append((char) ('A' + op.operator1));
			res.append(" + ");
			res.append((char)('A' + op.operator2));
			if(op.carry > 0) {
				res.append(" + ");
				res.append(op.carry);
			}
			res.append(" == ");
			res.append((char)('A'+ op.result));
			if(op.carried > 0) {
				res.append(" + ");
				res.append(op.carried * BASE);
			}
			res.append("\n");
		}
		return res.toString();
	}
	
	public boolean isOnlyFullSet() {
		return onlyFullSet;
	}	

	public static void main(String[] args) throws Exception {
		Puzzle puzzle = new Puzzle(5);
		Rendu sol = new Rendu();
		System.out.println(puzzle);
		puzzle.setCharacter(0, 1);
		puzzle.setCharacter(1, 2);
		puzzle.setCharacter(2, 8);
		//int nbsol = sol.nbSolutions(puzzle);
		//System.out.printf("%d solution(s)\n",  nbsol);
		if(sol.solve(puzzle)) {
			System.out.println("Solution found: "+ puzzle.printProposal());
		}
	}
}
