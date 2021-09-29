import java.util.HashSet;

public class User {
    private final String username;
    private final String password;
    private final HashSet<String> ratedVideos;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.ratedVideos = new HashSet<>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean canRate(String tileID) {
        return ratedVideos.add(tileID);
    }

}
