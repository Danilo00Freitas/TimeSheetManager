package TimeSheetApp.FrontEnd.TsMgmtScreens;

import TimeSheetApp.BackEnd.DataBaseInteraction.DbClockInManager;
import TimeSheetApp.BackEnd.DataBaseInteraction.DbConnectionManager;
import TimeSheetApp.BackEnd.PersonalizedTimer;
import TimeSheetApp.BackEnd.ScreenManager;
import TimeSheetApp.BackEnd.TimeSheetManager;
import TimeSheetApp.FrontEnd.CustomFrontendThings.CustomTextField;
import org.apache.poi.ss.formula.atp.Switch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;


public class EntryScreen extends JFrame {
    private JPanel mainPanel;
    private JPanel timerPane;
    private JTextField dateTextF;
    private JTextField timetextF;
    private ArrayList<String> timeSheetRecorderlist = new ArrayList<>();
    private ScreenManager screenManager;
    private TimeSheetManager timeSheetManager;
    private int rowIndex = 0;
    private String userCpf;
    private String[] options = {"Selecione","Entrada","intervalo","retorno do intervalo", "saída"};
   private String[] reasons = {"Selecione","Dia de trabalho normal","feriado", "hora extra"};
   private DbConnectionManager dataBaseConnection = new DbConnectionManager();
   private DbClockInManager dbClockInManager = new DbClockInManager();


   private CustomTextField customTextField = new CustomTextField();

    public EntryScreen(ScreenManager screenManager, TimeSheetManager timeSheetManager) {
        // Inicializando o screemanager
        this.screenManager = screenManager;
        this.timeSheetManager = timeSheetManager;
        this.userCpf = screenManager.getUserCpf();

        // Configurar o layout do timerPane como GridLayout com 2 linhas e 1 coluna
        timerPane = new JPanel();
        timerPane.setLayout(new GridLayout(2, 1));
        dateTextF = new JTextField();
        timetextF = new JTextField();

        // Adicionar os componentes ao timerPane/container
        timerPane.add(dateTextF);
        timerPane.add(timetextF);
        Font font = new Font("Verdana", Font.PLAIN, 50);
        dateTextF.setFont(font);
        dateTextF.setHorizontalAlignment(SwingConstants.CENTER);
        timetextF.setFont(font);
        timetextF.setHorizontalAlignment(SwingConstants.CENTER);
        setLocationRelativeTo(null);

        // Criar e configurar o JFrame
        setTitle("TimeSheetApp");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 1)); // GridLayout com 2 linhas e 1 coluna
        getContentPane().add(mainPanel);

        mainPanel.add(timerPane);

        //Configurando botões e interações do programa.
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1)); // GridLayout com 1 linha e 4 colunas

        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String stringF = dateFormat.format(currentDate);

        JComboBox<String> entryOptions = new JComboBox<>(options);
        JComboBox<String> reasonOptions = new JComboBox<>(reasons);

        var justifyTxtField = customTextField.focusedTxtField("Insira a justificativa");

        JButton saveBtn = new JButton("Enviar batida de ponto");
        JButton returnToMenuBtn = new JButton("Voltar para Menu");

        buttonPanel.add(entryOptions);
        buttonPanel.add(reasonOptions);
        buttonPanel.add(justifyTxtField);
        buttonPanel.add(saveBtn);
        buttonPanel.add(returnToMenuBtn);
        mainPanel.add(buttonPanel);

        // Configurar a janela e torná-la visível
        setPreferredSize(new Dimension(600, 400)); // Defina o tamanho preferido desejado
        pack();
        setLocationRelativeTo(null); // Centralizar a janela na tela
        setResizable(false);

        // Inicializar a interface
        initializeUI();
        // Adicionando ação aos botões

        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String currentDate = dateTextF.getText();
                String currentTime = timetextF.getText();
                String currentType = (String)entryOptions.getSelectedItem();
                String currentJustification = justifyTxtField.getText();
                String currentReason = (String) reasonOptions.getSelectedItem();

                switch (currentType) {
                    case "Entrada":
                        try{
                            System.out.println(userCpf);
                        dbClockInManager.insertArrival(userCpf,currentDate,currentTime,currentType,currentJustification,currentReason);
                        }catch (Exception exception){
                            JOptionPane.showMessageDialog(null,"Erro ao enviar batida de ponto");
                            System.out.println(exception);
                        }
                        // Faça algo quando currentType for "Entrada"
                        break;
                    case "Intervalo":
                        // Faça algo quando currentType for "Intervalo"
                        break;
                    case "Retorno":
                        // Faça algo quando currentType for "Retorno"
                        break;
                    case "Saída":
                        // Faça algo quando currentType for "Saída"
                        break;
                    default:
                        // Se currentType não corresponder a nenhum dos casos
                        // Faça algo para a situação padrão ou adicione uma mensagem de erro
                        break;
                }


                //adicionar logica para o botão de salvar
            }
        });
        returnToMenuBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screenManager.showMenuScreen();
            }
        });
    }

    // Iniciar o timer para atualizar o campo de texto do horário
    public void initializeUI() {
        PersonalizedTimer localTimer = new PersonalizedTimer(timetextF);
        timetextF.setEditable(false);
        localTimer.startTimer();
        dateTextF.setText(localTimer.getDate());
        dateTextF.setEditable(false);
    }
}
