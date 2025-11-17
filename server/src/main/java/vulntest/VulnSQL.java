package vulntest;
import java.sql.*;

public class VulnSQL {
    public static void main(String[] args) {
        String username = args[0]; // taint source

        try {
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/test", "root", "password"
            );

            Statement stmt = conn.createStatement();

            // inline taint → concatenation → SQL sink
            ResultSet rs = stmt.executeQuery(
                "SELECT * FROM users WHERE username = '" + username + "'"
            );
        } catch (Exception e) {
            // ignore
        }
    }
}
