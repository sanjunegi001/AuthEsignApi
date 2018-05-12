<%@ include file="template/header.jsp" %>


 
  <link href="<c:url value="/resources/css/normalize.css" />" rel="stylesheet">
  <link href="<c:url value="/resources/css/demo.css" />" rel="stylesheet">
  <link href="<c:url value="/resources/css/component.css" />" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css?family=Roboto:100,300" rel="stylesheet"> 
<script type="text/javascript">
  function ischkESIGNOtp()
   {
	 
	 return $('#chkESIGNOtp').is(':checked');	
	 
   }
 function ischkESIGNbio()
  {
	 
	 return $('#chkESIGNbio').is(':checked');	
	 
}
function ischkESIGNiris()
{
	 
	 return $('#chkESIGNiris').is(':checked');	
	 
}
function EnableDisableEsignProceedButton()
{
	
	 document.getElementById('chkESIGNbio').disabled = false;
	 document.getElementById('chkESIGNOtp').disabled = false;
	// document.getElementById('chkESIGNiris').disabled = false;
	 
	 var txtAadhaarValue = $('#txtAadhaarNo').val();
	 var txtNameValue = $('#txtName').val();
	 var txtLocationValue = $('#txtLocation').val();
	 var txtReasonValue = $('#txtReason').val();
	 
	
	 
	 
	 if($.trim(txtAadhaarValue).length < 12) {
	    	alert("Invalid Aadhaar Number");
	    	$('input:checkbox').each(function() { this.checked = false; });
	        document.getElementById('btnProceed').disabled = true;
	        return;
	    }
	 
	 if($.trim(txtAadhaarValue).length > 12) {
	    	alert("Invalid Aadhaar Number");
	    	 $('input:checkbox').each(function() { this.checked = false; });
	        document.getElementById('btnProceed').disabled = true;
	        return;
	    }
	 
	 var isAadhaarNumberValid = checkverhoff($('#txtAadhaarNo').val());
	 if (isAadhaarNumberValid === false) {
	        document.getElementById('btnProceed').disabled = true;
	        document.getElementById('chkESIGNbio').disabled = false;
	        document.getElementById('chkESIGNOtp').disabled=false;
	       // document.getElementById('chkESIGNiris').disabled=false;
	        
	        
	        alert("Invalid Aadhaar Number");
	        $('input:checkbox').each(function() { this.checked = false; });
	        return;
	    }
	 
	 if ((ischkESIGNOtp())&& isAadhaarNumberValid) {
	    	
	    	document.getElementById('btnProceed').disabled = false;
	    	document.getElementById('chkESIGNbio').disabled = true;
	    	
	    	//document.getElementById('chkESIGNiris').disabled = true;
	    }
	 else if((ischkESIGNbio())&& isAadhaarNumberValid)
		 {
		   
		    document.getElementById('btnProceed').disabled = false;
	    	document.getElementById('chkESIGNOtp').disabled = true;
	    	//document.getElementById('chkESIGNiris').disabled = true;
		 }
	 else if((ischkESIGNiris())&& isAadhaarNumberValid)
		 {
		 
		   
		    document.getElementById('btnProceed').disabled = false;
	    	document.getElementById('chkESIGNbio').disabled = true;
	    	document.getElementById('chkESIGNOtp').disabled = true;
		 }
	 else
		 {
		 document.getElementById('btnProceed').disabled = true;
		 }
	 
	 /* if(jQuery.trim(txtNameValue).length==0||jQuery.trim(txtNameValue).length==null) {
		    alert("Please Enter Name Value");
		    $('input:checkbox').each(function() { this.checked = false; });
	        document.getElementById('btnProceed').disabled = true;
	        return;
	 } */
	 
	
}
function checkverhoff(value) {
	
    var d = [[0, 1, 2, 3, 4, 5, 6, 7, 8, 9],
            [1, 2, 3, 4, 0, 6, 7, 8, 9, 5],
            [2, 3, 4, 0, 1, 7, 8, 9, 5, 6],
            [3, 4, 0, 1, 2, 8, 9, 5, 6, 7],
            [4, 0, 1, 2, 3, 9, 5, 6, 7, 8],
            [5, 9, 8, 7, 6, 0, 4, 3, 2, 1],
            [6, 5, 9, 8, 7, 1, 0, 4, 3, 2],
            [7, 6, 5, 9, 8, 2, 1, 0, 4, 3],
            [8, 7, 6, 5, 9, 3, 2, 1, 0, 4],
            [9, 8, 7, 6, 5, 4, 3, 2, 1, 0]];
    var p = [[0, 1, 2, 3, 4, 5, 6, 7, 8, 9],
            [1, 5, 7, 6, 2, 8, 3, 0, 9, 4],
            [5, 8, 0, 3, 7, 9, 6, 1, 4, 2],
            [8, 9, 1, 6, 0, 4, 3, 5, 2, 7],
            [9, 4, 5, 3, 1, 2, 6, 8, 7, 0],
            [4, 2, 8, 6, 5, 7, 3, 9, 0, 1],
            [2, 7, 9, 3, 8, 0, 6, 4, 1, 5],
            [7, 0, 4, 6, 9, 1, 3, 2, 5, 8]];
    var j = [0, 4, 3, 2, 1, 5, 6, 7, 8, 9];

    if ($.trim(value) === '')
        return false;

    var c = 0;
    value.replace(/\D+/g, "").split("").reverse().join("").replace(/[\d]/g, function (u, i, o) {
        c = d[c][p[i & 7][parseInt(u, 10)]];
    });
    return (c === 0);
}


