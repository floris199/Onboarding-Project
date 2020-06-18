package com.youngculture.servlets;

import com.youngculture.entities.OrdersEntity;
import com.youngculture.entities.ProductsEntity;
import com.youngculture.services.impl.CartService;
import com.youngculture.services.impl.OrderService;
import com.youngculture.services.intrf.CartServiceInterface;
import com.youngculture.services.intrf.OrderServiceInterface;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderServlet extends HttpServlet {

    private CartServiceInterface cartService;
    private OrderServiceInterface orderService;

    public void init(ServletConfig config)
    {
        cartService = new CartService();
        orderService = new OrderService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String username = (String)session.getAttribute( "username" );

        List<OrdersEntity> ordersEntityList =  orderService.getAllOrders( username );
        request.setAttribute( "orders", ordersEntityList );
        RequestDispatcher dispatcher = request.getRequestDispatcher("pages/orders.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        PrintWriter needsRedirect=response.getWriter();

        HttpSession session = request.getSession();

        String username = (String)session.getAttribute( "username" );
        Map<ProductsEntity, Integer> cart = (HashMap)session.getAttribute( "cart" );

        if( username == null || username.isEmpty() )
        {
            session.setAttribute("redirectToCart", "Y");
            needsRedirect.print("Y");
        }
        else
        {
            orderService.saveOrder( username );
            cartService.removeCart( username);

            cart = new HashMap<>();
            session.setAttribute("cart", cart );

            needsRedirect.print("N");
        }
        needsRedirect.flush();
        needsRedirect.close();
    }
}
