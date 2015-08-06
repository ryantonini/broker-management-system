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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ryantonini
 */

public class AccountTable extends Table<AccountItem> {
    
    public AccountTable(Connection con){
        super(con);
    }

    @Override
    public int add(AccountItem ai) throws SQLException {
        int acctNo = 0;
        String query = 
        "INSERT INTO Account(type, total_value, cash, status, open_date) " + 
        "Values(?, ?, ?, ?, ?)";
        
        try (PreparedStatement preparedStmt = con.prepareStatement(query, 
                PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStmt.setString(1, ai.getType()); 
            preparedStmt.setDouble(2, ai.getTotalValue());
            preparedStmt.setDouble(3, ai.getCash());
            preparedStmt.setString(4, ai.getStatus());
            preparedStmt.setString(5, ai.getOpenDate());
            preparedStmt.executeUpdate();
            ResultSet keyResultSet = preparedStmt.getGeneratedKeys();
            if (keyResultSet.next()) {
                acctNo = (int) keyResultSet.getInt(1);
            }
        } catch (SQLException e) {
            Logger.getLogger(AccountTable.class.getName()).log(Level.SEVERE, null, e);
        }      
        return acctNo;
    }
    
    public void updateNumericSettings(AccountItem ai, int acctNo) {
        String query =
        "UPDATE Account SET total_value = ?, cash = ? " +
        "WHERE acctNo = ?";
        
        try (PreparedStatement preparedStmt = con.prepareStatement(query)) {
            preparedStmt.setDouble(1, ai.getTotalValue());
            preparedStmt.setDouble(2, ai.getCash());
            preparedStmt.setInt(3, acctNo);
            preparedStmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void updateDateSettings(AccountItem ai, int acctNo) {
        String query = 
        "UPDATE Account SET status = ?, close_date = ? " +
        "WHERE acctNo = ?";
        
        try (PreparedStatement preparedStmt = con.prepareStatement(query)) {
            preparedStmt.setString(1, ai.getStatus());
            preparedStmt.setString(2, ai.getCloseDate());
            preparedStmt.setInt(3, acctNo);
            preparedStmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public AccountItem getInfo(int accountNumber) {
        String query = "SELECT * FROM Account WHERE acctNo = ?";
        AccountItem ai = new AccountItem("", 0, 0, "", "", "");
        PreparedStatement preparedStmt;
        try {
            preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, accountNumber);
            ResultSet rs = preparedStmt.executeQuery();
            while(rs.next()){
                //Retrieve by column name
                ai.setType(rs.getString("type"));
                ai.setTotalValue(rs.getDouble("total_value"));
                ai.setCash(rs.getDouble("cash"));
                ai.setStatus(rs.getString("status"));
                ai.setOpenDate(rs.getString("open_date"));
                ai.setCloseDate(rs.getString("close_date"));
                break;
            }
            rs.close();
            preparedStmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccountTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ai;
    } 
}


