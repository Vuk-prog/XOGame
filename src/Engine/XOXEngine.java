package Engine;

public class XOXEngine {
	
	private int[][] table = new int[3][3];
	private int currentPlayer;
	private int numOfMoves;
	private STANJE_IGRE stanjeIgre; 
	
	/*
	 * -1 -> OX
	 *  1 -> X
	 *  0 -> prazno
	*/
	
	public XOXEngine() {
		init();
	}
	
	public void init() {
		for(int i = 0; i < table.length; i++) {
			for(int j = 0; j < table.length; j++) {
				table[i][j] = 0;
			}
		}
		
		currentPlayer = 1;
		numOfMoves = 0;
		stanjeIgre = STANJE_IGRE.U_TOKU;
		
	}
	
	public STANJE_IGRE play(int i, int j) {
		if(table[i][j] == 0) {
			table[i][j] = currentPlayer;
			currentPlayer *= -1;
			numOfMoves++;
		}
		
		return endOfGame();
	}
	
	private STANJE_IGRE endOfGame() {
		
		int sumaNaGlavnoj = 0;
		int sumaNaSporednoj = 0;
		
		for( int i = 0; i < table.length; i++) {
			sumaNaGlavnoj += table[i][i];
			sumaNaSporednoj += table[i][table.length - i - 1];
			
			int horziontal = 0;
			int vertical = 0;
			
			for( int j = 0; j < table.length; j++) {
				horziontal += table[i][j];
				vertical += table[j][i];
			}
			
			if(horziontal == 3 || vertical == 3) {
				return STANJE_IGRE.POBEDIO_X;
			}
			
			if(horziontal == -3 || vertical == -3) {
				return STANJE_IGRE.POBEDIO_OX;
			}
		}
		
		if( sumaNaGlavnoj == 3 || sumaNaSporednoj == 3) {
			return STANJE_IGRE.POBEDIO_X;
		}
		
		if( sumaNaGlavnoj == -3 || sumaNaSporednoj == -3) {
			return STANJE_IGRE.POBEDIO_OX;
		}
		
		if( numOfMoves == 9) {
			return STANJE_IGRE.NERESENO;
		}
		
		
		return STANJE_IGRE.U_TOKU;
	}

	
	public void printTable() {
		
		for( int i = 0; i < table.length; i++) {
			for( int j = 0; j < table.length; j++) {
				System.out.print(table[i][j] + " ");
			}
			System.out.print("\n");
		}
	}
	
	public String getValue(int i, int j) {
		if(table[i][j] == 1) {
			return "X";
		}
		if(table[i][j] ==  -1) {
			return "O";
		}
		return " ";
	}
	
	public String currPlay() {
		if(currentPlayer == 1) {
			return "X";
		}
		return "O";
		
	}
	
	

}
