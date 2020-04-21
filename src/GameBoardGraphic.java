import javax.swing.*;
import java.awt.*;

public class GameBoardGraphic extends JPanel {

    private BoardGraphic game;

    public GameBoardGraphic(){};

    public GameBoardGraphic(GameGraphic frame) {
        BottomBoardGraphic bottomGame = new BottomBoardGraphic(frame);
        TopBoardGraphic topGame = new TopBoardGraphic(frame.pm.p1, frame.pm.p2);

        BorderLayout bl = new BorderLayout();
        setLayout(bl);
        game = new BoardGraphic(frame);
        add(game, BorderLayout.CENTER);

        add(bottomGame, BorderLayout.SOUTH);

        add(topGame, BorderLayout.NORTH);
    }

    public BoardGraphic getBoardGraphic() {
        return game;
    }
}
