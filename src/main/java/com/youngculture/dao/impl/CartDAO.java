package com.youngculture.dao.impl;

import com.youngculture.dao.intrf.DAOInterface;
import com.youngculture.dao.utils.HibernateUtils;
import com.youngculture.entities.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class CartDAO implements DAOInterface<CartEntity> {

    @Override
    public List<CartEntity> getAll() {
        return null;
    }

    @Override
    public List<CartEntity> get( String userId ) {
        Session session = HibernateUtils.getSessionFactory().openSession();

        Integer userIdAsInt = Integer.valueOf( userId );
        Query query = session.createQuery( "FROM CartEntity c where c.userId =: userId", CartEntity.class).setParameter("userId", userIdAsInt);

        List<CartEntity> results = query.getResultList();

        session.close();

        return results;
    }

    @Override
    public void update(CartEntity cartEntity) {

    }

    @Override
    public void delete(CartEntity cartEntity) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();

        try
        {
            session.createNativeQuery("delete from CART c where c.id = :id")
                    .setParameter("id", cartEntity.getId())
                    .executeUpdate();

            session.getTransaction().commit();

            session.close();
        }
        catch(Exception e)
        {
            session.getTransaction().rollback();
        }
    }

    @Override
    public String save(CartEntity cartEntity) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();

        try {
            session.save(cartEntity);

            session.getTransaction().commit();

            session.close();

            return String.valueOf( cartEntity.getId() );
        }
        catch(Exception e)
        {
            session.getTransaction().rollback();
            return "SAVING_ERROR";
        }
    }
}
