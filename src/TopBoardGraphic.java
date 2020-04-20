import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class TopBoardGraphic extends JPanel implements Style {

    static JLabel player1, player2;
    static TitledBorder blackTitle, noTitle;
    static Border blackline = BorderFactory.createLineBorder(Color.BLACK);
    static Border noLine = BorderFactory.createEmptyBorder();

    public TopBoardGraphic(Player p1, Player p2) {
        player1 = new JLabel(p1.name);
        blackTitle = BorderFactory.createTitledBorder(blackline, "Black");
        blackTitle.setTitleJustification(TitledBorder.CENTER);
        player1.setBorder(blackTitle);

        player2 = new JLabel(p2.name);
        noTitle = BorderFactory.createTitledBorder(noLine,"Red");
        noTitle.setTitleJustification(TitledBorder.CENTER);
        player2.setBorder(noTitle);

       setLayout(new GridLayout(0,2));

       add(player1);
       add(player2);
    }

    public static void togglePlayerTurn() {
        if (player1.getBorder().equals(blackTitle)) {
            noTitle.setTitle("Black");
            blackTitle.setTitle("Red");

            player1.setBorder(noTitle);
            player2.setBorder(blackTitle);
        } else {
            blackTitle.setTitle("Black");
            noTitle.setTitle("Red");

            player1.setBorder(blackTitle);
            player2.setBorder(noTitle);
        }
    }
}
