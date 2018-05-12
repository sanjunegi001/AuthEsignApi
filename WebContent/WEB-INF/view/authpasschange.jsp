<%@ include file="template/loginheader.jsp" %>
       
       
       <script type="text/javascript">

  function checkForm(form)
  {
	  
	
	  var newpassword = document.getElementsByName('newpassword');
	  var confirmpass = document.getElementsByName('confirmpass');
	  
	  var username = document.getElementsByName('chusername');
	  var oldpass  = document.getElementsByName('oldpassword');
	  
	  var captcha  = document.getElementsByName('captcha');
	  
	 
	  
	if(username[0].value ==null || username[0].value =="")
		{
		 alert("Error: Username Is Required");
		
		 return false;
		}
	  
	if(oldpass[0].value == null || oldpass[0].value =="")
		{
		 alert("Error: Old Password Is Required");
		 return false;
		}
	
	 if(newpassword[0].value == null || newpassword[0].value=="")
	  {
		 alert("Error: New Password Is Required");
		 return false;
	  }
	
    if(newpassword[0].value != null && newpassword[0].value == confirmpass[0].value){
    	
      
    	
      if(newpassword[0].value.length < 6) {
        alert("Error: New Password must contain at least six characters!");
        return false;
      }
     
      
      var re = /[0-9]/;
      if(!re.test(newpassword[0].value)) {
    	  
        alert("Error: New password must contain at least one number (0-9)!");
        return false;
      }
      var re = /[a-z]/;
      if(!re.test(newpassword[0].value)) {
        alert("Error: New password must contain at least one lowercase letter (a-z)!");
        return false;
      }
      var re = /[A-Z]/;
      if(!re.test(newpassword[0].value)) {
        alert("Error: New password must contain at least one uppercase letter (A-Z)!");
        return false;
      }
    } else {
      alert("Error: Please check that you've entered and confirmed your password!");
      return false;
    }

    if(captcha[0].value == null || captcha[0].value =="")
	{
	   alert("Error: Captcha Field Is Required");
	   return false;
	}
   
    return true;
  }

</script>
       
 
 
  <div class="logininnermainDiv" style="padding-bottom: 6px;"> 
 
   <div class="row"> 
    
     
       <div class="mainFormlogin" style="width:400px;">
       
         <span class="loginspan" ></span>
         
            
       
       <p  style="color: #f73030;font-weight: bold;">${error}</p>
       
       
       
          <h1 class="login-heading">ChangePassword</h1>
         
              
            <c:url var="passForm" value="processchangepass" />
           <form:form id="loginForm" name="passform" modelAttribute="changepassuser" action="${passForm}" onsubmit="return checkForm(this);">
             
              
                  <div id="aadharnumber">
                          <form:label path="chusername" id="username" style="float:left;width:115px;">User Name</form:label>
                          <input type="text" name="chusername" id="labelaadhar_input"></input>
                  </div>
                  
                   <div id="aadharnumber">
                          <form:label path="oldpassword" style="float:left;width:115px;">OldPassword</form:label>
                          <input type="password" name="oldpassword" id="labelaadhar_input"></input>
                   </div>
                   
                   <div id="aadharnumber">
                          <form:label path="newpassword" style="float:left;width:115px;">NewPassword</form:label>
                          <input type="password" name="newpassword" id="labelaadhar_input"></input>
                   </div>
                   
                   <div id="aadharnumber">
                          <form:label path="confirmpass" style="float:left;width:115px;">ConfirmPassword</form:label>
                          <input type="password" name="confirmpass" id="labelaadhar_input"></input>
                   </div>
                   
                   
                   <div id="aadharnumber">
                       
                     
                         <span style="float:left;width:115px;font-weight: bold;">Captcha</span>
                         <span style="float: left;margin-left: 12px;margin-top: -13px;">
                         
                             <img id="captcha_id" name="chimgCaptcha" src="captcha.jpg" style="float:left;">
                         
                              <a href="javascript:;" title="change captcha text" onclick="document.getElementById('captcha_id').src = 'captcha.jpg?' + Math.random();  return false" style="float:left;margin-left: 7px;padding-top: 12px;">
							    <img src="resources/images/refresh.png" />
						      </a>
                         
                         </span>
                        
                     </div>
                   
                   
                   
                    <div id="aadharnumber">
                          <form:label path="captcha" style="float:left;width:115px;">Enter above Image text#</form:label>
                          
                          <input type="text" name="captcha" id="labelaadhar_input"></input>
                     </div>
                     
                   
                   
                   
          
                  <div class="action_buttion1">
                   
                          <div class="action_buttion_auth_login">
                            <input type="submit" value="Update Password" id="inneraction_buttion_auth_login" style="width:135px;margin-left: -67px;">
                          </div>
                     
                   </div>
          
          
          
           </form:form>
           
             
           
          
       
       </div>
       
     
    </div>   
       
       
    
    </div>
    
  <%@ include file="template/loginfooter.jsp" %>