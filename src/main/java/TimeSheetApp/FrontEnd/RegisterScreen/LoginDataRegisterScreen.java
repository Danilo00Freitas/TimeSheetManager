package TimeSheetApp.FrontEnd.RegisterScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import TimeSheetApp.BackEnd.ScreenManager;

public class LoginDataRegisterScreen extends JFrame {
    private JPanel mainPanel;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton registerButton;
    private JButton goBackButton;
    private ScreenManager screenManager;

    public LoginDataRegisterScreen(ScreenManager screenManager) {
        // Inicializando o gerenciador de tela
        this.screenManager = screenManager;

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
                screenManager.showLoginScreen();
            }
        });

        // Botão voltar
        goBackButton = new JButton("Voltar");
        mainPanel.add(goBackButton);
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screenManager.showAddressDataRegisterScreen();
            }
        });
    }
}
