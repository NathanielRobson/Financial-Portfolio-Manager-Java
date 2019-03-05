# Product Context

## Legal
For our product we need to download stock data so our user can purchase or sell shares using up to date information. 
To achieve this, we first tried to use the IEX trading API. This API allows the user to download stock data from the IEX trading website. 
However, we were unable to correctly implement this in our product, so we instead looked at alternative methods. 

Yahoo finance is another website that displays stock info on companies. To access this stock data, we had to download it from their website. 
Yahoo used to offer an API that allowed you to download stock data from the website however, they recently disabled access to the website 
through the API. Because of this we had to use a workaround. We used a program that tricked the website into thinking that a normal user is 
accessing it, the website then would allow us to download stock data. This is a legal issue as we would be violating the Data Protection Act
(1998) due to not obtaining the data 'fairly and lawfully'.

Because of this we would have to find a different, legal method to get the data. A good choice would be the IEX trading API mentioned earlier. 
We could either learn more about the API so we can implement it ourselves or hire a developer that can implement it for us.

In our program we handle sensitive user data. This includes information on money a user currently has in their bank account as well as stocks
they currently own. This information will need to be kept private. The only security we currently have is a login system that requires a 
password. However, the passwords and logins stored in our product aren’t encrypted. This would make it very easy for a hacker to discover the 
login information of a user and potentially use the users money to buy shares or sell any shares the user owns. This lack of security could 
cause our program to violate the EU General Data Protection Regulation (2016) as our product isn’t secure by design. This would also cause
our product to violate the Data Protection Act again as we haven't been able to keep the users data 'Safe and secure'.

Furthermore, we haven't backed any of the data we have stored. This would violate the Data Protection Act again as we haven’t backed up the 
data stored in our product. To improve the issues I have mentioned above, we will need to implement more security by encrypting user data 
and login info as well as backing up saved data.

When releasing our product a freeware license would be most suitable. Freeware would be suitable as other products similar to ours are offered
for free. Therefore to be able to be competitive we would need to offer our product for free as well.

## Ethical
One ethical issue involves the method we have used to download stock data. As mentioned earlier we have used a unlawful workaround to get
stock data from the Yahoo finance website. This would be a ethical issue as we are stealing data to improve our product. As mentioned
earlier to work around this we would need to use a different method.

Another ethical issue is that our product provides our users a easy way to take major risks by buying and selling their shares. This could 
lead to addiction and our users slowly losing significant amounts of money. To help prevent this we could implement a system that warns a user when
they are taking a major risk that they may not be able to afford. This wouldn't prevent the user from doing what they want but instead
ensure that they are fully informed.

## Health & Safety

As our product is software based there aren’t many health and safety risks. Users should take breaks when using the product and make sure they 
are in adequate conditions to avoid eye strain, headaches etc.