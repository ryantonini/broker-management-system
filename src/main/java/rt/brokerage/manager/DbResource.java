/*
 * Copyright (C) 2015 ryantonini
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package rt.brokerage.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class provides the initial interaction with the MySQL database.  
 * 
 * It is responsible for connecting to a MySQL database (or creating it) using the 
 * received info (ie. user name, password, server name, port, database management 
 * system, and database name) from the properties file, and creating the database
 * tables.
 * 
 * Note: This class is only compatible with MySQL. 
 * 
 * @author Ryan Tonini
 */
public class DbResource {
    
    private final String userName;
    private final String password;
    private final String serverName;
    private final int port;
    private final String dbms;
    private final String dbName;
  
    public DbResource(String userName, String password, String serverName, int port, 
                    String dbms, String dbName) {
       this.userName = userName;
       this.password = password;
       this.serverName = serverName;
       this.port = port;
       this.dbms = dbms;
       this.dbName = dbName;
    }
    
    /**
     * Connects to the MySQL database.  If the database doesn't exist, it gets 
     * created.
     * 
     * @return a connection to the MySQL database.
     * @throws SQLException if connection to a non-supported database is 
     *         attempted.
     */
    public Connection connect() throws SQLException {     
       Connection con;
       Properties connectionProps = new Properties();
       connectionProps.put("user", this.userName);
       connectionProps.put("password", this.password);
      
       try {
            Class.forName("com.mysql.jdbc.Driver");
       } catch (ClassNotFoundException e) {
             System.err.println(e.getMessage());
       }
       
       /* only compatible with mysql */
       if (this.dbms.equals("mysql")) {
            con = DriverManager.getConnection(
                  "jdbc:" + this.dbms + "://" +
                  this.serverName + ":" + 
                  String.valueOf(this.port) + 
                  "/", connectionProps);
            System.out.println("Connection To Server Established");
            this.createDatabase(con);
            con.setCatalog(this.dbName);
            System.out.println("Connection to Database Established");
            return con;
        } else {
           System.out.println("Unsupported Database System");  
           throw new SQLException();
       }
    }

