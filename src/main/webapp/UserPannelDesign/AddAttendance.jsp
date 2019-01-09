<%-- 
    Document   : AddAttendance
    Created on : Mar 17, 2018, 1:54:51 PM
    Author     : Yogesh Chawla
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js" lang="">
    <head>
     
        <%@include file="headerfiles.jsp" %>
       <link rel="stylesheet" href="<%=application.getContextPath()%>/UserPannelDesign/files/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
   <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
  <script src="<%=application.getContextPath()%>/UserPannelDesign/files/jquery-1.12.4.js"></script>
   <script src="<%=application.getContextPath()%>/UserPannelDesign/files/jquery.dataTables.min.js"> </script>
   <script src="<%=application.getContextPath()%>/UserPannelDesign/files/jquery-ui.js"></script>
   <link rel="stylesheet" href="<%=application.getContextPath()%>/UserPannelDesign/files/jquery-ui.css">
   <link rel="stylesheet" href="<%=application.getContextPath()%>/UserPannelDesign/css/style.css">
  <link href="<%=application.getContextPath()%>/UserPannelDesign/files/jquery.dataTables.min.css" rel="stylesheet">
  </head>
  <script>
             $(document).ready(function()
            {
                var values=[];
//               $( function() {
//        $( "#datepicker" ).datepicker({
//        changeMonth: true,
//            changeYear: true,
//            maxDate: "-18Y" 
//        });
//    });   <% String msg = request.getParameter("msg");
                String d = request.getParameter("div");
                String fa = request.getParameter("fa");
                    %>
         $('#success').delay(3000).fadeOut();
                         $('#datepicker').on('change',function()
                {
                   
     
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
});
$('#sitepicker').on('change',function(){
    alert("changed");
    
    var cid = $('#sitepicker').val();
    $('#hidden1').css("visibility","visible");
    if ( $.fn.dataTable.isDataTable( '#example' ) )

                        {

                            table = $('#example').DataTable();

                            table.destroy();

                        }
    $('#example').dataTable({
                               "ordering": false,
         "paging": false,
         "info":false,
          "searching": false, "language": {
      "emptyTable": "No Details Found"
    },
                               "ajax": "<%=application.getContextPath()%>/AttendanceServlet?client="+cid,
                               
                              "columns": [

                                   { "data": "id" },

                                   { "data": "fname" },

                                   { "data": "mname" },
                                   
                                   {"data" : "lname"}
                                    

                                   
                                   
                            ],
                            "fnRowCallback": function( nRow, aData, iDisplayIndex, iDisplayIndexFull )

                            {

                                
                                var id = aData['id'];
                                var name = aData['fname']+" "+aData['mname']+" "+aData['lname'];
                                 
                                $('td:eq(0)', nRow).html(id); // where 4 is the zero-origin visible column in the HTML
                                $('td:eq(1)', nRow).html(name);
                                $('td:eq(2)',nRow).html("<input type='radio' class='"+aData['id']+"' name='"+aData['id']+"' value='8'> 1 Day <input type='radio' class='"+aData['id']+"' name='"+aData['id']+"' value='16'> 2 Day <input type='radio' class='"+aData['id']+"' name='"+aData['id']+"' value='0'>Absent</td>");
                                $('td:eq(3)',nRow).html("<input type='checkbox' value='"+aData['id']+"chk' name='attendance'></td>");
                                values.push(id);
                                return nRow;

                            }
                            
                        });
                        
});
$('#clientpicker').on('change',function()
        {
                g=$('#clientpicker').val();
                $.ajax({
                  url:'<%=application.getContextPath()%>/SetSiteServlet',
                  data:{'client':g},
                  success:function(res) 
                  {
                       
                           var obj = JSON.parse(res);
//                         $('#hidden').css("visibility","visible");
                           $('#sitepicker').empty();
                           $('#sitepicker').append('<option value="Not Selected"> Select Client Site</option>');
                           for(i=0;i<obj.length;i++)
                           {
                               var obj1 = obj[i];
                               
                               $('#sitepicker').append('<option value="'+obj1.cid+'">'+obj1.cname+'</option>');
                           }
                       
                  },
                  error:function(xhr,res)
                  {
                      alert(xhr.status);
                  }
                });
               $('#hidden3').css("visibility","visible");
        });
    $('#mark').on("click",function(){
        var js = '[';
       alert(values.length); 
       $.each(values,function(index,value)
       {
           js = js +  '{ "'+value+'" : "'+$("input[name='"+value+"']:checked").val()+'"},';
       });
       var date = $('#datepicker').val();
       js=js.substr(0,js.length-1);
       
       js = js+" ]";
        location.href="<%=application.getContextPath()%>/MakeAttendanceServlet?d="+js+"&date="+date+"&site="+$("#sitepicker").val()+"&client="+$("#clientpicker").val();
    });
    
   
                });

        </script>
        <style>
            #hidden{
                visibility: hidden;
            }
            #hidden3{
                visibility: hidden;
            }
            #hidden1{
                visibility: hidden;
            }
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
  %>          <div class="content mt-3">
                <div class="animated fadeIn">


                    <div id="add" class="row">
                        <div class="col-lg-12">
                            <div class="card">
                                <div class="card-header">
                                    <strong class="card-title">Attendance </strong>
                                </div>
                                <div class="card-body" >
                                    <!-- Credit Card -->
                                    <div id="pay-invoice">
                                        <div class="card-body">
                                            <div class="card-title">
                                                <h3 class="text-center"></h3>
                                            </div>
                                            <hr>

                                            

                                                <div class="row form-group">
                                                    <div class="col col-md-3"><label for="date" class=" form-control-label">Date</label></div>
                                                    <div class="col-12 col-md-9"><input type="date" id="datepicker" name="datepicker" class="form-control" required></div>
                                                </div>
                                                <div id="hidden" class="row form-group">
                                                    <div class="col col-md-2"><label for="date" class=" form-control-label">Client</label></div>
                                                            <div class="col-12 col-md-3"><select id="clientpicker">
                                                                    
                                                            </select></div>
                                                    <div id="hidden3">
                                                    <div class="col col-md-2"><label for="date" class=" form-control-label">Site</label></div>
                                                            <div class="col-12 col-md-3"><select id="sitepicker">
                                                                    
                                                            </select></div>
                                                 </div>
                                                 </div>
                                            
                                                 <div class="row form-group" id="hidden1">
                                                     <div class="col-md-auto">
                                            <table id="example" class="display" cellspacing="0" width="100%">
        <thead>
            <tr>
                
                <th>ID</th>
                <th>Name</th>
                <th>Present</th>
                <th>Submit</th>
            </tr>
        </thead>
        <tfoot>
        <th colspan="4"><input type="button" value="Mark" id="mark" name="mark"></th>
        </tfoot>
                                            <tbody>
                                            </tbody>
                                            </table>
                                                 </div>
                                        </div>
                                                
                                            
                                        </div>
                                    </div>

                                </div>
                            </div> <!-- .card -->

                        </div><!--/.col-->
                    </div>
                </div><!-- .animated -->
            </div><!-- .content -->
        </div><!-- /#right-panel -->

        <!-- Right Panel -->
        <%@include file="footerfiles.jsp" %>
    </body>
</html>
