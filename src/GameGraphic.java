import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

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

    protected GameBoardGraphic gb;
    public GameBoard gameBoard;
    public PlayerManager pm;
    public GameManager gm;
    public WelcomeScreenGraphic welcome;
    public CardLayout card = new CardLayout();
    public JPanel RootPanel;

    public GameGraphic() {
        gb = new GameBoardGraphic();
        try {
            pm = new PlayerManager();
            gm = new GameManager();
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
        if (gm.selectedGame != null) {
            gameBoard = new GameBoard(gb.getBoardGraphic(), gm.selectedGame.points);
        } else {
            gameBoard = new GameBoard(gb.getBoardGraphic());
        }
    }

    public void declareWinner(Player p) {
        String message = p.name + " is the Winner!";
        JOptionPane.showConfirmDialog(this, message, "Winner!!", JOptionPane.PLAIN_MESSAGE);

        try {
            pm.updatePlayerWins(p.name);
            gm.removeGame();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        WelcomeScreenGraphic.updateLoadGame();
        WelcomeScreenGraphic.updateLeaderBoard();
        card.previous(RootPanel);
    }
}
