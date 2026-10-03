import java.io.*;
import java.net.Socket;

public class Client implements Runnable {
    private Player player = new Player("player", 1);

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket(Config.SERVER_HOST, Config.SERVER_PORT);

        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true); // AutoFlush: sends the data immediately

        output.println("hi, i'm " +  player.getName());

        socket.close();
    }

    @Override
    public void run() {
        int level = (int) (Math.random() * 99) + 1;
        String name = generateName();
        player = new Player(name, level);
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
