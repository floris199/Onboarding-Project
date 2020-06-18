package com.youngculture.services.impl;

import com.youngculture.dao.impl.CategoryDAO;
import com.youngculture.dao.intrf.DAOInterface;
import com.youngculture.entities.CategoryEntity;
import com.youngculture.services.intrf.CategoryServiceInterface;

import java.util.List;

public class CategoryService implements CategoryServiceInterface {

    private DAOInterface<CategoryEntity> categoryDAO = new CategoryDAO();

    public List<CategoryEntity> getAllCategories( )
    {
        return categoryDAO.getAll();
    }
}
