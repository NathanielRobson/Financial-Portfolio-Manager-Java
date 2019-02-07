import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

class PurchaseStocksFrame extends JFrame {

    //Main Method for Testing
    String theCurrentUser;

    JLabel userLabel;
    JLabel userMoney;
    JLabel symbolLabel;
    JLabel totalLabel;
    JLabel priceLabel;
    JTextField symbolField;
    JTextField totalField;
    JTextArea priceArea;
    JButton getPriceBtn;
    JButton purchaseBtn;
    JLabel helpLabel;
    JLabel errorLabel;

    PurchaseStocksFrame(String theCurrentUser) {//Frame initialisation, layout and functionality
        this.theCurrentUser = theCurrentUser;
        try {
            readPersonalPortfolio();

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

        userMoney = new JLabel("Current Money: ");
        userMoney.setFont(myNextFont);
        userMoney.setForeground(Color.BLACK);

        symbolLabel = new JLabel("Company Symbol: ");
        symbolLabel.setFont(myNextFont);
        symbolLabel.setForeground(myBlueColor);

        symbolField = new JTextField(20);
        symbolField.setFont(myFieldFont);

        priceLabel = new JLabel("Current Share Price");
        priceLabel.setFont(myNextFont);
        priceLabel.setForeground(priceColor);

        priceArea = new JTextArea();
        priceArea.setColumns(3);
        priceArea.setEditable(false);

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
        userPanel.add(userMoney);
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

            if (action == 1) { //Reset button
                symbolField.setText("");
                errorLabel.setText("");
            }
            if (action == 2) {//Purchase shares button
                getUpdate();

            }
            if (action == 3) {//Return to main menu
                new MenuFrame(theCurrentUser);
                PurchaseStocksFrame.this.dispose();
            }
        }
    }

    public void getUpdate() {//Purchase method
        GetCSVUpdates quoteClass;
        quoteClass = new GetCSVUpdates();
        try {
            String symbol = symbolField.getText();
            String crumb = quoteClass.getCrumb(symbol);

            if (crumb != null && !crumb.isEmpty()) {
                errorLabel.setText(String.format("<html>Updated Price using the symbol '%s'<html/>", symbol.toUpperCase()));
                errorLabel.setForeground(Color.red);
                quoteClass.downloadData(symbol, 0, System.currentTimeMillis(), crumb);


            } else {
                errorLabel.setText("Unable to update price using the Symbol: " + symbol.toUpperCase());
            }
        } catch (Exception el) {
            errorLabel.setText("Unable to update, error occurred");
        }
    }

    public void readPersonalPortfolio() throws IOException {
        File file = new File(".//PersonalPortfolios/" + getTheCurrentUser() + ".csv");
        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        while ((st = br.readLine()) != null)
            System.out.println(st);
    }

    public String getTheCurrentUser() {
        return theCurrentUser;

    }
}
