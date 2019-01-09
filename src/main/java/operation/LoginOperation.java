
package operation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginOperation {
 Connection c;
 public LoginOperation(Connection c)
 {
     this.c=c;
 }
 public boolean getEmail(String email)
 {
     boolean t=false;
     Statement st;
     ResultSet rs;
     try{
         st=c.createStatement();
         rs=st.executeQuery("select email from system_user_login where email='"+email+"'");
         while(rs.next())
         {
             t=true;
         }
     }
     catch(Exception e)
     {
         System.out.println("Error at email");
     }
     return t;
 }
 public boolean getPassowrd(String email,String pass)
 {
     boolean t=false;
     Statement st;
     ResultSet rs;
     try{
         st=c.createStatement();
         rs=st.executeQuery("select email from system_user_login where email='"+email+"' and password='"+pass+"'");
         while(rs.next())
         {
             t=true;
         }
     }
     catch(Exception e)
     {
         System.out.println("Error at email");
     }
     return t;
 }
 public boolean isAdmin(String email)
 {
     boolean t=false;
     Statement st;
     ResultSet rs;
     try{
         st=c.createStatement();
         rs=st.executeQuery("select user_role from system_user_login where email='"+email+"'");
         while(rs.next())
         {
             if(rs.getString("user_role").equals("admin"))
             t=true;
         }
     }
     catch(Exception e)
     {
         System.out.println("Error at email");
     }
     return t;
 }
 public boolean isClient(String email)
 {
     boolean t=false;
     Statement st;
     ResultSet rs;
     try{
         st=c.createStatement();
         rs=st.executeQuery("select user_role from system_user_login where email='"+email+"'");
         while(rs.next())
         {
             if(rs.getString("user_role").equals("client"))
             t=true;
         }
     }
     catch(Exception e)
     {
         System.out.println("Error at email");
     }
     return t;
 }
 public void addClient(String email,String password,String contact)
 {
     PreparedStatement ps;
     try
     {
         ps=c.prepareStatement("insert into system_user_login values(?,?,?,?)");
         ps.setString(1, password);
         ps.setString(2, "client");
         ps.setString(3, email);
         ps.setString(4, contact);
         int u = ps.executeUpdate();
         System.out.println("Added Client User");
         ps.close();
     }
     catch(Exception ex)
     {
         System.out.println("Error in adding client user");
     }
 }
 public boolean checkPassword(String email,String password)
 {
     boolean t=false;
     Statement st;
     ResultSet rs;
     try{
         st=c.createStatement();
         rs=st.executeQuery("select password from system_user_login where email='"+email+"' and password='"+password+"'");
         while(rs.next())
         {
             t=true;
         }
     }
     catch(Exception e)
     {
         System.out.println("Error at email");
     }
     return t;
 }
 public String updatePassword(String email,String password)
 {
     String t=null;
     PreparedStatement st;
     ResultSet rs;
     try{
         st=c.prepareStatement("update system_user_login set password='"+password+"' where email='"+email+"'");
         int up = st.executeUpdate();
         t="Password Changed Successfully";
     }
     catch(Exception e)
     {
         System.out.println("Error at email");
         t="There Was Error While Updating Password";
     }
     return t;   
 }
}
