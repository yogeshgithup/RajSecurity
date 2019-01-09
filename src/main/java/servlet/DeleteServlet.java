
package servlet;

import data.SecurityPerson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import operation.Operation;


@WebServlet(name = "DeleteServlet", urlPatterns = {"/DeleteServlet"})
public class DeleteServlet extends HttpServlet {
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
        c=(Connection)sc.getAttribute("con");
        Operation op = new Operation(c);
        PrintWriter out = response.getWriter();
        try {
           String type=request.getParameter("type");
           
           if(type.equals("security"))
           {
               String id = request.getParameter("id");
                
               String msg = op.deleteSecurity(id);
               response.sendRedirect(request.getContextPath()+"/ViewDetailsServlet");
           }
           if(type.equals("bank"))
           {
               String id = request.getParameter("id");
               String msg = op.deleteBank(id);
               response.sendRedirect(request.getContextPath()+"/ViewDetailsServlet");
           }
           if(type.equals("exp"))
           {
               String id = request.getParameter("id");
               String msg = op.deleteExperience(id);
               response.sendRedirect(request.getContextPath()+"/ViewDetailsServlet");
           }
           if(type.equals("add"))
           {
               String id = request.getParameter("id");
               String msg = op.deleteResAdd(id);
               response.sendRedirect(request.getContextPath()+"/ViewDetailsServlet");
           }
           if(type.equals("per"))
           {
               String id = request.getParameter("id");
               String msg = op.deletePerAdd(id);
               response.sendRedirect(request.getContextPath()+"/ViewDetailsServlet");
           }
        } finally {
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
