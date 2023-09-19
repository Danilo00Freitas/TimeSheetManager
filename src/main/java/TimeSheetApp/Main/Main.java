package TimeSheetApp.Main;

import TimeSheetApp.BackEnd.ScreenManager;

public class Main {
    public static void main(String[] args) {
        ScreenManager screenManager = new ScreenManager();
        screenManager.showLoginScreen();
    }
}
