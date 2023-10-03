package TimeSheetApp.FrontEnd.RegisterScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import TimeSheetApp.BackEnd.DataBaseInteraction.DataBaseConnection;
import TimeSheetApp.BackEnd.InfoValidator;
import TimeSheetApp.BackEnd.PasswordManager.PasswordManager;
import TimeSheetApp.BackEnd.ScreenManager;
import TimeSheetApp.BackEnd.SystemIntegration.PersonalDataInformation;

public class LoginDataRegisterScreen extends JFrame {
    private JPanel mainPanel;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton registerButton;
    private JButton goBackButton;
    private ScreenManager screenManager;
    private DataBaseConnection dataBaseConnection;
    private PersonalDataInformation personalDataInformation;
    private PasswordManager passwordManager;;


    public LoginDataRegisterScreen(ScreenManager screenManager, PasswordManager passwordManager) {
        // Inicializando o gerenciador de tela
        this.screenManager = screenManager;
        this.passwordManager = passwordManager;

        // Criando a janela principal
        setTitle("Cadastro de E-mail e Senha");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null); // Centralizar a janela na tela
        setResizable(false);

        // Criando o painel principal onde os componentes ficarão
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 1));
        getContentPane().add(mainPanel);

        // Campo de entrada para e-mail
        emailField = new JTextField();
        emailField.setBorder(BorderFactory.createTitledBorder("E-mail"));
        mainPanel.add(emailField);

        // Campo de entrada para senha
        passwordField = new JPasswordField();
        passwordField.setBorder(BorderFactory.createTitledBorder("Senha"));
        mainPanel.add(passwordField);

        // Botão de cadastro
        registerButton = new JButton("Cadastrar");
        mainPanel.add(registerButton);

        registerButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                InfoValidator infoValidator = new InfoValidator();
                if (!infoValidator.validateEmail(emailField.getText()) ||
                        !infoValidator.validatePassword(passwordField.getText())){
                    JOptionPane.showMessageDialog(null,"E-mail ou senha inválidos...");
                    emailField.setText("");
                    passwordField.setText("");
                }else{


                    dataBaseConnection = new DataBaseConnection();
                    var pdi = screenManager.getPdi();
                    dataBaseConnection.insertIntoEmployeeRegisterTable(pdi.getCpf(), pdi.getNome(), pdi.getSetor(),
                            pdi.getCargo(), pdi.getSuperior(), pdi.getRotinaDeTrabalho(),pdi.getGenero(),
                            pdi.getTelefone(),pdi.getDataDeNascimento());

                    var addressInfo = screenManager.getAddInfo();
                    dataBaseConnection.insertIntoAddressTable(pdi.getCpf(),addressInfo.getCep(), addressInfo.getLogradouro(),
                            addressInfo.getNumero(), addressInfo.getComplemento(), addressInfo.getBairro(),
                            addressInfo.getLocalidade(), addressInfo.getUf());

                    //gerando e codificando senha
                    char[] senhaDigitada = passwordField.getPassword();

                    String password;
                    try {
                        password = passwordManager.generatePassword(senhaDigitada);
                        byte[] salt = passwordManager.generateSalt();
                        dataBaseConnection.insertIntologinRegisterTable(pdi.getCpf(),emailField.getText(), password, salt);

                    } catch (NoSuchAlgorithmException ex) {
                        throw new RuntimeException(ex);
                    }

                    emailField.setText("");
                    passwordField.setText("");
                    screenManager.showLoginScreen();
                }
                System.out.println("botão pressionado");
                System.out.println("PID funcionando 3: " + screenManager.getPdi().getNome());
            }
        });

        // Botão voltar
        goBackButton = new JButton("Voltar");
        mainPanel.add(goBackButton);
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screenManager.returnToAddresScreen();
                clearFields();
            }
        });
    }
    public void clearFields(){
        emailField.setText("");
        passwordField.setText("");
    }

}
