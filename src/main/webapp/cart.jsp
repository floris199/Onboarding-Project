<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="resources/css/user-style.css" >
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

    <c:set var="context" value="${pageContext.request.contextPath}" />

    <script src="../resources/js/myjs.js" />
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script src="https://cdn.jsdelivr.net/gh/vast-engineering/jquery-popup-overlay@2/jquery.popupoverlay.min.js"></script>
</head>

<body>
    <jsp:include page="header.jsp" />

    <c:if test="${sessionScope.cartQuantity > 0}" >
        <div class="container" id="cartDiv">
            <div class="row">
                <div class="col-12">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>Name</th>
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
                                <td>${item.key.name}</td>
                                <td id="price${item.key.id}">${item.key.pricesEntity.price}</td>
                                <td id="quantity${item.key.id}">${item.value}</td>
                                <td id="sub-total${item.key.id}">${item.key.pricesEntity.price * item.value}</td>
                                <td align="center">
                                    <button value="Remove"
                                       onclick="removeFromCart('${item.key.id}')">Remove</button>
                                </td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td colspan="6" align="right">Total</td>
                            <td id="totalPrice">${total}</td>
                        </tr>
                        </tbody>
                    </table>

                    <button id="orderButton" type="submit" ${sessionScope.cartQuantity == 0 ? 'disabled' : ''} onclick="order()">Order</button>
                </div>
            </div>
        </div>
    </c:if>

    <div id = "dialog-1"
         title = "Confirmation">Your order has been placed.</div>

    <h4 align="center" id="emptyCartLabel" ${sessionScope.cartQuantity > 0 ? 'class="hide"' : ''}>Your cart is empty. :(</h4>

    <jsp:include page="footer.jsp" />
</body>
</html>
