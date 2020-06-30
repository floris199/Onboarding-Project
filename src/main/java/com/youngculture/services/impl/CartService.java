package com.youngculture.services.impl;

import com.youngculture.dao.impl.CartDAO;
import com.youngculture.dao.impl.CartDetailsDAO;
import com.youngculture.dao.impl.ProductDAO;
import com.youngculture.dao.impl.UserDAO;
import com.youngculture.dao.intrf.DAOInterface;
import com.youngculture.dao.utils.HibernateUtils;
import com.youngculture.dto.CartDTO;
import com.youngculture.dto.CartDetailsDTO;
import com.youngculture.dto.ProductDTO;
import com.youngculture.entities.*;
import com.youngculture.services.intrf.CartServiceInterface;
import com.youngculture.transfomer.ObjectMapperUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.*;

public class CartService implements CartServiceInterface {

    private DAOInterface<CartEntity> cartDAO = new CartDAO();
    private DAOInterface<CartDetailsEntity> cartDetailsDAO = new CartDetailsDAO();
    private DAOInterface<UserEntity> userDAO = new UserDAO();
    private DAOInterface<ProductsEntity> productDAO = new ProductDAO();

    public Map<Integer, Integer> updateCartContent(Integer productId,
                                                      Map<Integer, Integer> cart,
                                                      String username)
    {
            ProductsEntity product = productDAO.get( String.valueOf( productId ) ).get( 0 );

            if (!cart.containsKey(productId)) {
                cart.put(productId, 1);
                if (username != null) {
                   /* CartEntity cartEntity = ObjectMapperUtils.map(getCart(username), CartEntity.class);
                    CartDetailsEntity cartDetailsEntity = new CartDetailsEntity();
                    setCartDetails(cartDetailsEntity, cartEntity.getId(), 1, product );
                    cartDetailsDAO.save(cartDetailsEntity);*/

                    modifyCartDetailsInfo( username, product, 1, true, false);
                }
            } else {
                cart.put(productId, cart.get(productId) + 1);
                if (username != null) {
                    /*CartEntity cartEntity = ObjectMapperUtils.map(getCart(username), CartEntity.class);
                    CartDetailsEntity cartDetailsEntity = new CartDetailsEntity();
                    setCartDetails(cartDetailsEntity, cartEntity.getId(), cart.get(productId), product);
                    cartDetailsDAO.update(cartDetailsEntity);*/

                    modifyCartDetailsInfo( username, product, cart.get( productId ), false, false);
                }
            }

        return cart;
    }

    @Override
    public CartDTO buildCart(String username, Map<Integer, Integer> cartMap)
    {
        CartDTO cartDTO = new CartDTO();

        if( username == null )
        {
            for( Map.Entry<Integer, Integer> item : cartMap.entrySet() )
            {
                ProductsEntity productsEntity = productDAO.get( String.valueOf( item.getKey() ) ).get( 0 );
                ProductDTO productDTO = ObjectMapperUtils.map( productsEntity, ProductDTO.class);
                CartDetailsDTO cartDetailsDTO = new CartDetailsDTO();
                cartDetailsDTO.setProductDTOs( productDTO );
                cartDetailsDTO.setQuantity( item.getValue() );
                cartDTO.getCartDetailsDTOs().add(cartDetailsDTO);
            }
        }
        else
        {
            cartDTO = getCart( username );
        }

        return cartDTO;
    }

    private void setCartDetails( CartDetailsEntity cartDetailsEntity, int cartId, int quantity, ProductsEntity product )
    {
        cartDetailsEntity.setCartId( cartId );
        cartDetailsEntity.setQuantity( quantity );
        cartDetailsEntity.setProductsEntity( product );
    }

