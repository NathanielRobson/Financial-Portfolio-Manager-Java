import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVtoArray {

    //Testing with Main
    public static void main(String[] args) {
        CSVtoArray c = new CSVtoArray();
        c.CSVtoArray("NFLX.csv");
    }

    //Each array is initialised
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
                if(line[0].contains("null")){ //This checks whether csv contains "null", if yes then it replaces it.
                    line[0] = "0";
                    System.out.println("Date null value replaced");

                } if (line[1].contains("null")){ //This occurs when the source where the csv has been downloaded from has a missing value
                    line[1] = "1";
                    System.out.println("Open null value replaced");

                } if (line[2].contains("null")){ //This loop effectively replaces the value to continue the program effectively
                    line[2] = "2";
                    System.out.println("High null value replaced");

                } if (line[3].contains("null")){
                    line[3] = "3";
                    System.out.println("Low null value replaced");

                } if (line[4].contains("null")){
                    line[4] = "4";
                    System.out.println("Close null value replaced");

                } if (line[5].contains("null")){
                    line[5] = "5";
                    System.out.println("Adj Close null value replaced");

                } if (line[6].contains("null")){
                    line[6] = "6";
                    System.out.println("Volume null value replaced");

                }
                //Adds each value to an individual array for future queries
                datearray.add(line[0]);
                openarray.add(line[1]);
                higharray.add(line[2]);
                lowarray.add(line[3]);
                closevaluearray.add(Double.valueOf(line[4]));
                adjclosearray.add(line[5]);
                volumearray.add(line[6]);

                line = reader.readNext();
            }
        } catch (IOException e) {
            System.out.println("Error, Unable to parse file to arrays.");
        }
    }
}
