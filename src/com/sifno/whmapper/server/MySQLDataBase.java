package com.sifno.whmapper.server;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.*;

/**
 * Created by Алёна on 21.07.2015.
 */
public class MySQLDataBase {

    private static final String URL = "jdbc:mysql://localhost:3306/evedb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Cbayjhektp88";
    private Connection connection;
    public MySQLDataBase() throws SQLException {


        Driver driver = new FabricMySQLDriver();
        DriverManager.registerDriver(driver);
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

    }

    public Connection getConnection() {
        return connection;
    }

    public void close() throws SQLException {
        connection.close();
    }


}
