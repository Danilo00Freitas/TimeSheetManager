package TimeSheetApp.Main;

import TimeSheetApp.BackEnd.ScreenManager;
import TimeSheetApp.BackEnd.SystemIntegration.NewHttpRequest;

public class Main {
    public static void main(String[] args) {
        /*ScreenManager screenManager = new ScreenManager();
        screenManager.showLoginScreen();*/

        NewHttpRequest newHttpRequest = new NewHttpRequest();
        String cepRequest = newHttpRequest.getAddressInfo("06210103");

        System.out.println(cepRequest);
    }
}
