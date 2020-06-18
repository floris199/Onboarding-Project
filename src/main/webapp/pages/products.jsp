<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="resources/css/user-style.css" >
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" >

        <c:set var="context" value="${pageContext.request.contextPath}" />

        <script src="resources/js/myjs.js" />
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </head>

    <body>
        <jsp:include page="header.jsp" />

        <div style = "display:flex">
        <form method="post" action="products" style="width: 20%">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th scope="col" class="no-top-border">Products</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="category" items="${sessionScope.categories}">
                    <tr ${sessionScope.selectedProductCategory == category.description ? 'class="highlight"' : ''}>
                        <td class="no-top-border">
                            <button name="selectedProductCategory" class="btn"
                                    value="${category.description}">${category.description}</button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </form>

        <form method="post" action="cart" style="width: 100%" class="margin-top80">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <input id="product_id" type="hidden" name="productId"/>
                        <input id="product_name" type="hidden" name="productName"/>
                        <input id="product_description" type="hidden" name="productDescription"/>
                        <input id="product_price" type="hidden" name="productPrice"/>

                        <table class="table table-bordered">
                            <tr>
                                <th>Article Name</th>
                                <th>Description</th>
                                <th>Price</th>
                                <th>Actions</th>
                            </tr>
                            <c:forEach var="product" items="${products}">
                                <tr>
                                    <td>${product.name}</td>
                                    <td>${product.description}</td>
                                    <td>${product.pricesEntity.price}</td>
                                    <td align="center">
                                        <button type="submit" class="add-cart btn" onclick="setProduct('${product.id}', '${product.name}', '${product.description}', '${product.pricesEntity.price}')"><i class="fa fa-cart-plus"></i>
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
        </form>
        </div>
    </body>

    <jsp:include page="footer.jsp" />
</html>
