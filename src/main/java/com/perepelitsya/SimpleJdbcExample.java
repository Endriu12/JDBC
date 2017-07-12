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


        //we turn off auto commit, then we can use transaction when we need her
        connection.setAutoCommit(false);


        System.out.println("Creating statment who add us simple method for use db");

        statement = connection.createStatement();

        String sql = "Select * FROM user";

        ResultSet resultSet = statement.executeQuery(sql);

        Savepoint savepointOne = connection.setSavepoint("SavepointOne");




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



        try {
            sql = "INSERT INTO developer VALUES (6, 'John','C#', 2200)";
            statement.executeUpdate(sql);

            sql = "INSE INTHE developers VALUES (7, 'Ron', 'Ruby', 1900)";
            statement.executeUpdate(sql);

            connection.commit();
        } catch (SQLException e) {
            System.out.println("SQLException. Executing rollback to savepoint...");
            connection.rollback(savepointOne);
        }

        resultSet = statement.executeQuery("Select * FROM user");

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
