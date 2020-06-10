package com.youngculture.dao.impl;

import com.youngculture.dao.intrf.DAOInterface;
import com.youngculture.dao.utils.HibernateUtils;
import com.youngculture.entities.CategoryEntity;
import com.youngculture.entities.ProductsEntity;
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


/*    public static void main ( String args[] )
    {
        Integer  i = 1;
        Session session = DAOUtils.getSessionFactory().openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<OrdersEntity> cr = cb.createQuery(OrdersEntity.class);
        Root<OrdersEntity> root = cr.from(OrdersEntity.class);
        cr.select(root).where(cb.equal(root.<Integer>get( "id" ), i));

        Query<OrdersEntity> query = session.createQuery(cr);
        List<OrdersEntity> results = query.getResultList();

        session.close();

        return;
    }*/

    @Override
    public List<ProductsEntity> getAll() {
        Session session = HibernateUtils.getSessionFactory().openSession();

        List<ProductsEntity> results = session.createQuery( "FROM ProductsEntity p", ProductsEntity.class).getResultList();

        session.close();

        return results;
    }

    @Override
    public List<ProductsEntity> get(String id) {
        return null;
    }

    @Override
    public void update(ProductsEntity productsEntity) {

    }

    @Override
    public void delete(ProductsEntity productsEntity) {

    }

    @Override
    public String save(ProductsEntity productsEntity) {
        return null;
    }

    public List<ProductsEntity> getProductsBasedOnCategory( String category )
    {
        Session session = HibernateUtils.getSessionFactory().openSession();

        List<ProductsEntity> results = session.createQuery( "FROM ProductsEntity p where p.categoryEntity.description =: category",
                                                                    ProductsEntity.class).setParameter("category", category).getResultList();

        session.close();

        return results;
    }
}
