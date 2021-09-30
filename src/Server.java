import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

abstract public class Server {
    private static final HashMap<String, Video> videos = new HashMap<>();
    private static final HashMap<String, Rating> ratings = new HashMap<>();
    private static final ArrayList<User> users = new ArrayList<>();
    private static final HashSet<String> usernames = new HashSet<>();

    // load files into data structures
    static {
        try {
            readAkas();
            readRatings();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // reads akas file
    private static void readAkas() throws IOException {

        for (String[] line : File.readData("title.akas.txt")) {

            String titleId = line[0];
            int ordering = Integer.parseInt(line[1]);
            String title = line[2];
            String region = line[3];
            String language = line[4];
            String[] types = line[5].split(" ");
            String[] attributes = line[6].split(" ");
            boolean isOriginalTitle = Boolean.parseBoolean(line[7]);

            videos.put(titleId, new Video(titleId, ordering, title, region,
                    language, types, attributes, isOriginalTitle));
        }
    }

    // reads ratings file
    private static void readRatings() throws IOException {

        for (String[] line : File.readData("title.ratings.txt")) {

            String tconst = line[0];
            double averageRating = Double.parseDouble(line[1]);
            int numVotes = Integer.parseInt(line[2]);

            ratings.put(tconst, new Rating(tconst, averageRating, numVotes));
        }
    }

    public static void showVideos() {

        for (Video video : videos.values()) {
            Rating rating = ratings.get(video.getTitleId());
            System.out.println(video);
            System.out.println(rating);
            System.out.println("-----------------------------------");
        }
    }

    // writes new ratings into file
    public static void updateRatings() throws IOException {
        ArrayList<String[]> data = new ArrayList<>();
        for (Rating rating : ratings.values()) {
            data.add(new String[]{rating.getTconst(),
                    String.format("%.1f", rating.getAverageRating()), String.valueOf(rating.getNumVotes())});
        }

        String header = "tconst\taverageRating\tnumVotes";

        File.writData("title.ratings.txt", header, data);
    }

    // rate a video if already not rated
    public static void addRating(String tconst, int score) {
        if (score < 1 || score > 10)
            throw new ArithmeticException("Score must be in range 1-10");

        Rating rating = ratings.get(tconst);
        int votes = rating.getNumVotes();
        double newAverage = (score + votes * rating.getAverageRating()) / (votes + 1);
        rating.setAverageRating(newAverage);
        rating.setNumVotes(votes + 1);
    }

    public static boolean uniqueUsername(String username) {
        return usernames.add(username);
    }

    // creates new user & returns true is username doesn't already exist
    public static void addUser(String username, String password) {
        users.add(new User(username, password));
    }

    // returns user if correct username & password
    public static User getUser(String username, String password) {
        for (User user : users)
            if (username.equals(user.getUsername()) &&
                    password.equals(user.getPassword()))
                return user;

        return null;
    }
}