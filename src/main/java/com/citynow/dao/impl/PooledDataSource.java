package com.citynow.dao.impl;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PooledDataSource {

    private static ComboPooledDataSource dataSource;

    static {
        try {
            dataSource = new ComboPooledDataSource();
            ResourceBundle resource = ResourceBundle.getBundle("database");
            dataSource.setDriverClass(resource.getString("driverName")); //loads the jdbc driver
            dataSource.setJdbcUrl(resource.getString("url"));
            dataSource.setUser(resource.getString("user"));
            dataSource.setPassword(resource.getString("password"));
            // the settings below are optional
            // c3p0 can work with defaults
            dataSource.setInitialPoolSize(Integer.parseInt(resource.getString("init_pool_size")));
            dataSource.setMinPoolSize(Integer.parseInt(resource.getString("min_pool_size")));
            dataSource.setAcquireIncrement(Integer.parseInt(resource.getString("acquire_increment")));
            dataSource.setMaxPoolSize(Integer.parseInt(resource.getString("max_pool_size")));
            dataSource.setTestConnectionOnCheckin(true);

        }catch(PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
