//import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class BottomBoardGraphic extends JPanel {
    GameGraphic frame;

    public BottomBoardGraphic(GameGraphic f) {
        frame = f;

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ButtonListener());
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ButtonListener());
        add(backButton);
        add(saveButton);
    }

    public class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if("Back".equals(command)){
                int n = JOptionPane.showConfirmDialog(frame, "Are you sure you want to quit?\nThe current game will be lost forever!", "Quit Game", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                System.out.println(n);
                if (n == 0) {
                    try {
                        frame.pm.updatePlayerWins(frame.pm.p1.name);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    WelcomeScreenGraphic.updateLeaderBoard();
                    frame.card.previous(frame.RootPanel);
                }
            } else if ("Save".equals(command)) {
                //TopBoardGraphic.togglePlayerTurn();
            }
        }
    }
}
