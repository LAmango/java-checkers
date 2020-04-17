import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WelcomeScreenGraphic extends JPanel {

    static CardLayout card = new CardLayout();
    static JPanel bottomWelcome = new JPanel();
    static GameGraphic frame;
    static String firstPlayerName = null;
    static String secondPlayerName = null;
    String fakePlayers[] = {"Lucas", "Steven", "Dave", "Tessa", "Alice", "Bob", "Joe Exotic", "Carol Baskin"};

    public WelcomeScreenGraphic(){};

    public WelcomeScreenGraphic(GameGraphic f) {
        frame = f;
        // set panel settings
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(10,10,10,10));
        setBackground(Color.BLACK);

        bottomWelcome.setLayout(card);

        // title component
        JLabel title  = new JLabel("Checkers");
        title.setFont(new Font("Times New Roman", Font.BOLD, 60));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setBorder(new EmptyBorder(5,5,5,5));

        // button component
        JButton startButton = new JButton("Start Game");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.addMouseListener(new NextPanel("p1"));

        // bottom welcomeScreen cardLayout component
        JPanel leaderboard = new JPanel();
        leaderboard.setBackground(new Color(128,128,128,200));
        leaderboard.setBorder(new EmptyBorder(10,10,10,10));
        leaderboard.setOpaque(true);
        JLabel lb_title = new JLabel("Leaderboard");
        lb_title.setFont(new Font("Time New Roman", Font.BOLD, 20));
        leaderboard.add(lb_title);

        PlayerSelectionPanel firstPlayer = new PlayerSelectionPanel(fakePlayers, 1, false);
        PlayerSelectionPanel secondPlayer = new PlayerSelectionPanel(fakePlayers, 2, true);

        bottomWelcome.add("lb", leaderboard);
        bottomWelcome.add("p1", firstPlayer);
        bottomWelcome.add("p2", secondPlayer);


        // add components to panel
        add(Box.createVerticalStrut(20));
        add(title);
        add(Box.createVerticalStrut(20));
        add(startButton);
        add(Box.createVerticalStrut(20));
        add(bottomWelcome);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        final int tileWidth = getWidth()/2;
        final int tileHeight = getHeight()/2;

        graphics.setColor(Color.RED);
        graphics.fillRect(0,0, tileWidth,tileHeight);
        graphics.setColor(Color.BLACK);
        graphics.fillRect(getWidth()/2, 0, tileWidth, tileHeight);
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, getHeight()/2, tileWidth, tileHeight);
        graphics.setColor(Color.RED);
        graphics.fillRect(getWidth()/2, getHeight()/2, tileWidth, tileHeight);
    }

    public static class NextPanel extends MouseAdapter {
        private final String panel;

        public NextPanel(String p) {
            panel = p;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            card.show(bottomWelcome, panel);
            super.mouseClicked(e);
        }
    }

    public static class StartGame extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            frame.startGame();
            super.mouseClicked(e);
        }
    }

    public static class PlayerListListener implements ListSelectionListener {
        private final JTextField textfield;
        private final JList players;
        private final int playerNum;

        PlayerListListener(JTextField tf, JList p, int pl) {
            textfield = tf;
            players = p;
            playerNum = pl;
        }

        public void valueChanged(ListSelectionEvent e) {
            String selectedName = players.getSelectedValue().toString();
            textfield.setText(selectedName);
            if (playerNum == 1) {
                firstPlayerName = selectedName;
            } else if (playerNum == 2) {
                if (firstPlayerName.equals(selectedName)) {
                    System.out.println("Selected the same player! " + playerNum);
                } else {
                    secondPlayerName = selectedName;
                }
            }
        }
    }
}
