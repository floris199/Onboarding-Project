package com.youngculture.dao;

import com.youngculture.entities.UserEntity;
import org.hibernate.Session;

public class UserDAO {

    public static void main ( String args[] )
    {
        Session session = DAOUtils.getSessionFactory().openSession();
        session.beginTransaction();

        UserEntity emp = new UserEntity();
        emp.setUsername("test");
        emp.setPasswrd("test");

        session.save(emp);

        session.getTransaction().commit();
        DAOUtils.shutdown();
    }
}
