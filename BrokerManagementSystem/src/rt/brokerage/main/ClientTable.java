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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ryantonini
 */

public class ClientTable extends Table<ClientItem>{
    
    public ClientTable(Connection con){
        super(con);
    }
  
    @Override
    public int add(ClientItem ci) throws SQLException {
        int clientID = 0;
        String query = 
        "INSERT INTO Client(first_name, last_name, address, city, zip, " +
        "state, country, ssn, phone, email) " +
        "Values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement preparedStmt = con.prepareStatement(query, 
                PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStmt.setString(1, ci.getFirstName());
            preparedStmt.setString(2, ci.getLastName());
            preparedStmt.setString(3, ci.getAddress());
            preparedStmt.setString(4, ci.getCity());
            preparedStmt.setString(5, ci.getZip());
            preparedStmt.setString(6, ci.getState());
            preparedStmt.setString(7, ci.getCountry());
            preparedStmt.setString(8, ci.getSsn());
            preparedStmt.setString(9, ci.getPhone());
            preparedStmt.setString(10, ci.getEmail());
            preparedStmt.executeUpdate();
            ResultSet keyResultSet = preparedStmt.getGeneratedKeys();
            if (keyResultSet.next()) {
                clientID = (int) keyResultSet.getInt(1);
            }
        } catch (SQLException e) {
            Logger.getLogger(ClientTable.class.getName()).log(Level.SEVERE, null, e);
        }       
        return clientID;
    }
    
    public ArrayList<ClientItem> getInfo(int acctNo) {
        String query = "SELECT * FROM Client WHERE cID in " +
        "(SELECT cID FROM AccountManager WHERE acctNo = ?)";       
        ArrayList<ClientItem> clients = new ArrayList<>();       
        PreparedStatement preparedStmt;
        try {
            preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, acctNo);
            ResultSet rs = preparedStmt.executeQuery();
            while(rs.next()){
                /* Retrieve by column name */                
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String address = rs.getString("address");
                String city = rs.getString("city");
                String zip = rs.getString("zip");
                String state = rs.getString("state");
                String country = rs.getString("country");
                String ssn = rs.getString("ssn");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                ClientItem ci = new ClientItem(firstName, lastName, address, city, 
                                                zip, state, country, ssn, phone, email);
                clients.add(ci);
            }
            rs.close();
            preparedStmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClientTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clients;     
    }
}
