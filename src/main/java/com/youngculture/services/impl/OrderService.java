package com.youngculture.services.impl;

import com.youngculture.dao.impl.*;
import com.youngculture.dao.intrf.DAOInterface;
import com.youngculture.dao.utils.HibernateUtils;
import com.youngculture.dto.OrderDTO;
import com.youngculture.dto.ProductDTO;
import com.youngculture.entities.*;
import com.youngculture.services.intrf.OrderServiceInterface;
import com.youngculture.transfomer.ObjectMapperUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class OrderService implements OrderServiceInterface
{
    private DAOInterface<UserEntity> userDAO = new UserDAO();
    private DAOInterface<CartEntity> cartDAO =  new CartDAO();
    private DAOInterface<OrdersEntity> orderDAO =  new OrderDAO();
    private DAOInterface<OrderDetailsEntity> orderDetailsDAO =  new OrderDetailsDAO();
    private DAOInterface<ProductsEntity> productDAO = new ProductDAO();

    @Override
    public String saveOrder( String username )
    {
        UserEntity userEntity = userDAO.get( username ).get( 0 );
        CartEntity cartEntity = cartDAO.get( String.valueOf( userEntity.getId( ) ) ).get( 0 );

        OrdersEntity orderEntity = new OrdersEntity( );
        orderEntity.setUserId( userEntity.getId() );

        Session session = HibernateUtils.getSessionFactory().openSession();

        try {
            session.beginTransaction();

            orderDAO.save( orderEntity, session );

            for(CartDetailsEntity cartDetailsEntity : cartEntity.getCartDetails() )
            {
                OrderDetailsEntity orderDetailsEntity =  new OrderDetailsEntity();
                orderDetailsEntity.setOrderId( orderEntity.getId() );
                orderDetailsEntity.setProductName( cartDetailsEntity.getProductsEntity().getName() );
                orderDetailsEntity.setProductDescription( cartDetailsEntity.getProductsEntity().getDescription() );
                orderDetailsEntity.setProductPrice( cartDetailsEntity.getProductsEntity().getPricesEntity().getPrice() );
                orderDetailsEntity.setQuantity( cartDetailsEntity.getQuantity() );

                orderDetailsDAO.save( orderDetailsEntity, session );
            }

            session.getTransaction( ).commit( );

            session.close();

            return "";
        }
        catch(HibernateException e)
        {
            session.getTransaction().rollback();
            return e.getMessage();
        }
    }

    @Override
    public List<OrderDTO> getAllOrders( String username )
    {
        UserEntity userEntity = userDAO.get( username ).get( 0 );

        List<OrdersEntity> ordersEntityList = orderDAO.get( String.valueOf( userEntity.getId( ) ) );

        List<OrderDTO> orderDTOs = ObjectMapperUtils.mapAll(ordersEntityList, OrderDTO.class);

        return orderDTOs;
    }
}
