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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rt.brokerage.quote.StockLookup;

/**
 * This class contains the functions to perform application tasks.  
 * 
 * The application tasks interact with the database via the Table classes.  
 * Example tasks include updating database tables after a transaction or 
 * executing a trade. 
 * 
 * @author Ryan Tonini
 */
public class QueryFunctions {
    
    private final Connection con;
    private final AccountTable accountTable;
    private final ClientTable clientTable;
    private final AccountManagerTable managerTable;
    private final AccountTypeTable typeTable;
    private final PortfolioTable portfolioTable;
    private final QuoteTable quoteTable;
    private final TransactionTable transTable;
    private final StockLookup lookup;
    
    public QueryFunctions(Connection con) {
        this.con = con;
        accountTable = new AccountTable(this.con);
        clientTable = new ClientTable(this.con);
        managerTable = new AccountManagerTable(this.con);
        typeTable = new AccountTypeTable(this.con);
        portfolioTable = new PortfolioTable(this.con);
        quoteTable = new QuoteTable(this.con);
        transTable = new TransactionTable(this.con);
        lookup = new StockLookup();
    }
    
    /**
     * Perform a trade on the account provided with the given information. 
     * 
     * @param acctNo the account number the order is to be performed on
     * @param ticker stock symbol involved in the order
     * @param type type of transaction (ie. buy, sell)
     * @param qty number of shares to be traded
     * @param price market price by default
     * @return integer value indicating the status of the trade.  A successful
     *         trade returns 0.  Unsuccessful trades return either 1 or 2.  If the
     *         market is closed when the trade is performed, then 3 is returned.
     */
    public int trade(int acctNo, String ticker, String type, int qty, String price) {      
        /* return status for message reports */
        int val = 0; 
        /* get current date of transaction */
        String tradeDate = getDate();   
        /* check if market is closed */
        if (checkMarketClosed()) {
            val = 3;
            return val;
        }
        /* get most recent price info for ticker */
        double currentPrice = lookup.getStockInfo(ticker).getLastTrade();
        double purchasePrice = currentPrice; //at buy time, purchase=current
        double totalValue = qty * currentPrice;
        double net = 0; //at buy time, no gain/loss

        /* get value, cash and commission of account */
        AccountItem data = accountTable.getInfo(acctNo);
        AccountTypeItem ati = typeTable.getInfo(acctNo);
        double accountValue = data.getTotalValue();
        double accountCash = data.getCash();
        double commission = ati.getCommission();

        /* tuple for the record in portfolio before transaction */
        PortfolioItem oldPort = portfolioTable.getInfo(acctNo, ticker);
        TransactionItem ti = new TransactionItem(acctNo, ticker, type, qty,
                price, tradeDate);
        PortfolioItem newPort = new PortfolioItem(acctNo, ticker, qty, purchasePrice,
                currentPrice, totalValue, net);
        AccountItem ai = new AccountItem("", 0, 0, "", "", "");
        
        try {
            switch (type) {
                case "Buy":
                    if (accountCash >= totalValue + commission) { // ensure sufficient funds are present
                        /* add to or update already existing tuple in portfolio table */
                        if (oldPort.getTicker().equals("")) {
                            portfolioTable.add(newPort);
                            ai.setTotalValue(accountValue - commission);
                        } else {
                            newPort.setQty(qty + oldPort.getQty());
                            newPort.setTotalValue(currentPrice * newPort.getQty());
                            newPort.setNet(currentPrice * oldPort.getQty() - oldPort.getTotalValue());
                            ai.setTotalValue(accountValue + newPort.getNet() - commission); // if price changes
                            portfolioTable.update(newPort);
                        }
                        /* make changes to account table */
                        ai.setCash(accountCash - totalValue - commission);
                        accountTable.updateNumericSettings(ai, acctNo);
                        
                    } else {
                        val = 1;
                        return val;
                    }
                    /* add to transactions table */
                    transTable.add(ti);
                    break;
                case "Sell":
                    if (oldPort.getQty() == qty) {
                        portfolioTable.delete(acctNo, ticker);
                    } else if (oldPort.getQty() > qty) {
                        ai.setCash(accountCash + totalValue);
                        newPort.setQty(oldPort.getQty() - newPort.getQty());
                        newPort.setPurchasePrice(oldPort.getPurchasePrice());
                        newPort.setTotalValue(currentPrice * newPort.getQty());
                        newPort.setNet(newPort.getTotalValue() - (oldPort.getCurrentPrice() * newPort.getQty()));
                        portfolioTable.update(newPort);
                    } else { // more qty than owned
                        val = 2;
                        return val; 
                    }
                    /* make changes to account table */
                    double diff = oldPort.getQty() * currentPrice - oldPort.getTotalValue();
                    ai.setCash(accountCash + totalValue - commission);
                    ai.setTotalValue(accountValue + diff - commission);
                    accountTable.updateNumericSettings(ai, acctNo);
                    /* add to transactions table */
                    transTable.add(ti);
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }    
        return val;     
    }
    
    /**
     * Check if the market is closed.
     * 
     * By market here, we are referring only to NYSE, NASDAQ AND TSX.  Does not 
     * currently support holidays.  
     * 
     * @return boolean value indicating whether the market is closed.  If true is
     *         returned, then the market is closed.  Otherwise, the market is 
     *         open.
     */
    public boolean checkMarketClosed() {
        boolean status = false;
        Calendar now = Calendar.getInstance(); 
        String day = "";
        int num = now.get(Calendar.DAY_OF_WEEK);
        switch (num) {
            case 1: 
                day = "Sunday"; break;
            case 2:
                day = "Monday"; break;
            case 3:
                day = "Tuesday"; break;
            case 4: 
                day = "Wednesday"; break;
            case 5:
                day = "Thursday"; break;
            case 6:
                day = "Friday";  break;
            case 7:
                day = "Saturday"; break;
        }
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);
        SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
        Date date;
        try {
            date = parser.parse(hour + ":" + minute);
            Date marketOpen = parser.parse("9:30");
            Date marketClose = parser.parse("16:00");
            if (!(date.after(marketOpen) && date.before(marketClose)) && 
                   day.matches("Saturday|Sunday")) {
                status = true;
            }
        } catch (ParseException e) {
            e.getMessage();
        }
        return status;
    }
    
