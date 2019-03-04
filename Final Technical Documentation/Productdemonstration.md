# Product Demonstration Report  

## Running the Program  

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.  
To run our program successfully, download our repository and execute/run the file named 'ProgramJAR.jar'.  
![Screenshot](/README-Images/jarlocation.jpg)  

If you would like to run it from the src folder in an IDE like IntelliJ then you can run the class 'MAIN'.  Both of the stated options to run the program perform well and as expected.  
![Screenshot](/README-Images/runmain.jpg)  .

This will require setting the module depencencies and adding all of the files in the 'lib' folder as dependencies. Please follow a tutorial on how to add project dependencies online.  

## Logging in  

You will then be greeted with our 'Login' page of which you will need to enter your login details. 
For testing purposes you may use the username 'NR' and the password 'teamNR' (case sensitive).  
View the user accounts file to retain a username and password, so you can make use of our program click this link [here!](/UserAccounts/members.csv)  
![Screenshot](/README-Images/loginimg1.jpg)  

If the information input is incorrect, a label will be displayed to inform you of what error has occurred.  
![Screenshot](/README-Images/loginerror1.jpg)  

After logging in you will arrive at our 'Menu' page from which you can select from many different options.  

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

Upon clicking the first button you will be see all of the shares you currently own.  
If you do not own any shares then nothing will be displayed here.  
![Screenshot](/README-Images/ownedsharesimg.jpg)  
  

The button 'View The User: 'CurrentUser's Personal Portfolio' toward the bottom of the frame will allow the user to display their own portfolio information, for example:  
![Screenshot](/README-Images/portfolioimg.jpg)  

#### Purchase or Sell Shares  

The second button will bring you to the purchase and sell frame. From here you can buy and sell shares from any of the companies listed on Yahoo Finance.  
By inputting the company symbol and then selecting 'Get Current Price' you can get the current price per share and purchase or sell however many you wish. 
If you make a profit in a sale you will be notified as well as if you make a loss you will be notified.  
The message displayed will inform you of how much profit/loss per share was made.  
![Screenshot](/README-Images/buyimg.jpg)  

This is what you will be greeted with when selling share(s) on the same frame.   
![Screenshot](/README-Images/sellimg.jpg)  

If input is incorrect or the connection to the internet was lost then a label will appear informing you of this.  
![Screenshot](/README-Images/buyerror.jpg)
![Screenshot](/README-Images/buyerror2.jpg)

When selling shares ensure you have atleast one share to allow the process to complete.  
If the user has no shares for the symbol then the error label will inform the user that no shares are owned by them.  
![Screenshot](/README-Images/notowned.jpg)

#### Add Already Owned Shares  

Clicking the third button will bring you to a page where you can add your previously purchased shares to the program to allow you to manipulate and create scenarios for personal use.    
This is what will appear. Below is the also the type of input that should be used for successful output.    
![Screenshot](/README-Images/addshareimg.jpg)  

If the incorrect input is placed or the company symbol does not exist then the following error label will be displayed.  
![Screenshot](/README-Images/addshareerror.jpg)  

#### Personal Bank Account Deposit/Withdraw Money

Upon clicking the Personal Bank Account button you will be greeted with a simple yet functional frame to allow the user to input an amount to either deposit in or withdraw from your account.  
There are also instructions on the frame for use.  
![Screenshot](/README-Images/bankimg.jpg)  

If the user enters incorrect or invalid input this will be displayed.  
![Screenshot](/README-Images/depositerror.jpg)  

#### View Account History

The 'View Account History' button creates a graph using the history of the users purchases and sales on different dates.  
Be aware if the graph will need data from more than one day to be effective.  
Below is a screenshot of the history for the user 'NR'.
![Screenshot](/README-Images/pastimg.jpg)  

#### View up to Date Company Graphs

Upon clicking the 'View up to Date Company Graphs' button a new frame will be displayed from which the user is able to input a company symbol ticker and a number of days to display on a graph.  
The graphs are built by using up to date information. The program will automatically download the required data when performing specific actions such as building a graph or buying shares.  

