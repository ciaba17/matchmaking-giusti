import java.net.ServerSocket;
import java.util.ArrayList;

class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(Config.SERVER_PORT);
        Matchmaker matchmaker = new Matchmaker(server);
        ArrayList<Match> currentMatches = new ArrayList<>();

        while (true) {
            if (currentMatches.isEmpty()) currentMatches.add(new Match());
        }
    }

}