import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class AppleLiveStockService {

    public static void main(String[] args) throws IOException {
        AppleLiveStockService Ap = new AppleLiveStockService();
        System.out.println(Ap.getApplePrice());
    }

    String AppleCurrentPrice;

    AppleLiveStockService() throws IOException {
        ArrayList<Double> AppleList = new ArrayList<>();

        URL AppleURL = new URL("https://www.google.co.uk/search?q=NASDAQ:AAPL&tbm=fin");
        URLConnection AppleConn = AppleURL.openConnection();
        AppleConn.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:56.0) Gecko/20100101 Firefox/56.0");
        InputStreamReader AppleinStream = new InputStreamReader(AppleConn.getInputStream());
        BufferedReader Applebuff = new BufferedReader(AppleinStream);
        AppleCurrentPrice = "not found";
        String Appleline = Applebuff.readLine();

        while (Appleline != null) {
            if (Appleline.contains("\"Apple Inc.\\\",\\\"AAPL\\\"")) {
                int targetLine = Appleline.indexOf("\"Apple Inc.\\\",\\\"AAPL\\\"");
                int decimal = Appleline.indexOf(".", targetLine);
                int startPos = decimal;

                while (Appleline.charAt(startPos) != '\"') {
                    startPos--;
                }
                AppleCurrentPrice = Appleline.substring(startPos + 25, decimal + 21);
            }
            if (Appleline.contains("")) {
                System.out.println(Appleline);
            }
            Appleline = Applebuff.readLine();
        }
        AppleList.add(Double.parseDouble(AppleCurrentPrice));
    }

    public Double getApplePrice() throws IOException {
        new AppleLiveStockService();
        return Double.parseDouble(AppleCurrentPrice);
    }
}
