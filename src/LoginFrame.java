import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class LoginFrame extends JFrame { //Login Frame
    //Main method for Testing
    public static void main(String[] args) { //Testing using Main
        new LoginFrame();
    }

    private String theCurrentUser;
    private JLabel errorLabel;
    private JTextField userField, passField;

    LoginFrame() { //Frame init and design and layout functionality

        //Custom fonts and colors for look and feel
        Font myFieldFont = new Font("Century Gothic", Font.BOLD, 14);
        Font myLabelFont = new Font("Century Gothic", Font.BOLD, 18);
        Font myButtonFont = new Font("Tahoma", Font.BOLD, 20);

        Color myBlueColor = new Color(59, 69, 182);
        Color resetColor = new Color(200, 0, 200);

        JLabel userLabel = new JLabel("User Name: ");
        userLabel.setFont(myLabelFont);
        userLabel.setForeground(myBlueColor);

        userField = new JTextField(20);
        userField.setFont(myFieldFont);

        JLabel passLabel = new JLabel("Password: ");
        passLabel.setFont(myLabelFont);
        passLabel.setForeground(myBlueColor);

        passField = new JPasswordField(20);
        passField.setFont(myFieldFont);

        JButton resetBtn = new JButton("Reset");
        JButton loginBtn = new JButton("Login");

        JLabel welcomeLabel = new JLabel("<html><font color = #2F4F4F> Welcome to Team 3's " +
                "Financial Portfolio Manager</font><br/><font color = #F4A460>" +
                " We Hope You Enjoy Your Time Using Our Program<br>" +
                "If You Have Any Queries Please Do Not Hesitate To<br>" +
                "<font color = #F4A460>Contact Us At Team3@essex.ac.uk Thank you :)</font></html>");
        welcomeLabel.setForeground(myBlueColor);
        welcomeLabel.setFont(myLabelFont);

        JLabel helpLabel = new JLabel("Please Login Using Your Unique Login Credentials");
        helpLabel.setForeground(myBlueColor);
        helpLabel.setFont(myLabelFont);

        errorLabel = new JLabel();
        errorLabel.setForeground(Color.red);
        errorLabel.setFont(myFieldFont);

        resetBtn.setBackground(myBlueColor);
        resetBtn.setForeground(resetColor);
        resetBtn.setFont(myButtonFont);

        loginBtn.setBackground(myBlueColor);
        loginBtn.setForeground(Color.white);
        loginBtn.setFocusPainted(false);
        loginBtn.setFont(myButtonFont);

        //Panel initialisation
        JPanel panelOne = new JPanel();
        JPanel panelTwo = new JPanel();
        JPanel panelThree = new JPanel();
        JPanel panelFour = new JPanel();
        JPanel panelFive = new JPanel();
        JPanel panelSix = new JPanel();

        //Adding objects to panels
        panelOne.add(userLabel);
        panelOne.add(userField);
        panelTwo.add(passLabel);
        panelTwo.add(passField);
        panelThree.add(resetBtn);
        panelThree.add(loginBtn);
        panelFive.add(welcomeLabel);
        panelSix.add(helpLabel);
        panelFour.add(errorLabel);

        //Adding panels to frame
        add(panelOne);
        add(panelTwo);
        add(panelThree);
        add(panelFour);
        add(panelFive);
        add(panelSix);

        //Frame Constraints
        setLayout(new FlowLayout());
        setTitle("Financial Portfolio Manager Login");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(480, 360);
        setVisible(true);

        //Individual action listeners for each button
        resetBtn.addActionListener(new ButtonHandler(this, 1));
        loginBtn.addActionListener(new ButtonHandler(this, 2));

    }

    public class ButtonHandler implements ActionListener { //Implements the action listener
        LoginFrame theApp;
        int action;
        loginService loginservice;

        ButtonHandler(LoginFrame app, int action) {
            this.theApp = app;
            this.action = action;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (action == 1) { //Reset button
                userField.setText("");
                passField.setText("");
                errorLabel.setText("");
            }
            if (action == 2) { //Login button Validation
                String userName;
                String passWord;
                userName = userField.getText().trim();
                passWord = passField.getText().trim();
                loginservice = new loginService();

                try {
                    if (!(userName.equals("") || passWord.equals(""))) {
                        //Runs loop through list of members to find input value match
                        for (int i = 0; i < loginservice.userNames.size(); i++) {
                            if (loginservice.userNames.get(i).equals(userName) && (loginservice.passWords.get(i).equals(passWord))) {//Checks members.csv file for the user account
                                theApp.setCurrentUser(userName);
                                new MenuFrame(userName);

                                LoginFrame.this.dispose();
                            } else {
                                errorLabel.setText("Incorrect login Details, Please Enter Correct Username and Password");
                            }
                        }
                    } else {
                        errorLabel.setText("Error Username or Password Field is Empty or Incorrect");
                    }
                } catch (Exception el) {
                    errorLabel.setText("Error Username or Password Field is Empty or Incorrect");
                }
            }
        }
    }

    //Set Current User
    private void setCurrentUser(String theNewCurrentUser) {
        this.theCurrentUser = theNewCurrentUser;
    }

}
