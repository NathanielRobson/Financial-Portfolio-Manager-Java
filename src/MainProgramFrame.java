import javax.swing.*;
import java.awt.*;

public class MainProgramFrame {
    static class LoginFrame extends JFrame {

        LoginFrame(){
        LoginFrame frame = new LoginFrame();

        JLabel userLabel = new JLabel("User ID: ");
        JTextField userField = new JTextField(20);
        JLabel passLabel = new JLabel("Password: ");
        JPasswordField passField = new JPasswordField(20);
        JButton resetBtn = new JButton("Reset TextFields");
        JButton loginBtn = new JButton("Login");
        resetBtn.setSize(110, 70);
        loginBtn.setSize(110,70);

        frame.add(userLabel);
        frame.add(userField);
        frame.add(passLabel);
        frame.add(passField);
        frame.add(resetBtn);
        frame.add(loginBtn);
        frame.setLayout(new FlowLayout());
        frame.setTitle("Login");
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        frame.setSize(400,400);

    }
}
public static void main( String args[] ){
    LoginFrame frame = new LoginFrame();
    frame.setVisible(true);
    }
}


