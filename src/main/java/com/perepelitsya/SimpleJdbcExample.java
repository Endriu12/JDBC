package com.perepelitsya;

import java.sql.*;

/**
 * Created by Andriu on 7/9/2017.
 */
public class SimpleJdbcExample {

    /**
     * JDBC Driver and database url
     */
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost/jdbc";

    /**
     * User and Password
     */
    static final String USER = "root";
    static final String PASSWORD = "root";


    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Connection connection = null;
        Statement statement = null;


        System.out.println("Register JDBC driver......");

        Class.forName(JDBC_DRIVER);

        System.out.println("Creating db connection");


        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);




        System.out.println("Creating statment who add us simple method for use db");

        statement = connection.createStatement();

        String sql = "Select * FROM user";

        ResultSet resultSet = statement.executeQuery(sql);

        while(resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String specialty = resultSet.getString("specialty");
            int salary = resultSet.getInt("salary");

            System.out.println("\n================\n");
            System.out.println("id: " + id);
            System.out.println("Name: " + name);
            System.out.println("Specialty: " + specialty);
            System.out.println("Salary: $" + salary);

        }


        System.out.println("Close all");

        resultSet.close();
        statement.close();
        connection.close();

    }
}
