import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.io.FileNotFoundException;
import java.io.IOException;

public class WelcomeScreenGraphic extends JPanel implements Style {

    static CardLayout card = new CardLayout();
    static JPanel bottomWelcome = new JPanel();
    static GameGraphic frame;
    static PlayerManager pm;
    static Boolean playerAdded = false;
    static JButton startButton;
    static PlayerSelectionPanel firstPlayer;
    static PlayerSelectionPanel secondPlayer;
    static LeaderBoard leaderBoard;

    public WelcomeScreenGraphic(GameGraphic f) {
        frame = f;
        pm = frame.pm;
        // set panel settings
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(10,10,10,10));
        setBackground(Color.BLACK);

        bottomWelcome.setLayout(card);

        // title component
        JLabel title  = new JLabel("Checkers");
        title.setFont(new Font("Times New Roman", Font.BOLD, 60));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setForeground(TITLE_COLOR);
        title.setBorder(new EmptyBorder(5,5,5,5));

        // button component
        startButton = new JButton("Start Game");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.addActionListener(new P1Panel());

        // bottom welcomeScreen cardLayout component
        leaderBoard = new LeaderBoard(pm.players);

        firstPlayer = new PlayerSelectionPanel(pm.players, 1, false);
        secondPlayer = new PlayerSelectionPanel(pm.players, 2, true);

        bottomWelcome.add("lb", leaderBoard);
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

    public static void updateLeaderBoard() {
        leaderBoard.updateBoard(frame.pm.players);
    }

    public static class P1Panel implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            startButton.setEnabled(false);
            firstPlayer.next.setEnabled(false);
            firstPlayer.resetPanel();
            card.next(bottomWelcome);
        }
    }

    public static class P2Panel implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(playerAdded) {
                firstPlayer.playerListModel.addElement(pm.firstPlayer);
                secondPlayer.playerListModel.addElement(pm.firstPlayer);
                leaderBoard.playerListModel.addElement(pm.firstPlayer);
                try {
                    pm.addPlayer(pm.firstPlayer);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
            playerAdded = false;
            secondPlayer.next.setEnabled(false);
            secondPlayer.resetPanel();
            card.next(bottomWelcome);
        }
    }

    public static class LBPanel implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            startButton.setEnabled(true);
            card.show(bottomWelcome, "lb");
        }
    }

    public static class StartGame implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if(playerAdded) {
                secondPlayer.playerListModel.addElement(pm.secondPlayer);
                firstPlayer.playerListModel.addElement(pm.secondPlayer);
                leaderBoard.playerListModel.addElement(pm.secondPlayer);
                try {
                    pm.addPlayer(pm.secondPlayer);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            playerAdded = false;
            card.show(bottomWelcome, "lb");
            startButton.setEnabled(true);
            pm.createPlayersForGame();
            frame.startGame();
        }
    }

    public static class PlayerListListener implements ListSelectionListener {
        private final JTextField textfield;
        private JLabel hintText;
        private final JList players;
        private final int playerNum;
        private PlayerSelectionPanel panel;

        PlayerListListener(JTextField tf, JList p, JLabel h, int pl, PlayerSelectionPanel pan) {
            textfield = tf;
            players = p;
            hintText = h;
            playerNum = pl;
            panel = pan;
        }

        public void valueChanged(ListSelectionEvent e) {
            if (players.getSelectedValue() == null) return;
            String selectedName = players.getSelectedValue().toString();
            textfield.setText(selectedName);
            playerAdded = false;
            if (playerNum == 1) {
                firstPlayer.next.setEnabled(true);
                pm.firstPlayer = selectedName;
            } else if (playerNum == 2) {
                if (pm.firstPlayer.equals(selectedName)) {
                    System.out.println("Selected the same player! " + playerNum);
                    hintText.setForeground(Style.ERROR);
                    hintText.setText("That player has already been chosen!");
                    panel.next.setEnabled(false);
                } else {
                    pm.secondPlayer = selectedName;
                    hintText.setForeground(Color.BLACK);
                    hintText.setText("Select a player from the list or type one in yourself!");
                    panel.next.setEnabled(true);
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
                firstPlayer.next.setEnabled(true);
                pm.firstPlayer = textField.getText();
            } else {
                secondPlayer.next.setEnabled(true);
                pm.secondPlayer = textField.getText();
            }
            if (textField.getText().length() == 0) {
                firstPlayer.next.setEnabled(false);
                secondPlayer.next.setEnabled(false);
                playerAdded = false;
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
