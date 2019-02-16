import com.opencsv.CSVWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;

public class AddSharesFrame extends JFrame { //Add Shares Frame
    //Main method for testing
    public static void main(String[] args) {
        new AddSharesFrame("NR");
    }

    private String theCurrentUser;
    private JLabel errorLabel;
    private JTextField inputField;
    private JTextField symbolField;
    private JTextField priceField;
    private JTextField dateField;

    AddSharesFrame(String theCurrentUser) {//Frame initialisation, layout and functionality
        this.theCurrentUser = theCurrentUser;

        //Look and Feel Effects
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Custom fonts and colors for look and feel
        Font myFieldFont = new Font("Century Gothic", Font.BOLD, 14);
        Font myTextFont = new Font("Century Gothic", Font.BOLD, 16);
        Font myNextFont = new Font("HelveticaNeue-Light", Font.ITALIC, 18);
        Font myButtonFont = new Font("Tahoma", Font.BOLD, 16);

        Color myBlueColor = new Color(59, 69, 182);
        Color priceColor = new Color(38, 200, 191);

        JLabel userLabel = new JLabel("The Current User Logged In: " + theCurrentUser);
        userLabel.setFont(myNextFont);
        userLabel.setForeground(Color.BLACK);

        JLabel symbolLabel = new JLabel("Company Symbol: ");
        symbolLabel.setFont(myNextFont);
        symbolLabel.setForeground(Color.BLACK);

        JLabel inputLabel = new JLabel("How Many Shares To Add: ");
        inputLabel.setFont(myNextFont);
        inputLabel.setForeground(myBlueColor);

        JLabel dateLabel = new JLabel("Date? Input Like So (yyyy-MM-dd)");
        dateLabel.setFont(myNextFont);
        dateLabel.setForeground(myBlueColor);

        JLabel priceLabel = new JLabel("How Much Did You Pay?");
        priceLabel.setFont(myNextFont);
        priceLabel.setForeground(myBlueColor);

        symbolField = new JTextField(10);
        symbolField.setFont(myFieldFont);

        inputField = new JTextField(5);
        inputField.setFont(myFieldFont);

        dateField = new JTextField(12);
        dateField.setFont(myFieldFont);

        priceField = new JTextField(5);
        priceField.setFont(myFieldFont);

        JLabel helpLabel = new JLabel("<html><font color = purple>Add Previously Owned Shares</font><br/>Please Enter The Symbol And An Amount to Add<br/>" +
                "Ensure Input is Between 1-1000<br>" +
                "Some Examples: <br/>" +
                "<font color = red>Netflix</font> = \"NFLX\"<br/>" +
                "<font color = green>Microsoft</font> = \"MSFT\"<br/>" +
                "<font color = #00FFFF>Twitter</font> = \"TWTR\"<br/>" +
                "<font color = gray>Apple</font> = \"AAPL\"<br/>" +
                "Facebook = \"FB\"</html>");
        helpLabel.setForeground(myBlueColor);
        helpLabel.setFont(myTextFont);

        errorLabel = new JLabel("");
        errorLabel.setForeground(Color.red);
        errorLabel.setFont(myFieldFont);

        JButton addShareBtn = new JButton("Add Shares");
        addShareBtn.setBackground(myBlueColor);
        addShareBtn.setForeground(priceColor);
        addShareBtn.setFont(myButtonFont);

        JButton resetBtn = new JButton("Reset");
        resetBtn.setBackground(myBlueColor);
        resetBtn.setForeground(Color.white);
        resetBtn.setFont(myButtonFont);

        //Panel initialisation
        JPanel userPanel = new JPanel();
        JPanel panelOne = new JPanel();
        JPanel panelTwo = new JPanel();
        JPanel panelThree = new JPanel();
        JPanel panelFour = new JPanel();
        JPanel panelFive = new JPanel();
        JPanel panelSix = new JPanel();
        JPanel panelSeven = new JPanel();

        //Adding Objects to panels
        userPanel.add(userLabel);
        panelOne.add(symbolLabel);
        panelOne.add(symbolField);
        panelTwo.add(inputLabel);
        panelTwo.add(inputField);
        panelThree.add(dateLabel);
        panelThree.add(dateField);
        panelFour.add(priceLabel);
        panelFour.add(priceField);
        panelFive.add(addShareBtn);
        panelFive.add(resetBtn);
        panelSix.add(helpLabel);
        panelSeven.add(errorLabel);

        //Adding panels to frame
        add(userPanel);
        add(panelOne);
        add(panelTwo);
        add(panelThree);
        add(panelFour);
        add(panelFive);
        add(panelSix);
        add(panelSeven);

        //Frame Constraints
        setLayout(new FlowLayout());
        setTitle("Financial Portfolio Manager Add Your Owned Shares");
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setSize(500, 550);

        //Individual action listeners for each button
        addShareBtn.addActionListener(new ButtonHandler(this, 1));
        resetBtn.addActionListener(new ButtonHandler(this, 2));
    }

    public class ButtonHandler implements ActionListener { //Implements the action listener
        AddSharesFrame theApp;
        int action;

        ButtonHandler(AddSharesFrame app, int action) {
            this.theApp = app;
            this.action = action;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String value = inputField.getText().trim();
            String symbol = symbolField.getText().trim().toUpperCase();
            String date = dateField.getText().trim().replaceAll("/", "-");
            String price = priceField.getText().trim();

            if (action == 1) {//Add Previously Bought Shares
                try {
                    addShares(symbol, Integer.parseInt(value), date, Double.valueOf(price));
                    errorLabel.setText("<html>Thankyou For Adding " + value + " " + symbol + " Previously Purchased Shares!<br/>" +
                            "Your Shares Are Now Manipulable Through Our Program</html>");

                } catch (Exception el) {
                    errorLabel.setText("You have input invalid values please try again");
                }
            }
            if (action == 2) {
                inputField.setText("");
                symbolField.setText("");
                dateField.setText("");
                priceField.setText("");
            }
        }

        private String getTheCurrentUser() {
            return theCurrentUser;
        }

        private void addShares(String symbol, int adding, String purchaseDate, double price) throws IOException {
            File savefile = new File(".//UserShares/" + getTheCurrentUser().toUpperCase() + "-" + symbol + "-" + "shares.csv");

            if (savefile.exists()) {
                Scanner sc = new Scanner(savefile).useDelimiter(",");
                String defaultshares = "0";
                while (sc.hasNext()) {
                    purchaseDate = sc.next().trim().replace("\"", "");
                    defaultshares = sc.next().trim().replace("\"", "");
                    sc.next();
                }
                sc.close();

                int intshares = Integer.parseInt(defaultshares.trim().replaceAll("\"", ""));
                int newshares = intshares + adding;
                System.out.println();
                String addedshares = String.valueOf(newshares);
                System.out.println();
                String[] newcsvlines = new String[]{purchaseDate, addedshares, String.valueOf(price)};
                writeToCsv(savefile, newcsvlines);

            } else if (!savefile.exists()) {
                String[] values = new String[]{purchaseDate, String.valueOf(adding), String.valueOf(price)};
                writeToCsv(savefile, values);
            } else {
                errorLabel.setText("Unable to Create New Save File For User");
            }
        }

        private void writeToCsv(File file, String[] details) throws IOException {
            FileWriter fileWriter = new FileWriter(file);
            System.out.println();
            CSVWriter csvWriter = new CSVWriter(fileWriter);
            csvWriter.writeAll(Collections.singleton(details));
            fileWriter.flush();
            csvWriter.close();
        }
    }
}


