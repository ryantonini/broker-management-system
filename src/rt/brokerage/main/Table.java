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
import java.sql.SQLException;

/**
 *
 * @author ryantonini
 * @param <T>
 */
public abstract class Table<T> {
    
    protected Connection con;
    
    /**
     *
     * @param con
     */
    public Table(Connection con) {
        this.con = con;
    }
    
    /**
     *
     * @param t
     * @return
     * @throws SQLException
     */
    public abstract int add(T t) throws SQLException;
    
}
