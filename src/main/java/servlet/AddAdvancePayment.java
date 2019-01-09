
package servlet;

import data.AdvancePayment;
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


public class AddAdvancePayment extends HttpServlet {
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
            c = (Connection)sc.getAttribute("con");
            if(c!=null)
            {
                AttendanceOperation ao = new AttendanceOperation(c);
                String date = request.getParameter("datepicker");
                String add = null;
                String[] names;
                names = request.getParameterValues("securitypicker");
                
                String amount = request.getParameter("amount");
                for(int i=0;i<names.length;i++)
                {
                AdvancePayment ap = new AdvancePayment();
                ap.setAmt(amount);
                ap.setId(names[i]);
                ap.setDate(date);
               add= ao.addAdvancePayement(ap);
                }
                 if(add.equals("success"))
                   response.sendRedirect(request.getContextPath()+"/UserPannelDesign/AdvancePayment.jsp?msg=Advance Payment Recorded Succesfully&div=isa_success&fa=fa fa-check");
               else
                   response.sendRedirect(request.getContextPath()+"/UserPannelDesign/AdvancePayment.jsp?msg=There Was An Error While Recording Advance Payment&div=isa_error&fa=fa fa-times-circle");
               
                }
                
                
        }
        catch(Exception e)
        {
            response.sendRedirect(request.getContextPath()+"/UserPannelDesign/AdvancePayment.jsp?msg=There Was An Error While Recording Advance Payment&div=isa_error&fa=fa fa-times-circle");
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
