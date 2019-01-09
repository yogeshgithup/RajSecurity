<%@page import="javax.xml.ws.Response"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="operation.Operation"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.File"%>
<%@page import="data.BankDetails"%>
<%@page import="data.Address"%>
<%@page import="data.Experience"%>
<%@page import="data.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="data.SecurityPerson"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js" lang="">
    <head>
        <%
            
        %>
        <%@include file="headerfiles.jsp" %>
  <script src="<%=application.getContextPath()%>/UserPannelDesign/files/jquery-1.12.4.js"></script>
   <script src="<%=application.getContextPath()%>/UserPannelDesign/files/jquery.dataTables.min.js"> </script>
    <script src="<%=application.getContextPath()%>/UserPannelDesign/files/jquery-ui.js"></script>
   <link rel="stylesheet" href="<%=application.getContextPath()%>/UserPannelDesign/files/jquery-ui.css">
   <link rel="stylesheet" href="<%=application.getContextPath()%>/UserPannelDesign/css/style.css">
  <link href="<%=application.getContextPath()%>/UserPannelDesign/files/jquery.dataTables.min.css" rel="stylesheet">
    </head>
    <script>
        var id,nation,s;
     var files,d,files;
     
      function editRow ( oTable, nRow )
			{
				
                                var aData = oTable.fnGetData(nRow); //newer version of fnGetData(nRow);
                               
				var jqTds = $('>td', nRow);
                                
                                jqTds[0].innerHTML = '<form id="for" method="post"  enctype="multipart/form-data"><input type="text" id="id" name="id" value="'+jqTds[0].innerHTML+'">';
                                jqTds[1].innerHTML='<input type="text" id="name" name="name" value="'+jqTds[1].innerHTML+'" > ';
				jqTds[2].innerHTML=jqTds[2].innerHTML+'<label>Change Image</label><input type="file" id="changeimage" name="changeimage">';
                                jqTds[3].innerHTML = '<input type="email" id="email" name="email" value="'+jqTds[3].innerHTML+'">';
                                jqTds[4].innerHTML = '<input type="text" id="no" value="'+jqTds[4].innerHTML+'" name="no"> ';
                                jqTds[5].innerHTML = '<input type="date" name="date" id="datepicker" value="'+jqTds[5].innerHTML+'">';
                                jqTds[6].innerHTML = '<input type="radio" id="indian" name="nationality" value="Indian" checked="">Indian&nbsp;<input type="radio" id="nepalian" name="nationality" value="Nepalian" >Nepalian';
				jqTds[7].innerHTML = '<select name="select" id="qualification" class="form-control"><option value="Not Educated">Not Educated</option><option value="4th Std">4th Std</option><option value="8th Std">8th Std</option><option value="10th Std">10th Std</option><option value="12th Std">12th Std</option><option value="Under Graduate">Under Graduate</option><option value="Post Graduate">Post Graduate</option></select>';
                                jqTds[8].innerHTML = '<input type="radio" id="hrs" name="s_type" value="HRS SHIFT" checked="">HRS SHIFT&nbsp;&nbsp;<input type="radio" id="servicemen" name="s_type" value="EX-SERVICEMEN">EX-SERVICEMEN&nbsp;&nbsp;<input type="radio" id="civilian" name="s_type" value="CIVILIAN TRAINED">CIVILIAN TRAINED';
                                jqTds[9].innerHTML = '<select name="post" id="post" class="form-control"><option value="Security Guard">Security Guard</option><option value="Security Supervisor">Security Supervisor</option><option value="Body Guard">Body Guard</option><option value="Security Officer">Security Officer</option><option value="Gunmen">Gunmen</option><option value="Lady Searcher">Lady Searcher</option></select>';
                                jqTds[10].innerHTML = '<input type="text" id="status" name="status" value="'+jqTds[10].innerHTML+'">';
                                jqTds[11].innerHTML = '<input type="text" id="other" name="other" value="'+jqTds[11].innerHTML+'">';
                                jqTds[12].innerHTML = '<input type="text" id="police" name="police" value="'+jqTds[12].innerHTML+'">';
                                jqTds[13].innerHTML = '<input type="text" id="railway" name="railway" value="'+jqTds[13].innerHTML+'"></form>';
				jqTds[19].innerHTML = '<a class="edit" href="" id="save">Save</a>';
                                
        $('#changeimage').change(function(event)
        {
            files = event.target.files;
            
        });
			}
                 
        
            function saveRow ( oTable, nRow )
			{ 
                            filedata = document.getElementById("changeimage");
                            var qual = document.getElementById("qualification").value;
                            var id = $('#id').val();
                            var name = $('#name').val();
                            var email = $('#email').val();
                            var no = $('#no').val();
                            var dob = $('#datepicker').val();
                            var status = document.getElementById("status").value;
                            var post = $('#post').val();
                            var status = $('#status').val();
                            var other = $('#other').val();
                            var police = $('#police').val();
                            var railway = $('#railway').val();
                            var nation = $('input[name=nationality]:checked').val();
                            var s = $('input[name=s_type]:checked').val();
                            var data = new FormData();
                            $.each(files,function(key,value)
                            {
                               data.append("changeimage",value);
                            });
                            data.append("name",name);
                            data.append("dob",dob);
                            data.append("nationality",nation);
                            data.append("email",email);
                            data.append("qualification",qual);
                            data.append("no",no);
                            data.append("stype",s);
                            data.append("police",police);
                            data.append("railway",railway);
                            data.append("status",status);
                            data.append("type","security");
                            data.append("post",post);
                            data.append("id",id);

            $.ajax({
            type: "POST",
            enctype: 'multipart/form-data',
            url: "<%=application.getContextPath()%>/UpdateServlet",
            data: data,
            processData: false,
            contentType: false,
            cache: false,
            timeout: 600000,
            success: function (path) {
    oTable.fnUpdate( id, nRow, 0, false );
    oTable.fnUpdate( name, nRow, 1, false );
    oTable.fnUpdate( path, nRow, 2, false );
    oTable.fnUpdate( email, nRow, 3, false );
    oTable.fnUpdate( no, nRow, 4, false );
    oTable.fnUpdate( dob, nRow, 5, false );
    oTable.fnUpdate( nation, nRow, 6, false );
    oTable.fnUpdate( qual, nRow, 7, false );
    oTable.fnUpdate( s, nRow, 8, false );
    oTable.fnUpdate(post,nRow,9,false);
    oTable.fnUpdate(status,nRow,10,false);
    oTable.fnUpdate(other,nRow,11,false);
    oTable.fnUpdate(police,nRow,12,false);
    oTable.fnUpdate(railway,nRow,13,false);
    oTable.fnUpdate( '<a class="edit" href="">Edit</a>', nRow, 19, false );
    oTable.fnDraw();
            },
            error : function(xhr,data,error)
            {
                alert(xhr.responseText);
            }
       

			});
                    }
