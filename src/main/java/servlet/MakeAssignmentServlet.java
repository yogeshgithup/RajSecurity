
package servlet;

import data.Assign;
import data.AttendanceMain;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Iterator;
import java.util.Set;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import operation.AttendanceOperation;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class MakeAssignmentServlet extends HttpServlet {
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
                String s = null;
                c=(Connection)sc.getAttribute("con");
                System.out.println("Make Assignment");
                if(c!=null)
                {
                AttendanceOperation op = new AttendanceOperation(c);
                String d = request.getParameter("d");
                    System.out.println(d);
                JSONParser parser = new JSONParser();
                JSONArray object = (JSONArray) parser.parse(d);
                    System.out.println(object);
                String date=request.getParameter("date");
                String site = request.getParameter("site");
                int day = Integer.parseInt(request.getParameter("day"));
                for(int i=0;i<object.size();i++)
                {

                    JSONObject obj = (JSONObject) object.get(i);
                   Set set= obj.keySet();
                   Iterator it=set.iterator();
                   String k=(String)it.next();
                   String v=(String)obj.get(k);
                   double wa = Double.parseDouble(v);
                   double pdw = wa/day;
                   String spdw = String.format("%.2f", pdw);
                   pdw = Double.parseDouble(spdw);
                   Assign as = new Assign();
                   as.setDate(date);
                   as.setSiteid(site);
                   as.setSpid(k);
                   as.setWage(v);
                   as.setDwage(pdw);
                   s =  op.addAssignment(as);
                    System.out.println(s);
                    out.println(pdw);
                }
                if(s.equals("success"))
                {
                    response.sendRedirect(request.getContextPath()+"/UserPannelDesign/AssignClient.jsp?msg=Successfully Assigned Security Person To The Client Site&div=isa_success&fa=fa fa-check");
                }
                else
                 response.sendRedirect(request.getContextPath()+"/UserPannelDesign/AssignClient.jsp?msg=There Was A Error While Assigning Security Person&div=isa_error&fa=fa fa-times-circle");
                }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
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
