package server;

import java.util.ArrayList;

public class Matchmaker implements Runnable {
    ArrayList<PlayerData> waitingPlayers;
    int matchCount = 0;

    public Matchmaker(ArrayList<PlayerData> waitingPlayers) {
        this.waitingPlayers = waitingPlayers;
    }

    @Override
    public void run() {
        while (true) {
            matchmaking();
            try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
        }
    }

    private int matchmaking() {
        ArrayList<PlayerData> candidatePool = new ArrayList<>();
        synchronized (waitingPlayers) {
            if (waitingPlayers.size() < Config.PLAYERS_PER_MATCH) return 1;

            // Use a smaller pool when there are not enough extra players
            int poolSize = Math.min(waitingPlayers.size(), Config.CANDIDATE_POOL_SIZE);
            for (int i = 0; i < poolSize; i++) {
                candidatePool.add(waitingPlayers.remove(0));
            }
        }

        ArrayList<Squad> squads = new ArrayList<>();

        for (int i = 0; i < Config.SQUADS_PER_MATCH; i++) {
            squads.add(new Squad("Squad " + (char) ('A' + i)));
        }

        // Sort the pool from low to high
        candidatePool.sort(
            (p1, p2) -> Double.compare(
            p1.getMatchmakingScore(),
            p2.getMatchmakingScore()
            )
        );

        int low = 0;
        int high = candidatePool.size() - 1;

        for (int i = 0; i < Config.PLAYERS_PER_MATCH; i++) {
            // Alternate low and high score player
            PlayerData player;

            if (i % 2 == 0) {
                player = candidatePool.get(high--);
            } else {
                player = candidatePool.get(low++);
            }

            Squad weakestSquad = null;

            for (Squad squad : squads) {
                if (squad.getPlayerCount() >= Config.PLAYERS_PER_SQUAD)
                    continue;

                if (weakestSquad == null ||
                    squad.getSquadScore() < weakestSquad.getSquadScore() ||
                    (squad.getSquadScore() == weakestSquad.getSquadScore() &&
                    squad.getPlayerCount() < weakestSquad.getPlayerCount())) {

                        weakestSquad = squad;
                }
            }
            weakestSquad.addPlayer(player);
        }

        synchronized (waitingPlayers) {
            waitingPlayers.addAll(candidatePool.subList(low, high + 1));
        }

        // Print each match with its squads
        matchCount++;
        synchronized (System.out) {
            System.out.println();
            System.out.println("========== MATCH #" + matchCount + " ==========");
            for (Squad squad : squads) {
                StringBuilder names = new StringBuilder();
                for (PlayerData player : squad.getPlayers()) {
                    if (names.length() > 0) names.append(", ");
                    names.append(player.getName());
                }
                System.out.println(" " + squad.getName() + " : " + names);
            }
            System.out.println("================================");
            System.out.println();
        }

        Match match = new Match(squads);
        new Thread(match).start();
        return 0;
    }
}
