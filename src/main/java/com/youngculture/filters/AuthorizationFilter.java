package com.youngculture.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthorizationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession(true);

        session = ((HttpServletRequest) servletRequest).getSession();
        
        String username = (String)session.getAttribute("username");

        if( username == null || username.isEmpty() ) {
            req.getRequestDispatcher("pages/login.jsp").forward(req, res);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
