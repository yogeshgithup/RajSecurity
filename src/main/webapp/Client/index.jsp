<%-- 
    Document   : index
    Created on : Apr 12, 2018, 10:27:49 AM
    Author     : Yogesh Chawla
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
  <link rel="stylesheet" href="<%=application.getContextPath()%>/UserPannelDesign/files/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
   <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
  <script src="<%=application.getContextPath()%>/UserPannelDesign/files/jquery-1.12.4.js"></script>
   <script src="<%=application.getContextPath()%>/UserPannelDesign/files/jquery.dataTables.min.js"> </script>
   <script src="<%=application.getContextPath()%>/UserPannelDesign/files/jquery-ui.js"></script>
   <link rel="stylesheet" href="<%=application.getContextPath()%>/UserPannelDesign/files/jquery-ui.css">
   <link rel="stylesheet" href="<%=application.getContextPath()%>/UserPannelDesign/css/style.css">
  <link href="<%=application.getContextPath()%>/UserPannelDesign/files/jquery.dataTables.min.css" rel="stylesheet">

        <%
            if(session.getAttribute("client")==null)
            response.sendRedirect("/RajSecurityForce/UserPannelDesign/");
        %>
        <script>
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
            function myfunction()
            {
                location.href="/RajSecurityForce/UserPannelDesign/ChangePassword.jsp";
            }
            $(document).ready(function(){
                $('#assign').on('click',function(e){
                   e.preventDefault();
                   $.ajax({
                       url:'/RajSecurityForce/GetAssignedPerson?id=<%=session.getAttribute("cid")%>',
                       success:function(res)
                        {
                            $('#th').empty();
                               $('#td').empty();
                               $('#tr').empty();
                               $('#th').append("<th>Name</th><th>Contact</th>");
                           var obj = JSON.parse(res);
                           for(i=0;i<obj.length;i++)
                           {
                               var obj1=obj[i];
                               $('#td').append('<tr><td>'+obj1.name+'</td><td>'+obj1.no+'</td></tr>');
                           }
                               
                        },
                        error:function(xhr,res)
                        {
                            alert(xhr.status);
                        }
                   
                   });
                });
                $('#attendance').on('click',function(e){
                   e.preventDefault();
                   $.ajax({
                       url:'/RajSecurityForce/GetAttendanceClient?id=<%=session.getAttribute("cid")%>',
                       success:function(res)
                        {
                            $('#th').empty();
                               $('#td').empty();
                               $('#tr').empty();
                               $('#th').append("<th>Name&bsol;Day</th>");
                               
                            var obj = JSON.parse(res);
                            for(j=1;j<=31;j++)
                           {                               
                               
                                   $("#th").append("<th>"+j+"</th>");

                           }
                            for(i=0;i<obj.length;i++)
                            {
                                var obj1=obj[i];
                                objatt=obj1.attendance;
                                $('#td').append("<tr>");
                                $('#td').append("<td>"+obj1.name+"</td>");
                                for(j=1;j<=31;j++)
                        {
                            
                            aatvalue=getAttendance(j,objatt);
                            if(aatvalue===1)
                            $('#td').append('<td></td>');
                            if(parseInt(aatvalue)===8)
                            $('#td').append('<td>P</td>');
                            if(parseInt(aatvalue)===16)
                            $('#td').append('<td>2P</td>');
                            if(parseInt(aatvalue)===0)
                            {
                            $('#td').append('<td>AB</td>');
                            }
                            
                        }
                        $('#td').append("</tr>");
                            }
                                
                        },
                        error:function()
                        {
                            
                        }                        
                   });
                });
            });
        </script>
        <style>
            td{
                border: 1px solid #cccccc;
            }
            th{
                border: 1px solid #cccccc;
            }
            </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>This Is Client Side!</h1>
        <input type="button" value="Change Password" onclick="myfunction()">
        <br>
        <br>
        <a id="assign" href="">Get Assigned Security Persons</a>
        <br>
        <br>
        <a id="attendance" href="">Get Assigned Security Persons Attendance</a>
        <br>
        <br>
        <br>
        <table id="tbl" style="border: 1px solid; " cellspacing="10" class="table" >
            <thead id="th"></thead>
            <tbody id="td"></tbody>
            <tfoot id="tf"></tfoot>
        </table>
    </body>
</html>
