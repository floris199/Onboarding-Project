package com.youngculture.servlets;

import com.youngculture.entities.ProductsEntity;
import com.youngculture.services.CartService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AjaxServlet extends HttpServlet {

    private CartService cartService = new CartService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Integer productId = Integer.valueOf( request.getParameter("productId" ) );
        Map<ProductsEntity, Integer> cart = (HashMap)session.getAttribute("cart" );

        cartService.removeItemFromCart( cart, productId );

        session.setAttribute("cart", cart);
        session.setAttribute("cartQuantity", (Integer)session.getAttribute("cartQuantity" ) - 1);
        RequestDispatcher view = request.getRequestDispatcher("cart.jsp");
        view.forward(request, response);
    }
}
