# Team Implementation Report
## Technical Diagrams
### Class Diagram  
This is an in depth class diagram made in IntelliJ, demonstrating all of the classes used in our program.  
Click to open in a new tab and view in higher detail.  
![ClassDiagram](/README-Images/ClassDiagram.png)   

### Use Case Diagram
I have also created a use case diagram for our program. In this diagram we can see the user requesting to buy a share and also view their total financial worth.  
![UseCaseDiagram](/README-Images/UseCaseDiagram.jpg)  

### CRC Cards
I have also created three CRC cards for demonstration purposes.  
![CRCcards](/README-Images/CRCcards.jpg)  

### Type Diagram
Here is a Type Diagram which I have created to aid in demonstrating the program.  
![TypeDiagram](/README-Images/TypeDiagram.jpg)  

## Technical Description
The software is built using IntelliJ IDE using Java version 11.0.2.  The software allows a user who has access to the program to buy, sell, manipulate, simulate scenarios, make forecasts, make predictions based on analytical evidence, view organizations shares over time, view organizations current value and also view the users financial worth and allows them to then manipulate that data further. There may be more than one user who can use the software as the 'members.csv' contains credentials of multiple individuals.  

Undeniably this task was extremely difficult and many complications were at hand throughout. The most important part of making a program is ensuring that it meets the user requirements and that there is relevant testing to assess this throughout. Without regular assessment, the program can end up taking paths further and further away from its main purpose and even in some cases in the end it can end up a completely different product. To ensure that this was not the case, in our weekly scrum meetings we made sure each of us gave feedback on the program in its current state and providing support and assistance to others when possible.  

Designing this program was a very tough task as in the beginning many of the team members struggles to envision what they were aiming for. In all honesty the task seemed out of reach for many of us as individuals as we felt like it was a very tough and complex task. However given enough time and support from each other we were able to begin making functional frames which served major purposes in the program. Once we had our first frame made with functionality and data manipulation it gave each individual involved higher hopes for the program and we each set out to improve the software further. As time went on more and more exceptional functionality such as login validation, graph scenario builders and live imported data was being produced to a high standard. Once we incorporated these complex but refined methods we felt as if our product was coming together finally.  To aid in development further in our weekly scrum meetings we decided to invest time into creating diagrams to ensure that each individual had the same view of the program in there heads. This is an important factor, everyone needs to understand what the main goal is and what needs to be done to reach it. The diagrams such as Use Case diagrams and CRC cards helped us model our software and build a foundation for further implementation.  

Future maintainers of our code should note that because we implement and manipulate data from Yahoo finance, there is a high chance that the program will need updating if Yahoo decide to adjust the way their website displays information. If it is changed then the code will need to be adjusted to follow this. Yahoo Finance stopped API functionality because effectively they were stealing data with or without knowing this. They were forced to prevent further support for this as they breached many laws in doing so.

