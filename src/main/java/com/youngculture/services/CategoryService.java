package com.youngculture.services;

import com.youngculture.dao.impl.CategoryDAO;
import com.youngculture.dao.intrf.DAOInterface;
import com.youngculture.entities.CategoryEntity;

import java.util.List;

public class CategoryService {

    private DAOInterface<CategoryEntity> categoryDAO = new CategoryDAO();

    public List<CategoryEntity> getAllCategories( )
    {
        return categoryDAO.getAll();
    }

    public void myMethod( String username )
    {
        categoryDAO.get( username );
    }

}
