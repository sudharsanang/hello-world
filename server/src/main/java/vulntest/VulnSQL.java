package vulntest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class VulnSQL {

    public void vulnerableQuery(String username) throws Exception {

        // Intentional vulnerability: unparameterized SQL
        // CodeQL WILL flag this as a SQL Injection
        String query = "SELECT * FROM users WHERE username = '" + username + "'";

        // Vulnerable execution
        Connection conn = DriverManager.getConnection("jdbc:h2:mem:test");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            System.out.println(rs.getString("username"));
        }
    }
}
