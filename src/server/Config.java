package server;

public class Config {
    // server.Server socket
    public static final String SERVER_HOST = "127.0.0.1";
    public static final int SERVER_PORT = 5000;
    // Matchmaking
    public static final int PRE_MATCHMAKING_TIME = 20_000; // Milliseconds
    public static final int MAX_NUMBER_OF_MATCHES = 3;

    // server.Match
    public static final int PLAYERS_PER_SQUAD = 3;
    public static final int SQUADS_PER_MATCH = 2;
    public static final int PLAYERS_PER_MATCH = PLAYERS_PER_SQUAD * SQUADS_PER_MATCH;

    // Matchmaking
    public static final double MAX_LEVEL = 100;
    public static final double MAX_RATING = 3000;
    public static final double LEVEL_WEIGHT = 0.4;
    public static final double RATING_WEIGHT = 0.6;
    public static final int CANDIDATE_POOL_SIZE = (int) Math.ceil(PLAYERS_PER_MATCH * 1.5); // Larger pool = better team balancing
}