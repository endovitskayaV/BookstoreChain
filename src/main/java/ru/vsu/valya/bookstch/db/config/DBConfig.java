package ru.vsu.valya.bookstch.db.config;

import java.sql.*;

public class DBConfig {
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/bookstore_chain";
    private static final String USER = "root";
    private static final String PASSWORD = "end080398";

    private  static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    static {
        connection = null;
        statement = null;
    }

    public void createConnection() throws SQLException, ClassNotFoundException{
        System.out.println("Registering JDBC driver...");
        Class.forName(JDBC_DRIVER);

        System.out.println("Creating database connection...");
        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
    }

    public ResultSet executeQuery(String query) throws SQLException{

        System.out.println("Creating statement...");
        statement = connection.createStatement();

        System.out.println("Executing query...");
        resultSet = statement.executeQuery(query);
        return resultSet;
    }

    public void closeConnection() throws SQLException{
        if(statement!=null)   statement.close();
        if(connection!=null)  connection.close();
        if(resultSet!=null)   resultSet.close();
    }
}
