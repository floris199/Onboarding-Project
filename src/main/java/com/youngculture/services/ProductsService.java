package com.youngculture.services;

import com.youngculture.dao.impl.ProductDAO;
import com.youngculture.entities.ProductsEntity;

import java.util.List;

public class ProductsService {

    public List<ProductsEntity> getProductsBasedOnCategory(String category ){

        return new ProductDAO().getProductsBasedOnCategory( category );
    }

}
