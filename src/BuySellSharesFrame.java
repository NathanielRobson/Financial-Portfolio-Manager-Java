import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Scanner;

class BuySellSharesFrame extends JFrame { //Purchase and Sell Shares Frame

    private String theCurrentUser, userMoney, purchaseDate;
    private Double price;
    private JLabel infoLabel;
    private JLabel errorLabel;
    private JTextField symbolField, totalField;
    private JTextArea priceArea, currentMoney;

    BuySellSharesFrame(String theCurrentUser) {//Frame initialisation, layout and functionality
        //Sets the Current user
        this.theCurrentUser = theCurrentUser;

        //Gets Current Date and Formats the same as CSV data
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.now();
        purchaseDate = dateFormat.format(localDate);

        //Reads User Portfolio
        try {
            readPersonalPortfolio();
            setCurrentMoney(userMoney);
        } catch (IOException e) {
            errorLabel.setText("Unable to read personal portfolio, File may not exist");
        }

        //Custom Fonts and Colors for Look and Feel
        Font myFieldFont = new Font("Century Gothic", Font.BOLD, 14);
        Font myTextFont = new Font("Century Gothic", Font.BOLD, 16);
        Font myNextFont = new Font("HelveticaNeue-Light", Font.ITALIC, 18);
        Font myButtonFont = new Font("Tahoma", Font.BOLD, 16);

        Color myBlueColor = new Color(59, 69, 182);
        Color priceColor = new Color(38, 200, 191);

        //Frame icon
        ImageIcon img = new ImageIcon(".//icons/buysellIcon.png");
        setIconImage(img.getImage());

        JLabel userLabel = new JLabel("Current User: " + theCurrentUser);
        userLabel.setFont(myNextFont);
        userLabel.setForeground(Color.BLACK);

        JLabel userMoneyLabel = new JLabel("Current Money: $");
        userMoneyLabel.setFont(myNextFont);
        userMoneyLabel.setForeground(Color.BLACK);

        JLabel symbolLabel = new JLabel("Company Symbol: ");
        symbolLabel.setFont(myNextFont);
        symbolLabel.setForeground(myBlueColor);

        symbolField = new JTextField(20);
        symbolField.setFont(myFieldFont);

        JLabel priceLabel = new JLabel("Current Share Price: $");
        priceLabel.setFont(myNextFont);
        priceLabel.setForeground(priceColor);

        priceArea = new JTextArea();
        priceArea.append(String.valueOf(price));
        priceArea.setColumns(3);
        priceArea.setEditable(false);

        currentMoney = new JTextArea();
        currentMoney.append(String.valueOf(userMoney));
        currentMoney.setColumns(5);
        currentMoney.setFont(myNextFont);
        currentMoney.setEditable(false);

        JLabel totalLabel = new JLabel("Total To Buy/Sell: ");
        totalLabel.setFont(myNextFont);
        totalLabel.setForeground(myBlueColor);

        totalField = new JTextField(3);
        totalField.setFont(myFieldFont);

        JLabel helpLabel = new JLabel("<html><font color = purple>Purchase Stocks!</font><br/><br/>Please Enter a Company Symbol<br/> And Then Get The Current Share Price, Then Buy!<br/><br/>" +
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

        infoLabel = new JLabel("");
        infoLabel.setForeground(Color.blue);
        infoLabel.setFont(myNextFont);

        JButton getPriceBtn = new JButton("Get Current Price");
        getPriceBtn.setBackground(myBlueColor);
        getPriceBtn.setForeground(priceColor);
        getPriceBtn.setFont(myButtonFont);

        JButton purchaseBtn = new JButton("Purchase Shares");
        purchaseBtn.setBackground(myBlueColor);
        purchaseBtn.setForeground(Color.white);
        purchaseBtn.setFont(myButtonFont);

        JButton sellBtn = new JButton("Sell Shares");
        sellBtn.setBackground(Color.white);
        sellBtn.setForeground(myBlueColor);
        sellBtn.setFont(myButtonFont);

        //Panel Initialisation
        JPanel userPanel = new JPanel();
        JPanel panelOne = new JPanel();
        JPanel panelTwo = new JPanel();
        JPanel panelThree = new JPanel();
        JPanel panelFour = new JPanel();
        JPanel panelFive = new JPanel();
        JPanel panelSix = new JPanel();
        JPanel panelSeven = new JPanel();

        //Adding objects to panels
        userPanel.add(userLabel);
        userPanel.add(userMoneyLabel);
        userPanel.add(currentMoney);
        panelOne.add(symbolLabel);
        panelOne.add(symbolField);
        panelTwo.add(priceLabel);
        panelTwo.add(priceArea);
        panelThree.add(totalLabel);
        panelThree.add(totalField);
        panelFour.add(getPriceBtn);
        panelFour.add(purchaseBtn);
        panelFour.add(sellBtn);
        panelFive.add(helpLabel);
        panelSix.add(errorLabel);
        panelSeven.add(infoLabel);

        //Adding panels to Frame
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
        setTitle("Financial Portfolio Manager Stock Purchase or Sell Shares");
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setSize(600, 600);

        //Individual action listeners for each button
        getPriceBtn.addActionListener(new ButtonHandler(this, 1));
        purchaseBtn.addActionListener(new ButtonHandler(this, 2));
        sellBtn.addActionListener(new ButtonHandler(this, 3));
    }

    public class ButtonHandler implements ActionListener { //Implements the action listener
        BuySellSharesFrame theApp;
        int action;

        ButtonHandler(BuySellSharesFrame app, int action) {
            this.theApp = app;
            this.action = action;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            if (action == 1) {//Update share price button
                getUpdate();
            }
            if (action == 2) {//Purchase shares
                try {
                    String symbol = symbolField.getText().trim().toUpperCase();
                    int totaltobuy = Integer.parseInt(totalField.getText());
                    double bank = Double.valueOf(currentMoney.getText());

                    try {
                        purchaseShare(symbol, totaltobuy, bank);
                    } catch (Exception el) {
                        errorLabel.setText("Error while purchasing " + symbol + " shares ");
                        System.out.println(el.toString());
                    }
                    try {
                        saveDataToFile(bank, symbol, totaltobuy);
                    } catch (Exception el) {
                        errorLabel.setText("Error while saving to file " + symbol);
                        System.out.println(el.toString());
                    }

                } catch (Exception el) {
                    errorLabel.setText("unable to purchase, ensure input is correct");
                }
            }

            if (action == 3) {//Sell Owned Shares
                try {
                    String symbol = symbolField.getText().trim().toUpperCase();
                    int totaltosell = Integer.parseInt(totalField.getText());
                    double bank = Double.valueOf(currentMoney.getText());

                    try {
                        sellShare(symbol, bank, String.valueOf(totaltosell));
                    } catch (Exception el) {
                        errorLabel.setText("Unable to Sell Shares, Ensure the Correct Input is Used and Price is Updated");
                    }
                } catch (Exception el) {
                    errorLabel.setText("Please Ensure Input is Correct and Symbol Exists. Thankyou");
                }
            }
        }
    }

    private void getUpdate() {//Update CSV method
        CSVUpdateService quoteClass;
        quoteClass = new CSVUpdateService();

        try {
            String symbol = symbolField.getText();
            String crumb = quoteClass.getCrumb(symbol);

            if (crumb != null && !crumb.isEmpty()) {
                errorLabel.setText(String.format("<html>Updated Price using the symbol '%s'<html/>", symbol.toUpperCase()));
                errorLabel.setForeground(Color.red);
                quoteClass.downloadData(symbol, 0, System.currentTimeMillis(), crumb);
                UpdatePrice(symbol);

            } else {
                errorLabel.setText("Unable to update price using the Symbol: " + symbol.toUpperCase());
            }
        } catch (Exception el) {
            errorLabel.setText("Unable to update, error occurred, Check Internet Connection");
        }
    }

    //Reads the personal portfolio Bank account file to produce array and file containing bank information
    private void readPersonalPortfolio() throws IOException {
        File file = new File(".//UserPortfolios/" + getTheCurrentUser() + "-BANK.csv");

        if (file.exists()) {
            CSVReader reader = new CSVReader(new FileReader(file));
            String[] line = reader.readNext();
            while (line != null) {
                userMoney = line[1].trim().replaceAll("\"", "");
                line = reader.readNext();
            }
            setCurrentMoney(userMoney);

        } else if (!file.exists()) {
            String startingcash = "10000";
            String[] uservalues = new String[]{getTheCurrentUser(), startingcash};

            try {
                writeToCsv(file, uservalues);
            } catch (Exception el) {
                errorLabel.setText("Unable to Write Data to File");
            }
            readPersonalPortfolio();
        }
    }

    //Purchase method
    private void purchaseShare(String symbol, int numOfShares, double userMoney) {
        double cost = Double.parseDouble(priceArea.getText());
        double totalcost = cost * numOfShares;
        int choice = JOptionPane.showConfirmDialog(this,
                "Confirm, You Are Purchasing " + numOfShares + " " + symbol + "\n For: $" + String.format("%.2f", totalcost));

        if (choice == JOptionPane.YES_OPTION) {
            try {
                if (totalcost <= userMoney) {
                    userMoney = userMoney - totalcost;
                    String cmoney = String.valueOf(String.format("%.2f", userMoney));
                    setCurrentMoney(cmoney);

                    this.currentMoney.setText(cmoney);

                    String[] moneyupdate = new String[]{theCurrentUser, cmoney};
                    File file = new File(".//UserPortfolios/" + getTheCurrentUser() + "-BANK.csv");
                    writeToCsv(file, moneyupdate);

                    dataCollection(cmoney, theCurrentUser, symbol, purchaseDate);

                    errorLabel.setText("<html>Thankyou For Your Transaction!<br>Purchased: "
                            + numOfShares + " " + symbol + " Share(s)</html");
                } else {
                    errorLabel.setText("Unable to Purchase Shares, You Have Insufficient Funds");
                }
            } catch (Exception el) {
                errorLabel.setText("You have Cancelled The Transaction Successfully");
            }
        } else {
            errorLabel.setText("You have Cancelled The Transaction Successfully");
        }
    }

    //Updates the price of the company selected
    private void UpdatePrice(String symbol) {
        CSVtoArrayService csvtoarray = new CSVtoArrayService();
        csvtoarray.CSVtoArray(symbol + ".csv");
        int recent = csvtoarray.closevaluearray.size() - 1;
        price = csvtoarray.closevaluearray.get(recent);
        this.priceArea.setText(String.valueOf(String.format("%.2f", price)));
    }

    //Saves Information to file
    private void saveDataToFile(Double currentMoney, String symbol, int buying) throws IOException {
        File savefile = new File(".//UserShares/" + getTheCurrentUser().toUpperCase() + "-" + symbol + "-" + "shares.csv");
        double thecost = Double.parseDouble(priceArea.getText());
        double thetotalcost = thecost * buying;

        if (savefile.exists()) {
            Scanner scanner = new Scanner(savefile).useDelimiter(",");
            String defaultshares = "0";
            while (scanner.hasNext()) {
                scanner.next();
                defaultshares = scanner.next().trim().replace("\"", "");
                scanner.next();
            }
            scanner.close();

            int intshares = Integer.parseInt(defaultshares.trim().replaceAll("\"", ""));
            int newshares = intshares + buying;
            String addedshares = String.valueOf(newshares);
            String[] newcsvlines = new String[]{purchaseDate, addedshares, String.valueOf(thecost)};

            writeToCsv(savefile, newcsvlines);

        } else if (!savefile.exists()) {
            String[] values = new String[]{purchaseDate, String.valueOf(buying), String.valueOf(thecost)};
            if (currentMoney > thetotalcost) {
                writeToCsv(savefile, values);
            }
        } else {
            errorLabel.setText("Unable to Create New Save File For User");
        }
    }

    //Sell Share Method
    private void sellShare(String symbol, double userMoney, String selling) throws IOException {
        File savefile = new File(".//UserShares/" + getTheCurrentUser().toUpperCase() + "-" + symbol + "-" + "shares.csv");

        double newsharevalue = Double.parseDouble(priceArea.getText());
        double totalsale = newsharevalue * Integer.valueOf(selling);
        int ownedshares;
        String boughtat;

        int choice = JOptionPane.showConfirmDialog(this,
                "Confirm, You Are Selling " + selling + " " + symbol + "Shares \n For: $" + String.format("%.2f", totalsale));

        if (choice == JOptionPane.YES_OPTION) {
            if (savefile.exists()) {
                Scanner scanner = new Scanner(savefile).useDelimiter(",");
                String defaultshares = "0";
                boughtat = "0";

                while (scanner.hasNext()) {
                    scanner.next();
                    defaultshares = scanner.next().trim().replace("\"", "");
                    boughtat = scanner.next().trim().replace("\"", "");
                }
                scanner.close();

                ownedshares = Integer.parseInt(defaultshares.trim().replaceAll("\"", ""));

                if (ownedshares >= Integer.valueOf(selling)) {
                    int sharesaftersale = ownedshares - Integer.valueOf(selling);
                    double profitloss;
                    if (Double.parseDouble(boughtat) > newsharevalue) {

                        profitloss = -(Math.abs(Double.parseDouble(boughtat) - newsharevalue));
                        infoLabel.setText("Loss in Sale by: " + String.format("%.2f", profitloss) + " Per Share");
                        System.out.println(profitloss);

                    } else if (Double.parseDouble(boughtat) <= newsharevalue) {
                        profitloss = (Math.abs(Double.parseDouble(boughtat) - newsharevalue));
                        infoLabel.setText("Profit or Breaking Even in Sale by: " + String.format("%.2f",profitloss) + " Per Share");
                        System.out.println(profitloss);

                    }

                    userMoney = userMoney + totalsale;
                    String cmoney = String.valueOf(String.format("%.2f", userMoney));
                    setCurrentMoney(cmoney);
                    this.currentMoney.setText(cmoney);

                    dataCollection(cmoney, theCurrentUser, symbol, purchaseDate);

                    String[] newcsvlines = new String[]{purchaseDate, String.valueOf(sharesaftersale), boughtat};

                    writeToCsv(savefile, newcsvlines);

                    String[] moneyupdate = new String[]{theCurrentUser, cmoney};
                    File file = new File(".//UserPortfolios/" + getTheCurrentUser() + "-BANK.csv");
                    writeToCsv(file, moneyupdate);

                    errorLabel.setText("<html>Thankyou for Selling " + selling + " " + symbol + " Shares With Us<br/>" +
                            "You Sold Them For: $" + String.format("%.2f", totalsale) + "</html>");

                } else if (ownedshares < Integer.valueOf(selling)) {
                    errorLabel.setText("Not Enough Shares To Sell, You Have " + ownedshares + " " + symbol + " Shares");
                } else {
                    errorLabel.setText("Unable to Sell Shares, Ensure the Correct Input is Used and Price is Updated");
                }
            } else {
                errorLabel.setText("Sorry No Shares Data Has Been Found");
            }
        }
    }

    //Data Collection method for Line Chart
    private void dataCollection(String totalworth, String theCurrentUser, String symbol, String purchaseDate) throws IOException {
        File savefile = new File(".//UserPortfolios/" + getTheCurrentUser().toUpperCase() + "-" + "Data.txt");
        Writer output = new BufferedWriter(new FileWriter(savefile, true));

        output.append(totalworth);
        output.append(",");
        output.append(theCurrentUser);
        output.append(",");
        output.append(purchaseDate);
        output.append(symbol);
        output.append("\n");

        output.close();

    }

    //Writes Data to CSV
    private void writeToCsv(File file, String[] details) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        CSVWriter csvWriter = new CSVWriter(fileWriter);
        csvWriter.writeAll(Collections.singleton(details));
        fileWriter.flush();
        csvWriter.close();
    }

    //Getters and Setters, Current user and Money
    private String getTheCurrentUser() {
        return theCurrentUser;
    }

    private void setCurrentMoney(String newUserMoney) {
        this.userMoney = newUserMoney;
    }
}
