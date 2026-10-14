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

            synchronized (waitingPlayers) {waitingPlayers.add(p);}

            p.waitForMatch();

            for (PlayerData squadPlayer : p.getSquadToNotify().getPlayers()) {
                output.writeUTF(squadPlayer.getName());
                output.flush(); // Sends the data immediately
            }


            socket.close();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public void sendDataToSquad() {

    }
}
