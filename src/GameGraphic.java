import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

/**************************************************
 * GameGraphic                                    *
 * ************************************************
 * * GameBoardGraphic                             *
 * * **********************************************
 * * * BoardGraphic                               *
 * * * ********************************************
 * * * * TileGraphic                              *
 * * * *                                          *
 * * * *                                          *
 * * * ********************************************
 * * * BottomBoarGraphic                          *
 * * * ********************************************
 * * * *                                          *
 * * * ********************************************
 * * **********************************************
 * ************************************************
 * ************************************************
 */

public class GameGraphic extends JFrame {

    private GameBoardGraphic gb = new GameBoardGraphic();
    public GameBoard gameBoard;
    public PlayerManager pm;
    public WelcomeScreenGraphic welcome;
    public CardLayout card = new CardLayout();
    public JPanel RootPanel;

    public GameGraphic() {
        try {
            pm = new PlayerManager();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        RootPanel = new JPanel();
        RootPanel.setLayout(card);
        welcome = new WelcomeScreenGraphic(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,500);
        RootPanel.add(welcome);
        add(RootPanel);
        //add(welcome);
        setVisible(true);
    }

    public void setNewPanel(JPanel panel) {
        getContentPane().removeAll();
        getContentPane().invalidate();
        getContentPane().add(panel);
        getContentPane().validate();
    }

    public void backToWelcome() {
        getContentPane().removeAll();
        getContentPane().invalidate();
        getContentPane().add(welcome);
        getContentPane().validate();
    }

    public void startGame() {
        RootPanel.remove(gb);
        gb = new GameBoardGraphic(this);
        RootPanel.add(gb);
        card.next(RootPanel);
        //setNewPanel(gb);
        gameBoard = new GameBoard(gb.getBoardGraphic());
    }
}
