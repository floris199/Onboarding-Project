package com.youngculture.services.intrf;

import com.youngculture.dto.OrderDTO;
import com.youngculture.entities.CartEntity;
import com.youngculture.entities.OrdersEntity;

import java.util.List;

public interface OrderServiceInterface
{
    public String saveOrder( String username );

    public List<OrderDTO> getAllOrders(String username );
}
