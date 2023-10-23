package TimeSheetApp.FrontEnd.RegisterScreen;
import TimeSheetApp.BackEnd.DataBaseInteraction.DbLoginManager;
import TimeSheetApp.BackEnd.InfoValidator;
import TimeSheetApp.BackEnd.ScreenManager;
import TimeSheetApp.BackEnd.SystemIntegration.PersonalDataInformation;
import TimeSheetApp.FrontEnd.CustomFrontendThings.CustomTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class PersonalDataRegisterScreen extends JFrame {
    private JPanel mainPanel;
    private JTextField cpfField;
    private JTextField nameField;
    private JComboBox<String> sectorComboBox; // Usando JComboBox para a seleção de setor
    private JComboBox<String> positionComboBox;
    private JTextField immediateSupervisorField;
    private JComboBox<String> genderComboBox;
    private JComboBox<String> wrkRoutComboBox;
    private JTextField phoneField;
    private JTextField birthDateField;
    private JButton registerButton;
    private JButton goBackButton;
    private ScreenManager screenManager;
    private String cpf;
    private String nome;
    private String setor;
    private String cargo;
    private String superior;
    private String rotinaDeTrabalho;
    private String genero;
    private String telefone;
    private String dataDeNascimento;
    private InfoValidator infoValidator = new InfoValidator();

    public PersonalDataRegisterScreen(ScreenManager screenManager) {
        // Inicializando o gerenciador de tela
        this.screenManager = screenManager;

        // Criando a janela principal
        setTitle("Cadastro de Usuário");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 800);
        setLocationRelativeTo(null); // Centralizar a janela na tela
        setResizable(false);

        // Criando o painel principal onde os componentes ficarão
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(11, 1));
        getContentPane().add(mainPanel);

        // Campo de entrada para CPF
        cpfField = new CustomTextField();
        cpfField.setBorder(BorderFactory.createTitledBorder("CPF"));
        mainPanel.add(cpfField);
        cpfField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String cpfText = cpfField.getText();
                if (infoValidator.validateCpf(cpfText)) {
                    cpfField.setForeground(Color.BLACK);
                    DbLoginManager dbLoginManager = new DbLoginManager();
                    var result = dbLoginManager.checkRegisterExistence(cpfField.getText());
                    if (result){
                        JOptionPane.showMessageDialog(null, "Erro: Você já esta cadastrado! Faça login na tela de login"
                                , "Usuário já cadastrado", JOptionPane.ERROR_MESSAGE);
                        clearFields();
                        screenManager.showLoginScreen();
                    }
                } else {
                    cpfField.setForeground(Color.RED);
                }
            }
        });

        // Campo de entrada para nome
        nameField = new JTextField();
        nameField.setBorder(BorderFactory.createTitledBorder("Nome"));
        mainPanel.add(nameField);

        // Opções fictícias de setor
        String[] setores = {"Vendas", "Recursos Humanos", "TI", "Marketing", "Produção"};
        sectorComboBox = new JComboBox<>(setores);
        sectorComboBox.setBorder(BorderFactory.createTitledBorder("Setor"));
        mainPanel.add(sectorComboBox);

        // Campo de entrada para cargo
        positionComboBox = new JComboBox<>();
        positionComboBox.setBorder(BorderFactory.createTitledBorder("Cargo"));
        mainPanel.add(positionComboBox);

        sectorComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedSetor = (String) sectorComboBox.getSelectedItem();
                positionComboBox.removeAllItems();

                if ("Vendas".equals(selectedSetor)) {
                    String[] cargosVendas = {"Gerente de Vendas", "Representante de Vendas", "Executivo de Contas", "Consultor de Vendas", "Supervisor de Vendas"};
                    for (String cargo : cargosVendas) {
                        positionComboBox.addItem(cargo);
                    }
                } else if ("Recursos Humanos".equals(selectedSetor)) {
                    String[] cargosRH = {"Gerente de Recursos Humanos", "Analista de RH", "Especialista em Recrutamento", "Generalista de RH", "Coordenador de Treinamento e Desenvolvimento"};
                    for (String cargo : cargosRH) {
                        positionComboBox.addItem(cargo);
                    }
                } else if ("TI".equals(selectedSetor)) {
                    String[] cargosTI = {"Administrador de Sistemas", "Desenvolvedor de Software", "Analista de Segurança da Informação", "Engenheiro de Redes", "Gerente de Projetos de TI"};
                    for (String cargo : cargosTI) {
                        positionComboBox.addItem(cargo);
                    }
                } else if ("Marketing".equals(selectedSetor)) {
                    String[] cargosMarketing = {"Gerente de Marketing", "Especialista em Mídias Sociais", "Analista de Marketing Digital", "Gerente de Produto", "Coordenador de Eventos"};
                    for (String cargo : cargosMarketing) {
                        positionComboBox.addItem(cargo);
                    }
                } else if ("Produção".equals(selectedSetor)) {
                    String[] cargosProducao = {"Gerente de Produção", "Supervisor de Linha de Produção", "Operador de Máquina", "Técnico de Controle de Qualidade", "Engenheiro de Produção"};
                    for (String cargo : cargosProducao) {
                        positionComboBox.addItem(cargo);
                    }
                }
            }
        });

        // Campo de entrada para superior imediato
        immediateSupervisorField = new JTextField();
        immediateSupervisorField.setBorder(BorderFactory.createTitledBorder("Superior Imediato"));
        mainPanel.add(immediateSupervisorField);

        // Campo de entrada para rotina de trabalho
        String[] wrkRout = {"12x36 - Dia", "12x36 - Noite", "8x5", "6x1 - Dia", "6x1 - Noite"};
        wrkRoutComboBox = new JComboBox<>(wrkRout);
        wrkRoutComboBox.setBorder(BorderFactory.createTitledBorder("Rotina de trabalho"));
        mainPanel.add(wrkRoutComboBox);

        // Campo de entrada para gênero
        String[] gender = {"Masculino", "Feminino", "Não Binário", "Gênero Fluido", "Agênero", "Outros..."};
        genderComboBox = new JComboBox<>(gender);
        genderComboBox.setBorder(BorderFactory.createTitledBorder("Gênero"));
        mainPanel.add(genderComboBox);

        // Campo de entrada para telefone
        phoneField = new CustomTextField();
        phoneField.setBorder(BorderFactory.createTitledBorder("Telefone (escreva tudo junto ddd+numero)"));
        mainPanel.add(phoneField);
        phoneField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String phoneFieldString = phoneField.getText();
                if (infoValidator.validateCellPhoneNumber(phoneFieldString)) {
                    phoneField.setForeground(Color.BLACK);
                } else {
                    phoneField.setForeground(Color.RED);
                }
            }
        });

        // Campo de entrada para data de nascimento
        birthDateField = new CustomTextField();
        birthDateField.setBorder(BorderFactory.createTitledBorder("Data de Nascimento - ex 21032002) "));
        mainPanel.add(birthDateField);
        birthDateField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String birthDateString = birthDateField.getText();
                if (infoValidator.validateBirthDate(birthDateString)) {
                    birthDateField.setForeground(Color.BLACK);
                } else {
                    birthDateField.setForeground(Color.RED);
                }
            }
        });

        // Botão de cadastro
        clearFields();
        registerButton = new JButton("Próximo");
        mainPanel.add(registerButton);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var pdi = saveVariables();

                String errorMsg = infoValidator.pdiFinalValidation(pdi);
                if (!errorMsg.isEmpty()) {
                    JTextPane textPane = new JTextPane();
                    textPane.setText(errorMsg);
                    JOptionPane.showMessageDialog(null, new JScrollPane(textPane), "Erros", JOptionPane.ERROR_MESSAGE);

                } else {
                    // Se não houve erros, continue com o processamento
                    System.out.println("Cadastro de " + pdi.getNome() + " portador do CPF: " + pdi.getCpf() + " realizado");
                    System.out.println("PDI FUNCIONANDO:" + pdi.getNome());
                    screenManager.showAddressDataRegisterScreen(pdi);
                    clearFields();
                }
            }
        });

        // Botão voltar
        goBackButton = new JButton("Voltar");
        mainPanel.add(goBackButton);
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
                screenManager.showLoginScreen();
            }
        });
    }

    public PersonalDataInformation saveVariables() {
        this.cpf = cpfField.getText();
        this.nome = nameField.getText();
        this.setor = (String) sectorComboBox.getSelectedItem().toString();
        this.cargo = (String) positionComboBox.getSelectedItem().toString();
        this.superior = immediateSupervisorField.getText();
        this.rotinaDeTrabalho = (String) wrkRoutComboBox.getSelectedItem().toString();
        this.genero = (String) genderComboBox.getSelectedItem().toString();
        this.telefone = phoneField.getText();
        this.dataDeNascimento = birthDateField.getText();
        return new PersonalDataInformation(cpf, nome, setor, cargo, superior,
                rotinaDeTrabalho,genero, telefone, dataDeNascimento);

    }
    public void clearFields(){
        cpfField.setText("");
        nameField.setText("");
        immediateSupervisorField.setText("");
        sectorComboBox.setSelectedIndex(0);
        positionComboBox.setSelectedIndex(0);
        wrkRoutComboBox.setSelectedIndex(0);
        genderComboBox.setSelectedIndex(0);
        phoneField.setText("");
        birthDateField.setText("");
    }

}

