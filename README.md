Broker Management System
========

A simple broker management system built from the Java Swing framework.  The system provides tools for the individual broker to manage client and account details, perform client transactions, view market data, monitor client portfolios, and other key functionalities.  

Getting Started
------------

###### Prerequisites:

* Java 1.7
* MySQL (Community Server)
* Maven 3.3.3 (alternatively you could use Netbeans IDE)

Clone the repository to your system locally via ``git clone``:

    git clone git@github.com:ryantonini/broker-management-system.git

or download the zip file.  Change to your project folder where pom.xml file is placed, and issue this command:

    mvn package

You can run the newly compiled and packaged JAR with the following command:

    java -cp target/BrokerSystem-1.0-SNAPSHOT.jar rt.brokerage.view.BrokerageUI
    
Alternatively, you can open the maven project from NetBeans IDE as follows:

    File -> Import Project -> From Zip 


```NOTE:``` Before you can run the application, you will have to change the database configuration settings in `src/resources/db.properties` using the settings for your local MySQL version.

Licensing
------------
Before you can run the application, you will have to change the database configuration settings in the src/resources/db.properties file.  Use the settings for your MySQL version.  The included values are just a sample.   
#### Licensing 
Please see the file called LICENSE.
