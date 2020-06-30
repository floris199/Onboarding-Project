package com.youngculture.services.intrf;

import com.youngculture.dto.CartDTO;
import com.youngculture.dto.ProductDTO;
import com.youngculture.entities.CartEntity;
import com.youngculture.entities.ProductsEntity;
import org.hibernate.Session;

import java.util.List;
import java.util.Map;

public interface CartServiceInterface
{
    public void removeItemFromCart(Map<Integer, Integer> cart, Integer productId, String username);

    public void mergeAndSaveCart(Map<Integer, Integer> cart, String username);

    public void createCart(String username);

    public CartDTO getCart(String username);

    public void removeCart(String username);

    public Map<Integer, Integer> updateCartContent(Integer productId, Map<Integer, Integer> cart, String username);

    public CartDTO buildCart( String username, Map<Integer, Integer> cartMap);
}
