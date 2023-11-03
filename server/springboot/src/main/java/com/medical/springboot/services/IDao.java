package com.medical.springboot.services;

import java.util.List;

public interface IDao<T> {

    public T create(T t);

    public List<T> read(String key); //read by key

    public T update(T t);

    public boolean delete(String key);
}
