
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js" lang="">
    <head>
        <%@include file="headerfiles.jsp" %>
         <link rel="stylesheet" href="<%=application.getContextPath()%>/UserPannelDesign/files/jquery-ui.css">
        <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
  <script src="<%=application.getContextPath()%>/UserPannelDesign/files/jquery-1.12.4.js"></script>
  <script src="<%=application.getContextPath()%>/UserPannelDesign/files/foundation.min.js"></script>
  <script src="<%=application.getContextPath()%>/UserPannelDesign/files/foundation.js"></script>
   <script src="<%=application.getContextPath()%>/UserPannelDesign/files/jquery.dataTables.min.js"> </script>
   <script src="<%=application.getContextPath()%>/UserPannelDesign/files/jquery-ui.js"></script>
   <link rel="stylesheet" href="<%=application.getContextPath()%>/UserPannelDesign/files/jquery-ui.css">
   <link rel="stylesheet" href="<%=application.getContextPath()%>/UserPannelDesign/css/style.css">
<!--   <link rel="stylesheet" href="<%=application.getContextPath()%>/UserPannelDesign/files/foundation.min.css">
   
   <link rel="stylesheet" href="<%=application.getContextPath()%>/UserPannelDesign/files/foundation.css">-->
  <link href="<%=application.getContextPath()%>/UserPannelDesign/files/jquery.dataTables.min.css" rel="stylesheet">
    </head>
    <script>
        var getDaysInMonth = function(month,year) {
  return new Date(year, month, 0).getDate();
};
               $(document).ready(function(){
            
           n=$('#clientpicker').val();
                $.ajax({
                  url:'<%=application.getContextPath()%>/SetClientServlet',
                  data:{'client':n},
                  success:function(res) 
                  {
                       
                           var obj = JSON.parse(res);
                           $('#hidden').css("visibility","visible");
                           $('#clientpicker').empty();
                           $('#clientpicker').append('<option value="Not Selected"> Select Client</option>');
                           for(i=0;i<obj.length;i++)
                           {
                               var obj1 = obj[i];
                               
                               $('#clientpicker').append('<option value="'+obj1.cid+'">'+obj1.cname+'</option>');
                           }
                       
                  },
                  error:function(xhr,res)
                  {
                      alert(xhr.status);
                  }
           });
           var count=1;
        var type = '<select name="type'+count+'" class="type"><option value="EX-SERVICEMEN">EX-SERVICEMEN</option><option value="CIVILIAN TRAINED">CIVILIAN TRAINED</option></select>';
        var post = '<select name="post'+count+'" class="post"><option value="Security Guard">Security Guard</option><option value="Gunmen">Gunmen</option><option value="Body Guard">Body Guard</option><option value="Security Officer">Security Officer</option><option value="Security Supervisor">Security Supervisor</option><option value="Lady Searcher">Lady Searcher</option></select>';
        var no = '<input type="number" name="no'+count+'" class="no">';
        var wage = '<input type="text" name="wage'+count+'" id="wage">';
        var btn = '<input type="button" class="plus" value="+">';
        var bt = '<input type="button" class="minus" value="-">';
        var row='<tr><td><input type="hidden" id="days" name="days"><input type="hidden" id="qtype" name="qtype"><input type="hidden" name="client" id="client"><input id="hi" name="hi" type="hidden">'+type+'</td><td>'+post+'</td><td>'+no+'</td><td>'+wage+'</td><td>'+btn+'</td></tr>';
        var nerow = '<tr><td>'+type+'</td><td>'+post+'</td><td>'+no+'</td><td>'+wage+'</td><td>'+btn+'&nbsp;&nbsp;'+bt+'</td></tr>';
        $(document).on('click','.plus',function(){
        count++;   
        $('#bo').append('<tr><td><select name="type'+count+'" class="type"><option value="EX-SERVICEMEN">EX-SERVICEMEN</option><option value="CIVILIAN TRAINED">CIVILIAN TRAINED</option></select></td><td>\n\
<select name="post'+count+'" class="post"><option value="Security Guard">Security Guard</option><option value="Gunmen">Gunmen</option><option value="Body Guard">Body Guard</option><option value="Security Officer">Security Officer</option><option value="Security Supervisor">Security Supervisor</option><option value="Lady Searcher">Lady Searcher</option></select></td><td>\n\
<input type="number" name="no'+count+'" class="no"></td><td><input type="text" name="wage'+count+'" id="wage"></td>\n\
<td>'+btn+'&nbsp;&nbsp;'+bt+'</td>'); 
           
        });
        $(document).on('click','.minus',function(){
           $(this).parent().parent().remove();
        });
        $('#clientpicker').on('change',function(){
               $('#bo').empty();
               if($('#clientpicker').val()==="Not Selected")
                alert('Please Select Client');   
                else
               $('#bo').append(row);
           });
           $('#send').on('click',function(){
//             
                if($('#clientpicker').val()!=="Not Selected")
                {
                    $('#client').val($('#clientpicker').val());
                    $('#hi').val(count);
                    $('#qtype').val($('#re').val());
                    d = new Date($('#datepicker').val());
                    var m = d.getMonth()+1;
                    var y = d.getFullYear();
                    var days = getDaysInMonth(m,y);
                    $('#days').val(days);
                    $('#fom').submit();
                }
                else
                {
                    alert("Atleast Select One Record");
                }
            });
            $("#success").delay(5000).fadeOut();
           });
           </script>
           <style>
               #hidden{
                   visibility:hidden;
               }
               </style>
           <body>
               <% String msg = request.getParameter("msg");
                String d = request.getParameter("div");
                String fa = request.getParameter("fa");
                    %>
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
                                    <strong class="card-title">Client Quotation</strong>
                                </div>
                                <div class="card-body" >
                                    <!-- Credit Card -->
                                    <div id="pay-invoice">
                                        <div class="card-body">
                                            <div class="card-title">
                                                <h3 class="text-center"></h3>
                                            </div>
                                            <hr>
                                            <form id="fom" action="<%=application.getContextPath()%>/AddQuotation" type="post">
                                            <div class="row form-group">
                                                 <div class="col col-md-3"><label for="date" class=" form-control-label">Date</label></div>
                                                 <div class="col-12 col-md-9"><input type="date" id="datepicker" name="datepicker" required=""></div>
                                            </div>
                                            <div class="row form-group">
                                                 <div class="col col-md-3"><label for="date" class=" form-control-label">Status</label></div>
                                                 <div class="col-12 col-md-9"><select id="status" name="status"><option value="1">Add</option><option value="0">Remove</option></select></div>
                                            </div>
                                            <div class="row form-group">
                                                 <div class="col col-md-3"><label for="date" class=" form-control-label">To</label></div>
                                                 <div class="col-12 col-md-9"><select id="clientpicker">
                                                                    
                                                            </select></div>
                                            </div>
                                            <div class="row form-group" id="hidden">
                                                
                                                <table class="table">
                                                    <thead>
                                                    <th>TYPE</th>
                                                    <th>POST</th>
                                                    <th>NO OF PERSONS</th>
                                                    <th>WAGES(Per Month)</th>
                                                    <th></th>
                                                    </thead>
                                                    <tbody id="bo">
                                                    </tbody>
                                                    <tfoot>
                                                    <th colspan="5"><input type="button" value="Add Requirement" id="send"></th>
                                                    </tfoot>
                                                </table>
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
            
            <%@include file="footerfiles.jsp" %>
           </body>
</html>