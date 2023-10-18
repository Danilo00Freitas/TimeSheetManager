package TimeSheetApp.FrontEnd.CustomFrontendThings;

import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

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
                if(justificationTxtField.getText().equals("Insira a justificativa")){
                    justificationTxtField.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if(justificationTxtField.getText().equals("")){
                    justificationTxtField.setText("Insira a justificativa");
                }
            }
        });
        return justificationTxtField;
    }
}

