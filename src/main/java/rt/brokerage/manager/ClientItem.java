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
 * This class contains the parameters corresponding to the attributes of Client
 * Table. 
 * 
 * It enables the user to set and get values for these parameters.  This 
 * class is used primarily in conjunction with the ClientTable class.
 * 
 * @author Ryan Tonini
 */
public class ClientItem {
   
    private String firstName;
    private String lastName;
    private String address;
    private String zip; 
    private String city;
    private String state;
    private String country;
    private String ssn; //format = XXXXXXXXX
    private String phone; //format = XXXXXXXXXX
    private String email;
    
    public ClientItem(String firstName, String lastName, String address, 
                        String city, String zip, String state, String country,
                        String ssn, String phone, String email){
        
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.zip = zip;
        this.state = state;
        this.country = country;
        this.ssn = ssn;
        this.phone = phone;
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
