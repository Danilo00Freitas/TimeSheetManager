package TimeSheetApp.FrontEnd.LoginAndMenuScreens;

import TimeSheetApp.BackEnd.DataBaseInteraction.DbConnectionManager;
import TimeSheetApp.BackEnd.DataBaseInteraction.DbLoginManager;
import TimeSheetApp.BackEnd.PasswordManager.PasswordManager;
import TimeSheetApp.BackEnd.ScreenManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;

public class LoginScreen extends JFrame {
    private JPanel mainPanel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private ScreenManager screenManager;
    private DbConnectionManager dataBaseConnection = new DbConnectionManager();
    private DbLoginManager dbLoginManager = new DbLoginManager();
    private char[] password;

    private String loginUserName;

    private PasswordManager passwordManager;



    public LoginScreen(ScreenManager screenManager, PasswordManager passwordManager) {
        // Inicializando screen manager
        this.screenManager = screenManager;
        this.passwordManager = passwordManager;

        // Criando armação principal
        setTitle("TimeSheet Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null); // Centralizar a janela na tela
        setResizable(false);

        // Criando painel principal onde os componentes vão ficar
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 1));
        getContentPane().add(mainPanel);

        // Campo de entrada para o nome de usuário
        usernameField = new JTextField();
        usernameField.setBorder(BorderFactory.createTitledBorder("Nome de Usuário"));
        mainPanel.add(usernameField);

        // Campo de entrada para a senha
        passwordField = new JPasswordField();
        passwordField.setBorder(BorderFactory.createTitledBorder("Senha"));
        mainPanel.add(passwordField);

        // Botão de login
        loginButton = new JButton("Login");
        mainPanel.add(loginButton);
        loginButton.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                try {
                    char[] senhaDigitada = passwordField.getPassword();
                    saveVariables(senhaDigitada);
                } catch (NoSuchAlgorithmException ex) {
                    throw new RuntimeException(ex);
                }

                System.out.println(loginUserName);
                System.out.println(password);

                if (dbLoginManager.verifyLogin(loginUserName, password)){
                    clearField();
                    screenManager.setUserCpf(dbLoginManager.getUserCpf());
                    screenManager.showMenuScreen();
                    JOptionPane.showMessageDialog(null,"Login realizado com sucesso! \n" + screenManager.getUserCpf());


                }else{
                    JOptionPane.showMessageDialog(null,"Erro ao realizar login. Verifique suas credênciais ou confirme se você está registrado!");
                    clearField();
                }

            }
        });

        // Botão para cadastro
        registerButton = new JButton("Realizar cadastro");
        mainPanel.add(registerButton);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screenManager.showPersonalDataRegisterScreen();
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Encerrar a aplicação quando a janela de login for fechada
        setVisible(true);
    }

    public void saveVariables(char[] password) throws NoSuchAlgorithmException {
        this.loginUserName = usernameField.getText();
        this.password = passwordManager.generatePassword(password).toCharArray();


    }
    public void clearField(){
        passwordField.setText("");
        usernameField.setText("");
    }
}

