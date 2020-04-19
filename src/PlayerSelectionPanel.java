import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class PlayerSelectionPanel extends JPanel {

    static DefaultListModel playerListModel;

    PlayerSelectionPanel(ArrayList<Player> playerArrayList, int playerNum, boolean start) {

        playerListModel = new DefaultListModel();
        // listModel setup
        for (Player p: playerArrayList) {
            playerListModel.addElement(p.name);
        }

        setBorder(new EmptyBorder(10, 10, 10, 10));
        setBackground(new Color(95, 95, 95));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JTextField textField;
        JList players;
        JLabel hint = new JLabel("Select a player from the list or type one in yourself!");

        textField = new JTextField(20);
        textField.setBackground(new Color(95,95,95));
        textField.setMaximumSize(new Dimension(Integer.MAX_VALUE, textField.getPreferredSize().height));
        textField.setBorder(BorderFactory.createTitledBorder("Player" + playerNum));
        textField.getDocument().addDocumentListener(new WelcomeScreenGraphic.TextFieldListener(playerNum, textField));
        add(textField);
        add(Box.createVerticalStrut(10));

        players = new JList(playerListModel);
        players.setBackground(new Color(95, 95, 95));
        players.getSelectionModel().addListSelectionListener(new WelcomeScreenGraphic.PlayerListListener(textField, players, hint, playerNum));
        players.setBorder(BorderFactory.createTitledBorder("Player Selection"));
        players.setMaximumSize(new Dimension(Integer.MAX_VALUE, players.getPreferredSize().height));
        add(players);
        add(Box.createVerticalStrut(10));

        hint.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(hint);
        add(Box.createVerticalStrut(10));

        // buttons
        Box buttons = new Box(BoxLayout.X_AXIS);
        JButton nextFirst = new JButton(start ? "Start" : "Next");
        nextFirst.addMouseListener(start ? new WelcomeScreenGraphic.StartGame(playerListModel) : new WelcomeScreenGraphic.NextPanel("p2", playerListModel));
        buttons.add(nextFirst);

        JButton cancel = new JButton("Cancel");
        cancel.addMouseListener(new WelcomeScreenGraphic.NextPanel("lb", playerListModel));
        buttons.add(Box.createHorizontalStrut(10));
        buttons.add(cancel);

        add(Box.createVerticalGlue());
        add(buttons);
    }
}