</script>



   
   
   
   
   
   
   
   
   <form id="eSignForm"  method="post" action="https://14.142.129.242/EsignAuth/getEkycDetails" >
  
     <input type="hidden"  name="msg" id="msg" value=""/>
     <input type="hidden"  name="obj" id="obj" value=""/>
  
  
   </form>
   
   <div class="mainDiv">
   
   
     <div class="innermainDiv">
       
         <%@ include file="template/innermenu.jsp" %>
   
    <div class="mainForm1">
      
     <form id="form1" method="post" action="#" enctype="multipart/form-data">
         <div class="mainaadharForm">
            <div id="main_bottom_auth_aadhaar">
               
               <table style="border-spacing:0 5px;margin-top: 40px; width: 800px; margin-left: auto; margin-right: auto; padding: 10px; font-weight: normal; font-size: small;">
               <tr>
            	  <td><lable style="font-weight: bold;float: left;font-size: 14px;">Aadhaar Number<span style="color:red">*</span></lable></td>
            	  <td><input type="text" name="txtAadhaarNo" id="txtAadhaarNo" style="float: left;width: 309px;border: 1px solid #ccc;height: 30px;border-radius: 5px;" placeholder="Aadhaar Number"></input></td>
            	</tr>
            	<tr><td>&nbsp;</td></tr>
            	<tr>
            	  <td><lable style="font-weight: bold;float: left;font-size: 14px;">Name<span style="color:red"></span></lable></td>
            	  <td><input type="text" name="txtName" id="txtName" style="float: left;width: 309px;border: 1px solid #ccc;height: 30px;border-radius: 5px;" placeholder="Enter Your Name"></input></td>
            	</tr>
            	<tr><td>&nbsp;</td></tr>
            	
            	<tr>
            	  <td><lable style="font-weight: bold;font-size: 14px;float: left;">Location<span style="color:red"></span></lable></td>
            	  <td><input type="text" name="txtLocation" id="txtLocation" style="float: left;width: 309px;border: 1px solid #ccc;height: 30px;border-radius: 5px;" placeholder="Enter Location"></input></td>
            	</tr>
            	<tr><td>&nbsp;</td></tr>
            	
            	<tr>
            	  <td><lable style="font-weight: bold;font-size: 14px;float: left;">Reason<span style="color:red"></span></lable></td>
            	  <td><input type="text" name="txtReason" id="txtReason" style="float: left;width: 309px;border: 1px solid #ccc;height: 30px;border-radius: 5px;" placeholder="Enter Reason"></input></td>
            	</tr>
            	<tr><td>&nbsp;</td></tr>
            	
            	 <tr>
                  	<td><lable style="font-weight: bold;font-size: 14px;float: left;">Esign Type<span style="color:red">*</span></lable></td>
                  	<td style="font-weight: bold;font-size: 14px;float: left;"><input type="checkbox" id="chkESIGNOtp" name="chkESIGNOtp" class="authtypecheckbox" value="EsignOtp" onclick="EnableDisableEsignProceedButton()" ><label>&nbsp;Otp</label></td>
					<td style="font-weight: bold;font-size: 14px;float: left;">&nbsp;&nbsp;&nbsp;</td>
					<td style="font-weight: bold;font-size: 14px;float: left;"><input type="checkbox" id="chkESIGNbio" name="chkESIGNbio" class="authtypecheckbox" value="EsignBio" onclick="EnableDisableEsignProceedButton()" ><label>&nbsp;Bio</label></td>
                  </tr>
                  <tr><td>&nbsp;</td></tr>
                  <tr>
                  	<td><lable style="font-weight: bold;font-size: 14px;float: left;">UploadFile<span style="color:red">*</span></lable></td>
                  	<td style="font-weight: bold;font-size: 14px;float: left;"><input type="file" name="file-1" id="file-1" class="inputfile inputfile-1" data-multiple-caption="{count} files selected" multiple style="height: 6px;"/><label for="file-1"><svg xmlns="http://www.w3.org/2000/svg" width="20" height="17" viewBox="0 0 20 17" style="margin-top: -10px;float: left;"><path d="M10 0l-5.2 4.9h3.3v5.1h3.8v-5.1h3.3l-5.2-4.9zm9.3 11.5l-3.2-2.1h-2l3.4 2.6h-3.5c-.1 0-.2.1-.2.1l-.8 2.3h-6l-.8-2.2c-.1-.1-.1-.2-.2-.2h-3.6l3.4-2.6h-2l-3.2 2.1c-.4.3-.7 1-.6 1.5l.6 3.1c.1.5.7.9 1.2.9h16.3c.6 0 1.1-.4 1.3-.9l.6-3.1c.1-.5-.2-1.2-.7-1.5z"/></svg><span style="font-size: 16px;float: left;margin-top: -7px;">Choose a file&hellip;</span></label></td>

                  </tr> 
            	
            	</table>
            
            </div>
            
           
              
              
              <div id="main_bottom_auth_aadhaar">
                             
                              <div class="action_buttion" style="padding-top: 30px;width: 242px;margin: 0 auto;float: none;">
                   
                                  <div class="action_buttion_auth">
                      
                                   <input class="btn btn-default btnesign" type="button" value="Proceed" id="btnProceed" onclick="otpEsignRequest()" disabled="disabled">
                        
                                 </div>
                                
                   
                        </div>
                             
                        
              </div>
            
            	
                <div class="form-group">
                        
                </div>
         
            </div>
            </form>
           
           
              
          
   </div>
       <!--End Main Form-->
       
   </div>
      
       
      </div>
     
   <script src="<c:url value="/resources/js/custom-file-input.js" />"></script>  
     
        <%@ include file="template/footer.jsp" %>
   
        
  <script type="text/javascript">
  
  function onProceedEkyc() {
	  
	 
	  $("#txtadhaarnumber").val("");
	  var aadhaarnumber =document.getElementById("txtAadhaarNo").value;
	  $("#txtadhaarnumber").val(aadhaarnumber);
	  
	  var aadhaarnumberbio =document.getElementById("txtAadhaarNo").value;
	  $("#txtadhaarnumberbio").val(aadhaarnumberbio);
	  var authtype =  document.querySelector('.authtypecheckbox:checked').value;
	  
	 
	
	  if(authtype =="EsignOtp")
		 {  
	  $('.bodyblockUI').block({
			 
		    message: $('.blockUI'),
		    centerX: true,
		    centerY: true,
		    css: {
		        width: '450px',
		        height: '400px',
		        border: '3px solid #FF9900',
		        backgroundColor: '#000',
		        color: '#fff',
		        padding: '25px',
		        cursor :'default'
		    }
	 
	 
	    });
	  
	}
	  else if(authtype =="EsignBio")
		 {
		
		     
		 $('.bodyblockUIBIO').block({
			 	  
			    message: $('.blockUI1'),
			    centerX: true,
			    centerY: true,
			    css: {
			        width: '450px',
			        height: '478px',
			        border: '3px solid #FF9900',
			        backgroundColor: '#000',
			        color: '#fff',
			        padding: '25px',
			        cursor :'default'
			        
			    }
	 
		    });
		 
		 
		 }
  
  }
  $('#pclose1').click(function() { 
		 
	  $('.bodyblockUIBIO').unblock();
	  location.reload();
  }); 
  $('#pclose').click(function() { 
	 
	  $('.bodyblockUI').unblock();
	  location.reload();
  }); 
  
  </script>