var bid,pid,rid,eid,sid;
            function restoreRow ( oTable, nRow ){
                    var aData = oTable.fnGetData(nRow);
                    var jqTds = $('>td', nRow);

                    for ( var i=0, iLen=jqTds.length ; i<iLen ; i++ ) {
                        oTable.fnUpdate( aData[i], nRow, i, false );
                    }
                     oTable.fnDraw();
             }
             function editRowBank ( oTable1, nRow )
			{
				
                                var aData = oTable1.fnGetData(nRow); //newer version of fnGetData(nRow);
                                
				var jqTds = $('>td', nRow);
                                id=aData[0];
                                jqTds[0].innerHTML = '<form id="bank" method="post"> <input type="text" value="'+jqTds[0].innerHTML+'" id="ifsc" >' ;
                                jqTds[1].innerHTML='<input type="text" id="acc" name="acc" value="'+jqTds[1].innerHTML+'"> ';
				jqTds[2].innerHTML = '<input type="text" id="bname" name="bname" value="'+jqTds[2].innerHTML+'" >';
				jqTds[3].innerHTML = '<input type="text" id="branch" name="branch" value="'+jqTds[3].innerHTML+'">';
				jqTds[4].innerHTML = '<input type="text" id="city" value="'+jqTds[4].innerHTML+'"> </form>';
				
				jqTds[6].innerHTML = '<a class="edit" href="" id="save">Save</a>';
                                
        
			}
                 
        
            function saveRowBank ( oTable1, nRow )
			{ 
                            
                            
                            
                            
                            
                           // alert(formObj.jid.value);
                            var data = new FormData();
                            
                           
                            data.append("ifsc",document.getElementById("ifsc").value);
                            data.append("acc",document.getElementById("acc").value);
                            data.append("bname",document.getElementById("bname").value);
                            data.append("branch",document.getElementById("branch").value);
                            data.append("city",document.getElementById("city").value);
                            data.append("id",bid);
                            var ifsc = document.getElementById("ifsc").value;
                            var acc=document.getElementById("acc").value;
                            var bname=document.getElementById("bname").value;
                            var branch=document.getElementById("branch").value;
                            var city=document.getElementById("city").value;
                            
            $.ajax({
            type: "POST",
            url: "<%=application.getContextPath()%>/UpdateBankServlet?ifsc="+ifsc+"&acc="+acc+"&bname="+bname+"&branch="+branch+"&city="+city+"&id="+bid,
            data: data,
            processData: false,
            contentType: false,
            cache: false,
            timeout: 600000,
            success: function (data) {

               
//               d=data;
//               
         var jqInputs = $('input', nRow);
    oTable1.fnUpdate( ifsc, nRow, 0, false );
    oTable1.fnUpdate( acc, nRow, 1, false );
    oTable1.fnUpdate( bname, nRow, 2, false );
    oTable1.fnUpdate( branch, nRow, 3, false );
    oTable1.fnUpdate( city, nRow, 4, false );
    oTable1.fnUpdate( '<a class="edit" href="">Edit</a>', nRow, 6, false );
    oTable1.fnDraw();
            },
            error : function(xhr,data,error)
            {
                alert(xhr.responseText);
            }
       

			});
                    }

            function restoreRowBank ( oTable1, nRow ){
                    var aData = oTable1.fnGetData(nRow);
                    var jqTds = $('>td', nRow);

                    for ( var i=0, iLen=jqTds.length ; i<iLen ; i++ ) {
                        oTable1.fnUpdate( aData[i], nRow, i, false );
                    }
                     oTable1.fnDraw();
             }
             function editRowPer ( oTable1, nRow )
			{
				
                                var aData = oTable1.fnGetData(nRow); //newer version of fnGetData(nRow);
                                
				var jqTds = $('>td', nRow);
                                id=aData[0];
                                jqTds[0].innerHTML = '<form id="per" method="post"> <input type="text" value="'+jqTds[0].innerHTML+'" id="type" readonly>' ;
                                jqTds[1].innerHTML='<input type="text" id="line1" name="line1" value="'+jqTds[1].innerHTML+'"> ';
				jqTds[2].innerHTML = '<input type="text" id="line2" name="line2" value="'+jqTds[2].innerHTML+'" >';
				jqTds[3].innerHTML = '<input type="text" id="area" name="area" value="'+jqTds[3].innerHTML+'">';
				jqTds[4].innerHTML = '<input type="text" id="district" value="'+jqTds[4].innerHTML+'">';
                                jqTds[5].innerHTML = '<input type="text" id="pin" name="pin" value="'+jqTds[5].innerHTML+'">';
				jqTds[6].innerHTML = '<input type="text" id="state" name="state" value="'+jqTds[6].innerHTML+'"> </form>';
				jqTds[8].innerHTML = '<a class="edit" href="" id="save">Save</a>';
                                
        
			}
                 
        
            function saveRowPer ( oTable1, nRow )
			{ 
                            
                            
                            
                            
                            
                           // alert(formObj.jid.value);
                            
                            var type = document.getElementById("type").value;
                            var line1=document.getElementById("line1").value;
                            var line2=document.getElementById("line2").value;
                            var area=document.getElementById("area").value;
                            var district=document.getElementById("district").value;
                            var pin=document.getElementById("pin").value;
                            var state=document.getElementById("state").value;
                            
            $.ajax({
            type: "GET",
            url: "<%=application.getContextPath()%>/UpdatePerServlet?typr="+type+"&line1="+line1+"&line2="+line2+"&area="+area+"&district="+district+"&pin="+pin+"&state="+state+"&id="+pid+"&type="+type,
            
            processData: false,
            contentType: false,
            cache: false,
            timeout: 600000,
            success: function (data) {

               
//               d=data;
//               
         var jqInputs = $('input', nRow);
    oTable1.fnUpdate( type, nRow, 0, false );
    oTable1.fnUpdate( line1, nRow, 1, false );
    oTable1.fnUpdate( line2, nRow, 2, false );
    oTable1.fnUpdate( area, nRow, 3, false );
    oTable1.fnUpdate( district, nRow, 4, false );
    oTable1.fnUpdate( pin, nRow, 5, false );
    oTable1.fnUpdate( state, nRow, 6, false );
    oTable1.fnUpdate( '<a class="edit" href="">Edit</a>', nRow, 8, false );
    oTable1.fnDraw();
            },
            error : function(xhr,data,error)
            {
                alert(xhr.responseText);
            }
       

			});
                    }

            function restoreRowPer ( oTable1, nRow ){
                    var aData = oTable1.fnGetData(nRow);
                    var jqTds = $('>td', nRow);

                    for ( var i=0, iLen=jqTds.length ; i<iLen ; i++ ) {
                        oTable1.fnUpdate( aData[i], nRow, i, false );
                    }
                     oTable1.fnDraw();
             }
             function editRowRes ( oTable1, nRow )
			{
				
                                var aData = oTable1.fnGetData(nRow); //newer version of fnGetData(nRow);
                                
				var jqTds = $('>td', nRow);
                                id=aData[0];
                                jqTds[0].innerHTML = '<form id="per" method="post"> <input type="text" value="'+jqTds[0].innerHTML+'" id="type" readonly>' ;
                                jqTds[1].innerHTML='<input type="text" id="line1" name="line1" value="'+jqTds[1].innerHTML+'"> ';
				jqTds[2].innerHTML = '<input type="text" id="line2" name="line2" value="'+jqTds[2].innerHTML+'" >';
				jqTds[3].innerHTML = '<input type="text" id="area" name="area" value="'+jqTds[3].innerHTML+'">';
				jqTds[4].innerHTML = '<input type="text" id="district" value="'+jqTds[4].innerHTML+'">';
                                jqTds[5].innerHTML = '<input type="text" id="pin" name="pin" value="'+jqTds[5].innerHTML+'">';
				jqTds[6].innerHTML = '<input type="text" id="state" name="state" value="'+jqTds[6].innerHTML+'"> </form>';
				jqTds[8].innerHTML = '<a class="edit" href="" id="save">Save</a>';
			}
                 
        
            function saveRowRes ( oTable1, nRow )
			{ 
                            var type = document.getElementById("type").value;
                            var line1=document.getElementById("line1").value;
                            var line2=document.getElementById("line2").value;
                            var area=document.getElementById("area").value;
                            var district=document.getElementById("district").value;
                            var pin=document.getElementById("pin").value;
                            var state=document.getElementById("state").value;
                            
            $.ajax({
            type: "GET",
            url: "<%=application.getContextPath()%>/UpdatePerServlet?type="+type+"&line1="+line1+"&line2="+line2+"&area="+area+"&district="+district+"&pin="+pin+"&state="+state+"&id="+rid+"&type="+type,
            
            processData: false,
            contentType: false,
            cache: false,
            timeout: 600000,
            success: function (data) {

               
//               d=data;
//               
         var jqInputs = $('input', nRow);
    oTable1.fnUpdate( type, nRow, 0, false );
    oTable1.fnUpdate( line1, nRow, 1, false );
    oTable1.fnUpdate( line2, nRow, 2, false );
    oTable1.fnUpdate( area, nRow, 3, false );
    oTable1.fnUpdate( district, nRow, 4, false );
    oTable1.fnUpdate( pin, nRow, 5, false );
    oTable1.fnUpdate( state, nRow, 6, false );
    oTable1.fnUpdate( '<a class="edit" href="">Edit</a>', nRow, 8, false );
    oTable1.fnDraw();
            },
            error : function(xhr,data,error)
            {
                alert(xhr.responseText);
            }
       

			});
                    }

            function restoreRowRes ( oTable1, nRow ){
                    var aData = oTable1.fnGetData(nRow);
                    var jqTds = $('>td', nRow);

                    for ( var i=0, iLen=jqTds.length ; i<iLen ; i++ ) {
                        oTable1.fnUpdate( aData[i], nRow, i, false );
                    }
                     oTable1.fnDraw();
             }
             function editRowExp ( oTable1, nRow )
			{
				
                                var aData = oTable1.fnGetData(nRow); //newer version of fnGetData(nRow);
				var jqTds = $('>td', nRow);
                                jqTds[0].innerHTML = '<form id="exp" method="post"> <input type="text" value="'+jqTds[0].innerHTML+'" id="name" >' ;
                                jqTds[1].innerHTML='<input type="text" id="add" name="add" value="'+jqTds[1].innerHTML+'"> ';
				jqTds[2].innerHTML = '<input type="text" id="years" name="years" value="'+jqTds[2].innerHTML+'" >';
				
				jqTds[4].innerHTML = '<a class="edit" href="" id="save">Save</a>';
                                
        
			}
                 
        
            function saveRowExp ( oTable1, nRow )
			{ 
                            
                            
                            
                            
                            
                           // alert(formObj.jid.value);
                            
                            var exp = document.getElementById("name").value;
                            var add=document.getElementById("add").value;
                            var years=document.getElementById("years").value;
                            
                            
            $.ajax({
            type: "GET",
            url: "<%=application.getContextPath()%>/UpdateExpServlet?exp="+exp+"&add="+add+"&years="+years+"&id="+eid,
            
            processData: false,
            contentType: false,
                cache: false,
            timeout: 600000,
            success: function (data) {

               
//               d=data;
//               
         var jqInputs = $('input', nRow);
    oTable1.fnUpdate( years, nRow, 0, false );
    oTable1.fnUpdate( add, nRow, 1, false );
    oTable1.fnUpdate( exp, nRow, 2, false );
    
    oTable1.fnUpdate( '<a class="edit" href="">Edit</a>', nRow, 4, false );
    oTable1.fnDraw();
            },
            error : function(xhr,data,error)
            {
                alert(xhr.responseText);
            }
       

			});
                    }

            function restoreRowExp ( oTable1, nRow ){
                    var aData = oTable1.fnGetData(nRow);
                    var jqTds = $('>td', nRow);

                    for ( var i=0, iLen=jqTds.length ; i<iLen ; i++ ) {
                        oTable1.fnUpdate( aData[i], nRow, i, false );
                    }
                     oTable1.fnDraw();
             }
    $(document).ready(function()
    {
     
        $( "#datepicker" ).datepicker({
        changeMonth: true,
            changeYear: true,
            maxDate: "-18Y" 
        });
        
      
       $(document).on('click','.hrbank',function(e)
       {
        e.preventDefault();   
        var d = "<div class='col-md-auto' id='hi1' name='tabledisp'><center><h5><b>Bank Details</b></h5></center><br><table id='example1' class='cell-border' cellspacing='0' width='100%'><thead><tr><th>IFSC Code</th><th>Account No</th><th>Bank Name</th><th>Bank Branch</th><th>Bank City</th><th>Remove</th><th>Edit</th></tr></thead><tbody></tbody></table></div>";
           
           var url = $(this).attr("href");
           $('#hi1').remove();
           
           $('#hi2').remove();
           $('#hi3').remove();
           $('#hi4').remove();

           $('#pay-invoice').append(d);
           $('#hi1').css("visibility","visible");
                     if ( $.fn.dataTable.isDataTable( '#example1' ) )

                        {

                            table = $('#example1').DataTable();
                            
                            table.destroy();
                            
                        }
                                                

       oTable1 =  $('#example1').dataTable

                        ({
                            "ordering": false,
         "paging": false,
         "info":false,
          "searching": false, "language": {
      "emptyTable": "No Bank Details Found"
    },
   
                               "ajax": url,

                              "columns": [

                                   { "data": "ifsc" },

                                   { "data": "account" },

                                   { "data": "name" },

                                   { "data": "branch" },
                                   
                                   { "data": "city" },
                                   
                                    {"data" : "id"},
                                    
                                    {"data":"id1"}

                                   
                            ],
                            "fnRowCallback": function( nRow, aData, iDisplayIndex, iDisplayIndexFull )

                            {

                                var ifsc = aData['ifsc'];
                                var account = aData['account'];
                                var name = aData['name'];
                                var branch = aData['branch'];
                                var city = aData['city'];
                                 var id = aData['id'];
                                  bid=aData['id'];
                                                              
                                $('td:eq(0)', nRow).html(ifsc); // where 4 is the zero-origin visible column in the HTML
                                $('td:eq(1)', nRow).html(account);
                                $('td:eq(2)', nRow).html(name);
                                $('td:eq(3)', nRow).html(branch);
                                $('td:eq(4)', nRow).html(city);
                                $('td:eq(5)',nRow).html("<a class='remove' href='<%=application.getContextPath()%>/DeleteServlet?type=bank&id="+id+"' id='hrbank'>Remove</a></td>");
                                $('td:eq(6)',nRow).html("<a href='' class='edit'>Edit</a></td>");
                                return nRow;

                            }


                          
                        });
                         
       });
          
       $(document).on('click','.hradd',function(e)
       {

        e.preventDefault();   
        var d = "<div class='col-md-auto' id='hi2' name='tabledisp'><center><h5><b>Residance Address</b></h5></center><table id='example2' class='cell-border' cellspacing='0' width='100%'><thead><tr><th>Type</th><th>Address Line 1</th><th>Address Line 2</th><th>Area</th><th>District</th><th>PinCode</th><th>State</th><th>Remove</th><th>Edit</th></tr></thead><tbody></tbody></table></div>";
           
           var url = $(this).attr("href");
           $('#hi2').remove();
           $('#hi1').remove();
           $('#hi3').remove();
           $('#hi4').remove();
           $('#pay-invoice').append(d);
                $('#hi2').css("visibility","visible");
                     if ( $.fn.dataTable.isDataTable( '#example2' ) )

                        {

                            table = $('#example2').dataTable();

                            table.destroy();

                        }
                        
       oTable2 =  $('#example2').dataTable

                        ({
                            "ordering": false,
         "paging": false,
         "info":false,
          "searching": false,"language": {
      "emptyTable": "No Address Record Found"
    },
                               "ajax": url,

                              "columns": [

                                   { "data": "type" },

                                   { "data": "line1" },

                                   { "data": "line2" },

                                   { "data": "area" },
                                   
                                   { "data": "dis" },
                                   
                                   { "data": "pin" },
                                   
                                   { "data": "state" },
                                   
                                    {"data" : "id"},
                                    {"data" : "id1"}
                                   
                            ],
                            "fnRowCallback": function( nRow, aData, iDisplayIndex, iDisplayIndexFull )
                            {
                                var type = aData['type'];
                                var line1 = aData['line1'];
                                var line2 = aData['line2'];
                                var area = aData['area'];
                                var dis = aData['dis'];
                                var pin = aData['pin'];
                                var state = aData['state'];
                                var id = aData['id'];
                                rid=id;
                                $('td:eq(0)', nRow).html(type); // where 4 is the zero-origin visible column in the HTML
                                $('td:eq(1)', nRow).html(line1);
                                $('td:eq(2)', nRow).html(line2);
                                $('td:eq(3)', nRow).html(area);
                                $('td:eq(4)', nRow).html(dis);
                                $('td:eq(5)', nRow).html(pin);
                                $('td:eq(6)', nRow).html(state);
                                $('td:eq(7)',nRow).html("<a class='remove' href='<%=application.getContextPath()%>/DeleteServlet?type=add&id="+id+"' id='hrbank'>Remove</a></td>");
                                $('td:eq(8)',nRow).html("<a href='' class='edit'>Edit</a></td>");
                                return nRow;
                            }


                          
                        });
       });

        
        $(document).on("click",".hrper",function(e)
       {
           var d = "<center><div class='col-md-auto' id='hi3'><center><h5><b>Permanent Address </b></h5></center><table id='example3' class='cell-border' cellspacing='0' width='100%'><thead><tr><th>Type</th><th>Address Line 1</th><th>Address Line 2</th><th>Area</th><th>District</th><th>PinCode</th><th>State</th><th>Remove</th><th>Edit</th></tr></thead><tbody></tbody></table></div></center>";
     
           e.preventDefault();
           var url = $(this).attr("href");
        $('#hi1').remove();
        $('#hi3').remove();
        $('#hi2').remove();
        $('#hi4').remove();
        $('#pay-invoice').append(d);
        $('#hi3').css("visibility","visible");
                     if ( $.fn.dataTable.isDataTable( '#example3' ) )

                        {

                            table = $('#example3').DataTable();

                            table.destroy();

                        }
                        
      oTable3 =  $('#example3').dataTable

                        ({
                            "ordering": false,
         "paging": false,
         "info":false,
          "searching": false,"language": {
      "emptyTable": "No Address Record Found"
    },
                               "ajax": url,

                              "columns": [

                                   { "data": "type" },

                                   { "data": "line1" },

                                   { "data": "line2" },

                                   { "data": "area" },
                                   
                                   { "data": "dis" },
                                   
                                   { "data": "pin" },
                                   
                                   { "data": "state" },
                                   
                                    {"data" : "id"},
                                    {"data" : "id1"}

                                   
                            ],
                            "fnRowCallback": function( nRow, aData, iDisplayIndex, iDisplayIndexFull )

                            {

                                var type = aData['type'];
                                var line1 = aData['line1'];
                                var line2 = aData['line2'];
                                var area = aData['area'];
                                var dis = aData['dis'];
                                var pin = aData['pin'];
                                var state = aData['state'];
                                 var id = aData['id'];
                                 pid=id;                             
                                $('td:eq(0)', nRow).html(type); // where 4 is the zero-origin visible column in the HTML
                                $('td:eq(1)', nRow).html(line1);
                                $('td:eq(2)', nRow).html(line2);
                                $('td:eq(3)', nRow).html(area);
                                $('td:eq(4)', nRow).html(dis);
                                $('td:eq(5)', nRow).html(pin);
                                $('td:eq(6)', nRow).html(state);
                                $('td:eq(7)',nRow).html("<a class='remove' href='<%=application.getContextPath()%>/DeleteServlet?type=per&id="+id+"' id='hrbank'>Remove</a></td>");
                                $('td:eq(8)',nRow).html("<a href='' class='edit'>Edit</a></td>");

                                return nRow;

                            }


                          
                        });
       });
        $(document).on("click",".hrexp",function(e)
       {
           var d = "<center><div class='col-md-auto' id='hi4'><center><h5><b>Experience Details</b></h5></center><br><table id='example4' class='cell-border' cellspacing='0' width='100%'><thead><tr><th>Organisation Name</th><th>Organisation Address</th><th>No Of Years</th><th>Remove</th><th>Edit</th></tr></thead><tbody></tbody></table></div></center>";
           e.preventDefault();
           var url = $(this).attr("href");
        $('#hi1').remove();
        $('#hi2').remove();
        $('#hi4').remove();
        $('#hi3').remove();
        $('#pay-invoice').append(d);
        $('#hi4').css("visibility","visible");
                     if ( $.fn.dataTable.isDataTable( '#example4' ) )

                        {

                            table = $('#example4').DataTable();

                            table.destroy();

                        }
                        
     oTable4 =   $('#example4').dataTable

                        ({
                             "ordering": false,
         "paging": false,
         "info":false,
          "searching": false,  "language": {
      "emptyTable": "No Experience Record Found"
    },
                               "ajax": url,

                              "columns": [

                                   { "data": "years" },

                                   { "data": "address" },

                                   { "data": "name" },
                                   
                                    {"data" : "id"},
                                    
                                        {"data" : "id1"}
                                   
                                   
                            ],
                            "fnRowCallback": function( nRow, aData, iDisplayIndex, iDisplayIndexFull )

                            {

                                var year = aData['years'];
                                var address = aData['address'];
                                var name = aData['name'];
                                 var id = aData['id'];
                                     eid=id;                         
                                $('td:eq(0)', nRow).html(name); // where 4 is the zero-origin visible column in the HTML
                                $('td:eq(1)', nRow).html(address);
                                $('td:eq(2)', nRow).html(year);
                                $('td:eq(3)',nRow).html("<a class='remove' href='<%=application.getContextPath()%>/DeleteServlet?type=exp&id="+id+"' id='hrbank'>Remove</a></td>");
                                $('td:eq(4)',nRow).html("<a href='' class='edit'>Edit</a></td>");

                                return nRow;

                            }


                          
                        });
                        
       });
       $(document).on('click','.remove',function(e){
          
           url=$(this).attr('href');
           
           e.preventDefault();
           $("#delete").css('visibility','visible');
          $("#delete").dialog({
     autoOpen: true,
     modal: true,
     buttons : {
          "Confirm" : function() {
//                        location.href=url;
                      $.ajax({
                          url:url,
                          success:function(res)
                          {
                              location.reload();
                          }
                        });
                          $(this).dialog("close");
          },
          "Cancel" : function(e) {
              e.preventDefault();
            $(this).dialog("close");
          }
        }
      });
}); 
       $('#go').click(function()
    {
         var fil = $('#filter').val();
        var othr = $('#other').val();
        if(fil!=="all" && othr==="")
        {
            alert("Please Input A Value");
        }
        else
        {
       $('#hi').css("visibility","visible");
       url = '<%=application.getContextPath()%>/ViewDetailsServlet?type='+$('#filter').val()+'&v='+$('#other').val();
       if ( $.fn.dataTable.isDataTable( '#example' ) )

                        {

                            table = $('#example').DataTable();

                            table.destroy();

                        }
       oTable =  $('#example').dataTable({
         "language": {
        "emptyTable": "No Security Guard Details Found"
    },
   
                               "ajax": url,

                              "columns": [

                                   { "data": "id" },
                                   { "data": "name" },
                                   { "data": "iname" },

                                   { "data": "email" },

                                   { "data": "number" },
                                   
                                   { "data": "dob" },
                                   
                                    {"data" : "nation"},
                                    
                                    {"data":"qualification"},
                                    {"data":"stype"},
                                    {"data":"post"},
                                    {"data":"status"},
                                    {"data":"other"},
                                    {"data":"police"},
                                    {"data":"railway"},
                                    {"data":"bank"},
                                    {"data":"add1"},
                                    {"data":"add2"},
                                    {"data":"exp"},
                                    {"data":"remove"},
                                    {"data":"edit"}
//                                    {"data":"iname"}
                                   
                            ],
                            "fnRowCallback": function( nRow, aData, iDisplayIndex, iDisplayIndexFull )

                            {

                                bid=aData['id'];
                                $('td:eq(0)', nRow).html(aData['id']);                              
                                $('td:eq(1)', nRow).html(aData['fname']+" "+aData['mname']+" "+aData['lname']); // where 4 is the zero-origin visible column in the HTML
                                $('td:eq(2)', nRow).html('<img class="zoom" src="<%=application.getContextPath()%>/download/'+aData['iname']+'">');
                                $('td:eq(3)', nRow).html(aData['email']);
                                $('td:eq(4)', nRow).html(aData['number']);
                                $('td:eq(5)', nRow).html(aData['dob']);
                                $('td:eq(6)', nRow).html(aData['nation']);
                                $('td:eq(7)', nRow).html(aData['qualification']);
                                $('td:eq(8)', nRow).html(aData['stype']);
                                $('td:eq(9)', nRow).html(aData['post']);
                                $('td:eq(10)', nRow).html(aData['status']);
                                $('td:eq(11)', nRow).html(aData['other']);
                                $('td:eq(12)', nRow).html(aData['police']);
                                $('td:eq(13)', nRow).html(aData['railway']);
                                $('td:eq(14)', nRow).html("<a class='hrbank' href='<%=application.getContextPath()%>/ViewServlet?type=bank&id="+aData['id']+"'>View</a>");
                                $('td:eq(15)', nRow).html("<a class='hrper' href='<%=application.getContextPath()%>/ViewServlet?type=per&id="+aData['id']+"'>View</a>");
                                $('td:eq(16)', nRow).html("<a class='hradd' href='<%=application.getContextPath()%>/ViewServlet?type=add&id="+aData['id']+"'>View</a>");
                                $('td:eq(17)', nRow).html("<a class='hrexp' href='<%=application.getContextPath()%>/ViewServlet?type=exp&id="+aData['id']+"'>View</a>");
                                $('td:eq(18)',nRow).html("<a class='remove' href='<%=application.getContextPath()%>/DeleteServlet?type=security&id="+aData['id']+"'>Remove</a>");
                                $('td:eq(19)',nRow).html("<a href='' class='edit'>Edit</a></td>");
                                return nRow;

                            }


                          
                        });
                    }
    });
        var nEditing = null;

                $( document ).on( 'click','#example a.edit', function (e){
                   
                     e.preventDefault();

                    var nRow = $(this).parents('tr')[0];
					if ( nEditing !== null && nEditing !== nRow ) {
						/* Currently editing - but not this row - restore the old before continuing to edit mode */
						
                        restoreRow( oTable, nEditing );
						editRow( oTable, nRow );
						nEditing = nRow;
					}
					else if (nEditing === nRow && this.innerHTML === "Save" ) {
                                            
                        saveRow( oTable, nEditing );
						nEditing = null;
					}
					else {
						/* No edit in progress - let's start one */
                       
						editRow( oTable, nRow );
						nEditing = nRow;
					}
                });
                var nEditing1 = null;
                $( document ).on( 'click','#example1 a.edit', function (e){
                   
                     e.preventDefault();
                     
                    var nRow1 = $(this).parents('tr')[0];
					if ( nEditing1 !== null && nEditing1 !== nRow1 ) {
						/* Currently editing - but not this row - restore the old before continuing to edit mode */
						
                        restoreRowBank( oTable1, nEditing1 );
						editRowBank( oTable1, nRow1 );
						nEditing1 = nRow1;
					}
					else if (nEditing1 === nRow1 && this.innerHTML === "Save" ) {
                                            
                        saveRowBank( oTable1, nEditing1 );
						nEditing1 = null;
					}
					else {
						/* No edit in progress - let's start one */
                       
						editRowBank( oTable1, nRow1 );
						nEditing1 = nRow1;
					}
                });
                var nEditing2 = null;
                $( document ).on( 'click','#example3 a.edit', function (e){
                   
                     e.preventDefault();
                     
                    var nRow2 = $(this).parents('tr')[0];
					if ( nEditing2 !== null && nEditing2 !== nRow2 ) {
						/* Currently editing - but not this row - restore the old before continuing to edit mode */
						
                        restoreRowPer( oTable3, nEditing2 );
						editRowPer( oTable3, nRow2 );
						nEditing2 = nRow2;
					}
					else if (nEditing2 === nRow2 && this.innerHTML === "Save" ) {
                                            
                        saveRowPer( oTable3, nEditing2 );
						nEditing2 = null;
					}
					else {
						/* No edit in progress - let's start one */
                       
						editRowPer( oTable3, nRow2 );
						nEditing2 = nRow2;
					}
                });
                var nEditing3 = null;
                $( document ).on( 'click','#example2 a.edit', function (e){
                   
                     e.preventDefault();
                     
                    var nRow3 = $(this).parents('tr')[0];
					if ( nEditing3 !== null && nEditing3 !== nRow3 ) {
						/* Currently editing - but not this row - restore the old before continuing to edit mode */
						
                        restoreRowRes( oTable2, nEditing3 );
						editRowRes( oTable2, nRow3 );
						nEditing3 = nRow3;
					}
					else if (nEditing3 === nRow3 && this.innerHTML === "Save" ) {
                                            
                        saveRowRes( oTable2, nEditing3 );
						nEditing3 = null;
					}
					else {
						/* No edit in progress - let's start one */
                       
						editRowRes( oTable2, nRow3 );
						nEditing3 = nRow3;
					}
                });
                var nEditing4 = null;
                $( document ).on( 'click','#example4 a.edit', function (e){
                   
                     e.preventDefault();
                     
                    var nRow4 = $(this).parents('tr')[0];
					if ( nEditing4 !== null && nEditing4 !== nRow4 ) {
						/* Currently editing - but not this row - restore the old before continuing to edit mode */
						
                        restoreRowExp( oTable4, nEditing4 );
						editRowExp( oTable4, nRow4 );
						nEditing4 = nRow4;
					}
					else if (nEditing4 === nRow4 && this.innerHTML === "Save" ) {
                                            
                        saveRowExp( oTable4, nEditing4 );
						nEditing4 = null;
					}
					else {
						/* No edit in progress - let's start one */
                       
						editRowExp( oTable4, nRow4 );
						nEditing4 = nRow4;
					}
                });
    });
    
  </script>
  <style>
      #delete
      {
          visibility: hidden;
      }
      .zoom {
    transition: transform .2s; /* Animation */
    width: 50px;
    height: 50px;
    margin: 0 auto;
}
      #hi
      {
          visibility: hidden;
      }
      #hi1
      {
          visibility: hidden;
      }
      #hi2
      {
          visibility: hidden;
      }
      #hi3
      {
          visibility: hidden;
      }  
      #hi4
      {
          visibility: hidden;
      }  
      .container {
  display: inline-block;
}
.manImg {
  display: none;
}
.hover-text:hover ~ .manImg {
  display: block;  
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
           <div class="content mt-3" >
                <div class="animated fadeIn" >


                    <div class="row" >
                        <div class="col-lg-12" >
                            
                                
                            <h4><strong class="card-title" >Security Person View </strong></h4>
                            <br>
                            <br>
                            
                           
                                        <div class="card" >
                                <div class="card-header">
                                </div>
                                            <div class="card-body" >
                                             <div id="pay-invoice" >
                                            <div class="card-body" >
                                            <div class="row form-group">
                                                        <div class="col col-md-2"><select id="filter" class="form-group">
                                                                <option value="all">All</option>
                                                                <option value="firstname">By Name</option>
                                                                <option value="s_type">By Type</option>
                                                                <option value="sp_id">By ID</option>
                                                        </select>
                                                        </div>
                                                    <div class="col col-md-2"><input type="text" name="other" id="other" class="form-control" placeholder=""></div>
                                                    <div class="col col-md-3 "><input type="button" name="go" value="Go" id="go"></div>
                                                    <br>
                                                    <div id="hi" style="overflow-x: auto">
                                           
                                               
                                   <table id="example" class="display" cellspacing="0" width="100%">
                                                    <thead>
                                                        <tr>
                                                            <th>ID</th>
                                                            <th>Name</th>
                                                            <th>Image</th>
                                                            <th>Email</th>
                                                            <th>Contact</th>
                                                            <th>DOB</th>
                                                            <th>Nationality</th>
                                                            <th>Qualification</th>
                                                            <th>S_TYPE</th>
                                                            <th>Post</th>
                                                            <th>Status</th>
                                                            <th>Other</th>
                                                            <th>Nearest Police Station</th>
                                                            <th>Nearest Railway Station</th>
                                                            <th>Bank Details</th>
                                                            <th>Permanent Address</th>
                                                            <th>Resident Address</th>
                                                            <th>Experience Address</th>
                                                            <th>Remove</th>
                                                            <th>Edit</th>
                                                             
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                       
                                                 </tbody>
                                             </table>
                                                 <div id="delete" title="Are You Sure You ?">
                                                     <p><span class="ui-icon ui-icon-alert" style="float:left; margin:12px 12px 20px 0;"></span>These Records will be deleted permanently and cannot be &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; recovered. Are you sure?</p>
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
                                </div>
                            </div>
                                
  
       
            <%@include file="footerfiles.jsp" %>
    </body></html>
