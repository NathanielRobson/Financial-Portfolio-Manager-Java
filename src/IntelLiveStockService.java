import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class IntelLiveStockService {

    public static void main(String[] args) throws IOException {
        IntelLiveStockService Intel = new IntelLiveStockService();
        System.out.println(Intel.getIntelPrice());

    }

    String IntelCurrentPrice;

    IntelLiveStockService() throws IOException {
        ArrayList<Double> IntelList = new ArrayList<>();

        URL CocaUrl = new URL("https://www.google.com/search?q=NASDAQ:INTC&tbm=fin");
        URLConnection IntelConn = CocaUrl.openConnection();
        IntelConn.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:56.0) Gecko/20100101 Firefox/56.0");
        InputStreamReader IntelinStream = new InputStreamReader(IntelConn.getInputStream());
        BufferedReader Intelbuff = new BufferedReader(IntelinStream);
        IntelCurrentPrice = "not found";
        String Intelline = Intelbuff.readLine();

        while (Intelline != null) {
            if (Intelline.contains("\"Intel Corporation\\\",\\\"INTC\\\"")) {
                int targetLine = Intelline.indexOf("\"Intel Corporation\\\",\\\"INTC\\\"");
                int decimal = Intelline.indexOf(".", targetLine);
                int startPos = decimal;

                while (Intelline.charAt(startPos) != '\"') {
                    startPos--;
                }
                IntelCurrentPrice = Intelline.substring(startPos + 1, decimal + 3);
            }
            Intelline = Intelbuff.readLine();
        }
        IntelList.add(Double.parseDouble(IntelCurrentPrice));
    }

    public Double getIntelPrice() throws IOException {
        new IntelLiveStockService();
        return Double.parseDouble(IntelCurrentPrice);
    }
}
