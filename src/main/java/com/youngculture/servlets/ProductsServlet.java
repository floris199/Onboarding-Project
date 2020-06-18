package com.youngculture.servlets;

import com.youngculture.services.impl.ProductsService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ProductsServlet extends HttpServlet {

    private ProductsService prodService = new ProductsService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("pages/products.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String selectedProductCategory = request.getParameter("selectedProductCategory");
        session.setAttribute("selectedProductCategory", selectedProductCategory);

        if( selectedProductCategory != null ) {

            session.setAttribute("products", prodService.getProductsBasedOnCategory( selectedProductCategory ));
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("pages/products.jsp");
        dispatcher.forward(request, response);
    }

}
