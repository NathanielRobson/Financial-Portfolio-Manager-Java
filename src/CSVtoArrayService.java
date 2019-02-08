import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVtoArrayService {
    //Testing with Main
    public static void main(String[] args) {
        CSVtoArrayService c = new CSVtoArrayService();
        c.CSVtoArray("NFLX.csv");
    }

    //Each array is initialised
    List<String> datearray = new ArrayList<>(Arrays.asList());
    List<String> openarray = new ArrayList<>(Arrays.asList());
    List<String> higharray = new ArrayList<>(Arrays.asList());
    List<String> lowarray = new ArrayList<>(Arrays.asList());
    List<Double> closevaluearray = new ArrayList<>(Arrays.asList());
    List<String> adjclosearray = new ArrayList<>(Arrays.asList());
    List<Long> volumearray = new ArrayList<>(Arrays.asList());

    void CSVtoArray(String file) {
        CSVReader reader;

        try {
            reader = new CSVReader(new FileReader(file));
            String[] line = reader.readNext();
            boolean begin = false;

            while (line != null) {
                while (!begin) {
                    line = reader.readNext();
                    begin = true;
                }
                if (line[0].contains("null") || line[1].contains("null") || line[2].contains("null")
                        || line[3].contains("null") || line[4].contains("null") || line[5].contains("null")
                        || line[6].contains("null")) { //This checks whether csv contains "null", if yes then it replaces it.
                    line[0] = "0";
                    line[1] = "1";
                    line[2] = "2";
                    line[3] = "3";
                    line[4] = "4";
                    line[5] = "5";
                    line[6] = "6";
                    System.out.println("CSV contains a null value, Exception handled");
                }
                //Adds each value to an individual array for future queries
                datearray.add(line[0]);
                openarray.add(line[1]);
                higharray.add(line[2]);
                lowarray.add(line[3]);
                closevaluearray.add(Double.valueOf(line[4]));
                adjclosearray.add(line[5]);
                volumearray.add(Long.parseLong(line[6]));

                line = reader.readNext();
            }
        } catch (IOException e) {
            System.out.println("Error, Unable to parse file to arrays.");
        }
    }

    public ArrayList<String> getDate() {
        return (new ArrayList<>(datearray));
    }

    public ArrayList<String> getOpen() {
        return new ArrayList<>(openarray);
    }

    public List<String> getHigh() {
        return (new ArrayList<>(higharray));
    }

    public List<String> getLow() {
        return (new ArrayList<>(lowarray));
    }

    public ArrayList<Double> getClose() {
        return (new ArrayList<>(closevaluearray));
    }

    public List<String> getAdjClose() {
        return (new ArrayList<>(adjclosearray));
    }

    public List<Long> getVolume() {
        return (new ArrayList<>(volumearray));
    }


}
