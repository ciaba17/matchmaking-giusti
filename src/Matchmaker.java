import java.util.ArrayList;

public class Matchmaker implements Runnable {
    ArrayList<Match> currentMatches;
    ArrayList<PlayerData> waitingPlayers;

    public Matchmaker(ArrayList<Match> currentMatches, ArrayList<PlayerData> waitingPlayers) {
        this.currentMatches = currentMatches;
        this.waitingPlayers = waitingPlayers;
    }

    @Override
    public void run() {
        while (matchmaking() != 0) {};


    }



    private int matchmaking() {
        if (waitingPlayers.size() < Config.CANDIDATE_POOL_SIZE) return 1;

        ArrayList<PlayerData> candidatePool = new ArrayList<>();

        for (int i = 0; i < Config.CANDIDATE_POOL_SIZE; i++) {
            candidatePool.add(waitingPlayers.get(i));
        }

        ArrayList<Squad> squads = new ArrayList<>();

        for (int i = 0; i < Config.SQUADS_PER_MATCH; i++) {
            squads.add(new Squad());
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

        for (int i = 0; i < candidatePool.size(); i++) {
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
                    break;

                if (weakestSquad == null ||
                    squad.getSquadScore() < weakestSquad.getSquadScore() ||
                    (squad.getSquadScore() == weakestSquad.getSquadScore() &&
                    squad.getPlayerCount() < weakestSquad.getPlayerCount())) {
                        weakestSquad = squad;
                }
            }
            weakestSquad.addPlayer(player);
        }

        Match match = new Match(squads);

        new Thread(match).start();
        return 0;
    }
}
