import javax.swing.*;
import java.awt.*;
import java.awt.geom.GeneralPath;

public class TileGraphic extends JPanel {
    private final Color backgroundColor;
    private Color pieceColor;
    private int row;
    private int col;
    private Boolean hasCheckPiece = false;
    private Boolean isHighlighted = false;
    private Boolean isKing = false;

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
        int border = getWidth() / 10;
        if(hasCheckPiece) {
            g.setColor(pieceColor);
            g.fillOval(border /2, border /2, getWidth()- border, getHeight()- border);
            //white border
            g.setColor(Color.WHITE);
            g.drawOval(border /2, border /2, getWidth()- border, getHeight()- border);
        }
        if (isHighlighted) {
            g.setColor(Color.YELLOW);
            g.drawRect(0,0,getWidth()-1, getHeight()-1);
        }
        if (isKing) {
            g.setColor(pieceColor);
            g.fillOval(border /2, border /2, getWidth()- border, getHeight()- border);
            //white border
            g.setColor(Color.WHITE);
            g.drawOval(border /2, border /2, getWidth()- border, getHeight()- border);
            // crown
            g.setColor(Color.YELLOW);
            Graphics2D g2 = (Graphics2D) g;
            GeneralPath crown = new GeneralPath();
            float crownL = border *2;
            float crownT = border *2;
            float crownB = getHeight() - border *2;
            float crownR = getWidth() - border *2;

            crown.moveTo(crownL, crownT);
            crown.lineTo(crownL, crownB);
            crown.lineTo(crownR, crownB);
            crown.lineTo(crownR, crownT);
            crown.lineTo((crownR+crownL)*2/3, (crownB+crownT)/2);
            crown.lineTo((crownR+crownL)/2, crownT);
            crown.lineTo((crownR+crownL)*1/3, (crownB+crownT)/2);
            crown.lineTo(crownL, crownT);
            g2.fill(crown);

        }
        setBackground(backgroundColor);
    }

    public void setCheckerPiece(Color color) {
        hasCheckPiece = true;
        pieceColor = color;
        repaint();
    }
    
    public Color getBackgroundColor(){
        return backgroundColor;
    }
    
    public boolean isEmpty(){
        if(hasCheckPiece)
            return false;
        return true;
    }
    
    public void makeEmpty(){
        hasCheckPiece = false;
        repaint();
    }

    public void setHighlight() {
        isHighlighted = !isHighlighted;
        repaint();
    }

    public void setKing() {
        isKing = true;
        repaint();
    }
    
    public int getRow(){
        return row;
    }
    
    public int getColumn(){
        return col;
    }
    
    public boolean getKing(){
        return isKing;
    }
    
    public boolean equalTile(TileGraphic tile){
        if(this.row == tile.row && this.col == tile.col)
            return true;
        return false;
    }
    
    public void swap(TileGraphic tile){
        TileGraphic temp = this;
         
        this.hasCheckPiece = tile.hasCheckPiece;
        this.isKing = tile.isKing;
        this.pieceColor = tile.pieceColor;
        this.row = tile.row;
        this.col = tile.col;
        
        tile.hasCheckPiece = temp.hasCheckPiece;
        tile.isKing = temp.isKing;
        tile.pieceColor = temp.pieceColor;
        tile.row = temp.row;
        tile.col = temp.col;
        if(!this.hasCheckPiece)
            makeEmpty();
            
        repaint();
    }
}
