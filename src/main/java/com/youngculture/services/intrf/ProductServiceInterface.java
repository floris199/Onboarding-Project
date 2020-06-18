package com.youngculture.services.intrf;

import com.youngculture.entities.ProductsEntity;

import java.util.List;

public interface ProductServiceInterface
{
    public List<ProductsEntity> getProductsBasedOnCategory(String category );
}
