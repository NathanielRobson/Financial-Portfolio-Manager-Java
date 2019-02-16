import javax.swing.*;
import java.io.File;
import java.util.Scanner;

class SearchandViewService extends JFrame { //Search and View Service

    static void readFile(String filepath, String Search) {//Search for file, search for chosen date then provide information regarding that day for selected company
        String[] data;
        JFrame frame = new JFrame();
        frame.setAlwaysOnTop(true);
        boolean found = false;

        //String initialisation specific to csv layout
        String Date = "",
                Open = "",
                High = "",
                Low = "",
                Close = "",
                AdjClose = "",
                Volume = "";
        try {
            Scanner a = new Scanner(new File(filepath));
            a.useDelimiter("[,\n]");

            while (a.hasNext() && !found) {
                //while csv has next value assign to next variable
                Date = a.next();
                Open = a.next();
                High = a.next();
                Low = a.next();
                Close = a.next();
                AdjClose = a.next();
                Volume = a.next();

                if (Date.contains(Search)) {
                    found = true;
                }
            }
            if (found) {

                //data displayed to the user
                data = new String[]{"Now Showing Information Regarding the Selected Date: " + Date,
                        "Date: " + Date, "Open: " + Open, "High:" + High,
                        "Low: " + Low, "Close: " + Close, "Adj Close: %" + AdjClose,
                        "Volume: " + Volume};

                //Frame initialisation to show only a list
                JFrame f = new JFrame("Share's on " + Date);
                f.add(new JList<>(data));
                f.setSize(200, 200);
                f.pack();
                f.setLocationRelativeTo(null);
                f.setVisible(true);
            } else {//Exception handling
                JOptionPane.showMessageDialog(null, "Record not Found");
            }
            a.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Unable To Find Date of Record");
        }

    }
}