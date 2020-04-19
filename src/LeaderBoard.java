import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class LeaderBoard extends JPanel {

    public LeaderBoard(ArrayList<Player> players) {
        DefaultListModel playerListModel = new DefaultListModel();

        for (Player p: players) {
            playerListModel.addElement(p);
        }
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(95,95,95));
        setBorder(new EmptyBorder(10,10,10,10));
        setOpaque(true);
        JLabel lb_title = new JLabel("Leaderboard");
        lb_title.setFont(new Font("Time New Roman", Font.BOLD, 20));
        add(lb_title);
        add(Box.createVerticalStrut(10));

        JList playerList = new JList(playerListModel);
        playerList.setBorder(BorderFactory.createTitledBorder("Players"));
        playerList.setBackground(new Color(95,95,95));
        playerList.setMaximumSize(new Dimension(Integer.MAX_VALUE, playerList.getPreferredSize().height));
        playerList.setFont(new Font("Monospaced", Font.BOLD, 12));
        add(playerList);
    }
}
