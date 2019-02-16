import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

class OwnedSharesFrame extends JFrame { //Currently Owned Shares Frame

    private String theCurrentUser;
    private JLabel errorLabel;
    private JTextArea sharesArea;

    //Reads the file directory, finds all files containing shares and outputs the current users shares
    private void listAllFiles(File folder) {
        File[] fileNames = folder.listFiles();
        assert fileNames != null;
        for (File file : fileNames) {
            // if directory call the same method again
            if (file.isDirectory() && file.getName().contains(getTheCurrentUser())) {
                listAllFiles(file);
            } else {
                try {
                    if (file.getName().contains(getTheCurrentUser())) {
                        String sharename = (file.getName().substring(3).replaceAll(".csv", "").replaceAll("-", " "));
                        sharesArea.append(sharename);
                        sharesArea.append("\n");
                        readContent(file);
                    }
                } catch (IOException e) {
                    errorLabel.setText("No Shares Found");
                }
            }
        }
    }

    //Reads each file line and appends the text area
    private void readContent(File file) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String strLine;
            // Read lines from the file, returns null when end of stream
            // is reached
            while ((strLine = br.readLine()) != null) {
                String[] nl = strLine.split(",");
                String share = nl[1].replaceAll("\"", "");
                sharesArea.append(share);
                sharesArea.append("\n");
            }
        }
    }

    OwnedSharesFrame(String theCurrentUser) {//Frame initialisation, layout and functionality
        //Sets the current user
        this.theCurrentUser = theCurrentUser;

        //Custom Colors and Fonts for look and feel
        Font myFieldFont = new Font("Century Gothic", Font.BOLD, 14);
        Font myTextFont = new Font("Century Gothic", Font.BOLD, 16);
        Font myNextFont = new Font("HelveticaNeue-Light", Font.ITALIC, 18);

        Color myBlueColor = new Color(59, 69, 182);

        JLabel userLabel = new JLabel("The Current User: " + theCurrentUser);
        userLabel.setFont(myNextFont);
        userLabel.setForeground(Color.BLACK);

        //Share area holds the shares information
        sharesArea = new JTextArea();
        sharesArea.setColumns(27);
        sharesArea.setRows(10);
        sharesArea.setFont(myNextFont);
        sharesArea.setEditable(false);

        JLabel helpLabel = new JLabel("<html><font color = purple>You Currently Own The Following Company Shares</font><br/>");
        helpLabel.setForeground(myBlueColor);
        helpLabel.setFont(myTextFont);

        errorLabel = new JLabel("");
        errorLabel.setForeground(Color.red);
        errorLabel.setFont(myFieldFont);

        //Panel initialisation
        JPanel userPanel = new JPanel();
        JPanel panelOne = new JPanel();
        JPanel panelTwo = new JPanel();
        JScrollPane scrollPane = new JScrollPane(sharesArea);

        //Adding Objects to Panels
        userPanel.add(userLabel);
        panelOne.add(helpLabel);
        panelTwo.add(errorLabel);

        //Adding panels to frame
        add(userPanel);
        add(panelOne);
        add(scrollPane);
        add(panelTwo);

        //Frame Constraints
        setLayout(new FlowLayout());
        setTitle("Financial Portfolio Manager Bank Account");
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setSize(550, 460);

        File folder = new File(".//UserShares");

        //Method Call to Find Shares Owned
        listAllFiles(folder);

    }

    //Gets the current user
    private String getTheCurrentUser() {
        return theCurrentUser;
    }
}
