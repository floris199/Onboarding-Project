package com.youngculture.servlets;

import com.youngculture.entities.PricesEntity;
import com.youngculture.entities.ProductsEntity;
import com.youngculture.services.impl.CartService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CartServlet extends HttpServlet {

    private CartService cartService = new CartService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        RequestDispatcher dispatcher = request.getRequestDispatcher("pages/cart.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Integer productId = Integer.valueOf( request.getParameter("productId" ) );
        String productName = request.getParameter("productName" );
        String productDescription = request.getParameter("productDescription" );
        Integer productPrice = Integer.valueOf( request.getParameter("productPrice" ) );
        String username =  (String)session.getAttribute( "username" ) ;

        PricesEntity price = new PricesEntity();
        price.setPrice( productPrice );

        ProductsEntity product = new ProductsEntity();
        product.setId( productId );
        product.setDescription( productDescription );
        product.setName( productName );
        product.setPricesEntity( price );

        Map<ProductsEntity, Integer> cart = (HashMap)session.getAttribute("cart");
        session.setAttribute( "cart", cartService.updateCartContent( product, cart, username ) );

        if( username != null && !username.isEmpty() ) {
            cartService.mergeAndSaveCart(cart, username);
        }

        response.sendRedirect("products");
    }

}
