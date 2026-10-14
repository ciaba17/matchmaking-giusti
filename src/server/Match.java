package server;

import java.util.ArrayList;

public class Match implements Runnable{
    ArrayList<Squad> squads;

    public Match(ArrayList<Squad> squads) {
        this.squads = squads;
    }

    @Override
    public void run() {
        for (Squad squad : squads) {
            for (PlayerData player : squad.getPlayers()) {
                player.matchFound(squad);
            }
        }
    }
}
