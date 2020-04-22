import javax.swing.*;
//import jdk.nashorn.internal.scripts.JO;

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
                if (n == 0) {
                    //frame.declareWinner(frame.pm.p1);
                    frame.gm.selectedGame = null;
                    frame.card.previous(frame.RootPanel);
                }
            } else if ("Save".equals(command)) {
                String def = null;
                String n = null;
                if (frame.gm.selectedGame != null) {
                    def = frame.gm.selectedGame.name;
                }
                try {
                    n = JOptionPane.showInputDialog(frame, "Name your saved game!", "Save Game", JOptionPane.PLAIN_MESSAGE, null, null, def).toString();
                } catch (NullPointerException npe) {
                    //npe.printStacktrace();
                }
                if (n != null) {
                    try {
                        if(frame.gm.selectedGame != null) {
                            frame.gm.games.remove(frame.gm.selectedGame);
                        }
                        frame.gm.addGame(GameBoard.points, frame.pm.p1, frame.pm.p2, BoardGraphic.turn, n);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    WelcomeScreenGraphic.updateLoadGame();
                    frame.card.previous(frame.RootPanel);
                }
            }
        }
    }
}
