package TimeSheetApp.Main;

import TimeSheetApp.BackEnd.ScreenManager;
import TimeSheetApp.BackEnd.SystemIntegration.CepInformationRec;
import TimeSheetApp.BackEnd.SystemIntegration.NewHttpRequest;

public class Main {
    public static void main(String[] args) {
        ScreenManager screenManager = new ScreenManager();
        screenManager.showLoginScreen();

        /*NewHttpRequest newHttpRequest = new NewHttpRequest();
        CepInformationRec cepRequest = newHttpRequest.getAddressInfo("0602614");
        System.out.println(cepRequest.toString());*/
    }
}
