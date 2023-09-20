package TimeSheetApp.BackEnd.DataBaseInteraction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseConection {
    public DataBaseConection() {
        try {
            String url = "jdbc:mysql://localhost:3306/timeSheetDB";
            String usuario = "root";
            String senha = "Ldan91jit@";

            // Cria um objeto Connection
            Connection connection = DriverManager.getConnection(url, usuario, senha);

            // Fecha a conexão
            connection.close();
        } catch (SQLException e) {
            // Lida com exceções SQL, por exemplo, imprime o erro
            e.printStackTrace();
        }
    }
}
