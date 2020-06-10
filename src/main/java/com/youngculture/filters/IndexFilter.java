package com.youngculture.filters;

import com.youngculture.dao.impl.UserDAO;
import com.youngculture.entities.CartDetailsEntity;
import com.youngculture.entities.CategoryEntity;
import com.youngculture.entities.UserEntity;
import com.youngculture.services.CartService;
import com.youngculture.services.CategoryService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class IndexFilter implements Filter {

/*    public CategoryService categoryService = null;*/
    public CartService cartserv;
    public UserDAO userDAO;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        cartserv = new CartService();
/*        categoryService = new CategoryService();*/
        userDAO = new UserDAO();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession(true);

        session = ((HttpServletRequest) servletRequest).getSession();

        String username = (String)session.getAttribute("username");

        List<CategoryEntity> categories = (List<CategoryEntity>)session.getAttribute("categories");
        if( categories == null ) {
            cartserv.getCartQuantity(username);
            userDAO.get( username );
/*            categoryService.myMethod( username );*/
/*           *//* List<CategoryEntity> allCategories = categoryService.getAllCa*//*tegories();
            session.setAttribute("categories", allCategories);*/
        }

        if( username == null || username.isEmpty() )
        {
            String guestCartNeedsReset = (String) session.getAttribute("guestCartNeedsReset");

            if( guestCartNeedsReset == null || guestCartNeedsReset.isEmpty() ) {

                guestCartNeedsReset = "Y";
                session.setAttribute("guestCartNeedsReset", guestCartNeedsReset);
            }
        }

        Integer cartQuantity = (Integer)session.getAttribute("cartQuantity");
        if( cartQuantity == null )
        {
            if (username == null || username.isEmpty()) {
                session.setAttribute("cartQuantity", 0);
            }
            else
            {
                UserEntity user = new UserDAO().get( username ).get( 0 );

                if( user.getCartEntity() != null )
                {
                    for (CartDetailsEntity cartDetails : user.getCartEntity().getCartDetails()) {
                        cartQuantity += cartDetails.getQuantity();
                    }
                }
                session.setAttribute("cartQuantity", cartQuantity );
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
