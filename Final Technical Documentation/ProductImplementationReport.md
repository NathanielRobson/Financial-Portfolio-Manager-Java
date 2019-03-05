# Team Implementation Report
**TODO**
*This section should describe the technical details of your implementation.  The subheadings and italicised text below may be used to guide you.*  


## Techical Diagrams

**TODO - sequence diagram?**
*Inlcude a class diagram / circuit diagram, and/or any other relevant technical diagrams.*    

This is an in depth class diagram made in IntelliJ, demonstrating all of the classes used in our program.  
Click to open in a new tab and view in higher detail.  
![ClassDiagram](/README-Images/ClassDiagram.png)  

I have also created a use case diagram for our program. In this diagram we can see the user requesting to buy a share and also view their total financial worth.  
![UseCaseDiagram](/README-Images/UseCaseDiagram.jpg)  

## Technical Description
**TODO**
*This section should describe the software implementation in prose form.  Focus on how the code was designed and built.* 
*It should make a clear description that could be used by any future developers to maintain and extend your code, if necessary.*
*Describe important functions / classes / class hierarchies.*
*In this section, you should also wish to highlight any technical achievements your team is particularly proud of, including relevant code snippets.*

## Algorithms and Data Structures

**TODO - needs more**
*Describe datastructures of at least one component of your implementation.*  

In our program there are many different data structures used here are some of the examples:  
In our program there is a class called CSVtoArrayService which sole purpose it to take the downloaded CSV file name and read the data in the file and output into an Array for further program data manipulation.  
To view the class [click here](/src/CSVtoArrayService.java)  
Below is a snippet of the method in the class which demonstrates the creation of the Arrays.  
````java
    //Array Initialisation
    List<String> datearray = new ArrayList<>(Arrays.asList());
    List<String> openarray = new ArrayList<>(Arrays.asList());
    List<String> higharray = new ArrayList<>(Arrays.asList());
    List<String> lowarray = new ArrayList<>(Arrays.asList());
    List<Double> closevaluearray = new ArrayList<>(Arrays.asList());
    List<String> adjclosearray = new ArrayList<>(Arrays.asList());
    List<Long> volumearray = new ArrayList<>(Arrays.asList());

    void CSVtoArray(String file) {
        CSVReader reader;

        //Adds each value to an individual array for future queries
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
````  
Once the CSV has been read, they are then added into the ArrayLists 'datearray', 'openarray', 'higharray', 'lowarray', 'closevaluearray', 'adjclosearray', 'volumearray'.  
This class is not complex which allows it to output the data in the file into the Arrays quickly and efficiently.  
This class only uses the Add operation.  

* [Add()] O(1) - Adding to the ArrayList is O(1) time where O is the approximate time for the given task and O(1) is constant time.  
* [Get()] O(1) - Retrieving an element is always a constant time O(1).  
* [Remove()] O(n) - however we do not remove from the arrays in our program, only overwrite.
* [IndexOf()] O(n) - this is because when checking it will iterate through n items in the array.  
* [Contains()] O(n) - this will run based on indexOf so it will run O(n) as above.  

*Describe at least one algorithm used in your implementation.*
*In both cases, describe the space / time complexity of each.*

## Imported Libraries 

These are the dependencies used in our program.  
All depency .jar files can be found in the ['lib'](/lib) folder.  

* [Apache Commons Codec](https://commons.apache.org/) - 'commons-codec-1.11'  
Interfaces and classes used by the various implementations in the sub-packages.  

* [Apache Commons Lang](https://commons.apache.org/) - 'commons-lang3-3.8.1'  
Provides highly reusable static utility methods, chiefly concerned with adding value to the java.lang classes.  
Assisted in creating consistent equals(Object), toString(), hashCode(), and compareTo(Object) methods.  
Provides some useful event-based utilities.  
Provides functionality for Exceptions.  

* [Apache Commons Logging](https://commons.apache.org/) - 'commons-logging-1.2'  
Provides functionality for logging exceptions for Java API's.  

* [Apache HttpClient Fluent API](https://hc.apache.org/) - 'fluent-hc-4.5.7'
* [Apache HttpClient 4.5.7](https://hc.apache.org/) - 'httpclient-4.5.7', 'httpclient-cache-4.5.7', 'httpclient-osgi-4.5.7', 'httpclient-win-4.5.7'
* [Apache HttpCore](https://hc.apache.org/) - 'httpcore-4.4.1
* [Apache HttpClient Mime](https://hc.apache.org/) - 'httpmime-4.5.7'  
These dependencies provide web implementation language function for our program. This allows us to use HTTP language to be used to download information from Yahoo Finance.  * [iText](https://itextpdf.com/en) - 'itext-1.3.1'  

* [JFree JCommon](http://www.jfree.org/jcommon/) - 'jcommon-1.0.23'
* [JFree JFreeChart](http://www.jfree.org/jfreechart/) - 'jfreechart-1.0.23', 'jfreechart-1.0.19-demo'  
These dependencies allow for graph implementation in our program when viewing the history of the users finances.  

* [Java Native Access](https://github.com/java-native-access/jna) - 'jna-4.5.2', 'jna-platform-4.5.2'  
Automatic conversion between C and Java Strings.  

* [Maven OpenCSV](http://opencsv.sourceforge.net/) - 'Opencsv-4.4'  
OpenCSV allows for more efficient reading of CSV files and more efficient writing to them. Instead of individually assigning each cell, you can use a String Array which will automatically be split by a comma delimeter for efficient editing.  

* [Knowm XChart](https://knowm.org/open-source/xchart/) - 'xchart-3.5.2', 'xchart-demo-3.5.2'  
This is used to create Graph scenarios in our program and to allow the user to simulate finances from any company listed on Yahoo Finance.  

## Known Issues

Currently known issues:  

If the user has just started and has no data on their account in our program. The view account history line chart will not display data.  
It will display data if the user's account is more than one day old because it works on a date basis. The chart cannot shot increase/decrease over time if there is not more than 1 day to display.  
Thankfully all of the accounts which are in the program have data on file and they have history.   
If you log into one with out enough data (more than 1 day) then switch user to 'NR' with the password 'teamNR'.   (Resolution)

