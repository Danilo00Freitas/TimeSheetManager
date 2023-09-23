package TimeSheetApp.BackEnd;

import TimeSheetApp.BackEnd.SystemIntegration.CepInformation;
import TimeSheetApp.BackEnd.SystemIntegration.CepInformationRec;
import TimeSheetApp.BackEnd.SystemIntegration.PersonalDataInformation;
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
    private PersonalDataInformation personalDataInformation;
    private CepInformation cepInformation;

    public ScreenManager() {
        // Inicializa os objetos das telas MenuScreen, EntryScreen e ChangeEntryScreen

        timeSheetManager = new TimeSheetManager();
        menuScreen = new MenuScreen(this);
        entryScreen = new EntryScreen(this, timeSheetManager);
        changeEntryScreen = new ChangeEntryScreen(this, timeSheetManager);
        loginScreen = new LoginScreen(this);
        personalDataRegisterScreen = new PersonalDataRegisterScreen(this);

        personalDataInformation = new PersonalDataInformation("","","","","",
                "","","","");

        CepInformationRec cepInformationRec = new CepInformationRec("","","","","","");
        CepInformation cepInformation= new CepInformation(cepInformationRec);

        addressDataRegisterScreen = new AddressDataRegisterScreen(this);

        loginDataRegisterScreen = new LoginDataRegisterScreen(this);
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

    public void setNewPdi(PersonalDataInformation pdi){
        PersonalDataInformation newPdi = new PersonalDataInformation(pdi.getCpf(), pdi.getNome(),
                pdi.getSetor(), pdi.getCargo(), pdi.getSuperior(),pdi.getRotinaDeTrabalho(),
                pdi.getGenero(), pdi.getTelefone(),pdi.getDataDeNascimento());

    }

    public PersonalDataInformation getPdi(){
        return this.personalDataInformation;
    }
    public CepInformation getAddInfo(){return this.cepInformation;}

    public CepInformation getCepInformation(){return this.cepInformation;}

}
