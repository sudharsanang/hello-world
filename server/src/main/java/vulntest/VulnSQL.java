// src/main/java/vulntest/VulnSQL.java
// TAINT SOURCE: args[0]
// SINK: Statement.executeQuery with concatenated SQL -> should be flagged as java/sql-injection

package vulntest;

import java.sql.*;

public class VulnSQL {
    public static void main(String[] args) {
        String name = (args.length > 0) ? args[0] : "guest";
        System.out.println("Hello, " + name);

        Connection conn = null;
        Statement stmt = null;
        try {
            // Intentionally leave connection null; CodeQL flags the pattern statically
            stmt = conn.createStatement();
            String sql = "SELECT * FROM users WHERE name = '" + name + "'";
            // Vulnerable: concatenated user input in SQL
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
