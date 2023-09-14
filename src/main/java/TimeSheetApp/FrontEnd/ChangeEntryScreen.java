package TimeSheetApp.FrontEnd;

import TimeSheetApp.BackEnd.ScreenManager;
import TimeSheetApp.BackEnd.TimeSheetManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChangeEntryScreen extends JFrame {
    private JPanel mainPanel;
    private ScreenManager screenManager;
    private TimeSheetManager timeSheetManager;
    private int index = 0;

    public ChangeEntryScreen(ScreenManager screenManager, TimeSheetManager timeSheetManager) {
        // Inicializando o screemanager
        this.screenManager = screenManager;
        this.timeSheetManager = timeSheetManager;

        // Criar armação principal
        setTitle("Alterar marcação de ponto");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Criar painel principal onde os botões vão ficar
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(10, 1));
        getContentPane().add(mainPanel);

        // Configurar a janela
        setPreferredSize(new Dimension(600, 400)); // Defina o tamanho preferido desejado
        pack();
        setLocationRelativeTo(null); // Centralizar a janela na tela
        setResizable(false);

        //Informando a data alterada
        JTextField dateToChange = new JTextField("Informe a data da correção.");
        dateToChange.setHorizontalAlignment(JTextField.CENTER);
        Font titleFont = new Font("Arial", Font.BOLD, 15);
        dateToChange.setFont(titleFont);
        dateToChange.setEditable(false);

        JTextField dateToChangeTxt = new JTextField("dd/mm/aaaa");
        dateToChangeTxt.setHorizontalAlignment(JTextField.CENTER);
        dateToChangeTxt.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (dateToChangeTxt.getText().isEmpty()) {
                    dateToChangeTxt.setText("dd/mm/aaaa");
                }
            }
        });
        mainPanel.add(dateToChange);
        mainPanel.add(dateToChangeTxt);

        // Informando o horário que será alterado
        JTextField timeToChange = new JTextField("Insira a correção de horário.");
        timeToChange.setHorizontalAlignment(JTextField.CENTER);
        timeToChange.setFont(titleFont);
        timeToChange.setEditable(false);

        JTextField timeToChangeTxt = new JTextField("hh:mm:ss");
        timeToChangeTxt.setHorizontalAlignment(JTextField.CENTER);
        timeToChangeTxt.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (timeToChangeTxt.getText().isEmpty()) {
                    timeToChangeTxt.setText("hh:mm:ss");
                }
            }
        });
        mainPanel.add(timeToChange);
        mainPanel.add(timeToChangeTxt);

        //opções de marcação

        JRadioButton entryBtn = new JRadioButton("Entrada");
        entryBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                index = 1;
            }
        });

        JRadioButton breakBtn = new JRadioButton("Saída Intervalo");
        String excelDirPath = "/home/danilo/Desktop/estudos/JAVA/TImeSheet";
        breakBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                index = 2;
            }
        });

        JRadioButton breakReturnBtn = new JRadioButton("Retorno intervalo");
        breakReturnBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                index = 3;
            }
        });

        JRadioButton exitBtn = new JRadioButton("Saída");
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                index = 4;
            }
        });

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(entryBtn);
        buttonGroup.add(breakBtn);
        buttonGroup.add(breakReturnBtn);
        buttonGroup.add(exitBtn);

        mainPanel.add(entryBtn);
        mainPanel.add(breakBtn);
        mainPanel.add(breakReturnBtn);
        mainPanel.add(exitBtn);

        //Save Changes Button
        JButton saveChangesBtn = new JButton("Salvar alterações");


        saveChangesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String changeDate = dateToChangeTxt.getText();
                String changeTime = timeToChangeTxt.getText();
                JOptionPane.showMessageDialog(null, "As alterações foram salvas.");
                System.out.println(index);
                timeSheetManager.changeEntry(changeDate,index,changeTime);
            }
        });

        mainPanel.add(saveChangesBtn);

        // Criar e adicionar o botão returnToMenuScreen
        JButton returnToMenuScreenBtn = new JButton("Voltar para Menu");
        returnToMenuScreenBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screenManager.showMenuScreen();
            }
        });

        mainPanel.add(returnToMenuScreenBtn);


    }
}
