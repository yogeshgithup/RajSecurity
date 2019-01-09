<%-- 
    Document   : NewAddAttendance
    Created on : Apr 3, 2018, 12:19:48 PM
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
      var getDaysInMonth = function(month,year) {
  return new Date(year, month, 0).getDate();
};
var names = [];
function getAttendance(day,obj)
{   atv=1;
    $.each(obj,function(k,v){
       if(parseInt(k)===day)
       {
           atv=v;
       }
    });
    return atv;
}
var y = null;
var day = null;
      $(document).ready(function(){
          $('#monthpicker').on('change',function(){
            var m = $('#monthpicker').val();
            var d = new Date();
             y = d.getFullYear();
            day = getDaysInMonth(m,y);
            if($('#monthpicker').val()!=="Not Selected")
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
            }
            else
            {
               // $('#att').empty();
                alert("Please Select A Month");
            }
            $('#bo').empty();
            
                
          });
          $('#clientpicker').on('change',function(){
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
          
          $('#sitepicker').on('change',function(){
              var d = new Date();
               client = $('#sitepicker').val();
                $.ajax({
                  url:'<%=application.getContextPath()%>/SetSiteServlet1?client='+client+'&year='+d.getFullYear()+'&month='+$('#monthpicker').val()+'&site='+$('#sitepicker').val(),
                  success:function(res) 
                  {
                      
                                                     $('#att').empty();
                                                     $('#bo').empty();
                                                     $('#att').append("<tr>");
                                $('#att').append("<th>Name&bsol;Date</th>");
                                $('#att').append("<th>Check</th>");
                                for(i=1;i<=day;i++)
                                {
                                    $('#att').append("<th>"+i+"</th>");
                                }
                                $('#att').append("<th>Action</th>");
                                $('#att').append("</tr>"); 
                        
                           var obj = JSON.parse(res);
                           
//                         $('#hidden').css("visibility","visible");
                           for(i=0;i<obj.length;i++)
                           {
                           
                               var obj1 = obj[i];
                                    $('#bo').append("<tr>");
                                    $('#bo').append("<td>"+obj1.fname+" "+obj1.mname+" "+obj1.lname+"</td>");
                                    $('#bo').append("<td><input type='checkbox' id='"+obj1.id+"' class='ch'></td>");
                                    arr = [];
                                    objatt=obj1.attendance;
                                    for(j=1;j<=day;j++)
                                    {
                                        aatvalue=getAttendance(j,objatt);
                                        if(aatvalue===1)
                                        $('#bo').append('<td><select id="'+j+'" class="ATT" for="'+obj1.id+'"><option value="-" selected>--</option><option value="8">P</option><option value="16">2P</option><option value="0">AB</option></select></td>');
                                        if(parseInt(aatvalue)===8)
                                        $('#bo').append('<td>P</td>');
                                        if(parseInt(aatvalue)===16)
                                        $('#bo').append('<td>2P</td>');
                                        if(parseInt(aatvalue)===0)
                                        $('#bo').append('<td>AB</td>');
                                    }
                                    $('#bo').append('<td><a href="" class="attedit" id="'+obj1.id+'">Edit</a></td>');
                                    $('#bo').append('</tr>');
                                    $('#foot').empty();
                                    $('#foot').append('<tr>');
                                    $('#foot').append('<th colspan="'+(day+2)+'"><input type="button" value="Mark" id="mark"> </th>');
                                    $('#foot').append('</tr>');
                
                           }
                                  
                  },
                  error:function(xhr,res)
                  {
                      alert(xhr.status);
                  }
                });
            
          });
          $(document).on('click','.ch',function(e){
             $(document).on('change','.ch',function(){
        if($(this).is(":checked"))
        {
            id = $(this).attr('id');
            var obj = $('.ATT');
            $.each(obj,function(index,value){
                 if($(this).attr('for')===id)
                 {
                    $(this).val(8);
                }
            });
        }
        });
          });
          $(document).on('click','.attedit',function(e){
            e.preventDefault(); 
            id = $(this).attr('id');
            m = $('#monthpicker').val();
            
            url = '<%=application.getContextPath()%>/UserPannelDesign/EditAttendance.jsp?id='+id+'&month='+m+'&year='+y;
            location.href=url;
          });
          $(document).on('click','#mark',function(){
              var obj = $('.ATT');
              var json = "[";
             $.each(obj,function(index,value){
                 if($(this).val()!=="-")
                 {
                 json = json + '{"id":"'+$(this).attr('for')+'","value":"'+$(this).val()+'","day":"'+$(this).attr('id')+'","site":"'+$('#sitepicker').val()+'","client":"'+$('#clientpicker').val()+'","month":"'+$('#monthpicker').val()+'","year":"'+y+'"},';
        }
        }) ;
             if(json!=="[")
              json=json.substr(0,json.length-1);
              
              json = json + "]";
              if(json!=="[]")
              {
              url="<%=application.getContextPath()%>/MakeAttendanceServlet";
              $.ajax({
                type:"post", 
                url:url,
                data:{"d":json},
                 success:function(res)
                 {
                     alert(res);
                     location.reload();
                     
                 },
                 error:function(xhr,res)
                 {
                     alert(xhr);
                 }
              });
          }
              else
                  alert("Please Select AtLeast One Record");
    });
    <% String msg = request.getParameter("msg");
                String d = request.getParameter("div");
                String fa = request.getParameter("fa");
                    %>
         $('#success').delay(3000).fadeOut();
      });
      
      </script>
      <style>
          table {
    border-collapse: collapse;
    border-spacing: 0;
    width: 100%;
    border: 1px solid #ddd;
}
th, td {
    text-align: left;
    padding: 8px;
    
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


                    <div id="add" class="row">
                        <div class="col-lg-auto">
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
<div class="col col-md-2"><label for="date" class=" form-control-label">Select Month</label></div>
                                                    <div class="col-12 col-md-3"><select id="monthpicker">
                                                            <option value="Not Selected">Select Month</option>
                                                            <option value="1">January</option>
                                                            <option value="2">February</option>
                                                            <option value="3">March</option>
                                                            <option value="4">April</option>
                                                            <option value="5">May</option>
                                                            <option value="6">June</option>
                                                            <option value="7">July</option>
                                                            <option value="8">August</option>
                                                            <option value="9">September</option>
                                                            <option value="10">October</option>
                                                            <option value="11">November</option>
                                                            <option value="12">December</option>
                                                            </select></div>                                                   </div>
                                        
                                        <div id="hidden" class="row form-group">
                                                    <div class="col col-md-2"><label for="date" class=" form-control-label">Client</label></div>
                                                            <div class="col-12 col-md-3"><select id="clientpicker">
                                                                    
                                                                </select></div></div>
                                                    <div id="hidden" class="row form-group">
                                                    
                                                    <div class="col col-md-2"><label for="date" class=" form-control-label">Site</label></div>
                                                            <div class="col-12 col-md-3"><select id="sitepicker">
                                                                    
                                                            </select></div>
                                                 
                                                 </div>
                                        <div >
                                            <table class="table">
                                                
                                                <thead id="att">
                                                </thead>
                                                <tbody id="bo"> 
                                                </tbody>
                                                <tfoot id="foot">
                                                    
                                                </tfoot>
                                            </table>
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


                                            