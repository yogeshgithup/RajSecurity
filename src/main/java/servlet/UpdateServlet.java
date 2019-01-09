
package servlet;

import data.SecurityPerson;
import data.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import operation.Operation;
@MultipartConfig
public class UpdateServlet extends HttpServlet {
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
        c = (Connection)sc.getAttribute("con");
        if(c!=null)
        {
            Operation op = new Operation(c);
        try {
                
                Part img = request.getPart("changeimage");
                String id=request.getParameter("id");
                String email = request.getParameter("email");
                String name=request.getParameter("name");
                System.out.println(request.getParameter("dob"));
                String dob = request.getParameter("dob");
                String nation = request.getParameter("nationality");
                String qualification = request.getParameter("qualification");
                String no = request.getParameter("no");
                String stype=request.getParameter("stype");
                String status = request.getParameter("status");
                String police= request.getParameter("police");
                String railway = request.getParameter("railway");
                String post = request.getParameter("post");
                System.out.println(id);
                SecurityPerson sp = op.viewSecurityPerson(id);
                String iname = sp.getIname();
                System.out.println("ID"+iname);
                File f = new  File(request.getServletContext().getRealPath("")+"imageUpload/"+iname);
                
                File f1 = new  File(request.getServletContext().getRealPath("")+"download/"+iname);
                System.out.println("INAME = "+iname);
                if(img!=null)
                {
                    System.out.println("IN IF");
                f1.delete();
                f.delete();
                }
                SecurityPerson p = new SecurityPerson();
                String[] na = name.split("\\s");
                System.out.println(na[0]);
                
                p.setDOB(dob);
                p.setFathername(na[1]);
                if(img!=null)
                p.setIname(img.getSubmittedFileName());
                
                p.setNationality(nation);
                p.setQualification(qualification);
                p.setS_type(stype);
                p.setPstation(police);
                p.setNstation(railway);
                p.setPost(post);
                p.setStatus(status);
                p.setOther("Other");
                p.setUserid(id);
                p.setPstation("Police Station");
                p.setNstation("Railway Station");
                String appPath = request.getServletContext().getRealPath("");
                File f2 = null;
                if(img!=null)
                {
                     f2 = new File(img.getSubmittedFileName());
                img.write(appPath+"imageUpload" + File.separator + f2.getName());
                InputStream filecontent=new FileInputStream(appPath+"imageUpload" + File.separator + f2.getName());
                p.setImage(filecontent);
                }
                if(img==null)
                {
                    System.out.println("IN IF");
                    System.out.println(op.updateSecurityPersonwi(p));
                }
                else
                {
                    System.out.println("IN ELSE");
                    System.out.println(op.updateSecurityPerson(p));
                }
                User u = new User();
                u.setFname(na[0]);
                u.setUserid(id);
                u.setMname(na[1]);
                u.setLname(na[2]);
                u.setNumber(no);
                u.setEmail(email);
                if(img!=null)
                {
                    System.out.println("IN IF");
                    out.println(f2.getName());
//                out.println(request.getServletContext().getContextPath()+"/imageUpload/"+f2.getName());
                }
                else
                {
                    System.out.println("IN ELSE");
                    out.println(iname);
//                    out.println(request.getServletContext().getContextPath()+"/download/"+iname);
                }
                    System.out.println(op.updateUser(u));
        } finally {
            out.close();
        }
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
