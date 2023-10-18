package TimeSheetApp.BackEnd.DataBaseInteraction;

import java.sql.*;

public class DbLoginManager {
    DbConnectionManager dbConnectionManager = new DbConnectionManager();
    private String userCpf;
    public DbLoginManager(){

    }

    public void insertIntoAddressTable(String cpf, String cep, String logradouro, String numero, String complemento,
                                       String bairro, String localidade, String uf) {

        try {
           var connection = dbConnectionManager.newDataBaseConnection();

            String query = "INSERT INTO addressTable (cpf, cep, logradouro, numero, complemento, bairro, localidade, uf) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // Define os valores usando placeholders
            preparedStatement.setString(1, cpf);
            preparedStatement.setString(2, cep);
            preparedStatement.setString(3, logradouro);
            preparedStatement.setString(4, numero);
            preparedStatement.setString(5, complemento);
            preparedStatement.setString(6, bairro);
            preparedStatement.setString(7, localidade);
            preparedStatement.setString(8, uf);

            // Executa a inserção
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Linhas afetadas: " + rowsAffected);

            // Fecha a conexão
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertIntoEmployeeRegisterTable(String cpf, String nome, String setor, String cargo, String superiorImediato,
                                                String rotinaDetrabalho, String genero, String telefone, String dataDeNascimento) {
        try {
            var connection = dbConnectionManager.newDataBaseConnection();

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

    public Boolean checkRegisterExistence(String cpf) {
        try {
            var connection = dbConnectionManager.newDataBaseConnection();
            String query = "SELECT cpf FROM employeeRegisters where cpf = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, cpf);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        }
        catch (SQLException exception) {
            exception.printStackTrace();
            return false;
        }
    }


    public void insertIntologinRegisterTable (String cpf, String email, String password, byte[] salt){
        try {
            var connection = dbConnectionManager.newDataBaseConnection();
            String query = "INSERT INTO loginRegisterTable (cpf, email, senha, salt) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // Define os valores usando placeholders
            preparedStatement.setString(1, cpf);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, String.valueOf(salt));

            // Executa a inserção
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Linhas afetadas: " + rowsAffected);

            // Fecha a conexão
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean verifyLogin(String username, char[] password) {
        try {
            var connection = dbConnectionManager.newDataBaseConnection();
            String query = "SELECT email, senha FROM loginRegisterTable WHERE email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String email = resultSet.getString("email");
                String storedPassword = resultSet.getString("senha");
                String enteredPassword = String.valueOf(password);

                if (email.equals(username) && storedPassword.equals(enteredPassword)) {
                    String query1 = "SELECT cpf FROM loginRegisterTable WHERE email = ?";
                    PreparedStatement preparedStatement1 = connection.prepareStatement(query1);
                    preparedStatement1.setString(1,username);
                    ResultSet resultSet1 = preparedStatement1.executeQuery();

                    if (resultSet1.next()){
                        this.userCpf = resultSet1.getString("cpf");
                        connection.close();
                        return true;

                    }
                }
            }else{
                connection.close();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }

        return false;
    }

    public String getUserCpf() {
        return this.userCpf;
    }
}