     public void removeItemFromCart(Map<Integer, Integer> cart,
                                    Integer productId,
                                    String username)
     {
         ProductsEntity product = productDAO.get( String.valueOf( productId ) ).get( 0 );

         for (Map.Entry<Integer, Integer> item : cart.entrySet()) {
             if( item.getKey() == productId )
             {
                 if( item.getValue() == 1)
                 {
                     item.setValue( 0 );
                     if( username!=null )
                     {
                         /*CartEntity cartEntity = ObjectMapperUtils.map(getCart(username), CartEntity.class);
                         CartDetailsEntity cartDetailsEntity = new CartDetailsEntity();
                         setCartDetails( cartDetailsEntity, cartEntity.getId(), 0, product );
                         cartDetailsDAO.delete( cartDetailsEntity );*/

                         modifyCartDetailsInfo( username, product, 0, false, true);
                     }
                 }
                 else
                 {
                     cart.put( item.getKey(), item.getValue() - 1);

                     if( username != null )
                     {
                         /*CartEntity cartEntity = ObjectMapperUtils.map(getCart(username), CartEntity.class);
                         CartDetailsEntity cartDetailsEntity =  new CartDetailsEntity();
                         setCartDetails( cartDetailsEntity, cartEntity.getId(), item.getValue(), product );
                         cartDetailsDAO.update( cartDetailsEntity );*/

                         modifyCartDetailsInfo( username, product, item.getValue(), false, false);
                     }
                 }
             }
         }

         cart.entrySet().removeIf(entry -> entry.getValue() == 0);
     }

    public void addItemToCart(Map<Integer, Integer> cart,
                                   Integer productId,
                                   String username)
    {
        ProductsEntity product = productDAO.get( String.valueOf( productId ) ).get( 0 );

        for (Map.Entry<Integer, Integer> item : cart.entrySet()) {
            if( item.getKey() == productId )
            {
                    cart.put( item.getKey(), item.getValue() + 1);

                    if( username != null )
                    {
                        /*CartEntity cartEntity = ObjectMapperUtils.map(getCart(username), CartEntity.class);
                        CartDetailsEntity cartDetailsEntity = new CartDetailsEntity();
                        setCartDetails(cartDetailsEntity, cartEntity.getId(), item.getValue(), product );
                        cartDetailsDAO.update(cartDetailsEntity);*/

                        modifyCartDetailsInfo( username, product, item.getValue(), false, false);
                    }
            }
        }

        cart.entrySet().removeIf(entry -> entry.getValue() == 0);
    }

    private void modifyCartDetailsInfo(String username,
                                       ProductsEntity product,
                                       int quantity,
                                       boolean save,
                                       boolean delete)
    {
        Session session = HibernateUtils.getSessionFactory().openSession();

        CartEntity cartEntity = ObjectMapperUtils.map(getCart(username), CartEntity.class);
        CartDetailsEntity cartDetailsEntity = new CartDetailsEntity();
        setCartDetails(cartDetailsEntity, cartEntity.getId(), quantity, product );

        try {
            session.beginTransaction();
            if( !save && !delete )
            {
                cartDetailsDAO.update(cartDetailsEntity, session);
            }

            if( save )
            {
                cartDetailsDAO.save(cartDetailsEntity, session);
            }

            if( delete )
            {
                cartDetailsDAO.delete(cartDetailsEntity, session);
            }
            session.close();
        }
        catch(HibernateException e)
        {
            session.getTransaction().rollback();
        }
    }

    public void mergeAndSaveCart(Map<Integer, Integer> cart, String username)
    {
        CartEntity cartEntity = ObjectMapperUtils.map(getCart(username), CartEntity.class);
        List<CartDetailsEntity> cartDetailsToBeUpdated =  new ArrayList<>();
        Map<Integer,Integer> cartDetailsToBeInserted =  new HashMap<>();

        if( cartEntity.getId() == 0 )
        {
            createCart( username );

            if( !cart.isEmpty() )
            {
                addCartItemsToCartEntity( cart, cartEntity );

                saveCartDetails( cartEntity.getCartDetails() );
            }

            return;
        }

        if( cartEntity.getId() != 0 && cart.isEmpty() )
        {
            addToCartMap(cartEntity, cart);
            return;
        }
        else
        {
            checkForProductsAlreadyInCart( cart, cartEntity, cartDetailsToBeUpdated, cartDetailsToBeInserted );
            addToCartMap(cartEntity, cart);
            updateCartDetails( cartDetailsToBeUpdated );

            int cartId = cartEntity.getId();
            cartEntity = new CartEntity();
            cartEntity.setId( cartId );

            addCartItemsToCartEntity( cartDetailsToBeInserted, cartEntity );

            saveCartDetails( cartEntity.getCartDetails() );
        }
    }

