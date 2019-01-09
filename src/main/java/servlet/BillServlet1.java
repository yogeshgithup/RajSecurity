
package servlet;

import data.AmountToWordConverter;
import data.BillAllDetails;
import data.BillDetails;
import data.ClientQuotation;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import operation.BillOperation;


public class BillServlet1 extends HttpServlet {

   Connection c;
ServletContext sc;
public void init(ServletConfig config) throws ServletException
{
    super.init(config);
    sc=this.getServletContext();
}
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, JRException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        JasperReport jasperReport = null;
        JasperDesign jasperDesign = null;
        Map parameters = new HashMap();
        
        try {
            
            c=(Connection)sc.getAttribute("con");
            if(c!=null)
            {
                BillDetails bd;
                BillAllDetails bad = new BillAllDetails();
                String client = request.getParameter("id");
                double gtotal=0,total=0,gst=0,scharge=0;
                int bid=1;
                double mon = Double.parseDouble(request.getParameter("mon"));
                BillOperation bo = new BillOperation(c);
                String schk = request.getParameter("schk");
                String gsttt = request.getParameter("gstt");
                String m = request.getParameter("m");
                String year = request.getParameter("year");
                String bill = request.getParameter("bill");
                double sval = Double.parseDouble(request.getParameter("sval"));
                ArrayList<ClientQuotation> cq = bo.getCQ(client,m,year);
                for(int i=0;i<cq.size();i++)
                {
                   double amt=0;
                   ClientQuotation cc = cq.get(i);
                   amt=cc.getRate()*bo.getFinal1(client, m, year,cc.getRate()+"");
                   total=total+amt;
                }
                //total=bo.getAmount(client,m,year);
                AmountToWordConverter atw = new AmountToWordConverter();
                
               // total=total*mon;
               if(gsttt.equals("yes"))
               {
                gst=(total*0.09);
               }
               if(schk.equals("yes"))
               {
                   scharge = (total*(sval/100));
               }
               else
               {
                   gst=0;
               }
                System.out.println("gst = "+gst);
                gtotal=total+(2*gst);
                gtotal+=scharge;
                System.out.println(Math.ceil(gtotal));
                int igtotal = (int)gtotal;
                gtotal = Math.ceil(gtotal);
                System.out.println("Gtotal : "+gtotal);
                String sgtotal = String.format("%.2f", gtotal);
                String word = atw.converter(sgtotal);
                System.out.println("word "+word);
                String da = request.getParameter("date");
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yy");
                long d = Date.parse(da);
                String dd = formatter.format(d);
                int[] amt = bo.getAmt(sgtotal);
                String aa = Integer.toString(amt[1]);
                if(Integer.parseInt(aa)<10)
                {
                    aa=aa+"0";
                }
                String gstt = bo.getGST(client);
                String address = bo.getAddress(client);
                System.out.println(address);
                int[] gg = bo.getAmt(gst+"");
                String ggg = Integer.toString(gg[1]);
                if(Integer.parseInt(ggg)<10)
                {
                    ggg=ggg+"0";
                }
                int[] pscharge = bo.getAmt(scharge+"");
                bd = new BillDetails(Integer.toString(bo.getBillNo()),bo.getClient(client) ,dd,address,total+"" , gg[0]+"", gg[0]+"", pscharge[0]+"",pscharge[1]+"", igtotal+"",word,aa+"",gstt,ggg);
                bo.delBillAll();
                
                bo.insertBillDetails(bd);
                for(int i=0;i<cq.size();i++)
                {
                    System.out.println("SIZEEEEEEEEE : "+cq.size());
                    BillAllDetails bb ;
                    ClientQuotation cc = cq.get(i);
                    int srno=i+1;
                    String particulars = cc.getSpost();
                    //double qty=Double.parseDouble(cc.getNo())*mon;
                    int qty = bo.getFinal1(client, m, year, cc.getRate()+"");
                    double rate = cc.getRate();
                    String str = String.format("%.2f", rate);
                    System.out.println(str + " rate");
                    double to = qty*rate;
                    int pp[] = bo.getAmt(to+"");
                    String rs1 = Double.toString(to);
                    int index = rs1.indexOf(".");
                    String rs = rs1.substring(0,index);
                    String ppp = Integer.toString(pp[1]);
                    if(Integer.parseInt(ppp)<10)
                    {   
                        ppp=ppp+"0";
                    }
                    bb=new BillAllDetails(srno+"", particulars, qty+"", str, pp[0]+"", Integer.toString(bo.getBillNo()), srno+"",ppp+"");
                    bo.insertBillAllDetails(bb);
                }
                bo.insertBill_Details(bo.getBillId(), client, bo.getBillNo(), da, sgtotal);
                System.out.println("gtotal = "+gtotal);
                String path = getServletContext().getRealPath("");
                parameters.put("capture", path+"/Capture.png");
                parameters.put("logo", path+"/logo.png");
                System.out.println("path=="+path);
                jasperDesign = JRXmlLoader.load(path+"/UserPannelDesign/"+ "rajBillNew.jrxml");
                jasperReport = JasperCompileManager.compileReport(jasperDesign);
                byte[] byteStream = JasperRunManager.runReportToPdf(jasperReport, parameters,(Connection)this.getServletContext().getAttribute("con"));
                System.out.println("--"+byteStream.length);
                File f=new File(path+"Bill.pdf");
                FileOutputStream  fos=new FileOutputStream(f);
                fos.write(byteStream);
                response.setContentType("application/pdf");
                OutputStream outStream = response.getOutputStream();
                String headerKey = "Content-Disposition";
                String fn=bo.getClient(client)+".pdf";
                String headerValue = String.format("attachment; filename=\"%s\"",fn);
                response.setHeader(headerKey, headerValue);
                response.setContentLength(byteStream.length);
                outStream.write(byteStream, 0, byteStream.length);
            }
        } finally {
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
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       try {
           processRequest(request, response);
       } catch (Exception ex) {
           Logger.getLogger(BillServlet1.class.getName()).log(Level.SEVERE, null, ex);
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
       } catch (Exception ex) {
           Logger.getLogger(BillServlet1.class.getName()).log(Level.SEVERE, null, ex);
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
