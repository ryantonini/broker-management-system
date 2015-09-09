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
 * This class contains the parameters corresponding to the attributes of 
 * Portfolio Table. 
 * 
 * It enables the user to set and get values for these parameters.  This class 
 * is used primarily in conjunction with the PortfolioTable class.
 * 
 * @author Ryan Tonini
 */

public class PortfolioItem {
    
    private int acctNo;
    private String ticker;
    private int qty;
    private double purchasePrice;
    private double currentPrice;
    private double totalValue;
    private double net;

    public PortfolioItem(int acctNo, String ticker, int qty, double purchasePrice, 
                            double currentPrice, double totalValue, double net) {
        this.acctNo = acctNo;
        this.ticker = ticker;
        this.qty = qty;
        this.purchasePrice = purchasePrice;
        this.currentPrice = currentPrice;
        this.totalValue = totalValue;
        this.net = net;
    }

    public int getAcctNo() {
        return acctNo;
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

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    public double getNet() {
        return net;
    }

    public void setNet(double net) {
        this.net = net;
    }
    
    
    
}
