package client;

import server.Config;

import java.io.*;
import java.net.Socket;

public class Client implements Runnable {
    private Player player = new Player("player", 1, 100);
    private static int nextId = 0;
    private boolean testing = false;

    public Client() {
    }

    public Client(boolean testing) {
        this.testing = testing;
    }

    private static synchronized int getNextId() {
        return ++nextId;
    }

    public static void main(String[] args) throws Exception { // For base client running
        Client client = new Client();
        client.start();
    }

    private void start() throws Exception {
        if (!testing) {
            System.out.println("================================");
            System.out.println("       MATCHMAKING CLIENT");
            System.out.println("================================");
            System.out.println(" Player : " + player.getName());
            System.out.println(" Level  : " + player.getLevel());
            System.out.println(" Rating : " + player.getRating());
            System.out.println("--------------------------------");
        }

        Socket socket = new Socket(Config.SERVER_HOST, Config.SERVER_PORT);

        try {
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            DataInputStream input = new DataInputStream(socket.getInputStream());

            output.writeUTF(player.getName());
            output.writeInt(player.getLevel());
            output.writeInt(player.getRating());
            output.flush(); // Sends the data immediately

            if (!testing) {
                System.out.println("[+] Connected to server");
                System.out.println("[..] Searching for a match...");
            }

            // Read the squad name and its players
            String squadName = input.readUTF();
            String squadPlayers = input.readUTF();

            if (testing) {
                synchronized (System.out) {
                    System.out.println("[OK] " + player.getName() + " -> " + squadName + " | " + squadPlayers);
                }
            } else {
                System.out.println();
                System.out.println("[+] MATCH FOUND");
                System.out.println("--------------------------------");
                System.out.println(" Squad: " + squadName);
                System.out.println(" Players:");
                for (String name : squadPlayers.split(", ")) {
                    System.out.println("   - " + name);
                }
                System.out.println("================================");
            }
        } finally {
            socket.close();
        }
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
            synchronized (System.out) {
                System.out.println("[ERR] " + player.getName() + ": " + e.getMessage());
            }
        }
    }

    private static String generateName() {
        String[] names = {
                "Shadow", "Ghost", "Viper", "Ninja", "Dragon",
                "Sniper", "Reaper", "Wolf", "Titan", "Raven"
        };

        int random = (int) (Math.random() * names.length);

        String name = names[random];

        return name + getNextId();
    }
}
