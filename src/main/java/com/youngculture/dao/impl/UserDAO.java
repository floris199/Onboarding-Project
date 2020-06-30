package com.youngculture.dao.impl;

import com.youngculture.dao.intrf.DAOInterface;
import com.youngculture.dao.utils.HibernateUtils;
import com.youngculture.entities.UserEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
    public void update(UserEntity userEntity, Session session) throws HibernateException{

    }

    @Override
    public void delete(UserEntity userEntity, Session session) throws HibernateException{

    }

    @Override
    public void save(UserEntity user, Session session) throws HibernateException {
            session.save(user);

            session.getTransaction().commit();

            session.close();
    }
}
