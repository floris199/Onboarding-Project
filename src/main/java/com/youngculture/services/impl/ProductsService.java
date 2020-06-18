package com.youngculture.services.impl;

import com.youngculture.dao.impl.ProductDAO;
import com.youngculture.entities.ProductsEntity;
import com.youngculture.services.intrf.ProductServiceInterface;

import java.util.List;

public class ProductsService implements ProductServiceInterface {

    public List<ProductsEntity> getProductsBasedOnCategory( String category ){

        return new ProductDAO().getProductsBasedOnCategory( category );
    }

}
