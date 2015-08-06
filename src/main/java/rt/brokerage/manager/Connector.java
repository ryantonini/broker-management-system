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

package rt.brokerage.main;

import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Creates a connection to a database using the info: user name, password, 
 * server name, port, database management system, and database name.  
 * 
 * This class currently only supports mySQL databases.  
 * 
 * @author ryantonini
 */

public class Connector {
   
   private final String userName;
   private final String password;
   private final String serverName;
   private final int port;
   private final String dbms;
   private final String dbName;
  
    public Connector(String userName, String password, String serverName, int port, 
                    String dbms, String dbName) {
       this.userName = userName;
       this.password = password;
       this.serverName = serverName;
       this.port = port;
       this.dbms = dbms;
       this.dbName = dbName;
   }
   
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
            this.create_database(con);
            con.setCatalog(this.dbName);
            System.out.println("Connection to Database Established");
            return con;
        } else {
           System.out.println("Unsupported Database System");  
           throw new SQLException();
       }
    }

    public void create_database(Connection con) throws SQLException {
       try (Statement stmt = con.createStatement()) {
           stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + this.dbName);
       } catch (SQLException ex) {
           Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
   
   public static void main(String[] args) throws SQLException{
        /* check connection */
        Connector c = new Connector("root", "tampabay4", "localhost", 3306, 
                       "mysql", "verify");
        Connection con = c.connect();    
   }
}
