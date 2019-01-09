/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.Serializable;

/**
 *
 * @author Yogesh Chawla
 */
public class User implements Serializable{
    String userid,fname,mname,lname,email,number;
    public User()
    {
        
    }
    public User(String userid,String fname,String mname,String lname,String email,String number)
    {
        this.userid = userid;
        this.fname = fname;
        this.mname = mname;
        this.lname = lname;
        this.email = email;
        this.number = number;  
    }

    public String getEmail() {
        return email;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getMname() {
        return mname;
    }

    public String getNumber() {
        return number;
    }

    public String getUserid() {
        return userid;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
    
}
