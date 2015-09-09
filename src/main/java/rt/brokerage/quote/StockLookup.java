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

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import rt.brokerage.manager.QuoteItem;

/**
 * This class is used to find stock quotes.
 * 
 * The GOOGLE Finance API is used to get quote data.  The quote data is not
 * real time, but is updated at frequent intervals (approximately every min).
 * 
 * @author Ryan Tonini
 */
public class StockLookup {
    
    /**
     * Gets the stock quote for the ticker symbol.
     * 
     * @param symbol the stock symbol to get the quote for
     * @return the stock quote for the symbol.  The quote returned includes the 
     *         ticker, exchange, last trade, trade time, date, change, percent change,
     *         previous close.
     */
    public QuoteItem getStockInfo(String symbol) {        
        QuoteItem quote = null;
        String url = renderUrl(symbol);
        Document doc;
        try {
            doc = Jsoup.connect(url).get();
            String text = doc.body().text();
            int firstIndex = text.indexOf("{");
            int secondIndex = text.indexOf("}");
            String[] values = text.substring(firstIndex+2, secondIndex).split(",");
            Map<String,String> map = new HashMap<>();   
        
            for(String attr: values){
                /* split the pairs to get key and value */
                String[] entry = attr.split(":", 2); 
                /* add them to the hashmap and trim whitespaces/quotations */
                map.put(entry[0].trim().replace("\"", ""), entry[1].trim().replace("\"", "")); 
            }
            Date date = new Date( );
            /* same as sql date format */
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
            String tradeDate = sdf.format(date);
            quote = new QuoteItem(map.get("t"), map.get("e"), Double.parseDouble(map.get("l")), 
                                map.get("ltt"), tradeDate, Double.parseDouble(map.get("c")), 
                                Double.parseDouble(map.get("cp")), 
                                Double.parseDouble(map.get("pcls_fix")));
        } catch (IOException e) {
            e.getMessage();
        }
        return quote;
    }
    
    private String renderUrl(String ticker) {
        String quoteURL = String.format("http://finance.google.com/finance/info?client=ig&q=%s", 
                                        ticker);
        return quoteURL;
    }

    public static void main(String[] args){
        StockLookup sl = new StockLookup();
        QuoteItem qi = sl.getStockInfo("IBM");
        String quoteRepr = String.format("%f, %s, %f, %f, %s, %s", qi.getChange(), 
                          qi.getExchange(), qi.getPercentChange(), qi.getLastTrade(), 
                          qi.getTradeDate(), qi.getTradeTime());
        System.out.println(quoteRepr);
    }
    
}
