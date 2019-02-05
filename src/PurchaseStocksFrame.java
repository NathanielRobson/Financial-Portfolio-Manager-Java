import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PurchaseStocksFrame extends JFrame {

    //Main Method for Testing
    public static void main(String[] args) {
        new PurchaseStocksFrame();

    }

    JLabel symbolLabel;
    JTextField symbolField;
    JButton resetBtn;
    JButton purchaseBtn;
    JButton backBtn;
    JLabel helpLabel;
    JLabel errorLabel;

    PurchaseStocksFrame() {//Frame initialisation, layout and functionality

        setSize(900, 500);

        Font myFieldFont = new Font("Century Gothic", Font.BOLD, 14);
        Font myTextFont = new Font("Century Gothic", Font.BOLD, 16);
        Font myLabelFont = new Font("Century Gothic", Font.BOLD, 18);

        Font myButtonFont = new Font("Tahoma", Font.BOLD, 20);
        Color myBlueColor = new Color(59, 69, 182);
        Color resetColor = new Color(200, 0, 200);

        symbolLabel = new JLabel("Company Symbol: ");
        symbolLabel.setFont(myLabelFont);
        symbolLabel.setForeground(myBlueColor);

        symbolField = new JTextField(20);
        symbolField.setFont(myFieldFont);

        resetBtn = new JButton("Reset");
        purchaseBtn = new JButton("Purchase Stocks");
        backBtn = new JButton("Return to Main Menu");

        helpLabel = new JLabel("<html>Enter Symbol to Purchase Stocks<br/><br/><br/>" +
                "Some Common Symbols Are:<br/><br/>" +
                "Apple Inc: AAPL<br/>" +
                "Amazon:    AMZN<br/>" +
                "Netflix:   NFLX<br/>" +
                "Facebook:  FB<br/>" +
                "Twitter:   TWTR<br/>" +
                "Intel:     INTC<br/>" +
                "Microsoft: MSFT<br/>" +
                "Google:    GOOGL</html>", SwingConstants.CENTER);

        helpLabel.setForeground(myBlueColor);
        helpLabel.setFont(myTextFont);

        errorLabel = new JLabel("<html>Select Purchase...<br/>Only When You are Sure you Would Like to <br/>Purchase Stocks</html>", SwingConstants.TRAILING);
        errorLabel.setForeground(myBlueColor);
        errorLabel.setFont(myFieldFont);

        resetBtn.setBackground(myBlueColor);
        resetBtn.setForeground(resetColor);
        resetBtn.setFont(myButtonFont);

        backBtn.setBackground(myBlueColor);
        backBtn.setForeground(Color.white);
        backBtn.setFont(myButtonFont);

        purchaseBtn.setBackground(myBlueColor);
        purchaseBtn.setForeground(Color.white);
        purchaseBtn.setFont(myButtonFont);

        JPanel panelOne = new JPanel();
        JPanel panelTwo = new JPanel();
        JPanel panelThree = new JPanel();
        JPanel panelFour = new JPanel();
        JPanel panelFive = new JPanel();
        JPanel panelSix = new JPanel();

        panelOne.add(symbolLabel);
        panelOne.add(symbolField);
        panelThree.add(purchaseBtn);
        panelThree.add(resetBtn);
        panelFive.add(helpLabel);
        panelFive.add(errorLabel);
        panelSix.add(backBtn);

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
        resetBtn.addActionListener(new ButtonHandler(this, 1));
        purchaseBtn.addActionListener(new ButtonHandler(this, 2));
        backBtn.addActionListener(new ButtonHandler(this, 3));
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
                purchase();
            }
            if (action == 3) {//Return to main menu
                new MenuFrame();
                PurchaseStocksFrame.this.dispose();
            }
        }
    }
    public void purchase(){//Purchase method
        GetCSVUpdates quoteClass;
        quoteClass = new GetCSVUpdates();
        String symbol = symbolField.getText();
        String crumb = quoteClass.getCrumb(symbol);

        if (crumb != null && !crumb.isEmpty()) {
            errorLabel.setText(String.format("<html>Purchased Stocks using the symbol '%s'<html/>", symbol.toUpperCase()));
            errorLabel.setForeground(Color.red);
            quoteClass.downloadData(symbol, 0, System.currentTimeMillis(), crumb);



        } else {
            errorLabel.setText("Unable to purchase data using the Symbol: " + symbol.toUpperCase());
        }
    }
}
