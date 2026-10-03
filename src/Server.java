import java.net.ServerSocket;
import java.net.Socket;

class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(Config.SERVER_PORT);

        while (true) {
            Socket socket = server.accept(); // Waits for a client request
            new Thread(new ClientManager(socket)).start();
        }
    }
}