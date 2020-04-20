import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class LeaderBoard extends JPanel implements Style {

    public DefaultListModel playerListModel;
    public JLabel lb_title;
    public JList playerList;

    public LeaderBoard(ArrayList<Player> players) {
        playerListModel = new DefaultListModel();

        for (Player p: players) {
            playerListModel.addElement(p);
        }
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(BACKGROUND);
        setBorder(new EmptyBorder(10,10,10,10));
        setOpaque(true);

        // title
        lb_title = new JLabel("Leaderboard");
        lb_title.setFont(new Font("Time New Roman", Font.BOLD, 20));
        add(lb_title);
        lb_title.setForeground(TITLE_COLOR);
        add(Box.createVerticalStrut(10));

        // leaderboard list
        playerList = new JList(playerListModel);
        playerList.setBorder(BorderFactory.createTitledBorder("Players"));
        playerList.setBackground(BACKGROUND);
        playerList.setMaximumSize(new Dimension(Integer.MAX_VALUE, playerList.getPreferredSize().height));
        playerList.setFont(new Font("Monospaced", Font.BOLD, 12));
        add(playerList);
    }
}
