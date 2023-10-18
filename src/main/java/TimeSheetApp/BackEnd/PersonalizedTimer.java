package TimeSheetApp.BackEnd;

import javax.swing.JTextField;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class PersonalizedTimer {

    private JTextField timeTxt;
    private JTextField dateTxt;

    public PersonalizedTimer(JTextField timeTxt) {
        this.timeTxt = timeTxt;
        this.dateTxt = new JTextField();
        SimpleDateFormat dateFormat = null;
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateTxt.setText(dateFormat.format(new Date()));
    }

    public String getDate() {
        return dateTxt.getText();
    }

    public void startTimer() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                updateTime();
            }
        };
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(task, 0, 1000);
    }

    private void updateTime() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        Date currentTime = new Date();
        timeTxt.setText(timeFormat.format(currentTime));
    }



}