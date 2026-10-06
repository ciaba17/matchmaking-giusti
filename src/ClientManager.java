import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientManager implements Runnable {
    Socket socket;
    ArrayList<PlayerData> waitingPlayers;

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

            PlayerData p = new PlayerData(name, level, socket);
            waitingPlayers.add(p);

            System.out.println(name);
            System.out.println(level);

            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
