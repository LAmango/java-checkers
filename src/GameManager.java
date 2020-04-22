import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class GameManager {
    protected ArrayList<Game> games = new ArrayList<>();

    protected Game selectedGame = null;

    public GameManager() throws FileNotFoundException {
        FileInputStream f = new FileInputStream(new File("games.txt"));
        games.clear();
        try (ObjectInputStream input = new ObjectInputStream(f)) {
            ArrayList<Object> obj = (ArrayList<Object>) input.readObject();
            if (obj != null) {
                for (Object g: obj)
                games.add((Game) g);
            }
            f.close();
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    public void selectGame(String gameName) {
        for (Game g: games) {
            if (g.name.equals(gameName)) {
                selectedGame = g;
            }
        }
    }

    public void removeGame() throws IOException {
        if (selectedGame != null) {
            games.remove(selectedGame);
        }

        saveGames();
    }

    public void addGame(BoardPoint[] pts, Player p1, Player p2, int t, String n) throws IOException {
        Game g = new Game(pts, p1, p2, t, n);
        games.add(g);

        saveGames();
    }

    public void saveGames() throws IOException {
        FileOutputStream f = new FileOutputStream(new File("games.txt"));
        ObjectOutputStream oos = new ObjectOutputStream(f);

        oos.writeObject(games);

        f.close();
        oos.close();
    }

}

class Game implements Serializable {
    public BoardPoint[] points;
    public String name;
    public Player player1, player2;
    public int turn;
    public LocalDateTime time;

    public Game(BoardPoint[] pts, Player p1, Player p2, int t, String n) {
        points = pts;
        player1 = p1;
        player2 = p2;
        turn = t;
        name = n;
        time = LocalDateTime.now();
    }

    public String toString() {
        return String.format("%s", name);
    }
}
