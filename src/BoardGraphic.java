import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoardGraphic extends JPanel {

    private GridLayout gridBoard;

    public BoardGraphic() {
        gridBoard = new GridLayout(8,8);
        setLayout(gridBoard);
        int col =  0;
        int row  = 0;
        TileGraphic[][] pos = new TileGraphic[8][8];
        for (int i = 0; i < 8; i++) {
            if (i%2 == 0) {
                for (int j = 0; j < 8; j++) {
                    TileGraphic tileGraphic = new TileGraphic(j%2 == 0 ? Color.RED : Color.BLACK, row, col++);
                    tileGraphic.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            System.out.println(tileGraphic.getCords());
                            tileGraphic.setCheckerPiece();
                            super.mouseClicked(e);
                        }
                    });
                    add(tileGraphic);
                }
            }else {
                for (int j = 0; j < 8; j++) {
                    TileGraphic tileGraphic = new TileGraphic(j%2 == 0 ? Color.BLACK : Color.RED, row, col++);
                    tileGraphic.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            System.out.println(tileGraphic.getCords());
                            tileGraphic.setCheckerPiece();
                            super.mouseClicked(e);
                        }
                    });
                    add(tileGraphic);
                }
            }
            col = 0;
            row++;
        }
    }

}
