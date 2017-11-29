<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/custom/loginstyles.css">
    <script src="${pageContext.request.contextPath}/resources/custom/loginscripts.js" type="text/javascript"></script>

    <body>

    <center style="margin-top: 18%"><h2>Please Login into System</h2></center>

    <center><button onclick="document.getElementById('id01').style.display = 'block'" style="width:auto;">Please Login</button></center>

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
                <input type="checkbox" checked="checked"> Remember me
            </div>

            <div class="container" style="background-color:#f1f1f1">
                <button type="button" onclick="document.getElementById('id01').style.display = 'none'" class="cancelbtn">Cancel</button>
                <span class="psw">Forgot <a href="#">password?</a></span>
            </div>
        </form:form>
    </div>

</body>
</html>
