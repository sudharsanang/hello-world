package vulntest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class VulnSQL {

    public static void main(String[] args) throws Exception {

        // Tainted source: CodeQL recognizes environment variables as untrusted
        String username = System.getenv("USER_INPUT");

        // Intentional SQL Injection vulnerability
        String query = "SELECT * FROM users WHERE username = '" + username + "'";

        Connection conn = DriverManager.getConnection("jdbc:h2:mem:test");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            System.out.println(rs.getString("username"));
        }
    }
}
