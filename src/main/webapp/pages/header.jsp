<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script src="resources/js/myjs.js"></script>

<c:set var="cartQuantity" value="0" scope="session"/>
<c:forEach items="${cart}" var="item">
    <c:set var="cartQuantity" value="${cartQuantity + item.value}" scope="session"/>
</c:forEach>

<c:set var="context" value="${pageContext.request.contextPath}" />

<nav class="navbar navbar-expand-lg navbar-light bg-light flex-nowrap">
    <a class="navbar-brand" href="#">
        <img src="resources/img/logo.png" alt="obp" height="50">
    </a>

    <div class="collapse navbar-collapse w-100 justify-content-center" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="${context}">Home<span class="sr-only"></span></a>
            </li>
            <c:if test="${sessionScope.username != null}" >
                <li class="nav-item">
                    <a class="nav-link" href="${context}/order">My Orders<span class="sr-only"></span></a>
                </li>
            </c:if>
            <c:if test="${sessionScope.username == null}" >
                <li class="nav-item">
                    <a class="nav-link" href="${context}/login">Log in<span class="sr-only"></span></a>
                </li>
            </c:if>
            <c:if test="${sessionScope.username == null}" >
                <li class="nav-item">
                    <a class="nav-link" href="${context}/register">Register</a>
                </li>
            </c:if>
            <c:if test="${sessionScope.username != null}" >
                <li class="nav-item">
                    <a class="nav-link" href="${context}/logout">Log out</a>
                </li>
            </c:if>
        </ul>

        <ul class="navbar-right">
            <li class="nav-item" ><a href="${context}/cart" class="mini-cart" id="cart"><i class="fa fa-shopping-cart"></i> Cart <span class="badge">${sessionScope.cartQuantity}</span></a></li>
        </ul> <!--end navbar-right -->

    </div>
</nav>

