import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

public class WelcomeScreenGraphic extends JPanel {

    static CardLayout card = new CardLayout();
    static JPanel bottomWelcome = new JPanel();
    static GameGraphic frame;
    static Boolean playerAdded = false;
    static String firstPlayerName = null;
    static String secondPlayerName = null;
    static JButton startButton;
    static PlayerManager pm;

    static {
        try {
            pm = new PlayerManager();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

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
        title.setBackground(Color.GRAY);
        title.setBorder(new EmptyBorder(5,5,5,5));

        // button component
        startButton = new JButton("Start Game");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.addMouseListener(new NextPanel("p1"));

        // bottom welcomeScreen cardLayout component
        LeaderBoard leaderboard = new LeaderBoard(pm.players);

        PlayerSelectionPanel firstPlayer = new PlayerSelectionPanel(pm.players, 1, false);
        PlayerSelectionPanel secondPlayer = new PlayerSelectionPanel(pm.players, 2, true);

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
        private DefaultListModel listModel;

        public NextPanel(String p, DefaultListModel lm) {
            panel = p;
            listModel = lm;
        }

        public NextPanel(String p) {
            panel = p;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (panel.equals("lb")) {
                startButton.setEnabled(true);
                firstPlayerName = null;
                secondPlayerName = null;
            } else {
                startButton.setEnabled(false);
                if (playerAdded) {
                    listModel.addElement(firstPlayerName);
                    try {
                        pm.addPlayer(firstPlayerName);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
            playerAdded = false;
            card.show(bottomWelcome, panel);
            super.mouseClicked(e);
        }
    }

    public static class StartGame extends MouseAdapter {
        private DefaultListModel listModel;

        StartGame(DefaultListModel lm) {
            listModel = lm;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if(playerAdded) {
                listModel.addElement(secondPlayerName);
                try {
                    pm.addPlayer(secondPlayerName);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            playerAdded = false;
            card.show(bottomWelcome, "lb");
            frame.startGame();
            super.mouseClicked(e);
        }
    }

    public static class PlayerListListener implements ListSelectionListener {
        private final JTextField textfield;
        private final JLabel hintText;
        private final JList players;
        private final int playerNum;

        PlayerListListener(JTextField tf, JList p, JLabel h, int pl) {
            textfield = tf;
            players = p;
            hintText = h;
            playerNum = pl;
        }

        public void valueChanged(ListSelectionEvent e) {
            String selectedName = players.getSelectedValue().toString();
            textfield.setText(selectedName);
            playerAdded = false;
            if (playerNum == 1) {
                firstPlayerName = selectedName;
            } else if (playerNum == 2) {
                if (firstPlayerName.equals(selectedName)) {
                    System.out.println("Selected the same player! " + playerNum);
                    hintText.setBackground(Color.RED);
                    hintText.setText("That player has already been chosen!");
                } else {
                    secondPlayerName = selectedName;
                    hintText.setBackground(Color.BLACK);
                    hintText.setText("Select a player from the list or type one in yourself!");
                }
            }
        }
    }

    public static class TextFieldListener implements DocumentListener {

        private final int playerNum;
        private final JTextField textField;

        public TextFieldListener(int p, JTextField tf) {
           playerNum = p;
           textField = tf;
        }

        public void update(DocumentEvent e) {
            playerAdded = true;
            if (playerNum == 1) {
                firstPlayerName = textField.getText();
            } else {
                secondPlayerName = textField.getText();
            }
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            update(e);
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            update(e);
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            update(e);
        }
    }
}