    @Override
    public void createCart( String username )
    {
        CartEntity cartEntity = ObjectMapperUtils.map(getCart(username), CartEntity.class);

        Session session = HibernateUtils.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            cartDAO.save( cartEntity, session );
            session.close();
        }
        catch(HibernateException e)
        {
            session.getTransaction().rollback();
        }
    }

    private void addToCartMap( CartEntity cartEntity, Map<Integer, Integer> cart )
    {
        for( CartDetailsEntity cartDetailsEntity : cartEntity.getCartDetails() )
        {
            cart.put( cartDetailsEntity.getProductsEntity().getProductId(), cartDetailsEntity.getQuantity() );
        }
    }

    private void saveCartDetails(List<CartDetailsEntity> cartDetailsToBeInserted)
    {
        Session session = HibernateUtils.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            for( CartDetailsEntity cartDetail: cartDetailsToBeInserted )
            {
                cartDetailsDAO.save( cartDetail, session );
            }
            session.close();
        }
        catch(HibernateException e)
        {
            session.getTransaction().rollback();
        }
    }

    private void updateCartDetails(List<CartDetailsEntity> cartDetailsToBeUpdated)
    {
        Session session = HibernateUtils.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            for( CartDetailsEntity cartDetail: cartDetailsToBeUpdated )
            {
                cartDetailsDAO.update( cartDetail, session );
            }
            session.close();
        }
        catch(HibernateException e)
        {
            session.getTransaction().rollback();
        }
    }

    public void checkForProductsAlreadyInCart(Map<Integer, Integer> cart,
                                              CartEntity cartEntity,
                                              List<CartDetailsEntity> cartDetailsToBeUpdated,
                                              Map<Integer,Integer> cartDetailsToBeInserted )
    {
        boolean toUpdate = false;
        for (Map.Entry<Integer,Integer> product : cart.entrySet() )
        {
            toUpdate = false;
            for( CartDetailsEntity cartDetails: cartEntity.getCartDetails() )
            {
                if( cartDetails.getProductsEntity().getProductId() == product.getKey() )
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

    public void addCartItemsToCartEntity( Map<Integer, Integer> cart, CartEntity cartEntity )
    {
        if( cartEntity.getCartDetails() == null)
        {
            cartEntity.setCartDetails( new ArrayList<>() );
        }

        for (Map.Entry<Integer, Integer> product : cart.entrySet()) {
            CartDetailsEntity cartDetails = new CartDetailsEntity();

            if (product.getValue() > 0)
            {
                cartDetails.setQuantity( product.getValue() );
                cartDetails.setCartId( cartEntity.getId() );
                cartDetails.setProductsEntity( productDAO.get( String.valueOf( product.getKey() ) ).get( 0 ) );
                cartEntity.getCartDetails().add(cartDetails);
            }
        }
    }

    public CartDTO getCart(String username) {
        UserEntity user = userDAO.get( username ).get( 0 );

        List<CartEntity> cartEntityList = new CartDAO().get( String.valueOf( user.getId() ) );
        if( !cartEntityList.isEmpty() )
        {
           CartEntity cartEntity = cartEntityList.get( 0 );
           CartDTO cartDTO = ObjectMapperUtils.map(cartEntity, CartDTO.class);

           return  cartDTO;
        }
        else
        {
            CartDTO cartDTO = new CartDTO();
            cartDTO.setUserId( user.getId() );

            return cartDTO;
        }
    }

    public void removeCart( String username )
    {
        Session session = HibernateUtils.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            cartDAO.delete( ObjectMapperUtils.map(getCart(username), CartEntity.class), session );
            session.close();
        }
        catch(HibernateException e)
        {
            session.getTransaction().rollback();
        }
    }
}