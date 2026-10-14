public class Player {
    private String name = "player";
    private int level = 0;
    private int rating = 0;
    private double matchmakingScore = 0;


    public Player(String name, int level, int rating) {
        this.name = name;
        this.level = level;
        this.rating = rating;

        double normalizedLevel = level / 100.0;
        double normalizedRating = rating / 3000.0;

        matchmakingScore = Config.LEVEL_WEIGHT * normalizedLevel + Config.RATING_WEIGHT * normalizedRating;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public double getMatchmakingScore() {
        return matchmakingScore;
    }

    public void setMatchmakingScore(float matchmakingScore) {
        this.matchmakingScore = matchmakingScore;
    }
}
