package com.demo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public interface Dao<T> {
    T getOneByid(int id);
    List<T> getAll();
    boolean Delete(int id);
    boolean Update(T o);
    boolean Save(T o);
    void Close(Connection con,Statement st,ResultSet res);
}
// OPTIONS | GET | PUT | PATCH | POST| DELETE | MATCH | ...