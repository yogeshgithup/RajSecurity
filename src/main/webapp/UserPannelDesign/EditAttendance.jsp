

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js" lang="">
    <head>
     
        <%@include file="headerfiles.jsp" %>    
    <% 
       String m = request.getParameter("month");
       String y = request.getParameter("year");
       String id = request.getParameter("id");
    %>
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
            function getMonth(m)
            {
              if(parseInt(m)===1)
              return "January";
              if(parseInt(m)===2)
              return "February";
              if(parseInt(m)===3)
              return "March";
          if(parseInt(m)===4)
              return "April";
          if(parseInt(m)===5)
              return "May";
          if(parseInt(m)===6)
              return "June";
          if(parseInt(m)===7)
              return "July";
          if(parseInt(m)===8)
              return "August";
          if(parseInt(m)===9)
              return "September";
          if(parseInt(m)===10)
              return "October";
          if(parseInt(m)===11)
              return "November";
          if(parseInt(m)===12)
              return "December";
            };
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
            $(document).ready(function(){
                $('#disp').html(getMonth(<%=m%>));
                url = '<%=application.getContextPath()%>/SetAttendance?id=<%=id%>&month=<%=m%>&year=<%=y%>';
                $.ajax({
                   url:url,
                   success:function(res)
                   {
                       $('#head').empty();
                       $('#body').empty();
                       $('#foot').empty();
                       var obj = JSON.parse(res);
                       for(i=0;i<obj.length;i++)
                       {
                           var obj1 = obj[i];
                           $('#name').html(obj1.fname+" "+obj1.lname);
                           var objatt=obj1.attendance;
                           var day = getDaysInMonth(<%=m%>,<%=y%>);
                           
                           for(j=1;j<=day;j++)
                           {
                               atv=getAttendance(j,objatt);
                               if(parseInt(atv)!==1)
                               {
                                   $("#head").append("<th>"+j+"</th>");
                               }
                           }
                           $('#body').append("<tr>");
                           for(k=1;k<=day;k++)
                           {
                               atv=getAttendance(k,objatt);
                               if(parseInt(atv)===8)
                                   $('#body').append('<td><select for="'+obj1.id+'" id="'+k+'" class="ATT"><option value=8 selected>P</option><option value=16>2P</option><option value=0>AB</option></select></td>');
                               if(parseInt(atv)===16)
                                   $('#body').append('<td><select for="'+obj1.id+'" id="'+k+'" class="ATT"><option value=8 >P</option><option value=16 selected>2P</option><option value=0>AB</option></select></td>');
                               if(parseInt(atv)===0)
                                   $('#body').append('<td><select for="'+obj1.id+'" id="'+k+'" class="ATT"><option value=8 >P</option><option value=16>2P</option><option value=0 selected>AB</option></select></td>');
                           }
                           $('#body').append("</tr>");
                           $('#foot').html('<th colspan="2"><input type="button" id="upp" value="Update"></th>');
                       }
                           
                   },
                   error:function(xhr,res)
                   {
                       alert("error "+xhr.status);
                   }
                });
                $(document).on('click','#upp',function(){
                    var obj = $('.ATT');
              var json = "[";
             $.each(obj,function(index,value){
                 if($(this).val()!=="-")
                 json = json + '{"id":"'+$(this).attr('for')+'","value":"'+$(this).val()+'","day":"'+$(this).attr('id')+'","month":"<%=m%>","year":"<%=y%>"},';
             }) ;
             if(json!=="[")
              json=json.substr(0,json.length-1);
              
              json = json + "]";
              location.href='<%=application.getContextPath()%>/UpdateAttendance?json='+json;
                });
            });
            </script>
    <body>
        <!-- Left Panel -->

        <!-- /#left-panel -->
        <%@include file="leftpanel.jsp" %>
        <!-- Left Panel -->

        <!-- Right Panel -->

        <div id="right-panel" class="right-panel">

            <!-- Header-->
            <%@include file="header.jsp" %>
      
<div class="content mt-3">
                <div class="animated fadeIn">


                    <div id="add" class="row">
                        <div class="col-lg-10">
                            <div class="card">
                                <div class="card-header">
                                    <strong class="card-title">Edit Attendance </strong>
                                </div>
                                <div class="card-body" >
                                    <!-- Credit Card -->
                                    <div id="pay-invoice">
                                        <div class="card-body">
                                            <div class="card-title">
                                                <h3 class="text-center"></h3>
                                            </div>
                                            <div  style="overflow-x:auto;">
                                            <center>
                                                <label id="name"></label><br>
                                                                                            <hr>

                                                <label id="disp"></label>
                                                <table class="table">
                                                    <thead id="head">
                                                    </thead>
                                                    <tbody id="body">
                                                    </tbody>
                                                    <tfoot id="foot">
                                                    </tfoot>
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
