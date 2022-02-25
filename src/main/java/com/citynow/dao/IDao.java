package com.citynow.dao;

import com.citynow.mapper.IMapper;

import java.util.List;

/**
 * Dao interface
 * @author VuKhanh
 * @param <T>
 */
public interface IDao<T> {

    /**
     * Query from database (select,..)
     * @param sql
     * @param mapper
     * @param parameters
     * @param <T>
     * @return List Object
     */
    <T> List<T> query(String sql, IMapper<T> mapper, Object... parameters);

    /**
     * Update data from database (Update, delete,..)
     * @param sql
     * @param parameters
     */
    void update (String sql, Object... parameters);

    /**
     * insert new value to database (Insert,...)
     * @param sql
     * @param parameters
     * @return ID Object
     */
    Long insert (String sql, Object... parameters);

    /**
     * Count record from database
     * @param sql
     * @param parameters
     * @return total record
     */
    int count(String sql, Object... parameters);
}
