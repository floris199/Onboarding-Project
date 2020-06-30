package com.youngculture.services.intrf;

import com.youngculture.dto.ProductDTO;

import java.util.List;

public interface ProductServiceInterface
{
    public List<ProductDTO> getProductsBasedOnCategory(String category );
}
