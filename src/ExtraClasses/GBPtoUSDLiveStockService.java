package ExtraClasses;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class GBPtoUSDLiveStockService {

    public static void main(String[] args) throws IOException {
        GBPtoUSDLiveStockService GBP = new GBPtoUSDLiveStockService();
        System.out.println(GBP.getGBPPrice());
    }

    String GBPPrice;

    GBPtoUSDLiveStockService() throws IOException {
        ArrayList<Double> GBPlist = new ArrayList<>();

        URL GBPurl = new URL("https://www.google.co.uk/search?q=GBP+to+USD");
        URLConnection GBPConn = GBPurl.openConnection();
        GBPConn.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:56.0) Gecko/20100101 Firefox/56.0");
        InputStreamReader GBPinStream = new InputStreamReader(GBPConn.getInputStream());
        BufferedReader GBPbuff = new BufferedReader(GBPinStream);
        GBPPrice = "not found";
        String GBPline = GBPbuff.readLine();

        while (GBPline != null) {

            if (GBPline.contains("\"knowledge-currency__tgt-amount\"")) {
                int targetLine = GBPline.indexOf("\"knowledge-currency__tgt-amount\"");
                int decimal = GBPline.indexOf(".", targetLine);
                int startPos = decimal;

                while (GBPline.charAt(startPos) != '>') {
                    startPos--;
                }
                GBPPrice = GBPline.substring(startPos + 1, decimal + 3);
            }
            GBPline = GBPbuff.readLine();
        }
        GBPlist.add(Double.parseDouble(GBPPrice));
    }

    public Double getGBPPrice() throws IOException {
        new GBPtoUSDLiveStockService();

        return Double.parseDouble(GBPPrice);

    }
}
