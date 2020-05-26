<%--
  Created by IntelliJ IDEA.
  User: flco3108
  Date: 5/25/2020
  Time: 3:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>

    <jsp:include page="header.jsp" />

    <div class="container login-container">
        <div class="col-md-6 login-form-1">
            <h3>Login for Form 1</h3>
            <form>
                <div class="form-group">
                    <input id="username" type="text" class="form-control" placeholder="Username" value="" />
                </div>
                <div class="form-group">
                    <input id="passwrd" type="password" class="form-control" placeholder="Password" value="" />
                </div>
                <div class="form-group">
                    <input type="submit" class="btnSubmit" value="Login" />
                </div>
            </form>
        </div>
    </div>

    <jsp:include page="footer.jsp" />
</body>
</html>
