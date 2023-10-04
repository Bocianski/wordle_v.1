public class Players {
    private final String name;
    private int score;

    public Players(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public Players(String name) {
        this.name = name;
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
