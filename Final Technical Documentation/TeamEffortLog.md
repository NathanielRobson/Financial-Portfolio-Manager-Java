# Team Effort Log
## Nathaniel J Robson
Program Classes built  - 14/16  
[AddSharesFrame](/src/AddSharesFrame.java), [BankAccountFrame](/src/BankAccountFrame.java), [BuySellSharesFrame](/src/BuySellSharesFrame.java), [CSVtoArrayService](/src/CSVtoArrayService.java), [CSVUpdateFrame](/src/CSVUpdateFrame.java), [CSVUpdateService](/src/CSVUpdateService.java), [FinancialPortfolioFrame](/src/FinancialPortfolioFrame.java), [GraphBuilderFrame](/src/GraphBuilderFrame.java), [LineChartService](/src/LineChartService.java), [LoginFrame](/src/LoginFrame.java), [LoginService](/src/loginService.java), [MenuFrame](/src/MenuFrame.java), [StockGraphService](/src/StockGraphService.java).  

Program classes refactored to further suit out teams needs - 2/2   
[DisplayTableService](/src/DisplayTableService.java) previously named DisplayTable.java, [SearchAndViewService](/src/SearchAndViewService.java) previously named SearchAndView.java.  

Technical documentation worked on - 8/9  
[README.md document](/README.md), [Product Implementation Report](/Final Technical Documentation/ProductImplementationReport.md), [Product Demonstration](/Final Technical Documentation/Productdemonstration.md), [Project ManagementLog](/Final Technical Documentation/ProjectManagementLog.md), [CE29x Team Final Product Report](/Final Technical Documentation/CE29x Team Final Product Report.docx), [Product Context Report](/Final Technical Documentation/ProductContextReport.md), [Marketing Plan](/Final Technical Documentation/MarketingPlan.md), Team Effort Log (This file).

