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

    java -cp target/BrokerSystem-1.0-SNAPSHOT.jar com.mycompany.app.App
    
The script will create a SQLite database file in the current working directory loaded with data from http://www.gamefaqs.com/.

## Broker Management System
#### Intoduction
A simple broker management system built from the Java Swing framework.  The system provides tools for the individual broker to manage client and account details, perform client transactions, view market data, monitor client portfolios, and other key functionalities.  A full list of supported features is included below.  
#### Getting Started
To get started, you will need the MySQL community server set up on your operating system.  The download page can be found at http://dev.mysql.com/downloads/mysql/.  

Next, you can either download a zip file of the repo directly from Github or clone the repo to your system locally if your familar with git.  It is recommended that you use the NetBeans IDE to open the project.  You can use the file -> import project -> from zip to view the project on NetBeans.

Before you can run the application, you will have to change the database configuration settings in the src/resources/db.properties file.  Use the settings for your MySQL version.  The included values are just a sample.   
#### Licensing 
Please see the file called LICENSE.
