package TimeSheetApp.BackEnd.DataBaseInteraction;

import javax.xml.transform.Result;
import java.sql.*;

public class DataBaseConnection {
    private String url = "jdbc:mysql://localhost:3306/timeSheetDB";
    private String usuario = "root";
    private String senha = "Ldan91jit@";


    public DataBaseConnection() {

    }

    public void newDataBaseConnection(String sqlInstruction) {

        try {
            // Cria um objeto Connection
            Connection connection = DriverManager.getConnection(url, usuario, senha);

            //Consulta que deve ser executada


            // Fecha a conexão
            connection.close();
        } catch (SQLException e) {
            // Lida com exceções SQL, por exemplo, imprime o erro
            e.printStackTrace();
        }
    }

    public void insertIntoEmployeeRegisterTable(String cpf, String nome, String setor, String cargo, String superiorImediato,
                                                String rotinaDetrabalho, String genero, String telefone, String dataDeNascimento) {
        try {
            Connection connection = DriverManager.getConnection(url, usuario, senha);
            String query = "INSERT INTO employeeRegisters (cpf, nome, setor, cargo, superiorImediato, rotinaDetrabalho, genero, telefone, dataDeNascimento) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // Define os valores usando placeholders
            preparedStatement.setString(1, cpf);
            preparedStatement.setString(2, nome);
            preparedStatement.setString(3, setor);
            preparedStatement.setString(4, cargo);
            preparedStatement.setString(5, superiorImediato);
            preparedStatement.setString(6, rotinaDetrabalho);
            preparedStatement.setString(7, genero);
            preparedStatement.setString(8, telefone);
            preparedStatement.setString(9, dataDeNascimento);

            // Executa a inserção
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Linhas afetadas: " + rowsAffected);

            // Fecha a conexão
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

