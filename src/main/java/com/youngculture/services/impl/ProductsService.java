package com.youngculture.services.impl;

import com.youngculture.dao.impl.ProductDAO;
import com.youngculture.dto.ProductDTO;
import com.youngculture.entities.ProductsEntity;
import com.youngculture.services.intrf.ProductServiceInterface;
import com.youngculture.transfomer.ObjectMapperUtils;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;

public class ProductsService implements ProductServiceInterface {

    public List<ProductDTO> getProductsBasedOnCategory( String category ){
        List<ProductsEntity> products = new ProductDAO().getProductsBasedOnCategory( category );

        List<ProductDTO> productDTOs = ObjectMapperUtils.mapAll(products, ProductDTO.class);

        return productDTOs;
    }

}
