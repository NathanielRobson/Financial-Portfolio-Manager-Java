import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

class GraphBuilderFrame extends JFrame {

    public static void main(String[] args) { //Test using Main

        new GraphBuilderFrame();
    }

    JLabel symbolLabel, daysLabel;
    JTextField symbolField, fromField, toField;
    JButton resetBtn, submitBtn;
    JLabel welcomeLabel, helpLabel, errorLabel;

    GraphBuilderFrame() { //Frame init and design and layout

        setSize(500, 500);

        Font myFieldFont = new Font("Century Gothic", Font.BOLD, 14);
        Font myTextFont = new Font("Century Gothic", Font.BOLD, 16);
        Font myNextFont = new Font("HelveticaNeue-Light", Font.ITALIC, 15);

        Font myButtonFont = new Font("Tahoma", Font.BOLD, 20);
        Color myBlueColor = new Color(59, 69, 182);
        Color resetColor = new Color(200, 0, 200);

        symbolLabel = new JLabel("<html>Symbol or Filename: <html>");
        symbolLabel.setFont(myFieldFont);
        symbolLabel.setForeground(myBlueColor);

        symbolField = new JTextField(20);
        symbolField.setFont(myFieldFont);

        daysLabel = new JLabel("<html>From - To: </html>");
        daysLabel.setFont(myTextFont);
        daysLabel.setForeground(myBlueColor);

        fromField = new JTextField(3);
        fromField.setFont(myFieldFont);

        toField = new JTextField(3);
        toField.setFont(myFieldFont);

        resetBtn = new JButton("Reset");
        submitBtn = new JButton("Submit");

        welcomeLabel = new JLabel("");
        welcomeLabel.setForeground(myBlueColor);
        welcomeLabel.setFont(myNextFont);

        helpLabel = new JLabel("<html><font color = purple>Scenario Graph Builder!</font><br/><br/>Please Enter a Company Symbol<br/> And Number of Days to Display (1 == Today)<br/><br/>" +
                "<font color = #FF00FF>(1 == Today!)</font><br/>Some Examples: <br/>" +
                "<font color = red>Netflix</font> = \"NFLX.CSV\" or \"NFLX\"<br/>" +
                "<font color = green>Microsoft</font> = \"NFLX.CSV\" or \"NFLX\"<br/>" +
                "<font color = #00FFFF>Twitter</font> = \"TWTR.CSV\" or \"TWTR\"<br/>" +
                "<font color = gray>Apple</font> = \"AAPL.CSV\" or \"AAPL\"<br/>" +
                "Facebook = \"FB.CSV\" or \"FB\"</html>");
        helpLabel.setForeground(myBlueColor);
        helpLabel.setFont(myNextFont);

        errorLabel = new JLabel();
        errorLabel.setForeground(Color.red);
        errorLabel.setFont(myNextFont);

        resetBtn.setBackground(myBlueColor);
        resetBtn.setForeground(resetColor);
        resetBtn.setFont(myButtonFont);

        submitBtn.setBackground(myBlueColor);
        submitBtn.setForeground(Color.white);
        submitBtn.setFocusPainted(false);
        submitBtn.setFont(myButtonFont);

        JPanel panelOne = new JPanel();
        JPanel panelTwo = new JPanel();
        JPanel panelThree = new JPanel();
        JPanel panelFour = new JPanel();
        JPanel panelFive = new JPanel();
        JPanel panelSix = new JPanel();

        panelOne.add(symbolLabel);
        panelOne.add(symbolField);
        panelTwo.add(daysLabel);
        panelTwo.add(fromField);
        panelTwo.add(toField);
        panelThree.add(resetBtn);
        panelThree.add(submitBtn);
        panelFive.add(welcomeLabel);
        panelSix.add(helpLabel);
        panelFour.add(errorLabel);

        add(panelOne);
        add(panelTwo);
        add(panelThree);
        add(panelFour);
        add(panelFive);
        add(panelSix);

        setLayout(new FlowLayout());
        setTitle("Financial Portfolio Manager Graph Builder");
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        //Action listeners for the individual buttons
        resetBtn.addActionListener(new ButtonHandler(this, 1));
        submitBtn.addActionListener(new ButtonHandler(this, 2));
    }

    public class ButtonHandler implements ActionListener { //Implements the action listener
        GraphBuilderFrame theApp;
        int action;
        StockGraphService stock;
        CSVUpdateService getupdate;

        ButtonHandler(GraphBuilderFrame app, int action) {
            this.theApp = app;
            this.action = action;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (action == 1) { //reset button
                symbolField.setText("");
                fromField.setText("");
                toField.setText("");
                errorLabel.setText("");
            }
            if (action == 2) { //get updates by downloading the most recent file online
                stock = new StockGraphService();
                getupdate = new CSVUpdateService();
                String newstring;

                if (!((fromField.getText().trim().toUpperCase().equals("")
                        || toField.getText().trim().toUpperCase().equals("")
                        || symbolField.getText().trim().toUpperCase().equals("")))) {
                    try {
                        String comp = symbolField.getText().toUpperCase();
                        int from = Integer.parseInt(fromField.getText());
                        int to = Integer.parseInt(toField.getText());

                        if (comp.contains(".CSV") && new File(comp).exists()) { //Loop checks to see if the string .csv was added to input or not, if not it will do it for you
                            stock.drawGraph(comp, from, to);

                        } else if (!comp.contains(".CSV")) {
                            newstring = comp + ".CSV";
                            System.out.println(newstring);

                            if (newstring.contains(".CSV") && new File(newstring).exists()) {
                                stock.drawGraph(newstring, from, to);

                            } else if (newstring.contains(".CSV") && !new File(newstring).exists()) {
                                getupdate = new CSVUpdateService();
                                String crumb;
                                String crumbinput;

                                if (newstring.contains(".CSV")) {
                                    crumbinput = newstring.substring(0, newstring.length() - 4);
                                    crumb = getupdate.getCrumb(crumbinput);

                                    if (crumb != null && !crumb.isEmpty()) {
                                        int startdate = 0;
                                        System.out.println((String.format("Downloaded data using the symbol '%s'", newstring.toUpperCase())));
                                        getupdate.downloadData(crumbinput, startdate, System.currentTimeMillis(), crumb);
                                        stock.drawGraph(newstring, from, to);

                                    } else {
                                        System.out.println(("Unable to download data using the Symbol: " + crumbinput.toUpperCase()));
                                    }
                                }
                            }
                        } else {
                            errorLabel.setText(("Unable to download data using the Symbol: " + comp.toUpperCase() + "  Please Ensure that .csv is added and the company symbol exists"));
                        }
                    } catch (Exception el) {
                        errorLabel.setText("<html>Please Ensure 'to' and 'from' are not '0' </br>" +
                                "And Only numbers in days fields</html>");
                    }
                } else {
                    errorLabel.setText("Error One of the fields is empty");
                }
            }

        }
    }
}