<script type="text/javascript"> 

  function generateeginOtp()
   {
	var aadhaarno     = $('#txtadhaarnumber').val();
	var demo_result_data = $('#otp-result-data');
	demo_result_data.empty();
	  
	
	   //var content = '<span style="font-weight: bold;color: #3caf40;font-size: 12px;margin-left: -51px;">'+otpdata.message+'</span>';
	   //var list = $('<div id="otptable" style="margin-top: 10px;margin-bottom: 10px;margin-right: 115px;"/>').html(content);
	  // demo_result_data.append(list);
	 $('#esignmodal').css("display", "none");
     $('#esignfade').css("display", "none");
     $('.otptext').css("display", "none");
     $('.otpfile').css("display", "none");
	if(aadhaarno!=null)
	{
		
		$.ajax({
			
			
			type : "POST",
			url : "<c:url value="/otpesignGeneration" />",
			processData: false,
			data: "aadharnumber="+aadhaarno,
			beforeSend : function() {
    			  
                $('#esignmodal').css("display", "block");
                $('#esignfade').css("display", "block");
               },
			success : function(data) {
				
				 
				 var otpdata = JSON.parse(data);
				 
				 if(otpdata.status ==2||otpdata.status ==1)
					 {
					 
					 
					   var content = '<span style="font-weight: bold;color: #3caf40;font-size: 12px;margin-left: -51px;">'+otpdata.message+'</span>';
					   var list = $('<div id="otptable" style="margin-top: 10px;margin-bottom: 10px;margin-right: 115px;"/>').html(content);
					   demo_result_data.append(list);
					    $('#esignmodal').css("display", "none");
		                $('#esignfade').css("display", "none");
		                $('.otptext').css("display", "block");
		                $('.otpfile').css("display", "block");
					 }
				 else if(otpdata.status =="2")
					 {
					    alert("There is something technical issue,Please try again after sometime.");
					    $('#esignmodal').css("display", "none");
		                $('#esignfade').css("display", "none");
					    return false;
					 }
				 
				 else
					 {
					  
					 
					
					  var content = '<span style="font-weight: bold;color: red !important;font-size: 12px;margin-right: 71px;">'+otpdata.message+'</span>';
					  var list = $('<div id="otptable" style="width: 400px;margin-top: 10px;margin-bottom: 10px;"/>').html(content);
					  demo_result_data.append(list);
					  $('#esignmodal').css("display", "none");
		              $('#esignfade').css("display", "none");
		              $('.otptext').css("display", "none");
		              $('.otpfile').css("display", "none");
					  
					  
					 }
				 
				
			},
			error : function(e) {
				console.log("ERROR: ", e);
				display(e);
			}
			
			
			
		});
	}
	
	else
	{
	
	  alert("Aadhaar Number Is Required!");
	
	}
  
	
}
</script>





