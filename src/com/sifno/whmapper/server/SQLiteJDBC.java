package com.sifno.whmapper.server;
import java.io.File;
import java.sql.*;

public class SQLiteJDBC {
    //public final static String path = "D:\\GitHub\\WHMapper\\universeDataDx.db";
    public final static String path = "D:\\GitHub\\WHMapper\\whmapperdb.db";

    Connection connection = null;

    public SQLiteJDBC(File file) {

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:"+file.getPath());
        } catch (
            Exception e
        )

        {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        System.out.println("Opened database successfully");
    }


    public Connection getConnection() {
        return connection;
    }

    public boolean isConnected() {
        return connection !=null;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}