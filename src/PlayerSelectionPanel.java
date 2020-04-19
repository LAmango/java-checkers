import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class PlayerSelectionPanel extends JPanel implements Style {

    public DefaultListModel playerListModel;
    public JButton next;
    public JTextField textField;
    public JList players;
    public JLabel hint;
    public JButton cancel;
    public int playerNum;

    PlayerSelectionPanel(ArrayList<Player> playerArrayList, int pN, boolean start) {

        playerListModel = new DefaultListModel();
        // listModel setup
        for (Player p: playerArrayList) {
            playerListModel.addElement(p.name);
        }

        playerNum = pN;

        setBorder(new EmptyBorder(10, 10, 10, 10));
        setBackground(BACKGROUND);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        hint = new JLabel("Select a player from the list or type one in yourself!");

        textField = new JTextField(20);
        textField.setBackground(BACKGROUND);
        textField.setMaximumSize(new Dimension(Integer.MAX_VALUE, textField.getPreferredSize().height));
        textField.setBorder(BorderFactory.createTitledBorder("Player" + playerNum));
        textField.getDocument().addDocumentListener(new WelcomeScreenGraphic.TextFieldListener(playerNum, textField));
        add(textField);
        add(Box.createVerticalStrut(10));

        players = new JList(playerListModel);
        players.setBackground(BACKGROUND);
        players.getSelectionModel().addListSelectionListener(new WelcomeScreenGraphic.PlayerListListener(textField, players, hint, playerNum, this));
        players.setBorder(BorderFactory.createTitledBorder("Player Selection"));
        players.setMaximumSize(new Dimension(Integer.MAX_VALUE, players.getPreferredSize().height));
        add(players);
        add(Box.createVerticalStrut(10));

        hint.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(hint);
        add(Box.createVerticalStrut(10));

        // buttons
        Box buttons = new Box(BoxLayout.X_AXIS);
        next = new JButton(start ? "Start" : "Next");
        next.addActionListener(start ? new WelcomeScreenGraphic.StartGame(playerListModel) : new WelcomeScreenGraphic.P2Panel(this));
        buttons.add(next);

        cancel = new JButton("Cancel");
        cancel.addActionListener(new WelcomeScreenGraphic.LBPanel());
        buttons.add(Box.createHorizontalStrut(10));
        buttons.add(cancel);

        add(Box.createVerticalGlue());
        add(buttons);
    }

    public void resetPanel() {
        textField.setText("");
        players.clearSelection();
        hint.setForeground(Color.BLACK);
        hint.setText("Select a player from the list or type one in yourself!");
        next.setEnabled(false);
    }
}