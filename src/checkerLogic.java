import java.awt.*;
import java.io.Serializable;
import java.util.*;

/*
****************************************************
* checkerLogic									   *
****************************************************
* * GameGraphic                                     *
****************************************************
* * * GameBoardGraphic                             *
* * * **********************************************
* * * * BoardGraphic                               *
* * * * ********************************************
* * * * * TileGraphic                              *
* * * * *                                          *
* * * * *                                          *
* * * * ********************************************
* * * * BottomBoarGraphic                          *
* * * * ********************************************
* * * * *                                          *
* * * * ********************************************
* * * **********************************************
* * ************************************************
* * ************************************************
 */

public class checkerLogic extends CheckerType{
	
	public static GameGraphic gameFrame;
	
	public static ArrayList<BoardPoint> moveAvailable(TileGraphic tile)
	{
		int index = tile.getRow() * 8 + tile.getColumn();
		return moveAvailable(gameFrame.gameBoard.points, gameFrame.gameBoard.points[index]);
	}
	
	public static ArrayList<BoardPoint> moveAvailable(BoardPoint[] board, BoardPoint point)
	{
		ArrayList<BoardPoint> list = new ArrayList<BoardPoint>(0);
		//System.out.println("index:" + point.getIndex());
		if (point.getPiece() == RED_REGULAR){
		//	System.out.println("RED");
			if( point.getIndex() + 9 < 63 && point.getCol() != 7){
				if( validSpot(board[point.getIndex()+9]) )
					list.add(board[point.getIndex()+9]);
			}
			if( point.getIndex() + 7 < 63 && point.getCol() != 0){
				if( validSpot(board[point.getIndex()+7]) )
					list.add(board[point.getIndex()+7]);
			}
		}
		else if (point.getPiece() == BLACK_REGULAR){
		//	System.out.println("BLACK");
			if( point.getIndex() - 9 > 0 && point.getCol() != 0){
			//	System.out.println("hello9");
				if( validSpot(board[point.getIndex()-9]) )
					list.add(board[point.getIndex()-9]);
			}			
			if( point.getIndex() - 7 > 0 && point.getCol() != 7){
			//	System.out.println("hello7");
			//	System.out.println("point: " + (point.getIndex()-7));
				if( validSpot(board[point.getIndex()-7]) )
					list.add(board[point.getIndex()-7]);
			//	System.out.println("point: " + point.getIndex());
			}
		}
		else{
			if( point.getIndex() + 9 < 63 ){
				if( validSpot(board[point.getIndex()+9]) )
					list.add(board[point.getIndex()+9]);
			}
			if( point.getIndex() + 7 < 63 ){
				if( validSpot(board[point.getIndex()+7]) )
					list.add(board[point.getIndex()+7]);
			}
			if( point.getIndex() - 9 > 0 ){
				if( validSpot(board[point.getIndex()-9]) )
					list.add(board[point.getIndex()-9]);
			}			
			if( point.getIndex() - 7 > 0 ){
				if( validSpot(board[point.getIndex()-7]) )
					list.add(board[point.getIndex()-7]);
			}
		}
		
		return list;
	}
	
	//tile1 is usually the full spot
	public static void makeMove(TileGraphic tile1, TileGraphic tile2)
	{
		int index1 = tile1.getRow() * 8 + tile1.getColumn();
		int index2 = tile2.getRow() * 8 + tile2.getColumn();
		
		//System.out.println("index1: " + index1);
		//System.out.println("index2: " + index2);
		
		makeMove(gameFrame.gameBoard.points[index1], gameFrame.gameBoard.points[index2]);
	}
	
	public static void makeMove(BoardPoint point1, BoardPoint point2)
	{
		//System.out.println("point1: " + point1.getIndex());
		//System.out.println("point2: " + point2.getIndex());
		gameFrame.gameBoard.swap(point1, point2);
		point1.swap(point2);
		gameFrame.gameBoard.printBoard();
	}
	
	public static boolean validSpot(BoardPoint point)
	{
		System.out.println(point.getPiece());
		if(point.getPiece() == 0)
			return true;
		return false;
	}

	public static boolean checkForWin(Player p1, Player p2)
	{
		if (p1.getGamePieces() == 0 || p2.getGamePieces() == 0)
			return true;
		return false;
	}
	
