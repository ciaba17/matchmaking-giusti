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

        while (true) {
            Socket socket = server.accept();
            new Thread(new ClientManager(socket, waitingPlayers)).start();
        }
    }

}