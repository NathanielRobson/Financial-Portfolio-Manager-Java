import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class CSVUpdateFrame extends JFrame {
    public static void main(String[] args) { //Main method for testing
        new CSVUpdateFrame();

    }

    public String theCurrentUser;
    JLabel symbolLabel;
    JTextField symbolField;
    JButton resetBtn,submitBtn;
    JLabel helpLabel,errorLabel;

    CSVUpdateFrame() {//Frame initialisation and layout functionality
        setSize(450, 350);

        Font myFieldFont = new Font("Century Gothic", Font.BOLD, 14);
        Font myFieldFont2 = new Font("Century Gothic", Font.BOLD, 12);
        Font myNextFont = new Font("HelveticaNeue-Light", Font.ITALIC, 17);
        Color myBlueColor = new Color(59, 69, 182);
        Color resetColor = new Color(200, 147, 183);

        symbolLabel = new JLabel("<html>Company Symbol: <html>");
        symbolLabel.setFont(myFieldFont);
        symbolLabel.setForeground(myBlueColor);

        symbolField = new JTextField(20);
        symbolField.setFont(myFieldFont2);


        helpLabel = new JLabel("<html><font color = purple>Download Newest Data</font><br/>Please Enter a Company Symbol<br/> To Download The Most Recent Data Some Examples: <br/>" +
                "<font color = red>Netflix</font> = \"NFLX.CSV\" or \"NFLX\"<br/>" +
                "<font color = green>Microsoft</font> = \"NFLX.CSV\" or \"NFLX\"<br/>" +
                "<font color = #00FFFF>Twitter</font> = \"TWTR.CSV\" or \"TWTR\"<br/>" +
                "<font color = gray>Apple</font> = \"AAPL.CSV\" or \"AAPL\"<br/>" +
                "Facebook = \"FB.CSV\" or \"FB\"</html>");

        helpLabel.setForeground(myBlueColor);
        helpLabel.setFont(myNextFont);

        errorLabel = new JLabel("");
        errorLabel.setForeground(Color.red);
        errorLabel.setFont(myFieldFont);

        resetBtn = new JButton("Reset");
        resetBtn.setBackground(myBlueColor);
        resetBtn.setForeground(resetColor);
        resetBtn.setFont(myNextFont);

        submitBtn = new JButton("Download Data");
        submitBtn.setBackground(myBlueColor);
        submitBtn.setForeground(Color.white);
        submitBtn.setFont(myNextFont);

        JPanel panelOne = new JPanel();
        JPanel panelTwo = new JPanel();
        JPanel panelThree = new JPanel();
        JPanel panelFour = new JPanel();
        JPanel panelFive = new JPanel();
        JPanel panelSix = new JPanel();

        panelOne.add(symbolLabel);
        panelOne.add(symbolField);
        panelTwo.add(submitBtn);
        panelTwo.add(resetBtn);
        panelThree.add(helpLabel);
        panelFour.add(errorLabel);

        add(panelOne);
        add(panelTwo);
        add(panelThree);
        add(panelFour);
        add(panelFive);
        add(panelSix);

        setLayout(new FlowLayout());
        setTitle("Financial Portfolio Manager CSV Updater");
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

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
                download();
            }
            if (action == 3) {//Return to main menu button, close current frame
                new MenuFrame(theCurrentUser);
                CSVUpdateFrame.this.dispose();
            }
        }
    }

    public void download() { //download method calls CSVUpdateService class and downloads newest csv and shares information to csv in root folder
        CSVUpdateService quoteClass;
        quoteClass = new CSVUpdateService();
        String symbol = symbolField.getText();
        String crumb = quoteClass.getCrumb(symbol);
        int startdate = 0;

        if (crumb != null && !crumb.isEmpty()) {
            errorLabel.setText(String.format("Downloaded Data Using the Symbol '%s'", symbol.toUpperCase()));
            errorLabel.setForeground(Color.red);
            quoteClass.downloadData(symbol, startdate, System.currentTimeMillis(), crumb);

        } else {
            errorLabel.setText("Unable to Download Data Using the Symbol: " + symbol.toUpperCase());
        }
    }

    public void setTheCurrentUser(String theNewCurrentUser) {
        this.theCurrentUser = theNewCurrentUser;
    }

    public String getTheCurrentUser() {
        return theCurrentUser;
    }

    public void getRecentPrice(){

    }

}
