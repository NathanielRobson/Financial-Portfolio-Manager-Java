import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

class GraphBuilderFrame extends JFrame { //Graph Building Frame
    //Main method for Testing
    public static void main(String[] args) { //Test using Main
        new GraphBuilderFrame();
    }

    private JTextField symbolField, fromField, toField, rangeField;
    private JLabel errorLabel;


    public JTextField getSymbolField() {
        return this.symbolField;

    }

    GraphBuilderFrame() { //Frame init and design and layout

        //Custom Fonts for Look and Feel
        Font myFieldFont = new Font("Century Gothic", Font.BOLD, 14);
        Font myTextFont = new Font("Century Gothic", Font.BOLD, 16);
        Font myNextFont = new Font("HelveticaNeue-Light", Font.ITALIC, 15);
        Font myButtonFont = new Font("Tahoma", Font.BOLD, 20);

        //Custom colors for look and feel
        Color myBlueColor = new Color(59, 69, 182);
        Color resetColor = new Color(200, 0, 200);

        //Frame icon
        ImageIcon img = new ImageIcon(".//icons/graphIcon.png");
        setIconImage(img.getImage());

        //symbol label
        JLabel symbolLabel = new JLabel("<html>Symbol or Filename: <html>");
        symbolLabel.setFont(myFieldFont);
        symbolLabel.setForeground(myBlueColor);

        //symbol field
        symbolField = new JTextField(20);
        symbolField.setFont(myFieldFont);

        //days label
        JLabel daysLabel = new JLabel("<html>From - To and Graph Range: </html>");
        daysLabel.setFont(myTextFont);
        daysLabel.setForeground(myBlueColor);

        //from field
        fromField = new JTextField(3);
        fromField.setFont(myFieldFont);

        //range label
        JLabel rangeLabel = new JLabel("Range for Graph");
        rangeLabel.setForeground(myBlueColor);
        rangeLabel.setFont(myNextFont);

        //to field
        toField = new JTextField(3);
        toField.setFont(myFieldFont);

        //range field
        rangeField = new JTextField(3);
        rangeField.setFont(myFieldFont);

        //reset button
        JButton resetBtn = new JButton("Reset");
        //submit button
        JButton submitBtn = new JButton("Submit");

        //welcome label
        JLabel welcomeLabel = new JLabel("");
        welcomeLabel.setForeground(myBlueColor);
        welcomeLabel.setFont(myNextFont);

        //help label
        JLabel helpLabel = new JLabel("<html><font color = purple>Scenario Graph Builder!</font><br/><br/>Please Enter a Company Symbol<br/>" +
                "And Number of Days to Display (1 == Today)(Range Default == 0)<br/><br/>" +
                "<font color = #FF00FF>(1 == Today!)</font><br/>Graph Range = Minimum Y Value (0 for Default!) <br/>" +
                "Input Examples: 30 - 10, 0 For 30 days to 10 days ago<br/>" +
                "Another Example: 100 - 90, 100 For 100 days to 90 days ago<br/>" +
                "<font color = red>Netflix</font> = \"NFLX.CSV\" or \"NFLX\"<br/>" +
                "<font color = green>Microsoft</font> = \"NFLX.CSV\" or \"NFLX\"<br/>" +
                "<font color = #00FFFF>Twitter</font> = \"TWTR.CSV\" or \"TWTR\"<br/>" +
                "<font color = gray>Apple</font> = \"AAPL.CSV\" or \"AAPL\"<br/>" +
                "Facebook = \"FB.CSV\" or \"FB\"</html>");
        helpLabel.setForeground(myBlueColor);
        helpLabel.setFont(myNextFont);

        //error label
        errorLabel = new JLabel();
        errorLabel.setForeground(Color.red);
        errorLabel.setFont(myNextFont);

        //reset button
        resetBtn.setBackground(myBlueColor);
        resetBtn.setForeground(resetColor);
        resetBtn.setFont(myButtonFont);

        //submit button
        submitBtn.setBackground(myBlueColor);
        submitBtn.setForeground(Color.white);
        submitBtn.setFocusPainted(false);
        submitBtn.setFont(myButtonFont);

        //Initialisation of Panels
        JPanel panelOne = new JPanel();
        JPanel panelTwo = new JPanel();
        JPanel panelThree = new JPanel();
        JPanel panelFour = new JPanel();
        JPanel panelFive = new JPanel();
        JPanel panelSix = new JPanel();

        //Adding Objects to Panels
        panelOne.add(symbolLabel);
        panelOne.add(symbolField);
        panelTwo.add(daysLabel);
        panelTwo.add(fromField);
        panelTwo.add(toField);
        panelThree.add(resetBtn);
        panelThree.add(submitBtn);
        panelFour.add(errorLabel);
        panelFive.add(welcomeLabel);
        panelTwo.add(rangeField);
        panelSix.add(helpLabel);

        //Adding Panels to Frame
        add(panelOne);
        add(panelTwo);
        add(panelThree);
        add(panelFour);
        add(panelFive);
        add(panelSix);

        //Frame Constraints
        setLayout(new FlowLayout());
        setTitle("Financial Portfolio Manager Graph Scenario Creator");
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setSize(500, 500);

        //Action listeners for the individual buttons
        resetBtn.addActionListener(new ButtonHandler(this, 1));
        submitBtn.addActionListener(new ButtonHandler(this, 2));
    }

    //Button Handler Class allows for action listeners to be added to each button
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
                        int range = Integer.parseInt(rangeField.getText());

                        if (comp.contains(".CSV") && new File(comp).exists()) { //Loop checks to see if the string .csv was added to input or not, if not it will do it for you
                            stock.drawGraph(comp, from, to, range);

                        } else if (!comp.contains(".CSV")) {
                            newstring = comp + ".CSV";
                            System.out.println(newstring);

                            if (newstring.contains(".CSV") && new File(newstring).exists()) {
                                stock.drawGraph(newstring, from, to, range);

                            } else if (newstring.contains(".CSV") && !new File(newstring).exists()) {
                                getupdate = new CSVUpdateService();
                                String crumb;
                                String crumbinput;

                                if (newstring.contains(".CSV")) {
                                    crumbinput = newstring.substring(range, newstring.length() - 4);
                                    crumb = getupdate.getCrumb(crumbinput);

                                    if (crumb != null && !crumb.isEmpty()) {
                                        int startdate = 0;
                                        System.out.println((String.format("Downloaded data using the symbol '%s'", newstring.toUpperCase())));
                                        getupdate.downloadData(crumbinput, startdate, System.currentTimeMillis(), crumb);
                                        stock.drawGraph(newstring, from, to, range);

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
