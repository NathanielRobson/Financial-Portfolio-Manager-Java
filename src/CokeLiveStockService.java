import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class CokeLiveStockService {
    public static void main(String[] args) throws IOException {
        new CokeLiveStockService();
    }

    CokeLiveStockService() throws IOException {

        ArrayList<Double> CocaColaList = new ArrayList<>();

        String CocaCola = "The Coca-Cola Co 'KO': ";
        URL CocaUrl = new URL("https://www.google.com/search?q=ko&tbm=fin#scso=_6ptTXMn4Fd-q1fAPtpC6qAM2:0");
        URLConnection CocaConn = CocaUrl.openConnection();
        CocaConn.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:56.0) Gecko/20100101 Firefox/56.0");
        InputStreamReader CocainStream = new InputStreamReader(CocaConn.getInputStream());
        BufferedReader Cocabuff = new BufferedReader(CocainStream);
        String CocaCurrentPrice = "not found";
        String Cocaline = Cocabuff.readLine();

        while (Cocaline != null) {
            if (Cocaline.contains("\"The Coca-Cola Co\\\",\\\"KO\\\"")) {
                int targetLine = Cocaline.indexOf("\"The Coca-Cola Co\\\",\\\"KO\\\"");
                int decimal = Cocaline.indexOf(".", targetLine);
                int startPos = decimal;

                while (Cocaline.charAt(startPos) != '\"') {
                    startPos--;
                }
                CocaCurrentPrice = Cocaline.substring(startPos + 1, decimal + 3);
            }
            Cocaline = Cocabuff.readLine();
        }
        System.out.println(CocaCola + CocaCurrentPrice + " USD");
        CocaColaList.add(Double.parseDouble(CocaCurrentPrice));
    }
}
