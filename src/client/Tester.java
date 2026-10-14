package client;

import server.Config;
import java.util.Scanner;

public class Tester {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("================================");
        System.out.println("       MATCHMAKING TESTER");
        System.out.println("================================");
        System.out.print(" Players to simulate: ");
        int nPlayer = scanner.nextInt();

        if (nPlayer <= 0) {
            System.out.println("[!] Enter a number greater than 0");
            scanner.close();
            return;
        }

        System.out.println("--------------------------------");
        System.out.println("[+] Starting " + nPlayer + " clients...");
        System.out.println("--------------------------------");

        for (int i = 0; i < nPlayer; i++) {
            new Thread(new Client(true)).start();
        }

        scanner.close();
    }
}
