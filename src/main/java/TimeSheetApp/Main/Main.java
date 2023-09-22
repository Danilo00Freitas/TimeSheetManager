package TimeSheetApp.Main;

import TimeSheetApp.BackEnd.DataBaseInteraction.DataBaseConnection;
import TimeSheetApp.BackEnd.ScreenManager;

public class Main {
    public static void main(String[] args) {
        ScreenManager screenManager = new ScreenManager();
        screenManager.showLoginScreen();

        /*DataBaseConnection dataBaseConection = new DataBaseConnection();*/

    }
}
