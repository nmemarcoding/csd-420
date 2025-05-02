// Name: Nima Memarzadeh
// Date: 05/02/2025
// Assignment: M9: Programming Assignment

// This Java program connects to a MySQL database, creates a 
// table, and inserts data into it.
// It handles exceptions and ensures that resources 
// are closed properly.


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement; 

public class TestConnection {

    public static void main(String[] args) {
        Connection con = null;
        Statement stmt = null; // Added Statement variable
        String url = "jdbc:mysql://localhost:3306/databasedb?";
        String user = "student1";
        String password = "pass";

        System.out.println("--- Starting Database Connection Test ---");

        try {
            // Load the MySQL JDBC driver
            System.out.println("Loading MySQL JDBC driver...");
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                System.out.println("Driver loaded successfully.");
            } catch (ClassNotFoundException e) {
                System.err.println("Error: MySQL JDBC Driver not found.");
                e.printStackTrace();
                throw e; // Re-throw to be caught by the outer catch block
            }

            // Attempt to establish the connection
            System.out.println("Attempting to connect to database: " + url + " with user: " + user);
            try {
                con = DriverManager.getConnection(url + "user=" + user + "&password=" + password);
                // If connection is successful
                System.out.println("Connection successful!");
                System.out.println("----------------------------------------");
            } catch (SQLException e) {
                System.err.println("Error establishing database connection.");
                e.printStackTrace();
                throw e; // Re-throw to stop execution if connection fails
            }


            // Create a statement object
            System.out.println("Creating statement object...");
            try {
                stmt = con.createStatement();
                System.out.println("Statement object created.");
                System.out.println("----------------------------------------");
            } catch (SQLException e) {
                System.err.println("Error creating statement object.");
                e.printStackTrace();
                throw e; // Re-throw to stop execution if statement creation fails
            }


            // Drop table if it exists
            System.out.println("Attempting to drop table 'address33' if it exists...");
            try {
                stmt.executeUpdate("DROP TABLE IF EXISTS address33");
                System.out.println("Table address33 dropped (if it existed).");
            } catch (SQLException e) {
                System.err.println("Error dropping table address33.");
                e.printStackTrace();
                // Decide if this error should stop execution or just be logged
            }
            System.out.println("----------------------------------------");

            // Create table
            System.out.println("Attempting to create table 'address33'...");
            try {
                stmt.executeUpdate("CREATE TABLE address33(ID int PRIMARY KEY,LASTNAME varchar(40), " +
                                   "FIRSTNAME varchar(40), STREET varchar(40), CITY varchar(40), STATE varchar(40), " +
                                   "ZIP varchar(40))");
                System.out.println("Table address33 created successfully.");
            } catch (SQLException e) {
                System.err.println("Error creating table address33.");
                e.printStackTrace();
                // If table creation fails, might not want to proceed
                throw e; // Re-throw to stop execution if table creation is essential
            }
            System.out.println("----------------------------------------");

            // Insert data
            System.out.println("Attempting to insert data into 'address33'...");
            try {
                int rowsAffected = 0;
                System.out.println("Inserting record 1...");
                rowsAffected += stmt.executeUpdate("INSERT INTO address33 VALUES(24,'Lou','Woods','1919 Bluewing Circle','Bellevue','NE','68123')");
                System.out.println("Inserting record 2...");
                rowsAffected += stmt.executeUpdate("INSERT INTO address33 VALUES(25,'Lou','Woods','1919 Bluewing Circle','Bellevue','NE','68123')");
                System.out.println("Inserting record 3...");
                rowsAffected += stmt.executeUpdate("INSERT INTO address33 VALUES(26,'Lou','Woods','1919 Bluewing Circle','Bellevue','NE','68123')");
                System.out.println(rowsAffected + " records inserted successfully into address33.");
            } catch (SQLException e) {
                System.err.println("Error inserting data into address33.");
                e.printStackTrace();
                // Decide if this error should stop execution or just be logged
            }
            System.out.println("----------------------------------------");

        } catch (ClassNotFoundException e) {
            // Error already printed in the inner catch block
            System.err.println("Halting execution due to missing driver.");
        } catch (SQLException e) {
            System.err.println("A critical database operation failed. Halting execution.");
            // Specific error details should have been printed by inner catch blocks
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
            System.err.println("Message: " + e.getMessage());
            // e.printStackTrace(); // Optionally print stack trace again here if needed
        } catch (Exception e) {
            System.err.println("An unexpected error occurred.");
            e.printStackTrace();
        } finally {
            // Ensure the statement is closed
            System.out.println("--- Starting Cleanup ---");
            if (stmt != null) { // Added check and close for statement
                try {
                    System.out.println("Closing statement...");
                    stmt.close();
                    System.out.println("Statement closed.");
                } catch (SQLException e) {
                    System.err.println("Error closing the statement.");
                    e.printStackTrace();
                }
            }
            // Ensure the connection is closed
            if (con != null) {
                try {
                    System.out.println("Closing database connection...");
                    con.close();
                    System.out.println("Database connection closed.");
                } catch (SQLException e) {
                    System.err.println("Error closing the connection.");
                    e.printStackTrace();
                }
            }
            System.out.println("--- Database Connection Test Finished ---");
        }
    }
}
