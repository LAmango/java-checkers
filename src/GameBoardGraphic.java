import javax.swing.*;
import java.awt.*;

public class GameBoardGraphic extends JPanel {

    private BoardGraphic game;

    public GameBoardGraphic(GameGraphic frame) {
        BottomBoardGraphic bottomGame = new BottomBoardGraphic(frame);

        BorderLayout bl = new BorderLayout();
        setLayout(bl);
        game = new BoardGraphic();
        add(game, BorderLayout.CENTER);

        add(bottomGame, BorderLayout.SOUTH);
    }

    public BoardGraphic getBoardGraphic() {
        return game;
    }
}
