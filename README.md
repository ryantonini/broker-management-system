Broker Management System
========

A simple broker management system built from the Java Swing framework.  The system provides tools for the individual broker to manage client and account details, perform client transactions, view market data, monitor client portfolios, and other key functionalities.  

Requirements
------------

GameFaqsPY is conveniently available via pip:

    pip install gamefaqs-py

or installable via ``git clone`` and ``setup.py``

    git clone git@github.com:ryantonini/gamefaqs-py.git
    sudo python setup.py install

After installation, run the following python script in your project files directory:

    python load_data.py
    
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
