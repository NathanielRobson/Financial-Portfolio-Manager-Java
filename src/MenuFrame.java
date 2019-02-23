import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

class MenuFrame extends JFrame {//Main Menu Frame

    public static void main(String[] args) {
        new MenuFrame("NR");
    }

    //The Current User Logged In
    private String theCurrentUser;

    MenuFrame(String theCurrentUser) {//Frame initialisation and design and layout
        this.theCurrentUser = theCurrentUser;

        //Frame Constraints, Layout and Settings
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        setTitle("Financial Portfolio Manager Main Menu");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(550, 600);
        setLocationRelativeTo(null);

        //Fonts and Colors UI/UX Customisation
        Font myBtnFont = new Font("Century Gothic", Font.BOLD, 18);
        Font myNextFont = new Font("HelveticaNeue-Light", Font.ITALIC, 18);
        Color myBtnColor = new Color(59, 69, 182);
        Color w = Color.white;

        //Frame icons
        ImageIcon img = new ImageIcon(".//icons/frameIcon.png");
        setIconImage(img.getImage());

        //Each Button on Menu Frame
        JButton displayTable = new JButton("<html><font color = #00FF00>Display Table</font> of Shares</html>");
        displayTable.setFont(myBtnFont);
        displayTable.setForeground(w);
        displayTable.setBackground(myBtnColor);

        JButton searchDate = new JButton("<html><font color = orange>Search</font> a <font color = orange>" +
                "Date</font> - View Company Share Data</html>");
        searchDate.setFont(myBtnFont);
        searchDate.setForeground(w);
        searchDate.setBackground(myBtnColor);

        JButton viewGraph = new JButton("<html><font color = #00FFFF>View</font> up to Date <font color = #00FFFF>" +
                "Company Graphs</font></html>");
        viewGraph.setFont(myBtnFont);
        viewGraph.setForeground(w);
        viewGraph.setBackground(myBtnColor);

        JButton updateBtn = new JButton("<html><font color = #FF00FF>Download</font> up to Date " +
                "<font color = #FF00FF>Shares</font> Information</html>");
        updateBtn.setFont(myBtnFont);
        updateBtn.setForeground(w);
        updateBtn.setBackground(myBtnColor);

        JButton bankBtn = new JButton("<html><font color = #79FF27>Personal Bank Account</font> Deposit/Withdraw Money</html>");
        bankBtn.setFont(myBtnFont);
        bankBtn.setForeground(w);
        bankBtn.setBackground(myBtnColor);

        JButton purchaseBtn = new JButton("<html><font color = #32CD32>Purchase or Sell</font> Shares</html>");
        purchaseBtn.setFont(myBtnFont);
        purchaseBtn.setForeground(w);
        purchaseBtn.setBackground(myBtnColor);

        JButton addSharesBtn = new JButton("<html><font color = yellow>Add</font> <font color = yellow>Already Owned " +
                "Shares</font></html>");
        addSharesBtn.setFont(myBtnFont);
        addSharesBtn.setForeground(w);
        addSharesBtn.setBackground(myBtnColor);

        JButton viewHistoryBtn = new JButton("<html><font color = orange>View Account History Chart</font</html>");
        viewHistoryBtn.setFont(myBtnFont);
        viewHistoryBtn.setForeground(w);
        viewHistoryBtn.setBackground(myBtnColor);

        JButton ownedSharesBtn = new JButton("<html><font color = #00FFFF>View Personal Portfolio & Owned Shares</font></html>");
        ownedSharesBtn.setFont(myBtnFont);
        ownedSharesBtn.setForeground(w);
        ownedSharesBtn.setBackground(myBtnColor);

        JButton logOutBtn = new JButton("Log out Or Switch User");
        logOutBtn.setFont(myBtnFont);
        logOutBtn.setForeground(Color.magenta);
        logOutBtn.setBackground(myBtnColor);

        //Label to inform user what to do, where they are and also states who is currently logged in
        JLabel message = new JLabel();
        message.setText("<html><font color = #F4A460>Main Menu - </font><br/>'<font color = purple>" + theCurrentUser +
                "'</font> Please Choose an Option From the List Below<br/></html>");
        message.setFont(myNextFont);
        message.setForeground(myBtnColor);

        //Panel Initialisation
        JPanel p1 = new JPanel();
        p1.setLayout(new GridBagLayout());

        //Grid Constraints for Buttons
        c.weightx = 1;
        c.weighty = .25;
        c.insets = new Insets(0, 0, 15, 0);
        c.gridheight = 10;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.BOTH;

        //Adding Objects to panels
        p1.add(message, c);
        p1.add(ownedSharesBtn, c);
        p1.add(purchaseBtn, c);
        p1.add(addSharesBtn, c);
        p1.add(bankBtn, c);
        p1.add(viewHistoryBtn, c);
        p1.add(viewGraph, c);
        p1.add(updateBtn, c);
        p1.add(searchDate, c);
        p1.add(displayTable, c);
        p1.add(logOutBtn, c);

        //Adding panel to Frame
        add(p1);
        setVisible(true);

        //Action Listeners assigning actions to each button
        logOutBtn.addActionListener(new ButtonHandler(this, 1));
        displayTable.addActionListener(new ButtonHandler(this, 2));
        searchDate.addActionListener(new ButtonHandler(this, 3));
        updateBtn.addActionListener(new ButtonHandler(this, 4));
        viewGraph.addActionListener(new ButtonHandler(this, 5));
        purchaseBtn.addActionListener(new ButtonHandler(this, 6));
        bankBtn.addActionListener(new ButtonHandler(this, 7));
        addSharesBtn.addActionListener(new ButtonHandler(this, 8));
        viewHistoryBtn.addActionListener(new ButtonHandler(this, 9));
        ownedSharesBtn.addActionListener(new ButtonHandler(this, 10));

    }

