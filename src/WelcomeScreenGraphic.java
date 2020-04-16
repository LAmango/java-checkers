import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WelcomeScreenGraphic extends JPanel {

    CardLayout card = new CardLayout();
    JPanel bottomWelcome = new JPanel();

    public WelcomeScreenGraphic(GameGraphic frame) {
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

        // first player panel
        JPanel firstPlayer = new JPanel();
        firstPlayer.setLayout(new BoxLayout(firstPlayer, BoxLayout.Y_AXIS));
        firstPlayer.add(new JLabel("Player 1"));
        firstPlayer.add(Box.createVerticalGlue());
        firstPlayer.add(new JTextField(20));
        JButton nextFirst = new JButton("Next");
        nextFirst.addMouseListener(new NextPanel("p2"));
        firstPlayer.add(Box.createVerticalGlue());
        firstPlayer.add(nextFirst);

        // second player panel
        JPanel secondPlayer = new JPanel();
        secondPlayer.setLayout(new BoxLayout(secondPlayer, BoxLayout.Y_AXIS));
        secondPlayer.add(new JLabel("Player 2"));
        secondPlayer.add(Box.createVerticalGlue());
        secondPlayer.add(new JTextField(20));
        JButton startSecond = new JButton("Start");
        startSecond.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                frame.startGame();
            }
        });
        secondPlayer.add(Box.createVerticalGlue());
        secondPlayer.add(startSecond);

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

    public class NextPanel extends MouseAdapter {
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
}
