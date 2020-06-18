package com.youngculture.services.impl;

import com.youngculture.dao.impl.CartDAO;
import com.youngculture.dao.impl.CartDetailsDAO;
import com.youngculture.dao.impl.UserDAO;
import com.youngculture.dao.intrf.DAOInterface;
import com.youngculture.entities.*;
import com.youngculture.services.intrf.CartServiceInterface;

import java.util.*;

public class CartService implements CartServiceInterface {

    private DAOInterface<CartEntity> cartDAO = new CartDAO();
    private DAOInterface<CartDetailsEntity> cartDetailsDAO = new CartDetailsDAO();
    private DAOInterface<UserEntity> userDAO = new UserDAO();

    public Map<ProductsEntity, Integer> updateCartContent(ProductsEntity product,
                                                          Map<ProductsEntity, Integer> cart,
                                                          String username)
    {
        if( !cart.containsKey( product ) )
        {
            extractedMethod(product, cart, username);
        }
        else
        {
            cart.put(product, cart.get( product ) + 1);
            if( username != null )
            {
                CartEntity cartEntity = getCart( username );
                CartDetailsEntity cartDetailsEntity =  new CartDetailsEntity();
                setCartDetails( cartDetailsEntity, cartEntity.getId(), cart.get( product ), product);
                cartDetailsDAO.update( cartDetailsEntity );
            }
        }


        return cart;
    }

    private void extractedMethod(ProductsEntity product, Map<ProductsEntity, Integer> cart, String username) {
        cart.put(product, 1);
        if( username != null )
        {
            CartEntity cartEntity = getCart( username );
            CartDetailsEntity cartDetailsEntity =  new CartDetailsEntity();
            setCartDetails( cartDetailsEntity, cartEntity.getId(), 1, product);
            cartDetailsDAO.save( cartDetailsEntity );
        }
    }

    private void setCartDetails( CartDetailsEntity cartDetailsEntity, int cartId, int quantity, ProductsEntity product )
    {
        cartDetailsEntity.setCartId( cartId );
        cartDetailsEntity.setQuantity( quantity );
        cartDetailsEntity.setProductsEntity( product );
    }

     public void removeItemFromCart(Map<ProductsEntity, Integer> cart,
                                    Integer productId,
                                    String username)
     {
         for (Map.Entry<ProductsEntity, Integer> product : cart.entrySet()) {
             if( product.getKey().getId() == productId )
             {
                 if( product.getValue() == 1)
                 {
                     product.setValue( 0 );
                     if( username!=null )
                     {
                         CartEntity cartEntity = getCart(username);
                         CartDetailsEntity cartDetailsEntity = new CartDetailsEntity();
                         setCartDetails( cartDetailsEntity, cartEntity.getId(), 0, product.getKey() );
                         cartDetailsDAO.delete( cartDetailsEntity );
                     }
                 }
                 else
                 {
                     cart.put( product.getKey(), product.getValue() - 1);

                     if( username != null )
                     {
                         CartEntity cartEntity = getCart( username );
                         CartDetailsEntity cartDetailsEntity =  new CartDetailsEntity();
                         setCartDetails( cartDetailsEntity, cartEntity.getId(), product.getValue(), product.getKey() );
                         cartDetailsDAO.update( cartDetailsEntity );
                     }
                 }
             }
         }

         cart.entrySet().removeIf(entry -> entry.getValue() == 0);
     }

    public void addItemToCart(Map<ProductsEntity, Integer> cart,
                                   Integer productId,
                                   String username)
    {
        for (Map.Entry<ProductsEntity, Integer> product : cart.entrySet()) {
            if( product.getKey().getId() == productId )
            {
                    cart.put( product.getKey(), product.getValue() + 1);

                    if( username != null )
                    {
                        CartEntity cartEntity = getCart(username);
                        CartDetailsEntity cartDetailsEntity = new CartDetailsEntity();
                        setCartDetails(cartDetailsEntity, cartEntity.getId(), product.getValue(), product.getKey());
                        cartDetailsDAO.update(cartDetailsEntity);
                    }
            }
        }

        cart.entrySet().removeIf(entry -> entry.getValue() == 0);
    }

