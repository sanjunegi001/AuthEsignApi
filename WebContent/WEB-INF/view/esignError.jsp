<%@ include file="template/header.jsp" %>



   <div class="mainDiv">
     
     
         <div class="innermainDiv">
        <%@ include file="template/redirectinnermenu.jsp" %>
         
         
         <div class="mainForm2">
         
          
          <div class ="mainInnerForm">
          
             <div>
             
                  <table style="margin-left:auto;margin-right:auto">
                    <tr>
                        <td style="height: 96px;border-collapse: collapse;border-spacing: 0;float: left;">
                          <img style="width:96px;height:96px;" src="<c:url value="/resources/images/error.png"/>" />
                       </td>
                       <td style="margin-top: 24px;margin-left: 19px;float: left;">
                          <table>
                                <tr>
                                   <td><span style="font-weight:bold ;font-size: 16px;">TransactionId:</span></td>
                                   <td><span style="color: #797272;font-weight: bold;font-size: 14px;padding-left:2px;"><c:out value="${esigntransactionnm}"/></span></td>
                                </tr>
                             	<tr>
                                    <td><span style="font-weight:bold;font-size: 16px;">ErrorCode:</span></td>
                                    <td><span style="color: red;font-weight: bold;font-size: 14px;"><c:out value="${error}"/></span></td>
                                </tr> 
                                <tr>
                                    <td><span style="font-weight:bold;font-size: 16px;">Message:</span></td>
                                    <td><span style="color: red;font-weight: bold;font-size: 14px;"><c:out value="${message}"/></span></td>
                                </tr>   
                               
                           </table>
                       </td>
                   </tr>
               </table>
   
             
                 
    
    
</div>
      </br></br>
      <div class="form-group">
                        <div class="col-md-offset-2 col-md-11">
                          <input class="btn btn-default btnesign" id="btnBack" type="button" onclick="backup()" value="Back">
                        </div>
       </div>
         
</div>  
         
         
 </div>        
</div>  
         
         
         
</div>
     
<script type="text/javascript">
function backup()
{
	
	window.close();
	

}
</script>     
  <%@ include file="template/footer.jsp" %>

