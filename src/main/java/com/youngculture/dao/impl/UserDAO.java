package com.youngculture.dao.impl;

import com.youngculture.dao.intrf.DAOInterface;
import com.youngculture.dao.utils.HibernateUtils;
import com.youngculture.entities.UserEntity;
import org.hibernate.Session;

import java.util.List;

import static com.youngculture.constants.Constants.EMPTY;
import static com.youngculture.constants.Constants.REGISTER_ERROR;

public class UserDAO implements DAOInterface<UserEntity> {

    @Override
    public List<UserEntity> getAll() {
        return null;
    }

    @Override
    public List<UserEntity> get( String username ) {
        Session session = HibernateUtils.getSessionFactory().openSession();

        List<UserEntity> results = session.createQuery( "FROM UserEntity u where u.username =: username", UserEntity.class).setParameter("username", username).getResultList();

        session.close();

        return results;
    }

    @Override
    public void update(UserEntity userEntity) {

    }

    @Override
    public void delete(UserEntity userEntity) {

    }

    @Override
    public String save(UserEntity user) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();

        try {
            session.save(user);

            session.getTransaction().commit();

            session.close();
        }
        catch(Exception e)
        {
            session.getTransaction().rollback();
            return REGISTER_ERROR;
        }

        return EMPTY;
    }
}
