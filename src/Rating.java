public class Rating {
    private final String tconst;
    // alphanumeric unique identifier of the title
    private double averageRating;
    // weighted average of all the individual user ratings
    private int numVotes;
    // number of votes the title has received

    public Rating(String tconst, double averageRating, int numVotes) {
        this.tconst = tconst;
        this.averageRating = averageRating;
        this.numVotes = numVotes;
    }

    public String getTconst() {
        return tconst;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public int getNumVotes() {
        return numVotes;
    }

    public void setNumVotes(int numVotes) {
        this.numVotes = numVotes;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "\n\taverageRating= " + String.format("%.1f", averageRating) +
                "\n\tnumVotes= " + numVotes +
                "\n}";
    }
}
