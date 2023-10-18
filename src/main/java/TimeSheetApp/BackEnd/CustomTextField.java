package TimeSheetApp.BackEnd;

import javax.swing.JTextField;
import java.awt.Color;

public class CustomTextField extends JTextField {

    public CustomTextField() {
        super();
    }
    public void setTextColor(Color color) {
        setForeground(color);
    }
}
