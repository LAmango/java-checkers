import javax.swing.*;
import java.awt.*;

public class TileGraphic extends JPanel {
    private Color backgroundColor;
    private Color pieceColor;
    private int border = 10;
    private int row, col;
    private Boolean hasCheckPiece = false;

    public TileGraphic(Color co, int r, int c) {
        backgroundColor = co;
        row = r;
        col = c;
        setBackground(backgroundColor);
    }

    public String getCords() {
        return "Row: " + row + " Col: " + col;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(hasCheckPiece) {
            g.setColor(pieceColor);
            g.fillOval(border/2,border/2, getWidth()-border, getHeight()-border);
            //white border
            g.setColor(Color.WHITE);
            g.drawOval(border/2,border/2, getWidth()-border, getHeight()-border);
        }
        setBackground(backgroundColor);
    }

    public void setCheckerPiece(Color color) {
        hasCheckPiece = true;
        pieceColor = color;
        repaint();
    }
}
