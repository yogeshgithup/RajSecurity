
package operation;

import data.Quotation;
import data.QuotationDetails;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class QuotationOperation {
    Connection c;
    public QuotationOperation(Connection c)
    {
        this.c=c;
    }
    public void InsertQuotation(Quotation qt)
    {
        PreparedStatement ps;
        ResultSet rs;
        String sql = "insert into quotation values(?,?,?,?,?,?,?)";
        try
        {
            ps=c.prepareStatement(sql);
            ps.setString(1, qt.getClient_id());
            ps.setString(2, qt.getCname());
            ps.setString(3, qt.getOrgname());
            ps.setString(4, qt.getCity());
            ps.setString(5, qt.getRefno());
            ps.setString(6, qt.getQdate());
            ps.setString(7, qt.getScharge());
            int u = ps.executeUpdate();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    public void insertQuotationDetails(QuotationDetails qd)
    {
        PreparedStatement ps;
        ResultSet rs;
        String sql = "insert into quotationdetails values(?,?,?,?,?,?,?)";
        try
        {
            ps=c.prepareStatement(sql);
            ps.setString(1, qd.getQid());
            ps.setString(2, qd.getSrno());
            ps.setString(3, qd.getCid());
            ps.setString(4, qd.getSpost());
            ps.setString(5, qd.getHrs());
            ps.setString(6, qd.getEx());
            ps.setString(7, qd.getCivilian());
            int u = ps.executeUpdate();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
public String getQuotationId()
    {
       PreparedStatement stmt;
       ResultSet rs;
       int  sp_id;
       int max=0;
       String sql="select substr(qid,3,length(qid)) data from quotationdetails";
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
public int getRefNo()
    {
       PreparedStatement stmt;
       ResultSet rs;
       int  sp_id;
       int max=100;
       String sql="select refno as data from quotation";
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
public String getClientId()
    {
       PreparedStatement stmt;
       ResultSet rs;
       int  sp_id;
       int max=0;
       String sql="select substr(client_id,3,length(client_id)) data from quotation";
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
}
