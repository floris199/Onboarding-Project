package com.youngculture.dao.impl;

import com.youngculture.dao.intrf.DAOInterface;
import com.youngculture.dao.utils.HibernateUtils;
import com.youngculture.entities.CategoryEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

public class CategoryDAO implements DAOInterface<CategoryEntity> {

    @Override
    public List<CategoryEntity> getAll()
    {
        Session session = HibernateUtils.getSessionFactory().openSession();

        List<CategoryEntity> results = session.createQuery( "FROM CategoryEntity c", CategoryEntity.class).getResultList();

        session.close();

        return results;
    }

    @Override
    public List<CategoryEntity> get(String id) {
        return null;
    }

    @Override
    public void update(CategoryEntity categoryEntity, Session session) throws HibernateException{
    }

    @Override
    public void delete(CategoryEntity categoryEntity,Session session) throws HibernateException {
    }

    @Override
    public void save(CategoryEntity categoryEntity, Session session) throws HibernateException {
    }
}
