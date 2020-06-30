package com.youngculture.servlets;

import com.youngculture.dto.CartDTO;
import com.youngculture.dto.ProductDTO;
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
import java.util.List;
import java.util.Map;

public class CartServlet extends HttpServlet {

    private CartService cartService = new CartService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute( "username" );
        Map<Integer, Integer> cart = (HashMap)session.getAttribute("cart");

        CartDTO cartDTO = cartService.buildCart( username, cart );
        session.setAttribute( "cartDTO", cartDTO );

        RequestDispatcher dispatcher = request.getRequestDispatcher("pages/cart.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Integer productId = Integer.valueOf( request.getParameter("productId" ) );
        String username =  (String)session.getAttribute( "username" ) ;


        Map<Integer, Integer> cart = (HashMap)session.getAttribute("cart");
        session.setAttribute( "cart", cartService.updateCartContent( productId, cart, username ) );

        response.sendRedirect("products");
    }

}
