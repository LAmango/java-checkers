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

    public BoardGraphic() {
        spots = new ArrayList<BoardPoint>(0);
        jumps = new ArrayList<BoardPoint>(0);
        lastClickedTile = new TileGraphic(Color.BLACK, -1, -1);
        gridBoard = new GridLayout(8,8);
        setLayout(gridBoard);
        int col =  0;
        int row  = 0;
        TileGraphic[][] pos = new TileGraphic[8][8];
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
            TileGraphic t = this.getTile(row, col);
            t.resetHighlight();
            if(col == 7){
                col = 0;
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
        //System.out.println("CORDS: " + t.getCords());
        t.makeEmpty();
    }

    public TileGraphic getTile(int row, int col) {
        //System.out.println(this.getComponent(col * 8 + row));
        return (TileGraphic) this.getComponent(col * 8 + row);
    }

    class TileClickListener extends MouseAdapter {
        private final TileGraphic tileGraphic;

        public TileClickListener(TileGraphic tg) {
            tileGraphic = tg;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("NEW CLICK!!!");
            
            resetHighlight();
            System.out.println("jumps size1: " + jumps.size());
            spots.clear();
            if (tileGraphic.isEmpty()){
                if(jumps.isEmpty())
                    checkerLogic.makeMove(lastClickedTile, tileGraphic);
                else{
                    checkerLogic.makeJump(lastClickedTile, tileGraphic);
                    emptyCheckerPiece((tileGraphic.getRow()+lastClickedTile.getRow())/2, (tileGraphic.getColumn()+lastClickedTile.getColumn())/2);
                }
                tileGraphic.swap(lastClickedTile);
            }
            else{
                spots = checkerLogic.moveAvailable(tileGraphic);
                jumps = checkerLogic.jumpAvailable(tileGraphic);
                System.out.println("size: " + spots.size());
                System.out.println("jumps size2: " + jumps.size());
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
            System.out.println(tileGraphic.getCords());
            //tileGraphic.setKing();
            super.mouseClicked(e);
        }
    }

}
