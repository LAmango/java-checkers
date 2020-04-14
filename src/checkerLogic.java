import java.awt.*;

public class checkerLogic {
	
	public static boolean checkForMove(GameBoard game, Player p1, int pos)
	{
		int x = pos/8, y = pos%8;
		if(game.board[x+1][y+1] == 0)
			return true;
		else if (game.board[x-1][y+1] == 0)
			return true;
		
		return false;
	}
	
	public static boolean checkForJump(GameBoard game, Player p1, int pos)
	{
		int x = pos/8, y = pos%8;
		if(game.board[x+2][y+2] == 0)
			return true;
		else if (game.board[x-2][y+2] == 0)
			return true;
		
		return false;
	}
	
	public static void makeMove(GameBoard game, Player p1, int pos)
	{
		int x = pos/8, y = pos%8;
	}
	
	public static void makeJump(GameBoard game, Player p1, int pos)
	{
		int x = pos/8, y = pos%8;
	}
	
	public static boolean checkForWin(Player p1, Player p2)
	{
		if (p1.getGamePieces() == 0 || p2.getGamePieces() == 0)
			return true;
		return false;
	}
	
	public static void main(String[] args) {
		Player p1 = new Player(1);
		Player p2 = new Player(2);
		GameGraphic gameFrame = new GameGraphic();
		System.out.println(checkForMove(gameFrame.gameBoard, p1, 42));
	}
	
}

//player class
	
class Player {
		
	protected int playerNumber;    //player 1 or 2
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
	protected static BoardGraphic boardGraphic;

	public GameBoard(BoardGraphic bg)
	{
		boardGraphic = bg;
		fillBoard();
		printBoard();
	}
	
	public static void fillBoard()
	{
		for(int x = 0; x < board.length; x++){
			for(int y = 0; y < 8; y++){
				if(x <= 2){
					if(x%2 == 0){
						if(y%2 != 0) {
							board[x][y] = RED_REGULAR;
							boardGraphic.addCheckerPieceToTile(Color.RED, y, x);
						}
					}
					else if(x%2 != 0){
						if(y%2 == 0) {
							board[x][y] = RED_REGULAR;
							boardGraphic.addCheckerPieceToTile(Color.RED, y, x);
						}
					}
				}
				else if(x >= 5){
					if(x%2 == 0){
						if(y%2 != 0) {
							board[x][y] = BLACK_REGULAR;
							boardGraphic.addCheckerPieceToTile(Color.BLACK, y, x);
						}
					}
					else if(x%2 != 0){
						if(y%2 == 0) {
							board[x][y] = BLACK_REGULAR;
							boardGraphic.addCheckerPieceToTile(Color.BLACK, y, x);
						}
					}
				}
			}
		}
	}
	
	public int checkType(int pos)
	{
		int x = pos/8, y = pos%8;
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
