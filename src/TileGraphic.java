import javax.swing.*;
import java.awt.*;

public class TileGraphic extends JPanel {
    private Color backgroundColor;
    private int row, col;
    private Boolean hasCheckPiece = false;

    public TileGraphic(Color co, int r, int c) {
        backgroundColor = co;
        row = r;
        col = c;

    }

    public String getCords() {
        return "Row: " + row + " Col: " + col;
    }

    protected void paintComponent(Graphics g) {
        if(hasCheckPiece) {
            g.setColor(getBackground() == Color.RED ? Color.BLACK : Color.RED);
            g.fillOval(0,0, getWidth(), getHeight());
        }
        setBackground(backgroundColor);
    }

    public void setCheckerPiece() {
        hasCheckPiece = !hasCheckPiece;
        repaint();
    }
}
