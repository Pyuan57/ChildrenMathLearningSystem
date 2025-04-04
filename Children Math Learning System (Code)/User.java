import java.io.Serializable;

class User implements Serializable {
    protected String name;
    protected int score;

    public User(String name) {
        this.name = name;
        this.score = 0; // Initial score set to 0
    }

    public String getName() {
        return name; // Get user's name
    }

    public int getScore() {
        return score; // Get user's score
    }

    public void incrementScore() {
        score++; // Increment score by 1
    }
}
