public class Player {
    private String name = "player";
    private float level = 0;
    private boolean searchingForGame = false;

    public Player(String name, float level) {
        this.name = name;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getLevel() {
        return level;
    }

    public void setLevel(float level) {
        this.level = level;
    }

    public boolean isSearchingForGame() {
        return searchingForGame;
    }

    public void setSearchingForGame(boolean searchingForGame) {
        this.searchingForGame = searchingForGame;
    }
}
