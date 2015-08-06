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
 * AccountType Table. It enables the user to set and get values for these 
 * parameters.  This class is used primarily in conjunction with the
 * AccountTypeTable class.
 * 
 * @author ryantonini
 */

public class AccountTypeItem {
    
    private String name;
    private String ownership; // either individual or joint/JTWROS
    private String desc;
    private double minInvest;
    private double commission;
    private double maintenance;
    private String period;
    
    public AccountTypeItem(String name, String ownership, String desc, 
                            double minInvest, double commission, 
                            double maintenance, String period) {
        this.name = name;
        this.ownership = ownership;
        this.desc = desc;
        this.minInvest = minInvest;
        this.commission = commission;
        this.maintenance = maintenance;
        this.period = period;
    }

    public double getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(double maintenance) {
        this.maintenance = maintenance;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnership() {
        return ownership;
    }

    public void setOwnership(String ownership) {
        this.ownership = ownership;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getMinInvest() {
        return minInvest;
    }

    public void setMinInvest(double minInvest) {
        this.minInvest = minInvest;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }
}
