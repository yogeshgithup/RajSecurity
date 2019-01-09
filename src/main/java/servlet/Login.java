
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import operation.LoginOperation;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import operation.Operation;


public class Login extends HttpServlet {
Connection c;
ServletContext sc;

public void init(ServletConfig config) throws ServletException
{
    super.init(config);
    sc=this.getServletContext();
}
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            c=(Connection)sc.getAttribute("con");
            if(c!=null)
            {
                LoginOperation op = new LoginOperation(c);
                Operation oop = new Operation(c);
                String email=request.getParameter("email");
                String pass=request.getParameter("password");
                if(op.getPassowrd(email, pass))
                {
                    if(op.isAdmin(email))
                    {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("email", email);
                    out.println("admin");
                        System.out.println("called");
                    //response.sendRedirect(request.getContextPath()+"/UserPannelDesign/SecurityPersonAdd.jsp?msg=SuccessFully Logged In&div=isa_success&fa=fa fa-check");
                    }
                    if(op.isClient(email))
                    {
                        HttpSession session = request.getSession(true);
                        session.setAttribute("client", email);
                        String cid=oop.getClientId(email);
                        session.setAttribute("cid", cid);
                        System.out.println("  -----cid   "+cid);
                      //  response.sendRedirect(request.getContextPath()+"/Client/");
                    }
                }
                else
                {
                    //response.sendRedirect(request.getContextPath()+"/UserPannelDesign/");
                    out.println("error");
                }
            }
        }
        catch(Exception e)
        {
            out.println("Error At Login");
        }
        finally {
            out.close();
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            c=(Connection)sc.getAttribute("con");
            if(c!=null)
            {
                LoginOperation op = new LoginOperation(c);
                Operation oop = new Operation(c);
                String email=request.getParameter("email");
                String pass=request.getParameter("password");
                if(op.getPassowrd(email, pass))
                {
                    if(op.isAdmin(email))
                    {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("email", email);
                    out.println("admin");
                        System.out.println("called");
                    response.sendRedirect(request.getContextPath()+"/UserPannelDesign/SecurityPersonAdd.jsp?msg=SuccessFully Logged In&div=isa_success&fa=fa fa-check");
                    }
                    if(op.isClient(email))
                    {
                        HttpSession session = request.getSession(true);
                        session.setAttribute("client", email);
                        String cid=oop.getClientId(email);
                        session.setAttribute("cid", cid);
                        System.out.println("  -----cid   "+cid);
                        response.sendRedirect(request.getContextPath()+"/Client/");
                    }
                }
                else
                {
                    response.sendRedirect(request.getContextPath()+"/UserPannelDesign/");
                    out.println("error");
                }
            }
        }
        catch(Exception e)
        {
            out.println("Error At Login");
        }
        finally {
            out.close();
        }
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
