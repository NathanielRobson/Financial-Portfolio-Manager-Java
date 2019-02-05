package ExtraClasses;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class FBLiveStockService {

    public static void main(String[] args) throws IOException {
        FBLiveStockService FB = new FBLiveStockService();
        System.out.println(FB.getFBPrice());
    }

    String FBCurrentPrice;

    FBLiveStockService() throws IOException {
        ArrayList<Double> FBList = new ArrayList<>();

        URL FBurl = new URL("https://www.google.co.uk/search?q=NASDAQ:FB&tbm=fin");
        URLConnection FBConn = FBurl.openConnection();
        FBConn.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:56.0) Gecko/20100101 Firefox/56.0");
        InputStreamReader FBinStream = new InputStreamReader(FBConn.getInputStream());
        BufferedReader FBbuff = new BufferedReader(FBinStream);
        FBCurrentPrice = "not found";
        String FBline = FBbuff.readLine();

        while (FBline != null) {
            if (FBline.contains("\"Facebook, Inc. Common Stock\\\",\\\"FB\\\"")) {
                int targetLine = FBline.indexOf("\"Facebook, Inc. Common Stock\\\",\\\"FB\\\"");
                int decimal = FBline.indexOf(".", targetLine);
                int startPos = decimal;

                while (FBline.charAt(startPos) != '\"') {
                    startPos--;
                }
                FBCurrentPrice = FBline.substring(startPos + 40, decimal + 32);
            }
            FBline = FBbuff.readLine();
        }
        FBList.add(Double.parseDouble(FBCurrentPrice));
    }

    public Double getFBPrice() throws IOException {
        new FBLiveStockService();

        return Double.parseDouble(FBCurrentPrice);

    }
}
