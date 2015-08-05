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

package rt.brokerage.quote;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

import rt.brokerage.main.QuoteItem;

/**
 * @author ryantonini
 */
public class StockLookup {
    
    /**
     * Gets the stock quote for the ticker symbol in the arguments of the method 
     * from YahooFinance.
     * 
     * @param symbol the stock symbol to get the quote for
     * @return the stock quote for the symbol.  The quote returned includes the 
     *         company name, last trade,trade time, date, change, open, previous
     *         close and volume. 
     */
    public QuoteItem getStockInfo(String symbol) {        
        QuoteItem stockInfo = null;
        try {
            URL yahooFinance;
            yahooFinance = new URL("http://finance.yahoo.com/d/quotes.csv?s=" + symbol + "&f=nl1t1c1opv");
            URLConnection yc = yahooFinance.openConnection();
            BufferedReader input = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            String inputLine;
            while ((inputLine = input.readLine()) != null) {
                inputLine = inputLine.replaceAll(", ", " ");
                String[] yahooStockInfo = inputLine.split(",");
                String name = yahooStockInfo[0].replace("\"", "");
                double lastTrade = Double.parseDouble(yahooStockInfo[1]);
                String tradeTime = yahooStockInfo[2].replace("\"", "");
                double change = Double.parseDouble(yahooStockInfo[3]);
                double open = Double.parseDouble(yahooStockInfo[4]);
                double prevClose = Double.parseDouble(yahooStockInfo[5]);
                int volume = Integer.parseInt(yahooStockInfo[6]);
                Date date = new Date( );
                // same as sql date format
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
                String tradeDate = sdf.format(date);
                stockInfo = new QuoteItem(symbol, name, lastTrade, tradeTime, 
                                    tradeDate, change, open, prevClose, volume);
                break;
            }
            input.close();
        } catch (IOException | NumberFormatException ex) {
            System.err.println("Unable to get stockinfo for: " + symbol);
            System.err.println(ex.getMessage());
        }
        return stockInfo;
     }
    
    public static void main(String[] args){
        StockLookup sq = new StockLookup();
        QuoteItem qi = sq.getStockInfo("FB");
        System.out.println(qi.getLastTrade());
        System.out.println(qi.getPrevClose());
        System.out.println(qi.getName());
        System.out.println(qi.getTicker());
        System.out.println(qi.getTradeTime());
        System.out.println(qi.getVolume());
    }
    
}
