import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.String;
public class Button {
    JButton button, button1, button2, button3, button4;
    JPanel p1, p2, p3;
    JTextField inp;
    JLabel message;
    Button() {
        JFrame frame = new JFrame("Flow Layout");
        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        button = new JButton("View Market Trends");
        button1 = new JButton("View Total Investment");
        button2 = new JButton("Simulate Scenario");
        button3 = new JButton("Purchase Shares");
        button4 = new JButton("View Past investments");
        //The following lines of code links the button to a button handler
        button.addActionListener(new ButtonHandler(this, 1));
        button1.addActionListener(new ButtonHandler(this, 2));
        button2.addActionListener(new ButtonHandler(this, 3));
        button3.addActionListener(new ButtonHandler(this, 4));
        button4.addActionListener(new ButtonHandler(this, 5));
        button5.addActionListener(new ButtonHandler(this, 6));
        p1.add(button);
        p1.add(button1);
        p1.add(button2);
        p1.add(button3);
        p1.add(button4);
        p2.add(inp);
        p3.add(message);
        frame.add(p1, BorderLayout.NORTH);
        frame.add(p2, BorderLayout.SOUTH);
        frame.add(p3, BorderLayout.CENTER);
        frame.setSize(700, 500); //sets the size of the Jframe
        frame.setVisible(true);
    }
    class ButtonHandler implements ActionListener { //Implements the action listener
        Button theApp;
        int action;
        ButtonHandler(Button app, int action) {
            this.theApp = app;
            this.action = action;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
        }
    }
    public static void main (String[] args) {
    }
}