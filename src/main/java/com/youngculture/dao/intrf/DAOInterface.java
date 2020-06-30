package com.youngculture.dao.intrf;

import org.hibernate.Session;

import java.util.List;

public interface DAOInterface<T> {
    public List<T> getAll();
    public List<T> get(String id);
    public void update(T t, Session session);
    public void delete(T t, Session session);
    public void save(T t, Session session);
}
