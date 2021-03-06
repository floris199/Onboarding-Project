package com.youngculture.servlets;

import com.youngculture.services.impl.RegisterService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {

    private RegisterService regService = new RegisterService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("registerErrorMsg", "");

        RequestDispatcher dispatcher = request.getRequestDispatcher("pages/register.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String username = request.getParameter("registerUsername");
        String passwrd = request.getParameter("registerPasswrd");

        String registerErrorMsg = regService.registerUser( username, passwrd );

        if( !registerErrorMsg.isEmpty() )
        {
            session.setAttribute("registerErrorMsg", registerErrorMsg);
            RequestDispatcher dispatcher = request.getRequestDispatcher("pages/register.jsp");
            dispatcher.forward(request, response);
            return;
        }
        session.setAttribute("username", username);

        RequestDispatcher dispatcher = request.getRequestDispatcher("pages/index.jsp");
        dispatcher.forward(request, response);
    }
}
