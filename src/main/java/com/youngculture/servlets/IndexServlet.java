package com.youngculture.servlets;

import com.youngculture.dao.impl.CategoryDAO;
import com.youngculture.dao.impl.UserDAO;
import com.youngculture.dao.intrf.DAOInterface;
import com.youngculture.entities.CartDetailsEntity;
import com.youngculture.entities.CategoryEntity;
import com.youngculture.entities.ProductsEntity;
import com.youngculture.entities.UserEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IndexServlet extends HttpServlet {

/*    private DAOInterface<CategoryEntity> categoryDAO;

    public void init(ServletConfig config)
    {
        categoryDAO = new CategoryDAO();
    }*/


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();

        String username = (String)session.getAttribute("username");

        List<CategoryEntity> categories = (List<CategoryEntity>)session.getAttribute("categories");
        if( categories == null )
        {
            List<CategoryEntity> allCategories = new CategoryDAO().getAll();
            session.setAttribute("categories", allCategories);
        }

        Map<ProductsEntity, Integer> cart = (HashMap)session.getAttribute("cart");
        if( cart == null )
        {
            cart = new HashMap<>();
            session.setAttribute("cart", cart );
        }

        RequestDispatcher view = request.getRequestDispatcher("index.jsp");
        view.forward(request, response);
    }
}
