<%-- 
    Document   : AdvancePayment
    Created on : Mar 19, 2018, 3:19:20 PM
    Author     : Yogesh Chawla
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js" lang="">
    <head>
        <%@include file="headerfiles.jsp" %>
         <link rel="stylesheet" href="<%=application.getContextPath()%>/UserPannelDesign/files/jquery-ui.css">
      
  <script src="<%=application.getContextPath()%>/UserPannelDesign/files/jquery-1.12.4.js"></script>
   <script src="<%=application.getContextPath()%>/UserPannelDesign/files/jquery.dataTables.min.js"> </script>
   <script src="<%=application.getContextPath()%>/UserPannelDesign/files/jquery-ui.js"></script>
     <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

   <link rel="stylesheet" href="<%=application.getContextPath()%>/UserPannelDesign/files/jquery-ui.css">
   <link rel="stylesheet" href="<%=application.getContextPath()%>/UserPannelDesign/css/style.css">
  <link href="<%=application.getContextPath()%>/UserPannelDesign/files/jquery.dataTables.min.css" rel="stylesheet">
    </head>
    <script>
<% String msg = request.getParameter("msg");
                String d = request.getParameter("div");
                String fa = request.getParameter("fa");
                    %>
    $(document).ready(function(){
        
        $('#datepicker').on('change',function(){
    $.ajax({
                  url:'<%=application.getContextPath()%>/SetAdvanceServlet',
                  data:{'client':''},
                  success:function(res) 
                  {
                       
                           var obj = JSON.parse(res);
//                         $('#hidden').css("visibility","visible");
                           $('#securitypicker').empty();
                           //$('#securitypicker').append('<option value="Not Selected"> Select Security Person</option>');
                           for(i=0;i<obj.length;i++)
                           {
                               var obj1 = obj[i];
                               
                               $('#securitypicker').append('<option value="'+obj1.sid+'">'+obj1.sname+'</option>');
                           }
                       
                  },
                  error:function(xhr,res)
                  {
                      $('#securitypicker').append('<option value="Not Selected"> Select Security Person</option>');
                  }
                });    
});
 $('#success').delay(3000).fadeOut();
});     



        

        </script>
        <style>
            .isa_info, .isa_success, .isa_warning, .isa_error {
margin: 10px 0px;
padding:12px;
 
}
.isa_info {
    color: #00529B;
    background-color: #BDE5F8;
}
.isa_success {
    color: #4F8A10;
    background-color: #DFF2BF;
}
.isa_warning {
    color: #9F6000;
    background-color: #FEEFB3;
}
.isa_error {
    color: #D8000C;
    background-color: #FFD2D2;
}
.isa_info i, .isa_success i, .isa_warning i, .isa_error i {
    margin:10px 22px;
    font-size:2em;
    vertical-align:middle;
}
            </style>
        <%@include file="headerfiles.jsp" %>
        <body>
        <!-- Left Panel -->

        <!-- /#left-panel -->
        <%@include file="leftpanel.jsp" %>
        <!-- Left Panel -->

        <!-- Right Panel -->

        <div id="right-panel" class="right-panel">

            <!-- Header-->
            <%@include file="header.jsp" %>
            <!-- Header-->
<% 
if(msg!=null)
{
            %>
            <div id="success" class="<%=d%>"><i class="<%=fa%>"></i>
     <%=msg%> </div>
     <%
}
  %>  
            <div class="content mt-3">
                <div class="animated fadeIn">


                    <div class="row">
                        <div class="col-lg-12">
                            <div class="card">
                                <div class="card-header">
                                    <strong class="card-title">Advance Payment </strong>
                                </div>
                                <div class="card-body" >
                                    <!-- Credit Card -->
                                    <div id="pay-invoice">
                                        <div class="card-body">
                                            <div class="card-title">
                                                <h3 class="text-center"></h3>
                                            </div>
                                            <hr>
                                            <form id="form" action="<%= application.getContextPath() %>/AddAdvancePayment" method="get" >
                                            <div class="row form-group">
                                                <div class="col col-md-2"><label for="date" class=" form-control-label">Date Of Advance Payment</label></div>
                                                    <div class="col-12 col-md-3"><input type="date" id="datepicker" name="datepicker" class="form-control" required></div>
                                            </div>
                                            <div class="row form-group">
                                                    <div class="col col-md-2"><label for="securitypicker" class=" form-control-label">Security Person</label></div>
                                                    <div class="col-12 col-md-3"><select id="securitypicker" name="securitypicker" multiple="">                                                                    
                                                            </select></div>
                                                 </div>
                                            <div class="row form-group">
                                                <div class="col col-md-2"><label for="date" class=" form-control-label">Amount</label></div>
                                                    <div class="col-12 col-md-3"><input type="number" id="amount" name="amount" class="form-control" required></div>
                                            </div>
                                            <div class="row form-group">
                                                <div class="col col-md-2"><input type="submit" id="submit" value="Add"></div>
                                            </div>
                                            
                                                </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </body>
  <%@include file="footerfiles.jsp" %>
</html>