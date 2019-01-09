
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import operation.AttendanceOperation;
import operation.Operation;
import org.json.simple.JSONArray;
import org.json.simple.JsonArray;


public class GetDetails extends HttpServlet {
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
        try {
            c=(Connection)sc.getAttribute("con");
            if(c!=null)
            {
                Operation op = new Operation(c);
                String type=request.getParameter("type");
                AttendanceOperation aop = new AttendanceOperation(c);
                String id=request.getParameter("id");
                String month = request.getParameter("month");
                String year = request.getParameter("year");
                if(type.equals("days"))
                {
 
                JsonArray ja = aop.getDetails(id, month, year);
                String j = ja.toString();
                out.println(j);
                }
                if(type.equals("salary"))
                {
                JsonArray ja = op.getUserSecurityJson(month, year, id);
                String j = ja.toString();
                out.println(ja);
                }
                if(type.equals("android"))
                {
                    String m = null;
                    if(month.equals("January"))
                        m="1";
                    if(month.equals("February"))
                        m="2";
                    if(month.equals("March"))
                        m="3";
                    if(month.equals("April"))
                        m="4";
                    if(month.equals("May"))
                        m="5";
                    if(month.equals("June"))
                        m="6";
                    if(month.equals("July"))
                        m="7";
                    if(month.equals("August"))
                        m="8";
                    if(month.equals("September"))
                        m="9";
                    if(month.equals("October"))
                        m="10";
                    if(month.equals("November"))
                        m="11";
                    if(month.equals("December"))
                        m="12";
                JSONArray ja = aop.getDetails1(m, year);
                String j = ja.toString();
                out.println(ja);
                }
            }
        }
        catch(Exception e)
        {
            
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
