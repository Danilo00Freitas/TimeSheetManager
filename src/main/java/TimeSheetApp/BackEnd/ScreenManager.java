package TimeSheetApp.BackEnd;

import TimeSheetApp.FrontEnd.LoginScreen;
import TimeSheetApp.FrontEnd.MenuScreen;
import TimeSheetApp.FrontEnd.EntryScreen;
import TimeSheetApp.FrontEnd.ChangeEntryScreen;
import TimeSheetApp.FrontEnd.RegisterScreen.AddressDataRegisterScreen;
import TimeSheetApp.FrontEnd.RegisterScreen.LoginDataRegisterScreen;
import TimeSheetApp.FrontEnd.RegisterScreen.PersonalDataRegisterScreen;

public class ScreenManager {
    private MenuScreen menuScreen;
    private EntryScreen entryScreen;
    private ChangeEntryScreen changeEntryScreen;
    private TimeSheetManager timeSheetManager;
    private LoginScreen loginScreen;
    private PersonalDataRegisterScreen personalDataRegisterScreen;
    private AddressDataRegisterScreen addressDataRegisterScreen;
    private LoginDataRegisterScreen loginDataRegisterScreen;

    public ScreenManager() {
        // Inicializa os objetos das telas MenuScreen, EntryScreen e ChangeEntryScreen

        timeSheetManager = new TimeSheetManager();
        menuScreen = new MenuScreen(this);
        entryScreen = new EntryScreen(this, timeSheetManager);
        changeEntryScreen = new ChangeEntryScreen(this,timeSheetManager);
        loginScreen = new LoginScreen(this);
        personalDataRegisterScreen = new PersonalDataRegisterScreen(this);
        addressDataRegisterScreen = new AddressDataRegisterScreen(this);
        loginDataRegisterScreen =  new LoginDataRegisterScreen(this);
    }

    public void showMenuScreen() {
        // Mostra a tela do Menu e esconde as outras telas
        loginScreen.setVisible(false);
        entryScreen.setVisible(false);
        addressDataRegisterScreen.setVisible(false);
        menuScreen.setVisible(true);
        personalDataRegisterScreen.setVisible(false);
        changeEntryScreen.setVisible(false);
        loginDataRegisterScreen.setVisible(false);
    }

    public void showEntryScreen() {
        // Mostra a tela de Entrada e esconde as outras telas
        loginScreen.setVisible(false);
        entryScreen.setVisible(false);
        addressDataRegisterScreen.setVisible(false);
        menuScreen.setVisible(false);
        personalDataRegisterScreen.setVisible(false);
        changeEntryScreen.setVisible(true);
        loginDataRegisterScreen.setVisible(false);
    }

    public void showChangeEntryScreen() {
        loginScreen.setVisible(false);
        entryScreen.setVisible(true);
        addressDataRegisterScreen.setVisible(false);
        menuScreen.setVisible(false);
        personalDataRegisterScreen.setVisible(false);
        changeEntryScreen.setVisible(false);
        loginDataRegisterScreen.setVisible(false);

    }

    public void showLoginScreen(){
        loginScreen.setVisible(true);
        entryScreen.setVisible(false);
        addressDataRegisterScreen.setVisible(false);
        menuScreen.setVisible(false);
        personalDataRegisterScreen.setVisible(false);
        changeEntryScreen.setVisible(false);
        loginDataRegisterScreen.setVisible(false);
    }

    public void showPersonalDataRegisterScreen(){
        loginScreen.setVisible(false);
        entryScreen.setVisible(false);
        addressDataRegisterScreen.setVisible(false);
        menuScreen.setVisible(false);
        personalDataRegisterScreen.setVisible(true);
        changeEntryScreen.setVisible(false);
        loginDataRegisterScreen.setVisible(false);
    }
    public void showAddressDataRegisterScreen(){
        loginScreen.setVisible(false);
        entryScreen.setVisible(false);
        addressDataRegisterScreen.setVisible(true);
        menuScreen.setVisible(false);
        personalDataRegisterScreen.setVisible(false);
        changeEntryScreen.setVisible(false);
        loginDataRegisterScreen.setVisible(false);
    }

    public void showLoginDataRegisterScreen(){
        loginScreen.setVisible(false);
        entryScreen.setVisible(false);
        addressDataRegisterScreen.setVisible(false);
        menuScreen.setVisible(false);
        personalDataRegisterScreen.setVisible(false);
        changeEntryScreen.setVisible(false);
        loginDataRegisterScreen.setVisible(true);
    }

}
