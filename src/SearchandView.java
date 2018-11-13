import javax.swing.*;
import javax.swing.JFileChooser;
import java.io.File;
import java.util.Scanner;

public class SearchandView {
    private static Scanner a;
    public static void main(String[] args) {

        JFileChooser chooser = new JFileChooser();
        File F = new File ("C:/");
        File FilePath;
        int Checker;
        chooser.setCurrentDirectory(F);
        Checker = chooser.showOpenDialog(null);

        if(Checker == JFileChooser.APPROVE_OPTION){
            FilePath = chooser.getSelectedFile();
            String a = FilePath.getAbsolutePath();
            String Search = JOptionPane.showInputDialog("Please enter the date you wish to display (DD/MM/YYYY): ");

            readFile(Search, a);
        }
        else{
            JOptionPane.showMessageDialog(null, "You have Clicked Cancel");
        }
    }

    public static void readFile(String Search, String filepath) {
        JFrame frame = new JFrame();
        frame.setAlwaysOnTop(true);
        boolean found = false;
        String Date = ""; String SharePrice = ""; String Volume = ""; String Change = "";
        String ChangePercent = ""; String vwap = ""; String changeOverTime = "";
        String NumofShares = ""; String ShareTotal = ""; String CashinBank = "";
        String Total = "";
        try {
            a = new Scanner(new File(filepath));
            a.useDelimiter("[,\n]");

            while(a.hasNext() && !found){
                Date = a.next(); SharePrice = a.next(); Volume = a.next();
                Change = a.next(); ChangePercent = a.next(); vwap = a.next();
                changeOverTime = a.next(); NumofShares = a.next(); ShareTotal = a.next();
                CashinBank = a.next(); Total = a.next();

                if(Date.equals(Search)){
                    found = true;
                }
            }
            if (found) {
                JOptionPane.showMessageDialog(frame, "Date: " + Date + " Share Price: $" + SharePrice + " Volume: " + Volume +
                                " Change: " + Change + " ChangePercent: %" + ChangePercent + " Vwap: " + vwap + " Change Over Time: " + changeOverTime +
                                " Number of Shares: " + NumofShares + " Shares Total: $" + ShareTotal + " Cash in Bank: $" + CashinBank + " Total : $" + Total);
                System.exit(0);
            }
            else {
                JOptionPane.showMessageDialog(null, "Record not Found");
            }
            a.close();
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Error");
        }
    }
}
