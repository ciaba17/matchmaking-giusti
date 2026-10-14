package server;

import java.net.Socket;

public class PlayerData {
    private Socket socket;
    private String name;
    private int level;
    private double matchmakingScore;

    private boolean matched = false;
    private Squad squadToNotify;



    PlayerData(Socket socket, String name, int level, double matchmakingScore) {
        this.socket = socket;
        this.name = name;
        this.level = level;
        this.matchmakingScore = matchmakingScore;
    }

    public synchronized void waitForMatch() throws InterruptedException {
        while (!matched) wait();
    }
    public synchronized void matchFound(Squad squad) {
        this.matched = true;
        this.squadToNotify = squad;
        notify();
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public double getMatchmakingScore() { return matchmakingScore; }

    public void setMatchmakingScore(double matchmakingScore) { this.matchmakingScore = matchmakingScore; }

    public Squad getSquadToNotify() {
        return squadToNotify;
    }

    public void setSquadToNotify(Squad squadToNotify) {
        this.squadToNotify = squadToNotify;

    }
}