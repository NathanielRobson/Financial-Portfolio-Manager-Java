import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

class MenuFrame extends JFrame {//Main Menu

    public static void main(String[] args) {
        new MenuFrame("");

    }
    String theCurrentUser;
    JButton searchDate,displayTable,viewGraph,purchaseBtn,logOutBtn,updateBtn,button2,button4;
    JLabel message;

    MenuFrame(String theCurrentUser) {//Frame initialisation and design and layout
        this.theCurrentUser = theCurrentUser;

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        setTitle("Financial Portfolio Manager Main Menu");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(550, 520);
        setLocationRelativeTo(null);

        Font myBtnFont = new Font("Century Gothic", Font.BOLD, 18);
        Font myNextFont = new Font("HelveticaNeue-Light", Font.ITALIC, 18);
        Color myBtnColor = new Color(59, 69, 182);
        Color w = Color.white;

        displayTable = new JButton("<html><font color = #00FF00>Display Table</font> of Shares</html>");
        displayTable.setFont(myBtnFont);
        displayTable.setForeground(w);
        displayTable.setBackground(myBtnColor);

        searchDate = new JButton("<html><font color = orange>Search</font> a <font color = orange>Date</font> to View Share Information</html>");
        searchDate.setFont(myBtnFont);
        searchDate.setForeground(w);
        searchDate.setBackground(myBtnColor);

        viewGraph = new JButton("<html><font color = #00FFFF>View</font> up to Date <font color = #00FFFF>Graphs</font></html>");
        viewGraph.setFont(myBtnFont);
        viewGraph.setForeground(w);
        viewGraph.setBackground(myBtnColor);

        updateBtn = new JButton("<html><font color = #FF00FF>Download</font> up to Date " +
                "<font color = #FF00FF>Shares</font> Information</html>");
        updateBtn.setFont(myBtnFont);
        updateBtn.setForeground(w);
        updateBtn.setBackground(myBtnColor);

        button2 = new JButton("<html>To do</html>");
        button2.setFont(myBtnFont);
        button2.setForeground(w);
        button2.setBackground(myBtnColor);

        purchaseBtn = new JButton("<html><font color = #32CD32>Purchase</font> Shares</html>");
        purchaseBtn.setFont(myBtnFont);
        purchaseBtn.setForeground(w);
        purchaseBtn.setBackground(myBtnColor);

        button4 = new JButton("<html><font color = yellow>View</font> Past <font color = yellow>Investments</font></html>");
        button4.setFont(myBtnFont);
        button4.setForeground(w);
        button4.setBackground(myBtnColor);

        logOutBtn = new JButton("Log out Or Switch User");
        logOutBtn.setFont(myBtnFont);
        logOutBtn.setForeground(Color.magenta);
        logOutBtn.setBackground(myBtnColor);

        message = new JLabel();
        message.setText("<html><font color = #F4A460>Main Menu - </font><br/>'<font color = purple>" + theCurrentUser + "'</font> Please Choose an Option From the List Below<br/></html>");
        message.setFont(myNextFont);
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
        p1.add(purchaseBtn, c);
        p1.add(button4, c);
        p1.add(logOutBtn, c);

        add(p2);
        add(p1);
        add(p3);
        add(p4);
        add(p5);
        setVisible(true);

        //Each button has individual action listener assigned to it
        logOutBtn.addActionListener(new ButtonHandler(this, 1));
        displayTable.addActionListener(new ButtonHandler(this, 2));
        searchDate.addActionListener(new ButtonHandler(this, 3));
        updateBtn.addActionListener(new ButtonHandler(this, 4));
        viewGraph.addActionListener(new ButtonHandler(this, 5));
        purchaseBtn.addActionListener(new ButtonHandler(this, 6));
    }

    class ButtonHandler implements ActionListener { //Implements the action listener
        MenuFrame theApp;
        int action;
        CSVUpdateService getupdate;
        GraphBuilderFrame builder;
        MenuFrame menuFrame;
        LoginFrame loginFrame;

