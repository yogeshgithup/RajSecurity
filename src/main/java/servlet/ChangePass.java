
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
import operation.LoginOperation;
public class ChangePass extends HttpServlet {
Connection c;
ServletContext sc;
public void init(ServletConfig config) throws ServletException
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
            LoginOperation op = new LoginOperation(c);
            String old=request.getParameter("old");
            String email=request.getParameter("email");
            String ne=request.getParameter("new");
            String conf = request.getParameter("confirm");
            
                if(op.checkPassword(email, old))
                {
                    op.updatePassword(email, ne);
                    if(op.isAdmin(email))
                    response.sendRedirect(request.getContextPath()+"/UserPannelDesign/");
                    if(op.isClient(email))
                    response.sendRedirect(request.getContextPath()+"/Client/");
                }
                
        }
        catch(Exception e)
        {
            System.out.println("Error While Changing Password");
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
