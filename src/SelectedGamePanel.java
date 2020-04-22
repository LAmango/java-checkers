import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public class SelectedGamePanel extends JPanel implements Style {

    JLabel name, player1, player2, pieces1, pieces2, time;

    SelectedGamePanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(BACKGROUND);
        setBorder(BorderFactory.createTitledBorder("Selected Game"));
    }

    SelectedGamePanel(Game game) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(BACKGROUND);
        setBorder(BorderFactory.createTitledBorder("Selected Game"));

        // components
        name = new JLabel("Name: " + game.name);
        name.setAlignmentX(Component.LEFT_ALIGNMENT);
        Box player1Box = new Box(BoxLayout.Y_AXIS);
        Box player2Box = new Box(BoxLayout.Y_AXIS);
        player1 = new JLabel(game.player1.name );
        pieces1 = new JLabel("Pieces: " + game.player1.getGamePieces());
        player1Box.add(player1);
        player1Box.add(pieces1);

        player2 = new JLabel(game.player2.name);
        pieces2 = new JLabel("Pieces: " + game.player2.getGamePieces());
        player2Box.add(player2);
        player2Box.add(pieces2);

        time = new JLabel("Last Saved: " + game.time.format(DateTimeFormatter.ofPattern("MM/dd/yy hh:mm a")));
        time.setAlignmentX(Component.LEFT_ALIGNMENT);
        JPanel playerPanel = new JPanel();
        playerPanel.setBackground(BACKGROUND);
        playerPanel.setLayout(new GridLayout(1,2));
        playerPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // add components
        add(Box.createVerticalStrut(10));
        add(name);
        add(time);
        playerPanel.add(player1Box);
        playerPanel.add(player2Box);
        add(Box.createVerticalStrut(10));
        add(playerPanel);
    }
}
