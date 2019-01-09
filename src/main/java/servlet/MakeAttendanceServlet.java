
package servlet;
import data.AttendanceMain;
import operation.AttendanceOperation;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import operation.AttendanceOperation;
import operation.Operation;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;


public class MakeAttendanceServlet extends HttpServlet {
ServletContext sc;
    Connection c;
    @Override
    public void init(ServletConfig config)
              throws ServletException
    {
        super.init(config);
        sc=this.getServletContext();
            
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String msg = null;
        try {
                c=(Connection)sc.getAttribute("con");
                if(c!=null)
                {
                AttendanceOperation op = new AttendanceOperation(c);
                String d = request.getParameter("d");
                JSONParser parser = new JSONParser();
                JSONArray object = (JSONArray) parser.parse(d);
                
                for(int i=0;i<object.size();i++)
                {
                    JSONObject obj = (JSONObject) object.get(i);
                   Set set= obj.keySet();
                   Iterator it=set.iterator();
                   String k=(String)it.next();
                   String v=(String)obj.get(k);
                    
                    String client = (String)obj.get("client");
                    String value = (String)obj.get("value");
                    String id = (String)obj.get("id");
                    String day = (String)obj.get("day");
                    String site = (String)obj.get("site");
                    String month = (String)obj.get("month");
                    Date d1 = new Date();
                    int u = Integer.parseInt((String)obj.get("year"));
                    int dd = Integer.parseInt(day);
                    int mm = Integer.parseInt(month);
                    Calendar calendar = Calendar.getInstance();  
calendar.set(u,(mm-1),dd);
Date da=calendar.getTime();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
    String strDate= formatter.format(da);  
                   AttendanceMain am = new AttendanceMain();
                   am.setAid(op.getAttendanceid());
                   am.setClient(client);
                   am.setSite(site);
                   am.setDate(strDate);
                   am.setId(id);
                   am.setValue(value);
                   msg = op.addAttendance(am);
                   
                }
                    System.out.println(msg);
                    if(msg.equals("Error At Attendance "))
                    {
                 out.println("Error While Marking Attendance.");
                 out.println("Cause : Same Person Cannot Be Present At More Than One Location On A Given Date ");
                    }
                    else
                        out.println("Success");
                
                 }
        }
        catch(Exception e)
        {
            msg="There Was A Error While Marking Attendance";
            
            out.println(e.getMessage());
            e.printStackTrace();
            response.sendRedirect(request.getContextPath()+"/UserPannelDesign/NewAddAttendance.jsp?msg="+msg+"&div=isa_error&fa=fa fa-times-circle");
        }
        finally {
            out.close();
        }
        
                
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
