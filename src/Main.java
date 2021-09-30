import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("~ Main Menu");
                System.out.println("1) SignUp");
                System.out.println("2) SignIn");
                System.out.println("3) Save & Exit");

                switch (sc.nextInt()) {
                    case 1 -> {
                        System.out.print("Enter a unique username: ");
                        String username = sc.next();

                        if (Server.uniqueUsername(username)) {
                            System.out.print("Enter a password: ");
                            String password = sc.next();
                            Server.addUser(username, password);
                            System.out.println("User registered.");
                        } else {
                            System.out.println("This username already exist, try another.");
                        }
                    }
                    case 2 -> {
                        System.out.print("Enter your username: ");
                        String username = sc.next();
                        System.out.print("Enter your password: ");
                        String password = sc.next();
                        User user = Server.getUser(username, password);
                        if (user == null) {
                            System.out.println("Incorrect username or password");
                        } else {

                            boolean login = true;
                            while (login) {

                                System.out.println("~ Hi " + username);
                                System.out.println("1) Show all videos");
                                System.out.println("2) Show top-10 movies");
                                System.out.println("3) Logout");

                                switch (sc.nextInt()) {
                                    case 1 -> {
                                        Server.showVideos(false, -1);

                                        System.out.println("Commands: ");
                                        System.out.println(" > rate <tileId> <yourScore>");
                                        System.out.println(" > done");

                                        System.out.println("*Notice: score must be in range 1-10");
                                        System.out.println("example: rate tt0000002 7");

                                        while (true) {
                                            System.out.print("Enter a command: ");
                                            if (sc.next().startsWith("d"))
                                                break;

                                            String tileId = sc.next();
                                            int score = sc.nextInt();

                                            if (!user.canRate(tileId)) {
                                                System.out.println("You've already rated to this video!");
                                            } else {
                                                try {
                                                    Server.addRating(tileId, score);

                                                    System.out.println("Rating added.");
                                                } catch (NullPointerException ex) {
                                                    System.out.println("Wrong tileId!");
                                                } catch (ArithmeticException ex) {
                                                    System.out.println(ex.getMessage());
                                                }
                                            }
                                        }
                                    }
                                    case 2 -> Server.showVideos(true, 10);
                                    case 3 -> login = false;
                                }
                            }
                        }
                    }
                    case 3 -> {
                        System.out.println("Updating data & exit");
                        try {
                            Server.updateRatings();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.exit(0);
                    }
                }

            } catch (InputMismatchException ex) {
                System.out.println("Sth went wrong, check inputs");
            }
        }

    }
}
