import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

class MenuFrame extends JFrame {
    //Main Menu

    public static void main(String[] args) { //Main method for Testing
        new MenuFrame();
    }

    JButton searchDate;
    JButton displayTable;
    JButton viewGraph;
    JButton button2;
    JButton purchaseBtn;
    JButton button4;
    JButton logOutBtn;
    JButton exitBtn;
    JButton updateBtn;
    JLabel message;

    MenuFrame() {//Frame initialisation and design and layout
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        setTitle("Financial Portfolio Manager Main Menu");
        setResizable(false);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1400, 800);
        setLocationRelativeTo(null);
        Font myBtnFont = new Font("Century Gothic", Font.BOLD, 18);
        Color myBtnColor = new Color(59, 69, 182);
        Color w = Color.white;

        displayTable = new JButton("Display Table of Shares");
        displayTable.setFont(myBtnFont);
        displayTable.setForeground(w);
        displayTable.setBackground(myBtnColor);

        searchDate = new JButton("Search a Date to View Share Information");
        searchDate.setFont(myBtnFont);
        searchDate.setForeground(w);
        searchDate.setBackground(myBtnColor);

        viewGraph = new JButton("View up to Date Graphs");
        viewGraph.setFont(myBtnFont);
        viewGraph.setForeground(w);
        viewGraph.setBackground(myBtnColor);

        updateBtn = new JButton("<html>Click Here to Get Up to Date Shares Information<i class=\"fas fa-band-aid\"></i></html>");
        updateBtn.setFont(myBtnFont);
        updateBtn.setForeground(w);
        updateBtn.setBackground(myBtnColor);

        button2 = new JButton("To do");
        button2.setFont(myBtnFont);
        button2.setForeground(w);
        button2.setBackground(myBtnColor);

        purchaseBtn = new JButton("Purchase Shares");
        purchaseBtn.setFont(myBtnFont);
        purchaseBtn.setForeground(w);
        purchaseBtn.setBackground(myBtnColor);

        button4 = new JButton("View Past investments");
        button4.setFont(myBtnFont);
        button4.setForeground(w);
        button4.setBackground(myBtnColor);

        logOutBtn = new JButton("Log Out or Switch User");
        logOutBtn.setFont(myBtnFont);
        logOutBtn.setForeground(Color.magenta);
        logOutBtn.setBackground(myBtnColor);

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
        GetCSVUpdates getupdate;
        GraphBuilderFrame builder;

        ButtonHandler(MenuFrame app, int action) {
            this.theApp = app;
            this.action = action;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (action == 2) { //Display table of selected company and download new data if the file doesnt exist

                String input;
                String newinput;
                input = JOptionPane.showInputDialog("Please Input a Filename or a Company Symbol").toUpperCase();

                try{
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
                                System.out.println("Unable to download data using the Symbol: " + crumbinput.toUpperCase());
                            }
                        }
                    }
                } else {
                    System.out.println("Unable to download data using the Symbol: " + input.toUpperCase() + "  Please Ensure that .csv is added and the company symbol exists on the web");
                }
            } catch(Exception el){
                JOptionPane.showMessageDialog(null,"Cancelled");
                }
            }
            if (action == 1) {
                new LoginFrame();
                MenuFrame.this.dispose();
            }
            if (action == 3) {//Search a selected date for a selected company
                String name = JOptionPane.showInputDialog("Please Enter the Symbol or Filename of The Company").toUpperCase();
                String SearchDate = JOptionPane.showInputDialog("Enter the Date Like So -> YYYY-MM-DD");
                String newname;
                try{
                if (name.contains(".CSV") && new File(name).exists()) { //If input does not contain .csv then it is added to allow for correct file extension
                    SearchandViewService.readFile(name,SearchDate);

                } else if (!name.contains(".CSV")) {
                    newname = name + ".CSV";

                    if (newname.contains(".CSV") && new File(newname).exists()) {
                        SearchandViewService.readFile(newname,SearchDate);

                    } else if (newname.contains(".CSV") && !new File(newname).exists()) {//If file doesnt exist then downloads newest data for selected company from internet
                        getupdate = new GetCSVUpdates();
                        String crumb;
                        String crumbinput;

                        if (newname.contains(".CSV")) {
                            crumbinput = newname.substring(0, newname.length() - 4);
                            crumb = getupdate.getCrumb(crumbinput);

                            if (crumb != null && !crumb.isEmpty()) {
                                getupdate.downloadData(crumbinput, 0, System.currentTimeMillis(), crumb);
                                SearchandViewService.readFile(newname,SearchDate);

                            } else {
                                System.out.println("Unable to view data using the Symbol: " + crumbinput.toUpperCase());
                                JOptionPane.showMessageDialog(null,"Unable to show selected data");
                            }
                        }
                    }
                } else {
                    System.out.println("Unable to view table data using the Symbol: " + name.toUpperCase() + "  Please Ensure that .csv is added and the company symbol exists on the web");
                    JOptionPane.showMessageDialog(null,"Unable to show selected data");
                }
            } catch (Exception el){
                    JOptionPane.showMessageDialog(null,"Cancelled !");
                }
            }
            if (action == 4) { //Search Stock Company and Download Data Dedicated frame for functionality
                new StockSearchFrame();
                MenuFrame.this.dispose();
            }
            if (action == 5) {//Graph of selected company and selected time period
                builder = new GraphBuilderFrame();
            }
            if (action == 6) {//Purchase Stocks from companies most recent value
                new PurchaseStocksFrame();

            } if (action == 7){//View Personal Investments in Companies
                //This should check the most recent price of the company of choice and then store that data e.g $130.50 per share
                //Times it by how many shares the user wished to purchase e.g $130.50 x 100
                //Provide total cost and then over time show increase or decrease of investment
                //If share decreases to $125.00 then provide accurate feedback regarding total loss 100 x 130.50 - 100 x 125.00 (difference == loss)
            }
        }
    }
}