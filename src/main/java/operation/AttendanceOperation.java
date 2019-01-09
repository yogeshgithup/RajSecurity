
package operation;

import data.AdvancePayment;
import data.Assign;
import data.AttendanceMain;
import data.SalaryOperation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JsonArray;

/**
 *
 * @author Yogesh Chawla
 */
public class AttendanceOperation {
    Connection c;
    public AttendanceOperation(Connection c)
    {
        this.c = c;
    }
     public String getAttendanceid()
    {
       PreparedStatement stmt;
       ResultSet rs;
       int  sp_id;
       int max=0;
       String sql="select substr(attendance_id,3,length(attendance_id)) data from attendance_main";
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
      return "AT"+max;
    }
    public String addAttendance(AttendanceMain am)
    {
        PreparedStatement ps;
        String sql = "insert into attendance_main values(?,?,?,?,?,?)";
        String msg;
        try
        {
            ps=c.prepareStatement(sql);
            c.setAutoCommit(false);
            ps.setString(1, am.getAid());
            ps.setString(2, am.getId());
            ps.setString(3, am.getDate());
            ps.setString(4, am.getValue());
            ps.setString(5, am.getClient());
            ps.setString(6, am.getSite());
            int u = ps.executeUpdate();
            c.commit();
            ps.close();
            msg="Attendance Marked Succesfully";
        }
        catch(Exception e)
        {
            msg="Error At Attendance ";
            System.out.println("Error At Attendance "+e.getLocalizedMessage());
        }
        return msg;
    }
    public String UpdateAttendance(AttendanceMain am)
    {
        PreparedStatement ps;
        String sql = "update attendance_main set attendance_value=? where attendance_date=? and sp_id=?";
        String msg;
        try
        {
            ps=c.prepareStatement(sql);
            c.setAutoCommit(false);
            ps.setString(1, am.getValue());
            ps.setString(2, am.getDate());
            ps.setString(3, am.getId());
            int u = ps.executeUpdate();
            c.commit();
            ps.close();
            msg="Attendance Updated Succesfully";
        }
        catch(Exception e)
        {
            msg="Error At Attendance "+e.getMessage();
        }
        return msg;
    }
    public String addAdvancePayement(AdvancePayment ap)
    {
        PreparedStatement ps;
        String sql = "insert into advance_payment values(?,?,?)";
        String msg;
        try
        {
            ps=c.prepareStatement(sql);
            c.setAutoCommit(false);
            ps.setString(1, ap.getId());
            ps.setString(2, ap.getDate());
            ps.setString(3, ap.getAmt());
           
            
            int u = ps.executeUpdate();
            c.commit();
            ps.close();
            msg="success";
        }
        catch(Exception e)
        {
            msg="Error At Advance Payment"+e.getMessage();
            System.out.println(msg);
        }
        return msg;
    }
    public String addAssignment(Assign ap)
    {
        PreparedStatement ps;
        String sql = "insert into client_site_assignment values(?,?,?,?,?)";
        String msg;
        try
        {
            ps=c.prepareStatement(sql);
            c.setAutoCommit(false);
            ps.setString(1, ap.getSiteid());
            ps.setString(2, ap.getSpid());
            ps.setString(3, ap.getDate());
            ps.setDouble(4, ap.getDwage());
            ps.setString(5, ap.getWage());
            
            int u = ps.executeUpdate();
            c.commit();
            ps.close();
            msg="success";
        }
        catch(Exception e)
        {
            msg="Error At Assignment "+e.getMessage();
        }
        return msg;
    }
    public ArrayList<SalaryOperation> getAttendanceDetails(String sp_id,String site_id,String month,String year)
    {
        ArrayList<SalaryOperation> so = new ArrayList();
        String sql1="select Wage from client_site_assignment where  site_id=? and sp_id=? and year(Date)=? and month(Date)=? group by (site_id,sp_id)";
        String sql="select sp_id,site_id,sum(attendance_value) as sum from attendance_main where  site_id=? and sp_id=? and year(attendance_date)=? and month(attendance_date)=? group by (site_id,sp_id)";
        PreparedStatement ps,ps1;
        ResultSet rsam,rsca;
        String sp,site,wage;
        int value;
        
        try
        {
            
            ps=c.prepareStatement(sql);
            ps.setString(1, site_id);
            ps.setString(2, sp_id);
            ps.setString(3, year);
            ps.setString(4, month);
            ps1=c.prepareStatement(sql1);
            ps1.setString(1, site_id);
            ps1.setString(2, sp_id);
            ps1.setString(3, year);
            ps1.setString(4, month);
            rsam=ps.executeQuery();
            rsca=ps1.executeQuery();
            while(rsam.next())
            {
                rsca.next();
                sp=rsam.getString("sp_id");
                site=rsam.getString("site_id");
                wage=rsca.getString("Wage");
                value=rsam.getInt("sum");
                SalaryOperation s = new SalaryOperation(sp, site, wage, value);
                so.add(s);
            }
        }
        catch(Exception e)
        {
            System.out.println("Error At Get Salary Details "+e.getLocalizedMessage());
            e.printStackTrace();
        }
        return so;
    }
    public JsonArray getDetails(String id,String month,String year)
    {
        JsonArray jo = new JsonArray();
        ResultSet rs,rs1,rs2;
        Statement st;
                try{
                    st=c.createStatement();
                    rs=st.executeQuery("select * from users where userid='"+id+"'");
                    while(rs.next())
                    {
                        JSONObject j = new JSONObject();
                        String fname = rs.getString("firstname");
                        String lname = rs.getString("lastname");
                        String name = fname+" "+lname;
                        j.put("name", name);
                        JSONObject at = new JSONObject();
                        rs1=st.executeQuery("select day(attendance_date) as day,attendance_value as val from attendance_main where year(attendance_date)="+year+" and month(attendance_date)="+month+" and sp_id='"+id+"'");
                        while(rs1.next())
                        {
                            String day = rs1.getString("day");
                            String val = rs1.getString("val");
                            at.put(day, val);
                        }
                        rs1.close();
                        rs2=st.executeQuery("select (sum(attendance_value))/8 as sum from attendance_main where year(attendance_date)="+year+" and month(attendance_date)="+month+" and sp_id='"+id+"'");
                        while(rs2.next())
                        {
                            int sum=rs2.getInt("sum");
                            j.put("sum", sum);
                        }
                        j.put("att", at);
                        jo.add(j);
                    }
                    rs.close();
                    st.close();
                    
                }
                catch(Exception ex)
                {
                                System.out.println("Error green");
                }
        return jo;
    }
    public JsonArray getDetails(String month,String year)
    {
        JsonArray jo = new JsonArray();
        ResultSet rs,rs1,rs2;
        Statement st,st1,st2;
                try{
                    st2=c.createStatement();
                    st1=c.createStatement();
                    st=c.createStatement();
                    rs=st.executeQuery("select * from users where userid in (select distinct(sp_id) from client_site_assignment)");
                    while(rs.next())
                    {
                        JSONObject j = new JSONObject();
                        String fname = rs.getString("firstname");
                        String lname = rs.getString("lastname");
                        String name = fname+" "+lname;
                        String id = rs.getString("userid");
                        j.put("name", name);
                        j.put("id", id);
                        JSONObject at = new JSONObject();
                        rs1=st1.executeQuery("select day(attendance_date) as day,attendance_value as val from attendance_main where year(attendance_date)="+year+" and month(attendance_date)="+month+" and sp_id='"+id+"'");
                        while(rs1.next())
                        {
                            String day = rs1.getString("day");
                            String val = rs1.getString("val");
                            at.put(day, val);
                        }
                        rs1.close();
                        rs2=st2.executeQuery("select (sum(attendance_value))/8 as sum from attendance_main where year(attendance_date)="+year+" and month(attendance_date)="+month+" and sp_id='"+id+"'");
                        while(rs2.next())
                        {
                            int sum=rs2.getInt("sum");
                            j.put("sum", sum);
                        }
                        j.put("att", at);
                        jo.add(j);
                    }
                    rs.close();
                    st.close();
                    
                }
                catch(Exception ex)
                {
                                System.out.println(ex);
                }
        return jo;
    }
    public JSONArray getDetails1(String month,String year)
    {
        JSONArray jo = new JSONArray();
        ResultSet rs,rs1,rs2;
        Statement st,st1,st2;
                try{
                    st2=c.createStatement();
                    st1=c.createStatement();
                    st=c.createStatement();
                    rs=st.executeQuery("select * from users where userid in (select distinct(sp_id) from client_site_assignment)");
                    while(rs.next())
                    {
                        JSONObject j = new JSONObject();
                        String fname = rs.getString("firstname");
                        String lname = rs.getString("lastname");
                        String name = fname+" "+lname;
                        String id = rs.getString("userid");
                        j.put("name", name);
                        j.put("id", id);
                        JSONObject at = new JSONObject();
                        rs1=st1.executeQuery("select day(attendance_date) as day,attendance_value as val from attendance_main where year(attendance_date)="+year+" and month(attendance_date)="+month+" and sp_id='"+id+"'");
                        while(rs1.next())
                        {
                            String day = rs1.getString("day");
                            String val = rs1.getString("val");
                            at.put(day, val);
                        }
                        rs1.close();
                        rs2=st2.executeQuery("select (sum(attendance_value))/8 as sum from attendance_main where year(attendance_date)="+year+" and month(attendance_date)="+month+" and sp_id='"+id+"'");
                        while(rs2.next())
                        {
                            int sum=rs2.getInt("sum");
                            j.put("sum", sum);
                        }
                        j.put("att", at);
                        jo.add(j);
                    }
                    rs.close();
                    st.close();
                    
                }
                catch(Exception ex)
                {
                                System.out.println(ex);
                }
        return jo;
    }
    public JsonArray getSalaryDetails(String id,String month,String year)
    {
        JsonArray jo = new JsonArray();
        ResultSet rs,rs1,rs2;
        Statement st;
                try{
                    st=c.createStatement();
                    rs=st.executeQuery("select * from users where userid='"+id+"'");
                    while(rs.next())
                    {
                        JSONObject j = new JSONObject();
                        String fname = rs.getString("firstname");
                        String lname = rs.getString("lastname");
                        String name = fname+" "+lname;
                        j.put("name", name);
                        rs1=st.executeQuery("select sum(amount) as sum from advance_payment where sp_id='"+id+"' and year(ap_date)='"+year+"' and month(ap_date)='"+month+"'");
                        while(rs1.next())
                        {
                            int s = rs1.getInt("sum");
                            j.put("ap", s);
                        }
                        rs1.close();
                                                    int total=0;

                        rs2=st.executeQuery("select site_id from attendance_main where year(attendance_date)="+year+" and month(attendance_date)="+month+" and sp_id='"+id+"' ");
                        while(rs2.next())
                        {
                            String site=rs2.getString("site_id");
                            ArrayList<SalaryOperation> so = getAttendanceDetails(id, site, month, year);
                            if(so.size()!=0)
                            {
                            SalaryOperation si = so.get(0);
                            int t = Integer.parseInt(si.getWage());
                                System.out.println("T "+t);
                                
                            total = total+((si.getValue()/8)*t);
                            System.out.println("wage = "+total);
                            }
                            j.put("total", total);
                        }
                        jo.add(j);
                    }
                    rs.close();
                    st.close();
                    
                }
                catch(Exception ex)
                {
                    System.out.println(ex.getLocalizedMessage());
                    ex.printStackTrace();
                }
        return jo;
    }
    
}
