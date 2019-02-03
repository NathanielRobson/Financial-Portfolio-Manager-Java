import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class SearchandView extends JFrame {

    private static Scanner a;

    public static void readFile(String Search, String filepath) {

        ArrayList<String> theList = new ArrayList<>();
        String[] data;
        JFrame frame = new JFrame();
        frame.setAlwaysOnTop(true);
        boolean found = false;

        String Date = "",
                Company = "",
                SharePrice = "",
                Volume = "",
                Change = "",
                ChangePercent = "",
                vwap = "",
                changeOverTime = "",
                NumofShares = "",
                ShareTotal = "",
                CashinBank = "",
                Total = "";

        try {
            a = new Scanner(new File(filepath));
            a.useDelimiter("[,\n]");

            while (a.hasNext() && !found) {

                Date = a.next();
                Company = a.next();
                SharePrice = a.next();
                Volume = a.next();
                Change = a.next();
                ChangePercent = a.next();
                vwap = a.next();
                changeOverTime = a.next();
                NumofShares = a.next();
                ShareTotal = a.next();
                CashinBank = a.next();
                Total = a.next();

                if (Date.equals(Search)) {
                    found = true;
                }
            }
            if (found) {
                data = new String[]{"Now Showing Information Regarding the Selected Date: " + Date,
                        "Date: " + Date, "Company: " + Company, "Share Price: $" + SharePrice,
                        "Volume: " + Volume, "Change: " + Change, "Percentage Change: %" + ChangePercent,
                        "Vwap: " + vwap, "Change Over Time: " + changeOverTime, "Number of Shares: " + NumofShares,
                        "Shares Total: $" + ShareTotal, "Cash in Bank: $" + CashinBank, "Total: $" + Total};

                JFrame f = new JFrame("Share's on " + Date);
                f.add(new JList<>(data));
                f.setSize(200, 200);
                f.pack();
                f.setLocationRelativeTo(null);
                f.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Record not Found");
            }
            a.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error");
        }
    }
}
