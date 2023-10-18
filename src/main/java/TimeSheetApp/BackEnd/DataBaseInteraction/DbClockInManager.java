package TimeSheetApp.BackEnd.DataBaseInteraction;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbClockInManager {
    DbConnectionManager dbConnectionManager = new DbConnectionManager();

    public DbClockInManager(){
    }

    public void insertArrival(String cpf, String date, String time, String type, String justification, String reason) {

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




}
