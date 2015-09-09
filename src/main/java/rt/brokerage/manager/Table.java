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
import java.sql.SQLException;

/**
 * Base class for all table implementations.  Each table will use a specific
 * row item object corresponding to its own schema.
 * 
 * @author Ryan Tonini
 * @param <T> row item for the specific table
 */
public abstract class Table<T> {
    
    protected Connection con;
    
    public Table(Connection con) {
        this.con = con;
    }
   
    /**
     * Add the row item to the database.
     * 
     * The table to which the row item is added will depend on the class from 
     * which the method is called.
     * 
     * @param t the row item to be inserted into the database
     * @return an integer value.  For most implementations of table, the value 
     *         returned is 0.  In this case, the value has no significance.  For 
     *         the Account Table, the value returned is the account number, which 
     *         is useful when performing other queries.
     * @throws SQLException if the info is not successfully inserted into the
     *         database.
     */
    public abstract int add(T t) throws SQLException;
}

