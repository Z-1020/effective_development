package tp3.sudoku;

public class Solveur {
	
	public boolean isValidLine(int pos, Sudoku sudoku) {
		Square[] line = sudoku.getLine(pos);
		for(int i = 0; i<8; i++) {
			for(int j= i+1; j<9; j++) {
				if(line[i] == line[j]) {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean isValidColumn(int pos, Sudoku sudoku) {
		Square[] column = sudoku.getColumn(pos);
		for(int i = 0; i<8; i++) {
			for(int j=i+1; j<9; j++) {
				if(column[i] == column[j]) {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean isValidBlock(int pos, Sudoku sudoku) {
		Square[] block = sudoku.getBlock(pos);
		for(int i =0; i<2; i++) {
			for(int j=i+1; j<3; j++) {
				if(block[i] == block[j]) {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean isValidSudoku(Sudoku sudoku) {
		for(int i = 0; i< 9; i++) {
			if(isValidBlock(i, sudoku) && isValidLine(i, sudoku) && isValidColumn(i, sudoku)) {
					return true;
				
			}
		}
		return false;
	}
	
	public boolean solve(Sudoku sudoku) {
		Square choice = null;
		if(isValidSudoku(sudoku)) {
			choice = sudoku.firstEmptySquare();
		}
		
		for(int i=0; i<9; i++ ) {
			sudoku.set(choice, i);
			if(solve(sudoku)) {
				return true;
			}
			
		}
		sudoku.unset(choice);
		return false;
	}

}
