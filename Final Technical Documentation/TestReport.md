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
Don below are some of the test using the Junit testing framework:
### JUnit Test 1
#### Test Description: Here a method was used so as to check that both user and password where correctly inserted in the login stage By checking that both the password and user name were contained in the members CSV file
#### Test result: Pass.
#### Screenshots: 
![Screenshot](/README-Images/JUnitTest1.JPG)

### JUnit Test 2
#### Test Description: This Test was conducted to see if a CSV file was created prior to the user downloading the information to display the Graph which would display a company data so as not to get confused with not updated data.  
#### Test result: Pass.
#### Screenshots: 
![Screenshot](/README-Images/JUnitTest2.JPG)

### JUnit Test 3
#### Test Description: This Test was conducted to see if the CSVs files containing the users data was in the repositry so that data can be retrieved to display a user shares. 
#### Test result: Pass.
#### Screenshots:
![Screenshot](/README-Images/JUnitTest3.JPG)

### JUnit Test 4
#### Test Description: As seen below this test was used to ensure that users that the correct data formaat was used when inputing date the shares would be either sold or bought.
#### Test result: Pass.
#### Screenshots:
![Screenshot](/README-Images/JUnitTest4.JPG)
![Screenshot](/README-Images/JUnitTest4b.JPG)

### JUnit Test 5
#### Test Description: This test was used to test that the amount of money a user possed would not be more than 10,000 
#### Test result: Fail.
#### Screenshots:
![Screenshot](/README-Images/JUnitTest5.JPG)
![Screenshot](/README-Images/JUnitTest5b.JPG)


These Junit Tests covered at least 15% of program functionality. 
The rest of the program testing of functions was used methods such "if" and "else" statements to ensure that if errors would come an appropriate massege would be dsiplayed.this were used to overcame some of the functionality not covered used Junit testing.
Down below are some example of functionality not covered by Junit Tests:

## Functionality test 1
#### Function Description: This functions was used, using an event handler whcih would run a loop though the lists of members to find input value matches and validates both the login button and Reset Buttons
#### Screenshots:
![Screenshot](/README-Images/FunctionalTest1.JPG)

## Functionality test 2
#### Function Description: By using another event handler here we made sure that the the program would get updates by downloading the most recent file online. And ensuring that the Loop checks to see if the string .csv was added to input or not, if not it will do it for you.
#### Screenshots:
![Screenshot](/README-Images/FunctionalTest2.JPG)

## Functionality test 3
#### Function Description: Here we checks that the program, reads the current users bank account data, creates it and sets the bank starting value at 10000, writes to CSV if file does not exist . 
#### Screenshots:
![Screenshot](/README-Images/FunctionalTest3.JPG)

## Functionality test 4
#### Function Description: Here the program checks that it reads How much money the current user has in their account at this moment in time, that the user is able to Deposit money and add it to the money in file.
#### Screenshots:
![Screenshot](/README-Images/FunctionalTest4.JPG)

Throught the code there are many parts in which JUnit was not used but but functional methods of testing the code have been implemented.
