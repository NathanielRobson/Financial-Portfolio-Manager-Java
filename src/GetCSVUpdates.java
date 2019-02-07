import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.*;
import java.util.Arrays;
import java.util.List;

import static org.apache.commons.lang3.StringEscapeUtils.unescapeJava;

public class GetCSVUpdates {

    HttpClient client;
    HttpClientContext context;

    public GetCSVUpdates() {
        CookieStore cookieStore = new BasicCookieStore();
        client = HttpClientBuilder.create().build();
        context = HttpClientContext.create();
        context.setCookieStore(cookieStore);
    }

    public String getPage(String symbol) { //Returns the page for which company you wish to download shares from
        String page = null;
        String url = String.format("https://finance.yahoo.com/quote/%s/?p=%s", symbol, symbol);
        HttpGet request = new HttpGet(url);
        request.addHeader("User-Agent", "Mozilla/5.0 (X11; U; Linux x86_64; en-US; rv:1.9.2.13) Gecko/20101206 Ubuntu/10.10 (maverick) Firefox/3.6.13");

        try {
            HttpResponse response = client.execute(request, context);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer result = new StringBuffer();
            String line;

            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            page = result.toString();
            HttpClientUtils.closeQuietly(response);

        } catch (Exception el) {
            System.out.println("Error while visiting the url" + url);
        }
        return page;
    }

    /*public OHLCDataItem[] getData (String symbol){
        List<OHLCDataItem> dataItems = new ArrayList<>();

    }*/

    public List<String> splitPageData(String page) {
        return Arrays.asList(page.split("}"));

    }

    public String findCrumb(List<String> lines) {
        //crumb is a personal browser cookie.
        //without this the page will not load within software
        String crumb = "";
        String rtn = "";

        for (String l : lines) {
            if (l.contains("CrumbStore")) {
                rtn = l;
                break;
            }
        }

        if (rtn != null && !rtn.isEmpty()) {
            String[] vals = rtn.split(":");                 // get third item
            crumb = vals[2].replace("\"", ""); // strip quotes
            crumb = unescapeJava(crumb);

        }
        return crumb;
    }

    public String getCrumb(String symbol) { //Finds special cookie to allow for downloading data from software
        return findCrumb(splitPageData(getPage(symbol)));

    }

    public void downloadData(String symbol, long startDate, long endDate, String crumb) { //Downloads the data from yahoo by taking the company symbol, the startdate, enddate and the special cookie crumb
        String filename = String.format("%s.csv", symbol.toUpperCase());
        String url = String.format("https://query1.finance.yahoo.com/v7/finance/download/%s?period1=%s&period2=%s&interval=1d&events=history&crumb=%s", symbol, startDate, endDate, crumb);
        HttpGet request = new HttpGet(url);
        request.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:56.0) Gecko/20100101 Firefox/56.0");

        try {
            HttpResponse response = client.execute(request, context);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                BufferedInputStream inStream = new BufferedInputStream(entity.getContent());
                BufferedOutputStream outStream = new BufferedOutputStream(new FileOutputStream(new File(filename)));
                int inByte;

                while ((inByte = inStream.read()) != -1) {
                    outStream.write(inByte);
                }
                inStream.close();
                outStream.close();
            }
            HttpClientUtils.closeQuietly(response);

        } catch (Exception el) {
            System.out.println("Error while downloading data");
        }
    }
}