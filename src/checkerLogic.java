import java.awt.*;
import java.util.*;

public class checkerLogic extends CheckerType{
	
	public static ArrayList<BoardPoint> moveAvailable(BoardPoint[] board, BoardPoint point)
	{
		ArrayList<BoardPoint> list = new ArrayList<BoardPoint>(0);
				
		if (point.getPiece() == RED_REGULAR){
			if( point.getIndex() + 9 < 63 )
				list.add( validSpot(board[point.getIndex()+9]) ? board[point.getIndex()+9] : null);
			if( point.getIndex() + 7 < 63 )
				list.add( validSpot(board[point.getIndex()+7]) ? board[point.getIndex()+7] : null);
		}
		else if (point.getPiece() == BLACK_REGULAR){
			if( point.getIndex() - 9 > 0 )
				list.add( validSpot(board[point.getIndex()-9]) ? board[point.getIndex()-9] : null);
			if( point.getIndex() - 7 > 0 )
				list.add( validSpot(board[point.getIndex()-7]) ? board[point.getIndex()-7] : null);
		}
		else{
			if( point.getIndex() + 9 < 63 )
				list.add( validSpot(board[point.getIndex()+9]) ? board[point.getIndex()+9] : null);
			if( point.getIndex() + 7 < 63 )
				list.add( validSpot(board[point.getIndex()+7]) ? board[point.getIndex()+7] : null);
			if( point.getIndex() - 9 > 0 )
				list.add( validSpot(board[point.getIndex()-9]) ? board[point.getIndex()-9] : null);
			if( point.getIndex() - 7 > 0 )
				list.add( validSpot(board[point.getIndex()-7]) ? board[point.getIndex()-7] : null);
		}
		
		return list;
	}
	
	public static boolean validSpot(BoardPoint point)
	{
		if(point.getPiece() == 0)
			return true;
		return false;
	}
	
	public static void makeMove(BoardPoint[] board, BoardPoint start, BoardPoint end)
	{
		if(validSpot(point)){
			start.swap(end);
		}
	}
	
	/*
	public static boolean checkMove(GameBoard game, Player p, int row, int column, int checkFor, boolean king)
	{
		//must pass all tests to have a valid move
		
		//check if Player 1 (black) or 2 (red)
		if (p.getPlayerNumber() == 1){
			
			if( king ){
				
			}
			else{
			
				if (row == 0 || row-1 < 0 )
					return false;
				
				if ( (column == 0 || column-1 < 0) && ( row != 0 || row-1 < 0 ) ){
					if(!checkRight(game, p, row, column, checkFor, false))
						return false;
				}
				else if ( (column == 7 || column+1 > 7) && ( row != 0 || row-1 < 0 ) ){
					if(!checkLeft(game, p, row, column, checkFor, false))
						return false;
				} 
				else {
					if(!(checkLeft(game, p, row, column, checkFor, false) && checkRight(game, p, row, column, checkFor, false) ) )
						return false;
				}
			}
		}
		else if (p.getPlayerNumber() == 2){
			if ( (column == 7 || column+1 > 7) && ( row != 7 || row+1 > 7 ) ){
				if(!checkLeft(game, p, row, column, checkFor, false))
					return false;
			}
			else if ( (column == 0 || column-1 < 0) && ( row != 7 || row+1 > 7 ) ){
				if(!checkRight(game, p, row, column, checkFor, false))
					return false;
			}
			else {
				if(!(checkLeft(game, p, row, column, checkFor, false) && checkRight(game, p, row, column, checkFor, false) ) )
					return false;
			}
		}	
		return true;
	}
	
	public static boolean checkRight(GameBoard game, Player p, int row, int column, int checkFor, boolean king)
	{
		int x = row, y = column;
		if(p.getPlayerNumber() == 1){
			if(game.board[x-1][y+1] != checkFor)
				return false;
		}
		else if(p.getPlayerNumber() == 2){
			if(game.board[x+1][y-1] != checkFor)
				return false;
		}
		if(king){
			if(game.board[x-1][y+1] != checkFor)
				return false;
			if(game.board[x+1][y-1] != checkFor)
				return false;
		}
		return true;
	}
	
	public static boolean checkLeft(GameBoard game, Player p, int row, int column, int checkFor, boolean king)
	{
		int x = row, y = column;
		if(p.getPlayerNumber() == 1){
			if(game.board[x-1][y-1] == checkFor)
				return true;
		}
		else if(p.getPlayerNumber() == 2){
			if(game.board[x+1][y+1] == checkFor)
				return true;
		}
		if(king){
			if(game.board[x-1][y+1] != checkFor)
				return false;
			if(game.board[x+1][y-1] != checkFor)
				return false;
		}
		return false;
	}
	
	public static boolean checkForJump(GameBoard game, Player p, int row, int column)
	{
		int x = row, y = column;
		if(p.getPlayerNumber() == 1){
		
			if(game.board[x-2][y-2] == 0)
				return true;
			else if (game.board[x+2][y-2] == 0)
				return true;
		}
		else if(p.getPlayerNumber() == 2){
			if(game.board[x+2][y+2] == 0)
				return true;
			else if (game.board[x-2][y+2] == 0)
				return true;
		}
		return false;

	}
	
	public static void makeMove(GameBoard game, Player p, int row, int column)
	{
		int x = row, y = column;
	}
	
	public static void makeJump(GameBoard game, Player p, int row, int column)
	{
		int x = row, y = column;
	}
	*/
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
		moveAvailable(gameFrame.gameBoard.points, gameFrame.gameBoard.points[3]);
		//System.out.println(checkForMove(gameFrame.gameBoard, p1, 5 , 4));
	}
	
}

