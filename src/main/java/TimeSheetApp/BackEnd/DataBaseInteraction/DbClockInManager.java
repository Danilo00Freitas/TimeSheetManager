package TimeSheetApp.BackEnd.DataBaseInteraction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbClockInManager {
    DbConnectionManager dbConnectionManager = new DbConnectionManager();

    public DbClockInManager(){
    }

    public void insertIntoTimeRecordsTable(String cpf, String date, String time, String type, String justification, String reason) {

        try {
            var connection = dbConnectionManager.newDataBaseConnection();

            String query = "INSERT INTO timeRecordsTable (cpf, data, horario, tipo, justificativa, motivo   ) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // Define os valores usando placeholders
            preparedStatement.setString(1, cpf);
            preparedStatement.setString(2, date);
            preparedStatement.setString(3, time);
            preparedStatement.setString(4, type);
            preparedStatement.setString(5, justification);
            preparedStatement.setString(6, reason);

            // Executa a inserção
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Linhas afetadas: " + rowsAffected);

            // Fecha a conexão
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean verifyRegisterExistence(String type, String date){
        try{
            var connection = dbConnectionManager.newDataBaseConnection();
            String query = "select cpf from timeRecordsTable where tipo = ? AND data = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,type);
            preparedStatement.setString(1,date);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                connection.close();
                return true;
            }else {
                connection.close();
                return false;
            }

        }catch (Exception e){

            System.out.println(e);
            return true;
        }
    }
    

}
