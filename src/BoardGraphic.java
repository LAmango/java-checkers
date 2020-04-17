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

    public BoardGraphic() {
        spots = new ArrayList<BoardPoint>(0);
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

    public void turnPieceIntoKing(int row, int col) {
        TileGraphic t = this.getTile(row, col);
        t.setKing();
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
            if ( !spots.isEmpty() ){
                for (BoardPoint p : spots){
                    highlightTile(p.getCol(), p.getRow());
                }
            }
            if (tileGraphic.isEmpty()){
                checkerLogic.makeMove(tileGraphic, lastClickedTile);
                tileGraphic.swap(lastClickedTile);
            }
            
            System.out.println(tileGraphic.getCords());
            lastClickedTile = tileGraphic;
            //tileGraphic.setKing();
            spots = checkerLogic.moveAvailable(tileGraphic);
            for (BoardPoint p : spots){
                highlightTile(p.getCol(), p.getRow());
            }
            //System.out.println("size: " + spots.size());
            super.mouseClicked(e);
        }
    }

}
