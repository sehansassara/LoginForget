package lk.ijse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    public static Connection getCon() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/LNVCompany";
        String username = "";
        String password = "";
        return DriverManager.getConnection(url, username, password);
    }
}
