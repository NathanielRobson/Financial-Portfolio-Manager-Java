import com.opencsv.CSVReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

class PurchaseStocksFrame extends JFrame {

    //Main Method for Testing
    String theCurrentUser,userName,userMoney,totalSpent,theProfit;
    Double price;
    JLabel userLabel,userMoneyLabel,symbolLabel,totalLabel,priceLabel,helpLabel,errorLabel;
    JTextField symbolField,totalField;
    JTextArea priceArea,currentMoney;
    JButton getPriceBtn,purchaseBtn;

    PurchaseStocksFrame(String theCurrentUser) {//Frame initialisation, layout and functionality
        this.theCurrentUser = theCurrentUser;
        try {
            readPersonalPortfolio();
            setCurrentMoney(userMoney);

        } catch (IOException e) {
            e.printStackTrace();
        }
        setSize(500, 500);

        Font myFieldFont = new Font("Century Gothic", Font.BOLD, 14);
        Font myTextFont = new Font("Century Gothic", Font.BOLD, 16);
        Font myNextFont = new Font("HelveticaNeue-Light", Font.ITALIC, 18);

        Font myButtonFont = new Font("Tahoma", Font.BOLD, 20);
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
                "<font color = red>Netflix</font> = \"NFLX.CSV\" or \"NFLX\"<br/>" +
                "<font color = green>Microsoft</font> = \"NFLX.CSV\" or \"NFLX\"<br/>" +
                "<font color = #00FFFF>Twitter</font> = \"TWTR.CSV\" or \"TWTR\"<br/>" +
                "<font color = gray>Apple</font> = \"AAPL.CSV\" or \"AAPL\"<br/>" +
                "Facebook = \"FB.CSV\" or \"FB\"</html>");
        helpLabel.setForeground(myBlueColor);
        helpLabel.setFont(myTextFont);

        errorLabel = new JLabel("");
        errorLabel.setForeground(Color.red);
        errorLabel.setFont(myFieldFont);

        getPriceBtn = new JButton("Get Current Price");
        getPriceBtn.setBackground(myBlueColor);
        getPriceBtn.setForeground(priceColor);
        getPriceBtn.setFont(myButtonFont);

        purchaseBtn = new JButton("Purchase Stocks");
        purchaseBtn.setBackground(myBlueColor);
        purchaseBtn.setForeground(Color.white);
        purchaseBtn.setFont(myButtonFont);

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
    }

    public class ButtonHandler implements ActionListener { //Implements the action listener
        PurchaseStocksFrame theApp;
        int action;

        ButtonHandler(PurchaseStocksFrame app, int action) {
            this.theApp = app;
            this.action = action;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            if (action == 1) {//Update share price button
                getUpdate();

            }
            if (action == 2) {//Purchase shares
                purchaseShare(symbolField.getText(), Integer.parseInt(totalField.getText()), Double.valueOf(currentMoney.getText()));
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
        File file = new File(".//PersonalPortfolios/" + getTheCurrentUser() + ".csv");
        CSVReader reader = new CSVReader(new FileReader(file));
        String[] line = reader.readNext();

        while (line != null) {
            userName = line[0];
            userMoney = line[1];
            totalSpent = line[2];
            theProfit = line[3];
            line = reader.readNext();
        }
        setCurrentMoney(userMoney);
        setTotalSpent(totalSpent);
        setProfit(theProfit);
    }

    public void purchaseShare(String symbol, int numOfShares, double userMoney) {
        System.out.println(userMoney);
        double cost = Double.parseDouble(priceArea.getText());
        System.out.println(cost);
        System.out.println(numOfShares);
        double totalcost = cost * numOfShares;

        int choice = JOptionPane.showConfirmDialog(this,
                "Confirm, You Are Purchasing " + numOfShares + " " + symbol + "\n For: $" + String.format("%.2f", totalcost));
        if (choice == JOptionPane.YES_OPTION) {
            try {
                if (totalcost <= userMoney) {
                    userMoney = userMoney - totalcost;
                    System.out.println(userMoney);
                    setCurrentMoney(String.valueOf(Double.valueOf(String.format("%.2f", userMoney))));
                    System.out.println();
                    errorLabel.setText("<html>Thankyou For Your Transaction!<br>Purchased: "
                            + numOfShares + " " + symbol + " Share(s)</html");
                    this.currentMoney.setText(String.valueOf(String.format("%.2f", userMoney)));
                    System.out.println(userMoney);
                } else {
                    errorLabel.setText("Sorry Unable to Purchase You Have Insufficient Funds");
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
        this.priceArea.setText(String.valueOf(String.format("%.2f", price)));
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
}
