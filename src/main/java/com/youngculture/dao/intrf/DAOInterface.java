package com.youngculture.dao.intrf;

import java.util.List;

public interface DAOInterface<T> {
    public List<T> getAll();
    public List<T> get(String id);
    public void update(T t);
    public void delete(T t);
    public String save(T t);
}
