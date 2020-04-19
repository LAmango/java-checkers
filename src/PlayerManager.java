import java.io.*;
import java.util.ArrayList;

public class PlayerManager {
    protected ArrayList<Player> players = new ArrayList<>();
    public String firstPlayer = null;
    public String secondPlayer = null;
    public Player p1, p2;

    public PlayerManager() throws FileNotFoundException {
        FileInputStream f = new FileInputStream(new File("players.txt"));
        players.clear();
        boolean cont = true;
            try (ObjectInputStream input = new ObjectInputStream(f)) {
                ArrayList<Object> obj = (ArrayList<Object>) input.readObject();
                if (obj != null) {
                    for (Object p : obj)
                    players.add((Player) p);
                }
            } catch (Exception e) {
                //System.out.println(e);
            }
    }

    public void resetPlayers() {
        firstPlayer = null;
        secondPlayer = null;
    }

    public void removePlayer(Player p) throws IOException {
        players.remove(p);
        savePlayers();
    }

    public void addPlayer(String name) throws IOException {
        Player p = new Player(name);
        players.add(p);
        savePlayers();
    }

    public void updatePlayerWins(String name) throws IOException {
        for (Player p: players) {
            if (p.name.equals(name)) {
                p.updateWins();
            }
        }

        savePlayers();

    }

    public void createPlayersForGame() {
        p1 = new Player(firstPlayer);
        p2 = new Player(secondPlayer);
    }

    public void savePlayers() throws IOException {
        FileOutputStream f = new FileOutputStream(new File("players.txt"));
        ObjectOutputStream oos = new ObjectOutputStream(f);

        oos.writeObject(players);

        f.close();
        oos.close();
    }
}
