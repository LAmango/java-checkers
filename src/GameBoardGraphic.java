import javax.swing.*;
import java.awt.*;

public class GameBoardGraphic extends JPanel {

    public GameBoardGraphic(GameGraphic frame) {
        BoardGraphic game = new BoardGraphic();
        BottomBoardGraphic bottomGame = new BottomBoardGraphic(frame);

        BorderLayout bl = new BorderLayout();
        setLayout(bl);
        add(game, BorderLayout.CENTER);

        add(bottomGame, BorderLayout.SOUTH);
    }

}
