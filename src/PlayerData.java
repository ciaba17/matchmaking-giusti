import java.net.Socket;

public class PlayerData {
    Socket socket;
    String name;
    int level;

    PlayerData(String name, int level, Socket socket) {
        this.name = name;
        this.level = level;
        this.socket = socket;
    }
}