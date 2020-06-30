package com.youngculture.dao.impl;

import com.youngculture.dao.intrf.DAOInterface;
import com.youngculture.dao.utils.HibernateUtils;
import com.youngculture.entities.OrderDetailsEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

public class OrderDetailsDAO implements DAOInterface<OrderDetailsEntity> {
    @Override
    public List<OrderDetailsEntity> getAll() {
        return null;
    }

    @Override
    public List<OrderDetailsEntity> get(String id) {
        return null;
    }

    @Override
    public void update(OrderDetailsEntity orderDetailsEntity, Session session) throws HibernateException{

    }

    @Override
    public void delete(OrderDetailsEntity orderDetailsEntity, Session session) throws HibernateException {

    }

    @Override
    public void save( OrderDetailsEntity orderDetailsEntity, Session session ) throws HibernateException
    {
        session.save( orderDetailsEntity );
    }
}
