package com.youngculture.services.intrf;

import com.youngculture.entities.CartEntity;
import com.youngculture.entities.OrdersEntity;

import java.util.List;

public interface OrderServiceInterface
{
    public void saveOrder( String username );

    public List<OrdersEntity> getAllOrders(String username );
}
