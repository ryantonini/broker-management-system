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
import rt.brokerage.quote.StockLookup;

/**
 *
 * @author ryantonini
 */

public class PortfolioTable extends Table<PortfolioItem> {
    
    public PortfolioTable(Connection con){
        super(con);
    }
    
    @Override
    public int add(PortfolioItem pi) throws SQLException {
        String query = 
        "INSERT INTO Portfolio(acctNo, ticker, qty, purchase_price, current_price, " +
        "total_value, net) Values(?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement preparedStmt = con.prepareStatement(query)) {
            preparedStmt.setInt(1, pi.getAcctNo());
            preparedStmt.setString(2, pi.getTicker());
            preparedStmt.setInt(3, pi.getQty());
            preparedStmt.setDouble(4, pi.getPurchasePrice());
            preparedStmt.setDouble(5, pi.getCurrentPrice());
            preparedStmt.setDouble(6, pi.getTotalValue());
            preparedStmt.setDouble(7, pi.getNet());
            preparedStmt.executeUpdate();
        } catch (SQLException e) {
             Logger.getLogger(PortfolioTable.class.getName()).log(Level.SEVERE, null, e);
        }
        return 0;
    }
    
    public void update(PortfolioItem pi) {
        String query = 
        "UPDATE Portfolio SET qty = ?, current_price = ?, total_value = ?, net = ? " +
        "WHERE ticker = ? and acctNo = ?";
        
        try (PreparedStatement preparedStmt = con.prepareStatement(query)){
            preparedStmt.setInt(1, pi.getQty());
            preparedStmt.setDouble(2, pi.getCurrentPrice());
            preparedStmt.setDouble(3, pi.getTotalValue());
            preparedStmt.setDouble(4, pi.getNet());
            preparedStmt.setString(5, pi.getTicker());
            preparedStmt.setInt(6, pi.getAcctNo());
            preparedStmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PortfolioTable.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
   
    public PortfolioItem getInfo(int accountNumber, String symbol) {
        String query = 
        "SELECT * FROM Portfolio WHERE acctNo = ? and ticker = ?";
        PortfolioItem pi = new PortfolioItem(0, "", 0, 0, 0, 0, 0);
        PreparedStatement preparedStmt;
        try {
            preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, accountNumber);
            preparedStmt.setString(2, symbol);
            ResultSet rs = preparedStmt.executeQuery();
            while(rs.next()){
                //Retrieve by column name
                pi.setAcctNo(accountNumber);
                pi.setTicker(symbol);
                pi.setQty(rs.getInt("qty"));
                pi.setPurchasePrice(rs.getDouble("purchase_price"));
                pi.setCurrentPrice(rs.getDouble("current_price"));
                pi.setTotalValue(rs.getDouble("total_value"));
                pi.setNet(rs.getDouble("net"));
            }
            rs.close();
            preparedStmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuoteTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pi;
    }
    
    /**
     *
     * @param accountNumber
     * @param symbol
     */
    public void delete(int accountNumber, String symbol) {
        String query = "DELETE FROM Portfolio WHERE acctNo = ? and ticker = ?";
        try(PreparedStatement preparedStmt = con.prepareStatement(query)) {
            preparedStmt.setInt(1, accountNumber);
            preparedStmt.setString(2, symbol);
            preparedStmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PortfolioTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<PortfolioItem> updateAll(int acctNo) {
        String query = "SELECT * FROM Portfolio WHERE acctNo = ?";
        StockLookup lookup = new StockLookup();
        ArrayList<PortfolioItem> piList = new ArrayList<>();
        PreparedStatement preparedStmt;
            
        try {
            preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, acctNo);
            ResultSet rs = preparedStmt.executeQuery();
            while(rs.next()){
                //Retrieve by column name
                String symbol = rs.getString("ticker");
                int qty = rs.getInt("qty");
                double purchasePrice = rs.getDouble("purchase_price");
                double oldTotalValue = rs.getDouble("total_value");
                double net = rs.getDouble("net");
                double newCurrentPrice = lookup.getStockInfo(symbol).getLastTrade();
                double newTotalValue = newCurrentPrice * qty;
                double newNet = net + (newTotalValue - oldTotalValue);
                PortfolioItem pi = new PortfolioItem(acctNo, symbol, qty, purchasePrice,
                                                 newCurrentPrice, newTotalValue, newNet);
                piList.add(pi);
                update(pi);
            }
            rs.close();
            preparedStmt.close();                      
        } catch (SQLException ex) {
            Logger.getLogger(PortfolioTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return piList;
    }
}