Yahoo did not intend to make the YahooFinance service for Developers however the majority of users weren't users, they were developers using software exporting the data to CSV files like done in our program. Because Yahoo did not intend this for developers it was in fact a breach in its own terms of service. Every single developer who exported data through software was breaching their terms of service also. To resolve this Yahoo Decided it was best to close the whole service down altogether.
[Read more here, there are many articles available online which explain this](https://www.programmableweb.com/news/oh-about-api-yahoo-killed-without-warning-developers/analysis/2017/11/10)  

Our classes are coded and named to allow for efficient reading and understanding by any one who is interested.  
The classes are named depending on what they contain for example, all classes ending in 'Frame' extend JFrame and all those which end in 'Service' are important methods which are called upon more than once throughout the program. They provide functionality to the 'Frame' classes.  

#### Our team are particularly proud of the following methods, classes and code snippets found below.
### Total Value
The following code snippet involves reading the users purchases shares, downloading the most up to date price available from the Internet for each company share and then calculating the users total financial worth and then finally displaying it in an easy to understand format to the user.  

````java
        //Takes the two lists from the text area to produce the total value of the user
        private void calculateValue(ArrayList<Integer> shareList, ArrayList<String> symbolList) {
            CSVUpdateService quoteClass;
            quoteClass = new CSVUpdateService();
            try {

                //For every value in the symbol list, update the price of each company and add the total value to the next total value
                for (int i = 0; i < symbolList.size(); i++) {
                    String symbol = symbolList.get(i);
                    String crumb = quoteClass.getCrumb(symbol);

                    if (crumb != null && !crumb.isEmpty()) {
                        errorLabel.setText(String.format("<html>Updated Price using the symbol '%s'<html/>", symbol.toUpperCase()));
                        errorLabel.setForeground(Color.red);
                        quoteClass.downloadData(symbol, 0, System.currentTimeMillis(), crumb);
                        updatePrice(symbol);

                    } else {
                        errorLabel.setText("Unable to update price using the Symbol: " + symbol.toUpperCase());
                    }
                    System.out.println(shareList.get(i));
                    Double d = price * shareList.get(i);
                    totalValue += d;
                    System.out.println(totalValue);

                }
            } catch (Exception el) {
                errorLabel.setText("Unable to update, error occurred, Check Internet Connection");
            }
        }

        //Method to create two arrays from the JTextArea, symbol array and share value array
        private void ArrayMaker(ArrayList<Integer> shareList, ArrayList<String> symbolList) {
            String[] strArr = theApp.sharesArea.getText().split("\n");
            for (String s : strArr) {
                int price = Integer.parseInt(s.substring(s.indexOf(":")).replaceAll(":", ""));
                String[] shareName = s.substring(0, s.indexOf(":")).substring(0, s.indexOf(" ")).replaceAll(" ", "").split("\n");
                symbolList.add(shareName[0]);
                shareList.add(price);
            }
        }
````

### Login Validation - LoginFrame, loginService
If the user presses the Login button on the Login Frame, the function below will execute. This will ensure that the username and password are equal to those found in the file 'members.csv'.  
````java
            if (action == 2) { //Login button Validation
                String userName;
                String passWord;
                userName = userField.getText().trim();
                passWord = passField.getText().trim();
                loginservice = new loginService();

                try {
                    if (!(userName.equals("") || passWord.equals(""))) {
                        //Runs loop through list of members to find input value match
                        for (int i = 0; i < loginservice.userNames.size(); i++) {
                            if (loginservice.userNames.get(i).equals(userName) && (loginservice.passWords.get(i).equals(passWord))) {//Checks members.csv file for the user account
                                theApp.setCurrentUser(userName);
                                new MenuFrame(userName);

                                LoginFrame.this.dispose();
                            } else {
                                errorLabel.setText("Incorrect login Details, Please Enter Correct Username and Password");
                            }
                        }
                    } else {
                        errorLabel.setText("Error Username or Password Field is Empty or Incorrect");
                    }
                } catch (Exception el) {
                    errorLabel.setText("Error Username or Password Field is Empty or Incorrect");
                }
            }
````

The above method calls the LoginService class (loginservice as above) to iterate through the 'members.csv' file and find the user which is attempting to log in.  If the user does not exist, the program will not allow the user to login as they will not have authorized access.  
If the file 'members.csv' does not exist the file is created and input with the default values which is the usernames and passwords of my team members.  

Below we can see the LoginService class method which is called upon to provide the user credentials.  
````java
    loginService() { //Reads the members.csv file to provide array list and splits the data into new arrays
        BufferedReader reader;
        BufferedWriter writer;
        try {
            if (file.exists()) {
                reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] users = line.split(",");
                    userNames.add(users[0]);
                    passWords.add(users[1]);
                }
                System.out.println("User File Read Successfully");
            } else {
                String users = "NR,teamNR,\nED,teamED,\nDC,teamDC,\nKM,teamKM,\nCK,teamCK,\nKL,teamKL";
                writer = new BufferedWriter(new FileWriter(file));
                writer.write(users);
                writer.close();
                System.out.println("No File Existed, File Has Been Created in //UserAccounts/members.csv");
            }
        } catch (Exception el) {
            System.out.println("Error While Trying to Parse Login Details");
        }
    }
````

If the file exists the data is read using the comma delimiter. It is then split into a String Array for further manipulation.  

