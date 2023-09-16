package TimeSheetApp.FrontEnd.RegisterScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import TimeSheetApp.BackEnd.ScreenManager;
import TimeSheetApp.BackEnd.SystemIntegration.NewHttpRequest;

public class AddressDataRegisterScreen extends JFrame {
    private JPanel mainPanel;
    private JTextField cepField;
    private JTextField logradouroField;
    private JTextField complementoField;
    private JTextField bairroField;
    private JTextField localidadeField;
    private JTextField ufField;
    private JButton registerButton;
    private JButton goBackButton;
    private ScreenManager screenManager;
    private NewHttpRequest newHttpRequest = new NewHttpRequest();

    public AddressDataRegisterScreen(ScreenManager screenManager) {
        // Inicializando o gerenciador de tela
        this.screenManager = screenManager;

        // Criando a janela principal
        setTitle("Cadastro de Endereço");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null); // Centralizar a janela na tela
        setResizable(false);

        // Criando o painel principal onde os componentes ficarão
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(8, 1));
        getContentPane().add(mainPanel);

        // Campo de entrada para CEP
        cepField = new JTextField();
        cepField.setBorder(BorderFactory.createTitledBorder("CEP"));
        mainPanel.add(cepField);

        // Campo de entrada para logradouro
        logradouroField = new JTextField();
        logradouroField.setBorder(BorderFactory.createTitledBorder("Logradouro"));
        mainPanel.add(logradouroField);

        // Campo de entrada para complemento
        complementoField = new JTextField();
        complementoField.setBorder(BorderFactory.createTitledBorder("Complemento"));
        mainPanel.add(complementoField);

        // Campo de entrada para bairro
        bairroField = new JTextField();
        bairroField.setBorder(BorderFactory.createTitledBorder("Bairro"));
        mainPanel.add(bairroField);

        // Campo de entrada para localidade
        localidadeField = new JTextField();
        localidadeField.setBorder(BorderFactory.createTitledBorder("Localidade"));
        mainPanel.add(localidadeField);

        // Campo de entrada para UF
        ufField = new JTextField();
        ufField.setBorder(BorderFactory.createTitledBorder("UF"));
        mainPanel.add(ufField);

        //Adicionando verificação de CEP
        cepField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String cepText = cepField.getText();
                if (cepText.length() == 8){
                    var cepInfo = newHttpRequest.getAddressInfo(cepText);
                    System.out.println(cepInfo);
                }
            }
        });

        // Botão de cadastro
        registerButton = new JButton("Próximo");
        mainPanel.add(registerButton);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screenManager.showLoginDataRegisterScreen();
            }
        });

        // Botão voltar
        goBackButton = new JButton("Voltar");
        mainPanel.add(goBackButton);
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screenManager.showPersonalDataRegisterScreen();
            }
        });
    }
}

