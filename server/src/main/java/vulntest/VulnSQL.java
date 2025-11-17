package vulntest;
import java.sql.*;

public class VulnSQL {

    public static void main(String[] args) throws Exception {

        String username = args[0]; // untrusted

        String query = "SELECT * FROM users WHERE username = '" + username + "'"; // SQL injection

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "pass");
        Statement st = con.createStatement();
        st.executeQuery(query);
    }
}
