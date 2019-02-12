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

class PurchaseSharesFrame extends JFrame {

    //Main Method for Testing
    String theCurrentUser, userName, userMoney, totalSpent, purchaseDate, theProfit, originalDate;
    Double price;
    JLabel userLabel, userMoneyLabel, symbolLabel, totalLabel, priceLabel, helpLabel, errorLabel;
    JTextField symbolField, totalField;
    JTextArea priceArea, currentMoney;
    JButton getPriceBtn, purchaseBtn, sellBtn;
    DateTimeFormatter dateFormat;
    LocalDate localDate;

    PurchaseSharesFrame(String theCurrentUser) throws IOException {//Frame initialisation, layout and functionality
        this.theCurrentUser = theCurrentUser;
        dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        localDate = LocalDate.now();
        purchaseDate = dateFormat.format(localDate);
        System.out.println(purchaseDate);

        try {
            readPersonalPortfolio();
            setCurrentMoney(userMoney);

        } catch (IOException e) {
            e.printStackTrace();
        }
        setSize(600, 600);

        Font myFieldFont = new Font("Century Gothic", Font.BOLD, 14);
        Font myTextFont = new Font("Century Gothic", Font.BOLD, 16);
        Font myNextFont = new Font("HelveticaNeue-Light", Font.ITALIC, 18);

        Font myButtonFont = new Font("Tahoma", Font.BOLD, 16);
        Color myBlueColor = new Color(59, 69, 182);
        Color priceColor = new Color(38, 200, 191);

        userLabel = new JLabel("Current User: " + theCurrentUser);
        userLabel.setFont(myNextFont);
        userLabel.setForeground(Color.BLACK);

        userMoneyLabel = new JLabel("Current Money: $");
        userMoneyLabel.setFont(myNextFont);
        userMoneyLabel.setForeground(Color.BLACK);

        symbolLabel = new JLabel("Company Symbol: ");
        symbolLabel.setFont(myNextFont);
        symbolLabel.setForeground(myBlueColor);

        symbolField = new JTextField(20);
        symbolField.setFont(myFieldFont);

        priceLabel = new JLabel("Current Share Price: $");
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

        totalLabel = new JLabel("Total To Buy: ");
        totalLabel.setFont(myNextFont);
        totalLabel.setForeground(myBlueColor);

        totalField = new JTextField(3);
        totalField.setFont(myFieldFont);

        helpLabel = new JLabel("<html><font color = purple>Purchase Stocks!</font><br/><br/>Please Enter a Company Symbol<br/> And Then Get The Current Share Price, Then Buy!<br/><br/>" +
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

        getPriceBtn = new JButton("Get Current Price");
        getPriceBtn.setBackground(myBlueColor);
        getPriceBtn.setForeground(priceColor);
        getPriceBtn.setFont(myButtonFont);

        purchaseBtn = new JButton("Purchase Shares");
        purchaseBtn.setBackground(myBlueColor);
        purchaseBtn.setForeground(Color.white);
        purchaseBtn.setFont(myButtonFont);

        sellBtn = new JButton("Sell Shares");
        sellBtn.setBackground(Color.white);
        sellBtn.setForeground(myBlueColor);
        sellBtn.setFont(myButtonFont);

        JPanel userPanel = new JPanel();
        JPanel panelOne = new JPanel();
        JPanel panelTwo = new JPanel();
        JPanel panelThree = new JPanel();
        JPanel panelFour = new JPanel();
        JPanel panelFive = new JPanel();
        JPanel panelSix = new JPanel();

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

        add(userPanel);
        add(panelOne);
        add(panelTwo);
        add(panelThree);
        add(panelFour);
        add(panelFive);
        add(panelSix);

        setLayout(new FlowLayout());
        setTitle("Financial Portfolio Manager Stock Purchase Tool");
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        //Individual action listeners for each button
        getPriceBtn.addActionListener(new ButtonHandler(this, 1));
        purchaseBtn.addActionListener(new ButtonHandler(this, 2));
        sellBtn.addActionListener(new ButtonHandler(this, 3));
    }

    public class ButtonHandler implements ActionListener { //Implements the action listener
        PurchaseSharesFrame theApp;
        int action;

        ButtonHandler(PurchaseSharesFrame app, int action) {
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
                    Double bank = Double.valueOf(currentMoney.getText());

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

            if (action == 3) {

            }
        }
    }

    public void getUpdate() {//Purchase method
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
            errorLabel.setText("Unable to update, error occurred");
        }
    }

    public void readPersonalPortfolio() throws IOException {
        File file = new File(".//UserPortfolios/" + getTheCurrentUser() + "-BANK.csv");

        if (file.exists()) {
            CSVReader reader = new CSVReader(new FileReader(file));
            String[] line = reader.readNext();
            while (line != null) {
                userName = line[0].trim().replaceAll("\"", "");
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
                errorLabel.setText("unable to write data to file");
            }
            readPersonalPortfolio();
        }
    }

    public void purchaseShare(String symbol, int numOfShares, double userMoney) {
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

                    errorLabel.setText("<html>Thankyou For Your Transaction!<br>Purchased: "
                            + numOfShares + " " + symbol + " Share(s)</html");
                } else {
                    errorLabel.setText("Unable to Purchase You Have Insufficient Funds");
                }
            } catch (Exception el) {
                errorLabel.setText("You have cancelled purchase");
            }
        } else {
            errorLabel.setText("You have Clicked Cancel");
        }
    }

    public void UpdatePrice(String symbol) {
        CSVtoArrayService csvtoarray = new CSVtoArrayService();
        csvtoarray.CSVtoArray(symbol + ".csv");

        int recent = csvtoarray.closevaluearray.size() - 1;
        price = csvtoarray.closevaluearray.get(recent);
        originalDate = csvtoarray.datearray.get(recent);
        this.priceArea.setText(String.valueOf(String.format("%.2f", price)));
    }

    public void saveDataToFile(Double currentMoney, String symbol, int buying) throws IOException {
        File savefile = new File(".//UserShares/" + getTheCurrentUser().toUpperCase() + "-" + symbol + "-" + "shares.csv");
        double thecost = Double.parseDouble(priceArea.getText());
        double thetotalcost = thecost * buying;
        String theoldpurchasedate = "";
        String boughtat = "";

        if (savefile.exists()) {
            Scanner scanner = new Scanner(savefile).useDelimiter(",");
            String defaultshares = "0";
            while (scanner.hasNext()) {
                theoldpurchasedate = scanner.next().trim().toUpperCase().replace("\"", "");
                defaultshares = scanner.next().trim().replace("\"", "");
                boughtat = scanner.next().trim().replace("\"", "");
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

    public void sellShare(String symbol, String areaprice, String moneyinbank) {
        double sharevalue = Double.parseDouble(priceArea.getText());
        int ownedshares;
        double totalsale;
        String numbertosell = JOptionPane.showInputDialog("How Many Shares For: " + symbol + " at $" + sharevalue + " Each, Would You Like To Sell?");

        totalsale = Integer.parseInt(numbertosell) * sharevalue;

    }


    public String getTheCurrentUser() {
        return theCurrentUser;

    }

    public void setCurrentMoney(String newUserMoney) {
        this.userMoney = newUserMoney;
    }

    public void setTotalSpent(String totalSpent) {
        this.totalSpent = totalSpent;
    }

    public void setProfit(String theProfit) {
        this.theProfit = theProfit;
    }

    public void writeToCsv(File file, String[] details) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        CSVWriter csvWriter = new CSVWriter(fileWriter);

        csvWriter.writeAll(Collections.singleton(details));

        fileWriter.flush();
        csvWriter.close();
    }
}
