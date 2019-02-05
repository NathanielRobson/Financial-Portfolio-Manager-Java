import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

class GraphBuilderFrame extends JFrame {

    public static void main(String[] args) { //Test using Main

        new GraphBuilderFrame();
    }

    JLabel symbolLabel;
    JTextField symbolField;
    JLabel daysLabel;
    JTextField daysField;
    JButton resetBtn;
    JButton submitBtn;
    JLabel welcomeLabel;
    JLabel helpLabel;
    JLabel errorLabel;

    GraphBuilderFrame() { //Frame init and design and layout

        setSize(500, 400);

        Font myFieldFont = new Font("Century Gothic", Font.BOLD, 14);
        Font myTextFont = new Font("Century Gothic", Font.BOLD, 16);
        Font myLabelFont = new Font("Century Gothic", Font.BOLD, 18);

        Font myButtonFont = new Font("Tahoma", Font.BOLD, 20);
        Color myBlueColor = new Color(59, 69, 182);
        Color resetColor = new Color(200, 0, 200);

        symbolLabel = new JLabel("Company Symbol/Filename: ");
        symbolLabel.setFont(myFieldFont);
        symbolLabel.setForeground(myBlueColor);

        symbolField = new JTextField(20);
        symbolField.setFont(myFieldFont);

        daysLabel = new JLabel("Number of Days to Display: ");
        daysLabel.setFont(myTextFont);
        daysLabel.setForeground(myBlueColor);

        daysField = new JTextField(2);
        daysField.setFont(myFieldFont);

        resetBtn = new JButton("Reset");
        submitBtn = new JButton("Submit");

        welcomeLabel = new JLabel("");
        welcomeLabel.setForeground(myBlueColor);
        welcomeLabel.setFont(myTextFont);

        helpLabel = new JLabel("<html>Please Enter a Company Symbol<br/><br/> And Number of Days to Display.<br/><br/>" +
                "Some Examples: <br/><br/>" +
                "Netflix - 20 days = \"NFLX.CSV\" - 20<br/>" +
                "Microsoft - 5 days = \"NFLX.CSV\" - 5<br/>" +
                "Twitter - 40 days = \"TWTR.CSV\" - 40</html>");
        helpLabel.setForeground(myBlueColor);
        helpLabel.setFont(myLabelFont);

        errorLabel = new JLabel();
        errorLabel.setForeground(Color.red);
        errorLabel.setFont(myFieldFont);

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
        panelTwo.add(daysField);
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
        StockGraph stock;
        GetCSVUpdates getupdate;

        ButtonHandler(GraphBuilderFrame app, int action) {
            this.theApp = app;
            this.action = action;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (action == 1) { //reset button
                symbolField.setText("");
                daysField.setText("");
                errorLabel.setText("");
            }
            if (action == 2) { //get updates by downloading the most recent file online
                stock = new StockGraph();
                getupdate = new GetCSVUpdates();
                String newstring;
                String comp = symbolField.getText().toUpperCase();
                int days = Integer.parseInt(daysField.getText());

                if (comp.contains(".CSV") && new File(comp).exists()) { //Loop checks to see if the string .csv was added to input or not, if not it will do it for you
                    stock.drawGraph(comp, days);

                } else if (!comp.contains(".CSV")) {
                    newstring = comp + ".CSV";
                    System.out.println(newstring);

                    if (newstring.contains(".CSV") && new File(newstring).exists()) {
                        stock.drawGraph(newstring, days);

                    } else if (newstring.contains(".CSV") && !new File(newstring).exists()) {
                        getupdate = new GetCSVUpdates();
                        String crumb;
                        String crumbinput;

                        if (newstring.contains(".CSV")) {
                            crumbinput = newstring.substring(0, newstring.length() - 4);
                            crumb = getupdate.getCrumb(crumbinput);

                            if (crumb != null && !crumb.isEmpty()) {
                                int startdate = 0;
                                System.out.println((String.format("Downloaded data using the symbol '%s'", newstring.toUpperCase())));
                                getupdate.downloadData(crumbinput, startdate, System.currentTimeMillis(), crumb);
                                stock.drawGraph(newstring, days);

                            } else {
                                System.out.println(("Unable to download data using the Symbol: " + crumbinput.toUpperCase()));
                            }
                        }
                    }
                } else {
                    System.out.println(("Unable to download data using the Symbol: " + comp.toUpperCase() + "  Please Ensure that .csv is added and the company symbol exists"));
                }
            }
        }
    }
}
