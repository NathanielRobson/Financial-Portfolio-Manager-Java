import app.Find;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.colors.ChartColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class StockGraph extends JPanel {

    JLabel  dateFrom, dateTo, message;
    JTextField dateStart, dateEnd;
    JButton buttonSearch, buttonClear;
    JLabel invalid = new JLabel();



    public StockGraph() {

        setLayout(null);
        setBackground(Color.darkGray);
        dateFrom = new JLabel("Please select a Date From: ");
        dateTo = new JLabel("Date To: ");
        dateStart = new JTextField("yyyy-mm-dd",10);
        dateEnd = new JTextField("yyyy-mm-dd",10);
        buttonSearch = new JButton("Enter");
        buttonClear = new JButton("Clear");
        message = new JLabel("");



        add(dateFrom);
        dateFrom.setBounds(30,30,100,20);
        dateFrom.setForeground(Color.white);

        add(dateTo);
        dateTo.setBounds(250,30,100,20);
        dateTo.setForeground(Color.white);

        add(dateStart);
        dateStart.setBounds(105,30,100,20);
        add(dateEnd);
        dateEnd.setBounds(310,30,100,20);
        add(buttonSearch);
        buttonSearch.setBounds(420,30,100,20);
        buttonSearch.addActionListener(new ButtonHandler(1));

        add(buttonClear);
        buttonClear.setBounds(530,30,100,20);
        buttonClear.addActionListener(new ButtonHandler(2));

        add(message);
        message.setBounds(50,200,600,20);
        message.setVisible(false);




    }

    class ButtonHandler implements ActionListener {

        private int action;

        public ButtonHandler(int action) {this.action = action;}

        public void actionPerformed(ActionEvent e) {

            if (action == 1) {

                String dateS1 = dateStart.getText();
                String dateS2 = dateEnd.getText();

                try {

                    Date d1 = new SimpleDateFormat("yyyy-MM-dd").parse(dateS1);
                    Date d2 = new SimpleDateFormat("yyyy-MM-dd").parse(dateS2);
                    getStockGraph(d1, d2);

                } catch (ParseException a) {
                }
            }

            else if (action == 2) {
                removeAll();
                revalidate();
                repaint();

                add(dateFrom);
                dateFrom.setBounds(30,20,100,20);
                dateFrom.setForeground(Color.white);

                add(dateTo);
                dateTo.setBounds(250,20,100,20);
                dateTo.setForeground(Color.white);

                add(dateStart);
                dateStart.setBounds(105,20,100,20);
                add(dateEnd);
                dateEnd.setBounds(310,20,100,20);
                add(buttonSearch);
                buttonSearch.setBounds(420,20,100,20);
                add(invalid);
                buttonSearch.addActionListener(new ButtonHandler(1));

                dateStart.setText("");
                dateEnd.setText("");
                add(buttonClear);
                buttonClear.setBounds(530,20,100,20);
                buttonClear.addActionListener(new ButtonHandler(2));

                add(message);
                message.setBounds(50,200,600,20);
                message.setVisible(false);


            }

        }
    }


    public void getStockGraph(Date d1, Date d2) {

        ArrayList<Date> xData = new ArrayList<Date>();
        ArrayList<Double> yData = new ArrayList<Double>();
        Find find = new Find();
        Iterator itDate = find.equilibriumValues.keySet().iterator();

        if (d1.compareTo(d2) > 0) {
            message.setText("Please check to see if the start date is beford the end date. Press 'Clear' to continue. ");
            message.setVisible(true);
            message.setForeground(Color.white);
        }

        else {

        while (itDate.hasNext()) {

            Date date2 = (Date) itDate.next();

            if (date2.compareTo(d1) > 0 & date2.compareTo(d2) < 0) {
                xData.add(date2);
                yData.add(Double.valueOf(find.equilibriumValues.get(date2)));
            }
        }XYChart chart = new XYChartBuilder().width(750).height(550).title("Balance Over Time").xAxisTitle("Date").yAxisTitle("Balance £").build();

            chart.addSeries("Balance",xData,yData);

            chart.getStyler().setHasAnnotations(true);
            chart.getStyler().setPlotBackgroundColor(ChartColor.getAWTColor(ChartColor.WHITE));
            chart.getStyler().setPlotGridLinesColor(Color.gray);
            chart.getStyler().setChartBackgroundColor(Color.Black);
            chart.getStyler().setLegendBackgroundColor(Color.WHITE);
            chart.getStyler().setChartFontColor(Color.MAGENTA);
            chart.getStyler().setChartTitleBoxBackgroundColor(Color.Black);
            chart.getStyler().setChartTitleBoxVisible(false);
            chart.getStyler().setChartTitleBoxBorderColor(Color.white);
            chart.getStyler().setPlotGridLinesVisible(true);
            chart.getStyler().setAxisTickPadding(30);
            chart.getStyler().setAxisTickMarkLength(15);
            chart.getStyler().setPlotMargin(20);
            chart.getStyler().setChartTitleFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
            chart.getStyler().setLegendFont(new Font(Font.SERIF, Font.PLAIN, 15));
            chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
            chart.getStyler().setLegendSeriesLineLength(12);
            chart.getStyler().setAxisTitleFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
            chart.getStyler().setAxisTickLabelsFont(new Font(Font.SERIF, Font.PLAIN, 11));
            chart.getStyler().setAxisTickLabelsColor(Color.white);
            chart.getStyler().setLocale(Locale.ENGLISH);

            setVisible(true);
            JPanel panel = new XChartPanel<>(chart);
            add(panel);
            panel.setBounds(1,70,650,350);

    }

    }

}