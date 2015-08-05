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

/**
 * This class contains the parameters corresponding to the attributes of 
 * Transaction Table. It enables the user to set and get values for these 
 * parameters.  This class is used primarily in conjunction with the 
 * TransactionTable class.
 * 
 * @author ryantonini
 */

public class TransactionItem {
   
    private int acctNo;
    private String ticker;
    private String type; //either buy or sell
    private int qty;
    private String price; // currently only supports market price
    private String date;

    public TransactionItem(int acctNo, String ticker, String type, 
                            int qty, String price, String date) {
        this.acctNo = acctNo;
        this.ticker = ticker;
        this.type = type;
        this.qty = qty;
        this.price = price;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAcctNo() {
        return acctNo;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setAcctNo(int acctNo) {
        this.acctNo = acctNo;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
    
    
    
}
