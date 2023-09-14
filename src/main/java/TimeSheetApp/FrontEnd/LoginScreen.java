package TimeSheetApp.FrontEnd;

import TimeSheetApp.BackEnd.ScreenManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen extends JFrame {
    private JPanel mainPanel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private ScreenManager screenManager;

    public LoginScreen(ScreenManager screenManager) {
        // Inicializando screen manager
        this.screenManager = screenManager;

        // Criando armação principal
        setTitle("TimeSheet Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null); // Centralizar a janela na tela
        setResizable(false);

        // Criando painel principal onde os componentes vão ficar
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 1));
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
                screenManager.showMenuScreen();
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Encerrar a aplicação quando a janela de login for fechada
        setVisible(true);
    }
}

