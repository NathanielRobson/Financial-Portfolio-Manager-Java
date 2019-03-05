# Team Effort Log
*In this section, add a heading for each team member, and under it add the sub headings "Sprint 1, Sprint 2, ..., Sprint N", as illustrated below.  Each team member can be responsible for writing their own section.*

### Sprint 1 (University Week 3):
* *Include a URL to the most siginificant Jira tasks assigned to you in sprint 1, and state when (or whether) it was completed, and the hours spent.*
* *Example: "Link to Task 2.  It was set and completed in sprint 2.  It took 1 hour.  Extra relevant comment."*
* *To save time, just describe the main (e.g. no more than 3) Jira issues assigned to you in this sprint. You are free to pick which 3 to describe.*
* *Describe any relevant java coding completed by you in Sprint 1.  Also include permalinks to your gitlab commits.*
* *Include the university week number in the subheading, as above.*


## Nathaniel J Robson

### Sprint 10 (University Week 10)  
After the MVP we had many ideas of what we were going to implement next. I began to focus on the starting point of our program, the Login page which is what the user will see first. It is important that this works well and allows the only users who are authorised to use the program to log in.
[Jira Issue - (Login function/page)](https://cseejira.essex.ac.uk/browse/CE291T3-32) This was completed in week 14. With full functionality. Overall it took aroud 5 hours to complete and to ensure that there was no way to log in without havng specific credentials found in the 'members.csv' file. The algorithm I coded will continue to allow the specified users to log in even if the file does not exist, because it will write the file for you if it doens exist.

I was also assigned to create a function to allow users to be able to view their total value of their investment. This however could not be implemented until we had a system to store user data in a file to read later on.  
[Jira - User Requirement](https://cseejira.essex.ac.uk/browse/CE291T3-9)  

In the later weeks I take it upon myself to improve the program as much as I possibly could and added so many different functions which improve our program.  
### Sprint 11 (University Week 12)
In week 12 I was assigned to work on program bugs and debugging, also to create buttons for the main page (Menu page).  

We also decided to add the user requirements to Jira and expand on them to allow for more accurate program ideas and  implementations.  

[Jira - Debugging code](https://cseejira.essex.ac.uk/browse/CE291T3-15)  
[Jira - Program bugs](https://cseejira.essex.ac.uk/browse/CE291T3-40)   
[Jira - User Requirement](https://cseejira.essex.ac.uk/browse/CE291T3-11)
### Sprint 12 (University Week 18)
In week 18 many different implementations took shape in our program such as live shares data imported from the internet. This task was quite time consuming and very difficult as I would have to write from scratch a method which could gather data from Yahoo Finance and save it in a CSV file upon request. To do this I researched and read documents on how to use Java to access web pages and collect data using arrays. To collect the data, the class CSVUpdateService was created and it accesses YahooFinance with a signiture to make the website believe that the program is not software accessing the website but it is a user insteas, this way the data is easily downloaded and saved to a CSV file. Without this implementation into our program I believe that we may have not succeeded. [Jira - Online CSV Update](https://cseejira.essex.ac.uk/browse/CE291T3-52)  
This task took around 10 hours to implement successfully.  

I also set out to create a frame for the class DisplayTableService to allow users to view the table of shares imported from the internet. This was an adaptation of previous code. Callum created the class and I adjusted it to suit our needs more.  
[Jira - JFrame instead of JMessageBox](https://cseejira.essex.ac.uk/browse/CE291T3-50)  
This task too around 2 hours to implement.  

This week I also worked on creating functioning graphs spent a lot of time researching on how to get them to display relevent data to the user in a user friendly manour. I decided to use 'xChart' to create a graph and display data to the user. [xChart source here.](https://knowm.org/open-source/xchart/)  
To view the code for the Graphs [click here - Source Code](/src/StockGraphService.java)  
This task took me around 12-14 hours of work spread over the 2 weeks to correctly display the data and fully understand how to implement the graphs correctly.  


### Sprint 13 (University Week 19)
This week I believed it would be a good idea to start work on storing user data and credentials in a database, so i began development in that area. I created a database and a database class which allowed the user to store their credentials in, however upon demonstrating this to a supervisor I was advised that an alternate method of saving the data in a CSV file was perfect so I was advised to drop development for this class here, so I did. Had we implemented this further I believe that the program would have become over complex for the client and may be difficult to set up everytime a user wishes to use the program.  [Jira - Remove Database](https://cseejira.essex.ac.uk/browse/CE291T3-60)  

I continued to work on the program and implemented on it further by adding functionality to search a date in a file for the shares information on that day. This allowed the user to simulate past scenarios and view specific timed data. The code can be viewed [here](/src/SearchandViewService.java)  

This class was created by Callum earlier in development however to make it suit our needs further I decided to create a frame which displays the information requested by simply inputting the company name and a date. The previous implentation of this class required you to locate the file you wish to search a date under and then search the date. The display was also in a JMessageBox previously.  
### Sprint 14 (University Week 20) **TODO**

### Sprint 15 (University Week 22)
### Sprint 16 (University Week 23)

## Callum Kleber

### Sprint 10 (University Week 10)
### Sprint 11 (University Week 12)
### Sprint 12 (University Week 18)
### Sprint 13 (University Week 19)
### Sprint 14 (University Week 20)
### Sprint 15 (University Week 22)
### Sprint 16 (University Week 23)

## Daniel Callender

### Sprint 10 (University Week 10)
### Sprint 11 (University Week 12)
### Sprint 12 (University Week 18)
### Sprint 13 (University Week 19)
### Sprint 14 (University Week 20)
### Sprint 15 (University Week 22)
### Sprint 16 (University Week 23)

## Koranteng Lartey

### Sprint 10 (University Week 10)
### Sprint 11 (University Week 12)
### Sprint 12 (University Week 18)
### Sprint 13 (University Week 19)
### Sprint 14 (University Week 20)
### Sprint 15 (University Week 22)
### Sprint 16 (University Week 23)

## Elios Dauti

### Sprint 10 (University Week 10)
### Sprint 11 (University Week 12)
### Sprint 12 (University Week 18)
### Sprint 13 (University Week 19)
### Sprint 14 (University Week 20)
### Sprint 15 (University Week 22)
### Sprint 16 (University Week 23)

## Kyle Moody

### Sprint 10 (University Week 10)
### Sprint 11 (University Week 12)
### Sprint 12 (University Week 18)
### Sprint 13 (University Week 19)
### Sprint 14 (University Week 20)
### Sprint 15 (University Week 22)
### Sprint 16 (University Week 23)

