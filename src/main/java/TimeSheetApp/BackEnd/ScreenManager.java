package TimeSheetApp.BackEnd;

import TimeSheetApp.BackEnd.PasswordManager.PasswordManager;
import TimeSheetApp.BackEnd.SystemIntegration.CepInformation;
import TimeSheetApp.BackEnd.SystemIntegration.CepInformationRec;
import TimeSheetApp.BackEnd.SystemIntegration.PersonalDataInformation;
import TimeSheetApp.FrontEnd.LoginAndMenuScreens.LoginScreen;
import TimeSheetApp.FrontEnd.LoginAndMenuScreens.MenuScreen;
import TimeSheetApp.FrontEnd.TsMgmtScreens.EntryScreen;
import TimeSheetApp.FrontEnd.TsMgmtScreens.ChangeEntryScreen;
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
    private PersonalDataInformation personalDataInformation;
    private CepInformation cepInformation;

    private String userCpf;

    private PasswordManager passwordManager;

    public ScreenManager() {
        // Inicializa os objetos das telas MenuScreen, EntryScreen e ChangeEntryScreen e passwordManager

        passwordManager = new PasswordManager();
        timeSheetManager = new TimeSheetManager();
        menuScreen = new MenuScreen(this);
        entryScreen = new EntryScreen(this, timeSheetManager);
        changeEntryScreen = new ChangeEntryScreen(this, timeSheetManager);
        loginScreen = new LoginScreen(this, passwordManager);
        personalDataRegisterScreen = new PersonalDataRegisterScreen(this);

        personalDataInformation = new PersonalDataInformation("","","","","",
                "","","","");

        CepInformationRec cepInformationRec = new CepInformationRec("","","","","","");

        addressDataRegisterScreen = new AddressDataRegisterScreen(this);

        loginDataRegisterScreen = new LoginDataRegisterScreen(this, passwordManager);
        this.userCpf = "";
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

    public void showMenuScreenAndSetCpf(String cpf) {
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

    public void showLoginScreen() {
        loginScreen.setVisible(true);
        entryScreen.setVisible(false);
        addressDataRegisterScreen.setVisible(false);
        menuScreen.setVisible(false);
        personalDataRegisterScreen.setVisible(false);
        changeEntryScreen.setVisible(false);
        loginDataRegisterScreen.setVisible(false);
    }

    public void showPersonalDataRegisterScreen() {
        loginScreen.setVisible(false);
        entryScreen.setVisible(false);
        addressDataRegisterScreen.setVisible(false);
        menuScreen.setVisible(false);
        personalDataRegisterScreen.setVisible(true);
        changeEntryScreen.setVisible(false);
        loginDataRegisterScreen.setVisible(false);
    }

    public void showAddressDataRegisterScreen(PersonalDataInformation personalDataInformation) {
        this.personalDataInformation = personalDataInformation;
        loginScreen.setVisible(false);
        entryScreen.setVisible(false);
        addressDataRegisterScreen.setVisible(true);
        menuScreen.setVisible(false);
        personalDataRegisterScreen.setVisible(false);
        changeEntryScreen.setVisible(false);
        loginDataRegisterScreen.setVisible(false);
    }

    public void showLoginDataRegisterScreen(CepInformation cepInformation) {

        this.cepInformation = cepInformation;
        loginScreen.setVisible(false);
        entryScreen.setVisible(false);
        addressDataRegisterScreen.setVisible(false);
        menuScreen.setVisible(false);
        personalDataRegisterScreen.setVisible(false);
        changeEntryScreen.setVisible(false);
        loginDataRegisterScreen.setVisible(true);
    }
    public void returnToAddresScreen(){
        loginScreen.setVisible(false);
        entryScreen.setVisible(false);
        addressDataRegisterScreen.setVisible(true);
        menuScreen.setVisible(false);
        personalDataRegisterScreen.setVisible(false);
        changeEntryScreen.setVisible(false);
        loginDataRegisterScreen.setVisible(false);
    }


    public PersonalDataInformation getPdi(){
        return this.personalDataInformation;
    }
    public CepInformation getAddInfo(){return this.cepInformation;}

    public void setUserCpf(String cpf){
        this.userCpf = cpf;
    }

    public String getUserCpf(){
        return this.userCpf;
    }


}
