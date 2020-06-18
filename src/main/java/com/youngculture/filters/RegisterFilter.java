package com.youngculture.filters;

import com.youngculture.services.impl.RegisterService;
import com.youngculture.services.intrf.RegisterServiceInterface;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegisterFilter implements Filter {

    private RegisterServiceInterface regService =  new RegisterService();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession(true);

        session = ((HttpServletRequest) servletRequest).getSession();
        session.setAttribute("registerErrorMsg", "");

        if(!req.getMethod().equalsIgnoreCase("POST")){
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String username = servletRequest.getParameter("registerUsername");
        String passwrd = servletRequest.getParameter("registerPasswrd");
        String confirmPasswrd = servletRequest.getParameter("registerConfirmPasswrd");

        String registerErrorMsg = regService.validateCredentials(username, passwrd, confirmPasswrd);

        if( !registerErrorMsg.equals( "" ) ) {
                session = ((HttpServletRequest) servletRequest).getSession();
                session.setAttribute("registerErrorMsg", registerErrorMsg);
                req.getRequestDispatcher("pages/register.jsp").forward(req, res);
                return ;
        }


        filterChain.doFilter(servletRequest, servletResponse);
    }
}
