<%@ include file="template/loginheader.jsp" %>
 
  <div class="logininnermainDiv" style="padding-bottom: 6px;"> 
 
   <div class="row"> 
    
     
       <div class="mainFormlogin" style="width:400px;">
       
         <span class="loginspan" ></span>
         
            
       
       <p  style="color: #f73030;font-weight: bold;">${error}</p>
       
       
       
          <h1 class="login-heading" style="color: #3eaf47;font-family: normal;font-size: 20px;font-weight: bold;">Success Message</h1>
         
            <p style="color: #fff;font-weight: bold;font-size: 16px;background-color: #47c747;height: 60px;padding-top: 15px;">
            
            You have successfully changed your password.You can login as with your username and new your password.
            
            </p>
            
             <a href="<c:url value="/login"/>" style="color: black;text-decoration: none;font-weight: bold;font-size: 20px;background-color: #e6eaa5;width: 100px;float: right;margin-top: 14px;margin-bottom: 15px;border-radius: 5px;">Login</a>
       
       </div>
       
     
    </div>   
       
       
    
    </div>
    
    
  <%@ include file="template/loginfooter.jsp" %>