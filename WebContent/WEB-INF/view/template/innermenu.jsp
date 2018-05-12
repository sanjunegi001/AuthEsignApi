<div class="menuHeader">
           <div class="authLogo">
            
            
           <span> 
           
           <img style="float: left; padding-top: 1px; padding-left: 2px; background-color: rgb(255, 255, 255); margin-left: 3px; margin-top: 3px; height: 72px; border-radius: 3px;" src="./resources/images/logo.png">
             
          </span>
          
          
          <div class="head_menu_right">
      
              <% if(session.getAttribute("user_login_name") != null) { %>
               
                        You Are Logged In As <span  style="margin-top: 10px;margin-right: 91px;font-weight: bold;font-size: 15px;"><%= session.getAttribute("user_login_name")%> </span>
                <% } %>
      
            
            <div class="inner_ineer_menu">
                 
                  <div class="inner_inner_menu_left">
                    <span>
                       <a href="<c:url value="/cpassword"/>">Change Password</a>
                    </span>
                    
                  </div>
                 
                  <span style="float: left;"> &#124;</span>
                  <div class="inner_inner_menu_right">
                   <span>
                   
                      <a href="<c:url value="/logout"/>">Logout</a>
                   </span>
                  
                  </div>
                 
            
            </div>
          </div>  
            
            </div>
             <!--Menu Code -->
          
            <div class="mainMenu">
                <ul style="float: left;">
                 
               
                   <li id="insideLi" style="width:150px;">
                     <a href="<c:url value="/esign"/>">Esign</a>
                  </li>
               	  <li id="insideLi" style="width:150px;">
                     <a href="<c:url value="/document"/>">Esign Documents</a>
                  </li>
              	
            </div>
            
         <!-- End Menu Code -->  
         </div>
         
         
         <script type="text/javascript">
     $(function (){

    $('.mainMenu ul li a').each(function(){
        var path = window.location.href;
        var current = path.substring(path.lastIndexOf('/')+1);
        var url = $(this).attr('href').substring($(this).attr('href').lastIndexOf('/')+1);
        
        
        if(url == current){
        	
            $(this).addClass('activemenu');
        };
    });         
});

</script>