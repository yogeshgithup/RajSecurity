package servlet;

import com.mysql.jdbc.StringUtils;
import data.Address;
import data.BankDetails;
import data.Experience;
import data.SecurityPerson;
import data.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.text.SimpleDateFormat;
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
public class AddSecurityPersonServlet extends HttpServlet {
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
        c=(Connection)sc.getAttribute("con");
        
        if(c!=null)
        {
            try 
            {
                Operation op = new Operation(c);
                System.out.println(op.getSecurityPersonId());
                String id=op.getSecurityPersonId();
                String fname=request.getParameter("fname");
                String mname=request.getParameter("mname");
                String lname=request.getParameter("lname");
                int count=Integer.parseInt(request.getParameter("count"));
                String gender=request.getParameter("gender");
                String fathername=request.getParameter("fathername");
                String number = request.getParameter("number");
                String email=request.getParameter("email");
                long dob1=Date.parse(request.getParameter("datepicker"));
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
                String dob = formatter.format(dob1);  
                Part image=request.getPart("image");
                String qualification = request.getParameter("select");
                String s_type=request.getParameter("s_type");
                String post = request.getParameter("post");
                String nationality = request.getParameter("nationality");
                String resadd1=request.getParameter("resadd1");
                String resadd2=request.getParameter("resadd2");
                String resarea=request.getParameter("resarea");
                String resdistrict=request.getParameter("resdis");
                String respin=request.getParameter("respin");
                String resstate=request.getParameter("resstate");
                String peradd1=request.getParameter("peradd1");
                String peradd2=request.getParameter("peradd2");
                String perarea=request.getParameter("perarea");
                String perdistrict=request.getParameter("perdis");
                String perpin=request.getParameter("perpin");
                String perstate=request.getParameter("perstate");
                String account=request.getParameter("account");
                String ifsc=request.getParameter("ifsc");
                String bank=request.getParameter("bank");
                String branch=request.getParameter("branch");
                String city=request.getParameter("city");
                String rstation=request.getParameter("rstation");
                String pstation=request.getParameter("pstation");
                String other=request.getParameter("other");
                User u = new User();
                u.setUserid(id);
                u.setEmail(email);
                u.setFname(fname);
                u.setLname(lname);
                u.setMname(mname);
                u.setNumber(number);
                String useradd=op.addUser(u);
                System.out.println("Add User="+useradd);
                SecurityPerson sp = new SecurityPerson();
                sp.setFathername(fathername);
                sp.setDOB(dob);
                sp.setUserid(id);
                sp.setNationality(nationality);
                sp.setNstation(rstation);
                sp.setOther(other);
                sp.setPstation(pstation);
                sp.setQualification(qualification);
                sp.setStatus("New");
                
                sp.setPost(post);
                sp.setS_type(s_type);
                String appPath = request.getServletContext().getRealPath("");
                File ff=new File(appPath+"imageUpload");
                if(!ff.exists())        
                {
                    boolean x= ff.mkdir();
                    System.out.println("---"+x);
                }
                String iname = image.getSubmittedFileName();
                iname=iname.replace(" ", "_");
                File f = new File(iname);
                image.write(appPath+"imageUpload" + File.separator + f.getName());
                 sp.setIname(f.getName());
                InputStream filecontent=new FileInputStream(appPath+"/imageUpload" + File.separator + f.getName());
                sp.setImage(filecontent);
                String addSecurity=op.addSecurityPerson(sp);
                System.out.println("Add Security="+addSecurity);
                Address ad=new Address();
                ad.setId(id);
                ad.setAdd1(peradd1);
                ad.setAdd2(peradd2);
                ad.setAdd_type("Permanent");
                ad.setArea(perarea);
                ad.setDistrict(perdistrict);
                ad.setPincode(perpin);
                ad.setState(perstate);
                String addres=op.addAddress(ad);
                System.out.println("Add AddRes="+addres);
                Address ad1=new Address();
                ad1.setId(id);
                ad1.setAdd1(resadd1);
                ad1.setAdd2(resadd2);
                ad1.setAdd_type("Residance");
                ad1.setArea(resarea);
                ad1.setDistrict(resdistrict);
                ad1.setPincode(respin);
                ad1.setState(resstate);
                String addper=op.addAddress(ad1);
                System.out.println("Add AddPer="+addper);
                BankDetails bd= new BankDetails();
                bd.setId(id);
                bd.setBranch(branch);
                bd.setAccount(account);
                bd.setCity(city);
                bd.setIfsc(ifsc);
                bd.setName(bank);
                String addBank=op.addBankDetails(bd);
                System.out.println("Add AddBank="+addBank);
                for(int i=0;i<count;i++)
                {
                    Experience e = new Experience();
                    e.setId(id);
                    e.setAdd(request.getParameter("address"+(i+1)));
                    e.setName(request.getParameter("orgname"+(i+1)));
                    e.setYears(request.getParameter("exp"+(i+1)));
                    String addExp = op.addExperienceDetails(e);
                    System.out.println("Add addExp"+i+1+"="+addExp);
                }
                if(addSecurity.equals("success"))
                response.sendRedirect(request.getContextPath()+"/UserPannelDesign/SecurityPersonAdd.jsp?msg=Security Person Added Succesfully&div=isa_success&fa=fa fa-check");
                else
                    response.sendRedirect(request.getContextPath()+"/UserPannelDesign/SecurityPersonAdd.jsp?msg=There Was An Error While Adding Security Person&div=isa_error&fa=fa fa-times-circle");
            }   
            catch(Exception e)
            {
                System.out.println("Error At Add Security Person "+e.getMessage());
                e.printStackTrace();
            response.sendRedirect(request.getContextPath()+"/UserPannelDesign/SecurityPersonAdd.jsp?msg=There Was An Error While Adding Security Person&div=isa_error&fa=fa fa-times-circle");
            }
            finally 
            {
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
