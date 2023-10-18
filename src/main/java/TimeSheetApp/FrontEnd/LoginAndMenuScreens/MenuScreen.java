package TimeSheetApp.FrontEnd.LoginAndMenuScreens;

import TimeSheetApp.BackEnd.ScreenManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuScreen extends JFrame {
    private JPanel mainPanel;
    private ScreenManager screenManager;
    private String cpf;

    public MenuScreen(ScreenManager screenManager) {

        //inicializando screen manager
        this.screenManager = screenManager;

        // Criando armação principal
        setTitle("TimeSheet Menu Screen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 800);
        setLocationRelativeTo(null); // Centralizar a janela na tela
        setResizable(false);

        // Criando painel principal onde os botões vão ficar
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 1));
        getContentPane().add(mainPanel);

        JButton newEntryBtn = new JButton("Corrijir marcação de ponto");
        newEntryBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screenManager.showEntryScreen();
            }
        });

        JButton changeEntryBtn = new JButton("Nova marcação de ponto");
        changeEntryBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screenManager.showChangeEntryScreen();
            }
        });

        mainPanel.add(newEntryBtn);
        mainPanel.add(changeEntryBtn);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Encerrar a aplicação quando a janela do menu for fechada
        /*setVisible(true);*/
    }
}
