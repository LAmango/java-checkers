import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class LoadGameBoard extends JPanel implements Style {

    public DefaultListModel gameListModel;
    public JLabel title;
    public JList gameList;
    public JButton load, cancel;
    public CardLayout card;
    public JPanel gameBoardPanel;
    public JPanel selectedGamePanel;

    public LoadGameBoard(ArrayList<Game> games) {
        gameListModel = new DefaultListModel();

        for (Game p: games) {
            gameListModel.addElement(p);
        }

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(BACKGROUND);
        setBorder(new EmptyBorder(10,10,10,10));
        setOpaque(true);

        // title
        title = new JLabel("Load Game");
        title.setFont(new Font("Time New Roman", Font.BOLD, 20));
        add(title);
        title.setForeground(TITLE_COLOR);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalStrut(10));

        // save game panel
        gameBoardPanel = new JPanel();
        gameBoardPanel.setLayout(new GridLayout(1,2));

        // game list
        gameList = new JList(gameListModel);
        gameList.setBorder(BorderFactory.createTitledBorder("Saved Games"));
        gameList.setBackground(BACKGROUND);
        gameList.setMaximumSize(new Dimension(Integer.MAX_VALUE, gameList.getPreferredSize().height));
        gameList.setFont(new Font("Monospaced", Font.BOLD, 12));
        gameList.getSelectionModel().addListSelectionListener(new WelcomeScreenGraphic.GameListListener(this));
        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(gameList);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        gameBoardPanel.add(scroll);

        //selected game panel
        selectedGamePanel = new JPanel();
        card = new CardLayout();
        selectedGamePanel.setLayout(card);

        selectedGamePanel.add(new SelectedGamePanel());

        for(Game g: games) {
            selectedGamePanel.add(new SelectedGamePanel(g), g.name);
        }

        gameBoardPanel.add(selectedGamePanel);

        add(gameBoardPanel);
        //add(Box.createVerticalGlue());

        Box buttons = new Box(BoxLayout.X_AXIS);
        buttons.setAlignmentX(Component.CENTER_ALIGNMENT);

        load = new JButton("Load");
        load.setEnabled(false);
        load.addActionListener(new WelcomeScreenGraphic.StartGame());
        buttons.add(load);

        cancel = new JButton("Cancel");
        cancel.addActionListener(new WelcomeScreenGraphic.LBPanel());
        buttons.add(Box.createHorizontalStrut(10));
        buttons.add(cancel);

        add(buttons);
    }

    public void updateLoadGame(ArrayList<Game> games) {
        gameListModel.removeAllElements();
        selectedGamePanel.removeAll();
        selectedGamePanel.add(new SelectedGamePanel());

        for (Game g : games) {
            gameListModel.addElement(g);
            selectedGamePanel.add(new SelectedGamePanel(g), g.name);
        }
    }

}
