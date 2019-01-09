package servlet;

import data.Quotation;
import data.QuotationDetails;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import operation.QuotationOperation;


public class NewAddQuotation extends HttpServlet {
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
            throws ServletException, IOException, JRException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            c=(Connection)sc.getAttribute("con");
            if(c!=null)
            {
                QuotationOperation qo = new QuotationOperation(c);
                String name = request.getParameter("name");
                String orgname = request.getParameter("orgname");
                String city = request.getParameter("city");
                String gunmenhrs = request.getParameter("gunmenhrs");
                String gunmenex = request.getParameter("gunmenex");
                String gunmenct = request.getParameter("gunmenct");
                String bodyguardhrs = request.getParameter("bodyguardhrs");
                String bodyguardex = request.getParameter("bodyguardex");
                String bodyguardct = request.getParameter("bodyguardct");
                String officershrs = request.getParameter("officershrs");
                String officerex = request.getParameter("officerex");
                String officerct = request.getParameter("officerct");
                String supervisorshrs = request.getParameter("supervisorshrs");
                String supervisorsex = request.getParameter("supervisorsex");
                String supervisorsct = request.getParameter("supervisorsct");
                String guardshrs = request.getParameter("guardshrs");
                String guardsex = request.getParameter("guardsex");
                String guardsct = request.getParameter("guardsct");
                String ladyhrs = request.getParameter("ladyhrs");
                String ladysex = request.getParameter("ladysex");
                String ladyct = request.getParameter("ladyct");
                String scharge = request.getParameter("scharge");
                String qid = qo.getQuotationId();
                String cid = qo.getClientId();
                Date d =new Date();
                int day = d.getDate();
                int month = d.getMonth()+1;
                int year = d.getYear()+1900;
                int refno = qo.getRefNo();
                Quotation qt = new Quotation(cid,name, orgname, city,refno+"",day+"/"+month+"/"+year,scharge);
                QuotationDetails qd1 = new QuotationDetails(qid,"1",cid,"Gunmen",gunmenhrs,gunmenex,gunmenct);
                QuotationDetails qd2 = new QuotationDetails(qid,"2",cid,"Body Guards",bodyguardhrs,bodyguardex,bodyguardct);
                QuotationDetails qd3 = new QuotationDetails(qid,"3",cid,"Security Officer",officershrs,officerex,officerct);
                QuotationDetails qd4 = new QuotationDetails(qid,"4",cid,"Security Supervisors",supervisorshrs,supervisorsex,supervisorsct);
                QuotationDetails qd5 = new QuotationDetails(qid,"5",cid,"Security Guard",guardshrs,guardsex,guardsct);
                QuotationDetails qd6 = new QuotationDetails(qid,"6",cid,"Lady Searcher",ladyhrs,ladysex,ladyct);
                qo.InsertQuotation(qt);
                qo.insertQuotationDetails(qd1);
                qo.insertQuotationDetails(qd2);
                qo.insertQuotationDetails(qd3);
                qo.insertQuotationDetails(qd4);
                qo.insertQuotationDetails(qd5);
                qo.insertQuotationDetails(qd6);
                JasperReport jasperReport = null;
                JasperDesign jasperDesign = null;
                Map parameters = new HashMap();
                parameters.put("CID", cid);
                String path = getServletContext().getRealPath("");
                System.out.println("path=="+path);
                 parameters.put("logo", path+"logo.png");
                 parameters.put("capture", path+"Capture.png");
                jasperDesign = JRXmlLoader.load(path+"UserPannelDesign/"+ "rajBillNew_1.jrxml");
                jasperReport = JasperCompileManager.compileReport(jasperDesign);
                byte[] byteStream = JasperRunManager.runReportToPdf(jasperReport, parameters,(Connection)this.getServletContext().getAttribute("con"));
                System.out.println("--"+byteStream.length);
                File f=new File(path+"Quotation.pdf");
                FileOutputStream  fos=new FileOutputStream(f);
                fos.write(byteStream);
                response.setContentType("application/pdf");
                OutputStream outStream = response.getOutputStream();
                String headerKey = "Content-Disposition";
                String fn=orgname+" Quotation.pdf";
                String headerValue = String.format("attachment; filename=\"%s\"",fn);
                response.setHeader(headerKey, headerValue);
                response.setContentLength(byteStream.length);
                outStream.write(byteStream, 0, byteStream.length);
                response.sendRedirect(request.getContextPath()+"/UserPannelDesign/AddQuotation.jsp");
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        finally {

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
    try {
        processRequest(request, response);
    } catch (JRException ex) {
        Logger.getLogger(NewAddQuotation.class.getName()).log(Level.SEVERE, null, ex);
    }
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
    try {
        processRequest(request, response);
    } catch (JRException ex) {
        Logger.getLogger(NewAddQuotation.class.getName()).log(Level.SEVERE, null, ex);
    }
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
