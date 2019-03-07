# Test report  

## Testing performed

This section will describe some of the test planned, and results.
Every test will include the goal and will also indicate if the test passed or failed.
Screenshots will be include the tests with results of such testsFor some tests, it is a good idea to use screenshots of target and actual results.
#### Test 1.
#### Test Description: user can successfully login into the financial portfolio.
#### Test result: Pass.
#### Screenshots: before/ after.
![Screenshot](/README-Images/test1.JPG)
![Screenshot](/README-Images/test1b.JPG)

#### Test 2 
#### Test Description: if user inputs incorrect login details appropriate message will be made.
#### Test result: Pass.
#### Screenshots: before/ after
![Screenshot](/README-Images/test2.JPG)
![Screenshot](/README-Images/test2b.JPG)

#### Test 3 
#### Test Description: User is able to see how much a share for facebook is and is able to purchase 50 shares for FaceBook
#### Test result: Pass.
#### Screenshots: before/ after
![Screenshot](/README-Images/test3.JPG)
![Screenshot](/README-Images/test3b.JPG)
![Screenshot](/README-Images/test3c.JPG)
![Screenshot](/README-Images/test3d.JPG)
![Screenshot](/README-Images/test3e.JPG)

#### Test 4
#### Test Description: User is able to see how many shares he/she owns.
#### Test result: Pass.
#### Screenshots: before/after
![Screenshot](/README-Images/test4.JPG)
![Screenshot](/README-Images/test4b.JPG)

#### Test 5
#### Test Description: User is able to add  8 more shares to the already bought shares.
#### Test result: Pass.
#### Screenshots: before/after
![Screenshot](/README-Images/test5.JPG)
![Screenshot](/README-Images/test5b.JPG)
![Screenshot](/README-Images/test5c.JPG)

#### Test 6 
#### Test Description: User is unable to purchase more than 1000 shares  
#### Test result: Fail.
#### Screenshots: before/ after
![Screenshot](/README-Images/test6.JPG)
![Screenshot](/README-Images/test6b.JPG)

#### Test 7 
#### Test Description: User is able to view bank account and is able to withdraw £1000
#### Test result: Pass.
#### Screenshots: before/ after
![Screenshot](/README-Images/test7.JPG)
![Screenshot](/README-Images/test7b.JPG)
![Screenshot](/README-Images/test7c.JPG)

#### Test 8 
#### Test Description: User is able to view bank account and is unabled to more than it owns.
#### Test result: Fail.
#### Screenshots: before/ after
![Screenshot](/README-Images/test8.JPG)
![Screenshot](/README-Images/test8b.JPG)

#### Test 9 
#### Test Description: User is able to view bank account and is unabled to more deposit more than 10000.
#### Test result: Pass.
#### Screenshots: before/ after
![Screenshot](/README-Images/test9.JPG)

#### Test 10 
#### Test Description: User is able to view investment chart.
#### Test result: Pass.
#### Screenshots: before/ after
![Screenshot](/README-Images/test10.JPG)

#### Test 11
#### Test Description: User is able to view twitters up date shares/stock  prices from 20 days ago.
#### Test result: Pass.
#### Screenshots: before/ after
![Screenshot](/README-Images/test11.JPG)
![Screenshot](/README-Images/test11b.JPG)
![Screenshot](/README-Images/test11c.JPG)

#### Test 12 
#### Test Description: User is able to download the data on apple and data matches Google finances data on apple.
#### Test result: Pass.
#### Screenshots: before/ after
![Screenshot](/README-Images/test12.JPG) 
![Screenshot](/README-Images/test12b.JPG)

As you can see below data is pulled correctly as checked from google finance data about apple

![Screenshot](/README-Images/test12c.JPG)
![Screenshot](/README-Images/test12d.JPG)

#### Test 13 
#### Test Description: User is able to search for data about Microsoft with data 2019-03-05 .
#### Test result: Pass.
#### Screenshots: before/ after
![Screenshot](/README-Images/test13.JPG)
![Screenshot](/README-Images/test13b.JPG)

*As you can see the data searched matches the records that Google finance holds.*

![Screenshot](/README-Images/test13c.JPG)

![Screenshot](/README-Images/test13d.JPG)

#### Test 14 
#### Test Description: Incorrect symbol(mst) and date format(05-03-2019) input by user to search for share data will display approriate error message.
#### Test result: Pass.
#### Screenshots: before/ after
![Screenshot](/README-Images/test14.JPG)
![Screenshot](/README-Images/test14b.JPG)
![Screenshot](/README-Images/test14c.JPG)

#### Test 15 
#### Test Description: by inputting company name symbol (nflx) user is able to view table of shares which also matches share data on Google finance.
#### Test result: Pass.
#### Screenshots: before/ after
![Screenshot](/README-Images/test15.JPG)
![Screenshot](/README-Images/test15b.JPG)

As you can see the data matches data held in Google fincance.

![Screenshot](/README-Images/test15c.JPG)












## Automatic Testing:
JUnit is a unit testing framework for Java programming language. JUnit is used in the development of test-driven development
*Describe any JUnit tests you created. Describe what percentage of program functionality is covered by JUnit tests.*

*Highlight any functionality not covered by JUnit tests, and say how you overcame that shortfall.*

*Describe if you managed to use the continuous integration server to run tests - which tests were run, and what were the results?* 