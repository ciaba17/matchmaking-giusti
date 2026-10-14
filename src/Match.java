import java.net.Socket;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Match implements Runnable{
    ArrayList<Squad> squads;

    public Match(ArrayList<Squad> squads) {
        this.squads = squads;
    }

    @Override
    public void run() {
        for (Squad squad : squads) {
            for (PlayerData player : squad.getPlayers()) {

            }
        }
    }
}
