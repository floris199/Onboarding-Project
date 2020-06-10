package com.youngculture.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class OrderServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        PrintWriter needsRedirect=response.getWriter();

        HttpSession session = request.getSession();

        String username = (String)session.getAttribute("username");

        if(username == null || username.isEmpty() )
        {
            session.setAttribute("redirectToCart", "Y");
            needsRedirect.print("Y");
        }
        else
        {
            needsRedirect.print("N");
        }
        needsRedirect.flush();
        needsRedirect.close();
    }
}
