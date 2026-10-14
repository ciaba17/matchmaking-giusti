package server;

import java.util.ArrayList;

public class Squad {
    private ArrayList<PlayerData> players = new ArrayList<PlayerData>();
    private String name;

    public Squad(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    public double getSquadScore() {
        // Sum all squad's players
        if (players.isEmpty())
            return 0.0;
        else
            return players.stream().mapToDouble(p -> p.getMatchmakingScore()).sum();
    }

    public void addPlayer(PlayerData p) {
        players.add(p);
    }

    public int getPlayerCount() { return players.size(); }

    public ArrayList<PlayerData> getPlayers() { return players; }
}
