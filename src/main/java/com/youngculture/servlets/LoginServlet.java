package com.youngculture.servlets;

import com.youngculture.entities.CartEntity;
import com.youngculture.entities.ProductsEntity;
import com.youngculture.services.CartService;
import com.youngculture.services.LoginService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class LoginServlet extends HttpServlet {

    private LoginService loginService = new LoginService();
    private CartService  cartService =  new CartService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("loginErrorMsg", "");

        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String username = request.getParameter("loginUsername");
        String passwrd = request.getParameter("loginPasswrd");
        String loginErrorMsg = loginService.validateCredentials( username, passwrd );

        if( !loginErrorMsg.isEmpty() )
        {
            session.setAttribute("loginErrorMsg", loginErrorMsg);
            RequestDispatcher view = request.getRequestDispatcher("login.jsp");
            view.forward(request, response);

            return;
        }

        session.setAttribute("username", username);

        Map<ProductsEntity, Integer> cart = (HashMap) session.getAttribute("cart");

        cartService.mergeAndSaveCart( cart, username);

        String redirectToCart = (String)session.getAttribute("redirectToCart" );
        if( redirectToCart != null && !redirectToCart.isEmpty() ) {
            if (redirectToCart.equals("Y"))
            {
                RequestDispatcher view = request.getRequestDispatcher("cart.jsp");
                view.forward(request, response);
            }
        }
        RequestDispatcher view = request.getRequestDispatcher("index.jsp");
        view.forward(request, response);
    }

}
