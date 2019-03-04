# Product Context

For our product we need to download stock data so our user can purchase or sell shares using up to date information. 
To achieve this, we first tried to use the IEX trading API. This API allows the user to download stock data from the IEX trading website. 
However, we were unable to correctly implement this in our product, so we instead looked at alternative methods. 

Yahoo finance is another website that displays stock info on companies. To access this stock data, we had to download it from their website. 
Yahoo used to offer an API that allowed you to download stock data from the website however, they recently disabled access to the website 
through the API. Because of this we had to use a workaround. We used a program that tricked the website into thinking that a normal user is 
accessing it, the website then would allow us to download stock data. This is a legal issue as we are obtaining data from Yahoo finance through 
an unofficial method that could be classed as stealing. This could lead to Yahoo suing us if we tried to release our product. 

This is also an ethical issue as we are stealing data to benefit our product.

Because of this we would have to find a different, legal method to get the data. A good choice would be the IEX trading API mentioned earlier. 
To improve our product, we could either learn more about the API so we can implement it ourselves or hire a developer that can implement it for 
us.

In our program we handle user data that will need to be kept private. The only security we currently have is a login system that requires a 
password. However, the passwords and logins stored in our product aren’t encrypted. This would make it very easy for a hacker to discover the 
login information of a user and potentially buy or sell any shares the user owns. This lack of security could cause our program to violate the 
general data protection regulation as our product isn’t secure by design.

Furthermore, our product could violate the data protection act as we haven’t backed up the data stored in our product.  To improve this, we will 
need to implement more security by encrypting user data and login info as well as backing up saved data.

As our product is software based there aren’t many health and safety risks. Users should take breaks when using the product and make sure they 
are in adequate conditions to avoid eye strain, headaches etc.

