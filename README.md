Financial Portfolio Manager

This project involved creating from scratch a Java application which has the ability to track and display investments of a client over time.  
The client can also purchase and sell shares through the program and at any one time the client can hold multiple shares for multiple different companies.  
The client can create and view graph scenarios for different companies, with live data demonstrating different outcomes to buying and selling at certain points in time.
For example if the client would like to view the share price history for the company Netflix, they can do so and thus this will allow them to decide whether or not  
to invest in the company. The client can view this in two formats, a table created by our program or in graph format.  
The client can view which shares they hold and also view how much they are worth at any given time.  
The program also has a banking system which starts at '$10,000' and this allows the user to simulate scenarios for investments by purchasing or selling company shares.  
The client can deposit more money if required and can also withdraw if need be.  
The client can also add pre purchased shares into the program to track and display data throughout.  
The program can display the clients Financial Portfolio, display the clients Total Worth and Increase in value since starting their profile.  
The program downloads up to date company shares data from yahoo.finance to allow scenario and shares manipulation over our financial program.  

### How To Get Started - Step by Step

## Logging in

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.  
To run our program successfully, download our repository and execute/run the file named 'ProgramJAR.jar'.  
![Screenshot](/README-Images/jarlocation.jpg)  

If you would like to run it from the src folder in an IDE like IntelliJ then you can run the class 'MAIN'.  Both of the stated options to run the program perform well and as expected.  
![Screenshot](/README-Images/runmain.jpg)   

This will require setting the module depencencies and adding all of the files in the 'lib' folder as dependencies. Please follow a tutorial on how to add project dependencies online.  

You will then be greeted with our 'Login' page of which you will need to enter your login details. 
For testing purposes you may use the username 'NR' and the password 'teamNR' (case sensitive).  
View the user accounts file to retain a username and password, so you can make use of our program click this link [here!](/UserAccounts/members.csv)  
![Screenshot](/README-Images/loginimg.jpg)  

## Menu Page

After logging in you will arrive at our 'Menu' page from which you can select from many different options. 

 ![Screenshot](/README-Images/menupage.jpg)  


1.  View Personal Portfolio & Owned Shares
2.  Purchase or Sell Shares
3.  Add Already Owned Shares
4.  Personal Bank Account Deposit/Withdraw Money
5.  View Account History
6.  View up to Date Company Graphs
7.  Download up to Date Shares Information
8.  Search a Date - View Company Share Data
9.  Display Table of Shares
10.  Log out or Switch User

#### View Personal Portfolio & Owned Shares  

![Screenshot](/README-Images/viewbtn.jpg)  

Upon clicking the first button you will be see all of the shares you currently own.  
If you do not own any shares then nothing will be displayed here.  
![Screenshot](/README-Images/ownedsharesimg.jpg)  
  

The button 'View The User: 'CurrentUser's Personal Portfolio' toward the bottom of the frame will allow the user to display their own portfolio information, for example:  
![Screenshot](/README-Images/portfolioimg.jpg)  

#### Purchase or Sell Shares  

![Screenshot](/README-Images/buybtn.jpg)  

The second button will bring you to the purchase and sell frame. From here you can buy and sell shares from any of the companies listed on Yahoo Finance.  
By inputting the company symbol and then selecting 'Get Current Price' you can get the current price per share and purchase or sell however many you wish. 
If you make a profit in a sale you will be notified as well as if you make a loss you will be notified.  
The message displayed will inform you of how much profit/loss per share was made.  
![Screenshot](/README-Images/buyimg.jpg)  

This is what you will be greeted with when selling share(s) on the same frame.   
![Screenshot](/README-Images/sellimg.jpg)  


#### Add Already Owned Shares  

![Screenshot](/README-Images/addbtn.jpg)  

Clicking the third button will bring you to a page where you can add your previously purchased shares to the program to allow you to manipulate and create scenarios for personal use.    
This is what will appear. Below is the also the type of input that should be used for successful output.    
![Screenshot](/README-Images/addshareimg.jpg)  

#### Personal Bank Account Deposit/Withdraw Money

![Screenshot](/README-Images/bankbtn.jpg)  

Upon clicking the Personal Bank Account button you will be greeted with a simple yet functional frame to allow the user to input an amount to either deposit in or withdraw from your account.  
There are also instructions on the frame for use.  
![Screenshot](/README-Images/bankimg.jpg)  

#### View Account History

![Screenshot](/README-Images/historybtn.jpg)  

The 'View Account History' button creates a graph using the history of the users purchases and sales on different dates.  
Be aware if the graph will need data from more than one day to be effective.  
Below is a screenshot of the history for the user 'NR'.
![Screenshot](/README-Images/pastimg.jpg)  

#### View up to Date Company Graphs

![Screenshot](/README-Images/graphbtn.jpg)  

Upon clicking the 'View up to Date Company Graphs' button a new frame will be displayed from which the user is able to input a company symbol ticker and a number of days to display on a graph.  
The graphs are built by using up to date information. The program will automatically download the required data when performing specific actions such as building a graph or buying shares.  

For creating a graph scenario three inputs are required 'From', 'To' and 'Range'.   
![Screenshot](/README-Images/graphframeimg.jpg)   


