package com.youngculture.servlets;

import com.youngculture.entities.ProductsEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

public class LogoutServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("username" );
        session.setAttribute("cart", new HashMap<ProductsEntity, Integer>() );
        session.removeAttribute("selectedProductCategory" );
        session.removeAttribute("products" );

/*        session.invalidate();
        session.setAttribute("cart", new HashMap<ProductsEntity, Integer>() );*/
        RequestDispatcher view = request.getRequestDispatcher("pages/index.jsp");
        view.forward(request, response);
    }
}
