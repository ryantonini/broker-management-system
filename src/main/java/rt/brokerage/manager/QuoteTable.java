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
 * Writes to and reads data from the Quote Table in the database.
 * 
 * @author Ryan Tonini
 */
public class QuoteTable extends Table<QuoteItem> {
    
    public QuoteTable(Connection con){
        super(con);
    }
    
    @Override
    public int add(QuoteItem qi) throws SQLException {
        String query = 
        "INSERT INTO Quote(ticker, exchange, last_trade, time, date, " + 
        "chng, percent_chng, prev_close) Values(?, ?, ?, ?, ?, ?, ?, ?)";      
        try (PreparedStatement preparedStmt = con.prepareStatement(query)) {
            preparedStmt.setString(1, qi.getTicker());
            preparedStmt.setString(2, qi.getExchange());
            preparedStmt.setDouble(3, qi.getLastTrade());
            preparedStmt.setString(4, qi.getTradeTime());
            preparedStmt.setString(5, qi.getTradeDate());
            preparedStmt.setDouble(6, qi.getChange());
            preparedStmt.setDouble(7, qi.getPercentChange());
            preparedStmt.setDouble(8, qi.getPrevClose());
            preparedStmt.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(QuoteTable.class.getName()).log(Level.SEVERE, null, e);
        }
        return 0;
    }
    
    /**
     * Update the stock quote for the given ticker.
     * 
     * @param qi the updated stock quote.  The stock quote contains the ticker symbol,
     *        last trade, trade time, trade date, change, percent change, and
     *        previous close.
     * @throws java.sql.SQLException if the SQL query doesn't execute successfully.
     */
    public void update(QuoteItem qi) throws SQLException {
        String query = 
        "UPDATE Quote SET last_trade = ?, time = ?, date = ?, chng = ?, percent_chng = ?, " +
        "prev_close = ? WHERE ticker = ?";     
        try (PreparedStatement preparedStmt = con.prepareStatement(query)) {
            preparedStmt.setDouble(1, qi.getLastTrade());
            preparedStmt.setString(2, qi.getTradeTime());
            preparedStmt.setString(3, qi.getTradeDate());
            preparedStmt.setDouble(4, qi.getChange());
            preparedStmt.setDouble(5, qi.getPercentChange());
            preparedStmt.setDouble(6, qi.getPrevClose());
            preparedStmt.setString(7, qi.getTicker());
            preparedStmt.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(QuoteTable.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    /**
     * Get the list of ticker symbols in the database. 
     * 
     * @return list of ticker symbols.
     */
    public List<String> getTickers() {       
        String query = "SELECT ticker FROM quote";
        List<String> tickerList = new ArrayList<>();
        Statement stmt;
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                /* Retrieve by column name */
                String tk = rs.getString("ticker");
                tickerList.add(tk);
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuoteTable.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return tickerList;   
    }
}
