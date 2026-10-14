package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(Config.SERVER_PORT);
        ArrayList<PlayerData> waitingPlayers = new ArrayList<>();
        Matchmaker matchmaker = new Matchmaker(waitingPlayers);
        new Thread(matchmaker).start();

        System.out.println("====================================");
        System.out.println("         MATCHMAKING SERVER");
        System.out.println("====================================");
        System.out.println(" Address : " + Config.SERVER_HOST + ":" + Config.SERVER_PORT);
        System.out.println(" Match   : " + Config.PLAYERS_PER_MATCH + " players");
        System.out.println(" Squad   : " + Config.PLAYERS_PER_SQUAD + " players");
        System.out.println("------------------------------------");
        System.out.println("[+] Server ready. Waiting for players...");
        System.out.println();

        while (true) {
            Socket socket = server.accept();
            new Thread(new ClientManager(socket, waitingPlayers)).start();
        }
    }
}