<script type="text/javascript">

    function otpEsignRequest()
    {
    	
  	   
    	  var authtype="";
    	  var formData = new FormData();
    	  var authtypevalue =  document.querySelector('.authtypecheckbox:checked').value;
    	  
    	  
    	  
    	  var aadhaar_no =jQuery.trim($('#txtAadhaarNo').val());
    	  var name ="";
    	  var location ="";
    	  var reason ="";	
    	  
    	  if(aadhaar_no =="")
		  {
		     alert("Aadhaar number is required!");
		     return false;
		  }
    	  
    	  if(authtypevalue =="EsignOtp")
  		 { 
     		  
     		  authtype="1";
     		  
  		 }else if(authtypevalue =="EsignBio")
 		 {
  			 authtype="2";
  			 
 		 }
  		 else{
  			 alert("Esign Type is required!");
 		       return false;
  			 
  		 }
    	  
    
    	  if(typeof($('#file-1')[0].files) =='undefined'||$('#file-1')[0].files.length==0)
    		  {
    		     alert("Please select pdf file!");
    		     return false;
    		  }
    	  else if($('#file-1')[0].files.length>1)
    		  {
    		 	 alert("You Can Select One file at a time");
 		    	 return false;
    		  	
    		  } 
    	  else{ 
    		   $.each($('#file-1')[0].files, function(i, file) {
    			  
    			  formData.append('file', file);
        		});
    	  }
    	  
    	  if(jQuery.trim($('#txtLocation').val()).length!=0||jQuery.trim($('#txtLocation').val()).length!=null) {
  		    
    		      location =jQuery.trim($('#txtLocation').val());
        	      
  	       }else{
  	    	   
  	    	 location="";
  	       }
    	  
    	  if(jQuery.trim($('#txtReason').val()).length!=0||jQuery.trim($('#txtReason').val()).length!=null) {
    		   
    		   reason =jQuery.trim($('#txtReason').val());
	       }else{
	    	   
	    	   reason="";
	       }
    	  
    	  if(jQuery.trim($('#txtName').val()).length!=0||jQuery.trim($('#txtName').val()).length!=null) {
   		   
   		      name =jQuery.trim($('#txtName').val());
	       }else{
	    	   
	    	   name="";
	       }
    	 
    	  formData.append('aadhaarno', aadhaar_no); 
    	  formData.append('authmode', authtype);
    	  formData.append('name',name);
    	  formData.append('location',location);
    	  formData.append('reason', reason);
    	  
    	  
    	  
    	  
    	if(aadhaar_no !="")
    		{
    		
    		   $.ajax({
    			
    			   
    			   type : "POST",
    				url : "<c:url value="/otpdocsign" />",
    				processData: false,
    				data: formData,
    				enctype:"multipart/form-data",
    				contentType: false,
    				
    					 beforeSend: function() {
    			                $.blockUI({ message: ' <img src="<c:url value="/resources/images/loading.gif"/>" />' });
    			            },  
    	          
    	               success : function(data) {
    			     	   var finaldata = JSON.parse(data);
    			     	   
    			     	 
    			     	   
    	            	   if(finaldata.statuscode =="1")
    	            		   {
    	            		    
    	            		   	 $('input:checkbox').each(function() { this.checked = false; });
    	            	  	     $('input[type=text]').each(function() {$(this).val('');});
    	            	  	     document.getElementById('chkESIGNbio').disabled = false;
    	            	  	     document.getElementById('chkESIGNOtp').disabled = false;
    	            	  	     document.getElementById('btnProceed').disabled = true;
    	            		     document.getElementById("msg").value=finaldata.inputxml;
    	            		     document.getElementById("obj").value=finaldata.addharno;
    	            		     jQuery("#eSignForm").attr("target","_blank");
    	            		     document.getElementById("eSignForm").submit();
    	            		     $.unblockUI();
    	            		    
    	    	                //  window.location.reload();
    	            		   
    	            		   }
    	            	   else if(finaldata.statuscode =="2")
	            		   {
    	            		   $.unblockUI();
    	            		     alert("File is not Valid " +finaldata.error);  
	            		        
	    	                     window.location.reload();
	            		   }
    	            	   
    	            	   else if(finaldata.statuscode =="3")
	            		   {
    	            		   $.unblockUI();
    	            		   alert(finaldata.message); 
    	            		 
	            		   }
    	            	   else if(finaldata.statuscode =="4")
    	            		{
    	            		     $.unblockUI();
    	            		      alert("Invalid file ! Please try pdf file");
    	            		}
    	          	   
    	               },
    	   		
    		   });
    		   
    	
    		}
    	else
    		{
    		   $.unblockUI();
    		   alert("Aadhaar number and Otp are required!");
    		
    		}
    	
    	
    	
    }

    

