import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

class FinancialPortfolioFrame extends JFrame { //Currently Owned Shares Frame

    //Main  method for Testing
    public static void main(String[] args) {
        new FinancialPortfolioFrame("NR");
    }

    private String theCurrentUser;
    private String userMoney;
    private double totalValue;
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
                        sharesArea.append(":");
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

    FinancialPortfolioFrame(String theCurrentUser) {//Frame initialisation, layout and functionality
        //Sets the current user
        this.theCurrentUser = theCurrentUser;

        //Custom Colors and Fonts for look and feel
        Font myFieldFont = new Font("Century Gothic", Font.BOLD, 14);
        Font myTextFont = new Font("Century Gothic", Font.BOLD, 16);
        Font myNextFont = new Font("HelveticaNeue-Light", Font.ITALIC, 18);

        //Frame colours
        Color myBlueColor = new Color(59, 69, 182);

        //Frame icon
        ImageIcon img = new ImageIcon(".//icons/frameIcon.png");
        setIconImage(img.getImage());

        JButton portfolioBtn = new JButton("View The User: " + theCurrentUser + "'s Personal Portfolio");
        portfolioBtn.setBackground(myBlueColor);
        portfolioBtn.setForeground(Color.white);
        portfolioBtn.setFont(myNextFont);

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
        JPanel panelThree = new JPanel();
        JScrollPane scrollPane = new JScrollPane(sharesArea);

        //Adding Objects to Panels
        userPanel.add(userLabel);
        panelOne.add(helpLabel);
        panelTwo.add(portfolioBtn);
        panelThree.add(errorLabel);

        //Adding panels to frame
        add(userPanel);
        add(panelOne);
        add(scrollPane);
        add(panelTwo);

        //Frame Constraints
        setLayout(new FlowLayout());
        setTitle("Financial Portfolio Manager Frame");
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setSize(560, 480);

        File folder = new File(".//UserShares");

        //Method Call to Find Shares Owned
        listAllFiles(folder);

        //Button Action Listener
        portfolioBtn.addActionListener(new ButtonHandler(this, 1));

    }

    //Button handler class to control buttons functions on frame
    public class ButtonHandler implements ActionListener { //Implements the action listener
        FinancialPortfolioFrame theApp;
        int action;
        Double price;

        ButtonHandler(FinancialPortfolioFrame app, int action) {
            this.theApp = app;
            this.action = action;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            //View the current users Personal Portfolio
            if (action == 1) {
                ArrayList<Integer> shareList = new ArrayList<>();
                ArrayList<String> symbolList = new ArrayList<>();
                ArrayMaker(shareList, symbolList);
                calculateValue(shareList, symbolList);
                try {
                    readPersonalPortfolio();
                } catch (IOException e1) {
                    errorLabel.setText("Unable to read bank account");
                }
                viewPortfolio();
            }
        }

        private void viewPortfolio() {
            double totalworth = Double.parseDouble(userMoney) + totalValue;
            sharesArea.setText(theCurrentUser + "'s Total 'Shares' Financial Value: \n'$" +
                    String.format("%.2f", totalValue) + "' \nThe user has '$" + userMoney + "' in the bank.\n" +
                    "The user started with '$10,000' \nYou have increased your total Financial Value by: \n'$" + String.format("%.2f", totalValue - 10000) + "'\n" +
                    "\nYour Absolute Total Worth: '$" + String.format("%.2f", totalworth) + "'\n" +
                    "Thankyou for using our Program :)");
            System.out.println(userMoney);
        }

        //Takes the two lists from the text area to produce the total value of the user
        private void calculateValue(ArrayList<Integer> shareList, ArrayList<String> symbolList) {
            CSVUpdateService quoteClass;
            quoteClass = new CSVUpdateService();
            try {

                //For every value in the symbol list, update the price of each company and add the total value to the next total value
                for (int i = 0; i < symbolList.size(); i++) {
                    String symbol = symbolList.get(i);
                    String crumb = quoteClass.getCrumb(symbol);

                    if (crumb != null && !crumb.isEmpty()) {
                        errorLabel.setText(String.format("<html>Updated Price using the symbol '%s'<html/>", symbol.toUpperCase()));
                        errorLabel.setForeground(Color.red);
                        quoteClass.downloadData(symbol, 0, System.currentTimeMillis(), crumb);
                        updatePrice(symbol);

                    } else {
                        errorLabel.setText("Unable to update price using the Symbol: " + symbol.toUpperCase());
                    }
                    System.out.println(shareList.get(i));
                    Double d = price * shareList.get(i);
                    totalValue += d;
                    System.out.println(totalValue);

                }
            } catch (Exception el) {
                errorLabel.setText("Unable to update, error occurred, Check Internet Connection");
            }
        }

        //Method to create two arrays from the JTextArea, symbol array and share value array
        private void ArrayMaker(ArrayList<Integer> shareList, ArrayList<String> symbolList) {
            String[] strArr = theApp.sharesArea.getText().split("\n");
            for (String s : strArr) {
                int price = Integer.parseInt(s.substring(s.indexOf(":")).replaceAll(":", ""));
                String[] shareName = s.substring(0, s.indexOf(":")).substring(0, s.indexOf(" ")).replaceAll(" ", "").split("\n");
                symbolList.add(shareName[0]);
                shareList.add(price);
            }
        }

        //Updates the price of each of the companies in the JTextArea
        private void updatePrice(String symbol) {
            CSVtoArrayService csvtoarray = new CSVtoArrayService();
            csvtoarray.CSVtoArray(symbol + ".csv");
            int recent = csvtoarray.closevaluearray.size() - 1;
            price = csvtoarray.closevaluearray.get(recent);
            System.out.println("Company: " + symbol + " price: " + price);
            //this.priceArea.setText(String.valueOf(String.format("%.2f", price)));
        }

        //Reads the current users bank account data
        private void readPersonalPortfolio() throws IOException {
            File file = new File(".//UserPortfolios/" + getTheCurrentUser() + "-BANK.csv");

            if (file.exists()) {
                CSVReader reader = new CSVReader(new FileReader(file));
                String[] line = reader.readNext();
                while (line != null) {
                    System.out.println();
                    userMoney = line[1].trim().replaceAll("\"", "");
                    line = reader.readNext();
                }
                setCurrentMoney(userMoney);

                //If the file doesnt exist, it creates it and sets the bank starting value at 10000
            } else if (!file.exists()) {
                String startingcash = "10000";
                String[] uservalues = new String[]{getTheCurrentUser(), startingcash};

                try {
                    writeToCsv(file, uservalues);
                } catch (Exception el) {
                    errorLabel.setText("unable to write data to file");
                }
                readPersonalPortfolio();
            }
        }

        //Writes to CSV if file does not exist
        private void writeToCsv(File file, String[] details) throws IOException {
            FileWriter fileWriter = new FileWriter(file);
            CSVWriter csvWriter = new CSVWriter(fileWriter);
            System.out.println();
            csvWriter.writeAll(Collections.singleton(details));
            System.out.println();
            fileWriter.flush();
            csvWriter.close();
        }
    }

    //Sets the current users money
    private void setCurrentMoney(String newUserMoney) {
        this.userMoney = newUserMoney;
    }

    //Gets the current user
    private String getTheCurrentUser() {
        return theCurrentUser;
    }
}
