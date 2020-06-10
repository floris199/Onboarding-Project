package com.youngculture.services;

import com.youngculture.dao.impl.CartDAO;
import com.youngculture.dao.impl.UserDAO;
import com.youngculture.dao.intrf.DAOInterface;
import com.youngculture.entities.CartDetailsEntity;
import com.youngculture.entities.CartEntity;
import com.youngculture.entities.ProductsEntity;
import com.youngculture.entities.UserEntity;

import java.util.HashMap;
import java.util.Map;

public class CartService {

    private DAOInterface<CartEntity> cartDAO = new CartDAO();

    public Map<ProductsEntity, Integer> updateCart(ProductsEntity product, Map<ProductsEntity, Integer> cart)
    {
        if( cart == null )
        {
            cart = new HashMap<>();
        }
        if( cart == null || !cart.containsKey( product ))
        {
            cart.put(product, 1);
        }
        else
        {
            cart.put(product, cart.get( product ) + 1);
        }

        return cart;
    }

    public int getCartQuantity( String username )
    {
        UserEntity user = new UserDAO().get( username ).get( 0 );

        int cartQuantity = 0;

        if( user.getCartEntity() != null )
        {
            for (CartDetailsEntity cartDetails : user.getCartEntity().getCartDetails()) {
                cartQuantity += cartDetails.getQuantity();
            }
        }

        return cartQuantity;
    }

    public Map<ProductsEntity, Integer> buildCartDetails( Map<Integer, Integer> cart, String username)
    {
        return new HashMap<>();
    }

    public void removeItemFromCart(Map<ProductsEntity, Integer> cart, Integer productId)
    {
        for (Map.Entry<ProductsEntity, Integer> product : cart.entrySet()) {
            if( product.getKey().getId() == productId )
            {
                if( product.getValue() == 1)
                {
                    cart.remove( product.getKey() );
                }
                else
                {
                    cart.put( product.getKey(), product.getValue() - 1);
                }
            }
        }
    }

    public void mergeAndSaveCart(Map<ProductsEntity, Integer> cart, String username)
    {
        UserEntity user = new UserDAO().get( username ).get( 0 );

        CartEntity cartFromDb = new CartDAO().get( String.valueOf( user.getId() ) ).get( 0 );

        if( cartFromDb != null && cart != null )
        {
            for (Map.Entry<ProductsEntity,Integer> product : cart.entrySet() )
            {
                for( CartDetailsEntity cartDetails: cartFromDb.getCartDetails() )
                {
                    if( cartDetails.getProductsEntity().getId() == product.getKey().getId() )
                    {
                        cartDetails.setQuantity( cartDetails.getQuantity() + product.getValue() );
                        product.setValue( 0 );
                    }
                    break;
                }
            }

            for( Map.Entry<ProductsEntity,Integer> product : cart.entrySet() )
            {
                CartDetailsEntity cartDetails = new CartDetailsEntity();

                if( product.getValue() > 0 )
                {
                    ProductsEntity productToBeSaved =  new ProductsEntity();
                    productToBeSaved.setId( product.getKey().getId() );

                    cartDetails.setQuantity( product.getValue() );
                    cartDetails.setCartId( cartFromDb.getId() );
                    cartDetails.setProductsEntity( productToBeSaved );
                    cartFromDb.getCartDetails().add( cartDetails );
                    cartDAO.save( cartFromDb );
                }
            }

        }
    }

    public CartEntity getCart(String username) {
        UserEntity user = new UserDAO().get( username ).get( 0 );

        CartEntity cartFromDb = new CartDAO().get( String.valueOf( user.getId() ) ).get( 0 );

        return cartFromDb;
    }
}