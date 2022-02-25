package com.citynow.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Interface Mapper
 * @author VuKhanh
 * @param <T>
 */
public interface IMapper<T> {
    T map(ResultSet rs) throws SQLException;
}