</script>



<script type="text/javascript">

$(document).ready(function(){


	$('#ekyc_request_btnCapture').click(function(event) {

         
		var devicevalue = $('#fpDriversEkyc').val();
		if(devicevalue =="MORPHO_1300E3")
		 {
			
			 var url = "https://localhost:15001/CaptureFingerprint";
			  var xmlhttp;
			  if (window.XMLHttpRequest)
			  {// code for IE7+, Firefox, Chrome, Opera, Safari

			     xmlhttp=new XMLHttpRequest();
			  
			  }
			  else
			  {// code for IE6, IE5
			    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");

			  }
			 xmlhttp.onreadystatechange=function()
			  {
			     if (xmlhttp.readyState==4 && xmlhttp.status==200)
			     {
			    	 
				   fpobject = JSON.parse(xmlhttp.responseText);
				   document.getElementById("realimage").src = "data:image/bmp;base64," + fpobject.Base64BMPIMage; 
			       //alert(fpobject.Base64ISOTemplate);
			       
			     }
				 
							
				 xmlhttp.onerror = function () {
			         alert("Check If Morpho Service/Utility is Running");
			    }

			  }
			  
			  var timeout = 5;
			  var fingerindex = 1;

			  xmlhttp.open("POST",url+"?"+timeout+"$"+fingerindex,true);
			  xmlhttp.send();
			
    	
			
			
			
		 }
		
		
	
		

});
});
</script>


