import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

public class BoardGraphic extends JPanel {

    private GridLayout gridBoard;
    protected TileGraphic lastClickedTile;
    protected GameBoard gameBoard;
    protected ArrayList<BoardPoint> spots;
    protected ArrayList<BoardPoint> jumps;
    protected static int turn;
    public GameGraphic frame;

    public BoardGraphic(GameGraphic f) {
        if (f.gm.selectedGame != null) {
            turn = f.gm.selectedGame.turn;
            if (turn == 2)
                TopBoardGraphic.togglePlayerTurn();
        } else {
            turn = 1;
        }
        frame = f;
        spots = new ArrayList<BoardPoint>(0);
        jumps = new ArrayList<BoardPoint>(0);
        lastClickedTile = new TileGraphic(Color.BLACK, -1, -1);
        gridBoard = new GridLayout(8,8);
        setLayout(gridBoard);
        int col =  0;
        int row  = 0;
        for (int i = 0; i < 8; i++) {
            if (i%2 == 0) {
                for (int j = 0; j < 8; j++) {
                    TileGraphic tileGraphic = new TileGraphic(j%2 == 0 ? Color.RED : Color.BLACK, row, col++);
                    if(tileGraphic.getBackgroundColor() == Color.BLACK)
                        tileGraphic.addMouseListener(new TileClickListener(tileGraphic));
                    add(tileGraphic);
                }
            }else {
                for (int j = 0; j < 8; j++) {
                    TileGraphic tileGraphic = new TileGraphic(j%2 == 0 ? Color.BLACK : Color.RED, row, col++);
                    if(tileGraphic.getBackgroundColor() == Color.BLACK)
                        tileGraphic.addMouseListener(new TileClickListener(tileGraphic));
                    add(tileGraphic);
                }
            }
            col = 0;
            row++;
        }
    }

    public Player getPlayer1() {
        return frame.pm.p1;
    }

    public Player getPlayer2() {
        return frame.pm.p2;
    }

    public void addCheckerPieceToTile(Color color, int row, int col) {
        TileGraphic t = this.getTile(row, col);
        t.setCheckerPiece(color);
    }

    public void highlightTile(int row, int col) {
        TileGraphic t = this.getTile(row, col);
        t.setHighlight();
    }
    
    public void resetHighlight(){
        for(int row = 0, col = 0; row < 8 ; col++){
            TileGraphic t = this.getTile(col, row);
            t.resetHighlight();
            if(col == 7){
                col = -1;
                row++;
            }
        }
    }

    public void turnPieceIntoKing(int row, int col) {
        TileGraphic t = this.getTile(row, col);
        t.setKing();
    }
    
    public void emptyCheckerPiece(int row, int col){
        TileGraphic t = this.getTile(col, row);
        t.makeEmpty();
    }

    public TileGraphic getTile(int row, int col) {
        return (TileGraphic) this.getComponent(col * 8 + row);
    }

    class TileClickListener extends MouseAdapter {
        private final TileGraphic tileGraphic;

        public TileClickListener(TileGraphic tg) {
            tileGraphic = tg;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            boolean match = false;
            for (BoardPoint p : spots){
                if(tileGraphic.equals(p))
                    match = true;
            }
            for (BoardPoint p : jumps){
                if(tileGraphic.equals(p))
                    match = true;
            }
            if (match == true && tileGraphic.getBackgroundColor() == Color.BLACK){
                if(jumps.isEmpty())
                    checkerLogic.makeMove(lastClickedTile, tileGraphic);
                else{
                    checkerLogic.makeJump(lastClickedTile, tileGraphic, jumps);
                    emptyCheckerPiece((tileGraphic.getRow()+lastClickedTile.getRow())/2, (tileGraphic.getColumn()+lastClickedTile.getColumn())/2);
                }
                tileGraphic.swap(lastClickedTile);
                turn = (turn == 1) ? 2 : 1;
                TopBoardGraphic.togglePlayerTurn();
                resetHighlight();
                if (checkerLogic.checkForWin() == 1)
                    checkerLogic.gameFrame.declareWinner(checkerLogic.gameFrame.pm.p1);
                else if (checkerLogic.checkForWin() == 2)
                    checkerLogic.gameFrame.declareWinner(checkerLogic.gameFrame.pm.p2);
            }
            else{
                if( (tileGraphic.getColorPiece() == Color.BLACK && turn == 1) || (tileGraphic.getColorPiece() == Color.RED && turn == 2) ){
                    resetHighlight();
                    jumps.clear();
                    jumps = checkerLogic.jumpAvailable(tileGraphic);
                    if(jumps.isEmpty())
                        spots = checkerLogic.moveAvailable(tileGraphic);
                    lastClickedTile = tileGraphic;
                    if(!jumps.isEmpty()){
                        for (BoardPoint p : jumps){
                            highlightTile(p.getCol(), p.getRow());
                        }
                    }
                    else {
                        for (BoardPoint p : spots){
                            highlightTile(p.getCol(), p.getRow());
                        }
                    }
                }
            }
            super.mouseClicked(e);
        }
    }

}
