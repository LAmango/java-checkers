import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.*;

/*
****************************************************
* checkerLogic									   *
****************************************************
* * GameGraphic                                    *
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
	
	protected static GameGraphic gameFrame;
	
	public static ArrayList<BoardPoint> moveAvailable(TileGraphic tile)
	{
		int index = tile.getRow() * 8 + tile.getColumn();
		return moveAvailable(gameFrame.gameBoard.points, gameFrame.gameBoard.points[index], 1);
	}
	
	public static ArrayList<BoardPoint> jumpAvailable(TileGraphic tile)
	{
		int index = tile.getRow() * 8 + tile.getColumn();
		return jumpAvailable(gameFrame.gameBoard.points, gameFrame.gameBoard.points[index], 1);
	}
	
	public static ArrayList<BoardPoint> moveAvailable(BoardPoint[] board, BoardPoint point, int jump)
	{
		ArrayList<BoardPoint> list = new ArrayList<BoardPoint>(0);
		if (point.getPiece() == RED_REGULAR){
			if( point.getIndex() + (9*jump) < 63 && point.getCol() != 7){
				if( validSpot(board[point.getIndex()+(9*jump)]) )
					list.add(board[point.getIndex()+(9*jump)]);
			}
			if( point.getIndex() + (7*jump) < 63 && point.getCol() != 0){
				if( validSpot(board[point.getIndex()+(7*jump)]) )
					list.add(board[point.getIndex()+(7*jump)]);
			}
		}
		else if (point.getPiece() == BLACK_REGULAR){
			if( point.getIndex() - (9*jump) > 0 && point.getCol() != 0){
				if( validSpot(board[point.getIndex()-(9*jump)]) )
					list.add(board[point.getIndex()-(9*jump)]);
			}			
			if( point.getIndex() - (7*jump) > 0 && point.getCol() != 7){
				if( validSpot(board[point.getIndex()-(7*jump)]) )
					list.add(board[point.getIndex()-(7*jump)]);
			}
		}
		else if (point.getPiece() == BLACK_KING || point.getPiece() == RED_KING){
			if( point.getIndex() + (9*jump) <= 63 ){
				if( validSpot(board[point.getIndex()+(9*jump)]) )
					list.add(board[point.getIndex()+(9*jump)]);
			}
			if( point.getIndex() + (7*jump) <= 63 ){
				if( validSpot(board[point.getIndex()+(7*jump)]) )
					list.add(board[point.getIndex()+(7*jump)]);
			}
			if( point.getIndex() - (9*jump) >= 0 ){
				if( validSpot(board[point.getIndex()-(9*jump)]) )
					list.add(board[point.getIndex()-(9*jump)]);
			}			
			if( point.getIndex() - (7*jump) >= 0 ){
				if( validSpot(board[point.getIndex()-(7*jump)]) )
					list.add(board[point.getIndex()-(7*jump)]);
			}
		}

		return list;
	}
	
	public static ArrayList<BoardPoint> jumpAvailable(BoardPoint[] board, BoardPoint point, int jump)
	{
		ArrayList<BoardPoint> list = new ArrayList<BoardPoint>(0);
		if (point.getPiece() == RED_REGULAR){
			if( point.getIndex() + (9*jump) <= 63 && (point.getCol() != 7 || point.getCol() != 6) && point.getIndex() + (9*(jump+1)) <= 63){
				if( differentPiece(point, board[point.getIndex()+(9*jump)]) && validSpot(board[point.getIndex()+(9*(jump+1))]))
					list.add(board[point.getIndex()+( 9*(jump+1) )]);
			}
			if( point.getIndex() + (7*jump) <= 63 && (point.getCol() != 0 || point.getCol() != 1) && point.getIndex() + (7*(jump+1)) <= 63){
				if( differentPiece(point, board[point.getIndex()+(7*jump)]) && validSpot(board[point.getIndex()+(7*(jump+1))]))
					list.add(board[point.getIndex()+(7*(jump+1))]);
			}
		}
		else if (point.getPiece() == BLACK_REGULAR){
			if( point.getIndex() - (9*jump) >= 0 && (point.getCol() != 0 || point.getCol() != 1) && point.getIndex() - (9*(jump+1)) >= 0){
				if( differentPiece(point, board[point.getIndex()-(9*jump)]) && validSpot(board[point.getIndex()-(9*(jump+1))]))
					list.add(board[point.getIndex()-(9*(jump+1))]);
			}			
			if( point.getIndex() - (7*jump) >= 0 && (point.getCol() != 7 || point.getCol() != 6) && point.getIndex() - (7*(jump+1)) >= 0){
				if( differentPiece(point, board[point.getIndex()-(7*jump)]) && validSpot(board[point.getIndex()-(7*(jump+1))]))
					list.add(board[point.getIndex()-(7* (jump+1))]);
			}
		}
		else if (point.getPiece() == BLACK_KING || point.getPiece() == RED_KING){
			if( point.getIndex() + (9*jump) <= 63 && (point.getCol() != 7 || point.getCol() != 6) && point.getIndex() + (9*(jump+1)) <= 63){
				if( differentPiece(point, board[point.getIndex()+(9*jump)]) && validSpot(board[point.getIndex()+(9*(jump+1))]))
					list.add(board[point.getIndex()+(9*(jump+1))]);
			}
			if( point.getIndex() + (7*jump) <= 63 && (point.getCol() != 0 || point.getCol() != 1) && point.getIndex() + (7*(jump+1)) <= 63){
				if( differentPiece(point, board[point.getIndex()+(7*jump)]) && validSpot(board[point.getIndex()+(7*(jump+1))]))
					list.add(board[point.getIndex()+(7*(jump+1))]);
			}
			if( point.getIndex() - (9*jump) >= 0 && (point.getCol() != 7 || point.getCol() != 6) && point.getIndex() - (9*(jump+1)) >= 0){
				if( differentPiece(point, board[point.getIndex()-(9*jump)]) && validSpot(board[point.getIndex()-(9*(jump+1))]))
					list.add(board[point.getIndex()-(9*(jump+1))]);
			}			
			if( point.getIndex() - (7*jump) >= 0 && (point.getCol() != 0 || point.getCol() != 1) && point.getIndex() - (7*(jump+1)) >= 0){
				if( differentPiece(point, board[point.getIndex()-(7*jump)]) && validSpot(board[point.getIndex()-(7*(jump+1))]))
					list.add(board[point.getIndex()-(7*(jump+1))]);
			}
		}
		
		return jumpAvailable(board, point, 2, list);
	}
	
	public static ArrayList<BoardPoint> jumpAvailable(BoardPoint[] board, BoardPoint point, int jump, ArrayList<BoardPoint> jumps)
	{
		if(jumps.isEmpty()) return jumps;
		
		ArrayList<BoardPoint> list = jumps;
		if (point.getPiece() == RED_REGULAR){
			if( point.getIndex() + (9*jump) <= 63 && (point.getCol() != 7 || point.getCol() != 6) && point.getIndex() + (9*(jump+2)) <= 63){
				if( differentPiece(point, board[point.getIndex()+(9*(jump+1))]) && validSpot(board[point.getIndex()+(9*(jump+2))]))
					list.add(board[point.getIndex()+( 9*(jump+2) )]);
			}
			if( point.getIndex() + (7*jump) <= 63 && (point.getCol() != 0 || point.getCol() != 1) && point.getIndex() + (7*(jump+2)) <= 63){
				if( differentPiece(point, board[point.getIndex()+(7*(jump+1))]) && validSpot(board[point.getIndex()+(7*(jump+2))]))
					list.add(board[point.getIndex()+(7*(jump+2))]);
			}
		}
		else if (point.getPiece() == BLACK_REGULAR){
			if( point.getIndex() - (9*jump) >= 0 && (point.getCol() != 0 || point.getCol() != 1) && point.getIndex() - (9*(jump+2)) >= 0){
				if( differentPiece(point, board[point.getIndex()-(9*(jump+1))]) && validSpot(board[point.getIndex()-(9*(jump+2))]))
					list.add(board[point.getIndex()-(9*(jump+2))]);
			}			
			if( point.getIndex() - (7*jump) >= 0 && (point.getCol() != 7 || point.getCol() != 6) && point.getIndex() - (7*(jump+2)) >= 0){
				if( differentPiece(point, board[point.getIndex()-(7*(jump+1))]) && validSpot(board[point.getIndex()-(7*(jump+2))]))
					list.add(board[point.getIndex()-(7* (jump+2))]);
			}
		}
		else if (point.getPiece() == BLACK_KING || point.getPiece() == RED_KING){
			if( point.getIndex() + (9*jump) <= 63 && (point.getCol() != 7 || point.getCol() != 6) && point.getIndex() + (9*(jump+2)) <= 63){
				if( differentPiece(point, board[point.getIndex()+(9*(jump+1))]) && validSpot(board[point.getIndex()+(9*(jump+2))]))
					list.add(board[point.getIndex()+(9*(jump+2))]);
			}
			if( point.getIndex() + (7*jump) <= 63 && (point.getCol() != 0 || point.getCol() != 1) && point.getIndex() + (7*(jump+2)) <= 63){
				if( differentPiece(point, board[point.getIndex()+(7*(jump+1))]) && validSpot(board[point.getIndex()+(7*(jump+2))]))
					list.add(board[point.getIndex()+(7*(jump+2))]);
			}
			if( point.getIndex() - (9*jump) >= 0 && (point.getCol() != 7 || point.getCol() != 6) && point.getIndex() - (9*(jump+2)) >= 0){
				if( differentPiece(point, board[point.getIndex()-(9*(jump+1))]) && validSpot(board[point.getIndex()-(9*(jump+2))]))
					list.add(board[point.getIndex()-(9*(jump+2))]);
			}
			if( point.getIndex() - (7*jump) >= 0 && (point.getCol() != 0 || point.getCol() != 1) && point.getIndex() - (7*(jump+2)) >= 0){
				if( differentPiece(point, board[point.getIndex()-(7*(jump+1))]) && validSpot(board[point.getIndex()-(7*(jump+2))]))
					list.add(board[point.getIndex()-(7*(jump+2))]);
			}
		}
		
		return list;
	}
	
	//tile1 is usually the full spot
	public static void makeMove(TileGraphic tile1, TileGraphic tile2)
	{
		int index1 = tile1.getRow() * 8 + tile1.getColumn();
		int index2 = tile2.getRow() * 8 + tile2.getColumn();
		
		makeMove(gameFrame.gameBoard.points[index1], gameFrame.gameBoard.points[index2], tile1);
	}
	
	public static void makeMove(BoardPoint startPoint, BoardPoint endPoint, TileGraphic tile)
	{	
		gameFrame.gameBoard.swap(startPoint, endPoint);
		startPoint.swap(endPoint);
		if( (startPoint.getIndex() >= 0 && startPoint.getIndex() <= 7) && tile.getColorPiece() == Color.BLACK ){
			tile.setKing();
			startPoint.setPiece(CheckerType.BLACK_KING);
		}
		else if ( (startPoint.getIndex() >= 56 && startPoint.getIndex() <= 63) && tile.getColorPiece() == Color.RED ){
			tile.setKing();
			startPoint.setPiece(CheckerType.RED_KING);
		}
	}
	
	public static void makeJump(TileGraphic tile1, TileGraphic tile2, ArrayList<BoardPoint> jumps)
	{
		BoardPoint[] points = gameFrame.gameBoard.points;
		
		int index1 = tile1.getRow() * 8 + tile1.getColumn();
		int index2 = tile2.getRow() * 8 + tile2.getColumn();
		
		if ( Math.abs(index1 - index2) == 28 ){
			int index3 = (index1+index2)/2;
			makeJump(points[index1], points[index3], points[index2], tile1, 28);
		}
		else if ( Math.abs(index1 - index2) == 36 ){
			int index3 = (index1+index2)/2;
			makeJump(points[index1], points[index3], points[index2], tile1, 36);
		}
		else {
			makeJump(points[index1], points[index2], tile1);
		}
		
	}
	
	public static void makeJump(BoardPoint startPoint, BoardPoint endPoint, TileGraphic tile)
	{
		BoardPoint[] points = gameFrame.gameBoard.points;
		int index = startPoint.getIndex()-((startPoint.getIndex()-endPoint.getIndex())/2);
		
		if (gameFrame.gameBoard.points[index].getPiece() == BLACK_REGULAR || points[index].getPiece() == BLACK_KING)
			gameFrame.pm.p1.eliminatePiece(1);
		else if (points[index].getPiece() == RED_REGULAR || points[index].getPiece() == RED_KING)
			gameFrame.pm.p2.eliminatePiece(1);
			
		gameFrame.gameBoard.emptySpot(points[index]);
		gameFrame.gameBoard.swap(startPoint, endPoint);
		startPoint.swap(endPoint);
		
		if( (startPoint.getIndex() >= 0 && startPoint.getIndex() <= 7) && tile.getColorPiece() == Color.BLACK ){
			tile.setKing();
			startPoint.setPiece(CheckerType.BLACK_KING);
		}
		else if ( (startPoint.getIndex() >= 56 && startPoint.getIndex() <= 63) && tile.getColorPiece() == Color.RED ){
			tile.setKing();
			startPoint.setPiece(CheckerType.RED_KING);
		}
	}
	
	public static void makeJump(BoardPoint startPoint, BoardPoint midPoint, BoardPoint endPoint, TileGraphic tile, int jumpLength)
	{
		BoardPoint[] points = gameFrame.gameBoard.points;
		int index1, index2;
		if(jumpLength == 28){
			index1 = midPoint.getIndex() - 7;
			index2 = midPoint.getIndex() + 7;
		}
		else {
			index1 = midPoint.getIndex() - 9;
			index2 = midPoint.getIndex() + 9;
		}
		
		if ( (gameFrame.gameBoard.points[index1].getPiece() == BLACK_REGULAR || points[index1].getPiece() == BLACK_KING ) &&
				(gameFrame.gameBoard.points[index2].getPiece() == BLACK_REGULAR || points[index2].getPiece() == BLACK_KING ) )
			gameFrame.pm.p1.eliminatePiece(2);
		else if ( (points[index1].getPiece() == RED_REGULAR || points[index1].getPiece() == RED_KING) && 
					(points[index2].getPiece() == RED_REGULAR || points[index2].getPiece() == RED_KING))
			gameFrame.pm.p2.eliminatePiece(2);
			
		TileGraphic tile2 = gameFrame.gb.game.getTile(points[index1].getCol(), points[index1].getRow() );
		TileGraphic tile3 = gameFrame.gb.game.getTile(points[index2].getCol(), points[index2].getRow() );
		
		tile2.makeEmpty();
		tile3.makeEmpty();
			
		gameFrame.gameBoard.emptySpot(points[index1]);
		gameFrame.gameBoard.emptySpot(points[index2]);
		gameFrame.gameBoard.swap(startPoint, endPoint);
		startPoint.swap(endPoint);
		if( (startPoint.getIndex() >= 0 && startPoint.getIndex() <= 7) && tile.getColorPiece() == Color.BLACK ){
			tile.setKing();
			startPoint.setPiece(CheckerType.BLACK_KING);
		}
		else if ( (startPoint.getIndex() >= 56 && startPoint.getIndex() <= 63) && tile.getColorPiece() == Color.RED ){
			tile.setKing();
			startPoint.setPiece(CheckerType.RED_KING);
		}
	}
	
	public static boolean validIndex(int index)
	{
		if (index >= 0 && index <= 63)
			return true;
		return false;
	}
	
	public static boolean validSpot(BoardPoint point)
	{
		if(point.getPiece() == 0 && point.getSpaceColor() == Color.BLACK)
			return true;
		return false;
	}
	
	public static boolean differentPiece(BoardPoint startPoint, BoardPoint diffPoint)
	{
		if ( (startPoint.getPiece() != diffPoint.getPiece()) && diffPoint.getPiece() != 0){
			if( startPoint.getPiece() == BLACK_KING )
				return (diffPoint.getPiece() != BLACK_REGULAR) ? true : false;
			else if ( startPoint.getPiece() == RED_KING )
				return (diffPoint.getPiece() != RED_REGULAR) ? true : false;
			else if ( startPoint.getPiece() == BLACK_REGULAR)
				return (diffPoint.getPiece() != BLACK_KING) ? true : false;
			else if ( startPoint.getPiece() == RED_REGULAR)
				return (diffPoint.getPiece() != RED_KING) ? true : false;
		}
		return false;
	}

	public static int checkForWin()
	{
		if (gameFrame.pm.p2.getGamePieces() == 0)
			return 1;
		else if (gameFrame.pm.p1.getGamePieces() == 0)
			return 2;
		return 0;
	}
	
	public static void main(String[] args) {
		gameFrame = new GameGraphic();
	}
	
}

//player class
	
class Player implements Serializable, Comparable<Player> {
		
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
	public void eliminatePiece(int takeaway)
	{ gamePieces = gamePieces - takeaway; }
	
	public int getGamePieces()
	{ return gamePieces; }

	@Override
	public String toString() {
		return  String.format("%-12s%12d\n", name, wins);
	}

	@Override
	public int compareTo(Player player) {
		return (Integer.compare(player.wins, this.wins));
	}
}

class GameBoard extends CheckerType{
	
	protected static BoardPoint[] points = new BoardPoint[64];
	protected static BoardGraphic boardGraphic;

	public GameBoard(BoardGraphic bg)
	{			
		boardGraphic = bg;
		fillBoard();
		//printBoard();
	}

	public GameBoard(BoardGraphic bg, BoardPoint[] bp) 
	{
		points = bp;
		boardGraphic = bg;
		fillBoardFromPoints();
		//printBoard();
	}

	public void fillBoardFromPoints() {
		for(int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				switch (points[i * 8 + j].getPiece()) {
					case RED_REGULAR:
						boardGraphic.addCheckerPieceToTile(Color.RED, j, i);
						break;
					case BLACK_REGULAR:
						boardGraphic.addCheckerPieceToTile(Color.BLACK, j, i);
						break;
					case RED_KING:
						boardGraphic.addCheckerPieceToTile(Color.RED, j, i);
						boardGraphic.getTile(j, i).setKing();
						break;
					case BLACK_KING:
						boardGraphic.addCheckerPieceToTile(Color.BLACK, j ,i);
						boardGraphic.getTile(j, i).setKing();
						break;
				}
			}
		}
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
					points[i] = new BoardPoint(i, x, y, Color.BLACK, RED_REGULAR);
					boardGraphic.addCheckerPieceToTile(Color.RED, y, x);
				}
				else if(x%2 != 0 && y%2 == 0){
					points[i] = new BoardPoint(i, x, y, Color.BLACK, RED_REGULAR);
					boardGraphic.addCheckerPieceToTile(Color.RED, y, x);
				}
				else
					points[i] = new BoardPoint(i, x, y, Color.RED);
			}
			else if ( i > 23 && i < 40){
				if(x%2 == 0 && y%2 != 0)
					points[i] = new BoardPoint(i, x, y, Color.BLACK);
				else if(x%2 != 0 && y%2 == 0)
					points[i] = new BoardPoint(i, x, y, Color.BLACK);
				else
					points[i] = new BoardPoint(i, x, y, Color.RED);
			}
			else {
				if(x%2 == 0 && y%2 != 0){
					points[i] = new BoardPoint(i, x, y, Color.BLACK, BLACK_REGULAR);
					boardGraphic.addCheckerPieceToTile(Color.BLACK, y, x);
				}
				else if(x%2 != 0 && y%2 == 0){
					points[i] = new BoardPoint(i, x, y, Color.BLACK, BLACK_REGULAR);
					boardGraphic.addCheckerPieceToTile(Color.BLACK, y, x);
				}
				else
					points[i] = new BoardPoint(i, x, y, Color.RED);
			}
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
	}

	public void swap(BoardPoint point1, BoardPoint point2){
		points[point1.getIndex()] = point2;
		points[point2.getIndex()] = point1;
	}
	
	public void emptySpot(BoardPoint point)
	{
		point.setPiece(0);
		points[point.getIndex()].setPiece(0); 
		printBoard();
	}
}

class CheckerType {
	public static final int BLACK_REGULAR = 1;
	public static final int RED_REGULAR = 2;
	public static final int BLACK_KING = 3;
	public static final int RED_KING = 4;
}

class BoardPoint implements Serializable {
	private int row;
	private int column;
	private int index;
	private int piece;
	private Color spaceColor;
	
	public BoardPoint(BoardPoint point){
		index = point.index;
		row = point.row;
		column = point.column;
		piece = point.piece;
		spaceColor = point.spaceColor;
	}
	
	public BoardPoint(int i, int r, int c, Color color){
		this(i, r, c, color, 0);
	}
	
	public BoardPoint(int i, int r, int c, Color color, int p){
		index = i;
		row = r;
		column = c;
		piece = p;
		spaceColor = color;
	}
	
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
	
	public Color getSpaceColor(){
		return spaceColor;
	}
	
	public boolean equals(BoardPoint p){
		if (p.getRow() == row && p.getCol() == column)
			return true;
		return false;
	}
	
	public void swap(BoardPoint point2){
		BoardPoint temp = new BoardPoint(this);

		index = point2.index;
		row = point2.row;
		column = point2.column;

		point2.index = temp.index;
		point2.row = temp.row;
		point2.column = temp.column;
	}
	
}