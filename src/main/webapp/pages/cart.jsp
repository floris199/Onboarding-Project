<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="resources/css/user-style.css" >
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

    <script src="resources/js/myjs.js" />
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>

<body>
    <jsp:include page="header.jsp" />

    <c:if test="${sessionScope.cartQuantity > 0}" >
        <div class="container" id="cartDiv">
            <div class="row">
                <div class="col-12">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>Description</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Sub Total</th>
                            <th>Option</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:set var="total" value="0"></c:set>
                        <c:forEach var="item" items="${sessionScope.cart}">
                            <c:set var="total" value="${total + item.key.pricesEntity.price * item.value}"></c:set>
                            <tr id="tr${item.key.id}">
                                <td>${item.key.description}</td>
                                <td id="price${item.key.id}">${item.key.pricesEntity.price}</td>
                                <td id="quantity${item.key.id}">${item.value}</td>
                                <td id="sub-total${item.key.id}">${item.key.pricesEntity.price * item.value}</td>
                                <td>
                                    <button value="Remove" class="btn"
                                            onclick="ajaxCalls.remove('${item.key.id}')"><i class="fa fa-arrow-down" aria-hidden="true"></i></button>
                                    <button value="Add" class="btn"
                                            onclick="ajaxCalls.add('${item.key.id}')"><i class="fa fa-arrow-up" aria-hidden="true"></i></button>
                                </td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td colspan="6" align="right">Total</td>
                            <td id="totalPrice">${total}</td>
                        </tr>
                        </tbody>
                    </table>

                    <button id="orderButton" class="btn btn-light" type="submit" ${sessionScope.cartQuantity == 0 ? 'disabled' : ''}
                            onclick="ajaxCalls.order()">Order</button>
                </div>
            </div>
        </div>
    </c:if>

    <div id="emptyCartDiv" class="box ${sessionScope.cartQuantity > 0 ? 'hide' : ''}">
        <h4 align="center" class="margin-top80" id="emptyCartLabel">Your cart is empty. :(</h4>
    </div>
    <jsp:include page="footer.jsp" />
</body>
</html>
