<%@ include file="template/header.jsp" %>
<div class="mainDiv">
   
   
     <div class="innermainDiv">
       
         <%@ include file="template/innermenu.jsp" %>
   
    <div class="mainForm1">
    
    <div style="clear: both;float: left;width: 812px;margin-top: 13px;margin-left: 30px;"> 
            <table style="width: 800px;line-height: 26px;">
                  
                 
  
                     <th style="font-weight:bold;color: black;width: 100px;">NAME</th>
                     <th style="font-weight:bold;color: black;">CREATED</th>
                    
                     <th style="font-weight:bold;color: black;">SIZE</th>
                    
                  
                  <c:if test="${model.employee.size() eq 0}">
       				<tr style="border-top: 1px solid #ccc;">
       				<td></td>
       				<td><H5>No Document Found</H5></td>
       				</tr>
       
       			</c:if>
                  
                  <c:forEach items="${model.employee}" var="element">
      
       
                  
                    <tr style="border-top: 1px solid #ccc;">
                     <td>
                      <c:out value="${element.name}" />
                     </td>
                     <td>
                       <c:out value="${element.date}" />
                     </td>
                   
                      <td>
                       <c:out value="${element.fsize}" />KB
                      </td>
                     
                     <td>
                       <a href="download?dfpath=<c:out value="${element.docs}" />">Download</a>
                     </td>
                     
                     <td>
                       <a href="<c:out value="${element.id}"/>" id="deleteEsign">Delete</a>
                     </td>
                     
                 </tr>
      
       </c:forEach>
       
             <tr style="border-top: 1px solid #ccc;">
                     <td colspan="6"></th>
                     
                  </tr> 
       
       
          </table>   
     </div> 
    
    </div>
    </div>
    </div>
    
 <%@ include file="template/footer.jsp" %> 
 
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