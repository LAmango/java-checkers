import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BottomBoardGraphic extends JPanel {
    public GameGraphic frame;
    public BottomBoardGraphic(GameGraphic f) {
        frame = f;

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ButtonListener());
        JButton saveButton = new JButton("Save");
        add(backButton);
        add(saveButton);
    }

    public class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if("Back".equals(command)){
                frame.card.previous(frame.RootPanel);
            }
        }
    }
}