### ButtonHandler
In every frame where there is a button there is a separate class called ButtonHandler which allows for much more efficient coding when applying event listeners to buttons.  
Every button is linked to an event handler which will perform an action based on the assigned 'action'.  
Below is a snippet from the 'MenuFrame' class which assigns 10 actions to 10 different methods (of an int value) for each button on the frame.  

````java

    MenuFrame() { 

        ... method contents...   

        //Action Listeners assigning actions to each button
        logOutBtn.addActionListener(new ButtonHandler(this, 1));
        displayTable.addActionListener(new ButtonHandler(this, 2));
        searchDate.addActionListener(new ButtonHandler(this, 3));
        updateBtn.addActionListener(new ButtonHandler(this, 4));
        viewGraph.addActionListener(new ButtonHandler(this, 5));
        purchaseBtn.addActionListener(new ButtonHandler(this, 6));
        bankBtn.addActionListener(new ButtonHandler(this, 7));
        addSharesBtn.addActionListener(new ButtonHandler(this, 8));
        viewHistoryBtn.addActionListener(new ButtonHandler(this, 9));
        ownedSharesBtn.addActionListener(new ButtonHandler(this, 10));
    }
}

    //Button Handler
    class ButtonHandler implements ActionListener { //Implements the action listener
        MenuFrame theApp;
        int action;
        CSVUpdateService getupdate;

        ButtonHandler(MenuFrame app, int action) {
            this.theApp = app;
            this.action = action;
        }

        //Individual button actions
        @Override
        public void actionPerformed(ActionEvent e) {
            if (action == 1) { //Log out button
                new LoginFrame();
                MenuFrame.this.dispose();
            }
            if (action == 2) { //Display table of selected company and download new data if the file doesnt exist
                displayTable();
            }
            if (action == 3) {//Search a selected date for a selected company
                searchDateCompany();
            }
            if (action == 4) { //Search Stock Company and Download Data Dedicated frame for functionality
                new CSVUpdateFrame();
            }
            if (action == 5) {//Graph of selected company and selected time period
                new GraphBuilderFrame();
            }
            if (action == 6) {//Purchase Stocks from companies most recent value
                new BuySellSharesFrame(theApp.theCurrentUser);
            }
            if (action == 7) {//View Bank Account and Withdraw and Deposit Funds
                new BankAccountFrame(theApp.theCurrentUser);
            }
            if (action == 8) { //Add Share to Account
                new AddSharesFrame(theApp.theCurrentUser);

            }
            if (action == 9) { //View Line Chart of Account Purchases and Sales
                new LineChartService(
                        "Financial Portfolio Manager Personal Value Chart",
                        "History of Investment Chart", theApp.theCurrentUser);
            }
            if (action == 10) {
                new FinancialPortfolioFrame(theApp.theCurrentUser);
            }
        }
````

### Reading and Writing to CSV Efficiently - OpenCSV implementation
Using the publicly available dependency 'OpenCSV' found online, I was able to create methods which read and write to CSV files a lot more efficiently and fluently instead of using clunky split/regex/contains methods.  
The two methods below read CSV files very efficiently and write to them too.  These methods are implemented within the 'FinancialPortfolioFrame' to read the data of the current user in the '/UserPortfolios' file. If the user data file doesn't exist it automatically creates it and sets the users current money to '10000' to allow for program data manipulation and further scenario creation.  
````java
        private void readPersonalPortfolio() throws IOException {
            File file = new File(".//UserPortfolios/" + getTheCurrentUser() + "-BANK.csv");

            if (file.exists()) {
                CSVReader reader = new CSVReader(new FileReader(file));
                String[] line = reader.readNext();
                while (line != null) {
                    System.out.println();
                    userMoney = line[1].trim().replaceAll("\"", "");
                    line = reader.readNext();
                }
                setCurrentMoney(userMoney);

                //If the file doesnt exist, it creates it and sets the bank starting value at 10000
            } else if (!file.exists()) {
                String startingcash = "10000";
                String[] uservalues = new String[]{getTheCurrentUser(), startingcash};

                try {
                    writeToCsv(file, uservalues);
                } catch (Exception el) {
                    errorLabel.setText("unable to write data to file");
                }
                readPersonalPortfolio();
            }
        }

        //Writes to CSV if file does not exist
        private void writeToCsv(File file, String[] details) throws IOException {
            FileWriter fileWriter = new FileWriter(file);
            CSVWriter csvWriter = new CSVWriter(fileWriter);
            System.out.println();
            csvWriter.writeAll(Collections.singleton(details));
            System.out.println();
            fileWriter.flush();
            csvWriter.close();
        }
    }