Some Symbol Examples: Apple = 'aapl', Netflix = 'NFLX', Microsoft = 'MSFT', Twitter = 'TWTR', FaceBook = 'FB'.  
'From' - How many days ago?, 'To' - Until when?, 'Range' - Graph range.  
E.g 'From' 30 days ago, 'To' 10 days ago will display the 20 days inbetween, 'Range' at 149 will set the graph Y axis Minimum.  
This allows for more accurate scenario simulation.  
![Screenshot](/README-Images/graphimg.jpg)  

As you can see in the image below the data in our program provides the same information as Google Finance for the past 20 days.  
![Screenshot](/README-Images/googfinanceimg.jpg)  

#### Download up to Date Shares Information

![Screenshot](/README-Images/downloadbtn.jpg)  

The 'Download up to Date Shares Information' button will bring the user to a new frame upon which the user can input a company symbol to download the latest shares information.  
The data downloaded is the full history up to the current date of the companies shares.  
![Screenshot](/README-Images/updateimg.jpg)  

Once the data has been downloaded, it will output the information to a .CSV file in the program's root directory.  
![Screenshot](/README-Images/nflxfileimg.jpg)  

Upon opening the file you will see the full history of the companies shares information.   
![Screenshot](/README-Images/nflxcsvimg.jpg)  

#### Search a Date - View Company Share Data

![Screenshot](/README-Images/searchbtn.jpg)  

'Search a Date - View Company Share Data' with this button you are greeted with a JOptionPane asking for a company symbol. For demonstration purposes I will use Netflix 'NFLX'.  
![Screenshot](/README-Images/searchdateimg.jpg)  

Another JOptionPane will appear asking for your specified search date E.g '22/02/2019' is required to be input like so '2019-02-22'.  
![Screenshot](/README-Images/dateinputimg.jpg)  

Once both inputs have been successfully filled in, the program will display data found for that specific date in a new JFrame containing a JTextArea with the following information:  
![Screenshot](/README-Images/outputdateimg.jpg)  

#### Display Table of Shares

![Screenshot](/README-Images/displaybtn.jpg)  

The 'Display Table of Shares' button will display the company of your choosing's CSV in a table format in a new frame for you to navigate and investigate.  
A JOptionPane will appear like before asking which company you would like to display, simply input the company symbol/ticker of choice E.g 'NFLX' and the table will be created, like so:  

![Screenshot](/README-Images/tableoutputimg.jpg)  

#### Log out or Switch User

![Screenshot](/README-Images/logoutbtn.jpg)  

And finally the 'Log out or Switch User' button pretty much does what it says on the tin.  It allows the user to log out and or switch user accounts.  

## Built With

* [IntelliJ](https://www.jetbrains.com/idea/) - Programming IDE used
* [Java](https://www.java.com/en/) - Java Version 11.0.2

## Dependencies
These are the dependencies used in our program.  
All depency files can be found in the ['lib'](/lib) folder.  

* [Apache Commons Codec](https://commons.apache.org/) - 'commons-codec-1.11'
* [Apache Commons Lang](https://commons.apache.org/) - 'commons-lang3-3.8.1'
* [Apache Commons Logging](https://commons.apache.org/) - 'commons-logging-1.2'
* [Apache HttpClient Fluent API](https://hc.apache.org/) - 'fluent-hc-4.5.7'
* [Apache HttpClient 4.5.7](https://hc.apache.org/) - 'httpclient-4.5.7', 'httpclient-cache-4.5.7', 'httpclient-osgi-4.5.7', 'httpclient-win-4.5.7'  
* [Apache HttpCore](https://hc.apache.org/) - 'httpcore-4.4.11'
* [Apache HttpClient Mime](https://hc.apache.org/) - 'httpmime-4.5.7'
* [iText](https://itextpdf.com/en) - 'itext-1.3.1'
* [JFree JCommon](http://www.jfree.org/jcommon/) - 'jcommon-1.0.23'
* [JFree JFreeChart](http://www.jfree.org/jfreechart/) - 'jfreechart-1.0.23', 'jfreechart-1.0.19-demo'
* [Java Native Access](https://github.com/java-native-access/jna) - 'jna-4.5.2', 'jna-platform-4.5.2'
* [Maven OpenCSV](http://opencsv.sourceforge.net/) - 'Opencsv-4.4'
* [Knowm XChart](https://knowm.org/open-source/xchart/) - 'xchart-3.5.2', 'xchart-demo-3.5.2'


## Known Issues

The only currently known issue is when displaying the users history.  
If the user has just started and has no data on their account in our program. The view account history line chart will not display data.  
It will display data if the user's account is more than one day old because it works on a date basis. The chart cannot shot increase/decrease over time if there is not more than 1 day to display.  
Thankfully all of the accounts which are in the program have data on file and they have history.   
If you log into one with out enough data (more than 1 day) then switch user to 'NR' with the password 'teamNR'.   (Resolution)

## Author

* **Nathaniel Jarvis Robson**

## License

This project is licensed under the University of Essex - see the [LICENSE.md](https://cseegit.essex.ac.uk/ce291/team3/teamproject/blob/master/LICENSE) file for more details.

## Acknowledgments

This program was intended for educational/personal use only. This product will not be redistributed for money or any other form of imbursement.

