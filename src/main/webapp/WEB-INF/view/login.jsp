<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/custom/loginstyles.css">
    <script src="${pageContext.request.contextPath}/resources/custom/loginscripts.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/resources/alert/sweetalert-dev.js"></script> 
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/alert/sweetalert.css">
    <body>
	    <center style="margin-top: 18%"><h2>Please Login into System</h2></center>
	    <center><button id="login-btn" onclick="document.getElementById('id01').style.display = 'block'" style="width:auto;">Please Login</button></center>
	    <div id="id01" class="modal">
	        <form:form class="modal-content animate" method="post" commandName="loginbean" action="${pageContext.request.contextPath}/check-login.html">
	            <div class="imgcontainer">
	                <span onclick="document.getElementById('id01').style.display = 'none'" class="close" title="Close Modal">&times;</span>
	                <img src="${pageContext.request.contextPath}/resources/img/users/login_avatar.png" alt="Avatar" class="avatar">
	            </div>
	            <div class="container">
	                <label><b>Username</b></label>
	                <input type="text" placeholder="Enter Username" name="userName" required>
	                <label><b>Password</b></label>
	                <input type="password" placeholder="Enter Password" name="password" required>
	                <button type="submit">Login</button>
	                <center><b style="color: #f44336">${checkLogin}</b></center><br>
	                <input type="checkbox" checked="checked"> Remember me
	            </div>
	            <div class="container" style="background-color:#f1f1f1">
	                <button type="button" onclick="document.getElementById('id01').style.display = 'none'" class="cancelbtn">Cancel</button>
	                <span class="psw"><a onclick="forgetPW()">Forgot password?</a></span>
	            </div>
	        </form:form>
	    </div>
	</body>
</html>
<script type="text/javascript">
    window.onload = function () { //first load page
        var checkLogin = '${checkLogin}';
        if(checkLogin == 'Invalid username or password!') {
        	document.getElementById('login-btn').click();
        }
    };
    
    function forgetPW() {
    	swal({
    		  title: "Forget Password!",
    		  text: "Please Enter your email",
    		  type: "input",
    		  showCancelButton: true,
    		  closeOnConfirm: false,
    		  animation: "slide-from-top",
    		  inputPlaceholder: "Enter your email"
    		},
    		function(inputValue){
    		  if (inputValue === false) return false;

    		  if (inputValue === "") {
    		    swal.showInputError("You need to write email here!");
    		    return false;
    		  } else if(!isValidEmail(inputValue)) {
    			swal.showInputError("Email is invalid!");
      		    return false;
    		  } else {
   			   swal("Done!", "Please check your email: " + inputValue + " for new password", "success");
       		  
       		   location.href="${pageContext.request.contextPath}/forget-password/" + inputValue + ".htm";
   			   return true;
    		  }
    		 
    		}
    	);
    }
    
    function isValidEmail(email) {
        var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(email.toLowerCase());
    }
</script>