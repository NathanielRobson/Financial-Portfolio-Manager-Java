import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class DisplayTableService { //Display Table Service

    static void ViewTable(String Filepath) { //Creates a table for each column value found in the csv file
        JFrame TableFrame = new JFrame();
        JTable Table = new JTable(new DefaultTableModel());

        //Reads each line in CSV and Displays table
        try (BufferedReader br = new BufferedReader(new FileReader(Filepath))) {
            String ColumnLine = br.readLine().trim();
            String[] ColumnNames = ColumnLine.split(",");
            DefaultTableModel Model = (DefaultTableModel) Table.getModel();
            Model.setColumnIdentifiers(ColumnNames);

            Object[] rows = br.lines().toArray();

            for (Object row : rows) {
                String Line = row.toString().trim();
                String[] RowData = Line.split(",");
                Model.addRow(RowData);
            }

            //Frame/pane constaints
            JScrollPane pane = new JScrollPane(Table);
            TableFrame.add(pane);
            TableFrame.setSize(1600, 800);
            TableFrame.setVisible(true);
            TableFrame.setTitle("Financial Portfolio Manager CSV Table");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


