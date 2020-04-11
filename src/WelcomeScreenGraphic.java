import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WelcomeScreenGraphic extends JPanel {
    public WelcomeScreenGraphic(GameGraphic frame) {
       setBackground(Color.GRAY);
       setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
       JButton startButton = new JButton("Start Game");
       startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
       startButton.addMouseListener(new MouseAdapter() {
           @Override
           public void mousePressed(MouseEvent e) {
               super.mouseClicked(e);
               GameBoardGraphic gb = new GameBoardGraphic(frame);
               frame.setNewPanel(gb);
           }
       });
       JLabel title  = new JLabel("Checkers");
       title.setFont(new Font("Times New Roman", Font.BOLD, 60));
       title.setAlignmentX(Component.CENTER_ALIGNMENT);
       add(title);
       add(startButton);
    }
}
