import javax.swing.*;

public class GameGraphic extends JFrame {

    public void setNewPanel(JPanel panel) {
        getContentPane().removeAll();
        getContentPane().invalidate();
        getContentPane().add(panel);
        getContentPane().validate();
    }

    public static void main(String[] args) {
        GameGraphic gameGraphicFrame = new GameGraphic();
        WelcomeScreenGraphic welcome = new WelcomeScreenGraphic(gameGraphicFrame);
        gameGraphicFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameGraphicFrame.setSize(500,500);
        gameGraphicFrame.add(welcome);
        gameGraphicFrame.setVisible(true);
    }
}
