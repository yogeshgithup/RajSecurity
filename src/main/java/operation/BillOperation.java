
package operation;

import data.BillAllDetails;
import data.BillDetails;
import data.Client;
import data.ClientQuotation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class BillOperation {
Connection c;
    public BillOperation(Connection c)
    {
        this.c=c;
    }
    public void delBillAll()
    {
        PreparedStatement ps,ps1;
        try
        {
            ps=c.prepareStatement("delete from billdetails");
            ps1=c.prepareStatement("delete from billalldetails");
            int u=ps1.executeUpdate();
            int upp=ps.executeUpdate();
            System.out.println("Deleted");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    public void insertBillDetails(BillDetails bd)
    {
        PreparedStatement ps;
        try
        {
            ps=c.prepareStatement("insert into billdetails values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1,bd.getBid());
            ps.setString(2,bd.getCname());
            ps.setString(3,bd.getBill_date());
            ps.setString(4,bd.getAddress());
            ps.setString(5,bd.getTotal());
            ps.setString(6,bd.getCgst());
            ps.setString(7, bd.getSgst());
            ps.setString(8,bd.getScharge());
            ps.setString(9, bd.getGtotal());
            ps.setString(10,bd.getWord());
            ps.setString(11,bd.getPaisa());
            ps.setString(12,bd.getGst());
            ps.setString(13,bd.getGstps()   );
            int u = ps.executeUpdate();
            System.out.println("Inserted BillDetails");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    public String getAddress(String cid)
    {
        String address="";
        ResultSet rs;
        Statement st;
        try
        {
            st=c.createStatement();
            rs=st.executeQuery("select address from client_site_address where client_id='"+cid+"'");
            while(rs.next())
            {
                address=rs.getString("address");
            }
                    
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return address;
    }
    public void insertBillAllDetails(BillAllDetails bd)
    {
       PreparedStatement ps;
       try
       {
           ps=c.prepareStatement("insert into billalldetails values(?,?,?,?,?,?,?,?)");
           ps.setString(1, bd.getSrno());
           ps.setString(2,bd.getParticulars());
           ps.setString(3,bd.getQty());
           ps.setString(4, bd.getRate());
           ps.setString(5,bd.getTotal());
           ps.setString(6, bd.getBid());
           ps.setString(7, bd.getIndexby());
           ps.setString(8, bd.getPs());
           int u = ps.executeUpdate();
           System.out.println("inserted billAllDetails");
       }
       catch(Exception e)
       {
           System.out.println(e);
       }
    }
    public int[] getAmt(String amt)
    {
        int[] am = new int[2];
        String rupees = amt;
        int index = rupees.indexOf(".");
        String rs=rupees;
        String ps1="00";
        String ps=null;
        if(index>=0)
        {
        rs = rupees.substring(0, index);
        ps = rupees.substring(index+1,rupees.length());
        
        if(ps.length()>2)
        {
            ps1=ps.substring(0,2);
        }
        else if(ps==null)
        {
            ps1="00";
        }
        else
        {
            ps1=ps;
        }
        if("".equals(rs))
        {
            rs="0";
        }
        }
        am[0]=Integer.parseInt(rs);
        am[1]=Integer.parseInt(ps1);
        return am;
    }
    public String getGST(String cid)
    {
        String gst = "";
        ResultSet rs;
        Statement st;
        try
        {
            st=c.createStatement();
            rs=st.executeQuery("select gst from client where client_id='"+cid+"'");
            while(rs.next())
            {
                gst = rs.getString("gst");
            }
                    
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return gst;
    }
    public ArrayList<ClientQuotation> getCQ(String id,String m,String year)
    {
        ArrayList<ClientQuotation> cq=null;
        PreparedStatement ps,ps1;
        
        ResultSet rs,rs1;
        try
        {
            cq=new ArrayList<ClientQuotation>();
            ps=c.prepareStatement("select distinct(amount) from client_quotation where client_id='"+id+"' and status=1 and month(date)="+m+" and year(date)="+year);
            rs=ps.executeQuery();
           
            while(rs.next())
            {
               String amt = rs.getString("amount");
                ps1=c.prepareStatement("select * from client_quotation where client_id='"+id+"' and amount='"+amt+"' and month(date)='"+m+"' and year(date)='"+year+"' and status=1");
                rs1=ps1.executeQuery();
                
                if(rs1.next())
                {
                    
                   ClientQuotation ca = new ClientQuotation();
                    ca.setClid(rs1.getString("client_id"));
                    ca.setNo(rs1.getString("no_of_security_persons"));
                    ca.setQid(rs1.getString("quotation_id"));
                    ca.setSpost(rs1.getString("s_post"));
                    ca.setStype(rs1.getString("s_type"));
                    ca.setRate(rs1.getDouble("amount")); 
                    cq.add(ca);

                }
                 
                               
            }
            
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return cq;
    }
    public String getClient(String cid)
    {
        String cl = null;
        Statement st;
        ResultSet rs;
        try
        {
            st=c.createStatement();
            rs=st.executeQuery("select * from client where client_id='"+cid+"'");
            while(rs.next())
            {
                cl=rs.getString("organisation_name");
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return cl;
    }
    public int getAdd(String cid,String month,String year)
    {
        int days=0;
        PreparedStatement ps=null;
        ResultSet rs=null;
        String sql = "select sum((((day(last_day(date))-day(date)))+1)*no_of_security_persons) as day from client_quotation where client_id=? and month(date)=? and year(date)=? and status=1";
        try
        {
            ps=c.prepareStatement(sql);
            ps.setString(1, cid);
            ps.setString(2, month);
            ps.setString(3, year);
            rs=ps.executeQuery();
            while(rs.next())
            {
                days=rs.getInt("day");
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return days;
    }
    public int getSubtraction(String cid,String month,String year)
    {
        int days=0;
        PreparedStatement ps=null;
        ResultSet rs=null;
        String sql = "select sum((((day(last_day(date))-day(date)))+1)*no_of_security_persons) as day from client_quotation where client_id=? and month(date)=? and year(date)=? and status=0 ";
        try
        {
            ps=c.prepareStatement(sql);
            ps.setString(1, cid);
            ps.setString(2, month);
            ps.setString(3, year);
            rs=ps.executeQuery();
            while(rs.next())
            {
                days=rs.getInt("day");
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return days;
    }
    public int getFinal(String cid,String month,String year)
    {
        int days=0;
        try
        {
           days =  getAdd(cid, month, year) - getSubtraction(cid, month, year);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return days;
    }
     public int getAdd1(String cid,String month,String year,String amout)
    {
        int days=0;
        PreparedStatement ps=null;
        ResultSet rs=null;
        String sql = "select sum((((day(last_day(date))-day(date)))+1)*no_of_security_persons) as day from client_quotation where client_id=? and month(date)=? and year(date)=? and status=1 and amount=?";
        try
        {
            ps=c.prepareStatement(sql);
            ps.setString(1, cid);
            ps.setString(2, month);
            ps.setString(3, year);
            ps.setDouble(4, Double.parseDouble(amout));
            rs=ps.executeQuery();
            while(rs.next())
            {
                days=rs.getInt("day");
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return days;
    }
    public int getSubtraction1(String cid,String month,String year,String amout)
    {
        int days=0;
        PreparedStatement ps=null;
        ResultSet rs=null;
        String sql = "select sum((((day(last_day(date))-day(date)))+1)*no_of_security_persons) as day from client_quotation where client_id=? and month(date)=? and year(date)=? and status=0 and amount=?";
        try
        {
            ps=c.prepareStatement(sql);
            ps.setString(1, cid);
            ps.setString(2, month);
            ps.setString(3, year);
            ps.setDouble(4, Double.parseDouble(amout));
            rs=ps.executeQuery();
            while(rs.next())
            {
                days=rs.getInt("day");
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return days;
    }
    public int getFinal1(String cid,String month,String year,String amout)
    {
        int days=0;
        try
        {
           days =  getAdd1(cid, month, year,amout) - getSubtraction1(cid, month, year,amout);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return days;
    }
    public double getAmount(String cid,String month,String year)
    {
        double amt=0;
        PreparedStatement ps=null;
        ResultSet rs=null;
        String sql = "select max(amount) as amt from client_quotation where client_id=? and month(date)=? and year(date)=?";
        try
        {
            ps=c.prepareStatement(sql);
            ps.setString(1, cid);
            ps.setString(2, month);
            ps.setString(3, year);
            rs=ps.executeQuery();
            while(rs.next())
            {
                amt=rs.getDouble("amt");
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return amt;
    }
    public int getBillId()
    {
       PreparedStatement stmt;
       ResultSet rs;
       int  sp_id;
       int max=0;
       String sql="select bill_id as data from bill_details";
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
      return max;
    }
    public int getBillNo()
    {
       PreparedStatement stmt;
       ResultSet rs;
       int  sp_id;
       int max=0;
       String sql="select bill_no as data from bill_details";
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
      return max;
    }
    public void insertBill_Details(int billid,String clientid, int billno, String billdate,String gtotal)
    {
        PreparedStatement ps = null;
        try
        {
            ps=c.prepareStatement("insert into bill_details values(?,?,?,?,?)");
            ps.setInt(1,billid);
            ps.setString(2,clientid);
            ps.setInt(3,billno);
            ps.setString(4,billdate);
            ps.setString(5,gtotal);
            int u = ps.executeUpdate();
            System.out.println("Bill_Details Inserted Successfully");
            ps.close();

        }
        catch(Exception e)
        {
            System.out.println("Error At Bill_Details "+e.getLocalizedMessage());
        }
    }
}
