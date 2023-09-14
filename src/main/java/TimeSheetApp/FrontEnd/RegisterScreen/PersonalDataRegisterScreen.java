package TimeSheetApp.FrontEnd.RegisterScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import TimeSheetApp.BackEnd.ScreenManager;

public class PersonalDataRegisterScreen extends JFrame {
    private JPanel mainPanel;
    private JTextField cpfField;
    private JTextField nameField;
    private JTextField sectorField;
    private JTextField positionField;
    private JTextField immediateSupervisorField;
    private JTextField workRoutineField;
    private JTextField genderField;
    private JTextField phoneField;
    private JTextField birthDateField;
    private JButton registerButton;
    private JButton goBackButton;
    private ScreenManager screenManager;

    public PersonalDataRegisterScreen(ScreenManager screenManager) {
        // Inicializando o gerenciador de tela
        this.screenManager = screenManager;

        // Criando a janela principal
        setTitle("Cadastro de Usuário");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null); // Centralizar a janela na tela
        setResizable(false);

        // Criando o painel principal onde os componentes ficarão
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(11, 1));
        getContentPane().add(mainPanel);

        // Campo de entrada para CPF
        cpfField = new JTextField();
        cpfField.setBorder(BorderFactory.createTitledBorder("CPF"));
        mainPanel.add(cpfField);

        // Campo de entrada para nome
        nameField = new JTextField();
        nameField.setBorder(BorderFactory.createTitledBorder("Nome"));
        mainPanel.add(nameField);

        // Campo de entrada para setor
        sectorField = new JTextField();
        sectorField.setBorder(BorderFactory.createTitledBorder("Setor"));
        mainPanel.add(sectorField);

        // Campo de entrada para cargo
        positionField = new JTextField();
        positionField.setBorder(BorderFactory.createTitledBorder("Cargo"));
        mainPanel.add(positionField);

        // Campo de entrada para superior imediato
        immediateSupervisorField = new JTextField();
        immediateSupervisorField.setBorder(BorderFactory.createTitledBorder("Superior Imediato"));
        mainPanel.add(immediateSupervisorField);

        // Campo de entrada para rotina de trabalho
        workRoutineField = new JTextField();
        workRoutineField.setBorder(BorderFactory.createTitledBorder("Rotina de Trabalho"));
        mainPanel.add(workRoutineField);

        // Campo de entrada para gênero
        genderField = new JTextField();
        genderField.setBorder(BorderFactory.createTitledBorder("Gênero"));
        mainPanel.add(genderField);

        // Campo de entrada para telefone
        phoneField = new JTextField();
        phoneField.setBorder(BorderFactory.createTitledBorder("Telefone"));
        mainPanel.add(phoneField);

        // Campo de entrada para data de nascimento
        birthDateField = new JTextField();
        birthDateField.setBorder(BorderFactory.createTitledBorder("Data de Nascimento"));
        mainPanel.add(birthDateField);

        // Botão de cadastro
        registerButton = new JButton("Pŕoximo");
        mainPanel.add(registerButton);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screenManager.showAddressDataRegisterScreen();
            }
        });

        // Botão voltar
        goBackButton = new JButton("Voltar");
        mainPanel.add(goBackButton);
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screenManager.showLoginScreen();
            }
        });
    }
}

