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
 * AccountManager Table. It enables the user to set and get values for these 
 * parameters.  This class is used primarily in conjunction with the 
 * AccountManagerTable class.
 * 
 * @author ryantonini
 */

public class AccountManagerItem {
    
    private int acctNo;
    private int cID;
    
    public AccountManagerItem(int acctNo, int cID) {
        this.acctNo = acctNo;
        this.cID = cID;
    }

    public int getAcctNo() {
        return acctNo;
    }

    public void setAcctNo(int acctNo) {
        this.acctNo = acctNo;
    }

    public int getcID() {
        return cID;
    }

    public void setcID(int cID) {
        this.cID = cID;
    }

}