//player class
	
class Player {
		
	protected int playerNumber;    //player 1 (Black) or 2 (Red)
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
	protected static BoardPoint[] points = new BoardPoint[64];
	protected static BoardGraphic boardGraphic;

	public GameBoard(BoardGraphic bg)
	{
		
		for(int i = 0, x = 0, y = 0; i < points.length; i++, y++){
			if(y == 8){
				y = 0;
				x++;
			}
			if(i < 24){
				if(x%2 == 0 && y%2 != 0)
					points[i] = new BoardPoint(i, x, y, RED_REGULAR);
				else if(x%2 != 0 && y%2 == 0)
					points[i] = new BoardPoint(i, x, y, RED_REGULAR);
				else
					points[i] = new BoardPoint(i, x, y);
			}
			else if ( i > 23 && i < 40)
				points[i] = new BoardPoint(i, x, y);
			else {
				if(x%2 == 0 && y%2 != 0)
					points[i] = new BoardPoint(i, x, y, BLACK_REGULAR);
				else if(x%2 != 0 && y%2 == 0)
					points[i] = new BoardPoint(i, x, y, BLACK_REGULAR);
				else
					points[i] = new BoardPoint(i, x, y);
			}
		}
		
		for(int x = 0, y = 0; x < points.length; x++, y++){
			System.out.print(points[x].getPiece());
			System.out.print(" ");
			if(y == 7){
				System.out.println();
				y = -1;
			}
		}
		
		System.out.println();
		
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

class BoardPoint {
	private int row;
	private int column;
	private int index;
	private int piece;
	//private int size;
	
	public BoardPoint(int i, int r, int c){
		this(i, r, c, 0);
	}
	
	public BoardPoint(int i, int r, int c, int p){
		index = i;
		row = r;
		column = c;
		piece = p;
	}
	
	/*
	public BoardPoint(int a, int b){
		this(a, b, 0);
	}
	
	public BoardPoint(int a, int b, int p){
		x = a;
		y = b;
		piece = p;
	}
	*/
	public void setPiece(int p){
		piece = p;
	}
	
	public int getPiece(){
		return piece;
	}
	
	public int getX(){
		return row;
	}
	
	public int getY(){
		return column;
	}
	
	public int getIndex(){
		return index;
	}
	
	public boolean equals(BoardPoint p){
		if (p.getX() == row && p.getY() == column)
			return true;
		return false;
	}
	
	public void swap(BoardPoint p){
		BoardPoint temp = this;
		
		this.index = p.index;
		this.row = p.row;
		this.column = p.column;
		this.piece = p.piece;
		p.index = temp.index;
		p.row = temp.row;
		p.column = temp.column;
		p.piece = temp.piece;
	}
	
	
}
