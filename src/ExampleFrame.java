import javax.swing.*;
import java.awt.event.*;

public class ExampleFrame
{
    public static void main( String[] args )
    {
        the_frame frame = new the_frame();
        frame.setVisible( true );
    }
}

class the_frame extends JFrame
{
    public the_frame()
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
