
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="resources/css/user-style.css" >

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</head>
<body>
    <jsp:include page="header.jsp" />

    <div class="flex-center">
        <div class="container login-container flex-center">
            <div class="col-md-6 login-form-1">
                <h3>Register</h3>
                <form method="post" action="register">
                    <div class="form-group">
                        <input id="username" name="registerUsername" type="text" class="form-control" placeholder="Username" value="${param.registerUsername}" />
                    </div>
                    <div class="form-group">
                        <input id="passwrd" name="registerPasswrd" type="password" class="form-control" placeholder="Password" value="${param.registerPasswrd}" />
                    </div>
                    <div class="form-group">
                        <input id="confirmPasswrd" name="registerConfirmPasswrd" type="password" class="form-control" placeholder="Confirm password" value="${param.registerConfirmPasswrd}" />
                    </div>
                    <div class="form-group">
                        <input type="submit" class="btnSubmit" value="Register" />
                    </div>
                </form>
                <c:if test = "${registerErrorMsg != null || registerErrorMsg!=''}">
                    <p><c:out value = "${registerErrorMsg}"/><p>
                </c:if>
            </div>
        </div>
    </div>

    <jsp:include page="footer.jsp" />
</body>
</html>
