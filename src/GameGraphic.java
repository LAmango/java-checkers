import javax.swing.*;

public class GameGraphic extends JFrame {

    private GameBoardGraphic gb;
    public GameBoard gameBoard;

    public GameGraphic() {
        WelcomeScreenGraphic welcome = new WelcomeScreenGraphic(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,500);
        add(welcome);
        setVisible(true);
    }

    public void setNewPanel(JPanel panel) {
        getContentPane().removeAll();
        getContentPane().invalidate();
        getContentPane().add(panel);
        getContentPane().validate();
    }

    public void startGame() {
        gb = new GameBoardGraphic(this);
        setNewPanel(gb);
        gameBoard = new GameBoard(gb.getBoardGraphic());
    }
}
