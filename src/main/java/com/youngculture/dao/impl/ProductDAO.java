package com.youngculture.dao.impl;

import com.youngculture.dao.intrf.DAOInterface;
import com.youngculture.dao.utils.HibernateUtils;
import com.youngculture.entities.CartEntity;
import com.youngculture.entities.CategoryEntity;
import com.youngculture.entities.ProductsEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.util.List;

public class ProductDAO implements DAOInterface<ProductsEntity> {

    @Override
    public List<ProductsEntity> getAll() {
        Session session = HibernateUtils.getSessionFactory().openSession();

        List<ProductsEntity> results = session.createQuery( "FROM ProductsEntity p", ProductsEntity.class).getResultList();

        session.close();

        return results;
    }

    @Override
    public List<ProductsEntity> get(String productId)
    {
        Session session = HibernateUtils.getSessionFactory().openSession();

        Integer productIdAsInt = Integer.valueOf( productId );
        Query query = session.createQuery( "FROM ProductsEntity p where p.id =: productIdAsInt", ProductsEntity.class).setParameter("productIdAsInt", productIdAsInt);

        List<ProductsEntity> results = query.getResultList();

        session.close();

        return results;
    }

    @Override
    public void update(ProductsEntity productsEntity, Session session) throws HibernateException{

    }

    @Override
    public void delete(ProductsEntity productsEntity, Session session) throws HibernateException {

    }

    @Override
    public void save(ProductsEntity productsEntity, Session session) throws HibernateException{
    }

    public List<ProductsEntity> getProductsBasedOnCategory( String category )
    {
        Session session = HibernateUtils.getSessionFactory().openSession();

        List<ProductsEntity> results = session.createQuery( "FROM ProductsEntity p where p.categoryEntity.categoryDescription =: category",
                                                                    ProductsEntity.class).setParameter("category", category).getResultList();

        session.close();

        return results;
    }
}
