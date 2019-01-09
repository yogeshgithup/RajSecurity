
package servlet;

import data.AttendanceMain;
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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class UpdateAttendance extends HttpServlet {
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
                    String msg;

        try {
            c=(Connection)sc.getAttribute("con");
            if(c!=null)
            {
                
            AttendanceOperation op = new AttendanceOperation(c);
             String json = request.getParameter("json");
             JSONParser parser = new JSONParser();
             JSONArray object = (JSONArray) parser.parse(json);
             for(int i=0;i<object.size();i++)
                {
                    JSONObject obj = (JSONObject) object.get(i);
                   Set set= obj.keySet();
                   Iterator it=set.iterator();
                   String k=(String)it.next();
                   String v=(String)obj.get(k);
                    out.println(k+" "+v);
                    String value = (String)obj.get("value");
                    String id = (String)obj.get("id");
                    String day = (String)obj.get("day");
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
                   am.setDate(strDate);
                   am.setId(id);
                   am.setValue(value);
                   System.out.println(op.UpdateAttendance(am)+" "+k);
                }
             msg="Attendance Updated Successfully";
                 response.sendRedirect(request.getContextPath()+"/UserPannelDesign/NewAddAttendance.jsp?msg="+msg+"&div=isa_success&fa=fa fa-check");
               
            }
        }
        catch(Exception e)
        {
            msg="There Was A Error While Updating Attendance";
            
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
