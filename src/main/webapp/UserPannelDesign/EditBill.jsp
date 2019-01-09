<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js" lang="">
    <head>
        
        <%@include file="headerfiles.jsp" %>
        <script src="<%=application.getContextPath()%>/UserPannelDesign/files/jquery-1.12.4.js"></script>
   <script src="<%=application.getContextPath()%>/UserPannelDesign/files/jquery.dataTables.min.js"> </script>
   <script src="<%=application.getContextPath()%>/UserPannelDesign/files/jquery-ui.js"></script>
   <link rel="stylesheet" href="<%=application.getContextPath()%>/UserPannelDesign/files/jquery-ui.css">
   <link rel="stylesheet" href="<%=application.getContextPath()%>/UserPannelDesign/css/style.css">
  <link href="<%=application.getContextPath()%>/UserPannelDesign/files/jquery.dataTables.min.css" rel="stylesheet">
    </head>
    <script>
        $(document).ready(function(){
            $('#billdetails').hide();
            $('#billno').on('change',function(e){
               $('#billdetails').hide();
            });
            $(document).on('click','#btnedit',function(e){
                var billno = $('#billno').val();
                var days = $('#days').html();
                var amount = $('#amt').html();
                $('#btn').empty();
                var dd = $('#date').html();
                
                $('#edd').html('<input class="btn btn-info" type="button" id="btnsave" value="Save">');
                $('#amt').html('<input type="text" name="newamt" id="newamt" class="form-control" value="'+amount+'">');
                $('#date').html('<input type="text" id="newdate" name="newdate" value="'+dd+'">');
                $('#days').html('<input type="text" name="newdays" id="newdays" class="form-control" value="0"><br><select id="newval" name="newval"><option value=0>Remove</option><option value=1>Add</option></select>');
               
            });
            $(document).on('click','#btnsucess',function(e){
               var billno = $('#billno').val();
               var id = $('#hid').val();
               var date = $('#date0').html();
               if($('#gstchk').is(':checked'))
               {
                   window.open("<%=application.getContextPath()%>/PrintServlet?bill="+billno+"&id="+id+"&date="+date+"&gstt=yes");
               }
               else
               {
                   window.open("<%=application.getContextPath()%>/PrintServlet?bill="+billno+"&id="+id+"&date="+date+"&gstt=no");
               }
              // window.open("<%=application.getContextPath()%>/PrintServlet?bill="+billno+"&id="+id+"&date="+date);
               location.reload();
            });
            
            $(document).on('click','#btnsave',function(e){
                e.preventDefault();
                var newamt = $('#newamt').val();
                
                var newdays = $('#newdays').val();
                var qid = $('#qid').val();
                var billno = $('#billno').val();
                var da = $('#newdate').val();
                var oamt = $('#oamt').val();
                var status = $('#newval').val();
                
                var id = $('#hid').val();
                
                var ur = $('#billid').val();
               
                $.ajax({
                   url:'<%=application.getContextPath()%>/SaveBillSer',
                   data:{oamt:oamt,amt:newamt,day:newdays,bill:billno,date:da,status:status,id:id},
                   type:'post',
                   success:function(data)
                    {
                        
                        $('#edd'+ur).html('<a class="btn btn-info" for="'+qid+'" role="button" id="btnedit" href="'+ur+'" >Edit</a>'); 
                        $('#days'+ur).html(data);
                        $('#amt'+ur).html(newamt);
                        $('#date'+ur).html(da);
                        $('#btn').html('<button type="button" id="btnsucess" class="btn btn-success">Print Bill</button>');
                    },
                   error:function(data)
                    {
                        alert(data);
                    }
                });
            });
            $('#btndel').on('click',function(e){
                var id = $('#bn').html();
                var cid = $('#hid').val();
                var dd = $('#date').html();
                if(id===null)
                {
                    alert("Save The Bill First");
                }
                else
                {
                    
                $.ajax({
                    url:'<%=application.getContextPath()%>/SerDelBill?id='+id+'&cid='+cid+'&date='+dd+'&amt=',
                    success:function(e){
                        location.reload();
                    },
                    error:function(e)
                    {
                        alert("Could Not Delete The Record Try Again After SomeTime");
                    }
                });
                }
            });
            $('body').on('focus',"#newdate", function(){
    $(this).datepicker();
});
            $('#btngo').on('click',function(e){
                if($('#billno').val()!=="")
                
                {
                $.ajax({
                   url:'<%=application.getContextPath()%>/EditBillServlet?billno='+$('#billno').val(),
                   success:function(e)
                    {
                        $('#bn').empty();
                        $('#days').empty();
                        $('#amt').empty();
                        $('#date').empty();
                       
                        var js=JSON.parse(e);
                        var i;
                        
                        for(i=0;i<js.length;i++)
                        {
                            $('#hid').val(js[i].id);
                                
                                var app =               '<tr>'+
                                                        '<td id="bn'+i+'">'+js[i].billno+'</td>'+
                                                        '<td id="days'+i+'">'+js[i].days+'</td>'+
                                                        '<td id="amt'+i+'">'+js[i].wage+'</td>'+
                                                        '<td id="date'+i+'">'+js[i].date+'</td>'+
                                                        '<td id="gstyn"><input type="checkbox" id="gstchk"></td>'+
                                                        '<td id="edd'+i+'"><a role="button" for="'+js[i].qid+'" href="'+i+'" class="btn btn-info" type="button" id="btnedit" value="Edit">Edit</td>'+
                                                        '<td id="del'+i+'"><a role="button" href="<%=request.getContextPath()%>/SerDelBill?id='+js[i].qid+'&cid='+js[i].id+'&no='+js[i].no+'&amt='+js[i].wage+'&date='+js[i].date+'&bill='+js[i].billno+'" class="btn btn-link" id="btndel" >Delete</a></td>'+
                                                        '</tr>';
                                                $('#tbody').append(app);
                        }
                         $('#btn').html('<button type="button" id="btnsucess" class="btn btn-success">Print Bill</button>');
                        $('#billdetails').show();
                    }
                });
                }
                else
                {
                    alert("Please Input A Number First");
                }
            });
            $(document).on('click','#btndel',function(e){
                if(!confirm('Are you sure you want to delete the Requirement'))
                    e.preventDefault();
                   
            });
            $(document).on('click','#btnedit',function(e){
                e.preventDefault();
                var url=$(this).attr("href");
                $('#billid').val(url);
                var qid = $(this).attr("for");
                var billno = $('#bn'+url).html();
                var days = $('#days'+url).html();
                var amount = $('#amt'+url).html();
                
                $('#oamt').val(amount);
                $('#qid').val(qid);
                $('#btn').empty();
                var dd = $('#date'+url).html();
                $('#edd'+url).html('<a role="button" class="btn btn-info" for="'+qid+'" id="btnsave" href="'+url+'" >Save</a>');
                $('#amt'+url).html('<input type="text" name="newamt" id="newamt" class="form-control" value="'+amount+'">');
                $('#date'+url).html('<input type="text" id="newdate" name="newdate" value="'+dd+'">');
                $('#days'+url).html('<input type="text" name="newdays" id="newdays" class="form-control" value="0"><br><select id="newval" name="newval"><option value=0>Remove</option><option value=1>Add</option></select>');
               
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
            <!-- Header-->

            <div class="content mt-3">
                <div class="animated fadeIn">


                    <div class="row">
                        <div class="col-lg-12">
                            <div class="card">
                                <div class="card-header">
                                    <strong class="card-title">Edit Bill</strong>
                                </div>
                                <div class="card-body" >
                                    <div class="card-title">
                                                <h3 class="text-center"></h3>
                                            </div>
                                            <hr>
                                    <!-- Credit Card -->
                                    <div id="pay-invoice">
                                       
                                        <div class="row form-group">
                                                <div class="col col-md-2"><label for="date" class=" form-control-label">Enter Bill No</label></div>
                                                <div class="col-12 col-md-3"><input type="text" id="billno" name="billno" placeholder="Bill No" class="form-control" required></div>
                                                <div class="col-12 col-md-1"><button type="button" id="btngo" name="btngo" class="btn btn-info">Go</button></div>           
                                        </div>
                                        <div  id="billdetails">
                                            <table class="table">
                                                <thead>
                                                    <th>Bill No</th>
                                                    <th>No Of Days</th>
                                                    <th>Amount</th>
                                                    <th>Date</th>
                                                    <th>Gst</th>
                                                    <th>Edit</th>
                                                    <th>Delete</th>
                                                </thead>
                                                <tbody id="tbody">
                                                    
                                                </tbody>
                                                <tfoot>
                                                    <th span="6" id="btn"></th>
                                                </tfoot>
                                            </table>
                                            <input type="hidden" id="hid" name="hid">
                                            <input type="hidden" id="billid" name="hid">
                                            <input type="hidden" id="qid" name="qid">
                                            <input type="hidden" id="oamt" name="qid">
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
