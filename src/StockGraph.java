import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.colors.ChartColor;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

class StockGraph {

    //Main method for testing
    public static void main(String[] args) {
        StockGraph Graph = new StockGraph();
        Graph.drawGraph("F.csv", 20, 1);

    }

    CSVtoArray toArray = new CSVtoArray();

    public List dateArrayFromFile(String file, int from, int to) { //From the end of the date array it selects the chosen number of days to display on the graph
        toArray.CSVtoArray(file);
        ArrayList<String> datetail = new ArrayList<>();
        int size = toArray.datearray.size();

        for (int i = size - to - 1; i <= size - from; i++) {

            if (true) {

                datetail.add(toArray.datearray.get(i));
            }
        }
        return datetail;
    }

    public List valueArrayFromFile(String file, int from, int to) { //From the end of the close value array it selects the chosen number of days to display on the graph
        toArray.CSVtoArray(file);
        ArrayList<Double> valuetail2 = new ArrayList<>();
        int size = toArray.closevaluearray.size();

        for (int i = size - to - 1; i <= size - from; i++) {
            if (true) {
                valuetail2.add(toArray.closevaluearray.get(i));
            }
        }
        return valuetail2;
    }

    public CategoryChart drawGraph(String filename, int from, int to) { //draw graph and initialise frame and layout of graph
        JFrame frame = new JFrame();
        CategoryChart chart = new CategoryChartBuilder().width(1000).height(800).xAxisTitle("Date").yAxisTitle("Stock price").build();

        chart.setTitle("Up to Date Graph of Company Stocks from: " + filename);
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart.getStyler().setHasAnnotations(true);
        chart.getStyler().setPlotBackgroundColor(ChartColor.getAWTColor(ChartColor.BLACK));
        chart.getStyler().setPlotGridLinesColor(Color.MAGENTA);
        chart.getStyler().setChartBackgroundColor(Color.WHITE);
        chart.getStyler().setLegendBackgroundColor(Color.PINK);
        chart.getStyler().setChartFontColor(Color.MAGENTA);
        chart.getStyler().setChartTitleBoxBackgroundColor(Color.white);
        chart.getStyler().setChartTitleBoxVisible(false);
        chart.getStyler().setChartTitleBoxBorderColor(Color.BLACK);
        chart.getStyler().setPlotGridLinesVisible(true);
        chart.getStyler().setAxisTickPadding(30);
        chart.getStyler().setAxisTickMarkLength(15);
        chart.getStyler().setPlotMargin(20);
        chart.getStyler().setChartTitleFont(new Font(Font.MONOSPACED, Font.BOLD, 24));
        chart.getStyler().setLegendFont(new Font(Font.SERIF, Font.PLAIN, 18));
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideSE);
        chart.getStyler().setLegendSeriesLineLength(12);
        chart.getStyler().setAxisTitleFont(new Font(Font.SANS_SERIF, Font.ITALIC, 18));
        chart.getStyler().setAxisTickLabelsFont(new Font(Font.SERIF, Font.PLAIN, 11));
        chart.getStyler().setLocale(Locale.ENGLISH);

        chart.addSeries(filename, dateArrayFromFile(filename, to, from), valueArrayFromFile(filename, to, from));

        //initialise frame and panel
        frame.setVisible(true);
        frame.setSize(1700, 700);
        frame.setTitle("new Chart");
        frame.setLocationRelativeTo(null);
        JPanel panel = new XChartPanel<>(chart);
        frame.add(panel);

        //return custom chart
        return chart;
    }
}
