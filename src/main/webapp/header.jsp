<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav class="navbar navbar-expand-lg navbar-light bg-light flex-nowrap">
    <a class="navbar-brand" href="#">
        <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/b/be/Lineage_OS_Logo.png/800px-Lineage_OS_Logo.png" alt="obp" height="50">
    </a>

    <div class="collapse navbar-collapse w-100 justify-content-center" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="${context}/login">Login 2<span class="sr-only"></span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${context}/Register">Register</a>
            </li>
        </ul>
    </div>
</nav>

