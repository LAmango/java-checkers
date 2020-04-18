import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PlayerSelectionPanel extends JPanel {

    DefaultListModel playerListModel = new DefaultListModel();

    PlayerSelectionPanel(String fakePlayers[], int playerNum, boolean start) {

        // listModel setup
        for (String player: fakePlayers) {
            playerListModel.addElement(player);
        }

        setBorder(new EmptyBorder(10, 10, 10, 10));
        setBackground(new Color(95, 95, 95));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JTextField textField = new JTextField(20);
        textField.setBackground(new Color(95,95,95));
        textField.setMaximumSize(new Dimension(Integer.MAX_VALUE, textField.getPreferredSize().height));
        textField.setBorder(BorderFactory.createTitledBorder("Player" + playerNum));
        add(textField);
        add(Box.createVerticalStrut(10));

        JList players = new JList(playerListModel);
        players.setBackground(new Color(95, 95, 95));
        players.getSelectionModel().addListSelectionListener(new WelcomeScreenGraphic.PlayerListListener(textField, players, playerNum));
        players.setBorder(BorderFactory.createTitledBorder("Player Selection"));
        players.setMaximumSize(new Dimension(Integer.MAX_VALUE, players.getPreferredSize().height));
        add(players);
        add(Box.createVerticalStrut(10));

        Box buttons = new Box(BoxLayout.X_AXIS);
        JButton nextFirst = new JButton(start ? "Start" : "Next");
        nextFirst.addMouseListener(start ? new WelcomeScreenGraphic.StartGame() : new WelcomeScreenGraphic.NextPanel("p2"));
        buttons.add(nextFirst);

        JButton cancel = new JButton("Cancel");
        cancel.addMouseListener(new WelcomeScreenGraphic.NextPanel("lb"));
        buttons.add(Box.createHorizontalStrut(10));
        buttons.add(cancel);

        add(Box.createVerticalGlue());
        add(buttons);
    }
}
