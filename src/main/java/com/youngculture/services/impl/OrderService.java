package com.youngculture.services.impl;

import com.youngculture.dao.impl.CartDAO;
import com.youngculture.dao.impl.OrderDAO;
import com.youngculture.dao.impl.OrderDetailsDAO;
import com.youngculture.dao.impl.UserDAO;
import com.youngculture.dao.intrf.DAOInterface;
import com.youngculture.entities.*;
import com.youngculture.services.intrf.OrderServiceInterface;

import java.util.ArrayList;
import java.util.List;

public class OrderService implements OrderServiceInterface
{
    private DAOInterface<UserEntity> userDAO = new UserDAO();
    private DAOInterface<CartEntity> cartDAO =  new CartDAO();
    private DAOInterface<OrdersEntity> orderDAO =  new OrderDAO();
    private DAOInterface<OrderDetailsEntity> orderDetailsDAO =  new OrderDetailsDAO();

    @Override
    public void saveOrder( String username )
    {
        UserEntity userEntity = userDAO.get( username ).get( 0 );
        CartEntity cartEntity = cartDAO.get( String.valueOf( userEntity.getId( ) ) ).get( 0 );

        OrdersEntity orderEntity = new OrdersEntity( );
        orderEntity.setUserId( userEntity.getId() );

        orderDAO.save( orderEntity );

        for(CartDetailsEntity cartDetailsEntity : cartEntity.getCartDetails() )
        {
            OrderDetailsEntity orderDetailsEntity =  new OrderDetailsEntity();
            orderDetailsEntity.setOrderId( orderEntity.getId() );
            orderDetailsEntity.setProductsEntity( cartDetailsEntity.getProductsEntity() );
            orderDetailsEntity.setQuantity( cartDetailsEntity.getQuantity() );

            orderDetailsDAO.save( orderDetailsEntity );
        }
    }

    @Override
    public List<OrdersEntity> getAllOrders( String username )
    {
        UserEntity userEntity = userDAO.get( username ).get( 0 );

        return orderDAO.get( String.valueOf( userEntity.getId( ) ) );
    }
}
