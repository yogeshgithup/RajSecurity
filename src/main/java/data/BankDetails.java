/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

public class BankDetails {
    String id,ifsc,account,name,branch,city;
    public BankDetails()
    {
        
    }
    public BankDetails(String id,String ifsc,String account,String name,String branch,String city)
    {
        this.id=id;
        this.account=account;
        this.branch=branch;
        this.city=city;
        this.name=name;
        this.ifsc=ifsc;
    }

    public String getAccount() {
        return account;
    }

    public String getBranch() {
        return branch;
    }

    public String getCity() {
        return city;
    }

    public String getId() {
        return id;
    }

    public String getIfsc() {
        return ifsc;
    }

    public String getName() {
        return name;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
