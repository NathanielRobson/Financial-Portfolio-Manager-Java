import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

class LoginFrame extends JFrame {

    public static void main(String[] args) { //Testing using Main

        new LoginFrame();
    }

    Statement statement;
    ResultSet result;

    String currentUser;

    JLabel userLabel;
    JTextField userField;
    JLabel passLabel;
    JTextField passField;
    JButton resetBtn;
    JButton loginBtn;
    JLabel welcomeLabel;
    JLabel helpLabel;
    JLabel errorLabel;

    LoginFrame() { //Frame init and design and layout functionality

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 400);

        Font myFieldFont = new Font("Century Gothic", Font.BOLD, 14);
        Font myTextFont = new Font("Century Gothic", Font.BOLD, 16);
        Font myLabelFont = new Font("Century Gothic", Font.BOLD, 18);

        Font myButtonFont = new Font("Tahoma", Font.BOLD, 20);
        Color myBlueColor = new Color(59, 69, 182);
        Color resetColor = new Color(200, 0, 200);

        userLabel = new JLabel("User ID: ");
        userLabel.setFont(myLabelFont);
        userLabel.setForeground(myBlueColor);

        userField = new JTextField(20);
        userField.setFont(myFieldFont);

        passLabel = new JLabel("Password: ");
        passLabel.setFont(myLabelFont);
        passLabel.setForeground(myBlueColor);

        passField = new JPasswordField(20);
        passField.setFont(myFieldFont);

        resetBtn = new JButton("Reset");
        loginBtn = new JButton("Login");

        welcomeLabel = new JLabel("Welcome to our Financial Portfolio Manager!");
        welcomeLabel.setForeground(myBlueColor);
        welcomeLabel.setFont(myTextFont);

        helpLabel = new JLabel("Please login using your credentials.");
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

        JPanel panelOne = new JPanel();
        JPanel panelTwo = new JPanel();
        JPanel panelThree = new JPanel();
        JPanel panelFour = new JPanel();
        JPanel panelFive = new JPanel();
        JPanel panelSix = new JPanel();

        panelOne.add(userLabel);
        panelOne.add(userField);
        panelTwo.add(passLabel);
        panelTwo.add(passField);
        panelThree.add(resetBtn);
        panelThree.add(loginBtn);
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
        setTitle("Financial Portfolio Manager Login");
        setResizable(false);
        setLocationRelativeTo(null);

        setVisible(true);

        //Individual action listeners for each button
        resetBtn.addActionListener(new ButtonHandler(this, 1));
        loginBtn.addActionListener(new ButtonHandler(this, 2));

        //Calls db connect method to allow for database functionality
        connectToDB();
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
               /*try { //DB Future Implementation
                   String user = userField.getText();
                   String pass = passField.getText();

                   String sql = "SELECT userName, passWord FROM LoginCred WHERE user ='" +
                           user +"'AND" + "'pass =' '" + pass + "'";
                   result = statement.executeQuery(sql);
                   int count = 0;
                   while (result.next()){ //DB Load Test
                       count++;
                       System.out.println(count);
                   }

               } catch(Exception el){

               }*/

            }
            if (action == 2) { //Login button Validation
                String userName = userField.getText();
                String passWord = passField.getText();

                loginservice = new loginService();
                boolean loggedIn = false;

                if (!userName.equals("") && !passWord.equals("")) {
                    for (int i = 1; i < loginservice.userNames.size(); i++) { //Runs loop through list of members to find input value match
                        if (!userName.equals("") && !passWord.equals("") && (loginservice.userNames.get(i).matches(userName)
                                && loginservice.passWords.get(i).matches(passWord))) { //Checks members.csv file for the user account

                            JOptionPane.showMessageDialog(null, "Thankyou For Logging In " + userName);
                            currentUser = loginservice.userNames.get(i);
                            System.out.println(currentUser);
                            loggedIn = true;
                            new MenuFrame();
                            LoginFrame.this.dispose();
                            break;

                        } else {
                            break;
                        }
                    }
                } else if (!loggedIn && (userName.equals("") || passWord.equals(""))) {
                    errorLabel.setText("Error Username or Password Field is Empty or Incorrect");
                }
            }
        }
    }

    public void connectToDB(){ //DB Connect Future Implementation
        Connection conn = null;

        try
        { //JDBC database driver and connectivity to db
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/test","root", "");
            System.out.print("Database is connected !");
            conn.close();
        }
        catch(Exception e)
        {
            System.out.print("error connecting to DB"+e);
        }
    }
    void theCurrentUser(String user) { //get current user for current users session
        ArrayList<String> theCurrentUserList = new ArrayList<>();
        theCurrentUserList.add(user);
    }
}
