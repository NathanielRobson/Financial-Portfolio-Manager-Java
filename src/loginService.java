import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class loginService {

    List<String> userID = new ArrayList<>();
    List<String> userIDs = new ArrayList<>();
    List<String> usernames = new ArrayList<>();
    List<String> userNames = new ArrayList<>();
    List<String> passwords = new ArrayList<>();
    List<String> passWords = new ArrayList<>();

    loginService() {

        BufferedReader reader = null;

        try {

            reader = new BufferedReader(new FileReader("members.csv"));
            String line = null;

            while ((line = reader.readLine()) != null) {

                String[] users = line.split(",");
                userID.add(users[0]);
                usernames.add(users[1]);
                passwords.add(users[2]);

            }

            String userIDJoin = String.join(",", userID);
            String usernameJoin = String.join(",", usernames);
            String passwordJoin = String.join(",", passwords);

            userIDs = Arrays.asList(userIDJoin.split("\\s*,\\s*"));
            userNames = Arrays.asList(usernameJoin.split("\\s*,\\s*"));
            passWords = Arrays.asList(passwordJoin.split("\\s*,\\s*"));

            new LinkedList<String>(userIDs);
            new LinkedList<String>(userNames);
            new LinkedList<String>(passWords);

        } catch (Exception el) {
            System.out.println("Error, no members.csv file exists");
        }
    }
}