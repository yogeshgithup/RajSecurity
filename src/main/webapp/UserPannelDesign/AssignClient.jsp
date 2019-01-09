<%-- 
    Document   : AssignClient
    Created on : Mar 20, 2018, 1:33:51 PM
    Author     : Yogesh Chawla
--%>

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
   <link rel="stylesheet" href="<%=application.getContextPath()%>/UserPannelDesign/files/alert.sass">
  <link href="<%=application.getContextPath()%>/UserPannelDesign/files/jquery.dataTables.min.css" rel="stylesheet">
    </head>
    <script>
            var getDaysInMonth = function(month,year) {
  return new Date(year, month, 0).getDate();
};
    $(document).ready(function(){
       var i=0;
        <% String msg = request.getParameter("msg");
                String d = request.getParameter("div");
                String fa = request.getParameter("fa");
                    %>
        
        $('#datepicker').on('change',function()
        {
                n=$('#clientpicker').val();
                $.ajax({
                  url:'<%=application.getContextPath()%>/SetClientServlet',
                  data:{'client':n},
                  success:function(res) 
                  {
                       
                           var obj = JSON.parse(res);
//                         $('#hidden').css("visibility","visible");
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
                if ( $.fn.dataTable.isDataTable( '#example1' ) )

                        {

                            table = $('#example1').DataTable();

                            table.destroy();

                        }
                        if ( $.fn.dataTable.isDataTable( '#example' ) )

                        {

                            table = $('#example').DataTable();

                            table.destroy();

                        }
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
                if ( $.fn.dataTable.isDataTable( '#example1' ) )

                        {

                            table = $('#example1').DataTable();

                            table.destroy();

                        }
                        if ( $.fn.dataTable.isDataTable( '#example' ) )

                        {

                            table = $('#example').DataTable();

                            table.destroy();

                        }
                $('#hidden').css("visibility","visible");
        });
        $('#success').delay(3000).fadeOut();
        $('#sitepicker').on('change',function(){
            if ( $.fn.dataTable.isDataTable( '#example1' ) )

                        {

                            table = $('#example1').DataTable();

                            table.destroy();

                        }
                        if ( $.fn.dataTable.isDataTable( '#example' ) )

                        {

                            table = $('#example').DataTable();

                            table.destroy();

                        }
        $('#hidden2').css("visibility","visible"); 
        var n = $('#clientpicker').val();
        var g = $('#sitepicker').val();
         d = new Date($('#datepicker').val());
        m = d.getMonth()+1;
        
        $('#example1').dataTable({
         "autoWidth": true,
        "deferRender": true,
         "info": false,
         "searching":false,
         "sort":false,
         "paging": false,
                               "ajax": '<%=application.getContextPath()%>/viewRequireJson?id='+n+'&sid='+g+'&mon='+m,
                               
                              "columns": [

                                   { "data": "post" },

                                   { "data": "type" },

                                   { "data": "no" }
                                   
                            ],
                            "fnRowCallback": function( nRow, aData, iDisplayIndex, iDisplayIndexFull )

                            {
                              
                                $('td:eq(0)', nRow).html(aData['type']); // where 4 is the zero-origin visible column in the HTML
                                $('td:eq(1)', nRow).html(aData['post']);
                                $('td:eq(2)', nRow).html(aData['no']);
                                 return nRow;
                            }
                    });
        $('#hidden1').css("visibility","visible");
                        $('#example').dataTable({
                            "autoWidth": true,
        "deferRender": true,
         "info": false,
         "searching":true,
         "sort":true,
         "paging": false,
                               "ajax": '<%=application.getContextPath()%>/viewAssignJson',

                              "columns": [

                                   { "data": "fname" },

                                   { "data": "mname" },

                                   { "data": "lname" },
                                   
                                   {"data" : "id"},
                                    
                                   {"data" : "post"},
                                   
                                   {"data" : "type"}
                                   
                                   
                            ],
                            "fnRowCallback": function( nRow, aData, iDisplayIndex, iDisplayIndexFull )

                            {

                                
                                
                                var name = aData['fname']+" "+aData['mname']+" "+aData['lname'];
                                 var id = aData['id'];
                                 
                                                              
                                $('td:eq(0)', nRow).html(name); // where 4 is the zero-origin visible column in the HTML
                                $('td:eq(1)', nRow).html(aData['no']);
                                $('td:eq(2)', nRow).html(aData['type']);
                                $('td:eq(3)',nRow).html(aData['post']);
                                $('td:eq(4)',nRow).html("<input type='number' for='"+aData['type']+" "+aData['post']+"' id='"+aData['id']+"' name='pda' class='pda'>");
                                $('td:eq(5)',nRow).html("<input type='checkbox'  id='chk' class='chk' name='chk' value='"+aData['id']+"'>");
                                var cid=aData['id'];                     
                                 return nRow;

                            }
                    });
                             });
                             $(document).on('change','#chk.chk',function(){
                                var a,d;
                                 var subarr = new Array();
                                if($(this).is(":checked"))
                                 {
                                      a = $(this).attr('value');
                                     
                                      d = $("#"+a).val();
                                     
                                       var b = $("#"+a).attr('for');
                                     
                                 }
                                 else
                                 {
                                     a="";
                                     d="";
                                 }
//                                 json = json + "{'id':'"+a+"','sal':'"+d+"'},";
//                                 alert(a+" "+d);
                               
                                i++;
                                
                             });
                             
                             $("#assign").on('click',function(){
                                var a,d; 
                                var json='[ ';
                                //json = json + "{'id':'"+a+"','sal':'"+d+"'},";
                                $.each($('input[type=checkbox]'),function(){
                                    if($(this).is(":checked"))
                                 {
                                      a = $(this).attr('value');
                                     
                                      d = $("#"+a).val();
                                      if(d!=="")
                                      json = json + '{"'+a+'":"'+d+'"},';
                                  else
                                      alert("Please Input Wages");
                                  }
                                 });
                                 var dd = new Date($('#datepicker').val());
                                 var mm = dd.getMonth()+1;
                                 var yy = dd.getFullYear();
                                 var da = getDaysInMonth(mm,yy);
                                 json=json.substr(0,json.length-1);
                                 json=json+"]";
                                 if(json==="[]")
                                 {
                                     alert("Please Select Atleast One Security Person");
                                 }
                                 else
                                 {
                                 location.href="<%=application.getContextPath()%>/MakeAssignmentServlet?d="+json+"&site="+$('#sitepicker').val()+"&date="+$('#datepicker').val()+"&day="+da;
                                 
                                 }
                             });
    });
    </script>
    <style>
        #hidden{
            visibility: hidden;
        }
        #hidden1{
            visibility: hidden;
           
        }
        #hidden2{
            visibility: hidden;
        }
        
        #example{
             border: 1px solid red;
             padding: 25px;
        }
        #example1{
             border: 1px solid red;
             padding: 25px;
        }
        .bs-example{
		margin: 20px;
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
  %>    
            <div class="content mt-3">
                <div class="animated fadeIn">


                    <div class="row">
                        <div class="col-lg-12">
                            <div class="card">
                                <div class="card-header">
                                    <strong class="card-title">Assignment</strong>
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
                                                <div class="col col-md-2"><label for="date" class=" form-control-label">Date Of Assignment</label></div>
                                                    <div class="col-12 col-md-3"><input type="date" id="datepicker" name="datepicker" class="form-control" required></div>
                                                    <div class="col col-md-1"><label for="client" class=" form-control-label">Client</label></div>
                                                    <div class="col-12 col-md-3"><select id="clientpicker">
                                                          
                                                            </select></div>
                                                </div>
                                            <div id="hidden" class="row form-group">
                                                <div class="col col-md-2"><label for="date" class=" form-control-label">Client Site</label></div>
                                                    <div class="col-12 col-md-3"><select id="sitepicker">
                                                            <option value="Not Selected">Select Client Site</option>
                                                            </select></div>
                                            </div>
                                         <div id="hidden2" class="row form-group">
                                             <center> <h6 style="color: purple"><strong>Requirement Details</strong></h6>
                                                 <br>
                                                 <table id="example1" class="row-border	" cellspacing="0" width="100%">
        <thead>
            <tr>
                
                <th>TYPE</th>
                <th>POST</th>
                <th>No Of Person Required</th>
                
            </tr>
        </thead>
        
                                            <tbody>
                                            </tbody>
                                            </table>
                                                 </center>
                                            </div>
                                            <br>
                                            <br>
                                           
                                                <br>
                                            <div  class="row form-group" id="hidden1">
                                            <center> <h6 style="color: purple"><strong>Assign Security Person</strong></h6>
                                            <br>
                                            
                                                <table  id="example" class="display" cellspacing="0" >
        <thead>
            <tr>
                
                <th>Name</th>
                <th>Mobile No</th>
                <th>Type</th>
                <th>Post</th>
                <th>Wage(Actual)</th>
                <th>Add</th>
            </tr>
        </thead>
        <tfoot>
        <th colspan="6"><input type="button" value="Assign" id="assign" ></th>
        </tfoot>
                                            <tbody>
                                            </tbody>
                                            </table>
                                            </center>
                                            </div>
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
   
