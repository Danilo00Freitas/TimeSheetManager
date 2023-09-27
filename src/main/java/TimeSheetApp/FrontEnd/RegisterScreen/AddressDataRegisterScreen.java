package TimeSheetApp.FrontEnd.RegisterScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import TimeSheetApp.BackEnd.ScreenManager;
import TimeSheetApp.BackEnd.SystemIntegration.CepInformation;
import TimeSheetApp.BackEnd.SystemIntegration.CepInformationRec;
import TimeSheetApp.BackEnd.SystemIntegration.NewHttpRequest;

public class AddressDataRegisterScreen extends JFrame {
    private JPanel mainPanel;
    private JTextField cepField;
    private JTextField logradouroField;
    private JTextField numberField;
    private JTextField complementoField;
    private JTextField bairroField;
    private JTextField localidadeField;
    private JTextField ufField;
    private JButton registerButton;
    private JButton goBackButton;
    private ScreenManager screenManager;
    private NewHttpRequest newHttpRequest = new NewHttpRequest();

    private CepInformationRec cepInformationRec = new CepInformationRec("","","","","","");
    private CepInformation cepInformation = new CepInformation(cepInformationRec);
    private FieldInfoValidator fieldInfoValidator;

    public AddressDataRegisterScreen(ScreenManager screenManager) {

        this.screenManager = screenManager;
        fieldInfoValidator = new FieldInfoValidator();

        // Criando a janela principal
        setTitle("Cadastro de Endereço");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null); // Centralizar a janela na tela
        setResizable(false);

        // Criando o painel principal onde os componentes ficarão
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(9, 1));
        getContentPane().add(mainPanel);

        // Campo de entrada para CEP
        cepField = new JTextField();
        cepField.setBorder(BorderFactory.createTitledBorder("CEP"));
        mainPanel.add(cepField);

        // Campo de entrada para logradouro
        logradouroField = new JTextField();
        logradouroField.setBorder(BorderFactory.createTitledBorder("Logradouro"));
        mainPanel.add(logradouroField);

        // Campo de entrada para numero
        numberField = new JTextField();
        numberField.setBorder(BorderFactory.createTitledBorder("Numero"));
        mainPanel.add(numberField);

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
                    CepInformation cepInformation = new CepInformation(cepInfo);
                    logradouroField.setText(cepInformation.getLogradouro().toString());
                    complementoField.setText(cepInformation.getComplemento().toString());
                    bairroField.setText(cepInformation.getBairro().toString());
                    localidadeField.setText(cepInformation.getLocalidade().toString());
                    ufField.setText(cepInformation.getUf().toString());
                    System.out.println(cepInfo);
                    saveVariables(cepInformation);
                }
            }
        });

        // Botão de cadastro
        registerButton = new JButton("Próximo");
        mainPanel.add(registerButton);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cepInformation.setNumero(numberField.getText());

                try{
                    if (fieldInfoValidator.validateADRSfield(cepInformation)) {
                        System.out.println("PDI FUNCIONANDO 2: " + screenManager.getPdi().getNome());
                        screenManager.showLoginDataRegisterScreen(cepInformation);
                        clearFields();
                    }else {
                        JOptionPane.showMessageDialog(null, "Erro: Você não preencheu todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }catch (Exception exception){
                    JOptionPane.showMessageDialog(null, "Erro: " + exception.getMessage(), "Exceção", JOptionPane.ERROR_MESSAGE);
                }


            }
        });

        // Botão voltar
        goBackButton = new JButton("Voltar");
        mainPanel.add(goBackButton);
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screenManager.showPersonalDataRegisterScreen();
                clearFields();
            }
        });
    }
    public void saveVariables(CepInformation cepInfo){
        this.cepInformation = cepInfo;
    }

    public void clearFields(){
        cepField.setText("");
        logradouroField.setText("");
        ufField.setText("");
        numberField.setText("");
        complementoField.setText("");
        bairroField.setText("");
        localidadeField.setText("");
    }

}

