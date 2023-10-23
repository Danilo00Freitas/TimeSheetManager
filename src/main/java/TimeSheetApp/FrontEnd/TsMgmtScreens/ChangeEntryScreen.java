package TimeSheetApp.FrontEnd.TsMgmtScreens;

import TimeSheetApp.BackEnd.DataBaseInteraction.DbClockInManager;
import TimeSheetApp.BackEnd.ScreenManager;
import TimeSheetApp.BackEnd.TimeSheetManager;
import TimeSheetApp.FrontEnd.CustomFrontendThings.CustomTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ChangeEntryScreen extends JFrame {
    private JPanel mainPanel;
    private ScreenManager screenManager;
    private TimeSheetManager timeSheetManager;

    private CustomTextField customTextField = new CustomTextField();
    private DbClockInManager dbClockInManager = new DbClockInManager();

    private ArrayList<String> tempEntry = new ArrayList<>();
    private EntryValidator entryValidator = new EntryValidator();

    public ChangeEntryScreen(ScreenManager screenManager, TimeSheetManager timeSheetManager) {
        this.screenManager = screenManager;
        this.timeSheetManager = timeSheetManager;

        setTitle("Alterar marcação de ponto");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Crie um painel para conter todos os elementos
        mainPanel = new JPanel(new GridBagLayout());
        getContentPane().add(mainPanel);
        mainPanel.setPreferredSize(new Dimension(600, 400));

        // Use uma instância vazia para preencher qualquer espaço extra
        JPanel filler = new JPanel();
        GridBagConstraints fillerConstraints = new GridBagConstraints();
        fillerConstraints.weightx = 0;
        fillerConstraints.weighty = 0;
        fillerConstraints.fill = GridBagConstraints.BOTH;
        mainPanel.add(filler, fillerConstraints);

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(5, 5, 5, 5);

        JTextField dateToChange = new JTextField("Informe a data da correção.");
        dateToChange.setHorizontalAlignment(JTextField.CENTER);
        Font titleFont = new Font("Arial", Font.BOLD, 15);
        dateToChange.setFont(titleFont);
        dateToChange.setEditable(false);

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        mainPanel.add(dateToChange, c);

        var dateChooser = customTextField.personalizedDateChooser();
        Font dateChooserFont = new Font("Arial", Font.PLAIN, 25);
        dateChooser.setFont(dateChooserFont);
        c.gridy = 1;
        c.weightx = 0.0;
        c.weighty = 0.3;
        mainPanel.add(dateChooser, c);

        JPanel changeEntryPanel = new JPanel();
        changeEntryPanel.setLayout(new GridLayout(4, 2));

        c.gridy = 2;
        c.gridheight = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        mainPanel.add(changeEntryPanel, c);


        JTextField arrivalTxtField = new JTextField("Entrada");
        JTextField setArrivalTxtField = new JTextField();

        JTextField breakTxtField = new JTextField("Intervalo");
        JTextField setBreakTxtField = new JTextField();

        JTextField breakReturnTxtField = new JTextField("Retorno do intervalo");
        JTextField setBreakReturnTxtField = new JTextField();

        JTextField exitTxtField = new JTextField("Saída");
        JTextField setExitTxtField = new JTextField();

        changeEntryPanel.add(arrivalTxtField);
        changeEntryPanel.add(setArrivalTxtField);
        changeEntryPanel.add(breakTxtField);
        changeEntryPanel.add(setBreakTxtField);
        changeEntryPanel.add(breakReturnTxtField);
        changeEntryPanel.add(setBreakReturnTxtField);
        changeEntryPanel.add(exitTxtField);
        changeEntryPanel.add(setExitTxtField);

        JButton saveChangesBtn = new JButton("Salvar alterações");
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 1;
        c.weighty = 0.1;
        mainPanel.add(saveChangesBtn, c);

        //Configurando os campos de texto para que eles recebebam os dados do banco

        dateChooser.getDateEditor().addPropertyChangeListener("date", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("date".equals(evt.getPropertyName())) {
                    Date selectedDate = (Date) evt.getNewValue();

                    if (selectedDate != null) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        String formattedDate = dateFormat.format(selectedDate);
                        System.out.println("Data selecionada: " + formattedDate);


                        // Coloque aqui a ação que deseja executar quando uma data for selecionada
                        setArrivalTxtField.setText(dbClockInManager.getRegisters(screenManager.getUserCpf(), "Entrada",formattedDate));
                        tempEntry.add(setArrivalTxtField.getText());
                        setBreakTxtField.setText(dbClockInManager.getRegisters(screenManager.getUserCpf(), "Intervalo",formattedDate));
                        tempEntry.add(setBreakTxtField.getText());
                        setBreakReturnTxtField.setText(dbClockInManager.getRegisters(screenManager.getUserCpf(), "Retorno do intervalo",formattedDate));
                        tempEntry.add(setBreakReturnTxtField.getText());
                        setExitTxtField.setText(dbClockInManager.getRegisters(screenManager.getUserCpf(), "Saída",formattedDate));
                    }tempEntry.add(setExitTxtField.getText());
                }
            }
        });

        saveChangesBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //adicione a ação

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date currentDate = dateChooser.getDate();
                String currentFormatedDate = dateFormat.format(currentDate);

                String currentArrival = setArrivalTxtField.getText();
                String currentbreak = setBreakTxtField.getText();
                String currentBreakReturn = setBreakReturnTxtField.getText();
                String currentExit = setExitTxtField.getText();


                try{

                    if (entryValidator.validateTimeEntrys(currentArrival, currentbreak, currentBreakReturn, currentExit)){
                        if(!tempEntry.isEmpty()){
                            if (!currentArrival.equals(tempEntry.get(0))){
                                dbClockInManager.updateTimeRegister(screenManager.getUserCpf(),currentFormatedDate,currentArrival,"Entrada");
                            }
                            if (!currentbreak.equals(tempEntry.get(1))) {
                                dbClockInManager.updateTimeRegister(screenManager.getUserCpf(),currentFormatedDate,currentbreak,"Intervalo");
                            }
                            if (!currentBreakReturn.equals(tempEntry.get(2))) {
                                dbClockInManager.updateTimeRegister(screenManager.getUserCpf(),currentFormatedDate,currentBreakReturn,"Retorno do intervalo");
                            }
                            if (!currentExit.equals(tempEntry.get(3))) {
                                dbClockInManager.updateTimeRegister(screenManager.getUserCpf(),currentFormatedDate,currentExit,"Saída");
                            }

                            JOptionPane.showMessageDialog(null,"Batidas inseridas com sucesso");
                        }else{
                            JOptionPane.showMessageDialog(null,"Voce precisa ter modificado o ponto para poder salvar as alterações");
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"Data inválida");
                    }


                }catch (Exception e1){
                    JOptionPane.showMessageDialog(null,e1);
                }

            }
        });

        JButton returnToMenuScreenBtn = new JButton("Voltar para Menu");
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 1;
        c.weighty = 0.1;
        mainPanel.add(returnToMenuScreenBtn, c);
        returnToMenuScreenBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screenManager.showMenuScreen();
            }
        });

        // Adicione outro preenchedor para ocupar espaço extra abaixo dos botões
        JPanel bottomFiller = new JPanel();
        GridBagConstraints bottomFillerConstraints = new GridBagConstraints();
        bottomFillerConstraints.weighty = 0.0;
        bottomFillerConstraints.fill = GridBagConstraints.BOTH;
        mainPanel.add(bottomFiller, bottomFillerConstraints);
        pack();
        setLocationRelativeTo(null);
    }

}
