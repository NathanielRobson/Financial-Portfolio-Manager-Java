import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

class MenuFrame extends JFrame {

    public static void main(String[] args) {
        new MenuFrame();
    }

    JButton searchDate;
    JButton displayTable;
    JButton viewGraph;
    JButton button2;
    JButton button3;
    JButton button4;
    JButton logOutBtn;
    JButton exitBtn;
    JButton updateBtn;
    JLabel message;

    MenuFrame() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        setTitle("Financial Portfolio Manager Main Menu");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 600);
        Font myBtnFont = new Font("Century Gothic", Font.BOLD, 18);
        Color myBtnColor = new Color(59, 69, 182);
        Color w = Color.white;

        displayTable = new JButton("Display Table of Shares");
        displayTable.setFont(myBtnFont);
        displayTable.setForeground(w);
        displayTable.setBackground(myBtnColor);

        searchDate = new JButton("Search a Date to View Share Information(In Progress)");
        searchDate.setFont(myBtnFont);
        searchDate.setForeground(w);
        searchDate.setBackground(myBtnColor);

        viewGraph = new JButton("View up to Date Graphs");
        viewGraph.setFont(myBtnFont);
        viewGraph.setForeground(w);
        viewGraph.setBackground(myBtnColor);

        updateBtn = new JButton("Click Here to Get Up to Date Shares Information");
        updateBtn.setFont(myBtnFont);
        updateBtn.setForeground(w);
        updateBtn.setBackground(myBtnColor);

        button2 = new JButton("Scenarios (IEX API - Callum)(TODO)");
        button2.setFont(myBtnFont);
        button2.setForeground(w);
        button2.setBackground(myBtnColor);

        button3 = new JButton("Purchase Shares(Input Personal CSV - Kay)(TODO)");
        button3.setFont(myBtnFont);
        button3.setForeground(w);
        button3.setBackground(myBtnColor);

        button4 = new JButton("View Past investments(History CSV Viewer - Elios)(TODO)");
        button4.setFont(myBtnFont);
        button4.setForeground(w);
        button4.setBackground(myBtnColor);

        logOutBtn = new JButton("Log Out or Change User");
        logOutBtn.setFont(myBtnFont);
        logOutBtn.setForeground(Color.magenta);
        logOutBtn.setBackground(myBtnColor);

        exitBtn = new JButton("Exit");
        exitBtn.setFont(myBtnFont);
        exitBtn.setForeground(new Color(200, 0, 200));
        exitBtn.setBackground(myBtnColor);

        message = new JLabel();
        message.setText("<html>Main Menu - Please Choose an Option From the List Below<br/><br/><br/><br/><br/></html>");
        message.setFont(new Font("Tahoma", Font.BOLD, 18));
        message.setForeground(myBtnColor);

        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        JPanel p4 = new JPanel();
        JPanel p5 = new JPanel();
        p1.setLayout(new GridBagLayout());

        c.weightx = 1;
        c.weighty = .25;
        c.insets = new Insets(0, 0, 15, 0);
        c.gridheight = 10;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.BOTH;

        p1.add(message, c);
        p1.add(updateBtn, c);
        p1.add(searchDate, c);
        p1.add(displayTable, c);
        p1.add(viewGraph, c);
        p1.add(button2, c);
        p1.add(button3, c);
        p1.add(button4, c);
        p1.add(logOutBtn, c);

        add(p2);
        add(p1);
        add(p3);
        add(p4);
        add(p5);
        setVisible(true);

        logOutBtn.addActionListener(new ButtonHandler(this, 1));
        displayTable.addActionListener(new ButtonHandler(this, 2));
        searchDate.addActionListener(new ButtonHandler(this, 3));
        updateBtn.addActionListener(new ButtonHandler(this, 4));
        viewGraph.addActionListener(new ButtonHandler(this, 5));
    }

    class ButtonHandler implements ActionListener { //Implements the action listener
        MenuFrame theApp;
        int action;
        GetCSVUpdates getupdate;
        GraphBuilderFrame builder;

        ButtonHandler(MenuFrame app, int action) {
            this.theApp = app;
            this.action = action;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (action == 2) {
                String input;
                String newinput;
                input = JOptionPane.showInputDialog("Please Input a Filename or a Company Symbol").toUpperCase();

                if (input.contains(".CSV") && new File(input).exists()) {
                    DisplayTable.ViewTable(input);

                } else if (!input.contains(".CSV")) {
                    newinput = input + ".CSV";

                    if (newinput.contains(".CSV") && new File(newinput).exists()) {
                        DisplayTable.ViewTable(newinput);

                    } else if (newinput.contains(".CSV") && !new File(newinput).exists()) {
                        getupdate = new GetCSVUpdates();
                        String crumb;
                        String crumbinput;

                        if (newinput.contains(".CSV")) {
                            crumbinput = newinput.substring(0, newinput.length() - 4);
                            crumb = getupdate.getCrumb(crumbinput);

                            if (crumb != null && !crumb.isEmpty()) {
                                int startdate = 0;
                                System.out.println((String.format("Downloaded data using the symbol '%s'", newinput.toUpperCase())));
                                getupdate.downloadData(crumbinput, startdate, System.currentTimeMillis(), crumb);
                                DisplayTable.ViewTable(newinput);

                            } else {
                                System.out.println(("Unable to download data using the Synonym: " + crumbinput.toUpperCase()));
                            }
                        }
                    }
                } else {
                    System.out.println(("Unable to download data using the Synonym: " + input.toUpperCase() + "  Please Ensure that .csv is added and the company symbol exists on the web"));
                }
            }
            if (action == 1) {
                new LoginFrame();
                MenuFrame.this.dispose();
            }
            if (action == 3) {
                String Search = JOptionPane.showInputDialog("Please enter the date you wish to display (DD/MM/YYYY): ");
                SearchandView.readFile(Search, "Test.csv");
            }
            if (action == 4) {
                new StockSearchFrame();
                MenuFrame.this.dispose();
            }
            if (action == 5) {
                builder = new GraphBuilderFrame();
            }
        }
    }
}