    public void mergeAndSaveCart(Map<ProductsEntity, Integer> cart, String username)
    {
        CartEntity cartEntity = getCart( username );
        List<CartDetailsEntity> cartDetailsToBeUpdated =  new ArrayList<>();
        Map<ProductsEntity,Integer> cartDetailsToBeInserted =  new HashMap<>();

        if( cartEntity.getId() == 0 && cart.isEmpty() )
        {
            cartDAO.save( cartEntity );
            return;
        }

        if( !cart.isEmpty() && cartEntity.getId() == 0 )
        {
            cartDAO.save( cartEntity );

            addCartItemsToCartEntity( cart, cartEntity );

            saveCartDetails( cartEntity.getCartDetails() );

            return;
        }
        else
        {
            checkForProductsAlreadyInCart( cart, cartEntity, cartDetailsToBeUpdated, cartDetailsToBeInserted );

            updateCartDetails( cartDetailsToBeUpdated );

            int cartId = cartEntity.getId();
            cartEntity = new CartEntity();
            cartEntity.setId( cartId );

            addCartItemsToCartEntity( cartDetailsToBeInserted, cartEntity );

            saveCartDetails( cartEntity.getCartDetails() );
        }
    }

    private void saveCartDetails(List<CartDetailsEntity> cartDetailsToBeInserted)
    {
        for( CartDetailsEntity cartDetail: cartDetailsToBeInserted )
        {
            cartDetailsDAO.save( cartDetail );
        }
    }

    private void updateCartDetails(List<CartDetailsEntity> cartDetailsToBeUpdated)
    {
        for( CartDetailsEntity cartDetail: cartDetailsToBeUpdated )
        {
            cartDetailsDAO.update( cartDetail );
        }
    }

    public void checkForProductsAlreadyInCart(Map<ProductsEntity, Integer> cart,
                                              CartEntity cartEntity,
                                              List<CartDetailsEntity> cartDetailsToBeUpdated,
                                              Map<ProductsEntity,Integer> cartDetailsToBeInserted )
    {
        boolean toUpdate = false;
        for (Map.Entry<ProductsEntity,Integer> product : cart.entrySet() )
        {
            toUpdate = false;
            for( CartDetailsEntity cartDetails: cartEntity.getCartDetails() )
            {
                if( cartDetails.getProductsEntity().getId() == product.getKey().getId() )
                {
                    cartDetails.setQuantity( cartDetails.getQuantity() + product.getValue() );
                    cartDetailsToBeUpdated.add( cartDetails );
                    toUpdate = true;
                    break;
                }
            }

            if( !toUpdate )
            {
                cartDetailsToBeInserted.put( product.getKey(), product.getValue() );
            }
        }
    }

    public void addCartItemsToCartEntity( Map<ProductsEntity, Integer> cart, CartEntity cartEntity )
    {
        if( cartEntity.getCartDetails() == null)
        {
            cartEntity.setCartDetails( new ArrayList<>() );
        }

        for (Map.Entry<ProductsEntity, Integer> product : cart.entrySet()) {
            CartDetailsEntity cartDetails = new CartDetailsEntity();

            if (product.getValue() > 0)
            {
                cartDetails.setQuantity( product.getValue() );
                cartDetails.setCartId( cartEntity.getId() );
                cartDetails.setProductsEntity( product.getKey() );
                cartEntity.getCartDetails().add(cartDetails);
            }
        }
    }

    public CartEntity getCart(String username) {
        UserEntity user = userDAO.get( username ).get( 0 );

        List<CartEntity> cartEntityList = new CartDAO().get( String.valueOf( user.getId() ) );
        if( !cartEntityList.isEmpty() )
        {
            return cartEntityList.get( 0 );
        }
        else
        {
            CartEntity cartEntity = new CartEntity();
            cartEntity.setUserId( user.getId() );

            return cartEntity;
        }
    }

    public Map< ProductsEntity, Integer> transformFromEntityToMap( CartEntity  cartEntity )
    {
        Map< ProductsEntity, Integer> cart = new HashMap<>( );

        for( CartDetailsEntity cartDetailsEntity : cartEntity.getCartDetails() )
        {
            ProductsEntity product = new ProductsEntity();
            product.setId( cartDetailsEntity.getProductsEntity().getId() );
            product.setName( cartDetailsEntity.getProductsEntity().getName() );
            product.setDescription( cartDetailsEntity.getProductsEntity().getDescription() );
            product.setPricesEntity( cartDetailsEntity.getProductsEntity().getPricesEntity() );

            cart.put( product, cartDetailsEntity.getQuantity() );
        }

        return cart;
    }

    public void removeCart( String username )
    {
        cartDAO.delete( getCart( username ) );
    }
}