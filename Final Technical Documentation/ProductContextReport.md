# Product Context
## Legal
[Data Protection Act 2018](http://www.legislation.gov.uk/ukpga/2018/12/contents/enacted) - It is important to ensure that the way we handle user data does not breach this law. If we do not correctly inform the user of how we will use their information and what information is gathered then we may be breaching this newly updated act. It is very important to inform the user of how their data is handled as it can lead to many complications if we were to sell the information gathered from users on to other organisations and not inform the users of this before hand. This was a common issue, and many companies and websites practiced this method to earn more money. If we were to distribute and release our product to the public, we would first need to ensure that our program functioned within this law and informed the user of how we would use their data. We would also need to allow the user to be in complete control of what information is given to us and also how we handle that information. For example we would need a document which would clearly define what information would be stored like their names, date of births and account data and we would also need to inform the user of how we would store this data to prevent anyone else unlawfully accessing it. Our program at this moment in time abides by this law because we do not officially hold any user information at this current time, also the data is not shared past the machine that the program is executed on. Our team will never be able to view this information stored unless we are given access to the machine which the program is executed on.  
[Intellectual Property Act 2014](http://www.legislation.gov.uk/ukpga/2014/18/contents/enacted) - If we were to distribute our program to the public we would need to trademark our program to ensure that it is known to the public that it cannot be redistributed or claimed by any other organisation or individual without giving proper reference or retrieving consent from one, if not all of our team members and even the University of Essex. To recognise our product as trademarked we would need to create a distinguishable logo or signature to disclose the product, so that it is known to the clients as well as the public that the product is owned by us. This way no one can claim this program as their own.  
[Copyright, Designs and Patents Act 1988](https://www.legislation.gov.uk/ukpga/1988/48/contents) - If our team was to distribute our program we would need to ensure that we abide by this law. To do this we would need to properly reference all externally used code and libraries to ensure that we do not claim that any of this is accidentally claimed as our own. This can be highly consequential if we were to use an external library and claim that we own it when it comes to selling our product. We would likely be sued by the original creators and also we would likely have to cease all development of this product unless all externally sourced libraries were correctly referenced and permission was gained from the creators to use it in our program. Any images, sound and data gathered from the internet such as the share data from Yahoo Finance would have to be correctly referenced ensuring that anyone who was to use our program would know that we do not claim that data as our own, in fact we are just re-displaying it in a different format. The likelihood of us redistributing the product in its current state is next to null. The program would need many alterations to ensure that it would be abiding by this law, we would likely have to gather our shares data from other websites such as IEX or Google Finance as these companies support developers where as Yahoo Finance does not.  
[Computer Misuse Act 1990](https://www.legislation.gov.uk/ukpga/1990/18/contents) - It is important to ensure our program is unable to be manipulated to cause damage to clients devices. If a user was to download our product from our website and it was to come with a virus, we may be accountable for this as the security of our program may not be prepared for defending against attackers. If the virus was intended to cause damages to the clients personal computer, e.g deleting files and corrupting the Operating System then this would jeopardise the reputation of our program and development team as the public may believe that we did not put the safety of the users data as one of our priorities while developing the program.  

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

When releasing our product a subscription license would be most suitable. As seen in our marketing plan we have found similar products that
offer a subscription service. We have therefore decided to follow what our competitors have done and also offer a subscription service. This
would also make our product more affordable and give our users more freedom.

Below you can see a list of the dependencies used in our product:  

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

All of these dependencies are free for users to use in their programs. We therefore wouldn't have to deal with any legal issues caused by the
dependencies.

## Ethical
One ethical issue involves the method we have used to download stock data. As mentioned earlier we have used a unlawful workaround to get
stock data from the Yahoo finance website. This would be a ethical issue as we are stealing data to improve our product. As mentioned
earlier to work around this we would need to use a different method.

Another ethical issue is that our product provides our users a easy way to take major risks by buying and selling their shares. This could 
lead to addiction and our users slowly losing significant amounts of money. To help prevent this we could implement a system that warns a user when
they are taking a major risk that they may not be able to afford. This wouldn't prevent the user from doing what they want but instead
ensure that they are fully informed.

If our program was open publicly this would also pose an ethical issue as a specialist software like this could be used by untrained traders without knowledge and could lead to some financial loses. This could be prevented by limiting its availability and maybe posing some checks or financial proof of users.

## Health & Safety

As our product is software based there aren’t many health and safety risks. Users should take breaks when using the product and make sure they 
are in adequate conditions to avoid eye strain, headaches and more health related complications.  
Another health and safety concern would be ensuring that the user is aware of the risks involved with buying and selling shares as it involves risking money. This can cause new users stress if they are unfamiliar of the consequences of uninformed trades. This stress and high risk can lead to unintended addiction to risking high amounts of money, similarly to gambling. This can cause mental health issues and major amounts of stress and discomfort. To prevent this our team could implement a function to inform users of the risks involved with our program to prevent users from making uninformed decisions. We could also have a timed message to continue to remind users of the risks involved within a certain interval.