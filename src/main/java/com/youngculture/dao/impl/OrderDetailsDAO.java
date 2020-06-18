package com.youngculture.dao.impl;

import com.youngculture.dao.intrf.DAOInterface;
import com.youngculture.dao.utils.HibernateUtils;
import com.youngculture.entities.OrderDetailsEntity;
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
    public void update(OrderDetailsEntity orderDetailsEntity) {

    }

    @Override
    public void delete(OrderDetailsEntity orderDetailsEntity) {

    }

    @Override
    public String save( OrderDetailsEntity orderDetailsEntity )
    {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();

        try {
            session.save( orderDetailsEntity );

            session.getTransaction( ).commit( );

            session.close( );

            return "";
        }
        catch( Exception e )
        {
            session.getTransaction().rollback();
            return "SAVING_ERROR";
        }
    }
}
