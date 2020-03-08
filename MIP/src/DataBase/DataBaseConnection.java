package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {

    public Connection connect() {


        String url = "jdbc:mysql://localhost:3306/dbstudentbg";
        String username = "root";
        String password = "1q2w3e4r";

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            return con;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

}
