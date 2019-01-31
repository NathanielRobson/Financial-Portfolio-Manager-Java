import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class LoginFrame extends JFrame {

    JLabel userLabel;
    JTextField userField;
    JLabel passLabel;
    JTextField passField;
    JButton resetBtn;
    JButton loginBtn;
    JLabel welcomeLabel;
    JLabel helpLabel;
    JLabel errorLabel;

    LoginFrame() {

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

            if (action == 1) {
                userField.setText("");
                passField.setText("");
                errorLabel.setText("");
            }
            if (action == 2) {


                String userName = userField.getText();
                String passWord = passField.getText();


                loginservice = new loginService();
                boolean loggedIn = false;

                if(!userName.equals("")&&!passWord.equals("")){
                    for (int i = 1; i < loginservice.userNames.size(); i++) {
                        if (!userName.equals("")&&!passWord.equals("")&&(loginservice.userNames.get(i).matches(userName)
                                && loginservice.passWords.get(i).matches(passWord))) {

                            JOptionPane.showMessageDialog(null, "Thankyou For Logging In " + userName);
                            loggedIn = true;
                            new MenuFrame();
                            System.out.println(loginservice.userNames.get(i));
                            LoginFrame.this.dispose();
                            break;

                        } else{
                            break;
                        }
                    }
            } else if(!loggedIn && (userName.equals("") || passWord.equals(""))){
                    errorLabel.setText("Error Username or Password Field is Empty or Incorrect");
                }
            }
        }
    }
}
