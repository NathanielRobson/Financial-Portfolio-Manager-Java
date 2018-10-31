import javax.swing.*;
import java.awt.event.*;

public class ExampleFrame
{
    public static void main( String[] args )
    {
        TheFrame frame = new TheFrame();
        frame.setVisible( true );
    }
}

class TheFrame extends JFrame
{
    public TheFrame()
    {
        JButton button = new JButton("Hello there.");
        JPanel panel = new JPanel();
        panel.add(button);
        add(panel);
        setSize( 300, 200 );
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Hello!");
            }
        });
    }
}
