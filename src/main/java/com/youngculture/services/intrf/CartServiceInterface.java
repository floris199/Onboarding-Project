package com.youngculture.services.intrf;

import com.youngculture.entities.CartEntity;
import com.youngculture.entities.ProductsEntity;

import java.util.Map;

public interface CartServiceInterface
{
    public Map<ProductsEntity, Integer> updateCartContent(ProductsEntity product, Map<ProductsEntity, Integer> cart, String username);

    public void removeItemFromCart(Map<ProductsEntity, Integer> cart, Integer productId, String username);

    public void mergeAndSaveCart(Map<ProductsEntity, Integer> cart, String username);

    public CartEntity getCart(String username);

    public void removeCart(String username);
}
