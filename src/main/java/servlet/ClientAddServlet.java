
package servlet;

import data.Client;
import data.SiteAddress;
import data.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Random;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import operation.LoginOperation;
import operation.Operation;
@WebServlet(name = "ClientAddServlet", urlPatterns = {"/ClientAddServlet"})
public class ClientAddServlet extends HttpServlet {
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
                String add;
                Operation op = new Operation(c);
                LoginOperation lop = new LoginOperation(c);
                User u = new User();
               Client ct = new Client();
                String cid = op.getClientId();
                String fname = request.getParameter("fname");
                String mname = request.getParameter("mname");
                String lname = request.getParameter("lname");
                String email = request.getParameter("email");
                String no = request.getParameter("no");
                String orgname = request.getParameter("orgname");
                String orgurl = request.getParameter("orgurl");
                String gst = request.getParameter("gst");
                u.setUserid(cid);
                u.setFname(fname);
                u.setLname(lname);
                u.setMname(mname);
                u.setNumber(no);
                u.setEmail(email);
                System.out.println("User " + op.addUser(u));
                Random rnd = new Random();
                int n = 100000 + rnd.nextInt(900000);
                String pass = Integer.toString(n);
                lop.addClient(email, pass, no);
                ct.setOrgname(orgname);
                ct.setUrl(orgurl);
                ct.setUserid(cid);
                ct.setGst(gst);
                add = op.addClient(ct);
                int count = Integer.parseInt(request.getParameter("count"));
                System.out.println(count);
                for(int i=0;i<count;i++)
                {
                    SiteAddress st = new SiteAddress();
                    String sid = op.getSiteId();
                    String address = request.getParameter("address"+(i+1));
                    String area = request.getParameter("area"+(i+1));
                    String cntno = request.getParameter("cntno"+(i+1));
                    st.setAddress(address);
                    st.setCid(cid);
                    st.setSid(sid);
                    st.setAddress(address);
                    st.setArea(area);
                    st.setCno(cntno);
                    
                    System.out.println("Site "+op.addSiteAddress(st));
                    
                }
                if(add.equals("success"))
                response.sendRedirect(request.getContextPath()+"/UserPannelDesign/ClientAdd.jsp?msg=Client Added Succesfully&div=isa_success&fa=fa fa-check");
                else
                    response.sendRedirect(request.getContextPath()+"/UserPannelDesign/ClientAdd.jsp?msg=There Was An Error While Adding Client&div=isa_error&fa=fa fa-times-circle");
                
            }
            
        }
        catch(Exception e)
                    {
                        response.sendRedirect(request.getContextPath()+"/UserPannelDesign/ClientAdd.jsp?msg=There Was An Error While Adding Client&div=isa_error&fa=fa fa-times-circle");
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
