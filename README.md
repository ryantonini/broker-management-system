Broker Management System
========

A simple broker management system built from the Java Swing framework.  The system provides tools for the individual broker to manage client and account details, perform client transactions, view market data, monitor client portfolios, and other key functionalities.  The application can also be treated as a stock simulator that mimics the behaviour of an account under a real brokerage.   

Getting Started
------------

###### Prerequisites:

* Java 1.7
* MySQL (Community Server)
* Maven 3.3.3 (alternatively you could use an IDE like Netbeans, Eclipse or IntelliJ)

Clone the repository to your system locally via ``git clone``:

    git clone https://github.com/ryantonini/broker-management-system.git

or download the zip file.  Change to your project folder where pom.xml file is placed, and issue this command:

    mvn package

You can run the newly compiled and packaged JAR with the following command:

    java -cp target/BrokerSystem-1.0-SNAPSHOT-jar-with-dependencies.jar rt.brokerage.view.BrokerageUI
    
Alternatively, you can open the maven project from the NetBeans IDE (hasn't been tested on Eclipse and IntelliJ).  From there, you can `Build Project` to generate the necessary jar files or run the `BrokerageUI.java` file to launch the application immediately. 

<b>NOTE:</b> Before you can run the application, you will have to change the database configuration variables in `src/resources/db.properties` using the settings for your local MySQL version.

Licensing
------------
Please see the file called LICENSE.
