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
 * This class contains the parameters corresponding to the attributes of Quote
 * Table. It enables the user to set and get values for these parameters.  This 
 * class is used primarily in conjunction with the QuoteTable class.
 * 
 * @author Ryan Tonini
 */
public class QuoteItem {
    
    private String ticker;
    private String exchange;
    private double lastTrade;
    private String tradeTime;
    private String tradeDate;
    private double change;
    private double percentChange;
    private double prevClose;

    public QuoteItem(String ticker, String exchange, double lastTrade, String tradeTime,
                     String tradeDate, double change, double percentChange, double prevClose) {
        this.ticker = ticker;
        this.exchange = exchange;
        this.lastTrade = lastTrade;
        this.tradeTime = tradeTime;
        this.tradeDate = tradeDate;
        this.change = change;
        this.percentChange = percentChange;
        this.prevClose = prevClose;
    }

    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getExchange() {
        return exchange;
    }

    public double getPercentChange() {
        return percentChange;
    }

    public void setPercentChange(double percentChange) {
        this.percentChange = percentChange;
    }

    public void setName(String name) {
        this.exchange = name;
    }

    public String getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(String tradeTime) {
        this.tradeTime = tradeTime;
    }

    public double getChange() {
        return change;
    }

    public void setChange(double change) {
        this.change = change;
    }

    public double getPrevClose() {
        return prevClose;
    }

    public void setPrevClose(double prevClose) {
        this.prevClose = prevClose;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public double getLastTrade() {
        return lastTrade;
    }

    public void setLastTrade(double lastTrade) {
        this.lastTrade = lastTrade;
    }

}
