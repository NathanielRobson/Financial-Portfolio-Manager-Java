

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainProgramFrame
{
    public static void main( String[] args )
    {
        new LoginFrame();


    }
}

class LoginFrame extends JFrame
{
     LoginFrame()
    {
        setSize(400,400);

        JLabel userLabel = new JLabel("User ID: ");
        JTextField userField = new JTextField(20);
        JLabel passLabel = new JLabel("Password: ");
        JPasswordField passField = new JPasswordField(20);
        JButton resetBtn = new JButton("Reset");
        JButton loginBtn = new JButton("Login");
        JLabel welcomeLabel = new JLabel("Welcome to our Financial Portfolio Manager!");
        JLabel helpLabel = new JLabel("Please login using your credentials.");
        JLabel errorLabel = new JLabel();
        resetBtn.setSize(110, 70);
        loginBtn.setSize(110,70);

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
        panelFour.add(errorLabel);
        panelFive.add(welcomeLabel);
        panelSix.add(helpLabel);


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
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        resetBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userField.setText("");
                passField.setText("");
            }
        });
    }
}