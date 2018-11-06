import javax.swing.*;
import java.io.File;
import java.util.Scanner;

public class SearchandView {
    private static Scanner a;
    public static void main(String[] args) {
        Scanner b = new Scanner(System.in);

        System.out.println("Please enter the filename of your csv file: ");
        String filepath = b.nextLine();
        System.out.println("Please enter the date you wish to display: ");
        String Search =  b.nextLine();

        readFile(Search, filepath);
    }

    public static void readFile(String Search, String filepath) {
        boolean found = false;
        String Date = ""; String Open = ""; String High = ""; String Low = "";
        String Close = ""; String Volume = ""; String Change = "";
        String ChangePercent = ""; String vwap = ""; String changeOverTime = "";
        try {
            a = new Scanner(new File(filepath));
            a.useDelimiter("[,\n]");

            while(a.hasNext() && !found){
                Date = a.next(); Open = a.next(); High = a.next(); Low = a.next();
                Close = a.next(); Volume = a.next(); Change = a.next(); ChangePercent = a.next();
                vwap = a.next(); changeOverTime = a.next();

                if(Date.equals(Search)){
                    found = true;
                }
            }
            if (found) {
                System.out.println("Date: " + Date + " Open: " + Open + " High: " + High
                + " Low: " + Low + " Close: " + Close + " Volume: " + Volume
                + " Change: " + Change + " ChangePercent: " + ChangePercent + " Vwap: " + vwap + " Change Over Time: " + changeOverTime);
            }
            else {
                JOptionPane.showMessageDialog(null, "Record not Found");
            }
            a.close();
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Error");
        }
}}
