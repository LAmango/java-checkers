public class checkerLogic {
	
	public boolean checkForMove(GameBoard game, Player p1)
	{
		int x = 0, y = 0;
		if(game.board[x+1][y+1] == 0)
			return true;
		else if (game.board[x-1][y+1] == 0)
			return true;
		
		return false;
	}
	
	public boolean checkForJump(GameBoard board, Player p1)
	{
		int x = 0, y = 0;
		if(game.board[x+2][y+2] == 0)
			return true;
		else if (game.board[x-2][y+2] == 0)
			return true;
		
		return false;
	}
	
	public void makeMove(GameBoard game, Player p1, int pos)
	{
		
	}
	
	public void makeJump(GameBoard game, Player p1, int pos)
	{
		
	}
	
	public boolean checkForWin(Player p1, Player p2)
	{
		if (p1.getGamePieces() == 0 || p2.getGamePieces() == 0)
			return true;
		return false;
	}
	
	public static void main(String[] args) {
		Player p1 = new Player(1);
		Player p2 = new Player(2);
		GameBoard gameBoard = new GameBoard();
	}
	
}

//player class
	
class Player {
		
	private int playerNumber;    //player 1 or 2
	private int currentPosition;
	private int gamePieces = 12;
	
	public Player(int num)
	{
		setPlayerNumber(num);
	}
	
	//Player 1 or 2
	public void setPlayerNumber(int num)
	{ playerNumber = num; }
	
	public int getPlayerNumber()
	{ return playerNumber; }
	
	//gamePieces
	public void eliminatePiece()
	{ gamePieces--; }
	
	public int getGamePieces()
	{ return gamePieces; }
	
}

class GameBoard extends CheckerType{
	
	protected static int[][] board = new int[8][8];
			
	public GameBoard()
	{
		fillBoard();
		printBoard();
	}
	
	public static void fillBoard()
	{
		for(int x = 0; x < board.length; x++){
			for(int y = 0; y < 8; y++){
				if(x <= 2){
					if(x%2 == 0){
						if(y%2 != 0)
							board[x][y] = RED_REGULAR;
					}
					else if(x%2 != 0){
						if(y%2 == 0)
							board[x][y] = RED_REGULAR;
					}
				}
				else if(x >= 5){
					if(x%2 == 0){
						if(y%2 != 0)
							board[x][y] = BLACK_REGULAR;
					}
					else if(x%2 != 0){
						if(y%2 == 0)
							board[x][y] = BLACK_REGULAR;
					}
				}
			}
		}
	}
	
	public int checkType()
	{
		int x = 0, y = 0;
		switch (board[x][y]) {
			case BLACK_REGULAR:
				return BLACK_REGULAR;
			case BLACK_KING:
				return BLACK_KING;
			case RED_REGULAR:
				return RED_REGULAR;
			case RED_KING:
				return RED_KING;
			default:
				return 0;
		}
	}
	
	public void printBoard()
	{
		for(int x = 0; x < board.length; x++){
			for(int y = 0; y < 8; y++){
				System.out.print(board[x][y]);
				System.out.print(" ");
			}
			System.out.println();
		}
	}
}

class CheckerType {
	public static final int BLACK_REGULAR = 1;
	public static final int RED_REGULAR = 2;
	public static final int BLACK_KING = 3;
	public static final int RED_KING = 4;
}