package com.youngculture.dao.impl;

import com.youngculture.dao.intrf.DAOInterface;
import com.youngculture.dao.utils.HibernateUtils;
import com.youngculture.entities.CartEntity;
import com.youngculture.entities.CategoryEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.List;

public class CategoryDAO implements DAOInterface<CategoryEntity> {

    @Override
    public List<CategoryEntity> getAll() {
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
    public void update(CategoryEntity categoryEntity) {

    }

    @Override
    public void delete(CategoryEntity categoryEntity) {

    }

    @Override
    public String save(CategoryEntity categoryEntity) {
        return null;
    }
}
