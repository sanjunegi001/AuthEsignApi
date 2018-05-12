<%@ include file="template/header.jsp" %>


  <script type="text/javascript">
   
  
     
     function ischkAadhaarStatus()
     {
    	
    	
    	 var txtAadhaarValue = $('#txtAadhaarNo').val();
    	 if($.trim(txtAadhaarValue).length < 12) {
    	    	alert("Invalid Aadhaar Number");
    	       
    	        document.getElementById('btnProceed').disabled = true;
    	        return false;
    	    }
    	 
    	 if($.trim(txtAadhaarValue).length > 12) {
    	    	alert("Invalid Aadhaar Number");
    	        document.getElementById('btnProceed').disabled = true;
    	        return false;
    	    }
    	 
    	 
    	 
    	 var isAadhaarNumberValid = checkverhoff($('#txtAadhaarNo').val());
    	 
    	 if (isAadhaarNumberValid === false) {
    	        document.getElementById('btnProceed').disabled = true;
    	        alert("Invalid Aadhaar Number");
    	    
    	        return false;
    	    }
    
    	 else
    		{
    	       return true;
    	  	} 
    	 
    	 
    	 
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



   <div class="mainDiv">
   
   
     <div class="innermainDiv">
       
         <%@ include file="template/innermenu.jsp" %>
   
    <div class="mainForm1">
       <!--Main Form-->
       
         <form>
            <div class="mainaadharForm">
            
                <div class="form-group">
                        <label class="col-md-2 control-label">Aadhaar Number</label>
                         <div class="col-md-10">
                                    <input type="text" id="txtAadhaarNo" name="txtAadhaarNo" class="form-control" placeholder="Enter Aadhaar number" />
                         </div>
                 </div>
              
            
                 <div class="form-group">
                        <div class="col-md-offset-2 col-md-11">
                          <input class="btn btn-default" id="btnProceed" type="button" onclick="onProceedEkyc()" value="Proceed">
                        </div>
                  </div>
                 
            
            
            
            
            
            </div>
         
         </form>
       
       
   </div>
       <!--End Main Form-->
   
   
   <script type="text/javascript">
      
   function onProceedEkyc()
   {
	   var isAadhaar = ischkAadhaarStatus();
	   
	   if(isAadhaar == true)
		   {
		   
		   var aadhaarno     = $('#txtAadhaarNo').val();
			  
		   alert("Hii"+aadhaarno);
		 
			   
			 // var redirect = '/EkycProcess.html';
			    
			   $.ajax({
					
					type : "POST",
					url : "<c:url value="/EkycProcess.html" />",
					processData: false,
					data: "txtAadhaarNo=" + aadhaarno,
					
					success : function(data) {
						
						
						var resultfinal = JSON.parse(data);
						var form = '';
						 form += '<input type="hidden" name="ECS_WEBAPI_DATA" value="'+resultfinal.wdata+'">';
						
						 $('<form action="http://115.111.25.104:8080/EcsWebApiV2/otp.jsp" method="POST">'+form+'</form>').submit();
						
						 $('body').append(form);
						  $(form).submit();
						 
						 alert("Hello"+form);
					},
					error : function(e) {
						console.log("ERROR: ", e);
						display(e);
					}
					
					
					
				});
			   
		   
		   
		   }
	   
	 
	   
   }
   
   </script>
   
   
   
   
   
   
      </div>
     
     
     
        <%@ include file="template/footer.jsp" %>