	public static void main(String[] args) {
		Player p1 = new Player("Lucas");
		Player p2 = new Player("Steven");
		gameFrame = new GameGraphic();
		//moveAvailable(gameFrame.gameBoard.points, gameFrame.gameBoard.points[3]);
		//System.out.println(checkForMove(gameFrame.gameBoard, p1, 5 , 4));
	}
	
}

//player class
	
class Player implements Serializable {
		
	protected int playerNumber;    //player 1 (Black) or 2 (Red)
	private int gamePieces = 12;
	protected String name;
	protected int wins;
	
	public Player(String n)
	{
		name = n;
		wins = 0;
	}

	public void updateWins() {
		wins++;
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

	@Override
	public String toString() {
		return  String.format("%-12s%12d\n", name, wins);
	}
}

class GameBoard extends CheckerType{
	
	protected static int[][] board = new int[8][8];
	protected static BoardPoint[] points = new BoardPoint[64];
	protected static BoardGraphic boardGraphic;

	public GameBoard(BoardGraphic bg)
	{	
		System.out.println();
		
		boardGraphic = bg;
		fillBoard();
		printBoard();
	}
	
	public static void fillBoard()
	{
		for(int i = 0, x = 0, y = 0; i < points.length; i++, y++){
			if(y == 8){
				y = 0;
				x++;
			}
			if(i < 24){
				if(x%2 == 0 && y%2 != 0){
					points[i] = new BoardPoint(i, x, y, RED_REGULAR);
					boardGraphic.addCheckerPieceToTile(Color.RED, y, x);
				}
				else if(x%2 != 0 && y%2 == 0){
					points[i] = new BoardPoint(i, x, y, RED_REGULAR);
					boardGraphic.addCheckerPieceToTile(Color.RED, y, x);
				}
				else
					points[i] = new BoardPoint(i, x, y);
			}
			else if ( i > 23 && i < 40)
				points[i] = new BoardPoint(i, x, y);
			else {
				if(x%2 == 0 && y%2 != 0){
					points[i] = new BoardPoint(i, x, y, BLACK_REGULAR);
					boardGraphic.addCheckerPieceToTile(Color.BLACK, y, x);
				}
				else if(x%2 != 0 && y%2 == 0){
					points[i] = new BoardPoint(i, x, y, BLACK_REGULAR);
					boardGraphic.addCheckerPieceToTile(Color.BLACK, y, x);
				}
				else
					points[i] = new BoardPoint(i, x, y);
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
		for(int x = 0, y = 0; x < points.length; x++, y++){
			System.out.print(points[x].getPiece());
			System.out.print(" ");
			if(y == 7){
				System.out.println();
				y = -1;
			}
		}
		
		//System.out.println();
		//System.out.println("INDEXs");
		for(int x = 0, y = 0; x < points.length; x++, y++){
			System.out.print(points[x].getIndex());
			System.out.print(" ");
			if(y == 7){
				System.out.println();
				y = -1;
			}
		}
		/*
		for(int x = 0; x < board.length; x++){
			for(int y = 0; y < 8; y++){
				System.out.print(board[x][y]);
				System.out.print(" ");
			}
			System.out.println();
		}*/
	}

	public void swap(BoardPoint point1, BoardPoint point2){
		//System.out.println(points[point1.getIndex()]);
		//System.out.println(points[point2.getIndex()]);
		points[point1.getIndex()] = point2;
		points[point2.getIndex()] = point1;
		//System.out.println(points[point1.getIndex()]);
		//System.out.println(points[point2.getIndex()]);
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
	
	public BoardPoint(BoardPoint point){
		index = point.index;
		row = point.row;
		column = point.column;
		piece = point.piece;
	}
	
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
	
	public int getRow(){
		return row;
	}
	
	public int getCol(){
		return column;
	}
	
	public int getIndex(){
		return index;
	}
	
	public boolean equals(BoardPoint p){
		if (p.getRow() == row && p.getCol() == column)
			return true;
		return false;
	}
	
	public void swap(BoardPoint point2){
		BoardPoint temp = new BoardPoint(this);
		
		//System.out.println("this: " + index);
		//System.out.println("point2: " + point2.index);
		
		index = point2.index;
		row = point2.row;
		column = point2.column;
		//this.piece = point2.piece;
		point2.index = temp.index;
		point2.row = temp.row;
		point2.column = temp.column;
		//point2.piece = temp.piece;
		
		//System.out.println("this: " + index);
		//System.out.println("point2: " + point2.index);
	}
	
	
}
