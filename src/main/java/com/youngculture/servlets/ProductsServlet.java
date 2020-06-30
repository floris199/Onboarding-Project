package com.youngculture.servlets;

import com.youngculture.dao.impl.CategoryDAO;
import com.youngculture.dao.intrf.DAOInterface;
import com.youngculture.entities.CategoryEntity;
import com.youngculture.services.impl.ProductsService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ProductsServlet extends HttpServlet {

    private ProductsService prodService;
    private DAOInterface<CategoryEntity> categoryDAO;

    public void init(ServletConfig config)
    {
        categoryDAO = new CategoryDAO();
        prodService = new ProductsService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("pages/products.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        List<CategoryEntity> allCategories = categoryDAO.getAll();
        session.setAttribute("categories", allCategories);

        String selectedProductCategory = request.getParameter("selectedProductCategory");
        session.setAttribute("selectedProductCategory", selectedProductCategory);

        if( selectedProductCategory != null ) {

            session.setAttribute("products", prodService.getProductsBasedOnCategory( selectedProductCategory ));
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("pages/products.jsp");
        dispatcher.forward(request, response);
    }

}
