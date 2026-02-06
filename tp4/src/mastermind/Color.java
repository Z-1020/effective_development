package mastermind;

public enum Color {
	RED('R'), BLUE('B'), GREEN('G'), 
	WHITE('W'), YELLOW('Y'), 
	PURPLE('P'), ORANGE('O'), BROWN('M'), BLACK('N');
	private char label;
	private final static int NB_COL_MAX = 9;
	
	private Color(char c) {
		this.label = c;
	}
	
	public static Color get(int i) {
		if(0 <= i && i < NB_COL_MAX)
			return values()[i];
		return null;
	}
	
	public static int nbColors() {
		return NB_COL_MAX;
	}
	
	public char getCode() {
		return this.label;
	}
	
	public String toString() {
		return "" + this.label;
	}
	
	
}
