import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVtoArray {

    public static void main(String[] args) {
        CSVtoArray c = new CSVtoArray();
        c.CSVtoArray("NFLX.csv");
    }

    List<String> datearray = new ArrayList<>(Arrays.asList());
    List<String> openarray = new ArrayList<>(Arrays.asList());
    List<String> higharray = new ArrayList<>(Arrays.asList());
    List<String> lowarray = new ArrayList<>(Arrays.asList());
    List<Double> closevaluearray = new ArrayList<>(Arrays.asList());
    List<String> adjclosearray = new ArrayList<>(Arrays.asList());
    List<String> volumearray = new ArrayList<>(Arrays.asList());

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
                datearray.add(line[0]);
                openarray.add(line[1]);
                higharray.add(line[2]);
                lowarray.add(line[3]);
                closevaluearray.add(Double.parseDouble(line[4]));
                adjclosearray.add(line[5]);
                volumearray.add(line[6]);

                line = reader.readNext();
            }
        } catch (IOException e) {
            System.out.println("Error, Unable to parse file to arrays.");
        }
    }
}
