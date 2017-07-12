package com.perepelitsya;


import java.sql.*;
/**
 * Created by Andriu on 7/12/2017.
 */
public class PacetJDBC {


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



    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;

        Class.forName(JDBC_DRIVER);
        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        connection.setAutoCommit(false);

        statement = connection.createStatement();

        String SQL = "SELECT * FROM USER ";
        ResultSet resultSet = statement.executeQuery(SQL);


        System.out.println("Initial developer's table content:");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");

            if (name == null) {
                System.out.println("Table is empty.");
                break;
            }
            String specialty = resultSet.getString("specialty");
            int salary = resultSet.getInt("salary");

            System.out.println("\n====================");
            System.out.println("id: " + id);
            System.out.println("Name: " + name);
            System.out.println("Specialty: " + specialty);
            System.out.println("Salary : $" + salary);
            System.out.println("====================\n");
        }

        SQL = "INSERT INTO USER VALUES (11, 'Proselyte', 'Java', 3000)";
        statement.addBatch(SQL);
        SQL = "INSERT INTO USER VALUES (12, 'AsyaSmile', 'UI/UX', 2500)";
        statement.addBatch(SQL);
        SQL = "INSERT INTO USER VALUES (13, 'Peter', 'C++', 3000)";
        statement.addBatch(SQL);

        try {


            statement.executeBatch();
            connection.commit();

            SQL = "SELECT * FROM USER ";
            resultSet = statement.executeQuery(SQL);

            System.out.println("Final developer's table content:");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String specialty = resultSet.getString("specialty");
                int salary = resultSet.getInt("salary");

                System.out.println("\n====================");
                System.out.println("id: " + id);
                System.out.println("Name: " + name);
                System.out.println("Specialty: " + specialty);
                System.out.println("Salary : $" + salary);
                System.out.println("====================\n");
            }

            resultSet.close();
            statement.close();
            connection.close();
        } finally {
            if (statement != null) {
                statement.close();
            }

            if (connection != null) {
                connection.close();
            }
        }

        System.out.println("Thank You.");
    }
}
