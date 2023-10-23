package TimeSheetApp.FrontEnd.TsMgmtScreens;

import TimeSheetApp.BackEnd.ScreenManager;
import TimeSheetApp.BackEnd.TimeSheetManager;
import TimeSheetApp.FrontEnd.CustomFrontendThings.CustomTextField;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeEntryScreen extends JFrame {
    private JPanel mainPanel;
    private ScreenManager screenManager;
    private TimeSheetManager timeSheetManager;
    private int index = 0;
    private CustomTextField customTextField = new CustomTextField();

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
        c.fill = GridBagConstraints.BOTH; // Preencher tanto horizontal quanto verticalmente
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
        Font dateChooserFont = new Font("Arial", Font.PLAIN, 25); // Defina o tamanho da fonte desejado
        dateChooser.setFont(dateChooserFont);
        c.gridy = 1;
        c.weightx = 0.0;
        c.weighty = 0.3;
        mainPanel.add(dateChooser, c);

        JPanel changeEntryPanel = new JPanel();
        changeEntryPanel.setLayout(new GridLayout(4, 2));

        c.gridy = 2;
        c.gridheight = 1;
        c.weightx = 1.0; // Defina o peso para 1.0 para o changeEntryPanel ocupar mais espaço horizontalmente
        c.weighty = 1.0; // Defina o peso para 1.0 para o changeEntryPanel ocupar mais espaço verticalmente
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
