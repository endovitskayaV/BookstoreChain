package ru.vsu.valya.bookstch.db.config;

import java.sql.*;

public class DBConnection {
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/bookstore_chain";
    private static final String USER = "root";
    private static final String PASSWORD = "end080398";

    private final Connection connection;

    private DBConnection(Connection connection) throws SQLException, ClassNotFoundException {
        this.connection = connection;
    }

    public static  DBConnection newInstance() throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        return new DBConnection(DriverManager.getConnection(DATABASE_URL, USER, PASSWORD));
    }

    public ResultSet executeQuery(String query) throws SQLException {
        return connection.createStatement().executeQuery(query);
    }

    public void executeUpdate(String query) throws SQLException {
         connection.prepareStatement(query).executeUpdate();
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }
}
