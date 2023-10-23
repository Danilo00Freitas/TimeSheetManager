package TimeSheetApp.BackEnd.DataBaseInteraction;

import java.sql.*;

public class DbConnectionManager {
    private String url = "jdbc:mysql://localhost:3306/timeSheetDB";

    //Usuário e senha ocultados... faça contato com Danilo dos Santos Freitas
    private String usuario = "";
    private String senha = "";


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





