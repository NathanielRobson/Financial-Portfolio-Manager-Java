import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import java.io.FileReader;
import java.io.IOException;

public class DisplayTable {

    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        File A = new File("C:/");
        File FilePath;
        int Checker;
        chooser.setCurrentDirectory(A);
        Checker = chooser.showOpenDialog(null);

        if (Checker == JFileChooser.APPROVE_OPTION) {
            FilePath = chooser.getSelectedFile();
            String a = FilePath.getAbsolutePath();

            ViewTable(a);
        }
        else {
            JOptionPane.showMessageDialog(null, "You have Clicked Cancel");

        }
    }

    public static void ViewTable(String Filepath) {
        JFrame TableFrame = new JFrame();
        JTable Table = new JTable(new DefaultTableModel());

        try (BufferedReader br = new BufferedReader(new FileReader(Filepath))){
            String ColumnLine = br.readLine().trim();
            String[] ColumnNames = ColumnLine.split(",");
            DefaultTableModel Model = (DefaultTableModel)Table.getModel();
            Model.setColumnIdentifiers(ColumnNames);

            Object[] rows = br.lines().toArray();

            for (int i =0; i<rows.length; i++){
                String Line = rows[i].toString().trim();
                String[]RowData = Line.split(",");
                Model.addRow(RowData);
            }
            JScrollPane pane = new JScrollPane(Table);
            TableFrame.add(pane);
            TableFrame.setSize(1600,800);
            TableFrame.setVisible(true);
            TableFrame.setTitle("Stock Table");
            }

        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}


