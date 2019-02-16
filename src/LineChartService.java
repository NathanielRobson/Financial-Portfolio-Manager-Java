import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

class LineChartService extends ApplicationFrame { //Line Chart Service

    private String theCurrentUser;
    private DefaultCategoryDataset thedataset = new DefaultCategoryDataset();

    //Sets Chart Constraints and Initialises the Chart using Other Methods within This Class
    LineChartService(String applicationTitle, String chartTitle, String theCurrentUser) {
        super(applicationTitle);
        JFrame frame = new JFrame();

        this.theCurrentUser = theCurrentUser;
        JFreeChart lineChart = null;
        try {
            lineChart = ChartFactory.createLineChart(
                    chartTitle,
                    "Date of Value Change", "Bank",
                    readDataCreateSet(),
                    PlotOrientation.VERTICAL,
                    true, true, false);
        } catch (IOException e) {
            System.out.println("Sorry file doesnt exist");
        }

        //Frame Constraints
        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(1400, 500));
        setContentPane(chartPanel);
        frame.setVisible(true);
        frame.setSize(1700, 700);
        frame.setTitle("Financial Portfolio Personal History Chart");
        frame.setLocationRelativeTo(null);
        frame.add(chartPanel);
    }

    //Creates a data set by reading file and applying it to Chart
    private DefaultCategoryDataset readDataCreateSet() throws IOException {
        File readfile = new File(".//UserPortfolios/" + getTheCurrentUser().toUpperCase() + "-" + "Data.txt");
        BufferedReader in = new BufferedReader(new FileReader(readfile));

        String fileline;
        while ((fileline = in.readLine()) != null) {
            String[] token = fileline.split(",");
            System.out.println(token[0]);
            System.out.println(token[1]);
            System.out.println(token[2]);
            double thevalue = Double.valueOf(String.format("%.2f", Double.valueOf(token[0])));

            thedataset.addValue(thevalue, token[1], token[2]);
        }
        return thedataset;
    }

    private String getTheCurrentUser() {
        return theCurrentUser;
    }
}