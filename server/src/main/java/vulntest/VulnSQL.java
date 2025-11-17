package vulntest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.http.HttpServletRequest;

public class VulnSQL {

    public void vulnerableQuery(HttpServletRequest request) throws Exception {

        // Get user input
        String username = request.getParameter("user");

        // Intentional vulnerability: unparameterized SQL
        // This WILL be caught by CodeQL (java/sql-injection)
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
