
package operation;

import data.Address;
import data.BankDetails;
import data.Client;
import data.ClientQuotation;
import data.Experience;
import data.SalaryOperation;
import data.SecurityPerson;
import data.SiteAddress;
import data.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.naming.ldap.HasControls;
import javax.servlet.ServletContext;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.JsonArray;

public class Operation {
    Connection c;
    
    
    public Operation(Connection c)
    {
        this.c = c;
    }
    public String addClient(Client cl)
    {
        PreparedStatement ps;
        String sql = "insert into client values(?,?,?,?)";
        String msg;
        try
        {
            ps=c.prepareStatement(sql);
            c.setAutoCommit(false);
            ps.setString(1, cl.getUserid());
            ps.setString(2, cl.getOrgname());
            ps.setString(3, cl.getUrl());
            ps.setString(4, cl.getGst());
            int u = ps.executeUpdate();
            c.commit();
            ps.close();
            msg="success";
        }
        catch(Exception e)
        {
            msg="Error At Client Add "+e.getMessage();
        }
        return msg;
    }
    public String addSiteAddress(SiteAddress s)
    {
        PreparedStatement st;
        String sql = "insert into client_site_address values(?,?,?,?,?)";
        String msg;
        try
        {
            c.setAutoCommit(false);
            st=c.prepareStatement(sql);
            st.setString(1, s.getSid());
            st.setString(2, s.getCid());
            st.setString(3, s.getAddress());
            st.setString(4, s.getArea());
            st.setString(5, s.getCno());
            int u = st.executeUpdate();
            c.commit();
            st.close();
            msg="success";
        }
        catch(Exception e)
        {
            System.out.println(msg="Error At Insert Site Address "+e.getMessage());
        }
        return msg;
    }
    public String addSecurityPerson(SecurityPerson sp)
    {
        PreparedStatement ps;
        String sql = "insert into security_person values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        String msg;
        try
        {
            c.setAutoCommit(false);
            ps=c.prepareStatement(sql);
            ps.setString(1, sp.getUserid());
            ps.setString(2, sp.getFathername());
            ps.setString(3, sp.getDOB());
            ps.setString(4, sp.getNationality());
            ps.setString(5, sp.getQualification());
            ps.setString(6, sp.getS_type());
            ps.setString(7, sp.getPost());
            ps.setString(8, sp.getStatus());
            ps.setString(9, sp.getOther());
            ps.setString(10, sp.getPstation());
            ps.setString(11, sp.getNstation());
//            ps.setString(12, sp.getSite());
            ps.setString(12, sp.getIname());
            ps.setBlob(13, sp.getImage());
            msg="success";
            int u = ps.executeUpdate();
            c.commit();
            ps.close();
        }
        catch(Exception e)
        {
            msg=e.getMessage();
        }
        
        return msg;
    }
    public String getSecurityPersonId()
    {
       PreparedStatement stmt;
       ResultSet rs;
       int  sp_id;
       int max=0;
       String sql="select substr(sp_id,3,length(sp_id)) data from security_person";
        try
        {
            stmt=c.prepareStatement(sql);
            rs=stmt.executeQuery(sql);
            
            while(rs.next())
            {
              sp_id = rs.getInt("data");
              if(max<sp_id)
              {
                  max=sp_id;
              }
             
            }
            max=max+1;
            stmt.close();
            rs.close();
        }
        catch(Exception e)
        { 
            System.out.println(e.getMessage());
        }
      return "SP"+max;
    }
    public String getClientId()
    {
       PreparedStatement stmt;
       ResultSet rs;
       int  sp_id;
       int max=0;
       String sql="select substr(client_id,3,length(client_id)) data from client";
        try
        {
            stmt=c.prepareStatement(sql);
            rs=stmt.executeQuery(sql);
            
            while(rs.next())
            {
              sp_id = rs.getInt("data");
              if(max<sp_id)
              {
                  max=sp_id;
              }
             
            }
            max=max+1;
            stmt.close();
            rs.close();
        }
        catch(Exception e)
        { 
            System.out.println(e.getMessage());
        }
      return "CL"+max;
    }
    public String getQuotationId()
    {
       PreparedStatement stmt;
       ResultSet rs;
       int  sp_id;
       int max=0;
       String sql="select substr(quotation_id,3,length(quotation_id)) data from client_quotation";
        try
        {
            stmt=c.prepareStatement(sql);
            rs=stmt.executeQuery(sql);
            
            while(rs.next())
            {
              sp_id = rs.getInt("data");
              if(max<sp_id)
              {
                  max=sp_id;
              }
             
            }
            max=max+1;
            stmt.close();
            rs.close();
        }
        catch(Exception e)
        { 
            System.out.println(e.getMessage());
        }
      return "QT"+max;
    }
     public String getSiteId()
    {
       PreparedStatement stmt;
       ResultSet rs;
       int  sp_id;
       int max=0;
       String sql="select substr(site_id,3,length(site_id)) data from client_site_address";
        try
        {
            stmt=c.prepareStatement(sql);
            rs=stmt.executeQuery(sql);
            
            while(rs.next())
            {
              sp_id = rs.getInt("data");
              if(max<sp_id)
              {
                  max=sp_id;
              }
            }
            max=max+1;
            stmt.close();
            rs.close();
        }
        catch(Exception e)
        { 
            System.out.println(e.getMessage());
        }
      return "ST"+max;
    }
    public String addUser(User u)
    {
        PreparedStatement ps;
        String sql = "insert into users values(?,?,?,?,?,?)";
        String msg;
        try
        {
            c.setAutoCommit(false);
            ps=c.prepareStatement(sql);
            ps.setString(1, u.getUserid());
            ps.setString(2, u.getFname());
            ps.setString(3, u.getMname());
            ps.setString(4, u.getLname());
            ps.setString(5, u.getEmail());
            ps.setString(6, u.getNumber());
            msg="Success";
            int up = ps.executeUpdate();
            c.commit();
            ps.close();
        }
        catch(Exception e)
        {
            msg=e.getMessage();
        }
        return msg;
    }
    public String addExperienceDetails(Experience e)
    {
        PreparedStatement ps;
        String sql="insert into experience_detail values(?,?,?,?)";
        String msg;
        try
        {
            c.setAutoCommit(false);
            ps=c.prepareStatement(sql);
            ps.setString(1, e.getId());
            ps.setString(2, e.getYears());
            ps.setString(3, e.getName());
            ps.setString(4, e.getAdd());
            int u = ps.executeUpdate();
            c.commit();
            ps.close();
            msg="success";
        }    
        catch(Exception ex)
        {
            msg=ex.getMessage();
        }
        return msg;
    }
    public String addAddress(Address a)
    {
        PreparedStatement ps;
        String sql="insert into address values(?,?,?,?,?,?,?,?)";
        String msg;
        try
        {
            c.setAutoCommit(false);
            ps=c.prepareStatement(sql);
            ps.setString(1,a.getId());
            ps.setString(2, a.getAdd_type());
            ps.setString(3, a.getAdd1());
            ps.setString(4, a.getAdd2());
            ps.setString(5, a.getArea());
            ps.setString(6, a.getDistrict());
            ps.setString(7, a.getPincode());
            ps.setString(8, a.getState());
            int u = ps.executeUpdate();
            c.commit();
            ps.close();
            msg="success";
        }
        catch(Exception e)
        {
            msg=e.getMessage();
        }
        return msg;
    }
    public String addBankDetails(BankDetails bd)
    {
        PreparedStatement ps;
        String sql="insert into bank_details values(?,?,?,?,?,?)";
        String msg;
        try
        {
            c.setAutoCommit(false);
            ps=c.prepareStatement(sql);
            ps.setString(1, bd.getId());
            ps.setString(2, bd.getIfsc());
            ps.setString(3, bd.getAccount());
            ps.setString(4, bd.getName());
            ps.setString(5, bd.getBranch());
            ps.setString(6, bd.getCity());
            int u = ps.executeUpdate();
            c.commit();
            ps.close();
            msg="success";
        }
        catch(Exception e)
        {
            msg=e.getMessage();
        }
        return msg;
    }
    public ArrayList<User> viewAllUsers(String sql)
    {
        ArrayList<User> asj = new ArrayList<User>();
        PreparedStatement ps;
        try
        {
            ps=c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
            while(rs.next())
            {
                User sub = new User();
                sub.setUserid(rs.getString("userid"));
                sub.setFname(rs.getString("firstname"));
                sub.setMname(rs.getString("middlename"));
                sub.setLname(rs.getString("lastname"));
                sub.setEmail(rs.getString("email"));
                sub.setNumber(rs.getString("contact_number"));
                asj.add(sub);
                }
            ps.close();
            rs.close();
        }
        catch(Exception ex)
        {    
            System.out.println("Error At Get All Users " +ex.getMessage());
            ex.printStackTrace();
        }
        return asj;
    }
    public ArrayList<SecurityPerson> viewAllSecurityPerson(String sql)
    {
        ArrayList<SecurityPerson> asj = new ArrayList<SecurityPerson>();
        PreparedStatement ps;
        try
        {
            ps=c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
            while(rs.next())
            {
                SecurityPerson sp = new SecurityPerson();
                sp.setUserid(rs.getString("sp_id"));
                sp.setFathername(rs.getString("father_name"));
                sp.setDOB(rs.getString("DOB"));
                sp.setNationality(rs.getString("nationality"));
                sp.setQualification(rs.getString("qualification"));
                sp.setS_type(rs.getString("s_type"));
                sp.setStatus(rs.getString("status"));
                sp.setPstation(rs.getString("nearest_police_station"));
                sp.setNstation(rs.getString("nearest_railway_station"));
                sp.setIname(rs.getString("imagename"));
                sp.setImage(rs.getBinaryStream("image"));
                asj.add(sp);
            }
        ps.close();
        rs.close();
        }
        
        
        catch(Exception e)
        {
            System.out.println("Error At viewAllSecurityPerson "+e.getMessage());
            e.printStackTrace();
        }
        return asj;
    }
    public SecurityPerson viewSecurityPerson(String id)
    {
        SecurityPerson sp = null;
        PreparedStatement ps;
        String sql="select * from security_person";
        try
        {
            
            ps=c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
            while(rs.next())
            {
                if(rs.getString("sp_id").equals(id))
                {
                 sp = new SecurityPerson();
                sp.setUserid(rs.getString("sp_id"));
                sp.setFathername(rs.getString("father_name"));
                sp.setDOB(rs.getString("DOB"));
                sp.setNationality(rs.getString("nationality"));
                sp.setQualification(rs.getString("qualification"));
                sp.setS_type(rs.getString("s_type"));
                sp.setStatus(rs.getString("status"));
                sp.setPstation(rs.getString("nearest_police_station"));
                sp.setNstation(rs.getString("nearest_railway_station"));
                sp.setIname(rs.getString("imagename"));
                sp.setImage(rs.getBinaryStream("image"));
                
                }
            }
        ps.close();
        rs.close();
        }
        
        
        catch(Exception e)
        {
            System.out.println("Error At viewAllSecurityPerson "+e.getMessage());
            e.printStackTrace();
        }
        return sp;
    }
    public ArrayList<BankDetails> viewAllBankDetails()
    {
        ArrayList<BankDetails> asj = new ArrayList<BankDetails>();
        PreparedStatement ps;
        String sql="select * from bank_details";
        try
        {
            ps=c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
            while(rs.next())
            {
                BankDetails bd = new BankDetails();
                bd.setId(rs.getString("sp_id"));
                bd.setIfsc(rs.getString("ifsc_code"));
                bd.setAccount(rs.getString("account_no"));
                bd.setName(rs.getString("bank_name"));
                bd.setBranch(rs.getString("bank_branch"));
                bd.setCity(rs.getString("bank_city"));
                asj.add(bd);
            }
            ps.close();
            rs.close();
        }
        catch(Exception e)
        {
            System.out.println("Error At viewAllBankDetails "+e.getMessage());
            e.printStackTrace();
        }
        return asj;
    }
    public ArrayList<Experience> viewAllExperiece()
    {
        ArrayList<Experience> asj = new ArrayList<Experience>();
        PreparedStatement ps;
        String sql="select * from experience_detail";
        try
        {
            ps=c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
            while(rs.next())
            {
                Experience ex = new Experience();
                ex.setId(rs.getString("sp_id"));
                ex.setYears(rs.getString("no_of_years"));
                ex.setAdd(rs.getString("address"));
                ex.setName(rs.getString("O_name"));
                asj.add(ex);
            }
            ps.close();
            rs.close();
        }
        catch(Exception e)
        {
            System.out.println("Error At viewAllExperience "+e.getMessage());
            e.printStackTrace();
        }
        return asj;
    }
    public ArrayList<Address> viewAllAddresses()
    {
        ArrayList<Address> asj = new ArrayList<Address>();
        PreparedStatement ps;
        String sql="select * from address";
        try
        {
            ps=c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
            while(rs.next())
            {
                Address ad = new Address();
                ad.setId(rs.getString("sp_id"));
                ad.setAdd_type(rs.getString("add_type"));
                ad.setAdd1(rs.getString("address_line_1"));
                ad.setAdd2(rs.getString("address_line_1"));
                ad.setArea(rs.getString("area"));
                ad.setDistrict(rs.getString("district"));
                ad.setPincode(rs.getString("pincode"));
                ad.setState(rs.getString("state"));
                asj.add(ad);
            }
            ps.close();
            rs.close();
        }
        catch(Exception e)
        {
            System.out.println("Error At viewAllAddress "+e.getMessage());
            e.printStackTrace();
        }
        return asj;
    }
    public ArrayList<BankDetails> viewAllBankDetails(String id)
    {
        ArrayList<BankDetails> asj = new ArrayList<BankDetails>();
        PreparedStatement ps;
        String sql="select * from bank_details  where sp_id='"+id+"'";
        try
        {
            ps=c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
            while(rs.next())
            {
                BankDetails bd = new BankDetails();
                bd.setId(rs.getString("sp_id"));
                bd.setIfsc(rs.getString("ifsc_code"));
                bd.setAccount(rs.getString("account_no"));
                bd.setName(rs.getString("bank_name"));
                bd.setBranch(rs.getString("bank_branch"));
                bd.setCity(rs.getString("bank_city"));
                asj.add(bd);
            }
            ps.close();
            rs.close();
        }
        catch(Exception e)
        {
            System.out.println("Error At viewAllBankDetails with String "+e.getMessage());
            e.printStackTrace();
        }
        return asj;
    }
    public ArrayList<Experience> viewAllExperiece(String id)
    {
        ArrayList<Experience> asj = new ArrayList<Experience>();
        PreparedStatement ps;
        String sql="select * from experience_detail  where sp_id='"+id+"'";
        try
        {
            ps=c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
            while(rs.next())
            {
                Experience ex = new Experience();
                ex.setId(rs.getString("sp_id"));
                ex.setYears(rs.getString("no_of_years"));
                ex.setAdd(rs.getString("address"));
                ex.setName(rs.getString("O_name"));
                asj.add(ex);
            }
            ps.close();
        }
        catch(Exception e)
        {
            System.out.println("Error At viewAllExperience with String "+e.getMessage());
            e.printStackTrace();
        }
        return asj;
    }
    public ArrayList<Address> viewAllPermanentAddresses(String id)
    {
        ArrayList<Address> asj = new ArrayList<Address>();
        PreparedStatement ps;
        String sql="select * from address where sp_id='"+id+"' and add_type='Permanent'";
        try
        {
            ps=c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
            while(rs.next())
            {
                Address ad = new Address();
                ad.setId(rs.getString("sp_id"));
                ad.setAdd_type(rs.getString("add_type"));
                ad.setAdd1(rs.getString("address_line_1"));
                ad.setAdd2(rs.getString("address_line_2"));
                ad.setArea(rs.getString("area"));
                ad.setDistrict(rs.getString("district"));
                ad.setPincode(rs.getString("pincode"));
                ad.setState(rs.getString("state"));
                asj.add(ad);
            }
            ps.close();
        }
        catch(Exception e)
        {
            System.out.println("Error At viewAllPermanentAddress with String "+e.getMessage());
            e.printStackTrace();
        }
        return asj;
    }
    public ArrayList<Address> viewAllResidentAddresses(String id)
    {
        ArrayList<Address> asj = new ArrayList<Address>();
        PreparedStatement ps;
        String sql="select * from address where sp_id='"+id+"' and add_type='Residance'";
        try
        {
            ps=c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
            while(rs.next())
            {
                Address ad = new Address();
                ad.setId(rs.getString("sp_id"));
                ad.setAdd_type(rs.getString("add_type"));
                ad.setAdd1(rs.getString("address_line_1"));
                ad.setAdd2(rs.getString("address_line_2"));
                ad.setArea(rs.getString("area"));
                ad.setDistrict(rs.getString("district"));
                ad.setPincode(rs.getString("pincode"));
                ad.setState(rs.getString("state"));
                asj.add(ad);
            }
            ps.close();
            rs.close();
        }
        catch(Exception e)
        {
            System.out.println("Error At viewAllResidentAddress with String "+e.getMessage());
            e.printStackTrace();
        }
        return asj;
    }
    public JsonArray getBankJson(String id)
    {
        Statement s;
        
        ResultSet rs;
        JsonArray ja = new JsonArray();
        
        try
        {
            s=c.createStatement();
            rs=s.executeQuery("select * from bank_details where sp_id='"+id+"'");

            while(rs.next())
            {
                Map nestedmap = new HashMap();
                nestedmap.put("id", rs.getString("sp_id"));
                nestedmap.put("id1", rs.getString("sp_id"));
                String ifsc=rs.getString("ifsc_code");
                String account=rs.getString("account_no");
                String bank_name=rs.getString("bank_name");
                String bank_branch=rs.getString("bank_branch");
                String bank_city=rs.getString("bank_city");
                nestedmap.put("ifsc", ifsc);
                nestedmap.put("account", account);
                nestedmap.put("name", bank_name);
                nestedmap.put("branch", bank_branch);
                nestedmap.put("city", bank_city);
                ja.add(nestedmap);
                                
            }
            
           s.close();
           rs.close();
        } 
        catch(Exception e)
        {
            
        } 
        return ja;
    }
    public JsonArray getResJson(String id)
    {
        PreparedStatement ps;
        
        ResultSet rs;
        JsonArray ja = new JsonArray();

        String sql="select * from address where sp_id='"+id+"' and add_type='Residance'";
        try
        {
            ps=c.prepareStatement(sql);
            rs = ps.executeQuery(sql);
            while(rs.next())
            {
                Map nestedmap = new HashMap();
                nestedmap.put("id", rs.getString("sp_id"));
                 nestedmap.put("id1", rs.getString("sp_id"));
                nestedmap.put("type",rs.getString("add_type"));
                nestedmap.put("line1",rs.getString("address_line_1"));
                nestedmap.put("line2",rs.getString("address_line_2"));
                nestedmap.put("area",rs.getString("area"));
                nestedmap.put("dis",rs.getString("district"));
                nestedmap.put("pin",rs.getString("pincode"));
                nestedmap.put("state",rs.getString("state"));
                ja.add(nestedmap);
            }
            ps.close();
            rs.close();
        }
        catch(Exception e)
        {
            System.out.println("Error At viewAllResidentAddress with String "+e.getMessage());
            e.printStackTrace();
        }
    return ja;
    }
    public JsonArray getPerJson(String id)
    {
        PreparedStatement ps;
        
        ResultSet rs;
        JsonArray ja = new JsonArray();

        String sql="select * from address where sp_id='"+id+"' and add_type='Permanent'";
        try
        {
            ps=c.prepareStatement(sql);
            rs = ps.executeQuery(sql);
            while(rs.next())
            {
                Map nestedmap = new HashMap();
                nestedmap.put("id", rs.getString("sp_id"));
                 nestedmap.put("id1", rs.getString("sp_id"));
                nestedmap.put("type",rs.getString("add_type"));
                nestedmap.put("line1",rs.getString("address_line_1"));
                nestedmap.put("line2",rs.getString("address_line_2"));
                nestedmap.put("area",rs.getString("area"));
                nestedmap.put("dis",rs.getString("district"));
                nestedmap.put("pin",rs.getString("pincode"));
                nestedmap.put("state",rs.getString("state"));
                ja.add(nestedmap);
            }
            ps.close();
            rs.close();
        }
        catch(Exception e)
        {
            System.out.println("Error At viewAllResidentAddress with String "+e.getMessage());
            e.printStackTrace();
        }
    return ja;
    }
    public JsonArray getExpJson(String id)
    {
        JsonArray ja = new JsonArray();
        PreparedStatement ps;
        String sql="select * from experience_detail  where sp_id='"+id+"'";
        try
        {
            ps=c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
            while(rs.next())
            {
                Map nestedmap = new HashMap();
                nestedmap.put("id", rs.getString("sp_id")); 
                nestedmap.put("id1", rs.getString("sp_id"));
                
                nestedmap.put("years",rs.getString("no_of_years"));
                nestedmap.put("address",rs.getString("address"));
                nestedmap.put("name",rs.getString("O_name"));
                ja.add(nestedmap);
            }
            ps.close();
            rs.close();
        }
        catch(Exception e)
        {
            System.out.println("Error At viewAllExperience with String "+e.getMessage());
            e.printStackTrace();
        }
        return ja;
    }
    public String deleteSecurity(String id)
    {
        PreparedStatement s,s1,s2,s3,s4;
        
        String sql = "delete from users where userid=?";
        String sql1 = "delete from security_person where sp_id=?";
        String sql2 = "delete  from bank_details where sp_id=?";    
        String sql3 = "delete  from address where sp_id=?";
        String sql4 = "delete  from experience_detail where sp_id=?";
        try
        {
            s=c.prepareStatement(sql);
            s.setString(1, id);
            s1=c.prepareStatement(sql1);
            s1.setString(1, id);
            s2=c.prepareStatement(sql2);
            s2.setString(1, id);
            s3=c.prepareStatement(sql3);
            s3.setString(1, id);
            s4=c.prepareStatement(sql4);
            s4.setString(1, id);
            int u1 = s4.executeUpdate();
            int u2 = s3.executeUpdate();
            int u3 = s2.executeUpdate();
            int u4 = s1.executeUpdate();
            int u5 = s.executeUpdate();
            s.close();
            s1.close();
            s2.close();
            s3.close();
            s4.close();
            return "Deleted Succesfully";
            
            
        }
        catch(Exception e)
        {
            System.out.println("Error at Delet User");
            e.printStackTrace();
            return e.getMessage();
        }
    }
    public String deleteSecurity1(String id)
    {
        PreparedStatement s1,s2,s3,s4;
        String sql1 = "delete  from security_person where sp_id=?";
        String sql2 = "delete  from bank_details where sp_id=?";    
        String sql3 = "delete  from address where sp_id=?";
        String sql4 = "delete  from experience_detail where sp_id=?";
        try
        {
            s1=c.prepareStatement(sql1);
            s1.setString(1, id);
            s2=c.prepareStatement(sql2);
            s2.setString(1, id);
            s3=c.prepareStatement(sql3);
            s3.setString(1, id);
            s4=c.prepareStatement(sql4);
            s4.setString(1, id);
            int u1 = s4.executeUpdate();
            int u2 = s3.executeUpdate();
            int u3 = s2.executeUpdate();
            int u4 = s1.executeUpdate();
            s1.close();
            s2.close();
            s3.close();
            s4.close();
            return "Deleted";
        }
        catch(Exception e)
        {
            System.out.println("Error At Security Delete "  );
            e.printStackTrace();
            return e.getMessage();
        }
    }
    public String deleteBank(String id)
    {
        PreparedStatement s1;
        String sql1 = "delete  from bank_details where sp_id=?";
        try
        {
            s1=c.prepareStatement(sql1);
            s1.setString(1, id);
            int u = s1.executeUpdate();
            s1.close();
            return "Deleted";
            
        }
        catch(Exception e)
        {
            System.out.println("Error At Delete Bank");
            e.printStackTrace();
            return "Error";
        }
    }
    public String deleteExperience(String id)
    {
        PreparedStatement s1;
        String sql1 = "delete  from experience_detail where sp_id=?";
        try
        {
            s1=c.prepareStatement(sql1);
            s1.setString(1, id);
            int u = s1.executeUpdate();
            s1.close();
            return "Deleted";
        }
        catch(Exception e)
        {
            System.out.println("Error At Delete Experience");
            e.printStackTrace();
            return "Error";
        }
    }
    public String deleteResAdd(String id)
    {
        PreparedStatement s1;
        String sql1 = "delete  from address where sp_id=? and add_type='Residance'";
        try
        {
            s1=c.prepareStatement(sql1);
            s1.setString(1, id);
            int u = s1.executeUpdate();
            s1.close();
            return "Deleted";
        }
        catch(Exception e)
        {
            System.out.println("Error Ar Delete Res");
            e.printStackTrace();
            return "Error";
        }
    }
    public String deletePerAdd(String id)
    {
        PreparedStatement s1;
        String sql1 = "delete  from address where sp_id=? and add_type='Permanent'";
        try
        {
            s1=c.prepareStatement(sql1);
            s1.setString(1, id);
            int u = s1.executeUpdate();
            s1.close();
            return "Deleted";
        }
        catch(Exception e)
        {
            System.out.println("Error At Delete Per");
            e.printStackTrace();
            return "Error";
        }
    }
    public JsonArray getSecurityJson()
    {
        JsonArray ja = new JsonArray();
        PreparedStatement ps;
        String sql="select * from security_person";
        try
        {
            ps=c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
            while(rs.next())
            {
                Map nestedmap = new HashMap();
                nestedmap.put("id",rs.getString("sp_id"));
                
                nestedmap.put("fname",rs.getString("father_name"));
                nestedmap.put("dob",rs.getString("DOB"));
                nestedmap.put("nationality",rs.getString("nationality"));
                nestedmap.put("qualification",rs.getString("qualification"));
                nestedmap.put("stype",rs.getString("s_type"));
                nestedmap.put("status",rs.getString("status"));
                nestedmap.put("pstation",rs.getString("nearest_police_station"));
                nestedmap.put("rstation",rs.getString("nearest_railway_station"));
                nestedmap.put("iname",rs.getString("imagename"));
                nestedmap.put("image",rs.getBinaryStream("image"));
                ja.add(nestedmap);
            }
        ps.close();
        rs.close();
        }
        
        
        catch(Exception e)
        {
            System.out.println("Error At viewAllSecurityPerson "+e.getMessage());
            e.printStackTrace();
        }
        return ja;
    }
    public String updateSecurityPerson(SecurityPerson sp)
    {
       PreparedStatement ps;
       String sql = "update security_person set father_name=?,DOB=?,nationality=?,qualification=?,s_type=?,status=?,other=?,nearest_police_station=?,nearest_railway_station=?,imagename=?,image=? where sp_id=?";
       try
       {
           ps = c.prepareStatement(sql);
           ps.setString(1, sp.getFathername());
           ps.setString(2, sp.getDOB());
           ps.setString(3, sp.getNationality());
           ps.setString(4, sp.getQualification());
           ps.setString(5, sp.getS_type());
           ps.setString(6, sp.getStatus());
           ps.setString(7, sp.getOther());
           ps.setString(8, sp.getPstation());
           ps.setString(9, sp.getNstation());
           ps.setString(10, sp.getIname());
           ps.setBlob(11, sp.getImage());
           ps.setString(12, sp.getUserid());
           int u = ps.executeUpdate();
           ps.close();
           return "success " + u + " Security";
       }
       catch(Exception ex)
       {
           return ex.getMessage();
       }
    }
    public String updateSecurityPersonwi(SecurityPerson sp)
    {
       PreparedStatement ps;
       String sql = "update security_person set father_name=?,DOB=?,nationality=?,qualification=?,s_type=?,status=?,other=?,nearest_police_station=?,nearest_railway_station=? where sp_id=?";
       try
       {
           ps = c.prepareStatement(sql);
           ps.setString(1, sp.getFathername());
           ps.setString(2, sp.getDOB());
           ps.setString(3, sp.getNationality());
           ps.setString(4, sp.getQualification());
           ps.setString(5, sp.getS_type());
           ps.setString(6, sp.getStatus());
           ps.setString(7, sp.getOther());
           ps.setString(8, sp.getPstation());
           ps.setString(9, sp.getNstation());
           ps.setString(10, sp.getUserid());
           int u = ps.executeUpdate();
           ps.close();
           return "success " + u + " Security";
       }
       catch(Exception ex)
       {
           return ex.getMessage();
       }
    }
    public String updateBank(BankDetails bd)
    {
        PreparedStatement pst;
        
        String sql = "update bank_details set ifsc_code=?, account_no=?, bank_name=?, bank_branch=? , bank_city=? where sp_id=?";
        try
        {
            pst = c.prepareStatement(sql);
            pst.setString(1, bd.getIfsc());
            pst.setString(2, bd.getAccount());
            pst.setString(3, bd.getName());
            pst.setString(4, bd.getBranch());
            pst.setString(5, bd.getCity());
            pst.setString(6, bd.getId());
            int u = pst.executeUpdate();
            return "Success";
        }
        catch(Exception e)
        {
            return e.getMessage();
        }
    }
    public String updateAdd(Address ad)
    {
        PreparedStatement pst;
        String sql = "update address set address_line_1=?,address_line_2=?,area=?,district=?,pincode=? where sp_id=? and add_type=?";
        try
        {
            pst = c.prepareStatement(sql);
            pst.setString(1, ad.getAdd1());
            pst.setString(2, ad.getAdd2());
            pst.setString(3, ad.getArea());
            pst.setString(4, ad.getDistrict());
            pst.setString(5, ad.getPincode());
            pst.setString(6, ad.getId());
            pst.setString(7, ad.getAdd_type());
            int u = pst.executeUpdate();
            pst.close();
            return "Success";
        }
        catch(Exception e)
        {
            return e.getMessage();
        }
    }
    public String updateExp(Experience e)
    {
        PreparedStatement ps;
        String sql="update experience_detail set no_of_years=?,O_name=?,address=? where sp_id=?";
        try
        {
            ps=c.prepareStatement(sql);
            ps.setString(1, e.getYears());
            ps.setString(2, e.getName());
            ps.setString(3, e.getAdd());
            ps.setString(4, e.getId());
            int u = ps.executeUpdate();
            ps.close();
            return "Success";
        }
        catch(Exception ex)
        {
            return ex.getMessage();
        }
    }
     public String updateQuotation(ClientQuotation cq)
    {
        PreparedStatement ps;
        String sql = "update client_quotation set amount = ? where quotation_id = ? and s_type = ?";
        try
        {
            ps = c.prepareStatement(sql);
            ps.setDouble(1, cq.getRate());
            ps.setString(2, cq.getQid());
            ps.setString(3, cq.getStype());
            int u = ps.executeUpdate();
            ps.close();
            return "success";
        }
       catch(Exception e)
       {
           System.out.println("Error at update quotation ");
           e.printStackTrace();
           return "error";
       }
    }
     public String updateGuestQuotation(ClientQuotation cq)
    {
        PreparedStatement ps;
        String sql = "update guest_quotation set amount = ? where quotation_id = ? and s_type = ?";
        try
        {
            ps = c.prepareStatement(sql);
            ps.setDouble(1, cq.getRate());
            ps.setString(2, cq.getQid());
            ps.setString(3, cq.getStype());
            int u = ps.executeUpdate();
            ps.close();
            return "success";
        }
       catch(Exception e)
       {
           System.out.println("Error at update quotation ");
           e.printStackTrace();
           return "error";
       }
    }
    public String updateUser(User u)
    {
        PreparedStatement ps;
        String sql = "update users set firstname=?,middlename=?,lastname=?,contact_number=?,email=? where userid=?";
        try
        {
         ps=c.prepareStatement(sql);
         ps.setString(1, u.getFname());
         ps.setString(2, u.getMname());
         ps.setString(3, u.getLname());
         ps.setString(4, u.getNumber());
         ps.setString(5, u.getEmail());
         ps.setString(6, u.getUserid());
         int up = ps.executeUpdate();
            System.out.println("User" + up);
         ps.close();
         return "Success";
        }
        catch(Exception ex)
        {
            return ex.getMessage();
        }
    }
    public JsonArray getClientJson(String sql)
    {
        Statement s,s1;
        
        ResultSet rs,rs1;
        JsonArray ja = new JsonArray();
        
        try
        {
            s=c.createStatement();
            s1=c.createStatement();
            rs=s.executeQuery(sql);

            while(rs.next())
            {
                Map nestedmap = new HashMap();
                String id = rs.getString("client_id");
                nestedmap.put("id", rs.getString("client_id"));
                nestedmap.put("id1", rs.getString("client_id"));
                nestedmap.put("id2", rs.getString("client_id"));
                String orgname=rs.getString("organisation_name");
                String url=rs.getString("url");
                String gst = rs.getString("gst");
                nestedmap.put("name", orgname);
                nestedmap.put("name2", orgname);
                nestedmap.put("url", url);
                nestedmap.put("gst", gst);
                rs1=s1.executeQuery("select * from users where userid='"+id+"'");
                while(rs1.next())
                        {
                            nestedmap.put("pname", rs1.getString("firstname")+" "+rs1.getString("middlename"));
                            nestedmap.put("no", rs1.getString("contact_number"));
                        }
                ja.add(nestedmap);
                                
            }
            
           s.close();
           rs.close();
        } 
        catch(Exception e)
        {
            
        } 
        return ja;
    }
    public JsonArray getAssignJson()
    {
        Statement s;
        
        ResultSet rs;
        JsonArray ja = new JsonArray();
        
        try
        {
            s=c.createStatement();
            rs=s.executeQuery("SELECT users.userid, users.firstname, users.middlename,users.lastname,users.email,users.contact_number,security_person.s_type,security_person.post FROM users INNER JOIN security_person ON users.userid=security_person.sp_id");

            while(rs.next())
            {
                Map nestedmap = new HashMap();
                nestedmap.put("id", rs.getString("userid"));
                nestedmap.put("fname", rs.getString("firstname"));
                nestedmap.put("mname", rs.getString("middlename"));
                nestedmap.put("lname", rs.getString("lastname"));
                nestedmap.put("email", rs.getString("email"));
                nestedmap.put("no", rs.getString("contact_number"));
                nestedmap.put("type", rs.getString("s_type"));
                nestedmap.put("post", rs.getString("post"));
                
                
                ja.add(nestedmap);
                                
            }
            
           s.close();
           rs.close();
        } 
        catch(Exception e)
        {
            
        } 
        return ja;
    }
    public JsonArray getRequireJson1(String id,String sid,String m)
    {
        Statement s,s1,s2;
        
        ResultSet rs,rs1,rs2;
        JsonArray ja = new JsonArray();
        String mon = m;
        String mo = null;
        try
        {
            s1=c.createStatement();
            s=c.createStatement();
            s2=c.createStatement();
            rs1=s1.executeQuery("select quotation_id from client_quotation where client_id='"+id+"'");
            String qid = null;
            int no;
            while(rs1.next())
            {
                qid=rs1.getString("quotation_id");
            }
            rs2=s2.executeQuery("select count(DISTINCT(sp_id)) as sum from client_site_assignment where site_id='"+sid+"' and month(Date)="+mon);
            System.out.println("select count(DISTINCT(sp_id)) as sum from client_site_assignment where site_id='"+sid+"' and month(Date)="+mon);
            while(rs2.next())
            {
                mo = rs2.getString("sum");
                System.out.println(mo+ "mo");
            }
                    
            rs=s.executeQuery("select * from client_quotation where client_id='"+id+"' and quotation_id='"+qid+"'");
            while(rs.next())
            {
                Map nestedmap = new HashMap();
                nestedmap.put("type", rs.getString("s_type"));
                nestedmap.put("post", rs.getString("s_post"));
                //nestedmap.put("no", rs.getString("no_of_security_persons"));
                int mm = Integer.parseInt(rs.getString("no_of_security_persons"))-Integer.parseInt(mo);
                System.out.println(mm+" MM");
                if(mm<0)
                {
                    mm=0;
                }
                nestedmap.put("no", mm);
                nestedmap.put("client", rs.getString("client_id"));
                
                
                ja.add(nestedmap);
                                
            }
            
           s.close();
           rs.close();
        } 
        catch(Exception e)
        {
            System.out.println(e +"at catch");
        } 
        return ja;
    }
    public JsonArray getRequireJson(String id,String year,String month)
    {
        Statement s,s1,ss,s2;
        String cid="";
        ResultSet rs3,rs,rs1 = null,rs2;
        JsonArray ja = new JsonArray();
        BillOperation bo = new BillOperation(c);
        double wage = bo.getAmount(id, month, year);
        int day = bo.getFinal(id, month, year);
        
        try
        {
            String type = null,post = null,no = null,client = null;
            ss=c.createStatement();
            s1=c.createStatement();
            s2=c.createStatement();
            s=c.createStatement();
            rs2=ss.executeQuery("select * from client_quotation where client_id='"+id+"'");
            while(rs2.next())
            {
                cid=rs2.getString("quotation_id");
            }
            int no1=0;
            rs3=s2.executeQuery("select count(DISTINCT(sp_id)) as sum from client_site_assignment where site_id in (select site_id from client_site_address where client_id='"+id+"') and month(Date)="+month);
            while(rs3.next())
            {
                no1=no1+rs3.getInt("sum");
                        
            }
            
            rs=s.executeQuery("select * from client_quotation where quotation_id='"+cid+"'");
            
            //System.out.println("select sum(attendance_value) as sum from attendance_main where client_id='"+id+"'");
            while(rs.next())
            {
                type=rs.getString("s_type");
                post=rs.getString("s_post");
                no=rs.getString("no_of_security_persons");
                client=rs.getString("client_id");
                Map nestedmap = new HashMap();
                nestedmap.put("type", type);
                nestedmap.put("post", post);
                nestedmap.put("no", no1);
                nestedmap.put("client", client);
                nestedmap.put("amt",wage+"");
                
                
                rs1=s1.executeQuery("select (sum(attendance_value)/8) as sum from attendance_main where client_id='"+id+"' and month(attendance_date)='"+month+"'");
                while(rs1.next())
                {
                    nestedmap.put("day", day+"");
                }
                
                ja.add(nestedmap);
                                
            }
           s1.close();
           ss.close();
           rs1.close();
           rs2.close();
           s.close();
           rs.close();
        } 
        catch(Exception e)
        {
            System.out.println("Eror "+e.getMessage());
        } 
        return ja;
    }
    public JsonArray getSiteJson(String id)
    {
        Statement s;
        
        ResultSet rs;
        JsonArray ja = new JsonArray();
        
        try
        {
            s=c.createStatement();
            rs=s.executeQuery("select * from client_site_address where client_id='"+id+"'");

            while(rs.next())
            {
                Map nestedmap = new HashMap();
                nestedmap.put("cid", rs.getString("client_id"));
                nestedmap.put("sid", rs.getString("site_id"));
                nestedmap.put("address", rs.getString("address"));
                nestedmap.put("no", rs.getString("contact_no"));
                
                ja.add(nestedmap);
                                
            }
            
           s.close();
           rs.close();
        } 
        catch(Exception e)
        {
            
        } 
        return ja;
    }
    public String deleteClient(String id)
    {
        String msg;
        PreparedStatement ps;
        String sql = "delete from client where client_id='"+id+"'";
        try
        {
            ps=c.prepareStatement(sql);
            deleteSite(id);
            int u  = ps.executeUpdate();
            deleteUser(id);
            msg = "success";
            ps.close();
        }
        catch(Exception e)
        {
            msg = "Error at delete client " + e.getMessage();
        }
        return msg;
    }
    public String deleteSite(String id)
    {
        String msg;
        PreparedStatement ps;
        String sql = "delete from client_site_address where site_id='"+id+"'";
        try
        {
            ps = c.prepareStatement(sql);
            int u = ps.executeUpdate();
            System.out.println("Query " + sql);
            ps.close();
            msg = "Success";
        }
        catch(Exception e)
        {
            msg = "error at delete site " + e.getMessage();
            System.out.println(msg);
        }
        return msg;
    }
    public String deleteUser(String id)
    {
        String msg;
        String sql = "delete from users where userid = '"+id+"'";
        PreparedStatement ps;
        try
        {
            ps = c.prepareStatement(sql);
            int u = ps.executeUpdate();
            ps.close();
            msg = "Success";
        }
        catch(Exception e)
        {
            msg = "error at delete user" + e.getMessage();
        }
        return msg;
    }
    public JsonArray getAttClient1(String cid,String month,String year)
    {
        JsonArray ja = new JsonArray();
        Statement ps,ps1 = null;

        
        String sql="select * from users where userid in (select sp_id from security_person where sp_id in (select sp_id from client_site_assignment where sp_id in (select sp_id from client_site_address where sp_id='"+cid+"')))";
        try
        {
            System.out.println(sql);
            ps=c.createStatement();
            ResultSet rs = ps.executeQuery(sql);
            while(rs.next())
            {
                JSONObject nestedmap = new JSONObject();
                nestedmap.put("id",rs.getString("userid"));
                nestedmap.put("fname",rs.getString("firstname"));
                nestedmap.put("mname",rs.getString("middlename"));
                nestedmap.put("lname",rs.getString("lastname"));
                String id = rs.getString("userid");
                ps1=c.createStatement();
                ResultSet rs1 = ps1.executeQuery("SELECT attendance_value as val,day(attendance_date) as day from attendance_main where month(attendance_date)='"+month+"' and year(attendance_date)='"+year+"'  and sp_id='"+id+"'");
                JSONObject ob = new JSONObject();
                while(rs1.next())
                {
                    
                    ob.put(rs1.getString("day"),rs1.getString("val"));
                }
                nestedmap.put("attendance", ob);
                ja.add(nestedmap);
                System.out.println("nestedmap "+nestedmap);
            }
        ps.close();
        rs.close();
        
        ps1.close();
        }
        
        
        catch(Exception e)
        {
            System.out.println("Error At viewAllSecurityPerson "+e.getMessage());
            e.printStackTrace();
        }
        return ja;
    }
    public JsonArray getSecurityClient1(String cid,String site,String month,String year)
    {
        JsonArray ja = new JsonArray();
        Statement ps,ps1 = null;

        
        String sql="select * from users where userid in (select sp_id from security_person where sp_id in (select sp_id from client_site_assignment where site_id in (select site_id from client_site_address where site_id='"+cid+"')))";
        try
        {
            System.out.println(sql);
            ps=c.createStatement();
            ResultSet rs = ps.executeQuery(sql);
            while(rs.next())
            {
                JSONObject nestedmap = new JSONObject();
                nestedmap.put("id",rs.getString("userid"));
                nestedmap.put("fname",rs.getString("firstname"));
                nestedmap.put("mname",rs.getString("middlename"));
                nestedmap.put("lname",rs.getString("lastname"));
                String id = rs.getString("userid");
                ps1=c.createStatement();
                ResultSet rs1 = ps1.executeQuery("SELECT attendance_value as val,day(attendance_date) as day from attendance_main where month(attendance_date)='"+month+"' and year(attendance_date)='"+year+"' and site_id='"+site+"' and sp_id='"+id+"'");
                JSONObject ob = new JSONObject();
                while(rs1.next())
                {
                    
                    ob.put(rs1.getString("day"),rs1.getString("val"));
                }
                nestedmap.put("attendance", ob);
                ja.add(nestedmap);
                System.out.println("nestedmap "+nestedmap);
            }
        ps.close();
        rs.close();
        
        ps1.close();
        }
        
        
        catch(Exception e)
        {
            System.out.println("Error At viewAllSecurityPerson "+e.getMessage());
            e.printStackTrace();
        }
        return ja;
    }
    public JsonArray getSecurityClient(String cid)
    {
        JsonArray ja = new JsonArray();
        PreparedStatement ps;
        String sql="select * from users where userid in (select sp_id from security_person where sp_id in (select sp_id from client_site_assignment where site_id in (select site_id from client_site_address where site_id='"+cid+"')))";
        try
        {
            ps=c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
            while(rs.next())
            {
                Map nestedmap = new HashMap();
                nestedmap.put("id",rs.getString("userid"));
                
                nestedmap.put("fname",rs.getString("firstname"));
                nestedmap.put("mname",rs.getString("middlename"));
                nestedmap.put("lname",rs.getString("lastname"));
                
                ja.add(nestedmap);
            }
        ps.close();
        rs.close();
        }
        
        
        catch(Exception e)
        {
            System.out.println("Error At viewAllSecurityPerson "+e.getMessage());
            e.printStackTrace();
        }
        return ja;
    }
    public String setClient()
    {
        Statement st;
        ResultSet rs;
        String msg;
        try{
            st=c.createStatement();
            rs=st.executeQuery("select * from client");
            JSONArray js = new JSONArray();
            int i=0;
            while(rs.next())
                        {
                           
                            
                            
                            String id = rs.getString("client_id");
                            String name= rs.getString("organisation_name");
                            JSONObject ja = new JSONObject();
                            ja.put("cid",id);
                            ja.put("cname", name);
                            js.add(ja);
                            i++;
                            
                        }
            msg = JSONValue.toJSONString(js);
            st.close();
            rs.close();
        }
        catch(Exception e)
        {
            msg = e.getMessage();
        }
        return msg;
    }
    public String setGuest()
    {
        Statement st;
        ResultSet rs;
        String msg;
        try{
            st=c.createStatement();
            rs=st.executeQuery("select * from guest");
            JSONArray js = new JSONArray();
            int i=0;
            while(rs.next())
                        {
                           
                            
                            
                            String id = rs.getString("guest_id");
                            String name= rs.getString("organisation_name");
                            JSONObject ja = new JSONObject();
                            ja.put("cid",id);
                            ja.put("cname", name);
                            js.add(ja);
                            i++;
                            
                        }
            msg = JSONValue.toJSONString(js);
            st.close();
            rs.close();
        }
        catch(Exception e)
        {
            msg = e.getMessage();
        }
        return msg;
    }
    public String setSecurity()
    {
        Statement st;
        ResultSet rs;
        String msg;
        try{
            st=c.createStatement();
            rs=st.executeQuery("select * from users where userid in (select sp_id from security_person) order by firstname asc");
            JSONArray js = new JSONArray();
            int i=0;
            while(rs.next())
                        {
                           
                            
                            
                            String id = rs.getString("userid");
                            String name= rs.getString("firstname")+" "+rs.getString("middlename")+" "+rs.getString("lastname");
                            JSONObject ja = new JSONObject();
                            ja.put("sid",id);
                            ja.put("sname", name);
                            js.add(ja);
                            i++;
                            
                        }
            msg = JSONValue.toJSONString(js);
            st.close();
            rs.close();
        }
        catch(Exception e)
        {
            msg = e.getMessage();
        }
        return msg;
    }
    public String setSite(String cid)
    {
        Statement st;
        ResultSet rs;
        String msg;
        try{
            st=c.createStatement();
            rs=st.executeQuery("select * from client_site_address where client_id='"+cid+"'");
            JSONArray js = new JSONArray();
            int i=0;
            while(rs.next())
                        {
                           
                            
                            
                            String id = rs.getString("site_id");
                            String name= rs.getString("area");
                            JSONObject ja = new JSONObject();
                            ja.put("cid",id);
                            ja.put("cname", name);
                            js.add(ja);
                            i++;
                            
                        }
            msg = JSONValue.toJSONString(js);
            st.close();
            rs.close();
        }
        catch(Exception e)
        {
            msg = e.getMessage();
        }
        return msg;
    }
   public JsonArray getUserSecurityJson(String month,String year)
    {
        Statement s,s1,s2,s3;
        
        ResultSet rs,rs1,rs2,rs3;
        JsonArray ja = new JsonArray();
        
        try
        {
            s=c.createStatement();
             s2=c.createStatement();
             s3=c.createStatement();
            s1=c.createStatement();
            rs=s.executeQuery("select * from users where userid in (select sp_id from security_person)");

            while(rs.next())
            {
                Map nestedmap = new HashMap();
                
                String id =  rs.getString("userid");
                System.out.println(id);
                nestedmap.put("id", rs.getString("userid"));
                nestedmap.put("fname", rs.getString("firstname"));
                nestedmap.put("mname", rs.getString("middlename"));
                nestedmap.put("lname", rs.getString("lastname"));
                nestedmap.put("email", rs.getString("email"));
               
//                rs1=s1.executeQuery("SELECT SUM(attendance_main.attendance_value)/8 as sum, (advance_payment.amount) as total FROM attendance_main LEFT JOIN advance_payment ON attendance_main.sp_id=advance_payment.sp_id  where attendance_main.sp_id='"+id+"' ");
//                while(rs1.next())
//                {
//                   
//                    nestedmap.put("days", rs1.getInt("sum")+"");
//                    int sum = rs1.getInt("sum")*250;
//                    int ap = rs1.getInt("total");
//                    int total = sum-ap;
//                    nestedmap.put("sum",total);
//                    nestedmap.put("ap",rs1.getInt("total"));
//                }
double total=0;
int days=0;
                
                rs1=s1.executeQuery("select DISTINCT(site_id) from attendance_main where year(attendance_date)='"+year+"' and month(attendance_date)='"+month+"' and sp_id='"+id+"'");
                 
                int i=0;
                while(rs1.next())
                {
                    System.out.println("count i " + i);
                    String site = rs1.getString("site_id");
                    System.out.println("Site = "+site);
                    System.out.println("month" + month);
                    
                    ArrayList<SalaryOperation> so = getAttendanceDetails(id, site, month, year);
                    
                    
                    System.out.println("length " + so.size());
                    if(so.size()!=0)
                    {
                    SalaryOperation si = so.get(0);
                    days = days + (si.getValue()/8);
                    double t = Double.parseDouble(si.getWage());
                    total = total+((si.getValue()/8)*t);
                    }
                    
                    i++;
                }
                nestedmap.put("days", days);
                rs2=s2.executeQuery("select sum(amount) as sum from advance_payment where sp_id='"+id+"' and year(ap_date)='"+year+"' and month(ap_date)='"+month+"'");
                while(rs2.next())
                {
                    
                    int sum = rs2.getInt("sum");
                    System.out.println("sum " + sum);
                    System.out.println("total " + total);
                    String r = String.format("%.2f", total-sum);
                    nestedmap.put("sum",r);
                }
                if(days>0)
               ja.add(nestedmap);
                
                rs1.close();
                
            }
           
           s.close();
           rs.close();
        } 
        catch(Exception e)
        {
            System.out.println("Error Getting "+e.getMessage());
            e.printStackTrace();
        } 
        return ja;
    }
   public JsonArray getUserSecurityJson(String month,String year,String sid)
    {
        Statement s,s1,s2,s3;
        
        ResultSet rs,rs1,rs2,rs3;
        JsonArray ja = new JsonArray();
        
        try
        {
            s=c.createStatement();
             s2=c.createStatement();
             s3=c.createStatement();
            s1=c.createStatement();
            rs=s.executeQuery("select * from users where userid ='"+sid+"'");

            while(rs.next())
            {
                JSONObject nestedmap = new JSONObject();
                
                String id =  rs.getString("userid");
                System.out.println(id);
                String name = rs.getString("firstname")+" "+rs.getString("lastname");
                nestedmap.put("name", name);
                
               
//                rs1=s1.executeQuery("SELECT SUM(attendance_main.attendance_value)/8 as sum, (advance_payment.amount) as total FROM attendance_main LEFT JOIN advance_payment ON attendance_main.sp_id=advance_payment.sp_id  where attendance_main.sp_id='"+id+"' ");
//                while(rs1.next())
//                {
//                   
//                    nestedmap.put("days", rs1.getInt("sum")+"");
//                    int sum = rs1.getInt("sum")*250;
//                    int ap = rs1.getInt("total");
//                    int total = sum-ap;
//                    nestedmap.put("sum",total);
//                    nestedmap.put("ap",rs1.getInt("total"));
//                }
double total=0;
double days=0;
                
                rs1=s1.executeQuery("select DISTINCT(site_id) from attendance_main where year(attendance_date)='"+year+"' and month(attendance_date)='"+month+"' and sp_id='"+id+"'");
                 
                int i=0;
                while(rs1.next())
                {
                    System.out.println("count i " + i);
                    String site = rs1.getString("site_id");
                    System.out.println("Site = "+site);
                    System.out.println("month" + month);
                    
                    ArrayList<SalaryOperation> so = getAttendanceDetails(id, site, month, year);
                    
                    
                    System.out.println("length " + so.size());
                    if(so.size()!=0)
                    {
                    SalaryOperation si = so.get(0);
                    days = days + (si.getValue()/8);
                    double t = Double.parseDouble(si.getWage());
                    total = total+((si.getValue()/8)*t);
                    }
                    i++;
                }
                nestedmap.put("days", days);
                rs2=s2.executeQuery("select sum(amount) as sum from advance_payment where sp_id='"+id+"' and year(ap_date)='"+year+"' and month(ap_date)='"+month+"'");
                while(rs2.next())
                {
                    
                    int sum = rs2.getInt("sum");
                    System.out.println("sum " + sum);
                    System.out.println("total " + total);
                    String r = String.format("%.2f", total);
                    nestedmap.put("app",sum);
                    nestedmap.put("sum",r);
                }
               ja.add(nestedmap);
                rs1.close();
                
            }
           
           s.close();
           rs.close();
        } 
        catch(Exception e)
        {
            System.out.println("Error Getting "+e.getMessage());
            e.printStackTrace();
        } 
        return ja;
    }
   public ArrayList<SalaryOperation> getAttendanceDetails(String sp_id,String site_id,String month,String year)
    {
        ArrayList<SalaryOperation> so = new ArrayList();
        String sql1="select Wage from client_site_assignment where  site_id='"+site_id+"' and sp_id='"+sp_id+"' and year(Date)='"+year+"' and month(Date)='"+month+"' group by site_id,sp_id";
        String sql="select sp_id,site_id,sum(attendance_value) as sum from attendance_main where  site_id='"+site_id+"' and sp_id='"+sp_id+"' and year(attendance_date)='"+year+"' and month(attendance_date)='"+month+"' group by site_id,sp_id";
        Statement ps,ps1;
        ResultSet rsam,rsca;
        String sp,site,wage;
        int value;
        try
        {
            
            ps=c.createStatement();
            ps1=c.createStatement();
            rsam=ps.executeQuery(sql);
            rsca=ps1.executeQuery(sql1);
            while(rsam.next())
            {
                if(rsca.next())
                {
                sp=rsam.getString("sp_id");
                site=rsam.getString("site_id");
                wage=rsca.getString("Wage");
                value=rsam.getInt("sum");
                    System.out.println("SPID "+sp);
                    System.out.println("Wage "+wage);
                SalaryOperation s = new SalaryOperation(sp, site, wage, value);
                so.add(s);
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("Error At Get Salary Details "+e.getLocalizedMessage());
            e.printStackTrace();
        }
        return so;
    }
   public JsonArray getClientQuotation(String id)
    {
        Statement s;
        
        ResultSet rs;
        JsonArray ja = new JsonArray();
        
        try
        {
            s=c.createStatement();
            rs=s.executeQuery("select * from client_quotation where client_id='"+id+"' and amount=''");

            while(rs.next())
            {
                Map nestedmap = new HashMap();
                nestedmap.put("type", rs.getString("s_type"));
                nestedmap.put("post", rs.getString("s_post"));
                nestedmap.put("no", rs.getString("no_of_security_persons"));
                nestedmap.put("client", rs.getString("client_id"));
                nestedmap.put("qtid",rs.getString("quotation_id"));
                
                ja.add(nestedmap);
                                
            }
            
           s.close();
           rs.close();
        } 
        catch(Exception e)
        {
            
        } 
        return ja;
    }
   public JsonArray getGuestQuotation(String id)
    {
        Statement s;
        
        ResultSet rs;
        JsonArray ja = new JsonArray();
        
        try
        {
            s=c.createStatement();
            rs=s.executeQuery("select * from guest_quotation where guest_id='"+id+"' and amount=''");

            while(rs.next())
            {
                Map nestedmap = new HashMap();
                nestedmap.put("type", rs.getString("s_type"));
                nestedmap.put("post", rs.getString("s_post"));
                nestedmap.put("no", rs.getString("no_of_security_persons"));
                nestedmap.put("guest", rs.getString("guest_id"));
                nestedmap.put("qtid",rs.getString("quotation_id"));
                
                ja.add(nestedmap);
                                
            }
            
           s.close();
           rs.close();
        } 
        catch(Exception e)
        {
            
        } 
        return ja;
    }
  public JsonArray getAssignView(String sid)
  {
      ResultSet rs,rs1,rs2;
      Statement s,s1,s2;
      JsonArray ja = new JsonArray();
      try
      {
          s=c.createStatement();
          s1=c.createStatement();
          s2=c.createStatement();
          rs=s.executeQuery("select * from client_site_assignment where site_id='"+sid+"'");
          while(rs.next())
          {
              JSONObject jo = new JSONObject();
              String spid = rs.getString("sp_id");
              String wage = rs.getString("Wage");
              jo.put("id",spid);
              jo.put("wage", wage);
              rs1=s1.executeQuery("select * from users where userid='"+spid+"'");
              while(rs1.next())
              {
                  String fname = rs1.getString("firstname");
                  String lname = rs1.getString("lastname");
                  String mobile = rs1.getString("contact_number");
                  String name = fname+" "+lname;
                  jo.put("name", name);
                  jo.put("mobile", mobile);
              }
              rs1.close();
              rs2=s2.executeQuery("select * from security_person where sp_id='"+spid+"'");
              while(rs2.next())
              {
                  String type= rs2.getString("s_type");
                  String post = rs2.getString("post");
                  jo.put("type",type);
                  jo.put("post", post);
              }
              rs2.close();
              ja.add(jo);
          }
          rs.close();
          s2.close();
          s1.close();
          s.close();
      }
      catch(Exception e)
      {
          System.out.println("Error At Viewing Json");
          e.printStackTrace();
      }
      return ja;
  }
  public String getClientId(String email)
  {
      String cid=null;
      Statement st;
      ResultSet rs;
      try
      {
          st=c.createStatement();
          rs=st.executeQuery("select client_id from client where client_id in (select userid from users where email='"+email+"')");
            while(rs.next())
            {
                cid=rs.getString("client_id");
            }
      }
      catch(Exception e)
      {
          
      }
      return cid;
  }
  public String insertQuotation(ClientQuotation cq)
  {
      PreparedStatement ps;
      String s=null;
      String sql="insert into client_quotation values(?,?,?,?,?,?,?,?,?)";
      try
      {
          ps=c.prepareStatement(sql);
          ps.setString(1, cq.getQid());
          ps.setString(2, cq.getClid());
          ps.setString(3, cq.getNo());
          ps.setString(4, cq.getSpost());
          ps.setString(5, cq.getStype());
          ps.setDouble(6, cq.getRate());
          ps.setDouble(7, cq.getMrate());
          ps.setString(8, cq.getQdate());
          ps.setString(9, cq.getReq());
          int u = ps.executeUpdate();
          s = "Quotation Added Successfully";
          ps.close();
      }
      catch(Exception e)
      {
          s=e.getLocalizedMessage();
          System.out.println(s);
      }
      return s;
  }
  public JsonArray viewAll(String usersql,String securitysql)
  {
      JsonArray ja = new JsonArray();
      Statement st1,st2;
      ResultSet rs1,rs2;
      try
      {
          st1=c.createStatement();
          st2=c.createStatement();
          rs1=st1.executeQuery(usersql);
          while(rs1.next())
          {
              JSONObject jo = new JSONObject();
              String id = rs1.getString("userid");
              jo.put("id",rs1.getString("userid"));
              jo.put("fname", rs1.getString("firstname"));
              jo.put("mname", rs1.getString("middlename"));
              jo.put("lname", rs1.getString("lastname"));
              jo.put("email", rs1.getString("email"));
              jo.put("number", rs1.getString("contact_number"));
              rs2=st2.executeQuery(securitysql+" and sp_id='"+id+"'");
              while(rs2.next())
              {
                  jo.put("dob", rs2.getString("DOB"));
                  jo.put("nation", rs2.getString("nationality"));
                  jo.put("qualification", rs2.getString("qualification"));
                  jo.put("stype", rs2.getString("s_type"));
                  jo.put("post", rs2.getString("post"));
                  jo.put("status", rs2.getString("status"));
                  jo.put("other", rs2.getString("other"));
                  jo.put("police", rs2.getString("nearest_police_station"));
                  jo.put("railway", rs2.getString("nearest_railway_station"));
                  jo.put("iname", rs2.getString("imagename"));
                  jo.put("name","");
                  jo.put("bank","");
                  jo.put("add1","");
                  jo.put("add2","");
                  jo.put("exp","");
                  jo.put("remove","");
                  jo.put("edit","");
              }
              ja.add(jo);
          }
      }
      catch(Exception e)
      {
          System.out.println(e);
      }
      return ja;
  }
  public void UpdateClient(Client cl)
  {
      PreparedStatement ps;
      try
      {
          ps=c.prepareStatement("update client set organisation_name=?,url=?,gst=? where client_id=?");
          ps.setString(1, cl.getOrgname());
          ps.setString(2, cl.getUrl());
          ps.setString(3, cl.getGst());
          ps.setString(4, cl.getUserid());
          int u = ps.executeUpdate();
      }
      catch(Exception e)
      {
          System.out.println(e);
      }
  }
  public String UpdateQuotation(ClientQuotation cq)
  {
      String msg = "";
      PreparedStatement st;
     try
     {
            
     }
     catch(Exception e)
     {
         System.out.println(e);
     }
      return msg;
  }
}