<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="resources/css/user-style.css" >
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

    <c:set var="context" value="${pageContext.request.contextPath}" />

    <script src="resources/js/myjs.js" />
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</head>

<body>
    <jsp:include page="header.jsp" />

    <div class="container">
        <div class="row">
            <div class="col-12">
                <table class="table ${requestScope.orders.size() > 0 ? '' : 'hide'}">
                    <thead>
                    <tr>
                        <th>Order Number</th>
                        <th>Products</th>
                        <th>Price</th>
                        <th>Date</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:set var="total" value="0"></c:set>
                    <c:forEach var="order" items="${requestScope.orders}">
                        <tr>
                            <td>${order.id}</td>
                            <td>
                                <c:set var="subTotal" value="0"/>
                                <c:forEach var="item" items="${order.products}">
                                    <pre>${item.productName}:  ${item.quantity} x ${item.productPrice}$</pre>
                                    <c:set var="subTotal" value="${subTotal + item.productPrice * item.quantity}" />
                                </c:forEach>
                            </td>
                            <td>${subTotal}</td>
                            <td>${order.createdAt}</td>
                        </tr>
                        <c:set var="total" value="${total + subTotal}" />
                    </c:forEach>
                    <tr>
                        <td colspan="6" align="right">Total</td>
                        <td>${total}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <div id="emptyOrdersDiv" class="box ${requestScope.orders.size() > 0 ? 'hide' : ''}">
        <h4 align="center" class="margin-top80" id="emptyOrdersLabel">You have no orders. :(</h4>
    </div>

    <t:footer />
</body>
</html>