    /**
     * Creates the database, if it does not already exists.
     * 
     * @param con the connection object.
     * @throws SQLException if the create database SQL query fails.
     */
    public void createDatabase(Connection con) throws SQLException {
       try (Statement stmt = con.createStatement()) {
           stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + this.dbName);
       } catch (SQLException ex) {
           Logger.getLogger(DbResource.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    
    /**
     * Create the tables in the database, if they do not already exist. 
     * 
     * @param con the connection object.
     * @throws SQLException if a single SQL statement fails.  
     */
    public void populate(Connection con) throws SQLException {
     
        String createClientQuery =
        "CREATE TABLE IF NOT EXISTS " +
        "Client " +
        "(cID INT NOT NULL AUTO_INCREMENT, " +
        "first_name VARCHAR(25) NOT NULL, " +
        "last_name VARCHAR(25) NOT NULL, " +
        "address VARCHAR(45) NOT NULL, " +
        "city VARCHAR(30) NOT NULL, " +
        "zip VARCHAR(12) NOT NULL, " +
        "state VARCHAR(30) NOT NULL, " +
        "country VARCHAR(20) NOT NULL, " +
        "ssn VARCHAR(14) NOT NULL, " +
        "phone VARCHAR(14) NOT NULL, " +
        "email VARCHAR(50) NOT NULL, " +
        "PRIMARY KEY (cID))";
        
        String createAccountQuery =
        "CREATE TABLE IF NOT EXISTS " +
        "Account " +
        "(acctNo INT NOT NULL AUTO_INCREMENT, " +
        "type VARCHAR(50) NOT NULL, " +
        "total_value DOUBLE(10,2) NOT NULL, " +
        "cash DOUBLE(10,2) NOT NULL, " +
        "status VARCHAR(10) NOT NULL, " +
        "open_date DATE NOT NULL, " +
        "close_date DATE, " +
        "PRIMARY KEY (acctNo))";
        
        String createAccountManagerQuery =
        "CREATE TABLE IF NOT EXISTS " +
        "AccountManager " +
        "(acctNo INT NOT NULL, " +
        "cID INT NOT NULL, " +
        "PRIMARY KEY (acctNo, cID))";
        
        String createAccountTypeQuery =
        "CREATE TABLE IF NOT EXISTS " +
        "AccountType " +
        "(name VARCHAR(50) NOT NULL, " +
        "ownership VARCHAR(20) NOT NULL, " +
        "descr TEXT NOT NULL, " +
        "min_invest DOUBLE(10,2) NOT NULL, " +
        "commission DOUBLE(8,2) NOT NULL, " +
        "maintenance DOUBLE(8,2) NOT NULL, " +
        "period VARCHAR(20) NOT NULL, " +
        "CHECK (min_invest >= 0.0 and commission >= 0.0 and maintenance >= 0.0), " +
        "PRIMARY KEY (name))";
        
        String createPortfolioQuery =
        "CREATE TABLE IF NOT EXISTS " +
        "Portfolio " +
        "(pID INT NOT NULL AUTO_INCREMENT, " +
        "acctNo INT NOT NULL, " +
        "ticker VARCHAR(8) NOT NULL, " +
        "qty INT NOT NULL, " +
        "purchase_price DOUBLE(6,2) NOT NULL, " +
        "current_price DOUBLE(6,2) NOT NULL, " +
        "total_value DOUBLE(10,2) NOT NULL, " +
        "net DOUBLE(10,2) NOT NULL, " +
        "PRIMARY KEY (pID))";
        
        String createTransactionQuery =
        "CREATE TABLE IF NOT EXISTS " +
        "Transaction " +
        "(tID INT NOT NULL AUTO_INCREMENT, " +
        "acctNo INT NOT NULL, " +
        "ticker VARCHAR(8) NOT NULL, " +
        "type VARCHAR(10) NOT NULL, " +
        "qty INT NOT NULL, " +
        "price VARCHAR(25) NOT NULL, " +
        "date DATE NOT NULL, " +
        "PRIMARY KEY (tID))";
        
        String createQuoteQuery =
        "CREATE TABLE IF NOT EXISTS " +
        "Quote " +
        "(ticker VARCHAR(8) NOT NULL, " +
        "exchange VARCHAR(40) NOT NULL, " +
        "last_trade DOUBLE(8,2) NOT NULL, " +
        "time VARCHAR(20), " +
        "date DATE, " +
        "chng DOUBLE(8,2), " +   
        "percent_chng DOUBLE(8,2), " + 
        "prev_close DOUBLE(8,2), " +                        
        "PRIMARY KEY (ticker))";
           
        String setAutoIncrementQuery = 
        "ALTER TABLE Account AUTO_INCREMENT=1001";
        
        Statement createClient = null;
        Statement createAccount = null;
        Statement createAccountManager = null;
        Statement createAccountType = null;
        Statement createPortfolio = null;
        Statement createTransaction = null;
        Statement createQuote = null;
        Statement setAuto = null;
        
        /* perform a transaction */
        try { 
            con.setAutoCommit(false);
            
            createClient = con.createStatement();
            createClient.executeUpdate(createClientQuery);
            
            createAccount = con.createStatement();
            createAccount.executeUpdate(createAccountQuery);
            
            createAccountManager = con.createStatement();
            createAccountManager.executeUpdate(createAccountManagerQuery);
            
            createAccountType = con.createStatement();
            createAccountType.executeUpdate(createAccountTypeQuery);
            
            createPortfolio = con.createStatement();
            createPortfolio.executeUpdate(createPortfolioQuery);
            
            createTransaction = con.createStatement();
            createTransaction.executeUpdate(createTransactionQuery);
            
            createQuote = con.createStatement();
            createQuote.executeUpdate(createQuoteQuery);
            
            setAuto = con.createStatement();
            setAuto.executeUpdate(setAutoIncrementQuery);
            
            con.commit();         
        } catch (SQLException ex) {
            Logger.getLogger(DbResource.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (createClient != null) {
                createClient.close(); }
            if (createAccount != null) {
                createAccount.close(); }
            if (createAccountManager != null) {
                createAccountManager.close(); }
            if (createAccountType != null) {
                createAccountType.close(); }
            if (createPortfolio != null) {
                createPortfolio.close(); }
            if (createTransaction != null) {
                createTransaction.close(); }
            if (createQuote != null) {
                createQuote.close(); }
            if (setAuto != null) {
                setAuto.close(); }
            
            con.setAutoCommit(true);    
        }
    }
    
}
