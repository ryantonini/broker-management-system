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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ryantonini
 */

public class TransactionTable extends Table<TransactionItem> {
    
    public TransactionTable(Connection con){
        super(con);
    }
    
    @Override
    public int add(TransactionItem ti) throws SQLException {
        String query = 
        "INSERT INTO Transaction(acctNo, ticker, type, qty, price, date) " +
        "Values(?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement preparedStmt = con.prepareStatement(query)) {
            preparedStmt.setInt(1, ti.getAcctNo());
            preparedStmt.setString(2, ti.getTicker());
            preparedStmt.setString(3, ti.getType());
            preparedStmt.setInt(4, ti.getQty());
            preparedStmt.setString(5, ti.getPrice());
            preparedStmt.setString(6, ti.getDate());
            preparedStmt.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(TransactionTable.class.getName()).log(Level.SEVERE, null, e);
        }       
        return 0;
    }  
}


