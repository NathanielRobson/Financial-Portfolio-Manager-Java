import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CSVUpdateFrame extends JFrame { //CSV Update Frame
    public static void main(String[] args) { //Main method for testing
        new CSVUpdateFrame();

    }

    private JTextField symbolField;
    private JLabel errorLabel;

    public CSVUpdateFrame() {//Frame initialisation and layout functionality

        //Custom fonts and colors for look and feel
        Font myFieldFont = new Font("Century Gothic", Font.BOLD, 14);
        Font myFieldFont2 = new Font("Century Gothic", Font.BOLD, 12);
        Font myNextFont = new Font("HelveticaNeue-Light", Font.ITALIC, 17);
        Color myBlueColor = new Color(59, 69, 182);
        Color resetColor = new Color(200, 147, 183);

        JLabel symbolLabel = new JLabel("<html>Company Symbol: <html>");
        symbolLabel.setFont(myFieldFont);
        symbolLabel.setForeground(myBlueColor);

        symbolField = new JTextField(20);
        symbolField.setFont(myFieldFont2);

        JLabel helpLabel = new JLabel("<html><font color = purple>Download Newest Data</font><br/>Please Enter a Company Symbol<br/> To Download The Most Recent Data Some Examples: <br/>" +
                "<font color = red>Netflix</font> = \"NFLX\"<br/>" +
                "<font color = green>Microsoft</font> = \"MSFT\"<br/>" +
                "<font color = #00FFFF>Twitter</font> = \"TWTR\"<br/>" +
                "<font color = gray>Apple</font> = \"AAPL\"<br/>" +
                "Facebook = \"FB\"</html>");

        helpLabel.setForeground(myBlueColor);
        helpLabel.setFont(myNextFont);

        errorLabel = new JLabel("");
        errorLabel.setForeground(Color.red);
        errorLabel.setFont(myFieldFont);

        JButton resetBtn = new JButton("Reset");
        resetBtn.setBackground(myBlueColor);
        resetBtn.setForeground(resetColor);
        resetBtn.setFont(myNextFont);

        JButton submitBtn = new JButton("Download Data");
        submitBtn.setBackground(myBlueColor);
        submitBtn.setForeground(Color.white);
        submitBtn.setFont(myNextFont);

        //Panel initialisation
        JPanel panelOne = new JPanel();
        JPanel panelTwo = new JPanel();
        JPanel panelThree = new JPanel();
        JPanel panelFour = new JPanel();
        JPanel panelFive = new JPanel();
        JPanel panelSix = new JPanel();

        //Adds objects to panels
        panelOne.add(symbolLabel);
        panelOne.add(symbolField);
        panelTwo.add(submitBtn);
        panelTwo.add(resetBtn);
        panelThree.add(helpLabel);
        panelFour.add(errorLabel);

        //Adds panels to frame
        add(panelOne);
        add(panelTwo);
        add(panelThree);
        add(panelFour);
        add(panelFive);
        add(panelSix);

        //Frame Constraints
        setLayout(new FlowLayout());
        setTitle("Financial Portfolio Manager Download Up To Date Company CSV");
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setSize(450, 380);

        //Each button with individual action listener
        resetBtn.addActionListener(new ButtonHandler(this, 1));
        submitBtn.addActionListener(new ButtonHandler(this, 2));
    }

    public class ButtonHandler implements ActionListener { //Implements the action listener
        CSVUpdateFrame theApp;
        int action;

        ButtonHandler(CSVUpdateFrame app, int action) {
            this.theApp = app;
            this.action = action;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            if (action == 1) {//Reset button
                symbolField.setText("");
                errorLabel.setText("");
            }
            if (action == 2) {//Download data button calls download method
                getUpdate();
            }
        }
    }

    private void getUpdate() {//Downloads Up to Date CSV method
        CSVUpdateService quoteClass;
        quoteClass = new CSVUpdateService();

        try {
            String symbol = symbolField.getText();
            String crumb = quoteClass.getCrumb(symbol);

            if (crumb != null && !crumb.isEmpty()) {
                errorLabel.setText(String.format("<html>Downloaded Data Using the Symbol '%s'<br/>" +
                        "Check Root File Directories For Updated '%s' File</html>", symbol.toUpperCase(), symbol.toUpperCase()));
                errorLabel.setForeground(Color.red);
                quoteClass.downloadData(symbol, 0, System.currentTimeMillis(), crumb);

            } else {
                errorLabel.setText("Unable to update price using the Symbol: " + symbol.toUpperCase());
            }
        } catch (Exception el) {
            errorLabel.setText("Unable to update, error occurred");
        }
    }
}