    /**
     * Lookup the quote for the ticker symbol given and add it the the database.
     * 
     * @param ticker ticker symbol to lookup and add to database
     * @return the quote for the stock symbol 
     */
    public QuoteItem addQuote(String ticker) {
        QuoteItem qi = lookup.getStockInfo(ticker);
        try {
            /* insert to db */
            quoteTable.add(qi);
        } catch (SQLException ex) {
            Logger.getLogger(QueryFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return qi;
    }
    
    /**
     * Update the portfolio for the account given.  The total valuation of the 
     * account is also updated.
     * 
     * @param acctNo the account number 
     * @return array of updated portfolio quotes for the given account and the 
     *         updated account values
     */
    public Object[] updatePortfolio(int acctNo) {        
        /* update the value of each stock */
        List<PortfolioItem> portfolioItems = portfolioTable.updateAll(acctNo);
        /* convert arraylist object to array object */
        PortfolioItem[] stockArray = new PortfolioItem[portfolioItems.size()];
        stockArray = portfolioItems.toArray(stockArray);
        double totalAsset = 0;
        for (PortfolioItem pi : stockArray) {
            totalAsset += pi.getTotalValue();
        }
        /* update account total value */
        AccountItem ai = accountTable.getInfo(acctNo);
        double newTotalValue = totalAsset + ai.getCash();
        ai.setTotalValue(newTotalValue);
        accountTable.updateNumericSettings(ai, acctNo);
        AccountItem[] aiArray = new AccountItem[] {ai};
        
        return new Object[] {stockArray, aiArray} ;
    }
        
    /**
     * Get a single string containing all users for the account number given. 
     * Names are comma separated.
     * 
     * @param acctNo the account number. 
     * @return names of all the owners of the account.
     */
    public String getAccountName(int acctNo) {
        /* add code to check if its valid account number - assume it is for now */
        List<ClientItem> clients = clientTable.getInfo(acctNo);
        String group = "";
        for (ClientItem client: clients) {
            String fullName = client.getFirstName() + " " + client.getLastName();
            group += fullName + ", ";
        }
        group = group.substring(0, group.length() - 2); // remove ending comma
        return group;
    }
   
    /**
     * Add cash amount to the given account number.  
     * 
     * @param acctNo the account number.
     * @param amount the amount of cash ($) to be added.
     * @param cash the current cash total of the account.
     * @param accountValue the current value of the account.
     * @return an array containing the updated cash and total value of the account.
     */
    public Double[] addCash(int acctNo, double amount, double cash, double accountValue) {       
        /* set new cash and total value variables */
        double newCash = cash + amount;
        double newAccountValue = accountValue + amount;
        AccountItem ai = new AccountItem("", newAccountValue, newCash, "", "", "");
        accountTable.updateNumericSettings(ai, acctNo);
        return new Double[] {newCash, newAccountValue};
   }
    
    /**
     * Get all the accounts of the user.  The user is identified by first name and
     * last name.
     * 
     * @param firstName the first name of the user.
     * @param lastName the last name of the user.
     * @return list of the accounts of the given user. 
     */
    public List<AccountItem> getUserAccounts(String firstName, String lastName) {
        List<AccountItem> aiList = new ArrayList<>();
        int clientID = clientTable.getClientID(firstName, lastName);
        List<Integer> accountNumbers = managerTable.findUserAccounts(clientID);
        for (Integer i: accountNumbers) {
            AccountItem ai = accountTable.getInfo(i);
            ai.setAcctNo(i);
            aiList.add(ai);      
        }
        return aiList; 
    }
    
    /**
     * Add a new Account type to the database. 
     * 
     * @param ati the required info of the new account type.
     * @return integer value reflecting the success of the operation.  
     */
    public int addNewAccountType(AccountTypeItem ati) {
        int val = 0;
        try {
            /* insert data into db */
            typeTable.add(ati);
        } catch (SQLException ex) {
            Logger.getLogger(QueryFunctions.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return val;   
    }
    
    /**
     * Get the minimum investment criteria and ownership type for the given 
     * account type.
     * 
     * @param accountType name of the account type
     * @return array containing the minimum investment and ownership
     */
    public Object[] getAccountType(String accountType) {
        List<AccountTypeItem> types = typeTable.getInfo();
        double minInvest = 0;
        String ownership = "";
        for (AccountTypeItem type : types) {
            if (type.getName().equals(accountType)) {
                minInvest = type.getMinInvest();
                ownership = type.getOwnership();
                break;
            }
        }
        return new Object[] {minInvest, ownership};
    }
   
    public List<AccountTypeItem> getAccountTypes() {
        return typeTable.getInfo();
    }
    
    /**
     * Add new account to the database.  
     * 
     * If the initial investment does not satisfy the minimum investment of the 
     * account type, then the method exits early.
     * 
     * @param accountType type of account to create
     * @param initialInvest the initial investment 
     * @param minInvest the minimum required investment for the account type
     * @return the account number.  The account number returned also reflects the 
     *         success of the operation.  If zero is returned, then the account 
     *         was not added successfully.  If a non-zero value is returned, then 
     *         the account was created successfully. 
     */
    public int addNewAccount(String accountType, double initialInvest, 
                                    double minInvest) {       
        int acctNo = 0;
        if (initialInvest < minInvest) {
            /* initial investment to small */
            System.out.println("Initial Investment smaller than Minimun Investment.");
            return acctNo;
        }
        String status = "Open";
        String openDate = getDate();
        AccountItem ai = new AccountItem(accountType, initialInvest, initialInvest,
                status, openDate, "");       
        try {
            /* insert into Account Table and return current account number */
            acctNo = accountTable.add(ai);
        } catch (SQLException ex) {
            Logger.getLogger(QueryFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return acctNo;    
    }
    
    /**
     * Add client to the database.  Also link the client to the given account 
     * number.  This operation is typically performed after an account is created.
     * 
     * @param acctNo the account number 
     * @param ci the required information of the client
     */
    public void addClientToAccount(int acctNo, ClientItem ci) {      
        int cID;
        try {
            /* insert data to Client Table and return current clientID */
            cID = clientTable.add(ci);
            AccountManagerItem ami = new AccountManagerItem(acctNo, cID);
            /* insert into AccountManager Table */
            managerTable.add(ami);
        } catch (SQLException ex) {
            Logger.getLogger(QueryFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Close the account given.  
     * 
     * @param acctNo the account number of the account to be closed
     */
    public void closeAccount(int acctNo) {
        
        String status = "Closed";
        String closeDate = getDate();
        AccountItem ai = new AccountItem("", 0, 0, status, "", closeDate);
        /* update account table in database */
        accountTable.updateDateSettings(ai, acctNo);
    }
    
    /**
     * Update the stock quotes in the database.  
     * 
     * @return a list of updated stock quotes 
     */
    public List<QuoteItem> updateMarket() {
        List<QuoteItem> quoteList = new ArrayList<>();
        for (String ticker : quoteTable.getTickers()) {
            quoteList.add(lookup.getStockInfo(ticker));
        }
        for (QuoteItem qi : quoteList) 
            try {
                /* update quote in database */
                quoteTable.update(qi);
            } catch (SQLException ex) {
                Logger.getLogger(QueryFunctions.class.getName()).log(Level.SEVERE, null, ex);
            }
        return quoteList;
    }
    
    public String getDate(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // sql format
        String dateString = sdf.format(date);
        return dateString;
    }    
}

