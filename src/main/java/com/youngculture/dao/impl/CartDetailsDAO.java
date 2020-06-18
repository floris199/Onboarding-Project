package com.youngculture.dao.impl;

import com.youngculture.dao.intrf.DAOInterface;
import com.youngculture.dao.utils.HibernateUtils;
import com.youngculture.entities.CartDetailsEntity;
import com.youngculture.entities.CartEntity;
import org.hibernate.Session;

import java.util.List;

public class CartDetailsDAO implements DAOInterface<CartDetailsEntity> {
    @Override
    public List<CartDetailsEntity> getAll() {
        return null;
    }

    @Override
    public List<CartDetailsEntity> get(String id) {
        return null;
    }

    @Override
    public void update(CartDetailsEntity cartDetailsEntity)
    {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();

        try {
            session.createNativeQuery("update CART_DETAILS c set c.quantity = :quantity " +
                    "where c.cart_id = :cartId and c.product_id = :productId")
                    .setParameter( "quantity", cartDetailsEntity.getQuantity() )
                    .setParameter( "cartId", cartDetailsEntity.getCartId() )
                    .setParameter( "productId", cartDetailsEntity.getProductsEntity().getId() )
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
    public void delete(CartDetailsEntity cartDetailsEntity)
    {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();

        try
        {
            session.createNativeQuery("delete from CART_DETAILS c where c.cart_id = :cartId and c.product_id = :productId")
                    .setParameter( "cartId", cartDetailsEntity.getCartId() )
                    .setParameter( "productId", cartDetailsEntity.getProductsEntity().getId() )
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
    public String save(CartDetailsEntity cartDetailsEntity)
    {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();

        try {
            session.save(cartDetailsEntity);

            session.getTransaction().commit();

            session.close();
            return "";
        }
        catch(Exception e)
        {
            session.getTransaction().rollback();
            return "SAVING_ERROR";
        }
    }
}
