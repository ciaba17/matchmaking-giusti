import java.io.*;
import java.net.Socket;

public class ClientManager implements Runnable {
    Socket socket;

    public ClientManager(Socket socket) {
        this.socket = socket;

    }

    @Override
    public void run() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter((new OutputStreamWriter(socket.getOutputStream())));

            String request = input.readLine();
            System.out.println(request);

            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
