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

/**
 * This class contains the parameters corresponding to the attributes of Account
 * Table. 
 * 
 * It enables the user to set and get values for these parameters.  This 
 * class is used primarily in conjunction with the AccountTable class. 
 * 
 * @author Ryan Tonini
 */
public class AccountItem {
    
    private int acctNo;
    private String type;
    private double totalValue; // cash + investment value
    private double cash; // money that can be used to buy stock.
    private String status; // either 'open' or 'closed' account
    private String openDate;
    private String closeDate;
    
    public AccountItem(String type, double totalValue, double cash,
                              String status, String openDate, String closeDate){
        this.type = type;
        this.totalValue = totalValue;
        this.cash = cash;
        this.status = status; 
        this.openDate = openDate; 
        this.closeDate = closeDate;
    }

    public int getAcctNo() {
        return acctNo;
    }

    public void setAcctNo(int acctNo) {
        this.acctNo = acctNo;
    }

    
    public String getType() {
        return type;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOpenDate() {
        return openDate;
    }

    public void setOpenDate(String openDate) {
        this.openDate = openDate;
    }

    public String getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(String closeDate) {
        this.closeDate = closeDate;
    }

    public void setType(String type) {
        this.type = type;
    }
            
}
