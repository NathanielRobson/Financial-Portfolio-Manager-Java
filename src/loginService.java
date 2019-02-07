import java.io.*;
import java.util.LinkedList;

public class loginService {
    public static void main(String[] args) {
        new loginService();
    }

    public LinkedList<String> userNames = new LinkedList<>();
    public LinkedList<String> passWords = new LinkedList<>();
    File file = new File(".//UserAccounts/members.csv");

    loginService() { //Reads the members.csv file to provide array list and splits the data into new arrays
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {

            if (file.exists()) {

                reader = new BufferedReader(new FileReader(".//UserAccounts/members.csv"));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    String[] users = line.split(",");
                    userNames.add(users[0]);
                    passWords.add(users[1]);
                }
                System.out.println("User File Read Successfully");
            } else {
                String users = "NR,teamNR,\nED,teamED,\nDC,teamDC,\nKM,teamKM,\nCK,teamCK,\nKL,teamKL";

                writer = new BufferedWriter(new FileWriter(file));

                writer.write(users);
                writer.close();
                System.out.println("No File Existed, File Has Been Created in //UserAccounts/members.csv");
            }
        } catch (Exception el) {
            System.out.println("Error While Trying to Parse Login Details");
        }
    }
}