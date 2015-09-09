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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Writes to and reads data from the AccountManager Table in the database. 
 * 
 * @author Ryan Tonini
 */
public class AccountManagerTable extends Table<AccountManagerItem>{
    
    public AccountManagerTable(Connection con){
        super(con);
    }

    @Override
    public int add(AccountManagerItem ami) throws SQLException {
        String query =
        "INSERT INTO AccountManager(acctNo, cID) Values(?, ?)";
         try (PreparedStatement preparedStmt = con.prepareStatement(query)) {
            preparedStmt.setInt(1, ami.getAcctNo());
            preparedStmt.setInt(2, ami.getcID());
            preparedStmt.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(AccountManagerTable.class.getName()).log(Level.SEVERE, null, e);
        }     
        return 0;
    }
   
    /**
     * Find all the accounts of the given client.  
     * 
     * @param cID the client id. 
     * @return list of account numbers. 
     */
    public List<Integer> findUserAccounts(int cID) {
        String query = "SELECT * FROM AccountManager WHERE cID = ?";
        List<Integer> idList = new ArrayList<>();
        PreparedStatement preparedStmt;
        
        try  {
            preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, cID);
            ResultSet rs = preparedStmt.executeQuery();
            while(rs.next()){
                //Retrieve by column name
                idList.add(rs.getInt("acctNo"));
            }
            rs.close();
            preparedStmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccountTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idList;
    }
}