<script type="text/javascript">

$(document).ready(function() {

	 $("a#ddownload").on('click', function() {
	        var dpath = $(this).attr("href");
	      
	        
	        
	        
	        $.ajax({
				
				   
				    type : "GET",
					url : "<c:url value="/download" />",
					processData: false,
					data: "dfpath=" + dpath,
					processData: false,
					contentType: false,
					beforeSend : function() {
		    			  
		                $('#esignmodal').css("display", "block");
		                $('#esignfade').css("display", "block");
		               },
		              
		   		
			   });
	        
	     return false;  
	        
	  });
	
	
});

</script>

<script type="text/javascript">

$(document).ready(function() {

	$("a#deleteEsign").on('click', function() {
	if(confirm("Are you sure to delete this record?")){
		
	    var eseid = $(this).attr("href");
		
		$.ajax({
			
			    type : "POST",
				url : "<c:url value="/esignDelete" />",
				processData: false,
				data: "esignseid=" + eseid,
				beforeSend : function() {
	    			  
	                $('#esignmodal').css("display", "block");
	                $('#esignfade').css("display", "block");
	               },
	               success : function(data) {
	           
	            	   var esigndata = JSON.parse(data);
	            	   if(esigndata.status=="1")
	            		   {
	            		       alert("File is deleted successfully!");
	            		       window.location.reload();
	            		   }
	            	   else if(esigndata.status=="0")
	            		   {
	            		      alert("File is not deleted?");
	            		   }
	            	   else
	            		   {
	            		   alert("File is not deleted?");    
	            		   }
	            	    
	            	    
	            	   
	               },
			
			
			
			
			
		  });
        
	     return false;  
		
	}else{
		
		return false;
	}
	});
	
});

</script>


<script type="text/javascript">

$(document).ready(function(){

	$('#send_esign_request').click(function(event) {
		
	
		try{
		
		var biofile ="";
		var aadhaarno     = $('#txtadhaarnumberbio').val();
		var figimage     = fpobject.Base64ISOTemplate;
	
	    alert($('#file-2')[0].files[0]); 
		
  	   
  	  	if(aadhaarno == null || aadhaarno =="")
			{
			   alert("Aadhaar number is required");
			}
		
		if(typeof($('#file-2')[0].files[0]) =='undefined')
		  {
		     alert("Please select pdf file!");
		     return false;
		  }
	    else
		  {
	    	biofile = $('#file-2')[0].files[0];
		  
		  }
		  var formData = new FormData();
	  	  formData.append('biofile', biofile);
	  	  formData.append('aadhaarno',aadhaarno); 
	  	  formData.append('figimage',figimage); 
		
		if(aadhaarno !=null && figimage != null)
			{
			
			 $.ajax({
				 
				 type : "POST",
				 
				 url : "<c:url value="/esigningBio" />",
				 //url : "<c:url value="/biomatricsauth.html" />",
				data:formData,
				enctype:"multipart/form-data",
				processData: false,
				contentType: false,
				success : function(data) {
					//$('#preloader').removeClass('active');
					//window.location.replace(data);
				},
				error : function(e) {
					console.log("ERROR: ", e);
					display(e);
				}
				 
			
			 });
			
			}
		else
			{
			
			alert("There is something wrong");
			
			}
		
		
		}
		catch(err)
		{
			alert("Please capture fingure properly!");
		}
		
	});

});
</script>
        