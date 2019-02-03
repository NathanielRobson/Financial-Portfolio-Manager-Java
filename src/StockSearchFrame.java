import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class StockSearchFrame extends JFrame {

    public static void main(String[] args) {
        new StockSearchFrame();

    }

    JLabel symbolLabel;
    JTextField symbolField;
    JButton resetBtn;
    JButton updateBtn;
    JButton backBtn;
    JLabel helpLabel;
    JLabel errorLabel;

    StockSearchFrame() {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
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
        updateBtn = new JButton("Search Stock Symbol");
        backBtn = new JButton("Return to Main Menu");

        helpLabel = new JLabel("<html>Please Search a Company Stock Symbol Using the Search Bar<br/><br/><br/>" +
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

        errorLabel = new JLabel("<html>Welcome..... Waiting for Input.....</html>", SwingConstants.TRAILING);
        errorLabel.setForeground(myBlueColor);
        errorLabel.setFont(myFieldFont);

        resetBtn.setBackground(myBlueColor);
        resetBtn.setForeground(resetColor);
        resetBtn.setFont(myButtonFont);

        backBtn.setBackground(myBlueColor);
        backBtn.setForeground(Color.white);
        backBtn.setFont(myButtonFont);

        updateBtn.setBackground(myBlueColor);
        updateBtn.setForeground(Color.white);
        updateBtn.setFont(myButtonFont);

        JPanel panelOne = new JPanel();
        JPanel panelTwo = new JPanel();
        JPanel panelThree = new JPanel();
        JPanel panelFour = new JPanel();
        JPanel panelFive = new JPanel();
        JPanel panelSix = new JPanel();

        panelOne.add(symbolLabel);
        panelOne.add(symbolField);
        panelThree.add(updateBtn);
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
        setTitle("Financial Portfolio Manager Stock Search Tool");
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        resetBtn.addActionListener(new ButtonHandler(this, 1));
        updateBtn.addActionListener(new ButtonHandler(this, 2));
        backBtn.addActionListener(new ButtonHandler(this, 3));
    }

    public class ButtonHandler implements ActionListener { //Implements the action listener
        StockSearchFrame theApp;
        int action;
        GetCSVUpdates quoteClass;

        ButtonHandler(StockSearchFrame app, int action) {
            this.theApp = app;
            this.action = action;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            if (action == 1) {
                symbolField.setText("");
                errorLabel.setText("");
            }
            if (action == 2) {
                quoteClass = new GetCSVUpdates();
                String symbol = symbolField.getText();
                String crumb = quoteClass.getCrumb(symbol);
                int startdate = 0;

                if (crumb != null && !crumb.isEmpty()) {
                    errorLabel.setText(String.format("Downloaded data using the symbol '%s'", symbol.toUpperCase()));
                    errorLabel.setForeground(Color.red);
                    quoteClass.downloadData(symbol, startdate, System.currentTimeMillis(), crumb);

                } else {
                    errorLabel.setText("Unable to download data using the Synonym: " + symbol.toUpperCase());
                }
            }
            if (action == 3) {
                new MenuFrame();
                StockSearchFrame.this.dispose();
            }
        }
    }
}