        ButtonHandler(MenuFrame app, int action) {
            this.theApp = app;
            this.action = action;


        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (action == 2) { //Display table of selected company and download new data if the file doesnt exist
                displayTable();
            }
            if (action == 1) {
                new LoginFrame();
                MenuFrame.this.dispose();
            }
            if (action == 3) {//Search a selected date for a selected company
                searchDateCompany();
            }
            if (action == 4) { //Search Stock Company and Download Data Dedicated frame for functionality
                new CSVUpdateFrame();
            }
            if (action == 5) {//Graph of selected company and selected time period
                builder = new GraphBuilderFrame();
            }
            if (action == 6) {//Purchase Stocks from companies most recent value

                new PurchaseStocksFrame(theApp.theCurrentUser);

            }
            if (action == 7) {//View Personal Investments in Companies
                //This should check the most recent price of the company of choice and then store that data e.g $130.50 per share
                //Times it by how many shares the user wished to purchase e.g $130.50 x 100
                //Provide total cost and then over time show increase or decrease of investment
                //If share decreases to $125.00 then provide accurate feedback regarding total loss 100 x 130.50 - 100 x 125.00 (difference == loss)
            }
        }

        public void searchDateCompany() {
            String name = "";
            String SearchDate = "";
            name = JOptionPane.showInputDialog("Please Enter the Symbol or Filename of The Company");
            SearchDate = JOptionPane.showInputDialog("Enter the Date Like So -> YYYY-MM-DD");
            String newname;

            try {
                name = name.toUpperCase();
                if (name.contains(".CSV") && new File(name).exists()) { //If input does not contain .csv then it is added to allow for correct file extension
                    SearchandViewService.readFile(name, SearchDate);

                } else if (!name.contains(".CSV")) {
                    newname = name + ".CSV";

                    if (newname.contains(".CSV") && new File(newname).exists()) {
                        SearchandViewService.readFile(newname, SearchDate);

                    } else if (newname.contains(".CSV") && !new File(newname).exists()) {//If file doesnt exist then downloads newest data for selected company from internet
                        getupdate = new CSVUpdateService();
                        String crumb;
                        String crumbinput;

                        if (newname.contains(".CSV")) {
                            crumbinput = newname.substring(0, newname.length() - 4);
                            crumb = getupdate.getCrumb(crumbinput);

                            if (crumb != null && !crumb.isEmpty()) {
                                getupdate.downloadData(crumbinput, 0, System.currentTimeMillis(), crumb);
                                SearchandViewService.readFile(newname, SearchDate);

                            } else {
                                System.out.println("Unable to view data using the Symbol: " + crumbinput.toUpperCase());
                                JOptionPane.showMessageDialog(null, "Unable to Show Selected Data, Symbol May Not Exist");
                            }
                        }
                    }
                } else {
                    System.out.println("Unable to view table data using the Symbol: " + name + "  Please Ensure that .csv is added and the company symbol exists on the web");
                    JOptionPane.showMessageDialog(null, "Unable to show selected data");
                }
            } catch (Exception el) {
                JOptionPane.showMessageDialog(null, "You have clicked Cancel");
            }
        }

        public void displayTable() {
            String input = "";
            String newinput = "";
            input = JOptionPane.showInputDialog("Please Input a Filename or a Company Symbol");

            try {
                input = input.toUpperCase();
                if (input.contains(".CSV") && new File(input).exists()) {
                    DisplayTableService.ViewTable(input);

                } else if (!input.contains(".CSV")) {
                    newinput = input + ".CSV";

                    if (newinput.contains(".CSV") && new File(newinput).exists()) {
                        DisplayTableService.ViewTable(newinput);

                    } else if (newinput.contains(".CSV") && !new File(newinput).exists()) {
                        getupdate = new CSVUpdateService();
                        String crumb;
                        String crumbinput;

                        if (newinput.contains(".CSV")) {
                            crumbinput = newinput.substring(0, newinput.length() - 4);
                            crumb = getupdate.getCrumb(crumbinput);

                            if (crumb != null && !crumb.isEmpty()) {
                                int startdate = 0;
                                System.out.println((String.format("Downloaded data using the symbol '%s'", newinput.toUpperCase())));
                                getupdate.downloadData(crumbinput, startdate, System.currentTimeMillis(), crumb);
                                DisplayTableService.ViewTable(newinput);

                            } else {
                                System.out.println("Unable to download data using the Symbol: " + crumbinput.toUpperCase());
                            }
                        }
                    }
                } else {
                    System.out.println("Unable to download data using the Symbol: " + input.toUpperCase() + "  Please Ensure that .csv is added and the company symbol exists on the web");
                }
            } catch (Exception el) {
                JOptionPane.showMessageDialog(null, "Cancelled");
            }
        }
    }

    public String getTheCurrentUser() {
        return theCurrentUser;
    }

    public void setCurrentUser(String theNewCurrentUser) {
        this.theCurrentUser = theNewCurrentUser;
    }
}
