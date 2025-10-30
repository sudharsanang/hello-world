package com.example;

/**
 * This is a class.
 */
public class Greeter {

  /**
   * This is a constructor.
   */
  public Greeter() {

  }

  //TODO: Add javadoc comment
  public String greet(String someone) {
    return String.format("Hello, %s!", someone);
  }
}
// vuln_hello_java.java
// Minimal JDBC example showing concatenated SQL from user input.
// Vulnerability: SQL injection via string concatenation.

import java.sql.*;

public class vuln_hello_java {
    public static void main(String[] args) {
        String name = (args.length > 0) ? args[0] : "world";
        System.out.println("Hello, " + name + "!");

        // NOTE: This code assumes a JDBC connection. For test purposes
        // you can mock or connect to a test DB. Do NOT use production systems.
        Connection conn = null;
        Statement stmt = null;
        try {
            // Example only: driver/URL omitted for brevity
            // conn = DriverManager.getConnection("jdbc:sqlite::memory:");
            stmt = conn.createStatement();
            String sql = "SELECT * FROM users WHERE name = '" + name + "'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println("found user: " + rs.getString("name"));
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println("DB error: " + e);
        } finally {
            try { if (stmt != null) stmt.close(); } catch (SQLException ignored) {}
            try { if (conn != null) conn.close(); } catch (SQLException ignored) {}
        }
    }
}
