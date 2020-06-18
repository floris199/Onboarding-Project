package com.youngculture.dao.impl;

import com.youngculture.dao.intrf.DAOInterface;
import com.youngculture.dao.utils.HibernateUtils;
import com.youngculture.entities.CartEntity;
import com.youngculture.entities.OrdersEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class OrderDAO implements DAOInterface<OrdersEntity> {
    @Override
    public List<OrdersEntity> getAll() {
        return null;
    }

    @Override
    public List<OrdersEntity> get( String userId )
    {
        Session session = HibernateUtils.getSessionFactory().openSession();

        Integer userIdAsInt = Integer.valueOf( userId );
        Query query = session.createQuery( "FROM OrdersEntity o where o.userId =: userId", OrdersEntity.class).setParameter("userId", userIdAsInt);

        List<OrdersEntity> results = query.getResultList();

        session.close();

        return results;
    }

    @Override
    public void update(OrdersEntity ordersEntity) {

    }

    @Override
    public void delete(OrdersEntity ordersEntity) {

    }

    @Override
    public String save(OrdersEntity ordersEntity)
    {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();

        try {
            session.save(ordersEntity);

            session.getTransaction().commit();

            session.close();

            return String.valueOf( ordersEntity.getId() );
        }
        catch(Exception e)
        {
            session.getTransaction().rollback();
            return "SAVING_ERROR";
        }
    }
}
