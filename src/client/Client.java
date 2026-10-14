package client;

import server.Config;

import java.io.*;
import java.net.Socket;

public class Client implements Runnable {
    private Player player = new Player("player", 1, 100);

    public static void main(String[] args) throws Exception { // For base client running
        Client client = new Client();
        client.start();
    }

    private void start() throws Exception {
        Socket socket = new Socket(Config.SERVER_HOST, Config.SERVER_PORT);

        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        DataInputStream input = new DataInputStream(socket.getInputStream());

        output.writeUTF(player.getName());
        output.writeInt(player.getLevel());
        output.writeInt(player.getRating());
        output.flush(); // Sends the data immediately
        System.out.println("Player data sent");

        System.out.println("matchmaking...");

        System.out.println(input.readUTF());

        socket.close();
    }

    @Override
    public void run() { // For running multiple clients during the testing
        String name = generateName();
        int level = (int) (Math.random() * Config.MAX_LEVEL) + 1;
        int rating = (int) (Math.random() * Config.MAX_RATING) + 1;

        player = new Player(name, level, rating);

        try {
            start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String generateName() {
        String[] names = {
                "Shadow", "Ghost", "Viper", "Ninja", "Dragon",
                "Sniper", "Reaper", "Wolf", "Titan", "Raven"
        };

        int random = (int) (Math.random() * names.length);

        String name = names[random];

        return name + random * 67;
    }
}