    //Button Handler
    class ButtonHandler implements ActionListener { //Implements the action listener
        MenuFrame theApp;
        int action;
        CSVUpdateService getupdate;

        ButtonHandler(MenuFrame app, int action) {
            this.theApp = app;
            this.action = action;
        }

        //Individual button actions
        @Override
        public void actionPerformed(ActionEvent e) {
            if (action == 1) { //Log out button
                new LoginFrame();
                MenuFrame.this.dispose();
            }
            if (action == 2) { //Display table of selected company and download new data if the file doesnt exist
                displayTable();
            }
            if (action == 3) {//Search a selected date for a selected company
                searchDateCompany();
            }
            if (action == 4) { //Search Stock Company and Download Data Dedicated frame for functionality
                new CSVUpdateFrame();
            }
            if (action == 5) {//Graph of selected company and selected time period
                new GraphBuilderFrame();
            }
            if (action == 6) {//Purchase Stocks from companies most recent value
                new BuySellSharesFrame(theApp.theCurrentUser);
            }
            if (action == 7) {//View Bank Account and Withdraw and Deposit Funds
                new BankAccountFrame(theApp.theCurrentUser);
            }
            if (action == 8) { //Add Share to Account
                new AddSharesFrame(theApp.theCurrentUser);

            }
            if (action == 9) { //View Line Chart of Account Purchases and Sales
                new LineChartService(
                        "Financial Portfolio Manager Personal Value Chart",
                        "History of Investment Chart", theApp.theCurrentUser);
            }
            if (action == 10) {
                new FinancialPortfolioFrame(theApp.theCurrentUser);
            }
        }

        //Searches a Date In a CSV File For the Company Symbol and Date Specified
        private void searchDateCompany() {
            String name;
            String SearchDate;
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
                    System.out.println("Unable to view table data using the Symbol: " + name + "" +
                            "  Please Ensure that .csv is added and the company symbol exists on the web");
                    JOptionPane.showMessageDialog(null, "Unable to show selected data");
                }
            } catch (Exception el) {
                JOptionPane.showMessageDialog(null, "You have clicked Cancel");
            }
        }

        //Displays a Full Table of History for the selected Company symbol
        private void displayTable() {
            String input;
            String newinput;
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
                    System.out.println("Unable to download data using the Symbol: " + input.toUpperCase() + " " +
                            " Please Ensure that .csv is added and the company symbol exists on the web");
                }
            } catch (Exception el) {
                JOptionPane.showMessageDialog(null, "Cancelled");
            }
        }
    }
}