### Sprint 10 (University Week 10 - Week 12)  
After the MVP we had many ideas of what we were going to implement next. I began to focus on the forefront of our program, the Login page which is what the user will see first. It is important that this works well and allows the only users who are authorised to use the program to log in.  
[Jira - (Login function/page)](https://cseejira.essex.ac.uk/browse/CE291T3-32)   
[Gitlab Commit](b458c61289181d795255f67e0e2adb348bc8f57c)  
This was completed in week 14. With full functionality. Overall it took around 8-10 hours to complete and to make sure that there was no way to log in without having specific credentials found in the 'members.csv' file. The algorithm I coded will continue to allow the specified users to log in even if the file does not exist, because it will write the file for you if it does not exist.

I was also assigned to create a function to allow users to be able to view their total value of their investment. This however could not be implemented until we had a system to store user data in a file to read later on.  
[Jira - User Requirement](https://cseejira.essex.ac.uk/browse/CE291T3-9)  

In the later weeks I take it upon myself to improve the program as much as I possibly could and added so many different functions which improve our program.  After receiving feedback from the MVP This gave me the motivation required to code as many functions into our program as I possibly could, to ensure that we get our desired highest grades.

### Sprint 11 (University Week 12 - Week 18)
In week 12 I was assigned to work on program bugs and debugging, and I also took it upon myself to begin development on creating buttons and functionality for the main menu page.  
[MenuFrame source code](/src/MenuFrame.java)  
[Jira - Debugging code](https://cseejira.essex.ac.uk/browse/CE291T3-15) 
[Jira - Program bugs](https://cseejira.essex.ac.uk/browse/CE291T3-40)  
[Gitlab Commit](f970ca1822b2cb3c2b2bcbb10b5c15ce0bd9b494)  
The menu took me around 20 hours overall to allow for methods and functions all to work in sync with the menu frame. An example of this is being able to move from one frame to the next and also interact with buttons on the menu page which have important functions such as searching a date in a CSV file.  

My next task was to combine both the menu frame and the login frame to allow the user to navigate and login to the program.
[Jira - Menu Frame](https://cseejira.essex.ac.uk/browse/CE291T3-31)  
[Jira - Combine Menu and Login](https://cseejira.essex.ac.uk/browse/CE291T3-34)  
[Gitlab Commit](282eb3ae5f0f57de0bb27877fff6df681386723f)  
The menu was not complete until week 21 as every time a new class or function was added to the program a new button to the menu page would have to follow.  

We also decided to add the user requirements to Jira and expand on them to allow for more accurate program ideas and future implementations.  I was assigned three of them.  
[Jira - User Requirement](https://cseejira.essex.ac.uk/browse/CE291T3-11)

### Sprint 12 (University Week 18 - Week 19)
In week 18 many different implementations took shape in our program, one example being live shares data imported from the internet. This task was quite time-consuming and very difficult as I would have to write from scratch a combination of methods which could gather data from Yahoo Finance and save it in a CSV file upon request. To do this I researched and read documents on how to use Java to access web pages and collect HTML data using arrays. To collect the data, I created the CSVUpdateService class. 
[CSVUpdateService (source code)](/src/CSVUpdateService.java)  
[Gitlab Commit](05e1bae6aacf6c17c1f283b78f24e706329b9aea)  
The CSVUpdateService class accesses YahooFinance with a virtual browser created in java to gain a signature(unique website cookie) which is then stored in an array and then applied to the website URL to make the website believe that the program is not software accessing the website, but instead a user. This way the data is easily downloaded and then saved to a CSV file for future manipulation within our program. Without this implementation into our program I strongly believe that our program may have not succeeded as well as it has.  

I also created a dedicated frame for this class which allows the user to manually download the company of their choice share's data upon a click of a button.  
[CSVUpdateFrame (source code)](/src/CSVUpdateFrame.java)  
[Jira - Online CSV Update](https://cseejira.essex.ac.uk/browse/CE291T3-52)  
[Gitlab Commit](7be0f82ee09bf7fe85fc3fc533b5301849ec71b0)  
This task took around 14-15 hours to implement effectively.  

I also set out to create a frame for the class DisplayTableService to allow users to view the table of shares imported from the internet. This was an adaptation of previous code. Callum created the class and I refactored it to suit the users requirements more effectively.  
The refactored class not only allows for automatic file locating but it also allows for automatic data downloading. If the file does not exist then the class downloads the file from YahooFinance using the class [CSVUpdateService (source code)](/src/CSVUpdateService.java). I believe this improved the functionality of the code by a large magnitude.  
[CSVUpdateService (source code)](/src/CSVUpdateService.java)  
[Jira - JFrame instead of JMessageBox](https://cseejira.essex.ac.uk/browse/CE291T3-50)  
[Gitlab Commit](05e1bae6aacf6c17c1f283b78f24e706329b9aea)  
This class refactor and JFrame creation took me around 4 hours to implement and complete.  

This week I also worked on creating functioning graphs and I spent a lot of time researching on how to get the graphs to display relevent data to the user in a user friendly manner. I decided to use the open source library 'xChart' to create a graph and display data to the user.  
[xChart Website](https://knowm.org/open-source/xchart/)  

I coded theses two classes to support the graph displays and data collection.  
[GraphBuilderFrame (source code)](/src/GraphBuilderFrame.java)  
[StockGraphService (source code)](/src/StockGraphService.java)  
[Gitlab Commit](05e1bae6aacf6c17c1f283b78f24e706329b9aea)  
Overall this task took me around 12-14 hours of work to correctly display the data and fully understand how to implement the graphs correctly.  

### Sprint 13 (University Week 19 - Week 20)
In this sprint I believed it would be a good idea to start work on storing user data and credentials in a database, so I began development in that area. I created a database and a database class which allowed the user to store their credentials in, however upon demonstrating this to a supervisor I was advised that an alternate method of saving the data in a CSV file was perfect so I was advised to drop development for this class here, so I did. Had we implemented this further I believe that the program would have become over complex for the client and may be difficult to set up every time a user wishes to use the program.  
[Jira - Implement Database](https://cseejira.essex.ac.uk/browse/CE291T3-58)  
[Jira - Remove Database](https://cseejira.essex.ac.uk/browse/CE291T3-60)  
[Gitlab Commit](37ae02ac64810c93e737f3f3a5671897bad3fcea)  
This task took me around 5 hours to implement as I researched and followed tutorials on database implementation, however there was an easier method to storing the data in CSV files so we decided to implement that instead. Even though this function was removed, I still gained very useful technical knowledge when dealing with database functionality for future reference.  

I continued to work on the program and implemented on it further by adding functionality to search a date in a file to view the share information on that particular day. This allowed the user to simulate past scenarios and view specific required values and timed data.
[SearchAndView (source code)](/src/SearchandViewService.java)  
This class was created by Callum earlier in development however to make it suit the user requirements further I decided to create a frame which displays the information requested by simply inputting the company name and a date.  
The previous implementation of this class was clunky and inefficient, it required you to locate the file you wish to search a date under manually and only then can you search the date in a file. The display was also in a JMessageBox previously which was not good enough by our supervisors standards, so to resolve this I created a specific JFrame for the gathered data.  
[Gitlab Commit](ff365f8da88bfaa3089cdae303d898972c859210)  
This task took me around 2-4 hours to complete.  

This [Gitlab Commit](ff365f8da88bfaa3089cdae303d898972c859210) is enormous however some key changes were implemented in this commit. I had spent lots of time implementing new functions such as a frame and class which allows users to purchase shares with out program previously named PurchaseSharesFrame [(BuySellSharesFrame)](/src/BuySellSharesFrame.java) and refining old code to better suit our program structure. I also restructured our programs classes to better suit our teams needs.  
This task took me around 6 hours to complete.

### Sprint 14 (University Week 20 - Week 21) 
In the coming weeks I push many functions and key aspects to our program/project. The first being a major fix to our Login Frame functionability.  
[LoginFrame (source code)](/src/LoginFrame.java).  
[Gitab Commit](6987d9f56fd15a4957cc74bf31ef9589839958ab)  
In this commit I also implemented a lot of exception handling to prevent the user from being able to break or crash our program. I also fixed the LoginFrame by refactoring the code upon which it worked.  I adjusted the graph building classes to better suit our users needs and also made the code a lot more efficient and I removed irrelevant lines.  

I was also assigned the task in Jira to make sure that the user could view their past investments. To resolve this issue I began development for a new frame which displayed all purchased shares by the user.  
[Jira - View Past Investment](https://cseejira.essex.ac.uk/browse/CE291T3-59)  

I also continued development of the class [BuySellSharesFrame (source code)](/src/BuySellSharesFrame.java) named PurchaseStocksFrame at this point in time.   
[Jira - Hold Shares in up to 5 Companies](https://cseejira.essex.ac.uk/browse/CE291T3-11)  
[Git Commit](eea17a743e44b50768a257e8bcdc9daa3bf77504)  
In this commit I continued development and I created a system that allows users to simulate purchasing shares with real currency. The user starts with '$10,000' USD and is able to buy shares using the PurchaseStocksFrame at this point in time. The frame contains methods which allow it to read the file containing the users current money, this allows for simulating profit and loss within our program. 
However they cannot sell their shares until the class has once again been refactored later on in week 22.  
The class was also designed to give user visual feedback depending on what shares they decided to purchase or sell and for what price.  
This task took me around 16 hours which I finally completed in week 22.

[Git Commit](eea17a743e44b50768a257e8bcdc9daa3bf77504)  
In this commit I also renamed most of the classes to better suit their functionality, for example any classes that displayed a JFrame would now end with the term 'Frame' to allow for better distinguishability between the classes. Any classes which did not have a JFrame ended with the term 'Service'.  
[Git removal](5c76883ca37b014cb85ba0a5ef113ba3c3641729)  
The database functionality for the login and storing of user credentials was also removed in this sprint.  

### Sprint 15 Final Sprint (University Week 21 - Week 23)  
As the deadline for the program approaches, it is now time to push as much functions to the program as physically possible by me. I decided to put as much time as possible in throughout the day and the night to ensure that our program was way more than just 'presentable'. I wanted to see what I could produce for our team so I got to work.  

On Jira there are too many tasks to display here which are assigned to me so I have attached the final sprint report link to allow for better understanding.  
[Jira - Final Sprint Report](https://cseejira.essex.ac.uk/secure/RapidBoard.jspa?rapidView=456&projectKey=CE291T3&view=reporting&chart=sprintRetrospective&sprint=1049)  

I have also attached multiple git commit links to allow for better understand of how much coding was done in these final two weeks.  

[Git Commit](d57bb324de3a4a66ec6bfad7cb54e67c013dd851) - Many key functions of our program took shape in this commit including, adding a frame which allowed the user to add previously owned shares to allow for manipulation through our program [AddSharesFrame (source code)].  
Another addition is adjusting the [GraphBuilderFrame (source code)](/src/GraphBuilderFrame.java) to allow the user to specify a range in which they would like the graph to display to them.  
I also added login sessions to the program which would allow the program to store the current user and provide user feedback on this, for example on the menu frame the user us greeted and told who is logged in and also when purchasing shares, the shares are saved in the current users data file found in the [User Portfolio's File](/UserPortfolios).  
I also refined the [DisplayTableService (source code)](/src/DisplayTableService.java) class code.  
I also refined the [CSVUpdateFrame (source code)](/src/CSVUpdateFrame.java) class code.  
Finally I also added a key function to our program, the ability to deposit and withdraw funds in the users account. 
I created a JFrame which allows the user to see how much money they have in their accounts and then manipulate it by either depositing more funds or withdrawing funds. The class can be viewed here [BankAccountFrame](/src/BankAccountFrame.java).

[Git Commit](8a148326f03d7c9bdfb33042020a3759fa71e93b) - Many many more key functions implemented in this commit including the ability to finally sell shares in the [BuySellShares (source code)](/src/BuySellShares.java) frame. This allows the user to finally find out whether or not they have made a profit on their shares they have been holding.  
This then leads me on to another addition which was made in this commit, the ability to view account history in a line chart format. This allows the user to find out visually, when they last sold or purchased a share and whether or not they made a profit or a loss on it. It also informs the user of which company the share belonged too. I added this functionality to the main menu [MenuFrame (source code)](/src/MenuFrame.java) to allow the user to quickly and easily find out about their account history. The service class for the line chart can be found here [LineChartService](/src/LineChartService.java).  
Which leads me on to my next addition to the program, I added a frame which allows the user to see which organisations shares they own and how many of them they own. It is displayed within a JTextArea for easy to understand data like so, 'AAPL shares 20'. This frame was named OwnedSharesFrame but has since been refactored to [FinancialPortfolioFrame](/src/FinancialPortfolioFrame.java).  

[Git Commit](ff1a1976d47f57cbbe54f0586db9e8d841d92df7) - In this commit I adjusting the look and feel of our program to ensure that the program was not only functional but also a pleasure to use and look at. I adjusted simple things like frame size, label contents and attempted to add an automatically adjusting UI Manager to the program which I ended up removing as I did not like the look of it.  

[Git Commit](554531736865813df319d9a8ce07ffc9b9d1b77f) - In this commit I was attempting to refine the current code to ensure it was efficient and unbreakable when executed. I also restructured the Dependency folder and renamed it to 'lib' which can be found here: [lib folder](/lib)  
I also worked on commenting the whole program to a high standard to ensure that everyone can understand my code and the functions of each method for future reference.

[Git Commit](7bd2bbe3dcb3f2423e8378141d7da1d7d27e824c) - More code refinement to the [BankAccountFrame (source code)](/src/BankAccountFrame.java) to prevent the user from withdrawing too much money at any one time and also to prevent them from depositing too much money. E.g if the user has over $100,000 in their account their only option is to withdraw the funds for safety reasons.  

[Git Commit](0117e1bc11ebd3d60c1f303a3177d5a2c4e94730) - These next two commits are the final commits that contain code changed. In this commit I implemented another major key function to our program, the ability to view the users financial portfolio. This collects all data on the user and combines it to give the user visual feedback. In the [FinancialPortfolioFrame (source code)](/src/FinancialPortfolioFrame.java) the user is displayed their currently owned shares, and a large button when pressed gets the most recent price for all of their owned shares, times it by how many shares they hold for each organisation and then provides the user with the total. This allows the user to view their total worth, see how much money they started with and also see how much profit or loss they have made over time.  

[Git Commit - icons](07035cd4b89d9e5a14774a490d857c8d5cfa8283) - In this commit I also refined most other classes in the program and also added a visual icon to each frame in the program to illustrate to the user of which type of window they are currently in. For example in the [BankAccountFrame (source code)](/src/BankAccountFrame.java) there is an icon of a bank in the top left corner. Another example of this is in the [GraphBuilderFrame (source code)](/src/GraphBuilderFrame.java), there is an icon of a graph. Simple yet effective UI tweak. 

[Git Commit](151318db9100258bf5df955f14660e19eb08dd72) - In this commit I added the executable file of our program, at this point in time, named Team_3_Financial_Portfolio_Manager.jar and then renamed [ProgramJAR.jar](/ProgramJAR.jar).  
[Git commit rename](42938faf292e54f12d0b2dbe769242e3e66094c5)  

#### Documents Produced
[Jira - README.md Task](https://cseejira.essex.ac.uk/browse/CE291T3-74)
[Git Commit - README.md](f14ee9b0121dee1b1a9fc6aa4f6de4e5855e60a4) - In this commit, I completed the README.md document for our team. This took me around 3-4 hours to complete.  
[Jira - Product Demonstration Task](https://cseejira.essex.ac.uk/browse/CE291T3-97)
[Git Commit - Productdemonstration.md](735b81092f0c597dc34cc92e76b8b9208c78cc84) - I completed the product demonstration document which took me around 7 hours, hopefully it is of a high standard.  

[Jira - Technical Details Report Task](https://cseejira.essex.ac.uk/browse/CE291T3-79)  
[Git Commit - ProjectImplementationReport.md](735b81092f0c597dc34cc92e76b8b9208c78cc84) - I completed the project implementation report which took me around 7 hours to complete.  
[Jira - Complete MarkDown Documents Task](https://cseejira.essex.ac.uk/browse/CE291T3-70)  
[Git Commit - ProjectManagementLog.md](a077627e03af171c7aec5a594401dc7c9e6cd9d2) - I completed the project management log which took me around 5 hours to complete.  

[Git Commit - Marketing Plan.md](bef256dcf24ea9ba7655b321a83fb00468bef031) - I made some final adjustments to the marketing plan and added an alternate forecast section towards the end. This took me around 30 minutes.  

[Git Commit - Product Context Report](bef256dcf24ea9ba7655b321a83fb00468bef031) - I added the Laws and Acts section to the product context report and also added some health and safety concerns. This took me around 1 hr 30 mins to complete.  

#### Diagrams - Type Diagram, Use Case Diagram, Class Diagrams, CRC Cards Produced
[Git Commit - Type Diagram](8e695d4ded0973794e657faa9661fe32f633ea45) - For this commit I produced a Type Diagram to demonstrate our program from a technical perspective.   
[Git Commit - CRC Cards](3a53ef00eeec8e3794c80427d6a5df79e9c19f95) - CRC Cards Commit, for more in-depth class demonstration.  

[Jira - Class Diagram Task](https://cseejira.essex.ac.uk/browse/CE291T3-70)  
[Git Commit - Class Diagram](8473c7468ab9ac5694f9827cd0cea80271696b93)
[Git Commit - Use Case Diagram](8473c7468ab9ac5694f9827cd0cea80271696b93)  
I also exported from IntelliJ a class diagram for our program demonstrating relationships between each class. Finally I also created a Use Case Diagram to demonstrate a user scenario for purchasing a share and also viewing their total financial value.  


## Callum Kleber

### Sprint 10 (University Week 10 - 12)
As this was the first meeting since the Christmas break, our group decided to plan what we still needed to add to our product. We felt that
our MVP was very basic and lacked many features. We therefore assigned each other different features to work on. I was assigned to add a
program that will allow the user to view the full contents of one of the csv files stored in our product.

### Sprint 11 (University Week 18)
I finished completing the program that will display a csv file this week, it took me 3 hours to complete. The Git commit can be seen below:

[Git Commit - View Table Program](eb78a2f36139a75652152fa75d2aaee61a136638)

I next decided to research how we can implement a feature that will alow the user to update the csv file stored in our program. I researched
online and discovered the IEX trading api which I decided to try and implement.

[Jira - IEX Trading api](https://cseejira.essex.ac.uk/browse/CE291T3-45)

### Sprint 12 (University Week 19)
After researching the IEX Trading api I was unable to find a way to implement it into our product. I informed my teammates of this and Nathaniel 
told us that he had found a workaround for this issue. He had instead implemented a feature that will access Yahoo Finance as a user and download
stock data from there instead. As we no longer needed the IEX trading api I stopped researching it.

### Sprint 13 (University Week 20)
As our program was close to completion, I decided to begin checking over the program to make sure that there wasn't any major issues and that it
meets the requirements we had created.

### Sprint 14 (University Week 21 - 22)
During this sprint programming of our product was completed. Our group decided to contintue testing and debugging the program to ensure that
everything is running correctlty. We also began working on the documentation for our final report and assigned each other documents to work on.
I was assigned the context report:

[Jira - Context Report](https://cseejira.essex.ac.uk/browse/CE291T3-63)

### Sprint 15 (University Week 23)
This week I completed the context report, the git commit for this can be seen below:

Git Commit - Context Report](23b76295af9b38b42592348de6171d886df04fb0)

By this point our team had finished testing the program and had completed the documentation (apart from the team effort log).


## Daniel Callender

### Sprint 10 (University Week 10- Week 12)
Personally, I felt as if our MVP had lacked many of the features that we stated that we woulkd like implemented to demonstrate. This way I had set out to edit the original menu class that I had initially made for our program. However, another member of the group had volunteered to make the menu page, and I had chosen decided that I would research methods of implementing other features into the team project and getting information about what we could implement back into the team. I had realised that we could implement a login feature into our program which Nathaniel had set out to do.This week, I had also suggested we add a main page/menue page to our program.
These weeks, I had also decided to research more companies that we could have added to our csv file to increase it.   
[Jira - (Main Page function)](https://cseejira.essex.ac.uk/browse/CE291T3-31)


[Jira - (Login function/page)](https://cseejira.essex.ac.uk/browse/CE291T3-32)  

### Sprint 12 (University Week 18)
Week 18, I had decided that I would work on the graphs of the program. During this week i had decided to have an attempt at hard coding the graph without the influence of Java API's. This had proved difficult but after many trials and errors, I had managed to get a graph to display but due to the graph only cutting out at a certain position, I had decided to not upload it to Gitlab until I was able to fix the error. I had also decided to find more research on how to fix this error via youtube and stackoverflow.
[Jira- Graphs](https://cseejira.essex.ac.uk/browse/CE291T3-55) This Graph took me 6 hours to complete and a further 3 hours to attempt to fix the errors.

### Sprint 13 (University Week 19)
During the week 19 sprint, I had decided to switch to using a Java API to create the graph I was working on. I had originally made the switch as I was wondering how we as a group would be able to implement the data fromour csv into the graph which would not make it to difficult to understand. When creating the graph using the API, I had created data that I would use to test to see whether or not the program was displaying the correct output. Initially it was not displaying the correct output however, due to perseverance I had found a way to fix this error. Initially, I was planning for our graph to displayed like a typical bar chart but as it was not clear, I had decided it was simpler to display it as a line chart.
During this time, Nathaniel had a completed a graph that was working with our current program, thus resulting me to retire my version of the chart. 
[Jira- Graphs](https://cseejira.essex.ac.uk/browse/CE291T3-55)

[Git commit - StockGraph](31ba0a65ec13d25adc7834ae27de8f782445d041) This graph had taken me 3 hours to complete and a futher 2 hours to implement my own data and debug the program

### Sprint 14 (University Week 20)
Week 20 a majority of our program was finished. Due to this, I had decided to test the program and see if it was catching the error exceptions. During this testing phase, it was noted that there were no errors thrown unless information was entered wrong but the program would just display a message that would inform the user which in this case was me what the error was. Due to this there were no bugs i was able to report back to my team about. To stay ahead of the tasks, I made sure to check that we were meeting the requirements that we had set out to do. I had also decided to investigate the documentation that we would be doing when we had finishd out product.

### Sprint 15 - Final Sprint (University Week 21 - Week 23 )
As we had finished our program, I compared it to the requirements that we had set out earlier on and during the product to see if we had matched them. During this time, I had also volunteered to complete the marketing document for the project. This included alot of research that was to know what type of market that our product aimed to go through. To also complete this, I had downloaded some stock trading applications to get a much better understanding of how we were going to enter market and how to distinguish ourselves. Due to the proactive thinking, this had enabled me to have a wider range of topics to talk about and compare us to our competition and what we had to offer that they had not. Also, talking to people that were already using financial portfolios helped play a role in choosing demogrpahics and what methods worked well. These were noted down and added to the marketing document.

[Jira - Marketing Document](https://cseejira.essex.ac.uk/browse/CE291T3-71)

[Git Commit Marketing Document](Final Technical Documentation/MarketingPlan.md)

## Koranteng Lartey


### Sprint 10 (University Week 10)
After coming back from the Christmas break and reviewing the feedback from the the previous MVP we all felt that as a product did not meet our expectation so we all decided to revamp the look of the product and assinged parts of the program to each other to improve on. I took it upon my self to make a new eastehicly pleasing Menu page which would gram the attention of the customer.
in this week i did some research on examples of through videos and websites on Main menus and  that would fit our product they way we wanted it. Morever inplement some Junit Testing to the code done in the program [Jira - Try to add Junit Test](https://cseejira.essex.ac.uk/browse/CE291T3-46)

### Sprint 11 (University Week 12)
In this week I continued with the research programing of the main menu and some of the functions that could be implemented in it mainly focusing on creating dta inserting tabs in which the user would be able to input a specif element of data needed to retrive the infromation the user would want to be diplayed and viewed.[Jira - task](https://cseejira.essex.ac.uk/browse/CE291T3-48) 

### Sprint 12 (University Week 18)
After researching some of my findings and showing it to my team members and deciding if they would be able to implement them in the product. I was assigned the task to reaserch Research into IEX API Java which is a set of services offered by The Investors Exchange (IEX) to provide access to data from the Exchange to developers and engineers for free. [Jira - link found here](https://cseejira.essex.ac.uk/browse/CE291T3-53)

### Sprint 13 (University Week 19)
As the program is in its stages of completion, I have been researching new ideas on how to improve the design of the program. Moreover tested some of the functions in the program for the testing report that will be made for the final report.[found here](https://cseejira.essex.ac.uk/browse/CE291T3-67)

### Sprint 14 (University Week 20 - 23)
In this week I was assigned with the Testing report so as to maximise my work on the testing report I would be testing every new function that the team would implement and give my findings with errors that would be popping through the code in the program. Moreover after feedback from the supervisor would be searching ways in which Junit testing could be implemented through the code. [Jira Link found here](https://cseejira.essex.ac.uk/browse/CE291T3-73)
As I started more test in would put my finding in the test report with screenshots displaying each time [Git commit test report](https://cseegit.essex.ac.uk/ce291/team3/teamproject/commit/6ce63ea85aa3459f7a62ac82781059e6d4b13225), Moreover was successfull in implementing Junit testing in the code [Git commit](https://cseegit.essex.ac.uk/ce291/team3/teamproject/commit/d2cce86a908689ebd3f57d2d05a5277c4c9696bd) and [code](https://cseegit.essex.ac.uk/ce291/team3/teamproject/commit/490aa59ef1b29e2abe9c4753f75b3598e5ad9312). 
Furthermore worked on covering the functionalities not covered by JUnit [git Commit test report](https://cseegit.essex.ac.uk/ce291/team3/teamproject/commit/c0baebfe2a0a18c2c5e1a684245bb629416cc465)

Overall I have worked on more hours researching and testing the code more than implementing it. But I beleieve the hours that I believe I have put into making code should be at least 15-20. So as not to be too much of a burden on the team I focused more on the testing which I believe contributed greatly to perfecting the porduct. 


## Elios Dauti



### Sprint 10 (University Week 10 - Week 12)
We discussed many more features to add to our program as our first deliverable was missing a few key features and we wanted to implement these into our program. We already had people working on a login page and additional GUI implementations. I was assigned to research how to implement a suitable and efficient graph model to output all our data and to report back to the team. I spent over 6 hours researching through different sources, like YouTube and other Java API’s to make a list of suitable methods to use and talked it through with the team in our meetings discussing which is the best one to use and how to implement it.

[Jira – simulating investments](https://cseejira.essex.ac.uk/browse/CE291T3-12)


### Sprint 11 (University Week 18)
During this time, we started working on the graph. I started experimenting with different API’s for the graph and due to my lack of experience with these techniques I was struggling to get an appropriate graph so consulted my team who aided the process and implemented these during our sessions putting our code together. We also were updating our GUI’s adding and remove some features as advised by our supervisor. We also started research on how to implement the upload of the files to the program and discussing the best way to get the most update data.

### Sprint 12 (University Week 19)
This sprint we finalised our graphs and decided to use “xchart” to make them as it seemed to be the most efficient and most suitable for our needs, which displayed graphically the data we needed. We tested this with a few CSV files but had to find out a better way of obtaining the CSV files in the first place and to be more versatile and work with many different and up to date data. We had a few problems at the start in how we wanted our graph to be displayed, e.g. bar chart or line graph but eventually we settled on the on using bar chart for the sales and line chart for the investment history. We also started implementation of retrieving data straight from IEX platform.

### Sprint 13 (University Week 20)
Week 20 and we have completed a majority of the functionality of our program and there are only a few things left to do. We had to make some minor adjustments to our graphs and remove some unneeded confirmation boxes. The problem with our graph was not the actual graph displayed but the axis it was on. The scale of our axis was rather skewed unnecessarily so most of the unimportant data was filling up most of the graph while the more intricate and important part of the data was lying on the very top making it difficult to see. We fixed this by adjusting the scale of the graph and not starting the y-axis on zero, which is a common practice when you have a challenge like ours.

### Sprint 14 (University Week 21 - Week 23)
Our final sprint, and our program is mostly complete, and we are just making minor adjustments and comparing it to the software specification and requirements we set out to achieve. We started running tests on our program; at first, we assigned a team member to work on running the tests, but they struggled to tackle this problem using Junit testing and offered the classic before and after style screenshot testing which covered many different inputs and outputs, but after talking about it with the team we helped and managed to aid the progress of this task, which is now complete. We also resourcefully completed the final technical documentation. I personally worked more on the product context report dealing with the ethics and health and safety of the documentation. All these sections required copious amounts of research to obtain a good knowledge of all the sections and complete them to the best of our abilities.

