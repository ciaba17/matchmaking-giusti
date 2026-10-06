public class Config {
    // Server socket
    public static final String SERVER_HOST = "127.0.0.1";
    public static final int SERVER_PORT = 5000;
    // Matchmaking
    public static final int PRE_MATCHMAKING_TIME = 20_000; // Milliseconds
    public static final int MAX_NUMBER_OF_MATCHES = 3;

    // Match
    public static final int PLAYERS_PER_SQUAD = 3;
    public static final int MAX_PLAYERS_PER_MATCH = PLAYERS_PER_SQUAD * 2;

}