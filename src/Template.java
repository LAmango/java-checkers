import javax.swing.*;
import java.awt.*;
import java.awt.geom.GeneralPath;
import java.util.Random;

public class Template {
    public  static class MyPanel extends JPanel
    {
        private Random random = new Random();

        public void paintComponent( Graphics g )
        {
            super.paintComponent( g ); // call superclass's paintComponent

            this.setBackground( Color.WHITE );

            g.setColor(Color.GRAY);
            g.fillOval(50,50, 50, 50);
        }
    }

    public static void main( String args[] )
    {
        // create frame for JPanel
        JFrame frame = new JFrame( "Triangles" );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        MyPanel p = new MyPanel();
        frame.add( p );
        frame.setSize( 500, 500 );
        frame.setVisible( true );
    }
}
