import java.util.Scanner;

public class Tester {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Players to simulate: ");
        int nPlayer = scanner.nextInt();

        for (int i = 0; i < nPlayer; i++) {
            new Thread(new Client()).start();
        }

        scanner.close();
    }
}