import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Matchmaker implements Runnable{
    ServerSocket server;

    public Matchmaker(ServerSocket server) {
        this.server = server;
    }

    @Override
    public void run() {
        int nPlayer = 0;
        ArrayList<PlayerData> waitingPlayers = new ArrayList<>();

        // Waits for x seconds before starting the matchmaking
        long endTime = System.currentTimeMillis() + Config.PRE_MATCHMAKING_TIME;
        while (System.currentTimeMillis() < endTime || nPlayer > Config.MAX_PLAYERS_PER_MATCH) {
            //Socket socket = server.accept();
            nPlayer++;

            //new Thread(new ClientManager(socket, waitingPlayers)).start();
        }
    }

    private void matchmaking(ArrayList<PlayerData> waitingPlayers) {
    }
}
