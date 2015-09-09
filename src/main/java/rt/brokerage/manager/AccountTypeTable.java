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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Writes to and reads data from the AccountType Table in the database.
 * 
 * @author Ryan Tonini
 */
public class AccountTypeTable extends Table<AccountTypeItem> {
    
    public AccountTypeTable(Connection con){
        super(con);
    }
   
    @Override
    public int add(AccountTypeItem ati) throws SQLException {
        String query = 
        "INSERT INTO AccountType(name, ownership, descr, min_invest, " +
        "commission, maintenance, period) Values(?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement preparedStmt = con.prepareStatement(query)) {
            preparedStmt.setString(1, ati.getName());
            preparedStmt.setString(2, ati.getOwnership());
            preparedStmt.setString(3, ati.getDesc());
            preparedStmt.setDouble(4, ati.getMinInvest());
            preparedStmt.setDouble(5, ati.getCommission());
            preparedStmt.setDouble(6, ati.getMaintenance());
            preparedStmt.setString(7, ati.getPeriod());
            preparedStmt.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(AccountTypeTable.class.getName()).log(Level.SEVERE, null, e);
        }       
        return 0;
    }
   
    /**
     * Get info from each account type.
     * 
     * @return list of row items corresponding to each account type.
     */
    public List<AccountTypeItem> getInfo() {
        String query = "SELECT * FROM AccountType";
        List<AccountTypeItem> typeList = new ArrayList<>();
        AccountTypeItem ati = new AccountTypeItem("", "", "", 0, 0, 0, "");
        Statement stmt;
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()) {
                /* Retrieve by column name */
                ati.setName(rs.getString("name"));
                ati.setOwnership(rs.getString("ownership"));
                ati.setDesc(rs.getString("descr"));
                ati.setMinInvest(rs.getDouble("min_invest"));
                ati.setCommission(rs.getDouble("commission"));
                ati.setMaintenance(rs.getDouble("maintenance"));
                ati.setPeriod(rs.getString("period"));
                typeList.add(ati);
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccountTypeTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return typeList;  
    }

    /**
     * Get info for the account type of the account given. 
     * 
     * @param acctNo the account number.
     * @return row item corresponding to the account type.
     */
    public AccountTypeItem getInfo(int acctNo) {
        String query = "SELECT * FROM AccountType WHERE name in (SELECT type FROM " +
        "Account WHERE acctNo = ?)";
        AccountTypeItem ati = new AccountTypeItem("", "", "", 0, 0, 0, "");
        PreparedStatement preparedStmt;        
        try {
            preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, acctNo);
            ResultSet rs = preparedStmt.executeQuery();
            while(rs.next()){
                /* Retrieve by column name */
                ati.setName(rs.getString("name"));
                ati.setOwnership(rs.getString("ownership"));
                ati.setDesc(rs.getString("descr"));
                ati.setMinInvest(rs.getDouble("min_invest"));
                ati.setCommission(rs.getDouble("commission"));
                ati.setMaintenance(rs.getDouble("maintenance"));
                ati.setPeriod(rs.getString("period"));
                break;
            }
            rs.close();
            preparedStmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccountTypeTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ati;   
    }
}
