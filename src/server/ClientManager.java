package server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientManager implements Runnable {
    Socket socket;
    ArrayList<PlayerData> waitingPlayers;
    PlayerData p;

    public ClientManager(Socket socket, ArrayList<PlayerData> waitingPlayers) {
        this.socket = socket;
        this.waitingPlayers = waitingPlayers;
    }

    @Override
    public void run() {
        try {
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());

            String name = input.readUTF();
            int level = input.readInt();
            int rating = input.readInt();

            double normalizedLevel = level / Config.MAX_LEVEL;
            double normalizedRating = rating / Config.MAX_RATING;
            double score = Config.LEVEL_WEIGHT * normalizedLevel + Config.RATING_WEIGHT * normalizedRating;

            p = new PlayerData(socket, name, level, score);

            synchronized (waitingPlayers) {
                waitingPlayers.add(p);
                synchronized (System.out) {
                    System.out.println("[JOIN] " + name + " (lvl " + level + ", rating " + rating + ") | Queue: " + waitingPlayers.size());
                }
            }

            p.waitForMatch();

            StringBuilder names = new StringBuilder();
            for (PlayerData squadPlayer : p.getSquadToNotify().getPlayers()) {
                if (names.length() > 0) names.append(", ");
                names.append(squadPlayer.getName());
            }

            // Send the squad name and the player list
            output.writeUTF(p.getSquadToNotify().getName());
            output.writeUTF(names.toString());
            output.flush();

            socket.close();
        } catch (IOException | InterruptedException e) {
            System.out.println("[ERR] Client disconnected: " + e.getMessage());
        }
    }
}
