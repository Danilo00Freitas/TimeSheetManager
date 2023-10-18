package TimeSheetApp.BackEnd.DataBaseInteraction;

import java.sql.*;

public class DbConnectionManager {
    private String url = "jdbc:mysql://localhost:3306/timeSheetDB";
    private String usuario = "root";
    private String senha = "Ldan91jit@";


    public DbConnectionManager() {

    }

    public Connection newDataBaseConnection() throws SQLException {

        return DriverManager.getConnection(url, usuario, senha);
    }

    public void closeDataBaseConnection(Connection connection) throws SQLException {
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}





