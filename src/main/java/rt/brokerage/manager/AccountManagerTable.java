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
 * This class is used to interact with the AccountManager table in the database.
 * 
 * @author ryantonini
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
}
