import javax.swing.*;

public class Game extends JFrame {

    public void setNewPanel(JPanel panel) {
        getContentPane().removeAll();
        getContentPane().invalidate();
        getContentPane().add(panel);
        getContentPane().validate();
    }

    public static void main(String[] args) {
        Game gameFrame = new Game();
        WelcomeScreen welcome = new WelcomeScreen(gameFrame);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setSize(500,500);
        gameFrame.add(welcome);
        gameFrame.setVisible(true);
    }
}
