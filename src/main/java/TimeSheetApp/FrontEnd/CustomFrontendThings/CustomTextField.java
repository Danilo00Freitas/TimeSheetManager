package TimeSheetApp.FrontEnd.CustomFrontendThings;

import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.toedter.calendar.JDateChooser;

public class CustomTextField extends JTextField {

    public CustomTextField() {
        super();
    }
    public void setTextColor(Color color) {
        setForeground(color);
    }

    public JTextField focusedTxtField(String text){

        JTextField justificationTxtField = new JTextField(text);
        justificationTxtField.setHorizontalAlignment(JTextField.CENTER);
        Font titleFont = new Font("Arial", Font.BOLD, 15);
        justificationTxtField.setFont(titleFont);

        justificationTxtField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(justificationTxtField.getText().equals(text)){
                    justificationTxtField.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if(justificationTxtField.getText().equals("")){
                    justificationTxtField.setText(text);
                }
            }
        });
        return justificationTxtField;
    }

    public JDateChooser personalizedDateChooser(){
        JDateChooser dateChooser = new JDateChooser();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        dateChooser.setDateFormatString("dd/MM/yyyy");

        JTextField dateTextField = (JTextField) dateChooser.getDateEditor().getUiComponent();
        dateTextField.setText("Escolha uma data");
        dateTextField.setForeground(Color.GRAY);
        dateTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (dateTextField.getText().equals("Insira uma data")) {
                    dateTextField.setText("");
                    dateTextField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (dateTextField.getText().isEmpty()) {
                    dateTextField.setText("Insira uma data");
                    dateTextField.setForeground(Color.GRAY);
                }
            }
        });

        JTextField dateEditor = ((JTextField) dateChooser.getDateEditor().getUiComponent());

        dateEditor.setHorizontalAlignment(JTextField.CENTER);
        return dateChooser;
    }


}