For creating a graph scenario three inputs are required 'From', 'To' and 'Range'.   
![Screenshot](/README-Images/graphframeimg.jpg)   

If input is incorrect or not revelevant to the information required then a label will appear instructing you on how to fix the issue.  
Here you can see what is displayed if the input is invalid or incorrect.  
![Screenshot](/README-Images/grapherror.jpg)  
![Screenshot](/README-Images/grapherror2.jpg)  

Some Symbol Examples: Apple = 'aapl', Netflix = 'NFLX', Microsoft = 'MSFT', Twitter = 'TWTR', FaceBook = 'FB'.  
'From' - How many days ago?, 'To' - Until when?, 'Range' - Graph range.  
E.g 'From' 30 days ago, 'To' 10 days ago will display the 20 days inbetween, 'Range' at 149 will set the graph Y axis Minimum.  
This allows for more accurate scenario simulation.  
![Screenshot](/README-Images/graphimg.jpg)  

As you can see in the image below the data in our program provides the same information as Google Finance for the past 20 days.  
![Screenshot](/README-Images/googfinanceimg.jpg)  

#### Download up to Date Shares Information

The 'Download up to Date Shares Information' button will bring the user to a new frame upon which the user can input a company symbol to download the latest shares information.  
The data downloaded is the full history up to the current date of the companies shares.  
If input is incorrect or not revelevant to the information required then a label will appear instructing you on how to fix the issue.
Again you are required to input a company symbol ticker e.g 'AAPL' for the company Apple etc.  
![Screenshot](/README-Images/updateimg.jpg)  

Once the data has been downloaded, it will output the information to a .CSV file in the program's root directory.  
![Screenshot](/README-Images/nflxfileimg.jpg)  

Upon opening the file you will see the full history of the companies shares information.   
![Screenshot](/README-Images/nflxcsvimg.jpg)  

If the user inputs an incorrect company symbol or one that does not exist the following label will be displayed to the user.  
![Screenshot](/README-Images/downloaderror.jpg)  

#### Search a Date - View Company Share Data

'Search a Date - View Company Share Data' with this button you are greeted with a JOptionPane asking for a company symbol. For demonstration purposes I will use Netflix 'NFLX'.  
If input is incorrect or not revelevant to the information required then a label will appear instructing you on how to fix the issue.  
![Screenshot](/README-Images/searchdateimg.jpg)  

Another JOptionPane will appear asking for your specified search date E.g '22/02/2019' is required to be input like so '2019-02-22'.  
Again, if input is incorrect or not revelevant to the information required then a label will appear instructing you on how to fix the issue.  
![Screenshot](/README-Images/dateinputimg.jpg)  

Once both inputs have been successfully filled in, the program will display data found for that specific date in a new JFrame containing a JTextArea with the following information:  
![Screenshot](/README-Images/outputdateimg.jpg)  

If a date does not not exist within the CSV file then another JOptionPane will appear informing you of this stating that 'The Record has not been found'.  
![Screenshot](/README-Images/notfound.jpg)  

If the input for the symbol was invalid, incorrect or the company symbol doesn't exist then the following JOptionPane will appear.  
![Screenshot](/README-Images/symbolexists.jpg)  

#### Display Table of Shares

The 'Display Table of Shares' button will display the company of your choosing's CSV in a table format in a new frame for you to navigate and investigate.  
A JOptionPane will appear like before asking which company you would like to display, simply input the company symbol/ticker of choice E.g 'NFLX' and the table will be created, like so:  
If input is incorrect or not revelevant to the information required then a JOptionPane will appear instructing you that input is incorrect.  
![Screenshot](/README-Images/tableoutputimg.jpg)  

If the user inputs the incorrect or invalid information for the company symbol then the table will not display.  

#### Log out or Switch User

And finally the 'Log out or Switch User' button pretty much does what it says on the tin.  It allows the user to log out and or switch user accounts.  
This will bring the user back to the Login page.

