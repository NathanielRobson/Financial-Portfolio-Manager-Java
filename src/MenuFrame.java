import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.String;

class MenuFrame extends JFrame {

    public static void main (String[] args) {
        new MenuFrame();
    }

    JButton button;
    JButton displayTable;
    JButton button2;
    JButton button3;
    JButton button4;
    JButton logOutBtn;
    JButton exitBtn;
    JLabel message;

    MenuFrame() {

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        setTitle("Financial Portfolio Manager Menu");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(700,400);

        Font myBtnFont = new Font("Century Gothic", Font.BOLD, 18);
        Color myBtnColor = new Color(59,69,182);
        Color w = Color.white;

        displayTable = new JButton("Display Table of Shares - (NR CK)(Working)");
        displayTable.setFont(myBtnFont);
        displayTable.setForeground(w);
        displayTable.setBackground(myBtnColor);

        button = new JButton("View Market Trends - (Graph Daniel)(TODO)");
        button.setFont(myBtnFont);
        button.setForeground(w);
        button.setBackground(myBtnColor);

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

        logOutBtn = new JButton("Log Out (NR)(Working)");
        logOutBtn.setFont(myBtnFont);
        logOutBtn.setForeground(w);
        logOutBtn.setBackground(myBtnColor);

        exitBtn = new JButton("Exit (NR)(Working)");
        exitBtn.setFont(myBtnFont);
        exitBtn.setForeground(new Color(200,0,200));
        exitBtn.setBackground(myBtnColor);

        message = new JLabel();
        message.setText("Please Choose an Option From the List Below");
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
        c.insets = new Insets(0, 0, 10, 0);
        c.gridheight = 10;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.BOTH;

        p1.add(message,c);
        p1.add(displayTable,c);
        p1.add(button,c);
        p1.add(button2,c);
        p1.add(button3,c);
        p1.add(button4,c);
        p1.add(logOutBtn,c);

        add(p2);
        add(p1);
        add(p3);
        add(p4);
        add(p5);

        setVisible(true);

        logOutBtn.addActionListener(new ButtonHandler(this, 1));
        displayTable.addActionListener(new ButtonHandler(this,2));

    }

    class ButtonHandler implements ActionListener { //Implements the action listener
        MenuFrame theApp;
        int action;
        ButtonHandler(MenuFrame app, int action) {
            this.theApp = app;
            this.action = action;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if(action == 2){
                DisplayTable.ViewTable("Test.csv");
            }
            if(action == 1){

                new LoginFrame();
                MenuFrame.this.dispose();

            }
        }
    }

}