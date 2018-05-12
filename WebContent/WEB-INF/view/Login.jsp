<%@ include file="template/loginheader.jsp" %>
  <div class="logininnermainDiv"> 
 
 
 
    <div class="row_left">
         <div class="inner-left-corner">
            
             <div id="image-content" style="margin-top: 34px;float: left;margin-left: 23px;">
             
             <img id="loader" src="<c:url value="/resources/images/authbridge-ls2.png" />" style="width: 736px;"/>
             </div>
             
             <div id="content" style="float: left;text-align: center;">
             
                <p style="color: #212121;font-size: 15px;font-weight: 300;line-height: 1.5;margin-top: 16px;width: 648px;text-align: justify;margin-left: 27px;">ESign facility which can be used to digitally sign your uploaded documents. This is similar to the process of self-attestation.  </p>
              
                <h1 style="float: left;margin-top: 34px;margin-bottom: 3px;margin-left: 26px;    font-weight: bold;font-size: 22px;">What is self-attestation? </h1>
                
                <p style="color: #212121;font-size: 15px;font-weight: 300;line-height: 1.5;margin-top: 16px;width: 648px;float: left;text-align: justify;margin-left: 27px;">Self Attestation is a method of verification of a document by the applicant himself/herself. It is done by taking a photocopy of a document and then self-certifying by affixing signature on it and writing true copy or self attested if required. Self Attestation empowers the owner of a document to vouch for its authenticity by affirmation. It saves valuable time and resources taken in finding officials who provide attestation and then actually getting them to attest it.  </p>             
             </div>
         
       
         </div>    
    </div>
 
 
 
 
   <div class="row"> 
    
     
       <div class="mainFormlogin">
       
         <span class="loginspan" ></span>
         
      
           <c:if test="${exerror ==1}">
                <script>
                   alert('Your Password Is Expired');
                   window.location = 'authpasschange';
                </script>
                
             </c:if>
                <%
           
                if (request.getParameter("succmessage") != null) {%>
                	 <p style="color: #68a26d;font-weight: bold;"><%= request.getParameter("succmessage") %></p>
                <% } %>
       
       
       <p  style="color: #f73030;font-weight: bold;">${error}</p>
       
       
       
          <h1 class="login-heading">Sign In to your Esign account</h1>
         
              
           
             
           
           <c:url var="loginForm" value="processLogin" />
           <form:form id="loginForm" name="myloginform" modelAttribute="loginuser" action="${loginForm}">
           
             <div class="mainaadharForm">
             
                  <div id="aadharnumber">
                          <form:label path="username" id="username">User Name</form:label>
                          <input type="text" name="username" id="labelaadhar_input"></input>
                  </div>
                  
                   <div id="aadharnumber">
                          <form:label path="password">Password</form:label>
                          <input type="password" name="password" id="labelaadhar_input" style="margin-left: 8px;"></input>
                   </div>
                   
                   
                     <div id="aadharnumber">
                       
                     
                         <span style="font-weight: bold;float: left;">Captcha</span>
                         <span style="float: left;margin-left: 22px;margin-top: -13px;">
                         
                             <img id="captcha_id" name="imgCaptcha" src="captcha.jpg" style="float:left;">
                         
                              <a href="javascript:;" title="change captcha text" onclick="document.getElementById('captcha_id').src = 'captcha.jpg?' + Math.random();  return false" style="float:left;margin-left: 7px;padding-top: 12px;">
							    <img src="resources/images/refresh.png" />
						      </a>
                         
                         </span>
                        
                     </div>
                     
                     <div id="aadharnumber" style="margin-left: 54px;">
                          <form:label path="captcha">Enter above Image text#</form:label>
                          
                          <input type="text" name="captcha" id="labelaadhar_input"></input>
                      </div>
                     
                     
                   
                    <div class="action_buttion1">
                   
                          <div class="action_buttion_auth_login" style="margin-left: 90px;">
                            <input type="submit" value="Login" id="inneraction_buttion_auth_login">
                          </div>
                     
                   </div>
             
             
             </div>
           
           </form:form>   
       
       </div>
       
     
    </div>   
       
       
    
    </div>
 
 <%@ include file="template/loginfooter.jsp" %>
 