````

## Algorithms and Data Structures
### CSVtoArrayService - Arrays
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
Once the CSV has been read by the method input, the data is then added into the ArrayLists 'datearray', 'openarray', 'higharray', 'lowarray', 'closevaluearray', 'adjclosearray', 'volumearray'.  
This class is not complex which allows it to output the data in the file into the Arrays quickly and efficiently.  
This class only uses the Add operation. 

### StockGraphService - List<String> dateArrayFromFile, List<Double> valueArrayFromFile
In the code snippet below, the data that is parsed to the method includes a filename, and two integers. The String is is to allow the user to decide what company financial data they wish to display in a graph.  
The two int values are to allow the user to select dates of which they wish to display in the graph, e.g Company 'aapl', From '30'(days ago), To '10'(days ago). This will display the 20 days in between to the user in a graph format.  
Using these two methods to return two separate arrays allows for the creation of a custom graph or chart based on the users input.  
````java
class StockGraphService { //Stock Graph Service

    private CSVtoArrayService toArray = new CSVtoArrayService();

    private List<String> dateArrayFromFile(String file, int from, int to) { //From the end of the date array it selects the chosen number of days to display on the graph
        toArray.CSVtoArray(file);
        ArrayList<String> datetail = new ArrayList<>();
        int size = toArray.datearray.size();

        for (int i = size - to - 1; i <= size - from; i++) {

            datetail.add(toArray.datearray.get(i));
        }
        return datetail;
    }

    private List<Double> valueArrayFromFile(String file, int from, int to) { //From the end of the close value array it selects the chosen number of days to display on the graph
        toArray.CSVtoArray(file);
        ArrayList<Double> valuetail2 = new ArrayList<>();
        int size = toArray.closevaluearray.size();

        for (int i = size - to - 1; i <= size - from; i++) {
            valuetail2.add(toArray.closevaluearray.get(i));
        }
        return valuetail2;
    }
````

#### Time Complexity
* [Add()] O(1) - Adding to the ArrayList is O(1) time where O is the approximate time for the given task and O(1) is constant time.  
* [Get()] O(1) - Retrieving an element is always a constant time O(1).  
* [Remove()] O(n) - however we do not remove from the arrays in our program, only overwrite.
* [IndexOf()] O(n) - this is because when checking it will iterate through n items in the array.  
* [Contains()] O(n) - this will run based on indexOf so it will run O(n) as above.  

Above is the standard Java time complexity for Arrays. In our program when we want to find a specific date to display in graph format this uses the IndexOf() time complexity, finding the specified data required to display will take O(n) time where 'n' is the amount of elements in the array, so if we wanted to view the most recent date for a company we would have to find the element at the size of the array. O(sizeofarray) would be our time complexity here.

If we wanted to add user data to an array in our program we would take into consideration the Add() time complexity which is O(1) where '1' is the element of which we are adding to the array. If we were intending to add more than one item, eg. 2 it would take 2xO(1) as we are still performing the same function 'Add()', only we are performing it twice.

The above code displayed uses the IndexOf() function meaning that the time complexity would be O(n) where n is the date/element we wish to find. The function will run O(n) times until 'n' is found. This means that if the element which we are searching for is at the last element of the array, the longest time complexity of this function could only be O(sizeofarray) whereas if we wanted the first element in the array this would only be O(1).

## Imported Libraries 

These are the dependencies used in our program.  
All dependency .jar files can be found in the ['lib'](/lib) folder.  

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
If you log into one with out enough data (more than 1 day) then switch user to 'NR' with the password 'teamNR'.   
(Partially Resolved)

We do not currently have a reset function so all data stored on the user is permanant. There is however a work around. Either delete the files in the 'UserShares' folder and the 'UserPortfolios' folder to wipe the data or as an alternative, open the corresponding account data file found in 'Userportfolios' and remove as many lines as you please.   
(Partially Resolved)
