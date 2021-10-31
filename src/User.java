import java.util.HashSet;

public class User {
    private final String username;
    private final String password;
    private HashSet<String> ratedVideos;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.ratedVideos = new HashSet<>();
    }

    public User(String username, String password, HashSet<String> ratedVideos) {
        this(username, password);
        this.ratedVideos = ratedVideos;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public HashSet<String> getRatedVideos() {
        return ratedVideos;
    }

    public boolean canRate(String tileID) {
        return ratedVideos.add(tileID);
    }

}
